package com.xworkz.finalproject.configuration;

import org.springframework.context.annotation.Bean;
import static com.xworkz.finalproject.loggers.ProjectLogger.*;

import java.util.logging.Logger;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages = "com.xworkz")
public class SpringConfiguration {
	Logger logger=getLogger();

	public SpringConfiguration() {

		logger.info("calling Default Constructor of:" + this.getClass().getSimpleName());
	}

	@Bean
	public ViewResolver viewResolver() {
		logger.info("Calling viewResolver Method");
		return new InternalResourceViewResolver("/", ".jsp");
	}
	
	@Bean
	public MultipartResolver multipartResolver() {
		logger.info("Creating view resolver");
		return new StandardServletMultipartResolver();
	}
}
