package com.xworkz.finalproject.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.xworkz.finalproject.dto.ProductDTO;

public interface ProductDAO {
	Boolean save(ProductDTO dto);

    List<ProductDTO> uniqueEmail(String email);
    
	List<ProductDTO> findByCategory(String category);
	
	List<ProductDTO> findByName(String name);
	
	Boolean updateProductImageAndPriceByEmailAndName(String fileName,double price,String name,LocalDate date,LocalTime time);

}
