package com.capgemini;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.capgemini.beans.CarDTO;
import com.capgemini.repository.CarDAO;

@Controller
public class CarOperations {

	@Autowired
	private CarDAO daoRef;

	private static final String ACTION_KEY = "action";
	private static final String VIEW_CAR_LIST_ACTION = "viewCarList";
	private static final String ADD_CAR_ACTION = "addCar";
	private static final String SAVE_CAR_ACTION = "saveCar";
	private static final String EDIT_CAR_ACTION = "editCar";
	private static final String DELETE_CAR_ACTION = "deleteCar";
	private static final String ERROR_KEY = "errorMessage";

	@RequestMapping(name = "controller", method = RequestMethod.GET)
	public String getCars(ModelMap map, @RequestParam(ACTION_KEY) String action) {

		String destinationPage=null;

		if (action.equals(VIEW_CAR_LIST_ACTION)) {
			map.addAttribute("carList", daoRef.findAll());
			destinationPage = "carList";
		}else if(action.equals(ADD_CAR_ACTION)) {
			destinationPage = "carForm";
		}

		return destinationPage;

	}

	@RequestMapping(value="controller", method=RequestMethod.POST)
	public String saveCar(@RequestParam(ACTION_KEY) String action, @ModelAttribute CarDTO car) {
		String destinationPage=null;
		
		if(action.equals(SAVE_CAR_ACTION)) {
			System.out.println(car.getMake());
			if(car.getId() == -1)
				daoRef.create(car);
		}
		
		
		destinationPage = "carList";
		return destinationPage;
		
	}
	
	
	
	
	
	@ModelAttribute("car")
	public CarDTO getCar(@RequestParam(required=false, value="id") Integer id) {
		System.out.println("creating a car");
		if(id == null) {
			return new CarDTO();
		}else
		
			return daoRef.findById(id);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
