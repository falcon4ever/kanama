import org.gradle.jvm.tasks.Jar

// The Kanama version is the one line of addons/kanama/kanama.version, which comes with the addon
// (settings.gradle.kts resolves the Kanama Gradle plugin from the same file).
val kanamaVersionFile = file("addons/kanama/kanama.version")
if (!kanamaVersionFile.isFile) {
    throw GradleException("Kanama addon not found: addons/kanama/kanama.version is missing. Unzip the Kanama addon into addons/ again.")
}
val kanamaVersion = kanamaVersionFile.readText().trim()
val kotlinSourcesDir = providers.gradleProperty("kanamaProjectScriptsDir")
    .orElse("kotlin-src")
val defaultGodotBin = file("/Applications/Godot.app/Contents/MacOS/Godot")
    .takeIf { it.exists() }
    ?.absolutePath
    ?: "godot"
val godotBin = providers.gradleProperty("kanama.godot.executable")
    .orElse(providers.gradleProperty("godotBin"))
    .orElse(providers.environmentVariable("KANAMA_GODOT"))
    .getOrElse(defaultGodotBin)

// Kanama comes only from the addon: no other repository may serve net.multigesture artifacts.
repositories {
    exclusiveContent {
        forRepository { maven { url = uri("addons/kanama/maven") } }
        filter { includeGroupByRegex("net\\.multigesture(\\..*)?") }
    }
    mavenCentral()
}

dependencies {
    "implementation"("net.multigesture.kanama:kanama:$kanamaVersion")
    "implementation"("net.multigesture.kanama:annotations:$kanamaVersion")
    "implementation"("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.11.0")
    "ksp"("net.multigesture.kanama:processor:$kanamaVersion")
}

// Task 133 C: KSP generates `Autoloads` from this project's `project.godot` `[autoload]` section,
// typing each autoload from its Kotlin script, scene root or GDScript `extends` line. Those files
// are not Kotlin sources, so they are declared as KSP inputs: editing them re-runs KSP.
tasks.matching { it.name == "kspKotlin" }.configureEach {
    val projectGodot = layout.projectDirectory.file("project.godot").asFile
    inputs
        .files(provider { kanamaAutoloadInputs(projectGodot) })
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

// Godot must neither import nor export the Gradle build directory: it holds KSP's generated `.kt`
// registrars (the editor would register a second class for each script, and exports would carry
// them) and the inner builds of the export tasks.
val ignoreBuildDirInGodot by tasks.registering {
    val gdignore = layout.buildDirectory.file(".gdignore")
    outputs.file(gdignore)
    doLast { gdignore.get().asFile.writeText("") }
}
tasks.matching { it.name == "kspKotlin" }.configureEach { dependsOn(ignoreBuildDirInGodot) }

tasks.named<Jar>("jar") {
    archiveFileName.set("kanama-scripts.jar")
    isPreserveFileTimestamps = false
    isReproducibleFileOrder = true
}

val installScriptJar by tasks.registering(Copy::class) {
    group = "kanama"
    description = "Install compiled Kotlin scripts into addons/kanama."
    dependsOn(tasks.named("jar"))
    from(tasks.named<Jar>("jar").flatMap { it.archiveFile })
    into(layout.projectDirectory.dir("addons/kanama"))
    // The Kanama version this jar was built with: the editor compares it with
    // addons/kanama/kanama.version and offers to rebuild when an addon update moved the version.
    val stamp = layout.projectDirectory.file("addons/kanama/kanama-scripts.version")
    val version = kanamaVersion
    inputs.property("kanamaVersion", version)
    doLast { stamp.asFile.writeText(version + "\n") }
}

tasks.register("buildScripts") {
    group = "kanama"
    description = "Compile kotlin-src and install addons/kanama/kanama-scripts.jar."
    dependsOn(installScriptJar)
}

tasks.register<Exec>("runGodot") {
    group = "kanama"
    description = "Run this project in Godot."
    commandLine(godotBin, "--path", projectDir.absolutePath)
}

tasks.register<Exec>("openGodotEditor") {
    group = "kanama"
    description = "Open this project in the Godot editor."
    commandLine(godotBin, "--path", projectDir.absolutePath, "--editor")
}

tasks.register<Exec>("importGodot") {
    group = "kanama"
    description = "Import this project's assets in Godot."
    commandLine(godotBin, "--headless", "--import", "--path", projectDir.absolutePath)
}

tasks.register("buildAndRunGodot") {
    group = "kanama"
    description = "buildScripts, then runGodot."
    dependsOn("buildScripts", "runGodot")
    tasks.named("runGodot").get().mustRunAfter("buildScripts")
}
