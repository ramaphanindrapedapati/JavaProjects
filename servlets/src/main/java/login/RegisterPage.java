package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class RegisterPage extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String name = req.getParameter("fullname");
		String Email = req.getParameter("Email");
		String password = req.getParameter("password");
		String course = req.getParameter("course");
		
		System.out.println(name +" "+Email+" "+password+" "+course);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet","root","root");
			
			String query1="select * from users Where email = ? ";
			PreparedStatement stmt1 = con.prepareStatement(query1);
			
			stmt1.setString(1, Email);
			
			ResultSet check = stmt1.executeQuery();
			if(check.next())
			{
				resp.sendRedirect("registerPage.html?error=email");
			}
			else
			{
				String query2="insert into users (name,email,password,course) values(?,?,?,?);";
				
				PreparedStatement stmt2 = con.prepareStatement(query2);
				
				stmt2.setString(1, name);
				stmt2.setString(2, Email);
				stmt2.setString(3, password);
				stmt2.setString(4, course);
				int count = stmt2.executeUpdate();
				
				stmt2.close();
				con.close();
				
				if(count > 0)
				{
					resp.sendRedirect("loginPage.html");
				}
				else
				{
					System.out.println("error occured");
				}
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}
}

