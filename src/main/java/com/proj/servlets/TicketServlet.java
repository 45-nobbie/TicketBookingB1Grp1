package com.proj.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.proj.beans.Book;
import com.proj.beans.Ticket;
import com.proj.beans.User;
import com.proj.dao.DBConnection;
import com.proj.dao.TicketDao;

/**
 * Servlet implementation class TicketServlet
 */
@WebServlet("/TicketServlet")
public class TicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TicketServlet() {
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
		
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			
//			HttpSession session1 = request.getSession();
			Book book = (Book) session.getAttribute("book");
			 if (user == null){
				  response.sendRedirect("Login.html");
				  return;
			  }
			
			Connection conn = null;
		    PreparedStatement stmt = null;
		    ResultSet rs = null;
		    try {
		      conn = DBConnection.getConnectiontoDatabase();
		      stmt = conn.prepareStatement(
		          "SELECT timetaken,price FROM vehicle WHERE vehicleid = ?");
		      stmt.setString(1, user.getUsername());
		      stmt.executeUpdate();
//		      while(rs.next()) {
//		    	 String timetaken = rs.getString("timetaken");
//		    	  String price = rs.getString("price");
//		      }
		    } catch (SQLException e) {
		      e.printStackTrace();
		    } finally {
		      try {
		        if (stmt != null) {
		          stmt.close();
		        }
		        if (conn != null) {
		          conn.close();
		        }
		        if (rs != null) {
		        	rs.close();
		        }
		      } catch (SQLException e) {
		        e.printStackTrace();
		      }
		    }
		 
		
		   String[] passengerNames = request.getParameterValues("passengerName[]");
		   int[] passengerAges = new int[passengerNames.length];
		   String[] passengerAddresses = request.getParameterValues("passengerAddress[]");
		   String timetaken = request.getParameter("timeTaken");
		   String price = request.getParameter("price");
		    for (int i = 0; i < passengerNames.length; i++) {
		        passengerAges[i] = Integer.parseInt(request.getParameterValues("passengerAge[]")[i]);
		      }
		    TicketDao dao = new TicketDao();
		    for (int i = 0; i < passengerNames.length; i++) {
		     
			Ticket ticket = new Ticket(UUID.randomUUID().toString(), "Booked", book.getSource(), 
		    		  book.getDestination(), timetaken, price, null, passengerNames[i], 
		    				  user.getUsername(), passengerAddresses[i], passengerAges[i],
		    				  false, null);
//		      	TicketDao dao = new TicketDao();
//		      ticket.setTicketid(UUID.randomUUID().toString());
//		      ticket.setTicketstatus("Booked");
//		      ticket.setSource(book.getSource());
//		      ticket.setDestination(book.getDestination());
//		      ticket.setHoursofjourney(book.getTimeTaken());
//		      ticket.setRoute(null);
//		      ticket.setPrice(book.getPrice());
//		      ticket.setPassengername(passengerNames[i]);
//		      ticket.setPassengerid(user.getUsername());
//		      ticket.setPassengeraddress(passengerAddresses[i]);
//		      ticket.setPassengerage(passengerAges[i]);
//		      ticket.isCoupanapplied();
//		      ticket.setFeedback(null);
		      dao.addticket(ticket);
		    }
		    response.sendRedirect("confirmation.jsp");
	}

}
