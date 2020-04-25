# README

### Requirements
* Java 8 (newer versions do not support the jaxb lib anymore)
* Have your neo4j database instance running 
* IASA-getProject and IASA-patternDocumentation components should have been executed before using this component

### Build 
1. Import all dependencies (enable auto import in IDE)
2. Go to class `org.patterncontrol.model.util.ProjectDatabaseCredentials` and add the credentials for your project database
3. Execute maven command: `clean install package` 
4. Go to target folder and copy camunda.war file
5. Download camunda tomcat server here: https://downloads.camunda.cloud/release/camunda-bpm/tomcat/7.10/
6. Paste camunda.war file in camunda tomcat server, path: `camunda-bpm-tomcat-7.10.0-alpha1/server/apache-tomcat-9.0.5/webapps`
7. Change file name `camunda.war` to `iasa.war` (or whatever else you like to name it (no numbers))
8. **As Admin** execute `start-camunda.bat` (windows) ìn camunda-bpm-tomcat-7.10.0-alpha1 folder or `sudo start-camunda.sh` (linux)
9. Camunda should open here: http://localhost:8080/camunda/app/cockpit/default/#/dashboard

### Errors - FAQ
* If build fails with something like "class not found: jaxb exception class not found": check if your are using Java 8. Project Structure>Project SDK: Java 1.8
