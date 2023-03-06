package com.xworkz.finalproject.service;

import java.util.List;

import org.springframework.ui.Model;

import com.xworkz.finalproject.dto.ProductDTO;
import com.xworkz.finalproject.dto.ProjectDTO;

public interface ProjectService {

	String passwordGenerator();

	Boolean validateAndSave(ProjectDTO project);

	Boolean sendEmail(String email, String password, ProjectDTO project);
	
	ProjectDTO getByEmail(String email);

	List<ProjectDTO> uniqueEmail(String email);

	List<ProjectDTO> findByMailAndPassword(String email, String password, ProjectDTO dto, Model model);

	void updateStatusByPassword(String email, String status);

	void updateCountByEmail(Integer count, String email);

	Boolean resetPasswordByEmailAndOTP(String password, String email, Integer otp, ProjectDTO project);

	Integer OTPGenerator();

	Boolean generateOTPByEmail(String email, ProjectDTO dto);

	Boolean sendOTP(String email, Integer otp);

	Boolean updateDetailsByEmail(String email, String name, String number);

	String encryptPassword();
	
	String encryptPassword(String pzssowrd);

	String decryptPassword(String password);

	// Boolean updatePasswordUsingOTP(String email,String password,Integer otp);
	// String signIn(ProjectDTO dto,Model model);

}
