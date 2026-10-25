// The Kanama Gradle plugin (`net.multigesture.kanama`, task 135 item 5): the export tasks a kit or
// store-addon project runs without a Kanama checkout. Published into the kit's own Maven repository
// (addons/kanama/maven) by publishKanamaPackageMavenRepository, beside the runtime it builds against;
// the project's settings.gradle.kts takes its version from addons/kanama/kanama.version.
//
// It runs inside the user's Gradle (9.4.1 embeds Kotlin 2.3), not on the Kotlin this repo compiles
// with: bytecode for Java 17 and the Kotlin 2.2 API, so a newer stdlib call fails here, not there.
plugins {
  kotlin("jvm")
  `java-gradle-plugin`
}

base { archivesName.set("kanama-gradle-plugin") }

kotlin {
  jvmToolchain(25)
  compilerOptions {
    jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
    apiVersion.set(org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_2_2)
    languageVersion.set(org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_2_2)
    freeCompilerArgs.add("-Xjdk-release=17")
  }
}

tasks.withType<JavaCompile>().configureEach { options.release.set(17) }

dependencies {
  testImplementation(kotlin("test-junit5"))
  testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test { useJUnitPlatform() }

gradlePlugin {
  plugins {
    create("kanama") {
      id = "net.multigesture.kanama"
      implementationClass = "net.multigesture.kanama.gradle.KanamaPlugin"
      displayName = "Kanama"
      description = "Builds a Kanama project's Kotlin scripts for Android export."
    }
  }
}

publishing {
  publications.withType<MavenPublication>().configureEach {
    if (name == "pluginMaven") artifactId = "kanama-gradle-plugin"
  }
}

// One copy of each piece of build logic the plugin shares with the checkout build:
// - the PanamaPort source remap and its audits (android/godot-plugin/buildSrc, which compiles the
//   runtime AAR and the checkout's scripts AAR with it);
// - the `.gdap` and descriptor writer (src/shared, which the root buildSrc compiles too, for
//   installAndroidPluginAar).
val remapSource = rootProject.layout.projectDirectory.file(
  "android/godot-plugin/buildSrc/src/main/kotlin/KanamaAndroidRemap.kt"
)
val copyRemapSource by
  tasks.registering(Sync::class) {
    from(remapSource)
    into(layout.buildDirectory.dir("generated/kanama-remap"))
  }

// KanamaVersions: every version the plugin's inner builds and checks use, read when the plugin is
// built from the files that pin them for the checkout, so a kit cannot drift from the checkout that
// made it. Each pattern must match (scripts/check_kit_version_pins.py checks the restated copies).
val pinSources =
  mapOf(
    "build.gradle.kts" to rootProject.layout.projectDirectory.file("build.gradle.kts"),
    "gradle.properties" to rootProject.layout.projectDirectory.file("gradle.properties"),
    "android" to rootProject.layout.projectDirectory.file("android/godot-plugin/build.gradle.kts"),
    "android.properties" to
      rootProject.layout.projectDirectory.file("android/godot-plugin/gradle.properties"),
    "android scripts" to
      rootProject.layout.projectDirectory.file("android/godot-plugin/scripts/build.gradle.kts"),
  )
val kanamaVersionsDir = layout.buildDirectory.dir("generated/kanama-versions")
val generateKanamaVersions by
  tasks.registering {
    val sources = pinSources.mapValues { it.value.asFile }
    val version = project.version.toString()
    val panamaPort = rootProject.extra["kanamaPanamaPortCoreDependency"].toString()
    val out = kanamaVersionsDir
    inputs.files(sources.values)
    inputs.property("version", version)
    inputs.property("panamaPort", panamaPort)
    outputs.dir(out)
    doLast {
      fun pin(source: String, pattern: String): String {
        val file = sources.getValue(source)
        return Regex(pattern, RegexOption.MULTILINE).find(file.readText())?.groupValues?.get(1)
          ?: throw GradleException("KanamaVersions: no match for /$pattern/ in ${file.path}")
      }
      val values =
        linkedMapOf(
          "KANAMA" to version,
          "KOTLIN" to pin("build.gradle.kts", """^\s*kotlin\("jvm"\) version "([^"]+)""""),
          "KSP" to pin("build.gradle.kts", """^\s*id\("com\.google\.devtools\.ksp"\) version "([^"]+)""""),
          "GODOT" to pin("gradle.properties", """^kanamaGodotVersion=(\S+)"""),
          "PANAMAPORT_CORE" to panamaPort,
          "ANDROID_GRADLE_PLUGIN" to pin("android", """^\s*id\("com\.android\.library"\) version "([^"]+)""""),
          "ANDROID_KOTLIN" to pin("android", """^\s*id\("org\.jetbrains\.kotlin\.android"\) version "([^"]+)""""),
          "ANDROID_KOTLIN_API" to pin("android scripts", """KotlinVersion\.(KOTLIN_\d+_\d+)"""),
          "ANDROID_COROUTINES" to pin("android.properties", """^kanamaAndroidCoroutinesVersion=(\S+)"""),
          "ANDROID_COMPILE_SDK" to pin("android scripts", """^\s*compileSdk = (\d+)"""),
          "ANDROID_MIN_SDK" to pin("android scripts", """^\s*minSdk = (\d+)"""),
        )
      val file = out.get().file("net/multigesture/kanama/gradle/KanamaVersions.kt").asFile
      file.parentFile.mkdirs()
      file.writeText(
        buildString {
          appendLine("// Generated by gradle-plugin/build.gradle.kts (generateKanamaVersions). Do not edit.")
          appendLine("package net.multigesture.kanama.gradle")
          appendLine()
          appendLine("internal object KanamaVersions {")
          values.forEach { (key, value) -> appendLine("  const val $key = \"$value\"") }
          appendLine("}")
        }
      )
    }
  }

sourceSets.named("main") {
  kotlin.srcDir(copyRemapSource)
  kotlin.srcDir(generateKanamaVersions)
  kotlin.srcDir("src/shared/kotlin")
}

// The remap source keeps the android/godot-plugin style (it is not ktfmt-formatted there either).
tasks.withType<com.ncorti.ktfmt.gradle.tasks.KtfmtBaseTask>().configureEach {
  exclude("**/KanamaAndroidRemap.kt", "**/KanamaVersions.kt")
}
