package Logistics;

public class ParkedCar extends Car {
	Location loc;

	public ParkedCar(int id, int ownerId) {
		super(id, ownerId);
		
	}
	
	public void setLocation(Location loc)
	{
		this.loc = loc;
	}
	
	public Location getLocation()
	{
		return loc;
	}

}
