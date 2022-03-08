package dbConnection02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
	
	public static final String DB_NAME = "cinema";
	public static final String DB_USER = "user=root";
	public static final String DB_PASSWORD = "password=root";
	public static final String CONNECTION_STRING =
			"jdbc:mysql://localhost:3306/" + DB_NAME + "?" + DB_USER + "&" + DB_PASSWORD;
	public static final String TABLE_PERSON = "person";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_LASTNAME = "lastName";

	public static void main(String[] args) {
		
		try {
			Connection conn = DriverManager.getConnection(CONNECTION_STRING);
			Statement statement = conn.createStatement();
			
			System.out.println("> CONNECTION ESTABLISHED\n");
			
			statement.execute("DROP TABLE IF EXISTS " + TABLE_PERSON);
			
			statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_PERSON +
								" (" + COLUMN_NAME + " varchar(50), " +
										COLUMN_LASTNAME + " varchar(50)" +
								" )");
			
			insertContact(statement, "MariaMaria", "RossiRossi");
			
			statement.execute("INSERT INTO " + TABLE_PERSON +
					" (" + COLUMN_NAME + ", " + 
							COLUMN_LASTNAME +
					" )" +
					" VALUES ('Pascal', 'Rossi'); ");
			
			statement.execute("INSERT INTO " + TABLE_PERSON +
					" (" + COLUMN_NAME + ", " + 
					COLUMN_LASTNAME +
					" )" +
					" VALUES ('Pippo', 'LoRusso'); ");
			
			statement.execute("INSERT INTO " + TABLE_PERSON +
					" (" + COLUMN_NAME + ", " + 
					COLUMN_LASTNAME +
					" )" +
					" VALUES ('Fu', 'Kong'); ");
			
			ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_PERSON);
			while(results.next()) {
				System.out.println(results.getString(COLUMN_NAME) + " "
						+ results.getString(COLUMN_LASTNAME));
			}
			
			results.close();
			statement.close();
			conn.close();
			
		} catch (SQLException e) {
			System.out.println(">> SOMETHING WENT WRONG: " + e.getMessage()); // il get message di SQLException ti da
																			  // una descrizione automatica dell'errore SQL
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("\n>>> OPERATION COMPLETED");
		}
		
	}
	
	private static void insertContact(Statement statement, String name, String lastName) throws SQLException {
		statement.execute("INSERT INTO " + TABLE_PERSON +
				" (" + COLUMN_NAME + ", " + 
						COLUMN_LASTNAME +
				" )" +
				" VALUES ('" + name + "', '" + lastName + "');");
	}

}
