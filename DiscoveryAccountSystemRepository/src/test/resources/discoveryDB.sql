create schema testDB;

create table MemberTest
(
	MemIdT int auto_increment,
	Mem_EmailT varchar(255) not null,
	Mem_First_NameT varchar(255) not null,
	Mem_Last_NameT varchar(255) null,
	Mem_Phone_NumberT varchar(10) not null,
	constraint Member_pk_t
		primary key (MemIdT)
);



