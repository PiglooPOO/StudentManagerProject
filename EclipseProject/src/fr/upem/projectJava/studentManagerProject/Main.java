package fr.upem.projectJava.studentManagerProject;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
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
		startMenu();
	}
	
	public static void startMenu(){
		
		int choiceNumber = 0;
		Scanner sc = null;
		
		while(choiceNumber!=16){
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
			try {
				sc = new Scanner(System.in);
				System.out.println("Entrer le chiffre correspondant à votre choix !\n");
				if(choiceNumber==-1)
					System.out.print("Ce choix est invalide, recommencez : ");
				choiceNumber = sc.nextInt();
			} catch(InputMismatchException e){
		        e.printStackTrace();
		    }
			
			switch(choiceNumber){
			
			case 1:
				Student e=new Student();
				e.addStudent();
				break;
			case 2:
				// Rechercher une élève
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
				if(sc != null)
					sc.close();
				System.out.println("\nFermeture du Programme");
				break;
			default:
				choiceNumber = -1;
			}
			if(choiceNumber!=-1 && choiceNumber!=16)
				choiceNumber=0;
			clearConsole();
		}
	}
	
	private static void clearConsole()
	{
	    for(int i = 0; i < 25; i++)
	    	System.out.println();
	}
}