### MySql Code for database setup
<pre> 

<li> mysql> create database smartbank_jpa;

</pre>  
### Table description
<pre> 
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

</pre>
  
 
  