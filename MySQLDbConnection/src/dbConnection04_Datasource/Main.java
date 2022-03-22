package dbConnection04_Datasource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.MysqlDataSource;

public class Main {

	public static void main(String[] args) {
		
		Connection conn;
		
		String serverName = "127.0.0.1";
		String dbName = "school";
		int portNumber = 3306;
		String user = "root";
		String password = "root";
		
		MysqlDataSource db = new MysqlDataSource();
		
		db.setServerName(serverName);
		db.setDatabaseName(dbName);
		db.setPortNumber(portNumber);
		db.setUser(user);
		db.setPassword(password);
		
		try {
			conn = db.getConnection();
			System.out.println("DB CONNECTION ESTABLISHED\n");
			
			Statement stmnt = conn.createStatement();
			ResultSet results = null;
			
			// =========================== QUERY STUDENTS ============================
			
			try {
				results = stmnt.executeQuery("SELECT * FROM STUDENTS;");
				
				while(results.next()) {
					System.out.println(
							"ID: " 		 + results.getString(1) + " || " +
							"Name: " 	 + results.getString(2) + " || " +
							"Lastname: " + results.getString(3) + " || " +
							"Age: " 	 + results.getString(4)
							); 
				}
				
				System.out.println("\n>>> END QUERY STUDENTS <<<"
						+ "\n==========================================\n");
				} catch (SQLException e) {
					System.out.println("QUERY FAILED: " + e.getMessage());
			}
			
			// =========================== QUERY TEACHERS ==========================
			
			try {
				String queryTeachers = "SELECT * FROM TEACHERS;";
				
				results = stmnt.executeQuery(queryTeachers);
				
				while(results.next()) {
					String teacher = String.format(
							"%s %s %s %s"
							,
							results.getString(1),
							results.getString(2),
							results.getString(3),
							results.getString(4)
							);
					System.out.println(teacher);
				}
				
				System.out.println("\n>>> END QUERY TEACHERS <<<"
						+ "\n==========================================\n");
			} catch (SQLException e) {
				System.out.println("QUERY FAILED: " + e.getMessage());
			}

			// =============================== QUERY CLASSES ==============================
			
			try {
				String queryClasses = "SELECT * FROM CLASSES;";
				
				boolean b = stmnt.execute(queryClasses);
				System.out.println("IS THE QUERY GOOD? -> " + b + "\n");
				
				results = stmnt.executeQuery(queryClasses);
				while(results.next()) {
					System.out.printf(
							  "CLASS_ID: %s || "
							+ "CLASS_NAME: %s || "
							+ "STUDENT_ID: %s || "
							+ "TEACHER_ID: %s\n"
							,
							results.getString(1),
							results.getString(2),
							results.getString(3),
							results.getString(4)
							);
				}
				
				System.out.println("\n>>> END QUERY CLASSES <<<"
						+ "\n==========================================\n");
			} catch (SQLException e) {
				System.out.println("QUERY FAILED: " + e.getMessage());
			}
			
			// ==================================== STUDENT INSERT ========================================
			
			try {
					String ins_student = "INSERT INTO STUDENTS (STD_NAME, STD_LASTNAME, STD_AGE) VALUES"
							+ " ('Sergio', 'Iovino', 16);";
					
					int row_ins_student = stmnt.executeUpdate(ins_student);
					System.out.println("For the student insert: " + row_ins_student + "row(s) affected");
			} catch (SQLException e) {
				System.out.println("QUERY FAILED: " + e.getMessage());
			} finally {
				System.out.println(">>> STUDENT INSERT COMPLETED <<<\n"
						+ "==============================================\n");
			}
			
			// ==================================== TEACHER INSERT ========================================
			
			try {
				String ins_teacher = "INSERT INTO TEACHERS (tchr_name, tchr_lastname, tchr_age) VALUES"
						+ " ('Pippo', 'Baudo', 75);";
				
				int row_ins_teacher = stmnt.executeUpdate(ins_teacher);
				System.out.println("For the teacher insert: " + row_ins_teacher + " row(s) affected\n");
				
			} catch(SQLException e) {
				System.out.println("QUERY FAILED: " + e.getMessage());
			} finally {
				System.out.println(">>> TEACHER INSERT COMPLETED <<<\n"
						+ "================================================\n");
			}
			
			// ==================================== STUDENT DELETE ========================================

			try {
				
				String del_student = "DELETE FROM STUDENTS WHERE STD_NAME = 'SERGIO' AND STD_LASTNAME = 'IOVINO';";
				
				int row_delete_student = stmnt.executeUpdate(del_student);
				System.out.println("For the student delete: " + row_delete_student + " row(s) affected");
				
			} catch(SQLException e) {
				System.out.println("QUERY FAILED: " + e.getMessage());
			} finally {
				System.out.println(">>> STUDENT DELETE COMPLETED <<<"
						+ "\n========================================================\n");
			}
			
			// ==================================== TEACHER DELETE ========================================

			try {
				
				String del_teacher = "DELETE FROM TEACHERS WHERE TCHR_ID = 7";
				
				int row_del_teacher = stmnt.executeUpdate(del_teacher);
				System.out.println("For teacher delete: " + row_del_teacher + " row(s) affected");
				
			} catch (SQLException e) {
				System.out.println("QUERY FAILED: " + e.getMessage());
			} finally {
				System.out.println("");
			}
			
			// ==================================== END/CLOSE CONNECTION ==================================

			stmnt.close();
			results.close();
			conn.close();
			System.out.println("\n>>> DB CONNECTION CLOSED <<<");
		} catch (SQLException e) {
			System.out.println("QUERY FAILED: " + e.getMessage());
		} finally {
			System.out.println("OPERATION COMPLETED");
		}
		
	}
	
}
