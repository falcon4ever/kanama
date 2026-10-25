pluginManagement {
    // The Kanama version is the one line in addons/kanama/kanama.version, so unzipping a new addon
    // moves the Kanama Gradle plugin and the Kanama libraries together.
    val kanamaVersion = File(settingsDir, "addons/kanama/kanama.version").let {
        if (!it.isFile) {
            throw GradleException("Kanama addon not found: addons/kanama/kanama.version is missing. Unzip the Kanama addon into addons/ again.")
        }
        it.readText().trim()
    }
    if (!File(settingsDir, "addons/kanama/maven/net/multigesture/kanama/kanama-gradle-plugin/$kanamaVersion").isDirectory) {
        throw GradleException("Kanama addon is incomplete: addons/kanama/maven has no Kanama $kanamaVersion. Unzip the Kanama $kanamaVersion addon into addons/ again.")
    }
    repositories {
        maven { url = uri("addons/kanama/maven") }
        gradlePluginPortal()
        mavenCentral()
    }
    resolutionStrategy.eachPlugin {
        if (requested.id.id == "net.multigesture.kanama") useVersion(kanamaVersion)
    }
}

// The build pins `jvmToolchain(25)`. The foojay resolver is Gradle's standard toolchain
// auto-provisioning: with only another JDK installed, JDK 25 is downloaded once into ~/.gradle/jdks.
// (Gradle requires pluginManagement {} to come before plugins {}.)
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "kanama-starter"
