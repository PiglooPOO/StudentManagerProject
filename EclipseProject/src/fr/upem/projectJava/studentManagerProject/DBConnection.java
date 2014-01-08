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
	private String url = "jdbc:mysql://mysql1.alwaysdata.com:3306/pigloopoo_db";
	private String user = "pigloopoo";
	private String passwd = "Minions77";
	
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
								+ "\t\t<number>" + result.getInt("number") + "</number>"
								+ "\n\t\t<name>" + result.getString("name") + "</name>"
								+ "\n\t\t<firstName>" + result.getString("firstname") + "</firstName>"
								+ "\n\t\t<adress>" + result.getString("adress") + "</adress>"
								+ "\n\t\t<phoneNumber>" + result.getString("phoneNumber") + "</phoneNumber>"
								+ "\n\t\t<mail>" + result.getString("mail") + "</mail>"
								+ "\n\t\t<birthday>" + result.getDate("birthday") + "</birthday>"
								+ "\n\t\t<gender>" + result.getInt("gender") + "</gender>\n"
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
								+ "\t\t<id>" + result.getInt("id") + "</id>"
								+ "\n\t\t<name>" + result.getString("name") + "</name>"
								+ "\n\t\t<isAvailable>"+ result.getInt("isAvailable") + "</isAvailable>\n"
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
								+ "\t\t<id>" + result.getInt("id") + "</id>"
								+ "\n\t\t<name>" + result.getString("name") + "</name>"
								+ "\n\t\t<nbYear>" + result.getInt("nbYear") + "</nbYear>"
								+ "\n\t\t<curYear>" + result.getInt("curYear") + "</curYear>"
								+ "\n\t\t<isAvailable>"+ result.getInt("isAvailable") + "</isAvailable>\n"
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
								+ "\t\t<year>" + result.getInt("year") + "</year>"
								+ "\n\t\t<idFormation>" + result.getInt("idFormation") + "</idFormation>"
								+ "\n\t\t<idStudent>" + result.getInt("idStudent") + "</idStudent>\n"
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
								+ "\t\t<year>" + result.getInt("year") + "</year>"
								+ "\n\t\t<idFormation>" + result.getInt("idFormation") + "</idFormation>"
								+ "\n\t\t<idSubject>" + result.getInt("idSubject") + "</idSubject>\n"
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
								+ "\t\t<year>" + result.getInt("year") + "</year>"
								+ "\n\t\t<idStudent>" + result.getInt("idStudent") + "</idStudent>"
								+ "\n\t\t<idSubject>" + result.getInt("idSubject") + "</idSubject>"
								+ "\n\t\t<note>" + result.getInt("note") + "</note>\n"
							+ "\t</year_student_subject_note>\n";
				os.write(caracteres.getBytes());
				}
				caracteres = "</years_students_subjects_notes>\n";
				os.write(caracteres.getBytes());
				
				result = state.executeQuery("SELECT * FROM settings");
				result.next();
				caracteres = "\n<settings>\n"
								+"\t<name>" + result.getString("name") + "</name>"
								+ "\n\t<directorName>" + result.getString("directorName") + "</directorName>"
								+ "\n\t<directorFirstName>" + result.getString("directorFirstName") + "</directorFirstName>\n"
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
