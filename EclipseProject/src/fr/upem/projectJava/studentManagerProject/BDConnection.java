package fr.upem.projectJava.studentManagerProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDConnection {
	
	private static Connection conn;
	private String url = "jdbc:mysql://localhost/test";
	private String user = "";
	private String passwd = "";
	
	private BDConnection(){
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
			new BDConnection();
		}
		return conn;	
	}	
}
