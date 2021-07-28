package com.capgemini.repository;

import java.util.List;

import javax.persistence.*;

import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

import com.capgemini.beans.CarDTO;

@Repository
@EnableTransactionManagement
public class JPACarDAO implements CarDAO{

	@PersistenceContext
	private EntityManager em;

//	@PersistenceContext
//	private final EntityTransaction tx;
//
//	{
//		assert false;
//		tx = entityManager.getTransaction();
//	}

	@Override
	public List<CarDTO> findAll() {
		Query query = em.createQuery("select car from CarDTO car");
		System.out.println("inside find all jpa ");
		return query.getResultList();
	}
	
	
	@Override
	@Transactional
	public void create(CarDTO car) {
		// TODO Auto-generated method stub
//		tx.begin();
		em.persist(car);
		em.close();
//		tx.commit();
	}
	
	@Override
	@Transactional
	public void delete(String[] ids) {
		// TODO Auto-generated method stub
		for (String id: ids){
			int parsedId = Integer.parseInt(id);
			CarDTO foundCar = em.find(CarDTO.class, parsedId);
			if(foundCar!=null){
//				tx.begin();
				em.remove(foundCar);
				
			}
			em.close();
		}
		
	}
	
	@Override
	public CarDTO findById(int id) {
		// TODO Auto-generated method stub
		CarDTO car= null;
		car = em.find(CarDTO.class, id);
		return car;
	}
	
	@Override
	@Transactional
	public void update(CarDTO car) {
		// TODO Auto-generated method stub
		CarDTO foundCar = em.find(CarDTO.class, car.getId());
		if(foundCar!=null){
			foundCar.setMake(car.getMake());
			foundCar.setModel(car.getModel());
			foundCar.setModelYear(car.getModelYear());
			em.merge(car);
			em.close();
		}
	}
	
}
