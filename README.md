# SpringMicroServices
Generic sample of Spring Boot project merging with Spring Cloud, Rabbit Message Queue and Netflix OSS stack.

### Commands
* Maven build - mvn clean install | mvn clean install -o
* Run JAR file - java -jar target\MSDemo-0.0.1-SNAPSHOT.jar
* Start service - rabbitmq-service.bat start
* Stop service - rabbitmqctl.bat stop
* Enable RabbitMQ Management - rabbitmq-plugins enable rabbitmq_management
* Generate Keystore - keytool -genkey -alias sas -keyalg RSA -keystore D:\SasWorkspace\sts\MSDemo\SpringMicroServices\src\main\resources\msdemoKeystore

### Local URLs
* Eureka Server - http://localhost:8761/
* Swagger UI - http://localhost:8080/swagger-ui.html
* RabbitMQ Manager - http://localhost:15672


### References
* https://www.linkedin.com/learning/spring-spring-cloud/welcome-to-external-configuration?u=2113185
* https://www.linkedin.com/learning/creating-your-first-restful-spring-boot-microservice-with-jpa/welcome?u=2113185
* https://www.linkedin.com/learning/spring-boot-essential-training
* https://www.rabbitmq.com/install-windows-manual.html
* https://www.concretepage.com/spring-4/spring-security-thymeleaf-login-logout