-------------------------------------------------------------------------------
-- TABLES DE ENTIDADES
--------------------------------------------------------------------------------
CREATE TABLE PERSON(
  ID_PERSON NUMBER(4) GENERATED ALWAYS AS IDENTITY,
  FIRST_NAME VARCHAR(30),
  LAST_NAME VARCHAR(30),
  DNI NUMBER(8),
  PHONE NUMBER(9),
  ADDRESS VARCHAR(200),
  EMAIL VARCHAR(200) 
);

CREATE TABLE STUDENT(
  ID_PERSON NUMBER(4),
  CODE_STUDENT CHARACTER(8),
  ID_SCHOOL NUMBER(4)
);

CREATE TABLE TEACHER(
  ID_PERSON NUMBER(4),
  CODE_TEACHER CHARACTER(8)
);

CREATE TABLE DIRECTOR(
  ID_PERSON NUMBER(4),
  CODE_DIRECTOR CHARACTER(8),
  ID_FACULTY NUMBER(4)
);

CREATE TABLE SCHOOL(
    ID_SCHOOL NUMBER(4) GENERATED ALWAYS AS IDENTITY,
    ID_FACULTY NUMBER(4),
    NAME_SCHOOL VARCHAR(50)
);

CREATE TABLE FACULTY(
  ID_FACULTY NUMBER(4) GENERATED ALWAYS AS IDENTITY,
  ID_PERSON NUMBER(4),
  NAME_FACULTY VARCHAR2(50)
);



CREATE TABLE SECTION(
  ID_SECTION NUMBER(4) GENERATED ALWAYS AS IDENTITY,
  ID_SUBJECT NUMBER(4),
  ID_PERSON NUMBER(4),
  ID_EXAM NUMBER(4),
  SECTION_GROUP VARCHAR2(20)
);

CREATE TABLE SUBJECT (
  ID_SUBJECT	NUMBER(4) GENERATED ALWAYS AS IDENTITY ,
  NAME_SUBJECT	VARCHAR2(100)
);

CREATE TABLE THEME (
  ID_THEME NUMBER(4) GENERATED ALWAYS AS IDENTITY,
  ID_SUBJECT NUMBER(4),
  NAME_THEME VARCHAR2(100) 
);

CREATE TABLE QUESTION (
  ID_QUESTION NUMBER (4) GENERATED ALWAYS AS IDENTITY,
  ID_THEME NUMBER(4),
  QUESTION VARCHAR2(500),
  SCORE NUMBER(2)
);

CREATE TABLE ALTERNATIVE(
  ID_ALTERNATIVE NUMBER(4) GENERATED ALWAYS AS IDENTITY,
  ID_QUESTION NUMBER(4),
  ALTERNATIVE_A VARCHAR2(500),
  ALTERNATIVE_B VARCHAR2(500),
  ALTERNATIVE_C VARCHAR2(500),
  ANSWER VARCHAR2(10)
);


CREATE TABLE EXAM(
  ID_EXAM NUMBER(4) GENERATED ALWAYS AS IDENTITY,
  ID_SECTION NUMBER(4)
);

CREATE TABLE QUESTION_EXAM(
  ID_QUESTION_EXAM NUMBER(4) GENERATED ALWAYS AS IDENTITY,
  ID_EXAM NUMBER(4),
  QUESTION VARCHAR2(500),
  N_QUESTION NUMBER(2)
);

CREATE TABLE ALTERNATIVE_EXAM(
  ID_ALTERNATIVE_EXAM NUMBER(4) GENERATED ALWAYS AS IDENTITY,
  ID_QUESTION_EXAM NUMBER(4),
  ALTERNATIVE_A VARCHAR2(500),
  ALTERNATIVE_B VARCHAR2(500),
  ALTERNATIVE_C VARCHAR2(500)
);


CREATE TABLE SECTION_STUDENT (
  ID_SECTION_STUDENT NUMBER(4) GENERATED ALWAYS AS IDENTITY,
  ID_SECTION NUMBER(4),
  ID_PERSON NUMBER(4)
);

CREATE TABLE ANSWERS_STUDENT(
  ID_ANSWERS_STUDENT NUMBER(4) GENERATED ALWAYS AS IDENTITY,
  ID_SECTION_STUDENT NUMBER(4),
  ANSWERS CHAR(15),
  N_QUESTION NUMBER(2)
);

CREATE TABLE NOTE(
  ID_NOTE NUMBER(4) GENERATED ALWAYS AS IDENTITY,
  ID_SECTION_STUDENT NUMBER(4),
  NOTE NUMBER(2,1)
);


--------------------------------------------------------------------------------
-- PRIMARY KEY
--------------------------------------------------------------------------------
ALTER TABLE PERSON ADD CONSTRAINT PERSON_PK PRIMARY KEY (ID_PERSON);
ALTER TABLE TEACHER ADD CONSTRAINT TEACHER_PK PRIMARY KEY (ID_PERSON);
ALTER TABLE STUDENT ADD CONSTRAINT PSTUDENT_PK PRIMARY KEY (ID_PERSON);
ALTER TABLE DIRECTOR ADD CONSTRAINT DIRECTOR_PK PRIMARY KEY (ID_PERSON);
ALTER TABLE FACULTY ADD CONSTRAINT FACULTY_PK PRIMARY KEY (ID_FACULTY);
ALTER TABLE SCHOOL ADD CONSTRAINT SCHOOL_PK PRIMARY KEY (ID_SCHOOL);

ALTER TABLE SECTION ADD CONSTRAINT SECTION_PK PRIMARY KEY (ID_SECTION);
ALTER TABLE SUBJECT ADD CONSTRAINT SUBJECT_PK PRIMARY KEY (ID_SUBJECT);
ALTER TABLE THEME ADD CONSTRAINT THEME_PK PRIMARY KEY (ID_THEME);
ALTER TABLE QUESTION ADD CONSTRAINT QUESTION_PK PRIMARY KEY(ID_QUESTION);
ALTER TABLE ALTERNATIVE ADD CONSTRAINT ALTERNATIVE_PK PRIMARY KEY (ID_ALTERNATIVE);

ALTER TABLE EXAM ADD CONSTRAINT EXAM_PK PRIMARY KEY (ID_EXAM);
ALTER TABLE QUESTION_EXAM ADD CONSTRAINT QUESTION_EXAM_PK PRIMARY KEY (ID_QUESTION_EXAM);
ALTER TABLE ALTERNATIVE_EXAM ADD CONSTRAINT ALTERNATIVE_EXAM_PK PRIMARY KEY (ID_ALTERNATIVE_EXAM);

ALTER TABLE SECTION_STUDENT ADD CONSTRAINT SECTION_STUDENT_PK PRIMARY KEY (ID_SECTION_STUDENT);
ALTER TABLE ANSWERS_STUDENT ADD CONSTRAINT ANSWERS_STUDENT_PK PRIMARY KEY (ID_ANSWERS_STUDENT);
ALTER TABLE NOTE ADD CONSTRAINT NOTE_PK PRIMARY KEY (ID_NOTE);


--------------------------------------------------------------------------------
-- FOREIGN KEY
--------------------------------------------------------------------------------
ALTER TABLE TEACHER ADD CONSTRAINT TEACHER_FK FOREIGN KEY (ID_PERSON) REFERENCES PERSON ON DELETE CASCADE;
ALTER TABLE STUDENT ADD CONSTRAINT STUDENT_1FK FOREIGN KEY (ID_PERSON) REFERENCES PERSON ON DELETE CASCADE;
ALTER TABLE STUDENT ADD CONSTRAINT STUDENT_2FK FOREIGN KEY (ID_SCHOOL) REFERENCES SCHOOL;
ALTER TABLE DIRECTOR ADD CONSTRAINT DIRECTOR_1FK FOREIGN KEY (ID_PERSON) REFERENCES PERSON ON DELETE CASCADE;
ALTER TABLE DIRECTOR ADD CONSTRAINT DIRECTOR_2FK FOREIGN KEY (ID_FACULTY) REFERENCES FACULTY;
ALTER TABLE FACULTY ADD CONSTRAINT FACULTY_FK FOREIGN KEY (ID_PERSON) REFERENCES DIRECTOR;
ALTER TABLE SCHOOL ADD CONSTRAINT SCHOOL_FK FOREIGN KEY (ID_FACULTY) REFERENCES FACULTY;

ALTER TABLE SECTION ADD CONSTRAINT SECTION_1FK FOREIGN KEY (ID_SUBJECT) REFERENCES SUBJECT;
ALTER TABLE SECTION ADD CONSTRAINT SECTION_2FK FOREIGN KEY (ID_PERSON) REFERENCES TEACHER;
ALTER TABLE THEME ADD CONSTRAINT THEME_FK FOREIGN KEY (ID_SUBJECT) REFERENCES SUBJECT;
ALTER TABLE QUESTION ADD CONSTRAINT QUESTION_FK FOREIGN KEY(ID_THEME) REFERENCES THEME;
ALTER TABLE ALTERNATIVE ADD CONSTRAINT ALTERNATIVE_FK FOREIGN KEY(ID_QUESTION) REFERENCES QUESTION;

ALTER TABLE EXAM ADD CONSTRAINT EXAM_FK FOREIGN KEY (ID_SECTION) REFERENCES SECTION;
ALTER TABLE QUESTION_EXAM ADD CONSTRAINT QUESTION_EXAM_FK FOREIGN KEY (ID_EXAM) REFERENCES EXAM;
ALTER TABLE ALTERNATIVE_EXAM ADD CONSTRAINT ALTERNATIVE_EXAM_FK FOREIGN KEY (ID_QUESTION_EXAM) REFERENCES QUESTION_EXAM;

ALTER TABLE SECTION_STUDENT ADD CONSTRAINT SECTION_ESTUDENT_1FK FOREIGN KEY (ID_SECTION) REFERENCES SECTION;
ALTER TABLE SECTION_STUDENT ADD CONSTRAINT SECTION_ESTUDENT_2FK FOREIGN KEY (ID_PERSON) REFERENCES STUDENT;
ALTER TABLE ANSWERS_STUDENT ADD CONSTRAINT ANSWERS_STUDENT_FK FOREIGN KEY (ID_SECTION_STUDENT) REFERENCES SECTION_STUDENT;
ALTER TABLE NOTE ADD CONSTRAINT NOTE_FK FOREIGN KEY (ID_SECTION_STUDENT) REFERENCES SECTION_STUDENT;

--------------------------------------------------------------------------------
-- UNICIDADE DE ATRIBUTO
--------------------------------------------------------------------------------
ALTER TABLE TEACHER MODIFY (CODE_TEACHER UNIQUE);
ALTER TABLE STUDENT MODIFY (CODE_STUDENT UNIQUE);
ALTER TABLE DIRECTOR MODIFY (CODE_DIRECTOR UNIQUE);

--------------------------------------------------------------------------------
-- NO NULO
--------------------------------------------------------------------------------
ALTER TABLE PERSON MODIFY (FIRST_NAME NOT NULL ENABLE);
ALTER TABLE PERSON MODIFY (LAST_NAME NOT NULL ENABLE);
ALTER TABLE PERSON MODIFY (DNI NOT NULL ENABLE);
ALTER TABLE STUDENT MODIFY (CODE_STUDENT NOT NULL ENABLE);
ALTER TABLE TEACHER MODIFY (CODE_TEACHER NOT NULL ENABLE);
ALTER TABLE DIRECTOR MODIFY (CODE_DIRECTOR NOT NULL ENABLE);
ALTER TABLE FACULTY MODIFY (NAME_FACULTY NOT NULL ENABLE);
ALTER TABLE SCHOOL MODIFY (NAME_SCHOOL NOT NULL ENABLE);

ALTER TABLE THEME MODIFY (NAME_THEME NOT NULL ENABLE);
ALTER TABLE QUESTION MODIFY (SCORE NOT NULL ENABLE);
ALTER TABLE QUESTION MODIFY (QUESTION NOT NULL ENABLE);
ALTER TABLE ALTERNATIVE MODIFY (ALTERNATIVE_A NOT NULL ENABLE);
ALTER TABLE ALTERNATIVE MODIFY (ALTERNATIVE_B NOT NULL ENABLE);
ALTER TABLE ALTERNATIVE MODIFY (ALTERNATIVE_C NOT NULL ENABLE);
ALTER TABLE ALTERNATIVE MODIFY (ANSWER NOT NULL ENABLE);

ALTER TABLE ANSWERS_STUDENT MODIFY (ANSWERS NOT NULL ENABLE);
ALTER TABLE ANSWERS_STUDENT MODIFY (N_QUESTION NOT NULL ENABLE);

--------------------------------------------------------------------------------
-- CHECK
--------------------------------------------------------------------------------
ALTER TABLE PERSON ADD CONSTRAINT limit_dni CHECK (DNI BETWEEN 10000000 AND 99999999);
ALTER TABLE PERSON ADD CONSTRAINT limit_phone CHECK (PHONE BETWEEN 100000000 AND 999999999);
ALTER TABLE PERSON ADD CONSTRAINT email_obli CHECK (REGEXP_LIKE(EMAIL, '@unmsm.edu.pe$')) enable novalidate;
ALTER TABLE TEACHER ADD CONSTRAINT range_code CHECK (REGEXP_LIKE(CODE_TEACHER, 
'[0-9|a-z|A-Z][0-9|a-z|A-Z][0-9|a-z|A-Z][0-9|a-z|A-Z][0-9|a-z|A-Z][0-9|a-z|A-Z][0-9|a-z|A-Z][0-9|a-z|A-Z]')) enable novalidate;
ALTER TABLE QUESTION ADD CONSTRAINT RANGO_SCORE CHECK (SCORE BETWEEN 0 AND 20);
ALTER TABLE ANSWERS_STUDENT ADD CONSTRAINT ANSWERS_STUDENT CHECK (N_QUESTION BETWEEN 0 AND 10);

--------------------------------------------------------------------------------
-- VISTAS
--------------------------------------------------------------------------------
CREATE VIEW VIEW_TEACHER 
  AS
  SELECT PERS.ID_PERSON, TEA.CODE_TEACHER, PERS.FIRST_NAME, PERS.LAST_NAME, PERS.DNI, PERS.PHONE, PERS.ADDRESS, PERS.EMAIL 
  FROM TEACHER TEA, PERSON PERS WHERE TEA.ID_PERSON = PERS.ID_PERSON ORDER BY PERS.ID_PERSON;

CREATE VIEW VIEW_STUDENT 
  AS
  SELECT PERS.ID_PERSON, ST.CODE_STUDENT, PERS.FIRST_NAME, PERS.LAST_NAME, PERS.DNI, PERS.PHONE, PERS.ADDRESS, PERS.EMAIL 
  FROM STUDENT ST, PERSON PERS WHERE ST.ID_PERSON = PERS.ID_PERSON;
  
CREATE VIEW VIEW_DIRECTOR
  AS
  SELECT PERS.ID_PERSON, DT.CODE_DIRECTOR, PERS.FIRST_NAME, PERS.LAST_NAME, PERS.DNI, PERS.PHONE, PERS.ADDRESS, PERS.EMAIL 
  FROM DIRECTOR DT, PERSON PERS WHERE DT.ID_PERSON = PERS.ID_PERSON;
  
CREATE VIEW VIEW_SECTION_SUBJECT
  AS
  SELECT SC.ID_SECTION, SC.SECTION_GROUP, SB.NAME_SUBJECT
  FROM SECTION SC, SUBJECT SB, PERSON PERS WHERE SC.ID_PERSON = PERS.ID_PERSON AND SC.ID_SUBJECT = SB.ID_SUBJECT;

                                                                 
                                                                 
--------------------------------------------------------------------------------
-- INSERTS --
--------------------------------------------------------------------------------
INSERT INTO PERSON (FIRST_NAME, LAST_NAME, DNI, PHONE, ADDRESS, EMAIL) VALUES ('Jaime Jhon','Cordova Sanchez',10000000,123456789,'jesus maria 175','director@unmsm.edu.pe');
INSERT INTO FACULTY (ID_PERSON, NAME_FACULTY) VALUES (null,'ingenieria de sistemas');
insert into director (ID_PERSON, CODE_DIRECTOR, ID_FACULTY)values(43,'10000000',34);

INSERT INTO PERSON (dni, FIRST_NAME, LAST_NAME, PHONE, ADDRESS, EMAIL) VALUES ('71548925', 'Jose', 'Perez Martinez', 958123648, 'Av Peru cdra 21', 'joseperez@gmail.com');
INSERT INTO PERSON (dni, FIRST_NAME, LAST_NAME, PHONE, ADDRESS, EMAIL) VALUES ('71546768', 'Miguel', 'Mejia Acevedo', 943211238, 'Av Peru cdra 15', 'miguelmejia@gmail.com');
INSERT INTO PERSON (dni, FIRST_NAME, LAST_NAME, PHONE, ADDRESS, EMAIL) VALUES ('74564456', 'Carolina', 'Alejo Guerrero', 945645645, 'Av Universitaria cdra 11', 'carolinaalejo@gmail.com');
INSERT INTO PERSON (dni, FIRST_NAME, LAST_NAME, PHONE, ADDRESS, EMAIL) VALUES ('74564578', 'Pedro', 'Andrade Bujanda', 945645677, 'Av Brasil cdra 3', 'pedroandrade@gmail.com');
INSERT INTO PERSON (dni, FIRST_NAME, LAST_NAME, PHONE, ADDRESS, EMAIL) VALUES ('71231212', 'Juan', 'Aremnta Romero', 921312321, 'Av Bolivar cdra 6', 'juanaremnta@gmail.com');
INSERT INTO PERSON (dni, FIRST_NAME, LAST_NAME, PHONE, ADDRESS, EMAIL) VALUES ('73487624', 'Enrique', 'Bernabe Palomino', 978678654, 'Av Bolivar cdra 9', 'enriquebernabe@gmail.com');
INSERT INTO PERSON (dni, FIRST_NAME, LAST_NAME, PHONE, ADDRESS, EMAIL) VALUES ('77897864', 'Angel', 'Ramos Gutierrez', 912345642, 'Av Colonial cdra 7', 'angelramos@gmail.com');
INSERT INTO PERSON (dni, FIRST_NAME, LAST_NAME, PHONE, ADDRESS, EMAIL) VALUES ('71231234', 'German', 'Arias Rodrigez', 932134521, 'Av Marina cdra 10', 'germanarias@gmail.com');
INSERT INTO PERSON (dni, FIRST_NAME, LAST_NAME, PHONE, ADDRESS, EMAIL) VALUES ('79789878', 'Ruben', 'Quispe Quispe', 912345645, 'Av Marina cdra 1', 'rubenquispe@gmail.com');
INSERT INTO PERSON (dni, FIRST_NAME, LAST_NAME, PHONE, ADDRESS, EMAIL) VALUES ('74678652', 'Fernando', 'Cardenas Alva', 978967865, 'Av Sucre cdra 8', 'fernandocardenas@gmail.com');
INSERT INTO PERSON (dni, FIRST_NAME, LAST_NAME, PHONE, ADDRESS, EMAIL) VALUES ('73213132', 'Alberto', 'Gonzales Carrasco', 945645312, 'Av La Mar cdra 7', 'albertogonzales@gmail.com');
INSERT INTO PERSON (dni, FIRST_NAME, LAST_NAME, PHONE, ADDRESS, EMAIL) VALUES ('72231230', 'Diana', 'Tavara Pineda', 978987632, 'Av Peru cdra 9', 'dianatavara@gmail.com');
INSERT INTO PERSON (dni, FIRST_NAME, LAST_NAME, PHONE, ADDRESS, EMAIL) VALUES ('70213154', 'Maria', 'Bentacur Taborda', 912345652, 'Av Bolivar cdra 12', 'mariabentacur@gmail.com');
INSERT INTO PERSON (dni, FIRST_NAME, LAST_NAME, PHONE, ADDRESS, EMAIL) VALUES ('77531312', 'Rubi', 'Gomez Villa', 987645343, 'Av Colonial cdra 4', 'rubigomez@gmail.com');
INSERT INTO PERSON (dni, FIRST_NAME, LAST_NAME, PHONE, ADDRESS, EMAIL) VALUES ('79876786', 'Luis', 'Garcia Rua', 902464356, 'Av La Paz cdra 6', 'luisgarcia@gmail.com');


INSERT INTO DIRECTOR (id_Person, code_Director) VALUES (1, '6786');
INSERT INTO DIRECTOR (id_Person, code_Director) VALUES (4, '1354');
INSERT INTO DIRECTOR (id_Person, code_Director) VALUES (3, '3556');
INSERT INTO DIRECTOR (id_Person, code_Director) VALUES (5, '2794');
INSERT INTO DIRECTOR (id_Person, code_Director) VALUES (2, '1235');


INSERT INTO TEACHER (id_Person, code_Teacher) VALUES (11, '2315');
INSERT INTO TEACHER (id_Person, code_Teacher) VALUES (13, '3135');
INSERT INTO TEACHER (id_Person, code_Teacher) VALUES (15, '9795');
INSERT INTO TEACHER (id_Person, code_Teacher) VALUES (14, '1315');
INSERT INTO TEACHER (id_Person, code_Teacher) VALUES (12, '7815');



INSERT INTO FACULTY (id_Person, name_Faculty) VALUES (1, 'Facultad de Ssitemas y Software');
INSERT INTO FACULTY (id_Person, name_Faculty) VALUES (3, 'Facultad de Electronia y Electrica');
INSERT INTO FACULTY (id_Person, name_Faculty) VALUES (5, 'Facultad de Quimica');
INSERT INTO FACULTY (id_Person, name_Faculty) VALUES (4, 'Facultad de Industrial');
INSERT INTO FACULTY (id_Person, name_Faculty) VALUES (2, 'Facultad de Minas');


INSERT INTO SCHOOL (id_Faculty, name_School) VALUES (1, 'Sistemas');
INSERT INTO SCHOOL (id_Faculty, name_School) VALUES (1, 'Software');
INSERT INTO SCHOOL (id_Faculty, name_School) VALUES (2, 'Electrica');
INSERT INTO SCHOOL (id_Faculty, name_School) VALUES (2, 'Electronica');
INSERT INTO SCHOOL (id_Faculty, name_School) VALUES (2, 'Telecomunicaciones');
INSERT INTO SCHOOL (id_Faculty, name_School) VALUES (3, 'Quimica');
INSERT INTO SCHOOL (id_Faculty, name_School) VALUES (3, 'Agroindustria');
INSERT INTO SCHOOL (id_Faculty, name_School) VALUES (4, 'Industrial');
INSERT INTO SCHOOL (id_Faculty, name_School) VALUES (4, 'Textil y Confecciones');
INSERT INTO SCHOOL (id_Faculty, name_School) VALUES (5, 'Minas');
INSERT INTO SCHOOL (id_Faculty, name_School) VALUES (5, 'Metalurgica');


INSERT INTO STUDENT (id_Person, code_Student, id_School) VALUES (6, '7786', 1);
INSERT INTO STUDENT (id_Person, code_Student, id_School) VALUES (7, '6846', 2);
INSERT INTO STUDENT (id_Person, code_Student, id_School) VALUES (10, '9126', 4);
INSERT INTO STUDENT (id_Person, code_Student, id_School) VALUES (9, '3566', 6);
INSERT INTO STUDENT (id_Person, code_Student, id_School) VALUES (8, '4566', 2);


INSERT INTO SUBJECT(namesubject) VALUES ('CALCULO I');
INSERT INTO SUBJECT(namesubject) VALUES ('CALCULO II');
INSERT INTO SUBJECT(namesubject) VALUES ('CALCULO III');
INSERT INTO SUBJECT(namesubject) VALUES ('MATEMATICA BASICA');
INSERT INTO SUBJECT(namesubject) VALUES ('MATEMATICA II');
INSERT INTO SUBJECT(namesubject) VALUES ('BIOLOGIA');

INSERT INTO THEME(id_Subject,nameTheme) VALUES (1,'VARIABLES Y FUNCIONES');
INSERT INTO THEME(id_Subject,nameTheme) VALUES (1,'SUCESIONES');
INSERT INTO THEME(id_Subject,nameTheme) VALUES (1,'LIMITES');
INSERT INTO THEME(id_Subject,nameTheme) VALUES (1,'DERIVADAS');
INSERT INTO THEME(id_Subject,nameTheme) VALUES (1,'TEOREMA DEL VALOR MEDIO Y SUS APLICACIONES');
INSERT INTO THEME(id_Subject,nameTheme) VALUES (1,'FUNCION EXPONENCIAL Y SU APLICACIONES');

INSERT INTO THEME(id_Subject,nameTheme) VALUES (2,'FUNCIONES EN VARIAS VARIABLES');
INSERT INTO THEME(id_Subject,nameTheme) VALUES (2,'DERIVACION IMPLICITA');
INSERT INTO THEME(id_Subject,nameTheme) VALUES (2,'FUNCIONES HOMOGENEAS');
INSERT INTO THEME(id_Subject,nameTheme) VALUES (2,'FUNCIONES DE R^n A R^m');
INSERT INTO THEME(id_Subject,nameTheme) VALUES (3,'INTEGRAL INDEFINIDA, INTEGRAL POR SUSTITUCION');

INSERT INTO THEME(id_Subject,nameTheme) VALUES (3,'TEOREMA FUNDAMENTAL DEL CALCULO');
INSERT INTO THEME(id_Subject,nameTheme) VALUES (3,'AREA, VALOR PROMEDIO, LONGUITUD DE UNA CURVA');
INSERT INTO THEME(id_Subject,nameTheme) VALUES (3,'INTEGRALES RELACIONADAS CON LAS FUNCIONES TRIGONOMÉTRICAS INVERSAS');
INSERT INTO THEME(id_Subject,nameTheme) VALUES (3,'TÉCNICAS DE INTEGRACIÓN ');
INSERT INTO THEME(id_Subject,nameTheme) VALUES (3,'FORMAS INDETERMINADAS, REGLA DE L ́HOPITAL');

INSERT INTO THEME(id_Subject,nameTheme) VALUES (4,'COMBINATORIA, BINOMIO DE NEWTON');
INSERT INTO THEME(id_Subject,nameTheme) VALUES (4,'TRIGONOMETRIA');
INSERT INTO THEME(id_Subject,nameTheme) VALUES (4,'NUMEROS COMPLEJOS');
INSERT INTO THEME(id_Subject,nameTheme) VALUES (4,'POLINOMIOS');
INSERT INTO THEME(id_Subject,nameTheme) VALUES (4,'FUNCIONES LINEALES Y CUADRATICAS');
INSERT INTO THEME(id_Subject,nameTheme) VALUES (4,'FUNCIONES EXPONENCIALES Y LOGARITMICAS');

INSERT INTO THEME(id_Subject,nameTheme) VALUES (5,'MATRICES DETERMINANTES');
INSERT INTO THEME(id_Subject,nameTheme) VALUES (5,'DETERMINANTES');
INSERT INTO THEME(id_Subject,nameTheme) VALUES (5,'SISTEMAS LINEALES');
INSERT INTO THEME(id_Subject,nameTheme) VALUES (5,'VECTORES EN EL ESPACIO');
INSERT INTO THEME(id_Subject,nameTheme) VALUES (5,'ECUACIONES DE RECTAS Y PLANO');
INSERT INTO THEME(id_Subject,nameTheme) VALUES (5,'RECTAS Y PLANOS EN EL ESPACIO');

INSERT INTO THEME(id_Subject,nameTheme) VALUES (6,'AGUA Y SALES MINERALES');
INSERT INTO THEME(id_Subject,nameTheme) VALUES (6,'GLUCIDOS');
INSERT INTO THEME(id_Subject,nameTheme) VALUES (6,'LIPIDOS');
INSERT INTO THEME(id_Subject,nameTheme) VALUES (6,'PROTEINAS');
INSERT INTO THEME(id_Subject,nameTheme) VALUES (6,'ENZIMAS');
INSERT INTO THEME(id_Subject,nameTheme) VALUES (6,'ACIDOS NUCLEICOS');

INSERT INTO QUESTION(id_Theme,question,score) VALUES (1,'Exprese el  área A de una esfera como funciión de su volumen V',3);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (1,'¿Cuál es la imagen de la función f: R→R 
f(x)=(𝑥−1)/(|x|+1)? ',4);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (1,'Un faro situado a 10 km de la costa gira a razón de 10 revoluciones por minuto. Si en t=0 el rayo de luz del faro incide en el punto P en la costa más cercano al faro, exprese la posición del rayo de luz a lo largo de la costa medida desde el punto P como función del tiempo, suponiendo que la costa es recta. Señale el dominio de la función',4);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (1,'Sea f : (0,∞) → R una función uno a uno y positiva y sea f^(−1)(y) su función inversa. 
Determine la inversa de la función g
g(x) = f(􏰝 (f(x) + 1)^(1/2) )',5);

INSERT INTO QUESTION(id_Theme,question,score) VALUES (2,'lim n→∞ (􏰉1/n^2 + 2/n^2 +···+(n−1)/n^2)􏰊 ',3);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (2,'lim n→∞􏰉(1/2!+2/3!+···+(n−1)/n!)􏰊',4);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (2,'lim n→∞ (􏰉1^2/n^3 + 2^2/n^3 +···+(n−1)^2/n^3)􏰊',4);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (2,'lim n→∞ 􏰉(1/1*2+1/2*3+···+1/(n)*(n+1))􏰊',5);

INSERT INTO QUESTION(id_Theme,question,score) VALUES (3,' lim x→1 √(x+3)−2/(x-1)',3);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (3,' lim x→∞ |x+1|−1/(4 − x^2)',4);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (3,' lim x→∞ (100x^2+1)/(x^2+100)',4);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (3,' lim x→∞ (√(x+√(x+√(x)))/(√(x+1))',5);

INSERT INTO QUESTION(id_Theme,question,score) VALUES (4,'Calcule la función derivada de y(x) = x|x| ',3);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (4,'Describa en qué puntos la función f (x) = |x^2 − 1| tiene derivada',3);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (4,'Calcule la derivada de la función f (x) = sen(g(x) + 2) en el punto x = 3, si g(3)=(π−12)/6 y dg/dx(3)=−4',5);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (4,'Sea f : R → R con f(0) = 1 y tal que, para cualesquiera x,h ∈ R, satisface f(x+h)−f(x)=8xh−2h+4h. Calcule f`(2)',5);

INSERT INTO QUESTION(id_Theme,question,score) VALUES (5,'Aplique las reglas de L’Hopital: lim x→π ((sen x)^2)/(x−π)',3);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (5,'Aplique las reglas de L’Hopital: lim x→0+ ((sen x)^2)/(x−tan(x))',4);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (5,'Aplique las reglas de L’Hopital: lim x→0 (arcsen(x))/(arctan(x))',4);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (5,'Aplique las reglas de L’Hopital: lim x→0 ((3sen x)-sen(3x))/(3tan(x)−tan(3x))',5);

INSERT INTO QUESTION(id_Theme,question,score) VALUES (6,'Encuentre el número real α tal que la curva f(x) = ex es tangente a la curva g(x) = αx^2',3);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (6,'Una especie de bacteria virulenta crece en un cultivo. Si la velocidad de crecimiento de la población bacteriana es proporcional al número de individuos presente, si en la población inicial hay 1000 bacterias y si el número de individuos se duplica después de los primeros 30 minutos, ¿cuántas bacterias habra ́ después de dos horas?',4);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (6,'Un cono circular recto de 24 cm de altura y 6 cm de radio en su base, se llena con agua y se coloca con su vértice apuntando hacia abajo. El agua empieza a salir a través de un orificio en el vértice con una velocidad, en cada instante, igual a la altura del agua en el cono en el instante en cuestión. Diga cuánto tarda en vaciarse el cono.',4);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (6,'4^x+6^(x^2)=5^x+5^(x^2),5);

INSERT INTO QUESTION(id_Theme,question,score) VALUES (7,'HALLAR LA ECUACION DE LA RECTA →r(t)=(1+t) i−t j,t∈R.',3);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (7,'HALLAR LA ECUACION DE LA PARÁBOLA →r ( m ) = ( m + 1 )i + ( m2 − 1 )j , m ∈ R',4);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (7,'HALLAR LA ECUACION DE LA PARÁBOLA →r(a)=(4−a) i−√a j,a≥0
',4);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (7,'HALLAR LA ECUACION DE LA CIRCUNFERENCIA r(θ)=3cosθ i+3senθ j,θ∈[0,2π)',5);

INSERT INTO QUESTION(id_Theme,question,score) VALUES (8,'HALLAR EL DOMINIO DE LA FUNCION f(x,y,z)=1−√(1−x−y−z)',3);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (8,'HALLAR EL DOMINIO DE LA FUNCION  f(x)=ln(1−x^2)',4);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (8,'HALLAR EL DOMINIO DE LA FUNCION f(x,y,z) = e^(√ln(x^2+y^2))',4);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (8,'HALLAR EL DOMINIO DE LA FUNCION f(x,y,z) = e^(1−√(1−lny))',5);

INSERT INTO QUESTION(id_Theme,question,score) VALUES (9,'HALLAR DA DERIVADA IMPLICITA x^2+xy+y2=7, P(1,2)',3);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (9,'HALLAR LA DERIVADA IMPLICITA e^(1−xy) +ln(x/y)=1, P(1,1).',4);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (9,'HALLAR LA DERIVADA IMPLICITA xe^y +sen(xy)+y−ln2=0, P(0,ln2).',4);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (9,'HALLAR LA DERIVACION IMPLICITA F(x,y)=2x^2+4xy−y^4+67',5);

INSERT INTO QUESTION(id_Theme,question,score) VALUES (10,'COMPROBAR SI LA FUNCION ES HOMOGENEA f(x,y)=x^2 +y^3.',3);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (10,'COMPROBAR SI LA FUNCION ES HOMOGENEA f(x,y)=ln(xy)',4);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (10,'COMPROBAR SI LA FUNCION ES HOMOGENEA Sea F (L, K) = AL^(a)Kbe^(cK/L)',4);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (10,'COMPROBAR SI LA FUNCION ES HOMOGENEA f(x,y)=lnu(x,y)',5);

INSERT INTO ALTERNATIVE(id_Question,alternativeA, alternativeB, alternativeC, answer) VALUES (1,'(36*πV^2)^(1/3)','(24πV^2)^(1/2)', '(16π*V^2)^(1/3)', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternativeA, alternativeB, alternativeC, answer) VALUES (2,'<−1,1>','[−1,1)', '<−1,1] ', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternativeA, alternativeB, alternativeC, answer) VALUES (3,'p:[0, 1]','p:[0, 1 >',
'p:<0, 1> ', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternativeA, alternativeB, alternativeC, answer) VALUES (4,'g−1(y) = f−1([f−1(y)]^2 − 1) ','g−1(y) = f−1([f−1(y)]^3 − 1) ', 'g−1(y) = f−1([f−1(y)]^2) ', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternativeA, alternativeB, alternativeC, answer) VALUES (5,'1/2','1/4', '1/8', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternativeA, alternativeB, alternativeC, answer) VALUES (6,'-1','1', '0', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternativeA, alternativeB, alternativeC, answer) VALUES (7,'1/6 ','-1/6', '1/3', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternativeA, alternativeB, alternativeC, answer) VALUES (8,'-1','1', '0', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternativeA, alternativeB, alternativeC, answer) VALUES (9,'1/3','1/2', '1/4', 'C');
INSERT INTO ALTERNATIVE(id_Question,alternativeA, alternativeB, alternativeC, answer) VALUES (10,'-1/2 ','-1/3', '1/4', 'C');
INSERT INTO ALTERNATIVE(id_Question,alternativeA, alternativeB, alternativeC, answer) VALUES (11,'1/6 ','1/4', '1/2', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternativeA, alternativeB, alternativeC, answer) VALUES (12,'-1/2','1/4', '1/2', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternativeA, alternativeB, alternativeC, answer) VALUES (13,'2|x|','2|x| /5', '2|x| /6', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternativeA, alternativeB, alternativeC, answer) VALUES (14,'− 5x ','− 2 x ', '− 3x', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternativeA, alternativeB, alternativeC, answer) VALUES (15,'−3√3','−2√2', '−2√3', 'C');
INSERT INTO ALTERNATIVE(id_Question,alternativeA, alternativeB, alternativeC, answer) VALUES (16,'6 ','-12', '12', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternativeA, alternativeB, alternativeC, answer) VALUES (17,'1 ','0', 'NN', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternativeA, alternativeB, alternativeC, answer) VALUES (18,'0 ','1', 'NN', 'C');
INSERT INTO ALTERNATIVE(id_Question,alternativeA, alternativeB, alternativeC, answer) VALUES (19,'1 ','0', '-1', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternativeA, alternativeB, alternativeC, answer) VALUES (20,'1/2 ','-1/2', '0', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternativeA, alternativeB, alternativeC, answer) VALUES (21,'2e^2 ','4^e', '(1/4)e^2', 'C');
INSERT INTO ALTERNATIVE(id_Question,alternativeA, alternativeB, alternativeC, answer) VALUES (22,'15000 bacterias ','16000 bacterias ', '18000 bacterias', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternativeA, alternativeB, alternativeC, answer) VALUES (23,'180 segundos','220 segundos', '216 segundos', 'C');
INSERT INTO ALTERNATIVE(id_Question,alternativeA, alternativeB, alternativeC, answer) VALUES (24,'x = 0 y x = 1','x=1', 'x=0', 'A');

INSERT INTO ALTERNATIVE(id_Question,alternativeA, alternativeB, alternativeC, answer) VALUES (25,'y=1−x ','y=x-1', 'y=x+2', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternativeA, alternativeB, alternativeC, answer) VALUES (26,'y = x^2 − 2x ','y = x^2 − x ', 'y = x^2 − 3x ', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternativeA, alternativeB, alternativeC, answer) VALUES (27,'y=−√2−x','y=−√4−x ', 'y=−√3−x ', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternativeA, alternativeB, alternativeC, answer) VALUES (28,'x^2 + y^2 = 25','x^2 + y^2 = 16 ', 'x^2 + y^2 = 9 ', 'C');
INSERT INTO ALTERNATIVE(id_Question,alternativeA, alternativeB, alternativeC, answer) VALUES (29,'−∞<x+y+z≤1','−∞<x+y+z≤2', '−∞<x+y+z≤4', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternativeA, alternativeB, alternativeC, answer) VALUES (30,'−1<x<3','−1<x<2', '−1<x<1 ', 'C');
INSERT INTO ALTERNATIVE(id_Question,alternativeA, alternativeB, alternativeC, answer) VALUES (31,' - 1≤ x^2 + y^2 < ∞ ','4≤ x^2 + y^2 < ∞ ', '1 ≤ x^2 + y^2 < ∞ ', 'C');
INSERT INTO ALTERNATIVE(id_Question,alternativeA, alternativeB, alternativeC, answer) VALUES (32,'0<y≤e ','0<y≤2e ', '0<y≤3e', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternativeA, alternativeB, alternativeC, answer) VALUES (33,' - (4/5)','4/5', '2/3 ', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternativeA, alternativeB, alternativeC, answer) VALUES (34,'−1','0', '1 ', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternativeA, alternativeB, alternativeC, answer) VALUES (35,'−(4+ln2) ','−(2+ln2) ', '−(2+ln4)', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternativeA, alternativeB, alternativeC, answer) VALUES (36,'-104','104', '1OO', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternativeA, alternativeB, alternativeC, answer) VALUES (37,'Homogenea de Grado 2','Homogenea de grado 1', 'No es homogenea', 'C');
INSERT INTO ALTERNATIVE(id_Question,alternativeA, alternativeB, alternativeC, answer) VALUES (38,'Homogenea de Grado 2','Homogenea de grado 1', 'No es homogenea', 'C');
INSERT INTO ALTERNATIVE(id_Question,alternativeA, alternativeB, alternativeC, answer) VALUES (39,'HOMOGÉNEA DE GRADO a','Homogénea de grado b', 'Homogénea de grado a+b', 'C');
INSERT INTO ALTERNATIVE(id_Question,alternativeA, alternativeB, alternativeC, answer) VALUES (40,'No es homogénea','Homogénea de grado 2', 'Homogénea de grado 1', 'A');


