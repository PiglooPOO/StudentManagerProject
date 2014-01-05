package fr.upem.projectJava.studentManagerProject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


public class Diplome {
  private static Font title = new Font(Font.FontFamily.HELVETICA, 32,
      Font.BOLD);
  private static Font normalFont20 = new Font(Font.FontFamily.TIMES_ROMAN, 20);
  private static Font normalFont16 = new Font(Font.FontFamily.TIMES_ROMAN, 16);
  private static Font normalFont14 = new Font(Font.FontFamily.TIMES_ROMAN, 14);
  private static Font normalFont10 = new Font(Font.FontFamily.TIMES_ROMAN, 10);
  
  public static void editDiplome(int number) {
    try {
	    	Statement state = DBConnection.getInstance().createStatement();
			ResultSet result = state.executeQuery("SELECT formation.name,year,student.name,student.firstName,student.adress,student.birthday FROM student,formation,year_formation_student WHERE number="+number+" AND idStudent="+number+" AND year_formation_student.idFormation=formation.id AND number=idStudent AND nbYear=curYear");
			result.next();
			Document document = new Document();
			PdfWriter e = PdfWriter.getInstance(document, new FileOutputStream(number+".pdf"));
			document.open();
			addMetaData(document);
			addTitlePage(document);
			addImage(document,"logo.png");
			addContent(document,result.getString(1),result.getInt(2));
			addInfo(document, result.getString(3), result.getString(4), result.getString(5), result.getDate(6), result.getString(1));
			addSignature(document);
			document.close();
			e.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  private static void addMetaData(Document document) {
    document.addTitle("Attestation");
    document.addSubject("");
    document.addKeywords("");
    document.addAuthor("");
    document.addCreator("");
  }

  private static void addTitlePage(Document document)
      throws DocumentException{
    Paragraph titleFile = new Paragraph("Ecole d'ing�nieur Pigloo", title);
    titleFile.setAlignment(Element.ALIGN_CENTER);
    addEmptyLine(titleFile,1);
    document.add(titleFile);
  }
  
  private static void addImage(Document document,String image)
	      throws DocumentException, MalformedURLException, IOException {
	  	
	    Image image1 = Image.getInstance(image);
	    image1.setAlignment(Element.ALIGN_CENTER);
	    document.add(image1);
	  }

  private static void addContent(Document document, String formation,int ann�e) throws DocumentException {

    Paragraph textFormation=new Paragraph("Attestion de r�ussite au Dipl�me d'ing�nieur Fili�re " +formation.toUpperCase(),normalFont20);
    addEmptyLine(textFormation,1);
    textFormation.setAlignment(Element.ALIGN_CENTER);
    addEmptyLine(textFormation,1);
    document.add(textFormation);
    
    Paragraph textYear = new Paragraph("ANNEE "+ ann�e +"/"+ (ann�e+1),normalFont16);
    addEmptyLine(textYear,8);
    textYear.setAlignment(Element.ALIGN_CENTER);
    document.add(textYear); 
  }

  private static void addInfo(Document document, String name, String firstName, String adress, Date birthday, String formation) throws DocumentException {
	  Paragraph textInfo = new Paragraph("Monsieur / Madame "+ name +" "+firstName+" "+adress+" n� le " + birthday.toString() + " est re�u dipl�me d'ing�nieur " + formation, normalFont14);
	  addEmptyLine(textInfo,6);
	  textInfo.setAlignment(Element.ALIGN_CENTER);
	  document.add(textInfo);
  }
  
  private static void addSignature(Document document) throws DocumentException, MalformedURLException, IOException {
	  Paragraph textInfo = new Paragraph("Le directeur de l'�cole", normalFont10);
	  textInfo.setAlignment(Element.ALIGN_RIGHT);
	  document.add(textInfo);
	  Image image1 = Image.getInstance("signature.png");
	  image1.setAlignment(Element.ALIGN_RIGHT);
	  document.add(image1);
	  
  }
  
  private static void addEmptyLine(Paragraph paragraph, int number) {
    for (int i = 0; i < number; i++) {
      paragraph.add(new Paragraph(" "));
    }
  }
} 