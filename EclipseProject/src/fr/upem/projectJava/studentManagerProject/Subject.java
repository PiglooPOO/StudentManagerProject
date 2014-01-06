package fr.upem.projectJava.studentManagerProject;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Subject {

	private String name;
	private	int isAvailable;
	
	public Subject(String name, int coefficient) {
		this.name=name;
	}
	
	public Subject(){
		Scanner sc = new Scanner(System.in);
		do{
			System.out.print("Entrer le nom de la matière : ");
			this.name=sc.nextLine();
			if(this.name.length()>30)
				System.out.println("Le nom de la matière entré est trop long.");
		}
		while(this.name.length()>30);
	}

	public String getName() {
		return name;
	}
	
	public static boolean searchSubjectsByName(String answerSubject){	
		Scanner sc = new Scanner(System.in);
		int choiceNumber = 0;
		int id = 0;
		System.out.println("Que voulez-vous faire ?\n"
				+ "1 Editer\n"
				+ "2 Supprimer\n"
				+ "3 Ajouter à une formation\n"
				+ "4 Visualiser\n"
				+ "5 Quitter\n");
		
		System.out.print(">> ");
		choiceNumber = sc.nextInt();
		sc.nextLine();
		
		
		switch(choiceNumber){
			case 1:
				System.out.println("Entrer le numéro de l'étudiant que vous voulez éditer\n"
						+ ">> ");
				id = sc.nextInt();
				sc.nextLine();
				Subject.editSubject(id);
				break;
			case 2:
				System.out.println("Entrer le numéro de l'étudiant que vous voulez supprimer\n"
						+ ">> ");
				id = sc.nextInt();
				sc.nextLine();
				Subject.deleteSubject(id);
				break;
			case 3:
				Subject sub = new Subject();
				sub.addSubject();
				break;
			case 4:
				System.out.println("Entrer le numéro de l'étudiant que vous voulez visualiser\n"
						+ ">> ");
				id = sc.nextInt();
				sc.nextLine();
				Subject.showSubject(id);
				break;
			case 5:
				// TODO
				break;
			default:
				choiceNumber = -1;
		}
		
		return false;
	}
	
	public void addSubject(){
		try {
			Statement state = DBConnection.getInstance().createStatement();
			state.executeUpdate("INSERT INTO `subject`(`name`) VALUES ('"+this.getName()+"')");
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
		return false;
	}

	public int getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(int isAvailable) {
		this.isAvailable = isAvailable;
	}

}
