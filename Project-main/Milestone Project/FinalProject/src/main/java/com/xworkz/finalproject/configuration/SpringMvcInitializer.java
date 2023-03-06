package com.xworkz.finalproject.configuration;

import java.io.File;
import java.util.logging.Logger;

import static com.xworkz.finalproject.loggers.ProjectLogger.*;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringMvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
		implements WebMvcConfigurer {
	Logger logger=getLogger();
	private String[] getServletMappings = { "/" };
	private Class[] getServletConfigClasses = { SpringConfiguration.class, DBConfiguration.class };

	public SpringMvcInitializer() {

		System.out.println("calling Default Constructor of:" + this.getClass().getSimpleName());
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		logger.info("Running getRootConfigClasses");
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		logger.info("Running getServletConfigClasses");
		return getServletConfigClasses;
	}

	@Override
	protected String[] getServletMappings() {
		logger.info("Running getServletMappings");
		return getServletMappings;
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		logger.info("Calling configureDefaultServletHandling Method");
		configurer.enable();
	}

	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {
		logger.info("Creating file Upload Customizer");
		// files uploaded will be saved here
		File upload = new File("E:/temp-files");

		// register a multipartconfigElement
		MultipartConfigElement multipartConfigElement = new MultipartConfigElement(upload.getAbsolutePath(),
				100000000,100000000 * 2, 100000000 / 2);
		registration.setMultipartConfig(multipartConfigElement);
	}

}
