create table kyc_main.legal_entity
(
    id         bigserial primary key,
    exposed_id bigint
);

create table kyc_main.account_holder
(
    legal_entity_id bigserial primary key,
    business_name   character varying not null,
    foreign key (legal_entity_id) references kyc_main.legal_entity (id)
        match simple on update no action on delete restrict
);



create table kyc_main.balance_account
(
    id                bigserial primary key,
    account_holder_id bigserial not null,
    foreign key (account_holder_id) references kyc_main.account_holder (legal_entity_id)
        match simple on update no action on delete no action
);


create table kyc_main.balance_account_sweep
(
    id                 bigserial primary key,
    balance_account_id bigint            not null,
    type               character varying not null,
    foreign key (balance_account_id) references kyc_main.balance_account (id)
        match simple on update no action on delete no action
);

create table kyc_main.legal_entity_association
(
    id                         bigserial primary key,
    legal_entity_id            bigserial not null,
    associated_legal_entity_id bigint    not null,
    job_title                  character varying,
    foreign key (associated_legal_entity_id) references kyc_main.legal_entity (id)
        match simple on update no action on delete no action,
    foreign key (legal_entity_id) references kyc_main.legal_entity (id)
        match simple on update no action on delete no action
);