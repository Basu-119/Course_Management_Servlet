package com.Digit.Project.Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/AddProfessor")
public class AddProfessor extends HttpServlet{
	
	private Connection con;
	private PreparedStatement pstmt;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/coursemanagementServlet";
		String user = "root";
		String pwd = "root";
		con = DriverManager.getConnection(url, user, pwd);

		String pro_id = req.getParameter("pro_id");
		String pro_name = req.getParameter("pro_name");
		String pro_exp = req.getParameter("pro_exp");
		String pro_sub = req.getParameter("pro_sub");
		
		String sql = "insert into professor values(?,?,?,?)";
		pstmt = con.prepareStatement(sql);

		try {
			pstmt.setString(1, pro_id);

			pstmt.setString(2, pro_name);

			pstmt.setString(3, pro_exp);
			
			pstmt.setString(4, pro_sub);

			int x = pstmt.executeUpdate();
			if (x > 0) {
				resp.sendRedirect("/CourseManagement_Servlet/AdminMain.html");
			}
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
		}

	} catch (

	Exception e) {
		e.printStackTrace();
	}

}
	}

