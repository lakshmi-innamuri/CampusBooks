<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ include file="/jsps/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Search Book Page</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/campusbooks.css">
<script src="<%=request.getContextPath()%>/script/campusbookscript.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"> </script>
<script>

$(document).ready(function(){

	 $("#searchForm").load("searchAside.jsp"); 
	
});

</script>
</head>

<%
	ResourceBundle states = ResourceBundle.getBundle("deptcodes");
	Enumeration<String> stateKeys = states.getKeys();
%>
<body>
<div class="mainBodyContent">
	<section>
		<div id="searchForm"></div>
	</section>
</div>
</body>
</html>