<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isErrorPage="true"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Login Page</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/campusbooks.css">
<script src="<%=request.getContextPath()%>/script/campusbookscript.js"></script>
</head>
<body  class="loginpageAlignment">
	<%!HttpSession session;
	String errorMessage;%>

	<div class="registrationPage">
		<h2>User Login</h2>
		<form id="loginForm"
			action="<%=request.getContextPath()%>/CampusBooksServlet"
			method="post" onsubmit="return checkLoginForm(this);">
			<%
				session = request.getSession();
				if (null != session.getAttribute("errorMessage")) {
					errorMessage = (String) session.getAttribute("errorMessage");
					//session.removeAttribute("errorMessage");
				}
			%>
<% if(null != session.getAttribute("fromLogin") && "yes".equalsIgnoreCase((String)session.getAttribute("fromLogin"))){
	session.removeAttribute("fromLogin");
	%>
				<%-- <c:choose>
				<c:when test="${errorMessage == true}"> --%>
					<div id="errorMsg" class="error">
						<h3>Error: Invalid credentials !</h3>
					</div>
				<%-- </c:when>
			</c:choose> --%>
	<%
}	
	%>



			
			<div class="textpadding required">
				<div class="alignleft">
					<label>User Name :</label>
				</div>
				<div class="alignright">
					<input type="text" placeholder="User Name" title="User Name"
						type="text" name="username">
				</div>
				<!--<div class="alignright"><input id="field_username" type="text" title="Username must not be blank and contain only letters, numbers and underscores." type="text" required pattern="\w+" name="username"></div>-->
				<div style="clear: both;"></div>
			</div>
			<div class="textpadding required">
				<div class="alignleft">
					<label>Password :</label>
				</div>
				<div class="alignright">
					<input type="password" placeholder="Password" title="Password"
						type="password" name="password">
				</div>
				<!--<div class="alignright"><input type="password" id="field_pwd"  title="Password must contain at least 6 characters, including UPPER/lowercase and numbers." type="password" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" name="password"></div>-->
				<div style="clear: both;"></div>
			</div>
			<div class="textpadding buttonAlignment">
				<button class="button" name="signin" type="submit">Sign In</button>
				<input type="hidden" value="fromLoginPage" name="login" />
			</div>
			<div class="textpadding buttonAlignment">
				<a href="<%=request.getContextPath()%>/jsps/registrationPage.jsp">Register Now !</a>
			</div>
		</form>
	</div>
</body>
</html>



