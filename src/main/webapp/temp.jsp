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
<meta charset="UTF-8">
<title>Enter The Vehicles</title>
</head>
<body>
	<form action="TempServlet" method = "post">
  <label for="vehicleid">Vehicle ID:</label>
  <input type="text" id="vehicleid" name="vehicleid" />
  <br />
  <label for="vehicletype">Vehicle Type:</label>
  <input type="text" id="vehicletype" name="vehicletype" />
  <br />
  <label for="source">Source:</label>
  <input type="text" id="source" name="source" />
  <br />
  <label for="destination">Destination:</label>
  <input type="text" id="destination" name="destination" />
  <br />
  <label for="timetaken">Time Taken:</label>
  <input type="text" id="timetaken" name="timetaken" />
  <br />
  <label for="price">Price:</label>
  <input type="number" step="0.1" id="price" name="price" />
  <br />
  <label for="departuretime">Departure Time:</label>
  <input type="text" id="departuretime" name="departuretime" />
  <input type="submit" value="Insert" />
	</form>	
</body>
</html>