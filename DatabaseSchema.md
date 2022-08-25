### MySql Code for database setup

<li> mysql> create database smartbank;
  
<li> mysql> create table corporate(corporateId int not null primary key auto_increment,corporateName varchar(30),address varchar(30),contactNumber varchar(10));
  
<li> mysql> ALTER TABLE corporate AUTO_INCREMENT=1001;
  
<li> mysql> alter table corporate add softdeleted boolean default false;
  
<li> mysql> create table users(corporateId int, foreign key(corporateId) references corporate(corporateId),loginId varchar(30),password varchar(20),username varchar(30),department varchar(20), address varchar(30), contactNumber varchar(10));
  
<li> mysql> alter table users add changed boolean default false;
  
<li> mysql> create table accounts(corporateId int,foreign key(corporateId) references corporate(corporateId),accountNumber varchar(10) primary key,accountName varchar(20),branch varchar(20),currency varchar(20),balance long,active boolean,openingBalance long,closingBalance long);
  
<li> mysql> create table transaction(accountNumber varchar(10),foreign key(accountNumber) references accounts(accountNumber),transactionId int not null primary key auto_increment,transactionType varchar(10),transactionDate date,balance long);
  
<li> mysql> ALTER TABLE transaction AUTO_INCREMENT=1001;
  
  
  ### Inserting some basic values
  
 <li> mysql> insert into corporate(corporateName,address,contactNumber) values ('Infosys','Mumbai,India','1111111111'),('Wipro','Pune,India','2222222222');
  
 <li> mysql> insert into users values(1001,'kim@infosys.in','userC11##','Kim','IT','Mumbai,India','1111111111'),(1002,'lee@wipro.in','userc22##','Lee','HR','Pune,India','2222222222');
   
<li> mysql> insert into accounts values(1001,'1122334C11','Account 1','Mumbai,India','Rupee','500000',1,'50000','50000000','Infosys');

<li> mysql> insert into accounts values(1002,'2233411C22','Account 1','Tokyo,Japan','Yen','450000',1,'50000','50000000','Wipro');
  
<li> mysql> insert into transaction values("7654321C22",1007,"credit","2021-08-03","200000");

<li> mysql> insert into transaction values("7654321C22",1008,"debit","2021-08-25","52000");

<li> mysql> insert into transaction values("4122334C22",1009,"credit","2021-08-03","200000");

<li> mysql> insert into transaction values("4122334C22",1010,"debit","2021-08-23","520000");
