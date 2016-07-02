package com.emich.edu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Calendar;
import java.util.Timer;

public class CampusBookHelper {

	private DBInterface dbInterface;
	
	public CampusBookHelper() {

		try {
			dbInterface = new DBInterface();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public int login(User userObj) {
		int userId = 0;
		try {
			userId = dbInterface.checkUserNameAndPassword(userObj);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userId;
	}

	public boolean userExists(User userObj) {
		boolean validUserName = true;
		try {
			if (dbInterface.userExists(userObj)) {
				validUserName = false;
			} else {
				validUserName = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return validUserName;
	}

	/**
	 * puts the user in to registration Queue DBInterface
	 * 
	 * @param userObj
	 * @return
	 */
	public boolean registerUserToQueue(User userObj, String host, String port) {
		boolean registerSuccessful = true;
		int code = 1234;// get registration code from registration queue
		String fromAddress = "campusbooks2014@gmail.com";
		String frompwd = "ecommerceproject";
		String subject = "Campus Books:: Please complete your Registration";
		String message = null;
		String toAddress = userObj.getEmailAddress() + "@emich.edu";
		try {
			code = dbInterface.registerUserToQueue(userObj);
			if (code > 0) {
				message = "http://localhost:8080/CampusBook/CampusBooksServlet?requestRestration=yes&code="
						+ code
						+ "\n\n\nClick on the link to validate your email address and complete registration";

				registerSuccessful = true;
				EmailUtility.sendEmail(host, port, fromAddress, frompwd,
						toAddress, subject, message);
			} else {
				registerSuccessful = false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return registerSuccessful;
	}

	public boolean registerUser(User userObj) {
		boolean registerSuccessful = true;
		try {
			if (dbInterface.registerUser(userObj)) {
				registerSuccessful = true;
			} else {
				registerSuccessful = false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return registerSuccessful;
	}

	/**
	 * This method get the personal details for a given user name from
	 * DBInterface
	 * 
	 * @param userObj
	 * @return
	 */
	public User getUserPersonalDetails(User userObj) {
		User userObject = null;
		try {
			userObject = dbInterface.getUserPersonalDetails(userObj);

			String emailAddress = userObject.getEmailAddress();
			if (null != emailAddress) {
				String[] address = emailAddress.split("@");
				userObject.setEmailAddress(address[0]);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userObject;
	}

	/**
	 * This Methods calls DBInterface method to update the personal details of
	 * the user.
	 * 
	 * @param personalDetailsObj
	 * @return boolean
	 */
	public boolean updatePersonalDetails(User personalDetailsObj) {
		boolean updateSuccessful = false;
		try {
			updateSuccessful = dbInterface
					.updatePersonalDetails(personalDetailsObj);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateSuccessful;
	}

	/**
	 * This Methods calls DBInterface method to add a new book
	 * 
	 * @param addBookObj
	 * @return boolean
	 */
	public boolean addBook(Book addBookObj, int userId) {
		boolean addBookSuccessful = false;
		try {

			// dbInterface.getUserId();

			addBookSuccessful = dbInterface.addBook(addBookObj, userId);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return addBookSuccessful;
	}

	/**
	 * search for the book with specified criteria DBInterface
	 * 
	 * @param Book
	 *            object - searchBook
	 * @return map - user and book object map
	 */
	public Map<Book, User> searchBook(Book searchBook) {
		Map<Book, User> mapFromDB = new HashMap<Book, User>();
		try {
			mapFromDB = dbInterface.searchBook(searchBook);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapFromDB;
	}
	/**
	 * search user
	 * 
	 * @param user object with criteria selected bu admin
	 *            
	 * @return search result as a user object
	 */
	public User searchUsers(User adminCriteria) {
		
		try {
			adminCriteria = dbInterface.retrieveUser(adminCriteria);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return adminCriteria;
	}

	/**
	 * gets Rating value method from db DBInterface
	 * 
	 * @param Book
	 *            object - searchBook
	 * @return map - user and book object map
	 */
	public double getRating(User seller) {
		double rating = 0.0;
		try {
			rating = dbInterface.retrieveRating(seller);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rating;
	}

	/**
	 * gets Rating value method from db DBInterface
	 * 
	 * @param Book
	 *            object - searchBook
	 * @return map - user and book object map
	 */
	public ArrayList<String> getComments(User seller) {
		ArrayList<String> comments = new ArrayList<String>();
		try {
			comments = dbInterface.retrieveComments(seller);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return comments;
	}

	/**
	 * gets top sellers DBInterface
	 * 
	 * @param
	 * @return top sellers as array list
	 */
	public ArrayList<User> getTopSellers() {
		ArrayList<User> topSellers = new ArrayList<User>();
		try {
			topSellers = dbInterface.retrieveTopSellers();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return topSellers;
	}

	/**
	 * gets top books DBInterface
	 * 
	 * @param
	 * @return top books as array list
	 */
	public ArrayList<Book> getTopBooks() {
		ArrayList<Book> topBooks = new ArrayList<Book>();
		;
		try {
			topBooks = dbInterface.retrieveTopBooks();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return topBooks;
	}

	/**
	 * gets Rating value method from db DBInterface
	 * 
	 * @param Book
	 *            object - searchBook
	 * @return map - user and book object map
	 */
	public ArrayList<Book> retrieveUserBooks(int userId) {
		ArrayList<Book> booksList = new ArrayList<Book>();
		try {
			booksList = dbInterface.retrieveUserBooks(userId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return booksList;
	}

	/**
	 * This method get the book details for a given book id from DBInterface
	 * 
	 * @param bookObj
	 * @return book object
	 */
	public Book retrieveBook(Book bookObj) {
		Book book = null;
		try {
			book = dbInterface.retrieveBook(bookObj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return book;
	}

	/**
	 * This Methods calls DBInterface method to update the book details of a
	 * particular.
	 * 
	 * @param personalDetailsObj
	 * @return boolean
	 */
	public boolean updateBookDetails(Book bookDetailsObj, int userId) {
		boolean updateSuccessful = false;
		try {
			updateSuccessful = dbInterface.updateBookDetails(bookDetailsObj,
					userId);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateSuccessful;
	}

	/**
	 * sends email to seller
	 * 
	 * @param
	 * @return boolean
	 */
	@SuppressWarnings("finally")
	public boolean sendEmail(User seller, User buyer, String subject,
			String message, String host, String port) {
		boolean emailSent = false;

		String fromAddress = "campusbooks2014@gmail.com";
		String frompwd = "ecommerceproject";
		try {
			seller.setEmailAddress(dbInterface.getUserEmailAddress(seller
					.getUserId()));
			buyer.setEmailAddress(dbInterface.getUserEmailAddress(buyer
					.getUserId()));
			String temp = subject;
			subject ="Campus Books :: Book Request" +subject+"  ";
			message+="\n\n"+"A Campus Books user, would like to purchase a book from your Posting at our site.\nPlease click here respond to the person regarding this email "+buyer.getEmailAddress()+"@emich.edu"+"\n\nTeam,\nCampus Books";
			EmailUtility.sendEmail(host, port, fromAddress, frompwd,
					seller.getEmailAddress()+"@emich.edu", subject, message);
			
			emailSent = true;

			if (dbInterface.addContact(seller, buyer)) {
				emailSent = true;
			} else
				emailSent = false;

		} catch (Exception ex) {
			ex.printStackTrace();
			emailSent = false;
			// resultMessage = "There were an error: " + ex.getMessage();
		} finally {
			return emailSent;
		}

	}

	/**
	 * This method returns the list of registered users returns a list of User
	 * objects
	 */
	public ArrayList<User> retrieveRegisteredUsers() {
		ArrayList<User> registeredUsersList = new ArrayList<User>();

		registeredUsersList = dbInterface.retrieveRegisteredUsers();
		return registeredUsersList;

	}

	public String getUserEmailAddress(int user_id) {

		String emailAddress = dbInterface.getUserEmailAddress(user_id);

		return emailAddress;

	}

	public boolean deActivateUser(int userId) {
		boolean deleted = false;
		try {
			if (dbInterface.deActivateUser(userId)) {
				deleted = true;
			} else {
				deleted = false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return deleted;
	}

	/**
	 * gets top sellers DBInterface
	 * 
	 * @param
	 * @return top sellers as array list
	 */
	public ArrayList<User> retrieveSellersList(int userId) {
		ArrayList<User> sellerList = new ArrayList<User>();
		try {
			sellerList = dbInterface.retrieveSellersList(userId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sellerList;
	}

	/**
	 * This method insert user data into the user table and delete the temperory
	 * user data in the registration_queue table.
	 * 
	 * @param code
	 * @return boolean - true if user data is delete else false.
	 */
	public boolean insertAndDelete(int code) {

		boolean tempUserDeleted = false;
		try {
			tempUserDeleted = dbInterface.insertAndDelete(code);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempUserDeleted;

	}
	
	public boolean addComments(int sellerUserId,int buyerUserId, String comments){
		boolean commentsAdded = false;
		try {
			commentsAdded = dbInterface.addComments(sellerUserId, buyerUserId, comments);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return commentsAdded;
	}
	public boolean addRating(int sellerUserId,int buyerUserId, int rating){
		boolean ratingAdded = false;
		try {
			ratingAdded = dbInterface.addRating(sellerUserId, buyerUserId, rating);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ratingAdded;
	}
	

}
