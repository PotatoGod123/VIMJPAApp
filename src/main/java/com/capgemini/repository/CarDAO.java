package com.capgemini.repository;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.capgemini.beans.CarDTO;

/*NA*/
/**
 * 
 * This is a CarDAO class
 * @see java.lang.Object
 *
 */ 
public interface CarDAO 
{
    public List<CarDTO> findAll(); 
    public CarDTO findById(int id);
    public void create(CarDTO car);
    public void update(CarDTO car);
    public void delete(String[] ids);
}