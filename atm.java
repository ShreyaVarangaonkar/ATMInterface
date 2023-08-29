package atmproject;
import java.sql.*;
import java.util.Scanner;

public class atm {
public static void main(String[] args) {
	try {
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/new","root","root");
		Statement stmt = con.createStatement();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome");
		System.out.println("Enter your Pin Number : ");
		int pin = sc.nextInt();
		
		ResultSet rs = stmt.executeQuery("Select * from atm_customer where ac_no="+pin);
		String name= null;
		int balance = 0;
		int count = 0;
		while(rs.next()) {
			name = rs.getString(2);
			balance = rs.getInt(3);
			count++;
		}
		
		
		int amount= 0;
		int take_amount= 0;
		
		if(count>0) {
			
			System.out.println("Hello"+ name);
			while(true) {
				System.out.println("Press 1 to Check Balance");
				System.out.println("Press 2 to Add Amount");
				System.out.println("Press 3 to Withdrawl amount");
				System.out.println("Press 4 to Print Receipt");
				System.out.println("Press 5 to Exit");
				System.out.println();
				System.out.println("Enter your choice");
				int choice = sc.nextInt();
				switch(choice) {
				case 1:
					System.out.println("Your balance is: "+ balance);
					break;
				
				case 2:
					System.out.println("Enter the amount to be added: ");
					amount = sc.nextInt();
					balance = balance + amount;
					int bal=stmt.executeUpdate("UPDATE list SET balance = "+balance+"WHERE ac_no= " +pin);
					System.out.println("Successfully added and your current balance is: "+ balance);
				    break;
				    
				case 3:
					System.out.println("Enter the amount to be withdraw: ");
					take_amount = sc.nextInt();
					if(take_amount>balance) {
						System.out.println("Sorry your request cannot be processed due to Insuffient Balance");
					}
					else {
						balance = balance - take_amount;
						int sub=stmt.executeUpdate("update list set balance = "+balance+"Where ac_no=" +pin);
						System.out.println("Successfully added and your current balance is: "+ balance);
					    
					}
					break;
					
				case 4:
					System.out.println("Thanks for visiting us");
					System.out.println("Your current balance is: "+ balance);
					System.out.println("Amount Deposited: "+ amount);
					System.out.println("Amount Withdrwal: "+ take_amount);
					break;
				}
				
				if(choice==5) {
					break;
				}
			}
			
		}
		else {
			System.out.println("Enter correct pin number");
		}
	}
	catch(Exception e) {
		System.out.println(e);
	}
}
}
