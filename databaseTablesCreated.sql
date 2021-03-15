create table users_added (
                             userId serial primary key,
                             email varchar(50) unique not null,
                             passwordEncrypted varchar(100) not null,
                             firstname varchar(30),
                             lastname varchar(30),
                             age varchar(15),
                             city varchar(30),
                             country varchar(30),
                             gender varchar(20),
                             courseName varchar(50)
)

create table courses_of_website (
                                    courseId serial primary key,
                                    courseName varchar(50) not null
)

insert into courses_of_website values(1, "WebandMobile2");
insert into courses_of_website values(2, "Calculus2");
insert into courses_of_website values(3, "Principles of Information Systems");