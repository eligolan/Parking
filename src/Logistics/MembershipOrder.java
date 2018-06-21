/*package Logistics;

import Actors.Customer;
import Actors.MemberShip;

public class MembershipOrder extends AllOrders {	
	    
	    MemberShip membership;
	    Time arrival_time;
	
	
		public MembershipOrder(MemberShip membership, Time arrival_time) {
			super(new Customer(membership.getName(),membership.getId()), membership.getCarId());
			this.membership=membership;
			this.arrival_time=arrival_time;
               
		}
		
		// will check that 14 days have not passed since arrival_time
		public boolean checkIfCanStillPark(){
			return true;
		}
		
		public double pay(){
			return 0.0;
		}
		
		// view order
		public String view(){
		  String text= "membership order \n";
		  String owner = "owner : " + membership.getName() +"\n";
		  String car = "car_id : "+ Integer.toString(membership.getCarId()) +"\n";
		  String arrival= "arrived at " + arrival_time.getDate() + " at " + Integer.toString(arrival_time.getHour()) ; 
		  return text + owner+car+arrival;
		  
		}	
}
*/