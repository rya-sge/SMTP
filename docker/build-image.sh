#!/bin/bash

# Ask maven to build the executable jar file from the source files
mvn clean install --file ../src/pom.xml

# Copy the executable jar file in the current directory
cp ../src/main/target/smtp-1.0-SNAPSHOT.jar .

# Build the Docker image locally
docker build --tag stmpSaugeViotti/smtp .
