package fr.upem.projectJava.studentManagerProject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


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
		Scanner sc = new Scanner(System.in);
		do{
			System.out.print("Nom de la fili�re : ");
			this.name=sc.nextLine();
			if(this.name.length()>50)
				System.out.println("Le nom de la fili�re entr� est trop long");
		}
		while(this.name.length()>50);
		System.out.print("Nombre d'ann�e total de la fili�re : ");
		this.nbYear=sc.nextInt();
		do{
		System.out.print("Ann�e de la fili�re :");
		this.curYear=sc.nextInt();
		if(curYear>nbYear)
			System.out.println("L'ann�e entr� n'est pas disponible pour cette fili�re");
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
		Scanner sc = null;
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
						String answerSubject = "";
						System.out.println("Entrer le nom de la mati�re : ");
						try{
							answerSubject = sc.nextLine();
							if(!Formation.addSubjectToFormation(Subject.searchSubjectsByName(answerSubject),id)){
								System.out.println("La mati�re "+answerSubject+" n'�xiste pas.");
								System.out.println("Appuyez sur Entrer pour continuer.");
								sc.nextLine();
								}
							if(answerSubject.length()<0){
								System.out.println("Ceci n'est pas un nom de mati�re.");
								System.out.println("Appuyez sur Entrer pour continuer.");
								sc.nextLine();
							}
						}catch(InputMismatchException e){
							System.out.println("Ceci n'est pas une mati�re.");
							System.out.println("Appuyez sur Entrer pour continuer.");
							sc.nextLine();
						}
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
			return null;
		}
		return null;
	}
	
	public static boolean addSubjectToFormation(int idSubject, int idFormation){
		Scanner sc = new Scanner(System.in);
		if(idSubject == -1){
			System.out.println("La mati�re n'�xiste pas.");
			System.out.println("Appuyez sur Entrer pour continuer.");
			sc.nextLine();
			return false;
		}
		int year = Year.getActualCurrentYear();
		System.out.println("Choisissez coefficient � appliquer � cette mati�re : ");
		int coef = sc.nextInt();
		sc.nextLine();
		Statement state;
		try {
			state = DBConnection.getInstance().createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM year_formation_subject WHERE year = "+year+" AND idFormation = "+idFormation+" AND idSubject = "+idSubject);
			if(result.next()){
				System.out.println("La mati�re est d�j� attrribu�e � cette formation, \n"
						+ "Voulez-vous modifier le coefficient? (Oui/Non) ");
				String valid = sc.nextLine();
				System.out.println("\nValidez-vous cet �tudiant? (Oui/Non)");
				do{
					valid = sc.next();
				}
				while(!valid.equalsIgnoreCase("non") && !valid.equalsIgnoreCase("n") && !valid.equalsIgnoreCase("oui") && !valid.equalsIgnoreCase("o"));
				if(valid.equalsIgnoreCase("non") || valid.equalsIgnoreCase("n"))
					return false;
				else {
					//return fonction();
				}
			}
			state.executeUpdate("INSERT INTO year_formation_subject VALUES("+year+","+idFormation+","+idSubject+","+coef+")");
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public static int searchFormationsByName(String answerFormation){
		try {
			Statement state = DBConnection.getInstance().createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM formation WHERE name LIKE \"%"+answerFormation+"%\"");
			while(result.next()){
				System.out.println(result.getInt("id")+" Diplome d'Ing�nieur "+result.getString("name"));
			}
			Scanner sc = new Scanner(System.in);
			int id = sc.nextInt();
			sc.nextLine();
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
