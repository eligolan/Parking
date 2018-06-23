package application;

import java.util.ArrayList;
import java.util.Date;

import Actors.Customer;
import Logistics.Order;
import Logistics.ParkingController;
import Logistics.Parking_Lot;

/**
 * 
 * @author user
 * this is single-tone class
 * her responsibility to hold the current Costumer
 * and the details  for the input for the windows
 */
 public class TextEditor {
	
	private static TextEditor single_instance = null;
	private int price ;
	private int floor;
	private int numParking;
	String bigText;
	String smallText;
	Customer cst = new Customer("No CST", -1, new Date());
	ArrayList<Order> orders; 
	
	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}

	public int getNumParking() {
		return numParking;
	}

	public void setNumParking(int numParking) {
		this.numParking = numParking;
	}

	
	
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
	private TextEditor() { 
		bigText = "Noa";
		smallText = "Bayer";
		price = 5;
	}
	
	/**
	 * Return singleton of the class
	 * @return class singleton instance
	 */
	public static TextEditor getInstance() {
       if (single_instance == null) {
           single_instance = new TextEditor();
       }

       return single_instance;
   }
	
	public String getBigText() {
		return bigText;
	}
	
	public void setBigText(String t) {
		this.bigText = t;
	}
	
	public String getSmallText() {
		return smallText;
	}
	
	public void setSmallText(String t) {
		this.smallText = t;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}
}
