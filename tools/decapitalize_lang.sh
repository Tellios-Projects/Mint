LANG_FILE=$1
OUT_PATH=$2

NAME=$(echo $LANG_FILE | rev | cut -d "/" -f1 | rev)
FILE_PATH="${OUT_PATH}${NAME}"
echo $FILE_PATH # debug

if [ ! -f "$FILE_PATH" ]; then
   mkdir -p "${OUT_PATH}" # create file directory if it doesn't exist
else
  > $FILE_PATH  # delete contents of file if not empty
fi
sed -i '/}/d' "$FILE_PATH"

while read LINE; do
  LINE=$(echo $LINE | tr -d "\r") # Remove the carriage returns
  REPLACE=$(awk '{$1=""; $2=""; print tolower($0)}' <(echo $LINE))
  LINE=$(cat <(cut <(echo "$LINE") -d " " -f -2 | tr -d '\n') <(echo "$REPLACE" | sed 's/^ //g') | sed 's/^/  /g')
  echo "$LINE" >> "$FILE_PATH"
done < $LANG_FILE

grep -qF -- "}" "$FILE_PATH" || echo "}" >> "$FILE_PATH" # and put one of these doohickey's at the end
