plugins {
    kotlin("jvm")
    id("com.google.devtools.ksp")
}

import com.google.devtools.ksp.gradle.KspAATask
import java.security.MessageDigest

group = "net.multigesture.kanama"
version = "0.2.1"

repositories {
    mavenCentral()
}

val configuredScriptDirs =
    providers.gradleProperty("kanamaProjectScriptsDirs")
        .orElse(providers.gradleProperty("kanamaProjectScriptsDir"))
val activeScriptDirs = configuredScriptDirs.orElse("__kanama_example_project__")

fun shortHash(value: String): String =
    MessageDigest.getInstance("SHA-256")
        .digest(value.toByteArray(Charsets.UTF_8))
        .take(8)
        .joinToString("") { "%02x".format(it) }

layout.buildDirectory.set(layout.projectDirectory.dir("build/${shortHash(activeScriptDirs.get())}"))

val kspOutputDir = layout.buildDirectory.dir("generated/ksp/main")
val kspKotlinOutputDir = kspOutputDir.map { it.dir("kotlin") }
val kspJavaOutputDir = kspOutputDir.map { it.dir("java") }
val kspClassOutputDir = kspOutputDir.map { it.dir("classes") }
val kspResourceOutputDir = kspOutputDir.map { it.dir("resources") }

kotlin {
    jvmToolchain(25)
    sourceSets.named("main") {
        val configuredDirs = configuredScriptDirs.orNull

        if (configuredDirs.isNullOrBlank()) {
            kotlin.srcDir(layout.projectDirectory.dir("../example_project"))
        } else {
            configuredDirs
                .split(File.pathSeparator, ",")
                .map { it.trim() }
                .filter { it.isNotEmpty() }
                .forEach { kotlin.srcDir(file(it)) }
        }
        kotlin.srcDir(kspKotlinOutputDir)
    }
}

dependencies {
    implementation(project(":"))
    implementation(project(":annotations"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
    ksp(project(":processor"))
}

tasks.matching { it.name == "kspKotlin" || it.name == "compileKotlin" }.configureEach {
    inputs.property("kanamaProjectScriptsDirs", activeScriptDirs)
}

tasks.withType<KspAATask>().configureEach {
    kspConfig.outputBaseDir.set(layout.buildDirectory.dir("generated/ksp"))
    kspConfig.kotlinOutputDir.set(kspKotlinOutputDir)
    kspConfig.javaOutputDir.set(kspJavaOutputDir)
    kspConfig.classOutputDir.set(kspClassOutputDir)
    kspConfig.resourceOutputDir.set(kspResourceOutputDir)
    kspConfig.cachesDir.set(layout.buildDirectory.dir("kspCaches/main"))
}

tasks.named<Jar>("jar") {
    archiveFileName.set("kanama-scripts.jar")
}
