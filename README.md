# Choi_Prom_P1
Joseph Choi and Seanghay Prom's Project 1 Repository

# Java Enterprise Foundations Project

## Project Description

This project constructs an API that will support a new internal expense reimbursement system. This system will manage the process of reimbursing employees for expenses incurred while on company time.

### Project Design Specifications and Documents

##### Relational Data Model
![Relational Model](https://github.com/220516-Java-Angular-Enterprise/Choi_Prom_P1/blob/main/p1_er.png)

### Technologies

- Java 8
- Apache Maven
- JDBC
- Java EE Servlets
- JSON Web Tokens
- JUnit
- Mockito
- PostGreSQL (running on AWS RDS)
- Jenkins (running on Docker)
- AWS EC2

##### System Use Case Diagrams
![System Use Case Diagrams](https://raw.githubusercontent.com/220207-java-enterprise/assignments/main/foundations-project/imgs/ERS%20Use%20Case%20Diagram.png)

##### Reimbursment Status State Flow
![Reimbursment Status State Flow](https://raw.githubusercontent.com/220207-java-enterprise/assignments/main/foundations-project/imgs/ERS%20State%20Flow%20Diagram.png)

### Functionalities

- An new employee or new finance manager can request registration with the system
- A registered employee can authenticate with the system by providing valid credentials
- An authenticated employee can submit a new reimbursement request
- An authenticated employee can view and manage their pending reimbursement requests
- An authenticated employee can view their reimbursement request history (sortable and filterable)
- An authenticated finance manager can view a list of all pending reimbursement requests
- An authenticated finance manager can view a history of requests that they have approved/denied
- An authenticated finance manager can approve/deny reimbursement requests
- An admin user can activate/deactivate user accounts
- An admin user can reset a registered user's password

### Non-Functional Features

- Basic validation is enforced to ensure that invalid/improper data is not persisted
- User passwords will be encrypted by the system before persisting them to the data source
- Users are unable to update reimbursement requests once they have been processed (only pending allowed)
- Sensitive endpoints are protected from unauthenticated and unauthorized requesters using JWTs
- Errors and exceptions are handled properly and their details are obfuscated from the user
- The system conforms to level 2 RESTful architecture constraints
- The system keeps detailed logs on info, error, and fatal events that occur
