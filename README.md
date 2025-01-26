# E-Store:  _____ Extinct Species _____
# Modify this document to expand any and all sections that are applicable for a better understanding from your users/testers/collaborators (remove this comment and other instructions areas for your FINAL release)

An online E-store system built in Java 11=> and ___ _replace with other platform requirements_ ___
  
## Team

- Liam Cummings
- Susant Raut
- Samuel Maselli
- Jason Sigal


## Prerequisites

- Java 8=>11 (Make sure to have correct JAVA_HOME setup in your environment)
- Maven
-  _add any other tech stack requirements_


## How to run it

1. Clone the repository and go to the root directory.
2. Execute `mvn compile exec:java`
3. Open in your browser `http://localhost:8080/`
4.  _add any other steps required or examples of how to use/run_

## Known bugs and disclaimers
(It may be the case that your implementation is not perfect.)

Document any known bug or nuisance.
If any shortcomings, make clear what these are and where they are located.

## How to test it

The Maven build script provides hooks for run unit tests and generate code coverage
reports in HTML.

To run tests on all tiers together do this:

1. Execute `mvn clean test jacoco:report`
2. Open in your browser the file at `PROJECT_API_HOME/target/site/jacoco/index.html`

To run tests on a single tier do this:

1. Execute `mvn clean test-compile surefire:test@tier jacoco:report@tier` where `tier` is one of `controller`, `model`, `persistence`
2. Open in your browser the file at `PROJECT_API_HOME/target/site/jacoco/{controller, model, persistence}/index.html`

To run tests on all the tiers in isolation do this:

1. Execute `mvn exec:exec@tests-and-coverage`
2. To view the Controller tier tests open in your browser the file at `PROJECT_API_HOME/target/site/jacoco/model/index.html`
3. To view the Model tier tests open in your browser the file at `PROJECT_API_HOME/target/site/jacoco/model/index.html`
4. To view the Persistence tier tests open in your browser the file at `PROJECT_API_HOME/target/site/jacoco/model/index.html`

*(Consider using `mvn clean verify` to attest you have reached the target threshold for coverage)
  
  
## How to generate the Design documentation PDF

1. Access the `PROJECT_DOCS_HOME/` directory
2. Execute `mvn exec:exec@docs`
3. The generated PDF will be in `PROJECT_DOCS_HOME/` directory


## How to setup/run/test program 
1. Tester, first obtain the Acceptance Test plan
2. IP address of target machine running the app
3. Execute ________
4. ...
5. ...

## License

MIT License

See LICENSE for details.


## Helpfull commands and notes
Compile backend
    mvn compile exec:java
Compile frontend
    ng serve --open

Curl commands that work

            //USER
    curl.exe -X GET http://localhost:8080/users/John
        //Id doesnt matter
    curl.exe -X POST -H "Content-Type: application/json" -d '{\"id\":0,\"name\":\"James\",\"password\":\"m4d120n\",\"cart\":[]}' 'http://localhost:8080/users'
        //ID dependent
    curl.exe -X PUT -H "Content-Type: application/json" -d '{\"id\":0,\"name\":\"James\",\"password\":\"m4d120nIsMyNewPassWurd\",\"cart\":[]}' 'http://localhost:8080/users'
        //ID Dependent
    curl.exe -X DELETE http://localhost:8080/users/0

            //ANIMAL
    curl.exe -X GET http://localhost:8080/animals
    curl.exe -X GET http://localhost:8080/animals/1
    curl.exe -X GET http://localhost:8080/animals/?name=m
        //ID doesnt matter
    curl.exe -X POST -H "Content-Type: application/json" -d '{\"id\":0,\"price\":10.00,\"quantity\":10,\"description\":\"similarhumans\",\"species\":\"Monkey\",\"family\":\"GenousMonkius\"}' 'http://localhost:8080/animals'
        //ID dependent
    curl.exe -X PUT -H "Content-Type: application/json" -d '{\"id\":0,\"price\":100.00,\"quantity\":10,\"description\":\"similartohumans\",\"species\":\"Monkey\",\"family\":\"GenousMonkius\"}' 'http://localhost:8080/animals'
    curl.exe -X DELETE http://localhost:8080/animals/0

