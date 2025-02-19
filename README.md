Rewards Application

Overview
The Rewards Application is a Spring Boot project designed to track customer transactions and calculate reward points based on the transaction amounts. 
Customers earn reward points based on the following rules:
- 1 point for every dollar spent over $50
- An additional 1 point for every dollar spent over $100

The application retrieves transactions from a database, calculates reward points for the past three months, and provides a REST API for fetching reward details.

Technologies Used
- Java 17
- Spring Boot 3
- Spring Data JPA
- Hibernate
- PostgreSQL / MySQL (Configurable database)
- REST API
- Lombok (optional)
- Maven

API Endpoints
Retrieve Reward Points
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

  Exception Handling
The application has a global exception handler to handle unexpected errors and return appropriate HTTP responses.

  Future Enhancements
- Add user authentication and authorization
- Implement a frontend dashboard for viewing rewards
- Store reward points in a separate table for performance optimization

