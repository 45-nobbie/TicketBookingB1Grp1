<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <%@ page import="java.io.*,java.util.*,java.sql.*"%>  
<%@ page import="javax.servlet.http.*,javax.servlet.*" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>  
<%@ page import="com.proj.beans.Book" %>

<!DOCTYPE html>
<html>
  <head>
<style>:root {
  font-size: 20px;
  box-sizing: inherit;
}


*,
*:before,
*:after {
  box-sizing: inherit;
}

p {
  margin: 0;
}

p:not(:last-child) {
  margin-bottom: 1.5em;
}

body {
  font: 1em/1.618 Inter, sans-serif;

  display: flex;
  align-items: center;
  justify-content: center;
  
  min-height: 100vh;
  padding: 30px;
  
  color: #224;
  background:
    url(https://source.unsplash.com/E8Ufcyxz514/2400x1823)
    center / cover no-repeat fixed;
}

.card {
  max-width: 300px;
  min-height: 200px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;

  max-width: 500px;
  height: 300px;
  padding: 35px;

  border: 1px solid rgba(255, 255, 255, .25);
  border-radius: 20px;
  background-color: rgba(255, 255, 255, 0.45);
  box-shadow: 0 0 10px 1px rgba(0, 0, 0, 0.25);

  backdrop-filter: blur(15px);
}

.stage {
  box-sizing: border-box;
  position: absolute;
  left: 0;
  top: 0;
  width: 10vw;
  height: 10vh;
  overflow: hidden;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #65799b;
}

.btn {
  width: 200px;
  font-size: 19x;
  height: 30px;
  border: none;
  outline: none;
  background: #c38b8b;
  color: #fff;
  cursor: pointer;
  position: relative;
  z-index: 0;
  border-radius: 10px;
}

.btn::before {
  position: absolute;
  content: "";
  width: calc(100% + 4px);
  height: calc(100% + 4px);
  left: -2px;
  top: -2px;
  background: linear-gradient(
    124deg,
    #ff2400,
    #e81d1d,
    #e8b71d,
    #e3e81d,
    #1de840,
    #1ddde8,
    #2b1de8,
    #dd00f3,
    #dd00f3
  );
  background-size: 400%;
  z-index: -1;
  filter: blur(1px);
  animation: move 20s linear infinite;
  opacity: 0;
  transition: opacity 0.3s ease-in-out;
  border-radius: 10px;
}

.btn:hover::before {
  opacity: 1;
}

.btn::after {
  z-index: -1;
  content: "";
  position: absolute;
  width: 100%;
  height: 100%;
  background: #555273;
  left: 0;
  top: 0;
  border-radius: 10px;
}

@keyframes move {
  0% {
    background-position: 0 0;
  }
  50% {
    background-position: 400% 0;
  }
  100% {
    background-position: 0 0;
  }
}

.card-footer {
  font-size: 0.65em;
  color: #446;
}
.coupan{
postion: absolute;
  top: 0;
  right: 0;
  float: right;
}
  </style>
    <title>Passenger Information</title>
  </head>
  <body>
  <div class="card">
    <%@ page import="com.proj.beans.Book" %>
    <%
  Book book = (Book) session.getAttribute("book");
  if (book == null){
	  response.sendRedirect("Login.html");
	  return;
  }
%>
	<%@ page import="com.proj.dao.DBConnection" %>
	<sql:setDataSource var="db" driver="org.postgresql.Driver"  
	     url="jdbc:postgresql://localhost/ProjDB"  
	     user="postgres"  password="ramji"/>
	 <sql:query dataSource="${db}" var="rs">  
	SELECT * FROM vehicle WHERE vehicleid = '<%= book.getVehicleId()%>';  
</sql:query> 
	<c:forEach var="book" items = "${rs.rows}">
      <table>
        <tr>
          <th>Vehicle Type:</th>
          <td><c:out value="${book.vehicletype}"/></td>
        </tr>
        <tr>
          <th>Source:</th>
          <td><c:out value="${book.source}"/></td>
        </tr>
        <tr>
          <th>Destination</th>
          <td><c:out value="${book.destination}"/></td>
        </tr>
        <tr>
          <th>Time Taken</th>
          <td><c:out value="${book.timetaken}"/></td>
        </tr>
        <tr>
          <th>Price</th>
          <td><c:out value="${book.price}"/></td>
        </tr>
        <tr>
        	<th>Departure Time:</th>
        	<td><c:out value="${book.departuretime}"/></td>
        </tr>
      </table>
      <br>
      </div>
 	<br />

     
      <form action="TicketServlet" method="post">
      <table id="passenger-table">
 <tr>
    <th colspan="3" align="Center" font-weight: 700>Passenger Information</th>
  </tr>
  <tr>
    <th>Name</th>
    <th>Age</th>
    <th>Address</th>
    <th></th>
  </tr>
  <tr>
    <td><input type="text" name="passengerName[]" required></td>
    <td><input type="number" name="passengerAge[]" required></td>
    <td><input type="text" name="passengerAddress[]" required></td>
    <td><button type="button" onclick="deletePassenger(this)">Delete</button></td>
  </tr>
</table>
 
<br />
<button type="button" onclick="addPassenger()" class= "btn">Add Passenger</button><br>
<p>Total price: <span id="total-price"><c:out value="${book.price}" /></span> Rupees</p>
  <input type="hidden" name="timeTaken" id="timetaken" value="<c:out value="${book.timetaken}"/>">
  <input type="hidden" name="price" id="price" value="<c:out value="${book.price}"/>">
<Button type="submit" value="Pay & Book" class="btn">Submit And Pay</Button>
<br>
 <div class="coupan">
  <label for="couponCode">Enter coupon code:</label><br>
  <input type="text" id="couponCode" name="couponCode">
  <button type="button" onclick="verifyCoupon()" class= "btn">Verify coupon</button><br>
</div>
<script>
  var 	coupons = ["Expelliarmus", ["Dobby"], ["Pandey50"]]
  var totalPrice = <c:out value="${book.price}"/>;
  function addPassenger() {
    // Add a new row to the table
    var table = document.getElementById("passenger-table");
    var row = table.insertRow(-1);
    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    var cell3 = row.insertCell(2);
    var cell4 = row.insertCell(3);
    cell1.innerHTML = '<input type="text" name="passengerName[]" required>';
    cell2.innerHTML = '<input type="number" name="passengerAge[]" required>';
    cell3.innerHTML = '<input type="text" name="passengerAddress[]" required>';
    cell4.innerHTML = '<button type="button" onclick="deletePassenger(this)">Delete</button>';
    totalPrice += <c:out value="${book.price}"/>;
    updateTotalPrice();
  }

  function deletePassenger(button) {
    // Remove the row containing the button
    var row = button.parentNode.parentNode;
    row.parentNode.removeChild(row);
    totalPrice -=  <c:out value="${book.price}"/>;
    updateTotalPrice();
  }
  function updateTotalPrice() {
	  var totalPriceElement = document.getElementById("total-price");
	  totalPriceElement.innerHTML = totalPrice;
	}
  function verifyCoupon() {
	    // Get the coupon code entered by the user
	     var couponCode = document.getElementById("couponCode").value;

		  // Verify the coupon code (replace with your own verification logic)
		  if (couponCode == "Dobby50") {
		    // Coupon code is valid
		    alert("Coupon verified!");
			totalPrice -= (totalPrice/2);
			updateTotalPrice();
		    // Disable the button to prevent multiple submissions
		    document.getElementById("verifyButton").disabled = true;
		  }
		  else if (couponCode == "Expelliarmus") {
			    // Coupon code is valid
			    alert("Coupon verified!");
				totalPrice -= (totalPrice/4);
				updateTotalPrice();
			    // Disable the button to prevent multiple submissions
			    document.getElementById("verifyButton").disabled = true;
			  }
		  else {
		    // Coupon code is invalid
		    alert("Invalid coupon code.");
		  }
	  }
</script>
</c:forEach>
    </body>
    </html>