package digitalLearning_DB_CRUD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Classe extends Datasource implements CRUD_METHODS{

	private Scanner sc = new Scanner(System.in);
	
	@Override
	public void search(int id) {
		try {
			super.open();
			stmnt = conn.createStatement();
			ResultSet results = stmnt.executeQuery(
					"SELECT * FROM CLASSES WHERE id = " + id);
			
			while(results.next()) {
				String classe = String.format(
						"ID: %s"
						+ " | CLASS NAME: %s"
						+ " | SCHOOL SUBJECT: %s"
						+ " | STUDENT(ID): %s"
						+ " | TEACHER(ID): %s"
						, 
						(results.getString(1)),
						(results.getString(2)),
						(results.getString(3)),
						(results.getString(4)),
						(results.getString(5))
						);
				System.out.println(classe);
			}
			
			results.close();
			stmnt.close();
			super.close();
		} catch (SQLException e) {
			System.out.println("QUERY FAILED: " + e.getMessage());
		}
		
	}

	@Override
	public void insert() {
		
		try {
			super.open();
			stmnt = conn.createStatement();
			
			System.out.print("ENTER CLASS NAME: ");
			String name = sc.next();
			System.out.print("ENTER SCHOOL_SUBJECT OF THIS CLASS: ");
			String schoolSubject = sc.next();
			System.out.print("ENTER STUDENT_ID: ");
			int studentID = sc.nextInt();
			System.out.print("ENTER TEACHER_ID: ");
			int teacherID = sc.nextInt();
			
			stmnt.execute(
					"INSERT INTO CLASSES"
					+ " (name, schoolSubject, studentID, teacherID) VALUES ('"
					+ name + "', '"
					+ schoolSubject + "', '"
					+ studentID + "', '"
					+ teacherID + "')"
					);
			
			System.out.println(">>> OPERATION COMPLETED!");
			stmnt.close();
			super.close();
		} catch (SQLException e) {
			System.out.println("QUERY FAILED: " + e.getMessage());
		}
		
	}

	@Override
	public void update() {

		try {
			super.open();
			stmnt = conn.createStatement();
			
			System.out.print("ENTER ID OF THE CLASS THAT U WANT TO UPDATE: ");
			int id = sc.nextInt();
			System.out.print("ENTER CLASS NAME: ");
			String name = sc.next();
			System.out.print("ENTER SCHOOL_SUBJECT OF THIS CLASS: ");
			String schoolSubject = sc.next();
			System.out.print("ENTER STUDENT_ID: ");
			int studentID = sc.nextInt();
			System.out.print("ENTER TEACHER_ID: ");
			int teacherID = sc.nextInt();
			
			stmnt.execute("UPDATE CLASSES"
					+ " SET name = '" + name
					+ "', schoolSubject = '" + schoolSubject
					+ "', studentID = " + studentID
					+ ", teacherID = " + teacherID
					+ " WHERE id = " + id
					+ ";"
					);
			
			System.out.println("\nUPDATE COMPLETED!");
			stmnt.close();
			super.close();
		} catch (SQLException e) {
			System.out.println("QUERY FAILED: " + e.getMessage());
		}
	}

	@Override
	public void delete(int id) {
		
		try {
			super.open();
			stmnt = conn.createStatement();
			
			stmnt.execute("DELETE FROM CLASSES WHERE id = " + id);
			
			System.out.println(">>> DELETE COMPLETED!");
			stmnt.close();
			super.close();
		} catch (SQLException e) {
			System.out.println("QUERY FAILED: " + e.getMessage());
		}
		
	}

	@Override
	public void readAll() {
		try {
			super.open();
			stmnt = conn.createStatement();
			ResultSet results = stmnt.executeQuery(
					"SELECT * FROM CLASSES;");
			
			while(results.next()) {
				String classe = String.format(
						  "ID: %s"
						+ " | CLASS NAME: %s"
						+ " | SCHOOL SUBJECT: %s"
						+ " | STUDENT(ID): %s"
						+ " | TEACHER(ID): %s"
						, 
						(results.getString(1)),
						(results.getString(2)),
						(results.getString(3)),
						(results.getString(4)),
						(results.getString(5))
						);
				System.out.println(classe);
			}
			
			stmnt.close();
			super.close();
		} catch (SQLException e) {
			System.out.println("QUERY FAILED: " + e.getMessage());
		}
		
	}
	
}
