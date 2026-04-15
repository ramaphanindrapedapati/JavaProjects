package Bank;

import java.sql.*;
import javax.sql.*;

import java.util.*;

public class BankServices {

	Connection con;
	Scanner sc;
	OwnerLogin ol;
	
	public BankServices(Connection con, Scanner sc,OwnerLogin ol) {
		
		this.con = con;
		this.sc = sc;
		this .ol = ol;
	}
	
	String name;
	double bal;
	int password ;
	public  void userRegister() throws SQLException
	{
		System.out.println("Enter the Name : ");
		name =sc.nextLine();
		
		System.out.println("Deposite Initialize  amount : ");
	    bal = sc.nextDouble();
	    sc.nextLine();
		
		System.out.println("Enter The Password");
		int password=sc.nextInt(); 
		sc.nextLine();
		
		String query = "insert into bank(name,amount,password) values(?,?,?)";
		
		PreparedStatement stmt= con.prepareStatement(query);
		
		stmt.setString(1, name);
		stmt.setDouble(2, bal);
		stmt.setInt(3, password);
		
		stmt.execute();
		
		System.out.println("Account Created");
		 
	}
	
	public void userLogin() throws SQLException {

	    int attempts = 0;

	    while (attempts < 3) {

	        System.out.println("Enter Name: ");
	        String name = sc.nextLine();

	        System.out.println("Enter Password: ");
	        int password = sc.nextInt();
	        sc.nextLine();
	        
	        if(name.equalsIgnoreCase("owner") &&  password == 9876)
	        {
	        		ol.ownerAccess();
	        }
	        else
	        {
	        	
	        		String query = "SELECT * FROM bank WHERE name=? AND password=?";
		        PreparedStatement stmt = con.prepareStatement(query);

		        stmt.setString(1, name);
		        stmt.setInt(2, password);

		        ResultSet res = stmt.executeQuery();

		        if (res.next()) {
		            System.out.println("Login Successful!");
		            username(name);
		            return; // exit after success
		        } else {
		            attempts++;
		            System.out.println("Invalid Credentials! Attempt " + attempts + "/3");
		        }
		        
	        }
	        
	    }

	    System.out.println("Too many failed attempts. Try again later.");
	}
	
	public void username(String name) throws SQLException
	{	
		int choice ;
		do {
			System.out.println("=====Welcome To The Bank=====");
			
			String query = "select amount from bank where name = ?";
			
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1,name);
			ResultSet res = stmt.executeQuery();
			
			if(res.next()== true)
			{
				System.out.println("your Current Balance is "+res.getDouble(1));
			}
			else	
			{
				System.out.println(" no record found ");
			}
			
			System.out.println("Enter 1 to Deposite : ");
			System.out.println("Enter 2 to Withdraw : ");
			System.out.println("Enter 3 to Transfer : ");
			System.out.println("Enter 4 to checkBalance : ");
			System.out.println("Enter 5 to Exit");
			
			System.out.println("Enter  Choice");
			choice = sc.nextInt();
			sc.nextLine();

			  switch (choice) 
			  {
              case 1:
            	  	deposit(name);
                  break;

              case 2:
            	  	withdraw(name);
                  break;

              case 3:
            	  	transfer(name);
                  break;

              case 4:
            	    checkBalance(name);
                  break;

              case 5:
                  System.out.println("Logged out!");
                  System.out.println("Thanks for Visiting....");
                  break;

              default:
                  System.out.println("Invalid choice");
			  }
		}while (choice !=5);	
	
	}
	
	
	public void deposit(String name) throws SQLException
	{
		System.out.println("Enter Amount to Deposite");
		double amount1 = sc.nextDouble(); 
		sc.nextLine();
		
		String query = "Select amount from bank where name = ?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1,name);
		ResultSet stmt1 = stmt.executeQuery();
		
		if(stmt1.next() == true)		
		{
		 double newBal = stmt1.getDouble(1)+amount1;
		 
		 String updatequery="Update bank set amount = ? where name = ?";
		 PreparedStatement stmt2 = con.prepareStatement(updatequery);
		 stmt2.setDouble(1, newBal);
		 stmt2.setString(2, name);
		 
		 stmt2.executeUpdate();
		
		 System.out.println("Amount Deposite Successfully ");
		}
		else 
		{
			System.out.println("No Records Found");
		}
	
	}
	
	public void withdraw(String name) throws SQLException
	{
		{
			System.out.println("Enter Amount to Withdraw");
			double amount1 = sc.nextDouble();
			sc.nextLine();
			
			String query = "Select amount from bank where name = ?";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1,name);
			ResultSet stmt1 = stmt.executeQuery();
			
			if(stmt1.next() == true)		
			{
				if(stmt1.getDouble(1) > amount1)
					
				{
					double newBal = stmt1.getDouble(1) - amount1;
					 
					 String updatequery="Update bank set amount = ? where name = ?";
					 PreparedStatement stmt2 = con.prepareStatement(updatequery);
					 stmt2.setDouble(1, newBal);
					 stmt2.setString(2, name);
					 
					 stmt2.executeUpdate();
					
					 System.out.println("Amount Withdraw Successfully ");
				}
				else
				{
					System.out.println("Insufficient Balance"); 
				}
			 
			}
			else 
			{
				System.out.println("No Records Found");
			}
		}
		
		
	}
	

	public void transfer(String name) throws SQLException
	{
		System.out.println("Enter the Person ID to Transfer amount : ");
		int id = sc.nextInt();
		sc.nextLine();
		
		System.out.println("Enter the Amount to To Transfer : ");
		double amount1 = sc.nextDouble();
		sc.nextLine();
		
		String query = "Select amount from bank where name = ?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, name);
		ResultSet stmt1 = stmt.executeQuery();
		
		if(stmt1.next() == true)		
		{
			if(stmt1.getDouble(1) > amount1)
			{
				double newBal = stmt1.getDouble(1) - amount1;
				 
				 String updatequery="Update bank set amount = ? where name = ?";
				 PreparedStatement stmt2 = con.prepareStatement(updatequery);
				 stmt2.setDouble(1, newBal);
				 stmt2.setString(2, name);
				 
				 stmt2.executeUpdate();
				 System.out.println("Amount Transfer Successfully ");
				 person2(id,amount1);
			}
			else
			{
				System.out.println("Insufficient Balance"); 
			}
		}	
		else 
		{
			System.out.println("No Records Found");
		}
		
	}
	
	public void person2(int id,double amount1) throws SQLException
	{
		String query = "Select amount from bank where id = ?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setInt(1, id);
		ResultSet stmt1 = stmt.executeQuery();
		if(stmt1.next() == true)		
		{
			
				double newBal = stmt1.getDouble(1) + amount1;
				 
				 String updatequery="Update bank set amount = ? where id = ?";
				 PreparedStatement stmt2 = con.prepareStatement(updatequery);
				 stmt2.setDouble(1, newBal);
				 stmt2.setInt(2, id);
				
				 stmt2.executeUpdate();
				 
		}	
		else 
		{
			System.out.println("No Records Found");
		}
		
	}
	
	public void checkBalance(String name) throws SQLException
	 {
		String query = "select amount from bank where name = ?"; 
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, name);
		ResultSet res = stmt.executeQuery();
		
		if(res.next() == true)
		{
			System.out.println("Your Available Balance is : "+ res.getDouble(1));
		}
		else
		{
			System.out.println("No Record Found");
		}
		
	 }	
}
