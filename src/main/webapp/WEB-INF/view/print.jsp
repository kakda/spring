<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<applet id="printApplet" 
		code="com.print.PrintFile.class" 
		width="500" 
		height="500" 
		codebase="<spring:url value="/resources" />/" 
		archive="PrintFile.jar" >
			<param name="fileUrl" value="http://i.stack.imgur.com/42Dfa.jpg">
	</applet>
	<img src="<spring:url value="/resources/42Dfa.jpg" />" />
</body>
</html>