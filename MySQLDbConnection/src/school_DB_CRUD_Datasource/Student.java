package school_DB_CRUD_Datasource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Student extends Datasource implements ICRUD{

	@Override // QUERY * STUDENTS
	public void readAll() {
		
		String queryStudents = "SELECT * FROM students;";
		try (Statement stmnt = conn.createStatement();
			 ResultSet results = stmnt.executeQuery(queryStudents);) {
			
			while(results.next()) {
				System.out.println(
						"ID: " + results.getString(1) +
						"\nName: " + results.getString(2) +
						"\nLastName: " + results.getString(3) +
						"\nAge: " + results.getString(4) +
						"\n"
						);
			}
		
		} catch(SQLException e) {
			System.out.println("Query failed: " + e.getMessage());
		}
		
	}

	@Override // QUERY * STUDENTS WHERE {ID}
	public void search() {
		
		System.out.print("\nInsert the id of the Student you want to see: ");
		int std_id = int_sc.nextInt();;;
		
		String search_std = "SELECT * FROM students WHERE std_id = " + std_id + ";";
		
		try (Statement stmnt = conn.createStatement();
			 ResultSet results = stmnt.executeQuery(search_std);) {
			
			System.out.println(""); // space between the quest and student result
			
			while(results.next()) {
				String student = (
					"ID: " + results.getString(1) +
					"\nName: " + results.getString(2) +
					"\nLastName: " + results.getString(3) +
					"\nAge: " + results.getString(4)
				);
				
					System.out.println(student);
					int rowCounter = results.getRow();
					if (rowCounter > 0)
						System.out.println("-> For student select " + rowCounter + " row(s) affected.");
			}
			
		} catch(SQLException e) {
			System.out.println("Query failed: " + e.getMessage());
		}
		
	}

	@Override // INSERT STUDENT
	public void insert() {
		
		System.out.print("\n>>> INSERT FORM <<<\nInsert student Name: ");
		String std_name = sc.nextLine();
		System.out.print("Insert student LastName: ");
		String std_lastName = sc.nextLine();
		System.out.print("Insert student Age: ");
		int std_age = int_sc.nextInt();;
		while(std_age < 12) {
			System.out.print("\nEnter an age >= 12.. => ");
			std_age = int_sc.nextInt();;
		}
		
		String ins_student_query = "INSERT INTO students (std_name, std_lastname, std_age) VALUES"
									+ " ('" + std_name 		+ "'"
									+ ", '" + std_lastName  + "'"
									+ ", "  + std_age 		+ ");";
		
		// STUDENT INSERT
		try (Statement stmnt = conn.createStatement();) {
			
			int row_ins_student = stmnt.executeUpdate(ins_student_query); // execute first the insert
			 
			 System.out.println("\nFor student insert we have " + row_ins_student + " row(s) affected.");
			
		} catch(SQLException e) {
			System.out.println("Query failed: " + e.getMessage());
		}
		
		String ins_student_data_query = "SELECT * FROM students WHERE std_id = ( SELECT MAX(std_id) FROM students);"; 
		
		// SHOW STUDENT DATA ENTERED
		try (Statement stmnt = conn.createStatement();
			 ResultSet results = stmnt.executeQuery(ins_student_data_query);) {
			
			while(results.next()) { // then we see the data entered
				String ins_student_data = String.format(
						  "ID: %s"
						+ "\nName: %s"
						+ "\nLastName: %s"
						+ "\nAge: %s"
						,
						results.getString(1),
						results.getString(2),
						results.getString(3),
						results.getString(4)
				);
					System.out.println("\nINSERT DONE!\n\nThis is your insert:\n" + ins_student_data);
		}
			
		} catch(SQLException e) {
			System.out.println("Query failed: " + e.getMessage());
		}
		
	}

	@Override // MODIFY A STUDENT
	public void update() {
		
		System.out.print(">>> UPDATE FORM <<<\n"
				+ "Insert Student ID that u want to modify: ");
		int std_id = int_sc.nextInt();;
		System.out.print("Insert student Name: ");
		String std_name = sc.nextLine();
		System.out.print("Insert student LastName: ");
		String std_lastName = sc.nextLine();
		System.out.print("Insert student age: ");
		int std_age = int_sc.nextInt();;
		while(std_age < 12) {
			System.out.print("Enter an age >= 12.. => ");
			std_age = int_sc.nextInt();;
		}
		
		String upd_Student = "UPDATE students"
				+ " SET std_name = '" + std_name 	 + "',"
				+ " std_lastname = '" + std_lastName + "',"
				+ " std_age = " 	  + std_age
				+ " WHERE std_id = "  + std_id 		 + ";";
		
		// UPDATE STUDENT
		try (Statement stmnt = conn.createStatement();) {
			
			int row_upd_student = stmnt.executeUpdate(upd_Student);
			
			if (row_upd_student != 0) {
				
				String upd_student_data = "SELECT * FROM students WHERE std_id = " + std_id + ";";
				
				// UPDATED STUDENT SHOW DATA JUST MODIFIED
				try (ResultSet results = stmnt.executeQuery(upd_student_data);) {
					
					System.out.println("\nUPDATE DONE!\n\nThese are the data of your update: ");
					
					while(results.next()) {
						System.out.println(
								  "ID: " 		 + results.getString(1)
								+ "\nName: " 	 + results.getString(2)
								+ "\nLastName: " + results.getString(3)
								+ "\nAge: " 	 + results.getString(4)
								);
					}
					
				} catch(SQLException e) {
					System.out.println("Query failed: " + e.getMessage());
				} // END INNER TRY
				
				System.out.println("\nFor student update we have " + row_upd_student + " row(s) affected.");
				
			}
			else
				System.out.println("\nNo student found with this [ID]!");
			
		} catch(SQLException e) {
			System.out.println("Query failed: " + e.getMessage());
		} // END EXTERNAL TRY
		
		
	}

	@Override // DELETE A STUDENT
	public void delete() {
		
		System.out.print("Enter the [id] of the student you want to delete: ");
		int std_id = int_sc.nextInt();;
		
		String del_student = "DELETE FROM students WHERE std_id = " + std_id + ";";
		
		String student_query = "SELECT * FROM students WHERE std_id = " + std_id + ";";
		
		try (Statement stmnt = conn.createStatement();
			 ResultSet results = stmnt.executeQuery(student_query)) {
			
			while(results.next()) {
				System.out.println(
						  "\nYou have deleted the following student:"
						+ "\nID: " 		 + results.getString(1)
						+ "\nName: " 	 + results.getString(2)
						+ "\nLastName: " + results.getString(3)
						+ "\nAge: " 	 + results.getString(4)
						);
			}
			
			int row_del_student = stmnt.executeUpdate(del_student);
			if (row_del_student > 0) {
				System.out.println("\nFor delete student we have " + row_del_student + " row(s) affected.");				
			} else {
				System.out.println("\nNo student found!");
			}
			
		} catch(SQLException e) {
			System.out.println("Query failed: " + e.getMessage());
		}
		
	}

}
