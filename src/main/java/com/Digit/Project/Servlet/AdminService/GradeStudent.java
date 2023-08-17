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


@WebServlet("/grade")
public class GradeStudent extends HttpServlet{
private Connection con;
private Statement stmt;
private ResultSet resultSet;
private PreparedStatement pstmt;
private PreparedStatement qstmt;

@Override
protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/coursemanagementServlet";
		String user = "root";
		String pwd = "root";
		con = DriverManager.getConnection(url, user, pwd);

		String sql = "select * from student where status!='marked'";

		stmt = con.createStatement();
		resultSet = stmt.executeQuery(sql);
		
		while (resultSet.next() == true) {

			String sql1 = "insert into studentmark values(?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql1);

			String sql2 = "update student set status=? where stu_id =?";
			qstmt = con.prepareStatement(sql2);

			// Adding Student name in studentmatk from student
			pstmt.setString(1, resultSet.getString("stu_id"));

			pstmt.setString(2, resultSet.getString("stu_name"));

			pstmt.setString(3, resultSet.getString("stu_cou"));


			pstmt.setInt(4, 100);

//			
//			pstmt.setInt(5, mark);
//			String rem=grading(mark);
//			pstmt.setString(6, rem);
//			pstmt.setString(7,remarking(rem));

			pstmt.setString(8, "marked");

			qstmt.setString(2, resultSet.getString("stu_id"));
			qstmt.setString(1, "Marked");

			int x = pstmt.executeUpdate();
			if(x>0) {
				System.out.println("Student marked successfully");
			}
//			result1 = pstmt.executeUpdate();
			qstmt.executeUpdate();
//			System.out.println(resultSet);
			Thread.sleep(1000);
		}

	} catch (Exception e) {
		e.printStackTrace();
	}

}
}
