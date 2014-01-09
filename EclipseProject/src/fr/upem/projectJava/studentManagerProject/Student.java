package fr.upem.projectJava.studentManagerProject;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	public Student(int id, String name, String firstName, String adress, String phoneNumber, String mail, String birthday, int gender) {
		this.name = name;
		this.firstName = firstName;
		this.adress = adress;
		this.phoneNumber = phoneNumber;
		this.mail = mail;
		this.birthday = birthday;
		this.gender = gender;
		DBConnection c = new DBConnection();
		c.executeUpdate("INSERT INTO `student` VALUES (" + id + ", '" + name + "', '" + firstName + "', '" + adress + "', '" + phoneNumber + "', '" + mail + "', '" + birthday + "', " + gender + ")");
		c.close();
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
				if(day>31 || day<1 || month<1 || month>12 || year<1900 || year>Calendar.getInstance().get(Calendar.YEAR)){
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
	
	/**
	* Description about the addStudent function :
	* This function allows to add a student in the database.
	*/
	public void addStudent(){
		System.out.println(this.toString());
		String valid=null;
		DBConnection c = null;
		
		System.out.println("\nValidez-vous cet étudiant? (Oui/Non)");
		do{
			valid = Main.sc.next();
		}
		while(!valid.equalsIgnoreCase("non") && !valid.equalsIgnoreCase("n") && !valid.equalsIgnoreCase("oui") && !valid.equalsIgnoreCase("o"));
		
		if(valid.equalsIgnoreCase("o") || valid.equalsIgnoreCase("oui")){
			c = new DBConnection();
			c.executeUpdate("INSERT INTO `student`(`name`, `firstName`, `adress`, `phoneNumber`, `mail`, `birthday`, `gender`) VALUES ('"+this.getName()+"','"+this.getFirstName()+"','"+this.getAdress()+"','"+this.getPhoneNumber()+"','"+this.getMail()+"','"+this.getBirthday()+"','"+this.getGender()+"')");
			System.out.println("Etudiant bien ajouté.");
			c.close();
		}
	}
	
	/**
	* Description about the showStudent function :
	* This function allows to show the characteristics of a student.
	* @param <number> is student number to identify a student (primary key).
	* @return <boolean> the function return true if the student exist, else false.
	*/
	public static boolean showStudent(int number){
		DBConnection c = null;
		ResultSet result = null;
		try {
			int choiceNumber = 1;
			while(choiceNumber!=0){
				c = new DBConnection();
				result = c.executeQuery("SELECT * FROM student WHERE number = "+number);
				if(result.next()){
					if(choiceNumber == 1){
						System.out.println(
								"\nNom :\t\t\t" + result.getString("name")+
								"\nPrénom :\t\t" + result.getString("firstName")+
								"\nAdresse :\t\t" + result.getString("adress")+
								"\nTel :\t\t\t" + result.getString("phoneNumber")+
								"\nMail :\t\t\t" + result.getString("mail")+
								"\nDate de naissance :\t" + result.getString("birthday").toString()+
								"\nSexe :\t\t\t" + ((result.getInt("gender")==2)?"Femme":"Homme"));
						c.close();
						System.out.println("\nFilière : \t\t"+Formation.FormationNameByStudentId(Student.followFormation(number)));
						System.out.println(""
								+ "\n1 Inscrire un élève dans une filière et année"
								+ "\n2 Modifier des informations"
								+ "\n3 Attribuer des notes"
								+ "\n4 Afficher ses moyennes"
								+ "\n5 Editer attestation de réussite"
								+ "\n0 Revenir au menu précédent");
					}
				}
				else{
					c.close();
					return false;
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
					String answerFormation = "";
					System.out.println("Entrez le nom de la filière : ");
					try{
						answerFormation = Main.sc.nextLine();
						if(!Student.addToFormation(number,Formation.searchFormationsByName(answerFormation))){
							System.out.println("Cette filière n'éxiste pas.");
							System.out.println("Appuyez sur Entrée pour continuer.");
							Main.sc.nextLine();
							}
						if(answerFormation.length()<0){
							System.out.println("Cette filière n'éxiste pas.");
							System.out.println("Appuyez sur Entrée pour continuer.");
							Main.sc.nextLine();
						}
					}catch(InputMismatchException e){
						System.out.println("Ceci n'est pas une filière.");
						System.out.println("Appuyez sur Entrée pour continuer.");
						Main.sc.nextLine();
					}
					break;
				case 2 :
					Student.editStudent(number);
					break;
				case 3 :
					Student.attributeMarkByStudentId(number);
					break;
				case 4 :
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
						e.printStackTrace();
					} catch (TemplateException e) {
						e.printStackTrace();
					} catch (JDOMException e) {
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
		} catch (SQLException e) {
			if(c!=null)
				c.close();
			e.printStackTrace();
			return false;
		}
	}
	
	
	private static boolean addToFormation(int idStudent, int idFormation) {
		int year;
		while((year = Main.sc.nextInt())<Year.getActualCurrentYear()){
			Main.sc.nextLine();
			System.out.println("Vous ne pouvez pas ajouter un étudiant à une année déjà terminée,\n"
					+ "Veuillez entrer une nouvelle année");
		}
		DBConnection c = null;
		c = new DBConnection();
		try {
			ResultSet result = c.executeQuery("SELECT idFormation FROM year_formation_student WHERE year = "+year+" AND idStudent = "+idStudent);
			if(result.next()){
				if(result.getInt("idFormation") == idFormation){
					System.out.println("\nCet étudiant est déjà inscrit dans cette formation.");
					System.out.println("Appuyez sur Entrée pour revenir à la fiche étudiant.");
					Main.sc.nextLine();
				}
				System.out.println("\nCet étudiant est déjà inscrit dans une formation, voulez vous l'en changer et perdre les données relatives à l'ancienne? (Oui/Non)");
				String valid = null;
				do{
					valid = Main.sc.nextLine();
				}
				while(!valid.equalsIgnoreCase("non") && !valid.equalsIgnoreCase("n") && !valid.equalsIgnoreCase("oui") && !valid.equalsIgnoreCase("o"));
				
				if(valid.equalsIgnoreCase("o") || valid.equalsIgnoreCase("oui")){
					c.executeUpdate("UPDATE year_formation_student SET idFormation = "+idFormation);
					c.executeUpdate("DELETE * FROM year_student_subject_note WHERE idStudent = "+idStudent+" AND year = "+year);
					c.close();
				}
				else {
					c.close();
					return false;
				}
			}else{
				c.executeUpdate("INSERT INTO year_formation_student VALUES("+year+","+idFormation+","+idStudent+")");
			}
			c.close();
		} catch (SQLException e) {
			c.close();
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	* Description about the followFormation function :
	* This function allows to know which formation the student follows.
	* @param <idStudent> is student identification (Stranger Key).
	* @return <boolean> return true if it works, else false.
	*/
	public static int followFormation(int idStudent){
		int year = Year.getActualCurrentYear();
		DBConnection c = null;
		
		try {
			c=new DBConnection();
			ResultSet result = c.executeQuery("SELECT idFormation FROM student, year_formation_student WHERE number = idStudent AND year = "+year+" AND idStudent = "+idStudent);
			
			if(result.next()){
				int r = result.getInt("idFormation");
				c.close();
				return r;
			}
		} catch (SQLException e) {
			if(c!=null)
				c.close();
			e.printStackTrace();
		}
		c.close();
		return -1;
	}
	
	/**
	* Description about the attributeMarkByStudentId function :
	* This function allows to attribute a mark to student with his id.
	* @param <id> is student number to identify a student (primary key).
	* @return <boolean> The function return false if there is a problem, else true.
	*/
	private static boolean attributeMarkByStudentId(int id) {
		/**
		 * cherchons la matière à noter
		 */
		String answerSubject = "";
		int idSubject = 0;
		System.out.println("Entrez le nom de la matière à noter : ");
		try{
			answerSubject = Main.sc.nextLine();
			if((idSubject = Subject.searchSubjectsByNameAndStudentId(answerSubject, id)) == -1){
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
		DBConnection c = null;
		try {
			c = new DBConnection();
			ResultSet result = c.executeQuery("SELECT * FROM year_student_subject_note WHERE year = "+year+" AND idStudent = "+id+" AND idSubject = "+idSubject);
			if(result.next()){
				System.out.println(""
						+ "Une note est déjà attribuée à cet étudiant pour cette matière, vous pouvez la modifier dans son relevé de notes,\n"
						+ "Appuyez sur Entrée pour revenir à la fiche étudiant.");
				Main.sc.nextLine();
				c.close();
				return false;
			}
			/**
			 * on note, lets go
			 */
			System.out.print("Entrez la note : ");
			int note = Main.sc.nextInt();
			Main.sc.nextLine();
			c.executeUpdate("INSERT INTO year_student_subject_note VALUES ("+year+","+id+","+idSubject+","+note+")");
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(""
					+ "Une erreur s'est produite lors de l'accès à la base de donnée, veuillez appeller le support,\n"
					+ "Appuyez sur Entrée pour revenir à la fiche étudiant.");
			Main.sc.nextLine();
			if(c!=null)
				c.close();
			return false;
		}

		System.out.println("Note bien attribuée, appuyez sur Entrée pour continuer.");
		Main.sc.nextLine();
		return true;
	}

	/**
	* Description about the printMarkForStudent function :
	* This function allows to print a mark for a student with his id.
	* @param <id> is student number to identify a student (primary key).
	* @return <boolean> The function return false if there is a problem, else true.
	*/
	private static boolean printMarksForStudent(int id) {
		DBConnection c = null;
		List<int[]> l = new ArrayList<int[]>();
		int[] averageNote = {0,0};
		int[] tmpArray = new int[3];
		
		try {
			c = new DBConnection();
			ResultSet result = c.executeQuery("SELECT note, coef, subject.name, year_formation_student.idStudent, year_student_subject_note.idSubject, year_student_subject_note.year" 
					+" FROM student, subject, year_student_subject_note, year_formation_subject, year_formation_student"
					//étudiant
					+" WHERE student.number = "+id
					+" AND student.number = year_student_subject_note.idStudent"
					+" AND student.number = year_student_subject_note.idStudent"
					+" AND student.number = year_formation_student.idStudent"
					//Matière
					+" AND year_student_subject_note.idSubject = year_formation_subject.idSubject"
					+" AND subject.id = year_student_subject_note.idSubject"
					+" AND subject.id = year_formation_subject.idSubject"
					//année
					+" AND year_student_subject_note.year = year_formation_subject.year"
					+" AND year_student_subject_note.year = year_formation_student.year"
					+" AND year_student_subject_note.year = "+Year.getActualCurrentYear());

			if(!result.next()){
				c.close();
				return false;
			}
			do{
				/**
				 * Ne pas supprimer, pour pouvoir lancer une modification.
				 */
				tmpArray[0] = result.getInt("idStudent");
				tmpArray[1] = result.getInt("idSubject");
				tmpArray[2] = result.getInt("year");
				
				l.add(tmpArray);
				
				averageNote[0] += result.getInt("note")*result.getInt("coef");
				averageNote[1] += result.getInt("coef");
				System.out.println(l.size()
						+ "\t" + result.getString("name")
						+ "\t coefficient : " + result.getInt("coef")
						+ "\t note : " + result.getInt("note"));
			}while(result.next());
			averageNote[0] /= averageNote[1];
			System.out.println(l.size()+"\t"
					+ " Moyenne : "
					+ averageNote[0]);
			c.close();
		} catch (SQLException e) {
			if(c!=null)
				c.close();
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
	
	/**
	* Description about the showStudentsByFormationName function :
	* This function allows to show the students sorted by the FormationName.
	* @param <st> is the string searched (here the FormationName) by the user.
	* @return <boolean> The function return false if there is a problem, else true.
	*/
	public static boolean showStudentsByFormationName(String st) {
		DBConnection c = null;
		try {
			c = new DBConnection();
			ResultSet result = c.executeQuery("SELECT * FROM formation WHERE name LIKE \"%"+st+"%\" ORDER BY id");
			if(!result.next()){
				c.close();
				return false;
			}
			do{
				System.out.println(result.getInt("id")+"\t"
						+ " " + result.getString("name")
						+ " " + result.getInt("curYear") + "e année");
			}while(result.next());
			c.close();
		} catch (SQLException e) {
			if(c!=null)
				c.close();
			e.printStackTrace();
		}
		
		System.out.print("Entrez le numéro de la formation dont vous voulez visualiser les étudiants : ");
		int id = -1;
		do {
			try {
				id = Main.sc.nextInt();
				Main.sc.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("Ceci n'est pas une formation");
			}
		} while (id<0);
		showStudentsByFormation(id);
		return true;
	}
	
	/**
	* Description about the showStudentsByFormation function :
	* This function allows to show all the students sorted by Formation.
	* @param <id> is student number to identify a student (primary key).
	*/
	public static void showStudentsByFormation(int id) {
		if(id==-1)
			return;
		DBConnection c = null;
		try {
			c = new DBConnection();
			ResultSet result = c.executeQuery("SELECT year,student.number,student.name,student.firstName "
					+ "FROM student,formation,year_formation_student "
					+ "WHERE formation.id = "+id+" AND formation.id = idFormation "
					+ "AND student.number = idStudent ORDER BY year");
			int year = 0;
			if(!result.next()){
				c.close();
				return;
			}
				
			do{
				if(year<result.getInt("year")){
					year = result.getInt("year");
					System.out.println("\n"
							+ "\t####################################\n"
							+ "\t################# "+year+" #############\n"
							+ "\t####################################\n"
							+ "");
				}
				System.out.println(result.getInt("number")+"\t"
						+ result.getString("name")
						+ " " + result.getString("firstName"));
			}while(result.next());
			c.close();
		} catch (SQLException e) {
			if(c!=null)
				c.close();
			e.printStackTrace();
		}
		
		System.out.print("Entrez le numéro de l'étudiant à visualiser : ");
		int idStudent = -1;
		do {
			try {
				idStudent = Main.sc.nextInt();
				Main.sc.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("Ceci n'est pas un étudiant");
			}
		} while (idStudent<0);
		showStudent(idStudent);
	}
	
	/**
	* Description about the showStudentsByYear function :
	* This function allows to show all the students sorted by year.
	* @param <year> is the year of the formation.
	* @return <boolean> The function return false if there is a problem, else true.
	*/
	public static boolean showStudentsByYear(int year) {
		DBConnection c = null;
		try {
			c = new DBConnection();
			ResultSet result = c.executeQuery("SELECT year,student.number,student.name,student.firstName "
					+ "FROM student,year_formation_student "
					+ "WHERE year = "+year+" "
					+ "AND student.number = idStudent ORDER BY number");
			if(!result.next()){
				c.close();
				return false;
			}
				
			do{
				if(year<result.getInt("year")){
					year = result.getInt("year");
					System.out.println("\n"
							+ "\t####################################\n"
							+ "\t################# "+year+" #############\n"
							+ "\t####################################\n"
							+ "");
				}
				System.out.println(result.getInt("number")+"\t"
						+ result.getString("name")
						+ " " + result.getString("firstName"));
			}while(result.next());
			c.close();
		} catch (SQLException e) {
			if(c!=null)
				c.close();
			e.printStackTrace();
		}
		
		System.out.print("Entrez le numéro de l'étudiant à visualiser : ");
		int idStudent = -1;
		do {
			try {
				idStudent = Main.sc.nextInt();
				Main.sc.nextLine();
			} catch (InputMismatchException e) {
				System.out.print("Veuillez entrer un numéro étudiant proposé ci-dessus : ");
			}
		} while (idStudent<0);
		showStudent(idStudent);
		return true;
	}
	
	/**
	* Description about the showStudentsByName function :
	* This function allows to show all the students sorted by name.
	* @param <st> is the string (here the name of the student) searched by the user.
	* @return <boolean> The function return false if there is a problem, else true.
	*/
	public static boolean showStudentsByName(String st) {
		DBConnection c = null;
		try {
			c = new DBConnection();
			ResultSet result = c.executeQuery("SELECT * FROM student WHERE student.name LIKE \"%"+st+"%\" ORDER BY number");
			if(!result.next()){
				c.close();
				return false;
			}
			
			do{
				System.out.println(result.getInt("number")+"\t"
						+ result.getString("name")
						+ " " + result.getString("firstName"));
			}while(result.next());
			c.close();
		} catch (SQLException e) {
			if(c!=null)
				c.close();
			e.printStackTrace();
		}
		
		System.out.print("Entrez le numéro de l'étudiant à visualiser : ");
		int idStudent = -1;
		do {
			try {
				idStudent = Main.sc.nextInt();
				Main.sc.nextLine();
			} catch (InputMismatchException e) {
				System.out.print("Veuillez entrer un numéro étudiant proposé ci-dessus : ");
			}
		} while (idStudent<0);
		showStudent(idStudent);
		return true;
	}
	
	/**
	* Description about the showStudentsByFirstName function :
	* This function allows to show all the students sorted by firstName.
	* @param <st> is the string (here the firstName of the student) searched by the user.
	* @return <boolean> The function return false if there is a problem, else true.
	*/
	public static boolean showStudentsByFirstName(String st) {
		DBConnection c = null;
		try {
			c = new DBConnection();
			ResultSet result = c.executeQuery("SELECT * FROM student WHERE student.firstName LIKE \"%"+st+"%\" ORDER BY number");
			if(!result.next()){
				c.close();
				return false;
			}
				
			do{
				System.out.println(result.getInt("number")+"\t"
						+ result.getString("name")
						+ " " + result.getString("firstName"));
			}while(result.next());
			c.close();
		} catch (SQLException e) {
			if(c!=null)
				c.close();
			e.printStackTrace();
		}
		
		System.out.print("Entrez le numéro de l'étudiant à visualiser : ");
		int idStudent = -1;
		do {
			try {
				idStudent = Main.sc.nextInt();
				Main.sc.nextLine();
			} catch (InputMismatchException e) {
				System.out.print("Veuillez entrer un numéro étudiant proposé ci-dessus : ");
			}
		} while (idStudent<0);
		showStudent(idStudent);
		return true;
	}
	
	/**
	* Description about the showStudentGraduate function :
	* This function allows to show all the students who are graduated.
	* @return <boolean> return true if it works, else false.
	*/
	public static boolean showStudentGraduate(){
		DBConnection c = null;
		int[] averageNote = {0,0};
		try {
			c = new DBConnection();
			ResultSet result=c.executeQuery("SELECT number FROM student, year_formation_student, formation "
						+ "WHERE student.number=year_formation_student.idStudent "
						+ "AND year_formation_student.idFormation = formation.id "
						+ "AND formation.curYear=formation.nbYear");
			
				while(result.next()){
					averageNote[0]=0;
					averageNote[1]=0;
					ResultSet result2= c.executeQuery("SELECT note, coef, student.number, student.name, student.firstName" 
							+" FROM student, subject, year_student_subject_note, year_formation_subject, year_formation_student"
							//étudiant
							+" WHERE student.number = "+result.getInt("number")
							+ "AND student.number = year_student_subject_note.idStudent"
							+" AND student.number = year_formation_student.idStudent"
							//Matière
							+" AND year_student_subject_note.idSubject = year_formation_subject.idSubject"
							+" AND subject.id = year_student_subject_note.idSubject"
							+" AND subject.id = year_formation_subject.idSubject"
							//année
							+" AND year_student_subject_note.year = year_formation_subject.year"
							+" AND year_student_subject_note.year = year_formation_student.year"
							+" AND year_student_subject_note.year = "+Year.getActualCurrentYear());
					averageNote[0] += result.getInt("note")*result.getInt("coef");
					averageNote[1] += result.getInt("coef");
					averageNote[0] /= averageNote[1];
					result2.next();
					if(averageNote[0]>=10)
						System.out.println(result2.getInt("number") + " " + result2.getString("name") + " " + result2.getString("firstName"));
					}
					try {
						int number=0;
						do{
							System.out.print("Entrez le numéro de l'étudiant pour lequel vous voulez crée le diplôme : ");
							number= Main.sc.nextInt();
							if(number<1)
								System.out.println("L'étudiant sélectionner n'éxiste pas");
							else{
								Diplome.editDiplome(number);
							}
						}while(number<1);
						
					} catch (IOException e) {
						e.printStackTrace();
					} catch (TemplateException e) {
						e.printStackTrace();
					} catch (JDOMException e) {
						e.printStackTrace();
					}
			} catch (SQLException e) {
			if(c!=null)
				c.close();
			e.printStackTrace();
		}
		return true;
	}

	/**
	* Description about the showStudentsBySubject function :
	* This function allows to show all the students sorted by Subjects.
	* @param <id> is student number to identify a student (primary key).
	*/
	public static void showStudentsBySubject(int id) {
		if(id==-1)
			return;
		DBConnection c = null;
		try {
			c = new DBConnection();
			ResultSet result = c.executeQuery("SELECT * "
					+ "FROM subject, year_formation_student, year_formation_subject "
					+ "WHERE subject.isAvailable = 1 AND subject.id = "+id
					+ " AND year_formation_student.year = year_formation_subject.year"
					+ " AND year_formation_student.year = "+Year.getActualCurrentYear()
					+ " AND year_formation_student.idFormation = year_formation_subject.idFormation"
					+ " AND subject.id = year_formation_subject.idSubject"
					+ " ORDER BY year_formation_subject.year");
			int year = 0;
			if(!result.next()){
				c.close();
				return;
			}
			do{
				if(year<result.getInt("year")){
					year = result.getInt("year");
					System.out.println("\n"
							+ "\t####################################\n"
							+ "\t################# "+year+" #############\n"
							+ "\t####################################\n"
							+ "");
				}
				System.out.println(result.getInt("number")+"\t"
						+ result.getString("name")
						+ " " + result.getString("firstName"));
			}while(result.next());
			c.close();
		} catch (SQLException e) {
			if(c!=null)
				c.close();
			e.printStackTrace();
		}
		
		System.out.print("Entrez le numéro de l'étudiant à visualiser : ");
		int idStudent = -1;
		do {
			try {
				idStudent = Main.sc.nextInt();
				Main.sc.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("Ceci n'est pas un étudiant");
			}
		} while (idStudent<0);
		showStudent(idStudent);
	}
	
	/**
	* Description about the editStudent function :
	* This function allows to edit a student.
	* @param <id> is student id to identify a student (primary key).
	*/
	public static void editStudent(int id){
		
		DBConnection c = null;
		c = new DBConnection();
		
		int choiceNumber = 1;
		while(choiceNumber!=0){
            if(choiceNumber == 1){
                System.out.println("\nQue voulez-vous modifier ?\n"
                		+ "1 Modifier le nom\n"
                		+ "2 Modifier l'adresse\n"
                		+ "3 Modifier le téléphone\n"
                		+ "4 Modifier le mail\n"
                		+ "0 Quitter\n");
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
                Main.sc.nextLine();
                choiceNumber = -2;
            }
            
            switch(choiceNumber){
            case 1:
            	System.out.println("Nouveau nom : ");
            	String newName = Main.sc.nextLine();
            	c.executeUpdate("UPDATE student SET name="+newName+" WHERE id="+id+"");
            	break;
            case 2:
            	System.out.println("Nouvelle adresse : ");
            	String newAdress = Main.sc.nextLine();
            	c.executeUpdate("UPDATE student SET adress="+newAdress+" WHERE id="+id+"");
            	break;
            case 3:
            	System.out.println("Nouveau numéro de téléphone : ");
            	String newPhoneNumber = Main.sc.nextLine();
            	c.executeUpdate("UPDATE student SET phoneNumber="+newPhoneNumber+" WHERE id="+id+"");
            	break;
            case 4:
            	System.out.println("Nouveau mail : ");
            	String newMail = Main.sc.nextLine();
            	c.executeUpdate("UPDATE student SET mail="+newMail+" WHERE id="+id+"");
            	break;
            default:;
            }
            if(choiceNumber != 0 && choiceNumber != -2)
                choiceNumber = 1;
            
            c.close();
            //clearConsole();
        }
	}
}
