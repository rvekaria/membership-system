# Membership System

## Build
This project is built using java 11. Ensure your JAVA_HOME environment variable is set appropriately:  
`export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-11.0.1.jdk/Contents/Home`

Build the project by running `mvn clean install`

## Run
Ensure the mongo database is running by running `mongodb`

Start the membership system by running `java -jar target/membership-system-0.0.1-SNAPSHOT.jar`