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
	$(document).ready(function(){
		$("#homeForm").load("<%=request.getContextPath()%>/jsps/homeLeftNavigation.jsp");
					});
</script>

</head>
<%@ include file="/jsps/header.jsp"%>
<body>
	<%!ArrayList<Book> booksList;%>


	<div id="allContent">
		<aside id="homeForm"></aside>



		<section id="adminHomeSection">
			<div id="homeResults">
				<div id="historyScrollDiv">
					<table>

						<%
							String status;
							session = request.getSession();
							boolean empty=true;
							//Book searchCriteriaObj = (Book) session.getAttribute("searchCriteria");

							//Map<Book, User> mapInSession = new HashMap<Book, User>();
							booksList = (ArrayList<Book>) session.getAttribute("booksList");

							//	Map<String, String> map = new HashMap<String, String>();
							//	mapInSession = (Map<Book, User>) session.getAttribute("searchResults");

							System.out.println("map is empty: " + booksList.isEmpty());

							if (!booksList.isEmpty()) {
								out.println("<div style='padding-left:14px;'><b><font size='5'>Books Available</font></b></div>");
								for (Book book : booksList) {
									
									status = book.getBookStatus();
									 request.setAttribute("status", status);
						%>
						<c:choose>
							<c:when test="${status eq 'avail'}">
							<%empty = false;%>
								<tr>
									<td><a
										href='<%=request.getContextPath()%>/CampusBooksServlet?edit=edit&bookId=<%=book.getBookId()%>'>
											<%=book.getBookTitle()%></a>
								</tr>
							</c:when>
						</c:choose>
						<%
							}
								if(empty){
									out.println("<div style='padding-left:14px;'> No Books Available!</div>");
									empty = true;
								}
							
							%>
								</table>
						</div>
						<div id="historyScrollDiv">
								<table>
							<%
							out.println("<div style='padding-left:20px;'><b><font size='5'>Books Not Available(Sold)</font></b></div>");
								for (Book book : booksList) {
									
									status = book.getBookStatus();
									 request.setAttribute("status", status);
						%>
					
					
						<c:choose>
							<c:when test="${status eq 'sold' || status eq 'not avail' }">
							<%empty = false;%>
								<tr>
									<td><a
										href='<%=request.getContextPath()%>/CampusBooksServlet?edit=edit&bookId=<%=book.getBookId()%>'>
											<%=book.getBookTitle()%></a>
								</tr>
							</c:when>
						</c:choose>
						<%
							}
								if(empty)
									out.println("<div style='padding-left:20px;'> No Books are sold yet </div>");
									
							} else {
								out.println("Sorry, No Books added by you");
							}
						%>

					</table>
				</div>
			</div>
		</section>
	</div>
</body>
</html>