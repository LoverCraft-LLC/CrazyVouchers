plugins {
    alias(libs.plugins.paperweight)
    alias(libs.plugins.shadowJar)
    alias(libs.plugins.runPaper)

    `paper-plugin`
}

base {
    archivesName.set(rootProject.name)
}

dependencies {
    paperweight.paperDevBundle(libs.versions.paper)

    api(project(":core"))

    // org.yaml is already bundled with Paper
    implementation(libs.vital.paper) {
        exclude("org.yaml")
    }

    implementation(libs.nbtapi)

    compileOnly(libs.placeholderapi)

    compileOnly(libs.itemsadder)

    compileOnly(libs.oraxen)
}

val component: SoftwareComponent = components["java"]

paperweight {
    reobfArtifactConfiguration = io.papermc.paperweight.userdev.ReobfArtifactConfiguration.REOBF_PRODUCTION
}

tasks {
    runServer {
        jvmArgs("-Dnet.kyori.ansi.colorLevel=truecolor")

        defaultCharacterEncoding = Charsets.UTF_8.name()

        minecraftVersion(libs.versions.minecraft.get())
    }

    assemble {
        dependsOn(reobfJar)

        doLast {
            copy {
                from(reobfJar.get())
                into(rootProject.projectDir.resolve("jars"))
            }
        }
    }

    shadowJar {
        archiveBaseName.set(rootProject.name)
        archiveClassifier.set("")

        listOf(
            "de.tr7zw.changeme.nbtapi",
            "com.ryderbelserion",
            "ch.jalu"
        ).forEach {
            relocate(it, "libs.$it")
        }
    }

    processResources {
        inputs.properties("name" to rootProject.name)
        inputs.properties("group" to project.group)
        inputs.properties("description" to project.properties["description"])
        inputs.properties("apiVersion" to "1.20")
        inputs.properties("authors" to project.properties["authors"])
        inputs.properties("website" to project.properties["website"])

        filesMatching("plugin.yml") {
            expand(inputs.properties)
        }
    }
}