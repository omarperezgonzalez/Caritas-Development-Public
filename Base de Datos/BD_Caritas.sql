CREATE TABLE [Donadores] (
  [id_donante] numeric PRIMARY KEY,
  [a_paterno] varchar(100) NOT NULL,
  [a_materno] varchar(255) NOT NULL,
  [nombre] varchar(100) NOT NULL,
  [id_titulo] numeric(18) DEFAULT (60),
  [id_tipo_donante] numeric(18) DEFAULT (61),
  [razon_social] varchar(100) NOT NULL,
  [fecha_nac] datetime NOT NULL,
  [email] varchar(100),
  [tel_casa] numeric(18),
  [tel_oficina] numeric(18),
  [tel_movil] numeric(18),
  [estatus_donante] numeric(18),
  [CURP] varchar(100),
  [ultimo_donativo] numeric(18),
  [id_genero] numeric(18)
)
GO

CREATE TABLE [Donativos] (
  [id_donativo] numeric PRIMARY KEY,
  [id_donante] numeric,
  [id_tipo_donativo] numeric(18),
  [id_forma_pago] numeric(18),
  [id_frecuencia] numeric(18),
  [num_frecuencia] numeric(18),
  [pago_unico] numeric(1),
  [importe] float,
  [id_estatus] numeric(18)
)
GO

CREATE TABLE [Bitacora] (
  [id_bitacora] numeric(18) PRIMARY KEY,
  [id_donativo] numeric(18),
  [id_num_pago] numeric(18),
  [fecha_cobro] datetime,
  [fecha_pago] datetime,
  [fecha_visita] datetime,
  [id_forma_pago] numeric(18),
  [importe] float,
  [id_recibo] varchar(50),
  [estatus_pago_tmp] numeric(18),
  [comentarios] varchar(max),
  [fecha_confirmacion] datetime
)
GO

CREATE TABLE [Catalogs] (
  [id] numeric PRIMARY KEY,
  [nombre] varchar(100),
  [llave] varchar(50)
)
GO

CREATE TABLE [Catalogs_Values] (
  [id] numeric PRIMARY KEY,
  [id_catalog] numeric,
  [nombre] varchar(100)
)
GO

ALTER TABLE [Donadores] ADD FOREIGN KEY ([id_titulo]) REFERENCES [Catalogs_Values] ([id_catalog])
GO

ALTER TABLE [Donadores] ADD FOREIGN KEY ([id_tipo_donante]) REFERENCES [Catalogs_Values] ([id_catalog])
GO

ALTER TABLE [Donadores] ADD FOREIGN KEY ([ultimo_donativo]) REFERENCES [Donativos] ([id_donativo])
GO

ALTER TABLE [Donativos] ADD FOREIGN KEY ([id_donante]) REFERENCES [Donadores] ([id_donante])
GO

ALTER TABLE [Bitacora] ADD FOREIGN KEY ([id_donativo]) REFERENCES [Donativos] ([id_donativo])
GO

ALTER TABLE [Catalogs_Values] ADD FOREIGN KEY ([id_catalog]) REFERENCES [Catalogs] ([id])
GO

EXEC sp_addextendedproperty
@name = N'Column_Description',
@value = 'Guarda el titulo de la persona (Sr, Sra, Dr, Ing, etc)',
@level0type = N'Schema', @level0name = 'dbo',
@level1type = N'Table',  @level1name = 'Donadores',
@level2type = N'Column', @level2name = 'id_titulo';
GO

EXEC sp_addextendedproperty
@name = N'Column_Description',
@value = 'Guarda el tipo de donante (Fisico, Moral, ONG, GOB, etc)',
@level0type = N'Schema', @level0name = 'dbo',
@level1type = N'Table',  @level1name = 'Donadores',
@level2type = N'Column', @level2name = 'id_tipo_donante';
GO

EXEC sp_addextendedproperty
@name = N'Column_Description',
@value = '
      Guarda el estatus del donativo, pueden ser 3 (Activo, Cancelado, Terminado)
    ',
@level0type = N'Schema', @level0name = 'dbo',
@level1type = N'Table',  @level1name = 'Donativos',
@level2type = N'Column', @level2name = 'id_estatus';
GO
