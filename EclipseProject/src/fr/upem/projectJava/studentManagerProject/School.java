package fr.upem.projectJava.studentManagerProject;

public class School {
	private String name;
	private String directorName;
	private String directorFirstName;
	
	public School(String name, String directorName, String directorFirstName){
		this.setName(name);
		this.setDirectorFirstName(directorFirstName);
		this.setDirectorName(directorName);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDirectorName() {
		return directorName;
	}

	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}

	public String getDirectorFirstName() {
		return directorFirstName;
	}

	public void setDirectorFirstName(String directorFirstName) {
		this.directorFirstName = directorFirstName;
	}
	
	public static void changeName(){
		System.out.print("Entrer le nouveau nom de l'école : ");
		String name= Main.sc.nextLine();
		DBConnection c =new DBConnection();
		c.executeUpdate("UPDATE settings SET name = '"+name+"'");
		System.out.println("Nom changé avec succès");
		c.close();
	}
	
	public static void changeDirector(){
		System.out.print("Entrer le prénom du nouveau directeur : ");
		String firstName= Main.sc.nextLine();
		System.out.print("Entrer le nom du nouveau directeur : ");
		DBConnection c = null;
		String name= Main.sc.nextLine();
		c = new DBConnection();
		c.executeUpdate("UPDATE settings SET directorName = "+name+" AND directorFirstName = "+firstName);
		System.out.println("Nom changé avec succès");
		c.close();
	}
}
