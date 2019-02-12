Hello and welcome to the test server application created by Boyko Eugene for LoopMe.

To run this server example your work environment have to contains the next installed applications:
- Java 8 or higer
- Maven 2 or higer 
and also have to be setted the following environments variables:
- JAVA_HOME
- M2_HOME
to get ability for running run script. 
Without these variables you can build this project as usual java maven project and run server by:
- double mouse click by serverstarter/target/serverstarter-1.0-SNAPSHOT.jar on Windows
- java -jar serverstarter/target/serverstarter-1.0-SNAPSHOT.jar on Linux

Instruction to run on Windows:
 - Execute runServerWindows.bat.

When you see something like: 
			' Started BoykoAppServerStarter in 2.903 seconds (JVM running for 3.202)' 

then you can check that server is running on localhost:8080/

Instruction to run on Linux:
At the root directory of progect(this readme file located in the root directory) performe the next commands:
- mvn clean install
- java -jar serverstarter/target/serverstarter-1.0-SNAPSHOT.jar (maybe you need to use the 'sudo' prefix)

When you see something like: 
			' Started BoykoAppServerStarter in 2.903 seconds (JVM running for 3.202)'

then you can check that server is running on localhost:8080/

Also after server started you get the ability to open embedded h2 databese:
follow to the 
http://localhost:8080/h2-console/ 
and set JDBC URL to jdbc:h2:mem:testdb
then press connect. Now you can see the database structure.