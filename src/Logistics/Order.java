package Logistics;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import Actors.Customer;

public class Order extends AllOrders {
	
	private String email;
	protected double price_per_hour;
	int order_type;
	
	public Order(Customer customer , String car_id, int order_id ,String email , Date start,Date end){
		super(customer , car_id , order_id);
		this.email=email;
		this.start = start;
		this.end = end;
		price_per_hour=5.0;
		order_type=1;
	}
		
	
	@Override
	public String toString() {

		 String text= "Order \n";
		  //String owner = "owner : " + customer.getName() +"\n";
		  String car = "car_id : "+ carNumber +"\n";
		  String startDate = "start : "+this.start.toString() +"\n";
		  String endDate = "end : "+this.end.toString() +"\n";
		return text + car + startDate + endDate;
	}
	
	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}
	
	public void setPricePerHour(double price){
		price_per_hour=price;
	}
	
	public double getPricePerHour(){
		return price_per_hour;
	}
	
	public void setOrderType(int ot){
		order_type=ot;
	}
	
	public int getOrderType(){
		return order_type;
	}
}
