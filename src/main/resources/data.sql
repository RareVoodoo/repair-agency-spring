

insert into users (username, password, enabled) values
('user','pass',true),
('manager','pass', true),
('master','pass',true);




insert into authorities (username, authority) values
('user', 'ROLE_USER'),
('manager', 'ROLE_ADMIN'),
('master', 'ROLE_MASTER');


insert into request (username, description, accepted, performed) values
('test_user','test description 1', false ,false),
('test_user1','test description 2', false ,false),
('test_user2','test description 3', false ,false);