# Rabo Assessment
Web Application developed by Mithun for Rabo code assessment

### Tech Stack Used
1.	Java 21
2.	Spring Boot 3
3.	Spring 6
4. Apache Kafka with Zookeeper
5. Maven
* ****

### Microservices Details
There are 3 Springboot apps namely 
1. CustomerService
2. AuthenticationService
3. TimelineService
* ****

### Solution Design
Please refer the file with the name "Solution_Design" in the root directory
* ****

## Steps for local Setup
Please follow the below order of steps for a smooth setup.

### Booting the local Kafka instance
* Apache kafka has been used to implement the event driven design
* To start the Kafka ecosystem locally, Zookeeper needs to be started first and then the kafka server.
* Execute the below comments in the order as they are :
1. bin/zookeeper-server-start.sh config/zookeeper.properties
2. bin/kafka-server-start.sh config/server.properties
### Building the apps
Use **mvn clean install** command from terminal of respective service


### Booting the app
* Navigate to the respective folder of the service and execute the below command :
* mvn spring-boot:run

### Database Details
* H2 database has been used for all the services to store data. 
* To access the DB console, please follow the URL : localhost:{{servicePortNumber}}/h2-console
* ****

## App Testing

### Postman Collection
Manual testing can be done via Postman Collection. Attached the postman collection in the path root directory. Please import them locally for testing

### Unit Testing
Unit test has been written using the Junit5 and Mockito framework

### Automation Test Cases
* The tests are intended to hit the rest endpoints and trigger an integration test. 
* The execution of the test cases are done in local environment and can be later configured for cloud. Hence, all the three services need to be started before executing the test cases.
* To run the test cases, please run the "TestRunner.java" class in Customer and Timeline modules. 
* Test results can be found in this path under respective test directory : target/karate-reports/karate-summary.html

Below are the test case details : 
* ****

### More info about the app
* Dead Letter Topic concept has been used to avoid data loss.
* The messages between the services have been transmitted in String format. String serializers have been used.
* Sample deployment file has been written in CloudFoundry format for the CustomerService app alone.
* Automation test cases are written through karate framework and can be found in the karate package of the /test directory.
* Handled the exceptions globally using controller advice
* Used lombok to avoid the boilerplate getters and setters code.
* Input validation and security aspects have not been implemented as the main focus was on the event driven architecture
* ****

Answers for Part 2 and Part 3 are below 

### Part 2 

* ****

### Part 3

As per the kafka architecture, the partition is where the actual event data are stored. A topic will have several partitions.
The default number of partitions per topic is 3 and can be configured/altered using a Java config file.

In-case there is a sudden surge in events(when the threshold for partitions is reached), the partition size needs to be increased. For Apache kafka,this is taken care by the zookeeper. 
The increase in partitions in leader broker means that the follower brokers(where event data is replicated) will also have to scale up their partition count.
Based on the flavour of Kafka(Apache/Confluent) used, the logic of auto-scaling of partitions  might differ.
This way, the consumers would not feel the latency , even when there are more events.

* ****
  