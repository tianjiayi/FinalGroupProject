package GroupProject;

//Member class
public class Member {
    private int MemberID;
    private String username;
	 private String password;
	 
private String FirstName;
private String LastName;
private String DateOfBirth;
private String Address;
private String zipCode;//throws digital and total number of digits
private String State;
private String SSN;//throws digital and total number of digits
private String PasswordRecoveryAnswer;
private String dropOrAddUsers;

java.util.ArrayList flightBooking= new java.util.ArrayList();

//need method to check the 

//c1 default,for admin
public Member(){
}

//c for login in


//c first time user
public Member (String username, String password,String FirstName,String LastName, String DateOfBirth,
		String Address,String zipCode,String State,String SSN,String PasswordRecoveryAnswer){
	this.username=username;
	this.username=password;
			this.FirstName = FirstName;
			this.LastName = LastName;
			this.DateOfBirth = DateOfBirth;
			this.Address = Address;
			this.zipCode = zipCode;
			this.State=State;
			this.SSN=SSN;
			this.PasswordRecoveryAnswer=PasswordRecoveryAnswer;
}


public Member(String firstName, String lastName) {
	super();
	FirstName = firstName;
	LastName = lastName;
}

public java.util.ArrayList getFlightBooking(){
	return flightBooking;
}
public void booking(String addOrDrop,String flightID){
	flightBooking.add(new FlightBooking("add",flightID,"departureCity","arrivingCity"));
}


public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getFirstName() {
	return FirstName;
}

public void setFirstName(String firstName) {
	FirstName = firstName;
}

public String getLastName() {
	return LastName;
}

public void setLastName(String lastName) {
	LastName = lastName;
}

public String getDateOfBirth() {
	return DateOfBirth;
}

public void setDateOfBirth(String dateOfBirth) {
	DateOfBirth = dateOfBirth;
}

public String getAddress() {
	return Address;
}

public void setAddress(String address) {
	Address = address;
}

public String getZipCode() {
	return zipCode;
}

public void setZipCode(String zipCode) {
	this.zipCode = zipCode;
}

public String getState() {
	return State;
}

public void setState(String state) {
	State = state;
}

public String getSSN() {
	return SSN;
}

public void setSSN(String SSN) {
	this.SSN = SSN;
}

public String getPasswordRecoveryAnswer() {
	return PasswordRecoveryAnswer;
}

public void setPasswordRecoveryAnswer(String passwordRecoveryAnswer) {
	PasswordRecoveryAnswer = passwordRecoveryAnswer;
}

//public String pswdRecovery(String passwordRecoveryAnswer){
//	boolean check;
//	do{	
//	System.out.print("Please enter your password recovery answer");
//	if (passwordRecoveryAnswer==this.getPasswordRecoveryAnswer())
//		System.out.print("Dear Customer "+this.getUsername()+", your password is "+this.getPassword());
//	else
//		check=false;
//	}while(check==false);
//	//note count number can added to limit the trial times.
//}

@Override
public String toString() {
	return "Member [MemberID=" + MemberID + ", username=" + username + ", password=" + password + ", FirstName="
			+ FirstName + ", LastName=" + LastName + ", DateOfBirth=" + DateOfBirth + ", Address=" + Address
			+ ", zipCode=" + zipCode + ", State=" + State + ", SSN=" + SSN + ", PasswordRecoveryAnswer="
			+ PasswordRecoveryAnswer + ", dropOrAddUsers=" + dropOrAddUsers + ", flightBooking=" + flightBooking + "]";
}
//recovery user password


}
