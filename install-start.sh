#!/bin/bash

mvn clean install

STATUS=$?
if [ $STATUS -eq 0 ]; then
echo "Starting Tomcat"
else
echo "Build Failed"
fi

java -jar api/target/api-0.0.1-SNAPSHOT.jar


