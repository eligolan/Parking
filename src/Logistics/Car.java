package Logistics;

public class Car {
	private int idCar;
	private int ownerId;
	
	/**
	 * constructor
	 * @param id integer car_id
	 * @param ownerId integer owner id
	 */
	public Car(int id, int ownerId)
	{
		this.idCar = id;
		this.ownerId = ownerId;
	}
	
	// setters and getters
	public int getId()
	{
		return idCar;
	}
	
	public int getOwnerId()
	{
		return ownerId;
	}
	
	public void setId(int id)
	{
		this.idCar = id;
	}
	
	public void setOwnerId(int ownerId)
	{
		this.ownerId = ownerId;
	}

}
