package Logistics;

import java.io.Serializable;

public class Location implements Serializable{
	private int corX; // row
	private int corY; // column
	private int corZ; // depth
	
	/**
	 * constructor
	 * @param x row
	 * @param y col
	 * @param z depth
	 */
	public Location(int x, int y, int z) 
	{
		this.corX = x;
		this.corY = y;
		this.corZ=z;
	}
	
	// setters and getters
	public int getX()
	{
		return corX;
	}
	
	public int getY()
	{
		return corY;
	}
	
	public int getZ()
	{
		return corZ;
	}
	
	public void setX(int x)
	{
		this.corX = x;
	}
	
	public void setY(int y)
	{
		this.corY = y;
	}
	
	public void setZ(int z)
	{
		this.corZ = z;
	}

}
