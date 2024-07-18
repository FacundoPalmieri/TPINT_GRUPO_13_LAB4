CREATE DATABASE `bdbanco`;
USE bdbanco; 
 
 CREATE TABLE Paises (
    id INT PRIMARY KEY AUTO_INCREMENT,
    Nombre VARCHAR(50) NOT NULL
);

INSERT INTO Paises (Nombre) VALUES ('Argentina');

CREATE TABLE Provincias (
    id INT PRIMARY KEY AUTO_INCREMENT,
    Nombre VARCHAR(50) NOT NULL,
    Pais_id INT NOT NULL,
    FOREIGN KEY (Pais_id) REFERENCES Paises(id)
);


INSERT INTO Provincias (Nombre, Pais_id) VALUES
('Buenos Aires',1),
('Ciudad Autónoma de Buenos Aires',1);



CREATE TABLE Localidades (
    id INT PRIMARY KEY AUTO_INCREMENT,
    Nombre VARCHAR(50) NOT NULL,
    Provincia_id INT NOT NULL,
    FOREIGN KEY (Provincia_id) REFERENCES Provincias(id)
);

INSERT INTO Localidades (Nombre, Provincia_id) VALUES 
('Agronomía', 2), 
('Almagro', 2), 
('Balvanera', 2), 
('Barracas', 2), 
('Belgrano', 2), 
('Boedo', 2), 
('Caballito', 2), 
('Chacarita', 2), 
('Coghlan', 2), 
( 'Colegiales', 2), 
('Constitución', 2), 
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
('San Cristóbal', 2), 
('San Nicolás', 2), 
('San Telmo', 2), 
('Vélez Sársfield', 2), 
('Versalles', 2), 
('Villa Crespo', 2), 
('Villa del Parque', 2), 
('Villa Devoto', 2), 
('Villa General Mitre', 2), 
('Villa Lugano', 2), 
('Villa Luro', 2), 
('Villa Ortúzar', 2), 
('Villa Pueyrredón', 2), 
('Villa Real', 2), 
('Villa Riachuelo', 2), 
('Villa Santa Rita', 2), 
('Villa Soldati', 2), 
('Villa Urquiza', 2);

INSERT INTO Localidades (Nombre, Provincia_id) VALUES 
('La Matanza', 1),
('Lomas de Zamora', 1),
('Quilmes', 1),
('Avellaneda', 1),
('Morón', 1),
('Tres de Febrero', 1),
('San Isidro', 1),
('San Fernando', 1),
('Tigre', 1),
('San Miguel', 1),
('Pilar', 1),
('Escobar', 1),
('Merlo', 1),
('Ituzaingó', 1),
('Vicente López', 1),
('Berazategui', 1),
('Florencio Varela', 1),
('General Rodríguez', 1),
('Hurlingham', 1),
('José C. Paz', 1),
('Malvinas Argentinas', 1),
('San Martín', 1),
('Berisso', 1),
('Ensenada', 1),
('Ezeiza', 1),
('General Pacheco', 1),
('General San Martín', 1),
('Lanús', 1),
('Marcos Paz', 1),
('Moreno', 1),
('Rafael Castillo', 1),
('San Justo', 1),
('La Plata', 1);

CREATE TABLE Direcciones (
    id INT PRIMARY KEY AUTO_INCREMENT,
    Calle VARCHAR(100) NOT NULL,
    Numero INT NOT NULL,
    Piso VARCHAR(2) NULL,
    Departamento VARCHAR(1),
    Localidad_id INT,
    FOREIGN KEY (Localidad_id) REFERENCES Localidades(id)
);

-- Insertar una dirección en Direccion
INSERT INTO Direcciones (Calle, Numero, Piso, Departamento, Localidad_id) 
VALUES 
('Calle Falsa', 123, '1', 'A', 1),
('Calle Falseta', 123, '1', 'A', 1),
('Avenida Siempre Viva', 742, '2', 'B', 2),
('Calle 9 de Julio', 555, '3', 'C', 3),
('Calle San Martín', 234, '4', 'D', 4),
('Avenida Rivadavia', 1000, '5', 'E', 5),
('Calle Belgrano', 678, '6', 'F', 6),
('Calle Córdoba', 890, '7', 'G', 7),
('Calle Mitre', 321, '8', 'H', 8),
('Avenida Pellegrini', 456, '9', 'I', 9),
('Calle Moreno', 567, '10', 'J', 10),
('Calle Sarmiento', 789, '11', 'K', 11),
('Calle Alsina', 123, '12', 'L', 12),
('Calle Brown', 456, '13', 'M', 13),
('Avenida Libertador', 789, '14', 'N', 14),
('Calle Pueyrredón', 123, '15', 'O', 15);




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
    FOREIGN KEY (Direccion_id) REFERENCES Direcciones(id)
 
);


-- Se inserta un usuario administrador para realizar pruebas
INSERT INTO Personas(dni,cuil,nombre,apellido,sexo,Celular,Telefono,Direccion_id,
nacionalidad,fecha_nacimiento,email)
VALUES
('11','11','Admnistrador','Prueba','M','11','11',1 ,'Argentina','1990-03-20','pruebas@hotmail.com'),
('BancoGrupo13', 'BancoGrupo13', 'Banco', 'Grupo 13', 'X', '123456789', '123456789', 1,'Argentina', '1990-01-01', 'bancogrupo13@example.com'),
('12345678', '20-12345678-9', 'Juan', 'Pérez', 'M', '3415123456', '3415123456', 2, 'Argentina', '1985-05-10', 'juan.perez@example.com'),
('23456789', '27-23456789-6', 'María', 'Gómez', 'F', '3516234567', '3516234567', 3, 'Argentina', '1990-08-15', 'maria.gomez@example.com'),
('34567890', '20-34567890-8', 'Carlos', 'Sánchez', 'M', '2617345678', '2617345678', 4, 'Argentina', '1987-12-30', 'carlos.sanchez@example.com'),
('45678901', '27-45678901-4', 'Laura', 'Rodríguez', 'F', '3818456789', '3818456789', 5, 'Argentina', '1992-03-05', 'laura.rodriguez@example.com'),
('56789012', '20-56789012-3', 'Jorge', 'López', 'M', '3419567890', '3419567890', 6, 'Argentina', '1988-07-20', 'jorge.lopez@example.com'),
('67890123', '27-67890123-2', 'Ana', 'Martínez', 'F', '3510678901', '3510678901', 7, 'Argentina', '1995-11-25', 'ana.martinez@example.com'),
('78901234', '20-78901234-1', 'Ricardo', 'Fernández', 'M', '2611789012', '2611789012', 8, 'Argentina', '1984-01-10', 'ricardo.fernandez@example.com'),
('89012345', '27-89012345-0', 'Lucía', 'González', 'F', '3812890123', '3812890123', 9, 'Argentina', '1991-06-30', 'lucia.gonzalez@example.com'),
('90123456', '20-90123456-8', 'Sergio', 'Ramos', 'M', '3413901234', '3413901234', 10, 'Argentina', '1986-09-20', 'sergio.ramos@example.com'),
('12345098', '27-12345098-6', 'Marta', 'Álvarez', 'F', '3515012345', '3515012345', 11, 'Argentina', '1993-02-15', 'marta.alvarez@example.com'),
('23456109', '20-23456109-7', 'Gabriel', 'Gutiérrez', 'M', '2616123456', '2616123456', 12, 'Argentina', '1989-10-25', 'gabriel.gutierrez@example.com'),
('34567210', '27-34567210-5', 'Sofía', 'Herrera', 'F', '3817234567', '3817234567', 13, 'Argentina', '1994-05-05', 'sofia.herrera@example.com'),
('45678321', '20-45678321-4', 'Pablo', 'Domínguez', 'M', '3418345678', '3418345678', 14, 'Argentina', '1983-12-10', 'pablo.dominguez@example.com'),
('56789432', '27-56789432-3', 'Elena', 'Ramírez', 'F', '3519456789', '3519456789', 15, 'Argentina', '1990-03-20', 'elena.ramirez@example.com'),
('67890543', '20-67890543-2', 'Javier', 'Ortiz', 'M', '2610567890', '2610567890', 16, 'Argentina', '1987-07-25', 'javier.ortiz@example.com');







CREATE TABLE Usuarios (
	id INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(50) UNIQUE NOT NULL CHECK(CHAR_LENGTH(usuario)>=4),
    pass VARCHAR(255) NOT NULL CHECK(CHAR_LENGTH(pass)>=4),
    persona_dni VARCHAR(20) NOT NULL,
    tipo_usuario_id INT NOT NULL default 2,
    habilitado INT NOT NULL DEFAULT 1 CHECK(habilitado in(0,1)),
    FOREIGN KEY (tipo_usuario_id) REFERENCES TipoUsuario(id),
    FOREIGN KEY (persona_dni) REFERENCES Personas(dni)

);

INSERT INTO usuarios(usuario, pass,persona_dni,tipo_usuario_id)
VALUES 
('Admin','1',11,1),
('BancoGrupo13', 'Banco', 'BancoGrupo13', 1),
('juanperez', '123', '12345678', 2), 
('mariagomez', '123', '23456789', 2), 
('carlossanchez', '123', '34567890', 2), 
('laurarodriguez', '123', '45678901', 2), 
('jorgelopez', '123', '56789012', 2), 
('anamartinez', '123', '67890123', 2), 
('ricardofernandez', '123', '78901234', 2), 
('luciagonzalez', '123', '89012345', 2), 
('sergioramos', '123', '90123456', 2), 
('martaalvarez', '123', '12345098', 2), 
('gabrielgutierrez', '123', '23456109', 2), 
('sofiaherrera', '123', '34567210', 2), 
('pablodominguez', '123', '45678321', 2), 
('elenaramirez', '123', '56789432', 2), 
('javierortiz', '123', '67890543', 2);


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
    FOREIGN KEY (cliente_dni) REFERENCES Personas (dni),
    FOREIGN KEY (tipo_cuenta_id) REFERENCES TipoCuenta(id)
);
-- Insertar la cuenta para 'BancoGrupo13'
INSERT INTO Cuentas (cliente_dni, fecha_creacion, tipo_cuenta_id, cbu, saldo, habilitado)
VALUES ('BancoGrupo13', CURDATE(), 1, '0001', 10000.00, 1);




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
    cliente_dni varchar(20) NOT NULL,
    cuenta_destino INT NOT NULL,
    fecha DATE NOT NULL,
    importe_solicitado DECIMAL(10, 2) NOT NULL CHECK(importe_solicitado > 0),
    importe_a_pagar DECIMAL(10, 2) NOT NULL CHECK(importe_a_pagar >= 0),
    importe_cuota DECIMAL(10, 2) NOT NULL CHECK(importe_cuota > 0),
    cuotas INT NOT NULL DEFAULT 1 CHECK(cuotas > 0),
    estado INT NOT NULL DEFAULT 1,
    cuotas_abonadas INT NOT NULL DEFAULT 0 CHECK(cuotas_abonadas >= 0),
    saldo_restante DECIMAL(10, 2) NOT NULL CHECK(saldo_restante >= 0),
    FOREIGN KEY (cliente_dni) REFERENCES Personas(dni),
    FOREIGN KEY (estado) REFERENCES EstadosPrestamos(id),
    FOREIGN KEY (cuenta_destino) REFERENCES cuentas (numero_cuenta)
);

CREATE TABLE EstadosPagos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    estado VARCHAR(50) NOT NULL
);

-- Ejemplo de inserción de algunos estados de pago
INSERT INTO EstadosPagos (estado) VALUES
('Pendiente'),
('Pagado'),
('Atrasado');

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




DELIMITER //

CREATE TRIGGER generar_cbu
BEFORE INSERT ON Cuentas
FOR EACH ROW
BEGIN
    DECLARE ultimo_id INT;
    
    -- Obtener el último número de cuenta
    SELECT MAX(numero_cuenta) INTO ultimo_id FROM Cuentas;
    
    -- Si no hay ninguna cuenta aún, establecer el último_id a 0
    IF ultimo_id IS NULL THEN
        SET ultimo_id = 0;
    END IF;
    
    -- Generar el CBU para la nueva cuenta
    SET NEW.cbu = CONCAT('000', ultimo_id + 1);

    
END //

DELIMITER ;

