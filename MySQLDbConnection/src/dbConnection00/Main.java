package dbConnection00;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) {

		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DigitalLearning", "root", "root");

			System.out.println("DB CONNECTION ESTABLISHED\n");
			Statement statement = conn.createStatement();
			
			ResultSet results = statement.executeQuery("SELECT * FROM STUDENTS");
			
			while(results.next()) {
				System.out.println(
								  results.getInt("id")
						+ " | " + results.getString("name")
						+ " | " + results.getString("lastName")
						+ " | " + results.getString("sex")
						+ " | " + results.getDate("dateOfBirth")
						+ " | " + results.getString("taxCode")
						);
			}
			
			results.close();
			statement.close();
			conn.close();
			
		} catch (SQLException e) {
			System.out.println("SOMETHING WENT WRONG: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("OPERATION COMPLETED");
		}
		
	}

}
