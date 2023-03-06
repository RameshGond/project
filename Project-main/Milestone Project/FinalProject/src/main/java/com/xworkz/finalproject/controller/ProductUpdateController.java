package com.xworkz.finalproject.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.xworkz.finalproject.dto.ProductDTO;
import com.xworkz.finalproject.dto.ProjectDTO;
import com.xworkz.finalproject.product.service.ProductService;
import com.xworkz.finalproject.service.ProjectService;

@Controller
@RequestMapping("/updateProduct")
public class ProductUpdateController {
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProjectService projectService;
	
@PostMapping	
public String updateProduct(ProductDTO product,Model model, @RequestParam("image") MultipartFile file)
		throws IOException {
	byte[] bytes = file.getBytes();
	String string = System.currentTimeMillis() + "_" + file.getOriginalFilename();
	Path path = Paths.get("E:/temp-files/" + string);
	Files.write(path, bytes);
	product.setFileName(string);
	System.out.println("Calling Update Controller");
	Boolean dto=productService.updateProductImageAndPriceByEmailAndName(product.getFileName(),product.getPrice(), product.getProductName(), product);
	ProjectDTO dtos = projectService.getByEmail(product.getEmail());
	if(dto) {
		System.out.println("Details Updated Successfully");
		model.addAttribute("msg", "Details Updated Successfullt");
		model.addAttribute("Data",dtos);
		return "DisplayProductDetails";
	}
	else {
		System.out.println("Invalid Info");
		model.addAttribute("msg","Details Updation Unsuccessfull");
		return "DisplayProductDetails";
	}
	
}
}
