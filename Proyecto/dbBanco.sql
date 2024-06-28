CREATE DATABASE `bdbanco`;
USE bdbanco; 
 
 
-- Crear tabla TipoUsuario
CREATE TABLE TipoUsuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(50) NOT NULL
);


-- Insertar valores en TipoUsuario
INSERT INTO TipoUsuario (descripcion) VALUES
('administrador'),
('cliente');


-- Crear tabla Usuarios
CREATE TABLE Usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dni VARCHAR(20) UNIQUE NOT NULL,
    cuil varchar(20) UNIQUE NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    sexo ENUM('M','F','X') NOT NULL,
    Celular  VARCHAR(100) NOT NULL,
    Telefono VARCHAR(100) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    localidad VARCHAR(100) NOT NULL,
    provincia VARCHAR(100) NOT NULL,
    nacionalidad VARCHAR(50) NOT NULL,
    fecha_nacimiento DATE NOT NULL CHECK(fecha_nacimiento<CURDATE()),
    email VARCHAR(100) UNIQUE NOT NULL,
    usuario VARCHAR(50) UNIQUE NOT NULL CHECK(CHAR_LENGTH(usuario)>=4),
    contrasenia VARCHAR(255) NOT NULL CHECK(CHAR_LENGTH(contrasenia)>=4),
    tipo_usuario_id INT NOT NULL default 2,
    habilitado INT NOT NULL DEFAULT 1 CHECK(habilitado in(0,1)),
    FOREIGN KEY (tipo_usuario_id) REFERENCES TipoUsuario(id)
);


-- Se inserta un usuario administrador para realizar pruebas
INSERT INTO Usuarios(dni,cuil,nombre,apellido,sexo,Celular,Telefono,direccion,localidad,provincia,
nacionalidad,fecha_nacimiento,email,usuario,contrasenia,tipo_usuario_id)
VALUES('11111111','20111111114','Jose','Pruebas','M','11','11','Calle Falsa 123','Buenos Aires','Buenos Aires',
'Argentina','1990-03-20','pruebas@hotmail.com','Administrador','Admin2024',1);





-- Crear tabla TipoCuenta
CREATE TABLE TipoCuenta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(50) NOT NULL
);


-- Insertar valores en TipoCuenta
INSERT INTO TipoCuenta (descripcion) VALUES
('caja de ahorro'),
('cuenta corriente');


-- Crear tabla Cuentas
CREATE TABLE Cuentas (
    numero_cuenta INT AUTO_INCREMENT PRIMARY KEY,
    cliente_dni  VARCHAR(20) NOT NULL,
    fecha_creacion DATE NOT NULL CHECK(fecha_creacion<=CURDATE()),
    tipo_cuenta_id INT NOT NULL,
    cbu VARCHAR(22) UNIQUE,
    saldo DECIMAL(10, 2) NOT NULL DEFAULT 10000.00 CHECK(saldo >=0),
	habilitado INT NOT NULL DEFAULT 1 CHECK(habilitado in(0,1)),
    FOREIGN KEY (cliente_dni) REFERENCES Usuarios (dni),
    FOREIGN KEY (tipo_cuenta_id) REFERENCES TipoCuenta(id)
);


-- Crear tabla TipoMovimiento
CREATE TABLE TipoMovimiento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(50) NOT NULL
);


-- Insertar valores en TipoMovimiento
INSERT INTO TipoMovimiento (descripcion) VALUES
('alta cuenta'),
('alta prestamo'),
('pago prestamo'),
('transferencia');


-- Crear tabla Movimientos
CREATE TABLE Movimientos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    numero_cuenta INT NOT NULL,
    fecha DATE NOT NULL,
    detalle VARCHAR(255) NOT NULL,
    importe DECIMAL(10, 2) NOT NULL CHECK (importe>0),
    tipo_movimiento_id INT NOT NULL,
    FOREIGN KEY (numero_cuenta) REFERENCES Cuentas(numero_cuenta),
    FOREIGN KEY (tipo_movimiento_id) REFERENCES TipoMovimiento(id)
);


CREATE TABLE EstadosPrestamos(
	 id INT AUTO_INCREMENT PRIMARY KEY,
     descripcion VARCHAR (20) NOT NULL
);



INSERT INTO EstadosPrestamos (descripcion) VALUES
	('Solicitado'),
    ('En Analisis'),
    ('Aprobado'),
    ('Rechazado'),
    ('Abonado');
    


-- Crear tabla Prestamos
CREATE TABLE Prestamos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT NOT NULL,
    fecha DATE NOT NULL,
    importe_solicitado DECIMAL(10, 2) NOT NULL CHECK(importeSolicitado >0),
    importe_a_pagar DECIMAL(10, 2) NOT NULL CHECK(importe_a_pagar >=0),
    importe_cuota DECIMAL(10, 2) NOT NULL CHECK(importe_cuota >0),
    cuotas INT NOT NULL DEFAULT 1 CHECK(cuotas >0),
    estado INT NOT NULL DEFAULT 1,
    cuotas_abonadas INT NOT NULL DEFAULT 0 CHECK(cuotas_abonadas >=0),
    saldo_restante DECIMAL(10, 2) NOT NULL CHECK(saldo_restante >= 0),
    FOREIGN KEY (cliente_id) REFERENCES Usuarios(id),
    FOREIGN KEY (estado) REFERENCES EstadosPrestamos(id)
);


DELIMITER //

CREATE TRIGGER generar_cbu
BEFORE INSERT ON Cuentas
FOR EACH ROW
BEGIN
    DECLARE ultimo_id INT;
    
    
    SELECT MAX(numero_cuenta) INTO ultimo_id FROM Cuentas;
    
   
    IF ultimo_id IS NULL THEN
        SET ultimo_id = 1;
    END IF;
    
   
    SET NEW.cbu = CONCAT('000', ultimo_id + 1);
END //

DELIMITER ;


