package GroupProject;
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
public class Driver {
		
	public static Connection getConnection() throws Exception{
		try{
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://127.0.0.1:3306/airlineReservationDB";
			String username = "root";
			String password = "1";
			Class.forName(driver);
			
			
			System.out.println("Connected");
			Connection conn = (Connection)DriverManager.getConnection (url,username, password);
			System.out.println("Connected");
			return conn;
			
//			//2. create a statement
//			PreparedStatement myStat = conn.prepareStatement(Queries.GET_USER_BY_USERNAME_AND_PASSWORD);
//			//3. execute SQL query
//			myStat.setString(1, "tian");
//			myStat.setString(2, "myPassword");
//			//4. process the result set
//			ResultSet resultSet = myStat.executeQuery(Queries.GET_USER_BY_USERNAME_AND_PASSWORD);
//		
//			while(resultSet.next()){
//				System.out.println(resultSet.getString("userId") + "   ,   "+ resultSet.getString("ssn")+ "    ,   "+ resultSet.getString("datebrith"));
//									
//			}            
																																																								
		}catch(Exception ex){
			System.out.println(ex);
						
		}
		return null;
	}
}