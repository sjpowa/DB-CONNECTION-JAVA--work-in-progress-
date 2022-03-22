package school_DB_CRUD_Datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Clazz extends Datasource implements ICRUD {

	@Override
	public void readAll() {
		
		String classes_query = "SELECT * FROM classes;";
		
		try (Statement stmnt = conn.createStatement();
			 ResultSet results = stmnt.executeQuery(classes_query)) {
			
			System.out.println("Classes List:");
			
			while(results.next()) {
				String clazz = String.format(
						  "\nClass ID: %s"
						+ "\nClass Name: %s"
						+ "\nStudent ID: %s"
						+ "\nTeacher ID: %s"
						,
						results.getString(1),
						results.getString(2),
						results.getString(3),
						results.getString(4)
						);
				System.out.println(clazz);
			}
			
		}catch(SQLException e) {
			System.out.println("Query failed: " + e.getMessage());
		}
	}

	@Override
	public void search() {
		
		String class_query = "SELECT * FROM classes WHERE clss_id = ?;";
		
		try (PreparedStatement prpstmnt = conn.prepareStatement(class_query);) {
			
			System.out.print("\nEnter the [ID] of the class you want to see: ");
			int clss_id = sc.nextInt();
			
			prpstmnt.setInt(1, clss_id);
			
			prpstmnt.executeQuery();
			
			ResultSet results = prpstmnt.executeQuery();
			
			while(results.next()) {
				System.out.println(
						"\nClass ID: " 	 + results.getString(1) +
						"\nClass name: " + results.getString(2) +
						"\nStudent ID: " + results.getString(3) +
						"\nTeacher ID: " + results.getString(4)
						);
			}
			
			results.close();
		} catch(SQLException e) {
			System.out.println("Query failed: " + e.getMessage());
		}
		
	}

	@Override
	public void insert() {
		
		String ins_class_query = "INSERT INTO classes"
				+ " (clss_name, studentID, teacherID) VALUES"
				+ " (?, ?, ?);";
		
		try (PreparedStatement prpstmnt = conn.prepareStatement(ins_class_query);) {
			
			System.out.print("Enter class name: ");
			String clss_names = sc.nextLine();
			System.out.print("Enter student [ID]: ");
			int clss_studentID = sc.nextInt();
			System.out.print("Enter teacher[ID]: ");
			int clss_teacherID = sc.nextInt();
			
			prpstmnt.setString(1, clss_names);
			prpstmnt.setInt(2, clss_studentID);
			prpstmnt.setInt(3, clss_teacherID);
			
			int row_ins_class = prpstmnt.executeUpdate();
			
			if (row_ins_class > 0) {
			
				String class_query = "SELECT * FROM classes WHERE clss_id ="
						+ " ( SELECT max(clss_id) FROM classes);";
				
				try (ResultSet results = prpstmnt.executeQuery(class_query);) {
					
					while(results.next()) {
						System.out.println(
								"\nThis is your class insert:" 			+
								"\nClass ID: " 	 + results.getString(1) +
								"\nClass name: " + results.getString(2) +
								"\nStudent ID: " + results.getString(3) +
								"\nTeacher ID: " + results.getString(4)
								);
					}
					
					results.close();
					
					
				} catch(SQLException e) {
					System.out.println("Query failed: " + e.getMessage());
				}
				
				System.out.println("Insert done successfully!");
			}
		} catch(SQLException e) {
			System.out.println("Query failed: " + e.getMessage());
		}
		
	}

	@Override
	public void update() {
		
		System.out.print("Enter the [ID] of the class you want to modify: ");
		int clss_id = int_sc.nextInt();
		System.out.print("Enter class name: ");
		String clss_name = sc.nextLine();
		System.out.print("Enter studentID: ");
		int studentID = int_sc.nextInt();
		System.out.print("Enter teacherID: ");
		int teacherID = int_sc.nextInt();
		
		String upd_class_query = "UPDATE classes SET"
				+ " clss_name = '"		+ clss_name + "'"
				+ ", studentID = " 		+ studentID
				+ ", teacherID = " 		+ teacherID
				+ " WHERE clss_id = " 	+ clss_id
				+ ";";
		
		try (Statement stmnt = conn.createStatement();) {
			
			int row_upd_class = stmnt.executeUpdate(upd_class_query);
			
			String upd_class_data = "SELECT * FROM classes WHERE clss_id = " + clss_id + ";";
			
			try(ResultSet results = stmnt.executeQuery(upd_class_data)){
				
				System.out.println("\nThis is the update you have just done:");
				while(results.next()) {
					System.out.printf(
							  "Class ID: %s"
							+ "\nClass name: %s"
							+ "\nStudent ID: %s"
							+"\nTeacher ID: %s"
							,
							results.getString(1),
							results.getString(2),
							results.getString(3),
							results.getString(4)
							);
				}
				
			}catch(SQLException e) {
				System.out.println("Query failed: " + e.getMessage());
			}
			
			if(row_upd_class > 0)
				System.out.println("\nFor classes update we have " + row_upd_class + " row(s) affected.");
			else
				System.out.println("\nNo class found with this [ID]!");
			
		} catch(SQLException e) {
			System.out.println("Query failed: " + e.getMessage());
		}
	}

	@Override
	public void delete() {
		
		System.out.print("Enter the [ID] of the Class you want to delete: ");
		int clss_id = int_sc.nextInt();
		
		String del_class_query = "DELETE FROM classes WHERE clss_id = " + clss_id + ";";
		
		String class_query = "SELECT * FROM classes WHERE clss_id = " + clss_id + ";";
		
		try(Statement stmnt = conn.createStatement();
			ResultSet results = stmnt.executeQuery(class_query)){
			
			while(results.next()) {
				System.out.println(
						  "Class ID: " 	   + results.getString(1)
						+ "\nClass name: " + results.getString(2)
						+ "\nStudent ID: " + results.getString(3)
						+ "\nTeacher ID: " + results.getString(4)
						);
			}
			
			int rowCounter = stmnt.executeUpdate(del_class_query);
			
			if(rowCounter > 0)
				System.out.println("\nFor Class delete method we have " + rowCounter + " row(s) affected.");
			else
				System.out.println("\nNo class found!");
			
		}catch(SQLException e) {
			System.out.println("Query failed: " + e.getMessage());
		}
		
	}

}
