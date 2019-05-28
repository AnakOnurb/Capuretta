CREATE DATABASE capuretta_db;

USE capuretta_db;

CREATE TABLE services
(
	id INT AUTO_INCREMENT NOT NULL,
	descricao VARCHAR(300) NOT NULL,
	preco_hora DECIMAL(10,2) NOT NULL,
	PRIMARY KEY (id)
);

INSERT INTO services (descricao, preco_hora) 
	VALUES 	("Produção Musical", 300), 
			("Gravação", 150),
            ("Mixagem e Masterização", 150),
			("Produção de Vídeo", 400);

CREATE TABLE users
(
	id INT AUTO_INCREMENT NOT NULL,
	nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    telefone VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);
select * from budget;
CREATE TABLE budget
(
	id INT AUTO_INCREMENT NOT NULL,
    usuario_id INT NOT NULL,
    observacoes VARCHAR(300) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE service_budget
(
	id INT AUTO_INCREMENT NOT NULL,
    id_service INT NOT NULL,
    id_budget INT NOT NULL,
    qtde_horas DECIMAL(10,2) NOT NULL,
	preco DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT `fk_service` FOREIGN KEY ( `id_service` ) REFERENCES `services` ( `id` ),
    CONSTRAINT `fk_budget` FOREIGN KEY ( `id_budget` ) REFERENCES `budget` ( `id` )
);

CREATE TABLE candidate
(
	id INT AUTO_INCREMENT NOT NULL,
	nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) NOT NULL,
    endereco VARCHAR (100) NOT NULL,
    telefone VARCHAR(50) NOT NULL,
    descricao VARCHAR(500) NOT NULL,
    PRIMARY KEY (id)
);

