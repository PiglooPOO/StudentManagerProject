package fr.upem.projectJava.studentManagerProject;
import java.sql.ResultSet;
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
	private	int isAvailable;
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
	
	public static boolean showFormation(int id){
		int choiceNumber = 0;
		Statement state;
		Scanner sc = null;
		try {
			state = DBConnection.getInstance().createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM formation WHERE id = "+id);
			if(result.next()){
				System.out.println(
						"\nGestion du " + result.getString("diploma")+ " " + result.getString("name") +" "+ result.getInt("curYear") +"ème année.\n");
				
				System.out.println("Que voulez-vous faire ?\n"
						+ "1 Editer\n"
						+ "2 Supprimer\n"
						+ "3 Ajouter des matières\n"
						+ "4 Visualiser les étudiants suivant cette formation\n"
						+ "5 Quitter\n");
				
				System.out.print(">> ");
				sc = new Scanner(System.in);
				choiceNumber = sc.nextInt();
				sc.nextLine();
				
				switch(choiceNumber){
					case 1:
						//TODO
						Formation.editFormation(id);
						break;
					case 2:
						//TODO
						Formation.deleteFormation(id);
						break;
					case 3:
						//TODO
						Formation.addSubjectToFormation(id);
						break;
					case 4:
						Student.showStudentsByFormation(id);
						break;
					case 5:
						// TODO
						break;
					default:
						// TODO
						break;
				}
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean addSubjectToFormation(int id){
		// TODO
		return false;
	}
	
	public static boolean searchFormationsByName(String answerFormation){
		Statement state;
		try {
			state = DBConnection.getInstance().createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM formation WHERE name LIKE \"%"+answerFormation+"%\" OR diploma LIKE \"%"+answerFormation+"%\"");
			while(result.next()){
				System.out.println(result.getInt("id")+" "+result.getString("diploma")+" "+result.getString("name"));
			}
			Scanner sc = new Scanner(System.in);
			int id = sc.nextInt();
			sc.nextLine();
			showFormation(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}
	
	public void addFormation(){
		try {
			Statement state = DBConnection.getInstance().createStatement();
			state.executeUpdate("INSERT INTO `formation`(`name`, `diploma`, `nbYear`, `curYear`) VALUES ('"+this.getName()+"','"+this.getDiploma()+"','"+this.getNbYear()+"','"+this.getCurYear()+"')");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void deleteFormation(int id){
		// TODO
	}
	
	public static void editFormation(int id){
		// TODO
	}

	public int getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(int isAvailable) {
		this.isAvailable = isAvailable;
	}
}
