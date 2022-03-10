package dbConnection03;

import java.util.List;

public class Main {
	
	public static void main(String[] args) {
		
		DBconn dbconn = new DBconn();
		
		dbconn.insertPerson("Pippo", "Rossi");
		dbconn.insertPerson("Minnie", "Bo");
		dbconn.insertPerson("Kung", "Fu");
		dbconn.insertPerson("Panda", "Lazer");
		dbconn.insertPerson("Lancia", "Delta");
		dbconn.insertPerson("Mucca", "Giotto");
		
		List<Person> people = dbconn.queryPeople();
		if (people == null) {
			System.out.println("NO PEOPLE!");
			return;
		}
		
		System.out.println("=================");
		for (Person person : people) {
			System.out.println(
					"NAME: " + person.getName()
					+ "\nLASTNAME: " + person.getLastName()
					+ "\n================="
					);
		}
		
//		dbconn.dropPersonTable();
		
	} // END MAIN JAVAC

} // END CLASS
