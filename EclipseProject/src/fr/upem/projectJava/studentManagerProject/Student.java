package fr.upem.projectJava.studentManagerProject;

import java.io.IOException;
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
import org.jdom.JDOMException;
import org.jopendocument.dom.template.TemplateException;

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
		do
		{
			System.out.print("Prénom:");
			this.firstName = Main.sc.next();
			if(this.firstName.length()>30)
				System.out.println("Erreur, le prénom que vous avez entré est trop long");
		}
		while(this.firstName.length()>30);
		do
		{
			System.out.print("Nom:");
			this.name = Main.sc.next();
			if(this.name.length()>30)
				System.out.println("Erreur, le nom que vous avez entré est trop long");
		}
		while(this.name.length()>30);
		
		this.adress = Main.sc.nextLine();
		System.out.print("Adresse:");
		adress = Main.sc.nextLine();
		
		do
		{
			System.out.print("Numéro de Téléphone:");
			this.phoneNumber = Main.sc.next();
			if(this.phoneNumber.length()!=10)
				System.out.println("Erreur, Entrez le numéro de téléphone sans espace");
		}
		while(this.phoneNumber.length()!=10);
		
		System.out.print("E-mail:");
		this.mail = Main.sc.next();
		
		Date date = null;
		while(date==null)
		{
			System.out.print("Date de naissance (dd/mm/yyyy):");
			String birthday = Main.sc.next();
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
			gender = Main.sc.next();
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
		String valid=null;
		do{
			System.out.println("\nValidez-vous cet étudiant? (Oui/Non)");
			valid = Main.sc.next();
		}
		while(!valid.equalsIgnoreCase("non") && !valid.equalsIgnoreCase("n") && !valid.equalsIgnoreCase("oui") && !valid.equalsIgnoreCase("o"));
		
		if(valid.equalsIgnoreCase("o") || valid.equalsIgnoreCase("oui")){
			try {
				Statement state = new DBConnection().createStatement();				
				state.executeUpdate("INSERT INTO `student`(`number`,`name`, `firstName`, `adress`, `phoneNumber`, `mail`, `birthday`, `gender`) VALUES ('null','"+this.getName()+"','"+this.getFirstName()+"','"+this.getAdress()+"','"+this.getPhoneNumber()+"','"+this.getMail()+"','"+this.getBirthday()+"','"+this.getGender()+"')");
				System.out.println("Etudiant bien ajouté.");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	public static boolean showStudent(int number){
		Statement state;
		try {
			state = new DBConnection().createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM student WHERE number = "+number);
			
			int choiceNumber = 1;
			if(result.next()){
				while(choiceNumber!=0){
					if(choiceNumber == 1){
						System.out.println(
								"\nNom :\t\t\t" + result.getString("name")+
								"\nPrénom :\t\t" + result.getString("firstName")+
								"\nAdresse :\t\t" + result.getString("adress")+
								"\nTel :\t\t\t" + result.getString("phoneNumber")+
								"\nMail :\t\t\t" + result.getString("mail")+
								"\nDate de naissance :\t" + result.getDate("birthday").toString()+
								"\nSexe :\t\t\t" + ((result.getInt("gender")==2)?"Femme":"Homme"+ 
								"\nFilière : \t\t"+Formation.FormationNameByStudentId(Student.followFormation(number))));
						
						System.out.println(""
								+ "\n1 Inscrire un élève dans une filière et année"
								+ "\n2 Modifier des informations"
								+ "\n3 Attribuer des notes"
								+ "\n4 Afficher ses moyennes"
								+ "\n5 Editer attestation de réussite"
								+ "\n0 Revenir au menu précédent");
						
					}
					System.out.print("Entrez le chiffre correspondant à votre choix : ");
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
							System.out.println("Appuyez sur Entrée pour revenir à la fiche étudiant.");
							Main.sc.nextLine();
						}
						break;
					case 5 :
						try {
							Diplome.editDiplome(number);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (TemplateException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (JDOMException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
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
			else{
				return false;
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static int followFormation(int idStudent){
		int year = Year.getActualCurrentYear();
		Statement state;
		
		try {
			state = DBConnection.getInstance().createStatement();
			ResultSet result = state.executeQuery("SELECT idFormation FROM student, year_formation_student WHERE number = idStudent AND year = "+year+" AND idStudent = "+idStudent);
			if(result.next())
				return result.getInt("idFormation");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	private static boolean attributeMarkByStudentId(int id) {
		/**
		 * cherchons la matière à noter
		 */
		String answerSubject = "";
		int idSubject = 0;
		System.out.println("Entrez le nom de la matière à noter : ");
		try{
			answerSubject = Main.sc.nextLine();
			//TODO
			if((idSubject = Subject.searchSubjectsByName(answerSubject)) == -1){
				System.out.println("La filière "+answerSubject+" n'éxiste pas dans cette filière.");
				System.out.println("Appuyez sur Entrée pour revenir à la fiche étudiant.");
				Main.sc.nextLine();
				return false;
				}

		}catch(InputMismatchException e){
			System.out.println("Ceci n'est pas une filière.");
			System.out.println("Appuyez sur Entrée pour revenir à la fiche étudiant.");
			Main.sc.nextLine();
			return false;
		}
		
		/**
		 * récupérons l'année actuelle
		 */
		int year = Year.getActualCurrentYear();
		
		/**
		 * on regarde si la note n'existe pas encore
		 */
		try {
			Statement state = new DBConnection().createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM year_student_subject_note WHERE year = "+year+" AND idStudent = "+id+" AND idSubject = "+idSubject);
			if(result.next()){
				System.out.println(""
						+ "Une note est déjà attribuée à cet étudiant pour cette matière, vous pouvez la modifier dans son relevé de notes,\n"
						+ "Appuyez sur Entrée pour revenir à la fiche étudiant.");
				Main.sc.nextLine();
				return false;
			}
			/**
			 * on note, lets go
			 */
			System.out.print("Entrez la note : ");
<<<<<<< HEAD
			int note = Main.sc.nextInt();
			Main.sc.nextLine();
=======
			int note = sc.nextInt();
			sc.nextLine();
>>>>>>> origin/NewBDD
			state.executeUpdate("INSERT INTO year_student_subject_note VALUES ("+year+","+id+","+idSubject+","+note+")");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(""
					+ "Une erreur s'est produite lors de l'accès à la base de donnée, veuillez appeller le support,\n"
					+ "Appuyez sur Entrée pour revenir à la fiche étudiant.");
			Main.sc.nextLine();
			return false;
		}

		System.out.println("Note bien attribuée, appuyez sur Entrée pour continuer.");
		Main.sc.nextLine();
		return true;
	}

	private static boolean printMarksForStudent(int id) {
		Statement state;
		List<int[]> l = new ArrayList<int[]>();
		int[] averageNote = {0,0};
		int[] tmpArray = new int[3];
		
		try {
<<<<<<< HEAD
			state = DBConnection.getInstance().createStatement();
			ResultSet result = state.executeQuery("SELECT year_student_subject_note.note, subject.name, idStudent, idSubject, year FROM student, subject, year_student_subject_note WHERE student.number = "+id+" AND student.number = idStudent AND subject.id = idSubject AND year = "+Year.getActualCurrentYear());
=======
			state = new DBConnection().createStatement();

			ResultSet result = state.executeQuery("SELECT year_student_subject_note.note, subject.name, idStudent, idSubject, year FROM student, subject, year_student_subject_note WHERE student.number = "+id+" AND student.number = idStudent AND subject.id = idSubject AND ((year = EXTRACT(YEAR FROM NOW()) AND EXTRACT(MONTH FROM NOW()) >= 9) OR (year = EXTRACT(YEAR FROM NOW())-1 AND EXTRACT(MONTH FROM NOW()) < 9))");
>>>>>>> origin/NewBDD
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
				averageNote[0] += result.getInt("year_student_subject_note.note");
				averageNote[1] += 1;
				System.out.println(l.size()+"\t"
						+ " " + result.getString("subject.name")
						+ " " + result.getInt("year_student_subject_note.note"));
			}while(result.next());
			averageNote[0] /= averageNote[1];
			System.out.println(l.size()+"\t"
					+ " Moyenne : "
					+ averageNote[0]);
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
		try {
			state = new DBConnection().createStatement();
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
		
		System.out.print("Entrez le numéro de la formation dont vous voulez visualiser les étudiants : ");
		int id = -1;
		do {
			try {
				id = Main.sc.nextInt();
				Main.sc.nextLine();
			} catch (InputMismatchException e) {
				// TODO: handle exception
			}
		} while (id<0);
		showStudentsByFormation(id);
		return true;
	}
	
	public static void showStudentsByFormation(int id) {
		Statement state;
		try {
			state = new DBConnection().createStatement();
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
		do {
			try {
				idStudent = Main.sc.nextInt();
				Main.sc.nextLine();
			} catch (InputMismatchException e) {
				// TODO: handle exception
			}
		} while (idStudent<0);
		showStudent(idStudent);
	}
	
	public static boolean showStudentsByYear(int year) {
		Statement state;
		try {
			state = new DBConnection().createStatement();
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
		do {
			try {
				idStudent = Main.sc.nextInt();
				Main.sc.nextLine();
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
		try {
			state = new DBConnection().createStatement();
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
		do {
			try {
				idStudent = Main.sc.nextInt();
				Main.sc.nextLine();
			} catch (InputMismatchException e) {
				// TODO: handle exception
			}
		} while (idStudent<0);
		showStudent(idStudent);
		return true;
	}
	
	public static boolean showStudentsByFirstName(String st) {
		Statement state;
		try {
			state = new DBConnection().createStatement();
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
		do {
			try {
				idStudent = Main.sc.nextInt();
				Main.sc.nextLine();
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
