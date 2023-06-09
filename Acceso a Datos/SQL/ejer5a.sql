DROP TABLE TEMP6;
CREATE TABLE TEMP6(COD_CLI NUMBER(5), EMPNO NUMBER(4));

DECLARE
	CURSOR C1 IS SELECT EMPNO, CODCLI FROM CLIENTES;
	
	REG V_CLIENTES C1%ROWTYPE;
	
BEGIN
	OPEN C1;
	FETCH C1 INTO V_CLIENTES;
	--LOOP
	LOOP
		
		INSERT INTO TEMP6 VALUES(V_EMPNO, V_CODCLI);
		FETCH C1 INTO REG;
		EXIT WHEN C1%NOTFOUND;
	END LOOP;
 
 	--WHILE
 	WHILE C1%FOUND LOOP
 		INSERT INTO TEMP6 VALUES(V_EMPNO, V_CODCLI);
		FETCH C1 INTO REG;
 	END LOOP;
 
 	--FOR
 	FOR I IN C1 LOOP
 		INSERT INTO TEMP6 VALUES(V_EMPNO, V_CODCLI);
 	END LOOP
	CLOSE C1;
END;
/
