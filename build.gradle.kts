plugins {
    java
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "me.badbones69"
version = "1.9.1"

repositories {
    mavenCentral()
    maven("https://repo.codemc.org/repository/maven-public/")
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.18.2-R0.1-SNAPSHOT")
    compileOnly("me.clip:placeholderapi:2.10.10")

    implementation("de.tr7zw:item-nbt-api:2.9.2")

    implementation("org.bstats:bstats-bukkit:3.0.0")
}

tasks {
    shadowJar {
        archiveFileName.set("${rootProject.name}[v${version}].jar")
        relocate("de.tr7zw", "me.badbones69.libs.nbtapi")
        relocate("org.bstats", "me.badbones69.libs.bstats")
    }

    processResources {
        filesMatching("plugin.yml") {
            expand(
                "version" to rootProject.version
            )
        }
    }
}