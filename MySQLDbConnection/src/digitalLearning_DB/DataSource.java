package digitalLearning_DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataSource {

	// qui ho voluto cambiare perche' prima non mi piaceva come veniva il collegamento
	// lascio cmq l'alternativa
	public static final String DB_NAME = "DigitalLearning";
	public static final String DB_USER = "root";
	public static final String DB_PASSWORD = "root";
	public static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/";
//	public static final String DB_USER = "user=root";
//	public static final String DB_PASSWORD = "password=root";
//	public static final String CONNECTION_STRING =
//						"jdbc:mysql://localhost:3306/" + DB_NAME + "?" + DB_USER + "&" + DB_PASSWORD;

	// COLUMN INDEX => FASTER SEARCH... NAME MATCHING IS SLOWER THAN INDEX
	// THE COMPILER, WITH INDEX SEARCH, JUST KNOWS WHERE TO GO!!!
	// Quando crei una tabella, tutte i nomi delle colonne che vai ad inserire
	// sono indicizzate a partire dal nr.1..
	// infatti se fai girare il programma con l'index sballato di una delle tabelle
	// cioe' ci metti uno 0 oppure un valore > || < del column.length/ nr delle colonne (capisc a te)
	// sara' la console a dirti: guadda ca si out of indice!!!
	
	public static final String TABLE_STUDENTS = "students";
	public static final String COLUMN_STUDENTS_ID = "id";
	public static final String COLUMN_STUDENTS_NAME = "name";
	public static final String COLUMN_STUDENTS_LASTNAME = "lastname";
	public static final String COLUMN_STUDENTS_SEX = "sex";
	public static final String COLUMN_STUDENTS_DATEOFBIRTH = "dateOfBirth";
	public static final String COLUMN_STUDENTS_TAXCODE = "taxCode";
	public static final int INDEX_STUDENTS_ID 		   = 1;
	public static final int INDEX_STUDENTS_NAME 	   = 2;
	public static final int INDEX_STUDENTS_LASTNAME    = 3;
	public static final int INDEX_STUDENTS_SEX 		   = 4;
	public static final int INDEX_STUDENTS_DATEOFBIRTH = 5;
	public static final int INDEX_STUDENTS_TAXCODE 	   = 6;

	public static final String TABLE_CLASSES = "classes";
	public static final String COLUMN_CLASSES_ID = "id";
	public static final String COLUMN_CLASSES_NAME = "name";
	public static final String COLUMN_CLASSES_SCHOOLSUBJECT = "schoolSubject";
	public static final String COLUMN_CLASSES_STUDENTID = "studentId";
	public static final String COLUMN_CLASSES_TEACHERID = "teacherId";
	public static final int INDEX_CLASSES_ID 			= 1;
	public static final int INDEX_CLASSES_NAME 			= 2;
	public static final int INDEX_CLASSES_SCHOOLSUBJECT = 3;
	public static final int INDEX_CLASSES_STUDENTID 	= 4;
	public static final int INDEX_CLASSES_TEACHERID 	= 5;

	public static final String TABLE_TEACHERS = "teachers";
	public static final String COLUMN_TEACHERS_ID = "id";
	public static final String COLUMN_TEACHERS_NAME = "name";
	public static final String COLUMN_TEACHERS_LASTNAME = "lastName";
	public static final String COLUMN_TEACHERS_DATEOFBIRTH = "dateOfBirth";
	public static final String COLUMN_TEACHERS_TAXCODE = "taxCode";
	public static final String COLUMN_TEACHERS_COMPANYNAME = "companyName";
	public static final String COLUMN_TEACHERS_MONTHLYSALARY = "monthlySalary";
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
	
	public static final String QUERY_STUDENT_HOW_MANY_TEACHERS_HAS =
			"SELECT " + TABLE_STUDENTS + "." + COLUMN_STUDENTS_NAME + ", COUNT("
			+ TABLE_TEACHERS + "." + COLUMN_TEACHERS_ID + ")"
			+ " FROM " + TABLE_STUDENTS + " INNER JOIN " + TABLE_CLASSES + " ON "
			+ TABLE_STUDENTS + "." + COLUMN_STUDENTS_ID + " = "
			+ TABLE_CLASSES + "." + COLUMN_CLASSES_STUDENTID + " INNER JOIN "
			+ TABLE_TEACHERS + " ON " + TABLE_CLASSES + "." + COLUMN_CLASSES_TEACHERID
			+ " = " + TABLE_TEACHERS + "." + COLUMN_TEACHERS_ID
			+ " WHERE " + TABLE_STUDENTS + "." + COLUMN_STUDENTS_NAME + " = '";
	
//	SELECT STUDENTS.NAME, CLASSES.SCHOOLSUBJECT, TEACHERS.companyName
//	FROM STUDENTS INNER JOIN CLASSES ON STUDENTS.ID = CLASSES.STUDENTID
//	INNER JOIN TEACHERS ON CLASSES.TEACHERID = TEACHERS.ID;

	
	public static final String QUERY_STUDENT_SCHOOLSUBJECT_COMPANYNAME =
			"SELECT DISTINCT " + TABLE_STUDENTS + "." + COLUMN_STUDENTS_NAME + ", "
			+ TABLE_CLASSES + "." + COLUMN_CLASSES_SCHOOLSUBJECT + ", "
			+ TABLE_TEACHERS + "." + COLUMN_TEACHERS_COMPANYNAME
			+ " FROM " + TABLE_STUDENTS + " INNER JOIN " + TABLE_CLASSES + " ON "
			+ TABLE_STUDENTS + "." + COLUMN_STUDENTS_ID + " = "
			+ TABLE_CLASSES + "." + COLUMN_CLASSES_STUDENTID
			+ " INNER JOIN " + TABLE_TEACHERS + " ON "
			+ TABLE_CLASSES + "." + COLUMN_CLASSES_TEACHERID + " = "
			+ TABLE_TEACHERS + "." + COLUMN_TEACHERS_ID
			+ " WHERE " + TABLE_STUDENTS + "." + COLUMN_STUDENTS_NAME + " = '";
	
	
	//	we need to do is add an open method to
//	open the database and a close method to
//	close it so public boolean open....
	private Connection conn;

	public boolean open() { // we use this method so than in Main we can open the connection
		try {
			conn = DriverManager.getConnection(CONNECTION_STRING + DB_NAME, DB_USER, DB_PASSWORD);
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
	
	public List<String> studentHowManyTeachersHas(String studentName) {
		
		try (Statement stmnt = conn.createStatement();
			 ResultSet results = stmnt.executeQuery(
					 QUERY_STUDENT_HOW_MANY_TEACHERS_HAS + studentName + "';");
					 ) {
			
			List<String> all = new ArrayList<>();
			
			while(results.next()) {
				all.add("\n"); // metto in colonna i valori.. dopo aver messo il print ovviamente.. senza ln
				all.add(results.getString(1)); // prendo valori colonna 1 => students.name
				all.add(results.getString(2)); // prondo valori colonna 2 => COUNT(teachers.id)
			}
			
			return all;
			
		} catch (SQLException e) {
			System.out.println("QUERY FAILED: " + e.getMessage());
			return null;
		}
		
	} // END HOW MANY TEACHERS HAVE A STUDENT
	
	public List<StudentClassTeacher> studentSchoolSubjectCompanyName(String studentName) {
		
		try (Statement stmn = conn.createStatement();
			 ResultSet results = stmn.executeQuery(
					 QUERY_STUDENT_SCHOOLSUBJECT_COMPANYNAME + studentName + "';");
				) {
			
			List<StudentClassTeacher> stdcltcs = new ArrayList<>();
			
			while(results.next()) {
				StudentClassTeacher stdcltc = new StudentClassTeacher();
				stdcltc.setName(results.getString(1));	// il set lo gettiamo nella prima colonna
				stdcltc.setSchoolSubject(results.getString(2)); // il set lo gettiamo nella seconda colonna
				stdcltc.setCompanyName(results.getString(3)); // il set lo gettiamo nella terza colonna
				stdcltcs.add(stdcltc);
			}
			
			return stdcltcs;
			
		} catch (SQLException e) {
			System.out.println("QUERY FAILED: " + e.getMessage());
			return null;
		}
		
	}
	
} // END DS CLASS
