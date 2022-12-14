## Smart Bank
Smart bank has a host of corporates as its customers. Each customer appoints certain users to access the Front office portal on its behalf. Bank users setup these customers and provide them appropriate entitlements on the Administration Portal.
<br> Smart Bank wishes to have 2 portals. 
<li> 1) Back-office Administration Portal for its Bank employees 
<li> 2) Front office portal for its Corporate customers.
   

  ###        1. Administration module - Back Office	
    
The Administrative module is used by Smart bank to manage its corporate customers, users and accounts.
<br> Smart bank has Alan on its administrative staff. 
<br> Alan takes care of all the operations activity like creating corporates, users, setting up accounts and providing entitlements to users.

Operation activity
<li> Login – Authentication
<li> Login for a bank user. 
<li> Menu creation - Entitlement evaluation

 Smart Bank's online system needs to provide a menu structure that allows users to access the following functionality :

    
 ###  (i) Corporate setup 
Allows on-boarding Corporates into Smart Bank system.
  <li> Add Corporate
<br> Fields: Corporate id (Autogenerated),Corporate name, addressee(s), phone number(s)
   <li> Modify Corporate
<br> Fields: Addressee(s), phone number(s)
  <li> Delete Corporate
<br> Allows soft delete of corporates if there are no associated users for the corporate.
  <li> View Corporate list
<br> Allows viewing list of corporates. Allows modify and delete of corporates in the system.

###   (ii) User setup   
  Allows on-boarding Users to registered active corporates into Smart Bank system.
  <li> Add User
<br> Fields: Corporate id, Login Id, User name, department, addressee(s), phone number(s)
  <li> Modify User
<br> Fields: Department, addresse(s), phone number(s)
  <li> Delete User 
<br> Allows soft delete of users associated to the corporate. 
  <li> View User list
<br> Allows viewing list of users for a selected corporate. Allows modify and delete of users for the selected corporate.

###  (iii)Account setup 
Allows on-boarding Accounts for registered active corporates into Smart Bank system.
  <li> Add Account
<br> Fields: Account Number, Account Name, Branch(Drop down), Currency(Drop down), Available balance
  <li> Close Account
<br> Marks an account as inactive, thus ensuring no transactions can be performed on the account. Only accounts not associated with a transaction active in the work-flow can be deleted. 
 <li> View Account list
<br> Allows viewing list of accounts for a selected corporate. Allows close of accounts for a corporate.

###     2. Front Office Functionality	
Smart Bank provides its corporate customers with an Internet Portal to enable online transactions. Corporate users login into the internet Portal to create, modify, delete, view, authorize and reject Bill payment requests on behalf of the corporate.

Operation activity
<li> Login – Authentication
<li> The first time a corporate user logs in the system provides a standard  password to login. On first time login  the user is expected change his / her password. Subsequent logins will be validated against the new password chosen by the user.
<li> Menu creation - Entitlement evaluation
  
<br> Based on the entitlements as configured by bank administrator Alan  the customer user will be allowed to perform following operations .
  <li> Account Summary
<br> List of accounts. Information shown for each account should include: Account number, Account name, Currency, Branch, Available balance .
  <li> Account Detail
<br> On selecting an account, user should have an option of viewing his account details. Information shown for each account should include: Account number, Account name, Currency, Branch, Available balance, Opening Balance, Closing balance and the Transaction Summary

 Integration
              
    Finally both the Back-office  administration module and Front office module should be integrated .
