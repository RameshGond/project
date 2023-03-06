package com.xworkz.finalproject.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.xworkz.finalproject.dto.ProductDTO;
import com.xworkz.finalproject.dto.ProjectDTO;

public interface ProjectDAO {
	Boolean save(ProjectDTO project);

	List<ProjectDTO> uniqueEmail(String email);
	
	ProjectDTO getByEmail(String email);

	List<ProjectDTO> findByMailAndPassword(String email, String password);

	Boolean updateStatusByPassword(String email, String status);

	Boolean updateCountByEmail(Integer count, String email);

	Boolean resetPasswordByEmailAndOTP(String email, String password, String status, Integer count, Integer otp);

	Boolean generateOTPByEmail(String email, Integer otp, LocalTime time, LocalDate date);

	Boolean updateDetailsByEmail(String email, String name, String number);

}
