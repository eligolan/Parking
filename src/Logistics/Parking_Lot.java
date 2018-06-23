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
	
	/**
	 * constructor for parking lot 
	 * @param parking_lot_id integer representing the parking lot id
	 * @param manger_id  integer representing the maager id
	 * @param capacity integer representing the parking lot car space capacity
	 */
	public Parking_Lot(int parking_lot_id, int manger_id, int capacity)
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
	
	/**
	 * will reinitialize parking lot when exiting the system
	 */
	public void exitSystem(){
		employees.clear();
		all_orders.clear();
		invalid_parking_location.clear();
		next_nearest_location.setX(1);
		next_nearest_location.setY(1);
		next_nearest_location.setZ(1);
	}

	/**
	 * setups locations for next nearest location in parking lot
	 */
	public void SetUpLocations(){
		
		maximum_corrdinates=new Location(4, 4, 3);
		next_nearest_location =new Location(1, 1, 1);
	}
	
	/**
	 * will add an invalid location
	 * @param loc Location type represents a locatio which is not valid
	 * meaning a location where a car cannot park
	 */
	public void addInvalidLocation(Location loc){
		invalid_parking_location.add(loc);
	}
	
	
	/**
	 * will add a new order to parking lot
	 * @param order type Order 
	 */
	public void AddOrderToParkingLot(Order order){
		all_orders.add(order);
	}
	
	/**
	 * updates next nearest location in parking lot
	 * after adding a car to parking lot and after removing
	 */
	public void updateNextNearestLocation(){
		
		boolean loop_flag=true;
		
		
		while(loop_flag){
		if(maximum_corrdinates.getY()==next_nearest_location.getY()){
			next_nearest_location.setY(1);
			if(next_nearest_location.getZ()==maximum_corrdinates.getZ()){
				next_nearest_location.setZ(1);
				next_nearest_location.setX(next_nearest_location.getX()+1);
			}
		}
		else{
			next_nearest_location.setY(next_nearest_location.getY()+1);
		}
//		while(loop_flag){
//		if(maximum_corrdinates.getZ()==next_nearest_location.getZ()){
//			next_nearest_location.setZ(1);
//			if(next_nearest_location.getY()==maximum_corrdinates.getY()){
//				next_nearest_location.setY(1);
//				next_nearest_location.setX(next_nearest_location.getX()+1);
//			}
//		}
//		else{
//			next_nearest_location.setZ(next_nearest_location.getZ()+1);
//		}
//	   	
		if(isParkingAvailableAtLocation(next_nearest_location))
			loop_flag=false;
	  }
		
//		System.out.println("update next nearest location");
//		System.out.println( next_nearest_location.getX());
//		System.out.println(next_nearest_location.getY());
//		System.out.println(next_nearest_location.getZ());
	}
	
	
	/**
	 * will add a new employee to parking lot
	 * @param employee type Employee
	 */
	public void addEmployee(Employee employee)
	{
		employees.add(employee);
	}
	
	/**
	 * fined next nearest location
	 * @return Location which indicates next nearest location to be parked at
	 */
	public Location FindNearestPlLocation(){
		return new Location(next_nearest_location.getX(),next_nearest_location.getY(),next_nearest_location.getZ());
	}
	
	/**
	 * sets next nearest location
	 * @param loc type Location
	 */
	public void SetNearestLocation(Location loc){
	    next_nearest_location.setX(loc.getX());
	    next_nearest_location.setY(loc.getY());
	    next_nearest_location.setZ(loc.getZ());
	}


	/**
	 * adds a car to parking lot
	 * @param order Order type containing order information
	 * @return Location of place where car was parked
	 */
	public Location updateEnableParking(Order order)
	{   
		if (!isParkingAvailableAtLocation(order.GetOrderLocation()))
            return null;   
		updateNextNearestLocation();
		
//		System.out.println("reservation location");
//		System.out.println(order.GetOrderLocation().getX());
//		System.out.println(order.GetOrderLocation().getY());
//		System.out.println(order.GetOrderLocation().getZ());
		return order.GetOrderLocation();
	}
	
	/**
	 * removes a car from parking lot
	 * @param order type Order indicating order information
	 */
	public void updateAbleParking(Order order)
	{   
	    all_orders.remove(order);
	    next_nearest_location.setX(1);
	    next_nearest_location.setY(1);
	    next_nearest_location.setZ(1);
	    updateNextNearestLocation();
	}
	
	
	/**
	 * checks if parking lot is full
	 * @return True or False
	 */
	public boolean isParkingLotFull(){
		if (all_orders.size()==maximum_capacity)
			return true;
		return false;
		
	}
    
	/**
	 * checks if parking lot is available at a certain location
	 * @param loc type Location which contains location coordinates
	 * @return True or False
	 */
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
	
   
	/**
	 * will remove car from parking lot by order id 
	 * @param order_id integer representing nuber of reservaition 
	 ** @return true of false
	 */
	public boolean exitParking(int order_id){
		for (Order order : all_orders) { 
	
			if (order.getOrderId() == order_id){
				updateAbleParking(order);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * will get all locations in parking lot which are not available
	 * @return Array of all location where parking lot is full
	 */
	public ArrayList<Location> getAllParkedLocaions(){
		ArrayList<Location> locs=new ArrayList<Location>();
		for(Order order : all_orders){
			locs.add(order.GetOrderLocation());
		}
		return locs;
	}
	/**
	 * cancels a parking order
	 * @param order_id integer representing order number 
	 * @return true or false
	 */
	
	public boolean CancelParking(int order_id){
	     	return exitParking(order_id);
	}
	
	/**
	 * gets Order from parking lot by order id
	 * @param order_id integer representing order number
	 * @return Order type 
	 */
	public Order getOrder(int order_id){
		
		for(Order o : all_orders){
			if(o.getOrderId() == order_id){
				return o;
			}
		}
		return null;
	}

    /**
     * 
     * @return integer representing parking lot capacity 
     */
	public int getMaximumCapacity(){
		return maximum_capacity;
	}
	
	/**
	 * set maximum capacity
	 * @param maximum_capacity iteger
	 */
	public void setMaximumCapacity(int maximum_capacity){
		this.maximum_capacity=maximum_capacity;
	}
	
	/**
	 * 
	 * @return integer representing parking lot id
	 */
	public int getId(){
		return parking_lot_id;
	}
	
}
