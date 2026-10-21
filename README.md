Projeto no padrão DAO.

Este projeto tem como objetivo administrar uma loja de artigos relacionados a informática.

Crie uma DataBase com o nome: lojainformatica, após adicione as tabelas:

Tabela para inserção em banco de dados e teste:

CREATE TABLE categoria ( id SERIAL PRIMARY KEY, nome VARCHAR(15), descricao VARCHAR(100) );

CREATE TABLE produto ( id SERIAL PRIMARY KEY, nome VARCHAR(50), descricao VARCHAR(200), preco DECIMAL(10,2), quantidade_estoque INTEGER, id_categoria INTEGER,

CONSTRAINT fk_produto_categoria
    FOREIGN KEY (id_categoria)
    REFERENCES categoria(id)
);

CREATE TABLE cliente ( id SERIAL PRIMARY KEY, nome VARCHAR(100), cpf VARCHAR(11), telefone VARCHAR(11), email VARCHAR(30), cep VARCHAR(8) );

CREATE TABLE venda ( id SERIAL PRIMARY KEY, data_venda DATE, valor_total DECIMAL(10,2), forma_pagamento VARCHAR(15), id_cliente INTEGER,

CONSTRAINT fk_venda_cliente
    FOREIGN KEY (id_cliente)
    REFERENCES cliente(id)
);

CREATE TABLE item_venda ( id SERIAL PRIMARY KEY, quantidade INTEGER, subtotal DECIMAL(10,2), id_produto INTEGER, id_venda INTEGER,

CONSTRAINT fk_item_venda_produto
    FOREIGN KEY (id_produto)
    REFERENCES produto(id),

CONSTRAINT fk_item_venda_venda
    FOREIGN KEY (id_venda)
    REFERENCES venda(id)
);
