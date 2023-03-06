package com.xworkz.finalproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import static com.xworkz.finalproject.loggers.ProjectLogger.*;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xworkz.finalproject.dto.ProjectDTO;
import com.xworkz.finalproject.service.ProjectService;

@Controller
@RequestMapping("/show")
public class UpdateController {
	Logger logger = getLogger();

	@Autowired
	private ProjectService projectService;

	public UpdateController() {
		logger.info("Calling Default Controller of update Controller");
	}

	@GetMapping("/edit")
	public String updateDetailsByEmail(ProjectDTO dto, Model model) {
		Boolean project = projectService.updateDetailsByEmail(dto.getEmail(), dto.getUserName(), dto.getNumber());
		System.out.println(project);
		if (project) {
			logger.info("Details Updated Successfully");
			model.addAttribute("msg", "Details Updated Successfully");
			return "SignIn";
		} else {
			logger.info("Incorrect Deatils");
			model.addAttribute("msg", "Incorrect Details Entered");
			return "EditDetails";

		}
	}

	@GetMapping("/prath")
	public String getEmail(HttpServletRequest req, Model model) {
		String email = req.getParameter("email");
		List<ProjectDTO> dto = projectService.uniqueEmail(email);
		if (dto != null) {
			model.addAttribute("Data", dto);
			return "EditDetails";
		} else {
			return "index";
		}

	}
	
	@GetMapping("**/image")
	public void sendFile(@RequestParam String fileName, HttpServletResponse response) throws IOException {
		System.out.println("running sendFile..." + fileName);
		// file name from DB
		// Path can be static
		File file = new File("E://temp-files/" + fileName);
		String mimeType = URLConnection.guessContentTypeFromName(file.getName());
		response.setContentType(mimeType);
		try (OutputStream stream = response.getOutputStream()) {
			stream.write(Files.readAllBytes(file.toPath()));
		}
	}

}
