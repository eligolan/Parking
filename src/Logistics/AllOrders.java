package Logistics;

import java.io.Serializable;
import java.util.Date;
import Actors.Customer;


public class AllOrders implements Serializable{
	public Customer getCustomer() {
		return customer;
	}

	protected Customer customer;
	protected int car_id;
	protected int order_type;
	protected String carNumber;
	double price_per_hour;
	protected Date start;
	protected Date end;
	protected int order_id;
    
	/**
	 * constructor
	 * @param customer type Customer which contains all customer information 
	 * @param car_id string representing car id
	 * @param order_id integer representing order_id
	 */
	public AllOrders(Customer customer ,String car_id,int order_id){
		this.customer=customer;
		this.carNumber = car_id;
		this.order_type=0;
		price_per_hour=0;
		this.order_id=order_id;
	}
	
	/**
	 * gets order id
	 * @return integer
	 */
	public int getOrderId(){
		return order_id;
	}
	/**
	 * setd order id
	 * @param oi integer
	 */
	public void setOrderId(int oi){
		order_id=oi;
	}
	/**
	 * gets arrival time
	 * @return Date type
	 */
	public Date getArrivalTime(){
		return start;
	}
	/**
	 *sets order type
	 * @param ot integer
	 */
	public void setOrderType(int ot){
		order_type=ot;
	}
	
	/**
	 * gets order type
	 * @return integer
	 */
	public int getOrderType(){
		return order_type;
	}
	/**
	 * sets price of order per hour
	 * @param price type double
	 */
	public void setPricePerHour(double price){
		price_per_hour=price;
	}
	
	/**
	 * gets price of order per hour
	 * @return double 
	 */
	public double getPricePerHour(){
		return price_per_hour;
	}
	
}
