create schema discoverydb;

create table member
(
    MemID            int auto_increment
        primary key,
    Mem_Email        varchar(255) not null,
    Mem_First_Name   varchar(255) not null,
    Mem_Last_Name    varchar(255) not null,
    Mem_Phone_Number varchar(255) not null
);

create table exchange_medium
(
    EM_ID          int auto_increment
        primary key,
    EM_Type        varchar(255) not null,
    EM_Description varchar(255) not null,
    EM_Balance     double       not null,
    EM_Date        date         not null,
    memId          int          not null,
    constraint em_mem_fk
        foreign key (memId) references member (MemID)
            on update cascade
);

create index em_mem_fk_idx
    on exchange_medium (memId);

create index mem_email_index
    on member (Mem_Email);

create table member_transaction
(
    MT_ID               int auto_increment
        primary key,
    MT_Amount           double       not null,
    MT_Description      varchar(255) not null,
    MT_Total            double       not null,
    MT_Transaction_Date date         not null,
    Em_ID               int          not null,
    constraint mt_em_fk
        foreign key (Em_ID) references exchange_medium (EM_ID)
);

create index mt_em_index
    on member_transaction (Em_ID);


