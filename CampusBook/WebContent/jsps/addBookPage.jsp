<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page language="java" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Add Book Page</title>
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
		
		<form id="loginForm"
			action="<%=request.getContextPath()%>/CampusBooksServlet"
			method="post" onsubmit="return validateBookDetails(this);">
			<div class="regWidth">
				<div class="textpadding ">
				<div class="alignleftReg titlePadding">
					<h3>Post Book</h3>
				</div>
				</div>
				
				<div class=" ">
						<div id="errorMsg" class=" errorleftReg " > </div>
				</div>
				<div style="clear: both;"></div>
				
				
				<div class="textpadding required">
					<div class="alignleftReg">
						<label>Book Title :</label>
					</div>
					<div class="alignrightReg">
						<input type="text" name="bookname"
							title="Book Title must not be blank and contain only letters">
					</div>
					<div style="clear: both;"></div>
				</div>
				<div class="textpadding required">
					<div class="alignleftReg">
						<label>ISBN:</label>
					</div>
					<div class="alignrightReg">
						<input type="text" name="isbn" title="ISBN must not be blank">
					</div>
					<div style="clear: both;"></div>
				</div>
				<div class="textpadding required">
					<div class="alignleftReg">
						<label>Author :</label>
					</div>
					<div class="alignrightReg">
						<input type="text" name="author"
							title="Author must not be blank and contain only letters">
					</div>
					<div style="clear: both;"></div>
				</div>
				<div class="textpadding required">
					<div class="alignleftReg">
						<label>Department Code:</label>
					</div>
					<div class="alignrightReg">

						<select name="deptid" title="Please select department code">
							<option value=" "></option>

							<%
								while (stateKeys.hasMoreElements()) {
									String key = (String) stateKeys.nextElement();
									String value = states.getString(key);
							%>

							<option value="<%=states.getString(key)%>">
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
					<div class="alignleftReg">
						<label>Course Name:</label>
					</div>
					<div class="alignrightReg">
						<input type="text" name="coursename"
							title="Course Name must not be blank and contain only letters">
					</div>
					<div style="clear: both;"></div>
				</div>
				<div class="textpadding required">
					<div class="alignleftReg">
						<label>Course Number:</label>
					</div>
					<div class="alignrightReg">
						<input type="text" name="coursenumber"
							title="Course Number must not be blank and contain only numbers">
					</div>
					<div style="clear: both;"></div>
				</div>
				<div class="textpadding required">
					<div class="alignleftReg">
						<label>Price :</label>
					</div>
					<div class="alignrightReg">
						<input type="text" name="price" title="Price">
					</div>
					<div style="clear: both;"></div>
				</div>
				<div class="textpadding buttonAlignment">
					<input class="button" type="submit" value="Post Book" name="addbook" /> <input
						type="hidden" value="fromAddBookPage" name="addbookpage" />
				</div>
			</div>
		</form>
	</div>
</body>
</html>