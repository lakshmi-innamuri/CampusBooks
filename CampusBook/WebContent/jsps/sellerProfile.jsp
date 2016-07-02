<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="java.util.*"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="com.emich.edu.*"%>

<html>
<head>
<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
<title>Seller Profile</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"> </script>
	<script>
	
	$(document).ready(function (){
$("#contactButton").click(function(event) {
	event.preventDefault();
	/* $('div.sellerProfileBodyContent').css({"width":"400px"}); */
	$('div.mainBodyContent').css({"padding-left": "60px"});
	$('div#sellerProfile').css({"margin":"0px"});
	
	
	 $("#contactForm").load("jsps/contactSeller.jsp"); 
	});
	});
 

</script>
</head>
<%@ include file="/jsps/header.jsp"%>
<body>
	<div class="mainBodyContent">
		<form id="contactSeller"
			action="<%=request.getContextPath()%>/CampusBooksServlet"
			method="post">

			<%
			CampusBookHelper controller = new CampusBookHelper();
			boolean emailSent = false;
			boolean showBackButton = true;
		/* 	User seller = new User();
			seller.setUsesrName(request.getParameter("sellerName"));
			seller = controller.getUserPersonalDetails(seller);
			
			seller.setUserId(Integer.parseInt(request.getParameter("sellerId")));
			 */
			 
			 if(null != session.getAttribute("fromTopSellersToSellerProfile") && (Boolean) session.getAttribute("fromTopSellersToSellerProfile")){
				 showBackButton = false;
				 session.removeAttribute("fromTopSellersToSellerProfile");
			 }
			 if(null != session.getAttribute("fromContactedToSellerProfile") && (Boolean) session.getAttribute("fromContactedToSellerProfile")){
				 showBackButton = false;
				 session.removeAttribute("fromContactedToSellerProfile");
			 }
			 if(null != session.getAttribute("emailSent") && "yes".equalsIgnoreCase((String) session.getAttribute("emailSent"))){
				 emailSent = true;
				 session.removeAttribute("emailSent");
			 }
			 
			User seller = (User)session.getAttribute("seller");
			 
			ArrayList<String> comments = (ArrayList<String>)session.getAttribute("comments");
			//comments = controller.getComments(seller);
			
			
			//put seller in session
			session.setAttribute("seller",seller);
			%>
			<div id="sellerProfile" class="registrationPage">

			<%
			if(emailSent)
				out.println("<div class='textpadding'>Email Sent Sucessfully..!! </div>");
			%>
			
			<div class="textpadding">
					<div class="alignleft">
						<label><u>Seller Information</u></label>
					</div>
					<div style="clear: both;"></div>
			</div>
			<div class="textpadding">
					<div class="alignleft">
						<label>Seller Name :</label>
					</div>
					<div class="alignright">
						<%=seller.getFirstName()%> <%=seller.getLastName() %>
					</div>
					<div style="clear: both;"></div>
			</div>
			<div class="textpadding">
					<div class="alignleft">
						<label>Rating :</label>
					</div>
					<div class="alignright">
						<%=controller.getRating(seller) %>
					</div>
					<div style="clear: both;"></div>
			</div>
			<div class="textpadding">
					<div class="alignleft">
						<label>Comments :</label>
					</div>
					<div class="alignright">
						<%
				
				for(String comment: comments){
					out.println("<div class='comment'> <textarea disabled> "+comment +"</textarea> </div>");
				}
				
				%>
					</div>
					<div style="clear: both;"></div>
			</div>
			<div class="textpadding">
					<div class="topSpacing">
						<button  id="contactButton" class="button" name="contactSeller">Contact </button> 
						
			</div>
			<% if(showBackButton){ %>
			<div class="textpadding buttonAlignment">	
				<input class="button" type="submit" value="Back to Search Results" id="backToSearch" name="contactSeller">
			</div>
			<% } %>
					<div style="clear: both;"></div>
			</div>
			</div>
		</form>
		<div id="contactForm"> </div>
		
	
	</div>
	
	


</body>
</html>