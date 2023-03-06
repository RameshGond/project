package com.xworkz.finalproject.controller;

import java.io.File;
import java.util.List;
import static com.xworkz.finalproject.loggers.ProjectLogger.*;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.xworkz.finalproject.dto.ProjectDTO;
import com.xworkz.finalproject.service.ProjectService;

@Controller
@RequestMapping("/project")
public class ProjectController {
	Logger logger=getLogger();
	@Autowired
	private ProjectService projectService;

	public ProjectController() {
		logger.info("Calling Default Constructor of ..........." + this.getClass().getSimpleName());
	}

	@PostMapping
	public String onSave(ProjectDTO project, Model model, @RequestParam("image") MultipartFile file)
			throws IOException {
		System.out.println("Calling onSave Method");
		byte[] bytes = file.getBytes();
		String string = System.currentTimeMillis() + "_" + file.getOriginalFilename();
		Path path = Paths.get("E:/temp-files/" + string);
		Files.write(path, bytes);
		project.setFileName(string);
		logger.info("Details Entered Are:" + project);
		if (project != null) {
			List<ProjectDTO> findByEmail = projectService.uniqueEmail(project.getEmail());
			if (findByEmail !=null) {
				model.addAttribute("message", "Email Already Exists,Enter Another mail Id");
			} else {
				Boolean save = projectService.validateAndSave(project);
				if (save) {
					logger.info("The values are Sent to service:" + save);
					model.addAttribute("message", "User has Succefully Signed Up");
				} else {
					logger.info("Details Are Incorrect");
					model.addAttribute("message", "Incorrect Details");
				}

			}
		}

		return "SignUp";

	}

	@GetMapping("**/image")
	public void sendFile(@RequestParam String fileName, HttpServletResponse response) throws IOException {
		logger.info("running sendFile..." + fileName);
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
