package digitalLearning_DB_CRUD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Student extends Datasource implements ICRUD {

	private Scanner sc = new Scanner(System.in);
	private Scanner int_sc = new Scanner(System.in);
	
	private int id;
	private String name;
	private String lastName;
	private String sex;
	private String dateOfBirth;
	private String taxCode;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}
	
	// metodo con setter e results.getString
	public List<Student> search02() {
		String query = "SELECT * FROM students";
		
		try {
			super.open();
			stmnt = conn.createStatement();
			ResultSet results = stmnt.executeQuery(query);
			
			List<Student> students = new ArrayList<>();
			
			while(results.next()) {
				Student student = new Student();
				student.setId(results.getInt(1));
				student.setName(results.getString(2));
				student.setLastName(results.getString(3));
				student.setSex(results.getString(4));
				student.setDateOfBirth(results.getString(5));
				student.setTaxCode(results.getString(6));
				students.add(student);
			}
			
			return students;
		}catch(SQLException e) {
			System.out.println("Query failed: " + e.getMessage());
			return null;
		}
		
	}

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
			String firstName = sc.nextLine();
			while (firstName.length() < 2) {
				System.out.print("\nNAME LENGTH LESS THAN 2 CHAR\nRE-ENTER THE NAME: ");
				firstName = sc.nextLine();
			}
			
			System.out.print("ENTER LASTNAME: ");
			String lastName = sc.nextLine();
			while (lastName.length() < 2) {
				System.out.print("\nLASTNAME LENGTH LESS THAN 2 CHAR\nRE-ENTER THE NAME: ");
				lastName = sc.nextLine();
			}
			
			System.out.print("ENTER SEX [M] or [F]: ");
			String sex = sc.nextLine();
			while ( (!sex.toLowerCase().equals("m")) && (!sex.toLowerCase().equals("f")) ) {
				System.out.print("\nENTER SEX AGAIN: ");
				sex = sc.nextLine();
			}
			
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
			int id = int_sc.nextInt();
			
			System.out.print("ENTER STUDENT FIRST NAME: ");
			String firstName = sc.nextLine();
			while (firstName.length() < 2) {
				System.out.print("\nNAME LENGTH LESS THAN 2 CHAR\nRE-ENTER THE NAME: ");
				firstName = sc.nextLine();
			}
			
			System.out.print("ENTER LASTNAME: ");
			String lastName = sc.nextLine();
			while (lastName.length() < 2) {
				System.out.print("\nLASTNAME LENGTH LESS THAN 2 CHAR\nRE-ENTER THE NAME: ");
				lastName = sc.nextLine();
			}
			
			System.out.print("ENTER SEX [M] or [F]: ");
			String sex = sc.nextLine();
			while ( (!sex.toLowerCase().equals("m")) && (!sex.toLowerCase().equals("f")) ) {
				System.out.print("\nENTER SEX AGAIN: ");
				sex = sc.nextLine();
			}
			
			System.out.println("\nENTER DATE OF BIRTH [yyyy-MM-dd]");
			System.out.print("ENTER YEAR: ");
			int yyyy = int_sc.nextInt();
			System.out.print("ENTER MONTH: ");
			int mm = int_sc.nextInt();
			while(mm > 12 || mm < 1) {
				System.out.print("OUT OF INDEX\nENTER MONTH AGAIN: ");
				mm = int_sc.nextInt();
			}
			System.out.print("ENTER DAYS: ");
			int dd = int_sc.nextInt();
			while(dd > 31 || dd < 1) {
				System.out.print("OUT OF INDEX\nENTER DAYS AGAIN: ");
				dd = int_sc.nextInt();
			}
			
			String dateOfBirth = (String) (yyyy + "-" + mm + "-" + dd);
			
			System.out.print("ENTER TAX CODE [16 chars]: ");
			String taxCode = sc.nextLine();
			while (taxCode.length() > 16 || taxCode.length() < 16) {
				if (taxCode.length() > 16) {
					System.out.print("\nTAX CODE LENGHT > 16 CHARACTERS\nDIGIT TAXCODE AGAIN: ");
					taxCode = sc.nextLine();
				} else {
					System.out.print("\nTAX CODE LENGHT < 16 CHARACTERS\nDIGIT TAXCODE AGAIN: ");
					taxCode = sc.nextLine();
				}
			}
			
			int rowCounter = stmnt.executeUpdate("update students "
					+ "set name = '" + firstName + "', "
					+ "lastname = '" + lastName + "', "
					+ "sex = '" + sex + "', "
					+ "dateofbirth = '" + dateOfBirth + "', "
					+ "taxcode = '" + taxCode + "' "
					+ "where id = " + id + ";"
					);
			
			if(rowCounter > 0)
				System.out.println("UPDATE COMPLETED!");
			else
				System.out.println("\n> Student with this [ID] not found.");
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
			int rowCounter = stmnt.executeUpdate("DELETE FROM students where id = " + id + ";");
			if(rowCounter > 0)
				System.out.println("DELETE COMPLETED!!!");
			else
				System.out.println("No student found with this [ID].");
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
