#!/bin/bash

mvn clean package
java $1 -Dmanofletter.logging.level=INFO -Dmanofletter.loggers=org.slf4j.Logger -Dmanofletter.filter=password -javaagent:manofletter-agent/target/manofletter-agent.jar -jar manofletter-sample-slf4j/target/manofletter-sample-slf4j*.jar
java $1 -Dmanofletter.logging.level=INFO -Dmanofletter.loggers=org.apache.logging.log4j.Logger -Dmanofletter.filter=password  -javaagent:manofletter-agent/target/manofletter-agent.jar -jar manofletter-sample-log4j2/target/manofletter-sample-log4j2*.jar

