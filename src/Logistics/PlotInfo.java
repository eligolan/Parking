package Logistics;

import java.io.Serializable;
public class PlotInfo implements Serializable {
	
	int parking_id;
	int m_id;
	int capacity;
	
	/**
	 * constructor
	 * @param parking_id integer parking lot id
	 * @param m_id integer manager id
	 * @param capacity integer parking lot capacity
	 */
	public PlotInfo(int parking_id,int m_id, int capacity){
		this.parking_id=parking_id;
		this.m_id=m_id;
		this.capacity =capacity;
	}
	
	// setters and getters
	public int getPLId(){return parking_id;}
	public int getMId(){return m_id;}
	public int getCapacity(){return capacity;}

}
