package fr.upem.projectJava.studentManagerProject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Subject {

	private String name;
	private	int isAvailable;
	
	public Subject(String name, int coefficient) {
		this.name=name;
	}
	
	public Subject(){
		do{
			System.out.print("Entrez le nom de la mati�re : ");
			this.name = Main.sc.nextLine();
			if(this.name.length()>30)
				System.out.println("Le nom de la mati�re entr� est trop long.");
		}
		while(this.name.length()>30);
	}

	public String getName() {
		return name;
	}
	
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
	
	public void addSubject(){
		DBConnection c = null;
		c = new DBConnection();
		c.executeUpdate("INSERT INTO `subject`(`id`,`name`) VALUES ('null','"+this.getName()+"')");
		c.close();
	}

	public static void deleteSubject(int id){
		// TODO
	}
	
	public static void editSubject(int id){
		// TODO
	}
	
	public static boolean showSubject(int id){
		// TODO
		
		int choiceNumber = 0;
		DBConnection c=null;
		try {
			c=new DBConnection();
			ResultSet result = c.executeQuery("SELECT * FROM subject WHERE isAvailable = 1 AND id = "+id);
			if(result.next()){
				System.out.println(
						"\nGestion de la mati�re " + result.getString("name") +" :");
				
				System.out.println("Que voulez-vous faire ?\n"
						+ "1 Editer\n"
						+ "2 Supprimer\n"
						+ "3 Ajouter � une formation\n"
						+ "4 Visualiser les �tudiants suivant cette mati�re.\n"
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
						Subject.deleteFormation(id);
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

	private static void addSubjectToFormation(int id) {
		// TODO Auto-generated method stub
		
	}

	private static void deleteFormation(int id) {
		// TODO Auto-generated method stub
		
	}

	public int getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(int isAvailable) {
		this.isAvailable = isAvailable;
	}

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
