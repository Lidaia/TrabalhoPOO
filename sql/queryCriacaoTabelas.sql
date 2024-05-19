-- Tabela de veículos   OK
CREATE TABLE VEICULO (
   placa VARCHAR(7) PRIMARY KEY,
   marca VARCHAR(50),
   modelo VARCHAR(50),
   ano_fabric INT,
   capacidade_pass INT,
   cor VARCHAR(20),
   tipo_combust VARCHAR(20),
   potencia_motor INT
);


-- Tabela de pessoas  OK
CREATE TABLE PESSOAS (
   Cpf_pessoa BIGINT PRIMARY KEY,
   Nome VARCHAR(50) NOT NULL,
   Endereco VARCHAR(50),
   Telefone INT,
   Sexo CHAR(1),
   Email VARCHAR(30)
);


--- Tabela passageiros  OK
CREATE TABLE PASSAGEIROS (
   Cpf_passag BIGINT PRIMARY KEY,
   Cartao_cred VARCHAR(20),
   Bandeira_cartao VARCHAR(20),
   Cidade_orig VARCHAR(30)
);




--- Tabela de motoristas  OK
CREATE TABLE MOTORISTAS (
   Cpf_motorista BIGINT PRIMARY KEY,
   CNH VARCHAR(15) NOT NULL,
   Banco_mot INT NOT NULL,
   Agencia_mot INT NOT NULL,
   Conta_mot INT NOT NULL
);


--- Tabela de proprietarios  OK
CREATE TABLE PROPRIETARIOS (
   Cpf_prop BIGINT PRIMARY KEY,
   CNH_prop VARCHAR(15) NOT NULL,
   Banco_prop INT NOT NULL,
   Agencia_prop INT NOT NULL,
   Conta_prop INT NOT NULL
);




--- Tabela de viagens  OK
CREATE TABLE VIAGEM (
   Cpf_pass_viag BIGINT NOT NULL,
   Cpf_mot_viag BIGINT NOT NULL,
   Placa_veic_viag VARCHAR(7) NOT NULL,
   Local_orig_viag VARCHAR(30),
   Local_dest_viag VARCHAR(30),
   Dt_hora_inicio DATE NOT NULL,
   Dt_hora_fim DATE,
   Qtde_pass INT,
   Forma_pagto VARCHAR,
   Valor_pagto NUMERIC(10,2),
   Cancelam_mot CHAR(1),
   Cancelam_pass CHAR(1),
   PRIMARY KEY (Cpf_pass_viag, Cpf_mot_viag, Placa_veic_viag, Dt_hora_inicio)
);


--- Tabela de motorista_veiculo  OK
CREATE TABLE MOTORISTA_VEICULO (
   Cpf_motorista BIGINT NOT NULL,
   Placa_veiculo VARCHAR(7) NOT NULL,
   PRIMARY KEY (Cpf_motorista, Placa_veiculo)
);




--- Tabela de tipo_pagto  OK
CREATE TABLE TIPO_PAGTO (
   COD_PAGTO INT PRIMARY KEY,
   DESC_PAGTO VARCHAR(20) NOT NULL
);

