package com.xworkz.finalproject.dao;

import java.time.LocalDate;
import static com.xworkz.finalproject.loggers.ProjectLogger.*;

import java.time.LocalTime;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xworkz.finalproject.dto.ProductDTO;
import com.xworkz.finalproject.dto.ProjectDTO;

@Repository
public class ProjectDAOImpl implements ProjectDAO {
	Logger logger=getLogger();
	@Autowired
	private EntityManagerFactory factory;

	EntityManager manager;

	@Override
	public Boolean save(ProjectDTO project) {

		System.out.println("Calling Save Method of ProjectDAO");
		EntityManager createEntityManager = factory.createEntityManager();

		try {
			EntityTransaction transaction = createEntityManager.getTransaction();
			transaction.begin();
			createEntityManager.persist(project);
			transaction.commit();
			logger.warning("persistence Exception may occur");
		} catch (PersistenceException e) {
			System.out.println("exception in save dao" + e.getMessage());
			logger.log(Level.SEVERE,"Persistence Exception Occured",e);
		} finally {
			createEntityManager.close();
		}
		return true;
	}

	@Override
	public List<ProjectDTO> uniqueEmail(String email) {
		try {
			manager = factory.createEntityManager();
			Query query = manager.createNamedQuery("uniqueEmail");
			query.setParameter("mail", email);
			List<ProjectDTO> dto = query.getResultList();
			System.out.println(dto);
			if (dto.isEmpty()) {
				return null;
			} else if (!dto.isEmpty()) {
				return dto;

			}
			logger.warning("persistence Exception may occur");
		} catch (PersistenceException exe) {
			exe.printStackTrace();
			logger.log(Level.SEVERE,"Persistence Exception Occured",exe);
		} finally {
			manager.close();
		}

		return null;
	}

	@Override
	public List<ProjectDTO> findByMailAndPassword(String email, String password) {
		manager = factory.createEntityManager();
		try {
			Query query = manager.createNamedQuery("findByMailAndPassword");
			query.setParameter("mail", email);
			query.setParameter("pass", password);
			System.out.println(email);
			System.out.println(password);
			List<ProjectDTO> project = query.getResultList();
			if (project.isEmpty()) {
				return null;
			} else if (!project.isEmpty()) {
				return project;
			}
			logger.warning("persistence Exception may occur");
		} catch (PersistenceException e) {
			e.printStackTrace();
			logger.log(Level.SEVERE,"Persistence Exception Occured",e);
		} finally {
			manager.close();
		}
		return null;
	}

	@Override
	public Boolean updateStatusByPassword(String email, String status) {
		manager = factory.createEntityManager();
		try {
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			Query query = manager.createNamedQuery("updateStatusByPassword");
			query.setParameter("mail", email);
			query.setParameter("status", status);
			query.executeUpdate();
			transaction.commit();
			logger.warning("persistence Exception may occur");
		} catch (PersistenceException e) {
			e.printStackTrace();
			logger.log(Level.SEVERE,"Persistence Exception Occured",e);
		} finally {
			manager.close();
		}
		return true;
	}

	@Override
	public Boolean updateCountByEmail(Integer count, String email) {
		manager = factory.createEntityManager();
		try {
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			Query query = manager.createNamedQuery("updateCountByEmail");
			query.setParameter("count", count);
			query.setParameter("email", email);
			query.executeUpdate();
			transaction.commit();
			logger.warning("persistence Exception may occur");
		} catch (PersistenceException e) {
			e.printStackTrace();
			logger.log(Level.SEVERE,"Persistence Exception Occured",e);
		} finally {
			manager.close();
		}
		return true;
	}

	@Override
	public Boolean resetPasswordByEmailAndOTP(String email, String password, String status, Integer count,
			Integer otp) {
		manager = factory.createEntityManager();
		try {
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			Query query = manager.createNamedQuery("resetPasswordByEmailAndOTP");
			query.setParameter("mail", email);
			query.setParameter("pass", password);
			query.setParameter("stat", status);
			query.setParameter("count", count);
			query.setParameter("otp", otp);
			query.executeUpdate();
			transaction.commit();
			logger.warning("persistence Exception may occur");
		} catch (PersistenceException e) {
			e.printStackTrace();
			logger.log(Level.SEVERE,"Persistence Exception Occured",e);
		} finally {
			manager.close();
		}
		return true;
	}

	@Override
	public Boolean generateOTPByEmail(String email, Integer otp, LocalTime time, LocalDate date) {
		manager = factory.createEntityManager();
		try {
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			Query query = manager.createNamedQuery("generateOTPTimeAndDateByEmail");
			query.setParameter("mail", email);
			query.setParameter("otp", otp);
			query.setParameter("time", time);
			query.setParameter("date", date);
			query.executeUpdate();
			transaction.commit();
			logger.warning("persistence Exception may occur");
		} catch (PersistenceException e) {
			e.printStackTrace();
			logger.log(Level.SEVERE,"Persistence Exception Occured",e);
		} finally {
			manager.close();
		}
		return true;
	}

	@Override
	public Boolean updateDetailsByEmail(String email, String name, String number) {
		try {
			manager = factory.createEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			Query query = manager.createNamedQuery("updateDetailsByEmail");
			query.setParameter("mail", email);
			query.setParameter("name", name);
			query.setParameter("num", number);
			query.executeUpdate();
			transaction.commit();
			logger.warning("persistence Exception may occur");
		} catch (PersistenceException e) {
			e.printStackTrace();
			logger.log(Level.SEVERE,"Persistence Exception Occured",e);
		} finally {
			manager.close();
		}
		return true;
	}
	
	@Override
	public ProjectDTO getByEmail(String email) {
		try {
			manager = factory.createEntityManager();
			Query query = manager.createNamedQuery("uniqueEmail");
			query.setParameter("mail", email);
			Object dto = query.getSingleResult();
			System.out.println("DTO in Object"+dto);
			if (dto != null) {
				ProjectDTO dtos = (ProjectDTO)dto;
				return dtos;
			} else {
				System.out.println("DTO is Null");

			}
		} catch (PersistenceException exe) {
			exe.printStackTrace();
			// logger.log(Level.SEVERE,"Persistence Exception Occured",exe);
		} finally {
			manager.close();
		}

		return null;
	}

}
