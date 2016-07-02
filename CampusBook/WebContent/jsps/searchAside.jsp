<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page language="java" import="java.util.*"%>
<%@ page import="com.emich.edu.User"%>
<%@ page import="com.emich.edu.Book"%>
<!DOCTYPE html>
<html>
<head>
<title>Search Book Page</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/campusbooks.css">
<script src="<%=request.getContextPath()%>/script/campusbookscript.js"></script>
<%
	ResourceBundle states = ResourceBundle.getBundle("deptcodes");
	Enumeration<String> stateKeys = states.getKeys();
	session = request.getSession();
	Book searchCriteriaObj = new Book();
	if (session.getAttribute("searchCriteria") != null) {
		searchCriteriaObj = (Book) session
				.getAttribute("searchCriteria");
	}
%>
</head>

<body>

	<section>

		<form id="loginForm"
			action="<%=request.getContextPath()%>/CampusBooksServlet"
			method="post">
				<div class="registrationPage" align="center">
					<div class="textpadding">
						<h2>Search </h2>
					</div>
					<div class="textpadding ">
						<div class="alignleft">
							<label>Book Title :</label>
						</div>
						<div class="alignright">
							<input type="text" name="searchTitle"
								value="<%=searchCriteriaObj.getBookTitle()%>" />
						</div>
						<div style="clear: both;"></div>
					</div>
					<div class="textpadding ">
						<div class="alignleft">
							<label>Author :</label>
						</div>
						<div class="alignright">
							<input type="text" name="searchAuthor"
								value="<%=searchCriteriaObj.getAuthor()%>" />
						</div>
						<div style="clear: both;"></div>
					</div>
					<div class="textpadding ">
						<div class="alignleft">
							<label>ISBN :</label>
						</div>
						<div class="alignright">
							<input type="text" name="searchISBN"
								value="<%=searchCriteriaObj.getIsbn()%>" />
						</div>
						<div style="clear: both;"></div>
					</div>
					<div class="textpadding ">
						<div class="alignleft">
							<label>Department :</label>
						</div>
						<div class="alignright">
							<select name="searchDept" title="Please select department code" style="width:143px;">
								<option value=" "></option>

								<%
									while (stateKeys.hasMoreElements()) {
										String key = (String) stateKeys.nextElement();
										String value = states.getString(key);
								%>
								
								<option
							<%=(states.getString(key).equalsIgnoreCase(
									searchCriteriaObj.getDepartmentCode()) ? "selected='selected'"
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
					<div class="textpadding ">
						<div class="alignleft">
							<label>Course Number :</label>
						</div>
						<div class="alignright">
							<input type="text" name="searchCourseNumber"
								value="<%=searchCriteriaObj.getCourseNumber()%>" />
						</div>
						<div style="clear: both;"></div>
					</div>
					<div class="textpadding ">
						<div class="alignleft">
							<label>Course Name :</label>
						</div>
						<div class="alignright">
							<input type="text" name="searchCourseName"
								value="<%=searchCriteriaObj.getCourseName()%>" />
						</div>
						<div style="clear: both;"></div>
					</div>

					<div class="textpadding buttonAlignment">
						<input class="button" type="submit" value="Search" name="search" /> <input
							type="hidden" value="fromSearch" name="searchPage" />
					</div>
				</div>
		</form>
	</section>
	<div id="searchResults"></div>
</body>
</html>