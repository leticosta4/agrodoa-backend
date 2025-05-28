-- contas de adm
INSERT INTO labweb_project.conta (idconta, nome, senha, email) VALUES
(1, 'Letícia Almeida', 'senha123', 'leticiacostaoa@gmail.com'),
(2, 'Alysson Oliveira', 'senha456', 'alyssonoliveira456@email.com'),
(3, 'Cainan de Brito', 'senha789', 'cainan.bas@gmail.com');

-- em adm
INSERT INTO labweb_project.administrador (conta_idconta, github, linkedin) VALUES
(1, 'https://github.com/leticosta4/', 'https://www.linkedin.com/in/let%C3%ADcia-almeida-9704162a0/'),
(2, 'https://github.com/verttB', 'https://www.linkedin.com/in/alysson-dos-anjos-00b431305/'),
(3, 'https://github.com/Cainan-bas', 'https://www.linkedin.com/in/cainan-de-brito-araujo-santos-0aab58322/');

