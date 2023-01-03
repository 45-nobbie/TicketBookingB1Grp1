<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
  <%@ page import="com.proj.beans.User" %>
  <%@ page import="java.io.*,java.util.*,java.sql.*"%>  
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>  
<!DOCTYPE html>
<html>
<head>
  <title>My Account</title>	
  <style>

    body {
  

  	 background: linear-gradient(to right, #f1f1f1, #cccccc);
	padding :20px;
}
    h1 {
  text-align: center;
  margin: 20px 0;
}
nav {
  background: #cccccc;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

/* Style the "Book" link */
nav a {
  font-size: 18px;
  color: #333;
  text-decoration: none;
  margin: 0 10px;
}

/* Style the "Home" and "Logout" buttons */
nav button {
  background-color: #333;
  color: #fff;
  border: none;
  outline: none;
  cursor: pointer;
  font-size: 16px;
  padding: 8px 16px;
  border-radius: 4px;
}
	.user-details, .booked-tickets {
  display: flex;
}
input {
  appearance: none;
  font-size: 18px;
  font-family: Arial, sans-serif;
  
}
/* Style the user details section */
.user-details {
  font-size: 18px;
  font-family: Arial, sans-serif;
  color: #333333;
  border: none;
  padding: 10px;
  margin: 10px 0;
}

/* Style the booked tickets section */
.booked-tickets {
  flex: 1;
  margin-left: 20px;
}

/* Style the table in the booked tickets section */
.booked-tickets table {
  width: 100%;
  border-collapse: collapse;
}

/* Style the table cells */
.booked-tickets td, .booked-tickets th {
  border: 1px solid #ddd;
  padding: 8px;
}

/* Style the table header cells */
.booked-tickets th {
  background-color: #333;
  color: #fff;
}

/* Style the "Cancel" button in the booked tickets table */
.booked-tickets td button {
  background-color: #ff4d4d;
  color: #fff;
  border: none;
  outline: none;
  cursor: pointer;
  font-size: 14px;
  padding: 6px 12px;
  border-radius: 4px;
}

/* Add hover effect to the "Cancel" button */
.booked-tickets td button:hover {
  background-color: #ff3333;
}

   </style>
</head>
<body >
  <%@ page import="com.proj.beans.User" %>

<%
  User user = (User) session.getAttribute("user");
  if (user == null){
	  response.sendRedirect("Login.html");
	  return;
  }
%>
<nav>

	<a href="Home.html" style="text-align:right;">Home</a><br />
	<a href ="BookRoute.jsp" style="text-align:right;">Book Ticket Now</a><br />
	<form action="LogoutServlet" method="post">
		<input type="submit" value="Logout" />
	</form>

</nav>
<%@ page import="com.proj.dao.DBConnection" %>
<sql:setDataSource var="db" driver="org.postgresql.Driver"  
     url="jdbc:postgresql://localhost/ProjDB"  
     user="postgres"  password="ramji"/>
 <sql:query dataSource="${db}" var="rs">  
SELECT * FROM accountprofile WHERE username = '<%= user.getUsername()%>';  
</sql:query> 
<c:forEach var="user" items = "${rs.rows}">

<h1  style="text-align: center;">Welcome, <c:out value="${user.name}"/>!</h1>
<div id = "userinfo" class="user-details">
<form action="EditAccountServlet" method="post">
  <label for="name">Name:</label>
  <input type="text" id="name" name="name" value="<c:out value="${user.name}"/>" />
  <br />
  <br>
  <label for="email">Email:</label>
  <input type="text" id="email" name="email" value="<c:out value="${user.email}"/>" />
  <br />
  <br>
  <label for="address">Address:</label>
  <input type="text" id="address" name="address" value="<c:out value="${user.address}"/>" />
  <br />
  <br>
  <label for="age">Age:</label>
  <input type="number" id="age" name="age" value="<c:out value="${user.age}"/>" />
  <br />
  <br>
  <label for="phone">Phone:</label>
  <input type="number" id="phone" name="phone" value="<c:out value="${user.phone}"/>" />
  <br />
  <br>
  <!-- Other input fields -->
  <input type="submit" value="Save Changes" />
  <br />
</form>
</div>
</c:forEach>


<sql:setDataSource var="db" driver="org.postgresql.Driver"  
     url="jdbc:postgresql://localhost/ProjDB"  
     user="postgres"  password="ramji"/>
   <sql:query dataSource="${db}" var="rs">  
SELECT * FROM ticket WHERE passengerid = '<%= user.getUsername()%>';  
</sql:query>
	  <br />
	  <div id="tickets" class="booked-tickets">
      <table border="1" class="center">
      <br>
        <tr>
          <th>Ticket Status</th>
          <th>Name</th>
          <th>Source</th>
          <th>Destination</th>
          <th>Time Taken</th>
          <th>Price</th>
          <th>Age</th>
        </tr>
        <c:forEach var="row" items="${rs.rows}">
          <tr>
            
            <td>${row.ticketstatus}</td>
            <td>${row.passengername}</td>
            <td>${row.source}</td>
            <td>${row.destination}</td>
            <td>${row.hoursofjourney}</td>
            <td>${row.price}</td>
            <td>${row.passengerage}</td>
            <form action="CancelTicketServlet" method="post">
            	<input type="hidden" name="ticketid" value="${row.ticketid}">
  				<input type="hidden" name="ticketstatus" value="${row.ticketstatus}">
  				<td><input type="submit" value="Cancel Ticket"> </td>
            </form>
          </tr>
        </c:forEach>
      </table>
      </div>
</body>
</html>