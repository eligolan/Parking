package Logistics;

public class ParkedCar extends Car {
	Location loc;
	AllOrders order_type;
	
	/**
	 * constructor
	 * @param id integer car id 
	 * @param ownerId integer owner id
	 * @param loc Location where car is parked in parking lot
	 * @param order_type type AllOrders containing order information
	 */
	public ParkedCar(int id, int ownerId, Location loc, AllOrders order_type) {
		super(id, ownerId);
		this.loc=loc;
		this.order_type=order_type;
	}
	
	//setters and getters
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
