package com.xworkz.finalproject.product.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.xworkz.finalproject.dto.ProductDTO;

public interface ProductService {
	Boolean validateAndSave(ProductDTO dto);

	List<ProductDTO> uniqueEmail(String email);

	List<ProductDTO> findByname(String name);
	
	Boolean updateProductImageAndPriceByEmailAndName(String fileName,double price,String name,ProductDTO dto);

}
