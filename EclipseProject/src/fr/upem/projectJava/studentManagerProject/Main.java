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
		
		
		int choiceNumber = 0;
		Scanner sc = null;
		
		while(choiceNumber!=16){
			System.out.println("Bienvenue dans StudentMangager de l'Ecole Pigloo\n");
			System.out.println("Menu\n");
			System.out.println("1 Ajouter un �l�ve\n"
					+ "2 Rechercher un �l�ve\n"
					+ "3 Ajouter une fili�re\n"
					+ "4 Rechercher une fili�re\n"
			        + "5 Ajouter une mati�re\n"
			        + "6 Rechercher une mati�re\n"
			        + "7 Ajouter des notes\n"
			        + "8 Rechercher des notes\n"
			        + "9 Ajouter une ann�e\n"
			        + "10 Inscrire un �l�ve dans une fili�re et ann�e\n"
			        + "11 Editer les dipl�mes\n"
					+ "12 Charger une base de donn�es\n"
					+ "13 Enregistrer une base de donn�es\n"
					+ "14 Modifier la configuration\n"
					+ "15 Editer une attestation de r�ussite\n"
					+ "16 Quitter");
			try {
				sc = new Scanner(System.in);
				System.out.println("Entrer le chiffre correspondant � votre choix !\n");
				if(choiceNumber==-1)
					System.out.print("Ce choix est invalide, recommencez : ");
				choiceNumber = sc.nextInt();
			} catch(InputMismatchException e){
				choiceNumber = -1;
		    }
			startMenu(choiceNumber);

			if(choiceNumber!=-1 && choiceNumber!=16)
				choiceNumber=0;
			clearConsole();
		}
		if(sc != null)
			sc.close();
		System.out.println("\nFermeture du Programme");
	}
	
	public static int startMenu(int choiceNumber){
		switch(choiceNumber){
			case 1:
				Student e=new Student();
				e.addStudent();
				break;
			case 2:
				// Rechercher une �l�ve
				break;
			case 3: 
				Formation f = new Formation();
				f.addFormation();
				break;
			case 4:
				// Rechercher une fili�re
				break;
			case 5:
				Subject m = new Subject();
				m.addSubject();
				break;
			case 6:
				// Rechercher une mati�re
				break;
			case 7:
				// Ajouter des notes
				break;
			case 8:
				// Rechercher des notes
				break;
			case 9:
				// Ajouter une ann�e
				break;
			case 10:
				// Inscrire un �l�ve dans une fili�re et ann�e.
				break;
			case 11:
				// Editer les dipl�mes
				break;
			case 12:
				// Charger une bade de donn�es
				break;
			case 13:
				// Enregistrer la bade de donn�es
				break;
			case 14:
				// Modifier la configuration
				break;
			case 15:
				// Editer une attestation de r�ussite
				break;
			case 16:
				if(sc != null)
					sc.close();
				System.out.println("\nFermeture du Programme");
				break;
			default:
				choiceNumber = -1;
		}
<<<<<<< HEAD
		return choiceNumber;
=======
>>>>>>> 08737260bee5bb87c37c0ad9c0795fd70db903be
	}
	
	private static void clearConsole()
	{
	    for(int i = 0; i < 25; i++)
	    	System.out.println();
	}
}