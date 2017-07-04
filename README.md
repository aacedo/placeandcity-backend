# README #

Jersey-based web services for Engaging Geographies web site.

### What is this repository for? ###

* Services for uploading/retrieving collected data to/from a server MongoDB database.  

### How do I get set up? ###

* Install MongoDB. Tested with 2.6.10.
* Install Java 8 or higher.
* Install maven to run o package the services. 
* In the pom.xml file, two plugins control the way to install the web services: (1) "maven-war-plugin" and (2) "jetty-maven-plugin".
 - If you prefer to use Tomcat, comment (2) and uncomment (1). In the command line, cd to the project folder, and type command "mvn package" to generate the war file, which you later will upload to the server.
 - To run it using jetty, comment (1) and uncomment (2). In the command line, cd to the project folder, and type jetty:run.

### Who do I talk to? ###

* Please address your questions or suggestions to: