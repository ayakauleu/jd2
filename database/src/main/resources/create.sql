set search_path = ibank;

drop table person cascade;
create table person (
  id bigserial primary key,
  loginname character varying(100) not null unique,
  password character varying(100)  not null,
  first_name character varying(100) ,
  last_name character varying(100) not null
);

drop table business cascade;
create table business (
  id bigserial primary key,
  name character varying(100) not null unique,
  unp character varying(9)
);

drop table business_role cascade;
create table business_role (
  id int primary key,
  name character varying(50) not null unique
);

drop table business_person_role ;
create table business_person_role (
  business_id bigint references business (id),
  person_id bigint references person (id),
  role_id int references business_role(id),
  primary key (business_id, person_id, role_id)
);



--drop extension pgcrypto;
--select encode(digest('test message', 'sha256'), 'hex') hash;

