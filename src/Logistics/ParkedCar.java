package Logistics;

public class ParkedCar extends Car {
	Location loc;
	AllOrders order_type;
	
	public ParkedCar(int id, int ownerId, Location loc, AllOrders order_type) {
		super(id, ownerId);
		this.loc=loc;
		this.order_type=order_type;
	}
	
	public void setLocation(Location loc)
	{
		this.loc = loc;
	}
	
	public Location getLocation()
	{
		return loc;
	}
	
	public void setOrder(AllOrders order)
	{
		this.order_type = order;
	}
	
	public AllOrders getOrder()
	{
		return order_type;
	}
	

}
