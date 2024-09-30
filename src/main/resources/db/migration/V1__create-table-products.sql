create table products(

	id BIGINT NOT null auto_increment,
	nome VARCHAR(100) not null,
	descricao VARCHAR(100) not null,
	valor DECIMAL(10,2) not null,
	disponivel char(13) not null,
	
	primary key(id)
	);