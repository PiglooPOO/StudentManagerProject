package fr.upem.projectJava.studentManagerProject;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		checkConnection();
		
		int choiceNumber = 0;
		Scanner sc = null;
		
		while(choiceNumber!=16){
			drawMenu();
			try {
				sc = new Scanner(System.in);
				System.out.println("Entrer le chiffre correspondant à votre choix !\n");
				if(choiceNumber==-1)
					System.out.print("Ce choix est invalide, recommencez : ");
				choiceNumber = sc.nextInt();
			} catch(InputMismatchException e){
				choiceNumber = -1;
		    }
			startSubMenu(choiceNumber);

			if(choiceNumber!=-1 && choiceNumber!=16)
				choiceNumber=0;
			clearConsole();
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
				+ "4 Rechercher une filière\n"
		        + "5 Ajouter une matière\n"
		        + "6 Rechercher une matière\n"
		        + "7 Ajouter des notes\n"
		        + "8 Rechercher des notes\n"
		        + "9 Ajouter une année\n"
		        + "10 Inscrire un élève dans une filière et année\n"
		        + "11 Editer les diplômes\n"
				+ "12 Charger une base de données\n"
				+ "13 Enregistrer une base de données\n"
				+ "14 Modifier la configuration\n"
				+ "15 Editer une attestation de réussite\n"
				+ "16 Quitter");
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
				// Rechercher une filière
				break;
			case 5:
				Subject m = new Subject();
				m.addSubject();
				break;
			case 6:
				// Rechercher une matière
				break;
			case 7:
				// Ajouter des notes
				break;
			case 8:
				// Rechercher des notes
				break;
			case 9:
				// Ajouter une année
				break;
			case 10:
				// Inscrire un élève dans une filière et année.
				break;
			case 11:
				// Editer les diplômes
				break;
			case 12:
				// Charger une bade de données
				break;
			case 13:
				// Enregistrer la bade de données
				break;
			case 14:
				// Modifier la configuration
				break;
			case 15:
				// Editer une attestation de réussite
				break;
			case 16:
				break;
			default:
				choiceNumber = -1;
		}
		return choiceNumber;
	}
	
	private static void startMenuSearchStudent(){
		int choiceNumber = 0;
		
		do{
			Scanner sc = new Scanner(System.in);
			System.out.println("1 Rechercher par numéro d'étudiant\n"
					+ "2 Rechercher par Nom\n"
					+ "3 Rechercher par Prénom\n"
					+ "4 Rechercher par Matière\n"
					+ "5 Rechercher par Filière\n"
					+ "6 Rechercher par Année\n"
					+ "7 Rechercher par Année et Filière\n"
					+ "8 Retour au Menu\n");
			System.out.print("Entrez votre choix : ");
			choiceNumber = sc.nextInt();
			switch(choiceNumber)
			{
			case 1:
				int number;
				System.out.print("Entrez le numéro étudiant de l'étudiant : ");
				try {
					number = sc.nextInt();
					if(!Student.showStudent(number)){
						System.out.println("L'étudiant "+number+" n'éxiste pas.");
						System.out.println("Appuyez sur Entrer pour continuer.");
						sc.nextLine();
					}
					if(number<0){
						System.out.println("Ceci n'est pas un numéro étudiant.");
						System.out.println("Appuyez sur Entrer pour continuer.");
						sc.nextLine();
					}
					
				} catch (InputMismatchException e) {
					System.out.println("Ceci n'est pas un numéro étudiant.");
					System.out.println("Appuyez sur Entrer pour continuer.");
					sc.nextLine();
				}
				
				break;
			case 2:
				break;
			case 3:
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
				choiceNumber = -1;
			}
		}while(choiceNumber != 8);
	}
	
	private static void clearConsole()
	{
	    for(int i = 0; i < 25; i++)
	    	System.out.println();
	}
}