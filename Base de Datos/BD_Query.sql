EXEC sp_msforeachtable 'DELETE FROM ?' < - Por si se quieren borrar todos los datos (En caso de que algo salga mal.)

--- Tabla de Administrador
CREATE TABLE Administrador (
    admin_id NUMERIC PRIMARY KEY,
    email varchar (100) NOT NULL,
    name varchar (100) NOT NULL, 
    password varchar(100) NOT NULL,
    lastName varchar(50) NOT NULL, 
)

-- -- -- ADMIN 
INSERT INTO Administrador(admin_id, email, name, password, lastName)
VALUES 
    (1, 'adminsupremo@hotmail.com', 'Kanye', 'admin123', 'West')

-- -- -- PRIMER USUARIO

INSERT INTO Donativos(id_donativo, id_tipo_donativo, id_forma_pago, id_frecuencia, num_frecuencia, pago_unico, importe, id_estatus)
VALUES 
    (3, 2, 1, 3, 3, 1, 300.0, 1)

INSERT INTO Donadores(id_donante, a_paterno, a_materno, nombre, razon_social, fecha_nac, email, password, tel_movil, estatus_donante, CURP, ultimo_donativo, id_genero, id_tipo_donante)
VALUES 
    (4, 'Borbolla', 'Franco', 'Jorge', 'Banco de Alimentos', 2000-05-15, 'jorgeluisborbolla@hotmail.com', 'Caretaker6763', 8444643938, 1, 'CURPBOFJ000515HCLRRRA0', 3, 1, 3)

INSERT INTO Bitacora(id_bitacora, id_num_pago, fecha_cobro, fecha_pago, fecha_visita, id_forma_pago, importe, id_recibo, estatus_pago_tmp, comentarios, fecha_confirmacion)
VALUES 
    (3, 2, 2021-11-20, 2021-11-20, 2021-11-20, 1, 300.0, 1, 1, 'El pago se realizo de forma exitosa.', '2021-11-20')

INSERT INTO Catalogs(id, nombre, llave)
VALUES 
    (1, 'Titulo:', 3)

INSERT INTO Catalogs_Values(id, id_catalog, nombre)
VALUES
    (1, 1, 'Sr.')

INSERT INTO Catalogs(id, nombre, llave)
VALUES 
    (3, 'Tipo de Donante:', 4)

INSERT INTO Catalogs_Values(id, id_catalog, nombre)
VALUES
    (2, 3, 'Fisico')

-- PRIMER USUARIO: Segundo Donativo
INSERT INTO Donativos(id_donativo, id_donante ,id_tipo_donativo, id_forma_pago, id_frecuencia, num_frecuencia, pago_unico, importe, id_estatus)
VALUES 
    (7, 4, 2, 1, 3, 3, 1, 200.0, 1)

INSERT INTO Bitacora(id_bitacora, id_donativo, id_num_pago, fecha_cobro, fecha_pago, fecha_visita, id_forma_pago, importe, id_recibo, estatus_pago_tmp, comentarios, fecha_confirmacion)
VALUES 
    (7, 7, 2, 2021-21-20, 2021-21-20, 2021-11-20, 1, 300.0, 1, 1, 'El pago se realizo de forma exitosa.', 2021-21-20)

PRIMER USUARIO: Tercer Donativo
INSERT INTO Donativos(id_donativo, id_donante ,id_tipo_donativo, id_forma_pago, id_frecuencia, num_frecuencia, pago_unico, importe, id_estatus)
VALUES 
    (8, 3, 2, 1, 3, 3, 1, 200.0, 1)

INSERT INTO Bitacora(id_bitacora, id_donativo, id_num_pago, fecha_cobro, fecha_pago, fecha_visita, id_forma_pago, importe, id_recibo, estatus_pago_tmp, comentarios, fecha_confirmacion)
VALUES 
    (8, 8, 2, 2021-11-20, 2021-21-20, 2021-11-20, 1, 100.0, 1, 1, 'El pago se realizo de forma exitosa.', 2021-21-20)

-- PRIMER USUARIO: Cuarto Donativo
INSERT INTO Donativos(id_donativo, id_donante ,id_tipo_donativo, id_forma_pago, id_frecuencia, num_frecuencia, pago_unico, importe, id_estatus)
VALUES 
    (10, 3, 2, 1, 3, 3, 1, 500.0, 1)

INSERT INTO Bitacora(id_bitacora, id_donativo, id_num_pago, fecha_cobro, fecha_pago, fecha_visita, id_forma_pago, importe, id_recibo, estatus_pago_tmp, comentarios, fecha_confirmacion)
VALUES 
    (13, 10, 2, 2021-11-13, 2021-11-13, 2021-11-13, 1, 500.0, 1, 1, 'El pago se realizo de forma exitosa.', 2021-11-14)

-- PRIMER USUARIO: Quinto Donativo
INSERT INTO Donativos(id_donativo, id_donante ,id_tipo_donativo, id_forma_pago, id_frecuencia, num_frecuencia, pago_unico, importe, id_estatus)
VALUES 
    (14, 3, 2, 1, 3, 3, 1, 100.0, 1)

INSERT INTO Bitacora(id_bitacora, id_donativo, id_num_pago, fecha_cobro, fecha_pago, fecha_visita, id_forma_pago, importe, id_recibo, estatus_pago_tmp, comentarios, fecha_confirmacion)
VALUES 
    (12, 14, 2, 2021-10-1, 2021-10-1, 2021-10-1, 1, 100.0, 1, 1, 'El pago se realizo de forma exitosa.', 2021-10-2)

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 

-- -- -- SEGUNDO USUARIO
INSERT INTO Donativos(id_donativo, id_tipo_donativo, id_forma_pago, id_frecuencia, num_frecuencia, pago_unico, importe, id_estatus)
VALUES 
    (5, 2, 1, 3, 3, 1, 200.0, 1)

-- INSERT INTO Donadores(id_donante, a_paterno, a_materno, nombre, razon_social, fecha_nac, email, password, tel_movil, estatus_donante, CURP, ultimo_donativo, id_genero, id_tipo_donante)
-- VALUES 
--     (3, 'Gonzalez', 'Perez', 'Omar', 'Banco de Ropa', 2001-01-03, 'omarperez@gmail.com', 'VacasVaqueras4853', 8443254356, 1, 'PEGO010103HMNRNMA6', 5, 1, 3)

INSERT INTO Bitacora(id_bitacora, id_num_pago, fecha_cobro, fecha_pago, fecha_visita, id_forma_pago, importe, id_recibo, estatus_pago_tmp, comentarios, fecha_confirmacion)
VALUES 
    (5, 4, 2021-07-04, 2021-07-04, 2021-07-04, 1, 200.0, 3, 1, 'El pago se hizo exitosamente', 2021-07-05)

INSERT INTO Catalogs(id, nombre, llave)
VALUES 
    (4, 'Titulo:', 3)

INSERT INTO Catalogs_Values(id, id_catalog, nombre)
VALUES
    (2, 4, 'Ing.')

INSERT INTO Catalogs(id, nombre, llave)
VALUES 
    (5, 'Tipo de Donante:', 2)

INSERT INTO Catalogs_Values(id, id_catalog, nombre)
VALUES
    (2, 3, 'Fisico')


-- SEGUNDO USUARIO: Segundo Donativo
INSERT INTO Donativos(id_donativo, id_tipo_donativo, id_forma_pago, id_frecuencia, num_frecuencia, pago_unico, importe, id_estatus)
VALUES 
    (6, 2, 1, 3, 3, 1, 300.0, 1)

INSERT INTO Bitacora(id_bitacora, id_donativo ,id_num_pago, fecha_cobro, fecha_pago, fecha_visita, id_forma_pago, importe, id_recibo, estatus_pago_tmp, comentarios, fecha_confirmacion)
VALUES 
    (6, 6, 4, 2021-10-13, 2021-10-13, 2021-10-04, 1, 300.0, 3, 1, 'El pago se hizo exitosamente', 2021-10-14)


SEGUNDO USUARIO: Tercer Donativo
INSERT INTO Donativos(id_donativo, id_tipo_donativo, id_forma_pago, id_frecuencia, num_frecuencia, pago_unico, importe, id_estatus)
VALUES 
    (12, 2, 1, 3, 3, 1, 300.0, 1)

INSERT INTO Bitacora(id_bitacora, id_donativo ,id_num_pago, fecha_cobro, fecha_pago, fecha_visita, id_forma_pago, importe, id_recibo, estatus_pago_tmp, comentarios, fecha_confirmacion)
VALUES 
    (9, 12, 4, 2021-11-02021-11-05, 2021-11-05, 2021-10-04, 1, 300.0, 3, 1, 'El pago se hizo exitosamente', 2021-11-06)

SEGUNDO USUARIO: Cuarto Donativo
INSERT INTO Donativos(id_donativo, id_tipo_donativo, id_forma_pago, id_frecuencia, num_frecuencia, pago_unico, importe, id_estatus)
VALUES 
    (16, 2, 1, 3, 3, 1, 200.0, 1)

INSERT INTO Bitacora(id_bitacora, id_donativo ,id_num_pago, fecha_cobro, fecha_pago, fecha_visita, id_forma_pago, importe, id_recibo, estatus_pago_tmp, comentarios, fecha_confirmacion)
VALUES 
    (14, 16, 4, 2021-10-1, 2021-10-1, 2021-10-1, 1, 200.0, 3, 1, 'El pago se hizo exitosamente', 2021-10-2)


Query para datos 
SELECT 
    nombre, 
    a_paterno, 
    importe, 
    fecha_pago
FROM Donadores 
JOIN Bitacora 
    ON 
SELECT fecha_pago FROM Bitacora INNER JOIN Donativos ON Donativos.id_donante = Donante.id_donante INNER JOIN Bitacora ON Bitacora.id_donativo=Donativos.id_donante

SELECT 
    fecha_pago,
    id_donante, 
    importe
FROM Bitacora AS bt 
    INNER JOIN
        Donativos as dn
    ON bt.id_bitacora = dn.id_donativo
    INNER JOIN 
        Donadores as don
    ON dn.id_donativo = don.id_donante

SELECT b.importe, fecha_pago  FROM Bitacora AS b
	INNER JOIN
	Donativos as d
	ON b.id_donativo = d.id_donativo
	INNER JOIN
	Donadores as a
	ON d.id_donante = a.id_donante
	WHERE a.id_donante = 4


SELECT id_donante FROM Donadores
