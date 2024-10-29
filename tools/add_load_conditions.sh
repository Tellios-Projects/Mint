#!/bin/bash

IN_DIRECTORY=$1
OUT_DIRECTORY=$2
RECIPE_APPEND=./recipe_conditions.txt

for f in $(find $IN_DIRECTORY -type f -wholename "*/recipes/*.json"); do
  echo "," >> $f
  while read RECIPE; do
      RECIPE=$(echo $RECIPE | tr -d "\r")
      echo $RECIPE >> $f
    done < $RECIPE_APPEND
  #echo "," >> $TEMP
  done
