package com.emich.edu;

/**
 * DTO class for storing book information and setters and getters for those.
 * 
 * @author Poornima Reddy S
 *
 */
public class Book {

	/* variable for storing book title */
	private String bookTitle;

	/* variable for storing isbn */
	private String isbn;

	/* variable for storing book author */
	private String author;

	/* variable for storing departmentCode */
	private String departmentCode;

	/* variable for storing courseNumber */
	private String courseNumber;

	/* variable for storing courseName */
	private String courseName;
	
	/* variable for storing price */
	private int price;

	/* variable for storing bookStatuse */
	private String bookStatus;
	
	private int bookId;
	
	public Book(){
		bookTitle="";
		isbn="";
		author="";
		departmentCode="";
		courseNumber="";
		courseName="";
		price=0;
		bookStatus="";
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getBookStatus() {
		return bookStatus;
	}

	public void setBookStatus(String bookStatus) {
		this.bookStatus = bookStatus;
	}
	
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public int getBookId() {
		return bookId;
	}

	public void setBookId(int id) {
		this.bookId = id;
	}

}