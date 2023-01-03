package com.proj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.proj.beans.Ticket;

public class TicketDao {
	public void addticket(Ticket ticket) {
		Connection conn = null;
	    PreparedStatement stmt = null;
	    try {
	      conn = DBConnection.getConnectiontoDatabase();
	      stmt = conn.prepareStatement(
	          "INSERT INTO ticket (ticketid, ticketstatus, source, destination, hoursofjourney, price, route, passengername, passengerid, passengeraddress, passengerage, coupanapplied, feedback) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
	      stmt.setString(1, ticket.getTicketid());
	      stmt.setString(2, ticket.getTicketstatus());
	      stmt.setString(3, ticket.getSource());
	      stmt.setString(4, ticket.getDestination());
	      stmt.setString(5, ticket.getHoursofjourney());
	      stmt.setString(6, ticket.getPrice());
	      stmt.setString(7,ticket.getRoute());
	      stmt.setString(8, ticket.getPassengername());
	      stmt.setString(9, ticket.getPassengerid());
	      stmt.setString(10, ticket.getPassengeraddress());
	      stmt.setInt(11, ticket.getPassengerage());
	      stmt.setBoolean(12, ticket.isCoupanapplied());
	      stmt.setString(13, ticket.getFeedback());
	     
	      stmt.executeUpdate();
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
	      } catch (SQLException e) {
	        e.printStackTrace();
	      }
	    }
	}
	public void CancelTicket(String ticketid) {
		Connection conn = null;
	    PreparedStatement stmt = null;
	    try {
	      conn = DBConnection.getConnectiontoDatabase();
	      stmt = conn.prepareStatement(
	          "UPDATE ticket SET ticketstatus = 'Cancelled' WHERE ticketid = ?");
	      stmt.setString(1, ticketid);
	      stmt.executeUpdate();
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
	      } catch (SQLException e) {
	        e.printStackTrace();
	      }
	    }
	}
}
