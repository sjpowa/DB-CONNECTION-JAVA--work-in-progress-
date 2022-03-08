package dbConnection03;

import java.util.List;

public class Main {
	
	public static void main(String[] args) {
		
		DataSource datasource = new DataSource();
		
		if (!datasource.open()) {
//			System.out.println("CAN'T OPEN DATASOURCE");
			return;
		}
		
		List<Student> students = datasource.queryStudents(DataSource.ORDER_BY_ASC);
		if (students == null) {
			System.out.println("NO STUDENT!");
			return;
		}
		
		for (Student student : students) {
			System.out.println(
				"ID = " + student.getId()
			+ ", Name = " + student.getName()
			+ ", LastName = " + student.getLastName()
			+ ", Sex = " + student.getSex()
			+ ", DOB = " + student.getDateOfBirth()
			+ ", TAX CODE = " + student.getTaxCode()
			);
		}
		
		datasource.close();
	}

}
