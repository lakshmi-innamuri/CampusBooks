package com.emich.edu;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * servlet for campus books - mediator for all transactions
 * 
 * @authors Poornima Reddy S, Lakshmi N Innamuri, Hyndavi Mandava
 *
 */

public class CampusBooksServlet extends HttpServlet {

	// private static final long serialVersionUID = 1L;
	private String host;
	private String port;
	private String user;
	private String pass;
	CampusBookHelper bookHelper = null;

	public void init() {

		// reads SMTP server setting from web.xml file
		ServletContext context = getServletContext();
		host = context.getInitParameter("host");
		port = context.getInitParameter("port");
		user = "campusbooks2014@gmail.com";
		pass = "ecommerceproject";

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("hello i reached servlet");

		User userObj = null;
		Book addBookObj = null;
		HttpSession session = null;
		bookHelper = new CampusBookHelper();
		/* to handle login form */
		if (null != request.getParameter("login")) {
			if ("fromLoginPage".equalsIgnoreCase(request.getParameter("login"))) {
				userObj = new User();
				session = request.getSession();
				if (null != request.getParameter("username")) {
					userObj.setUsesrName(request.getParameter("username"));
				}
				if (null != request.getParameter("password")) {
					userObj.setPassword(request.getParameter("password"));
				}

				// if valid user forward the user to home page else redirect the
				// user to error page

				if (bookHelper.login(userObj) != 0) {

					session.setAttribute("userId", bookHelper.login(userObj));
					session.setAttribute("errorMessage", "false");
					session.setAttribute("userName", userObj.getUsesrName());
					if ("admin".equalsIgnoreCase(userObj.getUsesrName())) {

						ArrayList<User> registeredUsers = new ArrayList<User>();
						registeredUsers = bookHelper.retrieveRegisteredUsers();
						session.setAttribute("registeredUsers", registeredUsers);
						RequestDispatcher dispatcher = request
								.getRequestDispatcher("/jsps/adminHomePage.jsp");
						dispatcher.forward(request, response);
					} else {
						RequestDispatcher dispatcher = request
								.getRequestDispatcher("/jsps/homePage.jsp");
						dispatcher.forward(request, response);
					}
				} else {
					session.setAttribute("errorMessage", "true");
					session.setAttribute("fromLogin", "yes");
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("/jsps/loginPage.jsp");
					dispatcher.forward(request, response);
				}
			}
		}

		// to handle the onlick event on user name and get the user personal
		// details from backend to be displayed on editPersonalDetailsPage.jsp
		if (null != request.getParameter("header")) {
			if ("fromHeaderPage".equalsIgnoreCase(request
					.getParameter("header"))) {
				if (null != request.getParameter("username")) {
					session = request.getSession();
					userObj = new User();
					userObj.setUsesrName(request.getParameter("username"));
					userObj = bookHelper.getUserPersonalDetails(userObj);

					if (null != session)
						session.setAttribute("userObject", userObj);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("jsps/editPersonalDetailsPage.jsp");
					dispatcher.forward(request, response);
				} else if (null != request.getParameter("logout")) {
					session = request.getSession();
					if (null != session.getAttribute("userObject")) {
						session.removeAttribute("userObject");
					}
					if (null != session.getAttribute("userName")) {
						session.removeAttribute("userName");
					}
					if (null != session.getAttribute("userId")) {
						session.removeAttribute("userId");
					}
					if (null != session.getAttribute("adminMsg")) {
						session.removeAttribute("adminMsg");
					}

					session.invalidate();
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("jsps/loginPage.jsp");
					dispatcher.forward(request, response);
				}
			}
		}

		// to handle the form data for editPersonalDetailsPage.jsp when user
		// submits it.
		if (null != request.getParameter("updatePersonalDetails")) {
			if ("fromEditPersonalDetails".equalsIgnoreCase(request
					.getParameter("updatePersonalDetails"))) {
				User updatePersonalDetailsObj = new User();
				session = request.getSession();
				if (null != session) {
					updatePersonalDetailsObj.setUsesrName((String) session
							.getAttribute("userName"));
				}

				if (null != request.getParameter("password")) {
					updatePersonalDetailsObj.setPassword(request
							.getParameter("password"));
				}
				if (null != request.getParameter("eid")) {
					updatePersonalDetailsObj
							.setEid(request.getParameter("eid"));
				}
				if (null != request.getParameter("Fname")) {
					updatePersonalDetailsObj.setFirstName(request
							.getParameter("Fname"));
				}
				if (null != request.getParameter("Lname")) {
					updatePersonalDetailsObj.setLastName(request
							.getParameter("Lname"));
				}
				if (null != request.getParameter("emailAddress")) {
					updatePersonalDetailsObj.setEmailAddress(request
							.getParameter("emailAddress"));
				}
				if (null != request.getParameter("phoneNumber")) {
					updatePersonalDetailsObj.setPhoneNumber(request
							.getParameter("phoneNumber"));
				}

				if (bookHelper.updatePersonalDetails(updatePersonalDetailsObj)) {
					// if updation is successful navigate the user to the home
					// page.
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("jsps/homePage.jsp");
					dispatcher.forward(request, response);
				} else {
					// need to redirect to error page or other jsp page
				}

			}
		}

		/* to handle registration form data */
		if (null != request.getParameter("registration")) {
			if ("fromRegistrationPage".equalsIgnoreCase(request
					.getParameter("registration"))) {

				if ("register".equalsIgnoreCase(request
						.getParameter("register"))) {
					userObj = new User();
					session = request.getSession();
					// set the form registration data in User object
					if (null != request.getParameter("username")) {
						userObj.setUsesrName(request.getParameter("username"));
					}
					if (null != request.getParameter("password")) {
						userObj.setPassword(request.getParameter("password"));
					}
					if (null != request.getParameter("Fname")) {
						userObj.setFirstName(request.getParameter("Fname"));
					}
					if (null != request.getParameter("Lname")) {
						userObj.setLastName(request.getParameter("Lname"));
					}
					if (null != request.getParameter("eid")) {
						userObj.setEid(request.getParameter("eid"));
					}
					if (null != request.getParameter("emailAddress")) {
						userObj.setEmailAddress(request
								.getParameter("emailAddress"));
					}
					if (null != request.getParameter("phoneNumber")) {
						userObj.setPhoneNumber(request
								.getParameter("phoneNumber"));
					}

					if (bookHelper.userExists(userObj)) {
						// if registration is successful forward the user to
						// login

						session.setAttribute("regErrorMessage", "false");
						if (bookHelper.registerUserToQueue(userObj, host, port)) {
							response.sendRedirect("jsps/registrationPendingPage.jsp");
						}
					} else {
						if (null != session) {
							session.setAttribute("regErrorMessage", "true");
							RequestDispatcher dispatcher = request
									.getRequestDispatcher("/jsps/registrationPage.jsp");
							dispatcher.forward(request, response);
						}

					}

				}
			}
		}

		if ("yes".equalsIgnoreCase(request.getParameter("cancel"))) {
			session = request.getSession();
			if (null != session.getAttribute("errorMessage")) {
				session.setAttribute("errorMessage", "false");
			}
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/jsps/loginPage.jsp");
			dispatcher.forward(request, response);
		}

		// to handle add book form data
		if (null != request.getParameter("addbookpage")) {
			if ("fromAddBookPage".equalsIgnoreCase(request
					.getParameter("addbookpage"))) {
				addBookObj = new Book();
				int userId = -1;
				session = request.getSession();
				// set the form registration data in User object
				if (null != request.getParameter("bookname")) {
					addBookObj.setBookTitle(request.getParameter("bookname"));
				}
				if (null != request.getParameter("isbn")) {
					addBookObj.setIsbn(request.getParameter("isbn"));
				}
				if (null != request.getParameter("author")) {
					addBookObj.setAuthor(request.getParameter("author"));
				}
				if (null != request.getParameter("deptid")) {
					addBookObj
							.setDepartmentCode(request.getParameter("deptid"));
				}
				if (null != request.getParameter("coursename")) {
					addBookObj
							.setCourseName(request.getParameter("coursename"));
				}
				if (null != request.getParameter("coursenumber")) {
					addBookObj.setCourseNumber(request
							.getParameter("coursenumber"));
				}
				if (null != request.getParameter("price")) {
					addBookObj.setPrice(Integer.parseInt(request
							.getParameter("price")));
				}

				if (null != session.getAttribute("userId")) {
					userId = ((Integer) session.getAttribute("userId"))
							.intValue();
				}

				// if registration is successful forward the user to login page
				if (bookHelper.addBook(addBookObj, userId)) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("jsps/homePage.jsp");
					dispatcher.forward(request, response);
				}
			}
		}

		// handles hits to search page
		if (null != request.getParameter("search")) {

			Book searchBook = null;

			if ("search".equalsIgnoreCase(request.getParameter("search"))) {
				searchBook = new Book();

				// set search criteria in a book object
				if (null != request.getParameter("searchTitle")) {
					searchBook
							.setBookTitle(request.getParameter("searchTitle"));
				}
				if (null != request.getParameter("searchISBN")) {
					searchBook.setIsbn(request.getParameter("searchISBN"));
				}
				if (null != request.getParameter("searchAuthor")) {
					searchBook.setAuthor(request.getParameter("searchAuthor"));
				}
				if (null != request.getParameter("searchDept")) {
					searchBook.setDepartmentCode(request
							.getParameter("searchDept"));
				}
				if (null != request.getParameter("searchCourseName")) {
					searchBook.setCourseName(request
							.getParameter("searchCourseName"));
				}
				if (null != request.getParameter("searchCourseNumber")
						&& request.getParameter("searchCourseNumber") != "") {
					searchBook.setCourseNumber(request
							.getParameter("searchCourseNumber"));
				}

				session = request.getSession();

				// put search criteria in session
				session.setAttribute("searchCriteria", searchBook);

				Map<Book, User> mapFromServlet = new HashMap<Book, User>();
				// get search resutls for the specified search criteria from
				// helper
				mapFromServlet = bookHelper.searchBook(searchBook);

				// put search results in the session
				session.setAttribute("searchResults", mapFromServlet);

				// redirect to jsp to display the results

				response.sendRedirect("jsps/searchResults.jsp");
			}
		}

		// to retrieve the list of books added by the user
		if (null != request.getParameter("fromHistory")) {
			session = request.getSession();
			int userId = -1;
			ArrayList<Book> booksList = null;
			if (null != session.getAttribute("userId")) {
				userId = ((Integer) session.getAttribute("userId")).intValue();
				booksList = bookHelper.retrieveUserBooks(userId);
				session.setAttribute("booksList", booksList);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("jsps/homeRightNavigation.jsp");
				dispatcher.forward(request, response);
			}
		}

		// to retrieve the list of contacted sellers
		if (null != request.getParameter("contactSellers")) {
			session = request.getSession();
			int userId = -1;
			ArrayList<User> sellerList = null;
			if (null != session.getAttribute("userId")) {
				userId = ((Integer) session.getAttribute("userId")).intValue();
				sellerList = bookHelper.retrieveSellersList(userId);
				session.setAttribute("sellerList", sellerList);

				session.setAttribute("fromContactedToSellerProfile", true);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("jsps/viewSellersListPage.jsp");
				dispatcher.forward(request, response);
			}
		}
		// to retrieve the book based on book id
		if (null != request.getParameter("edit")) {
			session = request.getSession();
			Book bookObj = new Book();
			if (null != request.getParameter("bookId")) {
				bookObj.setBookId(Integer.parseInt(request
						.getParameter("bookId")));
			}
			bookObj = bookHelper.retrieveBook(bookObj);
			session.setAttribute("bookObject", bookObj);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("jsps/editBookPage.jsp");
			dispatcher.forward(request, response);

		}

		// to handle the form data for editBooksPage.jsp when user
		// submits it.
		if (null != request.getParameter("fromEditBook")) {
			if ("fromEditBook".equalsIgnoreCase(request
					.getParameter("fromEditBook"))) {
				Book updateBookObj = new Book();
				session = request.getSession();
				int userId = -1;

				if (null != session.getAttribute("userId")) {
					userId = ((Integer) session.getAttribute("userId"))
							.intValue();
				}
				if (null != request.getParameter("bookId")) {
					updateBookObj.setBookId(Integer.parseInt(request
							.getParameter("bookId")));
				}
				if (null != request.getParameter("bookname")) {
					updateBookObj
							.setBookTitle(request.getParameter("bookname"));
				}
				if (null != request.getParameter("isbn")) {
					updateBookObj.setIsbn(request.getParameter("isbn"));
				}
				if (null != request.getParameter("author")) {
					updateBookObj.setAuthor(request.getParameter("author"));
				}
				if (null != request.getParameter("deptid")) {
					updateBookObj.setDepartmentCode(request
							.getParameter("deptid"));
				}
				if (null != request.getParameter("coursename")) {
					updateBookObj.setCourseName(request
							.getParameter("coursename"));
				}

				if (null != request.getParameter("coursenumber")) {
					updateBookObj.setCourseNumber(request
							.getParameter("coursenumber"));
				}

				if (null != request.getParameter("price")) {
					updateBookObj.setPrice(Integer.parseInt(request
							.getParameter("price")));
				}

				if (null != request.getParameter("bookstatus")) {
					updateBookObj.setBookStatus(request
							.getParameter("bookstatus"));
				}

				if (bookHelper.updateBookDetails(updateBookObj, userId)) {
					// if updation is successful navigate the user to the home
					// page.
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("jsps/homePage.jsp");
					dispatcher.forward(request, response);
				} else {
					// need to redirect to error page or other jsp page
				}

			}
		}
		if (null != request.getParameter("fromTopSellers")) {
			session = request.getSession();
			ArrayList<User> topSellers = new ArrayList<User>();

			topSellers = bookHelper.getTopSellers();

			// put top sellers in the session
			session.setAttribute("topSellers", topSellers);
			session.setAttribute("fromTopSellersToSellerProfile", true);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("jsps/viewTopSellers.jsp");
			dispatcher.forward(request, response);

		}

		if (null != request.getParameter("fromTopBooks")) {
			session = request.getSession();
			ArrayList<Book> topBooks = new ArrayList<Book>();

			topBooks = bookHelper.getTopBooks();

			// put top sellers in the session
			session.setAttribute("topBooks", topBooks);

			RequestDispatcher dispatcher = request
					.getRequestDispatcher("jsps/viewTopBooks.jsp");
			dispatcher.forward(request, response);

		}

		// shows email seller form
		if (null != request.getParameter("contactSeller")
				&& "Back to Search Results".equalsIgnoreCase(request
						.getParameter("contactSeller"))) {
			// && request.getParameter("fromTopSellers")=="yes"
			System.out.println("test");
			response.sendRedirect("jsps/searchResults.jsp");

		}

		// shows email seller form
		if (null != request.getParameter("contactSeller")
				&& "Contact".equalsIgnoreCase(request
						.getParameter("contactSeller"))) {

			RequestDispatcher dispatcher = request
					.getRequestDispatcher("jsps/contactSeller.jsp");
			dispatcher.forward(request, response);

		}

		// sends email to seller
		if (null != request.getParameter("emailSeller")) {
			// && request.getParameter("subjectLabel")=="yes"

			session = request.getSession();
			User seller = (User) session.getAttribute("seller");

			User buyer = new User();
			buyer.setUserId((Integer) session.getAttribute("userId"));
			buyer.setUsesrName((String) session.getAttribute("userName"));

			String subject = request.getParameter("subject");
			String message = request.getParameter("message");
			// use helper to pull seller email address and buyer email address
			if (bookHelper.sendEmail(seller, buyer, subject, message, host,
					port)) {
				request.setAttribute("seller", seller);
				request.setAttribute("viewSeller", "yes");
				request.setAttribute("SellerName", seller.getUsesrName());
				request.setAttribute("SellerId", seller.getUserId());
				request.setAttribute("SellerId", seller.getUserId());
				session.setAttribute("emailSent", "yes");
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("jsps/sellerProfile.jsp");
				dispatcher.forward(request, response);
			}

		}

		// shows seller profile
		if (null != request.getParameter("viewSeller")) {
			// && request.getParameter("fromTopSellers")=="yes"

			User seller = new User();
			if (null != request.getParameter("sellerName"))
				seller.setUsesrName(request.getParameter("sellerName"));
			seller = bookHelper.getUserPersonalDetails(seller);
			seller.setUserId(Integer.parseInt(request.getParameter("sellerId")));
			session = request.getSession();
			session.setAttribute("seller", seller);

			// get comments
			ArrayList<String> comments = new ArrayList<String>();
			comments = bookHelper.getComments(seller);

			session.setAttribute("comments", comments);

			RequestDispatcher dispatcher = request
					.getRequestDispatcher("jsps/sellerProfile.jsp");
			dispatcher.forward(request, response);

		}

		// to handle viewBookDetails request

		if (null != request.getParameter("viewBookDetails")) {
			session = request.getSession();
			Book bookObj = new Book();
			if (null != request.getParameter("bookId")) {
				bookObj.setBookId(Integer.parseInt(request
						.getParameter("bookId")));
			}
			bookObj = bookHelper.retrieveBook(bookObj);
			session.setAttribute("bookObject", bookObj);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("jsps/viewBookPage.jsp");
			dispatcher.forward(request, response);

		}

		// to handle delete of a user by admin
		if (null != request.getParameter("fromAdminPage")) {
			session = request.getSession();
			String userName = null, emailAddress = null;
			int userId = -1;
			if (null != request.getParameter("userName")) {
				userName = request.getParameter("userName");
			}
			if (null != request.getParameter("userId")) {
				userId = Integer.parseInt(request.getParameter("userId"));
			}

			// deactive the user from the table and send email to user

			if (bookHelper.deActivateUser(userId)) {
				session.setAttribute("adminMsg", "Sucessfully deactivated  "
						+ userName);
				emailAddress = bookHelper.getUserEmailAddress(userId);
				String subject = "Campus Book Site";
				String message = " Your account is deactivated from the website";
				try {
					EmailUtility.sendEmail(host, port, user, pass, emailAddress
							+ "@emich.edu", subject, message);
				} catch (AddressException e) {
					e.printStackTrace();
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}

			// then retrieve the updated users and display in the admin home
			// page
			ArrayList<User> registeredUsers = new ArrayList<User>();
			registeredUsers = bookHelper.retrieveRegisteredUsers();
			session.setAttribute("registeredUsers", registeredUsers);
			if (null != session.getAttribute("adminResults")) {
				session.removeAttribute("adminResults");
			}
			if (null != session.getAttribute("adminSearchCriteria")) {
				session.removeAttribute("adminSearchCriteria");
			}
			if (null != session.getAttribute("adminSearchResult")) {
				session.removeAttribute("adminSearchResult");
			}
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("jsps/adminHomePage.jsp");
			dispatcher.forward(request, response);
		}

		// to handle the temp registration
		if (null != request.getParameter("requestRestration")) {

			int code = Integer.parseInt(request.getParameter("code"));

			// insert the record from temp queue to user table.
			// then delete the record from the temp queue.
			boolean tempUserDeleted = bookHelper.insertAndDelete(code);

			if (tempUserDeleted) {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("jsps/registrationCompletePage.jsp");
				dispatcher.forward(request, response);
			} else {

				response.sendRedirect("jsps/loginPage.jsp");
			}

		}
		// handle add comment
		if (null != request.getParameter("addComment")
				&& null != request.getParameter("feedback_type")
				&& "comment".equalsIgnoreCase(request
						.getParameter("feedback_type"))) {
			int sellerUserId = Integer.parseInt(request
					.getParameter("selected_userId"));
			String comments = request.getParameter("selected_comment");
			session = request.getSession();
			int buyerUserId = -1;
			if (null != session) {
				buyerUserId = (Integer) session.getAttribute("userId");
			}
			if (bookHelper.addComments(sellerUserId, buyerUserId, comments)) {
				session.setAttribute("feedBackResponse",
						"Your Feedback is updated successfully..!!");
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("jsps/viewSellersListPage.jsp");
				dispatcher.forward(request, response);
			}
		}
		// handle add rating
		if (null != request.getParameter("addComment")
				&& null != request.getParameter("feedback_type")
				&& "rating".equalsIgnoreCase(request
						.getParameter("feedback_type"))) {
			System.out.println("rating");
			int sellerUserId = Integer.parseInt(request
					.getParameter("selected_userId"));
			int rating = Integer.parseInt(request
					.getParameter("selected_rating"));
			session = request.getSession();
			int buyerUserId = -1;
			if (null != session) {
				buyerUserId = (Integer) session.getAttribute("userId");
			}
			if (bookHelper.addRating(sellerUserId, buyerUserId, rating)) {
				session.setAttribute("feedBackResponse",
						"Your Feedback is updated successfully..!!");
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("jsps/viewSellersListPage.jsp");
				dispatcher.forward(request, response);
			}
		}

		// to handle admin search
		if (null != request.getParameter("userSearch")
				&& "Search"
						.equalsIgnoreCase(request.getParameter("userSearch"))) {

			session = request.getSession();
			User searchUser = new User();

			boolean search = false;
			if (null != request.getParameter("searchUserName")
					&& !"".equalsIgnoreCase(request
							.getParameter("searchUserName"))) {
				search = true;
				searchUser.setUsesrName(request.getParameter("searchUserName"));

			}

			if (null != request.getParameter("searchEmailAddress")
					&& !"".equalsIgnoreCase(request
							.getParameter("searchEmailAddress"))) {
				search = true;
				searchUser.setEmailAddress(request
						.getParameter("searchEmailAddress"));

			}
			if (search) {
				session.setAttribute("adminSearchCriteria", searchUser);

				searchUser = bookHelper.searchUsers(searchUser);
				session.setAttribute("adminSearchResult", searchUser);
				boolean results = false;
				if (searchUser == null)
					results = false;
				else
					results = true;
				session.setAttribute("adminResults", results);
			}
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("jsps/adminHomePage.jsp");
			dispatcher.forward(request, response);

		}

	}

	// Method to handle POST method request.
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}