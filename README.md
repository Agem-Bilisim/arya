# Arya is a cross-platform application development and migration platform

## Overview

## Prerequisites

### JDK7

- Download and install [JDK7](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html)

### Git

- Documentation about installing and configuring git can be found [here](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git) and [here](https://git-scm.com/book/en/v2/Getting-Started-First-Time-Git-Setup)
- Git [home](http://git-scm.com/) (download, docs)

### Maven 3

- Get [Maven 3](http://maven.apache.org/install.html) (Specifically, at least **version 3.1.1** is needed to use static code analyzers and maven-android plugin!). Maven version can be checked with `mvn -v`
- Maven [home](https://maven.apache.org/) (download, docs)

## Building From Source

Run the following command to pull the latest application `git pull origin master` and use Maven to build `mvn clean install -DskipTests`

## Running the Application

1. Download and extract  Apache Tomcat 7 from http://tomcat.apache.org/download-70.cgi
2. Open $TOMCAT_HOME/conf/tomcat-users.xml with your favorite text editor.
3. Go to $ARYA_HOME/arya-gateway/
4. Run the following command to deploy the application `mvn tomcat7:deploy`. If you already deployed before, then type `mvn tomcat7:redeploy`
5. Open web browser and go to address http://localhost:8080/arya/rest/YOUR_APP_NAME

## Generating Metadata From AgemUtils-based Project

1. Place AgemUtils-based project as a jar file under `$ARYA_HOME/arya-metadata-generator/reference-project` folder.
2. Modify `config.properties` file as you wish. At least, output files directory and reference project name must be defined.
3. Run `tr.com.agem.arya.metadata.generator.Main.java`!

## Testing

TODO...

## Static Analyzers

We also use checkstyle and findbugs plugins to do static analyzing on the changes. Run the following commands to analyze your code to check if it is compatible.

`mvn clean compile -P findbugs`

`mvn clean validate -P checkstyle`


## Arya Modules

- **arya-core-api** contains core functionalities and interfaces for all modules.
- **arya-adaptor-java** provides adaptor implementation for web projects written in Java (it can use either Rest calls or invoke methods from provided jar files).
- **arya-gateway** works as an entry point for all requests from interpreters.
- **arya-metadata-xul** contains JAXB element classes of `*.arya` files which generated automatically with `xjc` command.
- **arya-metadata-persistence** provides database implementation of metadata engine.
- **arya-metadata-persistence-xml** provides filesystem implementation of metadata engine.
- **arya-metadata-engine** handles metadata operations.
- **arya-metadata-generator** automatically generates metadata `*.arya` files from AgemUtils projects (such as Asya, RCOP etc.) using `*.jsp` files and their corresponding `*Form.java` classes.
- **[arya-interpreter-android](arya-interpreter-android/README.md)** is the Android implementation of interpreter.
- **[arya-interpreter-angularjs](arya-interpreter-angularjs/README.md)** is the AngularJS implementation of interpreter.
- **arya-interpreter-zkoss** is the Zkoss implementation of interpreter.

## Metadata

### View

### Data

### Script Functions

In addition to pure Javascript functions, some predefined functions can also be used to communicate with the Arya gateway, manipulate view components or set component values etc. In order to use these functions inside metadata `<script>` tag, all interpreters must implement these functions.

- getElementByClass(String className):
- getElementByName(String name):
- getElementById(String id):
- serializeForm():
- populate(String data):
- refresh(String view):
- post(String action, String requestType, Object paramsJson):