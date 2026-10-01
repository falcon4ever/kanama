plugins {
    id("com.android.library")
}

// Scripts half of the Kanama Android deliverable (task 36 AAR split): the
// consumer project's `kotlin-src/` Kotlin scripts and generated KSP
// registrars, remapped through the same PanamaPort pass and compiled against
// the remapped runtime classes from `:plugin`. Produces a per-project AAR the
// runtime plugin's `.gdap` pulls in as a local dependency — at export time
// both AARs dex into the same APK classloader, so registrar lookup needs no
// loader-aware path.
val demoDir = providers.gradleProperty("kanamaAndroidDemoDir").map { file(it) }
val androidScriptSources = layout.buildDirectory.dir("generated/kanamaAndroidScriptSources")
val panamaPortCoreDependency = providers.gradleProperty("kanamaPanamaPortCore")
    .orElse("com.github.falcon4ever.PanamaPort:Core:0.1.5-kanama-r8.1")

val auditAndroidDemoSources by tasks.registering {
    inputs.dir(demoDir.map { it.resolve("kotlin-src") })

    doLast {
        if (!demoDir.isPresent) {
            throw GradleException(
                "Missing -PkanamaAndroidDemoDir=/absolute/path/to/kanama demo project",
            )
        }
        KanamaAndroidRemap.auditOriginalDemoSources(demoDir.get().resolve("kotlin-src"))
    }
}

val prepareAndroidKanamaScriptSources by tasks.registering(Sync::class) {
    into(androidScriptSources)

    fun CopySpec.remapForeignImports() {
        filter { line: String ->
            KanamaAndroidRemap.remapLine(line)
        }
    }

    from(demoDir.map { it.resolve("kotlin-src") }) {
        remapForeignImports()
    }
    from(demoDir.map { it.resolve("build/generated/ksp/main/kotlin") }) {
        remapForeignImports()
    }

    doFirst {
        if (!demoDir.isPresent) {
            throw GradleException(
                "Missing -PkanamaAndroidDemoDir=/absolute/path/to/kanama demo project",
            )
        }
    }
}

val auditAndroidKanamaScriptSources by tasks.registering {
    dependsOn(auditAndroidDemoSources)
    dependsOn(prepareAndroidKanamaScriptSources)
    inputs.dir(androidScriptSources)

    doLast {
        KanamaAndroidRemap.auditGeneratedSources(androidScriptSources.get().asFile, "scripts")
    }
}

android {
    namespace = "net.multigesture.kanama.android.scripts"
    compileSdk = 36

    defaultConfig {
        minSdk = 26
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

androidComponents {
    onVariants(selector().all()) { variant ->
        variant.sources.kotlin?.addStaticSourceDirectory("build/generated/kanamaAndroidScriptSources")
    }
}

tasks.named("preBuild") {
    dependsOn(auditAndroidKanamaScriptSources)
}

dependencies {
    compileOnly(project(":plugin"))
    compileOnly(panamaPortCoreDependency)
    compileOnly("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.11.0")
}
