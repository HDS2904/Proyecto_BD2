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
ALTER TABLE TEACHER ADD CONSTRAINT range_codet CHECK (REGEXP_LIKE(CODE_TEACHER, 
'[0-9|a-z|A-Z][0-9|a-z|A-Z][0-9|a-z|A-Z][0-9|a-z|A-Z][0-9|a-z|A-Z][0-9|a-z|A-Z][0-9|a-z|A-Z][0-9|a-z|A-Z]')) enable novalidate;
ALTER TABLE STUDENT ADD CONSTRAINT range_codes CHECK (REGEXP_LIKE(CODE_STUDENT, 
'[0-9|a-z|A-Z][0-9|a-z|A-Z][0-9|a-z|A-Z][0-9|a-z|A-Z][0-9|a-z|A-Z][0-9|a-z|A-Z][0-9|a-z|A-Z][0-9|a-z|A-Z]')) enable novalidate;
ALTER TABLE DIRECTOR ADD CONSTRAINT range_coded CHECK (REGEXP_LIKE(CODE_DIRECTOR, 
'[0-9|a-z|A-Z][0-9|a-z|A-Z][0-9|a-z|A-Z][0-9|a-z|A-Z][0-9|a-z|A-Z][0-9|a-z|A-Z][0-9|a-z|A-Z][0-9|a-z|A-Z]')) enable novalidate;
ALTER TABLE QUESTION ADD CONSTRAINT RANGO_SCORE CHECK (SCORE BETWEEN 0 AND 20);
ALTER TABLE ANSWERS_STUDENT ADD CONSTRAINT ANSWERS_STUDENT CHECK (N_QUESTION BETWEEN 0 AND 10);

--------------------------------------------------------------------------------
-- INSERTS --
--------------------------------------------------------------------------------
INSERT INTO FACULTY (id_Person, name_Faculty) VALUES (NULL, 'Facultad de Sistemas y Software');
INSERT INTO FACULTY (id_Person, name_Faculty) VALUES (NULL, 'Facultad de Electronia y Electrica');
INSERT INTO FACULTY (id_Person, name_Faculty) VALUES (NULL, 'Facultad de Quimica');
INSERT INTO FACULTY (id_Person, name_Faculty) VALUES (NULL, 'Facultad de Industrial');
INSERT INTO FACULTY (id_Person, name_Faculty) VALUES (NULL, 'Facultad de Minas');

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

INSERT INTO PERSON (FIRST_NAME, LAST_NAME, DNI, PHONE, ADDRESS, EMAIL) VALUES ('Jose', 'Perez Martinez', 71548925, 958123648, 'Av Peru cdra 21', 'joseperez@unmsm.edu.pe');
INSERT INTO PERSON (FIRST_NAME, LAST_NAME, DNI, PHONE, ADDRESS, EMAIL) VALUES ('Miguel', 'Mejia Acevedo',71546768,  943211238, 'Av Peru cdra 15', 'miguelmejia@unmsm.edu.pe');
INSERT INTO PERSON (FIRST_NAME, LAST_NAME, DNI, PHONE, ADDRESS, EMAIL) VALUES ('Carolina', 'Alejo Guerrero', 74564456, 945645645, 'Av Universitaria cdra 11', 'carolinaalejo@unmsm.edu.pe');
INSERT INTO PERSON (FIRST_NAME, LAST_NAME, DNI, PHONE, ADDRESS, EMAIL) VALUES ('Pedro', 'Andrade Bujanda', 74564578, 945645677, 'Av Brasil cdra 3', 'pedroandrade@unmsm.edu.pe');
INSERT INTO PERSON (FIRST_NAME, LAST_NAME, DNI, PHONE, ADDRESS, EMAIL) VALUES ('Juan', 'Aremnta Romero', 71231212, 921312321, 'Av Bolivar cdra 6', 'juanaremnta@unmsm.edu.pe');
INSERT INTO PERSON (FIRST_NAME, LAST_NAME, DNI, PHONE, ADDRESS, EMAIL) VALUES ('Enrique', 'Bernabe Palomino', 73487624, 978678654, 'Av Bolivar cdra 9', 'enriquebernabe@unmsm.edu.pe');
INSERT INTO PERSON (FIRST_NAME, LAST_NAME, DNI, PHONE, ADDRESS, EMAIL) VALUES ('Angel', 'Ramos Gutierrez', 77897864, 912345642, 'Av Colonial cdra 7', 'angelramos@unmsm.edu.pe');
INSERT INTO PERSON (FIRST_NAME, LAST_NAME, DNI, PHONE, ADDRESS, EMAIL) VALUES ('German', 'Arias Rodrigez', 71231234, 932134521, 'Av Marina cdra 10', 'germanarias@unmsm.edu.pe');
INSERT INTO PERSON (FIRST_NAME, LAST_NAME, DNI, PHONE, ADDRESS, EMAIL) VALUES ('Ruben', 'Quispe Quispe', 79789878, 912345645, 'Av Marina cdra 1', 'rubenquispe@unmsm.edu.pe');
INSERT INTO PERSON (FIRST_NAME, LAST_NAME, DNI, PHONE, ADDRESS, EMAIL) VALUES ('Fernando', 'Cardenas Alva', 74678652, 978967865, 'Av Sucre cdra 8', 'fernandocardenas@unmsm.edu.pe');
INSERT INTO PERSON (FIRST_NAME, LAST_NAME, DNI, PHONE, ADDRESS, EMAIL) VALUES ('Alberto', 'Gonzales Carrasco', 73213132, 945645312, 'Av La Mar cdra 7', 'albertogonzales@unmsm.edu.pe');
INSERT INTO PERSON (FIRST_NAME, LAST_NAME, DNI, PHONE, ADDRESS, EMAIL) VALUES ('Diana', 'Tavara Pineda', 72231230, 978987632, 'Av Peru cdra 9', 'dianatavara@unmsm.edu.pe');
INSERT INTO PERSON (FIRST_NAME, LAST_NAME, DNI, PHONE, ADDRESS, EMAIL) VALUES ('Maria', 'Bentacur Taborda', 70213154, 912345652, 'Av Bolivar cdra 12', 'mariabentacur@unmsm.edu.pe');
INSERT INTO PERSON (FIRST_NAME, LAST_NAME, DNI, PHONE, ADDRESS, EMAIL) VALUES ('Rubi', 'Gomez Villa', 77531312, 987645343, 'Av Colonial cdra 4', 'rubigomez@unmsm.edu.pe');
INSERT INTO PERSON (FIRST_NAME, LAST_NAME, DNI, PHONE, ADDRESS, EMAIL) VALUES ('Luis', 'Garcia Rua', 79876786, 902464356, 'Av La Paz cdra 6', 'luisgarcia@unmsm.edu.pe');

INSERT INTO DIRECTOR (ID_PERSON, CODE_DIRECTOR, ID_FACULTY) VALUES (1,'10000001',1);
INSERT INTO DIRECTOR (ID_PERSON, CODE_DIRECTOR, ID_FACULTY) VALUES (2,'10000002',2);

INSERT INTO TEACHER (ID_PERSON, CODE_TEACHER) VALUES (3,'20000001');
INSERT INTO TEACHER (ID_PERSON, CODE_TEACHER) VALUES (4,'20000002');
INSERT INTO TEACHER (ID_PERSON, CODE_TEACHER) VALUES (5,'20000003');
INSERT INTO TEACHER (ID_PERSON, CODE_TEACHER) VALUES (6,'20000004');
INSERT INTO TEACHER (ID_PERSON, CODE_TEACHER) VALUES (7,'20000005');

INSERT INTO STUDENT (ID_PERSON, CODE_STUDENT, ID_SCHOOL) VALUES (8,'30000001',1);
INSERT INTO STUDENT (ID_PERSON, CODE_STUDENT, ID_SCHOOL) VALUES (9,'30000002',1);
INSERT INTO STUDENT (ID_PERSON, CODE_STUDENT, ID_SCHOOL) VALUES (10,'30000003',1);
INSERT INTO STUDENT (ID_PERSON, CODE_STUDENT, ID_SCHOOL) VALUES (11,'30000004',1);
INSERT INTO STUDENT (ID_PERSON, CODE_STUDENT, ID_SCHOOL) VALUES (12,'30000005',2);
INSERT INTO STUDENT (ID_PERSON, CODE_STUDENT, ID_SCHOOL) VALUES (13,'30000006',2);
INSERT INTO STUDENT (ID_PERSON, CODE_STUDENT, ID_SCHOOL) VALUES (14,'30000007',2);
INSERT INTO STUDENT (ID_PERSON, CODE_STUDENT, ID_SCHOOL) VALUES (15,'30000008',2);

INSERT INTO SUBJECT(name_subject) VALUES ('CALCULO I');
INSERT INTO SUBJECT(name_subject) VALUES ('CALCULO II');
INSERT INTO SUBJECT(name_subject) VALUES ('CALCULO III');
INSERT INTO SUBJECT(name_subject) VALUES ('MATEMATICA BASICA');
INSERT INTO SUBJECT(name_subject) VALUES ('MATEMATICA II');
INSERT INTO SUBJECT(name_subject) VALUES ('BIOLOGIA');
INSERT INTO SUBJECT(name_subject) VALUES ('ALGORITMICA I');
INSERT INTO SUBJECT(name_subject) VALUES ('BASE DE DATOS I');

INSERT INTO THEME(id_Subject,name_Theme) VALUES (1,'VARIABLES Y FUNCIONES');
INSERT INTO THEME(id_Subject,name_Theme) VALUES (1,'SUCESIONES');
INSERT INTO THEME(id_Subject,name_Theme) VALUES (1,'LIMITES');
INSERT INTO THEME(id_Subject,name_Theme) VALUES (1,'DERIVADAS');
INSERT INTO THEME(id_Subject,name_Theme) VALUES (1,'TEOREMA DEL VALOR MEDIO Y SUS APLICACIONES');
INSERT INTO THEME(id_Subject,name_Theme) VALUES (1,'FUNCION EXPONENCIAL Y SU APLICACIONES');
INSERT INTO THEME(id_Subject,name_Theme) VALUES (2,'FUNCIONES EN VARIAS VARIABLES');
INSERT INTO THEME(id_Subject,name_Theme) VALUES (2,'DERIVACION IMPLICITA');
INSERT INTO THEME(id_Subject,name_Theme) VALUES (2,'FUNCIONES HOMOGENEAS');
INSERT INTO THEME(id_Subject,name_Theme) VALUES (2,'FUNCIONES DE R^n A R^m');
INSERT INTO THEME(id_Subject,name_Theme) VALUES (3,'INTEGRAL INDEFINIDA, INTEGRAL POR SUSTITUCION');
INSERT INTO THEME(id_Subject,name_Theme) VALUES (3,'TEOREMA FUNDAMENTAL DEL CALCULO');
INSERT INTO THEME(id_Subject,name_Theme) VALUES (3,'AREA, VALOR PROMEDIO, LONGUITUD DE UNA CURVA');
INSERT INTO THEME(id_Subject,name_Theme) VALUES (3,'INTEGRALES RELACIONADAS CON LAS FUNCIONES TRIGONOMÉTRICAS INVERSAS');
INSERT INTO THEME(id_Subject,name_Theme) VALUES (3,'TÉCNICAS DE INTEGRACIÓN ');
INSERT INTO THEME(id_Subject,name_Theme) VALUES (3,'FORMAS INDETERMINADAS, REGLA DE L ´HOPITAL');
INSERT INTO THEME(id_Subject,name_Theme) VALUES (4,'COMBINATORIA, BINOMIO DE NEWTON');
INSERT INTO THEME(id_Subject,name_Theme) VALUES (4,'TRIGONOMETRIA');
INSERT INTO THEME(id_Subject,name_Theme) VALUES (4,'NUMEROS COMPLEJOS');
INSERT INTO THEME(id_Subject,name_Theme) VALUES (4,'POLINOMIOS');
INSERT INTO THEME(id_Subject,name_Theme) VALUES (4,'FUNCIONES LINEALES Y CUADRATICAS');
INSERT INTO THEME(id_Subject,name_Theme) VALUES (4,'FUNCIONES EXPONENCIALES Y LOGARITMICAS');
INSERT INTO THEME(id_Subject,name_Theme) VALUES (5,'MATRICES DETERMINANTES');
INSERT INTO THEME(id_Subject,name_Theme) VALUES (5,'DETERMINANTES');
INSERT INTO THEME(id_Subject,name_Theme) VALUES (5,'SISTEMAS LINEALES');
INSERT INTO THEME(id_Subject,name_Theme) VALUES (5,'VECTORES EN EL ESPACIO');
INSERT INTO THEME(id_Subject,name_Theme) VALUES (5,'ECUACIONES DE RECTAS Y PLANO');
INSERT INTO THEME(id_Subject,name_Theme) VALUES (5,'RECTAS Y PLANOS EN EL ESPACIO');
INSERT INTO THEME(id_Subject,name_Theme) VALUES (6,'AGUA Y SALES MINERALES');
INSERT INTO THEME(id_Subject,name_Theme) VALUES (6,'GLUCIDOS');
INSERT INTO THEME(id_Subject,name_Theme) VALUES (6,'LIPIDOS');
INSERT INTO THEME(id_Subject,name_Theme) VALUES (6,'PROTEINAS');
INSERT INTO THEME(id_Subject,name_Theme) VALUES (6,'ENZIMAS');
INSERT INTO THEME(id_Subject,name_Theme) VALUES (6,'ACIDOS NUCLEICOS');



INSERT INTO QUESTION(id_Theme,question,score) VALUES (1,'Exprese el  área A de una esfera como funciión de su volumen V',2);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (1,'¿Cuál es la imagen de la función f: R?R 
f(x)=(??1)/(|x|+1)? ',3);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (1,'Un faro situado a 10 km de la costa gira a razón de 10 revoluciones por minuto. Si en t=0 el rayo de luz del faro incide en el punto P en la costa más cercano al faro, exprese la posición del rayo de luz a lo largo de la costa medida desde el punto P como función del tiempo, suponiendo que la costa es recta. Señale el dominio de la función',4);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (1,'Sea f : (0,?) ? R una función uno a uno y positiva y sea f^(?1)(y) su función inversa. 
Determine la inversa de la función g
g(x) = f(? (f(x) + 1)^(1/2) )',5);

INSERT INTO QUESTION(id_Theme,question,score) VALUES (2,'lim n?? (?1/n^2 + 2/n^2 +···+(n?1)/n^2)? ',2);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (2,'lim n???(1/2!+2/3!+···+(n?1)/n!)?',3);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (2,'lim n?? (?1^2/n^3 + 2^2/n^3 +···+(n?1)^2/n^3)?',4);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (2,'lim n?? ?(1/1*2+1/2*3+···+1/(n)*(n+1))?',5);

INSERT INTO QUESTION(id_Theme,question,score) VALUES (3,' lim x?1 ?(x+3)?2/(x-1)',2);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (3,' lim x?? |x+1|?1/(4 ? x^2)',3);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (3,' lim x?? (100x^2+1)/(x^2+100)',4);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (3,' lim x?? (?(x+?(x+?(x)))/(?(x+1))',5);

INSERT INTO QUESTION(id_Theme,question,score) VALUES (4,'Calcule la función derivada de y(x) = x|x| ',2);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (4,'Describa en qué puntos la función f (x) = |x^2 ? 1| tiene derivada',3);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (4,'Calcule la derivada de la función f (x) = sen(g(x) + 2) en el punto x = 3, si g(3)=(??12)/6 y dg/dx(3)=?4',4);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (4,'Sea f : R ? R con f(0) = 1 y tal que, para cualesquiera x,h ? R, satisface f(x+h)?f(x)=8xh?2h+4h. Calcule f`(2)',5);

INSERT INTO QUESTION(id_Theme,question,score) VALUES (5,'Aplique las reglas de L’Hopital: lim x?? ((sen x)^2)/(x??)',2);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (5,'Aplique las reglas de L’Hopital: lim x?0+ ((sen x)^2)/(x?tan(x))',3);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (5,'Aplique las reglas de L’Hopital: lim x?0 (arcsen(x))/(arctan(x))',4);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (5,'Aplique las reglas de L’Hopital: lim x?0 ((3sen x)-sen(3x))/(3tan(x)?tan(3x))',5);

INSERT INTO QUESTION(id_Theme,question,score) VALUES (6,'Encuentre el número real ? tal que la curva f(x) = ex es tangente a la curva g(x) = ?x^2',2);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (6,'Una especie de bacteria virulenta crece en un cultivo. Si la velocidad de crecimiento de la población bacteriana es proporcional al número de individuos presente, si en la población inicial hay 1000 bacterias y si el número de individuos se duplica después de los primeros 30 minutos, ¿cuántas bacterias habra ? después de dos horas?',3);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (6,'Un cono circular recto de 24 cm de altura y 6 cm de radio en su base, se llena con agua y se coloca con su vértice apuntando hacia abajo. El agua empieza a salir a través de un orificio en el vértice con una velocidad, en cada instante, igual a la altura del agua en el cono en el instante en cuestión. Diga cuánto tarda en vaciarse el cono.',4);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (6,'4^x+6^(x^2)=5^x+5^(x^2)',5);

INSERT INTO QUESTION(id_Theme,question,score) VALUES (7,'HALLAR LA ECUACION DE LA RECTA ?r(t)=(1+t) i?t j,t?R.',2);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (7,'HALLAR LA ECUACION DE LA PARÁBOLA ?r ( m ) = ( m + 1 )i + ( m2 ? 1 )j , m ? R',3);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (7,'HALLAR LA ECUACION DE LA PARÁBOLA ?r(a)=(4?a) i??a j,a?0
',4);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (7,'HALLAR LA ECUACION DE LA CIRCUNFERENCIA r(?)=3cos? i+3sen? j,??[0,2?)',5);

INSERT INTO QUESTION(id_Theme,question,score) VALUES (8,'HALLAR EL DOMINIO DE LA FUNCION f(x,y,z)=1??(1?x?y?z)',2);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (8,'HALLAR EL DOMINIO DE LA FUNCION  f(x)=ln(1?x^2)',3);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (8,'HALLAR EL DOMINIO DE LA FUNCION f(x,y,z) = e^(?ln(x^2+y^2))',4);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (8,'HALLAR EL DOMINIO DE LA FUNCION f(x,y,z) = e^(1??(1?lny))',5);

INSERT INTO QUESTION(id_Theme,question,score) VALUES (9,'HALLAR DA DERIVADA IMPLICITA x^2+xy+y2=7, P(1,2)',2);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (9,'HALLAR LA DERIVADA IMPLICITA e^(1?xy) +ln(x/y)=1, P(1,1).',3);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (9,'HALLAR LA DERIVADA IMPLICITA xe^y +sen(xy)+y?ln2=0, P(0,ln2).',4);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (9,'HALLAR LA DERIVACION IMPLICITA F(x,y)=2x^2+4xy?y^4+67',5);

INSERT INTO QUESTION(id_Theme,question,score) VALUES (10,'COMPROBAR SI LA FUNCION ES HOMOGENEA f(x,y)=x^2 +y^3.',2);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (10,'COMPROBAR SI LA FUNCION ES HOMOGENEA f(x,y)=ln(xy)',3);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (10,'COMPROBAR SI LA FUNCION ES HOMOGENEA Sea F (L, K) = AL^(a)Kbe^(cK/L)',4);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (10,'COMPROBAR SI LA FUNCION ES HOMOGENEA f(x,y)=lnu(x,y)',5);

INSERT INTO QUESTION(id_Theme,question,score) VALUES (11,'∫1▒(−3)/𝑥^(4/3)  𝑑𝑥',2);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (11,'∫1▒𝑥^(1/8)  𝑑𝑥',3);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (11,'∫1▒〖(𝑋/5−2/𝑥^3 +2)〗 𝑑𝑥',4);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (11,'f′(x)=7x+cosx',5);

INSERT INTO QUESTION(id_Theme,question,score) VALUES (12,'G(x)=Integral(x,1) (1+t^4)dt',2);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (12,'G(x)=Integral(1,x) (1+t^4)dt',3);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (12,'G(x)=Integral(-1,1) (1+t^4)dt',4);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (12,'G(x)=Integral(x,1) (x^2)(1+t^4)dt',5);

INSERT INTO QUESTION(id_Theme,question,score) VALUES (13,'HALLAR EL AREA DE LA CURVA.  y=2−x, −2≤x≤1.',2);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (13,'HALLAR EL AREA DE LA CURVA.  y=3senx√1+cosx, −π≤x≤0.',3);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (13,'HALLAR EL AREA DE LA CURVA.  −3x2 +6x+9, −3≤x≤3.',4);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (13,'HALLAR EL AREA DE LA CURVA.  y=(1−x)1/3, −7≤x≤2.',5);

INSERT INTO QUESTION(id_Theme,question,score) VALUES (14,'csc( cos^−1(1/2)))',2);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (14,'SEC(sen^(-1)(3/4))',3);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (14,'cos(tan^(-1)(1))',4);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (14,'DERIVAR y = sen^(−1) ( √2 x)',5);

INSERT INTO QUESTION(id_Theme,question,score) VALUES (15,'INTEGRAL(e^(2x)/4+9e^(2x))dx',2);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (15,'INTEGRAL(e^(x)/4+9e^(2x))dx',3);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (15,'INTEGRAL(1/(2x(lnx)^(1/2)))dx',4);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (15,'INTEGRAL(1/x(1-lnx)^(1/2)))dx',5);

INSERT INTO QUESTION(id_Theme,question,score) VALUES (16,'lim x->2 (ln(cos(3x))/2x^2)',2);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (16,'lim x->2 (x-2/x^2-4)',3);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (16,'lim x->3 (x-3/x^2-3)',4);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (16,'lim x->0 (3^x-1/2^x-1)',5);

INSERT INTO QUESTION(id_Theme,question,score) VALUES (17,'¿De cuántas maneras pueden sentarse 10 personas en un banco si hay 4 sitios disponibles?',2);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (17,'En una clase de 10 alumnos van a distribuirse 3 premios. Averiguar de cuántos modos pueden hacerse si: Premios son diferentes ',3);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (17,' Hay que colocar a 5 hombres y 4 mujeres en una fila de modo que las mujeres ocupen los lugares pares. ¿De cuántas maneras puede hacerse?',4);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (17,'¿Cuántos números de 4 dígitos se pueden formar con las cifras 1,2,. . . ,9. Permitiendo repeticiones',5);

INSERT INTO QUESTION(id_Theme,question,score) VALUES (18,'Pasa a radianes los siguientes ángulos: 210',2);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (18,'Pasa a grados los ángulos: 7*pi/6 rad',3);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (18,'Si tgα = 1/3 y α es un ángulo que está en el primer cuadrante , calcula( sin hallar α):',4);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (18,'Si senα=0,35 y 0<α<90 halla sin calcular:',5);

INSERT INTO QUESTION(id_Theme,question,score) VALUES (19,'a) i^10 +  i^141 + i15',2);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (19,'(-1 + 2i)6',3);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (19,'EXPRESAR DE FORMA CANONICA 2(cos 135º + i sen 135º) · 3(cos 45º + i sen 45º)',4);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (19,'EXPRESAR DE FORMA CANONICA [2 (cos 30º + i sen 30º)]5',5);

INSERT INTO QUESTION(id_Theme,question,score) VALUES (20,'DIVIDIR (5x4 – 14 + 5x + x3) : (3 – x2)',2);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (20,'DIVIDIR (x7 – x) entre (x + 2)',3);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (20,' Halla el polinomio de segundo grado sabiendo que tiene por raíces x = 1 y x = -6 y que P(0) = –12.',4);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (20,'Factoriza las siguientes expresiones polinómicas: 3x^2 + 14x – 5',5);

INSERT INTO QUESTION(id_Theme,question,score) VALUES (21,'En mi ciudad, el billete en autobús urbano cuesta ya 1,20 €. Me ofrecen un abono mensual por 20 €. ¿Cuántos viajes deberé hacer al mes, como mínimo, para que me salga rentable comprar el abono?',2);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (21,'En mi ciudad, el billete en autobús urbano cuesta ya 1,20 €. Me ofrecen un abono mensual por 20 €. ¿Cuántos viajes deberé hacer al mes, como mínimo, para que me salga rentable comprar el abono?',3);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (21,'En mi ciudad, el billete en autobús urbano cuesta ya 1,20 €. Me ofrecen un abono mensual por 20 €. ¿Cuántos viajes deberé hacer al mes, como mínimo, para que me salga rentable comprar el abono?',4);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (21,'En mi ciudad, el billete en autobús urbano cuesta ya 1,20 €. Me ofrecen un abono mensual por 20 €. ¿Cuántos viajes deberé hacer al mes, como mínimo, para que me salga rentable comprar el abono?',5);

INSERT INTO QUESTION(id_Theme,question,score) VALUES (22,'¿En una matriz de orden p x q, qué letra representa las filas y cuál las columnas?',2);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (22,'¿Qué se requiere para sumar o restar dos matrices?',3);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (22,'¿Cuándo una matriz se dice que es cuadrada?',4);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (22,'¿Cuál es el requisito para poder multiplicar dos matrices A*B?',5);

INSERT INTO QUESTION(id_Theme,question,score) VALUES (23,'Si A es una matriz cuadrada de orden 3 con |A| = -2, ¿a qué es igual |-A|?',2);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (23,'El menor complementario del elemento que está en la fila 3 y en la columna 4 de una matriz A es:',3);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (23,'Menor de orden 3 de una matriz A es:',4);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (23,'La matriz inversa de una matriz regular A es igual a:',5);

INSERT INTO QUESTION(id_Theme,question,score) VALUES (24,'Indica cuáles de las siguientes expresiones son equivalentes a la ecuación x−2y=3',2);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (24,'La expresión x+2y=3−x+y es equivalente a una ecuación lineal con dos incógnitas.',3);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (24,'El par (2,3) es solución de la ecuación 2x+3y=5',4);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (24,'En la ecuación x−2y=3 existe una solución en la que x vale 5 e y vale:',5);

INSERT INTO QUESTION(id_Theme,question,score) VALUES (25,'Un ejemplo de magnitud vectorial es:',2);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (25,'El modulo del vector cuyas componentes rectangulares son 2 y -5 es:',3);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (25,'Las componentes del vector cuyo modulo es 5 y ángulo de inclinación es 30º son',4);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (25,'Los vectores cuya magnitud es la unidad y se representan por las letras minúsculas i, j k se denominan:',5);

INSERT INTO QUESTION(id_Theme,question,score) VALUES (26,'Si los vectores u, v, w corresponden a los v ́ertices de un tria ́ngulo equila ́tero con baricentro (punto donde se cortan las medianas) en el origen, en- tonces se cumple:',2);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (26,'El producto escalar de los vectores r1(cos α, sin α), r2(cos β, − sin β) es:',3);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (26,'Un vector perpendicular a la recta Ax+By+C =0 es:',4);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (26,'Los cuatro puntos del espacio que corresponden a los vectores u, v, w, z esta ́n sobre un mismo plano cuando:',5);

INSERT INTO QUESTION(id_Theme,question,score) VALUES (27,' Los enlaces por puente de Hidrógeno son',2);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (27,'El agua es un líquido casi incompresible debido a:',3);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (27,'En el agua la concentración de iones OH- y H+ es ',4);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (27,'¿Para qué utilizan los seres vivos los sistemas tampón?',5);

INSERT INTO QUESTION(id_Theme,question,score) VALUES (28,'¿Qué moléculas utilizan las células como reserva energética?',2);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (28,'¿Cuál de las siguientes pentosas tiene un importante papel en la fotosíntesis?',3);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (28,'¿Que monosacarido forma parte del ATP?',4);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (28,'Los polisacáridos semejantes desde el punto de vista de su composición química son:',5);

INSERT INTO QUESTION(id_Theme,question,score) VALUES (29,'Los triacilglicéridos:',2);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (29,'Los lípidos son:',3);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (29,'Los triacilglicéridos:',4);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (29,'Las apolipoproteínas:',5);

INSERT INTO QUESTION(id_Theme,question,score) VALUES (30,'Las glucoproteínas son',2);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (30,'En relación a la desnaturalización proteica por variación de la temperatura:',3);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (30,'Los aminoácidos…',4);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (30,'La parte no proteica de una heteroproteína se denomina:',5);

INSERT INTO QUESTION(id_Theme,question,score) VALUES (31,'Los catalizadores son sustancias que ',2);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (31,'Los catalizadores son sustancias',3);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (31,'Las enzimas',4);
INSERT INTO QUESTION(id_Theme,question,score) VALUES (31,'Las enzimas',5);


INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (1,'(36*?V^2)^(1/3)','(24?V^2)^(1/2)', '(16?*V^2)^(1/3)', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (2,'<?1,1>','[?1,1)', '<?1,1] ', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (3,'p:[0, 1]','p:[0, 1 >',
'p:<0, 1> ', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (4,'g?1(y) = f?1([f?1(y)]^2 ? 1) ','g?1(y) = f?1([f?1(y)]^3 ? 1) ', 'g?1(y) = f?1([f?1(y)]^2) ', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (5,'1/2','1/4', '1/8', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (6,'-1','1', '0', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (7,'1/6 ','-1/6', '1/3', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (8,'-1','1', '0', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (9,'1/3','1/2', '1/4', 'C');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (10,'-1/2 ','-1/3', '1/4', 'C');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (11,'1/6 ','1/4', '1/2', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (12,'-1/2','1/4', '1/2', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (13,'2|x|','2|x| /5', '2|x| /6', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (14,'? 5x ','? 2 x ', '? 3x', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (15,'?3?3','?2?2', '?2?3', 'C');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (16,'6 ','-12', '12', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (17,'1 ','0', 'NN', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (18,'0 ','1', 'NN', 'C');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (19,'1 ','0', '-1', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (20,'1/2 ','-1/2', '0', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (21,'2e^2 ','4^e', '(1/4)e^2', 'C');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (22,'15000 bacterias ','16000 bacterias ', '18000 bacterias', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (23,'180 segundos','220 segundos', '216 segundos', 'C');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (24,'x = 0 y x = 1','x=1', 'x=0', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (25,'y=1?x ','y=x-1', 'y=x+2', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (26,'y = x^2 ? 2x ','y = x^2 ? x ', 'y = x^2 ? 3x ', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (27,'y=??2?x','y=??4?x ', 'y=??3?x ', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (28,'x^2 + y^2 = 25','x^2 + y^2 = 16 ', 'x^2 + y^2 = 9 ', 'C');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (29,'??<x+y+z?1','??<x+y+z?2', '??<x+y+z?4', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (30,'?1<x<3','?1<x<2', '?1<x<1 ', 'C');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (31,' - 1? x^2 + y^2 < ? ','4? x^2 + y^2 < ? ', '1 ? x^2 + y^2 < ? ', 'C');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (32,'0<y?e ','0<y?2e ', '0<y?3e', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (33,' - (4/5)','4/5', '2/3 ', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (34,'?1','0', '1 ', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (35,'?(4+ln2) ','?(2+ln2) ', '?(2+ln4)', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (36,'-104','104', '1OO', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (37,'Homogenea de Grado 2','Homogenea de grado 1', 'No es homogenea', 'C');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (38,'Homogenea de Grado 2','Homogenea de grado 1', 'No es homogenea', 'C');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (39,'HOMOGÉNEA DE GRADO a','Homogénea de grado b', 'Homogénea de grado a+b', 'C');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (40,'No es homogénea','Homogénea de grado 2', 'Homogénea de grado 1', 'A');

INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (41,'−15x^(1/5)+C. ','15x^(1/5)+C. ', '15x^(1/5)+C. ', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (42,' (8/9)x^(9/8)+C',' (9/8)x^(8/9)+C', 'NA', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (43,'(x^2/10)+(1/x^2)+2x+C','(x^2/10)+(1/x^2)+C', '(1/x^2)+2x+C', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (44,'(x^2/2) +12x^(1/2)-9/x+C','(x^2/2) +12x^(1/2)-C', '(x^2/2) +x^(1/2)-9/x+C', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (45,'(1+x^4)^(1/4)','(1+x^4)^(1/3)', '(x^4)^(1/4)', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (46,'(1+x^4)^(1/4)',' - (1+x^4)^(1/4)', '(1+x)^(1/4)', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (47,'(1+x^4)^(1/4)','0', '(1+x)^(1/4)', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (48,'2X','(1+x)^(1/4)', '(1+x^2)^(1/4)', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (49,'7/(2/(2*ln(2)))','1/(2/(2*ln(2)))', '1/(4/(2*ln(2)))', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (50,'2^(3/2)','2^(7/2)', '2^(5/2)', 'C');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (51,'2^(3/2)','64', '1/(4/(2*ln(2)))', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (52,'32','64', '51/4', 'C');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (53,'2/(3)^(1/3)','2/(3)^(1/2)', '4/(3)^(1/2)', 'C');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (54,'4/(4)^(1/2)','4/(7)^(1/3)', '4/(7)^(1/2)', 'C');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (55,'tan(3x)','sen(3x)', 'cotg(3x)', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (56,'(2)^(1/2)/(1-4*x^(2))','(2)^(1/2)/(1-2*x^(2))', '(4)^(1/2)/(1-2*x^(2))', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (57,'1/8(ln(5+9e^(2x))+C)','1/8(ln(4+9e^(2x))+C)', '1/8(ln(4+9e^(x))+C)', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (58,'1/3(1/2sin^(-1)(u/2)+C)','1/3(1/2tan^(-1)(u/2)+C)', '1/3(1/4sin^(-1)(u/2)+C)', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (59,'1/3(1/2tan^(-1)(u/2)+C)','(2)^(1/2)', '1/4(1/4sin^(-1)(u/4)+C)', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (60,' -   (2)(u)^(1/2)+C','(2)^(1/2)', '1/4(1/4sin^(-1)(u/4)+C)', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (61,'1/4','1/2', '1/3', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (62,'0','1', '-1', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (63,'ln(3)/ln(2)','ln(2)/ln(3)', '0', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (64,'-2.25','-0.75', '-1.66666666666667', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (65,'1280','5040', '2389', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (66,'720','126', '16', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (67,'5016','1200', '2880', 'C');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (68,'2736','6561', '2634', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (69,'7*pi/4 rad','7*pi/3 rad', '7*pi/6 rad', 'C');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (70,'215','210', '220', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (71,'0','-1', '1', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (72,'117 – 42i.','117 – 44i.', '117 – 23i.', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (73,'-6','-5', '-4', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (74,' - (16)(3)^(1/2)',' - (16)(4)^(1/2)', ' - (16)(3)^(1/4)', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (75,' C(x) = –5x2 – x  – 15','C(x) = –5x2 – 3x– 15', ' C(x) = –x2 – x  – 15', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (76,'-1','C(x) = x6 – 2x5 + 4x4 – 8x3 + 16x2 – 32x + 63', '0', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (78,'3(x – 1/3)(x + 3)','3(x – 1/3)(x^2 + 5)', '3(x – 1/3)(x + 5)', 'C');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (79,'12','17', '20', 'C');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (80,'(-2, 2)','(2, -2)', ' La segunda no es función por lo tanto no hay punto de corte.', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (77,'2x^2 + 30x - 12','2x^2 + 10x - 12', '2x^2 + 10x - 10', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (81,'(1, -1)','(-3, 4)', '(4, -3)', 'C');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (82,'-2','2', '4', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (83,'p las columnas y q las filas','p las filas y q las columnas', 'p x q el orden de la matriz', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (84,'Que tengan el mismo orden','Que sean cuadradas', 'Que sean iguales', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (85,'Cuando tienen el mismo número de filas y de columnas','Cuando el número de filas es mayor que las columnas', 'Cuando el número de columnas es mayor que las filas', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (86,'Que el número de columnas de A debe coincidir con el número de filas de B','Que el número de filas de A debe coincidir con el número de filas de B', 'Que el número de filas de B debe ser mayor que el número de filas de A', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (87,'2','-2', '0', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (88,'El determinante de la matriz obtenida al quitar de A la fila 3 y la columna 4','La matriz obtenida al quitar de A la fila 3 y la columna 4', 'La matriz obtenida al quitar de A el elemento que está en la fila 3 y en la columna 4', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (89,'El determinante de la matriz que se obtiene quitando 3 filas y 3 columnas de A','El determinante de la matriz que se obtiene eligiendo únicamente 3 filas y 3 columnas de A', 'La matriz que se obtiene quitando 3 filas y 3 columnas de A', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (90,'El producto del inverso del determinante de A por su matriz adjunta','El producto del inverso del determinante de A por su matriz adjunta traspuesta', 'La traspuesta de su matriz adjunta', 'C');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (91,'x−3=2y','x+y=3(y+1)', 'x−2(y+1)=1', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (92,'VERDADER','FALSO', 'NA', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (93,'VERDADERO','FALSO', 'NA', 'C');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (94,'-2','1', '3', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (95,'DENSIDAD','FUERZA', 'TEMPERATURA', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (96,'6.2','4.58', '5.38', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (97,'4.3','4.9', '0.7', 'C');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (98,'Vectores paralelos','Vectores en el espacio', 'Vectores Unitarios', 'C');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (99,'u=v+w','u+v+w=0', 'u=v−w', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (100,'r1r2sin(α−β) ','r1r2(cosα+cosβ)', '(r1 +r2)cos(α−β)', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (101,'(B−1,A−1)','(A,B)', '(−B,A)', 'C');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (102,'z es combinacio ́n lineal de u, v, w.','u−z es combinaci ́on lineal de v−z,w−z', 'u, v, w, z son linealmente independientes.', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (103,'Más débiles que los covalentes','Más fuertes que los covalentes', 'Igual de fuertes que los covalentes', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (104,'Elevada fuerza de adhesión','La fuerza de cohesión entre sus moléculas', ' Elevado grado de ionización', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (105,'Muy alta','Baja', 'Muy Baja', 'C');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (106,'Para elevar o bajar su pH','Para que su pH varie con el medio.', 'Para mantener su pH constante.  ', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (107,'celulosa, almidón y glucógeno.','almidón y glucógeno.', 'celulosa y almidón', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (108,'Desoxirribosa','Ribulosa', 'Xilosa', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (109,'Ribosa','Ribulosa', 'Xilosa', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (110,'glucógeno y amilopectina.','ninguno de los anteriores es correcto.', 'celulosa y quitina.', 'C');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (111,'son los lípidos más abundantes y pueden ser sintetizados en la mayor parte de los tejidos del organismo','no son sintetizados en el hígado.', 'consisten en una molécula de glicerol esterificada con dos ácidos grasos.', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (112,'polímeros.','moléculas hidrosolubles.', 'biomoléculas orgánicas.', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (113,'A temperatura ambiente son sólidos','cuando los ácidos grasos esterificados son insaturados.
pueden saponificarse dando lugar a jabones.', 'cumplen funciones diversas y la más importante es la reguladora.', 'C');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (114,'se sintetizan mayoritariamente en la mucosa intestinal.','aunque no forman parte de las lipoproteínas si son importantes para el transporte de los ácidos grasos.', 'se unen a receptores de las células diana para que éstas reciban los lípidos que transportan.', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (115,'Heteroproteínas con un grupo prostético formado por un glúcido unido por puentes de H.','Heteroproteínas con un grupo prostético formado por un péptido de bajo peso molecular unido a un glúcido de alto peso molecular', 'Holoproteínas con un grupo prostético formado por un glúcido unido de forma covalente.', 'C');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (116,'es irreversible siempre.','es reversible por recalentamiento.', 'es irreverible si se ha alterado la estructura primaria.', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (117,'Un D-aminoácido desvía la luz polarizada hacia la derecha.','Todos los aminoácidos proteicos presentan la forma L.', 'tienen al menos un carbono asimétrico.', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (118,'Grupo prostético','Ribulosa', 'Xilosa', 'A');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (119,'sólo intervienen en las reacciones que tienen lugar en los seres vivos ','aumentan la velocidad de las reacciones químicas ', 'acaban por consumirse en el transcurso de una reacción ', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (120,'que se unen a la enzima por su centro activo ','que se consumen rápidamente durante la reacción, pero contribuyen a que sea más rápida ', 'que no hacen posibles las reacciones que no son espontáneas ', 'C');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (121,'permiten que se lleven a cabo aquellas reacciones en las que DG>0',' no se consumen en el transcurso de la reacción ', 'aceleran la velocidad de una reacción porque aumentan su energía de activación ', 'B');
INSERT INTO ALTERNATIVE(id_Question,alternative_A, alternative_B, alternative_C, answer) VALUES (122,'son catalizadores de naturaleza proteica ','hacen posibles reacciones que no son espontáneas ', ' no ven afectada su actividad por los cambios en la temperatura ', 'C');



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
