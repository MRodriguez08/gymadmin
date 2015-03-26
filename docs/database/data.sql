CREATE  TABLE users (
  username VARCHAR(45) NOT NULL ,
  password VARCHAR(45) NOT NULL ,
  enabled TINYINT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (username));
  
CREATE TABLE user_roles (
  user_role_id INT(11) NOT NULL AUTO_INCREMENT,
  username VARCHAR(45) NOT NULL,
  ROLE VARCHAR(45) NOT NULL,
  PRIMARY KEY (user_role_id),
  UNIQUE KEY uni_username_role (ROLE,username),
  KEY fk_username_idx (username),
  CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES users (username));
  
INSERT INTO users(username,password,enabled)
VALUES ('mrodriguez','$2a$10$ae7A7w3ef.nzJpG2aKmyeenhFsA4jljL6q.yAXU6vdhPMemgqngwS', TRUE);
INSERT INTO users(username,password,enabled)
VALUES ('pantera','$2a$10$OmcIfh64Z4JEfD2brEdKgetS1Wc6CmRy8sRhsIschgyiUrW0lB6gu', TRUE);

INSERT INTO user_roles (username, ROLE)	VALUES ('mrodriguez', 'ROLE_USER');
INSERT INTO user_roles (username, ROLE) VALUES ('mrodriguez', 'ROLE_ADMIN');
INSERT INTO user_roles (username, ROLE) VALUES ('pantera', 'ROLE_USER');


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

