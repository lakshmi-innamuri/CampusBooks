<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isErrorPage="true"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Registration Pending Message</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/campusbooks.css">
<script src="<%=request.getContextPath()%>/script/campusbookscript.js"></script>
</head>
<body class="registrationCompleteAlignment">
	<div>
	
	<!-- servlet call to save the registered user in the database table
	coding pending
	 -->
	<div style="font-style:italic;"><h3>You have successfully registered with Campus Book Web site ! Please click the below link</h3></div>
	<div style="padding-top:20px;"><a href="<%=request.getContextPath()%>/jsps/loginPage.jsp" >Campus Book Site Login Page</a></div>
	</div>
</body>
</html>



