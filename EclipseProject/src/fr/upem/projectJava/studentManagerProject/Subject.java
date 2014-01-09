package fr.upem.projectJava.studentManagerProject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;

public class Subject {

	private String name;
	private	int isAvailable;
	
	public Subject(String name, int coefficient) {
		this.name=name;
	}
	
	public Subject(int id, String name, int available) {
		this.name=name;
		this.isAvailable = available;
		DBConnection c = new DBConnection();
		c.executeUpdate("INSERT INTO `subject` VALUES ("+id+", '"+name+"', "+available+")");
		c.close();
	}
	
	public Subject(){
		do{
			System.out.print("Entrez le nom de la matière : ");
			this.name = Main.sc.nextLine();
			if(this.name.length()>30)
				System.out.println("Le nom de la matière entré est trop long.");
		}
		while(this.name.length()>30);
	}

	public String getName() {
		return name;
	}
	
	/**
	* Description about the searchSubjectsByName function :
	* This function allows to search the subjects sorted by subjectName.
	* @param <subjetName> what the user is searching for.
	* @return <Integer> return the idSubject if it works, else -1.
	*/
	public static int searchSubjectsByName(String subjectName){	
		DBConnection c = null;
		try {
			c = new DBConnection();
			ResultSet result = c.executeQuery("SELECT * FROM subject WHERE isAvailable = 1 AND name LIKE \"%"+subjectName+"%\"");
			if(!result.next())
				return -1;
			do{
				System.out.println(result.getInt("id")+" "+result.getString("name"));
			}while(result.next());
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
	
	/**
	* Description about the addSubject function :
	* This function allows to add a new subject.
	*/
	public void addSubject(){
		DBConnection c = null;
		c = new DBConnection();
		c.executeUpdate("INSERT INTO `subject`(`id`,`name`) VALUES ('null','"+this.getName()+"')");
		c.close();
	}

	/**
	* Description about the deleteSubject function :
	* This function allows to delete a subject.
	* @param <id> is subject id, to identify a subject (primary key).
	*/
	public static void deleteSubject(int id){
		DBConnection c = null;
		c = new DBConnection();
        c.executeUpdate("UPDATE subject SET isAvailable = 0");
        c.close();
	}
	
	/**
	* Description about the editSubject function :
	* This function allows to edit a subject.
	* @param <id> is subject id, to identify a subject (primary key).
	*/
	public static void editSubject(int id){
		// TODO
	}
	
	/**
	* Description about the showSubject function :
	* This function allows to show the characteristics of a subject.
	* @param <id> is subject id, to identify a subject (primary key).
	* @return <boolean> the function return true if the student exist, else false.
	*/
	public static boolean showSubject(int id){
		// TODO
		
		int choiceNumber = 0;
		DBConnection c=null;
		try {
			c=new DBConnection();
			ResultSet result = c.executeQuery("SELECT * FROM subject WHERE isAvailable = 1 AND id = "+id);
			if(result.next()){
				System.out.println(
						"\nGestion de la matière " + result.getString("name") +" :");
				
				System.out.println("Que voulez-vous faire ?\n"
						+ "1 Editer\n"
						+ "2 Supprimer\n"
						+ "3 Ajouter à une formation\n"
						+ "4 Visualiser les étudiants suivant cette matière.\n"
						+ "0 Quitter\n");
				
				System.out.print(">> ");
				choiceNumber = Main.sc.nextInt();
				Main.sc.nextLine();
				
				switch(choiceNumber){
					case 1:
						//TODO
						Subject.editSubject(id);
						break;
					case 2:
						//TODO
						Subject.deleteSubject(id);
						break;
					case 3:
						//TODO
						Subject.addSubjectToFormation(id);
						break;
					case 4:
						Student.showStudentsBySubject(id);
						break;
					default:
						// TODO
						break;
				}
				return true;
			}
		} catch (SQLException e) {
			if(c!=null)
				c.close();
			e.printStackTrace();
		}
		return false;
	}

	/**
	* Description about the addSubjectToFormation function :
	* This function allows to a subject to a formation.
	* @param <id> is subject id, to identify a subject (primary key).
	* @return <boolean> return true if it works, else false.
	*/
	private static boolean addSubjectToFormation(int id) {
		/**
		 * cherchons la matière à ajouter CURRENTLY WORKIN ON
		 */
		String answerSubject = "";
		int idSubject = 0;
		System.out.println("Entrez le nom de la matière à ajouter : ");
		try{
			answerSubject = Main.sc.nextLine();
			//TODO
			if((idSubject = Subject.searchSubjectsByName(answerSubject)) == -1){
				System.out.println("La filière n'éxiste pas dans cette filière.");
				System.out.println("Appuyez sur Entrée pour revenir à la fiche.");
				Main.sc.nextLine();
				return false;
			}
		}catch(InputMismatchException e){
			System.out.println("Ceci n'est pas une filière.");
			System.out.println("Appuyez sur Entrée pour revenir à la fiche étudiant.");
			Main.sc.nextLine();
			return false;
		}
		
		/**
		 * récupérons l'année actuelle
		 */
		int year = Year.getActualCurrentYear();
		return false;
	}

	public int getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(int isAvailable) {
		this.isAvailable = isAvailable;
	}

	/**
	* Description about the searchSubjectsByNameAndStudentId function :
	* This function allows to search a subject sorted by SubjectName and studentId.
	* @param <subjectName> is the subject name.
	* @param <studentid> is the student id (Stranger key).
	* @return <Integer> return the FormationName, else null.
	*/
	public static int searchSubjectsByNameAndStudentId(String subjectName, int studentid) {
		DBConnection c = null;
		try {
			c = new DBConnection();
			ResultSet result = c.executeQuery("SELECT * FROM subject, year_formation_student, year_formation_subject WHERE subject.isAvailable = 1 AND subject.name LIKE \"%"+subjectName+"%\""
					+ " AND year_formation_student.year = year_formation_subject.year"
					+ " AND year_formation_student.year = "+Year.getActualCurrentYear()
					+ " AND year_formation_student.idStudent = "+studentid
					+ " AND year_formation_student.idFormation = year_formation_subject.idFormation"
					+ " AND subject.id = year_formation_subject.idSubject"
					+ "");
			if(!result.next()){
				c.close();
				return -1;
			}
				
			do{
				System.out.println(result.getInt("id")+" "+result.getString("name"));
			}while(result.next());
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

}
