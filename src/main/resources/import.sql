-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;
insert into idioma(nome,sigla) values('Português do Brasil','pt-br');
insert into ator(nome,premios) values('Brad Pitt','Oscar,Globo de Ouro');
insert into sala(nome)values('Sala Refresh');
insert into poltrona(nome,sala_id)values('R1',1);
insert into poltrona(nome,sala_id)values('R2',1);
insert into filme(titulo,duracao,sinopse,classificacaoIndicativa,listaPremios,listaGeneros,idioma_id)values('Embrapa: Uma história de guerra','2h30min','conta a historia da empresa mais magnifica do brasil','Livre','Oscar,Globo De Ouro','Ação,Comédia',1);
insert into filme_atores_principais(filme_id,ator_id) values(1,1);