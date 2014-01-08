package fr.upem.projectJava.studentManagerProject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Formation {

	private String name;
	private int nbYear;
	private int curYear;
	private	int isAvailable;
	private List<Subject> subjectList = new ArrayList<Subject>();
	private List<Student> studentList = new ArrayList<Student>();
	
	public Formation(String name, int nbYear, int curYear) {
		this.name = name;
		this.nbYear = nbYear;
		this.curYear = curYear;
	}
	
	public Formation(){
		do{
			System.out.print("Nom de la fili�re : ");
			this.name = Main.sc.nextLine();
			if(this.name.length()>50)
				System.out.println("Le nom de la fili�re entr� est trop long");
		}
		while(this.name.length()>50);
		System.out.print("Nombre d'ann�e total de la fili�re : ");
		this.nbYear = Main.sc.nextInt();
		do{
		System.out.print("Ann�e de la fili�re :");
		this.curYear = Main.sc.nextInt();
		if(curYear>nbYear)
			System.out.println("L'ann�e entr�e n'est pas disponible pour cette fili�re");
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
		try {
			state = DBConnection.getInstance().createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM formation WHERE id = "+id);
			if(result.next()){
				System.out.println(
						"\nGestion du Diplome d'ing�nieur " + result.getString("name") +" "+ result.getInt("curYear") +"�me ann�e.\n");
				
				System.out.println("Que voulez-vous faire ?\n"
						+ "1 Editer\n"
						+ "2 Supprimer\n"
						+ "3 Ajouter des mati�res\n"
						+ "4 Visualiser les �tudiants suivant cette formation\n"
						+ "5 Quitter\n");
				
				System.out.print(">> ");
				choiceNumber = Main.sc.nextInt();
				Main.sc.nextLine();
				
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
	
	public static String FormationNameByStudentId(int id){
		Statement state;
		try {
			state = DBConnection.getInstance().createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM formation WHERE id = "+id);
			if(result.next()){
				return result.getString("name") +" "+ result.getInt("curYear") +"�me ann�e.\n";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean addSubjectToFormation(int id){
		// TODO
		return false;
	}
	
	public static int searchFormationsByName(String answerFormation){
		try {
			Statement state = DBConnection.getInstance().createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM formation WHERE name LIKE \"%"+answerFormation+"%\"");
			while(result.next()){
				System.out.println(result.getInt("id")+" Diplome d'Ing�nieur "+result.getString("name"));
			}
			int id = Main.sc.nextInt();
			Main.sc.nextLine();
			return id;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return -1;
	}
	
	public void addFormation(){
		try {
			Statement state = DBConnection.getInstance().createStatement();
			state.executeUpdate("INSERT INTO `formation`(`name`, `nbYear`, `curYear`) VALUES ('"+this.getName()+"','"+this.getNbYear()+"','"+this.getCurYear()+"')");
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
