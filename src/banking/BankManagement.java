package banking;

import java.util.Random;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

public class BankManagement { // these class provides all
							// bank method

	private static final int NULL = 0;
	private static String card = "";

	static Connection con = connection.getConnection();
	static String sql = "";
	public static boolean
	createAccount(String name,
				int passCode) // accepts user name and passcode
	{
		try {
			if (name == "" || passCode == NULL) {
				System.out.println("All Field Required!");
				return false;
			}

			//generate account number for user 
			Random rand = new Random();
        	for (int i = 0; i < 10; i++)
        	{
            	int n = rand.nextInt(10) + 0;
            	card += Integer.toString(n);
        	}

			// query
			Statement st = con.createStatement();
			sql = "insert into customer(ac_no, ac_name, balance, passcode) values('"+ card +"', '"+ name +"', 1000.00, "+ passCode +")";

			// Execution
			if (st.executeUpdate(sql) == 1) {
				System.out.println(name
								+ ", Now You Login!");
				return true;
			}
			// return
		}
		catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("Username Not Available!");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}