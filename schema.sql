drop TABLE if EXISTS Convenio;
create TABLE  convenio (
  nro_convenio serial NOT NULL ,
  medico varchar(30) NOT NULL,
  obra_social varchar(30) NOT NULL,
  cobertura float NOT NULL,
  Constraint pkConvenio PRIMARY KEY (medico,obra_social)
 ) ;


drop TABLE if EXISTS Medicamento;
CREATE TABLE Medicamento (
    id bigint NOT NULL,
    nombre text,
    stock double precision,
    stock_min double precision,
    vencimiento date,
    unidad text,
    CONSTRAINT pkCodigo PRIMARY KEY (id)
);

