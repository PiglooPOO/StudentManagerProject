package fr.upem.projectJava.studentManagerProject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

import org.jopendocument.dom.ODPackage;
import org.jopendocument.dom.ODSingleXMLDocument;
import org.jopendocument.dom.text.Heading;
import org.jopendocument.dom.text.Paragraph;

public class Diplome {

  
  public static void editDiplome(int number) {
    try {
	    	Statement state = DBConnection.getInstance().createStatement();
			ResultSet result = state.executeQuery("SELECT formation.name,year,student.name,student.firstName,student.adress,student.birthday,settings.name,directorName,directorFirstName FROM student,formation,year_formation_student,settings WHERE number="+number+" AND idStudent="+number+" AND year_formation_student.idFormation=formation.id AND number=idStudent AND nbYear=curYear");
			result.next();
			
			ODPackage p = new ODPackage(new File("styles.odt"));
			ODSingleXMLDocument document = p.toSingle();
			addTitlePage(document,result.getString("settings.name"));
			addImage(document,"logo.png");
			addContent(document,result.getString("formation.name"),result.getInt("year"));
			addInfo(document, result.getString("student.name"), result.getString("student.firstName"), result.getString("student.adress"), result.getDate("student.birthday"), result.getString("formation.name"));
			addSignature(document,result.getString("directorName"),result.getString("directorFirstName"));

    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  private static void addTitlePage(ODSingleXMLDocument document, String name){
	 
  }
  
  private static void addImage(ODSingleXMLDocument document,String image)
	      throws MalformedURLException, IOException {
	  	
	  
	  }

  private static void addContent(ODSingleXMLDocument document, String formation,int année){

   
  }

  private static void addInfo(ODSingleXMLDocument document, String name, String firstName, String adress, Date birthday, String formation){
	  
  }
  
  private static void addSignature(ODSingleXMLDocument document,String directorName, String directorFirstName) throws MalformedURLException, IOException {
	  
	  
  }
  
  private static void addEmptyLine(Paragraph paragraph, int number) {
    for (int i = 0; i < number; i++) {
      paragraph.addContent(" ");
    }
  } 
} 

