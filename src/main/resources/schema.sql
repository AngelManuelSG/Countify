
    create table app_user (
        birth_date date not null,
        user_id bigint not null auto_increment,
        email varchar(255) not null,
        encrypted_password varchar(255) not null,
        phone varchar(255),
        profile_picture varchar(255),
        user_name varchar(255) not null,
        primary key (user_id)
    ) engine=InnoDB;

    create table category (
        cat_id bigint not null auto_increment,
        user_fk bigint,
        cat_description varchar(255) not null,
        cat_name varchar(255) not null,
        primary key (cat_id)
    ) engine=InnoDB;

    create table spending_limit (
        deadline date not null,
        init_date date not null,
        limit_quantity decimal(38,2) not null,
        limit_id bigint not null auto_increment,
        user_fk bigint,
        primary key (limit_id)
    ) engine=InnoDB;

    create table transaction (
        quantity decimal(38,2) not null,
        transaction_date date not null,
        category_fk bigint,
        transaction_id bigint not null auto_increment,
        user_fk bigint,
        description varchar(255) not null,
        large_description varchar(255),
        type enum ('INCOME','OUTLAY') not null,
        primary key (transaction_id)
    ) engine=InnoDB;

    alter table app_user 
       add constraint UKcpt2jpnop7mcpds1sv2i5629w unique (user_name);

    alter table category 
       add constraint UKnjik5b9b2mrl1s1vx2ve40wt0 unique (cat_name);

    alter table category 
       add constraint FK3vkatve48kwcofi0i6ukfhlir 
       foreign key (user_fk) 
       references app_user (user_id);

    alter table spending_limit 
       add constraint FK9i8ln18f8qcmpodmrh0gwvnk7 
       foreign key (user_fk) 
       references app_user (user_id);

    alter table transaction 
       add constraint FKko2kimbwik7pygoemxu09o0sw 
       foreign key (category_fk) 
       references category (cat_id);

    alter table transaction 
       add constraint FKq4i6e9jk5lnd652974of7xjhl 
       foreign key (user_fk) 
       references app_user (user_id);
