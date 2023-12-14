create table if not exists post
(
    id      bigint auto_increment not null primary key,
    title   varchar(255)          not null,
    content varchar(255)          not null,
    type    enum ('NORMAL', 'NOTICE')
);
