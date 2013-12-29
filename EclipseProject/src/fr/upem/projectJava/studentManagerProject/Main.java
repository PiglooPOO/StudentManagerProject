package fr.upem.projectJava.studentManagerProject;

public class Main {
	
	public static void main(String[] args){
		startMenu();
	}
	
	public static void startMenu(){
		
		int choiceNumber = 16;
		System.out.println("Bienvenue dans StudentMangager de l'Ecole Pigloo");
		System.out.println("Menu\n");
		System.out.println("1 Ajouter un élève\n2 Rechercher un élève\n3 Ajouter une filière\n4 Rechercher une filière");
		System.out.println("5 Ajouter une matière\n6 Rechercher une matière\n7 Ajouter des notes\n8 Rechercher des notes");
		System.out.println("9 Ajouter une année\n10 Inscrire un élève dans une filière et année\n11 Editer les diplômes");
		System.out.println("12 Charger une base de données\n13 Enregistrer une base de données\n14 Modifier la configuration");
		System.out.println("15 Editer une attestation de réussite\n16 Quitter");
		
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