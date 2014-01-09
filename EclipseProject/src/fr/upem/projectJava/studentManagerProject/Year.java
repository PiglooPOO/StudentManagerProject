package fr.upem.projectJava.studentManagerProject;

import java.util.Calendar;


public class Year {

	private int currentYear;
	
	public Year(int currentYear) {
		this.currentYear = currentYear;
	}

	public int getCurrentYear() {
		return currentYear;
	}
	
	/**
	* Description about the getActualCurrentYear function :
	* This function allows to get the actual current year.
	* @return Integer, return the currently year.
	*/
	public static int getActualCurrentYear(){
		if(Calendar.getInstance().get(Calendar.MONTH)<=Calendar.SEPTEMBER)
			return Calendar.getInstance().get(Calendar.YEAR)-1;
		else
			return Calendar.getInstance().get(Calendar.YEAR);
	}
}
