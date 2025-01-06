## Microservices Example

### Tech Stack

1. [x] Spring Boot 3.2.5
2. [x] Spring Cloud Version 2023.0.3
3. [x] Spring Security With JWT Token
4. [x] Distribution Tracing using Zipkin Server
5. [x] Kafka Integration
6. [x] PostGresQL Server Database

### Modules

1. [x] Config Server
2. [x] Eureka Server
3. [x] Gateway Server
4. [x] Auth Service
5. [x] Challenge Service
6. [x] Notification Service
7. [x] Reward Service
8. [x] Employee Service

### How to RUN

1. [x] Start Eureka Service
2. [x] Start Config Service
3. [x] Start Gateway Service
4. [x] Start All Other Services

# Healthify Microservices Project

## Overview

Healthify is a microservices-based project designed to provide a comprehensive health management system. This project consists of multiple services, including an API Gateway, Authentication Service, Reward Service, Challenge Service, and a React-based client application.

## Services

1. **API Gateway**: Acts as the entry point for all client requests and routes them to the appropriate services.
2. **Authentication Service**: Handles user authentication and authorization.
3. **Reward Service**: Manages user rewards.
4. **Challenge Service**: Manages user challenges.
5. **Client Application**: A React-based frontend application for users to interact with the system.

## Prerequisites

- Java 17
- Maven
- Docker
- Node.js
- Yarn

## Setup Instructions

### Clone the Repository

```sh
git clone https://github.com/yourusername/healthify.git
cd healthify
```

### Build and Run Services

#### API Gateway

1. Navigate to the API Gateway directory:
   ```sh
   cd healthify-gateway
   ```
2. Build the project:
   ```sh
   mvn clean install
   ```
3. Run the project:
   ```sh
   mvn spring-boot:run
   ```

#### Authentication Service

1. Navigate to the Authentication Service directory:
   ```sh
   cd healthify-auth
   ```
2. Build the project:
   ```sh
   mvn clean install
   ```
3. Run the project:
   ```sh
   mvn spring-boot:run
   ```

#### Reward Service

1. Navigate to the Reward Service directory:
   ```sh
   cd healthify-reward
   ```
2. Build the project:
   ```sh
   mvn clean install
   ```
3. Run the project:
   ```sh
   mvn spring-boot:run
   ```

#### Challenge Service

1. Navigate to the Challenge Service directory:
   ```sh
   cd healthify-challenge
   ```
2. Build the project:
   ```sh
   mvn clean install
   ```
3. Run the project:
   ```sh
   mvn spring-boot:run
   ```

### Run Client Application

1. Navigate to the client application directory:
   ```sh
   cd healthify-client
   ```
2. Install dependencies:
   ```sh
   yarn install
   ```
3. Start the application:
   ```sh
   yarn start
   ```

### Docker Setup

1. Ensure Docker is running on your machine.
2. Navigate to the root directory of the project where the `docker-compose.yml` file is located.
3. Run the following command to start all services:
   ```sh
   docker-compose up -d
   ```

### ELK Stack Setup

1. Ensure the ELK stack is configured in your `docker-compose.yml` file.
2. Access Kibana at [http://localhost:5601](http://localhost:5601) to visualize logs.

## Usage

- Access the API Gateway at [http://localhost:8080](http://localhost:8080).
- Access the client application at [http://localhost:3000](http://localhost:3000).

## Testing

### Unit Tests

1. Navigate to the service directory you want to test.
2. Run the following command to execute unit tests:
   ```sh
   mvn test
   ```

### Integration Tests

1. Ensure all services are running.
2. Navigate to the service directory you want to test.
3. Run the following command to execute integration tests:
   ```sh
   mvn verify
   ```

## Troubleshooting

### Common Issues

1. **Service Not Starting**: Check the logs for any error messages and ensure all dependencies are correctly configured.
2. **Database Connection Issues**: Verify that the PostgreSQL server is running and the connection details are correct.
3. **Docker Issues**: Ensure Docker is running and the `docker-compose.yml` file is correctly configured.

### Getting Help

If you encounter any issues, please open an issue on the [GitHub repository](https://github.com/AnilTripathi/healthify/issues).

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contributing

1. Fork the repository.
2. Create a new branch (`git checkout -b feature/your-feature`).
3. Commit your changes (`git commit -am 'Add some feature'`).
4. Push to the branch (`git push origin feature/your-feature`).
5. Create a new Pull Request.
