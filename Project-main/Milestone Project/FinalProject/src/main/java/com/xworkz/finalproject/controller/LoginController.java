package com.xworkz.finalproject.controller;

import java.util.List;
import java.util.logging.Logger;

import static com.xworkz.finalproject.loggers.ProjectLogger.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xworkz.finalproject.dto.ProjectDTO;
import com.xworkz.finalproject.service.ProjectService;

@Controller
@RequestMapping("/")
public class LoginController {
	Logger logger=getLogger();
	@Autowired
	private ProjectService projectService;

	public LoginController() {
		logger.info("Calling Default Constructor of Login Controller");
	}

	@PostMapping("/login")
	public String onRequest(ProjectDTO project, Model model) {
		logger.info("Calling On Request Method");
		List<ProjectDTO> find = projectService.findByMailAndPassword(project.getEmail(), project.getPassword(), project,
				model);
		logger.info("find data" + find);
		if (find == null) {
			model.addAttribute("msg", "Incorrect Credentials entered");
		} else {
			if (find.get(0).getStatus().equals("Blocked")) {
				model.addAttribute("msg", "Your Account has been Locked");
				model.addAttribute("mesg", "Please Reset Your Password");
			} else {
				model.addAttribute("msg", "Welcome to your account");
				model.addAttribute("name", find);
				return "Welcome";
			}

		}
		return "SignIn";

	}

	@GetMapping("/otp")
	public String generateOTP(ProjectDTO dto, Model model) {
		Boolean project = projectService.generateOTPByEmail(dto.getEmail(), dto);
		if (project != null) {
			logger.info("OTP Genrated Successfully");
			model.addAttribute("msg", "OTP Generated Successfully");
			return "ResetPassword";
		} else {
			logger.info("Incorrect Email,Enter New Email");
			model.addAttribute("msg", "Incorrect Email,Enter New Email");
			return "OTPGenerator";
		}
	}

	@GetMapping("/pass")
	public String resetPassword(ProjectDTO project, Model model) {
		Boolean dto = projectService.resetPasswordByEmailAndOTP(project.getPassword(), project.getEmail(),
				project.getOtp(), project);
		logger.info("DTO in Controller" + dto);
		if (dto) {
			logger.info("Password Reset Successfull");
			model.addAttribute("msg", "Password Reset Successfull,Account Unlocked");
			return "SignIn";
		} else {
			logger.info("Password reset unsuccessfull");
			model.addAttribute("msg", "Password Reset Unsuccessfull");
			return "index";
		}

	}

}
