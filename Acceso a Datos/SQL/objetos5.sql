--Crear el tipo Empleado con los atributos
CREATE OR REPLACE TYPE EMPLEADO AS OBJECT(
	RUT VARCHAR(10), 
	NOMBRE VARCHAR(10),
	CARGO VARCHAR(9),
	FECHAING DATE, 
	SUELDO NUMBER(9), 
	COMISION NUMBER(9),
	ANTICIPO NUMBER(9), 
	MEMBER FUNCTION SUELDO_LIQUIDO RETURN NUMBER,
	MEMBER PROCEDURE AUMENTO_SUELDO(AUMENTO NUMBER)
);
/

--Crear el body para dicho tipo desarrollando la función y el procedimiento.

CREATE OR REPLACE TYPE BODY EMPLEADO AS
	 MEMBER FUNCTION SUELDO_LIQUIDO RETURN NUMBER IS 
	BEGIN
		RETURN (SUELDO+COMISION)-ANTICIPO;
	END;
		MEMBER PROCEDURE AUMENTO_SUELDO(AUMENTO NUMBER)IS
	BEGIN
		SUELDO:=SUELDO+AUMENTO;
	END;
END;
/	

/* Altera el tipo Empleado y añade el procedimiento setAnticipo 
que recibe como parámetro el anticipo de tipo number. */
ALTER TYPE EMPLEADO REPLACE AS OBJECT(
	RUT VARCHAR(10), 
	NOMBRE VARCHAR(10),
	CARGO VARCHAR(9),
	FECHAING DATE, 
	SUELDO NUMBER(9), 
	COMISION NUMBER(9),
	ANTICIPO NUMBER(9), 
	MEMBER FUNCTION SUELDO_LIQUIDO RETURN NUMBER,
	MEMBER PROCEDURE AUMENTO_SUELDO(AUMENTO NUMBER),
	MEMBER PROCEDURE SETANTICIPO(ANTICIPO NUMBER)
);

--Crea el body para el nuevo método setAnticipo
CREATE OR REPLACE TYPE BODY EMPLEADO AS
	 MEMBER FUNCTION SUELDO_LIQUIDO RETURN NUMBER IS 
	BEGIN
		RETURN (SUELDO+COMISION)-ANTICIPO;
	END;
		MEMBER PROCEDURE AUMENTO_SUELDO(AUMENTO NUMBER)IS
	BEGIN
		SUELDO:=SUELDO+AUMENTO;
	END;
		MEMBER PROCEDURE SETANTICIPO(ANTICIPO NUMBER) IS
	BEGIN
	   SELF.ANTICIPO:=ANTICIPO;
	END;   
END;
/	

--Crear una tabla empleados de tipo empleado
DROP TABLE EMPLEADOS;
CREATE TABLE EMPLEADOS OF EMPLEADO;


--Insertar dos o tres empleados, con estos datos
INSERT INTO EMPLEADOS VALUES('1','Pepa','Directora',SYSDATE, 2000,500,0);
INSERT INTO EMPLEADOS VALUES('2','Juana','Comercial',SYSDATE, 1000,300,0);
INSERT INTO EMPLEADOS VALUES('3','Rosa','Comercial',SYSDATE, 1000,400,0);

--Crear un bloque PL/SQL para listar el sueldo liquido del empleado rut= 2, Aumentarle el sueldo con 400 euros 
--Listar el sueldo aumentado
DECLARE
	EMPL EMPLEADO;
BEGIN
	SELECT VALUE(E) INTO EMPL FROM EMPLEADOS E WHERE E.RUT='2';
	DBMS_OUTPUT.PUT_LINE(EMPL.NOMBRE||' '||EMPL.CARGO||' SUELDO: '|| EMPL.SUELDO||' SUELDO LIQUIDO: '||
	EMPL.SUELDO_LIQUIDO());
	EMPL.AUMENTO_SUELDO(400);
	DBMS_OUTPUT.PUT_LINE(EMPL.NOMBRE||' '||EMPL.CARGO||' SUELDO: '|| EMPL.SUELDO||' SUELDO LIQUIDO: '||
	EMPL.SUELDO_LIQUIDO());
END;
/	
-- Persistir en la tabla empleados el sueldo aumentado

DECLARE
	EMPL EMPLEADO;
	AUMENTO NUMBER:=&Introduce_Aumento;
BEGIN
	SELECT VALUE(E) INTO EMPL FROM EMPLEADOS E WHERE E.RUT='2';
	EMPL.AUMENTO_SUELDO(AUMENTO);
	UPDATE EMPLEADOS SET SUELDO=SUELDO+AUMENTO WHERE RUT='2';
	DBMS_OUTPUT.PUT_LINE('HA SIDO ACTUALIZADO EL SUELDO DE  '||EMPL.NOMBRE||' NUEVO SUELDO: '||EMPL.SUELDO);
END;
/	

