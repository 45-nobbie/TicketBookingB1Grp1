package com.proj.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.proj.beans.Book;
import com.proj.beans.User;
import com.proj.dao.DBConnection;

/**
 * Servlet implementation class servtodel
 */
@WebServlet("/BookingServlet")
public class BookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingServlet() {
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
//		doGet(request, response);
	    String vehicleId = request.getParameter("vehicle");
	    String source = request.getParameter("source");
	    String destination = request.getParameter("destination");
//	    response.setContentType("text/html");
//		PrintWriter pw=response.getWriter();
//		Connection c = null;
//		Statement st=null;
//		ResultSet rs = null;
//		HttpSession session = request.getSession();
//		User user = (User) session.getAttribute("user");
//		if (user == null){
//			  response.sendRedirect("Login.html");
//			  return;
//		  };
		    
		    // retrieve additional values from the database
		    String vehicleType = retrieveVehicleType(vehicleId);
		    String timeTaken = retrieveTimeTaken(vehicleId);
		    String price = retrievePrice(vehicleId);
		    
		    
		    
		    Book book = new Book();
		    book.setVehicleId(vehicleId);
		    book.setVehicleType(vehicleType);
		    book.setSource(source);
		    book.setDestination(destination);
		    book.setTimeTaken(timeTaken);
		    book.setPrice(price);

		    
		    // store the values in session attributes
		    HttpSession session = request.getSession();
		    session.setAttribute("book", book);

		    
		    // redirect the user to the next JSP page
		    response.sendRedirect("passengerinfo.jsp");
		  }
		  
		  private String retrieveVehicleType(String vehicleId) {
		    // query the database to retrieve the vehicle type based on the vehicle ID
		    // return the vehicle type
			  Connection conn = DBConnection.getConnectiontoDatabase();
			  
			  try {
			    // create a prepared statement
			    String sql = "SELECT vehicletype FROM vehicles WHERE vehicleid = ?";
			    PreparedStatement pstmt = conn.prepareStatement(sql);
			    
			    // set the parameter values
			    pstmt.setString(1, vehicleId);
			    
			    // execute the query
			    ResultSet rs = pstmt.executeQuery();
			    
			    // retrieve the vehicle type from the result set
			    String vehicleType = null;
			    if (rs.next()) {
			      vehicleType = rs.getString("vehicletype");
			    }
			    
			    // close the result set and prepared statement
			    rs.close();
			    pstmt.close();
			    
			    // return the vehicle type
			    return vehicleType;
			  } catch (SQLException e) {
			    // handle the SQLException
			  } finally {
			    // close the database connection
			    try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
			return null;
		  }
		  
		  private String retrieveTimeTaken(String vehicleId) {
			  Connection conn = DBConnection.getConnectiontoDatabase();
			  
			  try {
			    // create a prepared statement
			    String sql = "SELECT timetaken FROM vehicles WHERE vehicleid = ?";
			    PreparedStatement pstmt = conn.prepareStatement(sql);
			    
			    // set the parameter values
			    pstmt.setString(1, vehicleId);
			    
			    // execute the query
			    ResultSet rs = pstmt.executeQuery();
			    
			    // retrieve the vehicle type from the result set
			    String timeTaken = null;
			    if (rs.next()) {
			      timeTaken = rs.getString("timetaken");
			    }
			    
			    // close the result set and prepared statement
			    rs.close();
			    pstmt.close();
			    
			    // return the vehicle type
			    return timeTaken;
			  } catch (SQLException e) {
			    // handle the SQLException
			  } finally {
			    // close the database connection
			    try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
			return null;
		  }
		  
		  private String retrievePrice(String vehicleId) {
		    // query the database to retrieve the price based on the vehicle ID, source, and destination
		    // return the price
			  Connection conn = DBConnection.getConnectiontoDatabase();
			  
			  try {
			    // create a prepared statement
			    String sql = "SELECT price FROM vehicles WHERE vehicleid = ?";
			    PreparedStatement pstmt = conn.prepareStatement(sql);
			    
			    // set the parameter values
			    pstmt.setString(1, vehicleId);
			    
			    // execute the query
			    ResultSet rs = pstmt.executeQuery();
			    
			    // retrieve the vehicle type from the result set
			    String price = null;
			    if (rs.next()) {
			      price = rs.getString("price");
			    }
			 
			    // close the result set and prepared statement
			    rs.close();
			    pstmt.close();
			    
			    // return the vehicle type
			    return price;
			  } catch (SQLException e) {
			    // handle the SQLException
			  } finally {
			    // close the database connection
			    try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
			return  null;
		  }

	}


