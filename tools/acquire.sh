#!/bin/bash
MOD_JAR=$1

cd jars
DIRNAME=$(echo $MOD_JAR | rev | cut -d "/" -f1 | cut -d "." -f2- | rev)
mkdir $DIRNAME
cd $DIRNAME
jar xf ../../$MOD_JAR
cd ../../
