/**
 * 
 */
package fr.upem.projectJava.studentManagerProject;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

/**
 * @author Fred
 *
 */
public class SimpleContentHandler implements ContentHandler {

	/* Student's attributes */
	
	private String number;
	private boolean isNumber;
	
	private String firstName;
	private boolean isFirstName;
	
	private String adress;
	private boolean isAdress;
	
	private String phoneNumber;
	private boolean isPhoneNumber;
	
	private String mail;
	private boolean isMail;
	
	private String birthday;
	private boolean isBirthday;
	
	private String gender;
	private boolean isGender;
				
	/* Formation's attributes */
			
	private String nbYear;
	private boolean isNbYear;
	
	private String curYear;
	private boolean isCurYear;
		
	/* General attributes */

	private String ID;
	private boolean isID;
	
	private String name;
	private boolean isName;
	
	private String available;
	private boolean isAvailable;
	
	private String coef;
	private boolean isCoef;

	/*For Join-Table attributes */

	private String year;
	private boolean isYear;
	
	private String idFormation;
	private boolean isIDFormation;
	
	private String idSubject;
	private boolean isIDSubject;
	
	private String idStudent;
	private boolean isIDStudent;
	
	private String note;
	private boolean isNote;
	
	/* For Director changes */
	
	private String directorName;
	private boolean isDirectorName;
	
	private String directorFirstName;
	private boolean isDirectorFirstName;
	
	
	public void setCoef(String coef) {
		this.coef = coef;
	}
	
	public void setCoef(boolean isCoef) {
		this.isCoef = isCoef;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}

	public void setNumber(boolean isNumber) {
		this.isNumber = isNumber;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setFirstName(boolean isFirstName) {
		this.isFirstName = isFirstName;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public void setAdress(boolean isAdress) {
		this.isAdress = isAdress;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setPhoneNumber(boolean isPhoneNumber) {
		this.isPhoneNumber = isPhoneNumber;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setMail(boolean isMail) {
		this.isMail = isMail;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public void setBirthday(boolean isBirthday) {
		this.isBirthday = isBirthday;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setGender(boolean isGender) {
		this.isGender = isGender;
	}

	public void setNbYear(String nbYear) {
		this.nbYear = nbYear;
	}

	public void setNbYear(boolean isNbYear) {
		this.isNbYear = isNbYear;
	}

	public void setCurYear(String curYear) {
		this.curYear = curYear;
	}

	public void setCurYear(boolean isCurYear) {
		this.isCurYear = isCurYear;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public void setID(boolean isID) {
		this.isID = isID;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setName(boolean isName) {
		this.isName = isName;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void setYear(boolean isYear) {
		this.isYear = isYear;
	}

	public void setIdFormation(String idFormation) {
		this.idFormation = idFormation;
	}

	public void setIDFormation(boolean isIDFormation) {
		this.isIDFormation = isIDFormation;
	}

	public void setIdSubject(String idSubject) {
		this.idSubject = idSubject;
	}

	public void setIDSubject(boolean isIDSubject) {
		this.isIDSubject = isIDSubject;
	}

	public void setIdStudent(String idStudent) {
		this.idStudent = idStudent;
	}

	public void setIDStudent(boolean isIDStudent) {
		this.isIDStudent = isIDStudent;
	}

	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}

	public void setDirectorName(boolean isDirectorName) {
		this.isDirectorName = isDirectorName;
	}

	public void setDirectorFirstName(String directorFirstName) {
		this.directorFirstName = directorFirstName;
	}

	public void setDirectorFirstName(boolean isDirectorFirstName) {
		this.isDirectorFirstName = isDirectorFirstName;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void setNote(boolean isNote) {
		this.isNote = isNote;
	}

	/**
	 * 
	 */
	public SimpleContentHandler() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#characters(char[], int, int)
	 */
	@Override
	public void characters(char[] ch, int start, int lenght) throws SAXException {
		if(isAdress){setAdress(String.valueOf(ch, start, lenght));}
		if(isAvailable){setAvailable(String.valueOf(ch, start, lenght));}
		if(isBirthday){setBirthday(String.valueOf(ch, start, lenght));}
		if(isCurYear){setCurYear(String.valueOf(ch, start, lenght));}
		if(isFirstName){setFirstName(String.valueOf(ch, start, lenght));}
		if(isGender){setGender(String.valueOf(ch, start, lenght));}
		if(isID){setID(String.valueOf(ch, start, lenght));}
		if(isMail){setMail(String.valueOf(ch, start, lenght));}
		if(isName){setName(String.valueOf(ch, start, lenght));}
		if(isNbYear){setNbYear(String.valueOf(ch, start, lenght));}
		if(isNumber){setNumber(String.valueOf(ch, start, lenght));}
		if(isPhoneNumber){setPhoneNumber(String.valueOf(ch, start, lenght));}
		if(isCoef) {setCoef(String.valueOf(ch, start, lenght));}
		if(isYear) {setYear(String.valueOf(ch, start, lenght));
		if(isIDFormation) {setIdFormation(String.valueOf(ch, start, lenght));}
		if(isIDStudent) {setIdStudent(String.valueOf(ch, start, lenght));}
		if(isIDSubject) {setIdSubject(String.valueOf(ch, start, lenght));}
		if(isDirectorFirstName) {setDirectorFirstName(String.valueOf(ch, start, lenght));}
		if(isDirectorName) {setDirectorName(String.valueOf(ch, start, lenght));}
		if(isNote) {setNote(String.valueOf(ch, start, lenght));}
		}
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#endDocument()
	 */
	@Override
	public void endDocument() throws SAXException {
		System.out.println("Fin de lecture du fichier");
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		DBConnection c = null;
		switch (localName) {
		case "Student": //Créé et envoie un nouvel étudiant dans la base
			@SuppressWarnings("unused")
			Student tempStu = new Student(Integer.valueOf(number), name, firstName, adress, phoneNumber, mail, birthday, Integer.valueOf(gender));
			tempStu = null;
			break;
			
		case "Subject": //Créé et envoie une nouvelle matière dans la base
			@SuppressWarnings("unused")
			Subject tempSub = new Subject(Integer.valueOf(ID), name, Integer.valueOf(available));
			tempSub = null;
			break;
		
		case "Formation": //Créé et envoie une nouvelle formation dans la base
			@SuppressWarnings("unused")
			Formation tempFor = new Formation(Integer.valueOf(ID), name, Integer.valueOf(nbYear), Integer.valueOf(curYear), Integer.valueOf(available));
			tempFor = null;
			break;
		
		case "year_formation_student":
			c = new DBConnection();
			c.executeUpdate("INSERT INTO `year_formation_student` VALUES ('"+Integer.valueOf(year)+"', '"+Integer.valueOf(idFormation)+"', '"+Integer.valueOf(idStudent)+"')");
			c.close();
			break;
		
		case "year_formation_subject":
			c = new DBConnection();
			c.executeUpdate("INSERT INTO `year_formation_subject` VALUES ('"+Integer.valueOf(year)+"', '"+Integer.valueOf(idFormation)+"', '"+Integer.valueOf(idSubject)+"','"+Integer.valueOf(coef)+"')");
			c.close();
			break;
		
		case "year_student_subject_note":
			c = new DBConnection();
			c.executeUpdate("INSERT INTO `year_student_subject_note` VALUES ('"+Integer.valueOf(year)+"', '"+Integer.valueOf(idStudent)+"', '"+Integer.valueOf(idSubject)+"' ,'"+Integer.valueOf(note)+"')");
			c.close();
			break;
		
		case "settings":
			c = new DBConnection();
			c.executeUpdate("INSERT INTO `settings` VALUES ('"+name+"', "+directorName+"', '"+directorFirstName+")");
			c.close();
			break;
		
		case "number":
			setNumber(false);
			break;
		case "name":
			setName(false);
			break;
		case "firstName":
			setFirstName(false);
			break;
		case "adress":
			setAdress(false);
			break;
		case "phoneNumber":
			setPhoneNumber(false);
			break;
		case "mail":
			setMail(false);
			break;
		case "birthday":
			setBirthday(false);
			break;
		case "gender":
			setGender(false);
			break;
			
		case "id":
			setID(false);
			break;
		case "isAvailable":
			setAvailable(false);
			break;
		case "coef":
			setCoef(false);
			break;
			
		case "nbYear":
			setNbYear(false);
			break;
		case "curYear":
			setCurYear(false);
			break;
		
		case "year":
			setYear(false);
			break;
		case "idFormation":
			setIDFormation(false);
			break;
		case "idStudent":
			setIDStudent(false);
			break;
		case "idSubject":
			setIDSubject(false);
			break;
		case "note":
			setNote(false);
			break;
						
		case "directorName":
			setDirectorName(false);
			break;
		case "directorFirstName":
			setDirectorFirstName(false);
			break;
			
		default:
			break;
		}
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#endPrefixMapping(java.lang.String)
	 */
	@Override
	public void endPrefixMapping(String arg0) throws SAXException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#ignorableWhitespace(char[], int, int)
	 */
	@Override
	public void ignorableWhitespace(char[] arg0, int arg1, int arg2)
			throws SAXException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#processingInstruction(java.lang.String, java.lang.String)
	 */
	@Override
	public void processingInstruction(String arg0, String arg1)
			throws SAXException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#setDocumentLocator(org.xml.sax.Locator)
	 */
	@Override
	public void setDocumentLocator(Locator arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#skippedEntity(java.lang.String)
	 */
	@Override
	public void skippedEntity(String arg0) throws SAXException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#startDocument()
	 */
	@Override
	public void startDocument() throws SAXException {
		System.out.println("Début de lecture du fichier");
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	public void startElement(String nameSpaceURI, String localName, String rawName, Attributes attributs) throws SAXException {
	switch (localName) {
		case "Student":
			setNumber(false);
			setName(false);
			setFirstName(false);
			setAdress(false);
			setPhoneNumber(false);
			setMail(false);
			setBirthday(false);
			setGender(false);
			break;
		case "Subject":
			setID(false);
			setName(false);
			setAvailable(false);
			setCoef(false);
			break;
		case "Formation":
			setID(false);
			setName(false);
			setNbYear(false);
			setCurYear(false);
			setAvailable(false);
			break;
		case "year_formation_student":
			setYear(false);
			setIDFormation(false);
			setIDStudent(false);
			break;
		case "year_formation_subject":
			setYear(false);
			setIDFormation(false);
			setIDSubject(false);
			break;
		case "year_student_subject_note":
			setYear(false);
			setIDSubject(false);
			setIDStudent(false);
			setNote(false);
			break;
			
		case "settings":
			break;
		
		case "number":
			setNumber(true);
			break;
		case "name":
			setName(true);
			break;
		case "firstName":
			setFirstName(true);
			break;
		case "adress":
			setAdress(true);
			break;
		case "phoneNumber":
			setPhoneNumber(true);
			break;
		case "mail":
			setMail(true);
			break;
		case "birthday":
			setBirthday(true);
			break;
		case "gender":
			setGender(true);
			break;
			
		case "id":
			setID(true);
			break;
		case "isAvailable":
			setAvailable(true);
			break;
		case "coef":
			setCoef(true);
			break;
			
		case "nbYear":
			setNbYear(true);
			break;
		case "curYear":
			setCurYear(true);
			break;
		
		case "year":
			setYear(true);
			break;
		case "idFormation":
			setIDFormation(true);
			break;
		case "idStudent":
			setIDStudent(true);
			break;
		case "idSubject":
			setIDSubject(true);
			break;
		case "note":
			setNote(true);
			break;
			
		case "directorName":
			setDirectorName(true);
			break;
		case "directorFirstName":
			setDirectorFirstName(true);
			break;
			
		default:
			break;
		}
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#startPrefixMapping(java.lang.String, java.lang.String)
	 */
	@Override
	public void startPrefixMapping(String arg0, String arg1)
			throws SAXException {
		// TODO Auto-generated method stub
	}
}
