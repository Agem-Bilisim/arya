HOW TO USE TOMCAT PLUGIN


1 - Download and extract  Apache Tomcat 7 from http://tomcat.apache.org/download-70.cgi

2 - Open $TOMCAT_HOME/conf/tomcat-users.xml with your favorite text editor.

    <role rolename="manager-gui"/>
    <role rolename="manager-gui"/>
    <user username="admin" password="" roles="manager-gui,manager-script" />

    type above lines inside "tomcat-users" tag.

3 - Go to $ARYA_HOME/arya-gateway/

4 - type to command line  "mvn tomcat7:deploy"

5 - Open web browser and go to address "http://localhost:8080/arya/rest/ismail"
