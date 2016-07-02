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
</head>
<body>
	<%!HttpSession session;
	String userName;
	int userId;
	%>
	<%
		session = request.getSession();
		userName = (String) session.getAttribute("userName");
		userId = ((Integer) session.getAttribute("userId")).intValue();
	%>
	<div class="headerContent">

		<form id="headerForm"
			action="<%=request.getContextPath()%>/CampusBooksServlet"
			method="post">
			<input type="hidden" value="fromHeaderPage" name="header" />
			<input type="hidden" value="<%=userId%>" name="userId" />
			<div id="title">
				<h1>Campus Books</h1>
			</div>
			<div class="">
			<img class="bookImg" src="<%=request.getContextPath()%>/images/books.png" alt="Campus Book">
			</div>
			<div id="userActions">

				<span><button type="submit" class="button" name="username" value="<%=userName%>"><%=userName%></button></span>
				<span><button type="submit" class="button" name="logout" value="logout">Logout</button></span>

			</div>
		</form>

		<nav>
		<ul>
			<li><a href="<%=request.getContextPath()%>/jsps/homePage.jsp">Home</a>
			</li>
			<li><a href="<%=request.getContextPath()%>/jsps/addBookPage.jsp">Post</a>
			</li>
			<li><a href="<%=request.getContextPath()%>/jsps/search.jsp">Search</a></li>
			<li><a href="<%=request.getContextPath()%>/CampusBooksServlet?fromTopBooks=yes">View Top Books</a></li>
			<li><a href="<%=request.getContextPath()%>/CampusBooksServlet?fromTopSellers=yes">View Top Sellers</a></li>

		</ul>
		</nav>

	</div>
</body>
</html>