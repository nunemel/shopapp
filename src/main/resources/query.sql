
    create table e_role (
       id bigint not null,
        created_by varchar(255),
        creation_date timestamp,
        last_modified_by varchar(255),
        last_modified_date timestamp,
        description varchar(255),
        name varchar(255) not null,
        primary key (id)
    );

create table e_user (
       id bigint not null,
        created_by varchar(255),
        creation_date timestamp,
        last_modified_by varchar(255),
        last_modified_date timestamp,
        active char(1) not null,
        email varchar(100) not null,
        first_name varchar(64) not null,
        last_name varchar(64) not null,
        password varchar(255) not null,
        username varchar(255),
        primary key (id)
    );

create table e_user_role (
       user_id bigint not null,
        role_id bigint not null,
        primary key (user_id, role_id)
    );

    alter table e_user 
       add constraint UK_rfcmqmt0hlo8decofnacwoa7o unique (email);
    alter table e_user 
       add constraint UK_918qn7ukklskyt1wu4x295ahk unique (username);


 alter table e_user_role 
       add constraint FKcjdium26n4nifd99y4oh6aglp 
       foreign key (role_id) 
       references e_role;

    alter table e_user_role 
       add constraint FKne6fk4kdvk2gqc1luq8jhon20 
       foreign key (user_id) 
       references e_user;

insert 
    into
        e_user_role
        (user_id, role_id) 
    values
        (3, 1);

insert 
    into
        e_user_role
        (user_id, role_id) 
    values
        (4, 2);

insert 
    into
        e_role
        (created_by, creation_date, last_modified_by, last_modified_date, description, name, id) 
    values
        ("ana", "2021-02-22T05:03:33.066+00:00", "ana", "2021-02-22T05:03:33.066+00:00", "user ana role", "USER", 1);
insert 
    into
        e_role
        (created_by, creation_date, last_modified_by, last_modified_date, description, name, id) 
    values
        ("vas", "2021-02-22T05:03:33.066+00:00", "vas", "2021-02-22T05:03:33.066+00:00", "admin vas role", "ADMIN", 2);

insert 
    into
        e_user
        (created_by, creation_date, last_modified_by, last_modified_date, active, email, first_name, last_name, password, username, id) 
    values
        ("ana", "2021-02-22T05:03:33.066+00:00", ?, "2021-02-22T05:03:33.066+00:00", true, "ana@email.com", "ana", "ana", "$2a$10$Ymi9CJNEbMTRxj9H.VSIM.y.jfHFstVYa9rgdT7SP1m4/r2eQkGDi", "ana", 3);
