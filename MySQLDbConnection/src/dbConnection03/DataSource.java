package dbConnection03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataSource {

	public static final String DB_NAME = "DigitalLearning";
	public static final String DB_USER = "user=root";
	public static final String DB_PASSWORD = "password=root";
	public static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/" + DB_NAME + "?" + DB_USER + "&"
			+ DB_PASSWORD;

	public static final String TABLE_STUDENTS = "students";
	public static final String COLUMN_STUDENTS_ID = "id";
	public static final String COLUMN_STUDENTS_NAME = "name";
	public static final String COLUMN_STUDENTS_LASTNAME = "lastname";
	public static final String COLUMN_STUDENTS_SEX = "sex";
	public static final String COLUMN_STUDENTS_DATEOFBIRTH = "dateofbirth";
	public static final String COLUMN_STUDENTS_TAXCODE = "taxCode";
	public static final int INDEX_STUDENTS_ID = 1;
	public static final int INDEX_STUDENTS_NAME = 2;
	public static final int INDEX_STUDENTS_LASTNAME = 3;
	public static final int INDEX_STUDENTS_SEX = 4;
	public static final int INDEX_STUDENTS_DATEOFBIRTH = 5;
	public static final int INDEX_STUDENTS_TAXCODE = 6;

	public static final int ORDER_BY_NONE = 1;
	public static final int ORDER_BY_ASC = 2;
	public static final int ORDER_BY_DESC = 3;
	

//	we need to do is add an open method to
//	open the database and a close method to
//	close it so public boolean open....
	private Connection conn;

	public boolean open() {
		try {
			conn = DriverManager.getConnection(CONNECTION_STRING);
			System.out.println("CONNECTION ESTABLISHED\n");
			return true;
		} catch (SQLException e) {
			System.out.println("CAN'T CONNECT TO DATABASE: " + e.getMessage());
			return false;
		}
	}

	public void close() {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("CAN'T CLOSE THE CONNECTION: " + e.getMessage());
		}
	}

//	public List<Artist> queryArtists() {
//		
//		Statement statement = null;
//		ResultSet results = null;
//		
//		try {
//			
//			statement = conn.createStatement();
//			results = statement.executeQuery("SELECT * FROM " + TABLE_ARTISTS);
//			
//		List<Artist> artists = new ArrayList<>();
//		while(results.next()) {
//			Artist artist = new Artist();
//			artist.setId(results.getInt(COLUMN_ARTIST_ID));
//			artist.setName(results.getString(COLUMN_ARTIST_NAME));
//			artists.add(artist);
//		}
//			
//		return artists;
//		
//		} catch (SQLException e) {
//			System.out.println("QUERY FAILED: " + e.getMessage());
//			return null;
//		} finally {
//			try {
//				if (results != null) {
//					results.close();
//				}
//			} catch (SQLException e) {
//				System.out.println("ERROR CLOSING ResultSet: " + e.getMessage());
//			}
//			
//			try {
//				if (statement != null) {
//					statement.close();
//				}
//			} catch (SQLException e) {
//				System.out.println("ERROR CLOSING Statement: " + e.getMessage());
//			}
//		}
//	}

	public List<Student> queryStudents(int sortOrder) {
		
		StringBuilder sb = new StringBuilder("SELECT * FROM ");
		sb.append(TABLE_STUDENTS);
		if (sortOrder != ORDER_BY_NONE) {
			if (sortOrder == ORDER_BY_DESC) {
				sb.append(" ORDER BY ");
				sb.append("name ");
				sb.append("DESC");
			}
			else {
				sb.append(" ORDER BY ");
				sb.append("name ");
				sb.append("ASC");
			}
		}

		// try with resources.. at the end of the try those 'PARAMETERS..' will be automatically closed!
		// try (with resources) {}
		try (Statement statement = conn.createStatement();
//			 ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_STUDENTS)) {
			ResultSet results = statement.executeQuery(sb.toString())) {

			List<Student> students = new ArrayList<>();
			while (results.next()) {
				Student student = new Student();
//				student.setId(results.getInt(COLUMN_STUDENTS_ID));
//				student.setName(results.getString(COLUMN_STUDENTS_NAME));
				student.setId(results.getInt(INDEX_STUDENTS_ID));			// if we have a lot of data
				student.setName(results.getString(INDEX_STUDENTS_NAME));	// is better to use column index for faster search!
				student.setLastName(results.getString(INDEX_STUDENTS_LASTNAME));
				student.setSex(results.getString(INDEX_STUDENTS_SEX));
				student.setDateOfBirth(results.getString(INDEX_STUDENTS_DATEOFBIRTH));
				student.setTaxCode(results.getString(INDEX_STUDENTS_TAXCODE));
				students.add(student);
			}

			return students;

		} catch (SQLException e) {
			System.out.println("QUERY FAILED: " + e.getMessage());
			return null;
		} 
	}

}
