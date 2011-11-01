CREATE SEQUENCE seq_id_convenio;
DROP TABLE IF EXISTS convenio;
CREATE TABLE  convenio (
  id integer NOT NULL DEFAULT NEXTVAL('seq_id_convenio'),
  nro_convenio serial NOT NULL ,
  medico varchar(30) NOT NULL,
  obra_social varchar(30) NOT NULL,
  cobertura float NOT NULL,
  Constraint pkConvenio PRIMARY KEY (id)
);


DROP TABLE if EXISTS ObraSocial;
CREATE TABLE  ObraSocial (
  id integer NOT NULL,
  nombre varchar(30) NOT NULL,
  direccion varchar(50) NOT NULL,
  telefono varchar(25) NOT NULL,
  cuit varchar(13) NOT NULL,
  CONSTRAINT pkObraSocial PRIMARY KEY (id),
  CONSTRAINT uNombre UNIQUE (nombre)
);


DROP TABLE IF EXISTS medicamento;
CREATE TABLE medicamento (
    id bigint NOT NULL,
    nombre text,
    stock double precision,
    stock_min double precision,
    vencimiento date,
    unidad text,
    CONSTRAINT pkCodigo PRIMARY KEY (id)
);

CREATE SEQUENCE seq_id_consulta;
DROP TABLE IF EXISTS consulta;
CREATE TABLE consulta(
  id integer NOT NULL DEFAULT nextval('seq_id_consulta'),
  idMedico integer NOT NULL,
  idPaciente integer NOT NULL,
  fecha date NOT NULL,
  diagnostico character varying(200),
  CONSTRAINT pkconsulta PRIMARY KEY (id)
)




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

