package Logistics;

import java.util.Date;

import Actors.Customer;
import Actors.MemberShip;

public class MembershipOrder extends AllOrders {	
	    
	    MemberShip membership;

		public MembershipOrder(MemberShip membership,int order_id) {
			super(new Customer(membership.getName(), membership.getId(), membership.getDateRegister()),membership.getCarId(),order_id);
			this.membership=membership;
		}
		
		// will check that 14 days have not passed since arrival_time
		public boolean checkIfCanStillPark(){
			return true;
		}
		
		public double pay(){
			return 0.0;
		}	
}
