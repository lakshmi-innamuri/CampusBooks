<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isErrorPage="true"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration Page</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/campusbooks.css">
<script src="<%=request.getContextPath()%>/script/campusbookscript.js"></script>
</head>
<body class="registrationContent frontPage">
	<%!HttpSession session;
	String regErrorMessage;%>
	<form id="registrationForm"
		action="<%=request.getContextPath()%>/CampusBooksServlet"
		method="post" onsubmit="return validateRegistrationForm(this)">
		<%
				session = request.getSession();
				if (null != session.getAttribute("errorMessage")) {
					regErrorMessage = (String) session.getAttribute("errorMessage");
				}
			%>
		<div class="regWidth">
			<div class="textpadding regTitle">
				<h2>Registration Page</h2>
			</div>
			<div id="errorMsg"> </div>
			<c:choose>
				<c:when test="${regErrorMessage == true}">
					<div id="errorMsg" class="error">
						<h3>User name is already taken, Please choose a different user name !</h3>
					</div>
				</c:when>
			</c:choose>
			<div class="textpadding required">
				<div class="alignleftReg">
					<label>User Name :</label>
				</div>
				<div class="alignrightReg">
					<input type="text" placeholder="User Name" name="username"
						title="Username must not be blank and contain only letters, numbers and underscores.">
				</div>
				<div class="alignrightReg" id="errorMsgUsername">
					
				</div>
				<div style="clear: both;"></div>
			</div>
			<div class="textpadding required">
				<div class="alignleftReg">
					<label>Password :</label>
				</div>
				<div class="alignrightReg">
					<input type="password" name="password" placeholder="Password"
						title="Password must contain at least 6 characters, including UPPER/lowercase and numbers.">
				</div>
				<div class="alignrightReg" id="errorMsgPassword">
					
				</div>
				<div style="clear: both;"></div>
			</div>
			<div class="textpadding required">
				<div class="alignleftReg">
					<label>First Name :</label>
				</div>
				<div class="alignrightReg">
					<input type="text" name="Fname" placeholder="First Name"
						title="First Name must contain letters only.">
				</div>
				<div style="clear: both;"></div>
			</div>
			<div class="textpadding required">
				<div class="alignleftReg">
					<label>Last Name :</label>
				</div>
				<div class="alignrightReg">
					<input type="text" name="Lname" placeholder="Last Name"
						title="Last Name must contain letters only.">
				</div>
				<div style="clear: both;"></div>
			</div>
			<div class="textpadding required">
				<div class="alignleftReg">
					<label>EID :</label>
				</div>
				<div class="alignrightReg"  style="padding-left: 3px;">
					E: <input type="text" name="eid" placeholder="EID"
						title="EID must contain 8 digits only.">
				</div>
				<div style="clear: both;"></div>
			</div>
			<div class="textpadding required">
				<div class="alignleftReg">
					<label>Email Address :</label>
				</div>
				<div class="alignrightReg">
					<input type="text" name="emailAddress" placeholder="Email Address"
						title="Email Address can contain letters, digits, underscore,  dot and -.">@emich.edu
				</div>
				<div style="clear: both;"></div>
			</div>
			<div class="textpadding required">
				<div class="alignleftReg">
					<label>Phone Number :</label>
				</div>
				<div class="alignrightReg">
					<input type="text" name="phoneNumber" placeholder="Phone Number" title="Phone number can contain 10 digits">
				</div>
				<div style="clear: both;"></div>
			</div>
				<div class="textpadding buttonAlignment"><button class="button" name="register" type="submit" value="register">Register</button>
				<span><a href="<%=request.getContextPath()%>/CampusBooksServlet?cancel=yes" ><button class="button" name="cancel"  type="button" value="cancel">Cancel</button></a>
				<input type="hidden" value="fromRegistrationPage"
					name="registration" /></span></div>
			</div>
		</div>
	</form>
</body>
</html>