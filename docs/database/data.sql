
delete from plans;

insert into plans(id, name,description)
values (nextval('plan_id_sequence'), '2 veces por semana','Dos veces por semana, una hora por dia');

insert into plans(id, name,description)
values (nextval('plan_id_sequence'), '3 veces por semana','Tres veces por semana, una hora por dia');


/* estados de pagos */
insert into payment_states(id, name,description)
values (1, 'Al dia','');

insert into payment_states(id, name,description)
values (2, 'Proximo a vencer','');

insert into payment_states(id, name,description)
values (3, 'Vencido','');