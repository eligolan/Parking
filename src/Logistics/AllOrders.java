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

	public AllOrders(Customer customer ,String car_id,int order_id){
		this.customer=customer;
		this.carNumber = car_id;
		this.order_type=0;
		price_per_hour=0;
		this.order_id=order_id;
	}
	
	public int getOrderId(){
		return order_id;
	}
	
	public void setOrderId(int oi){
		order_id=oi;
	}
	
	public Date getArrivalTime(){
		return start;
	}
	
	public void setOrderType(int ot){
		order_type=ot;
	}
	
	public int getOrderType(){
		return order_type;
	}
	public void setPricePerHour(double price){
		price_per_hour=price;
	}
	
	public double getPricePerHour(){
		return price_per_hour;
	}
	
}
