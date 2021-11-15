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
    * `CREATE USER 'user'@'localhost' IDENTIFIED BY 'password';`
    * `GRANT ALL PRIVILEGES ON * . * TO 'user'@'localhost';`

####Insert data using mysql Workbench by running these two sql files:
Files can be obtained separately
* auth_role.sql
* users.sql

####Change the settings with the given user and password:
Below is a code snippet which shows sample connection parameters for MySQL server. Those parameters are
included in the `application-local.properties` file under the `application`
module's
[resources](https://github.com/aliturgutbozkurt/zuri-petshop/blob/master/petshop-be/application/src/main/resources/)
folder and should be set according to the local MySQL server setup.

````properties
spring.datasource.url=connectionStringForMySQL
spring.datasource.username=username
spring.datasource.password=password
````

_Changes to `application-local.properties` file should be tracked locally and should not be
checked into source control in order to keep the example settings and avoid pushing sensitive data
by accident. It can be achieved by adding `application-local.properties` file into `.git/info/exclude` folder.
See your IDE manual to learn how to exclude files in git._

####Run Project Main appliction with the settings below:
* Create run configuration:
  * Main Class: com.turkninja.petshop.PetshopApplication
  * Use classpath of module: application

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
