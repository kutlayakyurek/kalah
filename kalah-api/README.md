# Kalah API Project With Spring Boot And Java

Kalah game restful web services with Spring Boot

## Introduction

This project aims for implementing restful web services for Kalah game engine

## How To Run

Linux & Unix
1. Execute 'mvn clean install' inside module folder
2. Copy built jar file to anywhere in your file system
3. Execute 'nohup java -jar -Dspring.profiles.active=prod kalah-api-1.0-SNAPSHOT.jar > /dev/null 2>&1 &' command
4. Go to "http://localhost:8080/swagger-ui.html"
    Note: Deployment address and port differs according to your deployment settings

## How To Contribute

Please fork this repository and then issue Pull requests for review.

### Status
- ![#f03c15](https://placehold.it/15/f03c15/000000?text=+) `Urgent Things to Do`
1. Unit and integration tests
2. Docker integration
3. Spring security integration
  
- ![#c5f015](https://placehold.it/15/c5f015/000000?text=+) `Test Coverage`

- ![#1589F0](https://placehold.it/15/1589F0/000000?text=+) `Requests`
