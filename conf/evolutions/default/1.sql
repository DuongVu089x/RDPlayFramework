# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table clazz (
  id                            integer auto_increment not null,
  name                          varchar(255) not null,
  create_name                   varchar(255),
  update_name                   varchar(255),
  is_active                     integer,
  constraint pk_clazz primary key (id)
);

create table student (
  id                            integer auto_increment not null,
  name                          varchar(255) not null,
  create_name                   varchar(255),
  update_name                   varchar(255),
  code                          varchar(11) not null,
  address                       varchar(50) not null,
  average_score                 decimal(38),
  date_of_birth                 date,
  is_active                     integer not null,
  constraint pk_student primary key (id)
);

create table student_class (
  studentid                     integer not null,
  classid                       integer not null,
  constraint pk_student_class primary key (studentid,classid)
);

create table user (
  id                            bigint auto_increment not null,
  username                      varchar(255) not null,
  password                      varchar(255) not null,
  status                        varchar(1),
  role                          varchar(10) not null,
  avatar                        varchar(255),
  constraint pk_user primary key (id)
);

alter table student_class add constraint fk_student_class_student foreign key (studentid) references student (id) on delete restrict on update restrict;
create index ix_student_class_student on student_class (studentid);

alter table student_class add constraint fk_student_class_clazz foreign key (classid) references clazz (id) on delete restrict on update restrict;
create index ix_student_class_clazz on student_class (classid);


# --- !Downs

alter table student_class drop foreign key fk_student_class_student;
drop index ix_student_class_student on student_class;

alter table student_class drop foreign key fk_student_class_clazz;
drop index ix_student_class_clazz on student_class;

drop table if exists clazz;

drop table if exists student;

drop table if exists student_class;

drop table if exists user;

