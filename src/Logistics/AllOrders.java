package Logistics;

import java.io.Serializable;

import Actors.Customer;

abstract class AllOrders implements Serializable{
	public Customer getCustomer() {
		return customer;
	}

	protected Customer customer;
	protected int car_id;
	protected int order_type;
	protected String carNumber;
	double price_per_hour;
	Time arrival=null;
	
	
	public AllOrders(Customer customer ,int car_id){
		this.customer=customer;
		this.car_id=car_id;
		this.order_type=0;
		price_per_hour=0;
	}
	
	public AllOrders(Customer customer ,String car_id){
		this.customer=customer;
		this.carNumber = car_id;
		this.order_type=0;
		price_per_hour=0;
	}
	
	
	abstract double pay();
	abstract String view();
	
	
	public int getArrivalTime(){
		return arrival.getHour();
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
