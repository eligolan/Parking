package Logistics;

import java.util.Date;
import Actors.Customer;

public class PreOrder extends Order {
		
	public PreOrder(Customer customer ,String car_id,int order_id, String email , Date start,Date end) {
	
		super(customer , car_id,order_id, email , start, end);
		price_per_hour=4.0;
		order_type=2;
	}
		
}
