#!/bin/bash
PNGFOLDER=$1
OUTPATH=$2

for f in $1/*.png; do
NAME=$(echo $f | cut -d "/" -f2 | cut -d "." -f1 | awk -F "_" '{print $2 "_" $3 "_" $1 ".png"}')
cp $f "$OUTPATH/$NAME"
done