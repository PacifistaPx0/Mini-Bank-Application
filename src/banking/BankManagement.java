package banking;

import java.util.Random;

//import javax.print.PrintException;

//import com.mysql.cj.Query;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
//import java.sql.Statement;

/**
 * these class provides all bank method
 */
public class BankManagement {

	private static final int NULL = 0;

	static Connection con = MySQLConnection.getConnection();
	static String sql = "";
	
	private static DataRetriever dataRetriever = new DataRetriever();

	/**
	 * returns a boolean value accepts user name and passcode
	 * 
	 * @param name
	 * @param passCode
	 * @return
	 */
	public static boolean createAccount(String name, int passCode) {
		//For generating user account number
		String card = "";
		Random rand = new Random();
		for (int i = 0; i < 8; i++) {
			int n = rand.nextInt(8) + 0;
			card += Integer.toString(n);
		}

		try {
			if (name == "" || passCode == NULL) {
				System.out.println("All Field Required!");
				return false;
			}


			// query using prepared statement to prevent injection
			sql = "insert into customer(ac_no, ac_name, balance, passcode) values(?,?,?,?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, Integer.parseInt(card));
			st.setString(2, name);
			st.setDouble(3, 1000.00);
			st.setInt(4, passCode);
			st.executeUpdate();
			System.out.println("Now you login!");
			return true;
			// return
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("Username Not Available!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * accept user name and passcode boolean is false by default
	 * 
	 * @param name
	 * @param passCode
	 * @return
	 */
	public static boolean login(String name, int passCode) {
		Scanner sc = new Scanner(System.in); //scanner for user input
		try {
			if (name == "" || passCode == NULL) {
				System.out.println("All Field Required!");
				return false;
			}
			
			sql = "select * from customer where ac_name = ? and passCode = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1,name);
			stmt.setInt(2, passCode);
			ResultSet rs = stmt.executeQuery(); //executeQuery for resultset
        	if (rs.next()) { 
				System.out.println("-----------------------------------------------------------");
				System.out.println("Hello "+ rs.getString(3)+"!");
				while (true){
					try{
						System.out.println("Select an option");
						System.out.println("-----------------------------------------------------------");
						
						System.out.println("1) Account Details");
						System.out.println("2) Withdraw");
						System.out.println("3) Deposit");
						System.out.println("4) Transfer");
						System.out.println("5) Logout");

						System.out.println("-----------------------------------------------------------");

						int user_input = sc.nextInt();//request user input
						if (user_input == 1){
							dataRetriever.getCustomers(name);
						}

						else if(user_input==2){
							dataRetriever.withdrawal(name);
						}

						else if(user_input==3){
							dataRetriever.deposit(name);
						}

						else if(user_input==4){
							dataRetriever.transfer(name);
						}

						else if (user_input == 5){
							System.out.println("You have successfully logged out");
							break; // breaks out of the if loop
						}
					}
					catch(Exception e){
						e.printStackTrace();
					}
				}
				// returns true which means successful 
				return true; 
			} 
			else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}