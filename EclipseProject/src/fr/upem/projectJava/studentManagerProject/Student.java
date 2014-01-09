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
	
	public void addStudent(){
		System.out.println(this.toString());
		String valid=null;
		DBConnection c = null;
		
		do{
			System.out.println("\nValidez-vous cet étudiant? (Oui/Non)");
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
								"\nDate de naissance :\t" + result.getDate("birthday").toString()+
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
		} catch (SQLException e) {
			if(c!=null)
				c.close();
			e.printStackTrace();
			return false;
		}
	}
	
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
<<<<<<< HEAD
				averageNote[0] += result.getInt("note")*result.getInt("coef");
=======
				averageNote[0] += result.getInt("note") * result.getInt("coef");
>>>>>>> 39a61a2a958b73f16c41bdb8f46760dfbf676f25
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
			// TODO Auto-generated catch block
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
				// TODO: handle exception
			}
		} while (id<0);
		showStudentsByFormation(id);
		return true;
	}
	
	public static void showStudentsByFormation(int id) {
		DBConnection c = null;
		try {
			c = new DBConnection();
			ResultSet result = c.executeQuery("SELECT year,student.number,student.name,student.firstName "
					+ "FROM student,formation,year_formation_student "
					+ "WHERE formation.id = "+id+" AND formation.id = idFormation "
					+ "AND student.number = idStudent ORDER BY year");
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
				// TODO: handle exception
			}
		} while (idStudent<0);
		showStudent(idStudent);
	}
	
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
				System.out.println(result.getInt("student.number")+"\t"
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
				System.out.println(result.getInt("student.number")+"\t"
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
				System.out.println(result.getInt("student.number")+"\t"
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

	public static void showStudentsBySubject(int id) {
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
				// TODO: handle exception
			}
		} while (idStudent<0);
		showStudent(idStudent);
	}
}
