package com.Digit.Project.Servlet.AdminService;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/getmark")
public class StudentMarkDetails extends HttpServlet {
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
			String stu_user_id=(String) session.getAttribute("stu_user_id");
			String sql = "select * from studentmark where stu_id=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, stu_user_id);
			res = pstmt.executeQuery();
			while (res.next() == true) {
				session.setAttribute("stu_mark_name", res.getString("stu_name"));
				session.setAttribute("stu_mark_cour", res.getString("stu_cour"));
				session.setAttribute("stu_mark_fmark", res.getInt("stu_fmark"));
				session.setAttribute("stu_mark_mark", res.getInt("stu_mark"));
				session.setAttribute("stu_mark_grade", res.getString("stu_grade"));
				session.setAttribute("stu_mark_remark", res.getString("stu_remark"));
				resp.sendRedirect("/CourseManagement_Servlet/StuMark.jsp");

			}


		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
