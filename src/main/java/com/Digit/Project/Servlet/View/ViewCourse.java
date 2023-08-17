package com.Digit.Project.Servlet.View;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ViewCourse")
public class ViewCourse extends HttpServlet {
	private Connection con;
	private Statement stmt;
	private ResultSet resultSet;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession(true);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/coursemanagementServlet";
			String user = "root";
			String pwd = "root";
			con = DriverManager.getConnection(url, user, pwd);

			String sql = "select * from courses";

			stmt = con.createStatement();
			resultSet = stmt.executeQuery(sql);
			while (resultSet.next() == true) {
				session.setAttribute("c_id", resultSet.getString("c_id"));
				session.setAttribute("c_name", resultSet.getString("c_name"));
				session.setAttribute("c_dur", resultSet.getString("c_time"));

			}

			resp.sendRedirect("/CourseManagement_Servlet/ViewCourse.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
