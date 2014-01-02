package fr.upem.projectJava.studentManagerProject;

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
	private Date birthday;
	private String sex;
	private int number;
	private static int occurence=0;
	
	public Student(String name, String firstName, String adress, String phoneNumber, String mail, Date birthday, String sex) {
		this.name = name;
		this.firstName = firstName;
		this.adress = adress;
		this.phoneNumber = phoneNumber;
		this.mail = mail;
		this.birthday = birthday;
		this.sex = sex;
		number=occurence;
		occurence++;
	}
	
	public Student(){
		Scanner sc=new Scanner(System.in);	
		System.out.print("Prénom:");
		String firstname = sc.next();
		System.out.print("Nom:");
		String name = sc.next();
		String adress=sc.nextLine();
		System.out.print("Adresse:");
		adress=sc.nextLine();
		System.out.print("Numéro de Téléphone:");
		String phoneNumber = sc.next();
		System.out.print("E-mail:");
		String mail = sc.next();
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
			}
			catch(ParseException e){
				System.out.println("Erreur dans le format de la date");
			}
		}
		boolean bon=false;
		String sex=null;
		while(bon==false)
		{
			System.out.print("Sexe (homme ou femme):");
			sex=sc.next();
			sex=sex.toLowerCase();
			if(sex.equals("femme") || sex.equals("homme"))
				bon=true;
		}
		new Student(name, firstname, adress, phoneNumber, mail, date, sex);
		sc.close();
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public int getNumber() {
		return number;
	}

	public static int getOccurence() {
		return occurence;
	}
	
	public void addStudent(Student e){
		
	}
}
