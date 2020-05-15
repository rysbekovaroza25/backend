alter table drivers add column password varchar(255);

alter table drivers drop column user_id;

alter table drivers rename to users;