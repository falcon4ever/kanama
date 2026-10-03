pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

// The build pins `jvmToolchain(25)`. The foojay resolver is Gradle's standard toolchain
// auto-provisioning: with only another JDK installed, JDK 25 is downloaded once into ~/.gradle/jdks.
// (Gradle requires pluginManagement {} to come before plugins {}.)
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "kanama-starter"
