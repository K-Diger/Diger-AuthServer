create table if not exists user
(
    id       bigint auto_increment not null primary key,
    login_id varchar(255)          not null,
    password varchar(255)          not null,
    nickname varchar(255)          not null,
    role     enum ('NORMAL', 'NOTICE')
);
