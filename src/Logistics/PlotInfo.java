package Logistics;

import java.io.Serializable;
public class PlotInfo implements Serializable {
	
	int parking_id;
	int m_id;
	int capacity;
	
	public PlotInfo(int parking_id,int m_id, int capacity){
		this.parking_id=parking_id;
		this.m_id=m_id;
		this.capacity =capacity;
		
	}
	
	public int getPLId(){return parking_id;}
	public int getMId(){return m_id;}
	public int getCapacity(){return capacity;}

}
