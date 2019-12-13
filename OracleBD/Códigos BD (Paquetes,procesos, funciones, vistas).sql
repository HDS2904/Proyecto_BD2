--------------------------------------------------------------------------------
-- PAQUETES ADMINISTRABLES
--------------------------------------------------------------------------------

------------- CREACION DE PROCEDIMIENTOS MANTENIMIENTO PERSON -----------------
CREATE OR REPLACE PACKAGE PACK_MANAGE_PERSONS AS
  TYPE type_cursor IS REF CURSOR RETURN PERSON%ROWTYPE;
  PROCEDURE INSERT_D (first_n PERSON.FIRST_NAME%TYPE,last_n PERSON.LAST_NAME%TYPE, 
  dni_p PERSON.DNI%TYPE, pho PERSON.PHONE%TYPE, addr PERSON.ADDRESS%TYPE, ema PERSON.EMAIL%TYPE);
  PROCEDURE UPDATE_D (id_p PERSON.ID_PERSON%TYPE, first_n PERSON.FIRST_NAME%TYPE,last_n PERSON.LAST_NAME%TYPE, 
  dni_p PERSON.DNI%TYPE, pho PERSON.PHONE%TYPE, addr PERSON.ADDRESS%TYPE, ema PERSON.EMAIL%TYPE);
  PROCEDURE DELETE_D (id_p PERSON.ID_PERSON%TYPE);
  FUNCTION SEARCH_D (id_p PERSON.ID_PERSON%TYPE) RETURN type_cursor;
  FUNCTION SEARCH_DNI (dni_p PERSON.DNI%TYPE) RETURN type_cursor;
  FUNCTION LIST_D RETURN type_cursor;
END PACK_MANAGE_PERSONS;


CREATE OR REPLACE PACKAGE BODY PACK_MANAGE_PERSONS IS
  PROCEDURE INSERT_D (first_n PERSON.FIRST_NAME%TYPE,last_n PERSON.LAST_NAME%TYPE, 
  dni_p PERSON.DNI%TYPE, pho PERSON.PHONE%TYPE, addr PERSON.ADDRESS%TYPE, ema PERSON.EMAIL%TYPE) 
    IS
    BEGIN
      INSERT INTO PERSON (FIRST_NAME, LAST_NAME, DNI, PHONE, ADDRESS, EMAIL) 
      VALUES (first_n, last_n, dni_p, pho, addr, ema);
      commit;
      EXCEPTION
      WHEN OTHERS THEN
      rollback;
  END INSERT_D;
  PROCEDURE UPDATE_D (id_p PERSON.ID_PERSON%TYPE, first_n PERSON.FIRST_NAME%TYPE,last_n PERSON.LAST_NAME%TYPE, 
  dni_p PERSON.DNI%TYPE, pho PERSON.PHONE%TYPE, addr PERSON.ADDRESS%TYPE, ema PERSON.EMAIL%TYPE)
    IS
    BEGIN
      UPDATE PERSON SET FIRST_NAME = first_n, LAST_NAME = last_n, DNI = dni_p, PHONE = pho,
      ADDRESS = addr, EMAIL = ema
      where ID_PERSON = id_p;
      commit;
      EXCEPTION
      WHEN OTHERS THEN
      rollback;
  END UPDATE_D;
  PROCEDURE DELETE_D (id_p PERSON.ID_PERSON%TYPE)
    IS
    BEGIN
      DELETE FROM PERSON WHERE ID_PERSON = id_p;
      commit;
      EXCEPTION
      WHEN OTHERS THEN 
      rollback;
  END DELETE_D;
  FUNCTION SEARCH_D (id_p PERSON.ID_PERSON%TYPE) RETURN type_cursor
  IS
    cursor1 PACK_MANAGE_PERSONS.type_cursor;
    BEGIN
      OPEN cursor1 FOR
      SELECT * FROM PERSON WHERE ID_PERSON = id_p;
      RETURN cursor1;
  END SEARCH_D;
  FUNCTION SEARCH_DNI (dni_p PERSON.DNI%TYPE) RETURN type_cursor
    IS
    cursor1 PACK_MANAGE_PERSONS.type_cursor;
    BEGIN
      OPEN cursor1 FOR
      SELECT * FROM PERSON WHERE DNI = dni_p;
      RETURN cursor1;
  END SEARCH_DNI;
  FUNCTION LIST_D RETURN type_cursor
    IS
    cursor1 PACK_MANAGE_PERSONS.type_cursor;
    BEGIN
      OPEN cursor1 FOR 
      SELECT * FROM PERSON;
      RETURN cursor1;
  END LIST_D;
END PACK_MANAGE_PERSONS;

------------- CREACION DE PROCEDIMIENTOS MANTENIMIENTO TEACHER -----------------
create or replace PACKAGE PACK_MANAGE_TEACHERS AS
  TYPE type_cursor IS REF CURSOR RETURN TEACHER%ROWTYPE;
  PROCEDURE INSERT_D (id_t TEACHER.ID_PERSON%TYPE, cod_t TEACHER.CODE_TEACHER%TYPE);
  PROCEDURE UPDATE_D (id_t TEACHER.ID_PERSON%TYPE, cod_t TEACHER.CODE_TEACHER%TYPE);
  PROCEDURE DELETE_D (id_t TEACHER.ID_PERSON%TYPE);
  FUNCTION SEARCH_D (id_t TEACHER.ID_PERSON%TYPE) RETURN type_cursor;
  FUNCTION LIST_D RETURN type_cursor;
  FUNCTION SEARCH_SUB (id_t TEACHER.ID_PERSON%TYPE) RETURN type_cursor;
END PACK_MANAGE_TEACHERS;


create or replace PACKAGE BODY PACK_MANAGE_TEACHERS IS
  PROCEDURE INSERT_D (id_t TEACHER.ID_PERSON%TYPE, cod_t TEACHER.CODE_TEACHER%TYPE) 
    IS
    BEGIN
      INSERT INTO TEACHER (ID_PERSON, CODE_TEACHER) VALUES (id_t, cod_t);
      commit;
      EXCEPTION
      WHEN OTHERS THEN
      rollback;
  END INSERT_D;
  PROCEDURE UPDATE_D (id_t TEACHER.ID_PERSON%TYPE, cod_t TEACHER.CODE_TEACHER%TYPE)
    IS
    BEGIN
      UPDATE TEACHER SET CODE_TEACHER = cod_t where ID_PERSON = id_t;
      commit;
      EXCEPTION
      WHEN OTHERS THEN
      rollback;
  END UPDATE_D;
  PROCEDURE DELETE_D (id_t TEACHER.ID_PERSON%TYPE)
    IS
    BEGIN
      DELETE FROM TEACHER WHERE ID_PERSON = id_t;
      commit;
      EXCEPTION
      WHEN OTHERS THEN 
      rollback;
  END DELETE_D;
  FUNCTION SEARCH_D (id_t TEACHER.ID_PERSON%TYPE) RETURN type_cursor
    IS
    cursor1 PACK_MANAGE_TEACHERS.type_cursor;
    BEGIN
      OPEN cursor1 FOR
      SELECT * FROM TEACHER WHERE ID_PERSON = id_t;
      RETURN cursor1;
  END SEARCH_D;
  FUNCTION LIST_D RETURN type_cursor
    IS
    cursor1 PACK_MANAGE_TEACHERS.type_cursor;
    BEGIN
      OPEN cursor1 FOR 
      SELECT * FROM TEACHER;
      RETURN cursor1;
  END LIST_D;
  FUNCTION SEARCH_SUB (id_t TEACHER.ID_PERSON%TYPE) RETURN type_cursor
    IS
    cursor1 PACK_MANAGE_TEACHERS.type_cursor;
    BEGIN
      OPEN cursor1 FOR
      SELECT SU.ID_SUBJECT, SU.NAME_SUBJECT 
      FROM TEACHER TE
      LEFT JOIN SECTION SE ON (SE.ID_PERSON=TE.ID_PERSON)
      LEFT JOIN SUBJECT SU ON (SU.ID_SUBJECT=SE.ID_SECTION)
      WHERE TE.ID_PERSON = id_t;
      RETURN cursor1;
  END SEARCH_SUB;
END PACK_MANAGE_TEACHERS;
  
--------- CREACION DE PROCEDIMIENTOS MANTENIMIENTO STUDENT ---------------------
CREATE OR REPLACE PACKAGE PACK_MANAGE_STUDENTS AS
  TYPE type_cursor IS REF CURSOR RETURN STUDENT%ROWTYPE;
  PROCEDURE INSERT_D (id_s STUDENT.ID_PERSON%TYPE, cod_s STUDENT.CODE_STUDENT%TYPE, id_sc STUDENT.ID_SCHOOL%TYPE);
  PROCEDURE UPDATE_D (id_s STUDENT.ID_PERSON%TYPE, cod_s STUDENT.CODE_STUDENT%TYPE, id_sc STUDENT.ID_SCHOOL%TYPE);
  PROCEDURE DELETE_D (id_s STUDENT.ID_PERSON%TYPE);
  FUNCTION SEARCH_D (id_s STUDENT.ID_PERSON%TYPE) RETURN type_cursor;
  FUNCTION LIST_D RETURN type_cursor;
END PACK_MANAGE_STUDENTS;

CREATE OR REPLACE PACKAGE BODY PACK_MANAGE_STUDENTS IS
  PROCEDURE INSERT_D (id_s STUDENT.ID_PERSON%TYPE, cod_s STUDENT.CODE_STUDENT%TYPE, id_sc STUDENT.ID_SCHOOL%TYPE)
    IS
    BEGIN
      INSERT INTO STUDENT (ID_PERSON, CODE_STUDENT, ID_SCHOOL) VALUES (id_s, cod_s, id_sc);
      commit;
      EXCEPTION
      WHEN OTHERS THEN
      rollback;
  END INSERT_D;
  PROCEDURE UPDATE_D (id_s STUDENT.ID_PERSON%TYPE, cod_s STUDENT.CODE_STUDENT%TYPE, id_sc STUDENT.ID_SCHOOL%TYPE)
    IS
    BEGIN
      UPDATE STUDENT SET CODE_STUDENT = cod_s, ID_SCHOOL = id_sc where ID_PERSON = id_s;
      commit;
      EXCEPTION
      WHEN OTHERS THEN
      rollback;
  END UPDATE_D;
  PROCEDURE DELETE_D (id_s STUDENT.ID_PERSON%TYPE)
    IS
    BEGIN
      DELETE FROM STUDENT WHERE ID_PERSON = id_s;
      commit;
      EXCEPTION
      WHEN OTHERS THEN 
      rollback;
  END DELETE_D;
  FUNCTION SEARCH_D (id_s STUDENT.ID_PERSON%TYPE) RETURN type_cursor
    IS
    cursor1 PACK_MANAGE_STUDENTS.type_cursor;
    BEGIN
      OPEN cursor1 FOR
      SELECT * FROM STUDENT WHERE ID_PERSON = id_s;
      RETURN cursor1;
  END SEARCH_D;
  FUNCTION LIST_D RETURN type_cursor
    IS
    cursor1 PACK_MANAGE_STUDENTS.type_cursor;
    BEGIN
      OPEN cursor1 FOR 
      SELECT * FROM STUDENT;
      RETURN cursor1;
  END LIST_D;
END PACK_MANAGE_STUDENTS;

---------- CREACION DE PROCEDIMIENTOS MANTENIMIENTO DIRECTOR -------------------
CREATE OR REPLACE PACKAGE PACK_MANAGE_DIRECTORS AS
  TYPE type_cursor IS REF CURSOR RETURN DIRECTOR%ROWTYPE;
  PROCEDURE INSERT_D (id_d DIRECTOR.ID_PERSON%TYPE, cod_d DIRECTOR.CODE_DIRECTOR%TYPE, id_f DIRECTOR.ID_FACULTY%TYPE);
  PROCEDURE UPDATE_D (id_d DIRECTOR.ID_PERSON%TYPE, cod_d DIRECTOR.CODE_DIRECTOR%TYPE, id_f DIRECTOR.ID_FACULTY%TYPE);
  PROCEDURE DELETE_D (id_d DIRECTOR.ID_PERSON%TYPE);
  FUNCTION SEARCH_D (id_d DIRECTOR.ID_PERSON%TYPE) RETURN type_cursor;
  FUNCTION LIST_D RETURN type_cursor;
END PACK_MANAGE_DIRECTORS;

CREATE OR REPLACE PACKAGE BODY PACK_MANAGE_DIRECTORS IS
  PROCEDURE INSERT_D (id_d DIRECTOR.ID_PERSON%TYPE, cod_d DIRECTOR.CODE_DIRECTOR%TYPE, id_f DIRECTOR.ID_FACULTY%TYPE)
    IS
    BEGIN
      INSERT INTO DIRECTOR (ID_PERSON, CODE_DIRECTOR, ID_FACULTY) VALUES (id_d, cod_d, id_f);
      commit;
      EXCEPTION
      WHEN OTHERS THEN
      rollback;
  END INSERT_D;
  PROCEDURE UPDATE_D (id_d DIRECTOR.ID_PERSON%TYPE, cod_d DIRECTOR.CODE_DIRECTOR%TYPE, id_f DIRECTOR.ID_FACULTY%TYPE)
    IS
    BEGIN
      UPDATE DIRECTOR SET CODE_DIRECTOR = cod_d, ID_FACULTY = id_f where ID_PERSON = id_d;
      commit;
      EXCEPTION
      WHEN OTHERS THEN
      rollback;
  END UPDATE_D;
  PROCEDURE DELETE_D (id_d DIRECTOR.ID_PERSON%TYPE)
    IS
    BEGIN
      DELETE FROM DIRECTOR WHERE ID_PERSON = id_d;
      commit;
      EXCEPTION
      WHEN OTHERS THEN 
      rollback;
  END DELETE_D;
  FUNCTION SEARCH_D (id_d DIRECTOR.ID_PERSON%TYPE) RETURN type_cursor
    IS
    cursor1 PACK_MANAGE_DIRECTORS.type_cursor;
    BEGIN
      OPEN cursor1 FOR
      SELECT * FROM DIRECTOR WHERE ID_PERSON = id_d;
      RETURN cursor1;
  END SEARCH_D;
  FUNCTION LIST_D RETURN type_cursor
    IS
    cursor1 PACK_MANAGE_DIRECTORS.type_cursor;
    BEGIN
      OPEN cursor1 FOR 
      SELECT * FROM DIRECTOR;
      RETURN cursor1;
  END LIST_D;
END PACK_MANAGE_DIRECTORS;

---------- CREACION DE PROCEDIMIENTOS MANTENIMIENTO FACULTY -------------------
CREATE OR REPLACE PACKAGE PACK_MANAGE_FACULTIES AS
  TYPE type_cursor IS REF CURSOR RETURN FACULTY%ROWTYPE;
  PROCEDURE INSERT_D (name_f FACULTY.NAME_FACULTY%TYPE, id_p FACULTY.ID_PERSON%TYPE);
  PROCEDURE UPDATE_D (id_f FACULTY.ID_FACULTY%TYPE, name_f FACULTY.NAME_FACULTY%TYPE, id_p FACULTY.ID_PERSON%TYPE);
  PROCEDURE DELETE_D (id_f FACULTY.ID_FACULTY%TYPE);
  FUNCTION SEARCH_D (id_f FACULTY.ID_FACULTY%TYPE) RETURN type_cursor;
  FUNCTION LIST_D RETURN type_cursor;
END PACK_MANAGE_FACULTIES;

CREATE OR REPLACE PACKAGE BODY PACK_MANAGE_FACULTIES IS
  PROCEDURE INSERT_D (name_f FACULTY.NAME_FACULTY%TYPE, id_p FACULTY.ID_PERSON%TYPE)
    IS
    BEGIN
      INSERT INTO FACULTY (NAME_FACULTY, ID_PERSON) VALUES (name_f, id_p);
      commit;
      EXCEPTION
      WHEN OTHERS THEN
      rollback;
  END INSERT_D;
  PROCEDURE UPDATE_D (id_f FACULTY.ID_FACULTY%TYPE, name_f FACULTY.NAME_FACULTY%TYPE, id_p FACULTY.ID_PERSON%TYPE)
    IS
    BEGIN
      UPDATE FACULTY SET NAME_FACULTY = name_f, ID_PERSON = id_p where ID_FACULTY = id_f;
      commit;
      EXCEPTION
      WHEN OTHERS THEN
      rollback;
  END UPDATE_D;
  PROCEDURE DELETE_D (id_f FACULTY.ID_FACULTY%TYPE)
    IS
    BEGIN
      DELETE FROM FACULTY WHERE ID_FACULTY = id_f;
      commit;
      EXCEPTION
      WHEN OTHERS THEN 
      rollback;
  END DELETE_D;
  FUNCTION SEARCH_D (id_f FACULTY.ID_FACULTY%TYPE) RETURN type_cursor
    IS
    cursor1 PACK_MANAGE_FACULTIES.type_cursor;
    BEGIN
      OPEN cursor1 FOR
      SELECT * FROM FACULTY WHERE ID_FACULTY = id_f;
      RETURN cursor1;
  END SEARCH_D;
  FUNCTION LIST_D RETURN type_cursor
    IS
    cursor1 PACK_MANAGE_FACULTIES.type_cursor;
    BEGIN
      OPEN cursor1 FOR 
      SELECT * FROM FACULTY;
      RETURN cursor1;
  END LIST_D;
END PACK_MANAGE_FACULTIES;
  
---------- CREACION DE PROCEDIMIENTOS MANTENIMIENTO SCHOOL -------------------
CREATE OR REPLACE PACKAGE PACK_MANAGE_SCHOOLS AS
  TYPE type_cursor IS REF CURSOR RETURN SCHOOL%ROWTYPE;
  PROCEDURE INSERT_D (name_s SCHOOL.NAME_SCHOOL%TYPE, id_f SCHOOL.ID_FACULTY%TYPE);
  PROCEDURE UPDATE_D (id_s SCHOOL.ID_SCHOOL%TYPE, name_s SCHOOL.NAME_SCHOOL%TYPE, id_f SCHOOL.ID_FACULTY%TYPE);
  PROCEDURE DELETE_D (id_s SCHOOL.ID_SCHOOL%TYPE);
  FUNCTION SEARCH_D (id_s SCHOOL.ID_SCHOOL%TYPE) RETURN type_cursor;
  FUNCTION LIST_D RETURN type_cursor;
END PACK_MANAGE_SCHOOLS;

CREATE OR REPLACE PACKAGE BODY PACK_MANAGE_SCHOOLS IS
  PROCEDURE INSERT_D (name_s SCHOOL.NAME_SCHOOL%TYPE, id_f SCHOOL.ID_FACULTY%TYPE)
    IS
    BEGIN
      INSERT INTO SCHOOL (NAME_SCHOOL, ID_FACULTY) VALUES (name_s, id_f);
      commit;
      EXCEPTION
      WHEN OTHERS THEN
      rollback;
  END INSERT_D;
  PROCEDURE UPDATE_D (id_s SCHOOL.ID_SCHOOL%TYPE, name_s SCHOOL.NAME_SCHOOL%TYPE, id_f SCHOOL.ID_FACULTY%TYPE)
    IS
    BEGIN
      UPDATE SCHOOL SET NAME_SCHOOL = name_s, ID_FACULTY = id_f where ID_SCHOOL = id_s;
      commit;
      EXCEPTION
      WHEN OTHERS THEN
      rollback;
  END UPDATE_D;
  PROCEDURE DELETE_D (id_s SCHOOL.ID_SCHOOL%TYPE)
    IS
    BEGIN
      DELETE FROM SCHOOL WHERE ID_FACULTY = id_s;
      commit;
      EXCEPTION
      WHEN OTHERS THEN 
      rollback;
  END DELETE_D;
  FUNCTION SEARCH_D (id_s SCHOOL.ID_SCHOOL%TYPE) RETURN type_cursor
    IS
    cursor1 PACK_MANAGE_SCHOOLS.type_cursor;
    BEGIN
      OPEN cursor1 FOR
      SELECT * FROM SCHOOL WHERE ID_SCHOOL = id_s;
      RETURN cursor1;
  END SEARCH_D;
  FUNCTION LIST_D RETURN type_cursor
    IS
    cursor1 PACK_MANAGE_SCHOOLS.type_cursor;
    BEGIN
      OPEN cursor1 FOR 
      SELECT * FROM SCHOOL;
      RETURN cursor1;
  END LIST_D;
END PACK_MANAGE_SCHOOLS;
  
---------- CREACION DE PROCEDIMIENTOS MANTENIMIENTO SECTION -------------------
create or replace PACKAGE PACK_MANAGE_SECTIONS AS
  TYPE type_cursor IS REF CURSOR RETURN SECTION%ROWTYPE;
  PROCEDURE INSERT_D (id_s SECTION.ID_SUBJECT%TYPE, id_p SECTION.ID_PERSON%TYPE, sec_g SECTION.SECTION_GROUP%TYPE);
  PROCEDURE UPDATE_D (id_se SECTION.ID_SECTION%TYPE, id_s SECTION.ID_SUBJECT%TYPE, id_p SECTION.ID_PERSON%TYPE,
  sec_g SECTION.SECTION_GROUP%TYPE);
  PROCEDURE DELETE_D (id_se SECTION.ID_SECTION%TYPE);
  FUNCTION SEARCH_D (id_se SECTION.ID_SECTION%TYPE) RETURN type_cursor;
  FUNCTION LIST_D RETURN type_cursor;
  FUNCTION SEARCH_TE_SU (id_te SECTION.ID_PERSON%TYPE,id_su SECTION.ID_SUBJECT%TYPE) RETURN type_cursor;
END PACK_MANAGE_SECTIONS;

create or replace PACKAGE BODY PACK_MANAGE_SECTIONS IS
  PROCEDURE INSERT_D (id_s SECTION.ID_SUBJECT%TYPE, id_p SECTION.ID_PERSON%TYPE, sec_g SECTION.SECTION_GROUP%TYPE)
    IS
    BEGIN
      INSERT INTO SECTION (ID_SUBJECT, ID_PERSON, SECTION_GROUP) VALUES (id_s, id_p, sec_g);
      commit;
      EXCEPTION
      WHEN OTHERS THEN
      rollback;
  END INSERT_D;
  PROCEDURE UPDATE_D (id_se SECTION.ID_SECTION%TYPE, id_s SECTION.ID_SUBJECT%TYPE, id_p SECTION.ID_PERSON%TYPE,
  sec_g SECTION.SECTION_GROUP%TYPE)
    IS
    BEGIN
      UPDATE SECTION SET ID_SUBJECT = id_s, ID_PERSON = id_p, SECTION_GROUP = sec_g
      WHERE ID_SECTION = id_se;
      commit;
      EXCEPTION
      WHEN OTHERS THEN
      rollback;
  END UPDATE_D;
  PROCEDURE DELETE_D (id_se SECTION.ID_SECTION%TYPE)
    IS
    BEGIN
      DELETE FROM SECTION WHERE ID_SECTION = id_se;
      commit;
      EXCEPTION
      WHEN OTHERS THEN 
      rollback;
  END DELETE_D;
  FUNCTION SEARCH_D (id_se SECTION.ID_SECTION%TYPE) RETURN type_cursor
    IS
    cursor1 PACK_MANAGE_SECTIONS.type_cursor;
    BEGIN
      OPEN cursor1 FOR
      SELECT * FROM SECTION WHERE ID_SECTION = id_se;
      RETURN cursor1;
  END SEARCH_D;
  FUNCTION LIST_D RETURN type_cursor
    IS
    cursor1 PACK_MANAGE_SECTIONS.type_cursor;
    BEGIN
      OPEN cursor1 FOR 
      SELECT * FROM SECTION;
      RETURN cursor1;
  END LIST_D;
  FUNCTION SEARCH_TE_SU (id_te SECTION.ID_PERSON%TYPE,id_su SECTION.ID_SUBJECT%TYPE) RETURN type_cursor
    IS
    cursor1 PACK_MANAGE_SECTIONS.type_cursor;
    BEGIN
      OPEN cursor1 FOR
      SELECT * FROM SECTION WHERE ID_PERSON= id_te AND ID_SUBJECT = id_su;
      RETURN cursor1;
  END SEARCH_TE_SU;
END PACK_MANAGE_SECTIONS;

---------- CREACION DE PROCEDIMIENTOS MANTENIMIENTO SUBJECT -------------------
CREATE OR REPLACE PACKAGE PACK_MANAGE_SUBJECTS AS
  TYPE type_cursor IS REF CURSOR RETURN SUBJECT%ROWTYPE;
  PROCEDURE INSERT_D (name_s SUBJECT.NAME_SUBJECT%TYPE);
  PROCEDURE UPDATE_D (id_s SUBJECT.ID_SUBJECT%TYPE, name_s SUBJECT.NAME_SUBJECT%TYPE);
  PROCEDURE DELETE_D (id_s SUBJECT.ID_SUBJECT%TYPE);
  FUNCTION SEARCH_D (id_s SUBJECT.ID_SUBJECT%TYPE) RETURN type_cursor;
  FUNCTION LIST_D RETURN type_cursor;
END PACK_MANAGE_SUBJECTS;

CREATE OR REPLACE PACKAGE BODY PACK_MANAGE_SUBJECTS IS
  PROCEDURE INSERT_D (name_s SUBJECT.NAME_SUBJECT%TYPE)
    IS
    BEGIN
      INSERT INTO SUBJECT (NAME_SUBJECT) VALUES (name_s);
      commit;
      EXCEPTION
      WHEN OTHERS THEN
      rollback;
  END INSERT_D;
  PROCEDURE UPDATE_D (id_s SUBJECT.ID_SUBJECT%TYPE, name_s SUBJECT.NAME_SUBJECT%TYPE)
    IS
    BEGIN
      UPDATE SUBJECT SET NAME_SUBJECT = name_s WHERE ID_SUBJECT = id_s;
      commit;
      EXCEPTION
      WHEN OTHERS THEN
      rollback;
  END UPDATE_D;
  PROCEDURE DELETE_D (id_s SUBJECT.ID_SUBJECT%TYPE)
    IS
    BEGIN
      DELETE FROM SUBJECT WHERE ID_SUBJECT = id_s;
      commit;
      EXCEPTION
      WHEN OTHERS THEN 
      rollback;
  END DELETE_D;
  FUNCTION SEARCH_D (id_s SUBJECT.ID_SUBJECT%TYPE) RETURN type_cursor
    IS
    cursor1 PACK_MANAGE_SUBJECTS.type_cursor;
    BEGIN
      OPEN cursor1 FOR
      SELECT * FROM SUBJECT WHERE ID_SUBJECT = id_s;
      RETURN cursor1;
  END SEARCH_D;
  FUNCTION LIST_D RETURN type_cursor
    IS
    cursor1 PACK_MANAGE_SUBJECTS.type_cursor;
    BEGIN
      OPEN cursor1 FOR 
      SELECT * FROM SUBJECT;
      RETURN cursor1;
  END LIST_D;
END PACK_MANAGE_SUBJECTS;

---------- CREACION DE PROCEDIMIENTOS MANTENIMIENTO THEME -------------------
CREATE OR REPLACE PACKAGE PACK_MANAGE_THEMES AS
  TYPE type_cursor IS REF CURSOR RETURN THEME%ROWTYPE;
  PROCEDURE INSERT_D (id_s THEME.ID_SUBJECT%TYPE, name_t THEME.NAME_THEME%TYPE);
  PROCEDURE UPDATE_D (id_t THEME.ID_THEME%TYPE, id_s THEME.ID_SUBJECT%TYPE, name_t THEME.NAME_THEME%TYPE);
  PROCEDURE DELETE_D (id_t THEME.ID_THEME%TYPE);
  FUNCTION SEARCH_D (id_t THEME.ID_THEME%TYPE) RETURN type_cursor;
  FUNCTION LIST_D RETURN type_cursor;
END PACK_MANAGE_THEMES;

CREATE OR REPLACE PACKAGE BODY PACK_MANAGE_THEMES IS
  PROCEDURE INSERT_D (id_s THEME.ID_SUBJECT%TYPE, name_t THEME.NAME_THEME%TYPE)
    IS
    BEGIN
      INSERT INTO THEME (ID_SUBJECT, NAME_THEME) VALUES (id_s, name_t);
      commit;
      EXCEPTION
      WHEN OTHERS THEN
      rollback;
  END INSERT_D;
  PROCEDURE UPDATE_D (id_t THEME.ID_THEME%TYPE, id_s THEME.ID_SUBJECT%TYPE, name_t THEME.NAME_THEME%TYPE)
    IS
    BEGIN
      UPDATE THEME SET NAME_THEME = name_t WHERE ID_THEME = id_t;
      commit;
      EXCEPTION
      WHEN OTHERS THEN
      rollback;
  END UPDATE_D;
  PROCEDURE DELETE_D (id_t THEME.ID_THEME%TYPE)
    IS
    BEGIN
      DELETE FROM THEME WHERE ID_THEME = id_t;
      commit;
      EXCEPTION
      WHEN OTHERS THEN 
      rollback;
  END DELETE_D;
  FUNCTION SEARCH_D (id_t THEME.ID_THEME%TYPE) RETURN type_cursor
    IS
    cursor1 PACK_MANAGE_THEMES.type_cursor;
    BEGIN
      OPEN cursor1 FOR
      SELECT * FROM THEME WHERE ID_THEME = id_t;
      RETURN cursor1;
  END SEARCH_D;
  FUNCTION LIST_D RETURN type_cursor
    IS
    cursor1 PACK_MANAGE_THEMES.type_cursor;
    BEGIN
      OPEN cursor1 FOR 
      SELECT * FROM THEME;
      RETURN cursor1;
  END LIST_D;
END PACK_MANAGE_THEMES;

---------- CREACION DE PROCEDIMIENTOS MANTENIMIENTO QUESTION -------------------
create or replace PACKAGE PACK_MANAGE_QUESTIONS AS
  TYPE type_cursor IS REF CURSOR RETURN QUESTION%ROWTYPE;
  PROCEDURE INSERT_D (id_t QUESTION.ID_THEME%TYPE, ques QUESTION.QUESTION%TYPE, es QUESTION.SCORE%TYPE);
  PROCEDURE UPDATE_D (id_q QUESTION.ID_QUESTION%TYPE, id_t QUESTION.ID_THEME%TYPE, ques QUESTION.QUESTION%TYPE,
  es QUESTION.SCORE%TYPE);
  PROCEDURE DELETE_D (id_q QUESTION.ID_QUESTION%TYPE);
  FUNCTION SEARCH_D (id_q QUESTION.ID_QUESTION%TYPE) RETURN type_cursor;
  FUNCTION LIST_D RETURN type_cursor;
  FUNCTION SEARCH_QU_SUB (id_s SUBJECT.ID_SUBJECT%TYPE) RETURN type_cursor;
END PACK_MANAGE_QUESTIONS;



create or replace PACKAGE BODY PACK_MANAGE_QUESTIONS IS
  PROCEDURE INSERT_D (id_t QUESTION.ID_THEME%TYPE, ques QUESTION.QUESTION%TYPE, es QUESTION.SCORE%TYPE)
    IS
    BEGIN
      INSERT INTO QUESTION (ID_THEME, QUESTION, SCORE) VALUES (id_t, ques, es);
      commit;
      EXCEPTION
      WHEN OTHERS THEN
      rollback;
  END INSERT_D;
  PROCEDURE UPDATE_D (id_q QUESTION.ID_QUESTION%TYPE, id_t QUESTION.ID_THEME%TYPE, ques QUESTION.QUESTION%TYPE,
  es QUESTION.SCORE%TYPE)
    IS
    BEGIN
      UPDATE QUESTION SET ID_THEME = id_q, QUESTION = ques, SCORE = es WHERE ID_QUESTION = id_q;
      commit;
      EXCEPTION
      WHEN OTHERS THEN
      rollback;
  END UPDATE_D;
  PROCEDURE DELETE_D (id_q QUESTION.ID_QUESTION%TYPE)
    IS
    BEGIN
      DELETE FROM QUESTION WHERE ID_QUESTION = id_q;
      commit;
      EXCEPTION
      WHEN OTHERS THEN 
      rollback;
  END DELETE_D;
  FUNCTION SEARCH_D (id_q QUESTION.ID_QUESTION%TYPE) RETURN type_cursor
    IS
    cursor1 PACK_MANAGE_QUESTIONS.type_cursor;
    BEGIN
      OPEN cursor1 FOR
      SELECT * FROM QUESTION WHERE ID_QUESTION = id_q;
      RETURN cursor1;
  END SEARCH_D;
  FUNCTION LIST_D RETURN type_cursor
    IS
    cursor1 PACK_MANAGE_QUESTIONS.type_cursor;
    BEGIN
      OPEN cursor1 FOR 
      SELECT * FROM QUESTION;
      RETURN cursor1;
  END LIST_D;
  FUNCTION SEARCH_QU_SUB (id_s SUBJECT.ID_SUBJECT%TYPE) RETURN type_cursor
    IS
    cursor1 PACK_MANAGE_QUESTIONS.type_cursor;
    BEGIN
      OPEN cursor1 FOR
      SELECT  
       QU.ID_QUESTION,QU.ID_THEME ,QU.QUESTION, QU.SCORE
      FROM SUBJECT SU
      LEFT JOIN THEME TH ON (SU.ID_SUBJECT=TH.ID_SUBJECT)
      LEFT JOIN QUESTION QU ON (TH.ID_THEME=QU.ID_THEME)
      WHERE SU.ID_SUBJECT=id_s
      ORDER BY 1;
      RETURN cursor1;
  END SEARCH_QU_SUB;
END PACK_MANAGE_QUESTIONS;

---------- CREACION DE PROCEDIMIENTOS MANTENIMIENTO ALTERNATIVE -------------------
create or replace PACKAGE PACK_MANAGE_ALTERNATIVES AS
  TYPE type_cursor IS REF CURSOR RETURN ALTERNATIVE%ROWTYPE;
  PROCEDURE INSERT_D (id_q ALTERNATIVE.ID_QUESTION%TYPE, al_a ALTERNATIVE.ALTERNATIVE_A%TYPE,
  al_b ALTERNATIVE.ALTERNATIVE_B%TYPE, al_c ALTERNATIVE.ALTERNATIVE_C%TYPE, ans ALTERNATIVE.ANSWER%TYPE);
  PROCEDURE UPDATE_D (id_a ALTERNATIVE.ID_ALTERNATIVE%TYPE, id_q ALTERNATIVE.ID_QUESTION%TYPE, al_a ALTERNATIVE.ALTERNATIVE_A%TYPE,
  al_b ALTERNATIVE.ALTERNATIVE_B%TYPE, al_c ALTERNATIVE.ALTERNATIVE_C%TYPE, ans ALTERNATIVE.ANSWER%TYPE);
  PROCEDURE DELETE_D (id_a ALTERNATIVE.ID_ALTERNATIVE%TYPE);
  FUNCTION SEARCH_D (id_a ALTERNATIVE.ID_ALTERNATIVE%TYPE) RETURN type_cursor;
  FUNCTION LIST_D RETURN type_cursor;
  FUNCTION SEARCH_QU (id_q ALTERNATIVE.ID_QUESTION%TYPE) RETURN type_cursor;
END PACK_MANAGE_ALTERNATIVES;

create or replace PACKAGE BODY PACK_MANAGE_ALTERNATIVES IS
  PROCEDURE INSERT_D (id_q ALTERNATIVE.ID_QUESTION%TYPE, al_a ALTERNATIVE.ALTERNATIVE_A%TYPE,
  al_b ALTERNATIVE.ALTERNATIVE_B%TYPE, al_c ALTERNATIVE.ALTERNATIVE_C%TYPE, ans ALTERNATIVE.ANSWER%TYPE)
    IS
    BEGIN
      INSERT INTO ALTERNATIVE (ID_QUESTION, ALTERNATIVE_A, ALTERNATIVE_B, ALTERNATIVE_C, ANSWER) VALUES
      (id_q, al_a, al_b, al_c, ans);
      commit;
      EXCEPTION
      WHEN OTHERS THEN
      rollback;
  END INSERT_D;
  PROCEDURE UPDATE_D (id_a ALTERNATIVE.ID_ALTERNATIVE%TYPE, id_q ALTERNATIVE.ID_QUESTION%TYPE, al_a ALTERNATIVE.ALTERNATIVE_A%TYPE,
  al_b ALTERNATIVE.ALTERNATIVE_B%TYPE, al_c ALTERNATIVE.ALTERNATIVE_C%TYPE, ans ALTERNATIVE.ANSWER%TYPE)
    IS
    BEGIN
      UPDATE ALTERNATIVE SET ID_QUESTION = id_q, ALTERNATIVE_A = al_a, ALTERNATIVE_B = al_b, ALTERNATIVE_C = al_c, ANSWER = ans
      WHERE ID_ALTERNATIVE = id_a;
      commit;
      EXCEPTION
      WHEN OTHERS THEN
      rollback;
  END UPDATE_D;
  PROCEDURE DELETE_D (id_a ALTERNATIVE.ID_ALTERNATIVE%TYPE)
    IS
    BEGIN
      DELETE FROM ALTERNATIVE WHERE ID_ALTERNATIVE = id_a;
      commit;
      EXCEPTION
      WHEN OTHERS THEN 
      rollback;
  END DELETE_D;
  FUNCTION SEARCH_D (id_a ALTERNATIVE.ID_ALTERNATIVE%TYPE) RETURN type_cursor
    IS
    cursor1 PACK_MANAGE_ALTERNATIVES.type_cursor;
    BEGIN
      OPEN cursor1 FOR
      SELECT * FROM ALTERNATIVE WHERE ID_ALTERNATIVE = id_a;
      RETURN cursor1;
  END SEARCH_D;
  FUNCTION LIST_D RETURN type_cursor
    IS
    cursor1 PACK_MANAGE_ALTERNATIVES.type_cursor;
    BEGIN
      OPEN cursor1 FOR 
      SELECT * FROM ALTERNATIVE;
      RETURN cursor1;
  END LIST_D;
  FUNCTION SEARCH_QU (id_q ALTERNATIVE.ID_QUESTION%TYPE) RETURN type_cursor
    IS
    cursor1 PACK_MANAGE_ALTERNATIVES.type_cursor;
    BEGIN
      OPEN cursor1 FOR
      SELECT * FROM ALTERNATIVE WHERE ID_QUESTION = id_q;
      RETURN cursor1;
  END SEARCH_QU;
END PACK_MANAGE_ALTERNATIVES;

---------- CREACION DE PROCEDIMIENTOS MANTENIMIENTO EXAM -------------------
CREATE OR REPLACE PACKAGE PACK_MANAGE_EXAMS AS
  TYPE type_cursor IS REF CURSOR RETURN EXAM%ROWTYPE;
  PROCEDURE INSERT_D (id_s EXAM.ID_SECTION%TYPE);
  PROCEDURE UPDATE_D (id_e EXAM.ID_EXAM%TYPE, id_s EXAM.ID_SECTION%TYPE);
  PROCEDURE DELETE_D (id_e EXAM.ID_EXAM%TYPE);
  FUNCTION SEARCH_D (id_e EXAM.ID_EXAM%TYPE) RETURN type_cursor;
  FUNCTION LIST_D RETURN type_cursor;
END PACK_MANAGE_EXAMS;

CREATE OR REPLACE PACKAGE BODY PACK_MANAGE_EXAMS IS
  PROCEDURE INSERT_D (id_s EXAM.ID_SECTION%TYPE)
    IS
    BEGIN
      INSERT INTO EXAM (ID_SECTION) VALUES (id_s);
      commit;
      EXCEPTION
      WHEN OTHERS THEN
      rollback;
  END INSERT_D;
  PROCEDURE UPDATE_D (id_e EXAM.ID_EXAM%TYPE, id_s EXAM.ID_SECTION%TYPE)
    IS
    BEGIN
      UPDATE EXAM SET ID_SECTION = id_s WHERE ID_EXAM = id_e;
      commit;
      EXCEPTION
      WHEN OTHERS THEN
      rollback;
  END UPDATE_D;
  PROCEDURE DELETE_D (id_e EXAM.ID_EXAM%TYPE)
    IS
    BEGIN
      DELETE FROM EXAM WHERE ID_EXAM = id_e;
      commit;
      EXCEPTION
      WHEN OTHERS THEN 
      rollback;
  END DELETE_D;
  FUNCTION SEARCH_D (id_e EXAM.ID_EXAM%TYPE) RETURN type_cursor
    IS
    cursor1 PACK_MANAGE_EXAMS.type_cursor;
    BEGIN
      OPEN cursor1 FOR
      SELECT * FROM EXAM WHERE ID_EXAM = id_e;
      RETURN cursor1;
  END SEARCH_D;
  FUNCTION LIST_D RETURN type_cursor
    IS
    cursor1 PACK_MANAGE_EXAMS.type_cursor;
    BEGIN
      OPEN cursor1 FOR 
      SELECT * FROM EXAM;
      RETURN cursor1;
  END LIST_D;
END PACK_MANAGE_EXAMS;

---------- CREACION DE PROCEDIMIENTOS MANTENIMIENTO QUESTION_EXAM -------------------
CREATE OR REPLACE PACKAGE PACK_MANAGE_QUESTION_EXAMS AS
  TYPE type_cursor IS REF CURSOR RETURN QUESTION_EXAM%ROWTYPE;
  PROCEDURE INSERT_D (id_e QUESTION_EXAM.ID_EXAM%TYPE, ques QUESTION_EXAM.QUESTION%TYPE, n_ques QUESTION_EXAM.N_QUESTION%TYPE);
  PROCEDURE UPDATE_D (id_qe QUESTION_EXAM.ID_QUESTION_EXAM%TYPE, id_e QUESTION_EXAM.ID_EXAM%TYPE,
  ques QUESTION_EXAM.QUESTION%TYPE, n_ques QUESTION_EXAM.N_QUESTION%TYPE);
  PROCEDURE DELETE_D (id_qe QUESTION_EXAM.ID_QUESTION_EXAM%TYPE);
  FUNCTION SEARCH_D (id_qe QUESTION_EXAM.ID_QUESTION_EXAM%TYPE) RETURN type_cursor;
  FUNCTION LIST_D RETURN type_cursor;
END PACK_MANAGE_QUESTION_EXAMS;

CREATE OR REPLACE PACKAGE BODY PACK_MANAGE_QUESTION_EXAMS IS
  PROCEDURE INSERT_D (id_e QUESTION_EXAM.ID_EXAM%TYPE, ques QUESTION_EXAM.QUESTION%TYPE, n_ques QUESTION_EXAM.N_QUESTION%TYPE)
    IS
    BEGIN
      INSERT INTO QUESTION_EXAM (ID_EXAM, QUESTION, N_QUESTION) VALUES (id_e, ques, n_ques);
      commit;
      EXCEPTION
      WHEN OTHERS THEN
      rollback;
  END INSERT_D;
  PROCEDURE UPDATE_D (id_qe QUESTION_EXAM.ID_QUESTION_EXAM%TYPE, id_e QUESTION_EXAM.ID_EXAM%TYPE,
  ques QUESTION_EXAM.QUESTION%TYPE, n_ques QUESTION_EXAM.N_QUESTION%TYPE)
    IS
    BEGIN
      UPDATE QUESTION_EXAM SET ID_EXAM = id_e, QUESTION = ques, N_QUESTION = n_ques WHERE ID_QUESTION_EXAM = id_qe;
      commit;
      EXCEPTION
      WHEN OTHERS THEN
      rollback;
  END UPDATE_D;
  PROCEDURE DELETE_D (id_qe QUESTION_EXAM.ID_QUESTION_EXAM%TYPE)
    IS
    BEGIN
      DELETE FROM QUESTION_EXAM WHERE ID_QUESTION_EXAM = id_qe;
      commit;
      EXCEPTION
      WHEN OTHERS THEN 
      rollback;
  END DELETE_D;
  FUNCTION SEARCH_D (id_qe QUESTION_EXAM.ID_QUESTION_EXAM%TYPE) RETURN type_cursor
    IS
    cursor1 PACK_MANAGE_QUESTION_EXAMS.type_cursor;
    BEGIN
      OPEN cursor1 FOR
      SELECT * FROM QUESTION_EXAM WHERE ID_QUESTION_EXAM = id_qe;
      RETURN cursor1;
  END SEARCH_D;
  FUNCTION LIST_D RETURN type_cursor
    IS
    cursor1 PACK_MANAGE_QUESTION_EXAMS.type_cursor;
    BEGIN
      OPEN cursor1 FOR 
      SELECT * FROM QUESTION_EXAM;
      RETURN cursor1;
  END LIST_D;
END PACK_MANAGE_QUESTION_EXAMS;

---------- CREACION DE PROCEDIMIENTOS MANTENIMIENTO ALTERNATIVE_EXAM -------------------
CREATE OR REPLACE PACKAGE PACK_MANAGE_ALTERNATIVE_EXAMS AS
  TYPE type_cursor IS REF CURSOR RETURN ALTERNATIVE_EXAM%ROWTYPE;
  PROCEDURE INSERT_D (id_qe ALTERNATIVE_EXAM.ID_QUESTION_EXAM%TYPE,al_a ALTERNATIVE.ALTERNATIVE_A%TYPE,
  al_b ALTERNATIVE.ALTERNATIVE_B%TYPE, al_c ALTERNATIVE.ALTERNATIVE_C%TYPE);
  PROCEDURE UPDATE_D (id_ae ALTERNATIVE_EXAM.ID_ALTERNATIVE_EXAM%TYPE, id_qe ALTERNATIVE_EXAM.ID_QUESTION_EXAM%TYPE, 
  al_a ALTERNATIVE.ALTERNATIVE_A%TYPE, al_b ALTERNATIVE.ALTERNATIVE_B%TYPE, al_c ALTERNATIVE.ALTERNATIVE_C%TYPE);
  PROCEDURE DELETE_D (id_ae ALTERNATIVE_EXAM.ID_ALTERNATIVE_EXAM%TYPE);
  FUNCTION SEARCH_D (id_ae ALTERNATIVE_EXAM.ID_ALTERNATIVE_EXAM%TYPE) RETURN type_cursor;
  FUNCTION LIST_D RETURN type_cursor;
END PACK_MANAGE_ALTERNATIVE_EXAMS;

CREATE OR REPLACE PACKAGE BODY PACK_MANAGE_ALTERNATIVE_EXAMS IS
  PROCEDURE INSERT_D (id_qe ALTERNATIVE_EXAM.ID_QUESTION_EXAM%TYPE,al_a ALTERNATIVE.ALTERNATIVE_A%TYPE,
  al_b ALTERNATIVE.ALTERNATIVE_B%TYPE, al_c ALTERNATIVE.ALTERNATIVE_C%TYPE)
    IS
    BEGIN
      INSERT INTO ALTERNATIVE_EXAM (ID_QUESTION_EXAM, ALTERNATIVE_A, ALTERNATIVE_B, ALTERNATIVE_C)
      VALUES (id_qe, al_a, al_b, al_c);
      commit;
      EXCEPTION
      WHEN OTHERS THEN
      rollback;
  END INSERT_D;
  PROCEDURE UPDATE_D (id_ae ALTERNATIVE_EXAM.ID_ALTERNATIVE_EXAM%TYPE, id_qe ALTERNATIVE_EXAM.ID_QUESTION_EXAM%TYPE, 
  al_a ALTERNATIVE.ALTERNATIVE_A%TYPE, al_b ALTERNATIVE.ALTERNATIVE_B%TYPE, al_c ALTERNATIVE.ALTERNATIVE_C%TYPE)
    IS
    BEGIN
      UPDATE ALTERNATIVE_EXAM SET ID_QUESTION_EXAM = id_qe, ALTERNATIVE_A = al_a, ALTERNATIVE_B = al_b, ALTERNATIVE_C = al_c
      WHERE ID_ALTERNATIVE_EXAM = id_ae;
      commit;
      EXCEPTION
      WHEN OTHERS THEN
      rollback;
  END UPDATE_D;
  PROCEDURE DELETE_D (id_ae ALTERNATIVE_EXAM.ID_ALTERNATIVE_EXAM%TYPE)
    IS
    BEGIN
      DELETE FROM ALTERNATIVE_EXAM WHERE ID_ALTERNATIVE_EXAM = id_ae;
      commit;
      EXCEPTION
      WHEN OTHERS THEN 
      rollback;
  END DELETE_D;
  FUNCTION SEARCH_D (id_ae ALTERNATIVE_EXAM.ID_ALTERNATIVE_EXAM%TYPE) RETURN type_cursor
    IS
    cursor1 PACK_MANAGE_ALTERNATIVE_EXAMS.type_cursor;
    BEGIN
      OPEN cursor1 FOR
      SELECT * FROM ALTERNATIVE_EXAM WHERE ID_ALTERNATIVE_EXAM = id_ae;
      RETURN cursor1;
  END SEARCH_D;
  FUNCTION LIST_D RETURN type_cursor
    IS
    cursor1 PACK_MANAGE_ALTERNATIVE_EXAMS.type_cursor;
    BEGIN
      OPEN cursor1 FOR 
      SELECT * FROM ALTERNATIVE_EXAM;
      RETURN cursor1;
  END LIST_D;
END PACK_MANAGE_ALTERNATIVE_EXAMS;

---------- CREACION DE PROCEDIMIENTOS MANTENIMIENTO SECTION_STUDENT -------------------
CREATE OR REPLACE PACKAGE PACK_MANAGE_SECTION_STUDENTS AS
  TYPE type_cursor IS REF CURSOR RETURN SECTION_STUDENT%ROWTYPE;
  PROCEDURE INSERT_D (id_s SECTION_STUDENT.ID_SECTION%TYPE, id_p SECTION_STUDENT.ID_PERSON%TYPE);
  PROCEDURE UPDATE_D (id_ss SECTION_STUDENT.ID_SECTION_STUDENT%TYPE, id_s SECTION_STUDENT.ID_SECTION%TYPE,
  id_p SECTION_STUDENT.ID_PERSON%TYPE);
  PROCEDURE DELETE_D (id_ss SECTION_STUDENT.ID_SECTION_STUDENT%TYPE);
  FUNCTION SEARCH_D (id_ss SECTION_STUDENT.ID_SECTION_STUDENT%TYPE) RETURN type_cursor;
  FUNCTION LIST_D RETURN type_cursor;
END PACK_MANAGE_SECTION_STUDENTS;

CREATE OR REPLACE PACKAGE BODY PACK_MANAGE_SECTION_STUDENTS IS
  PROCEDURE INSERT_D (id_s SECTION_STUDENT.ID_SECTION%TYPE, id_p SECTION_STUDENT.ID_PERSON%TYPE)
    IS
    BEGIN
      INSERT INTO SECTION_STUDENT (ID_SECTION, ID_PERSON) VALUES (id_s, id_p);
      commit;
      EXCEPTION
      WHEN OTHERS THEN
      rollback;
  END INSERT_D;
  PROCEDURE UPDATE_D (id_ss SECTION_STUDENT.ID_SECTION_STUDENT%TYPE, id_s SECTION_STUDENT.ID_SECTION%TYPE,
  id_p SECTION_STUDENT.ID_PERSON%TYPE)
    IS
    BEGIN
      UPDATE SECTION_STUDENT SET ID_SECTION = id_s, ID_PERSON = id_p WHERE ID_SECTION_STUDENT = id_ss;
      commit;
      EXCEPTION
      WHEN OTHERS THEN
      rollback;
  END UPDATE_D;
  PROCEDURE DELETE_D (id_ss SECTION_STUDENT.ID_SECTION_STUDENT%TYPE)
    IS
    BEGIN
      DELETE FROM SECTION_STUDENT WHERE ID_SECTION_STUDENT = id_ss;
      commit;
      EXCEPTION
      WHEN OTHERS THEN 
      rollback;
  END DELETE_D;
  FUNCTION SEARCH_D (id_ss SECTION_STUDENT.ID_SECTION_STUDENT%TYPE) RETURN type_cursor
    IS
    cursor1 PACK_MANAGE_SECTION_STUDENTS.type_cursor;
    BEGIN
      OPEN cursor1 FOR
      SELECT * FROM SECTION_STUDENT WHERE ID_SECTION_STUDENT = id_ss;
      RETURN cursor1;
  END SEARCH_D;
  FUNCTION LIST_D RETURN type_cursor
    IS
    cursor1 PACK_MANAGE_SECTION_STUDENTS.type_cursor;
    BEGIN
      OPEN cursor1 FOR 
      SELECT * FROM SECTION_STUDENT;
      RETURN cursor1;
  END LIST_D;
END PACK_MANAGE_SECTION_STUDENTS;

---------- CREACION DE PROCEDIMIENTOS MANTENIMIENTO ANSWERS_STUDENT -------------------
CREATE OR REPLACE PACKAGE PACK_MANAGE_ANSWERS_STUDENTS AS
  TYPE type_cursor IS REF CURSOR RETURN ANSWERS_STUDENT%ROWTYPE;
  PROCEDURE INSERT_D (id_ss ANSWERS_STUDENT.ID_SECTION_STUDENT%TYPE, ans ANSWERS_STUDENT.ANSWERS%TYPE,
  n_ques ANSWERS_STUDENT.N_QUESTION%TYPE);
  PROCEDURE UPDATE_D (id_as ANSWERS_STUDENT.ID_ANSWERS_STUDENT%TYPE, id_ss ANSWERS_STUDENT.ID_SECTION_STUDENT%TYPE,
  ans ANSWERS_STUDENT.ANSWERS%TYPE, n_ques ANSWERS_STUDENT.N_QUESTION%TYPE);
  PROCEDURE DELETE_D (id_as ANSWERS_STUDENT.ID_ANSWERS_STUDENT%TYPE);
  FUNCTION SEARCH_D (id_as ANSWERS_STUDENT.ID_ANSWERS_STUDENT%TYPE) RETURN type_cursor;
  FUNCTION LIST_D RETURN type_cursor;
END PACK_MANAGE_ANSWERS_STUDENTS;

CREATE OR REPLACE PACKAGE BODY PACK_MANAGE_ANSWERS_STUDENTS IS
  PROCEDURE INSERT_D (id_ss ANSWERS_STUDENT.ID_SECTION_STUDENT%TYPE, ans ANSWERS_STUDENT.ANSWERS%TYPE,
  n_ques ANSWERS_STUDENT.N_QUESTION%TYPE)
    IS
    BEGIN
      INSERT INTO ANSWERS_STUDENT (ID_SECTION_STUDENT, ANSWERS, N_QUESTION) VALUES (id_ss, ans, n_ques);
      commit;
      EXCEPTION
      WHEN OTHERS THEN
      rollback;
  END INSERT_D;
  PROCEDURE UPDATE_D (id_as ANSWERS_STUDENT.ID_ANSWERS_STUDENT%TYPE, id_ss ANSWERS_STUDENT.ID_SECTION_STUDENT%TYPE,
  ans ANSWERS_STUDENT.ANSWERS%TYPE, n_ques ANSWERS_STUDENT.N_QUESTION%TYPE)
    IS
    BEGIN
      UPDATE ANSWERS_STUDENT SET ID_SECTION_STUDENT = id_ss, ANSWERS = ans, N_QUESTION = n_ques 
      WHERE ID_ANSWERS_STUDENT = id_as;
      commit;
      EXCEPTION
      WHEN OTHERS THEN
      rollback;
  END UPDATE_D;
  PROCEDURE DELETE_D (id_as ANSWERS_STUDENT.ID_ANSWERS_STUDENT%TYPE)
    IS
    BEGIN
      DELETE FROM ANSWERS_STUDENT WHERE ID_ANSWERS_STUDENT = id_as;
      commit;
      EXCEPTION
      WHEN OTHERS THEN 
      rollback;
  END DELETE_D;
  FUNCTION SEARCH_D (id_as ANSWERS_STUDENT.ID_ANSWERS_STUDENT%TYPE) RETURN type_cursor
    IS
    cursor1 PACK_MANAGE_ANSWERS_STUDENTS.type_cursor;
    BEGIN
      OPEN cursor1 FOR
      SELECT * FROM ANSWERS_STUDENT WHERE ID_ANSWERS_STUDENT = id_as;
      RETURN cursor1;
  END SEARCH_D;
  FUNCTION LIST_D RETURN type_cursor
    IS
    cursor1 PACK_MANAGE_ANSWERS_STUDENTS.type_cursor;
    BEGIN
      OPEN cursor1 FOR 
      SELECT * FROM ANSWERS_STUDENT;
      RETURN cursor1;
  END LIST_D;
END PACK_MANAGE_ANSWERS_STUDENTS;

---------- CREACION DE PROCEDIMIENTOS MANTENIMIENTO NOTE -------------------
CREATE OR REPLACE PACKAGE PACK_MANAGE_NOTES AS
  TYPE type_cursor IS REF CURSOR RETURN NOTE%ROWTYPE;
  PROCEDURE INSERT_D (id_ss NOTE.ID_SECTION_STUDENT%TYPE, n_t NOTE.NOTE%TYPE);
  PROCEDURE UPDATE_D (id_n NOTE.ID_NOTE%TYPE, id_ss NOTE.ID_SECTION_STUDENT%TYPE, n_t NOTE.NOTE%TYPE);
  PROCEDURE DELETE_D (id_n NOTE.ID_NOTE%TYPE);
  FUNCTION SEARCH_D (id_n NOTE.ID_NOTE%TYPE) RETURN type_cursor;
  FUNCTION LIST_D RETURN type_cursor;
END PACK_MANAGE_NOTES;

CREATE OR REPLACE PACKAGE BODY PACK_MANAGE_NOTES IS
  PROCEDURE INSERT_D (id_ss NOTE.ID_SECTION_STUDENT%TYPE, n_t NOTE.NOTE%TYPE)
    IS
    BEGIN
      INSERT INTO NOTE (ID_SECTION_STUDENT, NOTE) VALUES (id_ss, n_t);
      commit;
      EXCEPTION
      WHEN OTHERS THEN
      rollback;
  END INSERT_D;
  PROCEDURE UPDATE_D (id_n NOTE.ID_NOTE%TYPE, id_ss NOTE.ID_SECTION_STUDENT%TYPE, n_t NOTE.NOTE%TYPE)
    IS
    BEGIN
      UPDATE NOTE SET ID_SECTION_STUDENT = id_ss, NOTE = n_t WHERE ID_NOTE = id_N;
      commit;
      EXCEPTION
      WHEN OTHERS THEN
      rollback;
  END UPDATE_D;
  PROCEDURE DELETE_D (id_n NOTE.ID_NOTE%TYPE)
    IS
    BEGIN
      DELETE FROM NOTE WHERE ID_NOTE = id_n;
      commit;
      EXCEPTION
      WHEN OTHERS THEN 
      rollback;
  END DELETE_D;
  FUNCTION SEARCH_D (id_n NOTE.ID_NOTE%TYPE) RETURN type_cursor
    IS
    cursor1 PACK_MANAGE_NOTES.type_cursor;
    BEGIN
      OPEN cursor1 FOR
      SELECT * FROM NOTE WHERE ID_NOTE = id_n;
      RETURN cursor1;
  END SEARCH_D;
  FUNCTION LIST_D RETURN type_cursor
    IS
    cursor1 PACK_MANAGE_NOTES.type_cursor;
    BEGIN
      OPEN cursor1 FOR 
      SELECT * FROM NOTE;
      RETURN cursor1;
  END LIST_D;
END PACK_MANAGE_NOTES;
