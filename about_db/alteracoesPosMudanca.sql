-- caso ja tenha criado as tabelas, rodar isso:

-- para corrigir a chave anuncio-produto
ALTER TABLE anuncio
ADD UNIQUE INDEX produto_idproduto_UNIQUE (produto_idproduto);


-- para mudar as letras do tipo relação beneficiário
update relacao_beneficiario set tipo_relacao_interessado =
    case when tipo_relacao_interessado = 'I' then 'S'
    when tipo_relacao_interessado = 'A' then 'N'
    else tipo_relacao_interessado end;

--varias tabelas devem precisar dropar alguas colunas extras  que foram criadas
-- automaticamente pelo hibernate quando compilei o projeto (erro na modelagel)
-- o comando basicamente é:
--  ALTER TABLE {nome_da_tabela} DROP COLUMN {nome_da_coluna};