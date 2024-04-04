#!/bin/bash
VANILLA_COLORS=./vanilla_colors.txt
COLORLIST=./colors.txt
TEMPLATES=$1
OUT_DIRECTORY=$2
REPLACE="magenta"

for f in $(find $TEMPLATES -type f -name "*.json"); do
  while read COLOR; do
    COLOR=$(echo $COLOR | tr -d "\r") # Remove the carriage returns

    NAME=$(sed "s/${REPLACE}/${COLOR}/g" <(echo $f | rev | cut -d "/" -f1 | rev))
    OUT_PATH="${OUT_DIRECTORY}"$(echo $f | rev | cut -d "/" -f2- | rev)

    echo $OUT_PATH/$NAME
    mkdir -p $OUT_PATH
    sed "s/${REPLACE}/${COLOR}/g" $f > "${OUT_PATH}/${NAME}"

  done < $COLORLIST
done