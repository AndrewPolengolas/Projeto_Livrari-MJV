create database livraria;

use livraria;

create table produto (
	id int auto_increment primary key unique,
    cod_barras int unique,
    preco double,
    nome varchar(60),
    descricao text,
    fk_id_editora int,
    foreign key(fk_id_editora) references editora(id)
);

INSERT INTO produto(cod_barras, preco, nome, descricao, fk_id_editora) VALUES(?, ?);

create table editora(
	id int auto_increment primary key unique,
    nome varchar(60),
    telefone long,
    email varchar(90),
    cnpj varchar(60) unique,
    fk_id_endereco int,
    foreign key(fk_id_endereco) references endereco(id)
);

INSERT INTO editora(nome, telefone, email, cnpj, fk_id_endereco) VALUES("Panini", 954702059, "Panini@org.com", "32.768.925/0001-73", 1);

create table endereco(
	id int auto_increment primary key unique,
    logradouro varchar(90),
    numero varchar(30),
    bairro varchar(90),
    cidade varchar(90),
    uf varchar(2)
);

drop database livraria;
drop table editora;

select * from endereco;
select * from produto;
select * from editora;

INSERT INTO endereco(logradouro, numero, bairro, cidade, uf) VALUES("Ermelina Andrade", "85A", "Penha", "São Paulo", "SP");



