@file:Suppress("UnstableApiUsage")

import net.fabricmc.loom.task.RemapJarTask

plugins {
    id("fabric-loom") version "1.8.9"
}

// add a repositories block here for fabric-only dependencies if you need it

dependencies {
    minecraft("com.mojang:minecraft:${rootProject.properties["minecraft_version"]}")
    mappings(loom.layered {
        officialMojangMappings()
        parchment("org.parchmentmc.data:parchment-${rootProject.properties["parchment_version"]}@zip")
    })

    modImplementation("net.fabricmc:fabric-loader:${rootProject.properties["fabric_loader_version"]}")
    // This line can be removed if you don't need fabric api
    modImplementation("net.fabricmc.fabric-api:fabric-api:${rootProject.properties["fabric_api_version"]}")

    implementation(project.project(":common").sourceSets.getByName("main").output)

    // Add fabric-only dependencies here.
}

loom {
    runs {
        named("client") {
            client()
            configName = "Fabric Client"
            ideConfigGenerated(true)
            runDir("run")
        }
        named("server") {
            server()
            configName = "Fabric Server"
            ideConfigGenerated(true)
            runDir("run")
        }
    }

    accessWidenerPath = project(":common").loom.accessWidenerPath
}

tasks {
    withType<JavaCompile> {
        // include common code in compiled jar
        source(project(":common").sourceSets.main.get().allSource)
    }

    // put all artifacts in the right directory
    withType<Jar> {
        destinationDirectory = rootDir.resolve("build").resolve("libs_fabric")
    }
    withType<RemapJarTask> {
        destinationDirectory = rootDir.resolve("build").resolve("libs_fabric")
    }

    // add common javadoc to jar
    javadoc { source(project(":common").sourceSets.main.get().allJava) }

    processResources {
        // add common resources to jar
        from(project(":common").sourceSets.main.get().resources)

        // TODO: explanation
        processUnifiedLoadConditions()
        convertFluidUnits()

        // make all properties defined in gradle.properties usable in the neoforge.mods.toml
        filesMatching("fabric.mod.json") {
            expand(rootProject.properties)
        }
    }

    named("compileTestJava").configure {
        enabled = false
    }

    named("test").configure {
        enabled = false
    }
}

fun AbstractCopyTask.convertFluidUnits() {
    filesMatching("data/**/*.json") {
        filter { line ->
            var result = line.replace(""""(\d*\.?\d*)_droplets"""".toRegex(), "$1")
            val regex = """"(\d*\.?\d*)_millibuckets"""".toRegex()
            regex.find(line)?.let { result = result.replace(regex, (it.groups[1]!!.value.toInt() * 81).toString()) }
            result
        }
    }
}

fun AbstractCopyTask.processUnifiedLoadConditions() {
    filesMatching("data/**/*.json") {
        filter { line ->
            // I am very sorry for anyone who tries to read or understand this
            line.replace("""^(\s*)"load_conditions":""".toRegex(),
                "$1\"fabric:load_conditions\":")
                .replace("""^(\s*\{?\s*)"condition":\s*"mod_loaded"""".toRegex(),
                    "$1\"condition\": \"fabric:all_mods_loaded\"")
                .replace("""^(\s*)"mod":\s*"(.*)"""".toRegex(),
                    "$1\"values\": [\"$2\"]")
                .replace("""^(\s*\{?\s*)"condition":\s*"and"""".toRegex(),
                    "$1\"condition\": \"fabric:and\"")
                .replace("""^(\s*\{?\s*)"condition":\s*"or"""".toRegex(),
                    "$1\"condition\": \"fabric:or\"")
                .replace("""^(\s*\{?\s*)"condition":\s*"is_fabric"""".toRegex(),
                    "$1\"condition\": \"true\"")
                .replace("""^(\s*\{?\s*)"condition":\s*"is_neoforge"""".toRegex(),
                    "$1\"condition\": \"false\"")
                .replace("""^(\s*\{?\s*)"condition":\s*"true"""".toRegex(),
                    "$1\"condition\": \"fabric:true\"")
                .replace("""^(\s*\{?\s*)"condition":\s*"false"""".toRegex(),
                    "$1\"condition\": \"fabric:not\", \"value\": {\"condition\": \"fabric:true\"}")
        }
    }
}
