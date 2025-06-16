-- caso ja tenha criado as tabelas, rodar isso:

-- para corrigir a chave anuncio-produto
ALTER TABLE anuncio
ADD UNIQUE INDEX produto_idproduto_UNIQUE (produto_idproduto);


-- para mudar as letras do tipo relação beneficiário
update relacao_beneficiario set tipo_relacao_interessado =
    case when tipo_relacao_interessado = 'I' then 'S'
    when tipo_relacao_interessado = 'A' then 'N'
    else tipo_relacao_interessado end;