package com.Digit.Project.Servlet.Remove;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RemoveCourse")
public class RemoveCourse extends HttpServlet {
	private Connection con;
	private PreparedStatement pstmt;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/coursemanagementServlet";
			String user = "root";
			String pwd = "root";
			con = DriverManager.getConnection(url, user, pwd);

			String rem_cou = req.getParameter("rem_cou");
			String sql1 = "delete from courses where c_id=?";
			pstmt = con.prepareStatement(sql1);

			System.out.println("Enter the ID of the Course name to remove");
			pstmt.setString(1, rem_cou);

			int x = pstmt.executeUpdate();
			if (x > 0) {
				resp.sendRedirect("/CourseManagement_Servlet/AdminMain.html");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
