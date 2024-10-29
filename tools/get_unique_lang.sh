LANG_FILE=$1
OUT_PATH=$2

NAME=$(echo $LANG_FILE | rev | cut -d "/" -f1 | rev | cut -d "." -f1)
FILE_PATH="${OUT_PATH}${NAME}_uniq.txt"
echo $FILE_PATH # debug

if [ ! -f "$FILE_PATH" ]; then
   mkdir -p "${OUT_PATH}" # create file directory if it doesn't exist
else
  > $FILE_PATH  # delete contents of file if not empty
fi
sed -i '/}/d' "$FILE_PATH"

cat $LANG_FILE | tr -d "{" | tr -d "}" | sed 's/.*: //g' | tr -d "," | tr -d "\"" | tr " " "\n" | sort -u > $FILE_PATH
