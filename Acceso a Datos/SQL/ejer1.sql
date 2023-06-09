DROP TABLE TEMP;
CREATE TABLE TEMP(V_ID NUMBER(4), MENSAJE VARCHAR2(20));

DECLARE
	V_ID ARTICULOS.IDART%TYPE:=&ARTICULO;
	V_STOCK ARTICULOS.STOCK%TYPE;
	
BEGIN
	SELECT STOCK INTO V_STOCK FROM ARTICULOS WHERE IDART=V_ID;
	
		IF V_STOCK>0 THEN
			UPDATE  ARTICULOS SET STOCK=V_STOCK-1 WHERE IDART=V_ID;
			INSERT INTO TEMP(ID_ART,MENSAJE) VALUES(V_ID,'VENDIDO');
		ELSE
			INSERT INTO TEMP(ID_ART,MENSAJE) VALUES(V_ID,'SIN EXISTENCIAS');	
		END IF;
END;
/