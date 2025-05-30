-- -----------------------------------------------------
-- Table `labweb_project`.`conta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `labweb_project`.`conta` (
  `idconta` INT(5) NOT NULL,
  `nome` VARCHAR(60) NOT NULL,
  `senha` VARCHAR(60) NOT NULL,
  `email` VARCHAR(60) NOT NULL,
  PRIMARY KEY (`idconta`),
  UNIQUE INDEX `idconta_UNIQUE` (`idconta` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `labweb_project`.`estado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `labweb_project`.`estado` (
  `idestado` INT(5) NOT NULL,
  `nome` VARCHAR(60) NOT NULL,
  PRIMARY KEY (`idestado`),
  UNIQUE INDEX `idestado_UNIQUE` (`idestado` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `labweb_project`.`cidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `labweb_project`.`cidade` (
  `idcidade` INT(5) NOT NULL,
  `nome` VARCHAR(60) NOT NULL,
  `estado_idestado` INT(5) NOT NULL,
  PRIMARY KEY (`idcidade`),
  UNIQUE INDEX `idcidade_UNIQUE` (`idcidade` ASC) VISIBLE,
  INDEX `fk_cidade_estado1_idx` (`estado_idestado` ASC) VISIBLE,
  CONSTRAINT `fk_cidade_estado1`
    FOREIGN KEY (`estado_idestado`)
    REFERENCES `labweb_project`.`estado` (`idestado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `labweb_project`.`tipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `labweb_project`.`tipo` (
  `idtipo` INT(5) NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idtipo`),
  UNIQUE INDEX `idtipo_UNIQUE` (`idtipo` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `labweb_project`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `labweb_project`.`usuario` (
  `conta_idconta` INT(5) NOT NULL,
  `cpf_ou_cnpj` BIGINT NOT NULL,
  `nome_arquivo_foto` VARCHAR(60) NULL,
  `telefone` BIGINT NOT NULL,
  `email_contato` VARCHAR(60) NOT NULL,
  `cidade_idcidade` INT(5) NOT NULL,
  `tipo_idtipo` INT(5) NOT NULL,
  `voluntario` INT(1) NOT NULL,
  PRIMARY KEY (`conta_idconta`),
  UNIQUE INDEX `conta_idconta_UNIQUE` (`conta_idconta` ASC) VISIBLE,
  INDEX `fk_usuario_cidade1_idx` (`cidade_idcidade` ASC) VISIBLE,
  UNIQUE INDEX `cpf_ou_cnpj_UNIQUE` (`cpf_ou_cnpj` ASC) VISIBLE,
  INDEX `fk_usuario_tipo1_idx` (`tipo_idtipo` ASC) VISIBLE,
  CONSTRAINT `fk_usuario_conta1`
    FOREIGN KEY (`conta_idconta`)
    REFERENCES `labweb_project`.`conta` (`idconta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuario_cidade1`
    FOREIGN KEY (`cidade_idcidade`)
    REFERENCES `labweb_project`.`cidade` (`idcidade`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuario_tipo1`
    FOREIGN KEY (`tipo_idtipo`)
    REFERENCES `labweb_project`.`tipo` (`idtipo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `labweb_project`.`produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `labweb_project`.`produto` (
  `idproduto` INT(5) NOT NULL,
  `nome` VARCHAR(60) NOT NULL,
  `quantidade` INT(3) NOT NULL,
  `data_validade` DATE NOT NULL,
  `preco_unidade` DECIMAL(5,2) NULL,
  PRIMARY KEY (`idproduto`),
  UNIQUE INDEX `idproduto_UNIQUE` (`idproduto` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `labweb_project`.`anuncio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `labweb_project`.`anuncio` (
  `idanuncio` INT(5) NOT NULL,
  `titulo` VARCHAR(60) NOT NULL,
  `nome_arquivo_foto` VARCHAR(60) NOT NULL,
  `data_expiracao` DATE NOT NULL,
  `status` VARCHAR(1) NOT NULL,
  `acao` VARCHAR(1) NOT NULL,
  `entrega_pelo_fornecedor` INT(1) NOT NULL,
  `cidade_idcidade` INT(5) NOT NULL,
  `produto_idproduto` INT(5) NOT NULL,
  `anunciante_conta_idconta` INT(5) NOT NULL,
  PRIMARY KEY (`idanuncio`),
  UNIQUE INDEX `idanuncio_UNIQUE` (`idanuncio` ASC) VISIBLE,
  INDEX `fk_anuncio_cidade1_idx` (`cidade_idcidade` ASC) VISIBLE,
  INDEX `fk_anuncio_produto1_idx` (`produto_idproduto` ASC) VISIBLE,
  INDEX `fk_anuncio_usuario1_idx` (`anunciante_conta_idconta` ASC) VISIBLE,
  CONSTRAINT `fk_anuncio_cidade1`
    FOREIGN KEY (`cidade_idcidade`)
    REFERENCES `labweb_project`.`cidade` (`idcidade`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_anuncio_produto1`
    FOREIGN KEY (`produto_idproduto`)
    REFERENCES `labweb_project`.`produto` (`idproduto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_anuncio_usuario1`
    FOREIGN KEY (`anunciante_conta_idconta`)
    REFERENCES `labweb_project`.`usuario` (`conta_idconta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `labweb_project`.`causa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `labweb_project`.`causa` (
  `idcausa` INT(5) NOT NULL,
  `nome` VARCHAR(60) NOT NULL,
  `descricao` TEXT(150) NOT NULL,
  `meta` DECIMAL(7,2) NOT NULL,
  `prazo` DATE NOT NULL,
  `nome_arquivo_foto` VARCHAR(60) NULL,
  PRIMARY KEY (`idcausa`),
  UNIQUE INDEX `idcausa_UNIQUE` (`idcausa` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `labweb_project`.`motivo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `labweb_project`.`motivo` (
  `idmotivo` INT(5) NOT NULL,
  `nome` VARCHAR(60) NOT NULL,
  PRIMARY KEY (`idmotivo`),
  UNIQUE INDEX `idmotivo_UNIQUE` (`idmotivo` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `labweb_project`.`denuncia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `labweb_project`.`denuncia` (
  `iddenuncia` INT(5) NOT NULL,
  `motivo_idmotivo` INT(5) NOT NULL,
  `denunciante_usuario_conta_idconta` INT(5) NOT NULL,
  `denunciado_usuario_conta_idconta` INT(5) NOT NULL,
  PRIMARY KEY (`iddenuncia`),
  UNIQUE INDEX `iddenuncia_UNIQUE` (`iddenuncia` ASC) VISIBLE,
  INDEX `fk_denuncia_motivo1_idx` (`motivo_idmotivo` ASC) VISIBLE,
  INDEX `fk_denuncia_usuario1_idx` (`denunciante_usuario_conta_idconta` ASC) VISIBLE,
  INDEX `fk_denuncia_usuario2_idx` (`denunciado_usuario_conta_idconta` ASC) VISIBLE,
  CONSTRAINT `fk_denuncia_motivo1`
    FOREIGN KEY (`motivo_idmotivo`)
    REFERENCES `labweb_project`.`motivo` (`idmotivo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_denuncia_usuario1`
    FOREIGN KEY (`denunciante_usuario_conta_idconta`)
    REFERENCES `labweb_project`.`usuario` (`conta_idconta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_denuncia_usuario2`
    FOREIGN KEY (`denunciado_usuario_conta_idconta`)
    REFERENCES `labweb_project`.`usuario` (`conta_idconta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `labweb_project`.`avaliacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `labweb_project`.`avaliacao` (
  `idavaliacao` INT(5) NOT NULL,
  `nota` INT(1) NOT NULL,
  `comentario` VARCHAR(100) NULL,
  `avaliador_usuario_conta_idconta` INT(5) NOT NULL,
  `avaliado_usuario_conta_idconta1` INT(5) NOT NULL,
  PRIMARY KEY (`idavaliacao`),
  UNIQUE INDEX `idavaliacao_UNIQUE` (`idavaliacao` ASC) VISIBLE,
  INDEX `fk_avaliacao_usuario1_idx` (`avaliador_usuario_conta_idconta` ASC) VISIBLE,
  INDEX `fk_avaliacao_usuario2_idx` (`avaliado_usuario_conta_idconta1` ASC) VISIBLE,
  CONSTRAINT `fk_avaliacao_usuario1`
    FOREIGN KEY (`avaliador_usuario_conta_idconta`)
    REFERENCES `labweb_project`.`usuario` (`conta_idconta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_avaliacao_usuario2`
    FOREIGN KEY (`avaliado_usuario_conta_idconta1`)
    REFERENCES `labweb_project`.`usuario` (`conta_idconta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `labweb_project`.`usuario_has_causa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `labweb_project`.`usuario_has_causa` (
  `usuario_conta_idconta` INT(5) NOT NULL,
  `causa_idcausa` INT(5) NOT NULL,
  `valor_doado` DECIMAL(5,2) NOT NULL,
  PRIMARY KEY (`usuario_conta_idconta`, `causa_idcausa`),
  INDEX `fk_usuario_has_causa_causa1_idx` (`causa_idcausa` ASC) VISIBLE,
  INDEX `fk_usuario_has_causa_usuario1_idx` (`usuario_conta_idconta` ASC) VISIBLE,
  CONSTRAINT `fk_usuario_has_causa_usuario1`
    FOREIGN KEY (`usuario_conta_idconta`)
    REFERENCES `labweb_project`.`usuario` (`conta_idconta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuario_has_causa_causa1`
    FOREIGN KEY (`causa_idcausa`)
    REFERENCES `labweb_project`.`causa` (`idcausa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `labweb_project`.`relacao_beneficiario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `labweb_project`.`relacao_beneficiario` (
  `anuncio_idanuncio` INT(5) NOT NULL,
  `usuario_conta_idconta` INT(5) NOT NULL,
  `tipo_relacao_interessado` VARCHAR(1) NOT NULL,
  PRIMARY KEY (`anuncio_idanuncio`, `usuario_conta_idconta`),
  INDEX `fk_anuncio_has_usuario_usuario1_idx` (`usuario_conta_idconta` ASC) VISIBLE,
  INDEX `fk_anuncio_has_usuario_anuncio1_idx` (`anuncio_idanuncio` ASC) VISIBLE,
  CONSTRAINT `fk_anuncio_has_usuario_anuncio1`
    FOREIGN KEY (`anuncio_idanuncio`)
    REFERENCES `labweb_project`.`anuncio` (`idanuncio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_anuncio_has_usuario_usuario1`
    FOREIGN KEY (`usuario_conta_idconta`)
    REFERENCES `labweb_project`.`usuario` (`conta_idconta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `labweb_project`.`negociacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `labweb_project`.`negociacao` (
  `negociacao_idnegociacao` INT(5) NOT NULL,
  `valor_pago` DECIMAL(5,2) NULL,
  `quantidade` INT NOT NULL,
  `relacaoo_beneficiario_anuncio_idanuncio` INT(5) NOT NULL,
  `relacao_beneficiario_usuario_conta_idconta` INT(5) NOT NULL,
  PRIMARY KEY (`negociacao_idnegociacao`),
  UNIQUE INDEX `negociacao_idnegociacao_UNIQUE` (`negociacao_idnegociacao` ASC) VISIBLE,
  INDEX `fk_compra_negociacao1_idx` (`relacaoo_beneficiario_anuncio_idanuncio` ASC, `relacao_beneficiario_usuario_conta_idconta` ASC) VISIBLE,
  CONSTRAINT `fk_compra_negociacao1`
    FOREIGN KEY (`relacaoo_beneficiario_anuncio_idanuncio` , `relacao_beneficiario_usuario_conta_idconta`)
    REFERENCES `labweb_project`.`relacao_beneficiario` (`anuncio_idanuncio` , `usuario_conta_idconta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `labweb_project`.`requisicao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `labweb_project`.`requisicao` (
  `idrequisicao` INT(5) NOT NULL,
  `usuario_conta_idconta` INT(5) NOT NULL,
  `tipo_anterior` INT(5) NOT NULL,
  PRIMARY KEY (`idrequisicao`),
  UNIQUE INDEX `idrequisicao_UNIQUE` (`idrequisicao` ASC) VISIBLE,
  INDEX `fk_requisicao_usuario1_idx` (`usuario_conta_idconta` ASC) VISIBLE,
  INDEX `fk_requisicao_tipo1_idx` (`tipo_anterior` ASC) VISIBLE,
  CONSTRAINT `fk_requisicao_usuario1`
    FOREIGN KEY (`usuario_conta_idconta`)
    REFERENCES `labweb_project`.`usuario` (`conta_idconta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_requisicao_tipo1`
    FOREIGN KEY (`tipo_anterior`)
    REFERENCES `labweb_project`.`tipo` (`idtipo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `labweb_project`.`administrador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `labweb_project`.`administrador` (
  `conta_idconta` INT(5) NOT NULL,
  `github` VARCHAR(45) NOT NULL,
  `linkedin` VARCHAR(45) NOT NULL,
  INDEX `fk_table1_conta1_idx` (`conta_idconta` ASC) VISIBLE,
  PRIMARY KEY (`conta_idconta`),
  CONSTRAINT `fk_table1_conta1`
    FOREIGN KEY (`conta_idconta`)
    REFERENCES `labweb_project`.`conta` (`idconta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
