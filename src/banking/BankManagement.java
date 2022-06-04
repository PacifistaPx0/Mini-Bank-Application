package banking;

import java.util.Random;

import javax.print.PrintException;

import com.mysql.cj.Query;

import java.util.Scanner;
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
			Scanner sc = new Scanner(System.in); //scanner for user input
			
			sql = "select * from customer where ac_name = '"+name+"'";
			PreparedStatement stmt = con.prepareStatement(sql); // prepared statement much better than statement
			ResultSet rs = stmt.executeQuery(sql);
        	if (rs.next()) { 
				System.out.println("Hello "+ rs.getString(3)+"!");
				while (true){
					try{
						System.out.println("Select an option");
						System.out.println("-----------------------------------------------------------");
						
						System.out.println("1) Account Details");
						System.out.println("2) Transfer");
						System.out.println("3) Logout");

						System.out.println("-----------------------------------------------------------");

						int user_input = sc.nextInt();//request user input
						if (user_input == 1){
							BankManagement.DataRetriever(name);
						}

						if (user_input == 3){
							System.out.println("You have successfully logged out");
							break;
						}
					}
					catch(Exception e){
						e.printStackTrace();
					}
				}
			} 
			else {
				return false;
			}

		}
		catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	public static void DataRetriever(String name) {
		
        try {
			sql = "select * from customer where ac_name = '"+name+"'";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()){
				System.out.println("Account number: "+ rs.getString(2));
				System.out.println("Account name: " + rs.getString(3));
				System.out.println("Balance: " + rs.getString(4));
			}
        }
        catch(Exception e) {
            e.printStackTrace();
        }
	}
}

