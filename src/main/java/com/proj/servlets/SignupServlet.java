package com.proj.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.proj.beans.User;
import com.proj.dao.UserDao;

/**
 * Servlet implementation class first
 */
@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		java.io.PrintWriter out = response.getWriter();
//		response.setContentType("text/html");
//		PrintWriter pw=response.getWriter();
//		RequestDispatcher rd;
//		Connection c = null;
//		try {
//			Class.forName("org.postgresql.Driver");//Load the driver to JVM
//			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ProjDB", "postgres", "ramji");
//			String query = ("INSERT INTO accountprofile (name, username, password, age, address, phone,  email) VALUES (?,?,?,?,?,?,?);");
//			PreparedStatement stmt=c.prepareStatement(query); 
//			stmt.setString(1, request.getParameter("name"));
//			stmt.setString(2, request.getParameter("username"));
//			stmt.setString(3, request.getParameter("password"));
//			stmt.setInt(4, Integer.parseInt(request.getParameter("age")));
//			stmt.setString(5, request.getParameter("address"));
//			stmt.setString(6, request.getParameter("phone"));
//			stmt.setString(7, request.getParameter("email"));
//         
//            int x=stmt.executeUpdate();    
//            
//            if(x==1)    
//            {    
//            pw.println("Values Inserted Successfully");    
//            }
//            else {
//             response.sendRedirect("/Signup.java");
//            }
//               
//
//		} catch (Exception e) {
//			System.err.println(e);
//		}		
//		
//		response.sendRedirect("/BookingTicket/Login.html");
		String name = request.getParameter("name");
	    String username = request.getParameter("username");
	    String email = request.getParameter("email");
	    String password = request.getParameter("password");
	    String address = request.getParameter("address");
	    int age = Integer.parseInt(request.getParameter("age"));
	    String phone = request.getParameter("phone");

	    User user = new User(name, username, email, password, address, age, phone);

	    UserDao dao = new UserDao();
	    dao.adduser(user);
	    response.sendRedirect("/TicketBooking/Login.html");
		
	}
	}

