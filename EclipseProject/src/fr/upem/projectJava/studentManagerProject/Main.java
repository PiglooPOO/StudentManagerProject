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
				System.out.println("Entrer le chiffre correspondant � votre choix !\n");
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
			System.out.println("1 Rechercher par num�ro d'�tudiant\n"
					+ "2 Rechercher par Nom\n"
					+ "3 Rechercher par Pr�nom\n"
					+ "4 Rechercher par Mati�re\n"
					+ "5 Rechercher par Fili�re\n"
					+ "6 Rechercher par Ann�e\n"
					+ "7 Rechercher par Ann�e et Fili�re\n"
					+ "8 Retour au Menu\n");
			System.out.print("Entrez votre choix : ");
			choiceNumber = sc.nextInt();
			switch(choiceNumber)
			{
			case 1:
				int number;
				System.out.print("Entrez le num�ro �tudiant de l'�tudiant : ");
				try {
					number = sc.nextInt();
					if(!Student.showStudent(number)){
						System.out.println("L'�tudiant "+number+" n'�xiste pas.");
						System.out.println("Appuyez sur Entrer pour continuer.");
						sc.nextLine();
					}
					if(number<0){
						System.out.println("Ceci n'est pas un num�ro �tudiant.");
						System.out.println("Appuyez sur Entrer pour continuer.");
						sc.nextLine();
					}
					
				} catch (InputMismatchException e) {
					System.out.println("Ceci n'est pas un num�ro �tudiant.");
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