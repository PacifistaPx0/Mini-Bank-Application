package banking;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * class for retrieving user data
 */
public class DataRetriever {

	private ResultSet rs;
	static Connection con = MySQLConnection.getConnection();

	DataRetriever(String name) {

		try {
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery("select * from customer where ac_name = '" + name + "'");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * accessor method to return ResultSet rs
	 * 
	 * @return
	 */
	public ResultSet getResultSet() {
		return rs;
	}
}
