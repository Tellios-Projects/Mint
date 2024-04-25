#!/bin/bash

JAR=$1
TEMPLATES=$2

while read NAME; do
  NAME=$(echo $NAME | tr -d "\r") # Remove the carriage returns
  for f in $(find $JAR -type f -iwholename "*${NAME}*.json"); do mkdir -p templates/$(echo $f | cut -d "/" -f2- | rev | cut -d "/" -f2- | rev)/ && cp $f "$_"
    echo $f
  done
done < $TEMPLATES
