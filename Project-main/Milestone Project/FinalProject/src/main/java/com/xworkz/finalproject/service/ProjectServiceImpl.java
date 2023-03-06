package com.xworkz.finalproject.service;

import static com.xworkz.finalproject.loggers.ProjectLogger.getLogger;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.xworkz.finalproject.dao.ProjectDAO;
import com.xworkz.finalproject.dto.ProjectDTO;

@Service
public class ProjectServiceImpl implements ProjectService {
	Logger logger = getLogger();

	@Autowired
	private ProjectDAO projectDAO;

	private LocalDateTime mailDateAndTime;

	Integer count = 0;

	@Override
	public Boolean validateAndSave(ProjectDTO project) {
		ValidatorFactory validator = Validation.buildDefaultValidatorFactory();
		Validator valid = validator.getValidator();
		Set<ConstraintViolation<ProjectDTO>> set = valid.validate(project);
		if (set.size() > 0) {
			logger.info("Details Entered are invalid");
			return false;
		} else {
			System.out.println("details entered are valid");
			String passwordGenerator = encryptPassword();
			String status = "Unblocked";
			Integer count = 0;
			if (!passwordGenerator.isEmpty()) {
				logger.info("Password is not empty");
				project.setPassword(passwordGenerator);
				project.setStatus(status);
				project.setCount(count);
				Boolean save = projectDAO.save(project);
				if (save) {
					project.setPassword(decryptPassword(passwordGenerator));
					sendEmail(project.getEmail(), project.getPassword(), project);
				}
				System.out.println(save);

			}
			return true;
		}

	}

	@Override
	public List<ProjectDTO> uniqueEmail(String email) {
		logger.info("Calling uniqueEmail method from service");
		List<ProjectDTO> unique = projectDAO.uniqueEmail(email);
		if (unique != null) {
			return unique;
		} else {
			return null;
		}
	}

	@Override
	public Boolean sendEmail(String email, String decPassword, ProjectDTO project) {
		String name = project.getUserName();

		// Recipients email needs to be mentioned
		String to = email;

		// Senders email needs to be mentioned
		String from = "pratheekkc13@outlook.com";

		// Assuming that you are sending mail through outlook smtp
		String host = "smtp.office365.com";

		// password for the mail
		String password = "pratheek123@";

		// get System properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.debug", "true");
		properties.put("mail.transport.protocol", "smtp");

		// Get the Session Object.//and pass username and password
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});

		// used to debug smtp issues
		session.setDebug(true);

		try {
			// Create a default mime message object.
			MimeMessage message = new MimeMessage(session);

			// Set From header field of the header
			message.setFrom(new InternetAddress(from));

			// Set To Header Field of the header
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject header field
			message.setSubject("Registration Confirmation mail");

			// Set the actual message
			message.setText("Hi" + " " + name + " " + "This is to confirm that your Registration was successful." + "\n"
					+ "Your Password is:" + decPassword + "\n" + "Thank You");

			logger.info("Sending......");
			// Send message
			Transport.send(message);
			logger.info("Message Sent Successfully");

		} catch (MessagingException max) {
			max.printStackTrace();
		}
		return null;
	}

	@Override
	public List<ProjectDTO> findByMailAndPassword(String email, String password, ProjectDTO dto, Model model) {

		logger.info("Calling findByMailAndPassword in Service");
		ValidatorFactory facotry = Validation.buildDefaultValidatorFactory();
		Validator validator = facotry.getValidator();
		Set<ConstraintViolation<String>> set = validator.validate(password);
		if (set.size() > 0) {
			System.out.println("Details Entered are invalid");
		} else {
			String password2 = password;
			String encryptPassword = encryptPassword(password2);
			dto.setPassword(encryptPassword);
			List<ProjectDTO> project1 = this.projectDAO.findByMailAndPassword(dto.getEmail(), dto.getPassword());
			if (project1 != null) {
				this.count = 0;
				this.projectDAO.updateCountByEmail(count, email);
				model.addAttribute("msg", "Login Successfull,Welome to your Account");
				return project1;
			} else if (this.count <= 3) {
				this.count++;
				logger.info("Checking the password" + this.count);
				this.projectDAO.updateCountByEmail(count, email);
			}
			if (count > 3) {
				logger.info("Your Account has been locked");
				dto.setStatus("Blocked");
				dto.setCount(0);
				this.projectDAO.updateCountByEmail(count, email);
				this.projectDAO.updateStatusByPassword(email, dto.getStatus());
			}
			return project1;

		}
		return null;
	}

	@Override
	public String passwordGenerator() {
		String upperCase = "QWERTYUIOPASDFGHJKLZXCVBNM";
		String lowerCase = "qwertyuiopasdfghjklzxcvbnm";
		String num = "0123456789";
		String specialChar = "!@#$%&";
		String combination = upperCase + lowerCase + num + specialChar;
		int length = 12;
		char[] password = new char[length];
		Random ran = new Random();
		for (int i = 0; i < length; i++) {
			password[i] = combination.charAt(ran.nextInt(combination.length()));

		}
		return new String(password);
	}

	@Override
	public void updateStatusByPassword(String email, String status) {
		logger.info("Calling updatestatusByPassword");
		Boolean updateStatus = projectDAO.updateStatusByPassword(email, status);
		System.out.println(updateStatus);

	}

	@Override
	public void updateCountByEmail(Integer count, String email) {
		logger.info("Calling updateCountById");
		Boolean updatecount = projectDAO.updateCountByEmail(count, email);
		System.out.println(updatecount);

	}

	@Override
	public Boolean resetPasswordByEmailAndOTP(String password, String email, Integer otp, ProjectDTO project) {
		logger.info("Calling ResetPasswordMethod");
		if (password.length() < 10 && password.length() > 12 && password == null) {
			logger.info("Password length is incorrect");
		} else {
			logger.info("Password is valid");
			List<ProjectDTO> dto = projectDAO.uniqueEmail(email);
			LocalTime getTime = dto.get(0).getTime();
			LocalTime systemTime = LocalTime.now();
			Duration between = Duration.between(getTime, systemTime);
			System.out.println(getTime);
			System.out.println(systemTime);
			System.out.println(between);
			long minutes = between.toMinutes();
			System.out.println(minutes);
			logger.info("Reset Password DTO is" + dto);
			if (dto == null) {
				System.out.println("Find by Email is Null");
				return null;
			} else {
				if (dto != null && dto.get(0).getOtp().equals(otp) && minutes < 3) {

					project.setStatus("Unblocked");
					project.setPassword(password);
					project.setCount(0);
					projectDAO.resetPasswordByEmailAndOTP(email, project.getPassword(), project.getStatus(),
							project.getCount(), otp);
					logger.info("Password is Reset and Account has been Unlocked");
					return true;
				} else {
					logger.info("Error in Resetting the password");
					logger.info("OTP has Expired");
				}

			}
		}

		return false;
	}

	@Override
	public Integer OTPGenerator() {
		System.out.println("Calling OTP generator method");
		int random = (int) (Math.random() * 9000) + 1000;
		Integer otp = Integer.valueOf(random);
		return otp;
	}

	@Override
	public Boolean generateOTPByEmail(String email, ProjectDTO dto) {
		logger.info("Calling Generate OTP By Email");
		Integer otp = this.OTPGenerator();
		dto.setOtp(otp);
		mailDateAndTime = LocalDateTime.now();
		LocalDate date = mailDateAndTime.toLocalDate();
		LocalTime time = mailDateAndTime.toLocalTime();
		System.out.println(mailDateAndTime);
		dto.setDate(date);
		dto.setTime(time);
		Boolean otpsent = projectDAO.generateOTPByEmail(email, otp, time, date);
		if (otpsent) {
			Boolean sendingOTP = this.sendOTP(email, otp);
			if (sendingOTP) {
				logger.info("OTP Sent to Mail Successfully");
			} else {
				logger.info("OTP Not Sent,Unable to Send Mail");
			}
		}
		return true;

	}

	@Override
	public Boolean sendOTP(String email, Integer otp) {

		// Recipients email needs to be mentioned
		String to = email;
		Integer otpSent = otp;

		// Senders email needs to be mentioned
		String from = "pratheekkc13@outlook.com";

		// Assuming that you are sending mail through outlook smtp
		String host = "smtp.office365.com";

		// password for the mail
		String password = "pratheek123@";

		// get System properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.debug", "true");
		properties.put("mail.transport.protocol", "smtp");

		// Get the Session Object.//and pass username and password
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});

		// used to debug smtp issues
		session.setDebug(true);

		try {
			// Create a default mime message object.
			MimeMessage message = new MimeMessage(session);

			// Set From header field of the header
			message.setFrom(new InternetAddress(from));

			// Set To Header Field of the header
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject header field
			message.setSubject("Registration Confirmation mail");

			// Set the actual message
			message.setText("Hi Your OTP to reset password is: " + otpSent);

			System.out.println("Sending......");
			// Send message
			Transport.send(message);
			System.out.println("OTP Sent Successfully");

		} catch (MessagingException max) {
			max.printStackTrace();
		}
		return true;
	}

	@Override
	public Boolean updateDetailsByEmail(String email, String name, String number) {
		logger.info("Calling Update Details by Email in Service");
		if (number.length() == 10 && name.length() > 3 && name.length() < 30) {
			projectDAO.updateDetailsByEmail(email, name, number);
			return true;
		} else {
			System.out.println("Incorrect details");
			return false;
		}
	}

	@Override
	public String encryptPassword() {
		Encoder encode = Base64.getEncoder();
		String password = passwordGenerator();
		String encodeToString = encode.encodeToString(password.getBytes());
		return encodeToString;

	}

	@Override
	public String encryptPassword(String password) {
		Encoder encode = Base64.getEncoder();
		String password1 = password;
		String encodeToString = encode.encodeToString(password1.getBytes());
		return encodeToString;

	}

	@Override
	public String decryptPassword(String password) {
		Decoder decoder = Base64.getDecoder();
		byte[] decodePassword = decoder.decode(password);
		String decryptedPassword = new String(decodePassword);
		System.out.println("Decrypted password:" + decryptedPassword);
		return decryptedPassword;
		// F75B4CqIAIXf
	}

	@Override
	public ProjectDTO getByEmail(String email) {
		System.out.println("Running Get By Email");
			ProjectDTO byEmail = projectDAO.getByEmail(email);
			System.out.println("DAta For Product"+byEmail);
			return byEmail;
	}
}
