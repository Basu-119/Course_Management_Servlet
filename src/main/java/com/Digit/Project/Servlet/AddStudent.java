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

@WebServlet("/AddStudent")
public class AddStudent extends HttpServlet {
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

			String stu_id = req.getParameter("stu_id");
			String stu_name = req.getParameter("stu_name");
			String stu_cou = req.getParameter("stu_cou");

			String sql = "insert into student values(?,?,?,?)";
			pstmt = con.prepareStatement(sql);

			try {
				pstmt.setString(1, stu_id);

				pstmt.setString(2, stu_name);

				pstmt.setString(3, stu_cou);
				pstmt.setString(4, "notmarked");

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
