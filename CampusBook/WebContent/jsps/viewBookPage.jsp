<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page language="java" import="java.util.*"%>
<%@ page import="com.emich.edu.Book"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>View Book</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/campusbooks.css">
<script src="<%=request.getContextPath()%>/script/campusbookscript.js"></script>
<%
	ResourceBundle states = ResourceBundle.getBundle("deptcodes");
	Enumeration stateKeys = states.getKeys();
%>
</head>
<%@ include file="/jsps/header.jsp"%>
<body class="">
<div class=" mainBodyContent">
<div class="viewbook">
	<form id="" action="<%=request.getContextPath()%>/CampusBooksServlet" method="post">
		<%
			session = request.getSession();
			Book bookObj = null;
			if (null != session.getAttribute("bookObject")) {
				bookObj = (Book) session.getAttribute("bookObject");
			}
		%>
		
			<div class="textpadding">
				<h2>Book Details </h2>
			</div>
			<div class="textpadding">
				<div class="alignleft">
					<label>Book Title :</label>
				</div>
				<div class="alignright">
					<%=bookObj.getBookTitle()%>
				</div>
				<div style="clear: both;"></div>
			</div>
			<div class="textpadding">
				<div class="alignleft">
					<label>ISBN:</label>
				</div>
				<div class="alignright">
					<%= bookObj.getIsbn() %>
				</div>
				<div style="clear: both;"></div>
			</div>
			<div class="textpadding">
				<div class="alignleft">
					<label>Author :</label>
				</div>
				<div class="alignright">
					<%=bookObj.getAuthor()%>
				</div>
				<div style="clear: both;"></div>
			</div>
			<div class="textpadding">
				<div class="alignleft">
					<label>Department Code:</label>
				</div>
				<div class="alignright">
					<%=bookObj.getDepartmentCode()%>
				</div>
				<div style="clear: both;"></div>
			</div>
			<div class="textpadding">
				<div class="alignleft">
					<label>Course Name:</label>
				</div>
				<div class="alignright">
					<%=bookObj.getCourseName()%>
				</div>
				<div style="clear: both;"></div>
			</div>
			<div class="textpadding">
				<div class="alignleft">
					<label>Course Number:</label>
				</div>
				<div class="alignright">
					<%=bookObj.getCourseNumber()%>
				</div>
				<div style="clear: both;"></div>
			</div>
			<div class="textpadding">
				<div class="alignleft">
					<label>Price :</label>
				</div>
				<div class="alignright">
					<%=bookObj.getPrice()%>
				</div>
				<div style="clear: both;"></div>
			</div>
			<div class="textpadding">
				<input type="hidden"  name="bookId" value=<%=bookObj.getBookId() %> />
			</div>
			<div class="textpadding buttonAlignment">
				<span><a href="<%=request.getContextPath()%>/jsps/searchResults.jsp" ><button class="button" name="cancel"  type="button" value="cancel">Cancel</button></a>
				</span></div>
	
	</form>
	</div>
	</div>
</body>
</html>