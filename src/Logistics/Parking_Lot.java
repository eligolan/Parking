package Logistics;
import java.util.ArrayList;
import java.util.List;
import Actors.Employee;


public class Parking_Lot {
	
	int parking_lot_id;
	private List<Employee> employees;
	private List<Location> invalid_parking_location;
    private ArrayList<Order> all_orders;
	private int manager_id;
	private int maximum_capacity;
	private Location maximum_corrdinates;
	private Location next_nearest_location;
	
	public Parking_Lot(int Parking_lot_id, int manger_id, int capacity)
	{
		this.parking_lot_id = parking_lot_id;
		invalid_parking_location= new ArrayList<Location>();
		employees = new ArrayList<Employee>();
		all_orders=new ArrayList<Order>();

		if(capacity==1){
		   maximum_capacity=12;
		}
		else {
			maximum_capacity=24;
		}
		
		SetUpLocations();
		
	}

	
	public void SetUpLocations(){
		
		maximum_corrdinates=new Location(4, 4, 1);
		next_nearest_location =new Location(1, 1, 1);
	}
	
	public void addInvalidLocation(Location loc){
		invalid_parking_location.add(loc);
	}
	
	public void AddOrderToParkingLot(Order order){
		all_orders.add(order);
	}
	
	public void updateNextNearestLocation(){
		
		boolean loop_flag=true;
		
		
		while(loop_flag){
		if(maximum_corrdinates.getY()==next_nearest_location.getY()){
			next_nearest_location.setY(1);
			if(next_nearest_location.getZ()==maximum_corrdinates.getZ()){
				System.out.println("heheheh");
				next_nearest_location.setZ(1);
				next_nearest_location.setX(next_nearest_location.getX()+1);
			}
		}
		else{
			next_nearest_location.setY(next_nearest_location.getY()+1);
		}
	   	
		if(isParkingAvailableAtLocation(next_nearest_location))
			loop_flag=false;
	  }
	}
	
	
	// will add employee to parking lot
	public void addEmployee(Employee employee)
	{
		employees.add(employee);
	}
	
	public Location FindNearestPlLocation(){
		return new Location(next_nearest_location.getX(),next_nearest_location.getY(),next_nearest_location.getZ());
	}
	
	public void SetNearestLocation(Location loc){
	    next_nearest_location.setX(loc.getX());
	    next_nearest_location.setY(loc.getY());
	    next_nearest_location.setZ(loc.getZ());
	}


	// will place car in parking lot
	public Location updateEnableParking(Order order)
	{   
		if (!isParkingAvailableAtLocation(order.GetOrderLocation()))
            return null;   
		updateNextNearestLocation();
		System.out.println(all_orders.size());
		return order.GetOrderLocation();
	}
	
	//will remove car from location
	public void updateAbleParking(Order order)
	{   
	    all_orders.remove(order);
	    next_nearest_location.setX(1);
	    next_nearest_location.setY(1);
	    next_nearest_location.setZ(1);
	    updateNextNearestLocation();
	}
	
	
	// will check if parking lot is full
	public boolean isParkingLotFull(){
		if (all_orders.size()==maximum_capacity)
			return true;
		return false;
		
	}
    
	// will check if parking is available at certain location
	public boolean isParkingAvailableAtLocation(Location loc){
		
		for (Order order : all_orders) {  
			Location key=order.GetOrderLocation();
			if (key.getX()== loc.getX() && key.getY()==loc.getY() && key.getZ()==loc.getZ())
				return false;
		}
		
		for(Location l : invalid_parking_location){
			if (l.getX()== loc.getX() && l.getY()==loc.getY() && l.getZ()==loc.getZ())
				return false;
		}
		
		return true;
	}
	
   
	// will exit from parking lot
	public boolean exitParking(int order_id){
		for (Order order : all_orders) { 
	
			if (order.getOrderId() == order_id){
				updateAbleParking(order);
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Location> getAllParkedLocaions(){
		ArrayList<Location> locs=new ArrayList<Location>();
		for(Order order : all_orders){
			locs.add(order.GetOrderLocation());
		}
		return locs;
	}
	
	public boolean CancelParking(int order_id){
	     	return exitParking(order_id);
	}
	
	public Order getOrder(int order_id){
		
		for(Order o : all_orders){
			if(o.getOrderId() == order_id){
				return o;
			}
		}
		return null;
	}


	public int getMaximumCapacity(){
		return maximum_capacity;
	}
	
	public void setMaximumCapacity(int maximum_capacity){
		this.maximum_capacity=maximum_capacity;
	}
	
	public int getId(){
		return parking_lot_id;
	}
	
	public void setId(int id){
		parking_lot_id=id;
	}


}
