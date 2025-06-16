-- caso ja tenha criado as tabelas, rodar isso aqui para corrigir a chave anuncio-produto
ALTER TABLE anuncio
ADD UNIQUE INDEX produto_idproduto_UNIQUE (produto_idproduto);
