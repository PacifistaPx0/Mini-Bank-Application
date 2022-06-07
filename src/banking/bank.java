package banking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bank {
	public static void main(String args[]) //main class of bank
		throws IOException
	{

		BufferedReader sc = new BufferedReader(
			new InputStreamReader(System.in));
		String name = "";
		int pass_code;
		int ch;

		while (true) {
			System.out.println(
				"\n || Welcome to RBank || \n");
			System.out.println("1)Create Account");
			System.out.println("2)Login Account");

			try {
				System.out.print("\n Enter Input:"); //user input
				ch = Integer.parseInt(sc.readLine());

				switch (ch) {
				case 1:
					try {
						System.out.print(
							"Enter Unique UserName:");
						name = sc.readLine();
						System.out.print(
							"Enter New Passcode:");
						pass_code = Integer.parseInt(
							sc.readLine());

						if (BankManagement.createAccount(
								name, pass_code)) {
							System.out.println(
								"MSG : Account Created Successfully!\n");
						}
						else {
							System.out.println(
								"ERR : Account Creation Failed!\n");
						}
					}
					catch (Exception e) {
						System.out.println(
							" ERR : Enter Valid Data::Insertion Failed!\n");
					}
					break;
				case 2:
					try {
						System.out.print(
							"Enter UserName:");
						name = sc.readLine();
						System.out.print(
							"Enter Passcode:");
						pass_code = Integer.parseInt(
							sc.readLine());

						if (BankManagement.login(
							name, pass_code)) {
							System.out.println(
							" ");
						}
						else {
							System.out.println(
								"ERR : Login failed!\n");
						}
					}
					catch(Exception e){
						System.out.println(
							"ERR: Wrong Username/Passcode"
						);
					}
					break;
				}
			}
			catch (Exception e) {
                System.out.println("Enter Valid Entry!");
            }
        }	
	}
}

