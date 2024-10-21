use hotel;

CREATE TABLE categoria_cama(
    id_categoria_cama INT NOT NULL,
    tipo VARCHAR(10) NOT NULL,
    medidas VARCHAR(50) NOT NULL,
    foto_url VARCHAR(200) NOT NULL,
    color VARCHAR(30) NOT NULL,
    PRIMARY KEY(id_categoria_cama)
)
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_unicode_ci;

CREATE TABLE cama(
    id_cama INT NOT NULL,
    recibo_url VARCHAR(100) NOT NULL,
    fecha_cama  DATE NOT NULL,
    id_categoriacama INT NOT NULL,
    PRIMARY KEY(id_cama),
    CONSTRAINT `cama_categoria_fk`
        FOREIGN KEY(id_categoriacama)
        REFERENCES categoria_cama (id_categoria_cama)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
)
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_unicode_ci;

CREATE TABLE categoria_habitacion(
    id_categoria_habitacion INT NOT NULL,
    nombre VARCHAR(30) NOT NULL,
    precio_noche DOUBLE NOT NULL,
    foto_url VARCHAR(100) NOT NULL,
    PRIMARY KEY(id_categoria_habitacion)
)
DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



CREATE TABLE categoria_cama_habitacion( 
    codigo_categoriacama INT NOT NULL,
    codigo_categoriahabitacion INT NOT NULL,
    cantidad SMALLINT NOT NULL,
    PRIMARY KEY(codigo_categoriacama, codigo_categoriahabitacion),
    CONSTRAINT `categoriacamahabitacion_categoriacama_fk`
        FOREIGN KEY(codigo_categoriacama)
        REFERENCES categoria_cama (id_categoria_cama)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    CONSTRAINT `categoriacamahabitacion_categoriahabitacion_fk`
        FOREIGN KEY(codigo_categoriahabitacion)
        REFERENCES categoria_habitacion (id_categoria_habitacion)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
)
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_unicode_ci;



CREATE TABLE habitacion(
    id_habitacion INT NOT NULL,
    piso INT NOT NULL,
    telefono INT NOT NULL,
    id_categoria INT NOT NULL,
    PRIMARY KEY(id_habitacion),
    CONSTRAINT `habitacion_categoriahabitacion_fk`
        FOREIGN KEY(id_categoria)
        REFERENCES categoria_habitacion(id_categoria_habitacion)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_unicode_ci;



CREATE TABLE cama_habitacion(
    id_cama INT NOT NULL,
    id_habitacion INT NOT NULL,
    estado VARCHAR(10),
    PRIMARY KEY(id_cama),
    CONSTRAINT `camahabitacion_habitacion_fk`
        FOREIGN KEY(id_habitacion)
        REFERENCES habitacion(id_habitacion)
        ON DELETE NO ACTION 
        ON UPDATE NO ACTION
)
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_unicode_ci;



CREATE TABLE cliente(
    cedula INT NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    tipo_identificacion VARCHAR(10),
    PRIMARY KEY(cedula)
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;




CREATE TABLE factura(
    id_factura BIGINT NOT NULL,
    total DOUBLE NOT NULL,
    subtotal DOUBLE NOT NULL,
    iva DOUBLE not null,
    inc DOUBLE NOT NULL,
    descuento DOUBLE NOT NULL,
    categoria VARCHAR(20) NOT NULL,
    fecha_hora DATE NOT NULL,
    PRIMARY KEY(id_factura)
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



CREATE TABLE reserva(
    id_reserva BIGINT NOT NULL,
    fecha_reserva DATE NOT NULL,
    estado VARCHAR(10),
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    id_cliente INT NOT NULL,
    id_factura BIGINT NOT NULL,
    PRIMARY KEY(id_reserva),
    CONSTRAINT `reserva_cliente_fk`
        FOREIGN KEY(id_cliente)
        REFERENCES cliente(cedula)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    CONSTRAINT `reserva_factura_fk`
        FOREIGN KEY(id_factura)
        REFERENCES factura(id_factura)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
)
DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



CREATE TABLE huesped(
    cedula INT NOT NULL,
    nombre VARCHAR(30) NOT NULL,
    apellido VARCHAR(30) NOT NULL,
    tipo_identificacion VARCHAR(10),
    PRIMARY KEY(cedula)
)
DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



CREATE TABLE reserva_huesped(
    id_reserva BIGINT NOT NULL,
    id_huesped INT NOT NULL,
    fecha_reserva DATE NOT NULL,
    PRIMARY KEY(id_reserva,id_huesped),
    CONSTRAINT `reserva_huesped_reserva_fk`
        FOREIGN KEY(id_reserva)
        REFERENCES reserva(id_reserva)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    CONSTRAINT `reserva_huesped_huesped_fk`
        FOREIGN KEY(id_huesped)
        REFERENCES huesped(cedula)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
)
DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



CREATE TABLE reserva_habitacion(
    id_reserva BIGINT NOT NULL,
    id_habitacion INT NOT NULL,
    cantidad INT NOT NULL,
    PRIMARY KEY(id_reserva,id_habitacion),
    CONSTRAINT `reserva_habitacion_reserva_fk`
        FOREIGN KEY(id_reserva)
        REFERENCES reserva(id_reserva)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    CONSTRAINT `reserva_habitacion_habitacion_fk`
        FOREIGN KEY(id_habitacion)
        REFERENCES habitacion(id_habitacion)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
)
DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;




CREATE TABLE recepcion_habitacion(
    codigo_recepcion_habitacion INT NOT NULL,
    fecha DATE NOT NULL,
    id_habitacion INT NOT NULL,
    categoria VARCHAR(8) NOT NULL,
    PRIMARY KEY(codigo_recepcion_habitacion),
    CONSTRAINT `recepcion_habitacion_habitacion_fk`
        FOREIGN KEY(id_habitacion)
        REFERENCES habitacion(id_habitacion)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
)
DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;




CREATE TABLE recepcion_huesped(
    id_recepcion INT NOT NULL,
    id_huesped INT NOT NULL,
    fecha_recepcion DATE NOT NULL,
    PRIMARY KEY(id_recepcion,id_huesped),
    CONSTRAINT `recepcion_huesped_recepcion_habitacion_fk`
        FOREIGN KEY(id_recepcion)
        REFERENCES recepcion_habitacion(codigo_recepcion_habitacion)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    CONSTRAINT `recepcion_huesped_huesped_fk`
        FOREIGN KEY(id_huesped)
        REFERENCES huesped(cedula)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
)
DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



