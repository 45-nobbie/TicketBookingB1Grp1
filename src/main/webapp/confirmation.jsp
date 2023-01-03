<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
body{
	background: linear-gradient(to right, white, lightgrey, grey);
}
	.logout-button {
  font-size: 18px;
  font-family: Arial, sans-serif;
  color: #ffffff;
  border: none;
  background: #333333;
   position: absolute;
  top: 0;
  right: 0;
  margin: 10px 0;
  cursor: pointer;
   border-bottom: 2px solid black;
  padding: 0px 0px;
	}
	
	.logout-button:hover {
	  background: #333333;
	  color: red;
	}
	a {
  font-size: 18px;
  font-family: Arial, sans-serif;
  color: #333333;
  text-decoration: none;
  margin: 10px 0;
}

a:link, a:visited {
  color: #333333;
}

a:hover, a:active {
  color: blue;
}
h3 {
  font-size: 24px;
  font-family: Arial, sans-serif;
  color: #333333;
  text-align: center;
  margin: 50px 0;
}
h1{
  font-size: 28px;
  font-family: Arial, sans-serif;
  color: #333333;
  text-align: center;
  margin: 50px 0;
}

</style>
<meta charset="UTF-8">
<title>Ticket Booked</title>
</head>
<body>
  <%@ page import="com.proj.beans.User" %>
  <%@ page import="com.proj.beans.Book" %>
  <%
  User user = (User) session.getAttribute("user");
  if (user == null){
	  response.sendRedirect("Login.html");
	  return;
  }
%>
   <%
  Book book = (Book) session.getAttribute("book");
  if (book == null){
	  response.sendRedirect("Home.html");
	  return;
  }
%>
	<h1>
	 Booking Confirmed 
	</h1>
	<h3>Thank You For Using Our Services <br>
		Your Ticket(s) has been Booked Successfully
	</h3>
	<h2>To view our Home Page please Follow this link-<a href="Home.html" >Home</a><br /></h2>
	<h2>To Book another ticket please follow this link-<a href ="BookRoute.jsp" >Book Now</a><br /></h2>
	<h2>To view user account and manage Booked tickets-<a href ="Welcome.jsp">My Account</a></h2>
	<div class="logout-button">
	<form action="LogoutServlet" method="post">
		<input type="submit" value="Logout" />
	</form>
	</div>
</body>
</html>