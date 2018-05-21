package Logistics;

public class Location {
	private int corX;
	private int corY;
	
	public Location(int x, int y)
	{
		this.corX = x;
		this.corY = y;
	}
	
	public int getX()
	{
		return corX;
	}
	
	public int getY()
	{
		return corY;
	}
	
	public void setX(int x)
	{
		this.corX = x;
	}
	
	public void setY(int y)
	{
		this.corY = y;
	}

}
