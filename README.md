# Membership System
This provides a REST API for registering, topping up and paying for food.

## Build
This project is built using maven and java 11 - each must be installed in order to build the project and run. Ensure 
your JAVA_HOME environment variable is set appropriately:  
`export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-11.0.1.jdk/Contents/Home`

MongoDB must be installed.

Build the project by running `mvn clean install`

## Run
Ensure the mongo database is running by running `mongodb`

Start the membership system by running `java -jar target/membership-system-0.0.1-SNAPSHOT.jar`

## API Documentation
**Swagger**
Once the application is running, you can view the API documentation at http://localhost:8080/swagger-ui.html  
Here you can see details of the request format for each endpoint.

**Postman**
The `Membership System.postman_collection.json` file can be imported into Postman for specific examples on making 
requests to each endpoint with basic authentication.
  
All requests must use basic authentication using the unique cardId and the employee's chosen pin as the username and password.

### How kiosk clients should use the endpoints
There is a 1 minute timeout upon which the client's https session will close and the user will have to re-authenticate.
The timeout is configurable via the application.properties file.

#### /register
**Note**: This is the only endpoint which does not require authentication  
This endpoint is to be used to initially register a card and an employee with the membership system. It is assumed 
there will be a registration form on the kiosk where the cardId can be filled in by scanning the reader and the employee 
can fill in the rest of the information by hand.

When this form is submitted, the information will be sent to the /register endpoint.

#### /employee
This endpoint is to be used upon tapping a card at the kiosk, upon which the employee's details including their current 
balance will be retrieved.

If the employee's card is unregistered, the response information will contain a message asking them to register. The 
kiosk client can get and display this message to the user.

#### /topUpBalance
Use this endpoint to send requests to increase an employee's balance.

### /buy
Use this endpoint whenever an employee makes a purchase - has the effect of reducing the employee's balance.

If what the employee is buying costs more than what they have in their balance, the transaction will not happen and 
the response wil contain a message asking the employee to top up.

## Run end to end API tests
The end to end tests are in another project. Clone using:
`git clone https://github.com/rvekaria/membership-acceptance-tests.git`

Ensure that the database and the membership system is running following the above instructions before running the 
end to end tests.

The end to end tests are written using a testing automation framework called gauge. You can run the tests by running:
`mvn clean install`

## Further Configuration
**Timeout**
A 1 minute session timeout has been implemented - this can be changed by adjusting the value of `server.servlet.session.timeout` in 
resources/application.properties

**CORS Security**
This is currently configured so that it is accessible to all traffic. The host addresses of the kiosk clients should 
be passed in as a parameter to the @CrossOrigin() annotations in the MemberController class.
