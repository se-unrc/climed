CREATE TABLE medicamento (
    codigo bigint NOT NULL,
    nombre text,
    stock double precision,
    stock_min double precision,
    vencimiento date,
    unidad text,
	CONSTRAINT pkCodigo PRIMARY KEY (codigo)
);