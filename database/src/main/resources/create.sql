set search_path = ibank;

create table if not exists users (
  id bigserial primary key,
  loginname character varying(100) not null unique,
  password character varying(100)  not null,
  name character varying(100) not null
);

create table if not exists employee (
  id bigint primary key references users (id),
  role_id integer,
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
  type_id integer references card_type(id),
  account_id bigint references card_account (id)
);

--ALTER TABLE ibank.business_person
--ADD CONSTRAINT business_person_person_id_fk
--FOREIGN KEY (person_id) REFERENCES person(id);

--drop extension pgcrypto;
--select encode(digest('test message', 'sha256'), 'hex') hash;

