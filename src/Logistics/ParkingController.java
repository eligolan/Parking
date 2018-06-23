package Logistics;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

import Actors.Customer;
import Actors.Employee;
import Actors.Manager;
import javafx.util.Pair;

public final class ParkingController {
	
	private static ParkingController single_instance = null;
	 ArrayList<Parking_Lot> parking;
	
 	/**
 	 * sets up parking lot controller
 	 */
	private ParkingController()
	{
		parking = new ArrayList<Parking_Lot>();
	}
	
	/**
	 * exits from system
	 * clears all parking lot from system
	 */
	public void ExitSystem(){
		for(Parking_Lot pl : parking)
			pl.exitSystem();
		parking.clear();
	}
	
	/**
	 * 
	 * @return Parking	Controller instance
	 */
	public static ParkingController getInstance()
    {
        if (single_instance == null)
            single_instance = new ParkingController();
 
        return single_instance;
    }
	
	
	/**
	 * 
	 * @param pli Array list containing all parking lots to be added to parking controller
	 */
	public void SetUpParkingLot(ArrayList<PlotInfo> pli){
		for (PlotInfo pl : pli) {
			addParkingLot(pl.getPLId(),pl.getMId(),pl.getCapacity());
			}
	}
	/**
	 * 
	 * @param all_orders Array list containing all parking reservations
	 */
	public void SetUpOrders(ArrayList<Order> all_orders){
		for (Order order : all_orders) {
		    Parking_Lot pl= GetParkingLotById(order.getPlotId());
		    pl.AddOrderToParkingLot(order);
		}
		
		for(Parking_Lot p : parking){
			Location loc =new Location(1, 1, 1);
			p.SetNearestLocation(loc);
			p.updateNextNearestLocation();
		}
	}
	
	
	/**
	 * 
	 * @param parking_id integer parking lot id
	 * @param m_id manager id
	 * @param capacity parking lot capacity
	 */
	public void addParkingLot(int parking_id ,int m_id,int capacity){
		
		if(!checkIfparkingLotExist(parking_id))
	        parking.add(new Parking_Lot(parking_id,m_id,capacity));
		else{
			System.err.println("can not add parkinglot-already exist");
		}    
	}
		
    /**
     * checks if parking lot exist
     * @param parking_id int parking lot id
     * @return true or false
     */
	public boolean checkIfparkingLotExist(int parking_id){
		
		for (Parking_Lot pl : parking) {
			if (pl.getId()==parking_id)
				return true;
		}	
		return false;
	}
    
    
	/**
	 * checks if parking lot is available at a certain location
	 * @param parking_id integer parking lot id
	 * @param loc Location
	 * @return true or false
	 */
	public boolean isParkingAvailable(int parking_id ,Location loc ) {
		Parking_Lot pl=this.GetParkingLotById(parking_id);
		if (!pl.isParkingAvailableAtLocation(loc))
			return false;		
		return true;
	}
	
	/**
	 * will check if a certain parking lot is full
	 * @param parking_id integer parking lot id
	 * @return true or false
	 */
	public boolean isParkingFull(int parking_id){
		
		Parking_Lot pl=this.GetParkingLotById(parking_id);
		 if (pl.isParkingLotFull())
			     return true;
		return false;
	}
	
	/**
	 * retrieves parking lot by id
	 * @param parking_id
	 * @return Praking_Lot
	 */
	public Parking_Lot GetParkingLotById(int parking_id){
		
		for (Parking_Lot pl : parking) {
			if (pl.getId()==parking_id)
				return pl;
		}	
		return null;
	}
	
    /**
     * orders parking by order type
     * @param parking_id integer parking lot id
     * @param customer Customer which contains customer information
     * @param car_id string car id
     * @param order_id integer order id
     * @param order_type enum order type
     * @param email String email
     * @param start Date arrival time
     * @param end Date departure time
     * @return Location
     */
	public Location orderParking(int parking_id,Customer customer,String car_id,int order_id, int order_type,String email ,Date start, Date end) {
				
	  Parking_Lot pl = GetParkingLotById(parking_id);
	  
	  if(OrderType.Order.getOrderType() == order_type){
		   
		 return order(pl,customer, car_id, order_id,email ,start , end);
	  }
      if(OrderType.PreOrder.getOrderType() == order_type){
		  
    	  return orderPreOrderParking();
	  }
      if(OrderType.MembershipOrder.getOrderType() == order_type){
		  
    	  return orderMembershipParking();
	  }
      return null;
		
	}
	
	public Location order(Parking_Lot pl,Customer customer,String car_id, int order_id,String email,Date start , Date end){
		
		Location next_location=pl.FindNearestPlLocation(); 
		Location order_loc =new Location(next_location.getX(),next_location.getY(),next_location.getZ());
		Order order =new Order(customer, car_id, order_id,email,start,end,order_loc,pl.getId());
		return pl.updateEnableParking(order);
		
	}
	
	
	/**
	 * handles order of pre type
	 * @return
	 */
	public Location orderPreOrderParking(){
		return null;
	}
	
	/**
	 * handles order of membership
	 * @return
	 */
	
	public Location orderMembershipParking(){
		return null;	
	}
	
	/**
	 * checks if parking lot exist
	 * @param parking_id integer parking lot id
	 * @return true or false
	 */
	public boolean doesParkingLotExist(int parking_id) {
		Parking_Lot pl=GetParkingLotById(parking_id);
		if(pl==null)
			return false;
		return true;
	}
    
	/**
	 * car exists from parking lot
	 * @param parking_id integer parking lot id
	 * @param order_id integer order id
	 * @return true or false
	 */
	public boolean exitParking(int parking_id,int order_id) {
		Parking_Lot pl=GetParkingLotById(parking_id);
		if(pl==null)
			return false;
		return pl.exitParking(order_id);
	}
    
	/**
	 *  cancels parking
	 * @param parking_id integer parking lot id 
	 * @param order_id integer order id
	 * @return true or false
	 */
	public boolean cancelParking(int parking_id , int order_id) {
		Parking_Lot pl=GetParkingLotById(parking_id);
		return pl.CancelParking(order_id);
	}
	
	/**
	 * gets order by id
	 * @param order_id integer order id
	 * @return Order which contains all order ids information
	 */
    public Order getOrder(int order_id){
    	
    	Order o;
    	for(Parking_Lot p : parking){
    		o=p.getOrder(order_id);
    		if(o!= null)
    			return o;
    	}
    	return null;
    }
	
	/**
	 * gets all unavailable locations of a certain parking lot
	 * @param parking_id integer parking lot id
	 * @return Array List which contains Location type
	 */
	public ArrayList<Location> display(int parking_id){
		Parking_Lot pl =GetParkingLotById(parking_id);
		ArrayList<Location> view_array = pl.getAllParkedLocaions();
		return view_array;	
	}
	
}
