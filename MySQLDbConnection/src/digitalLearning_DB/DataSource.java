package digitalLearning_DB;

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
	public static final String CONNECTION_STRING = 
			"jdbc:mysql://localhost:3306/" + DB_NAME + "?" + DB_USER + "&" + DB_PASSWORD;

	// COLUMN INDEX => FASTER SEARCH... NAME MATCHING IS SLOWER THAN INDEX
	// THE COMPILER, WITH INDEX SEARCH, JUST KNOWS WHERE TO GO!!!
	// Quando crei una tabella, tutte le variabili che vai ad inserire
	// sono indicizzate a partire dal nr.1..
	// infatti se fai girare il programma con l'index sballato di una delle tabelle
	// cioe' ci metti uno 0 oppure un valore > || < del column.length/ nr delle colonne (capisc a te)
	// sara' la console a dirti: guadda ca si out of indice!!!
	
	public static final String TABLE_STUDENTS = "students";
//	public static final String COLUMN_STUDENTS_ID = "id";
//	public static final String COLUMN_STUDENTS_NAME = "name";
//	public static final String COLUMN_STUDENTS_LASTNAME = "lastname";
//	public static final String COLUMN_STUDENTS_SEX = "sex";
//	public static final String COLUMN_STUDENTS_DATEOFBIRTH = "dateOfBirth";
//	public static final String COLUMN_STUDENTS_TAXCODE = "taxCode";
	public static final int INDEX_STUDENTS_ID 		   = 1;
	public static final int INDEX_STUDENTS_NAME 	   = 2;
	public static final int INDEX_STUDENTS_LASTNAME    = 3;
	public static final int INDEX_STUDENTS_SEX 		   = 4;
	public static final int INDEX_STUDENTS_DATEOFBIRTH = 5;
	public static final int INDEX_STUDENTS_TAXCODE 	   = 6;

	public static final String TABLE_CLASSES = "classes";
//	public static final String COLUMN_CLASSES_ID = "id";
//	public static final String COLUMN_CLASSES_NAME = "name";
//	public static final String COLUMN_CLASSES_SCHOOLSUBJECT = "schoolSubject";
//	public static final String COLUMN_CLASSES_STUDENTID = "studentId";
//	public static final String COLUMN_CLASSES_TEACHERID = "teacherId";
	public static final int INDEX_CLASSES_ID 			= 1;
	public static final int INDEX_CLASSES_NAME 			= 2;
	public static final int INDEX_CLASSES_SCHOOLSUBJECT = 3;
	public static final int INDEX_CLASSES_STUDENTID 	= 4;
	public static final int INDEX_CLASSES_TEACHERID 	= 5;

	public static final String TABLE_TEACHERS = "teachers";
//	public static final String COLUMN_TEACHERS_ID = "id";
//	public static final String COLUMN_TEACHERS_NAME = "name";
//	public static final String COLUMN_TEACHERS_LASTNAME = "lastName";
//	public static final String COLUMN_TEACHERS_DATEOFBIRTH = "dateOfBirth";
//	public static final String COLUMN_TEACHERS_TAXCODE = "taxCode";
//	public static final String COLUMN_TEACHERS_COMPANYNAME = "companyName";
//	public static final String COLUMN_TEACHERS_MONTHLYSALARY = "monthlySalary";
	public static final int INDEX_TEACHERS_ID 			 = 1;
	public static final int INDEX_TEACHERS_NAME 		 = 2;
	public static final int INDEX_TEACHERS_LASTNAME 	 = 3;
	public static final int INDEX_TEACHERS_DATEOFBIRTH 	 = 4;
	public static final int INDEX_TEACHERS_TAXCODE 		 = 5;
	public static final int INDEX_TEACHERS_COMPANYNAME 	 = 6;
	public static final int INDEX_TEACHERS_MONTHLYSALARY = 7;
	
	public static final int ORDER_BY_NONE = 1;
	public static final int ORDER_BY_ASC  = 2;
	public static final int ORDER_BY_DESC = 3;

	public static final String INNER_JOIN_STUDENTS_CLASSES_ALL = "SELECT * FROM " + TABLE_STUDENTS + " INNER JOIN "
			+ TABLE_CLASSES + " ON " + TABLE_STUDENTS + "." + INDEX_STUDENTS_ID + " ON " + TABLE_CLASSES + "."
			+ INDEX_CLASSES_STUDENTID;

//	we need to do is add an open method to
//	open the database and a close method to
//	close it so public boolean open....
	private Connection conn;

	public boolean open() { // we use this method so than in Main we can open the connection
		try {
			conn = DriverManager.getConnection(CONNECTION_STRING);
			System.out.println("CONNECTION ESTABLISHED\n");
			return true;
		} catch (SQLException e) {
			System.out.println("CAN'T CONNECT TO DATABASE: " + e.getMessage());
			return false;
		}
	}

	public void close() { // we can decide in Main when and where to close the connection
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
			} else {
				sb.append(" ORDER BY ");
				sb.append("name ");
				sb.append("ASC");
			}
		}

		// try (with resources) {}
		// try with resources.. at the end of the try those '..PARAMETERS..' will be
		// closed automatically!
		try (Statement statement = conn.createStatement();
//			 ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_STUDENTS)) {
				ResultSet results = statement.executeQuery(sb.toString())) {

			List<Student> students = new ArrayList<>();
			
			while (results.next()) {
				Student student = new Student();
//				student.setId(results.getInt(COLUMN_STUDENTS_ID));
//				student.setName(results.getString(COLUMN_STUDENTS_NAME));
				student.setId(results.getInt(INDEX_STUDENTS_ID)); // if we have a lot of data
				student.setName(results.getString(INDEX_STUDENTS_NAME)); // is better to use column index for faster
																			// search!
				student.setLastName(results.getString(INDEX_STUDENTS_LASTNAME));
				student.setSex(results.getString(INDEX_STUDENTS_SEX));
				student.setDateOfBirth(results.getString(INDEX_STUDENTS_DATEOFBIRTH));
				student.setTaxCode(results.getString(INDEX_STUDENTS_TAXCODE));
				students.add(student);
			}

			return students;

		} catch (SQLException e) {
			System.out.println("QUERY FAILED: " + e.getMessage()); // getMessage from SQLExc
																	// will give us where the query error is..
			return null;
		}

	} // END QUERY STUDENT

	public List<Class> queryClasses() {

		try (Statement statement = conn.createStatement();
			ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_CLASSES);
			) {
			
			List<Class> classes = new ArrayList<>();
			
			while(results.next()) {
				Class clazz = new Class();
				clazz.setId(results.getInt(INDEX_CLASSES_ID));
				clazz.setName(results.getString(INDEX_CLASSES_NAME));
				clazz.setSchoolSubject(results.getString(INDEX_CLASSES_SCHOOLSUBJECT));
				clazz.setStudentID(results.getInt(INDEX_CLASSES_STUDENTID));
				clazz.setTeacherID(results.getInt(INDEX_CLASSES_TEACHERID));
				classes.add(clazz);
			}
			
			return classes;
			
		} catch (SQLException e) {
			System.out.println("QUERY FAILED: " + e.getMessage());
			return null;
		}

	} // END QUERY CLASSES
	
	public List<Teacher> queryTeachers() {
		
		try (Statement stmnt = conn.createStatement();
			 ResultSet results = stmnt.executeQuery("SELECT * FROM " + TABLE_TEACHERS);	
			) {
			
			List<Teacher> teachers = new ArrayList<>();
			
			while(results.next()) {
				Teacher teacher = new Teacher();
				teacher.setId(results.getInt(INDEX_TEACHERS_ID));
				teacher.setName(results.getString(INDEX_TEACHERS_NAME));
				teacher.setLastName(results.getString(INDEX_STUDENTS_LASTNAME));
				teacher.setDataOfBirth(results.getString(INDEX_TEACHERS_DATEOFBIRTH));
				teacher.setTaxCode(results.getString(INDEX_TEACHERS_TAXCODE));
				teacher.setCompanyName(results.getString(INDEX_TEACHERS_COMPANYNAME));
				teacher.setMonthlySalary(results.getDouble(INDEX_TEACHERS_MONTHLYSALARY));
				teachers.add(teacher);
			}
			
			return teachers;
			
		} catch (SQLException e) {
			System.out.println("QUERY FAILED: " + e.getMessage());
			return null;
		}
		
	} // END QUERY TEACHERS
	
	public List<String> findTeachersForAStudent(int studentId) {
		
		try (Statement stmnt = conn.createStatement();
			 ResultSet results = stmnt.executeQuery(
//					 "select students.name from students inner join classes on students.id = classes.studentID where students.id = 11;")
					   " select students.name, teachers.name"
					 + " from students inner join classes on students.id = classes.studentID"
					 + " inner join teachers on classes.teacherID = teachers.id"
					 + " where students.id = " + studentId + ";");
					 ) {
			
			List<String> all = new ArrayList<>();
			
			while(results.next()) {
				all.add("\n"); // metto in colonna i valori.. dopo aver messo il print ovviamente.. senza ln
				all.add(results.getString(1));
				all.add(results.getString(2));
			}
			
			return all;
			
		} catch (SQLException e) {
			System.out.println("QUERY FAILED: " + e.getMessage());
			return null;
		}
		
	}
	
} // END DS CLASS
