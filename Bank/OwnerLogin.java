package Bank;

import java.sql.*;
import java.util.*;

public class OwnerLogin {
	
	Connection con;
	Scanner sc; 
	
	public OwnerLogin(Connection con, Scanner sc) {
		
		this.con = con;
		this.sc = sc;
	}
	
	public void ownerAccess() throws SQLException
	{
		int choice ;
		do {
			System.out.println("======Welcome OWNER======");
			System.out.println("Enter 1 to view all the Account holders");
			System.out.println("Enter 2 to Update Password");
			System.out.println("Enter 3 to Update name");
			System.out.println("Enter 4 to Delete an Account Holder Record");
			
			choice = sc.nextInt();
			
			switch(choice)
			{
			case 1 :
				viewAllData();
				break;
				
			case 2 :
				updatePassword();
				break;
				
			case 3:
				updateName();
				break;
				
			case 4:
				updateName();
				break;
				
			case 5 :
				deleteAccount();
				break;
				
			}
		} while(choice != 5);
		
	}
	
	public void viewAllData() throws SQLException
	{
		String query="select * from bank;";
		PreparedStatement stmt = con.prepareStatement(query);
		ResultSet res = stmt.executeQuery();
		
//		ResultSetMetaData meta = res.getMetaData();
//	    int columnCount = meta.getColumnCount();
//
//	    //  Print column names
//	    for (int i = 1; i <= columnCount; i++) {
//	        System.out.print(meta.getColumnName(i) + "\t");
//	    }
//	    System.out.println();
		
		while(res.next())
		{
			System.out.println(
					res.getInt("id")+"|"+ 
					res.getString("name")+"|"+ 
					res.getString("amount")+"|"+ 
					res.getDouble("password")+"|"
					);
		}
	}
	
	public void updatePassword() throws SQLException
	{
		System.out.println("Enter ID number to Update password :");
		int id = sc.nextInt();
		sc.nextLine();
		
		System.out.println("Enter the new password : ");
		int newPassword = sc.nextInt();
		
		String query = "update bank set password = ? where id = ?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setInt(1, newPassword);
		stmt.setInt(2, id);
		
		int rows = stmt.executeUpdate();

		if (rows > 0) {
		    System.out.println("Password Updated Successfully");
		} else {
		    System.out.println("ID not found");
		}
			
	}
	
	public void updateName() throws SQLException
	{
		System.out.println("Enter ID number to Update Name :");
		int id = sc.nextInt();
		sc.nextLine();
		
		System.out.println("Enter the new Name : ");
		String name = sc.nextLine();
		
		String query = "update bank set name = ? where id = ?;";
		PreparedStatement stmt = con.prepareStatement(query);
		
		stmt.setString(1, name);
		stmt.setInt(2, id);
	
	    int rows = stmt.executeUpdate();
		
		if (rows > 0) {
	        System.out.println("Name Updated Successfully");
	    } else {
	        System.out.println("ID not found");
	    }
	}
	
	public void deleteAccount() throws SQLException 
	{
		System.out.println("Enter id number to Delete Account Record : ");
		int id = sc.nextInt();
		
		String query = "delete from bank where id = ?;";
		PreparedStatement stmt = con.prepareStatement(query);
		
		stmt.setInt(1, id);
		
		int rows = stmt.executeUpdate();

		if (rows > 0) {
		    System.out.println("Deleted Successfully");
		} else {
		    System.out.println("ID not found");
		}
		
	}
}
