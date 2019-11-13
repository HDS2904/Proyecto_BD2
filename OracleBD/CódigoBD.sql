--------------------------------------------------------------------------------
--TABLESPACE
--------------------------------------------------------------------------------
create tablespace user_personal
 datafile 'C:\app\oracle\oradata\orcl\user_personal.dbf'
 size 100m autoextend on next 10m maxsize 200m
 extent management local
 segment space management auto
 logging;
--------------------------------------------------------------------------------
--TEMPORARY
--------------------------------------------------------------------------------
 create temporary tablespace data2
  tempfile 'd:/app/oracle/oradata/orcl/asdsa.dbf'
  size 200mb
  extent management local;
--------------------------------------------------------------------------------
--############################################################################
-----------------------------
-- TABLE PERSON
-----------------------------
CREATE TABLE PERSON(
  IDPERSON NUMBER(4) generated always as identity,
  FIRSNAME VARCHAR2(30),
  LASTNAME VARCHAR2(30),
  DNI NUMBER(8),
  PHONE NUMBER(9),
  ADDRESS VARCHAR2(200),
  EMAIL VARCHAR2(200) 
);
-----------------------------
-- TABLE TEACHER
-----------------------------
CREATE TABLE TEACHER(
  IDTEACHER NUMBER(4),
  CODETEACHER CHARACTER(8)
);
-----------------------------
-- TABLE STUDENT
-----------------------------
CREATE TABLE STUDENT(
  IDSTUDENT NUMBER(4),
  CODESTUDENT CHARACTER(8),
  IDSCHOOL NUMBER(4)
);
-----------------------------
-- PRIMARY KEY
-----------------------------
ALTER TABLE PERSON ADD CONSTRAINT PK_PERSON PRIMARY KEY (IDPERSON);
ALTER TABLE TEACHER ADD CONSTRAINT PK_TEACHER PRIMARY KEY (IDTEACHER);

ALTER TABLE STUDENT ADD CONSTRAINT PK_STUDENT PRIMARY KEY (IDSTUDENT);
-----------------------------
-- FOREIGN KEY
-----------------------------
ALTER TABLE TEACHER ADD FOREIGN KEY (IDTEACHER) REFERENCES PERSON ON DELETE CASCADE;

ALTER TABLE STUDENT ADD FOREIGN KEY (IDSTUDENT) REFERENCES PERSON ON DELETE CASCADE;

--------------------------------------------------------------------------------
--CREACION DE VISTAS--
--------------------------------------------------------------------------------
  --VISTAS TEACHER-----
CREATE VIEW LIST_TEACHER
  AS
  SELECT CODETEACHER,FIRSNAME,LASTNAME,DNI,PHONE,ADDRESS,EMAIL FROM TEACHER INNER JOIN PERSON ON PERSON.IDPERSON = TEACHER.IDTEACHER;
--VISTAS STUDENT-----
CREATE VIEW LIST_STUDENT
  AS
  SELECT CODESTUDENT,FIRSNAME,LASTNAME,DNI,PHONE,ADDRESS,EMAIL FROM STUDENT INNER JOIN PERSON ON PERSON.IDPERSON = STUDENT.IDSTUDENT;
--------------------------------------------------------------------------------
--CREACION DE PROCEDIMIENTOS ANTENIMIENTO TEACHER--
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE PACK_MANT_TEACHER AS
  PROCEDURE INSERT_T (cod_te TEACHER.CODETEACHER%TYPE, firs_name PERSON.FIRSNAME%TYPE,last_name PERSON.LASTNAME%TYPE, 
  dni_per PERSON.DNI%TYPE, pho PERSON.PHONE%TYPE, addr PERSON.ADDRESS%TYPE, ema PERSON.EMAIL%TYPE);
  PROCEDURE UPDATE_T (cod_te TEACHER.CODETEACHER%TYPE, firs_name PERSON.FIRSNAME%TYPE,last_name PERSON.LASTNAME%TYPE, 
  dni_per PERSON.DNI%TYPE, pho PERSON.PHONE%TYPE,addr PERSON.ADDRESS%TYPE, ema PERSON.EMAIL%TYPE);
  PROCEDURE DELETE_T (cod_te TEACHER.CODETEACHER%TYPE);
END PACK_MANT_TEACHER;

CREATE OR REPLACE PACKAGE BODY PACK_MANT_TEACHER IS

  PROCEDURE INSERT_T (cod_te TEACHER.CODETEACHER%TYPE, firs_name PERSON.FIRSNAME%TYPE,last_name PERSON.LASTNAME%TYPE,
  dni_per PERSON.DNI%TYPE, pho PERSON.PHONE%TYPE, addr PERSON.ADDRESS%TYPE, ema PERSON.EMAIL%TYPE)
  IS
  id_teacher TEACHER.IDTEACHER%TYPE;
  BEGIN
    INSERT INTO PERSON (FIRSNAME, LASTNAME, DNI, PHONE, ADDRESS, EMAIL) VALUES (firs_name, last_name, dni_per, pho, addr, ema);
    SELECT IDPERSON INTO id_teacher FROM PERSON WHERE DNI = dni_per;
    INSERT INTO TEACHER (IDTEACHER, CODETEACHER) VALUES (id_teacher, cod_te);
  END INSERT_T;
  
  PROCEDURE UPDATE_T (cod_te TEACHER.CODETEACHER%TYPE, firs_name PERSON.FIRSNAME%TYPE,last_name PERSON.LASTNAME%TYPE,
  dni_per PERSON.DNI%TYPE, pho PERSON.PHONE%TYPE,addr PERSON.ADDRESS%TYPE, ema PERSON.EMAIL%TYPE)
  IS
  id_person PERSON.IDPERSON%TYPE;
  BEGIN
    SELECT IDTEACHER INTO id_person FROM TEACHER WHERE CODETEACHER = cod_te;
    UPDATE PERSON SET PERSON.FIRSNAME = firs_name, PERSON.LASTNAME = last_name, PERSON.DNI = dni_per, PERSON.PHONE = pho,
    PERSON.ADDRESS = addr, PERSON.EMAIL = ema
    where IDPERSON = id_person;
    commit;
    EXCEPTION
    WHEN OTHERS THEN
    rollback;
  END UPDATE_T;
  
  PROCEDURE DELETE_T (cod_te TEACHER.CODETEACHER%TYPE)
  IS
  id_person PERSON.IDPERSON%TYPE;
  BEGIN
    SELECT IDTEACHER INTO id_person FROM TEACHER WHERE CODETEACHER = cod_te;
    DELETE FROM PERSON WHERE IDPERSON = id_person;
    commit;
    EXCEPTION
    WHEN OTHERS THEN 
    rollback;
  END DELETE_T;
  
END PACK_MANT_TEACHER;
--------------------------------------------------------------------------------
--CREACION DE PROCEDIMIENTO LISTADO TEACHER
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE PACK_LIST_TEACHER AS
  TYPE type_cursor IS REF CURSOR RETURN LIST_TEACHER%ROWTYPE;
  PROCEDURE GET_TEACHER (code_teacher IN TEACHER.CODETEACHER%TYPE, cursor1 OUT PACK_LIST_TEACHER.type_cursor);
  PROCEDURE GET_TEACHERS(cursor1 OUT PACK_LIST_TEACHER.type_cursor);
END PACK_LIST_TEACHER;

CREATE OR REPLACE PACKAGE BODY PACK_LIST_TEACHER IS

  PROCEDURE GET_TEACHER (code_teacher IN TEACHER.CODETEACHER%TYPE, cursor1 OUT PACK_LIST_TEACHER.type_cursor)
  AS
  BEGIN
    OPEN cursor1 FOR
    SELECT * FROM LIST_TEACHER WHERE CODETEACHER = code_teacher;
  END GET_TEACHER;
  
  PROCEDURE GET_TEACHERS(cursor1 OUT PACK_LIST_TEACHER.type_cursor)
  AS
  BEGIN
    OPEN cursor1 FOR 
    SELECT * FROM LIST_TEACHER;
  END GET_TEACHERS;
END PACK_LIST_TEACHER;
--=========================================================
--------------------------------------------------------------------------------
--CREACION DE PROCEDIMIENTOS MANTENIMIENTO STUDENT--
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE PACK_MANT_STUDENT AS
  PROCEDURE INSERT_E (cod_es STUDENT.CODESTUDENT%TYPE, firs_name PERSON.FIRSNAME%TYPE,last_name PERSON.LASTNAME%TYPE, 
  dni_per PERSON.DNI%TYPE, pho PERSON.PHONE%TYPE, addr PERSON.ADDRESS%TYPE, ema PERSON.EMAIL%TYPE);
  PROCEDURE UPDATE_E (cod_es STUDENT.CODESTUDENT%TYPE, firs_name PERSON.FIRSNAME%TYPE,last_name PERSON.LASTNAME%TYPE, 
  dni_per PERSON.DNI%TYPE, pho PERSON.PHONE%TYPE,addr PERSON.ADDRESS%TYPE, ema PERSON.EMAIL%TYPE);
  PROCEDURE DELETE_E (cod_es STUDENT.CODESTUDENT%TYPE);
END PACK_MANT_STUDENT;

CREATE OR REPLACE PACKAGE BODY PACK_MANT_STUDENT IS

  PROCEDURE INSERT_S (cod_st STUDENT.CODESTUDENT%TYPE, firs_name PERSON.FIRSNAME%TYPE,last_name PERSON.LASTNAME%TYPE,
  dni_per PERSON.DNI%TYPE, pho PERSON.PHONE%TYPE, addr PERSON.ADDRESS%TYPE, ema PERSON.EMAIL%TYPE)
  IS
  id_student STUDENT.IDSTUDENT%TYPE;
  BEGIN
    INSERT INTO PERSON (FIRSNAME, LASTNAME, DNI, PHONE, ADDRESS, EMAIL) VALUES (firs_name, last_name, dni_per, pho, addr, ema);
    SELECT IDPERSON INTO id_student FROM PERSON WHERE DNI = dni_per;
    INSERT INTO STUDENT (IDSTUDENT, CODESTUDENT) VALUES (id_student, cod_st);
  END INSERT_S;
  
  PROCEDURE UPDATE_S (cod_st STUDENT.CODESTUDENT%TYPE, firs_name PERSON.FIRSNAME%TYPE,last_name PERSON.LASTNAME%TYPE,
  dni_per PERSON.DNI%TYPE, pho PERSON.PHONE%TYPE,addr PERSON.ADDRESS%TYPE, ema PERSON.EMAIL%TYPE)
  IS
  id_person PERSON.IDPERSON%TYPE;
  BEGIN
    SELECT IDSTUDENT INTO id_person FROM STUDENT WHERE CODESTUDENT = cod_st;
    UPDATE PERSON SET PERSON.FIRSNAME = firs_name, PERSON.LASTNAME = last_name, PERSON.DNI = dni_per, PERSON.PHONE = pho,
    PERSON.ADDRESS = addr, PERSON.EMAIL = ema
    where IDPERSON = id_person;
    commit;
    EXCEPTION
    WHEN OTHERS THEN
    rollback;
  END UPDATE_S;
  
  PROCEDURE DELETE_S (cod_st STUDENT.CODESTUDENT%TYPE)
  IS
  id_person PERSON.IDPERSON%TYPE;
  BEGIN
    SELECT IDSTUDENT INTO id_person FROM STUDENT WHERE CODSTUDENT = cod_st;
    DELETE FROM PERSON WHERE IDPERSON = id_person;
    commit;
    EXCEPTION
    WHEN OTHERS THEN 
    rollback;
  END DELETE_S;
  
END PACK_MANT_STUDENT;
--------------------------------------------------------------------------------
--CREACION DE LISTADO STUDENT
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE PACK_LIST_STUDENT AS
  TYPE type_cursor IS REF CURSOR RETURN LIST_STUDENT%ROWTYPE;
  PROCEDURE GET_STUDENT (cod_st IN STUDENT.CODESTUDENT%TYPE, cursor1 OUT PACK_LIST_STUDENT.type_cursor);
  PROCEDURE GET_STUDENTS(cursor1 OUT PACK_LIST_STUDENT.type_cursor);
END PACK_LIST_STUDENT;

CREATE OR REPLACE PACKAGE BODY PACK_LIST_STUDENT IS

  PROCEDURE GET_STUDENT (cod_st IN STUDENT.CODESTUDENT%TYPE, cursor1 OUT PACK_LIST_STUDENT.type_cursor)
  AS
  BEGIN
    OPEN cursor1 FOR
    SELECT * FROM LIST_STUDENT WHERE CODESTUDENT = code_st;
  END GET_STUDENT;
  
  PROCEDURE GET_STUDENT(cursor1 OUT PACK_LIST_STUDENT.type_cursor)
  AS
  BEGIN
    OPEN cursor1 FOR 
    SELECT * FROM LIST_STUDENT;
  END GET_STUDENT;
END PACK_LIST_STUDENT;
--=========================================================
--#################SUBJECT################################
--=========================================================
CREATE TABLE SUBJECT (
  ID_SUBJECT	NUMBER(4) GENERATED ALWAYS AS IDENTITY ,
  NAMESUBJECT	VARCHAR2(15)
);

ALTER TABLE SUBJECT ADD CONSTRAINT SUBJECT_PK PRIMARY KEY (ID_SUBJECT);

--VISTA SUBJECT--
CREATE VIEW LIST_SUBJECT AS
SELECT * FROM SUBJECT;

--------------------------------------------------------------------------------
--CREACION DE PROCEDIMIENTOS MANTENIMIENTO SUBJECT--
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE PACK_MANT_SUBJECT AS
  PROCEDURE INSERT_SU (sub_name SUBJECT.NAMESUBJECT%TYPE);
  PROCEDURE UPDATE_SU (sub_id SUBJECT.ID_SUBJECT%TYPE, sub_name SUBJECT.NAMESUBJECT%TYPE);
  PROCEDURE DELETE_SU (sub_id SUBJECT.ID_SUBJECT%TYPE);
END PACK_MANT_SUBJECT;

CREATE OR REPLACE PACKAGE BODY PACK_MANT_SUBJECT IS

  PROCEDURE INSERT_SU (sub_name SUBJECT.NAMESUBJECT%TYPE)
  IS
  BEGIN
    INSERT INTO SUBJECT (NAMESUBJECT) VALUES (sub_name);
  END INSERT_SU;
  
  PROCEDURE UPDATE_SU (sub_id SUBJECT.ID_SUBJECT%TYPE, sub_name SUBJECT.NAMESUBJECT%TYPE)
  IS
  BEGIN
    UPDATE SUBJECT SET NAMESUBJECT = sub_name
    where ID_SUBJECT = sub_id;
    commit;
    EXCEPTION
    WHEN OTHERS THEN
    rollback;
  END UPDATE_SU;
  
  PROCEDURE DELETE_SU (sub_id SUBJECT.ID_SUBJECT%TYPE)
  IS
  BEGIN
    DELETE FROM SUBJECT WHERE ID_SUBJECT = sub_id;
    commit;
    EXCEPTION
    WHEN OTHERS THEN 
    rollback;
  END DELETE_SU;
  
END PACK_MANT_SUBJECT;
--------------------------------------------------------------------------------
--CREACION DE LISTADO STUDENT
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE PACK_LIST_SUBJECT AS
  TYPE type_cursor IS REF CURSOR RETURN LIST_SUBJECT%ROWTYPE;
  PROCEDURE GET_SUBJECT (sub_id SUBJECT.ID_SUBJECT%TYPE, cursor1 OUT PACK_LIST_SUBJECT.type_cursor);
  PROCEDURE GET_SUBJECTS (cursor1 OUT PACK_LIST_SUBJECT.type_cursor);
END PACK_LIST_SUBJECT;

CREATE OR REPLACE PACKAGE BODY PACK_LIST_SUBJECT IS

  PROCEDURE GET_SUBJECT (sub_id SUBJECT.ID_SUBJECT%TYPE, cursor1 OUT PACK_LIST_SUBJECT.type_cursor)
  AS
  BEGIN
    OPEN cursor1 FOR
    SELECT * FROM LIST_SUBJECT WHERE ID_SUBJECT = sub_id;
  END GET_SUBJECT;
  
  PROCEDURE GET_SUBJECTS (cursor1 OUT PACK_LIST_SUBJECT.type_cursor)
  AS
  BEGIN
    OPEN cursor1 FOR 
    SELECT * FROM LIST_SUBJECT;
  END GET_SUBJECTS;
END PACK_LIST_SUBJECT;

