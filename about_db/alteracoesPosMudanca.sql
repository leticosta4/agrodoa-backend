-- caso ja tenha criado as tabelas, rodar isso:


-- para corrigir a chave anuncio-produto
ALTER TABLE anuncio
ADD UNIQUE INDEX produto_idproduto_UNIQUE (produto_idproduto);


-- para mudar as letras do tipo relação beneficiário
update relacao_beneficiario set tipo_relacao_interessado =
    case when tipo_relacao_interessado = 'I' then 'S'
    when tipo_relacao_interessado = 'A' then 'N'
    else tipo_relacao_interessado end;

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

--alterando as tabelas que ja estavam preenchidas antes
update requisicao set idrequisicao = CONCAT('REQ', LPAD(idrequisicao, 4, '0')); -- esse LPAD é preenchimento à esquerda 
update negociacao set negociacao_idnegociacao = CONCAT('NEG', LPAD(negociacao_idnegociacao, 4, '0'));

--falta mts c FK (JA FIZ estado e cidade)
-- para ver o nome da FK usar: show create {nome_da_tabela}
-- 1. apagar a chave estrangeira
-- 2. alterar tipos das colunas primeiro na tabela referenciada depois na que faz referencia
-- 3. alterar o conteudo ja inserido SE a tabela tiver preenchida nas 2 tabelas em questão
-- 4. recriar a chave estrangeira
-- para automatizar, usar o scrip python para gerar os comandos