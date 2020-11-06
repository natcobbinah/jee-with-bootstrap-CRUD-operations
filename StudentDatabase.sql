#####################################
#Create Student Database
#####################################
create database StudentDB;

#####################################
#Use Student database
#####################################
use StudentDB;

#####################################
#Create table Students
#####################################
create table Students(
	StudentId int,
    Surname varchar(64),
    Firstname varchar(64),
    Gender varchar(30),
    DateofBirth varchar(64),
    Email varchar(64),
    Country varchar(64),
    PRIMARY KEY(StudentId)
) ENGINE = InnoDB;

#####################################
#select all students
#####################################
select * from students;





