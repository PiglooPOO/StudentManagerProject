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
	
    private String DBPath = "smp.db";
    private Connection connection = null;
    private Statement statement = null;	
    
    public DBConnection(String dBPath) {
        DBPath = dBPath;
    }
    public DBConnection() {
    	this.connect();
    }
 
    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + DBPath);
            statement = connection.createStatement();
            System.out.println("Connexion a " + DBPath + " avec succès");
        } catch (ClassNotFoundException notFoundException) {
            notFoundException.printStackTrace();
            System.out.println("Erreur de connecxion");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.out.println("Erreur de connecxion");
        }
    }
    
    public void close() {
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
    public Statement createStatement(){
    	return this.statement;
    }
    
    public ResultSet query(String requet) {
        ResultSet resultat = null;
        try {
            resultat = statement.executeQuery(requet);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur dans la requet : " + requet);
        }
        return resultat;
  
    }

	static void saveDB() {
		File file = new File("sauvegarde.xml");
		FileOutputStream os = null;
		DBConnection c =null;
		
		try{
			os = new FileOutputStream(file);
			try {
				c = new DBConnection();
				Statement state = c.createStatement();
				ResultSet result = state.executeQuery("SELECT * FROM student");
				
				String caracteres = "<XML>\n\t<Students>\n";
				os.write(caracteres.getBytes());
				while(result.next()){	
				caracteres = "\t\t<Student>\n"
								+ "\t\t\t<number>" + result.getInt("number") + "</number>"
								+ "\n\t\t\t<name>" + result.getString("name") + "</name>"
								+ "\n\t\t\t<firstName>" + result.getString("firstname") + "</firstName>"
								+ "\n\t\t\t<adress>" + result.getString("adress") + "</adress>"
								+ "\n\t\t\t<phoneNumber>" + result.getString("phoneNumber") + "</phoneNumber>"
								+ "\n\t\t\t<mail>" + result.getString("mail") + "</mail>"
								+ "\n\t\t\t<birthday>" + result.getDate("birthday") + "</birthday>"
								+ "\n\t\t\t<gender>" + result.getInt("gender") + "</gender>\n"
							+ "\t\t</Student>\n";
				os.write(caracteres.getBytes());
				}
				caracteres = "\t</Students>\n";
				os.write(caracteres.getBytes());
				
				result = state.executeQuery("SELECT * FROM subject");
				caracteres = "\n\t<Subjects>\n";
				os.write(caracteres.getBytes());
				while(result.next()){	
				caracteres = "\t\t<Subject>\n"
								+ "\t\t\t<id>" + result.getInt("id") + "</id>"
								+ "\n\t\t\t<name>" + result.getString("name") + "</name>"
								+ "\n\t\t\t<isAvailable>"+ result.getInt("isAvailable") + "</isAvailable>\n"
							+ "\t\t</Subject>\n";
				os.write(caracteres.getBytes());
				}
				caracteres = "\t</Subjects>\n";
				os.write(caracteres.getBytes());
				
				result = state.executeQuery("SELECT * FROM formation");
				caracteres = "\n\t<Formations>\n";
				os.write(caracteres.getBytes());
				while(result.next()){
				caracteres = "\t\t<Formation>\n"
								+ "\t\t\t<id>" + result.getInt("id") + "</id>"
								+ "\n\t\t\t<name>" + result.getString("name") + "</name>"
								+ "\n\t\t\t<nbYear>" + result.getInt("nbYear") + "</nbYear>"
								+ "\n\t\t\t<curYear>" + result.getInt("curYear") + "</curYear>"
								+ "\n\t\t\t<isAvailable>"+ result.getInt("isAvailable") + "</isAvailable>\n"
							+ "\t\t</Formation>\n";
				os.write(caracteres.getBytes());
				}
				caracteres = "\t</Formations>\n";
				os.write(caracteres.getBytes());
				
				result = state.executeQuery("SELECT * FROM year_formation_student");
				caracteres = "\n\t<years_formations_students>\n";
				os.write(caracteres.getBytes());
				while(result.next()){	
				caracteres = "\t\t<year_formation_student>\n"
								+ "\t\t\t<year>" + result.getInt("year") + "</year>"
								+ "\n\t\t\t<idFormation>" + result.getInt("idFormation") + "</idFormation>"
								+ "\n\t\t\t<idStudent>" + result.getInt("idStudent") + "</idStudent>\n"
							+ "\t\t</year_formation_student>\n";
				os.write(caracteres.getBytes());
				}
				caracteres = "\t</years_formations_students>\n";
				os.write(caracteres.getBytes());
				
				result = state.executeQuery("SELECT * FROM year_formation_subject");
				caracteres = "\n\t<years_formations_subjects>\n";
				os.write(caracteres.getBytes());
				while(result.next()){	
				caracteres = "\t\t<year_formation_subject>\n"
								+ "\t\t\t<year>" + result.getInt("year") + "</year>"
								+ "\n\t\t\t<idFormation>" + result.getInt("idFormation") + "</idFormation>"
								+ "\n\t\t\t<idSubject>" + result.getInt("idSubject") + "</idSubject>"
								+ "\n\t\t\t<coef>" + result.getString("coef") + "</coef>\n"
							+ "\t\t</year_formation_subject>\n";
				os.write(caracteres.getBytes());
				}
				caracteres = "\t</years_formations_subjects>\n";
				os.write(caracteres.getBytes());
				
				result = state.executeQuery("SELECT * FROM year_student_subject_note");
				caracteres = "\n\t<years_students_subjects_notes>\n";
				os.write(caracteres.getBytes());
				while(result.next()){	
				caracteres = "\t\t<year_student_subject_note>\n"
								+ "\t\t\t<year>" + result.getInt("year") + "</year>"
								+ "\n\t\t\t<idStudent>" + result.getInt("idStudent") + "</idStudent>"
								+ "\n\t\t\t<idSubject>" + result.getInt("idSubject") + "</idSubject>"
								+ "\n\t\t\t<note>" + result.getInt("note") + "</note>\n"
							+ "\t\t</year_student_subject_note>\n";
				os.write(caracteres.getBytes());
				}
				caracteres = "\t</years_students_subjects_notes>\n";
				os.write(caracteres.getBytes());
				
				result = state.executeQuery("SELECT * FROM settings");
				result.next();
				caracteres = "\n\t<settings>\n"
								+"\t\t<name>" + result.getString("name") + "</name>"
								+ "\n\t\t<directorName>" + result.getString("directorName") + "</directorName>"
								+ "\n\t\t<directorFirstName>" + result.getString("directorFirstName") + "</directorFirstName>\n"
							+ "\t</settings>\n"
						+ "</XML>";
				os.write(caracteres.getBytes());
				
				os.close();
				result.close();
				c.close();
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
			if(c!=null)
				c.close();
		}	
	}
	
}
