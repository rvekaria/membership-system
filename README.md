# Membership System

## Build
This project is built using java 11. Ensure your JAVA_HOME environment variable is set appropriately:  
`export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-11.0.1.jdk/Contents/Home`

Build the project by running `mvn clean install`

## Run
Ensure the mongo database is running by running `mongodb`

Start the membership system by running `java -jar target/membership-system-0.0.1-SNAPSHOT.jar`

## Swagger API Documentation
Once the application is running, you can view the API documentation at http://localhost:8080/swagger-ui.html  
Here you can see details of the request format for each endpoint.
  
All requests must be authenticated with the unique cardId and the employee's chosen pin.

### How kiosk clients should use the endpoints
#### /register
**Note**: This is the only endpoint which does not require authentication

This endpoint is to be used to initially register a card and an employee with the membership system.

#### /employee
This endpoint is to be used upon tapping a card at the kiosk, upon which the employee's details including their current 
balance will be retrieved.

#### /topUpBalance
Use this endpoint to send requests to increase an employee's balance.

### /buy
Use this endpoint whenever an employee makes a purchase - has the effect of reducing the employee's balance.

## Run end to end API tests
The end to end tests are in another project. Clone using:
`git clone https://github.com/rvekaria/membership-acceptance-tests.git`

Ensure that the database and the membership system is running following the above instructions before running the 
end to end tests.

The end to end tests are written using a testing automation framework called gauge. You can run the tests by running:
`mvn clean install`