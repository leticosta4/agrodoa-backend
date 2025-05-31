-- contas de adm
INSERT INTO labweb_project.conta (idconta, nome, senha, email) VALUES
(1, 'Letícia Almeida', 'senha123', 'leticiacostaoa@gmail.com'),
(2, 'Alysson Oliveira', 'senha456', 'alyssonoliveira456@email.com'),
(3, 'Cainan de Brito', 'senha789', 'cainan.bas@gmail.com'),
(4, 'Sophia Lima', 'minhanamorada', 'sophialimasantos4@gmail.com'),
(5, 'Brunna Moura', 'brunna123', 'brugabi@gmail.com'),
(6, 'Kaik Pereira', 'kaik123', 'kaik.pereira@gmail.com'),
(7, 'FornecedoraAlimentos1', 'fornecedora123', 'fornecedora.alimentos1@gmail.com'),
(8, 'FornecedoraALimentos2', 'fornecedora456', 'fornecedora.alimentos2@gmail.com'),
(9, 'AlimentosSaudaveis', 'alimentos123', 'alimentos.saudaveis@gmail.com'),
(10, 'Rafael Argolo', 'rafael123', 'rafael.argolo@gmail.com');
--- 4 a 6 p beneficiario, 7 a  p fornecedor, 10 p virar hibrido

-- em adm
INSERT INTO labweb_project.administrador (conta_idconta, github, linkedin) VALUES
(1, 'https://github.com/leticosta4/', 'https://www.linkedin.com/in/let%C3%ADcia-almeida-9704162a0/'),
(2, 'https://github.com/verttB', 'https://www.linkedin.com/in/alysson-dos-anjos-00b431305/'),
(3, 'https://github.com/Cainan-bas', 'https://www.linkedin.com/in/cainan-de-brito-araujo-santos-0aab58322/');


INSERT INTO labweb_project.tipo (idtipo, nome) VALUES
(1, 'beneficiário'),
(2, 'fornecedor'),
(3, 'híbrido');

INSERT INTO labweb_project.usuario (conta_idconta, cpf_ou_cnpj, nome_arquivo_foto, telefone, cidade_idcidade, tipo_idtipo, voluntario) VALUES
(4, '12345678909', 'foto_sophia.png', '71997172280', 1, 1, 0),
(5, '98765432100', 'foto_brunna.png', '71997456739', 1, 1, 0),
(6, '11122233396', 'foto_kaik.png', '71987664532', 2, 1, 1),
(10, '55544477735', 'foto_rafael.png', '7199443567', 3, 3, 0),
(7, '12345678000195', 'foto_fornecedora1.png', '71912345678', 1, 2, 0),
(8, '98765432000146', 'foto_fornecedora2.png', '21987654321', 4, 2, 0),
(9, '55444333000122', 'foto_aliimentos.png', '2134567890', 5, 2, 0);


INSERT INTO labweb_project.cidade (idcidade, nome, estado_idestado) VALUES
(1, 'Salvador', 1),
(2, 'Camaçari', 1),
(3, 'Lauro de Freitas', 1),
(4, 'Rio de Janeiro', 3),
(5, 'Santos', 3);

INSERT INTO labweb_project.estado (idestado, nome) VALUES
(1, 'Bahia'),
(2, 'Amazonas'),
(3, 'Rio de Janeiro');

INSERT INTO `labweb_project`.`produto` (`idproduto`, `nome`, `quantidade`, `data_validade`, `preco_unidade`) VALUES
(1, 'Arroz Integral 5kg', 50, '2024-12-31', 6.50),
(2, 'Feijão Carioca 1kg', 100, '2025-06-30', 8.50),
(3, 'Óleo de Soja 900ml', 30, '2024-10-15', NULL),
(4, 'Arroz Branco Prato Fino 5kg', 40, '2025-01-15', 5.80),  
(5, 'Feijão Camil 1kg', 80, '2025-07-20', 8.50),             
(6, 'Óleo de Girassol Soya 1L', 20, '2024-11-30', NULL),
(7, 'Arroz Parboilizado Uncle Ben’s 5kg', 60, '2025-03-10', 12.90),
(8, 'Feijão Preto Kero Coco 1kg', 70, '2025-08-05', 10.50),          
(9, 'Óleo de Milho Salada 900ml', 25, '2024-09-25', NULL);

INSERT INTO `labweb_project`.`anuncio` (
  `idanuncio`, `titulo`, `nome_arquivo_foto`, `data_expiracao`, `status`, `acao`, 
  `entrega_pelo_fornecedor`, `cidade_idcidade`, `produto_idproduto`, `anunciante_conta_idconta`
) VALUES
(1, 'Arroz Integral Tio João - Promoção', 'arroz_tiojoao.jpg', '2025-06-30', 'A', 'V', 1, 1, 1, 7),
(2, 'Feijão Kicaldo - Qualidade Garantida', 'feijao_kicaldo.jpg', '2024-07-15', 'E', 'V', 0, 1, 2, 7),
(3, 'Doação de Óleo Liza para Instituições', 'oleo_liza.jpg', '2025-05-20', 'F', 'D', 1, 1, 3, 7),
(4, 'Arroz Prato Fino - Atacado', 'arroz_pratofino.jpg', '2025-04-10', 'F', 'V', 1, 4, 4, 8),
(5, 'Feijão Camil - Ofertas para Restaurantes', 'feijao_camil.jpg', '2025-06-25', 'A', 'V', 1, 4, 5, 8),
(6, 'Óleo Soya para Comunidades Carentes', 'oleo_soya.jpg', '2024-07-30', 'E', 'D', 0, 4, 6, 8),
(7, 'Arroz Uncle Ben’s - Importado', 'arroz_unclebens.jpg', '2024-09-15', 'E', 'V', 0, 5, 7, 9),
(8, 'Feijão Preto Kero Coco - Tradicional', 'feijao_preto.jpg', '2024-08-05', 'F', 'V', 1, 5, 8, 9),
(9, 'Doação de Óleo Salada para Escolas', 'oleo_salada.jpg', '2025-06-10', 'A', 'D', 1, 5, 9, 9);

INSERT INTO `labweb_project`.`requisicao` (idrequisicao, usuario_conta_idconta, tipo_anterior) VALUES
(1, 10, 1)

-- I é interessado e A é aprovado
--para anuncios expirados
INSERT INTO `labweb_project`.`relacao_beneficiario` 
(`anuncio_idanuncio`, `usuario_conta_idconta`, `tipo_relacao_interessado`) VALUES
(2, 6, 'I'),  -- Kaik interessado no feijão
(7, 10, 'I'); -- Rafael interessado no arroz importado

--para anuncios finalizados
INSERT INTO `labweb_project`.`relacao_beneficiario` 
(`anuncio_idanuncio`, `usuario_conta_idconta`, `tipo_relacao_interessado`) VALUES
(3, 10, 'A'), -- Rafael aprovado na doação de óleo
(4, 5, 'A'),  -- Brunna aprovada no arroz
(8, 4, 'A');  -- Sophia aprovada no feijão preto

--para anuncios ativos
INSERT INTO `labweb_project`.`relacao_beneficiario` 
(`anuncio_idanuncio`, `usuario_conta_idconta`, `tipo_relacao_interessado`) VALUES
(1, 4, 'I'),  -- Sophia interessada no arroz
(1, 5, 'I'),  -- Brunna também interessada
(5, 4, 'I'),  -- Sophia interessada no feijão
(5, 6, 'I'),  -- Kaik também interessado
(9, 10, 'I');  -- Kaik interessado na doação de óleo

INSERT INTO `labweb_project`.`negociacao` 
(`negociacao_idnegociacao`, `valor_pago`, `quantidade`, `relacaoo_beneficiario_anuncio_idanuncio`, `relacao_beneficiario_usuario_conta_idconta`) VALUES

(1, NULL, 5, 3, 10),  -- Negociação 1: Doação de óleo (anúncio 3) para Rafael >>> Doação não tem valor_pago
(2, 23.20, 4, 4, 5),  -- Negociação 2: Compra de arroz (anúncio 4) por Brunna >>> 4 unidades x R$5.80 (preço do produto 4)
(3, 10.50, 1, 8, 4);  -- Negociação 3: Compra de feijão (anúncio 8) por Sophia >>> 1 unidade x R$10.50 (preço do produto 8)

INSERT INTO `labweb_project`.`causa` 
(`idcausa`, `nome`, `descricao`, `meta`, `prazo`, `nome_arquivo_foto`, `status_causa`) VALUES
(1, 'Natal Sem Fome', 'Arrecadação de alimentos para famílias em situação de vulnerabilidade durante o período natalino', 5000.00, '2024-12-20', 'natal_sem_fome.jpg', 'C'),
(2, 'Kit Escolar Solidário', 'Doação de materiais escolares para crianças de baixa renda no início do ano letivo', 3000.00, '2025-07-31', 'kit_escolar.jpg', 'A'),
(3, 'SOS Enchentes Bahia', 'Arrecadação emergencial para vítimas das enchentes no sul da Bahia', 10000.00, '2024-11-30', 'sos_enchentes.jpg', 'C'),
(4, 'Conectando Futuros', 'Captação de recursos para montar laboratórios de informática em escolas públicas', 15000.00, '2025-08-15', 'inclusao_digital.jpg', 'A'),
(5, 'Feira AgroSolidária', 'Financiamento coletivo para compra direta de produtores rurais familiares e doação a comunidades', 8000.00, '2024-09-30', 'feira_agro.jpg', 'C');