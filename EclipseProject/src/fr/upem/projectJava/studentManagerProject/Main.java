package fr.upem.projectJava.studentManagerProject;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args){
		startMenu();
	}
	
	public static void startMenu(){
		
		int choiceNumber = 16;
		System.out.println("Bienvenue dans StudentMangager de l'Ecole Pigloo\n");
		System.out.println("Menu\n");
		System.out.println("1 Ajouter un élève\n"
				+ "2 Rechercher un élève\n"
				+ "3 Ajouter une filière\n"
				+ "4 Rechercher une filière"
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
				+ "15 Editer une attestation de réussite\n16 Quitter");
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Entrer le chiffre correspondant à votre choix !\n");
		choiceNumber = sc.nextInt();
		
		switch(choiceNumber){
		
		case 1:
			// Ajouter un élève
			break;
		case 2:
			// Rechercher une élève
			break;
		case 3: 
			// Ajouter une filière
			break;
		case 4:
			// Rechercher une filière
			break;
		case 5:
			// Ajouter une matière
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
			// Quitter ?
			break;
		default:
			
		}
		
	}
}