CREATE SEQUENCE siguiente;

CREATE TABLE consulta
(
  idConsulta integer NOT NULL DEFAULT nextval('siguiente'::regclass),
  idMedico integer NOT NULL,
  idPaciente integer NOT NULL,
  fecha date NOT NULL,
  diagnostico character varying(200),
  CONSTRAINT pkconsulta PRIMARY KEY (idConsulta)	
)

