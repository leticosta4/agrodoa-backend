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