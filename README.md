# minibank-springboot-microservices
Application created to demonstrate :

1. How to build microservices using spring-boot. 
2. How to register microservices in Service registry using Netflix Eureka.
3. How to build docker image for each microservice. 
4. Simple use case to demonstrate circuit breaker using Netflix Hystrix.


# Architecture
<a><img src="docs/images/Architecture.png" alt="spring boot"></a>

# Running Microservices Locally

1. Build each Microservice using mvn clean install .
2. Start "servicediscovery" microservice using mvn spring-boot:run
3. Start "minibank-ui" microservice using mvn spring-boot:run
4. Start "minibank-service" microservice using mvn spring-boot:run

Open http://localhost:9090 to see the service registry(Eureka Server UI)
