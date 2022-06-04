package banking;

import java.util.Random;

import com.mysql.cj.Query;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

/**
 * these class provides all bank method
 */
public class BankManagement {

	private static final int NULL = 0;

	// variable to generate user account number
	private static String card = "";

	static Connection con = MySQLConnection.getConnection();
	static String sql = "";

	/**
	 * returns a boolean value accepts user name and passcode
	 * 
	 * @param name
	 * @param passCode
	 * @return
	 */
	public static boolean createAccount(String name, int passCode) {
		try {
			if (name == "" || passCode == NULL) {
				System.out.println("All Field Required!");
				return false;
			}

			// generate account number for user
			Random rand = new Random();
			for (int i = 0; i < 10; i++) {
				int n = rand.nextInt(10) + 0;
				card += Integer.toString(n);
			}

			// query
			Statement st = con.createStatement();
			sql = "insert into customer(ac_no, ac_name, balance, passcode) values('" + card + "', '" + name
					+ "', 1000.00, " + passCode + ")";

			// Execution
			if (st.executeUpdate(sql) == 1) {
				System.out.println(name + ", Now You Login!");
				return true;
			}
			// return
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("Username Not Available!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * accept user name and passcode
	 * 
	 * @param name
	 * @param passCode
	 * @return
	 */
	public static boolean login(String name, int passCode) {

		try {
			if (name == "" || passCode == NULL) {
				System.out.println("All Field Required!");
				return false;
			}

			// query
			/*
			 * sql = "select * from customer where ac_name='" + name + "' and passcode=" +
			 * passCode; PreparedStatement st = con.prepareStatement(sql); ResultSet rj =
			 * st.executeQuery(); System.out.println(rj);
			 */

			// Testing Data retriever instance
			DataRetriever dr = new DataRetriever(name);
			ResultSet rs = dr.getResultSet();
			// printing specific column of user data where name
			while (rs.next()) {
				System.out.println("Account number: " + rs.getString(2));
				System.out.println("Account name: " + rs.getString(3));
				System.out.println("Balance: " + rs.getString(4));
			}

			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}