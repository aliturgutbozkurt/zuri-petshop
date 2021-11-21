# README #

This README would normally document whatever steps are necessary to get your application up and running.

## What is this repository for? ###

* Quick summary
* Version
* [Learn Markdown](https://bitbucket.org/tutorials/markdowndemo)

## How do I get set up? ###

### Applications to install:
In order to setup and run PetShop application we need to install following applications:
* IntelliJ Idea community (https://www.jetbrains.com/idea/download):
* Maven (https://maven.apache.org/download.cgi)
* Git (https://git-scm.com/download/)
* Github Desktop (https://desktop.github.com)
* Java JDK 11 (https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)
* Node.js (https://nodejs.org/en/)
* MySql Server8.0 
  * For Windows (https://dev.mysql.com/downloads/windows/installer/8.0.html)
  * For Mac:
  brew install mysql
* MySql Workbench 8.0 (https://www.mysql.com/products/workbench/)
* Postman (https://www.postman.com/downloads/?utm_source=postman-home)
###Setting up the Petshop Project:
####Clone the project from github repository:

* git clone https://github.com/aliturgutbozkurt/zuri-petshop.git
* In terminal open mysql client and run the following commands:
    * CREATE USER 'user'@'localhost' IDENTIFIED BY 'password'; 
    * GRANT ALL PRIVILEGES ON * . * TO 'user'@'localhost';

####Run Project Main appliction with the settings below:
* create run configuration:
  * Main Class: com.turkninja.petshop.PetshopApplication
  * Use classpath of module: application 
  
####Change the settings with the given user and password:
In petshop-be/application/src/main/resources/application.properties 

* Database connetion setting: (please change the user, password and timezone):
  * spring.datasource.username=user
  * spring.datasource.password=password
  * spring.datasource.url=jdbc:mysql://localhost:3306/petshop?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=CET

####Insert data using mysql Workbench by running these two sql files:
Files can be obtained separately
* auth_role.sql
* users.sql 



####Test connection via postman: 
Method : POST
url : localhost:8081/api/v1/auth/login
body:
{
"email":"admin@petshop.com",
"password":"1234"
}

* Configuration
* Dependencies
* Database configuration
* How to run tests
* Deployment instructions

### Contribution guidelines ###

* Writing tests
* Code review
* Other guidelines

### Who do I talk to? ###

* Repo owner or admin
* Other community or team contact
