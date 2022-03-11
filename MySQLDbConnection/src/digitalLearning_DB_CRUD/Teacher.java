package digitalLearning_DB_CRUD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Teacher extends Datasource implements CRUD_METHODS {

	private Scanner sc = new Scanner(System.in);
	
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
						,
						(results.getString(1)),
						(results.getString(2)),
						(results.getString(3)),
						(results.getString(4)),
						(results.getString(5)),
						(results.getString(6))
						);
				System.out.println(teacher);
			}
			
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
			String name = sc.next();
			System.out.print("\nENTER LASTNAME: ");
			String lastName = sc.next();
			
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
			
			System.out.print("\nENTER COMPANY-NAME: ");
			String companyName = sc.next();
			
			System.out.print("\nENTER MONTHLY-SALARY: ");
			int monthlySalary = sc.nextInt();
			
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
			int id = sc.nextInt();
			
			System.out.print("ENTER TEACHER NAME: ");
			String name = sc.next();
			System.out.print("\nENTER LASTNAME: ");
			String lastName = sc.next();
			
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
			
			System.out.print("\nENTER COMPANY-NAME: ");
			String companyName = sc.next();
			
			System.out.print("\nENTER MONTHLY-SALARY: ");
			int monthlySalary = sc.nextInt();
			
			stmnt.execute(
					"UPDATE TEACHERS"
					+ " SET name = '" + name
					+ "', lastname = '" + lastName
					+ "', dateofbirth = '" + dateOfBirth
					+ "', taxcode = '" + taxCode
					+ "', companyName = " + companyName
					+ "', monthlysalary = " + monthlySalary
					+ " WHERE id = " + id
					+ ";"
					);
			
			System.out.println(">>> UPDATE COMPLETED!");
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
			stmnt.execute("DELETE FROM TEACHERS WHERE id = " + id);
			
			System.out.println(">>> DELETE COMPLETED!");
			super.close();
		} catch (SQLException e) {
			System.out.println("QUERY FAILED: " + e.getMessage());
		}
	}

}
