#!/bin/bash

# Ask maven to build the executable jar file from the source files
mvn clean install --file ../pom.xml

# Build the Docker image locally
docker build --tag smtp_sauge_viotti/smtp .
