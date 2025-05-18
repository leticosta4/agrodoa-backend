create table if not exists algos (
  id integer primary key auto_increment,
  conteudo varchar(50) not null
);

insert into algos (conteudo) values ('algo1'), ('algo2'), ('algo3');
