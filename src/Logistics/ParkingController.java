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
	
 	//c'tor
	private ParkingController()
	{
		parking = new ArrayList<Parking_Lot>();
	}
	
	public static ParkingController getInstance()
    {
        if (single_instance == null)
            single_instance = new ParkingController();
 
        return single_instance;
    }
	
	
	//setUp
	public void SetUpParkingLot(ArrayList<PlotInfo> pli){
		for (PlotInfo pl : pli) {
			addParkingLot(pl.getPLId(),pl.getMId(),pl.getCapacity());
			}
	}
	
	public void SetUpOrders(ArrayList<Order> all_orders){
		for (Order order : all_orders) {
		    Parking_Lot pl= GetParkingLotById(order.getPlotId());
		    pl.AddOrderToParkingLot(order);
		}
		
	}
	
	// will create a new parking lot and add it to list of all parking lots
	public void addParkingLot(int parking_id ,int m_id,int capacity){
		
		if(!checkIfparkingLotExist(parking_id))
	        parking.add(new Parking_Lot(parking_id,m_id,capacity));
		else{
			System.err.println("can not add parkinglot-already exist");
		}    
	}
		
	
	// will check if parking lot exist already
	public boolean checkIfparkingLotExist(int parking_id){
		
		for (Parking_Lot pl : parking) {
			if (pl.getId()==parking_id)
				return true;
		}	
		return false;
	}
    
	
	// will get parking id and check that parking is available 
	public boolean isParkingAvailable(int parking_id ,Location loc ) {
		Parking_Lot pl=this.GetParkingLotById(parking_id);
		if (!pl.isParkingAvailableAtLocation(loc))
			return false;		
		return true;
	}
	
	// will check if a certain parking lot is full
	public boolean isParkingFull(int parking_id){
		
		Parking_Lot pl=this.GetParkingLotById(parking_id);
		 if (pl.isParkingLotFull())
			     return true;
		return false;
	}
	
	// retrieves parking lot by id 
	public Parking_Lot GetParkingLotById(int parking_id){
		
		for (Parking_Lot pl : parking) {
			if (pl.getId()==parking_id)
				return pl;
		}	
		return null;
	}
	
    // handles parking lot orders by type
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
	
	
	// will handle pre order parking request
	public Location orderPreOrderParking(){
		return null;
	}
	
	//will handle orders of type membership
	public Location orderMembershipParking(){
		return null;	
	}
	
	// will check that parking lot exist
	public boolean doesParkingLotExist(int parking_id) {
		Parking_Lot pl=GetParkingLotById(parking_id);
		if(pl==null)
			return false;
		return true;
	}

	public boolean exitParking(int parking_id,int order_id) {
		Parking_Lot pl=GetParkingLotById(parking_id);
		if(pl==null)
			return false;
		return pl.exitParking(order_id);
	}

	public void cancelParking(int parking_id , int order_id) {
		Parking_Lot pl=GetParkingLotById(parking_id);
		pl.CancelParking(order_id);
	}
	
    public Order getOrder(int order_id){
    	
    	Order o;
    	for(Parking_Lot p : parking){
    		o=p.getOrder(order_id);
    		if(o!= null)
    			return o;
    	}
    	return null;
    }
	
	
	// display function for gui
	// will display all locations which are not available
	public ArrayList<Location> display(int parking_id){
		Parking_Lot pl =GetParkingLotById(parking_id);
		ArrayList<Location> view_array = pl.getAllParkedLocaions();
		return view_array;	
	}
	
}
