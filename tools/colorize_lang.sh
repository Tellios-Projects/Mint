#!/bin/bash
TEMPLATES=$1
LANG_FILE=$2
OUT_PATH=$3
COLORLIST=./colors.txt
REPLACE="magenta"

NAME=$(echo $LANG_FILE | rev | cut -d "/" -f1 | rev)
FILE_PATH="${OUT_PATH}/${NAME}"
echo $FILE_PATH

if [ ! -f "$FILE_PATH" ]; then
   mkdir -p "${OUT_PATH}"
   echo "{" > "$FILE_PATH" # put one of these doohickey's at the start of the file
fi
sed -i '/}/d' "$FILE_PATH"  

for f in $(find $TEMPLATES -type f -iwholename "*blockstates/*.json"); do
   TRANSLATION=$(echo $f | rev | cut -d "/" -f1 | cut -d "." -f2- | rev) # | tr "_" " ")

   while read COLOR; do
   COLOR=$(echo $COLOR | tr -d "\r") # Remove the carriage returns
   VAR=$(grep -h $(echo $TRANSLATION) $LANG_FILE | sed "s/${REPLACE}/${COLOR}/g" | sed "s/${REPLACE^}/${COLOR^}/g")
   grep -qF -- "$VAR" "$FILE_PATH" || echo "$VAR" >> "$FILE_PATH" # append only if line does not exist
   done < $COLORLIST
done

grep -qF -- "}" "$FILE_PATH" || echo "}" >> "$FILE_PATH" # and put one of these doohickey's at the end
