@echo off

echo "Began execution of Command"

set JAVA_HOME="C:\Program Files\Java\jdk1.7.0_45"
set APP_ROOT=C:\apache-tomcat-7.0.27\webapps\relate-integration\ROOT

rem FOR /F "delims=.=" %%G IN (..\build.properties)  DO @echo %%G %%H

set PATH=%JAVA_HOME%\bin;%PATH%
set SHELL_CP=%APP_ROOT%\WEB-INF\conf;%APP_ROOT%\WEB-INF\classes;%APP_ROOT%\WEB-INF\lib\*;
set PROPERTIES_FILE_PATH=%APP_ROOT%\WEB-INF\classes\com\properties\impex.properties

java  -cp %SHELL_CP% com.rim.integration.ImpexExecutor import emailfeed %PROPERTIES_FILE_PATH%

echo "Finished execution of Command"

pause