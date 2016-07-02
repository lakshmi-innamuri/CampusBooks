<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="com.emich.edu.User"%>
<%@ page import="com.emich.edu.Book"%>
<%@ page import="java.util.Map.Entry"%>
<!DOCTYPE html>
<html>
<head>
<title>Search Results Page</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/campusbooks.css">
<script src="<%=request.getContextPath()%>/script/campusbookscript.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"> </script>
<script>
function AddComment(userId){
	document.getElementById('selected_userId').value = userId;
	document.getElementById('selected_comment').value =document.getElementById('comment'+userId).value  ;
	document.getElementById('feedback_type').value ="comment"  ;
	document.getElementById("contact").submit();
}	
function AddRating(userId){
	document.getElementById('selected_userId').value = userId;
	document.getElementById('selected_rating').value =document.getElementById('rating'+userId).value  ;
	document.getElementById('feedback_type').value ="rating"  ;
	document.getElementById("contact").submit();
}


	$(document).ready(function(){
		$("#homeForm").load("<%=request.getContextPath()%>/jsps/homeLeftNavigation.jsp");
					});
	

		
</script>

</head>
<%@ include file="/jsps/header.jsp"%>
<body>
	<%
		ArrayList<User> sellersList = new ArrayList<User>();

			session = request.getSession();
			if(null != session.getAttribute("sellerList")){
				sellersList = (ArrayList<User>) session.getAttribute("sellerList");
			}
	%>


	<div id="allContent">
		<aside id="homeForm"></aside>

		<section id="contactedSellersList">

		<%
			if (null != session.getAttribute("feedBackResponse")){
				out.println("<div id='feedBackMessage'>"+session.getAttribute("feedBackResponse")+"</div>");
				session.removeAttribute("feedBackResponse");
			}
		%>
			<form id="contact"
				action="<%=request.getContextPath()%>/CampusBooksServlet"
				method="post">
				<input type="hidden" id="addComment" name="addComment"
					value="addComment" /> <input type="hidden" id="selected_userId"
					name="selected_userId" /> <input type="hidden"
					id="selected_comment" name="selected_comment" /> <input
					type="hidden" id="selected_rating" name="selected_rating" /> <input
					type="hidden" id="feedback_type" name="feedback_type" />
				<div id="historySellerScrollDiv">



					<table class="topSeller">
						<%
									if(!sellersList.isEmpty()){	
											out.println("<tr><td><b><font size='5'>Contacted Sellers</font></b></td></tr>");
											
											
										for (User seller : sellersList) {
												out.println("<tr><td><a href='/CampusBook/CampusBooksServlet?viewSeller=yes&sellerName="+seller.getUsesrName()+"&sellerId="+seller.getUserId()+ "'> "+seller.getLastName()+"   "+seller.getFirstName()+"<a></td></tr>");
												out.println("<tr><td><select id='rating"+seller.getUserId()+"'><option value=1>1</option><option value=2>2</option><option value=3>3</option> <option value=4>4</option><option value=5>5</option></select></td></tr>");						
												out.println("<tr><td><button class='button' type='button' id='" +seller.getUserId()+ "' onclick = 'AddRating( "+seller.getUserId()+ ")'>Add Rating </button></td></tr>");
												out.println("<tr><td><div class='comment'><textarea rows='4' id='comment"+seller.getUserId()+"' cols='50'></textarea> </div></td></tr>");
												out.println("<tr><td><button class='button' type='button' id='" +seller.getUserId()+ "' onclick = 'AddComment( "+seller.getUserId()+ ")'>Add Comment </button></td></tr>");
												
										}
										}else{
											out.println("You have not contacted any sellers till now");
										}
								%>
					</table>
				</div>
			</form>

		</section>
	</div>
</body>
</html>