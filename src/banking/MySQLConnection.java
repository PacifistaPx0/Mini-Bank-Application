package banking;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Global connection Class
 */
public class MySQLConnection {
	// Global Connection Object
	static Connection con;

	public static Connection getConnection() {
		try {

			// jdbc driver
			String mysqlJDBCDriver = "com.mysql.cj.jdbc.Driver"; 
			// mysql url
			String url = "jdbc:mysql://localhost:3306/bank"; 
			// mysql username
			String user = "root"; 
			// mysql passcode
			String pass = "Password"; 
			Class.forName(mysqlJDBCDriver);
			con = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			System.out.println("Connection Failed!");
		}

		return con;
	}
}
