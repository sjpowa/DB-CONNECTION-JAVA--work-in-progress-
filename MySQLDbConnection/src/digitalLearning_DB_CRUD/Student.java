package digitalLearning_DB_CRUD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Student extends Datasource implements CRUD_METHODS {

	private Scanner sc = new Scanner(System.in);

	@Override
	public void search(int id) {
		
		try {
			super.open();
			stmnt = conn.createStatement();
			ResultSet results = stmnt.executeQuery("SELECT * FROM STUDENTS WHERE id = " + id + ";");
			
			while (results.next()) {
				String student = String.format(
						"ID: %s"
						+ " | NAME: %s"
						+ " | LASTNAME: %s"
						+ " | SEX: %s"
						+ " | DATE OF BIRTH: %s"
						+ " | TAX CODE: %s"
						,
						(results.getString(1)), // get value from db first column, in this case ID
						(results.getString(2)),
						(results.getString(3)),
						(results.getString(4)),
						(results.getString(5)),
						(results.getString(6))
						);
					System.out.println(student); // print all the student String
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
			
			System.out.print(">>> INSERT A STUDENT <<<\n\nENTER FIRST NAME: ");
			String firstName = sc.next();
			while (firstName.length() < 2) {
				System.out.print("\nNAME LENGTH LESS THAN 2 CHAR\nRE-ENTER THE NAME: ");
				firstName = sc.next();
			}
			
			System.out.print("\nENTER LASTNAME: ");
			String lastName = sc.next();
			while (lastName.length() < 2) {
				System.out.print("\nLASTNAME LENGTH LESS THAN 2 CHAR\nRE-ENTER THE NAME: ");
				lastName = sc.next();
			}
			
			System.out.print("\nENTER SEX [M] or [F]: ");
			String sex = sc.next();
			while ( (!sex.toLowerCase().equals("m")) && (!sex.toLowerCase().equals("f")) ) {
				System.out.print("\nENTER SEX AGAIN: ");
				sex = sc.next();
			}
			
			System.out.println("\nENTER DATE OF BIRTH [yyyy-MM-dd]");
			System.out.print("\nENTER YEAR: ");
			int yyyy = sc.nextInt();
			System.out.print("\nENTER MONTH: ");
			int mm = sc.nextInt();
			while(mm > 12 || mm < 1) {
				System.out.print("\nOUT OF INDEX\nENTER MONTH AGAIN: ");
				mm = sc.nextInt();
			}
			System.out.print("\nENTER DAYS: ");
			int dd = sc.nextInt();
			while(dd > 31 || dd < 1) {
				System.out.print("\nOUT OF INDEX\nENTER DAYS AGAIN: ");
				dd = sc.nextInt();
			}
			
			String dateOfBirth = (String) (yyyy + "-" + mm + "-" + dd);
			
			System.out.print("\nENTER TAX CODE [16 chars]: ");
			String taxCode = sc.next();
			while (taxCode.length() > 16 || taxCode.length() < 16) {
				if (taxCode.length() > 16) {
					System.out.print("\nTAX CODE LENGHT > 16 CHARACTERS\nDIGIT TAXCODE AGAIN: ");
				} else {
					System.out.print("\nTAX CODE LENGHT < 16 CHARACTERS\nDIGIT TAXCODE AGAIN: ");
				}
				taxCode = sc.next();
			}
			
			stmnt.execute("INSERT INTO STUDENTS (name, lastname, sex, dateOfBirth, taxCode) VALUES "
					+ "(  '" + firstName
					+ "', '" + lastName
					+ "', '" + sex.toUpperCase()
					+ "', '" + dateOfBirth
					+ "', '" + taxCode.toUpperCase()
					+ "')");
			System.out.println("\n>>> INSERT COMPLETED!!!");
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
			
			System.out.print("\nENTER STUDENT 'ID' THAT U HAVE TO MODIFY: ");
			int id = sc.nextInt();
			
			System.out.print("ENTER STUDENT FIRST NAME: ");
			String firstName = sc.next();
			while (firstName.length() < 2) {
				System.out.print("\nNAME LENGTH LESS THAN 2 CHAR\nRE-ENTER THE NAME: ");
				firstName = sc.next();
			}
			
			System.out.print("\nENTER LASTNAME: ");
			String lastName = sc.next();
			while (lastName.length() < 2) {
				System.out.print("\nLASTNAME LENGTH LESS THAN 2 CHAR\nRE-ENTER THE NAME: ");
				lastName = sc.next();
			}
			
			System.out.print("\nENTER SEX [M] or [F]: ");
			String sex = sc.next();
			while ( (!sex.toLowerCase().equals("m")) && (!sex.toLowerCase().equals("f")) ) {
				System.out.print("ENTER SEX AGAIN: ");
				sex = sc.next();
			}
			
			System.out.println("\nENTER DATE OF BIRTH [yyyy-MM-dd]");
			System.out.print("ENTER YEAR: ");
			int yyyy = sc.nextInt();
			System.out.print("ENTER MONTH: ");
			int mm = sc.nextInt();
			while(mm > 12 || mm < 1) {
				System.out.print("OUT OF INDEX\nENTER MONTH AGAIN: ");
				mm = sc.nextInt();
			}
			System.out.print("ENTER DAYS: ");
			int dd = sc.nextInt();
			while(dd > 31 || dd < 1) {
				System.out.print("OUT OF INDEX\nENTER DAYS AGAIN: ");
				dd = sc.nextInt();
			}
			
			String dateOfBirth = (String) (yyyy + "-" + mm + "-" + dd);
			
			System.out.print("\nENTER TAX CODE [16 chars]: ");
			String taxCode = sc.next();
			while (taxCode.length() > 16 || taxCode.length() < 16) {
				if (taxCode.length() > 16) {
					System.out.print("\nTAX CODE LENGHT > 16 CHARACTERS\nDIGIT TAXCODE AGAIN: ");
					taxCode = sc.next();
				} else {
					System.out.print("\nTAX CODE LENGHT < 16 CHARACTERS\nDIGIT TAXCODE AGAIN: ");
					taxCode = sc.next();
				}
			}
			
			stmnt.execute("update students "
					+ "set name = '" + firstName + "', "
					+ "lastname = '" + lastName + "', "
					+ "sex = '" + sex + "', "
					+ "dateofbirth = '" + dateOfBirth + "', "
					+ "taxcode = '" + taxCode + "' "
					+ "where id = " + id + ";"
					);
			
			System.out.println("UPDATE COMPLETED!");
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
			stmnt.execute("DELETE FROM students where id = " + id + ";");
			System.out.println("DELETE COMPLETED!!!");
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
			ResultSet results = stmnt.executeQuery("SELECT * FROM STUDENTS;");
			
			// FORMAT WAY
			while (results.next()) {
				String student = String.format(
						  "ID: %s"
						+ " | NAME: %s"
						+ " | LASTNAME: %s"
						+ " | SEX: %s"
						+ " | DATE OF BIRTH: %s"
						+ " | TAX CODE: %s"
						,
						(results.getString(1)), // get value from db first column, in this case ID
						(results.getString(2)),
						(results.getString(3)),
						(results.getString(4)),
						(results.getString(5)),
						(results.getString(6))
						);
					System.out.println(student); // print all the student String
			}
			
			results.close();
			stmnt.close();
			super.close();
		} catch (SQLException e) {
			System.out.println("QUERY FAILED: " + e.getMessage());
		}
	}

}
