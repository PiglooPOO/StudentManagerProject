package fr.upem.projectJava.studentManagerProject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
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
			System.out.print("Nom de la filière : ");
			this.name = Main.sc.nextLine();
			if(this.name.length()>50)
				System.out.println("Le nom de la filière entré est trop long");
		}
		while(this.name.length()>50);
		System.out.print("Nombre d'année total de la filière : ");
		this.nbYear = Main.sc.nextInt();
		do{
		System.out.print("Année de la filière :");
		this.curYear = Main.sc.nextInt();
		if(curYear>nbYear)
			System.out.println("L'année entrée n'est pas disponible pour cette filière");
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
		DBConnection c = null;
		try {
			int choiceNumber = 1;
			
			while(choiceNumber!=0){
				if(choiceNumber == 1){
					c = new DBConnection();
					ResultSet result = c.executeQuery("SELECT * FROM formation WHERE isAvailable = 1 AND id = "+id);
					if(result.next()){
						System.out.println(
								"\nGestion du Diplome d'ingénieur " + result.getString("name") +" "+ result.getInt("curYear") +"ème année.\n"
								+ "Que voulez-vous faire ?\n"
								+ "1 Editer\n"
								+ "2 Supprimer\n"
								+ "3 Ajouter des matières\n"
								+ "4 Visualiser les étudiants suivant cette formation\n"
								+ "0 Quitter\n");
						
						System.out.print(">> ");
						try {				
								while((choiceNumber = Main.sc.nextInt())<0 || choiceNumber>4){
									choiceNumber = Main.sc.nextInt();
									System.out.print("Ce choix est invalide, recommencez : ");
								}
								Main.sc.nextLine();
							}catch(InputMismatchException e){
								System.out.println("Ce choix est invalide, ");
								choiceNumber = -2;
						    }
						}
					else{
						c.close();
						return false;
					}
					c.close();
					}
				}
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
						System.out.println("Entrer le nom de la matière : ");
						try{
							answerSubject = Main.sc.nextLine();
							if(!Formation.addSubjectToFormation(Subject.searchSubjectsByName(answerSubject),id)){
								System.out.println("La matière "+answerSubject+" n'est pas disponible.");
								System.out.println("Appuyez sur Entrer pour continuer.");
								Main.sc.nextLine();
								}
							if(answerSubject.length()<0){
								System.out.println("Ceci n'est pas un nom de matière.");
								System.out.println("Appuyez sur Entrer pour continuer.");
								Main.sc.nextLine();
							}
						}catch(InputMismatchException e){
							System.out.println("Ceci n'est pas une matière.");
							System.out.println("Appuyez sur Entrer pour continuer.");
							Main.sc.nextLine();
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
				c.close();
				return true;
			}
			catch (SQLException e) {
			if(c!=null)
				c.close();
			e.printStackTrace();
		}
		return false;
	}
	
	public static String FormationNameByStudentId(int id){
		DBConnection c = null;
		try {
			c = new DBConnection();
			ResultSet result = c.executeQuery("SELECT * FROM formation, year_formation_student WHERE isAvailable = 1 AND id = idFormation AND year = "+Year.getActualCurrentYear()+" AND idStudent = "+id);
			if(result.next()){
				String str = result.getString("name") +" "+ result.getInt("curYear") +"ème année.\n";
				c.close();
				return str;
			}
			c.close();
			return "Cet étudiant ne suit actuellement pas de formation";
		} catch (SQLException e) {
			if(c!=null)
				c.close();
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static boolean addSubjectToFormation(int idSubject, int idFormation){
		if(idSubject == -1){
			System.out.println("La matière n'éxiste pas.");
			System.out.println("Appuyez sur Entrer pour continuer.");
			Main.sc.nextLine();
			return false;
		}
		if(idFormation == -1){
			System.out.println("La formation n'éxiste pas.");
			System.out.println("Appuyez sur Entrer pour continuer.");
			Main.sc.nextLine();
			return false;
		}
		int year = Year.getActualCurrentYear();
		int coef = 0;
		DBConnection c=null;
		try {
			c = new DBConnection();
			ResultSet result = c.executeQuery("SELECT * FROM year_formation_subject WHERE year = "+year+" AND idFormation = "+idFormation+" AND idSubject = "+idSubject);
			if(result.next()){
				System.out.println("La matière est déjà attribuée à cette formation, \n"
						+ "Voulez-vous modifier le coefficient qui est actuellement de "+result.getInt("coef")+"? (Oui/Non) ");
				String valid = Main.sc.nextLine();

				do{
					valid = Main.sc.next();
				}while(!valid.equalsIgnoreCase("non") && !valid.equalsIgnoreCase("n") && !valid.equalsIgnoreCase("oui") && !valid.equalsIgnoreCase("o"));
				
				if(valid.equalsIgnoreCase("non") || valid.equalsIgnoreCase("n")){
					c.close();
					return false;
				}
				else {
					System.out.print("Entrez le coefficient : ");
					coef = Main.sc.nextInt();
					
					while(coef<1){
						System.out.println("Un coefficient doit être positif : ");
						coef = Main.sc.nextInt();
					}
					c.executeUpdate("UPDATE year_formation_subject SET coef = "+coef+" WHERE year = "+year+" AND idFormation = "+idFormation+" AND idSubject = "+idSubject);
					c.close();
					return true;
				}
			}
			System.out.print("Choisissez coefficient à appliquer à cette matière : ");
			coef = Main.sc.nextInt();
			Main.sc.nextLine();
			c.executeUpdate("INSERT INTO year_formation_subject VALUES("+year+","+idFormation+","+idSubject+","+coef+")");
			c.close();
			return true;
		} catch (SQLException e) {
			if(c!=null)
				c.close();
			e.printStackTrace();
			return false;
		}
	}
	
	public static int searchFormationsByName(String answerFormation){
		DBConnection c = null;
		try {
			c = new DBConnection();
			ResultSet result = c.executeQuery("SELECT * FROM formation WHERE name LIKE \"%"+answerFormation+"%\"");
			while(result.next()){
				System.out.println(result.getInt("id")+"\t"+result.getString("name")+" "+result.getInt("curYear")+"e année");
			}
			System.out.print("Entrez le chiffre correspondant à votre choix : ");
			int id = Main.sc.nextInt();
			Main.sc.nextLine();
			c.close();
			return id;
		} catch (SQLException e) {
			if(c!=null)
				c.close();
			e.printStackTrace();
		}

		return -1;
	}
	
	public void addFormation(){
		DBConnection c = null;
		c = new DBConnection();
		c.executeUpdate("INSERT INTO `formation`(`id`, `name`, `nbYear`, `curYear`) VALUES ('null','"+this.getName()+"','"+this.getNbYear()+"','"+this.getCurYear()+"')");
		c.close();
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
