#!/bin/bash
mvn install:install-file -Dfile=lib/pig-withouthadoop.jar -DgroupId=org.apache.pig -DartifactId=pig-withouthadoop -Dversion=0.12.0 -Dpackaging=jar -DgeneratePom=true
