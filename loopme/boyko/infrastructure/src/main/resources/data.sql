insert into User (name, email, role) values ('PredefinedAdmin', 'admin@email.com', 'ADMIN');
insert into User (name, email, role) values ('PredefinedOperator', 'operator@email.com', 'OPERATOR');
insert into User (name, email, role) values ('PredefinedPublisher', 'publisher@email.com', 'PUBLISHER');

insert into Application (name, application_Type, user_Id) values ('YouTube', 'ANDROID', select id from User where name = 'PredefinedPublisher');
insert into Application (name, application_Type, user_Id) values ('MK Mobile', 'IOS', select id from User where name = 'PredefinedOperator');

insert into Application_content_types (id, content_type_id) values (select id from Application where name = 'YouTube', 0);  -- content_type_id = 0 it corresponds to coordinate number of ContentType.VIDEO
insert into Application_content_types (id, content_type_id) values (select id from Application where name = 'MK Mobile', 1); -- content_type_id = 1 it corresponds to coordinate number of ContentType.IMAGE