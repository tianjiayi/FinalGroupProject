package GroupProject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.*;
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Scanner;

public class app {
	

	public static void main(String[] args) throws Exception{
		
		
		Connection conn = Driver.getConnection();
		Scanner input = new Scanner(System.in);
		
		System.out.print("Welcome to the airline booking system, Are you a member? Y/N" );
		
		String membership = input.next();
		if(membership.equalsIgnoreCase("Y")){
			System.out.print("Please enter username: ");
			String username = input.next();
			System.out.print("Please enter password: ");
			String password = input.next();
			
			//member login process
			
			
			ResultSet memberRs = getMemberIdByUsernameAndPassword(conn, username, password);
			//found member in Db
			if(memberRs.next()){
				int member = memberRs.getInt("memberID"); 
				//show booking info
				List<Integer> FlightIdList = getFlightIdsByMemberId(member, conn);
				printFlightInfoByFlightId(conn, FlightIdList);
				//add or delete flight goes here
				//add flight need user input destination and departure...get a flight
				//user input Y or N to book
				//Y ---> save to booking table ()
			}else{
				//member not found
				PreparedStatement getMemberByUsernamePS = conn.prepareStatement(Queries.GET_MEMBER_BY_USERNAME);
				getMemberByUsernamePS.setString(1, username);
				ResultSet memberRsByUsername = getMemberByUsernamePS.executeQuery();
				if(memberRsByUsername.next()){
					//pop security question
					String sq = memberRsByUsername.getString("securityQuestion");
					System.out.println(sq);
					String sqAnswer = input.next();
					int memberId = -1;
					if(memberRsByUsername.getString("securityAnswer").equalsIgnoreCase(sqAnswer)){
						System.out.println("login sucess");
						memberId = memberRsByUsername.getInt("memberID");
						//show booking info
						List<Integer> FlightIdList = getFlightIdsByMemberId(memberId, conn);
						printFlightInfoByFlightId(conn, FlightIdList);
						//1 find all flight id in booking table by member id
						//2 find all flight in flight table by flight id from step 1
					}else{
						//wrong security answer
						System.out.println("login failed please register");
						memberId = createUser(input, conn);
					}
				}else{
					System.out.println("Username and password are not found. Please register first.");
					createUser(input, conn);
					
					//wrong username and wrong password situation goes here
					//create user
				}
			}
		}else if(membership.equalsIgnoreCase("N")){
			createUser(input, conn);
		}
			
			
//			int count=0;
//			
//			do{
//				
//				
//			Scanner y = new Scanner(System.in);
//			System.out.print("Please enter username: ");
//			String username = y.next();
//			System.out.print("Please enter password: ");
//			String password = y.next();
//			
//			
//			
//			
//			try {
//				Connection conn = Driver.getConnection();
//				//2. create a statement
//				PreparedStatement myStat = conn.prepareStatement(Queries.GET_USER_BY_USERNAME_AND_PASSWORD);
//			    //3. execute SQL query
//				myStat.setString(1, username);
//				myStat.setString(2, password);
//				//4. process the result set
//				ResultSet resultSet = myStat.executeQuery(Queries.GET_USER_BY_USERNAME_AND_PASSWORD);
//				if(resultSet.getString("MemberID")==null){
//					
//					if(count>3){
//						System.out.print("Sorry, you tried too many times. Bye!" );
//						System.exit(0);
//						
//					}else
//					
//					System.out.print("Invalid input, please try again, or try answer question to find back your password? Y/N" );
//					count++;	
//					 if(input.next().equalsIgnoreCase("Y")){
//						 
//						 
//						 System.out.print("Enter your username to get security question: ");
//						 String usernameS= input.next();
//						 
//					 
//						 //call method 11
//						 System.out.print("Enter your answer: ");
//						 String answer = input.next();
//						 
//						 //call method 12, check if match the answer from database, if matches ,show password
//						 
//						 
//						 
//						 
//					 }
//					
//					
//					
//				}else{
//					
//					//validation of the Admin status
//					if(resultSet.getString("AdminID")!=null){
//						
//						
//					//start a loop for admin	
//						int adminSelection=0;
//						while(adminSelection!=10){
//						
//
//						System.out.print("Admin Main menu \n" + 
//						"1:check flight information \n"+
//						"2:add flight \n"+ 
//						"3:delate flight\n"+
//						"4:check member information\n"+ 
//						"5:add passenger \n"+ 
//						"6:delete passenger\n"+
//						"7:booking flight \n"+
//						"8:check booked flight informtation \n"+
//						"9:check fyour profile \n"+
//						"10:log out \n"+"Enter a choice: ");
//						
//						adminSelection = input.nextInt();
//						
//						if(adminSelection== 1){
//							//call method 3
//							break;
//							}
//						
//						else if(adminSelection==2){
//							System.out.print("Please add a flightCode:");
//							String flightCode = input.next();
//							System.out.print("Add a flight id");
//							int flightID = input.nextInt();
//							System.out.print("Enter the destination: ");
//							String destination = input.next();
////							System.out.print("Enter the departureTime (yyyy-MM-ddTHH:mm");
////							DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm",
////                                    Locale.US);							
////							System.out.print("Enter the arrivalTime: ");
//							
//							System.out.print("Enter seat capacity: ");
//							int seatCapacity = input.nextInt();
//							System.out.print("Enter departureCity: ");
//							String departureCity = input.next();
//							
//							//call method 9
//							
//							System.out.println("Flight added.");
//							break;
//					
//						}
//						else if(adminSelection==3){
//						
//							System.out.print("Enter the flight id for drop: ");
//							int dropFlight = input.nextInt();
//							
//							//call method 10
//							
//							System.out.println("flight deleted.");
//							break;
//							
//						}else if(adminSelection==4){
//							//call method 1
//							
//							
//							break;
//							
//							
//						}else if(adminSelection==5){
//							
//						}else if(adminSelection==6){
//							
//						}			
//						else if(adminSelection==7){
//							System.out.print("Enter your departure city: ");
//							String departureCity = input.next();
//							System.out.print("Enter your destination city: ");
//							String destination = input.next();
//							System.out.print("Matching flight information: ");
//							//call method 5
//							
//							
//							
//							System.out.print("Please select a flight id for booking: ");
//							int bookingFlight = input.nextInt();
//							
//							//call method 6
//							
//							
//							System.out.println("flight booked!");
//							break;
//						}
//						else if(adminSelection==8){
//							System.out.println("Enter your memberID: ");
//							int mID = input.nextInt();
//							
//							//call method 7
//							
//							
//							break;
//						}
//						else if(adminSelection==9){
//							System.out.println("Enter your memberID: ");
//							int mID = input.nextInt();
//							
//							
//							//call method 8
//							break;
//						}
//							
//						else if (adminSelection==10){
//							System.out.println("Logged out.");
//							adminSelection=0;
//							System.exit(4);
//							
//						}else
//						System.out.println("Invalid input.Enter an option from 1 to 5: ");
//			
//						
//					}
//						
//						
//					System.out.print("Logged in. Your booked flight information: ");
//					//call method 2
//					
//					break;
//					}
//				
//				} catch (Exception e) {
//				e.printStackTrace();
//			}}while(count<=3);
//			
//			
//			
//			
//		}else if (membership.equalsIgnoreCase("N")){
//			System.out.print("Please register your information below: \n");
//			System.out.print("Enter your first name: ");
//			String fname = input.next();
//			System.out.println("Enter your last name: ");
//			String lname = input.next();
//			System.out.print("Enter your address: ");
//			String address = input.next();
//			System.out.print("Enter your zipcode: ");
//			String zip = input.next();
//			System.out.print("Enter your state: ");
//			String state = input.next();
//			System.out.print("Enter username: ");
//			String username = input.next();
//			System.out.print("Enter password: ");
//			String password = input.next();
//			System.out.print("Enter your email: ");
//			String email = input.next();
//			System.out.print("Enter your ssn: ");
//			String ssn = input.next();						
//			System.out.print("Enter security question: ");
//			String securityQuestion = input.next();
//			
//			//store the user information into database
//			try {
//				//get connection
//				Connection conn = Driver.getConnection();
//				//create a statement
//				PreparedStatement myStat = conn.prepareStatement(Queries.REGISTER_USER_INFO );	
//				ResultSet resultSet = myStat.executeQuery(Queries.REGISTER_USER_INFO);			
//				 
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			
//			
//			
//			//!  return to log in?
//			
//		
//		}
//			
//		
//		
//		
//			System.out.print("Are you ready for booking a flight?  Y/N");
//			String booking = input.next();
//			if(booking.equalsIgnoreCase("Y")){
//				
//				
//				
//				
//				
//				
//				
//				
//			}else if (booking.equalsIgnoreCase("N")){
//				
//				System.out.print("Do you want to log out? Y/N");
//				String logout = input.next();
//				if(logout.equalsIgnoreCase("Y")){
//					System.out.println("logged out.");
//					System.exit(1);
//				}else{
//					int userSelection=0;
//					while(userSelection!=5){
//					System.out.print("How can we help you?\n "+
//					"Main Manu: \n"+ 
//					"1:check flight information \n"+
//					"2:booking flight\n" +
//					"3: check booked flight information"+
//					"4:check your profile \n"+
//					"5:log out \n"+"Enter a choice: ");
//					userSelection = input.nextInt();
//					
//					if(userSelection== 1){
//						//call method 3
//						System.out.print("Enter a number to go back to the main many: ");
//						int anynumber = input.nextInt();
//						break;
//						}
//					else if(userSelection==2){
//						System.out.print("Enter your departure city: ");
//						String departureCity = input.next();
//						System.out.print("Enter your destination city: ");
//						String destination = input.next();
//						System.out.print("Matching flight information: ");
//						//call method 5
//						
//						
//						
//						System.out.print("Please select a flight id for booking: ");
//						int bookingFlight = input.nextInt();
//						
//						//call method 6
//						
//						
//						System.out.println("flight booked!");
//						break;
//					}
//					else if(userSelection==3){
//						System.out.println("Enter your memberID: ");
//						int mID = input.nextInt();
//						
//						//call method 7
//						
//						
//						break;
//					}
//					else if(userSelection==4){
//						System.out.println("Enter your memberID: ");
//						int mID = input.nextInt();
//						
//						
//						//call method 8
//						break;
//					}
//						
//					else if (userSelection==5){
//						System.out.println("Logged out.");
//						userSelection=0;
//						System.exit(3);
//						
//					}else
//					System.out.println("Invalid input.Enter an option from 1 to 5: ");
//					
//					
//					}
//						
//					
//				}
//				
//				
//			}
//		
//		
//		
//		
//		
//		
//		
//		
//		
//	
//		
//		
//		
//	//test	
//		try {
//			//get connection
//			Connection conn = Driver.getConnection();
//			//create a statement
//			PreparedStatement myStat = conn.prepareStatement(Queries.GET_USER_BY_USERNAME_AND_PASSWORD );	
//			myStat.setString(1, "sarahtian");
//			myStat.setString(2,"tjy");
//			
//			ResultSet resultSet = myStat.executeQuery(Queries.GET_USER_BY_USERNAME_AND_PASSWORD);
//		
//			while(resultSet.next()){
//			System.out.println(resultSet.getString("memberID") + "   ,   "+ resultSet.getString("fname")+ "    ,   "+ resultSet.getString("ssn"));
//									
//			}    
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		
//		
//		
//		
//		
//		
//		
//		
//		
//
//		
//		
//		
//		
//		//1. method: get member information
//		public static void method1(){
//			
//		
//		try {
//			//get connection
//			Connection conn = Driver.getConnection();
//			//create a statement
//			PreparedStatement myStat = conn.prepareStatement(Queries.GET_MEMBERINFO );	
//			ResultSet resultSet = myStat.executeQuery(Queries.GET_MEMBERINFO);
//		
//			while(resultSet.next()){
//			System.out.println(resultSet.getString("memberID") + "   ,   "+ 
//			resultSet.getString("lname")+"   ,   "+ resultSet.getString("fname")+ "    ,   "+ 
//			resultSet.getString("address")+"   ,   "+ resultSet.getString("zip")+"   ,    "+
//			resultSet.getString("state")+"   ,   "+ 
//			resultSet.getString("username")+"   ,   "+
//			resultSet.getString("password")+"   ,   "+
//			resultSet.getString("email")+"   ,   "+
//			resultSet.getString("securityQuestion")+"   ,   "+
//			resultSet.getString("adminID")+"   ,   "+
//			resultSet.getString("ssn"));
//									
//			}    
//			} catch (Exception e) {
//				
//				e.printStackTrace();
//			}
//		
//		
//		}}
//		
//	
//		
//	}
//	
//	public static void deleteFlightById(String  dropFlight){
//		try {
//			//get connection
//			Connection conn = Driver.getConnection();
//			//create a statement
//			PreparedStatement myStat = conn.prepareStatement(Queries.DELETE_FLIGHT );	
//			ResultSet resultSet = myStat.executeQuery(Queries.DELETE_FLIGHT);
//			myStat.setString(1, dropFlight);
//			
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		
//		}	
//	
//		//optional method:
//		//get current time method
//		public static java.sql.Timestamp getCurrentTimeStamp() {
//
//			java.util.Date today = new java.util.Date();
//			return new java.sql.Timestamp(today.getTime());
//
//		}
//		
//	
//		
//		
//		
//		
//		//2. method: check booked information
//   public static void method2(){
//	   
//		try {
//			//get connection
//			Connection conn = Driver.getConnection();
//			//create a statement
//			PreparedStatement myStat = conn.prepareStatement(Queries.GET_BOOKINGINFO );	
//			ResultSet resultSet = myStat.executeQuery(Queries.GET_BOOKINGINFO);
//		
//			while(resultSet.next()){
//			System.out.println(resultSet.getString("bookingID") + "   ,   "+ resultSet.getString("bookingDate")+ "    ,   "+ resultSet.getString("memberID")+"    ,   "+ resultSet.getString("flightID"));
//									
//			}    
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//  
//   
//	
//		
//		
//		//3. method: check flight information
//    public static method3(){
//		try {
//			//get connection
//			Connection conn = Driver.getConnection();
//			//create a statement
//			PreparedStatement myStat = conn.prepareStatement(Queries.GET_FLIGHTINFO );	
//			ResultSet resultSet = myStat.executeQuery(Queries.GET_FLIGHTINFO);
//		
//			while(resultSet.next()){
//			System.out.println(resultSet.getString("flightID") + "   ,   "+ resultSet.getString("flightCode")+ "    ,   "+ resultSet.getString("departureCity")+"    ,   "+ resultSet.getString("destination")+"    ,   "+ resultSet.getString("departureTime")+"    ,   "+ resultSet.getString("arrivalTime")+"    ,   "+ resultSet.getString("seatCapacity"));
//									
//			}    
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		
//    }
//    
//		
//		
//		//4. method get user by username and password
//    public static void method4(){
//		try {
//			Connection conn = Driver.getConnection();
//			//2. create a statement
//			PreparedStatement myStat = conn.prepareStatement(Queries.GET_USER_BY_USERNAME_AND_PASSWORD);
//		    //3. execute SQL query
//			myStat.setString(1, username);
//			myStat.setString(2, password);
//			//4. process the result set
//			ResultSet resultSet = myStat.executeQuery(Queries.GET_USER_BY_USERNAME_AND_PASSWORD);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    }
//		
//		
//    
//		//5. method get flight information by departure city and destination
//	public static void method5(){	
//    try {
//			Connection conn = Driver.getConnection();
//			//2. create a statement
//			PreparedStatement myStat = conn.prepareStatement(Queries.GET_FLIGHTCODE_BY_DEPARTURE_DESTINATION);
//		    //3. execute SQL query
//			myStat.setString(1, departureCity);
//			myStat.setString(2, destination);
//			//4. process the result set
//			ResultSet resultSet = myStat.executeQuery(Queries.GET_FLIGHTCODE_BY_DEPARTURE_DESTINATION);
//			while(resultSet.next()){
//				System.out.println(resultSet.getString("flightID") + "   ,   "+ resultSet.getString("flightCode")+ "    ,   "+ resultSet.getString("departureCity")+"    ,   "+ resultSet.getString("destination")+"    ,   "+ resultSet.getString("departureTime")+"    ,   "+ resultSet.getString("arrivalTime")+"    ,   "+ resultSet.getString("seatCapacity"));
//										
//				}   		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//		
//		
//		
//		
//		
//		//6. method flight booking
//	public static void method6(){
//		try {
//			//get connection
//			Connection conn = Driver.getConnection();
//			//create a statement
//			PreparedStatement myStat = conn.prepareStatement(Queries.BOOK_TICKET );	
//			ResultSet resultSet = myStat.executeQuery(Queries.BOOK_TICKET);	
//			myStat.setString(1, memberID);
//			myStat.setString(2, flightID);
//			 
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//	}
//		
//		
//		
//		
//		//7. check booked flight info from customer
//	public static void method7(){
//		try {
//			//get connection
//			Connection conn = Driver.getConnection();
//			//create a statement
//			PreparedStatement myStat = conn.prepareStatement(Queries.GET_BOOKINGINFO_BY_MEMBERID );	
//			ResultSet resultSet = myStat.executeQuery(Queries.GET_BOOKINGINFO_BY_MEMBERID);
//			myStat.setString(1, memberID);
//			while(resultSet.next()){
//			System.out.println(resultSet.getString("bookingID") + "   ,   "+ resultSet.getString("bookingDate")+ "    ,   "+ resultSet.getString("memberID")+"    ,   "+ resultSet.getString("flightID"));
//									
//			}    
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//	}
//	
//	
//	
//		
//		//8. get specific user info by memberID
//	public static void method8(){
//		try {
//			//get connection
//			Connection conn = Driver.getConnection();
//			//create a statement
//			PreparedStatement myStat = conn.prepareStatement(Queries.GET_MEMBERINFO_BY_MEMBERID );	
//			myStat.setString(1, "memberID");
//			
//			
//			ResultSet resultSet = myStat.executeQuery(Queries.GET_MEMBERINFO_BY_MEMBERID);
//		
//			while(resultSet.next()){
//			System.out.println(resultSet.getString("memberID") + "   ,   "+ resultSet.getString("fname")+ "    ,   "+ resultSet.getString("ssn"));
//									
//			}    
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//	}
//		
//		
//		
//		
//		
//		
//		//9.add flight information to database
//	public static void method9(){
//		try {
//			//get connection
//			Connection conn = Driver.getConnection();
//			//create a statement
//			PreparedStatement myStat = conn.prepareStatement(Queries.ADD_FLIGHT );	
//			//ResultSet resultSet = myStat.executeQuery(Queries.ADD_FLIGHT);	
//			//flightCode, destination, seatCapacity,  departureCity, flightID
//			myStat.setString(1, flightCode);
//			myStat.setString(2, destination);
//			myStat.setString(3, seatCapacity);
//			myStat.setString(4, departureCity);
//			myStat.setString(5, flightID);
//			myStat.executeUpdate();
//			
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//	}
//		
//		
//		//10. delete flight from database
//		//user input flightID: dropFlight
//	
//		
//		
//		
//		//11. get the security question based on username
//		//entered: usernameS
//		private void method11(){
//		try {
//			//get connection
//			Connection conn = Driver.getConnection();
//			//create a statement
//			PreparedStatement myStat = conn.prepareStatement(Queries.GET_SECURITYQUESTION_BY_USERNAME );	
//			ResultSet resultSet = myStat.executeQuery(Queries.GET_SECURITYQUESTION_BY_USERNAME);
//			myStat.setString(1, usernameS);
//			
//			while(resultSet.next()){
//				System.out.println(resultSet.getString("securityQuestion") );
//										
//				} 
//			
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		
//		}
//		
	}
	
	private static int createUser(Scanner input, Connection conn) throws SQLException{
		System.out.print("Please register your information below: \n");
		System.out.print("Enter your first name: ");
		String fname = input.next();
		System.out.println("Enter your last name: ");
		String lname = input.next();
		System.out.print("Enter your address: ");
		String address = input.next();
		System.out.print("Enter your zipcode: ");
		String zip = input.next();
		System.out.print("Enter your state: ");
		String state = input.next();
		System.out.print("Enter username: ");
		String username = input.next();
		System.out.print("Enter password: ");
		String password = input.next();
		System.out.print("Enter your email: ");
		String email = input.next();
		System.out.print("Enter your ssn: ");
		String ssn = input.next();						
		System.out.print("Enter security question: ");
		String securityQuestion = input.next();
		System.out.print("Enter security answer: ");
		String sqAnswer = input.next();
		
		//create user in db
		PreparedStatement createMemberPS = conn.prepareStatement(Queries.REGISTER_USER_INFO);
		createMemberPS.setString(1, fname);
		createMemberPS.setString(2, lname);
		createMemberPS.setString(3, address);
		createMemberPS.setString(4, zip);
		createMemberPS.setString(5, state);
		createMemberPS.setString(6, username);
		createMemberPS.setString(7, password);
		createMemberPS.setString(8, email);
		createMemberPS.setString(9, ssn);
		createMemberPS.setString(10, securityQuestion);
		createMemberPS.setString(11, sqAnswer);
		createMemberPS.executeUpdate();
		System.out.println("Record is inserted into Member table!");
		
		//get new user 
		int memberId = -1;
		ResultSet memberRs = getMemberIdByUsernameAndPassword(conn, username, password);
		if(memberRs.next()){
			memberId = memberRs.getInt("memberID"); 
		}
		
		return memberId;
	}
	
	
	private static ResultSet getMemberIdByUsernameAndPassword(Connection conn, String username, String password)  throws SQLException{
		PreparedStatement myStat = conn.prepareStatement(Queries.GET_USER_BY_USERNAME_AND_PASSWORD);
		myStat.setString(1, username);
		myStat.setString(2, password);
		ResultSet memberRs = myStat.executeQuery();
		return memberRs;
	}
	
	private static List<Integer> getFlightIdsByMemberId(int memberId, Connection conn) throws SQLException{
		List<Integer> flightIdList = new ArrayList<>();
		PreparedStatement myStat = conn.prepareStatement(Queries.GET_FLIGHT_BY_MemberID);
		myStat.setInt(1, memberId);
		ResultSet rs = myStat.executeQuery();
		while(rs.next()){
			flightIdList.add(rs.getInt("flightID"));
		}
		return flightIdList;
	}
	
	private static void printFlightInfoByFlightId(Connection conn, List<Integer> flightIds) throws SQLException{
		System.out.println("*****************************************************************************");
		for(int id : flightIds){
			PreparedStatement myStat = conn.prepareStatement(Queries.GET_FLIGHT_BY_ID);
			myStat.setInt(1, id);
			ResultSet resultSet = myStat.executeQuery();
			while(resultSet.next()){
				System.out.println("*"+ resultSet.getString("flightID") + " , "
						+ resultSet.getString("flightCode")+ " , "
						+ resultSet.getString("departureCity")+" , "
						+ resultSet.getString("destination")+" , "
						+ resultSet.getString("departureTime")+" , "
						+ resultSet.getString("arrivalTime")+" , "
						+ resultSet.getString("seatCapacity"));
			}
		}
		System.out.println("*****************************************************************************");
	}
}
		
		
		
		

			
			
			
		
		

