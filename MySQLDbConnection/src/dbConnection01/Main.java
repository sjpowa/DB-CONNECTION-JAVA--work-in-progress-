package dbConnection01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) {
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema?user=root&password=root");
//			conn.setAutoCommit(false); // con FALSE se facciamo delle modifiche non verranno committate, quindi non verranno
									   // inserite nel database anche se non avremo errori in console
			System.out.println("> CONNECTION ESTABLISHED");
			Statement statement = conn.createStatement();
//			statement.execute("CREATE TABLE IF NOT EXISTS " +
//								"tickets (id int primary key auto_increment, filmName varchar(50));");
//			
//			statement.execute("INSERT INTO tickets (filmName) VALUES ('sbatman');");
			
//			statement.execute("DROP TABLE tickets;");
			
//			statement.execute("SELECT  *  FROM tickets;");
//			ResultSet results = statement.getResultSet(); // we can do it, also in another way
			
			ResultSet results = statement.executeQuery("SELECT * FROM tickets");
			
			while(results.next()) {
				System.out.println(results.getInt("id") + " "
						+ results.getString("filmName"));
			}
			
			results.close();
			statement.close();
			conn.close();
			
		} catch (SQLException e) {
			System.out.println(">> SOMETHING WENT WRONG: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println(">>> OPERATION COMPLETED");
		}
		
		
	}
	
}
