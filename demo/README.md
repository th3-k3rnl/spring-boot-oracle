# About
Example Spring Boot Application copied from [this tutorial](https://www.javaguides.net/2020/01/spring-boot-hibernate-oracle-crud-example.html).

# Instructions on use

Before starting the Spring Boot service, there are a couple of things that need to be done so that our Oracle database is ready to interface with this service.

1. Create a user - Either the initial one provided in the resources/application.properties file, or one you specify. Make sure the passwords match in that file and when you create the user in SQL Plus.
2. GRANT the user you've created with the privilege to CREATE a TABLE. This user will also need permission to CREATE a SEQUENCE for the Employee `id`.

At this point, you should be ready to compile the Spring Boot Service and have a play.

Compile using `mvn install` and then run by executing `mvn spring-boot:run`.

The first time the service is set up, it will throw an exception as it attempts to run the SQL procedure `drop table employees cascade constraints`. Since the table has not yet been created, it cannot `DROP` it and therefore the JDBC driver returns `Caused by: oracle.jdbc.OracleDatabaseException: ORA-00942: table or view does not exist`. The second exception that is thrown during set up is failure to executed the SQL procedure `drop sequence hibernate_sequence`. Again, the sequence does not yet exist in the Pluggable database. Therefore, the Oracle JDBC returns `Caused by: oracle.jdbc.OracleDatabaseException: ORA-02289: sequence does not exist`.

# Curl Requests

Here is an example POST request. When you provide the REST service with this request, the database in XEPDB1 will be created, and the first entry will be committed to the table `employees`.

```bash
curl  -X POST http://localhost:8080/api/v1/employees \
	-H "Content-Type: application/json" \
	-d '{ "firstName": "Your first name", "lastName": "Your last name", "emailId": "domain@provider.tld" }'
```

You can verify that this has happened by logging

```bash
curl  -X GET http://localhost:8080/api/v1/employees/1
```