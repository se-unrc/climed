Drop TABLE if EXISTS Convenio;
Create TABLE Comidas (
    id serial,
    nombre varchar(45) unique,
    descripcion varchar(200),
    CONSTRAINT pkComidas PRIMARY KEY (id),
);
