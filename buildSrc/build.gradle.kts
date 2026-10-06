// Build logic of the root build that is worth unit tests: plain Kotlin the root build.gradle.kts
// calls (the iOS source-line table generator, task 131 item 13). Tests: `./gradlew -p buildSrc
// test` (CI runs it; `gradle build` of the root does not, as Gradle no longer tests buildSrc).
plugins {
  `kotlin-dsl`
  id("com.ncorti.ktfmt.gradle") version "0.22.0"
}

repositories {
  mavenCentral()
  gradlePluginPortal()
}

dependencies {
  testImplementation(kotlin("test-junit5"))
  testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test { useJUnitPlatform() }

// The root build's ktfmt knobs (build.gradle.kts, `allprojects`), so buildSrc reads the same.
ktfmt {
  googleStyle()
  blockIndent.set(2)
  continuationIndent.set(2)
}

tasks.matching { it.name.startsWith("ktfmt") && it.name.endsWith("Scripts") }
  .configureEach { enabled = false }
