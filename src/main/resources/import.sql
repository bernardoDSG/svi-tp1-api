-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;
insert into Ator (nome,premios) values ('Brad Pitt','Oscar,Globo de Ouro') ;
insert into Ator (nome,premios) values ('Leonardo DiCaprio','Oscar') ;
insert into Idioma (nome,sigla) values ('portugues brasil','pt-br') ;
insert into Idioma (nome,sigla) values ('ingles','en-us') ;
insert into Filme (titulo,duracao,sinopse,classificacaoIndicativa,listaPremios,listaGeneros,idioma_id) 
values ('Era uma Vez em Hollywood', '2h40m','um filme sobre as aventuras em Hollywood'
,'16 anos','Oscar,Globo de Ouro','Ação,Comédia',2);
insert into filme_atores_principais (filme_id,ator_id) values (1,1);
insert into filme_atores_principais (filme_id,ator_id) values (1,2);
insert into Sala(dtype,nome) values ('Sala','Sala 1');
insert into Poltrona(nome,estaOcupada,sala_id) values ('A1',false,1);
insert into Poltrona(nome,estaOcupada,sala_id) values ('A2',true,1);
insert into Sessao(dtype,ingressosEsgotado,horarioInicio,horarioFim,idioma_id,filme_id) 
values('Sessao',false,'2025-04-10T20:30:00','2025-04-10T23:10:00',2,1);
insert into sessao_sala (sessao_id,sala_id) values (1,1);