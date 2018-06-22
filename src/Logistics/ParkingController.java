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
	public void SetUp(int parking_id , String loc , Manager m , String name , int capacity){
	   addParkingLot(parking_id, loc, m, name, capacity);
	}
	
	// will create a new parking lot and add it to list of all parking lots
	public void addParkingLot(int parking_id ,String loc, Manager m, String name, int capacity){
		
		if(!checkIfparkingLotExist(parking_id))
	        parking.add(new Parking_Lot(parking_id, loc, name, m,capacity));
		else{
			System.err.println("can not add parkinglot-already exist");
		}    
	}
	
	// will add employee to specific parking lot
	public void addEmployeeToParkingLot(int parking_id,Employee employee){
		 
        if(checkIfparkingLotExist(parking_id)){
		   Parking_Lot pl=GetParkingLotById(parking_id);
		   if(pl.checkIfEmployeeExist(employee)){
		      System.err.println("emloyee exist already");
		   }
		   else{
			   pl.addEmployee(employee);
		   }
        }
        else{
        	System.err.println("canot add employee to parking lot ");
        	System.err.println("arking lot doesnot exist");
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
	public void orderParking(int parking_id,Customer customer,String car_id,int order_id, int order_type,String email ,Date start, Date end) {
//		ObjectSender snd = new ObjectSender(4,parking_id+" " + customer_id + " " + car_id + " " + email + " ");
//		String msg = ClientServerController.sendMsgToServer(snd).toString();
//		if(msg.equals("failed!")) {
//			return false;
//		}
//		return true;
		//TODO add order type
				
	  Parking_Lot pl = GetParkingLotById(parking_id);
	  if(OrderType.Order.getOrderType() == order_type){
		   
		  order(pl,customer, car_id, order_id,email ,start , end);
	  }
      if(OrderType.PreOrder.getOrderType() == order_type){
		  
    	  orderPreOrderParking(pl,customer, car_id,order_id,email ,start, end);
	  }
      if(OrderType.MembershipOrder.getOrderType() == order_type){
		  
    	  orderMembershipParking(pl,customer, car_id);
	  }
		
	}
	
	public void order(Parking_Lot pl,Customer customer,String car_id, int order_id,String email,Date start , Date end){
		//TODO
/*		Location new_loc=pl.FindNearestPlLocation(); 
		Order order =new Order((new Customer(Integer.toString(customer_id),customer_id)), car_id, email, departure);
		pl.updateEnableParking(new ParkedCar(car_id, customer_id, new_loc, order));*/
	}
	
	
	// will handle pre order parking request
	public void orderPreOrderParking(Parking_Lot pl,Customer customer,String car_id, int order_id,String email,Date start , Date end){
		//TODO
/*		Location new_loc=pl.FindNearestPlLocation(); 
		PreOrder pre_order =new PreOrder((new Customer(Integer.toString(customer_id),customer_id)), car_id, email, arrival,departure);
		pl.updateEnableParking(new ParkedCar(car_id, customer_id, new_loc, pre_order));
		*/
	}
	
	//will handle orders of type membership
	public void orderMembershipParking(Parking_Lot pl,Customer customer, String car_id){
		
		if(pl.checkIfMembershipExist(customer.getId(), car_id)){
			Location new_loc=pl.FindNearestPlLocation(); 
			//MembershipOrder mo= new MembershipOrder(pl.getMembership(customer_id, car_id), null);
			//pl.updateEnableParking(new ParkedCar(car_id, customer_id, new_loc, mo));
		}		
	}
	
	// will check that parking lot exist
	public boolean doesParkingLotExist(int parking_id) {
		Parking_Lot pl=GetParkingLotById(parking_id);
		if(pl==null)
			return false;
		return true;
	}

	public void exitParking(int parking_id,int customer_id , int car_id) {
		Parking_Lot pl=GetParkingLotById(parking_id);
		if(pl==null){
			System.err.println("parking lot does not exist");
		}
		
		pl.exitParking(customer_id,car_id);
	}

	public void cancelParking(int parking_id , int custome_id , int car_id) {
		Parking_Lot pl=GetParkingLotById(parking_id);
		pl.CancelParking(custome_id,car_id);
	}
	
	// method called by manager 
	public void changePrices(int order_type, double new_price){
		for(Parking_Lot pl : parking){
			pl.changePriceOfOrder(order_type ,new_price);
		}
	}
	
	public void fileComplaint(int customer_id , int parking_id , String complaint){
		Parking_Lot pl=GetParkingLotById(parking_id);
		if(pl==null){
			System.err.println("parking lot does not exist");
		}
		pl.fileComplaint(customer_id, complaint);
		
	}
	
	
	// display function for gui
	// will display all locations which are not available
	public ArrayList<Pair<Integer, Integer>> display(int parking_id, int rows , int col){
		ArrayList<Pair<Integer, Integer>> view_array=new ArrayList<Pair<Integer, Integer>>();
		Parking_Lot pl =GetParkingLotById(parking_id);
		for(int i=0;i<rows;i++)
		{
		  for(int j=0; j<col;j++){
			  Location loc=new Location(i, j, 1);
			  if(!pl.isParkingAvailableAtLocation(loc)){
				 Pair<Integer, Integer > indexes = new Pair<>(i,j);
				 view_array.add(indexes);
			  }
		 }
		  
		}
		return view_array;	
	}
	
	



}
