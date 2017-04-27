package GroupProject;

public class Queries {
	public static final String GET_NAME_BY_SSN = "SELECT lname, fname From Admin WHERE ssn = ?";
	
	public static final String GET_MEMBERINFO ="SELECT * FROM Member";
	public static final String GET_BOOKINGINFO ="SELECT * FROM booking";
	
	
    public static final String GET_BOOKINGINFO_BY_MEMBERID="SELECT * FROM Booking WHERE memberID=?";
	
	
	
	
	
	public static final String GET_PASSWORD_BY_SECURITYQUESTION="SELECT password FROM Member WHERE securityQuestion=?";
	public static final String GET_SECURITYQUESTION_BY_USERNAME="SELECT securityQuestion FROM Member WHERE username = ?";
	
	
	
	
	public static final String GET_FLIGHTCODE_AND_ORDERID_BY_MEMBERID="SELECT orderID, flightCode FROM Booking JOIN Flight ON Booking.flightID=Flight.flightID WHERE memberID=?";
	public static final String DELETE_MEMBER_FROM_BOOKING_BY_MEMBERID_AND_BOOKINGID = "DELETE FROM Booking WHERE MemberID=? AND bookingID=?";

	
	/*
	 * Flight table
	 */
	public static final String GET_ALL_FLIGHTS = "SELECT * FROM Flight";
	public static final String GET_FLIGHT_BY_ID = "SELECT * FROM Flight Where flightID = ?";
	public static final String GET_FLIGHT_BY_DEPARTURE_DESTINATION = "SELECT * FROM Flight WHERE departureCity = ? AND destination = ?";
	public static final String ADD_FLIGHT = "INTERT INTO Flight(flightCode, destination, seatCapacity,  departureCity, departureTime, arrivalTime)"+
																"VALUES(?,      ?,           ?,             ?,            ?,              ?)";
	public static final String DELETE_FLIGHT ="DELETE FROM Flight WHERE flightID = ?";
	
	
	/*
	 * Member Table
	 */
	public static final String GET_USER_BY_USERNAME_AND_PASSWORD = "select * from Member where username = ? and password = ?";
	public static final String GET_MEMBER_BY_USERNAME = "select * from Member where username = ?";
	public static final String GET_MEMBERID_BY_USERNAME_AND_PASSWORD="SELECT MemberID FROM Member WHERE username = ? AND password = ?";
	public static final String GET_MEMBERINFO_BY_MEMBERID="SELECT * FROM Member WHERE memberID = ?";
	public static final String REGISTER_USER_INFO = "INSERT INTO Member (fname, lname, address, zip, state, username, password,email,ssn,securityQuestion, securityAnswer)"
	                                                            +"VALUES(  ?,     ?,     ?,      ?,    ?,      ?,         ?,     ?,   ?,       ?,              ?)";

	
	/*
	 * Booking Table
	 */
	public static final String INSERT_A_BOOKING = "INSERT INTO Booking(memberID, flightID) VALUES (? ,?)";
	public static final String GET_BOOKINGID_BY_MemberID_AND_FLIGHTID = "SELECT bookingID FROM Booking WHERE flightID=? AND MemberID=?";
	public static final String GET_FLIGHT_BY_MemberID = "SELECT * FROM Booking WHERE MemberID=?";
	
}


