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


CREATE SEQUENCE seq_id_dieta;
DROP TABLE IF EXISTS dieat;
CREATE TABLE dieat(
  id integer NOT NULL DEFAULT nextval('seq_id_dieta'),
  codigo integer NOT NULL,
  deayuno varchar(50),
  almuerzo varchar(50),
  merienda  varchar(50),
  cena varchar(50),
  CONSTRAINT pkconsulta PRIMARY KEY (id)
)
