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

				
				
				//start the loop for main menu
				String quit= "i";
				while(quit!="n"){
				System.out.print("How can we help you?\n"+" Main menu \n" + 
				"1:check all flights information \n"+
				"2:booking flight \n"+
				"3:check booked flight informtation \n"+
				"4:add flight (Admin only) \n"+ 
				"5:delete flight (Admin only)\n"+
				"6:check all member information (Admin only)\n"+ 
				"7:log out \n"+"Enter a choice: ");
				
				int selection = input.nextInt();
				switch(selection){
				case 1:
					System.out.println("All flight informtation are here: ");
					printAllFlightInfo(conn);
					
					break;
				case 2:
					System.out.println("Enter the departure ciry: ");
					String departureCity = input.next();
					System.out.println("Enter the destination city: ");
					String arrivalCity = input.next();
					System.out.println("Here is the matching flight information: ");
					
					//call method show matching flight information
					printFlightByDepartureAndArrivalCity(conn, departureCity, arrivalCity);
					
					System.out.println("Enter a flight number to book: ");
					int flightId = input.nextInt();					
					//put into the order table in database
					
					System.out.println("Your flight has booked successfully.");
					
					break;
				case 3:
					System.out.println("Your booked flight information: ");
					getMemberIdByUsernameAndPassword(conn, username, password);
					printFlightInfoByFlightId(conn, FlightIdList);
					
					break;
				
				case 4:
					System.out.print("Please enter the flight information for adding: \n");
					//create a flight
					createFlight(input, conn);
					break;
				case 5:
					
					
					//call deleteFlight method
					deleteFlight(input, conn);
					
					//flight deleted
					break;
					
				case 6:					
					System.out.print("All member's information: \n");
					//call get all member information method
					printAllMemberInfo(conn);
					
					break;
				case 7:
					System.out.println("Logged out.\n");
				    System.exit(0);
				   
					break;
					
				default:
						System.out.println("Invalid input.Enter an option from 1 to 7: ");
					
				}
				System.out.print("Ready to go back to the main menu? press any key except 'n' ");
				quit = input.next().toLowerCase();

			
			}
			
		
				
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
						//show booking info:
						//1 find all flight id in booking table by member id
						List<Integer> FlightIdList = getFlightIdsByMemberId(memberId, conn);
						//2 find all flight in flight table by flight id from step 1
						printFlightInfoByFlightId(conn, FlightIdList);
						//main menu loop goes here
						//start the loop for main menu
						String quit= "i";
						while(quit!="n"){
						System.out.print("How can we help you?\n"+" Main menu \n" + 
						"1:check all flights information \n"+
						"2:booking flight \n"+
						"3:check booked flight informtation \n"+
						"4:add flight (Admin only) \n"+ 
						"5:delete flight (Admin only)\n"+
						"6:check all member information (Admin only)\n"+ 
						"7:log out \n"+"Enter a choice: ");
						
						int selection = input.nextInt();
						switch(selection){
						case 1:
							System.out.println("All flight informtation are here: ");
							printAllFlightInfo(conn);
							
							break;
						case 2:
							System.out.println("Enter the departure ciry: ");
							String departureCity = input.next();
							System.out.println("Enter the destination city: ");
							String arrivalCity = input.next();
							System.out.println("Here is the matching flight information: ");
							
							//call method show matching flight information
							printFlightByDepartureAndArrivalCity(conn, departureCity, arrivalCity);
							
							System.out.println("Enter a flight number to book: ");
							int flightId = input.nextInt();					
							//put into the order table in database
							
							System.out.println("Your flight has booked successfully.");
							
							break;
						case 3:
							System.out.println("Your booked flight information: ");
							getMemberIdByUsernameAndPassword(conn, username, password);
							printFlightInfoByFlightId(conn, FlightIdList);
							
							break;
						
						case 4:
							System.out.print("Please enter the flight information for adding: \n");
							//create a flight
							createFlight(input, conn);
							break;
						case 5:
							
							
							//call deleteFlight method
							deleteFlight(input, conn);
							
							//flight deleted
							break;
							
						case 6:					
							System.out.print("All member's information: \n");
							//call get all member information method
							printAllMemberInfo(conn);
							
							break;
						case 7:
							System.out.println("Logged out.\n");
						    System.exit(0);
						   
							break;
							
						default:
								System.out.println("Invalid input.Enter an option from 1 to 7: ");
							
						}
						System.out.print("Ready to go back to the main menu? press any key except 'n' ");
						quit = input.next().toLowerCase();
	
						
						}		
					}else{
						//wrong security answer
						System.out.println("login failed please register");
						memberId = createUser(input, conn);
					}
				}else{
					
					//if wrong username and wrong password situation goes here
					//create user
					System.out.println("Username and password are not found. Please register first.");
					createUser(input, conn);
					
					
				}
			}
		}else if(membership.equalsIgnoreCase("N")){
			createUser(input, conn);
		}
			
			




	
	
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
	
	
	
	private static void printFlightByDepartureAndArrivalCity(Connection conn, String departureCity, String arrivalCity) throws SQLException{
		System.out.println("*****************************************************************************");
		PreparedStatement myStat = conn.prepareStatement(Queries.GET_FLIGHT_BY_DEPARTURE_DESTINATION);
		myStat.setString(1, departureCity);
		myStat.setString(2, arrivalCity);
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
	
	System.out.println("*****************************************************************************");
		
	
	}
	
	
	
	
	private static void printAllFlightInfo(Connection conn) throws SQLException{
		System.out.println("*****************************************************************************");
		PreparedStatement myStat = conn.prepareStatement(Queries.GET_ALL_FLIGHTS);
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
	
	System.out.println("*****************************************************************************");
		
	
	}
	
	
	
	
	
	
	
	
	private static void printAllMemberInfo(Connection conn) throws SQLException{
		System.out.println("*****************************************************************************");
		PreparedStatement myStat = conn.prepareStatement(Queries.GET_MEMBERINFO);
		ResultSet resultSet = myStat.executeQuery();
		while(resultSet.next()){
			System.out.println("*"+ resultSet.getString("memberID") + " , "
					+ resultSet.getString("lname")+ " , "
					+ resultSet.getString("fname")+" , "
					+ resultSet.getString("address")+" , "
					+ resultSet.getString("zip")+" , "
					+ resultSet.getString("state")+" , "
					+ resultSet.getString("username")+" , "
					+ resultSet.getString("password")+" , "
					+ resultSet.getString("email")+" , "
					+ resultSet.getString("ssn")+" , "
					+ resultSet.getString("securityQuestion")+" , "
					+ resultSet.getString("securityAnswer"));
		}
	
	System.out.println("*****************************************************************************");
		
	
	}
	
	
	

		
	private static void createFlight(Scanner input, Connection conn) throws SQLException{
		System.out.print("Please enter the flight information below: \n");
		System.out.print("Enter the flight code: ");
		String flightCode = input.next();
		System.out.println("Enter the destination: ");
		String destination = input.next();
		System.out.println("Enter the flight's seat capacity: ");
		String seatCapacity = input.next();
		System.out.print("Enter the departure city: ");
		String departureCity = input.next();
		System.out.println("Enter the departure time: ");
		String departureTime = input.next();
		System.out.print("Enter the arrival time: ");
		String arrivalTime = input.next();
		
		
		
		
		//create flight in db
		PreparedStatement createFlightPS = conn.prepareStatement(Queries.ADD_FLIGHT);
		createFlightPS.setString(1, flightCode);
		createFlightPS.setString(2, destination);
		createFlightPS.setString(3, seatCapacity);
		createFlightPS.setString(4, departureCity);
		createFlightPS.setString(5, departureTime);
		createFlightPS.setString(6, arrivalTime);
		
		
		createFlightPS.executeUpdate();
		System.out.println("Record is inserted into Flight table!");
		
		//get new flight 
	
	}
	
	private static void deleteFlight(Scanner input, Connection conn) throws SQLException{
		System.out.print("Please enter the flight id numer for delete: " );
		int deleteFlightId = input.nextInt();
		PreparedStatement deleteFlightPS = conn.prepareStatement(Queries.DELETE_FLIGHT);
		deleteFlightPS.setInt(1, deleteFlightId);
		deleteFlightPS.executeUpdate();
		System.out.println("Flight deleted!");
	
	
	
	}
	
		
		
		
     private static void printMemberProfByMemberId(Connection conn, List<Integer> memberId) throws SQLException{
    	 System.out.println("*****************************************************************************");
    	 for(int id: memberId){
    	 PreparedStatement myStat = conn.prepareStatement(Queries.GET_MEMBERINFO_BY_MEMBERID);
    	 myStat.setInt(1,id);
    	 ResultSet resultSet = myStat.executeQuery();
    	 while(resultSet.next()){
				System.out.println("*"+ resultSet.getString("memberID") + " , "
						+ resultSet.getString("lname")+ " , "
						+ resultSet.getString("fname")+" , "
						+ resultSet.getString("address")+" , "
						+ resultSet.getString("zip")+" , "
						+ resultSet.getString("state")+" , "
						+ resultSet.getString("email")+" , "
						+ resultSet.getString("ssn")+" , "
						+ resultSet.getString("userneme")+" , "
						+ resultSet.getString("email"));
			
		}
		System.out.println("*****************************************************************************");
    	 } 
    	 
    	 
     }
			
			
			
		
}

