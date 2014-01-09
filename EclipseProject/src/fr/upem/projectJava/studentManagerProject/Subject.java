package fr.upem.projectJava.studentManagerProject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Subject {

	private String name;
	private	int isAvailable;
	
	public Subject(String name, int coefficient) {
		this.name=name;
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
	
	public static int searchSubjectsByName(String answerSubject){	
		Statement state;
		try {
			state = new DBConnection().createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM subject WHERE name LIKE \"%"+answerSubject+"%\"");
			if(!result.next())
				return -1;
			do{
				System.out.println(result.getInt("id")+" "+result.getString("name"));
			}while(result.next());
			int id = Main.sc.nextInt();
			Main.sc.nextLine();
			return id;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public void addSubject(){
		try {
			Statement state = new DBConnection().createStatement();
			state.executeUpdate("INSERT INTO `subject`(`id`,`name`) VALUES ('null','"+this.getName()+"')");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
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
		Statement state;
		try {
			state = new DBConnection().createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM subject WHERE id = "+id);
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
			// TODO Auto-generated catch block
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

}
