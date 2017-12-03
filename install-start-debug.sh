#!/bin/bash

mvn clean install

STATUS=$?
if [ $STATUS -eq 0 ]; then
echo "Build Successful"
else
echo "Build Failed"
fi

java -jar -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005 api/target/api-0.0.1-SNAPSHOT.jar
