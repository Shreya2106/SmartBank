### MySql Code for database setup

<li> mysql> create database smartbank;
  
<li> mysql> create table bank_login_cred(loginid varchar(30) primary key,password varchar(20));

<li> mysql> create table corporate(corporateId int not null primary key auto_increment,corporateName varchar(30),address varchar(30),contactNumber varchar(10));
  
<li> mysql> ALTER TABLE corporate AUTO_INCREMENT=1001;
  
<li> mysql> alter table corporate add softdeleted boolean default false;
  
<li> mysql> create table users(corporateId int, foreign key(corporateId) references corporate(corporateId),loginId varchar(30),password varchar(20),username varchar(30),department varchar(20), address varchar(30), contactNumber varchar(10));
  
<li> mysql> alter table users add changed boolean default false;
  
<li> mysql> create table accounts(corporateId int,foreign key(corporateId) references corporate(corporateId),accountNumber int primary key auto_increment,accountName varchar(20),branch varchar(20),currency varchar(20),balance long,active boolean,openingBalance long,closingBalance long);
  
<li> mysql> ALTER TABLE accounts AUTO_INCREMENT=1001100100;
  
<li> mysql> create table transaction(accountNumber int,foreign key(accountNumber) references accounts(accountNumber),transactionId int not null primary key auto_increment,transactionType varchar(10),transactionDate date,balance long);
  
<li> mysql> ALTER TABLE transaction AUTO_INCREMENT=1001;
  
### Table description
  
mysql> desc bank_login_cred;
+----------+-------------+------+-----+---------+-------+
| Field    | Type        | Null | Key | Default | Extra |
+----------+-------------+------+-----+---------+-------+
| loginid  | varchar(30) | NO   | PRI | NULL    |       |
| password | varchar(20) | YES  |     | NULL    |       |
| username | varchar(30) | YES  |     | NULL    |       |
+----------+-------------+------+-----+---------+-------+
  
 mysql> desc corporate;
+---------------+-------------+------+-----+---------+----------------+
| Field         | Type        | Null | Key | Default | Extra          |
+---------------+-------------+------+-----+---------+----------------+
| corporateId   | int         | NO   | PRI | NULL    | auto_increment |
| corporateName | varchar(30) | YES  |     | NULL    |                |
| address       | varchar(30) | YES  |     | NULL    |                |
| contactNumber | varchar(10) | YES  |     | NULL    |                |
| softdeleted   | tinyint(1)  | YES  |     | 0       |                |
+---------------+-------------+------+-----+---------+----------------+
  
mysql> desc users;
+---------------+-------------+------+-----+---------+-------+
| Field         | Type        | Null | Key | Default | Extra |
+---------------+-------------+------+-----+---------+-------+
| corporateId   | int         | YES  | MUL | NULL    |       |
| loginId       | varchar(30) | YES  |     | NULL    |       |
| password      | varchar(20) | YES  |     | NULL    |       |
| username      | varchar(30) | YES  |     | NULL    |       |
| department    | varchar(20) | YES  |     | NULL    |       |
| address       | varchar(30) | YES  |     | NULL    |       |
| contactNumber | varchar(10) | YES  |     | NULL    |       |
| changed       | tinyint(1)  | YES  |     | 0       |       |
+---------------+-------------+------+-----+---------+-------+

 mysql> desc accounts;
+----------------+-------------+------+-----+---------+----------------+
| Field          | Type        | Null | Key | Default | Extra          |
+----------------+-------------+------+-----+---------+----------------+
| corporateId    | int         | YES  | MUL | NULL    |                |
| accountNumber  | int         | NO   | PRI | NULL    | auto_increment |
| accountName    | varchar(20) | YES  |     | NULL    |                |
| branch         | varchar(20) | YES  |     | NULL    |                |
| currency       | varchar(20) | YES  |     | NULL    |                |
| balance        | mediumtext  | YES  |     | NULL    |                |
| active         | tinyint(1)  | YES  |     | NULL    |                |
| openingBalance | mediumtext  | YES  |     | NULL    |                |
| closingBalance | mediumtext  | YES  |     | NULL    |                |
+----------------+-------------+------+-----+---------+----------------+

 mysql> desc transaction;
+-----------------+-------------+------+-----+---------+----------------+
| Field           | Type        | Null | Key | Default | Extra          |
+-----------------+-------------+------+-----+---------+----------------+
| accountNumber   | int         | YES  | MUL | NULL    |                |
| transactionId   | int         | NO   | PRI | NULL    | auto_increment |
| transactionType | varchar(10) | YES  |     | NULL    |                |
| transactionDate | date        | YES  |     | NULL    |                |
| balance         | mediumtext  | YES  |     | NULL    |                |
+-----------------+-------------+------+-----+---------+----------------+

  
 
  
