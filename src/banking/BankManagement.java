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

public class BankManagement { // these class provides all
							// bank method

	private static final int NULL = 0;
	private static String card = ""; // variable to generate user account number 

	static Connection con = connection.getConnection();
	static String sql = "";
	public static boolean // returns a boolean value 
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



	public static boolean
	login(String name, int passCode) // accept user name and passcode
	{ 	

		try{
			if (name == "" || passCode == NULL) {
				System.out.println("All Field Required!");
				return false;
			}

			
			// query
            /*sql = "select * from customer where ac_name='"
                  + name + "' and passcode=" + passCode;
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rj = st.executeQuery();
			System.out.println(rj);*/

			DataRetriever dr = new DataRetriever(name); //Testing Data retriever instance
			ResultSet rs = dr.getResultSet();
        	while (rs.next()) { //printing specific column of user data where name
				System.out.println("Account number: "+ rs.getString(2));
                System.out.println("Account name: " + rs.getString(3));
                System.out.println("Balance: " + rs.getString(4));
        	}

			return true;

		}
		catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
}
class DataRetriever { //class for retrieving user data

	private ResultSet rs;
	static Connection con = connection.getConnection();
    DataRetriever(String name) {
		
        try {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery("select * from customer where ac_name = '"+name+"'");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
	}
	public ResultSet getResultSet() {
		return rs;	//accessor method to return ResultSet rs 
	}
}
