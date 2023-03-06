package com.xworkz.finalproject.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xworkz.finalproject.dto.ProjectDTO;
import com.xworkz.finalproject.service.ProjectService;

@Controller
@RequestMapping("/mail")
public class EmailMapToProductController {
	@Autowired
	private ProjectService projectService;

	@GetMapping
	public String getProductEmail(HttpServletRequest req, Model model) {
		String email = req.getParameter("email");
		List<ProjectDTO> dto = projectService.uniqueEmail(email);
		if (dto != null) {
			model.addAttribute("Data", dto);
			return "Product";
		} else {
			return "index";
		}

	}

}
