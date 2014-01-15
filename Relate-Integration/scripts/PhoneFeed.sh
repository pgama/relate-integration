#!/bin/bash

echo "Executing command:"

echo "Began execution of Command"

JAVA_HOME="C:\Program Files\Java\jdk1.7.0_45"
APP_ROOT=C:\apache-tomcat-7.0.27\webapps\relate-integration\ROOT

PATH=$JAVA_HOME\bin:%PATH%
SHELL_CP=$APP_ROOT\WEB-INF\conf:$APP_ROOT\WEB-INF\classes:$APP_ROOT\WEB-INF\lib\*:
PROPERTIES_FILE_PATH=$APP_ROOT\WEB-INF\classes\com\properties\impex.properties

java  -cp $SHELL_CP com.rim.integration.ImpexExecutor import phonefeed $PROPERTIES_FILE_PATH

echo "Finished execution of Command"