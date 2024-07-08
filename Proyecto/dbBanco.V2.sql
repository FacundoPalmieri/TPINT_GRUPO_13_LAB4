CREATE DATABASE `bdbanco`;
USE bdbanco; 
 
 CREATE TABLE Paises (
    id INT PRIMARY KEY AUTO_INCREMENT,
    Nombre VARCHAR(50) NOT NULL
);

CREATE TABLE Provincia (
    id INT PRIMARY KEY AUTO_INCREMENT,
    Nombre VARCHAR(50) NOT NULL,
    Paises_id INT NOT NULL,
    FOREIGN KEY (Paises_id) REFERENCES Paises(id)
);


INSERT INTO Provincia (Nombre) VALUES ('Buenos Aires'),('Ciudad Autonoma de Buenos Aires');



CREATE TABLE Localidad (
    id INT PRIMARY KEY AUTO_INCREMENT,
    Nombre VARCHAR(50) NOT NULL,
    Provincia_id INT NOT NULL,
    FOREIGN KEY (Provincia_id) REFERENCES Provincia(id)
);

INSERT INTO Localidad (Nombre, Provincia_id) VALUES 
('Agronomia', 2), 
('Almagro', 2), 
('Balvanera', 2), 
('Barracas', 2), 
('Belgrano', 2), 
('Boedo', 2), 
('Caballito', 2), 
('Chacarita', 2), 
('Coghlan', 2), 
( 'Colegiales', 2), 
('Constitucion', 2), 
('Flores', 2), 
('Floresta', 2), 
('La Boca', 2), 
('La Paternal', 2), 
('Liniers', 2), 
('Mataderos', 2), 
('Monte Castro', 2), 
('Monserrat', 2), 
('Nueva Pompeya', 2), 
('Núñez', 2), 
('Palermo', 2), 
('Parque Avellaneda', 2), 
('Parque Chacabuco', 2), 
('Parque Chas', 2), 
('Parque Patricios', 2), 
('Puerto Madero', 2), 
('Recoleta', 2), 
('Retiro', 2), 
('Saavedra', 2), 
('San Cristobal', 2), 
('San Nicolas', 2), 
('San Telmo', 2), 
('Velez sarsfield', 2), 
('Versalles', 2), 
('Villa Crespo', 2), 
('Villa del Parque', 2), 
('Villa Devoto', 2), 
('Villa General Mitre', 2), 
('Villa Lugano', 2), 
('Villa Luro', 2), 
('Villa Ortuzar', 2), 
('Villa Pueyrredón', 2), 
('Villa Real', 2), 
('Villa Riachuelo', 2), 
('Villa Santa Rita', 2), 
('Villa Soldati', 2), 
('Villa Urquiza', 2);

INSERT INTO Localidad (Nombre, Provincia_id) VALUES 
('La Matanza', 1),
('Lomas de Zamora', 1),
('Quilmes', 1),
('Avellaneda', 1),
('Moron', 1),
('Tres de Febrero', 1),
('San Isidro', 1),
('San Fernando', 1),
('Tigre', 1),
('San Miguel', 1),
('Pilar', 1),
('Escobar', 1),
('Merlo', 1),
('Ituzaingo', 1),
('Vicente Lopez', 1),
('Berazategui', 1),
('Florencio Varela', 1),
('General Rodriguez', 1),
('Hurlingham', 1),
('Jose C. Paz', 1),
('Malvinas Argentinas', 1),
('San Martin', 1),
('Berisso', 1),
('Ensenada', 1),
('Ezeiza', 1),
('General Pacheco', 1),
('General San Martin', 1),
('Lanus', 1),
('Marcos Paz', 1),
('Moreno', 1),
('Rafael Castillo', 1),
('San Justo', 1),
('La Plata', 1);

CREATE TABLE Direccion (
    id INT PRIMARY KEY AUTO_INCREMENT,
    Calle VARCHAR(100),
    Numero INT,
    Piso VARCHAR(10),
    Departamento VARCHAR(10),
    Localidad_id INT,
    FOREIGN KEY (Localidad_id) REFERENCES Localidad(id)
);
-- Crear tabla TipoUsuario
CREATE TABLE TipoUsuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(50) NOT NULL
);


-- Insertar valores en TipoUsuario
INSERT INTO TipoUsuario (descripcion) VALUES
('administrador'),
('cliente');


-- Crear tabla Personas
CREATE TABLE Personas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dni VARCHAR(20) UNIQUE NOT NULL,
    cuil varchar(20) UNIQUE NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    sexo ENUM('M','F','X') NOT NULL,
    Celular  VARCHAR(100) NOT NULL,
    Telefono VARCHAR(100) NOT NULL,
    Direccion_id INT NOT NULL,
    nacionalidad VARCHAR(50) NOT NULL,
    fecha_nacimiento DATE NOT NULL CHECK(fecha_nacimiento<CURDATE()),
    email VARCHAR(100) UNIQUE NOT NULL,
    FOREIGN KEY (Direccion_id) REFERENCES Direccion(id)
 
);


-- Se inserta un usuario administrador para realizar pruebas
INSERT INTO Usuarios(dni,cuil,nombre,apellido,sexo,Celular,Telefono,
nacionalidad,fecha_nacimiento,email,usuario,contrasenia,tipo_usuario_id)
VALUES('11111111','20111111114','Jose','Pruebas','M','11','11',
'Argentina','1990-03-20','pruebas@hotmail.com','Administrador','Admin2024',1);


CREATE TABLE Usuarios (
    usuario VARCHAR(50) UNIQUE NOT NULL CHECK(CHAR_LENGTH(usuario)>=4),
    pass VARCHAR(255) NOT NULL CHECK(CHAR_LENGTH(pass)>=4),
    id_cliente INT NOT NULL,
    tipo_usuario_id INT NOT NULL default 2,
    habilitado INT NOT NULL DEFAULT 1 CHECK(habilitado in(0,1)),
    FOREIGN KEY (tipo_usuario_id) REFERENCES TipoUsuario(id),
    FOREIGN KEY (id_cliente) REFERENCES Personas(id)

)



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
    cuenta_origen INT NOT NULL,
    fecha DATE NOT NULL,
    detalle VARCHAR(255) NOT NULL,
    importe DECIMAL(10, 2) NOT NULL CHECK (importe > 0),
    cuenta_destino INT,
    tipo_movimiento_id INT NOT NULL,
    FOREIGN KEY (cuenta_origen) REFERENCES Cuentas(numero_cuenta),
    FOREIGN KEY (cuenta_destino) REFERENCES Cuentas(numero_cuenta),
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
    importe_solicitado DECIMAL(10, 2) NOT NULL CHECK(importe_solicitado > 0),
    importe_a_pagar DECIMAL(10, 2) NOT NULL CHECK(importe_a_pagar >= 0),
    importe_cuota DECIMAL(10, 2) NOT NULL CHECK(importe_cuota > 0),
    cuotas INT NOT NULL DEFAULT 1 CHECK(cuotas > 0),
    estado INT NOT NULL DEFAULT 1,
    cuotas_abonadas INT NOT NULL DEFAULT 0 CHECK(cuotas_abonadas >= 0),
    saldo_restante DECIMAL(10, 2) NOT NULL CHECK(saldo_restante >= 0),
    FOREIGN KEY (cliente_id) REFERENCES Clientes(id),
    FOREIGN KEY (estado) REFERENCES EstadosPrestamos(id)
);


-- Crear tabla PagosPrestamo
CREATE TABLE PagosPrestamo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    prestamo_id INT NOT NULL,
    fecha_pago DATE NOT NULL,
    importe_pago DECIMAL(10, 2) NOT NULL CHECK(importe_pago > 0),
    cuota INT NOT NULL,
    estado INT NOT NULL DEFAULT 1,
    FOREIGN KEY (prestamo_id) REFERENCES Prestamos(id),
    FOREIGN KEY (estado) REFERENCES EstadosPagos(id)
);

CREATE TABLE EstadosPagos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    estado VARCHAR(50) NOT NULL
);

-- Ejemplo de inserción de algunos estados de pago
INSERT INTO EstadosPagos (estado) VALUES
(1, 'Pendiente'),
(2, 'Pagado'),
(3, 'Atrasado');


DELIMITER //

CREATE TRIGGER generar_cbu
BEFORE INSERT ON Cuentas
FOR EACH ROW
BEGIN
    DECLARE ultimo_id INT;
    
    
    SELECT MAX(numero_cuenta) INTO ultimo_id FROM Cuentas;
    
   
    IF ultimo_id IS NULL THEN
        SET ultimo_id = 0;
    END IF;
    
   
    SET NEW.cbu = CONCAT('000', ultimo_id + 1);
END //

DELIMITER ;


