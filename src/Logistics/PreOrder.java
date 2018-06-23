package Logistics;

import java.util.Date;
import Actors.Customer;

public class PreOrder extends Order {
	
	/**
	 * constructor
	 * @param customer type Customer which contains customer information
	 * @param car_id String 
	 * @param order_id Integer
	 * @param email String
	 * @param start Date indicating arrival time 
	 * @param end Date indication departure time
	 * @param loc Location where car will be parked 
	 * @param pl_id integer parking lot id
	 */
	public PreOrder(Customer customer ,String car_id,int order_id, String email , Date start,Date end,Location loc ,int p_id) {
	
		super(customer , car_id,order_id, email , start, end, loc, p_id);
		price_per_hour=4.0;
		order_type=2;
	}
		
}
