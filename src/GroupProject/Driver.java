package GroupProject;
import java.sql.*;
import com.mysql.jdbc.Connection;
public class Driver {

	public static void main(String[] args) {
		// Driver for mySql
		Connection myConn = null;
		try{
		      
		    	    // Load the JDBC driver
		    	    Class.forName("com.mysql.jdbc.Driver");
		    	    System.out.println("Driver loaded");

			//1. get a connection to database
		    	    //note: my MySQL workbench port number is 3306
			myConn = (Connection)DriverManager.getConnection("jdbc:mysql://airlineDB:3306//airlineReservationDB", "root","1");
			
			if(myConn !=null){
				System.out.println("Connected successfully to database.");
			}
			
			
			
			
			
			
			//2. create a statement
			Statement myStat = myConn.createStatement();
			//3. execute SQL query
			ResultSet myRs = myStat.executeQuery("select * from flight");
			//4. process the result set
			while(myRs.next()){
				System.out.println(myRs.getString("destination") + myRs.getString("flightCode"));
				
				
				//5. Close the connection
			    myConn.close();

				
			}
			
			
			
			
			
		}catch(Exception ex){
			
			ex.printStackTrace();
			
		}

	}

}
