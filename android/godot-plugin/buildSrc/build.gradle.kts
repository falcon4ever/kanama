plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

// Tests: `./gradlew -p android/godot-plugin/buildSrc test` (scripts/local_ci.sh runs it; Gradle
// compiles buildSrc for every build but does not test it).
dependencies {
    testImplementation(kotlin("test-junit5"))
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test { useJUnitPlatform() }
