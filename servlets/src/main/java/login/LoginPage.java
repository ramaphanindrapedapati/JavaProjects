package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class LoginPage extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	 try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet","root","root");
		
		String login_email = req.getParameter("login_Email");
		String login_password = req.getParameter("login_Password");
		
		
		
		 String query = "select * from users where email = ? and password = ?";
		 PreparedStatement stmt = con.prepareStatement(query);
		 
		 stmt.setString(1, login_email);
		 stmt.setString(2, login_password);
		 
		 ResultSet exe = stmt.executeQuery();
		 
		 if(exe.next() == true)
		 {
//			 resp.sendRedirect("bankPage.html");
			 
			 resp.sendRedirect("welcome.html");
			 
			 System.out.println(
					 exe.getInt(1)+"|"+
					 exe.getString(2)+"|"+
					 exe.getString(3)+"|"+
					 exe.getString(4));
			 
		 }
		 else {
			 resp.sendRedirect("loginPage.html?error=invalid");
		
		 }
		 
		 
		
	 } catch (Exception e) {
		
		e.printStackTrace();
	 }
		
	}

}
