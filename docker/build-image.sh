#!/bin/bash

# Ask maven to build the executable jar file from the source files
mvn clean install --file ../pom.xml

# Copy the executable jar file in the current directory
cp ../target/smtp-1.0-SNAPSHOT.jar .

# Build the Docker image locally
docker build --tag stmp_sauge_viotti/smtp .
