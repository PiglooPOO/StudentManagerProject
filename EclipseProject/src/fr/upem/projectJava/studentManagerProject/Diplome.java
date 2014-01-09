package fr.upem.projectJava.studentManagerProject;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdom.JDOMException;
import org.jopendocument.dom.OOUtils;
import org.jopendocument.dom.template.JavaScriptFileTemplate;
import org.jopendocument.dom.template.TemplateException;

public class Diplome {

	/**
	* Description about the editDiplome function :
	* This function allows to edit a diploma.
	* @param <number> is student number to identify a student (primary key).
	*/
  public static void editDiplome(int number) throws IOException, TemplateException, JDOMException {
	  DBConnection c = null;
    try {
    		c=new DBConnection();
			ResultSet result = c.executeQuery("SELECT formation.name,year,student.name,student.firstName,student.adress,student.birthday,settings.name,directorName,directorFirstName FROM student,formation,year_formation_student,settings WHERE number="+number+" AND idStudent="+number+" AND year_formation_student.idFormation=formation.id AND number=idStudent AND nbYear=curYear");
			result.next();
			
			File templateFile = new File("template/Attestation.odt");
			File file = new File(number+".odt");
			JavaScriptFileTemplate template = new JavaScriptFileTemplate(templateFile);
			
			template.setField("Titre", result.getString("settings.name").toUpperCase());
			template.setField("formationName",result.getString("formation.name").toUpperCase());
			template.setField("annee",result.getString("year")+"/"+(result.getString("year")+1));
			template.setField("name",result.getString("student.name").toUpperCase());
			template.setField("firstName",result.getString("student.firstName"));
			template.setField("adress",result.getString("student.adress"));
			String date = result.getDate("student.birthday").toString();
			String[] recup=date.split("-");
			int day=Integer.parseInt(recup[2]);
			int month=Integer.parseInt(recup[1]);
			int year=Integer.parseInt(recup[0]);
			template.setField("birthday",day+"/"+month+"/"+year);
			template.setField("formationName",result.getString("formation.name").toUpperCase());
			template.setField("DirectorFirstName",result.getString("directorFirstName"));
			template.setField("directorName",result.getString("directorName").toUpperCase());
			
			template.createDocument().saveToPackageAs(file);
			
			OOUtils.open(file);
			result.close();
			c.close();
    	}
    	catch (SQLException e) {
    		if(c!=null)
				c.close();
		e.printStackTrace();
    	}
    }
}
    
