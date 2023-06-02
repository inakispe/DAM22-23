DROP TABLE TEMP3B;
CREATE TABLE TEMP3B(COD_EMP NUMBER(4),NOMBRE VARCHAR2(15),JOB VARCHAR2(10));

DECLARE
	TYPE REG_EMPLE IS RECORD 
	(EMPNO EMP.EMPNO%TYPE,
	ENAME EMP.ENAME%TYPE,
	JOB EMP.JOB%TYPE);

	V_EMPLE REG_EMPLE;
	V_EMPNO EMP.EMPNO%TYPE:=7782;
BEGIN
	SELECT EMPNO, ENAME, JOB INTO V_EMPLE.EMPNO,V_EMPLE.ENAME, V_EMPLE.JOB FROM EMP WHERE EMPNO=V_EMPNO;
	INSERT INTO TEMP3B VALUES( V_EMPLE.EMPNO, V_EMPLE.ENAME, V_EMPLE.JOB);
	
END;
/