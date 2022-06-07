package banking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

/**
 * class for retrieving user data
 */
public class DataRetriever {

	private ResultSet rs;
	static Connection con = MySQLConnection.getConnection();

	public void getCustomers(String name) {
		try {
			String sql = "select * from customer where ac_name = '" + name + "'";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				System.out.println("Account number: " + rs.getString(2));
				System.out.println("Account name: " + rs.getString(3));
				System.out.println("Balance: " + rs.getString(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deposit(String name) {
		Scanner sc = new Scanner(System.in);
		double balance;
		double newValue;
		try {
			String sql = "select balance from customer where ac_name = '" + name + "'";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (true) {
				System.out.println("Please Enter an amount you wish to deposit");
				int user_input = sc.nextInt();
				if (user_input > 10000000) {
					System.out.println("Maximum amount possble for deposition exceeded. The limit is 10,000,000");
				} else {
					// getting user balance and updating it with deposited amount
					double i = user_input;
					while (rs.next()) {
						newValue = rs.getInt(1);
						balance = newValue + i;
						sql = "update customer set balance = '" + balance + "' where ac_name = '" + name + "'";
						PreparedStatement st = con.prepareStatement(sql);

						if (st.executeUpdate() == 1) {
							System.out.println("Your account has been deposited. Your new balance is " + balance);
						}
					}

				}
				break;
			}

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
