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
import javax.servlet.http.HttpSession;

import com.proj.beans.User;
import com.proj.dao.UserDao;

/**
 * Servlet implementation class Login
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
//		response.setContentType("text/html");
//		PrintWriter pw=response.getWriter();
//		RequestDispatcher rd;
//		String userdbName = null;
//		String userdbPsw = null;
//		Connection con = null;
//		try {
//		String uname=request.getParameter("username");
//		String upass=request.getParameter("password");
//		Class.forName("org.postgresql.Driver");//Load the driver to JVM
//		con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ProjDB", "postgres", "ramji");
//
//		String sql = "select * from accountprofile where username=? and password=?";
//		PreparedStatement ps = con.prepareStatement(sql);
//
//		ps.setString(1,uname);
//		ps.setString(2,upass);
//
//		ResultSet rs = ps.executeQuery();
//
//		if(rs.next())
//		{ 
//		userdbName = rs.getString("username");
//		userdbPsw = rs.getString("password");
//		}
//
//		if(uname.equals(userdbName) && upass.equals(userdbPsw))
//		{
//		 pw.print("WELCOME "+(userdbName));
//
//		}
//		else {
//			pw.print("Error");
//			
//		}
//
//		} catch (Exception e) {
//			System.err.println(e);
//		}
		String username = request.getParameter("username");
		  String password = request.getParameter("password");

		  UserDao dao = new UserDao();
		  User user = dao.getUserByUsernameAndPassword(username, password);
		  if (user != null) {
		    HttpSession session = request.getSession();
		    session.setAttribute("user", user);
		    response.sendRedirect("Home.html");
		  } else {
		    response.sendRedirect("Login.html");
		  }
		
		
	}
}


