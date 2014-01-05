package fr.upem.projectJava.studentManagerProject;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Formation {

	private String name;
	private String diploma;
	private int nbYear;
	private int curYear;
	private List<Subject> subjectList = new ArrayList<Subject>();
	private List<Student> studentList = new ArrayList<Student>();
	
	public Formation(String name, String diploma, int nbYear, int curYear) {
		this.name = name;
		this.diploma = diploma;
		this.nbYear = nbYear;
		this.curYear = curYear;
	}
	
	public Formation(){
		Scanner sc = new Scanner(System.in);
		do{
			System.out.print("Nom de la filière : ");
			this.name=sc.nextLine();
			if(this.name.length()>50)
				System.out.println("Le nom de la filière entré est trop long");
		}
		while(this.name.length()>50);
		do{
		System.out.print("Nom du diplôme remis par la filière : ");
		this.diploma=sc.nextLine();
		if(this.name.length()>50)
			System.out.println("Le nom de la filière entré est trop long");
		}
		while(this.name.length()>50);
		System.out.print("Nombre d'année total de la filière : ");
		this.nbYear=sc.nextInt();
		do{
		System.out.print("Année de la filière :");
		this.curYear=sc.nextInt();
		if(curYear>nbYear)
			System.out.println("L'année entré n'est pas disponible pour cette filière");
		}
		while(curYear>nbYear);
	}

	public int getNbYear() {
		return nbYear;
	}

	public int getCurYear() {
		return curYear;
	}
	
	public String getName() {
		return name;
	}

	public List<Subject> getSubjects() {
		return subjectList;
	}

	public void setSubjectList(List<Subject> subjects) {
		this.subjectList = subjects;
	}
	
	public void addSubject(Subject subject) {
		this.subjectList.add(subject);
	}

	public String getDiploma() {
		return diploma;
	}

	public void setDiploma(String diploma) {
		this.diploma = diploma;
	}
	
	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}
	
	public void addStudent(Student etudiant) {
		this.studentList.add(etudiant);
	}
	
	public void addFormation(){
		try {
			Statement state = DBConnection.getInstance().createStatement();
			state.executeUpdate("INSERT INTO `formation`(`name`, `diploma`, `nbYear`, `curYear`) VALUES ('"+this.getName()+"','"+this.getDiploma()+"','"+this.getNbYear()+"','"+this.getCurYear()+"')");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
