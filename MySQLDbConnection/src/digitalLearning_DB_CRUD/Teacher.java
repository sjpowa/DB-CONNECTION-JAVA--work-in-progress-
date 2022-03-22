package digitalLearning_DB_CRUD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Teacher extends Datasource implements ICRUD {

	private Scanner sc = new Scanner(System.in);
	private Scanner int_sc = new Scanner(System.in);
	
	@Override
	public void search(int id) {
		
		try {
			super.open();
			stmnt = conn.createStatement();
			ResultSet results = stmnt.executeQuery("SELECT * FROM TEACHERS WHERE id = " + id);
			
			while(results.next()) {
				
				String teacher = String.format(
						  "NAME: %s"
						+ " | LASTNAME: %s"
						+ " | DOB: %s"
						+ " | TAXCODE: %s"
						+ " | COMPANY-NAME: %s"
						+ " | MONTHLY-SALARY: %s"
						+ " | ROW NUMBER: %s"
						,
						(results.getString(1)),
						(results.getString(2)),
						(results.getString(3)),
						(results.getString(4)),
						(results.getString(5)),
						(results.getString(6)),
						(results.getRow())
						);
				System.out.println(teacher);
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
			
			System.out.print("ENTER TEACHER NAME: ");
			String name = sc.nextLine();
			System.out.print("ENTER LASTNAME: ");
			String lastName = sc.nextLine();
			
			System.out.println("ENTER DATE OF BIRTH [yyyy-MM-dd]");
			System.out.print("ENTER YEAR: ");
			int yyyy = int_sc.nextInt();
			System.out.print("ENTER MONTH: ");
			int mm = int_sc.nextInt();
			while(mm > 12 || mm < 1) {
				System.out.print("\nOUT OF INDEX\nENTER MONTH AGAIN: ");
				mm = int_sc.nextInt();
			}
			System.out.print("ENTER DAYS: ");
			int dd = int_sc.nextInt();
			while(dd > 31 || dd < 1) {
				System.out.print("\nOUT OF INDEX\nENTER DAYS AGAIN: ");
				dd = int_sc.nextInt();
			}
			
			String dateOfBirth = (String) (yyyy + "-" + mm + "-" + dd);
			
			System.out.print("ENTER TAX CODE [16 chars]: ");
			String taxCode = sc.nextLine();
			while (taxCode.length() > 16 || taxCode.length() < 16) {
				if (taxCode.length() > 16) {
					System.out.print("\nTAX CODE LENGHT > 16 CHARACTERS\nDIGIT TAXCODE AGAIN: ");
				} else {
					System.out.print("\nTAX CODE LENGHT < 16 CHARACTERS\nDIGIT TAXCODE AGAIN: ");
				}
				taxCode = sc.nextLine();
			}
			
			System.out.print("ENTER COMPANY-NAME: ");
			String companyName = sc.nextLine();
			
			System.out.print("ENTER MONTHLY-SALARY: ");
			int monthlySalary = int_sc.nextInt();
			
			stmnt.execute(
					"INSERT INTO TEACHERS (name, lastname, dateOfBirth, taxCode, companyName, monthlySalary) VALUES"
					+ " ('"  + name
					+ "', '" + lastName
					+ "', '" + dateOfBirth
					+ "', '" + taxCode
					+ "', '" + companyName
					+ "', " + monthlySalary
					+ ");"
					);
			
			System.out.println(">>> INSERT COMPLETED!");
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
			
			System.out.print("INSERT THE 'ID' OF THE TEACHER U WANT TO UPDATE: ");
			int id = int_sc.nextInt();
			
			System.out.print("ENTER TEACHER NAME: ");
			String name = sc.nextLine();
			System.out.print("ENTER LASTNAME: ");
			String lastName = sc.nextLine();
			
			System.out.println("ENTER DATE OF BIRTH [yyyy-MM-dd]");
			System.out.print("ENTER YEAR: ");
			int yyyy = int_sc.nextInt();
			System.out.print("ENTER MONTH: ");
			int mm = int_sc.nextInt();
			while(mm > 12 || mm < 1) {
				System.out.print("\nOUT OF INDEX\nENTER MONTH AGAIN: ");
				mm = int_sc.nextInt();
			}
			System.out.print("ENTER DAYS: ");
			int dd = int_sc.nextInt();
			while(dd > 31 || dd < 1) {
				System.out.print("\nOUT OF INDEX\nENTER DAYS AGAIN: ");
				dd = int_sc.nextInt();
			}
			
			String dateOfBirth = (String) (yyyy + "-" + mm + "-" + dd);
			
			System.out.print("ENTER TAX CODE [16 chars]: ");
			String taxCode = sc.nextLine();
			while (taxCode.length() > 16 || taxCode.length() < 16) {
				if (taxCode.length() > 16) {
					System.out.print("\nTAX CODE LENGHT > 16 CHARACTERS\nDIGIT TAXCODE AGAIN: ");
				} else {
					System.out.print("\nTAX CODE LENGHT < 16 CHARACTERS\nDIGIT TAXCODE AGAIN: ");
				}
				taxCode = sc.nextLine();
			}
			
			System.out.print("\nENTER COMPANY-NAME: ");
			String companyName = sc.nextLine();
			
			System.out.print("\nENTER MONTHLY-SALARY: ");
			int monthlySalary = int_sc.nextInt();
			
			int rowCounter = stmnt.executeUpdate(
					  "UPDATE TEACHERS"
					+ " SET name = '" + name
					+ "', lastname = '" + lastName
					+ "', dateofbirth = '" + dateOfBirth
					+ "', taxcode = '" + taxCode
					+ "', companyName = '" + companyName
					+ "', monthlysalary = " + monthlySalary
					+ " WHERE id = " + id
					+ ";"
					);
			
			if(rowCounter > 0)
				System.out.println(">>> UPDATE COMPLETED!");
			else
				System.out.println("No teacher found with this [ID].");
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
			int rowCounter = stmnt.executeUpdate("DELETE FROM TEACHERS WHERE id = " + id);
			
			if(rowCounter > 0)
				System.out.println(">>> DELETE COMPLETED!");
			else
				System.out.println("No teacher found with this [ID].");
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
			ResultSet results = stmnt.executeQuery("SELECT * FROM TEACHERS;");
			
			while(results.next()) {
				String teacher = String.format(
						  "ID: %s"
						+ " | NAME: %s"
						+ " | LASTNAME: %s"
						+ " | DOB: %s"
						+ " | TAXCODE: %s"
						+ " | COMPANY-NAME: %s"
						+ " | MONTHLY-SALARY: %s"
						+ " | Row number: %s"
						,
						(results.getString(1)),
						(results.getString(2)),
						(results.getString(3)),
						(results.getString(4)),
						(results.getString(5)),
						(results.getString(6)),
						(results.getString(7)),
						(results.getRow())
						);
				System.out.println(teacher);
			}
			
			results.close();
			stmnt.close();
			super.close();
		} catch (SQLException e) {
			System.out.println("QUERY FAILED: " + e.getMessage());
		}
	}

}
