# Banking Microservices Simulator

Services:
- Eureka Server (8761)
- API Gateway (8080)
- Account Service (8081)
- Transaction Service (8082)
- Notification Service (8083)

Quick start (docker-compose):
$ docker-compose up --build

APIs:
- Create Account: POST /api/accounts
- Get Account: GET /api/accounts/{accountNumber}
- Deposit: POST /api/transactions/deposit
- Withdraw: POST /api/transactions/withdraw
- Transfer: POST /api/transactions/transfer
- Send Notification: POST /api/notifications/send

Design:
- Each microservice has its own MongoDB (accounts_db, transactions_db)
- Eureka for service discovery
- Gateway for routing
- Feign + Resilience4j for inter-service calls with circuit breakers
- Correlation ID added to logs for tracing

More: instructions for building jars, docker images, Postman collection, test notes.
