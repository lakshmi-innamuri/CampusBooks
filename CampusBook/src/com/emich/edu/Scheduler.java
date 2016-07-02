package com.emich.edu;

import java.util.ArrayList;
import java.util.Date;
import java.util.TimerTask;


/**
 * handles sending periodic email for feed back
 * @authors Poornima Reddy S, Lakshmi N Innamuri
 *
 */


public class Scheduler extends TimerTask {
	int count;
	Utility utility = null;
	DBInterface dbInterface = null;
	
	public Scheduler(DBInterface dbInterface){
		
		this.dbInterface = dbInterface;
	}
	public void run() {
		
			
			ArrayList<String> emailList = null;
			String subject = "Campus Books:: Seller Feedback Needed !";
			String message = "Our system shows that you have contacted a Seller. \nPlease provide feedback for your seller from the Contacted Sellers link in your Campus Books 'Home Page'.";
			try {
				emailList = dbInterface.retrieveAllContacts();

				for (String emailAddr : emailList) {
					EmailUtility.sendEmail("smtp.gmail.com", "587", "campusbooks2014@gmail.com", "ecommerceproject", emailAddr,
							subject, message);
					System.out.println("email sent");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("The task is called " + new Date());
		
	}
}