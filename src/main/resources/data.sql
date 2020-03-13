

insert into users (username, password, enabled) values
('user','pass',true),
('manager','pass', true),
('master','pass',true);




insert into authorities (username, authority) values
('user', 'ROLE_USER'),
('manager', 'ROLE_ADMIN'),
('master', 'ROLE_MASTER');