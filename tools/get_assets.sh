#!/bin/bash

MOD_JAR=../.gradle/loom-cache/remapped_mods/net_fabricmc_yarn_1_20_1_1_20_1_build_9_v2/$1
TEMPLATES=$2

#mkdir temp
#cd temp
#jar xf ../$MOD_JAR
#cd ../

while read NAME; do

NAME=$(echo $NAME | tr -d "\r") # Remove the carriage returns
for f in $(find temp -type f -iwholename "*${NAME}*.json"); do mkdir -p templates/$(echo $f | cut -d "/" -f2- | rev | cut -d "/" -f2- | rev)/ && cp $f "$_"; done
done < $TEMPLATES

# rm -rf temp
