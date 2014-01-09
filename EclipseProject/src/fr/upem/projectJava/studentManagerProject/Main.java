package fr.upem.projectJava.studentManagerProject;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		checkConnection();
		int choiceNumber = 1;
		
		while(choiceNumber!=0){
			if(choiceNumber == 1)
				drawMenu();
			System.out.print("Entrez le chiffre correspondant � votre choix : ");
			try {				
				while((choiceNumber = Main.sc.nextInt())<0 || choiceNumber>14){
					Main.sc.nextLine();
					System.out.print("Ce choix est invalide, recommencez : ");
				}
				Main.sc.nextLine();
			} catch(InputMismatchException e){
				System.out.println("Ce choix est invalide, ");
				choiceNumber = -2;
		    }
			startSubMenu(choiceNumber);
			if(choiceNumber != 0 && choiceNumber != -2)
				choiceNumber = 1;
			//clearConsole();
		}
		if(Main.sc != null)
			Main.sc.close();
		System.out.println("\nFermeture du Programme");
	}
	
	
	public static void checkConnection(){
		
		DBConnection a;
		for (int i = 1; ; i++) {
			a = new DBConnection();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(i+")\tErreur de la connection avec la BDD, nouvel essaie dans 3 secondes.");

			if(i==10){
				System.out.println("\n\tErreur de la connection avec la BDD");
				return;
			}
			if(a != null){
				a.close();
				break;
			}
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
				startMenuConfig();
				break;
			default:;
		}
		return choiceNumber;
	}
	
	private static void startMenuSearchStudent(){
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
			System.out.print("Entrez le chiffre correspondant � votre choix : ");
			try {				
				while((choiceNumber = Main.sc.nextInt())<0 || choiceNumber>7){
					Main.sc.nextLine();
					System.out.print("Ce choix est invalide, recommencez : ");
				}
				Main.sc.nextLine();
			} catch(InputMismatchException e){
				System.out.println("Ce choix est invalide, ");
				choiceNumber = -2;
		    }
			switch(choiceNumber){
				case 1:
					System.out.print("Entrez le num�ro �tudiant de l'�tudiant : ");
					try {
						number = Main.sc.nextInt();
						if(!Student.showStudent(number)){
							System.out.println("L'�tudiant "+number+" n'�xiste pas.");
							System.out.println("Appuyez sur Entr�e pour continuer.");
							Main.sc.nextLine();
						}
						if(number<0){
							System.out.println("Ceci n'est pas un num�ro �tudiant.");
							System.out.println("Appuyez sur Entr�e pour continuer.");
							Main.sc.nextLine();
						}
						
					} catch (InputMismatchException e) {
						System.out.println("Ceci n'est pas un num�ro �tudiant.");
						System.out.println("Appuyez sur Entr�e pour continuer.");
						Main.sc.nextLine();
					}
					
					break;
				case 2:
					System.out.print("Entrez le nom de l'�tudiant : ");
					try {
						str = Main.sc.nextLine();
						if(!Student.showStudentsByName(str)){
							System.out.println("L'�tudiant "+str+" n'�xiste pas.");
							System.out.println("Appuyez sur Entr�e pour continuer.");
							Main.sc.nextLine();
						}
						if(str == ""){
							System.out.println("Ceci n'est pas un nom.");
							System.out.println("Appuyez sur Entr�e pour continuer.");
							Main.sc.nextLine();
						}
					} catch (InputMismatchException e) {
						System.out.println("Ceci n'est pas un nom.");
						System.out.println("Appuyez sur Entr�e pour continuer.");
						Main.sc.nextLine();
					}
					break;
				case 3:
					System.out.print("Entrez le nom de l'�tudiant : ");
					try {
						str = Main.sc.nextLine();
						if(!Student.showStudentsByFirstName(str)){
							System.out.println("L'�tudiant "+str+" n'�xiste pas.");
							System.out.println("Appuyez sur Entr�e pour continuer.");
							Main.sc.nextLine();
						}
						if(str == ""){
							System.out.println("Ceci n'est pas un nom.");
							System.out.println("Appuyez sur Entr�e pour continuer.");
							Main.sc.nextLine();
						}
					} catch (InputMismatchException e) {
						System.out.println("Ceci n'est pas un nom.");
						System.out.println("Appuyez sur Entr�e pour continuer.");
						Main.sc.nextLine();
					}
					break;
				case 4:
					//TODO
					break;
				case 5:
					System.out.print("Entrez le nom de la fili�re : ");
					try {
						str = Main.sc.nextLine();
						if(!Student.showStudentsByFormationName(str)){
							System.out.println("La fili�re "+str+" n'�xiste pas.");
							System.out.println("Appuyez sur Entr�e pour continuer.");
							Main.sc.nextLine();
						}
						if(str == ""){
							System.out.println("Ceci n'est pas un nom de fili�re.");
							System.out.println("Appuyez sur Entr�e pour continuer.");
							Main.sc.nextLine();
						}
					} catch (InputMismatchException e) {
						System.out.println("Ceci n'est pas un nom de fili�re.");
						System.out.println("Appuyez sur Entr�e pour continuer.");
						Main.sc.nextLine();
					}
					break;
				case 6:
					System.out.print("Entrez le num�ro �tudiant de l'�tudiant : ");
					try {
						number = Main.sc.nextInt();
						if(!Student.showStudentsByYear(number)){
							System.out.println("Pas d'�tudiant inscrit pour l'ann�e "+number+".");
							System.out.println("Appuyez sur Entr�e pour continuer.");
							Main.sc.nextLine();
						}
						if(number<0){
							System.out.println("Cette ann�e n'est pas prise en charge.");
							System.out.println("Appuyez sur Entr�e pour continuer.");
							Main.sc.nextLine();
						}
						
					} catch (InputMismatchException e) {
						System.out.println("Ceci n'est pas une ann�e.");
						System.out.println("Appuyez sur Entr�e pour continuer.");
						Main.sc.nextLine();
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
		int choiceNumber = 1;
		
		while(choiceNumber!=0){
			if(choiceNumber == 1)
				System.out.println(">> Rechercher dans la structure des fili�res\n"
						+ "1 Rechercher une fili�re\n"
						+ "2 Rechercher une mati�re\n"
						+ "0 Retour au Menu\n\n");
			System.out.print("Entrez le chiffre correspondant � votre choix : ");
			try {	
				while((choiceNumber = Main.sc.nextInt())<0 || choiceNumber>2){
					Main.sc.nextLine();
					System.out.print("Ce choix est invalide, recommencez : ");
				}
				Main.sc.nextLine();
			} catch(InputMismatchException e){
				System.out.println("Ce choix est invalide, ");
				choiceNumber = -2;
		    }

			switch(choiceNumber){
			case 1:
				String answerFormation = "";
				System.out.println("Entrez le nom de la fili�re : ");
				try{
					answerFormation = Main.sc.nextLine();
					//TODO
					if(!Formation.showFormation(Formation.searchFormationsByName(answerFormation))){
						System.out.println("Cette fili�re n'�xiste pas.");
						System.out.println("Appuyez sur Entr�e pour continuer.");
						Main.sc.nextLine();
						}
					if(answerFormation.length()<0){
						System.out.println("Cette fili�re n'�xiste pas.");
						System.out.println("Appuyez sur Entr�e pour continuer.");
						Main.sc.nextLine();
					}
				}catch(InputMismatchException e){
					System.out.println("Ceci n'est pas une fili�re.");
					System.out.println("Appuyez sur Entr�e pour continuer.");
					Main.sc.nextLine();
				}
				break;
			case 2:
				String answerSubject = "";
				System.out.println("Entrez le nom de la mati�re : ");
				try{
					answerSubject = Main.sc.nextLine();
					Subject.showSubject(Subject.searchSubjectsByName(answerSubject));
					if(answerSubject.length()<0){
						System.out.println("Ceci n'est pas un nom de mati�re.");
						System.out.println("Appuyez sur Entr�e pour continuer.");
						Main.sc.nextLine();
					}
				}catch(InputMismatchException e){
					System.out.println("Ceci n'est pas une mati�re.");
					System.out.println("Appuyez sur Entr�e pour continuer.");
					Main.sc.nextLine();
				}
				break;
			default:
			}

			if(choiceNumber != 0 && choiceNumber != -2)
				choiceNumber = 1;
			//clearConsole();
		}
	}	
	
	public static void startMenuConfig(){
		int choiceNumber = 1;
		
		while(choiceNumber!=0){
			if(choiceNumber == 1)
				System.out.println(">> Modifier la configuration de l'�cole\n"
						+ "1 Changer le nom de l'�cole\n"
						+ "2 Changer le directeur de l'�cole\n"
						+ "0 Retour au Menu\n\n");
			System.out.print("Entrez le chiffre correspondant � votre choix : ");
			try {				
				while((choiceNumber = Main.sc.nextInt())<0 || choiceNumber>14){
					Main.sc.nextLine();
					System.out.print("Ce choix est invalide, recommencez : ");
				}
				Main.sc.nextLine();
			} catch(InputMismatchException e){
				System.out.println("Ce choix est invalide, ");
				choiceNumber = -2;
		    }
			switch(choiceNumber){
			case 1:
				School.changeName();
				break;
			case 2:
				School.changeDirector();
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
		try {
			  if(System.getProperty("os.name").startsWith("Windows" ))
			    Runtime.getRuntime().exec("cls");
			  else
			    Runtime.getRuntime().exec("clear");
			} catch(Exception excpt) {
			  for(int i=0;i<100;i++)
			    System.out.println();
			}
	}
}