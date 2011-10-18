-- Base de datos necesaria para Especialidad

--Se necesita la tabla de Medico para conectarse con Especialidad y Matricula.
--Es preciso que climed se adapte al nombre de la base de datos final.

DROP TABLE IF EXISTS climed.Especialidad;
CREATE TABLE  climed.Especialidad (
   NOMBRE varchar(30),	
   ID_ESPECIALIDAD varchar(30),
   PRIMARY KEY (ID_ESPECIALIDAD)
);

DROP TABLE IF EXISTS climed.Matricula;
CREATE TABLE  climed.Matricula (
   NUMERO_MATRICULA varchar(30),
   ID_MEDICO varchar(30),	
   ID_ESPECIALIDAD varchar(30),
   FECHA_DE_OBTENCION date NOT NULL,
   PRIMARY KEY (NUMERO_MATRICULA)
);

BEGIN;

INSERT INTO climed.Especialidad (NOMBRE, ID_ESPECIALIDAD) VALUES
('Traumatologia','1'),
('Cardiología', '2'),
('Pediatría', '3');

COMMIT;
