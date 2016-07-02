<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page language="java" import="java.util.*"%>
<%@ page import="com.emich.edu.Book"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Update Book Page</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/campusbooks.css">
<script src="<%=request.getContextPath()%>/script/campusbookscript.js"></script>
<%
	ResourceBundle states = ResourceBundle.getBundle("deptcodes");
	Enumeration stateKeys = states.getKeys();
%>
</head>
<%@ include file="/jsps/header.jsp"%>
<body>
<div class="mainBodyContent">
	<form id="loginForm" action="<%=request.getContextPath()%>/CampusBooksServlet" method="post"
		onsubmit="return validateBookDetails(this);">
		<%
			session = request.getSession();
			Book bookObj = null;
			if (null != session.getAttribute("bookObject")) {
				bookObj = (Book) session.getAttribute("bookObject");
			}
		%>
		<div class="registrationPage">
			<div class="textpadding">
				<h2>Update Book Form</h2>
			</div>
			<div class="textpadding required">
				<div class="alignleft">
					<label>Book Title :</label>
				</div>
				<div class="alignright">
					<input type="text" name="bookname"
						value="<%=bookObj.getBookTitle()%>"
						title="Book Title must not be blank and contain only letters">
				</div>
				<div style="clear: both;"></div>
			</div>
			<div class="textpadding required">
				<div class="alignleft">
					<label>ISBN:</label>
				</div>
				<div class="alignright">
					<input type="text" name="isbn" title="ISBN must not be blank"
						value="<%=bookObj.getIsbn()%>">
				</div>
				<div style="clear: both;"></div>
			</div>
			<div class="textpadding required">
				<div class="alignleft">
					<label>Author :</label>
				</div>
				<div class="alignright">
					<input type="text" name="author"
						title="Author must not be blank and contain only letters"
						value="<%=bookObj.getAuthor()%>">
				</div>
				<div style="clear: both;"></div>
			</div>
			<div class="textpadding required">
				<div class="alignleft">
					<label>Department Code:</label>
				</div>
				<div class="alignright">
					<input type="hidden" value="<%=bookObj.getDepartmentCode()%>" /> <select
						name="deptid" title="Please select department code">

						<option value=""></option>

						<%
							while (stateKeys.hasMoreElements()) {
								String key = (String) stateKeys.nextElement();
								String value = states.getString(key);
						%>
						<option
							<%=(states.getString(key).equalsIgnoreCase(
						bookObj.getDepartmentCode()) ? "selected='selected'"
						: "")%>>
							<%=states.getString(key)%>
						</option>
						<%
							}
						%>
					</select>
				</div>
				<div style="clear: both;"></div>
			</div>
			<div class="textpadding required">
				<div class="alignleft">
					<label>Course Name:</label>
				</div>
				<div class="alignright">
					<input type="text" name="coursename"
						value="<%=bookObj.getCourseName()%>"
						title="Course Name must not be blank and contain only letters">
				</div>
				<div style="clear: both;"></div>
			</div>
			<div class="textpadding required">
				<div class="alignleft">
					<label>Course Number:</label>
				</div>
				<div class="alignright">
					<input type="text" name="coursenumber"
						value="<%=bookObj.getCourseNumber()%>"
						title="Course Number must not be blank and contain only numbers">
				</div>
				<div style="clear: both;"></div>
			</div>
			<div class="textpadding">
				<div class="alignleft">
					<label>Price :</label>
				</div>
				<div class="alignright">
					<input type="text" name="price" title="Price"
						value="<%=bookObj.getPrice()%>">
				</div>
				<div style="clear: both;"></div>
			</div>
			<div class="textpadding required">
				<div class="alignleft">
					<label>Book Status :</label>
				</div>
				<div class="alignright">
					<input type="hidden" value=<%=bookObj.getBookStatus()%> /> <select
						id="bookstatus" name="bookstatus" title="Please select book status">
						<option value=<%=bookObj.getBookStatus()%>></option>
						<option value="avail"
							<%=("avail".equalsIgnoreCase(bookObj.getBookStatus()) ? "selected='selected'"
					: "")%>>Available</option>
						<option value="not avail"
							<%=("not avail".equalsIgnoreCase(bookObj.getBookStatus()) ? "selected='selected'"
					: "")%>>Not
							Available</option>
							<option value="Sold"
							<%=("Sold".equalsIgnoreCase(bookObj.getBookStatus()) ? "selected='selected'"
					: "")%>>Sold
							</option>
					</select>
				</div>
				<div style="clear: both;"></div>
				<input type="hidden"  name="bookId" value="<%=bookObj.getBookId() %>" />
			</div>
			<div class="textpadding buttonAlignment">
				<input type="submit" class="button" value="Update Book" name="updatebook" /> 
				<span><a href="<%=request.getContextPath()%>/jsps/homeRightNavigation.jsp" ><button class="button" name="cancel"  type="button" value="cancel">Cancel</button></a>
				<input
					type="hidden" value="fromEditBook"
					name="fromEditBook" />
			</div>
		</div>
	</form>
	</div>
</body>
</html>