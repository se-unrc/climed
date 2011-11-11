--DROP SCHEMA IF EXISTS climed;
--CREATE SCHEMA climed;

--DROP TABLE IF EXISTS climed.convenio;
CREATE TABLE  climed.convenio (
  id serial,
  medico varchar(30) NOT NULL,
  obra_social varchar(30) NOT NULL,
  cobertura float NOT NULL,
  Constraint pkConvenio PRIMARY KEY (id)
);


--DROP TABLE if EXISTS climed.ObraSocial;
CREATE TABLE  climed.ObraSocial (
  id integer NOT NULL,
  nombre varchar(30) NOT NULL,
  direccion varchar(50) NOT NULL,
  telefono varchar(25) NOT NULL,
  cuit varchar(13) NOT NULL,
  CONSTRAINT pkObraSocial PRIMARY KEY (id),
  CONSTRAINT uNombre UNIQUE (nombre)
);


--DROP TABLE IF EXISTS climed.medicamento;
CREATE TABLE climed.medicamento (
    id bigint NOT NULL,
    nombre text,
    stock double precision,
    stock_min double precision,
    vencimiento date,
    unidad text,
    CONSTRAINT pkCodigo PRIMARY KEY (id)
);

--DROP TABLE IF EXISTS climed.consulta;
CREATE TABLE climed.consulta(
  id serial,
  idMedico integer NOT NULL,
  idPaciente integer NOT NULL,
  fecha date NOT NULL,
  diagnostico character varying(200),
  CONSTRAINT pkconsulta PRIMARY KEY (id)
);


--DROP TABLE IF EXISTS climed.dieta;
CREATE TABLE climed.dieta(
  id serial,
  codigo integer NOT NULL,
  deayuno varchar(50),
  almuerzo varchar(50),
  merienda  varchar(50),
  cena varchar(50),
  CONSTRAINT pkdieta PRIMARY KEY (id)
);

--DROP TABLE IF EXISTS climed.habitacion;
CREATE TABLE climed.habitacion(
  id serial,
  nroHabitacion integer,
  fecha date,
  nroPiso integer,
  CONSTRAINT pkhabitacion PRIMARY KEY (id)
);

--DROP DOMAIN IF EXISTS climed.Tcategoria;
CREATE DOMAIN climed.Tcategoria AS int
        DEFAULT 0
        CHECK (value > -1 and value < 4);

--DROP TABLE IF EXISTS climed.persona;
CREATE TABLE climed.persona(
        identificador SERIAL,
        ocupacion VarChar(30),
        nombre VarChar(30),
        apellido VarChar(30),
        dni VarChar(11),
        direccion VarChar(50),
        telefono VarChar(30),
        categoria climed.Tcategoria,
        horasExtras int default 0,
        especialidad VarChar(30),
        borrado boolean default false,
        PRIMARY KEY (identificador)
);

--DROP TABLE IF EXISTS climed.especialidad;
CREATE TABLE  climed.especialidad (
   NOMBRE varchar(30),	
   ID_ESPECIALIDAD varchar(30),
   PRIMARY KEY (ID_ESPECIALIDAD)
);
--DROP TABLE IF EXISTS climed.matricula;
CREATE TABLE  climed.matricula (
   NUMERO_MATRICULA varchar(30),
   ID_MEDICO varchar(30),	
   ID_ESPECIALIDAD varchar(30),
   FECHA_DE_OBTENCION varchar(30) NOT NULL,
   PRIMARY KEY (NUMERO_MATRICULA)
);

BEGIN;

INSERT INTO climed.especialidad (NOMBRE, ID_ESPECIALIDAD) VALUES
('Traumatologia','1'),
('Cardiología', '2'),
('Pediatría', '3');

COMMIT;
