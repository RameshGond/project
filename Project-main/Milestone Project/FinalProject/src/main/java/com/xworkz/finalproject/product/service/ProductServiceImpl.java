package com.xworkz.finalproject.product.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xworkz.finalproject.dao.ProductDAO;
import com.xworkz.finalproject.dto.ProductDTO;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDAO dao;

	private LocalDateTime dateAndTime;

	@Override
	public Boolean validateAndSave(ProductDTO dto) {
		dateAndTime = LocalDateTime.now();
		LocalDate date = dateAndTime.toLocalDate();
		LocalTime time = dateAndTime.toLocalTime();
		dto.setCreatedDate(date);
		dto.setCreatedTime(time);
		dao.save(dto);
		return true;
	}

	@Override
	public List<ProductDTO> uniqueEmail(String email) {
		System.out.println("Calling uniqueEmail method from service");
		List<ProductDTO> unique = dao.uniqueEmail(email);
		if (unique != null) {
			return unique;
		} else {
			return null;
		}
	}

	@Override
	public List<ProductDTO> findByname(String name) {
		System.out.println("Calling findByName Method");
		if (name != null) {
			List<ProductDTO> findByName = dao.findByName(name);
			if (findByName != null) {
				return findByName;
			} else {
				return null;
			}
		}
		return null;

	}

	@Override
	public Boolean updateProductImageAndPriceByEmailAndName(String fileName, double price, String name,
			ProductDTO dto) {
		dateAndTime = LocalDateTime.now();
		LocalDate date = dateAndTime.toLocalDate();
		LocalTime time = dateAndTime.toLocalTime();
		dto.setUpdatedDate(date);
		dto.setUpdatedTime(time);
		Boolean update = dao.updateProductImageAndPriceByEmailAndName(fileName, price, name, date, time);
		if (update) {
			System.out.println("Update Successfull");
		} else {
			System.out.println("Update Unsuccessfull");
		}
		return true;
	}

}
