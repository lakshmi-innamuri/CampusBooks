<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.*"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="com.emich.edu.User"%>
<html>
<head>
<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
<title>Update Personal Details Page</title>
<link href="<%=request.getContextPath()%>/css/campusbooks.css"
	type="text/css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/script/campusbookscript.js"></script>

</head>
<%@ include file="/jsps/header.jsp"%>
<body>
	<div class="mainBodyContent">
		<form id="registrationForm"
			action="<%=request.getContextPath()%>/CampusBooksServlet"
			method="post" onsubmit="return validateRegistrationForm(this)">

			<%
			User userObj  = null;
				session = request.getSession();
				if (null != session) {
					if (null != session.getAttribute("userObject")) {
						 userObj = (User) session.getAttribute("userObject");
					}
				}
			%>
			<div class="registrationPage">
				<div class="textpadding personalDetailsHeader">
					<h2>Update Personal Details</h2>
				</div>
				<div class="textpadding required">
					<div class="alignleft">
						<label>User Name :</label>
					</div>
					<div class="alignright">
						<input type="text" name="username"
							value=<%=userObj.getUsesrName()%> disabled>
					</div>
					<div style="clear: both;"></div>
				</div>
				<div class="textpadding required">
					<div class="alignleft">
						<label>Password :</label>
					</div>
					<div class="alignright">
						<input type="password" name="password"
							value=<%=userObj.getPassword()%>
							title="Password must contain at least 6 characters, including UPPER/lowercase and numbers.">
					</div>
					<div style="clear: both;"></div>
				</div>
				<div class="textpadding required">
					<div class="alignleft">
						<label>First Name :</label>
					</div>
					<div class="alignright">
						<input type="text" name="Fname" value=<%=userObj.getFirstName()%>
							title="First Name must contain letters only.">
					</div>
					<div style="clear: both;"></div>
				</div>
				<div class="textpadding required">
					<div class="alignleft">
						<label>Last Name :</label>
					</div>
					<div class="alignright">
						<input type="text" name="Lname" value=<%=userObj.getLastName()%>
							title="Last Name must contain letters only.">
					</div>
					<div style="clear: both;"></div>
				</div>
				<div class="required">
					<div class="textpadding required">
						<div class="alignleft">
							<label>EID :</label>
						</div>
						<div class="alignright">
							E: <input type="text" name="eid" title="EID"
								value=<%=userObj.getEid()%>
								title="EID must contain 8 digits only.">
						</div>
						<div style="clear: both;"></div>
					</div>
				</div>
				<div class="textpadding required" style="width: 391px;">
					<div class="alignleft">
						<label>Email Address :</label>
					</div>
					<div class="alignright">
						<input type="text" name="emailAddress"
							value=<%=userObj.getEmailAddress()%>
							title="Email Address can contain letters, digits, underscore,  dot and -.">@emich.edu
					</div>
					<div style="clear: both;"></div>
				</div>
				<div class="textpadding required">
					<div class="alignleft">
						<label>Phone Number :</label>
					</div>
					<div class="alignright">
						<input type="text" name="phoneNumber"
							value=<%=userObj.getPhoneNumber()%>
							title="Phone number can contain 10 digits">
					</div>
					<div style="clear: both;"></div>
				</div>
				<div class="textpadding updatebuttonAlignment">
					<input class="button" type="submit" value="Update" name="update" />
					<input type="hidden" value="fromEditPersonalDetails"
						name="updatePersonalDetails" />
				</div>
			</div>
		</form>
	</div>
</body>
</html>