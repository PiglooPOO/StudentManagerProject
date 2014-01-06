package fr.upem.projectJava.studentManagerProject;

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
			System.out.print("Entrer le chiffre correspondant � votre choix : ");
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
				+ "4 Rechercher une fili�re ou une mati�re\n"
		        + "5 Ajouter une mati�re\n"
		        + "6 Ajouter une ann�e\n"
		        + "7 Editer les dipl�mes\n"
				+ "8 Charger une base de donn�es\n"
				+ "9 Enregistrer une base de donn�es\n"
				+ "10 Modifier la configuration\n"
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
				//TODO  Ajouter une ann�e
				break;
			case 7:
				//TODO  Editer les dipl�mes
				break;
			case 8:
				//TODO Charger une base de donn�es
				break;
			case 9:
				DBConnection.saveDB();
				break;
			case 10:
				//TODO Modifier la configuration
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
				System.out.println("1 Rechercher par num�ro d'�tudiant\n"
						+ "2 Rechercher par Nom\n"
						+ "3 Rechercher par Pr�nom\n"
						+ "4 Rechercher par Mati�re\n"
						+ "5 Rechercher par Fili�re\n"
						+ "6 Rechercher par Ann�e\n"
						+ "7 Rechercher par Ann�e et Fili�re\n"
						+ "0 Retour au Menu principal\n");
			System.out.print("Entrer le chiffre correspondant � votre choix : ");
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
					System.out.print("Entrez le nom de l'�tudiant : ");
					try {
						str = sc.nextLine();
						if(!Student.showStudentsByName(str)){
							System.out.println("L'�tudiant "+str+" n'�xiste pas.");
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
					break;
				case 3:
					System.out.print("Entrez le nom de l'�tudiant : ");
					try {
						str = sc.nextLine();
						if(!Student.showStudentsByFirstName(str)){
							System.out.println("L'�tudiant "+str+" n'�xiste pas.");
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
					break;
				case 4:
					//TODO
					break;
				case 5:
					System.out.print("Entrez le nom de la fili�re : ");
					try {
						str = sc.nextLine();
						if(!Student.showStudentsByFormationName(str)){
							System.out.println("La fili�re "+str+" n'�xiste pas.");
							System.out.println("Appuyez sur Entrer pour continuer.");
							sc.nextLine();
						}
						if(str == ""){
							System.out.println("Ceci n'est pas un nom de fili�re.");
							System.out.println("Appuyez sur Entrer pour continuer.");
							sc.nextLine();
						}
					} catch (InputMismatchException e) {
						System.out.println("Ceci n'est pas un nom de fili�re.");
						System.out.println("Appuyez sur Entrer pour continuer.");
						sc.nextLine();
					}
					break;
				case 6:
					System.out.print("Entrez le num�ro �tudiant de l'�tudiant : ");
					try {
						number = sc.nextInt();
						if(!Student.showStudentsByYear(number)){
							System.out.println("Pas d'�tudiant inscrit pour l'ann�e "+number+".");
							System.out.println("Appuyez sur Entrer pour continuer.");
							sc.nextLine();
						}
						if(number<0){
							System.out.println("Cette ann�e n'est pas prise en charge.");
							System.out.println("Appuyez sur Entrer pour continuer.");
							sc.nextLine();
						}
						
					} catch (InputMismatchException e) {
						System.out.println("Ceci n'est pas une ann�e.");
						System.out.println("Appuyez sur Entrer pour continuer.");
						sc.nextLine();
					}
					break;
				case 7:
					//TODO
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
				System.out.println(">> Rechercher dans la structure des fili�res\n"
						+ "1 Rechercher une de fili�re\n"
						+ "2 Rechercher une de mati�re\n"
						+ "0 Retour au Menu\n\n");
			System.out.print("Entrer le chiffre correspondant � votre choix : ");
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

			switch(choiceNumber){
			case 1:
				String answerFormation = "";
				System.out.println("Entrer le nom de la fili�re : ");
				try{
					answerFormation = sc.nextLine();
					//TODO
					if(!Formation.searchFormationsByName(answerFormation)){
						System.out.println("La fili�re "+answerFormation+" n'�xiste pas.");
						System.out.println("Appuyez sur Entrer pour continuer.");
						sc.nextLine();
						}
					if(answerFormation.length()<0){
						System.out.println("Ceci n'est pas un nom de fili�re.");
						System.out.println("Appuyez sur Entrer pour continuer.");
						sc.nextLine();
					}
				}catch(InputMismatchException e){
					System.out.println("Ceci n'est pas une fili�re.");
					System.out.println("Appuyez sur Entrer pour continuer.");
					sc.nextLine();
				}
				break;
			case 2:
				String answerSubject = "";
				System.out.println("Entrer le nom de la fili�re : ");
				try{
					answerSubject = sc.nextLine();
					if(!Subject.searchSubjectsByName(answerSubject)){
						System.out.println("La mati�re "+answerSubject+" n'�xiste pas.");
						System.out.println("Appuyez sur Entrer pour continuer.");
						sc.nextLine();
						}
					if(answerSubject.length()<0){
						System.out.println("Ceci n'est pas un nom de mati�re.");
						System.out.println("Appuyez sur Entrer pour continuer.");
						sc.nextLine();
					}
				}catch(InputMismatchException e){
					System.out.println("Ceci n'est pas une mati�re.");
					System.out.println("Appuyez sur Entrer pour continuer.");
					sc.nextLine();
				}
				break;
				
			case 3:
				break;
			default:
			}

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