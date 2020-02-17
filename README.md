# SpringBoot-CSVReader

Background
----------
Develop a web application with one endpoint, eg GET http://localhost:8080/users

Requirements
------------
1.Read a csv file, and insert into the database.
2.Expose /users endpoint that returns users with valid salary (0 <= salary <= 4000)

Technology 
----------
1. Spring Boot
2. MySQL Database
3. Thymeleaf
4. Opencsv

Setup
-----
1. Clone Repository at git clone https://github.com/ram-nathan-84/springboot-csv.git
2. Create Schema at MySQL Database. Schema name is hr_sys. This can be changed at application.properties
3. Datasource URL, username and password should also be changed if needed. 
4. Go to Repository root (cloned) and mvn spring-boot:run
5. Database tables will be automatically created
6. Launch http://localhost:8080 to lauch Excel Upload Form

Read CSV
--------
1. Select CSV(use file.csv provided) and Submit
2. Upon successful insertion of data, the inserted data will be displayed.

API
---

- GET /users?minSal={from}&maxSal={to}

Returns users with salary within the given range.

Sample URL: http://localhost:8080/users?minSal=0&maxSal=4000
