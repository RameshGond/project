package com.xworkz.finalproject.configuration;

import java.util.HashMap;
import static com.xworkz.finalproject.loggers.ProjectLogger.*;
import java.util.Map;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class DBConfiguration {
	Logger logger=getLogger();

	public DBConfiguration() {
		logger.info("Created DB Configuration..." + this.getClass().getSimpleName());
	}

// persistence.xml
	// factory--- datasource
	// packages to scan: @Entity

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManager(DataSource dataSource) {

		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setPackagesToScan("com.xworkz");
		factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		logger.info("getting data source from spring ");
		factoryBean.setDataSource(dataSource);

		Map<String, Object> jpaProperties = new HashMap<String, Object>();
		jpaProperties.put("hibernate.show_sql", true);

		factoryBean.setJpaPropertyMap(jpaProperties);
		return factoryBean;

	}

	// persitence.xml : db properties ---> Connection
	@Bean
	public DataSource dataSource() {
		logger.info("created datasource");
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/xworkz");
		dataSource.setPassword("13398");
		dataSource.setUsername("root");
		return dataSource;
	}
}
