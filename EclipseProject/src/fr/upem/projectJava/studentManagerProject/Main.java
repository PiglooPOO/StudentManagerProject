package fr.upem.projectJava.studentManagerProject;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args){
		startMenu();
	}
	
	public static void startMenu(){
		
		int choiceNumber = 0;
		
		while(choiceNumber!=16){
			System.out.println("Bienvenue dans StudentMangager de l'Ecole Pigloo\n");
			System.out.println("Menu\n");
			System.out.println("1 Ajouter un �l�ve\n"
					+ "2 Rechercher un �l�ve\n"
					+ "3 Ajouter une fili�re\n"
					+ "4 Rechercher une fili�re"
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
				Scanner sc = new Scanner(System.in);
				System.out.println("Entrer le chiffre correspondant � votre choix !\n");
				if(choiceNumber==-1)
					System.out.print("Ce choix est invalide, recommencez : ");
				choiceNumber = sc.nextInt();
			} catch(InputMismatchException e){
		        e.printStackTrace();
		    }
			
			switch(choiceNumber){
			
			case 1:
				// Ajouter un �l�ve
				break;
			case 2:
				// Rechercher une �l�ve
				break;
			case 3: 
				// Ajouter une fili�re
				break;
			case 4:
				// Rechercher une fili�re
				break;
			case 5:
				// Ajouter une mati�re
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
				// Quitter ?
				break;
			default:
				choiceNumber = -1;
			}
			if(choiceNumber!=-1 && choiceNumber!=16)
				choiceNumber=0;
			clearConsole();
		}
		System.out.println("\nFermeture du Programme");
	}
	
	private static void clearConsole()
	{
	    try
	    {
	        String os = System.getProperty("os.name");

	        if (os.contains("Windows"))
	        {
	            Runtime.getRuntime().exec("cls");
	        }
	        else
	        {
	            Runtime.getRuntime().exec("clear");
	        }
	    }
	    catch (Exception exception)
	    {
	        //  Handle exception.
	    }
	}
}