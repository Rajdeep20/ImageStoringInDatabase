package com.ashoavnIT;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Servlet implementation class ThirdGenServ
 */
public class ThirdGenServ extends GenericServlet {
	private static final long serialVersionUID = 1L;

	public ThirdGenServ() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {

		System.out.println("Inside Generic Servlet");

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String image = request.getParameter("image");
		String imageTag = "<img src= '" + image + "' alt=='img' height='100px' width='100px' >";

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h2> Welcome to ashovanIT , Pune  </h2>");

		out.println("username" + ":" + username + "<br>");
		out.println("password" + ":" + password + "<br>");
		out.println(imageTag);

		
		//JDBC connection
		String url = "jdbc:mysql://localhost:3306/company";
		String user = "root";
		String sqlPass = "1320@Raj";

		try {
			System.out.println("inside try block ");
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, sqlPass);
			
			String sql = "insert into users values(?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, image);
			
			//FileInputStream fis = new FileInputStream("D:\\RajDoc\\pp.jpeg");
			//ps.setBinaryStream(1, fis, fis.available());
			ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Done");
		System.out.println("Database connected");

	}

}
