package fr.upem.projectJava.studentManagerProject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
			System.out.print("Pr�nom:");
			this.firstName = sc.next();
			if(this.firstName.length()>30)
				System.out.println("Erreur, le pr�nom que vous avez entrer est trop long");
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
			System.out.print("Num�ro de T�l�phone:");
			this.phoneNumber = sc.next();
			if(this.phoneNumber.length()!=10)
				System.out.println("Erreur, entrer le num�ro de t�l�phone sans espace");
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
			if(sc != null)
				sc.close();
			System.out.println("\nEtudiant bien ajout�.");
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
			System.out.println("\nValidez-vous cet �tudiant? (O/N)");
			valid = sc.next();
		}
		while(!valid.equalsIgnoreCase("non") && !valid.equalsIgnoreCase("n") && !valid.equalsIgnoreCase("oui") && !valid.equalsIgnoreCase("o"));
		
		if(valid.equalsIgnoreCase("o") || valid.equalsIgnoreCase("oui")){
			try {
				Statement state = DBConnection.getInstance().createStatement();
				state.executeUpdate("INSERT INTO `student`(`name`, `firstName`, `adress`, `phoneNumber`, `mail`, `birthday`, `gender`) VALUES ('"+this.getName()+"','"+this.getFirstName()+"','"+this.getAdress()+"','"+this.getPhoneNumber()+"','"+this.getMail()+"','"+this.getBirthday()+"','"+this.getGender()+"')");
				System.out.println("Etudiant bien ajout�.");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public static boolean showStudent(int number){
		Statement state;
		Scanner sc = null;
		int choiceNumber = 0;
		try {
			state = DBConnection.getInstance().createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM student WHERE number = "+number);
			if(result.next()){
				System.out.println(
						"\nNom :\t\t" + result.getString("name")+
						"\nPr�nom :\t" + result.getString("firstName")+
						"\nAdresse :\t" + result.getString("adress")+
						"\nTel :\t\t" + result.getString("phoneNumber")+
						"\nMail :\t\t" + result.getString("mail")+
						"\nDate de naissance :\t" + result.getDate("birthday").toString()+
						"\nSexe :\t\t" + ((result.getInt("gender")==2)?"Femme":"Homme"));
				System.out.println(""
						+ "\n1 Inscrire un �l�ve dans une fili�re et ann�e"
						+ "\n2 Modifier des informations"
						+ "\n3 Attribuer des notes"
						+ "\n4 Afficher ses moyennes"
						+ "\n5 Editer attestation de r�ussite"
						+ "\n6 Revenir au menu pr�c�dent");
				
				sc = new Scanner(System.in);
				while((choiceNumber = sc.nextInt()) != 0){
					System.out.println("Mauvais choix.");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
		
	}

	public String toString() {
		String gender = (this.gender==2)?"Femme":"Homme";
		return "Nom : " +name+"\n"
				+ "Pr�nom : " +firstName+"\n"
				+ "Adress : " +adress+"\n"
				+ "Num�ro de T�l�phone : " +phoneNumber+"\n"
				+ "Email : " +mail+ "\n"
				+ "Date de naissance : " +birthday+"\n"
				+ "Sexe : " +gender;
	}
}
