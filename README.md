# fabric-mod-s6

## Description

A fork of the [fabric-mod-s6](https://gitlab.com/holycube/fabric-mod-s6) originally conceived by *Olivier "ShoukaSeikyo" Martinez* for [Holycube S6](https://www.holycube.fr/).

I am maintaining this mod for my own minecraft server.

## Changelogs

### v0.0.3+1.20.6 - 30 May 2024 : Minor update

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
