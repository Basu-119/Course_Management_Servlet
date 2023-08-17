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
out.println(session.getAttribute("pro_id"));
out.println(session.getAttribute("pro_name"));
out.println(session.getAttribute("pro_exp"));
out.println(session.getAttribute("pro_sub"));

%>



</body>
</html>