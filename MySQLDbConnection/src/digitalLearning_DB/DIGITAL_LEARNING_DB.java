package digitalLearning_DB;

public class DIGITAL_LEARNING_DB {

	/* I HAVE USED MySQL DBMS
	
	create database DigitalLearning;

	use DigitalLearning;

	drop database DigitalLearning;

	# =========================================================================================== #
	# ============= DB -> WITH -> CLASSI -> STUDENTI -> INSEGNANTI =========== #
	-- 1 to N relation TEACHERS and CLASSES ---> ONE TEACHER CAN TEACH IN MULTIPLE CLASSES
	-- 1 to N relation CLASS and STUDENTS ---> ONE CLASS CAN HAVE MORE THAN ONE STUDENT
	-- N to N relation STUDENTS and TEACHERS ---> STUDENTS CAN HAVE MORE THAN ONE TEACHER && TEACHERS CAN HAVE MORE THAN ONE STUDENT.. (OBVIOUSLY)..
	-- I HAVE CREATED THE CLASSES TABLE TO LINK WITH A RELATION n - n => STUDENTS AND TEACHERS

	# =========================================================================================== #
	# STUDENTS TABLE
	create table students
	(
	id int primary key auto_increment,
	name varchar(50) not null,
	lastname varchar(50) not null,
	sex char(1),
	dateOfBirth date not null,
	taxCode varchar(16) unique not null
	);

	drop table students;

	# =========================================================================================== #
	# TEACHERS TABLE
	create table teachers
	(
	id int primary key auto_increment,
	name varchar(50) not null,
	lastname varchar(50) not null,
	dateOfBirth date not null,
	taxCode varchar(16) unique not null,
	companyName varchar(50),
	monthlySalary double
	);

	drop table teachers;
	alter table teachers drop column age;
	alter table teachers add column age int, add column idk boolean;

	# =========================================================================================== #
	# CLASSES TABLE
	create table classes
	(
	id int primary key auto_increment,
	name varchar(50) not null,
	schoolSubject varchar(100),
	studentID int,
	teacherID int,
	foreign key (studentID) references students(id),
	foreign key (teacherID) references teachers(id)
	);

	drop table classes;
	drop table classes restrict; -- restrict controlla se la tabella e' collegata ad un'altra

	# =========================================================================================== #
	# STUDENT INSERT

	insert into students (name, lastname, sex, dateOfBirth, taxCode) values
	('Mendel', 'Gaitskill', 'M', '1996-3-31', 'POFOOZ78O22E637B'),
	('Bellanca', 'Cona', 'F', '2000-1-22', 'BYAOYT55V28J829S'),
	('Brittaney', 'Negus', 'F', '1996-12-2', 'VAEPEJ33F77E538J'),
	('Johnny', 'Kiera', 'M', '1989-11-5', 'ZRJVQK59S45V451M'),
	('Booth', 'Harvard', 'M', '1995-1-29', 'NRQAPF47C14Q582L'),
	('Guglielma', 'Whittingham', 'F', '1994-10-13', 'LHYOXD69L28W576V'),
	('Robenia', 'Couser', 'F', '1992-3-4', 'DHJZJP62Q13X698L'),
	('Francisco', 'Brougham', 'M', '1997-4-30', 'BEBTPJ14Z97A641F'),
	('Scottie', 'Diamond', 'M', '1985-2-19', 'TIPZYE12N97B821E'),
	('Wang', 'Giacomasso', 'M', '1999-6-20', 'NKQTHV24Q86M115P'),
	('Lind', 'Chatelot', 'M', '1992-9-18', 'CJQWYI28S51U139S'),
	('Nalani', 'Laweles', 'F', '1996-1-27', 'LNYUDI88R76D251Y'),
	('Ashlan', 'Houndsom', 'F', '1996-5-31', 'ODJTSO98X68D635K'),
	('Jeremias', 'Sousa', 'M', '1991-2-19', 'JHFFZT42R79K453R'),
	('Johann', 'Bemrose', 'M', '1991-12-18', 'CKZVIV33G13X864L'),
	('Ruperta', 'Thorold', 'F', '1986-3-18', 'ZURGKW43C38W454Y'),
	('Flo', 'Hagley', 'F', '1999-5-19', 'BMTJXK55X26E364O'),
	('Lorrie', 'Mattusevich', 'M', '2001-11-7', 'GYLOLN78S78W433U'),
	('Natale', 'Vahl', 'M', '1994-10-18', 'GXMAKN73N77S347B'),
	('Angel', 'Dana', 'M', '1996-3-16', 'HAWBTC45N91C332R'),
	('Monique', 'Franchioni', 'F', '2000-11-11', 'KECNDQ82X73D462M'),
	('Gallard', 'Fost', 'M', '1998-4-26', 'WUPVVJ53W15P482D'),
	('Marie', 'Duling', 'F', '1996-8-9', 'HGAPPK24C97O365F'),
	('Dalston', 'Perree', 'M', '1998-12-14', 'QTTLAJ55B89T894B'),
	('Caitrin', 'Sadry', 'F', '1998-1-13', 'HRNKHJ45N42Q289A'),
	('Antoine', 'Drogan', 'M', '1992-1-20', 'OOJANT36K97Z341X'),
	('Deedee', 'Grannell', 'F', '1989-11-30', 'OUZMBU22S66A429H'),
	('Sheelagh', 'Cescotti', 'F', '1994-3-13', 'DUNDOA23H55D964D'),
	('Maritsa', 'Readings', 'F', '1996-9-30', 'WGTHTQ32I89J571K'),
	('Theda', 'Gummie', 'F', '1996-9-7', 'TWVFHG34I51P544F'),
	('Morly', 'Karus', 'M', '1986-1-24', 'RFNAZW98H77L245O'),
	('Bree', 'Doorey', 'F', '1991-7-27', 'JCGCEK31B55V585I'),
	('Darrick', 'Peto', 'M', '1998-5-12', 'XEYGET25C41A528S'),
	('Collete', 'McAlindon', 'F', '1993-10-12', 'ENBHTN28V51O773A'),
	('Jourdain', 'Wellan', 'M', '1998-8-11', 'VFLDOC67F95T617G');

	# =========================================================================================== #
	# TEACHERS INSERT

	insert into teachers (name, lastname, dateOfBirth, taxCode, companyName, monthlySalary) values
	('Carl', 'Carlson', '1995-3-27', 'ETKUMJ79M22S765X', 'zROTOM', 2472.64),
	('Yaro', 'Tonillo', '2000-4-11', 'SPWXEY24M57Y164N', 'GENERAzION', 1289.30),
	('Alize', 'PensaBen', '1998-1-29', 'PRSIJM12G91M156W', 'GENERAzION', 2139.03),
	('Gian', 'Fango', '1994-2-24', 'OFUFPM84R54B969R', 'GENERAzION', 1959.37);

	# =========================================================================================== #
	# CLASSES INSERT

	insert into classes (name, schoolSubject, studentID, teacherID) values
	('zROTOM x ACTURE', 'JAVA, SQL',  1, 1),
	('zROTOM x ACTURE', 'JAVA, SQL',  2, 1),
	('zROTOM x ACTURE', 'JAVA, SQL',  3, 1),
	('zROTOM x ACTURE', 'JAVA, SQL',  4, 1),
	('zROTOM x ACTURE', 'JAVA, SQL',  5, 1),
	('zROTOM x ACTURE', 'JAVA, SQL',  6, 1),
	('zROTOM x ACTURE', 'JAVA, SQL',  7, 1),
	('zROTOM x ACTURE', 'JAVA, SQL',  8, 1),
	('zROTOM x ACTURE', 'JAVA, SQL',  9, 1),
	('zROTOM x ACTURE', 'JAVA, SQL', 10, 1),
	('GENEARAzION', 'C#, SQL, EF', 11 , 2),
	('GENEARAzION', 'C#, SQL, EF', 11 , 3),
	('GENEARAzION', 'C#, SQL, EF', 11 , 4),
	('GENEARAzION', 'C#, SQL, EF', 12 , 2),
	('GENEARAzION', 'C#, SQL, EF', 13 , 2),
	('GENEARAzION', 'C#, SQL, EF', 14 , 2),
	('GENEARAzION', 'C#, SQL, EF', 15 , 2),
	('GENEARAzION', 'C#, SQL, EF', 16 , 2),
	('GENEARAzION', 'C#, SQL, EF', 16 , 3),
	('GENEARAzION', 'C#, SQL, EF', 17 , 2),
	('GENEARAzION', 'C#, SQL, EF', 18 , 2),
	('GENEARAzION', 'C#, SQL, EF', 19 , 2),
	('GENEARAzION', 'C#, SQL, EF', 20 , 2),
	('GENEARAzION', 'C#, SQL, EF', 21 , 2),
	('GENEARAzION', 'C#, SQL, EF', 22 , 2),
	('GENEARAzION', 'C#, SQL, EF', 23 , 2),
	('GENEARAzION', 'C#, SQL, EF', 23 , 4),
	('GENEARAzION', 'C#, SQL, EF', 24 , 2),
	('GENEARAzION', 'C#, SQL, EF', 25 , 2),
	('GENEARAzION', 'C#, SQL, EF', 26 , 2),
	('GENEARAzION', 'C#, SQL, EF', 27 , 2),
	('GENEARAzION', 'C#, SQL, EF', 28 , 2),
	('GENEARAzION', 'C#, SQL, EF', 29 , 2),
	('GENEARAzION', 'C#, SQL, EF', 30 , 2),
	('GENEARAzION', 'C#, SQL, EF', 31 , 2),
	('GENEARAzION', 'C#, SQL, EF', 32 , 2),
	('GENEARAzION', 'C#, SQL, EF', 33 , 2),
	('GENEARAzION', 'C#, SQL, EF', 34 , 2),
	('GENEARAzION', 'C#, SQL, EF', 35 , 2);

	# =========================================================================================== #
	# ====================================== QUERY SECTION ====================================== #

	select * from students;
	select * from teachers;
	select * from classes;

	# =================================== INNER JOIN ============================================= #
	# PRINT ALL DATA OF EVERY TABLE

	-- i want to read everything of everything joinJoinJoinJoin...
	select *
		from students inner join classes on students.id = classes.studentID
			inner join teachers on teachers.id = classes.teacherID;
	        
	# ======================================= HAVING ============================================= #

	-- SEE ALL THE STUDENTS THAT HAVE MORE THAN ONE TEACHER!
	select students.name, count(classes.studentID) as TeachersCounter
	from students inner join classes on students.id = classes.studentID
	group by students.name
	having TeachersCounter > 1;

	# ======================= MI SEMBRA GIUSTO FARE ESERCIZI CON LE VIEW ====================== #
	# ======================================== VIEW =========================================== #
	# PRINT THE STUDENT WITH MOST TEACHERS! #

	-- i have to create a view where i count how many teachers has a student
	create view counterTeacherPerStudent as    
	select students.name as StudentName, count(classes.teacherID) as TeacherCounter
	from classes inner join students on classes.studentID = students.id
	group by students.name;

	# CHECKING WHAT THE VIEW CALLS..
	select students.name as StudentName, count(classes.teacherID) as TeacherCounter
	from classes inner join students on classes.studentID = students.id
	group by students.name
	order by TeacherCounter desc;

	-- FINALLY 'PRINT' THE STUDENT WITH MOST TEACHER
	select *
	from counterTeacherPerStudent
	where TeacherCounter = (select max(TeacherCounter) from counterTeacherPerStudent);

	# ========================== DATE == TEACHERS AGE (NO DATE) ================================= #
	-- i want to see the age of every teacher so convert from date to year
	select name as TeacherName, (year(curdate()) - year(dateofbirth)) as Age
	from teachers
	order by Age asc;

	# ================================ DATE(age) IN SQL +++ VIEWS =============================== #
	-- i want to see who is the older teacher, only age not date

	-- WE HAVE TO CREATE A VIEW, WE CAN'T RETURN MIN() OR MAX() OF A DATE AS AN INT => ONLY THE YEAR cuz THE DB WANTS A DATE TO RETURN
	create view TeachersAge as
	select name as TeacherName, (year(curdate()) - year(dateofbirth)) as Age
	from teachers
	order by Age asc;

	-- DOWN HERE WE SEE WHAT THE VIEW WILL CALL
	select name as TeacherName, (year(curdate()) - year(dateofbirth)) as Age
	from teachers
	order by Age asc;

	-- QUERY TO SEE WHO IS THE OLDER TEACHER
	select *
	from TeachersAge
	where Age = (select max(Age) from TeachersAge );

	-- QUERY TO SEE WHO IS THE YONGEST TEACHER
	select *
	from TeachersAge
	where Age = (select min(Age) from TeachersAge );

	drop view TeachersAge;

	# ===================================== DISTINCT ============================================ #
	-- check if there are students with age = 26 and print it ONCE!

	select 2022 - 26; -- = 1996

	select count(id) as count26YearStudent
	from students
	where year(dateofbirth) = 1996;

	-- so we have eight students with 26 years.. we want only know if in the school there is at least one student with 26 year
	-- DISTINCT
	select distinct (year(curdate()) - year(dateofbirth)) as Age
	from students
	where year(dateofbirth) = 1996;

	# ======================================== DELETE =========================================== #
	-- 'Dalston' 'Perree', a student, has left the school => delete him => we dont store data so erase him
	select *
	from students
	where name = 'Dalston' and lastname = 'Perree'; -- ok we found out that his ID = 24

	# dato che la tabella classi ha un reference collegata alla tabella studenti
	# devo prima cancellare TUTTI I DATI dell'id dello studente in CLASSES
	# e poi posso cancellarlo definitavamente in STUDENTS
	select *
	from classes
	where studentID = 24; -- the studentID = 24 is linked to classes.id = 28

	# delete of the student from classes
	delete
	from classes
	where id = 28;

	# now i can definitely delete the student from the STUDENTS TABLE!
	delete
	from students
	where id = 24;

	select *
	from students
	where name = 'Dalston' and lastname = 'Perree'; -- user not found

	# ========================= COUNT +++ GROUP BY +++ ORDER BY +++ DESC ======================== #
	-- count how many students are in a class (any class u want)
	select count(students.id) as Students_Counter, classes.name as ClassName
	from students inner join classes on students.id = classes.studentID
	group by classes.name
	order by Students_Counter desc;

	# ======================================== UPDATE =========================================== #
	-- Lind [from students] should be (but IDK) a Female name like Linda, change her sex from Male to Female
	select *
	from students
	where name = 'Lind';

	update students
	set sex = 'F'
	where id = 11;

	# ==================================== UPDATE YYYY DATE ====================================== #
	-- change the age of 'Wang' 'Giacomasso' to 21 years, only wrong yyyy so change only it
	select *
	from students
	where name = 'Wang' and lastname = 'Giacomasso';

	select (year(curdate()) - year(dateofbirth)) as Age
	from students
	where id = 10;

	select 2022 - 21; -- set year to 2001

	update students
	set dateofbirth = '2001-05-15',
	sex = 'M'
	where id = 10;

	# ===================================== INSERT ============================================== #
	-- insert in the class another student
	insert into students (name, lastname, sex, dateofbirth, taxcode) values('Mario', 'Bros', 'M', '1981-05-22', 'OLEMAK52D56Z762X');

	select *
	from students
	where name = 'Mario' and lastname = 'Bros';

	# ======================================== UPDATE =========================================== #
	-- modify the name of a 'Brittaney' 'Negus' beacuse her true name is 'Britney'
	select *
	from students
	where name = 'Brittaney' and lastname = 'Negus';

	# UPDATE
	update students
	set name = 'Britney'
	where id = 3;

	select *
	from students
	where id = 3;

	# ================================== ALTER == TABLE ========================================= #
	-- change in STUDENTS the NAME COLUMN in NOME, LASTNAME -> COGNOME
	alter table students
	change column name
	nome varchar(100);

	# =============================== AVOID THIS SH*T DOWN HERE ================================= #
	-- ..u can also do this too.. but avoid this sh*t or u will call data that u dont want => the first data of every column..
	select students.name, teachers.lastname, classes.name
	from students, teachers, classes;

	# ================================== ALTER == TABLE ========================================= #

	alter table students
	change column lastname
	cognome varchar(100);

	select *
	from students;

	# ====================================== BETWEEN ============================================= #

	-- i want to see all the students with age between 29 and 32
	select 2022 - 29; -- 1993
	select 2022 - 32; -- 1990

	select *
	from students
	where year(dateofbirth)
	between 1990
		and 1993;
	    
	# ==================================== OR OPERATOR =========================================== #

	-- print the name of all students that have 28 and 22 years
	select 2022 - 28; -- 1994
	select 2022 - 22; -- 2000

	select *
	from students
	where year(dateofbirth) = 1994 
	or year(dateofbirth) = 2000; -- REMEMBER, A STUDENT CANT HAVE TWO AGES.. -> in caso di pericolo ALT+F4

	select *
	from students
	where year(dateofbirth) = 1994; -- so we dont really have students born in 1994

	# ================================= VIEW = DATE = AGE ======================================= #
	    
	-- FIND THE OLDER STUDENT/STUDENTS
	create view studentsAge as
	select name, (year(curdate()) - year(dateofbirth)) as age
	from students
	order by age desc;

	select name, (year(curdate()) - year(dateofbirth)) as age
	from students
	order by age desc;

	select *
	from studentsAge
	where age = (select max(age) from studentsAge );

	-- i want who is the youngest student/students
	select *
	from studentsAge
	where age = (select min(age) from studentsAge );

	-- i want to see the avg age of the class
	select avg(year(curdate()) - year(dateofbirth)) as avg_Age_Students
	from students;

	# ========================================================================================== #

	-- select all the data of a student
	select *
	from students
	where id = 16;

	-- i want to see the lastName of all students
	select lastName as Students_LastName
	from students;

	# ===================================== CONCAT ============================================= #

	-- I WANT TO SEE IN THE SAME TABLE NAME AND LASTNAME
	select concat(name, ' | ', lastname) as Name_LastName
	from students;

	select concat(lastname, ' | ', companyName) as lastName_Company
	from teachers;

	# ========================================================================================== #
	-- check how many money a teacher earn in one year
	select *, (monthlysalary * 12) as annualSalary
	from teachers;

	-- who is the richest teacher
	select *
	from teachers
	where monthlysalary = (select max(monthlysalary) from teachers );

	# ===================================== LIKE OPERATOR ====================================== #

	select *
	from students
	where name like '%o'; -- cerca tutti gli studenti hanno il nome che finisce con 'O'

	select *
	from students
	where name like'f%'; -- cerca tutti gli studenti che hanno il nome che inizia con F

	select *
	from students
	where name like 'f_a%'; -- cerca gli studenti con lettera iniziale F e terza lettera A

	select *
	from students
	where name like '__e%'; -- studenti con nome dove la E si trova in terza posizione da sinistra

	select *
	from students
	where name like '%a__'; -- studenti con nome dove la A si trova in terza posizione dalla fine

	# ====================================== HAVING ============================================ #
	-- I WANT TO SEE ALL THE TEACHERS THAT HAVE MORE THAN 2000$
	select *
	from teachers
	having monthlySalary > 2000; -- having di solito si usa solo dopo il group by

	# ===================================== BETWEEN ============================================ #
	-- I WANT TO SEE ALL THE STUDENTS THAT HAVE A SALARY BETWEEN 1K AND 2K
	select *
	from teachers
	where monthlySalary between 1000 and 2000;

	*/
}
