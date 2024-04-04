#!/bin/bash

# Bring out a jar from our libraries
bash acquire.sh maven/modrinth/supplementaries/qtARIsSl/supplementaries-qtARIsSl.jar

# Generate templates from a template search file
  # In the template search file:
  # We should use magenta as the template color-
  # as it is the least likely color to show up within another word
  # (IE, alfRed, Blacklist, Whitelist)
bash get_templates.sh jars/supplementaries-qtARIsSl/ supplementaries_templates.txt

# Use sed to generate new colored files from our template files
bash colorize.sh templates/ ../src/main/resources/

# Problems with our pipeline:
# - If our MOD ID changes, the colorize.sh script will need to be manually updated for this
# - IMO this is far less elegant than using a multi-pack datagen, we may need to switch to that someday
# - Can be painfully slow to run
# - Template search files can miss important things if they are written wrong
