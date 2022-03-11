package digitalLearning_DB_CRUD;

public class Main {

	public static void main(String[] args){
		
		Student student = new Student();
		
		student.search(15);
//		student.insert();
//		student.update();
//		student.delete(14);
		
		Classe classe = new Classe();
		
		classe.search(12);
//		classe.insert();
//		classe.update();
//		classe.delete(35);
		
		Teacher teacher = new Teacher();
		
		teacher.search(3);
//		teacher.insert();
//		teacher.update();
//		teacher.delete(2);
		
	}
}
