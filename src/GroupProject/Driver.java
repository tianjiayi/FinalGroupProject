package GroupProject;
import java.sql.*;
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
public class Driver {

	public static void main(String[] args) throws Exception{
		getConnection();
				
	}
	/*	// Driver for mySql
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
		}}}*/
		
		
	public static Connection getConnection() throws Exception{
		try{
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://127.0.0.1:3306/airlineReservationDB";
			String username = "root";
			String password = "1";
			Class.forName(driver);
			
			Connection conn = (Connection)DriverManager.getConnection (url,username, password);
			System.out.println("Connected");
			
			
			
			//2. create a statement
			Statement myStat = conn.createStatement();
			//3. execute SQL query
			ResultSet myRs = myStat.executeQuery("select * from flight");
			//4. process the result set
			while(myRs.next()){
				System.out.println(myRs.getString("destination") + "   ,   "+ myRs.getString("flightCode"));
				
								
			}
			
						
			
		}catch(Exception ex){
			System.out.println(ex);
			
			
		}
			
		
		
		return null;
	}
		
		
		
		
}