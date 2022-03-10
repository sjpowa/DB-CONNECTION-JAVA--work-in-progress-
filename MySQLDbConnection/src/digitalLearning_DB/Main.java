package digitalLearning_DB;

import java.util.List;

public class Main {
	
	public static void main(String[] args) {
		
		DataSource datasource = new DataSource();
		
		if (!datasource.open()) {
			System.out.println("CAN'T OPEN DATASOURCE");
			return; // to get e.getMessage() from open() method..
		}
		
		List<Student> students = datasource.queryStudents(DataSource.ORDER_BY_ASC);
		if (students == null) {
			System.out.println("NO STUDENT!");
			return; // this return is used to return the detailed query/table e.message() from queryStudents();
		}
		
		for (Student student : students) {
			System.out.println(
				"ID = " 	  + student.getId()
			+ ", NAME = " 	  + student.getName()
			+ ", LASTNAME = " + student.getLastName()
			+ ", SEX = " 	  + student.getSex()
			+ ", DOB = " 	  + student.getDateOfBirth()
			+ ", TAX CODE = " + student.getTaxCode()
			);
		}
		
		System.out.println(
				"\n=================================================================================================\n"
				);
		
		List<Class> classes = datasource.queryClasses();
		if (classes == null) {
			System.out.println("NO CLASS");
			return;
		}
		
		for (Class clazz : classes) {
			System.out.println(
					"ID = " 			+ clazz.getId()
				+ ", NAME = " 			+ clazz.getName()
				+ ", SCHOOL SUBJECT = " + clazz.getSchoolSubject()
				+ ", STUDENT ID = " 	+ clazz.getStudentID()
				+ ", TEACHER ID = " 	+ clazz.getTeacherID()
					);
		}
		
		System.out.println(
				"\n================================================="
			  + "================================================\n"
				);
		
		List<Teacher> teachers = datasource.queryTeachers();
		if (teachers == null) {
			System.out.println("NO TEACHER");
			return;
		}
		
		for (Teacher teacher : teachers) {
			System.out.println(
					"ID = " 			+ teacher.getId()
				+ ", NAME = " 			+ teacher.getName() 
				+ ", LASTNAME = " 		+ teacher.getLastName() 
				+ ", DOB = " 			+ teacher.getDataOfBirth() 
				+ ", TAX CODE = " 		+ teacher.getTaxCode() 
				+ ", COMPANY NAME = " 	+ teacher.getCompanyName() 
				+ ", MONTHLY SALARY = " + teacher.getMonthlySalary() 
					);
		}
		
		System.out.println(
				"\n================================================================================================="
				);
		List<String> studentTeachers = datasource.studentHowManyTeachersHas("lind");
			if (studentTeachers == null) {
				System.out.println("NO STUDENT");
				return;
			}
		
		System.out.print(
						 "=================\n"
					   + "STUDENT | TEACHER"
					   + "\n================="
					   );
		
		for (String values : studentTeachers) {
			System.out.print(values + "   ");
		}
		System.out.println("\n=================\n");
		
		System.out.println(
				"=================================================================================================\n"
				);
		
		List<StudentClassTeacher> stdcltcs = datasource.studentSchoolSubjectCompanyName("lind");
		if (stdcltcs == null) {
			System.out.println("NO ELEMENTS FOUND!");
			return;
		}
		
		for (StudentClassTeacher stdcltc : stdcltcs) {
			System.out.println(
					  "> STUDENT: " + stdcltc.getName()
					+ "\n> SCHOOL SUBJECT: " + stdcltc.getSchoolSubject()
					+ "\n> COMPANY NAME: " + stdcltc.getCompanyName()
					);
		}
		
		System.out.println("\nOPERATION COMPLETED!");
		datasource.close();
	}

}

