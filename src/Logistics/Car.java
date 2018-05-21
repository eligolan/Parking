package Logistics;

public class Car {
	private int idCar;
	private int ownerId;
	
	public Car(int id, int ownerId)
	{
		this.idCar = id;
		this.ownerId = ownerId;
	}
	
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
