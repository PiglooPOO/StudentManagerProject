package fr.upem.projectJava.studentManagerProject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class Year {

	private int currentYear;
	private List<Formation> formationList = new ArrayList<Formation>();
	
	public Year(int currentYear) {
		this.currentYear = currentYear;
		this.formationList = null;
	}

	public List<Formation> getFormationList() {
		return formationList;
	}

	public void setFormationList(List<Formation> formationList) {
		this.formationList = formationList;
	}
	
	public void addFormation(Formation f) {
		this.formationList.add(f);
	}

	public int getCurrentYear() {
		return currentYear;
	}
	
	/**
	* Description about the getActualCurrentYear function :
	* This function allows to get the actual current year.
	* @return <Integer> return the currently year.
	*/
	public static int getActualCurrentYear(){
		if(Calendar.getInstance().get(Calendar.MONTH)<=Calendar.SEPTEMBER)
			return Calendar.getInstance().get(Calendar.YEAR)-1;
		else
			return Calendar.getInstance().get(Calendar.YEAR);
	}
}
