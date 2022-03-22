package school_DB_CRUD_Datasource;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Datasource.open(); // open the connection
		
		Scanner sc = new Scanner(System.in);
		
		Student std = new Student();
		Teacher tchr = new Teacher();
		Clazz clss = new Clazz();
		
		while(true) {
		
			System.out.print(
					  "[1] STUDENTS TABLE"
					+ "\n[2] TEACHERS TABLE"
					+ "\n[3] CLASSES TABLE"
					+ "\n[0] TO EXIT"
					+ "\nSelect which table you want to use: "
					);
			
			int table_name = sc.nextInt();
			
			while(true) {
	// ============================= STUDENT ============================ //
				if(table_name == 1) {
					System.out.print("\n-> Students table selected."
							+ "\n[1] INSERT A STUDENT"
							+ "\n[2] DELETE A STUDENT"
							+ "\n[3] MODIFY A STUDENT"
							+ "\n[4] SEARCH A STUDENT"
							+ "\n[5] READ ALL STUDENTS"
							+ "\nEnter the number here ---> ");
					while(true) {
						int std_num = sc.nextInt();
						if(std_num == 1) {
							std.insert(); // student insert
							break;
						}
						else if(std_num == 2) {
							std.delete(); // delete a student
							break;
						}
						else if(std_num == 3) {
							std.update(); // modify a student
							break;
						}
						else if(std_num == 4) {
							std.search(); // student query per id
							break;
						}
						else if(std_num == 5) {
							std.readAll(); // students query
							break;
						}
						else
							System.out.print("std_Wrong number..\nType again: ");
					}
				}
	// ============================== TEACHERS =================================
				else if(table_name == 2) {
						System.out.print("\n-> Teachers table selected."
								+ "\n[1] INSERT A TEACHER"
								+ "\n[2] DELETE A TEACHER"
								+ "\n[3] MODIFY A TEACHER"
								+ "\n[4] SEARCH A TEACHER"
								+ "\n[5] READ ALL TEACHERS"
								+ "\nEnter the number here ---> ");
						while(true) {
							int tchr_num = sc.nextInt();
							if(tchr_num == 1) {
								tchr.insert(); // teacher insert
								break;
							}
							else if(tchr_num == 2) {
								tchr.delete(); // delete a teacher
								break;
							}
							else if(tchr_num == 3) {
								tchr.update(); // modify a teacher
								break;
							}
							else if(tchr_num == 4) {
								tchr.search(); // teacher query per id
								break;
							}
							else if(tchr_num == 5) {
								tchr.readAll(); // teachers query
								break;
							}
							else
								System.out.print("tchr\nWrong number\nType again: ");
						}
				}
	// ============================== CLASSES =================================
				else if(table_name == 3) {
					System.out.print("\n-> Classes table selected."
							+ "\n[1] INSERT A CLASS"
							+ "\n[2] DELETE A CLASS"
							+ "\n[3] MODIFY A CLASS"
							+ "\n[4] SEARCH A CLASS"
							+ "\n[5] READ ALL CLASSES"
							+ "\nEnter the number here ---> ");
					while(true) {
						int clss_num = sc.nextInt();
						if(clss_num == 1) {
							clss.insert(); // teacher insert
							break;
						}
						else if(clss_num == 2) {
							clss.delete(); // delete a teacher
							break;
						}
						else if(clss_num == 3) {
							clss.update(); // modify a teacher
							break;
						}
						else if(clss_num == 4) {
							clss.search(); // teacher query per id
							break;
						}
						else if(clss_num == 5) {
							clss.readAll(); // teachers query
							break;
						}
						else
							System.out.print("clss\nWrong number..\nType again: ");
					}
				}
				else if(table_name == 0) {
					System.out.println("\nProgram closed.");
					System.exit(0);
					break;
				}
				else {
					System.out.print("\ntables..\nWrong number entered..\nType again: ");
					table_name = sc.nextInt();
				}
				break; // questo break mi serve per uscire dopo l'esecuzione di uno dei metopdi crud
			}
		
			boolean x = false;
			
			while(true) {
				System.out.print("\n\n-> Do you want to do another operation? [Y/N] -> ");
				String answer = sc.next();
				if(answer.toLowerCase().equals("n")) {
					x = true;
					break;
				}
				else if(answer.toLowerCase().equals("y")) {
					x = false;
					break;
				}
			}
			
			if(x == true)
				break;
			else if(x == false)
				continue;
		}
		
		sc.close(); // close the scanner
		Datasource.close(); // close the connection
		
	}
	
}
