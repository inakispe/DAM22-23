--Crear un tipo colección llamado colec_hijos que tendrá
--como máximo grupos de 10 valores y además serán de máximo 30 caracteres
create or replace type colec_hijos as varray(10) of varchar(30);
/

--Crea la tabla empleado
drop table emp6;
create table emp6(
	idemp number,
	nombre varchar(30),
	apellido varchar(30),
	hijos colec_hijos
);
--Insertar los datos que aparecen en la tabla anterior
insert into emp6 values (1, 'Francisco','Perez', colec_hijos('Luis','Ursula'));
insert into emp6 values (2, 'Esperanza','Jimenez', colec_hijos('Jose','Carlos','Pedro'));

--Visualizar todos los empleados.
select e.hijos from emp6 e;
--Visualizar el nombre de los hijos del empleado idemp 1
select e.hijos from emp6 e where idemp=1;



--Crea un bloque PL para visualizar cuántos hijos tiene el empleado idemp=1

SET SERVEROUTPUT ON;

DECLARE
	V_HIJOS COLEC_HIJOS;
	CUENTA NUMBER;
BEGIN
	SELECT E.HIJOS INTO V_HIJOS FROM EMP6 E WHERE E.IDEMP=1;
	CUENTA:=V_HIJOS.COUNT;
	DBMS_OUTPUT.PUT_LINE('TOTAL HIJOS :'||CUENTA);
END;
/ 

--Crea un bloque PL para visualizar el nombre del empleado y el nombre de todos sus hijos
DECLARE
	CURSOR C_HIJOS IS SELECT * FROM EMP6;
	V_ID NUMBER;
	V_NOMBRE VARCHAR(30);
	V_APELLIDO VARCHAR(30);
	V_HIJOS COLEC_HIJOS;
BEGIN
	OPEN C_HIJOS;
	LOOP 
		FETCH C_HIJOS INTO V_ID, V_NOMBRE, V_APELLIDO, V_HIJOS;
		EXIT WHEN C_HIJOS%NOTFOUND;
		DBMS_OUTPUT.PUT_LINE('ID '||V_ID||'NOMBRE '||V_NOMBRE||'APELLIDO '||V_APELLIDO);
		
		FOR I IN V_HIJOS.FIRST ..V_HIJOS.LAST LOOP
			DBMS_OUTPUT.PUT_LINE('EL HIJO '||I||' SE LLAMA '||V_HIJOS(I));
		END LOOP;
	END LOOP;
END;
/

-- Visualizar cuántos hijos tienen todos los empleados.

DECLARE
	V_HIJOS COLEC_HIJOS;
	CUENTA NUMBER;
BEGIN
	SELECT E.HIJOS INTO V_HIJOS FROM EMP6 E WHERE E.IDEMP=1;
	CUENTA:=V_HIJOS.COUNT;
	DBMS_OUTPUT.PUT_LINE('TOTAL HIJOS :'||CUENTA);
END;
/ 

DECLARE
	CURSOR C_HIJOS IS SELECT * FROM EMP6;
	V_ID NUMBER;
	V_NOMBRE VARCHAR(30);
	V_APELLIDO VARCHAR(30);
	V_HIJOS COLEC_HIJOS;
	CUENTA NUMBER;
BEGIN
	OPEN C_HIJOS;
	LOOP 
		FETCH C_HIJOS INTO V_ID, V_NOMBRE, V_APELLIDO, V_HIJOS;
		EXIT WHEN C_HIJOS%NOTFOUND;
		DBMS_OUTPUT.PUT_LINE('ID '||V_ID||'NOMBRE '||V_NOMBRE||'APELLIDO '||V_APELLIDO);
		CUENTA:=V_HIJOS.COUNT;
		DBMS_OUTPUT.PUT_LINE('TOTAL HIJOS '||CUENTA);
	END LOOP;
END;
/
--Añadir un hijo mas al empleado idemp=1 que se llame Antonio
DECLARE
	HIJO COLEC_HIJOS;
BEGIN
	SELECT HIJOS INTO HIJO FROM EMP6 WHERE IDEMP=1;
	FOR I IN HIJO.FIRST ..HIJO.LAST LOOP
		DBMS_OUTPUT.PUT_LINE('EL HIJO: '||I||' SE LLAMA: '||
		HIJO(I));
	END LOOP;
	HIJO.EXTEND;
	HIJO(HIJO.LAST):='ANTONIO';
	DBMS_OUTPUT.PUT_LINE('hijos despues de añadir');
	FOR I IN HIJO.FIRST ..HIJO.LAST LOOP
		DBMS_OUTPUT.PUT_LINE('EL HIJO: '||I||' SE LLAMA: '||
		HIJO(I));
	END LOOP;
END;
/

DECLARE
	V_HIJOS COLEC_HIJOS;
	I NUMBER;
	J NUMBER;
	CUENTA NUMBER;
BEGIN
	SELECT HIJOS INTO V_HIJOS FROM EMP6 E WHERE E.IDEMP=1;
	FOR I IN V_HIJOS.FIRST .. V_HIJOS.LAST LOOP
		DBMS_OUTPUT.PUT_LINE('EL HIJO: '||I||' SE LLAMA: '||
		V_HIJOS(I));
	END LOOP;
	V_HIJOS.EXTEND(3,1);
	FOR J IN v_HIJOS.FIRST .. V_HIJOS.LAST LOOP
		DBMS_OUTPUT.PUT_LINE('EL HIJO: '||I||' SE LLAMA: '||
		V_HIJOS(J));
	END LOOP;
END;
/

CREATE TABLE EMP6_HIJOS(
	IDEMP NUMBER PRIMARY KEY,
	NOMBRE VARCHAR(10), 
	APELLIDO VARCHAR(10), 
	HIJO COLEC_HIJOS
);

INSERT INTO EMP6_HIJOS VALUES(1,'Teresa','Lopez',colec_hijos('Ana', 'Rosa','Pepa'));

DECLARE
	I COLEC_HIJOS;
BEGIN
	SELECT HIJOS INTO I FROM EMP6 WHERE IDEMP=1;
	I.EXTEND;
	I(I.LAST):='ANTONIO';
	UPDATE EMP6_HIJOS SET HIJO=I WHERE IDEMP=1;
END;
/
	