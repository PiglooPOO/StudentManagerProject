package fr.upem.projectJava.studentManagerProject;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Subject {

	private String name;

	
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
	
	public void addSubject(){
		try {
			Statement state = DBConnection.getInstance().createStatement();
			state.executeUpdate("INSERT INTO `subject`(`name`) VALUES ('"+this.getName()+"')");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

}
