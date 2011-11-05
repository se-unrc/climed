DROP DOMAIN IF EXISTS Tcategoria;
CREATE DOMAIN Tcategoria AS int
	DEFAULT 0
	CHECK (value > -1 and value < 4);

DROP TABLE IF EXISTS persona;
CREATE TABLE persona(
	identificador SERIAL,
	ocupacion VarChar(30),
	nombre VarChar(30),
	apellido VarChar(30),
	dni VarChar(11),
	direccion VarChar(50),
	telefono VarChar(30),
	categoria Tcategoria,
	horasExtras int default 0,	
	especialidad VarChar(30),
	borrado boolean default false,
	PRIMARY KEY (identificador)
);
