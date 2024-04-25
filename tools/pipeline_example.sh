#!/bin/bash

# Bring out a jar from our libraries
bash acquire.sh ../.gradle/loom-cache/remapped_mods/net_fabricmc_yarn_1_20_1_1_20_1_build_9_v2/maven/modrinth/supplementaries/qtARIsSl/supplementaries-qtARIsSl.jar

# Generate templates from a template search file
  # In the template search file:
  # We should use magenta as the template color-
  # as it is the least likely color to show up within another word
  # (IE, alfRed, Blacklist, Whitelist)
bash get_templates.sh jars/supplementaries-qtARIsSl/ supplementaries_templates.txt

# Use sed to generate new colored files from our template files
bash colorize.sh templates/supplementaries-qtARIsSl/ output/

# Then generate lang in a sorta similar manner
bash colorize_lang.sh templates/supplementaries-qtARIsSl/ jars/supplementaries-qtARIsSl/assets/supplementaries/lang/en_us.json ../src/main/resources/assets/supplementaries/lang

# Problems with our pipeline:
# - If our MOD ID changes from mint, the colorize.sh script will need to be manually updated for this
# - IMO this is far less elegant than using a multi-pack datagen, we may need to switch to that someday
# - Can be painfully slow to run (especially unpacking the jar)
# - Template search files can miss important things if they are written wrong
