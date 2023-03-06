package com.xworkz.finalproject.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "userdata")
@NamedQueries({
		@NamedQuery(name = "uniqueEmail", query = "select user from ProjectDTO user where user.email=:mail"),
		@NamedQuery(name = "findByMailAndPassword", query = "select user from ProjectDTO user where user.email=:mail and user.password=:pass "),
		@NamedQuery(name = "updateStatusByPassword", query = "update ProjectDTO user set user.status=:status where user.email=:mail"),
		@NamedQuery(name = "updateCountByEmail", query = "update ProjectDTO user set user.count=:count where user.email=:email"),
		@NamedQuery(name = "resetPasswordByEmailAndOTP", query = "update ProjectDTO as user set user.password=:pass, user.status =:stat, user.count=:count where user.email=:mail and user.otp=:otp"),
		@NamedQuery(name = "generateOTPTimeAndDateByEmail", query = "update ProjectDTO user set user.otp=:otp,user.time=:time,user.date=:date where user.email=:mail"),
		@NamedQuery(name="updateDetailsByEmail",query="update ProjectDTO user set user.userName=:name,user.number=:num where user.email=:mail"),
		@NamedQuery(name = "findEncryptedPasswordByEmail", query = "select user.password from ProjectDTO user where user.email=:mail"),})
       
public class ProjectDTO {

	@Id
	@GenericGenerator(name = "save", strategy = "increment")
	@GeneratedValue(generator = "save")
	@Min(value = 1)
	private Integer id;
	@NotNull
	@NotEmpty
	@Length(min = 2)
	@Length(max = 25)
	private String userName;
	@NotNull
	@NotEmpty
	@Length(min = 2)
	@Length(max = 30)
	private String email;
	@NotNull
	@NotEmpty
	@Length(min = 10)
	@Length(max = 12)
	private String number;

	private String password;

	private String status;

	private Integer count;

	private Integer otp;
	
	private LocalTime time;
	
	private LocalDate date;
	
	private String fileName;
	
	//private ProductDTO product;

	public ProjectDTO() {
		System.out.println("Calling Default COnstructor of...." + this.getClass().getSimpleName());
	}

}
