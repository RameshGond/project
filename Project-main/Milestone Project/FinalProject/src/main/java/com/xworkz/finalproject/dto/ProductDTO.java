package com.xworkz.finalproject.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Entity
@Table(name = "product_details")
@NamedQueries({
		@NamedQuery(name = "findByEmail", query = "Select product from ProductDTO product where product.email=:mail"),
		@NamedQuery(name = "findByCategory", query = "Select product from ProductDTO product where product.category=:cat"),
		@NamedQuery(name="updateProductImageAndPriceByEmailAndName",query="update ProductDTO product set product.price=:pr,product.fileName=:im,product.updatedDate=:ud,product.updatedTime=:ut where product.productName=:nm")})
public class ProductDTO {
	@Id
	@GenericGenerator(name = "save", strategy = "increment")
	@GeneratedValue(generator = "save")
	private int id;
	private String productName;
	private String email;
	private String category;
	private double price;
	private String fileName;
	private LocalDate createdDate;
	private LocalTime createdTime;
	private LocalDate updatedDate;
	private LocalTime updatedTime;

	public ProductDTO() {
		System.out.println("Calling default Constructor of........." + this.getClass().getSimpleName());
	}

}
