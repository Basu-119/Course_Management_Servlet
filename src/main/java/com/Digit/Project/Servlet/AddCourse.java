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

@WebServlet("/AddCourse")
public class AddCourse extends HttpServlet {
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

			String cou_id = req.getParameter("cou_id");
			String cou_name = req.getParameter("cou_name");
			String cou_dur = req.getParameter("cou_dur");

			String sql = "insert into courses values(?,?,?)";
			pstmt = con.prepareStatement(sql);

			try {
				pstmt.setString(1, cou_id);

				pstmt.setString(2, cou_name);

				pstmt.setString(3, cou_dur + "months");

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
