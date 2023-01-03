package com.proj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.proj.beans.User;

public class UserDao {
	public void adduser (User user) {
		Connection conn = null;
	    PreparedStatement stmt = null;
	    try {
	      conn = DBConnection.getConnectiontoDatabase();
	      stmt = conn.prepareStatement(
	          "INSERT INTO accountprofile (name, username, password, age, address, phone, email) VALUES (?, ?, ?, ?, ?, ?, ?)");
	      stmt.setString(1, user.getName());
	      stmt.setString(2, user.getUsername());
	      stmt.setString(3, user.getPassword());
	      stmt.setInt(4, user.getAge());
	      stmt.setString(5, user.getAddress());
	      stmt.setString(6, user.getPhone());
	      stmt.setString(7, user.getEmail());
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
	public User getUserByUsernameAndPassword(String username, String password) {
		Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    try {
	      conn = DBConnection.getConnectiontoDatabase();
	      stmt = conn.prepareStatement("SELECT * FROM accountprofile WHERE username = ? AND password = ?");
	      stmt.setString(1, username);
	      stmt.setString(2, password);
	      rs = stmt.executeQuery();
	      if (rs.next()) {
	        String name = rs.getString("name");
	        String email = rs.getString("email");
	        String address = rs.getString("address");
	        int age = rs.getInt("age");
	        String phone = rs.getString("phone");
	        return new User(name, username, email, password, address, age, phone);
	      }
	    } catch (SQLException e) {
	      e.printStackTrace();
	    } finally {
	      try {
	        if (rs != null) {
	          rs.close();
	        }
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
	    return null;
	  }
	public void UpdateUser(User upUser){
		Connection conn = null;
	    PreparedStatement stmt = null;
	    try {
	      conn = DBConnection.getConnectiontoDatabase();
	      stmt = conn.prepareStatement(
	          "UPDATE accountprofile SET name = ?, email = ?, address = ?, age = ?, phone= ? WHERE username = ?");
	      stmt.setString(1, upUser.getName());
	      stmt.setString(2, upUser.getEmail());
	      stmt.setString(3, upUser.getAddress());
	      stmt.setInt(4, upUser.getAge());
	      stmt.setString(5, upUser.getPhone());
	      stmt.setString(6, upUser.getUsername());
	     
	      
	      
	      
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

