package com.capgemini.repository;

import java.util.List;

import javax.persistence.*;
import javax.transaction.TransactionManager;

import org.springframework.stereotype.Repository;

import com.capgemini.beans.CarDTO;
@Repository
public class JPACarDAO implements CarDAO{

	@PersistenceContext
	private EntityManager entityManager;

	private final EntityTransaction tx;

	{
		assert false;
		tx = entityManager.getTransaction();
	}

	@Override
	public List<CarDTO> findAll() {
		Query query = entityManager.createQuery("select * from CarDTO car");
		
		return query.getResultList();
	}
	
	
	
	
	
	
	
	
	@Override
	public void create(CarDTO car) {
		// TODO Auto-generated method stub
		tx.begin();
		entityManager.persist(car);
		tx.commit();
	}
	
	@Override
	public void delete(String[] ids) {
		// TODO Auto-generated method stub
		for (String id: ids){
			int parsedId = Integer.parseInt(id);
			CarDTO foundCar = entityManager.find(CarDTO.class, parsedId);
			if(foundCar!=null){
				tx.begin();
				entityManager.remove(foundCar);
				tx.commit();
			}
		}
		
	}
	
	@Override
	public CarDTO findById(int id) {
		// TODO Auto-generated method stub
		CarDTO car= null;
		car = entityManager.find(CarDTO.class, id);
		return car;
	}
	
	@Override
	public void update(CarDTO car) {
		// TODO Auto-generated method stub
		CarDTO foundCar = entityManager.find(CarDTO.class, car.getId());
		if(foundCar!=null){
			tx.begin();
			foundCar.setMake(car.getMake());
			foundCar.setModel(car.getModel());
			foundCar.setModelYear(car.getModelYear());
			tx.commit();
		}
	}
	
}
