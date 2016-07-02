<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="java.util.*"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="com.emich.edu.*"%>

<html>
<head>
<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
<title>View Top Books</title>
<link href="<%=request.getContextPath()%>/css/campusbooks.css"
	type="text/css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/script/campusbookscript.js"></script>
</head>
<%@ include file="/jsps/header.jsp"%>
<body>
	<%
		ArrayList<Book> topBooks = new ArrayList<Book>();

		session = request.getSession();
		topBooks = (ArrayList<Book>) session.getAttribute("topBooks");
	%>

	<div class="sellerTable mainBodyContent">
	<div style="padding-top:30px;margin-left:-580px;"><b><font size="5">Top Books</font></b></div>
		<div id="scrollDiv">
			<table class="topSeller">


				<%
					for (Book book : topBooks) {

						out.println("<tr><td>" + book.getBookTitle() + "</td></tr>");

					}
				%>
			</table>

		</div>
	</div>
</body>
</html>