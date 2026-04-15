package Bank;

import java.sql.*;
import java.util.*;

public class BankApp {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java","root","root");

        Scanner sc = new Scanner(System.in);
        
        OwnerLogin ol = new OwnerLogin(con,sc);
        
        BankServices bs = new BankServices(con,sc,ol);
        
        

        int choice;

        do {
            System.out.println("\n===== BANK MENU =====");
            System.out.println("Enter 1 to Create Account");
            System.out.println("Enter 2 to Login");
            System.out.println("Enter 3 to Exit");

            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    bs.userRegister();
                    break;

                case 2:
                    bs.userLogin();
                    break;
                   	
                case 3:
                    System.out.println("Thank you for using the Bank App!");
                    break;

                default:
                    System.out.println("Invalid Input");
            }

        } while (choice != 3); 
    }
}