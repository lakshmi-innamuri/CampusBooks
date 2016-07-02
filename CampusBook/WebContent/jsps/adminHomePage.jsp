<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page import="java.util.*"%>
<%@ page import="com.emich.edu.User"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Campus Books Home Page</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/campusbooks.css">
<script src="<%=request.getContextPath()%>/script/campusbookscript.js"></script>
<%
	ResourceBundle states = ResourceBundle.getBundle("deptcodes");
	Enumeration stateKeys = states.getKeys();
	session = request.getSession();
	User userCriteriaObj = new User();
	User searchResult = new User();
	if (session.getAttribute("adminSearchCriteria") != null) {
		userCriteriaObj = (User) session
				.getAttribute("adminSearchCriteria");
	}
	
	if (session.getAttribute("adminSearchResult") != null) {
		searchResult = (User) session
				.getAttribute("adminSearchResult");
	}
%>
</head>
<body>

	<%!HttpSession session;
	String userName;
	String adminMsg = null;
	int userId;%>
	<%
		session = request.getSession();
		userName = (String) session.getAttribute("userName");
		userId = ((Integer) session.getAttribute("userId")).intValue();
		ArrayList<User> registeredUsers = new ArrayList<User>();

		session = request.getSession();
		registeredUsers = (ArrayList<User>) session
				.getAttribute("registeredUsers");

		if (null != session.getAttribute("adminMsg")) {
			adminMsg = (String) session.getAttribute("adminMsg");
		}
	%>
	<div class="headerContent">

		<form id="headerForm"
			action="<%=request.getContextPath()%>/CampusBooksServlet"
			method="post">
			<input type="hidden" value="fromHeaderPage" name="header" /> <input
				type="hidden" value="<%=userId%>" name="userId" />
			<div id="title">
				<h1>Campus Books</h1>
			</div>
			<div class="">
				<img class="bookImg"
					src="<%=request.getContextPath()%>/images/books.png"
					alt="Campus Book">
			</div>
			<div id="userActions">
				<span><button type="submit" class="button" name="logout"
						value="logout">Logout</button></span>
			</div>


		</form>
	</div>
	<div id="admin" class="adminmainBodyContent">
		<div class="registrationPage" align="center">
		
			<div class="textpadding">
				<h2>List of Users</h2>
			</div>
			<div id="adminScrollDiv">
			<div>
				<c:choose>
					<c:when test="${adminMsg != null}">
						<div id="errorMsg" class="error">
							<h3><%=adminMsg %></h3>
						</div>
					</c:when>
				</c:choose>

				<%
					for (User user : registeredUsers) {
				%>


				<div>
					<div class="alignleft" style="padding-top: 11px;">
						<%=user.getUsesrName()%>
					</div>
					<div class="alignright" style="padding-top: 11px;">
						<a
							href="<%=request.getContextPath()%>/CampusBooksServlet?fromAdminPage=fromAdminPage&userName=<%=user.getUsesrName()%>&userId=<%=user.getUserId()%> "><input
							class="button" type="submit" value="Deactivate" name="Delete" />
						</a>
					</div>
					<div style="clear: both;"></div>
				</div>
				<%
					}
				%>
			</div>
			</div>
			<form action="<%=request.getContextPath()%>/CampusBooksServlet"
			method="post">
			<div class="textpadding">
				<h2>Search User</h2>
			</div>
			<div class="textpadding ">
				<div class="alignleft">
					<label>User Name :</label>
				</div>
				<div class="alignright">
					<input type="text" name="searchUserName"
						value="<%=userCriteriaObj.getUsesrName()%>" />
				</div>
				<div style="clear: both;"></div>
			</div>
			<div class="textpadding ">
				<div class="alignleft">
					<label>Email Address :</label>
				</div>
				<div class="alignright">
					<input type="text" name="searchEmailAddress"
						value="<%=userCriteriaObj.getEmailAddress()%>" />
				</div>
				<div style="clear: both;"></div>
			</div>
			<div class="textpadding buttonAlignment">
				<input class="button" type="submit" value="Search" name="userSearch" />
				<input type="hidden" value="fromUserSearch" name="fromUserSearch" />
			</div>
			</form>
<%
if ((User)session.getAttribute("adminSearchResult") != null) {
	if (session.getAttribute("adminResults") != null && (Boolean)session.getAttribute("adminResults") != false) {
%>			

			
			<div class="textpadding">
				<h2>Searched User</h2>
			</div>
			
			<div>
					<div class="alignleft" style="padding-top: 11px;">
						<%=searchResult.getUsesrName()%>
					</div>
					<div class="alignright" style="padding-top: 11px;">
						<a
							href="<%=request.getContextPath()%>/CampusBooksServlet?fromAdminPage=fromAdminPage&userName=<%=searchResult.getUsesrName()%>&userId=<%=searchResult.getUserId()%> "><input
							class="button" type="submit" value="Deactivate" name="Delete" />
						</a>
					</div>
					<div style="clear: both;"></div>
				</div>
<%} 
}else if( session.getAttribute("adminSearchCriteria") != null){

	out.println("<div class='textpadding'> <h2>No results match your search</h2> </div>");
}


%>
		</div>
		</div>
	
</body>
</html>