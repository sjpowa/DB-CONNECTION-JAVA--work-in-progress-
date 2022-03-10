package dbConnection03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBconn {
	
	public static final String CONNECTION_STRING = "jdbc:mysql://127.0.0.1:3306/";
	public static final String DB_NAME = "cinema";	
	public static final String DB_USER = "root";
	public static final String DB_PASSWORD = "root";
	
	public static final String TABLE_PERSON = "person";
	public static final String COLUMN_PERSON_NAME = "name";
	public static final String COLUMN_PERSON_LASTNAME = "lastName";

	public List<Person> queryPeople() {
		
		// CONVIENE OVVIAMENTE METTERE TUTTO IN try()
		// MA FACCIAMO FINTA CHE NON LO SAPPIAMO
		Statement statement = null;
		ResultSet results = null;
		Connection conn = null;
		
		try {
			
			conn = DriverManager.getConnection(CONNECTION_STRING + DB_NAME, DB_USER, DB_PASSWORD);
			statement = conn.createStatement();
			results = statement.executeQuery("SELECT * FROM " + TABLE_PERSON);
			
		List<Person> people = new ArrayList<>();
		while(results.next()) {
			Person person = new Person();
			person.setName(results.getString(COLUMN_PERSON_NAME));
			person.setLastName(results.getString(COLUMN_PERSON_LASTNAME));
			people.add(person);
		}
			
		return people;
		
		} catch (SQLException e) {
			System.out.println("QUERY FAILED: " + e.getMessage());
			return null;
		} finally {
			try {
				if (results != null) {
					results.close();
				}
			} catch (SQLException e) {
				System.out.println("ERROR CLOSING ResultSet: " + e.getMessage());
			}
			
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				System.out.println("ERROR CLOSING Statement: " + e.getMessage());
			}
		}
	} // END METHOD
	
	public void insertPerson(String name, String lastName) {
		
		try (
			Connection conn = DriverManager.getConnection(CONNECTION_STRING + DB_NAME, DB_USER, DB_PASSWORD);
			Statement stmnt = conn.createStatement();
				) {
			
			stmnt.execute("INSERT INTO PERSON VALUES ('" + name + "', '" + lastName + "');");
			
		} catch (SQLException e) {
			System.out.println("SOMETHING WRONG.." + e.getMessage());
		}
		
	} // END METHOD
	
	public void dropPersonTable() {
		
		try (Connection conn = DriverManager.getConnection(CONNECTION_STRING + DB_NAME, DB_USER, DB_PASSWORD);
			 Statement stmnt = conn.createStatement();) {
			
			stmnt.execute("drop table " + TABLE_PERSON);
			
		} catch (SQLException e) {
			System.out.println("QUERY FAILED: " + e.getMessage());
		}
		
	} // END METHOD
	
} // END CLASS
