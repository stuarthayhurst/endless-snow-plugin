## Endless Snow
  - A Minecraft plugin to allow endless snow pile-up and persistent storms
  - Supports Spigot 1.21.10+
  - Don't use this on worlds you care about
    - I'd recommend creating a fresh world, then backup regularly
  - This won't work on any 8 layer deep snow 'blocks' created while deep snow is disabled

## Installation:
  - Use `mvn package` to compile the plugin to `target/`
    - Alternatively, download a release
  - Copy the plugin to your server

## Usage:
### Deep snow:
  - Use `/deepsnow` to check deep snow status
  - Use `/deepsnow on` to enable deep snow
  - Use `/deepsnow off` to disable deep snow

### Persistent storm:
  - Use `/persistentstorm` to check the persistent storm's status
  - Use `/persistentstorm on` to enable the persistent storm
  - Use `/persistentstorm off` to disable the persistent storm

## Config:
  - `deepSnow`: `[true / false]` - Enable deep snow when the server starts
  - `persistentStorm`: `[true / false]` - Start a persistent storm when the server starts
