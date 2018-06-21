package application;

import java.util.ArrayList;

import Actors.Customer;
import Logistics.Order;
import Logistics.ParkingController;
import Logistics.Parking_Lot;

 public class TextEditor {
	
	private static TextEditor single_instance = null;
	
	String bigText;
	String smallText;
	Customer cst;
	ArrayList<Order> orders; 
	
	public Customer getCst() {
		return cst;
	}

	public void setCst(Customer cst,ArrayList<Order> orders) {
		this.cst = cst;
		this.orders = orders;
	}

	//c'tor
	private TextEditor()
	{ 
		bigText = "Noa";
		smallText = "Bayer";
	}
	
	public static TextEditor getInstance()
   {
       if (single_instance == null)
           single_instance = new TextEditor();

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

}
