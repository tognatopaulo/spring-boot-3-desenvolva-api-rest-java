create sequence usuarios_seq;

create table usuarios(

                         id bigint not null default nextval ('usuarios_seq'),
                         login varchar(100) not null,
                         senha varchar(255) not null,

                         primary key(id)

);