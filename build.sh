#! /bin/bash

javac src/com/company/*.java src/com/company/policies/*.java -d .

java com/company/Main $1 $2
