package com.emich.edu;

/**
 * DTO class for storing user information and setters and getters for those.
 * 
 * @author Poornima Reddy S
 *
 */
public class User {

	/* variable for storing usesrName */
	private String usesrName;

	/* variable for storing password */
	private String password;

	/* variable for storing first name */
	private String firstName;

	/* variable for storing last name */
	private String lastName;

	/* variable for storing EID */
	private String eid;

	/* variable for storing email address */
	private String emailAddress;

	/* variable for storing phone number */
	private String phoneNumber;
	
	private int userId;

	public String getUsesrName() {
		return usesrName;
	}

	public void setUsesrName(String usesrName) {
		this.usesrName = usesrName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public int getUserId() {
		
		return this.userId;
	}

	public User() {
		super();
		this.usesrName = "";
		this.password = "";
		this.firstName = "";
		this.lastName = "";
		this.eid = "";
		this.emailAddress = "";
		this.phoneNumber = "";
		this.userId = -1;
	}

	public void setUserId(int id) {
		
		this.userId = id;
	}
	
}