package com.Digit.Project.Servlet.AdminService;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ApproveReq")
public class ApproveReq extends HttpServlet {
	private Connection con;
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet resultSet;
	private PreparedStatement pstmt1;
	private HttpSession session;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/coursemanagementServlet";
			String user = "root";
			String pwd = "root";
			con = DriverManager.getConnection(url, user, pwd);

			String sql = "select * from prorequest";

			stmt = con.createStatement();
			resultSet = stmt.executeQuery(sql);
			session = req.getSession();

			String user_name = (String) session.getAttribute("user_name");
			String user_pass = (String) session.getAttribute("user_pass");
			String pro_name = (String) session.getAttribute("pro_name");
			String pro_sub = (String) session.getAttribute("pro_sub");
			String pro_exp = (String) session.getAttribute("pro_exp");

			while (resultSet.next() == true) {
				int op =Integer.parseInt(req.getParameter("choice"));
				if (op==1) {
					String sql1 = "insert into users values(?,?,?)";
					pstmt = con.prepareStatement(sql1);

					pstmt.setString(1, user_name);
					pstmt.setString(2, user_pass);

					pstmt.setString(3, "Professor");

					int x = pstmt.executeUpdate();
					if (x > 0) {
						String sql3 = "insert into professor values(?,?,?,?)";
						pstmt1 = con.prepareStatement(sql3);
						pstmt1.setString(1, user_name);

						pstmt1.setString(2, pro_name);
						pstmt1.setString(3, pro_exp);
						pstmt1.setString(4, pro_sub);
						int y = pstmt1.executeUpdate();
						if (y > 0) {

							String sql2 = "delete from prorequest where user_name=?";
							pstmt = con.prepareStatement(sql2);

							pstmt.setString(1, user_name);

							int x1 = pstmt.executeUpdate();
							
							resp.sendRedirect("/CourseManagement_Servlet/AdminMain.html");
						}

					}

				} else if (op==2) {
					String sql2 = "delete from prorequest where user_name=?";
					pstmt = con.prepareStatement(sql2);

					pstmt.setString(1, user_name);

					int x = pstmt.executeUpdate();
					resp.sendRedirect("/CourseManagement_Servlet/AdminMain.html");

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
