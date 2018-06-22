package Actors;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class MemberShip extends Customer {
	
	private String carId;
	private double payment;
	
	public MemberShip(String name, int id, Date register , String car_id) {
		super(name, id ,register);
		this.carId = carId;  
		 payment=250.0;
	}
	
	public String getCarId() {
		return carId;
	}
	
	public void setCarId(String carId) {
		this.carId = carId;
	}
	

}
