package Actors;

import java.util.Date;
public class Customer extends Person{
	
	private Date dateRegister;
	
	public Customer(String name, int id, Date resigerDate) {
		super(name, id);
		dateRegister = resigerDate;
	}
	
	public Date getDateRegister() {
		return dateRegister;
	}

	public void placeOrder()
	{
		
	}
	
	public void trackRequest(int num)
	{
		
	}
}
