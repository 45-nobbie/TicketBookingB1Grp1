<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Booking</title>
</head>
<style>
body{
    margin: 0;
    padding: 0;
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 100vh;
    background: #000;
    background-image: url("file:///C:/Users/Meawzer/Downloads/op_watching_the_sunset_by_sanjoh_d3aol5b-fullview.jpg");
    background-position: relative;
    background-size: cover;
    font-family: sans-serif;
    letter-spacing: 1px;
}

.container{
    position: relative;
    width: 450px;
    min-height: 350px;
    border-radius: 20px;
    background: rgba(255,255,255,0.5);
    box-shadow: 0 5px 15px rgba(0,0,0,1);
}

.container:before{
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    width: 50%;
    height: 100%;
    background: rgba(255,255,255,0.1);
    pointer-events: none ;
}

.container:after{
    content: '';
    position: absolute;
    top: -5px;
    left: -5px;
    right: -5px;
    bottom: -5px;
    background: linear-gradient(60deg, #ff0047, #6eff00);
    pointer-events: none;
    animation: animate 10s linear infinite;
}

@keyframes animate
{
    0%
    {
        filter: blur(60px) hue-rotate(0deg);
    }
    100%
    {
        filter: blur(60px) hue-rotate(360deg);
    }
}
.form{
    position: absolute;
    width: 100%;
    height: 100%;
    padding: 40px;
    box-sizing: border-box;
    z-index: 1;
}

.form h2{
    margin-top: 0px;
    margin-left: 140px;
    padding: 0;
    color: #fff;
    font-size: 24px;
    font-family: sans-serif;
    justify-content: center;
}

.form .inputbox{
    width: 300px;
    margin-top: 20px;
}

#text
{
    display: block;
    color: #000;
    font-weight: 800;
    font-style: italic;
    padding: 5px;
}

.form .inputbox input{
    width: 100%;
    background: #292929;
    border-radius: 6px;
    border: none;
    outline: : none;
    font-size: 14px;
    padding: 7px 0;
    font-family: sans-serif;
    color: #fff;
    font-style: 18px;
}

::placeholder{
    color: #eee;
    font-style: 18px;
    font-family: sans-serif;
}

.form .inputbox input[type="submit"]
{
    color: #fff;
    border: none;
    font-weight: 900;
    max-width: 100px;
    cursor: pointer;
    border-radius: 10px;
    font-family: Tahoma, sans-serif;
    background-color: rgb(16, 124, 247);
}
</style>
<body>
<div class="container">
<div class="form">
<h2> Book Your Tickets</h2>
  <%@ page import="com.proj.beans.User" %>

<%
  User user = (User) session.getAttribute("user");
  if (user == null){
	  response.sendRedirect("Login.html");
	  return;
  }
%>
	<form action="ShowRoute.jsp" method ="post">
	<div class="inputbox" id= "form">
	  <label for="source">Source:</label>
      <input type="text" id="source" name="source" required />
      <br />
      <br>
      </div>
      <div class="inputbox" id="form">
      <label for="destination">Destination:</label>
      <input type="text" id="destination" name="destination" required />
      <br />
      <br>
      </div>
      <div class="inputbox" id="form">
      <label for="dateofjourney">Date of Journey:</label>
      <input type="date" id="dateofjourney" name="dateofjourney" required />
      <br />
      <br>
      </div>
      <div class="inputbox">
      <input type="submit" value="Go" />
      </div>
	</form>
	</div>
	</div>
	
</body>
</html>