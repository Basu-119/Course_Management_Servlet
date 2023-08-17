package com.Digit.Project.Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/stulogin")
public class StudentLogin extends HttpServlet {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet res;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession();
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/coursemanagementServlet";
			String user = "root";
			String pwd = "root";
			con = DriverManager.getConnection(url, user, pwd);
			String user_id = req.getParameter("user_id");
			String pass = req.getParameter("pass");
			String sql = "select * from users where user_name=? and user_pass=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setString(2, pass);
			res = pstmt.executeQuery();
			if (res.next()) {
				session.setAttribute("stu_user_id", user_id);
				resp.sendRedirect("/CourseManagement_Servlet/StudentMain.html");
			} else {

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
