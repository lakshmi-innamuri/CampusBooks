<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="java.util.*"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="com.emich.edu.*"%>

<html>
<head>
<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
<title>Seller Profile</title>
<link href="<%=request.getContextPath()%>/css/campusbooks.css"
	type="text/css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/script/campusbookscript.js"></script>
</head>
<%@ include file="/jsps/header.jsp"%>
<body>
	<%
		ArrayList<User> topSellers = new ArrayList<User>();

		session = request.getSession();
		topSellers = (ArrayList<User>) session.getAttribute("topSellers");
	%>

	<div class="sellerTable mainBodyContent">
	
					<div style="padding-top:30px;margin-left:-580px;"><b><font size="5">Top Sellers</font></b></div>
		<div id="scrollDiv">
			<table class="topSeller">
				

				<%
					for (User seller : topSellers) {

						out.println("<tr><td><a href='" + request.getContextPath()
								+ "/CampusBooksServlet?viewSeller=yes&sellerName="
								+ seller.getUsesrName() + "&sellerId="
								+ seller.getUserId() + "'>" + seller.getUsesrName()
								+ "</a></td></tr>");

					}
				%>
			</table>
		</div>
	</div>
</body>
</html>