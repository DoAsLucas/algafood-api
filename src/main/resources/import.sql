insert into kitchen (id, name) values (1,'Tailandesa');
insert into kitchen (id, name) values (2,'Indiana');
insert into kitchen (id, name) values (3,'Australiana');


insert into restaurant (name, taxa_frete, kitchen_id) values ('Lanchonete Jóia', 7.90, 1);
insert into restaurant (name, taxa_frete, kitchen_id) values ('César Lanches', 9.00, 1);
insert into restaurant (name, taxa_frete, kitchen_id) values ('Yaki Hashi', 5.30, 2);
insert into restaurant (name, taxa_frete, kitchen_id) values ('Outback', 11.90, 3);

insert into payment_method (description) values ("Débito");
insert into payment_method (description) values ("Crédito");
insert into payment_method (description) values ("Dinheiro");

insert into permission (name, description) values ("Administrador", "Possui todos os acessos do sistema");
insert into permission (name, description) values ("Atendente", "Possui acesso limitado ao atendimento");

insert into state (id, name) values (1, "Bahia");
insert into state (id, name) values (2, "Amazonas");
insert into state (id, name) values (3, "Sergipe");

insert into city (name, state_id) values ("Salvador", 1);
insert into city (name, state_id) values ("Feira de Santana", 1);
insert into city (name, state_id) values ("Manaus", 2);
insert into city (name, state_id) values ("Itabaiana", 3);
insert into city (name, state_id) values ("Aracajú", 3);