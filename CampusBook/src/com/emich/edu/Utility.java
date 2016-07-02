package com.emich.edu;

import java.util.Calendar;
import java.util.Timer;

/**
 * sets timer for scheduler
 * @authors Poornima Reddy S, Lakshmi N Innamuri
 *
 */

public class Utility {
	DBInterface dbInterface = null;
	
	public Utility() {
		try {
			dbInterface = new DBInterface();
			
			Timer timer = new Timer();
			Calendar date = Calendar.getInstance();
			timer.schedule(new Scheduler(dbInterface), date.getTime(), 86400000);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
