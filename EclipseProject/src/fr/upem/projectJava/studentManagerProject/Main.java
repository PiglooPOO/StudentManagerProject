package fr.upem.projectJava.studentManagerProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException{
		
		Connection conn = null;
		try{
			try{
				Class.forName("org.postgresql.Driver");
			}
			catch(ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			
			String url = "jdbc:postgresql://sqletud.univ-mlv.fr:5432/xgerber_db";
			String user = "xgerber";
			String passwd = "Minions77";
			
			conn = DriverManager.getConnection(url, user, passwd);
			System.out.println("Connexion effective !");
			startMenu();
			
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			if(conn != null)
				conn.close();
		}  
	}
	
	public static void startMenu(){
		
		int choiceNumber = 0;
		
		Scanner sc = null;
		
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
				sc = new Scanner(System.in);
				System.out.println("Entrer le chiffre correspondant � votre choix !\n");
				if(choiceNumber==-1)
					System.out.print("Ce choix est invalide, recommencez : ");
				choiceNumber = sc.nextInt();
			} catch(InputMismatchException e){
		        e.printStackTrace();
		    }
			
			switch(choiceNumber){
			
			case 1:
				// Ajouter un �tudiant
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
		if(sc != null)
			sc.close();
		System.out.println("\nFermeture du Programme");
	}
	
	private static void clearConsole()
	{
	    for(int i = 0; i < 25; i++)
	    	System.out.println();
	}
}