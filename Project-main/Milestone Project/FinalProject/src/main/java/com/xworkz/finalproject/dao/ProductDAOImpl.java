package com.xworkz.finalproject.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xworkz.finalproject.dto.ProductDTO;

@Repository
public class ProductDAOImpl implements ProductDAO {
	@Autowired
	EntityManagerFactory factory;

	EntityManager manager;

	@Override
	public Boolean save(ProductDTO dto) {
		System.out.println("Calling Save Method From Product DTO");
		try {
			manager = factory.createEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(dto);
			transaction.commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			manager.close();
		}
		return true;

	}

	@Override
	public List<ProductDTO> uniqueEmail(String email) {
		try {
			manager = factory.createEntityManager();
			Query query = manager.createNamedQuery("findByEmail");
			query.setParameter("mail", email);
			List<ProductDTO> dto = query.getResultList();
			System.out.println(dto);
			if (dto.isEmpty()) {
				return null;
			} else if (!dto.isEmpty()) {
				return dto;

			}
		} catch (PersistenceException exe) {
			exe.printStackTrace();
			// logger.log(Level.SEVERE,"Persistence Exception Occured",exe);
		} finally {
			manager.close();
		}

		return null;
	}

	@Override
	public List<ProductDTO> findByCategory(String category) {
		try {
			manager = factory.createEntityManager();
			Query query = manager.createNamedQuery("findByCategory");
			query.setParameter("cat", category);
			List<ProductDTO> dto = query.getResultList();
			System.out.println(dto);
			if (dto.isEmpty()) {
				return null;
			} else if (!dto.isEmpty()) {
				return dto;

			}
		} catch (PersistenceException exe) {
			exe.printStackTrace();
			// logger.log(Level.SEVERE,"Persistence Exception Occured",exe);
		} finally {
			manager.close();
		}

		return null;
	}

	@Override
	public List<ProductDTO> findByName(String name) {
		try {
			manager = factory.createEntityManager();
			Query query = manager.createNamedQuery("findByName");
			query.setParameter("nam", name);
			List<ProductDTO> dto = query.getResultList();
			System.out.println(dto);
			if (dto.isEmpty()) {
				return null;
			} else if (!dto.isEmpty()) {
				return dto;

			}
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			manager.close();
		}
		return null;
	}

	@Override
	public Boolean updateProductImageAndPriceByEmailAndName(String fileName, double price, String name, LocalDate date,
			LocalTime time) {
		System.out.println("Calling updateProductImageAndPriceByEmailAndName method");
		try {
			manager = factory.createEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			Query query = manager.createNamedQuery("updateProductImageAndPriceByEmailAndName");
			query.setParameter("im", fileName);
			query.setParameter("pr", price);
			query.setParameter("nm", name);
			query.setParameter("ud", date);
			query.setParameter("ut", time);
			query.executeUpdate();
			transaction.commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			manager.close();

		}
		return true;

	}

}
