-- caso ja tenha criado as tabelas, rodar isso:

--==================================== REFATORAÇÃO ====================================
-- tirando algumas colunas de preço de algumas tabelas REFATORAÇÃO
alter table anuncio drop column tipo_anuncio;
alter table produto drop column preco_unidade;
alter table negociacao drop column valor_pago;
alter table usuario_has_causa drop column valor_doado;
alter table causa drop column valor_arrecadado;
alter table causa drop column meta;


-- tabela causa e requisicao:
rename table requisicao to requisicao_tipo_perfil;
drop table usuario_has_causa;
drop table causa;
--recrie as tabelas no create table e faça ps inserts de causa

ALTER TABLE causa ADD COLUMN data_criacao DATE;
UPDATE causa SET data_criacao = CURDATE();
ALTER TABLE causa MODIFY COLUMN data_criacao DATE NOT NULL;

alter table causa change nome_arquivo_foto nome_arquivo_foto varchar(60) not null;
alter table causa change meta_assinatura meta_voluntarios bigint not null;



------==========================================================================================================================
ALTER TABLE labweb_project.causa MODIFY COLUMN descricao TEXT;

-- colunas de adm com varchar maior
alter table administrador modify github varchar(255);
alter table administrador modify linkedin varchar(255);

-- caso ja tenha criado algumas colunas com tabelas que armazenam só um caracter para indicar tipo, status, etc:
-- estamos adaptando para string para lidar de forma mais facil com os enums
-- rodar:alter table causa add column data_criacao date not null;
ALTER table anuncio MODIFY status VARCHAR(20) NOT NULL;
ALTER table causa MODIFY status_causa VARCHAR(20) NOT NULL;
ALTER table relacao_beneficiario MODIFY tipo_relacao_interessado VARCHAR(20) NOT NULL;
ALTER table negociacao MODIFY status_negociacao VARCHAR(20) NOT NULL;
ALTER table denuncia MODIFY status_denuncia VARCHAR(20) NOT NULL;

-- e caso ja tenha rodado os inserts para anuncio, causa, relacao_beneficiario e negociacao, rode:
UPDATE anuncio SET status = 'ATIVO' WHERE status = 'A';
UPDATE anuncio SET status = 'EXPIRADO' WHERE status = 'E';
UPDATE anuncio SET status = 'FINALIZADO' WHERE status = 'F';
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


--mudando a estrutura dos nossos ids
alter table avaliacao modify idavaliacao VARCHAR(7);
alter table denuncia modify iddenuncia VARCHAR(7);
ALTER TABLE negociacao MODIFY COLUMN negociacao_idnegociacao VARCHAR(7);
ALTER TABLE requisicao MODIFY COLUMN idrequisicao VARCHAR(7);

--alterando as tabelas que ja estavam preenchidas antes
update requisicao set idrequisicao = CONCAT('REQ', LPAD(idrequisicao, 4, '0')); -- esse LPAD é preenchimento à esquerda 
update negociacao set negociacao_idnegociacao = CONCAT('NEG', LPAD(negociacao_idnegociacao, 4, '0'));

--varios atributos na tabela de user foram alterados:
update conta set email = 'alyssonoliveira456@gmail.com' where idconta = 'CON0002';
update conta set email = 'bgabriellams@gmail.com' where idconta = 'CON0005';
update conta set email = 'kaikcpereira@gmail.com' where idconta = 'CON0006';
update conta set email = 'leti.almeida0221@gmail.com' where idconta = 'CON0007';
update conta set email = 'melcostaoa@gmail.com' where idconta = 'CON0008';
update conta set email = 'alyssonoliveira4567@gmail.com' where idconta = 'CON0009';
update conta set email = 'alyssonoliveira45678@gmail.com' where idconta = 'CON0010';
update conta set nome = 'Alizon Anjos' where idconta = 'CON0010';


--os nomes dos tipos de user estavam errados no banco
update tipo set nome = 'beneficiario' where idtipo = 'TIP0001';
update tipo set nome = 'hibrido' where idtipo = 'TIP0003';


--nova coluna na tabela usuatio pq vamos desativar a conta dele
alter table usuario add column situacao varchar(7) not null;
update usuario set situacao = 'ATIVO';

--pra testar a rota de iniciar negociacao
UPDATE anuncio 
SET data_expiracao = '2025-08-10' 
WHERE idanuncio = 'ANU0009';

--anuncio na verdade tem o campo descricao também
ALTER TABLE labweb_project.anuncio ADD COLUMN descricao TEXT;

UPDATE labweb_project.anuncio SET descricao = 'Pacote de 1kg, produto orgânico e saudável.' WHERE idanuncio = 'ANU0001';
UPDATE labweb_project.anuncio SET descricao = 'Feijão tipo 1, embalado a vácuo, ideal para o dia a dia.' WHERE idanuncio = 'ANU0002';
UPDATE labweb_project.anuncio SET descricao = 'Óleo de soja Liza 900ml para doação a entidades sociais.' WHERE idanuncio = 'ANU0003';
UPDATE labweb_project.anuncio SET descricao = 'Venda em grandes quantidades com preços especiais.' WHERE idanuncio = 'ANU0004';
UPDATE labweb_project.anuncio SET descricao = 'Feijão ideal para estabelecimentos comerciais.' WHERE idanuncio = 'ANU0005';
UPDATE labweb_project.anuncio SET descricao = 'Doação de óleo vegetal para famílias em situação de vulnerabilidade.' WHERE idanuncio = 'ANU0006';
UPDATE labweb_project.anuncio SET descricao = 'Arroz premium importado dos EUA, em embalagem de 1kg.' WHERE idanuncio = 'ANU0007';
UPDATE labweb_project.anuncio SET descricao = 'Feijão preto tipo 1, sabor tradicional e embalado a vácuo.' WHERE idanuncio = 'ANU0008';
UPDATE labweb_project.anuncio SET descricao = 'Óleo vegetal 900ml para doação a escolas públicas.' WHERE idanuncio = 'ANU0009';