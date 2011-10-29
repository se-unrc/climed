CREATE SEQUENCE seq_id_convenio;
DROP TABLE IF EXISTS convenio;
CREATE TABLE  convenio (
  id integer NOT NULL DEFAULT NEXTVAL('seq_id_convenio'),
  nro_convenio serial NOT NULL ,
  medico varchar(30) NOT NULL,
  obra_social varchar(30) NOT NULL,
  cobertura float NOT NULL,
  Constraint pkConvenio PRIMARY KEY (id)
 ) ;


CREATE SEQUENCE seq_id_medicamento;
DROP TABLE IF EXISTS medicamento;
CREATE TABLE medicamento (
    id bigint NOT NULL DEFAULT nextval('seq_id_medicamento'),
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

