package com.proj.beans;

public class Ticket {
	  private String ticketid;
	  private String ticketstatus;
	  private String source;
	  private String destination;
	  private String hoursofjourney;
	  private String price;
	  private String route;
	  private String passengername;
	  private String passengerid;
	  private String passengeraddress;
	  private int passengerage;
	  private boolean coupanapplied;
	  private String feedback;

	  // Constructor, getters, and setters
	  public Ticket(String ticketid, String ticketstatus, String source, String destination, String hoursofjourney, String price, 
			  String route, String passengername, String passengerid, String passengeraddress, 
			  int passengerage, boolean coupanapplied, String feedback) {
		  this.ticketid = ticketid;
		  this.ticketstatus = ticketstatus;
		  this.source = source;
		  this.destination = destination;
		  this.hoursofjourney = hoursofjourney;
		  this.price = price;
		  this.route = route;
		  this.passengername = passengername;
		  this.passengerid = passengerid;
		  this.passengeraddress = passengeraddress;
		  this.passengerage = passengerage;
		  this.coupanapplied = coupanapplied;
		  this.feedback = feedback;
		  
		  
	  }
	  public String getTicketid() {
		    return ticketid;
		  }

		  public void setTicketid(String ticketid) {
		    this.ticketid = ticketid;
		  }

		  public String getTicketstatus() {
		    return ticketstatus;
		  }

		  public void setTicketstatus(String ticketstatus) {
		    this.ticketstatus = ticketstatus;
		  }

		  public String getSource() {
		    return source;
		  }

		  public void setSource(String source) {
		    this.source = source;
		  }

		  public String getDestination() {
		    return destination;
		  }

		  public void setDestination(String destination) {
		    this.destination = destination;
		  }

		  public String getHoursofjourney() {
		    return hoursofjourney;
		  }
		  public void setHoursofjourney(String hoursofjourney) {
			  this.hoursofjourney = hoursofjourney;
		  }
		  public String getPrice() {
			    return price;
			  }

			  public void setPrice(String price) {
			    this.price = price;
			  }

			  public String getRoute() {
			    return route;
			  }

			  public void setRoute(String route) {
			    this.route = route;
			  }

			  public String getPassengername() {
			    return passengername;
			  }

			  public void setPassengername(String passengername) {
			    this.passengername = passengername;
			  }

			  public String getPassengerid() {
			    return passengerid;
			  }

			  public void setPassengerid(String passengerid) {
			    this.passengerid = passengerid;
			  }

			  public String getPassengeraddress() {
			    return passengeraddress;
			  }

			  public void setPassengeraddress(String passengeraddress) {
			    this.passengeraddress = passengeraddress;
			  }

			  public int getPassengerage() {
			    return passengerage;
			  }

			  public void setPassengerage(int passengerage) {
			    this.passengerage = passengerage;
			  }

			  public boolean isCoupanapplied() {
			    return coupanapplied;
			  }

			  public void setCoupanapplied(boolean coupanapplied) {
			    this.coupanapplied = coupanapplied;
			  }

			  public String getFeedback() {
			    return feedback;
			  }

			  public void setFeedback(String feedback) {
			    this.feedback = feedback;
			  }
	}
