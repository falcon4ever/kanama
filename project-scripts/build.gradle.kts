plugins {
    kotlin("jvm")
    id("com.google.devtools.ksp")
}

import com.google.devtools.ksp.gradle.KspAATask
import java.security.MessageDigest

group = "net.multigesture.kanama"
version = "0.4.0"

repositories {
    mavenCentral()
}

// The desktop scripts jar is also what the export-time editor loads to learn a project's
// @Export names: Godot's export instantiates and re-packs every scene when it converts text
// resources to binary, and properties the script instance does not report are dropped. So when a
// caller registers a project's kotlin-src for iOS only (`installIosAddon
// -PkanamaIosProjectScriptsDir=…`), compile the same sources for desktop too instead of shipping the
// example project's jar into that project (task 106: Match3's tile_scene arrived null on the phone).
val configuredScriptDirs =
    providers.gradleProperty("kanamaProjectScriptsDirs")
        .orElse(providers.gradleProperty("kanamaProjectScriptsDir"))
        .orElse(providers.gradleProperty("kanamaIosProjectScriptsDirs"))
        .orElse(providers.gradleProperty("kanamaIosProjectScriptsDir"))
        // `installAndroidPluginAar -PkanamaAndroidDemoDir=<project>` generates the Android scripts
        // AAR's KSP registrars here, with this checkout's processor (task 119 item 40), so the
        // project's kotlin-src is this build's input without any extra property.
        .orElse(providers.gradleProperty("kanamaAndroidDemoDir").map { "$it/kotlin-src" })
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
        // Godot Android exports leave a full project copy (kotlin-src + old
        // generated registrars) inside android/build's Gradle intermediates;
        // sweeping those up as script sources causes mass redeclarations. A
        // project's android/ dir (build template + plugins) and .godot cache
        // never hold hand-written script sources.
        kotlin.exclude("android/**", ".godot/**", "build/**")
    }
}

dependencies {
    implementation(project(":"))
    implementation(project(":annotations"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.11.0")
    ksp(project(":processor"))
}

tasks.matching { it.name == "kspKotlin" || it.name == "compileKotlin" }.configureEach {
    inputs.property("kanamaProjectScriptsDirs", activeScriptDirs)
}

// Task 133 C: the processor generates `Autoloads` from the project's `project.godot` (the one above
// the script sources), which is not a Kotlin source: declare it as a KSP input so an edit to the
// `[autoload]` section re-runs KSP. `files()` tolerates the candidates that do not exist.
tasks.matching { it.name == "kspKotlin" }.configureEach {
    val dirs =
        configuredScriptDirs.orNull
            ?.split(File.pathSeparator, ",")
            ?.map { it.trim() }
            ?.filter { it.isNotEmpty() }
            ?.map { file(it) }
            ?: listOf(layout.projectDirectory.dir("../example_project").asFile)
    // Task 133 C2: and the scene / GDScript that types each autoload.
    val candidates = dirs.flatMap { listOf(it.resolve("project.godot"), it.resolve("../project.godot")) }
    inputs
        .files(provider { candidates.flatMap { kanamaAutoloadInputs(it) } })
        .withPropertyName("kanamaGodotProjectFiles")
}

/**
 * Task 133 C2: the files KSP reads to generate `Autoloads` — `project.godot` and, per autoload, the
 * scene or GDScript whose root class / `extends` line types it (a `uid://` path is found through its
 * `.uid` sidecar or scene header). Read when the task runs, so a new autoload is tracked too.
 */
fun kanamaAutoloadInputs(projectGodot: File): List<File> {
    if (!projectGodot.isFile) return listOf(projectGodot)
    val root = projectGodot.parentFile
    val paths = mutableListOf<String>()
    var inAutoload = false
    projectGodot.forEachLine { raw ->
        val line = raw.trim()
        if (line.startsWith("[")) {
            inAutoload = line == "[autoload]"
        } else if (inAutoload && '=' in line) {
            paths += line.substringAfter('=').trim().removeSurrounding("\"").removePrefix("*")
        }
    }
    val uids = paths.filter { it.startsWith("uid://") }.toSet()
    val declaring = mutableMapOf<String, List<File>>()
    if (uids.isNotEmpty()) {
        val skip = setOf(".godot", ".git", "addons", "build", ".gradle")
        root.walkTopDown()
            .onEnter { it == root || it.name !in skip }
            .filter { it.isFile && (it.name.endsWith(".uid") || it.name.endsWith(".tscn")) }
            .forEach { file ->
                val first = file.bufferedReader().use { it.readLine() }?.trim().orEmpty()
                val uid =
                    if (file.name.endsWith(".uid")) first
                    else Regex("uid=\"(uid://[^\"]+)\"").find(first)?.groupValues?.get(1)
                if (uid != null && uid in uids) {
                    declaring[uid] =
                        if (file.name.endsWith(".uid")) listOf(file, File(file.path.removeSuffix(".uid")))
                        else listOf(file)
                }
            }
    }
    return listOf(projectGodot) +
        paths.flatMap { path ->
            if (path.startsWith("res://")) listOf(root.resolve(path.removePrefix("res://")))
            else declaring[path].orEmpty()
        }.filter { it.name.endsWith(".tscn") || it.name.endsWith(".gd") || it.name.endsWith(".uid") }
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
