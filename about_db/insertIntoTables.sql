-- contas de adm
INSERT INTO labweb_project.conta (idconta, nome, senha, email) VALUES
('CON0001', 'Letícia Almeida', 'senha123', 'leticiacostaoa@gmail.com'),
('CON0002', 'Alysson Oliveira', 'senha456', 'alyssonoliveira456@gmail.com'),
('CON0003', 'Cainan de Brito', 'senha789', 'cainan.bas@gmail.com'),
('CON0004', 'Sophia Lima', 'minhanamorada', 'sophialimasantos4@gmail.com'),
('CON0005', 'Brunna Moura', 'brunna123', 'bgabriellams@gmail.com'),
('CON0006', 'Kaik Pereira', 'kaik123', 'kaikcpereira@gmail.com'),
('CON0007', 'FornecedoraAlimentos1', 'fornecedora123', 'leti.almeida0221@gmail.com'),
('CON0008', 'FornecedoraALimentos2', 'fornecedora456', 'melcostaoa@gmail.com'),
('CON0009', 'AlimentosSaudaveis', 'alimentos123', 'alyssonoliveira4567@gmail.com'),
('CON0010', 'Alizon Anjos', 'Alizon123', 'alyssonoliveira45678@gmail.com');
--- 4 a 6 p beneficiario, 7 a  p fornecedor, 10 p virar hibrido

-- em adm
INSERT INTO labweb_project.administrador (conta_idconta, github, linkedin) VALUES
('CON0001', 'https://github.com/leticosta4/', 'https://www.linkedin.com/in/let%C3%ADcia-almeida-9704162a0/'),
('CON0002', 'https://github.com/verttB', 'https://www.linkedin.com/in/alysson-dos-anjos-00b431305/'),
('CON0003', 'https://github.com/Cainan-bas', 'https://www.linkedin.com/in/cainan-de-brito-araujo-santos-0aab58322/');


INSERT INTO labweb_project.tipo (idtipo, nome) VALUES
('TIP0001', 'beneficiario'),
('TIP0002', 'fornecedor'),
('TIP0003', 'hibrido');

INSERT INTO labweb_project.usuario (conta_idconta, cpf_ou_cnpj, nome_arquivo_foto, telefone, cidade_idcidade, tipo_idtipo, situacao) VALUES
('CON0004', '12345678909', 'foto_sophia.png', '71997172280', 'CID0001', 'TIP0001', 'ATIVO'),
('CON0005', '98765432100', 'foto_brunna.png', '71997456739', 'CID0001', 'TIP0001', 'ATIVO'),
('CON0006', '11122233396', 'foto_kaik.png', '71987664532', 'CID0002', 'TIP0001', 'ATIVO'),
('CON0010', '55544477735', 'foto_Alizon.png', '7199443567', 'CID0003','TIP0003', 'ATIVO'),
('CON0007', '12345678000195', 'foto_fornecedora1.png', '71912345678', 'CID0001', 'TIP0002', 'ATIVO'),
('CON0008', '98765432000146', 'foto_fornecedora2.png', '21987654321', 'CID0004', 'TIP0002', 'ATIVO'),
('CON0009', '55444333000122', 'foto_aliimentos.png', '2134567890', 'CID0005', 'TIP0002', 'ATIVO');


INSERT INTO labweb_project.cidade (idcidade, nome, estado_idestado) VALUES
('CID0001', 'Salvador', 'EST0001'),
('CID0002', 'Camaçari', 'EST0001'),
('CID0003', 'Lauro de Freitas', 'EST0001'),
('CID0004', 'Rio de Janeiro', 'EST0003'),
('CID0005', 'Santos', 'EST0003');

INSERT INTO labweb_project.estado (idestado, nome) VALUES
('EST0001', 'Bahia'),
('EST0002', 'Amazonas'),
('EST0003', 'Rio de Janeiro');


INSERT INTO labweb_project.produto (idproduto, nome, quantidade, data_validade) VALUES
('PRO0001', 'Arroz Integral 5kg', 50, '2024-12-31'),
('PRO0002', 'Feijão Carioca 1kg', 100, '2025-06-30'),
('PRO0003', 'Óleo de Soja 900ml', 30, '2024-10-15'),
('PRO0004', 'Arroz Branco Prato Fino 5kg', 40, '2025-01-15'),  
('PRO0005', 'Feijão Camil 1kg', 80, '2025-07-20'),             
('PRO0006', 'Óleo de Girassol Soya 1L', 20, '2024-11-30'),
('PRO0007', 'Arroz Parboilizado Uncle Ben’s 5kg', 60, '2025-03-10'),
('PRO0008', 'Feijão Preto Kero Coco 1kg', 70, '2025-08-05'),          
('PRO0009', 'Óleo de Milho Salada 900ml', 25, '2024-09-25');

INSERT INTO labweb_project.anuncio (
  idanuncio, titulo, descricao, nome_arquivo_foto, data_expiracao, status, 
  entrega_pelo_fornecedor, cidade_idcidade, produto_idproduto, id_anunciante
) VALUES
('ANU0001', 'Arroz Integral Tio João - Promoção', 'Pacote de 1kg, produto orgânico e saudável.', 'arroz_tiojoao.jpg', '2025-06-30', 'ATIVO', 1, 'CID0001', 'PRO0001', 'CON0007'),
('ANU0002', 'Feijão Kicaldo - Qualidade Garantida', 'Feijão tipo 1, embalado a vácuo, ideal para o dia a dia.', 'feijao_kicaldo.jpg', '2024-07-15', 'EXPIRADO', 0, 'CID0001', 'PRO0002', 'CON0007'),
('ANU0003', 'Doação de Óleo Liza para Instituições', 'Óleo de soja Liza 900ml para doação a entidades sociais.', 'oleo_liza.jpg', '2025-05-20', 'FINALIZADO', 1, 'CID0001', 'PRO0003', 'CON0007'),
('ANU0004', 'Arroz Prato Fino - Atacado', 'Venda em grandes quantidades com preços especiais.', 'arroz_pratofino.jpg', '2025-04-10', 'FINALIZADO', 1, 'CID0004', 'PRO0004', 'CON0008'),
('ANU0005', 'Feijão Camil - Ofertas para Restaurantes', 'Feijão ideal para estabelecimentos comerciais.', 'feijao_camil.jpg', '2025-06-25', 'ATIVO', 1, 'CID0004', 'PRO0005', 'CON0008'),
('ANU0006', 'Óleo Soya para Comunidades Carentes', 'Doação de óleo vegetal para famílias em situação de vulnerabilidade.', 'oleo_soya.jpg', '2024-07-30', 'EXPIRADO', 0, 'CID0004', 'PRO0006', 'CON0008'),
('ANU0007', 'Arroz Uncle Ben’s - Importado', 'Arroz premium importado dos EUA, em embalagem de 1kg.', 'arroz_unclebens.jpg', '2024-09-15', 'EXPIRADO', 0, 'CID0005', 'PRO0007', 'CON0009'),
('ANU0008', 'Feijão Preto Kero Coco - Tradicional', 'Feijão preto tipo 1, sabor tradicional e embalado a vácuo.', 'feijao_preto.jpg', '2024-08-05', 'FINALIZADO', 1, 'CID0005', 'PRO0008', 'CON0009'),
('ANU0009', 'Doação de Óleo Salada para Escolas', 'Óleo vegetal 900ml para doação a escolas públicas.', 'oleo_salada.jpg', '2025-06-10', 'ATIVO', 1, 'CID0005', 'PRO0009', 'CON0009');


INSERT INTO labweb_project.requisicao_tipo_perfil (idrequisicao, usuario_conta_idconta) VALUES
('REQ0001', 'CON0010');

-- o tipo da relação pode ser Negociando ou Salvou
--para anuncios expirados
INSERT INTO labweb_project.relacao_beneficiario 
(anuncio_idanuncio, usuario_conta_idconta, tipo_relacao_interessado) VALUES
('ANU0002', 'CON0006', 'SALVOU'),  -- Kaik interessado no feijão
('ANU0007', 'CON0010', 'SALVOU');  -- Alizon interessado no arroz importado

--para anuncios finalizados
INSERT INTO labweb_project.relacao_beneficiario 
(anuncio_idanuncio, usuario_conta_idconta, tipo_relacao_interessado) VALUES
('ANU0003', 'CON0010', 'NEGOCIANDO'), -- Alizon aprovado na doação de óleo
('ANU0004', 'CON0005', 'NEGOCIANDO'),  -- Brunna aprovada no arroz
('ANU0008', 'CON0004', 'NEGOCIANDO');  -- Sophia aprovada no feijão preto

--para anuncios ativos
INSERT INTO labweb_project.relacao_beneficiario 
(anuncio_idanuncio, usuario_conta_idconta, tipo_relacao_interessado) VALUES
('ANU0001', 'CON0004', 'SALVOU'),  -- Sophia interessada no arroz
('ANU0001', 'CON0005', 'SALVOU'),  -- Brunna também interessada
('ANU0005', 'CON0004', 'SALVOU'),  -- Sophia interessada no feijão
('ANU0005', 'CON0006', 'SALVOU'),  -- Kaik também interessado
('ANU0009', 'CON0010', 'SALVOU');  -- Alizon interessado na doação de óleo

INSERT INTO `labweb_project`.`negociacao` 
(`negociacao_idnegociacao`, `quantidade`, `id_anuncio`, `id_beneficiario`, `status_negociacao`) VALUES

('NEG0001', 5, 'ANU0003', 'CON0010', 'FINALIZADA'),
('NEG0002', 4, 'ANU0004', 'CON0005', 'FINALIZADA'),
('NEG0003', 1, 'ANU0008', 'CON0004', 'FINALIZADA');

INSERT INTO labweb_project.causa 
(idcausa, nome, descricao, prazo, nome_arquivo_foto, status_causa, meta_voluntarios, conta_idconta) VALUES
('CAU0001', 'Combate à Fome', 'Campanha para arrecadação de alimentos.', '2025-12-31', 'fome.jpg', 'ABERTA', 1000, 'CON0001'),
('CAU0002', 'Educação para Todos', 'Financiamento de materiais escolares.', '2025-11-30', 'educacao.jpg', 'ABERTA', 800, 'CON0001'),
('CAU0003', 'Meio Ambiente', 'Plantio de árvores em áreas urbanas.', '2025-05-15', 'meioambiente.jpg', 'CONCLUIDA', 500, 'CON0001'),
('CAU0004', 'Saúde da Mulher', 'Campanha de exames preventivos gratuitos.', '2025-09-20', 'saude.jpg', 'ABERTA', 1200, 'CON0001'),
('CAU0005', 'Abrigo Animal', 'Construção de canis e gatis para animais resgatados.', '2025-08-31', 'abrigo.jpg', 'ABERTA', 600, 'CON0001');

INSERT INTO motivo (idmotivo, nome) VALUES
('MOT0001', 'Desrespeito'),
('MOT0002', 'Propaganda enganosa'),
('MOT0003', 'Não recebimento do produto');


--fazer inserts da tabela denuncia, avaliação e usuario_has_causa

-- importante lembrar:
-- os ids agora são VARCHAR(7) E NO PADRAO 3 PRIMEIRAS LETRAS MAIUSCULAS + 4 DIGITOS
