-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;
insert into idioma(nome,sigla) values('Português do Brasil','pt-br');
insert into idioma(nome,sigla) values('Russo','rus');
insert into ator(nome,premios) values('Brad Pitt','Oscar,Globo de Ouro');
insert into ator(nome,premios) values('Ronald Reagan','Oscar,British Academy Film Awards');
insert into sala(nome)values('Sala Refresh');
insert into poltrona(nome,sala_id)values('R1',1);
insert into poltrona(nome,sala_id)values('R2',1);
insert into sala(nome)values('Sala Hip Hop');
insert into poltrona(nome,sala_id)values('H1',2);
insert into poltrona(nome,sala_id)values('H2',2);
insert into sala(nome)values('Sala Youtube');
insert into poltrona(nome,sala_id)values('Y1',3);
insert into poltrona(nome,sala_id)values('Y2',3);
insert into filme(titulo,duracao,sinopse,classificacaoIndicativa,listaPremios,listaGeneros,idioma_id)values('Embrapa: Uma história de guerra','2h30min','conta a historia da empresa mais magnifica do brasil','Livre','Oscar,Globo de Ouro','Ação,Comédia',1);
insert into filme(titulo,duracao,sinopse,classificacaoIndicativa,listaPremios,listaGeneros,idioma_id)values('Guerra nas Estrelas: A Vingança dos Sith','2h20min','A história dos jedis e siths','12 anos','Oscar,British Academy Film Awards','Ficção Científica,Aventura',2);
insert into filme_atores_principais(filme_id,ator_id) values(1,1);
insert into sessao(horarioInicio,horarioFim,idioma_id,filme_id) values('2024-07-01 14:00:00','2024-07-01 16:30:00',1,1);
insert into sessao_sala(sessao_id,sala_id) values(1,1);
