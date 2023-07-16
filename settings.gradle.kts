pluginManagement {
    repositories {
        maven(url = "https://maven.fabricmc.net") {
            name = "Fabric"
        }
        gradlePluginPortal()
    }
}

rootProject.name = "holycube6"
include("java")

