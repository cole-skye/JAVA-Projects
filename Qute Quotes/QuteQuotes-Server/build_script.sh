#!/bin/bash

mvn clean

mvn compile

mvn package

mvn test

java -jar /Users/colefortuin/Desktop/QuteQuotes/out/artifacts/QuteQuotes_jar/QuteQuotes.jar