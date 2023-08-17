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
session= request.getSession();
out.println(session.getAttribute("stu_id"));
out.println(session.getAttribute("stu_name"));
out.println(session.getAttribute("stu_cou"));

%>



</body>
</html>