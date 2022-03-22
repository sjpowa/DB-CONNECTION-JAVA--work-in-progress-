package digitalLearning_DB_CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Datasource {

	private static final String CONNECTION_URL =
			"jdbc:mysql://localhost:3306/DigitalLearning";
			
	protected static Connection conn;
	protected static Statement stmnt;
	
	public static boolean open() {
		
		try {
			conn = DriverManager.getConnection(CONNECTION_URL, "root", "root");
			System.out.println("CONNECTION ESTABLISHED\n");
			return true;
		} catch (SQLException e) {
			System.out.println("CAN'T CONNECT TO THE DB: " + e.getMessage());
			return false;
		}
		
	}
	
	public static void close() {
		try {
			if (conn != null) {
				conn.close();
				System.out.println("\nCONNECTION CLOSED!");
			}
		} catch (SQLException e) {
			System.out.println("\nCAN'T CLOSE THE CONNECTION: " + e.getMessage());
		}
	}
	
}
