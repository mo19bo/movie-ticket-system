# The simulation movie tickets booking system
This  architecture of software is C/S (Client/Server).
Client is developed by using java as programming languages and java-swt as interface development.
Database employs MySQL to build a small databas (Designed by *powerdesigner*).
Server takes PHP + Apache as a development environment. 
## Development environment
JDK version: JDK 1.6.0_14  
Development tools: MyEclipse 7.0  
Database: SQL Server 2005  
Server: Tomcat 6.0  
## Main moduel:
* **Comsumer**   
Comsumer personal information managment (Login, register and log-out)  
Display the information of movie  
Query movie information (including fuzzy search, exact search, searching by type,screening）  
Shopping cart management (Comsumer can cancel and add new order to cart)
Browse consumption history  
Account recharge (simulation recharge system)
* **System administrator**  
Administrator login and log-out  
Manage movie information (Delete, add, update movie information)  
Member information management (e.g. black account)
Announcements management
