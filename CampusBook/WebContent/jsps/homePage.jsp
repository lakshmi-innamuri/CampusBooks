<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page import="java.util.*"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Campus Books Home Page</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/campusbooks.css">
<script src="<%=request.getContextPath()%>/script/campusbookscript.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js">
	
</script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#homeForm").load("<%=request.getContextPath()%>/jsps/homeLeftNavigation.jsp");
	});
</script>
</head>
<%@ include file="/jsps/header.jsp"%>
<body>
	<div class="mainBodyContent">
		<section>
		<div id="homeForm"></div>
		</section>
	</div>
</body>
</html>