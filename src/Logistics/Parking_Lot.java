package Logistics;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Actors.Customer;
import Actors.Employee;
import Actors.Manager;
import Actors.MemberShip;

public class Parking_Lot {
	int numId;
	private List<ParkedCar> listCar;
	private List<Employee> employees;
	private List<MemberShip> pl_memeberships;
	private List<Location> invalid_parking_location;
	private Map<Location, ParkedCar> parkingZone;
	private Map<Location, Integer> reservedParkings;
	private Manager manager;
	private String loc;
	private String name;
	private int maximum_capacity;
	private Location maximum_corrdinates;
	private Location next_nearest_location;
	
	public Parking_Lot(int numId,String loc, String name, Manager manger, int capacity)
	{
		this.numId = numId;
		this.loc = loc;
		this.name = name;
		this.setManager(manger);
		listCar = new ArrayList<ParkedCar>();
		invalid_parking_location= new ArrayList<Location>();
		employees = new ArrayList<Employee>();
		pl_memeberships= new ArrayList<MemberShip>();
		parkingZone = new HashMap<Location, ParkedCar>();
		reservedParkings = new HashMap<Location, Integer>();
		maximum_capacity=capacity;
		
		SetUpLocations();
		
	}
	
	public void SetUpLocations(){
		
		maximum_corrdinates=new Location(4, 4, 1);
		next_nearest_location =new Location(1, 1, 1);
	}
	
	public void addInvalidLocation(Location loc){
		invalid_parking_location.add(loc);
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
	
	// will check if employee exist
	public boolean checkIfEmployeeExist(Employee employee){
		for (Employee empl : employees) {
			if (empl.getId()==employee.getId())
				return true;
		}	
		return false;
	}
	
	// will add membership to parking lot
	public void addMembershipToPl(String name, int id, int carId){
		pl_memeberships.add(new MemberShip(name, id, carId));
	}
	
	// will get membership by id and car id
	public MemberShip getMembership(int id , int car_id){
		for (MemberShip ms : pl_memeberships){
			if(ms.getCarId()==car_id && ms.getId()==id)
				return ms;
		}
		return null;
	}
	
	// check if customer has membership to parking lot
	public boolean checkIfMembershipExist(int id , int carId){
		for(MemberShip ms : pl_memeberships){
			if(ms.getId()==id && ms.getCarId()==carId)
				return true;
		}
		return false;
	}
	
	
	// will place car in parking lot
	public void updateEnableParking(ParkedCar car)
	{   
		if (isParkingAvailableAtLocation(car.getLocation())){
            Location c=car.getLocation();			
			Location locNew  = new Location(c.getX(),c.getY(),c.getZ());
		    listCar.add(car);
		    parkingZone.put(car.getLocation(), car);
		    updateNextNearestLocation();
		}
		else{
			System.err.println("location not available");
		}
		
	}
	
	//will remove car from location
	public void updateAbleParking(ParkedCar car)
	{   
		Location locNew  = car.getLocation();
		listCar.remove(car);
		parkingZone.remove(car.getLocation());
	}
	
	// will check if parking lot is full
	public boolean isParkingLotFull(){
		if (listCar.size()==maximum_capacity)
			return true;
		return false;
		
	}
    
	// will check if parking is available at certain location
	public boolean isParkingAvailableAtLocation(Location loc){
		
		for (Location key : parkingZone.keySet()) {  
			if (key.getX()== loc.getX() && key.getY()==loc.getY() && key.getZ()==loc.getZ())
				return false;
		}
		
		for (Location key : reservedParkings.keySet()) {
			if (key.getX()== loc.getX() && key.getY()==loc.getY() && key.getZ()==loc.getZ())
				return false;
		}
		
		for(Location l : invalid_parking_location){
			if (l.getX()== loc.getX() && l.getY()==loc.getY() && l.getZ()==loc.getZ())
				return false;
		}
		
		return true;
	}
	
	public void reserveLocation(Location loc, int customer_id){
		
		if(isParkingAvailableAtLocation(loc)){
			reservedParkings.put(loc, customer_id);
		}
		else{
			System.err.println("parking location not available");
		}
		
	}
    
	// will exit from parking lot
	public void exitParking(int customer_id,int car_id){
		for (Map.Entry<Location ,ParkedCar> entry : parkingZone.entrySet()) { 
			
			Location loc = entry.getKey();
			ParkedCar car =entry.getValue();
			
			if (car.getId()== car_id && car.getOwnerId()==customer_id){
				updateAbleParking(car);
				break;
			}
		}
	}
	
	//will remove car from parking lot
	public void removeCarFromParkingLot(Location loc,ParkedCar car){
		
		AllOrders order_type= car.getOrder();
		double payment = order_type.pay();
		parkingZone.remove(loc);
		SetNearestLocation(loc);
	}
	
	// change prices per order called by manger 
	public void changePriceOfOrder(int order_type ,double new_price){
		
		for( Map.Entry<Location , ParkedCar> entry : parkingZone.entrySet()){
			ParkedCar car= entry.getValue();
			AllOrders order =car.getOrder();
			if(order_type== order.getOrderType()){
				order.setPricePerHour(new_price);
				car.setOrder(order);
				parkingZone.put(entry.getKey(),car);
			}
		}
	}
	
	public void fileComplaint(int customer_id,String complaint){
		String response = manager.getComplaintResponse(complaint);
		if (response.equals("accepted")){
			double customer_credit =manager.getCredit();
		}
		else{
			System.out.println("complaint rejected");
		}
	}
	
	public double CancelParking(int customer_id,int car_id){
		double fine_for_cancelation=0;
		for(ParkedCar pc : listCar){
			if(pc.getOwnerId()==customer_id && pc.getId()==car_id){
					
//				Date date=new Date();
//				@SuppressWarnings("deprecation")
//				int cancelation_time= pc.getOrder().getArrivalTime() - date.getHours();
//				if(cancelation_time >=3)
//					fine_for_cancelation = pc.getOrder().getPricePerHour() * 0.1;
//				if(cancelation_time <3 && cancelation_time > 1)
//					fine_for_cancelation = pc.getOrder().getPricePerHour() * 0.5;
//				if(cancelation_time >0 && cancelation_time <=1)
//					fine_for_cancelation=pc.getOrder().getPricePerHour() * 0.1;
				
				
//				break;
				ParkedCar car_n = getCar(customer_id,car_id);
				removeCarFromParkingLot(car_n.getLocation(), car_n);
 				System.out.println(parkingZone.size());
	    	}
			
		}
		
		System.out.println(fine_for_cancelation);
		return fine_for_cancelation;
	}
	
	public ParkedCar getCar(int customer_id,int car_id){
		for (Map.Entry<Location ,ParkedCar> entry : parkingZone.entrySet()) { 
			
			Location loc = entry.getKey();
			ParkedCar car =entry.getValue();
			
			if (car.getId()== car_id && car.getOwnerId()==customer_id){
				return car;
			}
		}
		return null;
	}
	
	
	
	
	public String viewOrder(int customer_id,int car_id){
		
		String pl_name=name + "\n";
		String view="";
		for(ParkedCar pc : listCar){
			if(pc.getOwnerId()==customer_id && pc.getId()==car_id)
				view= pc.getOrder().view();
		}
		return pl_name +view;
	}
	
	//setters and getters 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}
	public int getMaximumCapacity(){
		return maximum_capacity;
	}
	
	public void setMaximumCapacity(int maximum_capacity){
		this.maximum_capacity=maximum_capacity;
	}
	
	public int getId(){
		return numId;
	}
	
	public void setId(int id){
		numId=id;
	}


}
