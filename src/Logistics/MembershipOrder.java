package Logistics;

import java.util.Date;

import Actors.Customer;
import Actors.MemberShip;

public class MembershipOrder extends AllOrders {	
	    
	    MemberShip membership;
        
	    /**
	     * constructor
	     * @param membership type Membership containing membership information
	     * @param order_id 
	     */
		public MembershipOrder(MemberShip membership,int order_id) {
			super(new Customer(membership.getName(), membership.getId(), membership.getDateRegister()),membership.getCarId(),order_id);
			this.membership=membership;
		}
				
}
