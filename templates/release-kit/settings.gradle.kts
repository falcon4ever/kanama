// The build below pins `jvmToolchain(25)`. The foojay resolver is Gradle's standard toolchain
// auto-provisioning: with only another JDK installed, JDK 25 is downloaded once into ~/.gradle/jdks.
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "kanama-starter"
