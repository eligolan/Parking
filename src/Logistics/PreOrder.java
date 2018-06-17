package Logistics;

import java.util.Date;
import Actors.Customer;

public class PreOrder extends Order {
	
	double paymentForOrder;
	boolean fine_for_late_arrival;
	
	public PreOrder(Customer customer , int car_id, String email , Time et,Time ar) {
		super(customer , car_id, email , et);
		price_per_hour=4.0;
		fine_for_late_arrival=checkForFineForOrder(ar);
		paymentForOrder=this.calculatePayment();
		arrival=ar;
		order_type=2;
	}
	
	@SuppressWarnings("deprecation")
	public boolean checkForFineForOrder(Time arrival){
		Date date = new Date();
		if(date.getHours() != arrival.getHour() || date.getDay() != arrival.getDay())
			return true;
		return false;
	}
	
	@SuppressWarnings("deprecation")
	//will calculate pre ordered parking reservation
	public double  calculatePayment(){
	    
		int diff=arrival.getHour() -estimated_departure.getHour();;
		double sumForPayment=0;
		sumForPayment=(estimated_departure.getDay() -arrival.day)*price_per_hour;
		if(arrival.getHour() > estimated_departure.getHour()){
			sumForPayment -=diff;
		}
		else{
			diff*=-1;
			sumForPayment+=diff;
		}
     
		return sumForPayment;
	}
	
	public double pay(){
		double fine=0;
		if (fine_for_late_arrival)
			fine=paymentForOrder + (paymentForOrder *0.2);
		return paymentForOrder+fine;
	}
	
	// view order
	public String view(){
	  String text= "Pre Order \n";
	  String owner = "owner : " + customer.getName() +"\n";
	  String car = "car_id : "+ Integer.toString(car_id) +"\n";
	  String departe= "departe at " + estimated_departure.getDate() + " at " + Integer.toString(estimated_departure.getHour()) ; 
	  return text + owner+car+departe;
	  
	}	
	

	

}
