# minibank-springboot-microservices
Application created to demonstrate :

1. How to build microservices using spring-boot. 
2. How to register microservices in Service registry using Netflix Eureka.
3. How to build docker image for each microservice. 
4. Simple use case to demonstrate circuit breaker using Netflix Hystrix.


# Architecture
<a><img src="docs/images/Architecture.png" alt="Architecture"></a>

# Pre Requisite
1. Jdk 1.8 or 1.11
2. Maven
3. Mongo DB

# Running Microservices Locally

1. Build each Microservice using mvn clean install .
2. Start "servicediscovery" microservice using mvn spring-boot:run
3. Start "minibank-ui" microservice using mvn spring-boot:run
4. Start "minibank-service" microservice using mvn spring-boot:run

Open http://localhost:9090 to see the service registry(Eureka Server UI)
<a><img src="docs/images/Eureka.png" alt="Eureka UI"></a>


# Running in Docker Container
	1. Docker Commands to build image
	 
	• Open Terminal in servicediscovery :  docker build --tag servicediscovery  .
	• Open Terminal in minibank-ui :  docker build --tag minibank-ui .
	• Open Terminal in minibank-service :  docker build --tag minibank-service .

	2. Docker Commands to run container

	Run Db container:  
	
	• docker pull mongo
	• docker  run -it --name mongo-minibank -d mongo
		
	Create and Start Containers:

	• Service Discovery : docker run -p 9090:9090 -it --name servicediscovery -d servicediscovery
	
	Running the container + Linking it to dependent containers
	
	• MiniBank-UI          : docker run -p 8080:8080 -it --name minibank-ui --link servicediscovery -d minibank-ui
	• MiniBank-Service : docker run -p 8081:8081 -it --name minibankservice --link servicediscovery --link mongo-minibank -d minibank-service


Open http://localhost:8080

# Login Page

<a><img src="docs/images/Login.png" alt="Login UI"></a>

# Home Page

<a><img src="docs/images/Home.png" alt="Home UI"></a>


# Feedback
Email : sach.bennur@gmail.com
