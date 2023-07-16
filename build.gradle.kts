buildscript {
    repositories {
        mavenCentral()
        maven(url = "https://jitpack.io")
        maven(url = "https://raw.githubusercontent.com/TerraformersMC/Archive/main/releases/")
        maven(url = "https://maven.shedaniel.me/")
    }
}

plugins {
    id("fabric-loom").version("1.2-SNAPSHOT")
    val kotlinVersion: String by System.getProperties()
    kotlin("jvm").version(kotlinVersion)
}

dependencies {
    val minecraft_version: String by project
    minecraft("com.mojang", "minecraft", minecraft_version)
    val yarn_mappings: String by project
    mappings("net.fabricmc", "yarn", yarn_mappings, null, "v2")

    val loader_version: String by project
    modImplementation("net.fabricmc", "fabric-loader", loader_version)
    val fabric_version: String by project
    modImplementation("net.fabricmc.fabric-api", "fabric-api", fabric_version)
    val fabric_kotlin_version: String by project
    modImplementation("net.fabricmc", "fabric-language-kotlin", fabric_kotlin_version)

    compileOnly ("org.projectlombok:lombok:1.18.24")
    annotationProcessor ("org.projectlombok:lombok:1.18.24")
}

tasks {
    val javaVersion = JavaVersion.VERSION_17
    withType<JavaCompile> {
        sourceCompatibility = javaVersion.toString()
        targetCompatibility = javaVersion.toString()
        options.release.set(javaVersion.toString().toInt())
    }

    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = javaVersion.toString()
            freeCompilerArgs = listOf("-Xjvm-default=all")
        }
    }

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(javaVersion.toString()))
        }
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }
}

