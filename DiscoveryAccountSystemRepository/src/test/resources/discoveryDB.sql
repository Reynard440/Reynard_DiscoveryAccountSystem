create schema discoverydb;

create table Member
(
	MemId int auto_increment,
	Mem_Email varchar(255) not null,
	Mem_First_Name varchar(255) not null,
	Mem_Last_Name varchar(255) null,
	Mem_Phone_Number varchar(10) not null,
	constraint Member_pk
		primary key (MemId)
);



