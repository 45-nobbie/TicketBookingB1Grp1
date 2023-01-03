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
<link rel = "stylesheet" href = "./css/StyleSignUp.css">
<style>
body {
	color: #fff;
	background: #405969;
	font-family: 'Roboto', sans-serif;
}
html,
body {
	height: 100%;
}

body {
	margin: 0;
	background: linear-gradient(45deg, #49a09d, #5f2c82);
	font-family: sans-serif;
	font-weight: 100;
}

.container {
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
}

button, button::after {
  width: 255px;
  height: 75px;
  font-size: 36px;
  font-family: 'Bebas Neue', cursive;
  background: linear-gradient(45deg, transparent 5%, #FF013C 5%);
  border: 0;
  color: #fff;
  letter-spacing: 3px;
  line-height: 88px;
  box-shadow: 6px 0px 0px #00E6F6;
  outline: transparent;
  position: relative;
}

button::after {
  --slice-0: inset(50% 50% 50% 50%);
  --slice-1: inset(80% -6px 0 0);
  --slice-2: inset(50% -6px 30% 0);
  --slice-3: inset(10% -6px 85% 0);
  --slice-4: inset(40% -6px 43% 0);
  --slice-5: inset(80% -6px 5% 0);
  
  content: 'AVAILABLE NOW';
  display: block;
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: linear-gradient(45deg, transparent 3%, #00E6F6 3%, #00E6F6 5%, #FF013C 5%);
  text-shadow: -3px -3px 0px #F8F005, 3px 3px 0px #00E6F6;
  clip-path: var(--slice-0);
}

button:hover::after {
  animation: 1s glitch;
  animation-timing-function: steps(2, end);
}

@keyframes glitch {
  0% {
    clip-path: var(--slice-1);
    transform: translate(-20px, -10px);
  }
  10% {
    clip-path: var(--slice-3);
    transform: translate(10px, 10px);
  }
  20% {
    clip-path: var(--slice-1);
    transform: translate(-10px, 10px);
  }
  30% {
    clip-path: var(--slice-3);
    transform: translate(0px, 5px);
  }
  40% {
    clip-path: var(--slice-2);
    transform: translate(-5px, 0px);
  }
  50% {
    clip-path: var(--slice-3);
    transform: translate(5px, 0px);
  }
  60% {
    clip-path: var(--slice-4);
    transform: translate(5px, 10px);
  }
  70% {
    clip-path: var(--slice-2);
    transform: translate(-10px, 10px);
  }
  80% {
    clip-path: var(--slice-5);
    transform: translate(20px, -10px);
  }
  90% {
    clip-path: var(--slice-1);
    transform: translate(-10px, 0px);
  }
  100% {
    clip-path: var(--slice-1);
    transform: translate(0);
  }
}
table {
	width: 800px;
	border-collapse: collapse;
	overflow: hidden;
	box-shadow: 0 0 20px rgba(0,0,0,0.1);
}

th,
td {
	padding: 15px;
	background-color: rgba(255,255,255,0.2);
	color: #fff;
}

th {
	text-align: left;
}

thead {
	th {
		background-color: #55608f;
	}
}

tbody {
	tr {
		&:hover {
			background-color: rgba(255,255,255,0.3);
		}
	}
	td {
		position: relative;
		&:hover {
			&:before {
				content: "";
				position: absolute;
				left: 0;
				right: 0;
				top: -9999px;
				bottom: -9999px;
				background-color: rgba(255,255,255,0.2);
				z-index: -1;
			}
		}
	}
}
</style>
<title>All Available Vehicles</title>
</head>
<body>
<%@ page import="com.proj.beans.User" %>

<%
  User user = (User) session.getAttribute("user");
  if (user == null){
	  response.sendRedirect("Login.html");
	  return;
  }
%>
<%
  String source = request.getParameter("source");
  String destination = request.getParameter("destination");
%>
<sql:setDataSource var="db" driver="org.postgresql.Driver"  
     url="jdbc:postgresql://localhost/ProjDB"  
     user="postgres"  password="ramji"/>
 <sql:query dataSource="${db}" var="result">  
SELECT * FROM vehicle WHERE source = '<%= request.getParameter("source")%>' AND destination = '<%=request.getParameter("destination")%>';  
</sql:query> 
    <form action="BookingServlet" method="post">
    <div class="container">
    
      <table>
      <thread>
        <tr>
          <th>Select</th>
          <th>Vehicle Type</th>
          <th>Source</th>
          <th>Destination</th>
          <th>Time Taken</th>
          <th>Price</th>
          <th>Departure Time</th>
        </tr>
        </thread>
        <tbody>
        <c:forEach var="row" items="${result.rows}">
          <tr>
            <td>
              <input type="radio" name="vehicle" value="${row.vehicleid}" required />
            </td>
            <td>${row.vehicletype}</td>
            <td>${row.source}</td>
            <td>${row.destination}</td>
            <td>${row.timetaken}</td>
            <td>${row.price}</td>
            <td>${row.departuretime}</td>
            <input type="hidden" name="vehicleid" value="${row.vehicleid}" />
            <input type="hidden" name="source" value="${row.source}" />
            <input type="hidden" name="destination" value="${row.destination}"  />
          </tr>
          </tbody>
        </c:forEach>
      </table>
      
      <button type="submit" value="Book">Book Now </button>
    </form>
    </div>
</body>
</html>