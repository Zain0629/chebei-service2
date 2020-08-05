@ECHO OFF
Title Tonet Service
SetLocal EnableDelayedExpansion 
ECHO Chebei Tonet Service Start...
SET mainClass=com.chebei.user.ChebeiUserRun
SET appRoot=%cd%
SET appLibPath=%appRoot%\lib
SET appClasspath=%appRoot%\setting

SET CLASSPATH=%appClasspath%
  
FOR %%i IN ("%appLibPath%\*.jar") DO SET CLASSPATH=!CLASSPATH!;%%i

java -cp .;%CLASSPATH% -Xms512M -Xmx1024M %mainClass%
EndLocal  
pause