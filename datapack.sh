#!/bin/bash

datapackOutput="target/frozenBiomes.zip"
worldPresetPath="target/datapack/frozenBiomes/data/minecraft/worldgen/world_preset"

#Generate the datapack structure
rm -rf "target/datapack/" "$datapackOutput"
mkdir -p "$worldPresetPath"
cp "datapack/pack.mcmeta" "target/datapack/frozenBiomes"
cp "datapack/winter-base.json" "$worldPresetPath/winter-base.json"

#Zip the datapack
cd "target/datapack" || exit 1
zip -r "../../$datapackOutput" "frozenBiomes"
