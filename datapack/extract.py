#!/usr/bin/python3

import json, sys

allowedBiomes = {
  "minecraft:frozen_river",
  "minecraft:snowy_plains",
  "minecraft:ice_spikes",
  "minecraft:grove",
  "minecraft:frozen_peaks",
  "minecraft:jagged_peaks",
  "minecraft:snowy_slopes",
  "minecraft:snowy_taiga",
  "minecraft:snowy_beach",
  "minecraft:frozen_ocean",
  "minecraft:deep_frozen_ocean"
}

overworldBiomesPath = sys.argv[1]
worldPresetPath = sys.argv[2]
winterPresetPath = sys.argv[3]

#Load the biome data
biomeData = None
with open(overworldBiomesPath) as inputFile:
  biomeData = json.load(inputFile)

#Remove non-snowy biomes
keptBiomes = []
for biome in biomeData["generator"]["biome_source"]["biomes"]:
  if biome["biome"] in allowedBiomes:
    keptBiomes.append(biome)
biomeData["generator"]["biome_source"]["biomes"] = keptBiomes

#Open the base world preset file
worldPresetData = None
with open(worldPresetPath) as worldPresetFile:
  worldPresetData = json.load(worldPresetFile)

#Replace the default biomes and save the file
worldPresetData["dimensions"]["minecraft:overworld"]["generator"] = biomeData["generator"]
with open(winterPresetPath, 'w') as outputFile:
  json.dump(worldPresetData, outputFile)
