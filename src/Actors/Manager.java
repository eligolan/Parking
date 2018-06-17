package Actors;

public class Manager extends Employee {

	public Manager(String name, int id) {
		super(name, id);
	}
	
	public String getComplaintResponse(String complaint){
		if (complaint.contains("sue you"))
		   return "accepted";
		return "rejected";
	}
	
	public	double getCredit(){
		return 150.0;
	}

}
