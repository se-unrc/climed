DROP DOMAIN IF EXISTS Tcategoria;
CREATE DOMAIN Tcategoria AS int
	DEFAULT 0
	CHECK (value>-1 and value<4);

DROP TABLE IF EXISTS persona;
CREATE TABLE persona(
	numero SERIAL,
	nombre VarChar(30),
	apellido VarChar(30),
	dni VarChar(11),
	direccion VarChar(50),
	telefono VarChar(30),
	PRIMARY KEY (numero)
);

DROP TABLE IF EXISTS personal;
CREATE TABLE personal(
	identificador SERIAL,
	numero int,
	PRIMARY KEY (identificador),
	CONSTRAINT FK_id FOREIGN KEY (numero) REFERENCES  persona (numero) ON UPDATE CASCADE ON DELETE RESTRICT
);

DROP TABLE IF EXISTS paciente;
CREATE TABLE paciente(
	numero int,
	PRIMARY KEY (numero),
	CONSTRAINT FK_id FOREIGN KEY (numero) REFERENCES  persona (numero) ON UPDATE CASCADE ON DELETE RESTRICT
);

DROP TABLE IF EXISTS enfermero;
CREATE TABLE enfermero(
	numero int,
	categoria Tcategoria,
	PRIMARY KEY (numero),
	CONSTRAINT FK_id FOREIGN KEY (numero) REFERENCES  personal (identificador) ON UPDATE CASCADE ON DELETE RESTRICT
);

DROP TABLE IF EXISTS personal_administrativo;
CREATE TABLE personal_administrativo(
	numero int,
	PRIMARY KEY (numero),
	CONSTRAINT FK_id FOREIGN KEY (numero) REFERENCES  personal (identificador) ON UPDATE CASCADE ON DELETE RESTRICT
);

DROP TABLE IF EXISTS hora_extra;
CREATE TABLE hora_extra(
	numero SERIAL,
	horas int,
	fecha VarChar(30),
	PRIMARY KEY (numero)
);

DROP TABLE IF EXISTS hora_extra_trabajada;
CREATE TABLE hora_extra_trabajada(
	numero_hora int,
	numero_personal int,
	PRIMARY KEY (numero_hora, numero_personal),
	CONSTRAINT FK_hora FOREIGN KEY (numero_hora) REFERENCES  hora_extra (numero) ON UPDATE CASCADE ON DELETE RESTRICT,
	CONSTRAINT FK_personal FOREIGN KEY (numero_personal) REFERENCES  personal_administrativo (numero) ON UPDATE CASCADE ON DELETE RESTRICT
	
);

DROP TABLE IF EXISTS medico;
CREATE TABLE medico(
	id SERIAL,
	numero int,
	PRIMARY KEY (id),
	CONSTRAINT FK_personal FOREIGN KEY (numero) REFERENCES  personal (identificador) ON UPDATE CASCADE ON DELETE RESTRICT
);
