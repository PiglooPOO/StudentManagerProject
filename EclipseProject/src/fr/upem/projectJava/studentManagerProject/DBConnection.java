package fr.upem.projectJava.studentManagerProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	
	private static Connection conn;
	private String url = "jdbc:mysql://localhost/smp";
	private String user = "root";
	private String passwd = "";
	
	private DBConnection(){
		try {
			try{
				Class.forName("com.mysql.jdbc.Driver");
			}
			catch(ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			conn = DriverManager.getConnection(url, user, passwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static Connection getInstance(){
		if(conn == null){
			new DBConnection();
			saveDB();
		}
		return conn;	
	}
	
	static void saveDB() {
		File file = new File("sauvegarde.xml");
		FileOutputStream os = null;
		
		try{
			os = new FileOutputStream(file);
			try {
				Statement state = DBConnection.getInstance().createStatement();
				ResultSet result = state.executeQuery("SELECT * FROM student");
				
				String caracteres = "<Students>\n";
				os.write(caracteres.getBytes());
				while(result.next()){	
				caracteres = "\t<Student>\n"
								+ "\t\t<number>" + result.getInt(1) + "</number>"
								+ "\n\t\t<name>" + result.getString(2) + "</name>"
								+ "\n\t\t<firstName>" + result.getString(3) + "</firstName>"
								+ "\n\t\t<adress>" + result.getString(4) + "</adress>"
								+ "\n\t\t<phoneNumber>" + result.getString(5) + "</phoneNumber>"
								+ "\n\t\t<mail>" + result.getString(6) + "</mail>"
								+ "\n\t\t<birthday>" + result.getDate(7) + "</birthday>"
								+ "\n\t\t<gender>" + result.getInt(8) + "</gender>\n"
							+ "\t</Student>\n";
				os.write(caracteres.getBytes());
				}
				caracteres = "</Students>\n";
				os.write(caracteres.getBytes());
				
				result = state.executeQuery("SELECT * FROM subject");
				caracteres = "\n<Subjects>\n";
				os.write(caracteres.getBytes());
				while(result.next()){	
				caracteres = "\t<Subject>\n"
								+ "\t\t<id>" + result.getInt(1) + "</id>"
								+ "\n\t\t<name>" + result.getString(2) + "</name>"
								+ "\n\t\t<isAvaible>"+ result.getInt(3) + "</isAvailable>\n"
							+ "\t</Subject>\n";
				os.write(caracteres.getBytes());
				}
				caracteres = "</Subjects>\n";
				os.write(caracteres.getBytes());
				
				result = state.executeQuery("SELECT * FROM formation");
				caracteres = "\n<Formations>\n";
				os.write(caracteres.getBytes());
				while(result.next()){	
				caracteres = "\t<Formation>\n"
								+ "\t\t<id>" + result.getInt(1) + "</id>"
								+ "\n\t\t<name>" + result.getString(2) + "</name>"
								+ "\n\t\t<diploma>" + result.getString(3) + "</diploma>"
								+ "\n\t\t<nbYear>" + result.getInt(4) + "</nbYear>"
								+ "\n\t\t<curYear>" + result.getInt(5) + "</curYear>"
								+ "\n\t\t<isAvaible>"+ result.getInt(6) + "</isAvailable>\n"
							+ "\t</Formation>\n";
				os.write(caracteres.getBytes());
				}
				caracteres = "</Formations>\n";
				os.write(caracteres.getBytes());
				
				result = state.executeQuery("SELECT * FROM year_formation_student");
				caracteres = "\n<years_formations_students>\n";
				os.write(caracteres.getBytes());
				while(result.next()){	
				caracteres = "\t<year_formation_student>\n"
								+ "\t\t<year>" + result.getInt(1) + "</year>"
								+ "\n\t\t<idFormation>" + result.getInt(2) + "</idFormation>"
								+ "\n\t\t<idStudent>" + result.getInt(3) + "</idStudent>\n"
							+ "\t</year_formation_student>\n";
				os.write(caracteres.getBytes());
				}
				caracteres = "</years_formations_students>\n";
				os.write(caracteres.getBytes());
				
				result = state.executeQuery("SELECT * FROM year_formation_subject");
				caracteres = "\n<years_formations_subjects>\n";
				os.write(caracteres.getBytes());
				while(result.next()){	
				caracteres = "\t<year_formation_subject>\n"
								+ "\t\t<year>" + result.getInt(1) + "</year>"
								+ "\n\t\t<idFormation>" + result.getInt(2) + "</idFormation>"
								+ "\n\t\t<idSubject>" + result.getInt(3) + "</idSubject>\n"
							+ "\t</year_formation_subject>\n";
				os.write(caracteres.getBytes());
				}
				caracteres = "</years_formations_subjects>\n";
				os.write(caracteres.getBytes());
				
				result = state.executeQuery("SELECT * FROM year_student_subject_note");
				caracteres = "\n<years_students_subjects_notes>\n";
				os.write(caracteres.getBytes());
				while(result.next()){	
				caracteres = "\t<year_student_subject_note>\n"
								+ "\t\t<year>" + result.getInt(1) + "</year>"
								+ "\n\t\t<idStudent>" + result.getInt(2) + "</idStudent>"
								+ "\n\t\t<idSubject>" + result.getInt(3) + "</idSubject>"
								+ "\n\t\t<note>" + result.getInt(4) + "</note>\n"
							+ "\t</year_student_subject_note>\n";
				os.write(caracteres.getBytes());
				}
				caracteres = "</years_students_subjects_notes>\n";
				os.write(caracteres.getBytes());
				
				result = state.executeQuery("SELECT * FROM settings");
				result.next();
				caracteres = "\n<settings>\n"
								+"\t<name>" + result.getString(1) + "</name>"
								+ "\n\t<directorName>" + result.getString(2) + "</directorName>"
								+ "\n\t<directorFirstName>" + result.getString(3) + "</directorFirstName>\n"
							+ "</settings>\n";
				os.write(caracteres.getBytes());
				
				os.close();
				result.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally{
			if(os!=null)
			{
				try{
					os.close();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}	
		}	
	}
}
