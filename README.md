# fabric-mod-s6

## Description

A fork of the [fabric-mod-s6](https://gitlab.com/holycube/fabric-mod-s6) originally conceived by *Olivier "ShoukaSeikyo" Martinez* for [Holycube S6](https://www.holycube.fr/).

I am maintaining this mod for my own minecraft server.

## Changelogs

### v0.0.9+1.21.4 - 22 Janvier 2025 : Hotfix

- Add pale moss carper to debug stick

### v0.0.8+1.21.4 - 22 Janvier 2025 : Updated for **Minecraft 1.21.4**

- Add crafter block to debug stick
- Add Pale wood collection to debug stick
- Add Resin collection
- Change format recipe "MarliWrench"

### v0.0.7+1.21 - 3 September 2024 : Hotfix

- Add pointed dripstone to debug stick
- Also add decorated pot's facing to debug stick

### v0.0.6+1.21 - 4 August 2024 : Hotfix

- Add "FACING"/"AXIS" property of a bunch of missing blocks
  - Wood, stripped wood, and stripped log blocks, as well as Warped and Crimson wood
  - Shulker boxes and barrels
  - Basalt and Polished basalt
  - Single chests, trapped chests and ender chests (double chests are WIP)
  - Lectern, loom, grindstone, stone cutter, campfires and beehive/bee nest
  - Carved pumpkins and Jack-o-lantern
- Add Banners' orientation too

### v0.0.5+1.21 - 4 August 2024 : Hotfix

- Add "IN_WALL" property for fence gates in debug stick

### v0.0.4+1.21 - 31 July 2024 : Hotfix - Missing blocks and block states

- Reverse to Java 21, Kotlin 1.9.24
- See [Issue #1](https://github.com/AarnoldGad/fabric-mod-s6/issues/1)

### v0.0.3+1.21 - 13 June 2024 : Updated for **Minecraft 1.21** (doih)

- Update to Java 22, Kotlin 2.0.0 and Gradle 8.8
- Rename data.holycube6.recipes to data.holycube6.recipe, otherwise custom recipes are not found
- Update to Minecraft 1.21 (doih)

### v0.0.3+1.20.6 - 6 June 2024 : Minor update

- Add creative mode bypass to change light level of `minecraft:light` on right click

### v0.0.2+1.20.6 - 30 May 2024 : Minor update

- Add crafting recipe for `minecraft:light`

### v0.0.1+1.20.6 - 27 May 2024 : Updated for **Minecraft 1.20.6**

- Update to Java Version 21
- Update to gradle 8.7
- Update fabric, loom, etc. versions
- Update **NBTs** for **Item Components**
- Replace debug stick craft hack for normal json recipe with item components
- Use normal json crafting recipe for deepslate coal (but doesn't give back 1 stone anymore)
- Delete loot boxes in merchants inventory
- Update **LiteralTextContent** for **PlainTextContent.Literal** in sendHUD()

### v0.0.1+1.20.1 - 17 July 2023 : Updated for **Minecraft 1.20.1**
- Deactivated player heads in wandering traders' shops
- Renamed DebugStick from "HolyWrench" to "MarliWrench" to fit my server

## Build

Simply run the "*build*" task from *gradlew*, generated jar file should be in **build/libs**

## Licence

According to [original repository](https://gitlab.com/holycube/fabric-mod-s6), Creative Commons Zero v1.0 Universal (CC0)

## Credits

- Olivier "ShoukaSeikyo" Martinez (Author)
- Gaétan "Gad" Jalin (Maintainer)
