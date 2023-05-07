# Microservices-project

This repository contains the code for a fraud check microservices project developed using modern microservices architecture using Spring Cloud and Docker. The project includes the following microservices:

1. Customer
2. Fraud
3. Notification
4. Eureka Server (for Service Registry & Discovery)
5. API Gateway (for routing requests to corresponding microservices)

The Customer microservice performs a POST request to register a new user. It then communicates with the Fraud microservice to check if the customer is fraudulent. If the customer is legitimate, an asynchronous notification is sent using the message broker RabbitMQ. The Fraud and Notification microservices have mocked logic in place to demonstrate the architecture.

# Tools/Techologies/Languages
The project was built using the following tools, technologies, and languages:

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- Spring Cloud
- RabbitMQ
- Docker
- Maven
- Postman

# Getting Started
To get started with this project, follow these steps:

1. Clone the repository to your local machine using the following command:
    ```shell 
    git clone https://github.com/TejaCherukuri/microservices-project.git
    ```
2. Navigate to the project directory and run the following command to start all the microservices using Docker:
    ```shell 
    docker compose up -d
    ```
    This command will start all the microservices, and they will be available at the following URLs:

    - Customer: http://localhost:8080
    - Fraud: http://localhost:8081
    - Notification: http://localhost:8082
    - API Gateway: http://localhost:8083
    - Eureka Server: http://localhost:8761
    - Zipkin: http://localhost:9411
    - RabbitMQ Management Console: http://localhost:15672
    - Pgadmin: http://localhost:5050
3. To test the application, open Postman and send a POST request with the following JSON body to http://localhost:8083/api/v1/customers
    ```json
    {
     "firstName":"YourFirstName",
     "lastName":"YourLastName",
     "email":"youremail@gmail.com"
    }
4. Check the logs using the following command
    ```shell 
    docker logs <microservice_name>
    ```
    Alternatively, you can spin up Zipkin at the above mentioned port and check the request trace
