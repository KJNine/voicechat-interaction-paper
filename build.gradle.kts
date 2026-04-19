plugins {
    java
    id("xyz.jpenilla.run-paper") version "2.3.1"
}

group = "dev.igalaxy.voicechatinteraction"
version = "1.3.2"
description = "Detect voice chat with the sculk sensor"
val minecraftVersion = "1.21.11"

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/") {
        name = "papermc-repo"
    }
    maven("https://maven.maxhenkel.de/repository/public")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.11-R0.1-SNAPSHOT")
    compileOnly("de.maxhenkel.voicechat:voicechat-api:2.6.0")
}

tasks {
    runServer {
        // Configure the Minecraft version for our task.
        // This is the only required configuration besides applying the plugin.
        // Your plugin's jar (or shadowJar if present) will be used automatically.
        minecraftVersion("1.21.11")
    }
}

tasks.build {
}

tasks.processResources {
    val props = mapOf(
        "version" to version,
        "apiVersion" to minecraftVersion,
        "description" to project.description
    )
    inputs.properties(props)
    filteringCharset = "UTF-8"
    filesMatching("plugin.yml") {
        expand(props)
    }
}
