package net.multigesture.kanama.buildlogic

import java.io.File
import java.security.MessageDigest

/**
 * A Web export of a Godot project with Kotlin scripts, around the Kotlin/Wasm compile: finding
 * Godot and its Web template, merging `kotlin-src` with its `web/` overrides, staging a copy of the
 * project with the generated GDScript proxies attached, and finishing Godot's export with the
 * Kotlin/Wasm bundle and the bridge.
 *
 * One copy for both callers (task 135 item 5): the Kanama Gradle plugin's `exportWeb` (a kit or
 * store-addon project) and the checkout's `:web-runtime:exportWeb` (the root buildSrc compiles this
 * directory too). Plain Kotlin, no Gradle API: a failure is a [KanamaWebExportException] whose
 * message says what to do.
 */
object KanamaWebExport {
  class KanamaWebExportException(message: String) : RuntimeException(message)

  private fun fail(message: String): Nothing = throw KanamaWebExportException(message)

  /** The renderer and threading every Kanama Web export uses. */
  const val RENDERER = "gl_compatibility"
  const val THREADS_SUPPORTED = false

  /** The files of the staged project's `kanama-web/` folder that come from the Web runtime. */
  const val BRIDGE = "kanama-web-bridge.js"
  const val SHELL = "shell.html"
  const val PRESET = "export_presets.cfg"

  /** The Kotlin/Wasm loader webpack writes (the shell loads it by this name). */
  const val LOADER = "kanama-web-spike.js"

  const val PROXY_MANIFEST = "KanamaWebProxyManifest.generated.tsv"
  const val PROTOCOL_JSON = "KanamaWebProtocol.generated.json"

  private val hostOs: String = System.getProperty("os.name").lowercase()

  private fun onPath(vararg names: String): File? =
    System.getenv("PATH")
      .orEmpty()
      .split(File.pathSeparator)
      .filter { it.isNotBlank() }
      .firstNotNullOfOrNull { dir ->
        names.map { File(dir, it) }.firstOrNull { it.isFile && it.canExecute() }
      }

  /**
   * Godot: [property] (`-PkanamaGodotExecutable`), else [environment] (`KANAMA_GODOT_BIN`), else
   * the standard install (macOS) or `godot` on PATH. A bare name is looked up on PATH by the
   * export; a path must exist.
   */
  fun findGodot(property: String?, environment: String?): String {
    fun named(path: String, source: String): String {
      if ((File.separatorChar in path || '/' in path) && !File(path).isFile) {
        fail(
          "Godot not found at $path ($source). Pass -PkanamaGodotExecutable=/absolute/path/to/godot"
        )
      }
      return path
    }
    property?.takeIf { it.isNotBlank() }?.let { return named(it, "-PkanamaGodotExecutable") }
    environment?.takeIf { it.isNotBlank() }?.let { return named(it, "KANAMA_GODOT_BIN") }
    val home = System.getProperty("user.home")
    val found =
      when {
        "mac" in hostOs ->
          listOf("/Applications/Godot.app", "$home/Applications/Godot.app")
            .map { File(it, "Contents/MacOS/Godot") }
            .firstOrNull { it.isFile } ?: onPath("godot", "godot4")
        "windows" in hostOs -> onPath("godot.exe", "godot4.exe")
        else -> onPath("godot", "godot4")
      }
    return found?.absolutePath
      ?: fail(
        "Godot was not found (looked for KANAMA_GODOT_BIN, the standard install and `godot` on " +
          "PATH). Pass -PkanamaGodotExecutable=/absolute/path/to/godot"
      )
  }

  /**
   * Godot's Web release template: [property] (`-PkanamaWebTemplateRelease`), else
   * `web_nothreads_release.zip` in Godot's export templates folder for [godotVersion].
   */
  fun findTemplateRelease(property: File?, godotVersion: String): File {
    if (property != null) {
      if (!property.isFile) {
        fail(
          "Godot Web release template not found: $property. " +
            "Pass -PkanamaWebTemplateRelease=/absolute/path/to/web_nothreads_release.zip"
        )
      }
      return property
    }
    val home = System.getProperty("user.home")
    val templates =
      when {
        "mac" in hostOs -> File(home, "Library/Application Support/Godot/export_templates")
        "windows" in hostOs ->
          File(System.getenv("APPDATA") ?: "$home/AppData/Roaming", "Godot/export_templates")
        else ->
          File(
            System.getenv("XDG_DATA_HOME")?.takeIf { it.isNotBlank() } ?: "$home/.local/share",
            "godot/export_templates",
          )
      }
    val template = templates.resolve("$godotVersion/web_nothreads_release.zip")
    if (!template.isFile) {
      fail(
        "Godot's Web export template is not installed at $template. Install the $godotVersion " +
          "export templates from Editor > Manage Export Templates, or pass " +
          "-PkanamaWebTemplateRelease=/absolute/path/to/web_nothreads_release.zip"
      )
    }
    return template
  }

  /**
   * Task 64 (single-source scripts): the Web build compiles a merged view of [shared] (the
   * project's `kotlin-src`) with [overrides] (`web/kotlin-src`, when present) copied over it, so a
   * file present in both resolves to the Web copy. [excludesFile] (`web/kotlin-src-excludes.txt`)
   * lists `kotlin-src`-relative paths that stay desktop-only, one per line. The result is
   * `[out]/kotlin-src`, so the processor derives the same `res://kotlin-src/...` paths.
   */
  fun mergeScripts(shared: File, overrides: File?, excludesFile: File?, out: File) {
    val overrideActive = overrides?.isDirectory == true
    val excludes =
      if (excludesFile?.isFile == true) {
        excludesFile
          .readLines()
          .map { it.trim() }
          .filter { it.isNotEmpty() && !it.startsWith("#") }
          .map { it.replace('\\', '/') }
      } else emptyList()
    for (entry in excludes) {
      if (!shared.resolve(entry).isFile) {
        fail("kotlin-src-excludes.txt lists '$entry' but kotlin-src/$entry does not exist")
      }
      if (overrideActive && overrides!!.resolve(entry).isFile) {
        fail(
          "kotlin-src-excludes.txt lists '$entry' but web/kotlin-src/$entry exists too -- " +
            "an excluded file cannot also be overridden"
        )
      }
    }
    out.deleteRecursively()
    val target = out.resolve("kotlin-src")
    copyKotlinSources(shared, target) { it !in excludes }
    if (overrideActive) copyKotlinSources(overrides!!, target) { true }
    if (target.walkTopDown().none { it.isFile && it.extension == "kt" }) {
      fail("$shared has no Kotlin script (.kt) to compile for the Web")
    }
  }

  private fun copyKotlinSources(from: File, into: File, keep: (String) -> Boolean) {
    if (!from.isDirectory) return
    from
      .walkTopDown()
      .filter { it.isFile && it.extension == "kt" }
      .forEach { file ->
        val path = file.relativeTo(from).invariantSeparatorsPath
        if (keep(path)) file.copyTo(into.resolve(path), overwrite = true)
      }
  }

  /** Why `run/main_scene` of [projectDir] does not name a scene file, or null when it does. */
  fun mainSceneProblem(projectDir: File): String? {
    val setting =
      projectDir
        .resolve("project.godot")
        .readLines()
        .map { it.trim() }
        .firstOrNull { it.startsWith("run/main_scene=") }
        ?: return "$projectDir/project.godot has no main scene (run/main_scene). " +
          "Set one in Project > Project Settings > Application > Run"
    val scene = setting.substringAfter('=').trim().removeSurrounding("\"")
    if (scene.startsWith("res://")) {
      return if (projectDir.resolve(scene.removePrefix("res://")).isFile) null
      else "The main scene $scene of $projectDir does not exist"
    }
    if (scene.startsWith("uid://")) {
      val skip = setOf(".git", ".godot", ".gradle", ".kotlin", "build", "addons")
      val scenes =
        projectDir
          .walkTopDown()
          .onEnter { it == projectDir || it.name !in skip }
          .filter { it.isFile && (it.extension == "tscn" || it.extension == "scn") }
          .toList()
      val found =
        scenes.any { file ->
          file.extension == "tscn" &&
            file.bufferedReader().use { it.readLine() }.orEmpty().contains("uid=\"$scene\"")
        }
      // A binary .scn keeps its uid inside; with one present the uid cannot be ruled out here.
      return if (found || scenes.any { it.extension == "scn" }) null
      else "The main scene $scene of $projectDir matches no scene file"
    }
    return "The main scene \"$scene\" of $projectDir is not a res:// or uid:// path"
  }

  /**
   * Left out of the staged copy: version control, editor and build caches, the Gradle project, the
   * Android build, the Kanama desktop add-on, the Kotlin sources (the proxies replace them), the Web
   * override folder, and the project's own export presets (the export uses Kanama's).
   */
  private val stageExcludedTopLevel =
    setOf(
      ".git", ".godot", ".gradle", ".kotlin", "build", ".idea", "android", "web", "kotlin-src",
      "gradle", "build.gradle.kts", "gradle.properties", "gradlew", "gradlew.bat",
      "settings.gradle.kts", "export_presets.cfg",
    )

  private fun stagedExcluded(path: String): Boolean {
    val top = path.substringBefore('/')
    return top in stageExcludedTopLevel ||
      ('/' !in path && path.endsWith(".iml")) ||
      path == "addons/kanama" ||
      path.startsWith("addons/kanama/")
  }

  /**
   * Left out of the before/after checksum: folders a build, an IDE or the Godot editor may write
   * while the staging runs (the editor's build on save rewrites addons/kanama's scripts jar), none
   * of them staged. Everything else in the project must be byte-identical afterwards.
   */
  private val checksumExcludes =
    setOf(".git", ".godot", ".gradle", ".kotlin", "build", ".idea", "android/build", "addons/kanama")

  /** The SHA-256 of [projectDir]'s files (paths and contents), leaving out [checksumExcludes]. */
  fun projectChecksum(projectDir: File): String {
    val digest = MessageDigest.getInstance("SHA-256")
    projectDir
      .walkTopDown()
      .onEnter { dir ->
        val path = dir.relativeTo(projectDir).invariantSeparatorsPath
        path.isEmpty() || path !in checksumExcludes
      }
      .filter { it.isFile }
      .map { file -> file.relativeTo(projectDir).invariantSeparatorsPath to file }
      .sortedBy { it.first }
      .forEach { (path, file) ->
        digest.update(path.toByteArray(Charsets.UTF_8))
        digest.update(0)
        digest.update(file.readBytes())
        digest.update(0)
      }
    return digest.digest().joinToString("") { "%02x".format(it) }
  }

  /** What [stageProject] reads. */
  class StageInputs(
    /** The Godot project (the folder of `project.godot`). */
    val projectDir: File,
    /** Where the staged copy goes (deleted first). */
    val stagingDir: File,
    /** Kanama's Web export preset. */
    val preset: File,
    /** The bridge and the shell, copied into the staged project's `kanama-web/`. */
    val assets: List<File>,
    /** KSP's proxy output: the `.gd` proxies, the proxy manifest and the protocol descriptor. */
    val proxies: File,
    /** The merged `kotlin-src` the Kotlin/Wasm build compiled ([mergeScripts]). */
    val mergedSources: File,
    /** More files for the staged `kanama-web/` (the checkout's coverage report). */
    val extraFiles: List<File> = emptyList(),
  )

  /** The scripts a staging found. */
  class StageResult(val scriptCount: Int, val unattached: List<String>)

  /**
   * Stages [inputs]' project for Godot's Web export, leaving the project itself unchanged (checked
   * by checksum): a copy without the Kotlin sources and the Gradle project, Kanama's preset, the
   * generated proxies under `kanama-web/generated/`, every `res://kotlin-src/...kt` attachment
   * rewritten to its proxy, and the Compatibility renderer pinned. The expected scripts are the
   * project's own: every `@ScriptClass` KSP found must have a proxy row, and every row a script.
   */
  fun stageProject(inputs: StageInputs): StageResult {
    val project = inputs.projectDir
    val staging = inputs.stagingDir
    val checksumBefore = projectChecksum(project)
    staging.deleteRecursively()
    project
      .walkTopDown()
      .onEnter { dir ->
        val path = dir.relativeTo(project).invariantSeparatorsPath
        path.isEmpty() || !stagedExcluded(path)
      }
      .filter { it.isFile && !stagedExcluded(it.relativeTo(project).invariantSeparatorsPath) }
      .forEach { it.copyTo(staging.resolve(it.relativeTo(project).path), overwrite = true) }
    inputs.preset.copyTo(staging.resolve(PRESET), overwrite = true)
    val kanamaWeb = staging.resolve("kanama-web")
    inputs.proxies
      .listFiles { file -> file.isFile && file.extension == "gd" }
      .orEmpty()
      .forEach { it.copyTo(kanamaWeb.resolve("generated/${it.name}"), overwrite = true) }
    for (name in listOf(PROXY_MANIFEST, PROTOCOL_JSON)) {
      val file = inputs.proxies.resolve(name)
      if (!file.isFile) fail("Missing generated Web file: $file")
      file.copyTo(kanamaWeb.resolve(name), overwrite = true)
    }
    for (file in inputs.assets + inputs.extraFiles) {
      file.copyTo(kanamaWeb.resolve(file.name), overwrite = true)
    }

    val mappings =
      inputs.proxies
        .resolve(PROXY_MANIFEST)
        .readLines()
        .filter { it.isNotBlank() && !it.startsWith("#") }
        .map { line ->
          val columns = line.split('\t')
          if (columns.size != 3) fail("Invalid Web proxy manifest row: $line")
          columns[0] to columns[1]
        }
        .filter { (sourcePath, _) -> sourcePath.startsWith("res://kotlin-src/") }
        .toMap()
    val protocol = inputs.proxies.resolve(PROTOCOL_JSON)
    val compiled =
      Regex("\"resourcePath\"\\s*:\\s*\"([^\"]*)\"")
        .findAll(protocol.readText())
        .map { it.groupValues[1] }
        .filter { it.startsWith("res://kotlin-src/") }
        .toSet()
    if (compiled.isEmpty()) fail("$project/kotlin-src has no @ScriptClass script to export")
    val missingSources =
      compiled.filter { !inputs.mergedSources.resolve(it.removePrefix("res://kotlin-src/")).isFile }
    if (missingSources.isNotEmpty()) {
      fail(
        "Stale Web proxies: the generated protocol lists ${missingSources.sorted()}, which " +
          "kotlin-src no longer has. Run the same command again with --rerun-tasks added"
      )
    }
    if (compiled != mappings.keys) {
      fail(
        "The Web proxies do not match the project's scripts: no proxy for " +
          "${(compiled - mappings.keys).sorted()}, no script for ${(mappings.keys - compiled).sorted()}"
      )
    }

    val used = mutableSetOf<String>()
    val attachmentUid =
      Regex(
        "(\\[ext_resource type=\"Script\") uid=\"uid://[^\"]*\" (path=\"res://kanama-web/generated/)"
      )
    staging
      .walkTopDown()
      .filter {
        it.isFile &&
          (it.extension == "tscn" ||
            it.extension == "tres" ||
            it.relativeTo(staging).path == "project.godot")
      }
      .forEach { staged ->
        val original = staged.readText()
        var rewritten =
          mappings.entries.fold(original) { text, (sourcePath, proxyPath) ->
            if (text.contains(sourcePath)) used += sourcePath
            text.replace(sourcePath, proxyPath)
          }
        rewritten = rewritten.replace(attachmentUid, "$1 $2")
        if (rewritten != original) staged.writeText(rewritten)
        if (rewritten.contains("res://kotlin-src/")) {
          fail("Unmapped Kotlin attachment remains in staged file: $staged")
        }
      }

    // The Web export runs the Compatibility renderer: pin it in the staged copy, whatever the
    // project uses (Forward+, Mobile, or no [rendering] section at all).
    val stagedProject = staging.resolve("project.godot")
    var projectText = stagedProject.readText()
    projectText =
      projectText.replace(
        Regex("(config/features=PackedStringArray\\([^)]*)\"(Forward Plus|Mobile)\"")
      ) {
        "${it.groupValues[1]}\"GL Compatibility\""
      }
    projectText =
      projectText.replace(
        Regex("(?m)^renderer/rendering_method=\"(forward_plus|mobile)\"$"),
        "renderer/rendering_method=\"$RENDERER\"",
      )
    if (!Regex("(?m)^renderer/rendering_method=").containsMatchIn(projectText)) {
      val section = Regex("(?m)^\\[rendering\\][ \\t]*$")
      projectText =
        if (section.containsMatchIn(projectText)) {
          projectText.replaceFirst(section, "[rendering]\n\nrenderer/rendering_method=\"$RENDERER\"")
        } else {
          projectText.trimEnd() + "\n\n[rendering]\n\nrenderer/rendering_method=\"$RENDERER\"\n"
        }
    }
    if (!projectText.contains("renderer/rendering_method=\"$RENDERER\"")) {
      fail("Could not pin the Compatibility renderer in the staged $stagedProject")
    }
    stagedProject.writeText(projectText)

    val checksumAfter = projectChecksum(project)
    if (checksumAfter != checksumBefore) {
      fail("$project changed during staging: before=$checksumBefore after=$checksumAfter")
    }
    kanamaWeb
      .resolve("SourceChecksum.generated.txt")
      .writeText("sha256=$checksumAfter\nstatus=unchanged\n")
    return StageResult(compiled.size, (mappings.keys - used).sorted())
  }

  /** Points the staged project's preset at the Web templates Godot exports with. */
  fun setPresetTemplates(stagingDir: File, release: File, debug: File?) {
    val preset = stagingDir.resolve(PRESET)
    if (!preset.isFile) fail("Staged project has no $PRESET: $preset")
    preset.writeText(
      preset
        .readText()
        .replace("custom_template/release=\"\"", "custom_template/release=\"${release.absolutePath}\"")
        .replace(
          "custom_template/debug=\"\"",
          "custom_template/debug=\"${debug?.absolutePath.orEmpty()}\"",
        )
    )
  }

  /** Godot's headless Web export of [stagingDir] into [exportDir]/index.html. */
  fun godotExportCommand(
    godot: String,
    stagingDir: File,
    exportDir: File,
    debug: Boolean,
  ): List<String> =
    listOf(
      godot,
      "--headless",
      "--path",
      stagingDir.absolutePath,
      if (debug) "--export-debug" else "--export-release",
      "Web",
      exportDir.resolve("index.html").absolutePath,
    )

  /** The `protocolVersion` of a generated protocol descriptor. */
  fun protocolVersion(protocolJson: File): Int {
    if (!protocolJson.isFile) fail("Missing generated Web protocol descriptor: $protocolJson")
    return Regex("\"protocolVersion\"\\s*:\\s*(\\d+)")
      .find(protocolJson.readText())
      ?.groupValues
      ?.get(1)
      ?.toInt() ?: fail("Could not read protocolVersion from $protocolJson")
  }

  /** The protocol a bridge speaks (`const KANAMA_WEB_PROTOCOL_VERSION = <n>;`). */
  fun bridgeProtocolVersion(bridge: File): Int =
    Regex("const KANAMA_WEB_PROTOCOL_VERSION = (\\d+);")
      .find(bridge.readText())
      ?.groupValues
      ?.get(1)
      ?.toInt() ?: fail("$bridge states no KANAMA_WEB_PROTOCOL_VERSION")

  /** What [finishExport] reads. */
  class FinishInputs(
    val exportDir: File,
    /** The webpack distribution: the loader and its content-hashed wasm. */
    val distribution: File,
    val bridge: File,
    val protocolJson: File,
    val stagingDir: File,
    /** The export report's `demo` (`project` for a user's project). */
    val demo: String,
    /** The export report's `project`: a hash naming the project, or null. */
    val projectId: String? = null,
  )

  class FinishResult(val totalBytes: Long, val protocolVersion: Int, val buildId: String)

  /**
   * Completes Godot's export in [FinishInputs.exportDir]: the Kotlin/Wasm bundle, the bridge and the
   * protocol descriptor go in beside `index.html`, the two fixed-name scripts are cache-busted, and
   * `kanama-web/export-report.json` lists every served file. Fails when the served page leaks a
   * local path or ships a source map.
   */
  fun finishExport(inputs: FinishInputs): FinishResult {
    val exportDir = inputs.exportDir
    val demo = inputs.demo
    inputs.distribution
      .listFiles { file -> file.isFile && (file.extension == "js" || file.extension == "wasm") }
      .orEmpty()
      .forEach { it.copyTo(exportDir.resolve(it.name), overwrite = true) }
    inputs.bridge.copyTo(exportDir.resolve(BRIDGE), overwrite = true)
    // Task 81 (census-as-gate): the export carries the protocol manifest that describes it, so the
    // smoke harness can resolve the bridge's exercised "method#<id>" census keys to member names.
    inputs.protocolJson.copyTo(exportDir.resolve("kanama-web/$PROTOCOL_JSON"), overwrite = true)

    val indexHtml = exportDir.resolve("index.html")
    if (!indexHtml.isFile) fail("Godot Web export did not produce index.html for $demo")
    val loader = exportDir.resolve(LOADER)
    val bridge = exportDir.resolve(BRIDGE)
    if (!loader.isFile) fail("Kotlin/Wasm loader missing from $demo export")

    // Cache-busting: the wasm is content-hashed by webpack (its name is its hash) and the loader
    // references it by that name. Only the two fixed-name entry scripts can go stale, so they are
    // stamped with a build id derived from their content.
    val buildId =
      MessageDigest.getInstance("SHA-256").let { digest ->
        digest.update(loader.readBytes())
        digest.update(bridge.readBytes())
        digest.digest().joinToString("") { "%02x".format(it) }.substring(0, 16)
      }
    val originalIndex = indexHtml.readText()
    if (!originalIndex.contains("src=\"$LOADER\"")) {
      fail("Exported shell is missing the $LOADER script tag")
    }
    if (!originalIndex.contains("src=\"$BRIDGE\"")) {
      fail("Exported shell is missing the $BRIDGE script tag")
    }
    val servedIndex =
      originalIndex
        .replace("src=\"$LOADER\"", "src=\"$LOADER?v=$buildId\"")
        .replace("src=\"$BRIDGE\"", "src=\"$BRIDGE?v=$buildId\"")
    indexHtml.writeText(servedIndex)

    // Self-contained: no workstation-absolute path in the served HTML.
    val stagingPath = inputs.stagingDir.absolutePath
    if (servedIndex.contains(stagingPath)) fail("Exported index.html leaks the staging path $stagingPath")
    if (servedIndex.contains(System.getProperty("user.home"))) {
      fail("Exported index.html leaks a workstation-absolute path")
    }
    if (exportDir.listFiles { file -> file.extension == "wasm" }.isNullOrEmpty()) {
      fail("Web export has no wasm payload for $demo")
    }
    if (exportDir.walkTopDown().any { it.isFile && it.extension == "map" }) {
      fail("Web export must not ship source maps for $demo")
    }

    // Release payload report: every served file and its size.
    val protocolVersion = protocolVersion(inputs.protocolJson)
    val servedFiles =
      exportDir
        .walkTopDown()
        .filter { it.isFile }
        .map { it.relativeTo(exportDir).invariantSeparatorsPath to it.length() }
        .sortedBy { it.first }
        .toList()
    val totalBytes = servedFiles.sumOf { it.second }
    val report = exportDir.resolve("kanama-web/export-report.json")
    report.parentFile.mkdirs()
    report.writeText(
      buildString {
        append("{\n")
        append("  \"demo\": \"$demo\",\n")
        if (inputs.projectId != null) append("  \"project\": \"${inputs.projectId}\",\n")
        append("  \"protocolVersion\": $protocolVersion,\n")
        append("  \"buildId\": \"$buildId\",\n")
        append("  \"renderer\": \"$RENDERER\",\n")
        append("  \"threadsSupported\": $THREADS_SUPPORTED,\n")
        append("  \"sourceMaps\": false,\n")
        append("  \"totalBytes\": $totalBytes,\n")
        append("  \"files\": [\n")
        append(
          servedFiles.joinToString(",\n") { (path, size) ->
            "    {\"name\": \"$path\", \"bytes\": $size}"
          }
        )
        append("\n  ]\n")
        append("}\n")
      }
    )
    return FinishResult(totalBytes, protocolVersion, buildId)
  }

  /**
   * A hash of [projectDir]'s canonical path (so `/tmp/x` and `/private/tmp/x` are one project): the
   * export report is served, so the path itself must not be in it.
   */
  fun projectId(projectDir: File): String =
    MessageDigest.getInstance("SHA-256")
      .digest(projectDir.canonicalPath.toByteArray(Charsets.UTF_8))
      .joinToString("") { "%02x".format(it) }
      .substring(0, 16)
}
