In Eclipse, go to the File menu and choose:

  File -> Import... -> Existing Projects into Workspace

  Browse to the directory containing this file,
  select "PengurusCRM".
  
  Be sure to uncheck "Copy projects into workspace" if it is checked.
  
  Click Finish.
  
You can now browse the project in Eclipse.

If you have generated your project with the option '-maven', you have a 'pom.xml'
file ready to use. Assuming you have 'maven2' installed in your system, 'mvn' is 
in your path, and you have access to maven repositories, you should be able to run:

mvn clean         # delete temporary stuff
mvn test          # run all the tests (gwt and junit)
mvn gwt:run       # run development mode
mvn gwt:compile   # compile to javascript
mvn package       # generate a .war package ready to deploy

For more information about other available goals, read maven and gwt-maven-plugin 
documentation (http://maven.apache.org, http://mojo.codehaus.org/gwt-maven-plugin)  
