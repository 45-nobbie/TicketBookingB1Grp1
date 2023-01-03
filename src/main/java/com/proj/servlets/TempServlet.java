package com.proj.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TempServlet
 */
@WebServlet("/TempServlet")
public class TempServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TempServlet() {
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
		Connection con = null;
		try {
		Class.forName("org.postgresql.Driver");//Load the driver to JVM
		con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ProjDB", "postgres", "ramji");
		
	// Get the form data
	String vehicleid = request.getParameter("vehicleid");
	String vehicletype = request.getParameter("vehicletype");
	String source = request.getParameter("source");
	String destination = request.getParameter("destination");
	String timetaken = request.getParameter("timetaken");
	double price = Double.parseDouble(request.getParameter("price"));
	String departuretime = request.getParameter("departuretime");

	// Establish a database connection
	Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ProjDB", "postgres", "ramji");

	// Create a SQL insert statement
	String sql = "INSERT INTO vehicle (vehicleid, vehicletype, source, destination, timetaken, price, departuretime) VALUES (?, ?, ?, ?, ?, ?, ?)";
	PreparedStatement stmt = conn.prepareStatement(sql);

	// Set the insert values in the statement
	stmt.setString(1, vehicleid);
	stmt.setString(2, vehicletype);
	stmt.setString(3, source);
	stmt.setString(4, destination);
	stmt.setString(5, timetaken);
	stmt.setDouble(6, price);
	stmt.setString(7, departuretime);

	// Execute the insert statement
	stmt.executeUpdate();

	// Close the connection and statement
	stmt.close();
	conn.close();
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	 response.sendRedirect("temp.jsp");
	}
}
