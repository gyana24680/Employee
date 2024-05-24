When creating an employee API using Spring Boot and Java, I am using these technologies together to build a robust and efficient solution. 
Spring Web will handle HTTP requests and responses, Spring Data JPA will manage data persistence using JPA, Lombok will reduce boilerplate code in your entities ,
DevTools will enhance the development experience with automatic application restarts and other features, and the MySQL Driver will enable communication with the MySQL database. 


1.Create Employee (POST):

Endpoint: http://localhost:9090/api/employees/save

Request Body:

{
    "firstName": "Gyana",
    "lastName": "Nayak",
    "email": "luckynayak20@gmail.com",
    "phoneNumbers": ["9853060626", "8249892623"],
    "dateOfJoin": "2023-04-01",
    "yearlySalary": 500000.00
}


2.Retrieve Employee Details (GET):

Endpoint (By ID): http://localhost:9090/api/employees/{id} 
Example: http://localhost:9090/api/employees/1

Endpoint (All Employees): http://localhost:9090/api/employees
Retrieve details of all employees.

