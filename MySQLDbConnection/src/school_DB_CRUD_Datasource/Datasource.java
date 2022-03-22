package school_DB_CRUD_Datasource;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.cj.jdbc.MysqlDataSource;

public class Datasource {
	
	protected Scanner sc = new Scanner(System.in); // i want to use the scanner once for every child class
	protected Scanner int_sc = new Scanner(System.in);
	
	// i use static methods and static fields
	// beacuse for every operation i have to do here, in this project,
	// i need these stuff
	
	// then if i want to open() and close() manually in the Main Class
	// try (with resources) will have a problem,
	// cuz my conn = null -> cuz if it's not static
	// -> the open() and close() methods are not reachable
	// in try(reach here)
	
	// when the program will start
	// the classes with static fields or methods
	// will entirely run, also if not used || THIS IS A RECAP
	
	protected static String serverName = "127.0.0.1";
	protected static int portNumber = 3306;
	protected static String db_name = "school";
	protected static String db_user = "root";
	protected static String db_password = "root";
	
	protected static MysqlDataSource db = new MysqlDataSource();
	
	protected static Connection conn = null;
	
	// OPEN CONNECTION METHOD
	public static void open() {
		
		db.setServerName(serverName);
		db.setPortNumber(portNumber);
		db.setDatabaseName(db_name);
		db.setUser(db_user);
		db.setPassword(db_password);
		
		try {
			conn = db.getConnection();
			System.out.println("> CONNECTION ESTABLISHED\n");
		} catch(SQLException e) {
			System.out.println("> NO CONNECTION: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// CLOSE CONNECTION METHOD
	public static void close() {
		try {
			if (conn != null) {
				conn.close();
				System.out.println("\n> CONNECTION CLOSED");
			}
		} catch(SQLException e) {
			System.out.println("\n> CAN'T CLOSE THE CONNECTION: " + e.getMessage());
		} finally {
			System.out.println("\n> OPERATION COMPLETED");
		}
	}
	
}
