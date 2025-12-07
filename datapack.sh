#!/bin/bash

datapackOutput="target/frozenBiomes.zip"
worldPresetPath="target/datapack/frozenBiomes/data/minecraft/worldgen/world_preset"

#Generate the datapack structure
rm -rf "target/datapack/" "$datapackOutput"
mkdir -p "$worldPresetPath"
cp "datapack/pack.mcmeta" "target/datapack/frozenBiomes"

#Generate the winter preset
python3 "datapack/extract.py" "datapack/data/overworld.json" "datapack/data/single_biome_surface.json" "$worldPresetPath/winter.json"

#Zip the datapack
cd "target/datapack" || exit 1
zip -r "../../$datapackOutput" "frozenBiomes"
