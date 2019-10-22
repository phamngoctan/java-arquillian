# Arquillian with remote-wildlfy mode
## How to run
Start your WildFly 10 instance.  

Run this maven command: mvn clean verify or run the test as a JUnit test in your IDE. You can run the test as many time as you want without stopping the container.  

Stop the container.  

## NOTES
The container resides in a separate JVM and its lifecycle (startup/shutdown) is managed by the user. Arquillian only binds to the container to deploy the test archive and invokes tests via a remote protocol; after the test run the archive is also undeployed.

### for arquillian.xml file
We can delete it completely and arquillian automatically pick the 127.0.0.1:9990 to deploy
Otherwise, it will base on the arquillian.xml to pick the remote host