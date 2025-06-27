-- contas de adm
INSERT INTO labweb_project.conta (idconta, nome, senha, email) VALUES
('CON0001', 'Letícia Almeida', 'senha123', 'leticiacostaoa@gmail.com'),
('CON0002', 'Alysson Oliveira', 'senha456', 'alyssonoliveira456@email.com'),
('CON0003', 'Cainan de Brito', 'senha789', 'cainan.bas@gmail.com'),
('CON0004', 'Sophia Lima', 'minhanamorada', 'sophialimasantos4@gmail.com'),
('CON0005', 'Brunna Moura', 'brunna123', 'brugabi@gmail.com'),
('CON0006', 'Kaik Pereira', 'kaik123', 'kaik.pereira@gmail.com'),
('CON0007', 'FornecedoraAlimentos1', 'fornecedora123', 'fornecedora.alimentos1@gmail.com'),
('CON0008', 'FornecedoraALimentos2', 'fornecedora456', 'fornecedora.alimentos2@gmail.com'),
('CON0009', 'AlimentosSaudaveis', 'alimentos123', 'alimentos.saudaveis@gmail.com'),
('CON0010', 'Rafael Argolo', 'rafael123', 'rafael.argolo@gmail.com');
--- 4 a 6 p beneficiario, 7 a  p fornecedor, 10 p virar hibrido

-- em adm
INSERT INTO labweb_project.administrador (conta_idconta, github, linkedin) VALUES
('CON0001', 'https://github.com/leticosta4/', 'https://www.linkedin.com/in/let%C3%ADcia-almeida-9704162a0/'),
('CON0002', 'https://github.com/verttB', 'https://www.linkedin.com/in/alysson-dos-anjos-00b431305/'),
('CON0003', 'https://github.com/Cainan-bas', 'https://www.linkedin.com/in/cainan-de-brito-araujo-santos-0aab58322/');


INSERT INTO labweb_project.tipo (idtipo, nome) VALUES
('TIP0001', 'beneficiário'),
('TIP0002', 'fornecedor'),
('TIP0003', 'híbrido');

INSERT INTO labweb_project.usuario (conta_idconta, cpf_ou_cnpj, nome_arquivo_foto, telefone, cidade_idcidade, tipo_idtipo, voluntario) VALUES
('CON0004', '12345678909', 'foto_sophia.png', '71997172280', 1, 1, 0),
('CON0005', '98765432100', 'foto_brunna.png', '71997456739', 1, 1, 0),
('CON0006', '11122233396', 'foto_kaik.png', '71987664532', 2, 1, 1),
('CON0010', '55544477735', 'foto_rafael.png', '7199443567', 3, 3, 0),
('CON0007', '12345678000195', 'foto_fornecedora1.png', '71912345678', 1, 2, 0),
('CON0008', '98765432000146', 'foto_fornecedora2.png', '21987654321', 4, 2, 0),
('CON0009', '55444333000122', 'foto_aliimentos.png', '2134567890', 5, 2, 0);


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


INSERT INTO labweb_project.produto (idproduto, nome, quantidade, data_validade, preco_unidade) VALUES
('PRO0001', 'Arroz Integral 5kg', 50, '2024-12-31', 6.50),
('PRO0002', 'Feijão Carioca 1kg', 100, '2025-06-30', 8.50),
('PRO0003', 'Óleo de Soja 900ml', 30, '2024-10-15', NULL),
('PRO0004', 'Arroz Branco Prato Fino 5kg', 40, '2025-01-15', 5.80),  
('PRO0005', 'Feijão Camil 1kg', 80, '2025-07-20', 8.50),             
('PRO0006', 'Óleo de Girassol Soya 1L', 20, '2024-11-30', NULL),
('PRO0007', 'Arroz Parboilizado Uncle Ben’s 5kg', 60, '2025-03-10', 12.90),
('PRO0008', 'Feijão Preto Kero Coco 1kg', 70, '2025-08-05', 10.50),          
('PRO0009', 'Óleo de Milho Salada 900ml', 25, '2024-09-25', NULL);

INSERT INTO labweb_project.anuncio (
  idanuncio, titulo, nome_arquivo_foto, data_expiracao, status, tipo_anuncio, 
  entrega_pelo_fornecedor, cidade_idcidade, produto_idproduto, id_anunciante
) VALUES
('ANU0001', 'Arroz Integral Tio João - Promoção', 'arroz_tiojoao.jpg', '2025-06-30', 'A', 'V', 1, 'CID0001', 'PRO0001', 'CON0007'),
('ANU0002', 'Feijão Kicaldo - Qualidade Garantida', 'feijao_kicaldo.jpg', '2024-07-15', 'E', 'V', 0, 'CID0001', 'PRO0002', 'CON0007'),
('ANU0003', 'Doação de Óleo Liza para Instituições', 'oleo_liza.jpg', '2025-05-20', 'F', 'D', 1, 'CID0001', 'PRO0003', 'CON0007'),
('ANU0004', 'Arroz Prato Fino - Atacado', 'arroz_pratofino.jpg', '2025-04-10', 'F', 'V', 1, 'CID0004', 'PRO0004', 'CON0008'),
('ANU0005', 'Feijão Camil - Ofertas para Restaurantes', 'feijao_camil.jpg', '2025-06-25', 'A', 'V', 1, 'CID0004', 'PRO0005', 'CON0008'),
('ANU0006', 'Óleo Soya para Comunidades Carentes', 'oleo_soya.jpg', '2024-07-30', 'E', 'D', 0, 'CID0004', 'PRO0006', 'CON0008'),
('ANU0007', 'Arroz Uncle Ben’s - Importado', 'arroz_unclebens.jpg', '2024-09-15', 'E', 'V', 0, 'CID0005', 'PRO0007', 'CON0009'),
('ANU0008', 'Feijão Preto Kero Coco - Tradicional', 'feijao_preto.jpg', '2024-08-05', 'F', 'V', 1, 'CID0005', 'PRO0008', 'CON0009'),
('ANU0009', 'Doação de Óleo Salada para Escolas', 'oleo_salada.jpg', '2025-06-10', 'A', 'D', 1, 'CID0005', 'PRO0009', 'CON0009');

INSERT INTO labweb_project.requisicao (idrequisicao, usuario_conta_idconta, tipo_anterior) VALUES
('REQ0001', 'CON0010', 'TIP0001');

-- o tipo da relação pode ser Negociando ou Salvou
--para anuncios expirados
INSERT INTO labweb_project.relacao_beneficiario 
(anuncio_idanuncio, usuario_conta_idconta, tipo_relacao_interessado) VALUES
('ANU0002', 'CON0006', 'S'),  -- Kaik interessado no feijão
('ANU0007', 'CON0010', 'S');  -- Rafael interessado no arroz importado

--para anuncios finalizados
INSERT INTO labweb_project.relacao_beneficiario 
(anuncio_idanuncio, usuario_conta_idconta, tipo_relacao_interessado) VALUES
('ANU0003', 'CON0010', 'N'), -- Rafael aprovado na doação de óleo
('ANU0004', 'CON0005', 'N'),  -- Brunna aprovada no arroz
('ANU0008', 'CON0004', 'N');  -- Sophia aprovada no feijão preto

--para anuncios ativos
INSERT INTO labweb_project.relacao_beneficiario 
(anuncio_idanuncio, usuario_conta_idconta, tipo_relacao_interessado) VALUES
('ANU0001', 'CON0004', 'S'),  -- Sophia interessada no arroz
('ANU0001', 'CON0005', 'S'),  -- Brunna também interessada
('ANU0005', 'CON0004', 'S'),  -- Sophia interessada no feijão
('ANU0005', 'CON0006', 'S'),  -- Kaik também interessado
('ANU0009', 'CON0010', 'S');  -- Rafael interessado na doação de óleo

INSERT INTO `labweb_project`.`negociacao` 
(`negociacao_idnegociacao`, `valor_pago`, `quantidade`, `id_anuncio`, `id_beneficiario`) VALUES

('NEG0001', NULL, 5, 'ANU0003', 'CON0010'),  -- Negociação 1: Doação de óleo (anúncio 3) para Rafael >>> Doação não tem valor_pago
('NEG0002', 23.20, 4, 'ANU0004', 'CON0005'),  -- Negociação 2: Compra de arroz (anúncio 4) por Brunna >>> 4 unidades x R$5.80 (preço do produto 4)
('NEG0003', 10.50, 1, 'ANU0008', 'CON0004');  -- Negociação 3: Compra de feijão (anúncio 8) por Sophia >>> 1 unidade x R$10.50 (preço do produto 8)

INSERT INTO labweb_project.causa 
(idcausa, nome, descricao, meta, prazo, nome_arquivo_foto, status_causa, valor_arrecadado) VALUES
('CAU0001', 'Natal Sem Fome', 'Arrecadação de alimentos para famílias em situação de vulnerabilidade durante o período natalino', 5000.00, '2024-12-20', 'natal_sem_fome.jpg', 'C', NULL),
('CAU0002', 'Kit Escolar Solidário', 'Doação de materiais escolares para crianças de baixa renda no início do ano letivo', 3000.00, '2025-07-31', 'kit_escolar.jpg', 'A', NULL),
('CAU0003', 'SOS Enchentes Bahia', 'Arrecadação emergencial para vítimas das enchentes no sul da Bahia', 10000.00, '2024-11-30', 'sos_enchentes.jpg', 'C', NULL),
('CAU0004', 'Conectando Futuros', 'Captação de recursos para montar laboratórios de informática em escolas públicas', 15000.00, '2025-08-15', 'inclusao_digital.jpg', 'A', NULL),
('CAU0005', 'Feira AgroSolidária', 'Financiamento coletivo para compra direta de produtores rurais familiares e doação a comunidades', 8000.00, '2024-09-30', 'feira_agro.jpg', 'C', NULL);


--fazer inserts da tabela motivo, denuncia, avaliação e usuario_has_causa

-- importante lembrar:
-- os ids agora são VARCHAR(7) E NO PADRAO 3 PRIMEIRAS LETRAS MAIUSCULAS + 4 DIGITOS