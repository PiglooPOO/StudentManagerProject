package fr.upem.projectJava.studentManagerProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private static Connection conn;
	private String url = "jdbc:mysql://localhost";
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
			System.out.println("SQL connection done");
		}
		return conn;	
	}
}
