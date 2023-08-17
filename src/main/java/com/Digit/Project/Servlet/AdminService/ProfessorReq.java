package com.Digit.Project.Servlet.AdminService;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ProfessorReq")
public class ProfessorReq extends HttpServlet {
	private Connection con;
	private PreparedStatement pstmt;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession();

			String pro_name = req.getParameter("pro_name");
			String user_name = req.getParameter("user_name");
			String user_pass = req.getParameter("user_pass");
			String pro_sub = req.getParameter("pro_sub");
			String pro_exp = req.getParameter("pro_exp");

			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/coursemanagementServlet";
			String user = "root";
			String pwd = "root";
			con = DriverManager.getConnection(url, user, pwd);

			String sql = "insert into prorequest values(?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, user_name);

			pstmt.setString(2, pro_name);
			pstmt.setString(3, user_pass);
			pstmt.setString(4, pro_sub);
			pstmt.setString(5, pro_exp);
			pstmt.setString(6, "Professor");

			int x = pstmt.executeUpdate();
			if (x > 0) {
				session.setAttribute("user_name",user_name);
				session.setAttribute("user_pass", user_pass);
				session.setAttribute("pro_name", pro_name);
				session.setAttribute("pro_sub", pro_sub);
				session.setAttribute("pro_exp", pro_exp);


				resp.sendRedirect("/CourseManagement_Servlet/ProReq.html");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
