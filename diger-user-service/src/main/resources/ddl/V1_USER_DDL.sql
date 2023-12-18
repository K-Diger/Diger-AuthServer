create table if not exists user
(
    id         bigint auto_increment not null primary key,
    login_id   varchar(255)          not null,
    password   varchar(255)          not null,
    nickname   varchar(255)          not null,
    deleted_at datetime(6)           not null,
    created_at datetime(6)           not null,
    updated_at datetime(6)           not null
);
