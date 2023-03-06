package com.xworkz.finalproject.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.xworkz.finalproject.dto.ProductDTO;
import com.xworkz.finalproject.dto.ProjectDTO;
import com.xworkz.finalproject.product.service.ProductService;
import com.xworkz.finalproject.service.ProjectService;

@Controller
@RequestMapping("addp")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProjectService projectService;

	@PostMapping
	public String productSave(ProductDTO dto, Model model, @RequestParam("image") MultipartFile file)
			throws IOException {
		System.out.println("Calling onSave Method");
		byte[] bytes = file.getBytes();
		String string = System.currentTimeMillis() + "_" + file.getOriginalFilename();
		Path path = Paths.get("E:/temp-files/" + string);
		Files.write(path, bytes);
		dto.setFileName(string);
		System.out.println("Details Entered Are:" + dto);
		if (dto == null) {
			model.addAttribute("message", "Invalid Details");
			return "Product";
		} else {
			Boolean save = productService.validateAndSave(dto);
			if (save) {
				System.out.println("Product Added Successfully" + save);
				model.addAttribute("message", "Product Successfully Added");
			}
		}
		return "Product";

	}

	@GetMapping("/show")
	public String productDetails(ProductDTO dto, Model model) {
		List<ProductDTO> products = productService.uniqueEmail(dto.getEmail());
		if (products != null) {
			model.addAttribute("products", products);
			return "DisplayProductDetails";
		} else {
			model.addAttribute("msg", "Invalid Admin Email");
			return "Product";
		}

	}

	@GetMapping("/findByName")
	public String productDetailsByName(ProductDTO dto, Model model) {
		List<ProductDTO> products = productService.findByname(dto.getProductName());
		if (products != null) {
			model.addAttribute("products", products);
			return "DisplayProductDetails";
		} else {
			model.addAttribute("msg", "invalid Product Name");
			return "SearchProductByEmail";
		}
	}

	@GetMapping("/pro")
	public String getEmail(HttpServletRequest req, Model model) {
		String email = req.getParameter("email");
		List<ProductDTO> dto = productService.uniqueEmail(email);
		if (dto != null) {
			model.addAttribute("Data", dto);
			return "Product";
		} else {
			return "Welcome";
		}
	}

	@GetMapping("/prath")
	public String mapEmail(HttpServletRequest req, Model model, ProjectDTO dto) {
		System.out.println("Running mapEmail in controller");
		String email = req.getParameter("email");
	//	System.out.println(email);
		ProjectDTO byEmail = projectService.getByEmail(email);
		System.out.println("getting data from getbyemail"+byEmail);
		if (byEmail != null) {
			model.addAttribute("product",byEmail);
			return "UpdatePriceandImageByName";
		} else {
			return "DisplayProductDetails";
		}
	}

	@PostMapping("**/updatedProduct")
	public String updateProduct(ProductDTO product, Model model, @RequestParam("image") MultipartFile file)
			throws IOException {
		byte[] bytes = file.getBytes();
		String string = System.currentTimeMillis() + "_" + file.getOriginalFilename();
		Path path = Paths.get("E:/temp-files/" + string);
		Files.write(path, bytes);
		product.setFileName(string);
		System.out.println("Calling Update Controller");
		Boolean dto = productService.updateProductImageAndPriceByEmailAndName(product.getFileName(), product.getPrice(),
				product.getProductName(), product);
		ProjectDTO dtos = projectService.getByEmail(product.getEmail());
		if (dto) {
			System.out.println("Details Updated Successfully");
			model.addAttribute("msg", "Details Updated Successfullt");
			model.addAttribute("Data", dtos);
			return "UpdatePriceandImageByName";
		} else {
			System.out.println("Invalid Info");
			model.addAttribute("msg", "Details Updation Unsuccessfull");
			return "UpdatePriceandImageByName";
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
