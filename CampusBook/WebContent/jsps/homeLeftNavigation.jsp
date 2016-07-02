<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page language="java" import="java.util.*"%>
<%@ page import="com.emich.edu.User"%>
<%@ page import="com.emich.edu.Book"%>
<!DOCTYPE html>
<html>
<head>
<title>Search Book Page</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/campusbooks.css">

<script src="<%=request.getContextPath()%>/script/campusbookscript.js"></script>




</head>

<body>




	<%!HttpSession session;
	int userId;%>
	<%
		session = request.getSession();
		userId = ((Integer) session.getAttribute("userId")).intValue();
	%>
	<section>

		<form id="loginForm"
			action="<%=request.getContextPath()%>/CampusBooksServlet"
			method="post">
			<table class="leftNav" align="center">
				<caption></caption>
				<tr>
					<td><a href="<%=request.getContextPath()%>/CampusBooksServlet?fromHistory=history"> History Of Added Books </a></td>
				</tr>
				<tr>
					<td><a href="<%=request.getContextPath()%>/CampusBooksServlet?contactSellers=contact"> Contacted Sellers </a></td>
				</tr>
			</table>
			<input type="hidden" value="<%=userId%>" name="userId" />
		</form>
	</section>
</body>
</html>