# Pizzaria Muito Massa
O projeto feito para estudo consiste em um sistema para gerenciar os pedidos dos clientes da Pizzaria Muito Massa, uma pizzaria fictícia localizada no Rio de Janeiro.
A pizzaria oferece opções de pizzas com diferentes sabores, preços e ingredientes, além de bebidas de diferentes tipos e preços. Nesta pizzaria, apenas clientes maiores de idade podem fazer um pedido. No pedido, teremos o preço total (pizzas e bebidas), a data na qual ele foi feito, quem fez o pedido e o endereço do cliente que fez o pedido. Ao fazer o pedido, o cliente escolherá o tamanho da pizza pedida (Pequena – 20cm, Média – 40cm ou Grande – 60cm) e o tamanho da bebida pedida (Pequena - Lata/Garrafa de 500ml, Média – Garrafa de 1L, Grande – Garrafa de 2L).

Para a criação das tabelas necessárias para o banco de dados:
CREATE TABLE CLIENTE(
idCliente int auto_increment not null,
nome varchar(40),
telefone varchar(14),
email varchar(40),
cpf varchar(14),
dataNascimento Date,
PRIMARY KEY(idCliente)
);

CREATE TABLE ENDERECO(
idEndereco int auto_increment not null,
cidade varchar(30),
bairro varchar(40),
rua varchar(40),
numero int,
complemento varchar(100) null,
idCliente int not null,
PRIMARY KEY(idEndereco),
FOREIGN KEY(idCliente) REFERENCES CLIENTE(idCliente)
);

CREATE TABLE PEDIDO(
idPedido int auto_increment not null,
precoPedido float,
dataPedido Date,
enderecoCliente varchar(50),
idCliente int not null,
PRIMARY KEY(idPedido),
FOREIGN KEY(idCliente) REFERENCES CLIENTE(idCliente)
);

CREATE TABLE PIZZA(
idPizza int auto_increment not null,
sabor varchar(20),
ingredientes varchar(50),
preco float,
PRIMARY KEY(idPizza)
);

CREATE TABLE PEDIDO_PIZZA(
idPedido int not null,
idPizza int not null,
tam_pizza varchar(15),
FOREIGN KEY(idPedido) REFERENCES PEDIDO(idPedido),
FOREIGN KEY(idPizza) REFERENCES PIZZA(idPizza)
);

CREATE TABLE BEBIDA(
idBebida int auto_increment not null,
nome varchar(20),
preco float,
PRIMARY KEY(idBebida)
);

CREATE TABLE PEDIDO_BEBIDA(
idPedido int not null,
idBebida int not null,
tam_bebida varchar(30),
FOREIGN KEY(idPedido) REFERENCES PEDIDO(idPedido),
FOREIGN KEY(idBebida) REFERENCES BEBIDA(idBebida)
);
