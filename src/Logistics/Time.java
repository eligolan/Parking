package Logistics;
public class Time {
	
	int hour;
	int day;
	// will be string like yyyy/MM/dd
	String date;
 
	public Time(int hour , int day, String date ){
		this.day=day;
		this.hour=hour;
		this.date=date; 
	}
    
	public int getDay(){
		return day;
	}
	
	public int getHour(){
		return hour;
	}
	
	public String getDate(){
		return this.date;
	}
	
	public void setDay(int day){
		this.day=day;
	}
	public void setHour(int hour){
		this.hour=hour;
	}
	
	public void setDate(String date){
		this.date=date;
	}
}
