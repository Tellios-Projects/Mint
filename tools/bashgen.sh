#!/bin/bash

# DOESNT WORK YET

TO_ACQUIRE=$1
TEMPLATES_LIST=$2
MOD_ID=$3

bash acquire.sh $TO_ACQUIRE > "${JAR_FOLDER}" &&
bash get_templates.sh jars/$JAR_FOLDER/ $TEMPLATES_LIST > "${TEMPLATES_FOLDER}" &&
bash colorize.sh $TEMPLATES_FOLDER ../src/main/resources/ &&
bash colorize_lang.sh $TEMPLATES_FOLDER $(find $JAR_FOLDER -name "en_us.json") ../src/main/resources/assets/${MOD_ID}/lang