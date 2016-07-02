package com.emich.edu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * handles all Data Base related tasks
 * @authors Poornima Reddy S, Lakshmi N Innamuri
 *
 */

public class DBInterface {

	private Connection connection = null;
	private Statement statement = null;
//	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	DBInterface() throws Exception {
		System.out.println("-------- MySQL JDBC Connection Testing ------------");

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/campus_books", "root", "root");

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
		} finally {

		}

		if (connection != null) {
			System.out
					.println("You made it, take control your database now!!!!!!");
		} else {
			System.out.println("Failed to make connection!");
		}

	}

	/**
	 * This method checks if the user is a registered user, and if user is
	 * registered then will check username/password entries matches. Returns
	 * true is user is already registered and username/password matches else it
	 * returns false.
	 * 
	 * @param userObj
	 * @return boolean
	 */
	public int checkUserNameAndPassword(User userObj) {

		int userId = 0;
		try {
			PreparedStatement login = connection
					.prepareStatement("select user_id from users where username=? and password=? and status= 'active' ");
			login.setString(1, userObj.getUsesrName());
			login.setString(2, userObj.getPassword());
			resultSet = login.executeQuery();
			if (resultSet.next()) {
				userId = resultSet.getInt(1);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return userId;
	}

	/**
	 * returns true if the user name already exists and returns
	 * false if user name doesn't exists
	 * 
	 * @param uname
	 * @return
	 */
	public boolean userExists(User userObj) {
		Boolean exists = false;
		try {
			PreparedStatement user;
			user = connection
					.prepareStatement("select count(*) from users where username=? and status='active' ");
			user.setString(1, userObj.getUsesrName());
			resultSet = user.executeQuery();
			resultSet.next();
			if (resultSet.getInt(1) == 1) {
				exists = true;
			} else {
				exists = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exists;
	}

	/**
	 * stores all the user information in the backend.
	 * 
	 * @param userObj
	 * @return boolean
	 */
	public int registerUserToQueue(User userObj) {
		int registrationCode = 0;
		boolean registered = false;
		PreparedStatement stmt = null;
		try {

			stmt = connection
					.prepareStatement("insert into registration_queue(username,password,first_name,last_name,email,mobile_number,status,eid,ratings_count,ratings_value) values(?,?,?,?,?,?,?,?,0,0)");
			stmt.setString(1, userObj.getUsesrName());
			stmt.setString(2, userObj.getPassword());
			stmt.setString(3, userObj.getFirstName());
			stmt.setString(4, userObj.getLastName());
			stmt.setString(5, userObj.getEmailAddress());
			stmt.setString(6, userObj.getPhoneNumber());
			stmt.setString(7, "active");
			stmt.setString(8, userObj.getEid());

			int rows = stmt.executeUpdate();

			if (rows == 1) {
				registered = true;
				if (registered) {
					registrationCode = getRegistrationCode(userObj
							.getUsesrName());
				}
			} else {
				registered = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return registrationCode;
	}

	/**
	 * get registration code to validate the user
	 * 
	 * @param string username
	 * @return int registration code
	 */

	private int getRegistrationCode(String username) {

		System.out.println("get user email");
		int regCode = 0;
		try {
			String regCodeSQL = "select registration_code from registration_queue where username =?";
			PreparedStatement preStatement1 = connection
					.prepareStatement(regCodeSQL);
			preStatement1.setString(1, username);

			ResultSet regCodeRS = preStatement1.executeQuery();
			if (regCodeRS.next()) {
				regCode = regCodeRS.getInt(1);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return regCode;
	}

	/**
	 * copies data from registaration queue and stores in users table
	 * 
	 * @param userObj
	 * @return boolean
	 */
	public boolean registerUser(User userObj) {

		boolean registered = false;
		PreparedStatement stmt = null;
		try {
			stmt = connection
					.prepareStatement("insert into users(username,password,first_name,last_name,email,mobile_number,status,eid) values(?,?,?,?,?,?,?,?)");
			stmt.setString(1, userObj.getUsesrName());
			stmt.setString(2, userObj.getPassword());
			stmt.setString(3, userObj.getFirstName());
			stmt.setString(4, userObj.getLastName());
			stmt.setString(5, userObj.getEmailAddress());
			stmt.setString(6, userObj.getPhoneNumber());
			stmt.setString(7, "active");
			stmt.setString(8, userObj.getEid());

			int rows = stmt.executeUpdate();

			if (rows == 1) {
				registered = true;
			} else {
				registered = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return registered;
	}

	/**
	 * return the personal details for a given user name
	 * 
	 * @param userObj
	 * @return userObj
	 */
	public User getUserPersonalDetails(User userObj) {

		User user = null;
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select username,password,first_name,last_name,email,mobile_number,eid from users where username=? ");
			preparedStatement.setString(1, userObj.getUsesrName());
			System.out
					.println("user name in details " + userObj.getUsesrName());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				user = new User();
				user.setUsesrName(resultSet.getString(1));
				user.setPassword(resultSet.getString(2));
				user.setFirstName(resultSet.getString(3));
				user.setLastName(resultSet.getString(4));
				user.setEmailAddress(resultSet.getString(5));
				user.setPhoneNumber(resultSet.getString(6));
				user.setEid(resultSet.getString(7));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	/**
	 * updates the personal details of the user in the backend.
	 * Returns true if the update is successful else returns false
	 * 
	 * @param User
	 * @return boolean
	 */
	public boolean updatePersonalDetails(User personalDetailsObj) {

		boolean updated = false;
		PreparedStatement update;
		try {

			update = connection
					.prepareStatement("update users set password = ?, first_name=?, last_name=?, email=?, mobile_number=?, eid=? where username=? ");

			update.setString(1, personalDetailsObj.getPassword());
			update.setString(2, personalDetailsObj.getFirstName());
			update.setString(3, personalDetailsObj.getLastName());
			update.setString(4, personalDetailsObj.getEmailAddress());
			update.setString(5, personalDetailsObj.getPhoneNumber());
			update.setString(6, personalDetailsObj.getEid());
			update.setString(7, personalDetailsObj.getUsesrName());

			int rows = update.executeUpdate();
			if (rows > 0) {
				updated = true;
			} else {
				updated = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return updated;
	}

	/**
	 * adds a new book information to the books table 
	 * Returns true if the add is successful else returns false
	 * 
	 * @param Book, int (books object and id of user adding the book)
	 * @return boolean
	 */
	public boolean addBook(Book addBookObj, int userId) {
		boolean bookAdded = false;
		PreparedStatement stmt = null;
		PreparedStatement authorstmt = null;
		int bookId = 0;
		try {
			
			stmt = connection
					.prepareStatement(
							"insert into books(user_id,isbn,title,price,course_name,course_number,course_department,status) values(?,?,?,?,?,?,?,?)",
							Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, userId);
			stmt.setString(2, addBookObj.getIsbn());
			stmt.setString(3, addBookObj.getBookTitle());
			stmt.setInt(4, addBookObj.getPrice());
			stmt.setString(5, addBookObj.getCourseName());
			stmt.setString(6, addBookObj.getCourseNumber());
			stmt.setString(7, addBookObj.getDepartmentCode());
			stmt.setString(8, "avail");

			int rows = stmt.executeUpdate();

			if (rows == 1) {
				bookAdded = true;

				ResultSet rs = stmt.getGeneratedKeys();
				if (rs.next()) {
					bookId = rs.getInt(1);
				}
				authorstmt = connection
						.prepareStatement("insert into author(author_name,book_id) values(?,?)");
				authorstmt.setString(1, addBookObj.getAuthor());
				authorstmt.setInt(2, bookId);

				int authorRows = authorstmt.executeUpdate();
				if (authorRows == 1)
					bookAdded = true;
				else
					bookAdded = false;
			} else {
				bookAdded = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return bookAdded;
	}

	/**
	 * search for book Returns map of users and book object
	 * 
	 * @param Book (search criteria)
	 * @return map (book and its seller)
	 */
	public Map<Book, User> searchBook(Book searchCriteria) {

		Map<Book, User> results = new HashMap<Book, User>();
		Book book = null;
		User user = null;
	//	select title from books where title regexp '[[:<:]](singl)|(singl)[[:>:]]|[[:<:]](calc)|(calc)[[:>:]]';
		try {
		statement = connection.createStatement();
		String query = "select distinct b.book_id, b.title, u.user_id, u.username from books b, users u, author a where u.status = 'active' and b.status='avail' and b.user_id = u.user_id  ";
		boolean needAnd = true;

		if (searchCriteria.getCourseName() != null
		&& searchCriteria.getCourseName().trim() != "") {
			
			if(needAnd)
				query += " and course_name regexp '  ";
				else 
				query += " course_name= '" + searchCriteria.getCourseName() + "' ";
			
			String textSplit[] = searchCriteria.getCourseName().split("\\s+");

			boolean needOr = false;
			for (String string : textSplit) {
				if(needOr)
					query += "|[[:<:]]("+string+")|("+string+")[[:>:]]";
				else
					query += "[[:<:]]("+string+")|("+string+")[[:>:]]";
				needOr = true;
			}
			query += "'";

		needAnd = true;
		}

		if (searchCriteria.getIsbn() != null
		&& !searchCriteria.getIsbn().trim().equalsIgnoreCase("")) {
			
			if(needAnd)
				query += " and isbn regexp '  ";
				else 
				query += " isbn regexp '  ";
			
			String textSplit[] = searchCriteria.getIsbn().trim().split("\\s+");

			boolean needOr = false;
			for (String string : textSplit) {
				if(needOr)
					query += "|[[:<:]]("+string+")|("+string+")[[:>:]]";
				else
					query += "[[:<:]]("+string+")|("+string+")[[:>:]]";
				needOr = true;
			}
			query += "'";

		needAnd = true;
		}

		if (null != searchCriteria.getCourseNumber()
		&& !searchCriteria.getCourseNumber().trim().equals("")) {
			
			if(needAnd)
				query += " and course_number regexp '  ";
				else 
				query += " course_number regexp '  ";
			
			String textSplit[] = searchCriteria.getCourseNumber().trim().split("\\s+");

			boolean needOr = false;
			for (String string : textSplit) {
				if(needOr)
					query += "|[[:<:]]("+string+")|("+string+")[[:>:]]";
				else
					query += "[[:<:]]("+string+")|("+string+")[[:>:]]";
				needOr = true;
			}
			query += "'";

		needAnd = true;
		}

		if (searchCriteria.getDepartmentCode() != null
		&& !searchCriteria.getDepartmentCode().trim().equals("")) {
			
			if(needAnd)
				query += " and course_department regexp '  ";
				else 
				query += " course_department regexp '  ";
			
			String textSplit[] = searchCriteria.getDepartmentCode().trim().split("\\s+");

			boolean needOr = false;
			for (String string : textSplit) {
				if(needOr)
					query += "|[[:<:]]("+string+")|("+string+")[[:>:]]";
				else
					query += "[[:<:]]("+string+")|("+string+")[[:>:]]";
				needOr = true;
			}
			query += "'";

		needAnd = true;
		}

		if (searchCriteria.getBookTitle() != null
		&& !searchCriteria.getBookTitle().trim().equals("")) {
			
			if(needAnd)
				query += " and title regexp '  ";
				else 
				query += " title regexp '  ";
			
			String textSplit[] = searchCriteria.getBookTitle().trim().split("\\s+");

			boolean needOr = false;
			for (String string : textSplit) {
				if(needOr)
					query += "|[[:<:]]("+string+")|("+string+")[[:>:]]";
				else
					query += "[[:<:]]("+string+")|("+string+")[[:>:]]";
				needOr = true;
			}
			query += "'";

		needAnd = true;
		}

		if (searchCriteria.getAuthor() != null
		&& !searchCriteria.getAuthor().trim().equalsIgnoreCase("")){
			
			if(needAnd)
				query += " and a.book_id = b.book_id and a.author_name regexp' ";
				else 
				query += " a.book_id = b.book_id and a.author_name regexp' ";
			
			String textSplit[] = searchCriteria.getAuthor().trim().split("\\s+");

			boolean needOr = false;
			for (String string : textSplit) {
				if(needOr)
					query += "|[[:<:]]("+string+")|("+string+")[[:>:]]";
				else
					query += "[[:<:]]("+string+")|("+string+")[[:>:]]";
				needOr = true;
			}
			query += "'";

		needAnd = true;
		} 
		
		query += " order by round(u.ratings_value/u.ratings_count) asc";
		System.out.println("query ::" + query);
		PreparedStatement preStatement = connection.prepareStatement(query);

		resultSet = preStatement.executeQuery(query);
		while (resultSet.next()) {
		book = new Book();
		user = new User();
		book.setBookId(resultSet.getInt(1));
		book.setBookTitle(resultSet.getString(2));
		user.setUserId(Integer.parseInt(resultSet.getString(3)));
		user.setUsesrName(resultSet.getString(4));
		results.put(book, user);

		}

		} catch (Exception e) {
		e.printStackTrace();
		}

		return results;
		}
	
	/**
	 * retrieve the user admin searched for
	 * 
	 * @param User (with admin criteria)
	 * @return User (user with details)
	 */
	public User retrieveUser(User adminCriteria) {
		User user = null;
		
		try {
			statement = connection.createStatement();

			String query = "select user_id, username from users where status = 'active' ";
			boolean needAnd = true;

			if (adminCriteria.getUsesrName() != null
					&& adminCriteria.getUsesrName().trim() != "") {
				if(needAnd)
				query += " and username= '" + adminCriteria.getUsesrName()
						+ "' ";
				else 
				query += " username= '" + adminCriteria.getUsesrName() + "' ";
				needAnd = true;
			}

			if (adminCriteria.getEmailAddress() != null
					&& !adminCriteria.getEmailAddress().trim().equalsIgnoreCase("")) {
				if (needAnd)
					query += " and email= '" + adminCriteria.getEmailAddress() + "'";
				else
					query += "email= '" + adminCriteria.getEmailAddress() + "'";
				needAnd = true;
			}

			System.out.println("query is " + query);
			PreparedStatement preStatement = connection.prepareStatement(query);

			resultSet = preStatement.executeQuery(query);

			if (resultSet.next()) {
				System.out.println("User searched " + resultSet.getString(1));
				if (!"admin".equalsIgnoreCase(resultSet.getString(2))) {
				user = new User();

				user.setUserId(Integer.parseInt(resultSet.getString(1)));
				user.setUsesrName(resultSet.getString(2));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}
	
	
	
	/**
	 * retrieves the ratings of a specific user
	 * 
	 * @param User
	 * @return double (user rating)
	 */
	public double retrieveRating(User userObj) {

		double rating = 0.0;
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select round(ratings_value/ratings_count) from users where username=? ");
			preparedStatement.setString(1, userObj.getUsesrName());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				rating = resultSet.getFloat(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rating;
	}

	/**
	 * retreives comments on a user
	 * 
	 * @param User 
	 * @return array list (all comments)
	 */
	public ArrayList<String> retrieveComments(User seller) {

		ArrayList<String> comments = new ArrayList<String>();
		;
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select comment from user_comments where seller=? ");
			preparedStatement.setInt(1, seller.getUserId());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				comments.add(resultSet.getString(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return comments;
	}

	/**
	 * retreives books added by given user
	 * 
	 * @param int (user id)
	 * @return array list ( all books)
	 */
	public ArrayList<Book> retrieveUserBooks(int userId) {

		ArrayList<Book> booksList = new ArrayList<Book>();
		Book book = null;
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select book_id,title,status from books where user_id=? ");
			preparedStatement.setInt(1, userId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				book = new Book();
				book.setBookId(resultSet.getInt(1));
				book.setBookTitle(resultSet.getString(2));
				book.setBookStatus(resultSet.getString(3).toLowerCase());
				booksList.add(book);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return booksList;
	}

	/**
	 * returns the book details for a given book id
	 * 
	 * @param book  
	 * @return book object
	 */
	public Book retrieveBook(Book bookObj) {

		Book book = null;
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select b.book_id,b.isbn,b.title,b.price,b.course_name,b.course_number,b.course_department,b.status,a.author_name from books b, author a where b.book_id=a.book_id and b.book_id= ?");
			preparedStatement.setInt(1, bookObj.getBookId());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				book = new Book();
				book.setBookId(resultSet.getInt(1));
				book.setIsbn(resultSet.getString(2));
				book.setBookTitle(resultSet.getString(3));
				book.setPrice(resultSet.getInt(4));
				book.setCourseName(resultSet.getString(5));
				book.setCourseNumber(resultSet.getString(6));
				book.setDepartmentCode(resultSet.getString(7));
				book.setBookStatus(resultSet.getString(8));
				book.setAuthor(resultSet.getString(9));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return book;
	}

	/**
	 * updates the book details of the book in the backend. Returns
	 * true if the update is successful else returns false
	 * 
	 * @param Book, int (Book details and the if of user who addede the book)
	 * @return boolean
	 */
	public boolean updateBookDetails(Book bookDetailsObj, int userId) {

		boolean updated = false;
		PreparedStatement update, authorUpdate;
		try {

			update = connection
					.prepareStatement("update books set book_id = ?, user_id=?, isbn=?, title=?, price=?, course_name=?, course_number=?, course_department=?,status=? where book_id=? ");

			update.setInt(1, bookDetailsObj.getBookId());
			update.setInt(2, userId);
			update.setString(3, bookDetailsObj.getIsbn());
			update.setString(4, bookDetailsObj.getBookTitle());
			update.setInt(5, bookDetailsObj.getPrice());
			update.setString(6, bookDetailsObj.getCourseName());
			update.setString(7, bookDetailsObj.getCourseNumber());
			update.setString(8, bookDetailsObj.getDepartmentCode());
			update.setString(9, bookDetailsObj.getBookStatus());
			update.setInt(10, bookDetailsObj.getBookId());

			int rows = update.executeUpdate();
			if (rows > 0) {
				updated = true;
				authorUpdate = connection
						.prepareStatement("update author set author_name = ? where book_id=? ");

				authorUpdate.setString(1, bookDetailsObj.getAuthor());
				authorUpdate.setInt(2, bookDetailsObj.getBookId());
				int authorRows = authorUpdate.executeUpdate();
				if (authorRows > 0) {
					updated = true;
				}
			} else {
				updated = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return updated;
	}

	/**
	 * retreives top sellers
	 * 
	 * @param
	 * @return array list (sellers ordered by rating)
	 */
	public ArrayList<User> retrieveTopSellers() {

		ArrayList<User> topSellers = new ArrayList<User>();
		User seller = null;
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select user_id, username from users order by(ratings_value) desc");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				if (!"admin".equalsIgnoreCase(resultSet.getString(2))) {

					seller = new User();
					seller.setUserId(resultSet.getInt(1));
					seller.setUsesrName(resultSet.getString(2));

					topSellers.add(seller);
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return topSellers;
	}

	/**
	 * retrieve top books
	 * 
	 * @param
	 * @return array list (books ordered by the number of time a book a specific title is sold)
	 */
	public ArrayList<Book> retrieveTopBooks() {

		ArrayList<Book> topBooks = new ArrayList<Book>();
		Book book = null;
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select title, count(*) as sold_count from books where status='sold' group by title order by sold_count desc");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				book = new Book();
				book.setBookTitle(resultSet.getString(1));

				topBooks.add(book);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return topBooks;
	}

	/**
	 * adds contacts to buyer
	 * 
	 * @param buyer
	 *            and seller (user objects)
	 * @return boolean 
	 */
	public boolean addContact(User seller, User buyer) {
		boolean contactAdded = false;
		int buyerId = buyer.getUserId();
		int sellerId = seller.getUserId();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select count(*) from buyer_contacts where buyer=? and seller=?");
			preparedStatement.setInt(1, buyer.getUserId());
			preparedStatement.setInt(2, seller.getUserId());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				// buyer is contacting seller first time
				System.out.println("result set int: " + resultSet.getInt(1));
				if (resultSet.getInt(1) == 0) {

					if (insertContact(sellerId, buyerId)) {
						contactAdded = true;
					} else
						contactAdded = false;

				} else if (resultSet.getInt(1) == 1) {
					contactAdded = true;
				} else
					contactAdded = false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return contactAdded;
	}

	// helper for add contact method
	private boolean insertContact(int sellerId, int buyerId) {
		boolean inserted = false;
		try {
			String selectSQL = "insert into buyer_contacts values(?,?,curdate())";

			PreparedStatement preStatement1 = connection
					.prepareStatement(selectSQL);
			preStatement1.setInt(1, buyerId);
			preStatement1.setInt(2, sellerId);

			if (preStatement1.executeUpdate() == 1) {
				System.out.println("inserted");
				inserted = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return inserted;
	}

	public String getUserEmailAddress(int user_id) {

		System.out.println("get user email");
		String email = null;
		try {

			String selectSQL = "select email from users where user_id =?";
			PreparedStatement preStatement1 = connection
					.prepareStatement(selectSQL);
			preStatement1.setInt(1, user_id);

			ResultSet rs = preStatement1.executeQuery();
			if (rs.next()) {
				System.out.println("name: " + rs.getString(1));
				email = rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return email;
	}

	public ArrayList<User> retrieveRegisteredUsers() {

		ArrayList<User> registeredUserList = new ArrayList<User>();
		User user = null;
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select username,user_id from users where status = 'active' ");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				if (!"admin".equalsIgnoreCase(resultSet.getString(1))) {
					user = new User();
					user.setUsesrName(resultSet.getString(1));
					user.setUserId(resultSet.getInt(2));
					registeredUserList.add(user);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return registeredUserList;
	}

	/**
	 * delete the user from Users table. Returns true if
	 * the delete is successful else returns false
	 * 
	 * @param int (user id)
	 * @return boolean
	 */
	public boolean deActivateUser(int userId) {

		boolean deleted = false;
		PreparedStatement update;
		try {

			update = connection
					.prepareStatement("update users set status=?  where user_id=? ");

			update.setString(1, "Deactive");
			update.setInt(2, userId);

			int rows = update.executeUpdate();
			if (rows > 0) {
				deleted = true;
			} else {
				deleted = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return deleted;
	}



	/**
	 * This method returns the list of sellers the buyer contacted.
	 * 
	 * @param int (user id)
	 * @return sellerList
	 */
	public ArrayList<User> retrieveSellersList(int userId) {

		ArrayList<User> sellerList = new ArrayList<User>();

		User seller = null;
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select distinct u.user_id,u.first_name, u.last_name, u.username from buyer_contacts b, "
							+ "users u where u.user_id = b.seller and b.buyer=? "
							+ "order by contact_date desc");
			preparedStatement.setInt(1, userId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				seller = new User();
				seller.setUserId(resultSet.getInt(1));
				seller.setFirstName(resultSet.getString(2));
				seller.setLastName(resultSet.getString(3));
				seller.setUsesrName(resultSet.getString(4));
				sellerList.add(seller);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return sellerList;
	}

	/**
	 * This method returns the list of sellers the buyer contacted.
	 * 
	 * @param userId
	 * @return sellerList
	 */
	public boolean insertAndDelete(int code) {

		boolean deleted = false;
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into users(username,password,first_name,last_name,email,mobile_number,status,ratings_count,ratings_value,eid)"
							+ " select username,password,first_name,last_name,email,mobile_number,status,ratings_count,ratings_value,eid from registration_queue "
							+ "where registration_code=?");
			preparedStatement.setInt(1, code);
			int rows = preparedStatement.executeUpdate();
			if (rows > 0) {
				PreparedStatement delStmt = connection
						.prepareStatement("delete from registration_queue where registration_code=?");

				delStmt.setInt(1, code);
				int delRows = delStmt.executeUpdate();
				if (delRows > 0) {
					deleted = true;
				} else {
					deleted = false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return deleted;
	}

	public ArrayList<String> retrieveAllContacts() {

		ArrayList<String> emailList = new ArrayList<>();

		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select distinct u.email from buyer_contacts b, users u where b.buyer=u.user_id and curdate() - contact_date=1");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				emailList.add(resultSet.getString(1) + "@emich.edu");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emailList;
	}

	public boolean addComments(int sellerUserId, int buyerUserId,
			String comments) {

		boolean commentAdded = false;

		PreparedStatement addCommentStmt;
		try {
			addCommentStmt = connection
					.prepareStatement("insert into user_comments(seller,comment,buyer) values (?,?,?)");
			
			addCommentStmt.setInt(1, sellerUserId);
			addCommentStmt.setString(2, comments);
			addCommentStmt.setInt(3, buyerUserId);

			int addedRows = addCommentStmt.executeUpdate();
			if (addedRows > 0) {
				commentAdded = true;
			} else {
				commentAdded = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return commentAdded;

	}
	
	
	public boolean addRating(int sellerUserId, int buyerUserId,
			int rating) {

		boolean ratingUpdated = false;

		PreparedStatement addRatingsStmt;
		try {
			addRatingsStmt = connection
					.prepareStatement("update users set ratings_count=ratings_count+1, ratings_value=ratings_value+? where user_id=?");
			
			addRatingsStmt.setInt(1, rating);
			addRatingsStmt.setInt(2, sellerUserId);
			

			int addedRows = addRatingsStmt.executeUpdate();
			if (addedRows > 0) {
				ratingUpdated = true;
			} else {
				ratingUpdated = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ratingUpdated;

	}
	
	// You need to close the resultSet
	public void closeConnection() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {

		}

	}
}
