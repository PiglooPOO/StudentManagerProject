package fr.upem.projectJava.studentManagerProject;
public class Subject {

	private String title;
	private int coefficient;

	
	public Subject(String title, int coefficient) {
		this.title=title;
		this.coefficient=coefficient;
	}

	public String getTitle() {
		return title;
	}

	public int getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(int coefficient) {
		this.coefficient = coefficient;
	}

}
