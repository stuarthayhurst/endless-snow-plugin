#!/bin/bash

datapackOutput="target/frozenBiomes.zip"
worldPresetPath="target/datapack/frozenBiomes/data/minecraft/worldgen/world_preset"

#Generate the datapack structure
rm -rf "target/datapack/" "$datapackOutput"
mkdir -p "$worldPresetPath"
cp "datapack/pack.mcmeta" "target/datapack/frozenBiomes"
cp "datapack/winter-base.json" "$worldPresetPath/winter-base.json"

#Download 1.21.10 biome data if requested
if [[ "$1" == "full" ]]; then
  wget -O "target/datapack/overworld.json" "https://github.com/misode/mcmeta/raw/1312815d39c46c3a4d695a6809a0ca8cc4d2c143/data/minecraft/dimension/overworld.json"
  wget -O "target/datapack/single_biome_surface.json" "https://github.com/misode/mcmeta/raw/8cbb984f856c653813d1b2e4251d49d381bf0bce/data/minecraft/worldgen/world_preset/single_biome_surface.json"

  if [[ ! -f "target/datapack/overworld.json" ]] || [[ ! -f "target/datapack/single_biome_surface.json" ]]; then
    echo "Failed to download biome data" 1>&2
    exit 1
  fi

  #Generate the winter-full preset
  python3 "datapack/extract.py" "target/datapack/overworld.json" "target/datapack/single_biome_surface.json" "$worldPresetPath/winter-full.json"
fi

#Zip the datapack
cd "target/datapack" || exit 1
zip -r "../../$datapackOutput" "frozenBiomes"
