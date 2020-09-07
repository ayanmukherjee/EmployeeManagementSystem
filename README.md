# Employee Management System

Employee Management System is a service that exposes REST APIs to store and retrieve employee information.

## How to Run
This service is packaged as a war which has Tomcat 8 embedded; no Tomcat installation is required. You run it using the java -jar command.

* Make sure you are using JDK 1.8, Maven 3.x and Git CLI
* Clone this repository by executing `git clone https://github.com/ayanmukherjee/EmployeeManagementSystem.git`
* Switch to EmployeeManagementSystem directory where you can build the project and run the tests by running `mvn clean package`
* Once built successfully, you can run the service by executing `java -jar target/ems.war` or `mvn spring-boot:run`
* Check ems.log file to make sure no exceptions are thrown

## About the service

The service is a simple employee management system REST service to create an employee, fetch employee details and show the list of employees in the system. It uses an in-memory database (H2) to store the data. The service runs on port 8080 in an embedded tomcat.

#### Accessing Database
Run the server and browse to http://localhost:8080/h2-console. 
Default username is 'sa' with a blank password and database schema name as ems.

#### Accessing Swagger 2 API docs
Run the server and browse to http://localhost:8080/swagger-ui.html

## Feedback

Please email at ayan.mukherjee@yahoo.com for any feedback.

## License
Employee Management System service is released under version 2.0 of the [Apache License](https://www.apache.org/licenses/LICENSE-2.0).
