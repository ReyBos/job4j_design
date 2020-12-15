create table position (
    id serial primary key,
    name varchar(255)
);
create table employer (
    id serial primary key,
    name varchar(255),
    position_id int references position(id)
);
insert into position(name) VALUES ('programmer');
insert into employer(name, position_id) VALUES ('Ivan', 1);
select * from employer;

create table students(
    id serial primary key,
    name varchar(255)
);

create table courses(
    id serial primary key,
    name varchar(255)
);

create table students_courses(
    id serial primary key,
    student_id int references students(id),
    course_id int references courses(id)
);

insert into students(name) values ('Ivan');
insert into students(name) values ('Kirill');
insert into students(name) values ('Roman');

insert into courses(name) values ('Java SE');
insert into courses(name) values ('Spring');
insert into courses(name) values ('Hibernate');

insert into students_courses(student_id, course_id) values (1, 1);
insert into students_courses(student_id, course_id) values (1, 2);
insert into students_courses(student_id, course_id) values (1, 3);
insert into students_courses(student_id, course_id) values (2, 1);
insert into students_courses(student_id, course_id) values (2, 2);
insert into students_courses(student_id, course_id) values (1, 3);