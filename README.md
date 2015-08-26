## Arya is a cross-platform application development and migration platform

### Building From Source

Run the following command to pull the latest application `git pull origin master` and use Maven to build `mvn clean install -DskipTests`

### Running the Application

1. Download and extract  Apache Tomcat 7 from http://tomcat.apache.org/download-70.cgi
2. Open $TOMCAT_HOME/conf/tomcat-users.xml with your favorite text editor.
3. Go to $ARYA_HOME/arya-gateway/
4. Run the following command to deploy the application `mvn tomcat7:deploy`. If you already deployed before, then type `mvn tomcat7:redeploy`
5. Open web browser and go to address http://localhost:8080/arya/rest/YOUR_APP_NAME

### Testing

TODO...

### Static Analyzers

We also use checkstyle and findbugs plugins to do static analyzing on the changes. Run the following commands to analyze your code to check if it is compatible.

`mvn clean compile -P findbugs`

`mvn clean validate -P checkstyle`


### Arya Modules:

- **arya-core-api** contains core functionalities and interfaces for all modules.
- **arya-adaptor-java** provides adaptor implementation for web projects written in Java (it can use either Rest calls or invoke methods from provided jar files).
- **arya-gateway** works as an entry point for all requests from interpreters.
- **arya-metadata-xul** contains JAXB element classes of `*.arya` files which generated automatically with `xjc` command.
- **arya-metadata-persistence** provides database implementation of metadata engine.
- **arya-metadata-persistence-xml** provides filesystem implementation of metadata engine.
- **arya-metadata-engine** handles metadata operations.
- **arya-metadata-generator** automatically generates metadata `*.arya` files from AgemUtils projects (such as Asya, RCOP etc.) using `*.jsp` files and their corresponding `*Form.java` classes.
- **arya-interpreter-android**
- **arya-interpreter-angularjs**
- **arya-interpreter-zkoss** is the implementation of Zkoss web interpreter.
