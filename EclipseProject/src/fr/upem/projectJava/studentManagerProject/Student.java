package fr.upem.projectJava.studentManagerProject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Student {
	
	private String name;
	private String firstName;
	private String adress;
	private String phoneNumber;
	private String mail;
	private String birthday;
	private int gender;
	private int number;
	
	public Student(String name, String firstName, String adress, String phoneNumber, String mail, String birthday, int gender) {
		this.name = name;
		this.firstName = firstName;
		this.adress = adress;
		this.phoneNumber = phoneNumber;
		this.mail = mail;
		this.birthday = birthday;
		this.gender = gender;
	}
	
	public Student(){
		Scanner sc = new Scanner(System.in);
		do
		{
			System.out.print("Prénom:");
			this.firstName = sc.next();
			if(this.firstName.length()>30)
				System.out.println("Erreur, le prénom que vous avez entrer est trop long");
		}
		while(this.firstName.length()>30);
		do
		{
			System.out.print("Nom:");
			this.name = sc.next();
			if(this.name.length()>30)
				System.out.println("Erreur, le nom que vous avez entrer est trop long");
		}
		while(this.name.length()>30);
		
		this.adress=sc.nextLine();
		System.out.print("Adresse:");
		adress=sc.nextLine();
		
		do
		{
			System.out.print("Numéro de Téléphone:");
			this.phoneNumber = sc.next();
			if(this.phoneNumber.length()!=10)
				System.out.println("Erreur, entrer le numéro de téléphone sans espace");
		}
		while(this.phoneNumber.length()!=10);
		
		System.out.print("E-mail:");
		this.mail = sc.next();
		
		Date date = null;
		while(date==null)
		{
			System.out.print("Date de naissance (dd/mm/yyyy):");
			String birthday = sc.next();
			DateFormat format= new SimpleDateFormat("dd/mm/yyyy");
			
			try{
				date=format.parse(birthday);
				String[] verif=birthday.split("/");
				int day=Integer.parseInt(verif[0]);
				int month=Integer.parseInt(verif[1]);
				int year=Integer.parseInt(verif[2]);
				if(day>31 || day<1 || month<1 || month>12 || year<1900 || year>Calendar.getInstance().get(Calendar.YEAR))
				{
					date=null;
					System.out.println("La date saisie est invalide");
				}
				else
				{
					if(day<10 && month<10)
						this.birthday=year+"-0"+month+"-0"+day;
					if(day>9 && month<10)
						this.birthday=year+"-0"+month+"-"+day;
					if(day<10 && month>9)
						this.birthday=year+"-"+month+"-0"+day;
					else
						this.birthday=year+"-"+month+"-"+day;
				}
			}
			catch(ParseException e){
				System.out.println("Erreur dans le format de la date");
			}
		}
		
		boolean bon=false;
		String gender=null;
		while(bon==false)
		{
			System.out.print("Sexe (homme ou femme):");
			gender=sc.next();
			gender=gender.toLowerCase();
			if(gender.equalsIgnoreCase("femme") || gender.equalsIgnoreCase("homme"))
				bon=true;
		}
		if(gender.equalsIgnoreCase("homme")){
			this.gender = 1;
		}
		if(gender.equalsIgnoreCase("femme")){
			this.gender = 2;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public int getNumber() {
		return number;
	}
	
	public void addStudent(){
		System.out.println(this.toString());
		Scanner sc = new Scanner(System.in);
		String valid=null;
		do{
			System.out.println("\nValidez-vous cet étudiant? (O/N)");
			valid = sc.next();
		}
		while(!valid.equalsIgnoreCase("non") && !valid.equalsIgnoreCase("n") && !valid.equalsIgnoreCase("oui") && !valid.equalsIgnoreCase("o"));
		
		if(valid.equalsIgnoreCase("o") || valid.equalsIgnoreCase("oui")){
			try {
				Statement state = DBConnection.getInstance().createStatement();
				state.executeUpdate("INSERT INTO `student`(`name`, `firstName`, `adress`, `phoneNumber`, `mail`, `birthday`, `gender`) VALUES ('"+this.getName()+"','"+this.getFirstName()+"','"+this.getAdress()+"','"+this.getPhoneNumber()+"','"+this.getMail()+"','"+this.getBirthday()+"','"+this.getGender()+"')");
				System.out.println("Etudiant bien ajouté.");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public static boolean showStudent(int number){
		Statement state;
		Scanner sc = null;
		try {
			state = DBConnection.getInstance().createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM student WHERE number = "+number);
			
			int choiceNumber = 1;
			if(result.next()){
				while(choiceNumber!=0){
					if(choiceNumber == 1){
						System.out.println(
								"\nNom :\t\t" + result.getString("name")+
								"\nPrénom :\t" + result.getString("firstName")+
								"\nAdresse :\t" + result.getString("adress")+
								"\nTel :\t\t" + result.getString("phoneNumber")+
								"\nMail :\t\t" + result.getString("mail")+
								"\nDate de naissance :\t" + result.getDate("birthday").toString()+
								"\nSexe :\t\t" + ((result.getInt("gender")==2)?"Femme":"Homme"));
						
						System.out.println(""
								+ "\n1 Inscrire un élève dans une filière et année"
								+ "\n2 Modifier des informations"
								+ "\n3 Attribuer des notes"
								+ "\n4 Afficher ses moyennes"
								+ "\n5 Editer attestation de réussite"
								+ "\n0 Revenir au menu précédent");
						
					}
					System.out.print("Entrer le chiffre correspondant à votre choix : ");
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
					
					switch (choiceNumber) {
					case 1 :
						//TODO
						break;
					case 2 :
						//TODO
						break;
					case 3 :
						//TODO en cours Quentin
						Student.attributeMarkByStudentId(number);
						break;
					case 4 :
						//TODO en cours Quentin
						
						if(!Student.printMarksForStudent(number)){
							System.out.println("Cet étudiant n'a aucune note.");
							System.out.println("Appuyez sur Entrer pour revenir à la fiche étudiant.");
							sc.nextLine();
						}
						break;
					case 5 :
						Diplome.editDiplome(number);
						break;
					default:
						break;
					}
					if(choiceNumber != 0 && choiceNumber != -2)
						choiceNumber = 1;
					//clearConsole();
				}
				return true;
			}
			else
				return false;
				
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	private static boolean attributeMarkByStudentId(int id) {
		Scanner sc = new Scanner(System.in);
		
		/**
		 * cherchons la matière à noter
		 */
		String answerSubject = "";
		int idSubject = 0;
		System.out.println("Entrer le nom de la matière à noter : ");
		try{
			answerSubject = sc.nextLine();
			//TODO
			if((idSubject = Subject.searchSubjectsByName(answerSubject)) == -1){
				System.out.println("La filière "+answerSubject+" n'éxiste pas.");
				System.out.println("Appuyez sur Entrer pour revenir à la fiche étudiant.");
				sc.nextLine();
				return false;
				}
		}catch(InputMismatchException e){
			System.out.println("Ceci n'est pas une filière.");
			System.out.println("Appuyez sur Entrer pour revenir à la fiche étudiant.");
			sc.nextLine();
			return false;
		}
		
		/**
		 * récupérons l'année actuelle
		 */
		int year;
		if(Calendar.getInstance().get(Calendar.MONTH)<=Calendar.SEPTEMBER)
			year = Calendar.getInstance().get(Calendar.YEAR)-1;
		else
			year = Calendar.getInstance().get(Calendar.YEAR);
		
		/**
		 * on regarde si la note n'existe pas encore
		 */
		try {
			Statement state = DBConnection.getInstance().createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM year_student_subject_note WHERE year = "+year+" AND idStudent = "+id+" AND idSubject = "+idSubject);
			if(result.next()){
				System.out.println(""
						+ "Une note est déjà attribuée à cet étudiant pour cette matière, vous pouvez la modifier dans son relevé de notes,\n"
						+ "Appuyez sur Entrer pour revenir à la fiche étudiant.");
				sc.nextLine();
				return false;
			}
			/**
			 * on note, lets go
			 */
			System.out.print("Entrez la note : ");
			int note = sc.nextInt();
			sc.nextLine();
			String st1 = "INSERT INTO year_student_subject_note VALUES ("+year+","+id+","+idSubject+","+note+")";
			System.out.println(st1);
			state.executeQuery(st1);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(""
					+ "Une erreur s'est produise lors de l'accès à la base de donnée, veuillez appeller le support,\n"
					+ "Appuyez sur Entrer pour revenir à la fiche étudiant.");
			sc.nextLine();
			return false;
		}

		System.out.println("Note bien attribuée, appuyez sur entrer pour continuer.");
		sc.nextLine();
		return true;
	}

	private static boolean printMarksForStudent(int id) {
		Statement state;
		Scanner sc = null;
		List l = new ArrayList();
		int[] tmpArray = new int[3];
		try {
			state = DBConnection.getInstance().createStatement();
			ResultSet result = state.executeQuery("SELECT year_student_subject_note.note, subject.name, idStudent, idSubject, year FROM student, subject, year_student_subject_note WHERE student.number = "+id+" AND student.number = idStudent AND subject.id = idSubject AND ((year = EXTRACT(YEAR FROM NOW()) AND EXTRACT(MONTH FROM NOW()) >= 9) OR (year = EXTRACT(YEAR FROM NOW())-1 AND EXTRACT(MONTH FROM NOW()) < 9))");
			if(!result.next())
				return false;
			do{
				/**
				 * Ne pas supprimer, pour pouvoir lancer une modification.
				 */
				tmpArray[0] = result.getInt("idStudent");
				tmpArray[1] = result.getInt("idSubject");
				tmpArray[2] = result.getInt("year");
				l.add(tmpArray);
				
				System.out.println(l.size()+"\t"
						+ " " + result.getString("subject.name")
						+ " " + result.getInt("year_student_subject_note.note"));
			}while(result.next());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	public String toString() {
		String gender = (this.gender==2)?"Femme":"Homme";
		return "Nom : " +name+"\n"
				+ "Prénom : " +firstName+"\n"
				+ "Adress : " +adress+"\n"
				+ "Numéro de Téléphone : " +phoneNumber+"\n"
				+ "Email : " +mail+ "\n"
				+ "Date de naissance : " +birthday+"\n"
				+ "Sexe : " +gender;
	}
	
	public static boolean showStudentsByFormationName(String st) {
		Statement state;
		Scanner sc = null;
		try {
			state = DBConnection.getInstance().createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM formation WHERE name LIKE \"%"+st+"%\" ORDER BY id");
			if(!result.next())
				return false;
			do{
				System.out.println(result.getInt("id")+"\t"
						+ " " + result.getString("name")
						+ " " + result.getInt("curYear") + "e année");
			}while(result.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.print("Entrer le numéro de la formation dont vous voulez visualiser les étudiants : ");
		int id = -1;
		sc = new Scanner(System.in);
		do {
			try {
				id = sc.nextInt();
				sc.nextLine();
			} catch (InputMismatchException e) {
				// TODO: handle exception
			}
		} while (id<0);
		showStudentsByFormation(id);
		return true;
	}
	
	public static void showStudentsByFormation(int id) {
		Statement state;
		Scanner sc = null;
		try {
			state = DBConnection.getInstance().createStatement();
			ResultSet result = state.executeQuery("SELECT year,student.number,student.name,student.firstName FROM student,formation,year_formation_student WHERE formation.id = "+id+" AND formation.id = idFormation AND student.number = idStudent ORDER BY year");
			int year = 0;
			while(result.next()){
				if(year<result.getInt("year")){
					year = result.getInt("year");
					System.out.println("\n"
							+ "\t####################################\n"
							+ "\t################# "+year+" #############\n"
							+ "\t####################################\n"
							+ "");
				}
				System.out.println(result.getInt("student.number")+"\t"
						+ result.getString("name")
						+ " " + result.getString("firstName"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.print("Entrez le numéro de l'étudiant à visualiser : ");
		int idStudent = -1;
		sc = new Scanner(System.in);
		do {
			try {
				idStudent = sc.nextInt();
				sc.nextLine();
			} catch (InputMismatchException e) {
				// TODO: handle exception
			}
		} while (idStudent<0);
		showStudent(idStudent);
	}
	
	public static boolean showStudentsByYear(int year) {
		Statement state;
		Scanner sc = null;
		try {
			state = DBConnection.getInstance().createStatement();
			ResultSet result = state.executeQuery("SELECT year,student.number,student.name,student.firstName FROM student,year_formation_student WHERE year = "+year+" AND student.number = idStudent ORDER BY number");
			if(!result.next())
				return false;
			do{
				if(year<result.getInt("year")){
					year = result.getInt("year");
					System.out.println("\n"
							+ "\t####################################\n"
							+ "\t################# "+year+" #############\n"
							+ "\t####################################\n"
							+ "");
				}
				System.out.println(result.getInt("student.number")+"\t"
						+ result.getString("name")
						+ " " + result.getString("firstName"));
			}while(result.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.print("Entrez le numéro de l'étudiant à visualiser : ");
		int idStudent = -1;
		sc = new Scanner(System.in);
		do {
			try {
				idStudent = sc.nextInt();
				sc.nextLine();
			} catch (InputMismatchException e) {
				// TODO: handle exception
				return false;
			}
		} while (idStudent<0);
		showStudent(idStudent);
		return true;
	}
	
	public static boolean showStudentsByName(String st) {
		Statement state;
		Scanner sc = null;
		try {
			state = DBConnection.getInstance().createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM student WHERE student.name LIKE \"%"+st+"%\" ORDER BY number");
			if(!result.next())
				return false;
			do{
				System.out.println(result.getInt("student.number")+"\t"
						+ result.getString("name")
						+ " " + result.getString("firstName"));
			}while(result.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.print("Entrez le numéro de l'étudiant à visualiser : ");
		int idStudent = -1;
		sc = new Scanner(System.in);
		do {
			try {
				idStudent = sc.nextInt();
				sc.nextLine();
			} catch (InputMismatchException e) {
				// TODO: handle exception
			}
		} while (idStudent<0);
		showStudent(idStudent);
		return true;
	}
	
	public static boolean showStudentsByFirstName(String st) {
		Statement state;
		Scanner sc = null;
		try {
			state = DBConnection.getInstance().createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM student WHERE student.firstName LIKE \"%"+st+"%\" ORDER BY number");
			if(!result.next())
				return false;
			do{
				System.out.println(result.getInt("student.number")+"\t"
						+ result.getString("name")
						+ " " + result.getString("firstName"));
			}while(result.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.print("Entrez le numéro de l'étudiant à visualiser : ");
		int idStudent = -1;
		sc = new Scanner(System.in);
		do {
			try {
				idStudent = sc.nextInt();
				sc.nextLine();
			} catch (InputMismatchException e) {
				// TODO: handle exception
			}
		} while (idStudent<0);
		showStudent(idStudent);
		return true;
	}

	public static void showStudentsBySubject(int id) {
		// TODO Auto-generated method stub
		
	}
}
