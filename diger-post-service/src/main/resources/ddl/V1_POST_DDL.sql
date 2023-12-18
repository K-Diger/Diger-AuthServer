create table if not exists post
(
    id         bigint auto_increment     not null primary key,
    title      varchar(255)              not null,
    content    varchar(255)              not null,
    type       enum ('NORMAL', 'NOTICE') not null,
    deleted_at datetime(6)               null,
    created_at datetime(6)               not null,
    updated_at datetime(6)               not null
);
