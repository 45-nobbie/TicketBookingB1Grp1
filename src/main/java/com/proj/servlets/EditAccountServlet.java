package com.proj.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.proj.beans.User;
import com.proj.dao.UserDao;

/**
 * Servlet implementation class EditAccountServlet
 */
@WebServlet("/EditAccountServlet")
public class EditAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditAccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		  if (user == null){
			  response.sendRedirect("Login.html");
			  return;
		  }
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		  if (user == null){
			  response.sendRedirect("Login.html");
			  return;
		  }
		
		
		 String username = user.getUsername();
		 String name = request.getParameter("name");
		 String email = request.getParameter("email");
		 String password = user.getPassword();
		 String address = request.getParameter("address");
		 int age = Integer.parseInt(request.getParameter("age"));
		 String phone = request.getParameter("phone");
		 
		 User upUser = new User(name, username, email, password, address, age, phone);
		 
		 UserDao userdao = new UserDao();
		 userdao.UpdateUser(upUser);
		 
		 response.sendRedirect("Welcome.jsp");
		 
	}

}
