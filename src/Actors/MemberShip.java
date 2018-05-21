package Actors;

import java.util.Date;

public class MemberShip extends Customer {
	
	private int carId;
	private Date date;
	
	public MemberShip(String name, int id, int carId, Date date) {
		super(name, id);
		this.carId = carId;
		this.date = date;
	}
	
	
//	public MemberShip(int id, int carId, Date date)
//	{
//		
//	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getCarId() {
		return carId;
	}
	public void setCarId(int carId) {
		this.carId = carId;
	}

}
