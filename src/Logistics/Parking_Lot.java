package Logistics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Actors.Employee;
import Actors.Manager;

public class Parking_Lot {
	private List<ParkedCar> listCar;
	private List<Employee> employees;
	private Map<Location, ParkedCar> parkingZone;
	private Manager manager;
	private Location loc;
	private String name;
	
	public Parking_Lot(Location loc, String name, Manager manger)
	{
		this.loc = loc;
		this.name = name;
		this.setManager(manger);
		listCar = new ArrayList<ParkedCar>();
		employees = new ArrayList<Employee>();
		parkingZone = new HashMap<Location, ParkedCar>();
	}
	
	public void addEmployee(Employee employee)
	{
		employees.add(employee);
	}
	
	public void updateEnableParking(ParkedCar car)
	{
		Location locNew  = car.getLocation();
		listCar.add(car);
		parkingZone.put(locNew, car);
		
	}
	
	public void updateAbleParking(ParkedCar car)
	{
		Location locNew  = car.getLocation();
		listCar.remove(car);
		parkingZone.remove(locNew);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Location getLoc() {
		return loc;
	}
	public void setLoc(Location loc) {
		this.loc = loc;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

}
