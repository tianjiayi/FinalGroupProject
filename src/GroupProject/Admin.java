package GroupProject;
import java.util.ArrayList;
public class Admin extends Member{
     private int adminID;
     
     public Admin(){
     }
     public Admin(int AdminID){
    	 this.adminID= AdminID;
    	 
    	 
     }

     
     
     public int getAdminID() {
		return adminID;
	}
	public void setAdminID(int adminID) {
		this.adminID = adminID;
	}
	
	
	public void addFlight(String flightCode){
		
	}
	
	
	public void dropFlight(String flightCod){
		
		
		
	}
	
	public void addPassenger(String MemberID){
		
	}
	
	public void dropPassenger(String MemberID){
		
	}
		
		
		
	@Override
	
     public String toString(){
     	return super.getUsername()+super.getPassword()+super.getFirstName();
     }
     
public void checkUsers(ArrayList userlist){
     	
     }
}