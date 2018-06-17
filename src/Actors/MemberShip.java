package Actors;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MemberShip extends Customer {
	
	private int carId;
	private LocalDateTime date;
	private double payment;
	
	public MemberShip(String name, int id, int carId) {
		super(name, id);
		this.carId = carId;
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		 this.date=LocalDateTime.now();  
		 payment=250.0;
	}
	

	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public int getCarId() {
		return carId;
	}
	public void setCarId(int carId) {
		this.carId = carId;
	}

}
