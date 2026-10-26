plugins {
    kotlin("jvm") version "2.4.21"
    id("com.google.devtools.ksp") version "2.3.12"
    // Its version comes from addons/kanama/kanama.version (settings.gradle.kts).
    id("net.multigesture.kanama")
}

val kotlinSourcesDir = providers.gradleProperty("kanamaProjectScriptsDir")
    .orElse("kotlin-src")

kotlin {
    jvmToolchain(25)
    sourceSets.named("main") {
        kotlin.srcDir(kotlinSourcesDir)
        kotlin.srcDir(layout.buildDirectory.dir("generated/ksp/main/kotlin"))
    }
}

apply(from = "addons/kanama/kanama-project.gradle.kts")
