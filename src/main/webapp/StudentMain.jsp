<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
	Class.forName("com.mysql.cj.jdbc.Driver");
	String url = "jdbc:mysql://localhost:3306/coursemanagementServlet";
	String user = "root";
	String pwd = "root";
	Connection con = DriverManager.getConnection(url, user, pwd);
	HttpSession stusession = request.getSession();
	String sql = "select * from studentmark where stu_id=? ";
	PreparedStatement ptsmt;
	ptsmt = con.prepareStatement(sql);
	String user_id = (String) stusession.getAttribute("user_id");
	ptsmt.setString(1, user_id);
	ResultSet r;
	r = ptsmt.executeQuery();
	while (r.next() == true) {
		String name = r.getString("stu_name");
		out.println("Student name"+name);
		out.println("Student ID is :" + r.getString("stu_id") + "");
		out.println("Student Name is :" + r.getString("stu_name") + "");
		out.println("Student Course is :" + r.getString("stu_cour") + "");
		out.println("Student Full Mark  is :" + r.getString("stu_fmark") + "");
		out.println("Student Mark is :" + r.getString("stu_mark") + "");
		out.println("Student Grade is :" + r.getString("stu_grade") + "");
		out.println("Student Remark is :" + r.getString("stu_remark") + "");

	}
		response.sendRedirect("/CourseManagement_Servlet/StudentMain.jsp");
	%>
	
	<h1>$name</h1>

</body>
</html>