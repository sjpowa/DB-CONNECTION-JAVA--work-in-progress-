package school_DB_CRUD_Datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Teacher extends Datasource implements ICRUD {

	@Override // SELECT * TEACHERS
	public void readAll() {
		
		String teachers_query = "SELECT * FROM TEACHERS;";
		
		try (Statement stmnt = conn.createStatement();
			 ResultSet results = stmnt.executeQuery(teachers_query)) {
			
			System.out.println("All teachers are:");
			
			while(results.next()) {
						System.out.println(
								"\nID: " 		 + results.getString(1) +
								"\nName: " 	 	 + results.getString(2) +
								"\nLastname: " 	 + results.getString(3) +
								"\nAge: "		 + results.getString(4)
								);
			}
			
		} catch(SQLException e) {
			System.out.println("Query failed: " + e.getMessage());
		}
	}

	
	@Override // SELECT * FROM TEACHERS WHERE ID = ?
	public void search() {

		System.out.print("\nEnter the [ID] of the teacher you want to see: ");
		int tchr_id = sc.nextInt();
		
		String student_query = "SELECT * FROM teachers WHERE tchr_id = " + tchr_id + ";";
		
		try (Statement stmnt = conn.createStatement();
			 ResultSet results = stmnt.executeQuery(student_query);) {
			
			while(results.next()) {
				System.out.printf(
						  "\nTeacher with [ID] = " + tchr_id + " :"
						+ "\nID: %s"
						+ "\nName: %s"
						+ "\nLastName: %s"
						+ "\nAge: %s"
						,
						results.getString(1),
						results.getString(2),
						results.getString(3),
						results.getString(4)
						);
			}
			
		} catch(SQLException e) {
			System.out.println("Query failed: " + e.getMessage());
		}
		
	}

	@Override
	public void insert() {
		
		String ins_teacher_query = "INSERT INTO teachers (tchr_name, tchr_lastname, tchr_age) VALUES"
				+ " (?, ?, ?);";
		
		try(PreparedStatement prpstmnt = conn.prepareStatement(ins_teacher_query);) {
			
			System.out.print("\nEnter teacher name: ");
			String tchr_name = sc.nextLine();
			System.out.print("Enter teacher lastname: ");
			String tchr_lastName = sc.nextLine();
			System.out.print("Enter teacher age: ");
			int tchr_age = sc.nextInt();
			
			prpstmnt.setString(1, tchr_name);
			prpstmnt.setString(2, tchr_lastName);
			prpstmnt.setInt(3, tchr_age);
			
			int row_ins_teacher = prpstmnt.executeUpdate();
			
			if (row_ins_teacher > 0)
				System.out.println("\nTeacher successfully updated!");
			
		} catch(SQLException e) {
			System.out.println("Query failed: " + e.getMessage());
		} // END FIRST TRY
		
		String last_ins_teacher = "SELECT * FROM teachers WHERE tchr_id ="
				+ " ( SELECT max(tchr_id) FROM teachers);";
		
		// SEE WHAT HAVE U ENTERED AS NEW INSERT TEACHER
		try (Statement stmnt = conn.createStatement();
			 ResultSet results = stmnt.executeQuery(last_ins_teacher)) {
			
			while(results.next()) {
				String teacher = String.format(
						  "\nID: %s"
						+ "\nName: %s"
						+ "\nLastname: %s"
						+ "\nAge: %s"
						,
						results.getString(1),
						results.getString(2),
						results.getString(3),
						results.getString(4)
						);
				System.out.println("\nThis is the teacher you have entered:" + teacher);
			}
			
		} catch(SQLException e) {
			System.out.println("Query failed: " + e.getMessage());
		}
		
	}

	@Override
	public void update() {
		
		String upd_teacher_query = "UPDATE teachers SET tchr_name = ?, tchr_lastname = ?, tchr_age = ?"
				+ " WHERE tchr_id = ?;";
		
		String upd_teacher_query_data = "SELECT * FROM teachers WHERE tchr_id = ?;";
		
		try (PreparedStatement prpstmnt = conn.prepareStatement(upd_teacher_query);
			 PreparedStatement prpstmnt02 = conn.prepareStatement(upd_teacher_query_data);) {
			
			System.out.print("\nEnter the [ID] of the teacher you want to modify: ");
			int tchr_id = int_sc.nextInt();
			System.out.print("Enter teacher name: ");
			String tchr_name = sc.nextLine();
			System.out.print("Enter teacher Lastname: ");
			String tchr_lastName = sc.nextLine();
			System.out.print("Enter teacher age: ");
			int tchr_age = sc.nextInt();
			
			prpstmnt.setString(1, tchr_name);
			prpstmnt.setString(2, tchr_lastName);
			prpstmnt.setInt(3, tchr_age);
			prpstmnt.setInt(4, tchr_id);
			
			int row_upd_teacher_query = prpstmnt.executeUpdate();
			
			if (row_upd_teacher_query > 0) {
				
				prpstmnt02.setInt(1, tchr_id);
				
				ResultSet results = prpstmnt02.executeQuery();
				
				while(results.next()) {
					System.out.println(
							"\nThis is your update:" 					+
							"\nID: " 		 + results.getString(1) +
							"\nName: " 	 	 + results.getString(2) +
							"\nLastname: " 	 + results.getString(3) +
							"\nAge: " 	 	 + results.getString(4)
							);
				}
				
				System.out.println("\nUpdate executed successfully, "
						+ row_upd_teacher_query + " row(s) affected.");
				results.close();
			}
			else
				System.out.println("\nTeacher not found with this [ID]!");
			
		} catch(SQLException e) {
			System.out.println("Query failed: " + e.getMessage());
		}
		
	}

	@Override
	public void delete() {
		
		System.out.print("Enter the [ID] of the teacher that you want to delete: ");
		int tchr_id = sc.nextInt();
		
		String del_teacher_data = "SELECT * FROM teachers WHERE tchr_id = " + tchr_id + ";";
		String del_teacher = "DELETE FROM teachers WHERE tchr_id = " + tchr_id + ";";
		
		try (Statement stmnt = conn.createStatement();
			 ResultSet results = stmnt.executeQuery(del_teacher_data);) {
			
			while(results.next()) {
				System.out.println(
						"\nThe teacher that you have just deleted is:" 	+
						"\nID: " 		+ results.getString(1) 	 		+
						"\nName: " 	 	+ results.getString(2) 	 		+
						"\nLastname: "  + results.getString(3) 	 		+
						"\nAge: " 	 	+ results.getString(4)
						);
			}
			
			int row_del_teacher = stmnt.executeUpdate(del_teacher);
			
			if (row_del_teacher > 0)
				System.out.println("\nDelete completed!");
			else
				System.out.println("\nNo teacher found!");
			
		} catch(SQLException e) {
			System.out.println("Query failed: " + e.getMessage());
		}
		
	}
	
	// METHOD TO CREATE TRIGGER INS A TEACHER IN HIRED-TEACHER TABLE AFTER TEACHER INSERT
	public void trigger_ins_hired_teacher() {
		
		String trigger_ins_hired_teacher_query =
				    " CREATE TRIGGER ins_hired_teacher_automatically_after_teacher_ins "
				  + " AFTER INSERT ON teachers "
				  + " FOR EACH ROW BEGIN "
				  + " INSERT INTO hired_teachers (hrd_tchr_id, hrd_tchr_name, hrd_tchr_lastname) "
				  + " VALUES (new.tchr_id, new.tchr_name, new.tchr_lastname); "
				  + " END; ";
		
		try(Statement stmnt = conn.createStatement();) {
			
			stmnt.execute(trigger_ins_hired_teacher_query);
			System.out.println("> TRIGGER 'trigger_ins_hired_teacher_query' CREATED!");
			
		} catch(SQLException e) {
			System.out.println("Query failed: " + e.getMessage());
		}
		
	}

}
