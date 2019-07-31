#!/bin/bash

mvn clean package
java $1 $2 -javaagent:manofletter-agent/target/manofletter-agent.jar -jar manofletter-sample-slf4j/target/manofletter-sample-slf4j*.jar

