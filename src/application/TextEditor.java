package application;

import java.util.ArrayList;
import java.util.Date;

import Actors.Customer;
import Logistics.Order;
import Logistics.ParkingController;
import Logistics.Parking_Lot;

 public class TextEditor {
	
	private static TextEditor single_instance = null;
	private int price ;
	
	String bigText;
	String smallText;
	Customer cst = new Customer("No CST", -1, new Date());
	ArrayList<Order> orders; 
	
	public Customer getCst() {
		return cst;
	}

	public void setCst(Customer cst,ArrayList<Order> orders) {
		this.cst = cst;
		this.orders = orders;
	}
	
	

	public ArrayList<Order> getOrders() {
		return orders;
	}

	//c'tor
	private TextEditor()
	{ 
		bigText = "Noa";
		smallText = "Bayer";
		price = 5;
	}
	
	public static TextEditor getInstance()
   {
       if (single_instance == null) {
           single_instance = new TextEditor();
       }

       return single_instance;
   }
	
	public String getBigText()
	{
		return bigText;
	}
	
	public void setBigText(String t)
	{
		this.bigText = t;
	}
	
	public String getSmallText()
	{
		return smallText;
	}
	
	public void setSmallText(String t)
	{
		this.smallText = t;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
