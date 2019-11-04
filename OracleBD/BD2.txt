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
  IDPERSON NUMBER GENERATED ALWAYS AS IDENTITY,
  FIRSNAME VARCHAR(20),
  LASTNAME VARCHAR(20),
  PHONE NUMBER(9),
  ADDRESS VARCHAR(40),
  EMAIL VARCHAR(30) 
);
select * from TEACHER;
DELETE FROM PERSON WHERE IDPERSON = 2;
insert INTO PERSON (firsname,lastname,phone,address,email) VALUES ('jon','can',985476214,'r','ff');
-----------------------------
-- TABLE STUDENT
-----------------------------
CREATE TABLE STUDENT(
  IDPERSON CHAR(4),
  CODESTUDENT CHAR(4),
  IDSCHOOL CHAR(4)
);

-----------------------------
-- TABLE TEACHER
-----------------------------
drop table person;
CREATE TABLE TEACHER(
  IDPERSON NUMBER,
  CODETEACHER VARCHAR(4)
);

-----------------------------
-- PRIMARY KEY
-----------------------------
ALTER TABLE PERSON ADD CONSTRAINT PK_PERSON PRIMARY KEY (IDPERSON);
ALTER TABLE TEACHER ADD CONSTRAINT PK_TEACHER PRIMARY KEY (IDPERSON);

-----------------------------
-- FOREIGN KEY
-----------------------------
ALTER TABLE TEACHER ADD FOREIGN KEY (IDPERSON) REFERENCES PERSON ON DELETE CASCADE;

--------------------------------------------------------------------------------
--CREACION DE PROCEDIMIENTOS--
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE PACK_MANT_TEACHER AS
  PROCEDURE INSERT_T (cod_te TEACHER.CODETEACHER%TYPE, firs_name PERSON.FIRSNAME%TYPE,last_name PERSON.LASTNAME%TYPE,
  pho PERSON.PHONE%TYPE, addr PERSON.ADDRESS%TYPE, ema PERSON.EMAIL%TYPE);
  PROCEDURE UPDATE_T (cod_te TEACHER.CODETEACHER%TYPE, firs_name PERSON.FIRSNAME%TYPE,last_name PERSON.LASTNAME%TYPE,
  pho PERSON.PHONE%TYPE,addr PERSON.ADDRESS%TYPE, ema PERSON.EMAIL%TYPE);
  PROCEDURE DELETE_T (cod_te TEACHER.CODETEACHER%TYPE);
END PACK_MANT_TEACHER;

CREATE OR REPLACE PACKAGE BODY PACK_MANT_TEACHER IS

  PROCEDURE INSERT_T (cod_te TEACHER.CODETEACHER%TYPE, firs_name PERSON.FIRSNAME%TYPE,last_name PERSON.LASTNAME%TYPE,
  pho PERSON.PHONE%TYPE, addr PERSON.ADDRESS%TYPE, ema PERSON.EMAIL%TYPE)
  IS
  id_person PERSON.IDPERSON%TYPE;
  BEGIN
    INSERT INTO PERSON (firsname,lastname,phone,address,email) VALUES (firs_name, last_name, pho, addr, ema);
    select idperson into id_person from PERSON WHERE PERSON.PHONE = pho;
    INSERT INTO TEACHER VALUES (id_person, cod_te);
  END INSERT_T;
  
  PROCEDURE UPDATE_T (cod_te TEACHER.CODETEACHER%TYPE, firs_name PERSON.FIRSNAME%TYPE,last_name PERSON.LASTNAME%TYPE,
  pho PERSON.PHONE%TYPE,addr PERSON.ADDRESS%TYPE, ema PERSON.EMAIL%TYPE)
  IS
  id_person PERSON.IDPERSON%TYPE;
  BEGIN
    select idperson into id_person from TEACHER WHERE CODETEACHER = cod_te;
    UPDATE PERSON SET PERSON.FIRSNAME = firs_name, PERSON.LASTNAME = last_name, PERSON.PHONE = pho,
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
    select idperson into id_person from TEACHER WHERE CODETEACHER = cod_te;
    DELETE FROM PERSON WHERE IDPERSON = id_person;
    commit;
    EXCEPTION
    WHEN OTHERS THEN 
    rollback;
  END DELETE_T;
  
END PACK_MANT_TEACHER;
--------------------------------------------------------------------------------
--CREACION DE CONSULTAS
--------------------------------------------------------------------------------
--VISTAS PROFESOR-----
CREATE VIEW LIST_TEACHER
  AS
  SELECT CODETEACHER, FIRSNAME,LASTNAME,PHONE,ADDRESS,EMAIL FROM TEACHER INNER JOIN PERSON ON PERSON.IDPERSON = TEACHER.IDPERSON;

--CONSULTAS Y LISTADO DE DATOS
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
    SELECT CODETEACHER, FIRSNAME,LASTNAME,PHONE,ADDRESS,EMAIL FROM TEACHER INNER JOIN PERSON ON 
    PERSON.IDPERSON = TEACHER.IDPERSON AND TEACHER.CODETEACHER = code_teacher;
  END GET_TEACHER;
  
  PROCEDURE GET_TEACHERS(cursor1 OUT PACK_LIST_TEACHER.type_cursor)
  AS
  BEGIN
    OPEN cursor1 FOR 
    SELECT * FROM LIST_TEACHER;
  END GET_TEACHERS;
END PACK_LIST_TEACHER;
