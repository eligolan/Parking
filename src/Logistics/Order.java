package Logistics;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import Actors.Customer;

public class Order extends AllOrders {
	
	private String email;
	protected Time estimated_departure;
	protected Time arrival;
	protected double price_per_hour;
	protected Date start;
	protected Date end;
	
	int order_type;
	
	public Order(Customer customer , int car_id, String email , Time departe){
		super(customer , car_id);
		this.email=email;
		estimated_departure=departe;
		arrival=SetArrivalTime();
		price_per_hour=5.0;
		order_type=1;
	}
	
	public Order(Customer customer , String car_id, String email , Date start,Date end){
		super(customer , car_id);
		this.email=email;
		this.start = start;
		this.end = end;
		price_per_hour=5.0;
		order_type=1;
	}
	
	@SuppressWarnings("deprecation")
	public Time SetArrivalTime(){
		Time temp;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String frmtdDate = dateFormat.format(date);
		String in=frmtdDate.split(" ")[0];
		temp=new Time(date.getHours(), date.getDay(), in);
		return temp;
	}
	
	// view order
	public String view(){
	  String text= "Order \n";
	  String owner = "owner : " + customer.getName() +"\n";
	  String car = "car_id : "+ Integer.toString(car_id) +"\n";
	  String departe= "departe at " + estimated_departure.getDate() + " at " + Integer.toString(estimated_departure.getHour()) ; 
	  return text + owner+car+departe;
	  
	}	
	
	@SuppressWarnings("deprecation")
	public double pay(){
		double payment;
		Date date=new Date();
		payment= (date.getHours()-arrival.getHour()) * price_per_hour;
		return payment;
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
