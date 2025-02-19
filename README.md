Rewards Application

Overview
The Rewards Application is a Spring Boot project designed to track customer transactions and calculate reward points based on the transaction amounts. 
Customers earn reward points based on the following rules:
- 1 point for every dollar spent over $50
- An additional 1 point for every dollar spent over $100

The application retrieves transactions from a database, calculates reward points for the past three months, and provides a REST API for fetching reward details.

* Technologies Used
- Java 17
- Spring Boot 3
- Spring Data JPA
- Hibernate
- PostgreSQL / MySQL (Configurable database)
- REST API
- Lombok (optional)
- Maven

* Setup and Installation

-- Prerequisites
Ensure you have the following installed:
- Java 17 or higher
- Maven 3.6+
- PostgreSQL / MySQL (or any supported database)

* Steps to Run the Application
1. Clone the repository:
   git clone https://github.com/your-repo/rewards-application.git
   cd rewards-application
   

2. Configure the database in application.properties :
   spring.datasource.url=jdbc:mysql://localhost:3306/rewards_db
   spring.datasource.username=root
   spring.datasource.password=password
   spring.jpa.hibernate.ddl-auto=update

3. Build and run the application:
   mvn clean install
   mvn spring-boot:run
  

* API Endpoints

* Retrieve Reward Points
   GET /api/rewards
Description: Returns reward points for all customers based on transactions from the last 3 months.
Response Format:
  {
    "John Doe": {
      "JANUARY": 120,
      "FEBRUARY": 90
    },
    "Jane Smith": {
      "JANUARY": 150
    }
  }


* Exception Handling
The application has a global exception handler to handle unexpected errors and return appropriate HTTP responses.

* Future Enhancements
- Add user authentication and authorization
- Implement a frontend dashboard for viewing rewards
- Store reward points in a separate table for performance optimization

* License

Contact
For any queries or support, please contact: [your-email@example.com]

