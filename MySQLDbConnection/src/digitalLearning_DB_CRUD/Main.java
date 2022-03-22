package digitalLearning_DB_CRUD;

import java.util.List;

public class Main {

	public static void main(String[] args){
		
		Student student = new Student();
		
//		student.search(15);
//		student.insert();
//		student.update();
//		student.delete(38);
//		student.readAll();

		// METODO FUORI MANO SENZA INTERFACE
		
		List<Student> students = student.search02();
		if(students == null) {
			System.out.println("No student found.");
			return;
		}
		
		for (Student studente : students) {
			System.out.println(
				"Student selected is: " +
				"\nId: " 			+ 	student.getId() 			+
				"\nName: " 	 		+	studente.getName()			+
				"\nLastname: " 		+   studente.getLastName()		+
				"\nSex: " 	 		+	studente.getSex()			+
				"\nDate of birth: " +	studente.getDateOfBirth()	+
				"\nTax Code: " 		+	studente.getTaxCode()
				);
		}
		
		Datasource.close();
		
		// ============================================================ //
		
//		Classe classe = new Classe();
//		
//		classe.search(3);
//		classe.insert();
//		classe.update();
//		classe.delete(39);
//		classe.readAll();
		
//		Teacher teacher = new Teacher();
//		
//		teacher.search(3);
//		teacher.insert();
//		teacher.update();
//		teacher.delete(5);
//		teacher.readAll();
		
	}
}
