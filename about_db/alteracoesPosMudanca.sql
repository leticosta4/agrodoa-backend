-- caso ja tenha criado as tabelas, rodar isso:

-- colunas de adm com varchar maior
alter table administrador modify github varchar(255);
alter table administrador modify linkedin varchar(255);

-- caso ja tenha criado algumas colunas com tabelas que armazenam só um caracter para indicar tipo, status, etc:
-- estamos adaptando para string para lidar de forma mais facil com os enums
-- rodar:
ALTER table anuncio MODIFY tipo_anuncio VARCHAR(20) NOT NULL;
ALTER table anuncio MODIFY status VARCHAR(20) NOT NULL;
ALTER table causa MODIFY status_causa VARCHAR(20) NOT NULL;
ALTER table relacao_beneficiario MODIFY tipo_relacao_interessado VARCHAR(20) NOT NULL;
ALTER table negociacao MODIFY status_negociacao VARCHAR(20) NOT NULL;
ALTER table denuncia MODIFY status_denuncia VARCHAR(20) NOT NULL;

-- e caso ja tenha rodado os inserts para anuncio, causa, relacao_beneficiario e negociacao, rode:
UPDATE anuncio SET status = 'ATIVO' WHERE status = 'A';
UPDATE anuncio SET status = 'EXPIRADO' WHERE status = 'E';
UPDATE anuncio SET status = 'FINALIZADO' WHERE status = 'F';
UPDATE anuncio SET tipo_anuncio = 'VENDA' WHERE tipo_anuncio = 'V';
UPDATE anuncio SET tipo_anuncio = 'DOACAO' WHERE tipo_anuncio = 'D';
UPDATE causa SET status_causa = 'ABERTA' WHERE status_causa = 'A';
UPDATE causa SET status_causa = 'CONCLUIDA' WHERE status_causa = 'C';
UPDATE negociacao SET status_negociacao = 'FINALIZADA' WHERE status_negociacao = 'F';
UPDATE relacao_beneficiario SET tipo_relacao_interessado = 'SALVOU' WHERE tipo_relacao_interessado = 'S';
UPDATE relacao_beneficiario SET tipo_relacao_interessado = 'NEGOCIANDO' WHERE tipo_relacao_interessado = 'N';

-- para corrigir a chave anuncio-produto
ALTER TABLE anuncio
ADD UNIQUE INDEX produto_idproduto_UNIQUE (produto_idproduto);

ALTER TABLE usuario DROP COLUMN voluntario;


-- para mudar as letras do tipo relação beneficiário
update relacao_beneficiario set tipo_relacao_interessado =
    case when tipo_relacao_interessado = 'I' then 'S'
    when tipo_relacao_interessado = 'A' then 'N'
    else tipo_relacao_interessado end;


-- caso um item da tabela conta esteja duplicado (no meu banco tinha esse ai de baixo):
UPDATE conta
SET nome = 'AlimentosSaudaveis',
    senha = 'alimentos123',
    email = 'alimentos.saudaveis@gmail.com'
WHERE idconta = 'CON0009';


--varias tabelas devem precisar dropar alguas colunas extras que foram criadas caso o projeto já tenha rodado
-- automaticamente pelo hibernate quando compilei o projeto (erro na modelagel)
-- o comando basicamente é:
--  ALTER TABLE {nome_da_tabela} DROP COLUMN {nome_da_coluna};


-- em algumas tabelas, algumas colunas que deveriam ser DECIMAL(5,2) estão com double ou float, então rodar:
-- mas VERFICAR ANTES COM:   describe {nome_da_tabela};
alter table produto modify preco_unidade DECIMAL(5,2);
alter table causa modify meta DECIMAL(9,2);
alter table causa modify valor_arrecadado DECIMAL(5,2);
alter table usuario_has_causa modify valor_doado DECIMAL(5,2);


--mudando a estrutura dos nossos ids
alter table avaliacao modify idavaliacao VARCHAR(7);
alter table denuncia modify iddenuncia VARCHAR(7);
ALTER TABLE negociacao MODIFY COLUMN negociacao_idnegociacao VARCHAR(7);
ALTER TABLE requisicao MODIFY COLUMN idrequisicao VARCHAR(7);

-- Mudanca de 45 para 60 no linkedin do Administrador
ALTER TABLE administrador MODIFY linkedin VARCHAR(255);
ALTER TABLE administrador MODIFY github VARCHAR(255);

--alterando as tabelas que ja estavam preenchidas antes
update requisicao set idrequisicao = CONCAT('REQ', LPAD(idrequisicao, 4, '0')); -- esse LPAD é preenchimento à esquerda 
update negociacao set negociacao_idnegociacao = CONCAT('NEG', LPAD(negociacao_idnegociacao, 4, '0'));

--falta mts c FK
-- para ver o nome da FK usar: show create {nome_da_tabela}
-- 1. apagar a chave estrangeira
-- 2. alterar tipos das colunas primeiro na tabela referenciada depois na que faz referencia
-- 3. alterar o conteudo ja inserido SE a tabela tiver preenchida nas 2 tabelas em questão
-- 4. recriar a chave estrangeira
-- para automatizar, usar o scrip python para gerar os comandos