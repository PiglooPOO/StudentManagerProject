package fr.upem.projectJava.studentManagerProject;

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
		Scanner sc=new Scanner(System.in);
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
			if(gender.equals("femme") || gender.equals("homme"))
				bon=true;
		}
		if(gender=="homme")
			this.gender=1;
		if(gender=="femme")
			this.gender=2;
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
		try {
			Statement state = DBConnection.getInstance().createStatement();
			state.executeUpdate("INSERT INTO `student`(`name`, `firstName`, `adress`, `phoneNumber`, `mail`, `birthday`, `gender`) VALUES ('"+this.getName()+"','"+this.getFirstName()+"','"+this.getAdress()+"','"+this.getPhoneNumber()+"','"+this.getMail()+"','"+this.getBirthday()+"','"+this.getGender()+"')");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
