package fr.upem.projectJava.studentManagerProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		checkConnection();
		Scanner sc = null;
		int choiceNumber = 1;
		
		while(choiceNumber!=0){
			if(choiceNumber == 1)
				drawMenu();
			System.out.print("Entrer le chiffre correspondant à votre choix : ");
			try {
				sc = new Scanner(System.in);
				
				while((choiceNumber = sc.nextInt())<0 || choiceNumber>14){
					sc.nextLine();
					System.out.print("Ce choix est invalide, recommencez : ");
				}
				sc.nextLine();
			} catch(InputMismatchException e){
				System.out.println("Ce choix est invalide, ");
				choiceNumber = -2;
		    }
			startSubMenu(choiceNumber);
			if(choiceNumber != 0 && choiceNumber != -2)
				choiceNumber = 1;
			//clearConsole();
		}
		if(sc != null)
			sc.close();
		System.out.println("\nFermeture du Programme");
	}
	
	
	public static void checkConnection(){
		for (int i = 1; (DBConnection.getInstance()==null); i++) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(i>10){
				System.out.println("\n\tErreur de la connection avec la BDD");
				return;
			}
			System.out.println(i+")\tErreur de la connection avec la BDD, nouvel essaie dans 3 secondes.");
		}
	}
	
	
	public static void drawMenu(){
		System.out.println("Bienvenue dans StudentMangager de l'Ecole Pigloo\n");
		System.out.println("Menu\n");
		System.out.println("1 Ajouter un élève\n"
				+ "2 Rechercher un élève\n"
				+ "3 Ajouter une filière\n"
				+ "4 Rechercher une filière ou une matière\n"
		        + "5 Ajouter une matière\n"
		        + "8 Ajouter une année\n"
		        + "10 Editer les diplômes\n"
				+ "11 Charger une base de données\n"
				+ "12 Enregistrer une base de données\n"
				+ "13 Modifier la configuration\n"
				+ "0 Quitter");
	}
	
	public static int startSubMenu(int choiceNumber){
		switch(choiceNumber){
			case 1:
				Student e=new Student();
				e.addStudent();
				break;
			case 2:
				startMenuSearchStudent();
				break;
			case 3: 
				Formation f = new Formation();
				f.addFormation();
				break;
			case 4:
				startMenuSearchFormation();
				break;
			case 5:
				Subject m = new Subject();
				m.addSubject();
				break;
			case 6:
				// Ajouter des notes
				break;
			case 7:
				// Rechercher des notes
				break;
			case 8:
				// Ajouter une année
				break;
			case 9:
				// Inscrire un élève dans une filière et année.
				break;
			case 10:
				// Editer les diplômes
				break;
			case 11:
				// Charger une bade de données
				break;
			case 12:
				DBConnection.saveDB();
				break;
			case 13:
				// Modifier la configuration
				break;
			case 14:
				// Editer une attestation de réussite
				break;
			case 15:
				break;
			default:;
		}
		return choiceNumber;
	}
	
	private static void startMenuSearchStudent(){
		Scanner sc = null;
		int choiceNumber = 1;
		String str;
		int number;
		while(choiceNumber!=0){
			if(choiceNumber == 1)
				System.out.println("1 Rechercher par numéro d'étudiant\n"
						+ "2 Rechercher par Nom\n"
						+ "3 Rechercher par Prénom\n"
						+ "4 Rechercher par Matière\n"
						+ "5 Rechercher par Filière\n"
						+ "6 Rechercher par Année\n"
						+ "7 Rechercher par Année et Filière\n"
						+ "0 Retour au Menu principal\n");
			System.out.print("Entrer le chiffre correspondant à votre choix : ");
			try {
				sc = new Scanner(System.in);
				
				while((choiceNumber = sc.nextInt())<0 || choiceNumber>7){
					sc.nextLine();
					System.out.print("Ce choix est invalide, recommencez : ");
				}
				sc.nextLine();
			} catch(InputMismatchException e){
				System.out.println("Ce choix est invalide, ");
				choiceNumber = -2;
		    }
			switch(choiceNumber){
				case 1:
					System.out.print("Entrez le numéro étudiant de l'étudiant : ");
					try {
						number = sc.nextInt();
						if(!Student.showStudent(number)){
							System.out.println("L'étudiant "+number+" n'éxiste pas.");
							System.out.println("Appuyez sur Entrer pour continuer.");
<<<<<<< HEAD
							sc.nextLine();
=======
							sc.nextLine();sc.nextLine();
>>>>>>> 3095401cd3baf2a090e50810be343d4a14e48c4f
						}
						if(number<0){
							System.out.println("Ceci n'est pas un numéro étudiant.");
							System.out.println("Appuyez sur Entrer pour continuer.");
<<<<<<< HEAD
							sc.nextLine();
=======
							sc.nextLine();sc.nextLine();
>>>>>>> 3095401cd3baf2a090e50810be343d4a14e48c4f
						}
						
					} catch (InputMismatchException e) {
						System.out.println("Ceci n'est pas un numéro étudiant.");
						System.out.println("Appuyez sur Entrer pour continuer.");
<<<<<<< HEAD
						sc.nextLine();
=======
						sc.nextLine();sc.nextLine();
>>>>>>> 3095401cd3baf2a090e50810be343d4a14e48c4f
					}
					
					break;
				case 2:
					System.out.print("Entrez le nom de l'étudiant : ");
					try {
						str = sc.nextLine();
						if(!Student.showStudentsByName(str)){
							System.out.println("L'étudiant "+str+" n'éxiste pas.");
							System.out.println("Appuyez sur Entrer pour continuer.");
<<<<<<< HEAD
							sc.nextLine();
=======
							sc.nextLine();sc.nextLine();
>>>>>>> 3095401cd3baf2a090e50810be343d4a14e48c4f
						}
						if(str == ""){
							System.out.println("Ceci n'est pas un nom.");
							System.out.println("Appuyez sur Entrer pour continuer.");
<<<<<<< HEAD
							sc.nextLine();
=======
							sc.nextLine();sc.nextLine();
>>>>>>> 3095401cd3baf2a090e50810be343d4a14e48c4f
						}
					} catch (InputMismatchException e) {
						System.out.println("Ceci n'est pas un nom.");
						System.out.println("Appuyez sur Entrer pour continuer.");
<<<<<<< HEAD
						sc.nextLine();
					}
					break;
				case 3:
					System.out.print("Entrez le nom de l'étudiant : ");
					try {
						str = sc.nextLine();
						if(!Student.showStudentsByFirstName(str)){
							System.out.println("L'étudiant "+str+" n'éxiste pas.");
							System.out.println("Appuyez sur Entrer pour continuer.");
							sc.nextLine();
						}
						if(str == ""){
							System.out.println("Ceci n'est pas un nom.");
							System.out.println("Appuyez sur Entrer pour continuer.");
							sc.nextLine();
						}
					} catch (InputMismatchException e) {
						System.out.println("Ceci n'est pas un nom.");
						System.out.println("Appuyez sur Entrer pour continuer.");
						sc.nextLine();
					}
=======
						sc.nextLine();sc.nextLine();
					}
					break;
				case 3:
>>>>>>> 3095401cd3baf2a090e50810be343d4a14e48c4f
					break;
				case 4:
					break;
				case 5:
					break;
				case 6:
					break;
				case 7:
					break;
				case 8:
					break;
				default:
			}
			
			if(choiceNumber != 0 && choiceNumber != -2)
				choiceNumber = 1;
			//clearConsole();
		}
	}
	
	private static void startMenuSearchFormation(){
		Scanner sc = null;
		int choiceNumber = 1;
		
		while(choiceNumber!=0){
			if(choiceNumber == 1)
				System.out.println(">> Rechercher dans la structure des filières\n"
						+ "1 Rechercher une de filière\n"
						+ "2 Rechercher une de matière\n"
						+ "0 Retour au Menu\n\n");
			System.out.print("Entrer le chiffre correspondant à votre choix : ");
			try {
				sc = new Scanner(System.in);
				
				while((choiceNumber = sc.nextInt())<0 || choiceNumber>2){
					sc.nextLine();
					System.out.print("Ce choix est invalide, recommencez : ");
				}
				sc.nextLine();
			} catch(InputMismatchException e){
				System.out.println("Ce choix est invalide, ");
				choiceNumber = -2;
		    }
			/**
			 * start submenu
			 */
			switch(choiceNumber){
			case 1:
				String answerFormation = "";
				System.out.println("Entrer le nom de la filière : ");
				try{
					answerFormation = sc.nextLine();
					//TODO
					if(!Formation.searchFormationsByName(answerFormation)){
						System.out.println("La filière "+answerFormation+" n'éxiste pas.");
						System.out.println("Appuyez sur Entrer pour continuer.");
						sc.nextLine();
						}
					if(answerFormation.length()<0){
						System.out.println("Ceci n'est pas un nom de filière.");
						System.out.println("Appuyez sur Entrer pour continuer.");
						sc.nextLine();
					}
				}catch(InputMismatchException e){
					System.out.println("Ceci n'est pas une filière.");
					System.out.println("Appuyez sur Entrer pour continuer.");
					sc.nextLine();
				}
				break;
			case 2:
				String answerSubject = "";
				System.out.println("Entrer le nom de la filière : ");
				try{
					answerSubject = sc.nextLine();
					if(!Subject.searchSubjectsByName(answerSubject)){
						System.out.println("La matière "+answerSubject+" n'éxiste pas.");
						System.out.println("Appuyez sur Entrer pour continuer.");
						sc.nextLine();
						}
					if(answerSubject.length()<0){
						System.out.println("Ceci n'est pas un nom de matière.");
						System.out.println("Appuyez sur Entrer pour continuer.");
						sc.nextLine();
					}
				}catch(InputMismatchException e){
					System.out.println("Ceci n'est pas une matière.");
					System.out.println("Appuyez sur Entrer pour continuer.");
					sc.nextLine();
				}
				break;
				
			case 3:
				break;
			default:
			}
			/**
			 * end submenu
			 */
			if(choiceNumber != 0 && choiceNumber != -2)
				choiceNumber = 1;
			//clearConsole();
		}
	}	

	private static void clearConsole()
	{
	    for(int i = 0; i < 25; i++)
	    	System.out.println();
	}
}