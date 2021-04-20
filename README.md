# Events-Demo

To run the application, use following commands:

mvn clean package
java -jar target/eventsDemo-0.0.1-SNAPSHOT.jar

It will ask for an user input, for the path to the logfile to process. Provide an absolute path to file in your local file system.

Please note that "mvn" is not detecting the test at the moment due to probably some dependency issues.
So while running it through Eclipse, please select JUnit 4.
Tests are preset under com.example.eventsDemo.manager.EventsManagerTest class.
