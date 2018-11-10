set search_path = ibank;

create table if not exists users (
  id bigserial primary key,
  loginname character varying(100) not null unique,
  password character varying(100)  not null,
  name character varying(100) not null,
  role_id integer not null
);

create table if not exists employee (
  id bigint primary key references users (id),
  division_id integer
);

create table if not exists person (
  id bigint primary key references users (id),
  phone character varying(100)
);

create table if not exists business (
  id bigserial primary key,
  name character varying(100) not null unique,
  unp character varying(9)
);

create table if not exists business_person (
  business_id bigint references business (id),
  person_id bigint references person (id),
  primary key (business_id, person_id)
);

create table if not exists card_account(
  id bigserial primary key,
  currency character varying (3),
  rest numeric(10, 2) default 0,
  person_id bigint not null references person (id)
);

create table if not exists card_type (
  id integer primary key,
  type_name character varying (100)
);

create table if not exists card (
  id bigserial primary key,
  card_number character varying(16),
  type_id integer references card_type (id),
  account_id bigint references card_account (id)
);


/*
create table if not exists operation (
  id bigserial primary key,
  card_id bigint references card (id),
  operation_date date,
  currcode integer references currency (currcode),
  amount numeric(16, 2)
);

create table if not exists payment_types (
  id serial primary key,
  name character varying (100)
);

create table if not exists payment (
  id bigint references operations (id),
  payment_type_id integer references payment_type (id),
  payment_params character varying (100)
);

create table if not exists transfer
  id bigint references operations (id),
  receiver_card_id bigint references card (id)
);

create table if not exists currency (
  code integer primary key,
  name character varying (100)
);
*/

--alter table ibank.business_person
--add constraint business_person_person_id_fk
--foreign key (person_id) references person(id);

--drop extension pgcrypto;
--select encode(digest('test message', 'sha256'), 'hex') hash;

select * from person p
left join card_account a on a.person_id = p.id
join card c on c.account_id = a.id;
