import java.io.ByteArrayOutputStream
import java.io.File
import java.security.MessageDigest

plugins {
    kotlin("multiplatform")
    id("com.google.devtools.ksp")
}

val webScriptSourceRoot =
    layout.projectDirectory.dir("src/commonMain/kotlin/net/multigesture/kanama/web")

// The Web gameplay script root. An explicit -PkanamaWebExtraScriptSourceDir wins;
// otherwise it is derived from the selected demo's checkout as <projectDir>/web,
// where the committed web/kotlin-src/*.kt live. This keeps exportWeb free of any
// second workstation path -- pointing at the demo checkout is enough.
val webDemoKey: String = providers.gradleProperty("kanamaWebDemo").orElse("match3").get()
val webDemoProjectDir: File? =
    when (webDemoKey) {
        "match3" -> "kanamaWebMatch3ProjectDir"
        "bunnymark" -> "kanamaWebBunnymarkProjectDir"
        "dodge" -> "kanamaWebDodgeProjectDir"
        "web3d" -> "kanamaWebWeb3dProjectDir"
        "platformer" -> "kanamaWebPlatformerProjectDir"
        "squash" -> "kanamaWebSquashProjectDir"
        "fps" -> "kanamaWebFpsProjectDir"
        "charactercontroller" -> "kanamaWebCharactercontrollerProjectDir"
        "thirdperson" -> "kanamaWebThirdpersonProjectDir"
        "racing" -> "kanamaWebRacingProjectDir"
        "citybuilder" -> "kanamaWebCitybuilderProjectDir"
        "tpsdemo" -> "kanamaWebTpsdemoProjectDir"
        else -> null
    }
        ?.let { providers.gradleProperty(it).orNull }
        ?.let(rootProject::file)
val explicitWebScriptSourceRoot: File? =
    providers.gradleProperty("kanamaWebExtraScriptSourceDir").orNull?.let(rootProject::file)
val extraWebScriptSourceRoot: File? =
    explicitWebScriptSourceRoot
        ?: webDemoProjectDir?.resolve("web")?.takeIf { it.resolve("kotlin-src").isDirectory }
        ?: layout.projectDirectory
            .dir("src/web3dSmoke/web")
            .asFile
            .takeIf { webDemoKey == "web3d" && it.resolve("kotlin-src").isDirectory }

// Task 64 (single-source scripts): when the demo checkout has the shared
// desktop script root <projectDir>/kotlin-src, the Wasm build compiles a MERGED
// view -- the shared root is copied first and web/kotlin-src (IF present) is
// overlaid on top, so a file present in both resolves to the web copy
// (per-target override) and a file present only in the shared root reaches the
// Web build unchanged. A fully-converged demo may have NO web/kotlin-src left
// at all (git prunes the emptied directory); the merge activates on the shared
// root alone, so convergence can never turn the script root off. A demo with
// no shared root, or an explicit -PkanamaWebExtraScriptSourceDir, keeps the
// single-root behavior unchanged. Desktop-only scripts that must stay at their
// res://kotlin-src/ paths (e.g. Bunnymark's benchmark variants, selected by
// path at runtime) are listed in <projectDir>/web/kotlin-src-excludes.txt, one
// kotlin-src-relative path per line; the merge skips them and fails loudly on
// a stale or contradictory entry. The merged layout keeps the kotlin-src/
// prefix so the processor derives the same res://kotlin-src/*.kt resource
// paths as before.
val sharedWebScriptSourceRoot: File? =
    if (explicitWebScriptSourceRoot != null) null
    else webDemoProjectDir?.resolve("kotlin-src")?.takeIf { it.isDirectory }
val webOverrideParentDir: File? =
    if (explicitWebScriptSourceRoot != null) null else webDemoProjectDir?.resolve("web")
val mergedWebScriptRoot = layout.buildDirectory.dir("web-merged-scripts/$webDemoKey")
val mergeWebGameplayScripts: TaskProvider<Task>? =
    if (sharedWebScriptSourceRoot != null) {
        tasks.register("mergeWebGameplayScripts") {
            group = "build"
            description =
                "Merges the shared kotlin-src with web/kotlin-src overrides (Task 64)."
            val overrideDir = webOverrideParentDir?.resolve("kotlin-src")
            val excludesFile = webOverrideParentDir?.resolve("kotlin-src-excludes.txt")
            inputs.dir(sharedWebScriptSourceRoot)
            // files() tolerates absent paths, so an override-less or
            // excludes-less demo still has a stable, content-tracked input set.
            inputs.files(listOfNotNull(overrideDir, excludesFile))
            outputs.dir(mergedWebScriptRoot)
            doLast {
                val overrideActive = overrideDir?.isDirectory == true
                val excludes: List<String> =
                    if (excludesFile?.isFile == true) {
                        excludesFile
                            .readLines()
                            .map { it.trim() }
                            .filter { it.isNotEmpty() && !it.startsWith("#") }
                    } else emptyList()
                excludes.forEach { entry ->
                    check(sharedWebScriptSourceRoot.resolve(entry).isFile) {
                        "kotlin-src-excludes.txt lists '$entry' but kotlin-src/$entry does not exist"
                    }
                    check(!overrideActive || !overrideDir!!.resolve(entry).isFile) {
                        "kotlin-src-excludes.txt lists '$entry' but web/kotlin-src/$entry exists too -- " +
                            "an excluded file cannot also be overridden"
                    }
                }
                val out = mergedWebScriptRoot.get().asFile
                out.deleteRecursively()
                val target = out.resolve("kotlin-src")
                copy {
                    from(sharedWebScriptSourceRoot) {
                        include("**/*.kt")
                        excludes.forEach { exclude(it) }
                    }
                    into(target)
                }
                if (overrideActive) {
                    copy {
                        from(overrideDir) { include("**/*.kt") }
                        into(target)
                    }
                }
                check(target.isDirectory && target.listFiles()?.any { it.extension == "kt" } == true) {
                    "merged web script root is empty -- no gameplay scripts would be compiled"
                }
            }
        }
    } else null

kotlin {
    @OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)
    wasmJs {
        browser {
            commonWebpackConfig {
                outputFileName = "kanama-web-spike.js"
                sourceMaps = false
            }
        }
        binaries.executable()
    }

    sourceSets {
        val commonMain by getting {
            // Phase 0 keeps the existing JVM annotations module untouched while proving that the
            // same source declarations compile for Wasm. A post-GO common API migration can turn
            // :annotations into a real KMP dependency without coupling that refactor to the spike.
            kotlin.srcDir(rootProject.file("annotations/src/main/kotlin"))
            dependencies {
                implementation(kotlin("stdlib"))
                implementation(project(":kanama-common-api"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.11.0")
            }
        }
        val wasmJsMain by getting {
            if (mergeWebGameplayScripts != null) {
                // A plain directory srcDir (not a files().builtBy() wrapper): KSP
                // snapshots source-set dirs by content, and the wrapper form was
                // measured to drop the gameplay sources from the KSP task's inputs
                // entirely -- proxies then came out of the build cache without any
                // demo scripts. Task dependencies are declared explicitly below.
                kotlin.srcDir(mergedWebScriptRoot)
            } else {
                extraWebScriptSourceRoot?.let(kotlin::srcDir)
            }
        }
    }
}

dependencies {
    add("kspWasmJs", project(":processor"))
}

ksp {
    val gameplayScriptRoot =
        if (mergeWebGameplayScripts != null) mergedWebScriptRoot.get().asFile
        else extraWebScriptSourceRoot
    val scriptRoots =
        listOfNotNull(webScriptSourceRoot.asFile, gameplayScriptRoot)
            .joinToString(System.getProperty("path.separator")) { it.absolutePath }
    arg("kanamaScriptRoots", scriptRoots)
    arg("kanamaRuntimeTarget", "web")
}

// Both consumers of the merged gameplay sources need the explicit dependency:
// the srcDir above is a plain directory (deliberately -- see the comment there),
// so nothing else orders the merge before KSP or the Kotlin compilation.
mergeWebGameplayScripts?.let { merge ->
    tasks.configureEach {
        if (name == "kspKotlinWasmJs" || name == "compileKotlinWasmJs") {
            dependsOn(merge)
        }
    }
}

val webSpikeSourceProject = layout.projectDirectory.dir("src/webSpikeGodot/project")
val webSpikeAssets = layout.projectDirectory.dir("src/webSpikeGodot/assets")
val webProxyResources =
    layout.buildDirectory.dir(
        "generated/ksp/wasmJs/wasmJsMain/resources/net/multigesture/kanama/web/generated/proxies"
    )
val webSpikeStaging = layout.buildDirectory.dir("web-spike/godot-project")
val webSpikeExport = layout.buildDirectory.dir("web-spike/export")
val webBunnymarkSourceProject =
    providers.gradleProperty("kanamaWebBunnymarkProjectDir").orNull?.let(rootProject::file)
val webBunnymarkVariant =
    providers.gradleProperty("kanamaWebBunnymarkVariant").orElse("BunnymarkV1DrawTexture")
val webBunnymarkLanguage =
    providers.gradleProperty("kanamaWebBunnymarkLanguage").orElse("kanama")
val webBunnymarkBuildKey =
    providers.provider {
        val language = webBunnymarkLanguage.get()
        webBunnymarkVariant.get() + if (language == "kanama") "" else "-$language"
    }
val webBunnymarkStaging =
    layout.buildDirectory.dir(webBunnymarkBuildKey.map { "web-bunnymark/$it/godot-project" })
val webBunnymarkExport =
    layout.buildDirectory.dir(webBunnymarkBuildKey.map { "web-bunnymark/$it/export" })
val webMatch3SourceProject =
    providers.gradleProperty("kanamaWebMatch3ProjectDir").orNull?.let(rootProject::file)
val webMatch3Staging = layout.buildDirectory.dir("web-match3/godot-project")
val webMatch3Export = layout.buildDirectory.dir("web-match3/export")
val webDodgeSourceProject =
    providers.gradleProperty("kanamaWebDodgeProjectDir").orNull?.let(rootProject::file)
val webDodgeStaging = layout.buildDirectory.dir("web-dodge/godot-project")
// The web3d render smoke is a self-contained in-repo fixture (like the webSpike), not an
// external demo checkout; an explicit -PkanamaWebWeb3dProjectDir can still override it.
val webWeb3dSourceProject: File =
    providers.gradleProperty("kanamaWebWeb3dProjectDir").orNull?.let(rootProject::file)
        ?: layout.projectDirectory.dir("src/web3dSmoke").asFile
val webWeb3dStaging = layout.buildDirectory.dir("web-web3d/godot-project")
val webPlatformerSourceProject =
    providers.gradleProperty("kanamaWebPlatformerProjectDir").orNull?.let(rootProject::file)
val webPlatformerStaging = layout.buildDirectory.dir("web-platformer/godot-project")
val webSquashSourceProject =
    providers.gradleProperty("kanamaWebSquashProjectDir").orNull?.let(rootProject::file)
val webSquashStaging = layout.buildDirectory.dir("web-squash/godot-project")
val webFpsSourceProject =
    providers.gradleProperty("kanamaWebFpsProjectDir").orNull?.let(rootProject::file)
val webFpsStaging = layout.buildDirectory.dir("web-fps/godot-project")
val webCharacterSourceProject =
    providers.gradleProperty("kanamaWebCharactercontrollerProjectDir").orNull
        ?.let(rootProject::file)
val webCharacterStaging = layout.buildDirectory.dir("web-charactercontroller/godot-project")
val webThirdpersonSourceProject =
    providers.gradleProperty("kanamaWebThirdpersonProjectDir").orNull?.let(rootProject::file)
val webThirdpersonStaging = layout.buildDirectory.dir("web-thirdperson/godot-project")
val webRacingSourceProject =
    providers.gradleProperty("kanamaWebRacingProjectDir").orNull?.let(rootProject::file)
val webRacingStaging = layout.buildDirectory.dir("web-racing/godot-project")
val webCitybuilderSourceProject =
    providers.gradleProperty("kanamaWebCitybuilderProjectDir").orNull?.let(rootProject::file)
val webCitybuilderStaging = layout.buildDirectory.dir("web-citybuilder/godot-project")
val webTpsdemoSourceProject =
    providers.gradleProperty("kanamaWebTpsdemoProjectDir").orNull?.let(rootProject::file)
val webTpsdemoStaging = layout.buildDirectory.dir("web-tpsdemo/godot-project")
val webMatch3ImportLog = layout.buildDirectory.file("reports/web-match3-import.log")
val webMatch3ImportOutput = ByteArrayOutputStream()
val webGameplayCoverage = layout.buildDirectory.file("reports/web-gameplay-coverage.json")
val webGameplayCoverageSources =
    files(
        layout.projectDirectory.file(
            "src/commonMain/kotlin/net/multigesture/kanama/api/WebGodotApi.kt"
        ),
        layout.projectDirectory.file(
            "src/commonMain/kotlin/net/multigesture/kanama/api/WebMatch3Api.kt"
        ),
        layout.projectDirectory.file(
            "src/commonMain/kotlin/net/multigesture/kanama/api/WebDodgeApi.kt"
        ),
        // Task 64: AnimationPlayer.getCurrentAnimation rides the generic tier; its marker
        // lives in the platformer wrapper file.
        layout.projectDirectory.file(
            "src/commonMain/kotlin/net/multigesture/kanama/api/WebPlatformerApi.kt"
        ),
        // Task 76: the web3d fixture exercises the generic callv fallback; its
        // genericWebGameplayFallback markers populate the report's slow-path bucket.
        layout.projectDirectory.file("src/web3dSmoke/web/kotlin-src/Main.kt"),
    )
val webDistribution = layout.buildDirectory.dir("dist/wasmJs/productionExecutable")

tasks.register<Exec>("generateWebGameplayCoverage") {
    group = "verification"
    description = "Generates the explicit Task 57e Web gameplay-call backlog."
    inputs.files(webGameplayCoverageSources)
    inputs.file(rootProject.file("scripts/generate_web_gameplay_coverage.py"))
    outputs.file(webGameplayCoverage)

    commandLine(
        "python3",
        rootProject.file("scripts/generate_web_gameplay_coverage.py").absolutePath,
        "--output",
        webGameplayCoverage.get().asFile.absolutePath,
        *webGameplayCoverageSources.files
            .sortedBy { it.absolutePath }
            .map { it.absolutePath }
            .toTypedArray(),
    )
}

// Task 60a: the Web backend dispatch is generated from the shared platform_backend_calls.json plus
// the Web-local policy in generate_web_backend.py. `checkWebBackendDispatch` fails loud on drift;
// `generateWebBackendDispatch` rewrites the committed file (run ktfmtFormat after).
val webBackendDispatchGenerator = rootProject.file("scripts/generate_web_backend.py")
val webBackendPolicy = rootProject.file("scripts/platform_backend_calls.json")
val webBackendDispatchFile =
    layout.projectDirectory.file(
        "src/wasmJsMain/kotlin/net/multigesture/kanama/web/WebCommonGodotBackend.generated.kt"
    )

tasks.register<Exec>("generateWebBackendDispatch") {
    group = "verification"
    description = "Regenerates the Kotlin/Wasm Web backend dispatch from the shared backend contract."
    inputs.file(webBackendDispatchGenerator)
    inputs.file(rootProject.file("scripts/platform_backend_contract.py"))
    inputs.file(webBackendPolicy)
    outputs.file(webBackendDispatchFile)
    commandLine(
        "python3",
        webBackendDispatchGenerator.absolutePath,
        "--output",
        webBackendDispatchFile.asFile.absolutePath,
    )
}

tasks.register<Exec>("checkWebBackendDispatch") {
    group = "verification"
    description = "Fails if the generated Web backend dispatch drifts from the shared contract."
    inputs.file(webBackendDispatchGenerator)
    inputs.file(rootProject.file("scripts/platform_backend_contract.py"))
    inputs.file(webBackendPolicy)
    inputs.file(webBackendDispatchFile)
    commandLine(
        "python3",
        webBackendDispatchGenerator.absolutePath,
        "--output",
        webBackendDispatchFile.asFile.absolutePath,
        "--check",
    )
}

tasks.named("check") { dependsOn("checkWebBackendDispatch") }

tasks.register("stageWebSpikeGodotProject") {
    group = "verification"
    description = "Creates a disposable Godot project with generated Web proxies."
    dependsOn("kspKotlinWasmJs")
    inputs.dir(webSpikeSourceProject)
    inputs.dir(webSpikeAssets)
    inputs.dir(webProxyResources)
    outputs.dir(webSpikeStaging)

    doLast {
        val stagingDir = webSpikeStaging.get().asFile
        delete(stagingDir)
        copy {
            from(webSpikeSourceProject)
            into(stagingDir)
        }
        copy {
            from(webSpikeAssets)
            into(stagingDir.resolve("kanama-web"))
        }
        copy {
            from(webProxyResources)
            include("*.gd")
            into(stagingDir.resolve("kanama-web/generated"))
        }

        // Task 84: the benchmark harness now NAMES itself, exactly like every demo page does.
        // It used to be the bridge's fallback mode, which meant "the page said nothing" and "the
        // page asked for the synthetic transport benchmark" were the same state. The bridge's
        // fallback is a plain game now, so this line is what selects the benchmark.
        val shell = stagingDir.resolve("kanama-web/shell.html")
        val originalShell = shell.readText()
        val pageStart = "globalThis.KanamaWebPageStartedAt = performance.now();"
        check(originalShell.contains(pageStart)) { "Missing Web shell bootstrap marker" }
        shell.writeText(
            originalShell.replace(pageStart, "$pageStart\n      globalThis.KanamaWebMode = \"spike\";")
        )

        val manifest = webProxyResources.get().file("KanamaWebProxyManifest.generated.tsv").asFile
        check(manifest.isFile) { "Missing generated Web proxy manifest: $manifest" }
        val mappings =
            manifest
                .readLines()
                .filter { it.isNotBlank() && !it.startsWith("#") }
                .associate { line ->
                    val columns = line.split('\t')
                    check(columns.size == 3) { "Invalid Web proxy manifest row: $line" }
                    columns[0] to columns[1]
                }

        val stagedReferences =
            fileTree(stagingDir) {
                    include("**/*.tscn")
                    include("**/*.tres")
                }
                .files
        var rewrittenScenes = 0
        var rewrittenResources = 0
        stagedReferences.forEach { stagedFile ->
            val original = stagedFile.readText()
            val rewritten =
                mappings.entries.fold(original) { text, (sourcePath, proxyPath) ->
                    text.replace(sourcePath, proxyPath)
                }
            if (rewritten != original) {
                stagedFile.writeText(rewritten)
                when (stagedFile.extension) {
                    "tscn" -> rewrittenScenes += 1
                    "tres" -> rewrittenResources += 1
                }
            }
            check(!rewritten.contains(".kt\"")) {
                "Unmapped Kotlin attachment remains in staged file: $stagedFile"
            }
        }
        check(rewrittenScenes > 0) { "The Web spike did not rewrite a staged .tscn attachment" }
        check(rewrittenResources > 0) { "The Web spike did not rewrite a staged .tres attachment" }
    }
}

tasks.register<Exec>("exportWebSpike") {
    group = "verification"
    description = "Exports the staged Phase 0 Godot project and installs the Kotlin/Wasm bundle."
    dependsOn("stageWebSpikeGodotProject", "wasmJsBrowserDistribution")
    inputs.dir(webSpikeStaging)
    inputs.dir(webDistribution)
    outputs.dir(webSpikeExport)

    doFirst {
        val godotExecutable =
            providers.gradleProperty("kanamaGodotExecutable").orNull
                ?: error("Pass -PkanamaGodotExecutable=/absolute/path/to/godot")
        val webTemplateRelease =
            providers.gradleProperty("kanamaWebTemplateRelease").orNull
                ?: error("Pass -PkanamaWebTemplateRelease=/absolute/path/to/web_nothreads_release.zip")
        val webTemplateFile = file(webTemplateRelease)
        check(webTemplateFile.isFile) { "Godot Web release template not found: $webTemplateFile" }
        val stagedPreset = webSpikeStaging.get().file("export_presets.cfg").asFile
        stagedPreset.writeText(
            stagedPreset
                .readText()
                .replace(
                    "custom_template/release=\"\"",
                    "custom_template/release=\"${webTemplateFile.absolutePath}\"",
                )
        )
        val exportDir = webSpikeExport.get().asFile
        delete(exportDir)
        exportDir.mkdirs()
        commandLine(
            godotExecutable,
            "--headless",
            "--path",
            webSpikeStaging.get().asFile.absolutePath,
            "--export-release",
            "Web",
            exportDir.resolve("index.html").absolutePath,
        )
    }

    doLast {
        val exportDir = webSpikeExport.get().asFile
        copy {
            from(webDistribution)
            include("*.js", "*.wasm")
            into(exportDir)
        }
        copy {
            from(webSpikeAssets.file("kanama-web-bridge.js"))
            into(exportDir)
        }
        check(exportDir.resolve("index.html").isFile) { "Godot Web export did not produce index.html" }
        check(exportDir.resolve("kanama-web-spike.js").isFile) {
            "Kotlin/Wasm loader was not installed into the Web export"
        }
    }
}

tasks.register("stageWebBunnymarkProject") {
    group = "verification"
    description = "Stages a real Bunnymark variant with its generated Web proxy or GDScript baseline."
    dependsOn("kspKotlinWasmJs")
    webBunnymarkSourceProject?.let(inputs::dir)
    inputs.dir(webSpikeAssets)
    inputs.dir(webProxyResources)
    inputs.property("kanamaWebBunnymarkVariant", webBunnymarkVariant)
    inputs.property("kanamaWebBunnymarkLanguage", webBunnymarkLanguage)
    outputs.dir(webBunnymarkStaging)

    doLast {
        val sourceProject =
            webBunnymarkSourceProject
                ?: error(
                    "Pass -PkanamaWebBunnymarkProjectDir=/absolute/path/to/kanama-demos/Bunnymark"
                )
        check(sourceProject.resolve("Benchmarker.tscn").isFile) {
            "Bunnymark project not found: $sourceProject"
        }
        val variant = webBunnymarkVariant.get()
        val language = webBunnymarkLanguage.get()
        check(variant in setOf("BunnymarkV1DrawTexture", "BunnymarkV1Sprites")) {
            "Unsupported -PkanamaWebBunnymarkVariant=$variant"
        }
        check(language in setOf("kanama", "gd")) {
            "Unsupported -PkanamaWebBunnymarkLanguage=$language"
        }

        val stagingDir = webBunnymarkStaging.get().asFile
        delete(stagingDir)
        copy {
            from(sourceProject) {
                include("Benchmarker.tscn")
                include("project.godot")
                include("images/godot_bunny.png")
                include("scripts/Benchmarker.gd")
                if (language == "gd") include("benchmarks/$variant/gd/$variant.gd")
            }
            into(stagingDir)
        }
        copy {
            from(webSpikeSourceProject.file("export_presets.cfg"))
            into(stagingDir)
        }
        copy {
            from(webSpikeAssets)
            into(stagingDir.resolve("kanama-web"))
        }
        copy {
            from(webProxyResources)
            include("*.gd")
            into(stagingDir.resolve("kanama-web/generated"))
        }

        val manifest = webProxyResources.get().file("KanamaWebProxyManifest.generated.tsv").asFile
        check(manifest.isFile) { "Missing generated Web proxy manifest: $manifest" }
        val sourcePath = "res://kotlin-src/${variant}Kanama.kt"
        val proxyPath =
            manifest
                .readLines()
                .filter { it.isNotBlank() && !it.startsWith("#") }
                .map { it.split('\t') }
                .firstOrNull { it.size == 3 && it[0] == sourcePath }
                ?.get(1)
                ?: error("Missing Bunnymark Web proxy mapping for $sourcePath")

        val benchmarker = stagingDir.resolve("scripts/Benchmarker.gd")
        val originalBenchmarker = benchmarker.readText()
        val defaultBenchmark =
            "var benchmark: String = \"BunnymarkV2\""
        val dynamicKotlinScriptPath =
            "return \"res://kotlin-src/\" + benchmark_name + \"Kanama.kt\""
        val defaultLanguage =
            "var language: String = \"kanama\""
        val adaptiveHarnessStart =
            "if benchmark_node.has_method(\"add_bunny\"):\n        set_process(true)"
        check(originalBenchmarker.contains(defaultBenchmark)) {
            "Bunnymark harness default changed; update the Web staging transform"
        }
        check(originalBenchmarker.contains(dynamicKotlinScriptPath)) {
            "Bunnymark Kotlin script lookup changed; update the Web staging transform"
        }
        check(originalBenchmarker.contains(defaultLanguage)) {
            "Bunnymark harness language default changed; update the Web staging transform"
        }
        check(originalBenchmarker.contains(adaptiveHarnessStart)) {
            "Bunnymark adaptive harness startup changed; update the Web staging transform"
        }
        val stagedBenchmarker =
            originalBenchmarker
                .replace(defaultBenchmark, "var benchmark: String = \"$variant\"")
                .replace(defaultLanguage, "var language: String = \"$language\"")
                .let { staged ->
                    if (language == "kanama") {
                        staged.replace(dynamicKotlinScriptPath, "return \"$proxyPath\"")
                    } else {
                        staged
                    }
                }
                .replace(adaptiveHarnessStart, "if benchmark_node.has_method(\"add_bunny\"):\n        set_process(false)")
        check(stagedBenchmarker != originalBenchmarker) {
            "Bunnymark staging did not select the requested Web variant"
        }
        check(language != "kanama" || !stagedBenchmarker.contains("res://kotlin-src/")) {
            "A Kotlin script path remains in staged Bunnymark Benchmarker.gd"
        }
        benchmarker.writeText(stagedBenchmarker)

        if (language == "gd") {
            check(variant == "BunnymarkV1Sprites") {
                "The GDScript scaling baseline is only defined for BunnymarkV1Sprites"
            }
            val gdscript =
                stagingDir.resolve("benchmarks/$variant/gd/$variant.gd")
            val originalGdscript = gdscript.readText()
            val processStart = "func _process(delta):\n"
            val processEnd = "\t\tbunny[1] = speed\n\nfunc add_bunny():"
            check(originalGdscript.contains(processStart) && originalGdscript.contains(processEnd)) {
                "BunnymarkV1Sprites GDScript changed; update the Web baseline instrumentation"
            }
            val baselineLifecycle =
                """

                var _kanama_web_baseline_bridge
                var _kanama_web_baseline_callback

                func _ready() -> void:
	                if not OS.has_feature("web"):
		                return
	                _kanama_web_baseline_bridge = JavaScriptBridge.get_interface("KanamaWebBridge")
	                _kanama_web_baseline_callback = JavaScriptBridge.create_callback(_kanama_web_baseline_call)
	                _kanama_web_baseline_bridge.installGdscriptBaselineCallback(_kanama_web_baseline_callback)
	                _kanama_web_baseline_bridge.recordGdscriptBaselineReady()

                func _exit_tree() -> void:
	                if _kanama_web_baseline_bridge != null:
		                _kanama_web_baseline_bridge.clearGdscriptBaselineCallback()
	                _kanama_web_baseline_callback = null

                func _kanama_web_baseline_call(args: Array) -> void:
	                match String(args[0]):
		                "add": add_bunny()
		                "remove": remove_bunny()
		                "finish": finish()

                """.trimIndent()
            gdscript.writeText(
                originalGdscript
                    .replace("extends Node2D\n", "extends Node2D\n$baselineLifecycle\n")
                    .replace(
                        processStart,
                        "${processStart}\tvar _kanama_web_baseline_started_usec := Time.get_ticks_usec()\n",
                    )
                    .replace(
                        processEnd,
                        "\t\tbunny[1] = speed\n\n\tif _kanama_web_baseline_bridge != null:\n\t\t_kanama_web_baseline_bridge.recordGdscriptBaselineFrame(float(Time.get_ticks_usec() - _kanama_web_baseline_started_usec) / 1000.0)\n\nfunc add_bunny():",
                    )
            )
        }

        val shell = stagingDir.resolve("kanama-web/shell.html")
        val originalShell = shell.readText()
        val pageStart = "globalThis.KanamaWebPageStartedAt = performance.now();"
        check(originalShell.contains(pageStart)) { "Missing Web shell bootstrap marker" }
        shell.writeText(
            originalShell.replace(
                pageStart,
                "$pageStart\n      globalThis.KanamaWebMode = \"bunnymark\";\n      globalThis.KanamaWebBunnymarkVariant = \"$variant\";\n      globalThis.KanamaWebBunnymarkLanguage = \"$language\";",
            )
        )
    }
}

tasks.register<Exec>("exportWebBunnymark") {
    group = "verification"
    description = "Exports the selected real Kotlin/Wasm Bunnymark or GDScript baseline."
    dependsOn("stageWebBunnymarkProject", "wasmJsBrowserDistribution")
    inputs.dir(webBunnymarkStaging)
    inputs.dir(webDistribution)
    outputs.dir(webBunnymarkExport)

    doFirst {
        val godotExecutable =
            providers.gradleProperty("kanamaGodotExecutable").orNull
                ?: error("Pass -PkanamaGodotExecutable=/absolute/path/to/godot")
        val webTemplateRelease =
            providers.gradleProperty("kanamaWebTemplateRelease").orNull
                ?: error("Pass -PkanamaWebTemplateRelease=/absolute/path/to/web_nothreads_release.zip")
        val webTemplateFile = file(webTemplateRelease)
        check(webTemplateFile.isFile) { "Godot Web release template not found: $webTemplateFile" }
        val stagedPreset = webBunnymarkStaging.get().file("export_presets.cfg").asFile
        stagedPreset.writeText(
            stagedPreset
                .readText()
                .replace(
                    "custom_template/release=\"\"",
                    "custom_template/release=\"${webTemplateFile.absolutePath}\"",
                )
        )
        val exportDir = webBunnymarkExport.get().asFile
        delete(exportDir)
        exportDir.mkdirs()
        commandLine(
            godotExecutable,
            "--headless",
            "--path",
            webBunnymarkStaging.get().asFile.absolutePath,
            "--export-release",
            "Web",
            exportDir.resolve("index.html").absolutePath,
        )
    }

    doLast {
        val exportDir = webBunnymarkExport.get().asFile
        copy {
            from(webDistribution)
            include("*.js", "*.wasm")
            into(exportDir)
        }
        copy {
            from(webSpikeAssets.file("kanama-web-bridge.js"))
            into(exportDir)
        }
        check(exportDir.resolve("index.html").isFile) {
            "Godot Web Bunnymark export did not produce index.html"
        }
        check(exportDir.resolve("kanama-web-spike.js").isFile) {
            "Kotlin/Wasm loader was not installed into the Bunnymark export"
        }
    }
}

tasks.register("stageWebMatch3Project") {
    group = "verification"
    description = "Stages Match3 with faithful generated Web proxies without editing its source."
    dependsOn("kspKotlinWasmJs", "generateWebGameplayCoverage")
    webMatch3SourceProject?.let(inputs::dir)
    inputs.dir(webSpikeAssets)
    inputs.dir(webProxyResources)
    inputs.file(webGameplayCoverage)
    outputs.dir(webMatch3Staging)

    doLast {
        val sourceProject =
            webMatch3SourceProject
                ?: error(
                    "Pass -PkanamaWebMatch3ProjectDir=/absolute/path/to/kanama-demos/Starter-Kit-Match3"
                )
        check(sourceProject.resolve("scenes/main.tscn").isFile) {
            "Match3 project not found: $sourceProject"
        }

        fun sourceChecksum(): String {
            val excludedRoots =
                setOf(".git", ".godot", ".gradle", ".kotlin", "build", "android/build")
            val digest = MessageDigest.getInstance("SHA-256")
            sourceProject
                .walkTopDown()
                .filter { it.isFile }
                .map { file -> file.relativeTo(sourceProject).invariantSeparatorsPath to file }
                .filter { (path, _) ->
                    excludedRoots.none { root -> path == root || path.startsWith("$root/") }
                }
                .sortedBy { it.first }
                .forEach { (path, file) ->
                    digest.update(path.toByteArray(Charsets.UTF_8))
                    digest.update(0)
                    digest.update(file.readBytes())
                    digest.update(0)
                }
            return digest.digest().joinToString("") { "%02x".format(it) }
        }

        val checksumBefore = sourceChecksum()
        val stagingDir = webMatch3Staging.get().asFile
        delete(stagingDir)
        copy {
            from(sourceProject) {
                exclude(".git/**")
                exclude(".godot/**")
                exclude(".gradle/**")
                exclude(".kotlin/**")
                exclude("build/**")
                exclude("android/**")
                exclude("addons/kanama/**")
                exclude("gradle/**")
                exclude("kotlin-src/**")
                exclude("build.gradle.kts")
                exclude("gradle.properties")
                exclude("gradlew")
                exclude("gradlew.bat")
                exclude("settings.gradle.kts")
            }
            into(stagingDir)
        }
        copy {
            from(webProxyResources)
            include("*.gd")
            into(stagingDir.resolve("kanama-web/generated"))
        }
        copy {
            from(webProxyResources)
            include("KanamaWebProxyManifest.generated.tsv")
            include("KanamaWebProtocol.generated.json")
            into(stagingDir.resolve("kanama-web"))
        }
        copy {
            from(webSpikeAssets)
            into(stagingDir.resolve("kanama-web"))
        }
        copy {
            from(webSpikeSourceProject.file("export_presets.cfg"))
            into(stagingDir)
        }
        copy {
            from(webGameplayCoverage)
            into(stagingDir.resolve("kanama-web"))
        }

        val manifest = webProxyResources.get().file("KanamaWebProxyManifest.generated.tsv").asFile
        check(manifest.isFile) { "Missing generated Web proxy manifest: $manifest" }
        val expectedSources =
            setOf(
                "res://kotlin-src/Audio.kt",
                "res://kotlin-src/Main.kt",
                "res://kotlin-src/Particles.kt",
                "res://kotlin-src/SmokeQuit.kt",
                "res://kotlin-src/Tile.kt",
            )
        val mappings =
            manifest
                .readLines()
                .filter { it.isNotBlank() && !it.startsWith("#") }
                .map { line ->
                    val columns = line.split('\t')
                    check(columns.size == 3) { "Invalid Web proxy manifest row: $line" }
                    columns[0] to columns[1]
                }
                .filter { (sourcePath, _) -> sourcePath in expectedSources }
                .toMap()
        check(mappings.keys == expectedSources) {
            "Match3 Web proxy mappings are incomplete: expected=$expectedSources actual=${mappings.keys}"
        }

        val stagedReferences =
            fileTree(stagingDir) {
                    include("project.godot")
                    include("**/*.tscn")
                    include("**/*.tres")
                }
                .files
        val usedMappings = mutableSetOf<String>()
        stagedReferences.forEach { stagedFile ->
            val original = stagedFile.readText()
            val rewritten =
                mappings.entries.fold(original) { text, (sourcePath, proxyPath) ->
                    if (text.contains(sourcePath)) usedMappings += sourcePath
                    text.replace(sourcePath, proxyPath)
                }
            if (rewritten != original) stagedFile.writeText(rewritten)
            check(!rewritten.contains("res://kotlin-src/")) {
                "Unmapped Kotlin attachment remains in staged file: $stagedFile"
            }
        }
        check(usedMappings == expectedSources) {
            "Not every Match3 script attachment was staged: used=$usedMappings"
        }

        val stagedProject = stagingDir.resolve("project.godot")
        val stagedProjectText = stagedProject.readText()
        val forwardFeature = "config/features=PackedStringArray(\"4.7\", \"Forward Plus\")"
        val mobileRenderer = "renderer/rendering_method.mobile=\"gl_compatibility\""
        check(stagedProjectText.contains(forwardFeature)) {
            "Match3 renderer feature changed; update the Web staging transform"
        }
        check(stagedProjectText.contains(mobileRenderer)) {
            "Match3 mobile renderer setting changed; update the Web staging transform"
        }
        stagedProject.writeText(
            stagedProjectText
                .replace(
                    forwardFeature,
                    "config/features=PackedStringArray(\"4.7\", \"GL Compatibility\")",
                )
                .replace(
                    mobileRenderer,
                    "renderer/rendering_method=\"gl_compatibility\"\n$mobileRenderer",
                )
        )

        val checksumAfter = sourceChecksum()
        check(checksumAfter == checksumBefore) {
            "Match3 source project changed during staging: before=$checksumBefore after=$checksumAfter"
        }
        val evidence = stagingDir.resolve("kanama-web/Match3SourceChecksum.generated.txt")
        evidence.parentFile.mkdirs()
        evidence.writeText("sha256=$checksumAfter\nstatus=unchanged\n")

        val shell = stagingDir.resolve("kanama-web/shell.html")
        val originalShell = shell.readText()
        val pageStart = "globalThis.KanamaWebPageStartedAt = performance.now();"
        check(originalShell.contains(pageStart)) { "Missing Web shell bootstrap marker" }
        shell.writeText(
            originalShell.replace(
                pageStart,
                "$pageStart\n      globalThis.KanamaWebMode = \"match3\";",
            )
        )
    }
}

tasks.register("stageWebDodgeProject") {
    group = "verification"
    description = "Stages dodge-the-creeps with faithful generated Web proxies without editing its source."
    dependsOn("kspKotlinWasmJs", "generateWebGameplayCoverage")
    webDodgeSourceProject?.let(inputs::dir)
    inputs.dir(webSpikeAssets)
    inputs.dir(webProxyResources)
    inputs.file(webGameplayCoverage)
    outputs.dir(webDodgeStaging)

    doLast {
        val sourceProject =
            webDodgeSourceProject
                ?: error(
                    "Pass -PkanamaWebDodgeProjectDir=/absolute/path/to/kanama-demos/godot-demo-2d-dodge-the-creeps"
                )
        check(sourceProject.resolve("main.tscn").isFile) {
            "dodge-the-creeps project not found: $sourceProject"
        }

        fun sourceChecksum(): String {
            val excludedRoots = setOf(".git", ".godot", ".gradle", ".kotlin", "build", "android/build")
            val digest = MessageDigest.getInstance("SHA-256")
            sourceProject
                .walkTopDown()
                .filter { it.isFile }
                .map { file -> file.relativeTo(sourceProject).invariantSeparatorsPath to file }
                .filter { (path, _) ->
                    excludedRoots.none { root -> path == root || path.startsWith("$root/") }
                }
                .sortedBy { it.first }
                .forEach { (path, file) ->
                    digest.update(path.toByteArray(Charsets.UTF_8))
                    digest.update(0)
                    digest.update(file.readBytes())
                    digest.update(0)
                }
            return digest.digest().joinToString("") { "%02x".format(it) }
        }

        val checksumBefore = sourceChecksum()
        val stagingDir = webDodgeStaging.get().asFile
        delete(stagingDir)
        copy {
            from(sourceProject) {
                exclude(".git/**")
                exclude(".godot/**")
                exclude(".gradle/**")
                exclude(".kotlin/**")
                exclude("build/**")
                exclude("android/**")
                exclude("addons/kanama/**")
                exclude("gradle/**")
                exclude("kotlin-src/**")
                exclude("build.gradle.kts")
                exclude("gradle.properties")
                exclude("gradlew")
                exclude("gradlew.bat")
                exclude("settings.gradle.kts")
            }
            into(stagingDir)
        }
        copy {
            from(webProxyResources)
            include("*.gd")
            into(stagingDir.resolve("kanama-web/generated"))
        }
        copy {
            from(webProxyResources)
            include("KanamaWebProxyManifest.generated.tsv")
            include("KanamaWebProtocol.generated.json")
            into(stagingDir.resolve("kanama-web"))
        }
        copy {
            from(webSpikeAssets)
            into(stagingDir.resolve("kanama-web"))
        }
        copy {
            from(webSpikeSourceProject.file("export_presets.cfg"))
            into(stagingDir)
        }
        copy {
            from(webGameplayCoverage)
            into(stagingDir.resolve("kanama-web"))
        }

        val manifest = webProxyResources.get().file("KanamaWebProxyManifest.generated.tsv").asFile
        check(manifest.isFile) { "Missing generated Web proxy manifest: $manifest" }
        val expectedSources =
            setOf(
                "res://kotlin-src/HUD.kt",
                "res://kotlin-src/Main.kt",
                "res://kotlin-src/Mob.kt",
                "res://kotlin-src/Player.kt",
                "res://kotlin-src/SmokeQuit.kt",
            )
        val mappings =
            manifest
                .readLines()
                .filter { it.isNotBlank() && !it.startsWith("#") }
                .map { line ->
                    val columns = line.split('\t')
                    check(columns.size == 3) { "Invalid Web proxy manifest row: $line" }
                    columns[0] to columns[1]
                }
                .filter { (sourcePath, _) -> sourcePath in expectedSources }
                .toMap()
        check(mappings.keys == expectedSources) {
            "dodge Web proxy mappings are incomplete: expected=$expectedSources actual=${mappings.keys}"
        }

        val stagedReferences =
            fileTree(stagingDir) {
                    include("project.godot")
                    include("**/*.tscn")
                    include("**/*.tres")
                }
                .files
        val usedMappings = mutableSetOf<String>()
        stagedReferences.forEach { stagedFile ->
            val original = stagedFile.readText()
            val rewritten =
                mappings.entries.fold(original) { text, (sourcePath, proxyPath) ->
                    if (text.contains(sourcePath)) usedMappings += sourcePath
                    text.replace(sourcePath, proxyPath)
                }
            if (rewritten != original) stagedFile.writeText(rewritten)
            check(!rewritten.contains("res://kotlin-src/")) {
                "Unmapped Kotlin attachment remains in staged file: $stagedFile"
            }
        }
        check(usedMappings == expectedSources) {
            "Not every dodge script attachment was staged: used=$usedMappings"
        }

        // dodge-the-creeps already ships the GL Compatibility renderer, so unlike
        // Match3 there is no Forward Plus -> GL Compatibility rewrite; just guard it.
        val stagedProject = stagingDir.resolve("project.godot")
        check(stagedProject.readText().contains("renderer/rendering_method=\"gl_compatibility\"")) {
            "dodge renderer is no longer gl_compatibility; the Web export needs it"
        }

        val checksumAfter = sourceChecksum()
        check(checksumAfter == checksumBefore) {
            "dodge source project changed during staging: before=$checksumBefore after=$checksumAfter"
        }
        val evidence = stagingDir.resolve("kanama-web/DodgeSourceChecksum.generated.txt")
        evidence.parentFile.mkdirs()
        evidence.writeText("sha256=$checksumAfter\nstatus=unchanged\n")

        val shell = stagingDir.resolve("kanama-web/shell.html")
        val originalShell = shell.readText()
        val pageStart = "globalThis.KanamaWebPageStartedAt = performance.now();"
        check(originalShell.contains(pageStart)) { "Missing Web shell bootstrap marker" }
        shell.writeText(
            originalShell.replace(
                pageStart,
                "$pageStart\n      globalThis.KanamaWebMode = \"dodge\";",
            )
        )
    }
}

tasks.register("stageWebWeb3dProject") {
    group = "verification"
    description = "Stages the minimal 3D render smoke with generated Web proxies (Task 60c)."
    dependsOn("kspKotlinWasmJs", "generateWebGameplayCoverage")
    inputs.dir(webWeb3dSourceProject)
    inputs.dir(webSpikeAssets)
    inputs.dir(webProxyResources)
    inputs.file(webGameplayCoverage)
    outputs.dir(webWeb3dStaging)

    doLast {
        val sourceProject = webWeb3dSourceProject
        check(sourceProject.resolve("main.tscn").isFile) {
            "web3d-smoke project not found: $sourceProject"
        }

        fun sourceChecksum(): String {
            val excludedRoots = setOf(".git", ".godot", ".gradle", ".kotlin", "build")
            val digest = MessageDigest.getInstance("SHA-256")
            sourceProject
                .walkTopDown()
                .filter { it.isFile }
                .map { file -> file.relativeTo(sourceProject).invariantSeparatorsPath to file }
                .filter { (path, _) ->
                    excludedRoots.none { root -> path == root || path.startsWith("$root/") }
                }
                .sortedBy { it.first }
                .forEach { (path, file) ->
                    digest.update(path.toByteArray(Charsets.UTF_8))
                    digest.update(0)
                    digest.update(file.readBytes())
                    digest.update(0)
                }
            return digest.digest().joinToString("") { "%02x".format(it) }
        }

        val checksumBefore = sourceChecksum()
        val stagingDir = webWeb3dStaging.get().asFile
        delete(stagingDir)
        copy {
            from(sourceProject) {
                exclude(".git/**")
                exclude(".godot/**")
                exclude(".gradle/**")
                exclude(".kotlin/**")
                exclude("build/**")
                exclude("web/**")
            }
            into(stagingDir)
        }
        copy {
            from(webProxyResources)
            include("*.gd")
            into(stagingDir.resolve("kanama-web/generated"))
        }
        copy {
            from(webProxyResources)
            include("KanamaWebProxyManifest.generated.tsv")
            include("KanamaWebProtocol.generated.json")
            into(stagingDir.resolve("kanama-web"))
        }
        copy {
            from(webSpikeAssets)
            into(stagingDir.resolve("kanama-web"))
        }
        copy {
            from(webGameplayCoverage)
            into(stagingDir.resolve("kanama-web"))
        }

        val manifest = webProxyResources.get().file("KanamaWebProxyManifest.generated.tsv").asFile
        check(manifest.isFile) { "Missing generated Web proxy manifest: $manifest" }
        val expectedSources =
            setOf(
                "res://kotlin-src/Main.kt",
                "res://kotlin-src/SmokeQuit.kt",
                "res://kotlin-src/Player.kt",
                "res://kotlin-src/Coin.kt",
            )
        val mappings =
            manifest
                .readLines()
                .filter { it.isNotBlank() && !it.startsWith("#") }
                .map { line ->
                    val columns = line.split('\t')
                    check(columns.size == 3) { "Invalid Web proxy manifest row: $line" }
                    columns[0] to columns[1]
                }
                .filter { (sourcePath, _) -> sourcePath in expectedSources }
                .toMap()
        check(mappings.keys == expectedSources) {
            "web3d Web proxy mappings are incomplete: expected=$expectedSources actual=${mappings.keys}"
        }

        val stagedReferences =
            fileTree(stagingDir) {
                    include("project.godot")
                    include("**/*.tscn")
                    include("**/*.tres")
                }
                .files
        val usedMappings = mutableSetOf<String>()
        stagedReferences.forEach { stagedFile ->
            val original = stagedFile.readText()
            val rewritten =
                mappings.entries.fold(original) { text, (sourcePath, proxyPath) ->
                    if (text.contains(sourcePath)) usedMappings += sourcePath
                    text.replace(sourcePath, proxyPath)
                }
            if (rewritten != original) stagedFile.writeText(rewritten)
            check(!rewritten.contains("res://kotlin-src/")) {
                "Unmapped Kotlin attachment remains in staged file: $stagedFile"
            }
        }
        check(usedMappings == expectedSources) {
            "Not every web3d script attachment was staged: used=$usedMappings"
        }

        val stagedProject = stagingDir.resolve("project.godot")
        check(stagedProject.readText().contains("renderer/rendering_method=\"gl_compatibility\"")) {
            "web3d renderer is no longer gl_compatibility; the Web export needs it"
        }

        val checksumAfter = sourceChecksum()
        check(checksumAfter == checksumBefore) {
            "web3d source project changed during staging: before=$checksumBefore after=$checksumAfter"
        }

        val shell = stagingDir.resolve("kanama-web/shell.html")
        val originalShell = shell.readText()
        val pageStart = "globalThis.KanamaWebPageStartedAt = performance.now();"
        check(originalShell.contains(pageStart)) { "Missing Web shell bootstrap marker" }
        shell.writeText(
            originalShell.replace(
                pageStart,
                "$pageStart\n      globalThis.KanamaWebMode = \"web3d\";",
            )
        )
    }
}

tasks.register("stageWebPlatformerProject") {
    group = "verification"
    description = "Stages the Starter-Kit-3D-Platformer with generated Web proxies (Task 60c/60d)."
    dependsOn("kspKotlinWasmJs", "generateWebGameplayCoverage")
    webPlatformerSourceProject?.let(inputs::dir)
    inputs.dir(webSpikeAssets)
    inputs.dir(webProxyResources)
    inputs.file(webGameplayCoverage)
    outputs.dir(webPlatformerStaging)

    doLast {
        val sourceProject =
            webPlatformerSourceProject
                ?: error(
                    "Pass -PkanamaWebPlatformerProjectDir=/absolute/path/to/kanama-demos/Starter-Kit-3D-Platformer"
                )
        check(sourceProject.resolve("scenes/main.tscn").isFile) {
            "platformer project not found: $sourceProject"
        }

        val stagingDir = webPlatformerStaging.get().asFile
        delete(stagingDir)
        copy {
            from(sourceProject) {
                exclude(".git/**")
                exclude(".godot/**")
                exclude(".gradle/**")
                exclude(".kotlin/**")
                exclude("build/**")
                exclude("web/**")
                exclude("kotlin-src/**")
                exclude("addons/kanama_tools/**")
                exclude("addons/kanama/**")
                exclude("export_presets.cfg")
            }
            into(stagingDir)
        }
        // The demo ships Android/iOS presets; the Web export needs the canonical Web preset.
        copy {
            from(webSpikeSourceProject.file("export_presets.cfg"))
            into(stagingDir)
        }
        copy {
            from(webProxyResources)
            include("*.gd")
            into(stagingDir.resolve("kanama-web/generated"))
        }
        copy {
            from(webProxyResources)
            include("KanamaWebProxyManifest.generated.tsv")
            include("KanamaWebProtocol.generated.json")
            into(stagingDir.resolve("kanama-web"))
        }
        copy {
            from(webSpikeAssets)
            into(stagingDir.resolve("kanama-web"))
        }
        copy {
            from(webGameplayCoverage)
            into(stagingDir.resolve("kanama-web"))
        }

        val manifest = webProxyResources.get().file("KanamaWebProxyManifest.generated.tsv").asFile
        val expectedSources =
            setOf(
                    "Main",
                    "View",
                    "Hud",
                    "SmokeQuit",
                    "Player",
                    "Brick",
                    "Cloud",
                    "Coin",
                    "PlatformFalling",
                )
                .map { "res://kotlin-src/$it.kt" }
                .toSet()
        val mappings =
            manifest
                .readLines()
                .filter { it.isNotBlank() && !it.startsWith("#") }
                .map { it.split('\t').let { c -> c[0] to c[1] } }
                .filter { (sourcePath, _) -> sourcePath in expectedSources }
                .toMap()
        check(mappings.keys == expectedSources) {
            "platformer Web proxy mappings incomplete: missing=${expectedSources - mappings.keys}"
        }

        fileTree(stagingDir) {
                include("project.godot")
                include("**/*.tscn")
                include("**/*.tres")
            }
            .files
            .forEach { stagedFile ->
                val original = stagedFile.readText()
                var rewritten =
                    mappings.entries.fold(original) { text, (sourcePath, proxyPath) ->
                        text.replace(sourcePath, proxyPath)
                    }
                // Godot resolves Script ext_resources by UID before path; a rewritten reference
                // whose original .gd (and its UID) still exists in the staged tree would silently
                // load the ORIGINAL script instead of the generated proxy. Strip the UID from
                // rewritten references so the text path wins.
                rewritten =
                    rewritten.replace(
                        Regex(
                            "(\\[ext_resource type=\"Script\") uid=\"uid://[^\"]*\" " +
                                "(path=\"res://kanama-web/generated/)"
                        ),
                        "$1 $2",
                    )
                if (rewritten != original) stagedFile.writeText(rewritten)
                check(!rewritten.contains("res://kotlin-src/")) {
                    "Unmapped Kotlin attachment remains in staged file: $stagedFile"
                }
            }

        // The demo targets Forward+ (with a mobile gl_compatibility override); the Web
        // template only runs the Compatibility renderer, so pin it explicitly and turn off
        // screen-space AA -- Compatibility rejects it with a console error on every run,
        // which would trip the smoke's zero-console-errors gate (match3 precedent above).
        val stagedProject = stagingDir.resolve("project.godot")
        val stagedProjectText = stagedProject.readText()
        val forwardFeature = "config/features=PackedStringArray(\"4.7\", \"Forward Plus\")"
        val mobileRenderer = "renderer/rendering_method.mobile=\"gl_compatibility\""
        val screenSpaceAa = "anti_aliasing/quality/screen_space_aa=1"
        check(stagedProjectText.contains(forwardFeature)) {
            "platformer renderer feature changed; update the Web staging transform"
        }
        check(stagedProjectText.contains(mobileRenderer)) {
            "platformer mobile renderer setting changed; update the Web staging transform"
        }
        check(stagedProjectText.contains(screenSpaceAa)) {
            "platformer screen-space AA setting changed; update the Web staging transform"
        }
        stagedProject.writeText(
            stagedProjectText
                .replace(
                    forwardFeature,
                    "config/features=PackedStringArray(\"4.7\", \"GL Compatibility\")",
                )
                .replace(
                    mobileRenderer,
                    "renderer/rendering_method=\"gl_compatibility\"\n$mobileRenderer",
                )
                .replace(screenSpaceAa, "anti_aliasing/quality/screen_space_aa=0")
        )

        val shell = stagingDir.resolve("kanama-web/shell.html")
        val pageStart = "globalThis.KanamaWebPageStartedAt = performance.now();"
        shell.writeText(
            shell.readText()
                .replace(pageStart, "$pageStart\n      globalThis.KanamaWebMode = \"platformer\";")
        )
    }
}

tasks.register("stageWebSquashProject") {
    group = "verification"
    description = "Stages squash-the-creeps with generated Web proxies (Task 60d)."
    dependsOn("kspKotlinWasmJs", "generateWebGameplayCoverage")
    webSquashSourceProject?.let(inputs::dir)
    inputs.dir(webSpikeAssets)
    inputs.dir(webProxyResources)
    inputs.file(webGameplayCoverage)
    outputs.dir(webSquashStaging)

    doLast {
        val sourceProject =
            webSquashSourceProject
                ?: error(
                    "Pass -PkanamaWebSquashProjectDir=/absolute/path/to/kanama-demos/godot-demo-3d-squash-the-creeps"
                )
        check(sourceProject.resolve("Main.tscn").isFile) {
            "squash project not found: $sourceProject"
        }

        val stagingDir = webSquashStaging.get().asFile
        delete(stagingDir)
        copy {
            from(sourceProject) {
                exclude(".git/**")
                exclude(".godot/**")
                exclude(".gradle/**")
                exclude(".kotlin/**")
                exclude("build/**")
                exclude("web/**")
                exclude("kotlin-src/**")
                exclude("android/**")
                exclude("screenshots/**")
                exclude("addons/kanama_tools/**")
                exclude("addons/kanama/**")
                exclude("export_presets.cfg")
            }
            into(stagingDir)
        }
        // The demo ships Android presets; the Web export needs the canonical Web preset.
        copy {
            from(webSpikeSourceProject.file("export_presets.cfg"))
            into(stagingDir)
        }
        copy {
            from(webProxyResources)
            include("*.gd")
            into(stagingDir.resolve("kanama-web/generated"))
        }
        copy {
            from(webProxyResources)
            include("KanamaWebProxyManifest.generated.tsv")
            include("KanamaWebProtocol.generated.json")
            into(stagingDir.resolve("kanama-web"))
        }
        copy {
            from(webSpikeAssets)
            into(stagingDir.resolve("kanama-web"))
        }
        copy {
            from(webGameplayCoverage)
            into(stagingDir.resolve("kanama-web"))
        }

        val manifest = webProxyResources.get().file("KanamaWebProxyManifest.generated.tsv").asFile
        val expectedSources =
            setOf("Main", "Mob", "Player", "ScoreLabel", "SmokeQuit")
                .map { "res://kotlin-src/$it.kt" }
                .toSet()
        val mappings =
            manifest
                .readLines()
                .filter { it.isNotBlank() && !it.startsWith("#") }
                .map { it.split('\t').let { c -> c[0] to c[1] } }
                .filter { (sourcePath, _) -> sourcePath in expectedSources }
                .toMap()
        check(mappings.keys == expectedSources) {
            "squash Web proxy mappings incomplete: missing=${expectedSources - mappings.keys}"
        }

        fileTree(stagingDir) {
                include("project.godot")
                include("**/*.tscn")
                include("**/*.tres")
            }
            .files
            .forEach { stagedFile ->
                val original = stagedFile.readText()
                var rewritten =
                    mappings.entries.fold(original) { text, (sourcePath, proxyPath) ->
                        text.replace(sourcePath, proxyPath)
                    }
                // Godot resolves Script ext_resources by UID before path; strip the UID from
                // rewritten references so the text path wins (platformer precedent).
                rewritten =
                    rewritten.replace(
                        Regex(
                            "(\\[ext_resource type=\"Script\") uid=\"uid://[^\"]*\" " +
                                "(path=\"res://kanama-web/generated/)"
                        ),
                        "$1 $2",
                    )
                if (rewritten != original) stagedFile.writeText(rewritten)
                check(!rewritten.contains("res://kotlin-src/")) {
                    "Unmapped Kotlin attachment remains in staged file: $stagedFile"
                }
            }

        // The demo has no base rendering method (Forward+ default); the Web template only runs
        // the Compatibility renderer, so pin it explicitly (match3/platformer precedent).
        val stagedProject = stagingDir.resolve("project.godot")
        val stagedProjectText = stagedProject.readText()
        val mobileRenderer = "renderer/rendering_method.mobile=\"gl_compatibility\""
        check(stagedProjectText.contains(mobileRenderer)) {
            "squash mobile renderer setting changed; update the Web staging transform"
        }
        stagedProject.writeText(
            stagedProjectText.replace(
                mobileRenderer,
                "renderer/rendering_method=\"gl_compatibility\"\n$mobileRenderer",
            )
        )

        val shell = stagingDir.resolve("kanama-web/shell.html")
        val pageStart = "globalThis.KanamaWebPageStartedAt = performance.now();"
        shell.writeText(
            shell.readText()
                .replace(pageStart, "$pageStart\n      globalThis.KanamaWebMode = \"squash\";")
        )
    }
}

tasks.register("stageWebFpsProject") {
    group = "verification"
    description = "Stages the Starter-Kit-FPS with generated Web proxies (Task 60e)."
    dependsOn("kspKotlinWasmJs", "generateWebGameplayCoverage")
    webFpsSourceProject?.let(inputs::dir)
    inputs.dir(webSpikeAssets)
    inputs.dir(webProxyResources)
    inputs.file(webGameplayCoverage)
    outputs.dir(webFpsStaging)

    doLast {
        val sourceProject =
            webFpsSourceProject
                ?: error(
                    "Pass -PkanamaWebFpsProjectDir=/absolute/path/to/kanama-demos/Starter-Kit-FPS"
                )
        check(sourceProject.resolve("scenes/main.tscn").isFile) {
            "FPS project not found: $sourceProject"
        }

        val stagingDir = webFpsStaging.get().asFile
        delete(stagingDir)
        copy {
            from(sourceProject) {
                exclude(".git/**")
                exclude(".godot/**")
                exclude(".gradle/**")
                exclude(".kotlin/**")
                exclude("build/**")
                exclude("web/**")
                exclude("kotlin-src/**")
                exclude("android/**")
                exclude("screenshots/**")
                exclude("addons/kanama_tools/**")
                exclude("addons/kanama/**")
                exclude("export_presets.cfg")
            }
            into(stagingDir)
        }
        // The demo ships Android/iOS presets; the Web export needs the canonical Web preset.
        copy {
            from(webSpikeSourceProject.file("export_presets.cfg"))
            into(stagingDir)
        }
        copy {
            from(webProxyResources)
            include("*.gd")
            into(stagingDir.resolve("kanama-web/generated"))
        }
        copy {
            from(webProxyResources)
            include("KanamaWebProxyManifest.generated.tsv")
            include("KanamaWebProtocol.generated.json")
            into(stagingDir.resolve("kanama-web"))
        }
        copy {
            from(webSpikeAssets)
            into(stagingDir.resolve("kanama-web"))
        }
        copy {
            from(webGameplayCoverage)
            into(stagingDir.resolve("kanama-web"))
        }

        val manifest = webProxyResources.get().file("KanamaWebProxyManifest.generated.tsv").asFile
        val expectedSources =
            setOf("Audio", "Cloud", "Enemy", "Hud", "Impact", "Player", "Smoke", "Weapon")
                .map { "res://kotlin-src/$it.kt" }
                .toSet()
        val mappings =
            manifest
                .readLines()
                .filter { it.isNotBlank() && !it.startsWith("#") }
                .map { it.split('	').let { c -> c[0] to c[1] } }
                .filter { (sourcePath, _) -> sourcePath in expectedSources }
                .toMap()
        check(mappings.keys == expectedSources) {
            "FPS Web proxy mappings incomplete: missing=${expectedSources - mappings.keys}"
        }

        fileTree(stagingDir) {
                include("project.godot")
                include("**/*.tscn")
                include("**/*.tres")
            }
            .files
            .forEach { stagedFile ->
                val original = stagedFile.readText()
                var rewritten =
                    mappings.entries.fold(original) { text, (sourcePath, proxyPath) ->
                        text.replace(sourcePath, proxyPath)
                    }
                // Godot resolves Script ext_resources by UID before path; strip the UID from
                // rewritten references so the text path wins (platformer precedent).
                rewritten =
                    rewritten.replace(
                        Regex(
                            "(\\[ext_resource type=\"Script\") uid=\"uid://[^\"]*\" " +
                                "(path=\"res://kanama-web/generated/)"
                        ),
                        "$1 $2",
                    )
                if (rewritten != original) stagedFile.writeText(rewritten)
                check(!rewritten.contains("res://kotlin-src/")) {
                    "Unmapped Kotlin attachment remains in staged file: $stagedFile"
                }
            }

        // The demo targets Forward+ (with a mobile gl_compatibility override); the Web
        // template only runs the Compatibility renderer, so pin it explicitly and turn off
        // screen-space AA (match3/platformer/squash precedent).
        val stagedProject = stagingDir.resolve("project.godot")
        val stagedProjectText = stagedProject.readText()
        val forwardFeature = "config/features=PackedStringArray(\"4.7\", \"Forward Plus\")"
        val mobileRenderer = "renderer/rendering_method.mobile=\"gl_compatibility\""
        val screenSpaceAa = "anti_aliasing/quality/screen_space_aa=1"
        check(stagedProjectText.contains(forwardFeature)) {
            "FPS renderer feature changed; update the Web staging transform"
        }
        check(stagedProjectText.contains(mobileRenderer)) {
            "FPS mobile renderer setting changed; update the Web staging transform"
        }
        check(stagedProjectText.contains(screenSpaceAa)) {
            "FPS screen-space AA setting changed; update the Web staging transform"
        }
        stagedProject.writeText(
            stagedProjectText
                .replace(
                    forwardFeature,
                    "config/features=PackedStringArray(\"4.7\", \"GL Compatibility\")",
                )
                .replace(
                    mobileRenderer,
                    "renderer/rendering_method=\"gl_compatibility\"\n$mobileRenderer",
                )
                .replace(screenSpaceAa, "anti_aliasing/quality/screen_space_aa=0")
        )

        val shell = stagingDir.resolve("kanama-web/shell.html")
        val pageStart = "globalThis.KanamaWebPageStartedAt = performance.now();"
        shell.writeText(
            shell.readText()
                .replace(pageStart, "$pageStart\n      globalThis.KanamaWebMode = \"fps\";")
        )
    }
}

tasks.register<Exec>("importWebMatch3Project") {
    group = "verification"
    description = "Runs a headless Godot import of the disposable Match3 Web-proxy project."
    dependsOn("stageWebMatch3Project")
    inputs.dir(webMatch3Staging)
    outputs.file(webMatch3ImportLog)
    standardOutput = webMatch3ImportOutput
    errorOutput = webMatch3ImportOutput

    doFirst {
        webMatch3ImportOutput.reset()
        val godotExecutable =
            providers.gradleProperty("kanamaGodotExecutable").orNull
                ?: error("Pass -PkanamaGodotExecutable=/absolute/path/to/godot")
        commandLine(
            godotExecutable,
            "--headless",
            "--import",
            "--path",
            webMatch3Staging.get().asFile.absolutePath,
        )
    }

    doLast {
        val output = webMatch3ImportOutput.toString(Charsets.UTF_8)
        val logFile = webMatch3ImportLog.get().asFile
        logFile.parentFile.mkdirs()
        logFile.writeText(output)
        val failureMarkers =
            listOf(
                "SCRIPT ERROR:",
                "ERROR:",
                "Parse Error:",
                "Failed to load script",
                "Invalid assignment",
                "Invalid call",
                "Method not found",
                "Node not found",
                "nonexistent function",
            )
        val failures =
            output.lineSequence().filter { line ->
                failureMarkers.any { marker -> line.contains(marker, ignoreCase = true) }
            }.toList()
        check(failures.isEmpty()) {
            "Staged Match3 import reported script/scene errors:\n${failures.joinToString("\n")}\nFull log: $logFile"
        }
        logger.lifecycle("[kanama:web] Match3 staged import clean; log=$logFile")
    }
}

tasks.register<Exec>("exportWebMatch3") {
    group = "verification"
    description = "Exports the staged original Match3 project with its Kotlin/Wasm runtime."
    dependsOn("stageWebMatch3Project", "wasmJsBrowserDistribution")
    inputs.dir(webMatch3Staging)
    inputs.dir(webDistribution)
    outputs.dir(webMatch3Export)

    doFirst {
        val godotExecutable =
            providers.gradleProperty("kanamaGodotExecutable").orNull
                ?: error("Pass -PkanamaGodotExecutable=/absolute/path/to/godot")
        val webTemplateRelease =
            providers.gradleProperty("kanamaWebTemplateRelease").orNull
                ?: error("Pass -PkanamaWebTemplateRelease=/absolute/path/to/web_nothreads_release.zip")
        val webTemplateFile = file(webTemplateRelease)
        check(webTemplateFile.isFile) { "Godot Web release template not found: $webTemplateFile" }
        val stagedPreset = webMatch3Staging.get().file("export_presets.cfg").asFile
        stagedPreset.writeText(
            stagedPreset
                .readText()
                .replace(
                    "custom_template/release=\"\"",
                    "custom_template/release=\"${webTemplateFile.absolutePath}\"",
                )
        )
        val exportDir = webMatch3Export.get().asFile
        delete(exportDir)
        exportDir.mkdirs()
        commandLine(
            godotExecutable,
            "--headless",
            "--path",
            webMatch3Staging.get().asFile.absolutePath,
            "--export-release",
            "Web",
            exportDir.resolve("index.html").absolutePath,
        )
    }

    doLast {
        val exportDir = webMatch3Export.get().asFile
        copy {
            from(webDistribution)
            include("*.js", "*.wasm")
            into(exportDir)
        }
        copy {
            from(webSpikeAssets.file("kanama-web-bridge.js"))
            into(exportDir)
        }
        check(exportDir.resolve("index.html").isFile) {
            "Godot Web Match3 export did not produce index.html"
        }
        check(exportDir.resolve("kanama-web-spike.js").isFile) {
            "Kotlin/Wasm loader was not installed into the Match3 export"
        }
    }
}

// ---------------------------------------------------------------------------
// Task 57f -- stable, user-facing Web entry points.
//
// `buildWebScripts` publishes the generated GDScript proxy bundle (proxies +
// manifest + protocol) alongside the Kotlin/Wasm runtime a project attaches.
// `exportWeb` turns a validated demo into a self-contained, cache-busted,
// HTTP-servable directory with a release payload report. Both reuse the same
// generated resources and staging machinery as the per-demo tasks above, so
// their protocol version and proxy shapes cannot drift from one another.
// ---------------------------------------------------------------------------

val webScriptsBundle = layout.buildDirectory.dir("web-scripts")
val webExportRoot = layout.buildDirectory.dir("web-export")
tasks.register("stageWebCharactercontrollerProject") {
    group = "verification"
    description = "Stages the 3D character-controller tutorial with generated Web proxies (60e)."
    dependsOn("kspKotlinWasmJs", "generateWebGameplayCoverage")
    webCharacterSourceProject?.let(inputs::dir)
    inputs.dir(webSpikeAssets)
    inputs.dir(webProxyResources)
    inputs.file(webGameplayCoverage)
    outputs.dir(webCharacterStaging)

    doLast {
        val sourceProject =
            webCharacterSourceProject
                ?: error(
                    "Pass -PkanamaWebCharactercontrollerProjectDir=" +
                        "/absolute/path/to/kanama-demos/godot-4-3d-character-controller-tutorial"
                )
        check(sourceProject.resolve("game.tscn").isFile) {
            "Character-controller project not found: $sourceProject"
        }

        val stagingDir = webCharacterStaging.get().asFile
        delete(stagingDir)
        copy {
            from(sourceProject) {
                exclude(".git/**")
                exclude(".godot/**")
                exclude(".gradle/**")
                exclude(".kotlin/**")
                exclude("build/**")
                exclude("web/**")
                exclude("kotlin-src/**")
                exclude("android/**")
                exclude("lesson_reference/*.kt")
                exclude("addons/kanama_tools/**")
                exclude("addons/kanama/**")
                exclude("export_presets.cfg")
            }
            into(stagingDir)
        }
        // The demo ships Android/iOS presets; the Web export needs the canonical Web preset.
        copy {
            from(webSpikeSourceProject.file("export_presets.cfg"))
            into(stagingDir)
        }
        copy {
            from(webProxyResources)
            include("*.gd")
            into(stagingDir.resolve("kanama-web/generated"))
        }
        copy {
            from(webProxyResources)
            include("KanamaWebProxyManifest.generated.tsv")
            include("KanamaWebProtocol.generated.json")
            into(stagingDir.resolve("kanama-web"))
        }
        copy {
            from(webSpikeAssets)
            into(stagingDir.resolve("kanama-web"))
        }
        copy {
            from(webGameplayCoverage)
            into(stagingDir.resolve("kanama-web"))
        }

        val manifest = webProxyResources.get().file("KanamaWebProxyManifest.generated.tsv").asFile
        val expectedSources =
            setOf(
                    "Events",
                    "Flag3D",
                    "FlagReachedScreen",
                    "FreelookCamera3D",
                    "Game",
                    "KillPlane3D",
                    "Player3DTemplate",
                    "SmokeQuit",
                    "SophiaSkin",
                )
                .map { "res://kotlin-src/$it.kt" }
                .toSet()
        val mappings =
            manifest
                .readLines()
                .filter { it.isNotBlank() && !it.startsWith("#") }
                .map { it.split('\t').let { c -> c[0] to c[1] } }
                .filter { (sourcePath, _) -> sourcePath in expectedSources }
                .toMap()
        check(mappings.keys == expectedSources) {
            "Character-controller Web proxy mappings incomplete: " +
                "missing=${expectedSources - mappings.keys}"
        }

        fileTree(stagingDir) {
                include("project.godot")
                include("**/*.tscn")
                include("**/*.tres")
            }
            .files
            .forEach { stagedFile ->
                val original = stagedFile.readText()
                var rewritten =
                    mappings.entries.fold(original) { text, (sourcePath, proxyPath) ->
                        text.replace(sourcePath, proxyPath)
                    }
                // Godot resolves Script ext_resources by UID before path; strip the UID from
                // rewritten references so the text path wins (platformer precedent).
                rewritten =
                    rewritten.replace(
                        Regex(
                            "(\\[ext_resource type=\"Script\") uid=\"uid://[^\"]*\" " +
                                "(path=\"res://kanama-web/generated/)"
                        ),
                        "$1 $2",
                    )
                if (rewritten != original) stagedFile.writeText(rewritten)
                check(!rewritten.contains("res://kotlin-src/")) {
                    "Unmapped Kotlin attachment remains in staged file: $stagedFile"
                }
            }

        // The tutorial targets the Mobile renderer (with a mobile gl_compatibility override);
        // the Web template only runs the Compatibility renderer, so pin it explicitly
        // (FPS/squash precedent).
        val stagedProject = stagingDir.resolve("project.godot")
        val stagedProjectText = stagedProject.readText()
        val mobileFeature = "config/features=PackedStringArray(\"4.7\", \"Mobile\")"
        val mobileRenderer = "renderer/rendering_method.mobile=\"gl_compatibility\""
        check(stagedProjectText.contains(mobileFeature)) {
            "Character-controller renderer feature changed; update the Web staging transform"
        }
        check(stagedProjectText.contains(mobileRenderer)) {
            "Character-controller mobile renderer setting changed; update the staging transform"
        }
        stagedProject.writeText(
            stagedProjectText
                .replace(
                    mobileFeature,
                    "config/features=PackedStringArray(\"4.7\", \"GL Compatibility\")",
                )
                .replace(
                    mobileRenderer,
                    "renderer/rendering_method=\"gl_compatibility\"\n$mobileRenderer",
                )
        )

        val shell = stagingDir.resolve("kanama-web/shell.html")
        val pageStart = "globalThis.KanamaWebPageStartedAt = performance.now();"
        shell.writeText(
            shell.readText()
                .replace(
                    pageStart,
                    "$pageStart\n      globalThis.KanamaWebMode = \"charactercontroller\";",
                )
        )
    }
}

tasks.register("stageWebRacingProject") {
    group = "verification"
    description = "Stages Starter-Kit-Racing with generated Web proxies (Task 60g)."
    dependsOn("kspKotlinWasmJs", "generateWebGameplayCoverage")
    webRacingSourceProject?.let(inputs::dir)
    inputs.dir(webSpikeAssets)
    inputs.dir(webProxyResources)
    inputs.file(webGameplayCoverage)
    outputs.dir(webRacingStaging)

    doLast {
        val sourceProject =
            webRacingSourceProject
                ?: error(
                    "Pass -PkanamaWebRacingProjectDir=/absolute/path/to/kanama-demos/Starter-Kit-Racing"
                )
        check(sourceProject.resolve("scenes/main.tscn").isFile) {
            "Racing project not found: $sourceProject"
        }

        val stagingDir = webRacingStaging.get().asFile
        delete(stagingDir)
        copy {
            from(sourceProject) {
                exclude(".git/**")
                exclude(".godot/**")
                exclude(".gradle/**")
                exclude(".kotlin/**")
                exclude("build/**")
                exclude("web/**")
                exclude("kotlin-src/**")
                exclude("android/**")
                exclude("addons/kanama_tools/**")
                exclude("addons/kanama/**")
                exclude("export_presets.cfg")
            }
            into(stagingDir)
        }
        copy {
            from(webSpikeSourceProject.file("export_presets.cfg"))
            into(stagingDir)
        }
        copy {
            from(webProxyResources)
            include("*.gd")
            into(stagingDir.resolve("kanama-web/generated"))
        }
        copy {
            from(webProxyResources)
            include("KanamaWebProxyManifest.generated.tsv")
            include("KanamaWebProtocol.generated.json")
            into(stagingDir.resolve("kanama-web"))
        }
        copy {
            from(webSpikeAssets)
            into(stagingDir.resolve("kanama-web"))
        }
        copy {
            from(webGameplayCoverage)
            into(stagingDir.resolve("kanama-web"))
        }

        val manifest = webProxyResources.get().file("KanamaWebProxyManifest.generated.tsv").asFile
        val expectedSources =
            setOf("Smoke", "Vehicle", "VehicleMotorcycle", "View")
                .map { "res://kotlin-src/$it.kt" }
                .toSet()
        val mappings =
            manifest
                .readLines()
                .filter { it.isNotBlank() && !it.startsWith("#") }
                .map { it.split('\t').let { c -> c[0] to c[1] } }
                .filter { (sourcePath, _) -> sourcePath in expectedSources }
                .toMap()
        check(mappings.keys == expectedSources) {
            "Racing Web proxy mappings incomplete: missing=${expectedSources - mappings.keys}"
        }

        fileTree(stagingDir) {
                include("project.godot")
                include("**/*.tscn")
                include("**/*.tres")
            }
            .files
            .forEach { stagedFile ->
                val original = stagedFile.readText()
                var rewritten =
                    mappings.entries.fold(original) { text, (sourcePath, proxyPath) ->
                        text.replace(sourcePath, proxyPath)
                    }
                rewritten =
                    rewritten.replace(
                        Regex(
                            "(\\[ext_resource type=\"Script\") uid=\"uid://[^\"]*\" " +
                                "(path=\"res://kanama-web/generated/)"
                        ),
                        "$1 $2",
                    )
                if (rewritten != original) stagedFile.writeText(rewritten)
                check(!rewritten.contains("res://kotlin-src/")) {
                    "Unmapped Kotlin attachment remains in staged file: $stagedFile"
                }
            }

        // Forward+ project with a gl_compatibility mobile override: pin Compatibility.
        val stagedProject = stagingDir.resolve("project.godot")
        val stagedProjectText = stagedProject.readText()
        val forwardFeature = "config/features=PackedStringArray(\"4.7\", \"Forward Plus\")"
        val mobileRenderer = "renderer/rendering_method.mobile=\"gl_compatibility\""
        check(stagedProjectText.contains(forwardFeature)) {
            "Racing renderer feature changed; update the Web staging transform"
        }
        check(stagedProjectText.contains(mobileRenderer)) {
            "Racing mobile renderer setting changed; update the staging transform"
        }
        stagedProject.writeText(
            stagedProjectText
                .replace(
                    forwardFeature,
                    "config/features=PackedStringArray(\"4.7\", \"GL Compatibility\")",
                )
                .replace(
                    mobileRenderer,
                    "renderer/rendering_method=\"gl_compatibility\"\n$mobileRenderer",
                )
        )

        val shell = stagingDir.resolve("kanama-web/shell.html")
        val pageStart = "globalThis.KanamaWebPageStartedAt = performance.now();"
        shell.writeText(
            shell.readText()
                .replace(pageStart, "$pageStart\n      globalThis.KanamaWebMode = \"racing\";")
        )
    }
}

tasks.register("stageWebCitybuilderProject") {
    group = "verification"
    description = "Stages Starter-Kit-City-Builder with generated Web proxies (Task 60h)."
    dependsOn("kspKotlinWasmJs", "generateWebGameplayCoverage")
    webCitybuilderSourceProject?.let(inputs::dir)
    inputs.dir(webSpikeAssets)
    inputs.dir(webProxyResources)
    inputs.file(webGameplayCoverage)
    outputs.dir(webCitybuilderStaging)

    doLast {
        val sourceProject =
            webCitybuilderSourceProject
                ?: error(
                    "Pass -PkanamaWebCitybuilderProjectDir=" +
                        "/absolute/path/to/kanama-demos/Starter-Kit-City-Builder"
                )
        check(sourceProject.resolve("scenes/main.tscn").isFile) {
            "City-Builder project not found: $sourceProject"
        }

        val stagingDir = webCitybuilderStaging.get().asFile
        delete(stagingDir)
        copy {
            from(sourceProject) {
                exclude(".git/**")
                exclude(".godot/**")
                exclude(".gradle/**")
                exclude(".kotlin/**")
                exclude("build/**")
                exclude("web/**")
                exclude("kotlin-src/**")
                exclude("android/**")
                exclude("addons/kanama_tools/**")
                exclude("addons/kanama/**")
                exclude("export_presets.cfg")
            }
            into(stagingDir)
        }
        copy {
            from(webSpikeSourceProject.file("export_presets.cfg"))
            into(stagingDir)
        }
        copy {
            from(webProxyResources)
            include("*.gd")
            into(stagingDir.resolve("kanama-web/generated"))
        }
        copy {
            from(webProxyResources)
            include("KanamaWebProxyManifest.generated.tsv")
            include("KanamaWebProtocol.generated.json")
            into(stagingDir.resolve("kanama-web"))
        }
        copy {
            from(webSpikeAssets)
            into(stagingDir.resolve("kanama-web"))
        }
        copy {
            from(webGameplayCoverage)
            into(stagingDir.resolve("kanama-web"))
        }

        val manifest = webProxyResources.get().file("KanamaWebProxyManifest.generated.tsv").asFile
        val expectedSources =
            setOf("Audio", "Builder", "DataMap", "DataStructure", "Smoke", "Structure", "View")
                .map { "res://kotlin-src/$it.kt" }
                .toSet()
        val mappings =
            manifest
                .readLines()
                .filter { it.isNotBlank() && !it.startsWith("#") }
                .map { it.split('\t').let { c -> c[0] to c[1] } }
                .filter { (sourcePath, _) -> sourcePath in expectedSources }
                .toMap()
        check(mappings.keys == expectedSources) {
            "City-Builder Web proxy mappings incomplete: missing=${expectedSources - mappings.keys}"
        }

        fileTree(stagingDir) {
                include("project.godot")
                include("**/*.tscn")
                include("**/*.tres")
            }
            .files
            .forEach { stagedFile ->
                val original = stagedFile.readText()
                var rewritten =
                    mappings.entries.fold(original) { text, (sourcePath, proxyPath) ->
                        text.replace(sourcePath, proxyPath)
                    }
                rewritten =
                    rewritten.replace(
                        Regex(
                            "(\\[ext_resource type=\"Script\") uid=\"uid://[^\"]*\" " +
                                "(path=\"res://kanama-web/generated/)"
                        ),
                        "$1 $2",
                    )
                if (rewritten != original) stagedFile.writeText(rewritten)
                check(!rewritten.contains("res://kotlin-src/")) {
                    "Unmapped Kotlin attachment remains in staged file: $stagedFile"
                }
            }

        // Forward+ project with no mobile override: pin the Compatibility renderer the Web
        // template actually runs.
        val stagedProject = stagingDir.resolve("project.godot")
        val stagedProjectText = stagedProject.readText()
        val forwardFeature = "config/features=PackedStringArray(\"4.7\", \"Forward Plus\")"
        val renderingSection = "[rendering]"
        check(stagedProjectText.contains(forwardFeature)) {
            "City-Builder renderer feature changed; update the Web staging transform"
        }
        check(stagedProjectText.contains(renderingSection)) {
            "City-Builder rendering section changed; update the Web staging transform"
        }
        stagedProject.writeText(
            stagedProjectText
                .replace(
                    forwardFeature,
                    "config/features=PackedStringArray(\"4.7\", \"GL Compatibility\")",
                )
                .replace(
                    renderingSection,
                    "$renderingSection\n\nrenderer/rendering_method=\"gl_compatibility\"",
                )
        )

        val shell = stagingDir.resolve("kanama-web/shell.html")
        val pageStart = "globalThis.KanamaWebPageStartedAt = performance.now();"
        shell.writeText(
            shell.readText()
                .replace(pageStart, "$pageStart\n      globalThis.KanamaWebMode = \"citybuilder\";")
        )
    }
}

tasks.register("stageWebTpsdemoProject") {
    group = "verification"
    description = "Stages tps-demo-kanama with generated Web proxies (Task 60i)."
    dependsOn("kspKotlinWasmJs", "generateWebGameplayCoverage")
    webTpsdemoSourceProject?.let(inputs::dir)
    inputs.dir(webSpikeAssets)
    inputs.dir(webProxyResources)
    inputs.file(webGameplayCoverage)
    outputs.dir(webTpsdemoStaging)

    doLast {
        val sourceProject =
            webTpsdemoSourceProject
                ?: error(
                    "Pass -PkanamaWebTpsdemoProjectDir=" +
                        "/absolute/path/to/kanama-demos/tps-demo-kanama"
                )
        check(sourceProject.resolve("main/main.tscn").isFile) {
            "tps-demo project not found: $sourceProject"
        }

        val stagingDir = webTpsdemoStaging.get().asFile
        delete(stagingDir)
        copy {
            from(sourceProject) {
                exclude(".git/**")
                exclude(".godot/**")
                exclude(".gradle/**")
                exclude(".kotlin/**")
                exclude("build/**")
                exclude("web/**")
                exclude("kotlin-src/**")
                exclude("android/**")
                exclude("addons/kanama_tools/**")
                exclude("addons/kanama/**")
                exclude("export_presets.cfg")
            }
            into(stagingDir)
        }
        copy {
            from(webSpikeSourceProject.file("export_presets.cfg"))
            into(stagingDir)
        }
        copy {
            from(webProxyResources)
            include("*.gd")
            into(stagingDir.resolve("kanama-web/generated"))
        }
        copy {
            from(webProxyResources)
            include("KanamaWebProxyManifest.generated.tsv")
            include("KanamaWebProtocol.generated.json")
            into(stagingDir.resolve("kanama-web"))
        }
        copy {
            from(webSpikeAssets)
            into(stagingDir.resolve("kanama-web"))
        }
        copy {
            from(webGameplayCoverage)
            into(stagingDir.resolve("kanama-web"))
        }

        val manifest = webProxyResources.get().file("KanamaWebProxyManifest.generated.tsv").asFile
        val expectedSources =
            setOf(
                    "Blast",
                    "Bullet",
                    "CameraNoiseShakeEffect",
                    "DebugLabel",
                    "Door",
                    "FlyingForklift",
                    "Level",
                    "Main",
                    "Menu",
                    "Part",
                    "PartDisappear",
                    "Player",
                    "PlayerInputSynchronizer",
                    "RedRobot",
                    "Settings",
                )
                .map { "res://kotlin-src/$it.kt" }
                .toSet()
        val mappings =
            manifest
                .readLines()
                .filter { it.isNotBlank() && !it.startsWith("#") }
                .map { it.split('\t').let { c -> c[0] to c[1] } }
                .filter { (sourcePath, _) -> sourcePath in expectedSources }
                .toMap()
        check(mappings.keys == expectedSources) {
            "tps-demo Web proxy mappings incomplete: missing=${expectedSources - mappings.keys}"
        }

        fileTree(stagingDir) {
                include("project.godot")
                include("**/*.tscn")
                include("**/*.tres")
            }
            .files
            .forEach { stagedFile ->
                val original = stagedFile.readText()
                var rewritten =
                    mappings.entries.fold(original) { text, (sourcePath, proxyPath) ->
                        text.replace(sourcePath, proxyPath)
                    }
                rewritten =
                    rewritten.replace(
                        Regex(
                            "(\\[ext_resource type=\"Script\") uid=\"uid://[^\"]*\" " +
                                "(path=\"res://kanama-web/generated/)"
                        ),
                        "$1 $2",
                    )
                if (rewritten != original) stagedFile.writeText(rewritten)
                check(!rewritten.contains("res://kotlin-src/")) {
                    "Unmapped Kotlin attachment remains in staged file: $stagedFile"
                }
            }

        // Default (Forward+) project: pin the Compatibility renderer the Web template runs.
        val stagedProject = stagingDir.resolve("project.godot")
        val stagedProjectText = stagedProject.readText()
        val forwardFeature = "config/features=PackedStringArray(\"4.7\")"
        val renderingSection = "[rendering]"
        check(stagedProjectText.contains(forwardFeature)) {
            "tps-demo renderer feature changed; update the Web staging transform"
        }
        check(stagedProjectText.contains(renderingSection)) {
            "tps-demo rendering section changed; update the Web staging transform"
        }
        stagedProject.writeText(
            stagedProjectText
                .replace(
                    forwardFeature,
                    "config/features=PackedStringArray(\"4.7\", \"GL Compatibility\")",
                )
                .replace(
                    renderingSection,
                    "$renderingSection\n\nrenderer/rendering_method=\"gl_compatibility\"",
                )
        )

        // The menu's FogVolume needs the volumetric-fog shader, which the Compatibility
        // renderer this export runs has no implementation for (it logs "shader type fog not
        // supported"). Drop the node from the staged scene rather than ship a broken effect.
        val stagedMenu = stagingDir.resolve("menu/menu.tscn")
        val menuText = stagedMenu.readText()
        val fogHeader = "[node name=\"FogVolume\" type=\"FogVolume\" parent=\"WorldEnvironment\"]"
        check(menuText.contains(fogHeader)) {
            "tps-demo menu FogVolume changed; update the Web staging transform"
        }
        val fogStart = menuText.indexOf(fogHeader)
        val fogEnd = menuText.indexOf("\n[", fogStart + fogHeader.length)
        check(fogEnd > fogStart) { "tps-demo menu FogVolume block is not delimited as expected" }
        stagedMenu.writeText(menuText.removeRange(fogStart, fogEnd + 1))

        val shell = stagingDir.resolve("kanama-web/shell.html")
        val pageStart = "globalThis.KanamaWebPageStartedAt = performance.now();"
        shell.writeText(
            shell.readText()
                .replace(pageStart, "$pageStart\n      globalThis.KanamaWebMode = \"tpsdemo\";")
        )
    }
}

tasks.register("stageWebThirdpersonProject") {
    group = "verification"
    description = "Stages the third-person controller with generated Web proxies (Task 60f)."
    dependsOn("kspKotlinWasmJs", "generateWebGameplayCoverage")
    webThirdpersonSourceProject?.let(inputs::dir)
    inputs.dir(webSpikeAssets)
    inputs.dir(webProxyResources)
    inputs.file(webGameplayCoverage)
    outputs.dir(webThirdpersonStaging)

    doLast {
        val sourceProject =
            webThirdpersonSourceProject
                ?: error(
                    "Pass -PkanamaWebThirdpersonProjectDir=" +
                        "/absolute/path/to/kanama-demos/godot-4-3d-third-person-controller"
                )
        check(sourceProject.resolve("main.tscn").isFile) {
            "Third-person project not found: $sourceProject"
        }

        val stagingDir = webThirdpersonStaging.get().asFile
        delete(stagingDir)
        copy {
            from(sourceProject) {
                exclude(".git/**")
                exclude(".godot/**")
                exclude(".gradle/**")
                exclude(".kotlin/**")
                exclude("build/**")
                exclude("web/**")
                exclude("kotlin-src/**")
                exclude("android/**")
                exclude("addons/kanama_tools/**")
                exclude("addons/kanama/**")
                exclude("export_presets.cfg")
            }
            into(stagingDir)
        }
        copy {
            from(webSpikeSourceProject.file("export_presets.cfg"))
            into(stagingDir)
        }
        copy {
            from(webProxyResources)
            include("*.gd")
            into(stagingDir.resolve("kanama-web/generated"))
        }
        copy {
            from(webProxyResources)
            include("KanamaWebProxyManifest.generated.tsv")
            include("KanamaWebProtocol.generated.json")
            into(stagingDir.resolve("kanama-web"))
        }
        copy {
            from(webSpikeAssets)
            into(stagingDir.resolve("kanama-web"))
        }
        copy {
            from(webGameplayCoverage)
            into(stagingDir.resolve("kanama-web"))
        }

        val manifest = webProxyResources.get().file("KanamaWebProxyManifest.generated.tsv").asFile
        val expectedSources =
            setOf(
                    "BeeBot",
                    "BeeRoot",
                    "BeetleBot",
                    "BeetlebotSkin",
                    "Box",
                    "Bullet",
                    "CameraController",
                    "CameraMode",
                    "CharacterSkin",
                    "Coin",
                    "CoinModel",
                    "CoinsContainer",
                    "DeathPlane",
                    "DemoPage",
                    "DestroyedBox",
                    "FullScreenHandler",
                    "GrassScatter",
                    "Grenade",
                    "GrenadeLauncher",
                    "GrenadeVisual",
                    "Icone",
                    "JumpingPad",
                    "LinkButton",
                    "MeleeAttackArea",
                    "Player",
                    "SmokePuff",
                    "SmokeQuit",
                    "WeaponUi",
                )
                .map { "res://kotlin-src/$it.kt" }
                .toSet()
        val mappings =
            manifest
                .readLines()
                .filter { it.isNotBlank() && !it.startsWith("#") }
                .map { it.split('\t').let { c -> c[0] to c[1] } }
                .filter { (sourcePath, _) -> sourcePath in expectedSources }
                .toMap()
        check(mappings.keys == expectedSources) {
            "Third-person Web proxy mappings incomplete: missing=${expectedSources - mappings.keys}"
        }

        fileTree(stagingDir) {
                include("project.godot")
                include("**/*.tscn")
                include("**/*.tres")
            }
            .files
            .forEach { stagedFile ->
                val original = stagedFile.readText()
                var rewritten =
                    mappings.entries.fold(original) { text, (sourcePath, proxyPath) ->
                        text.replace(sourcePath, proxyPath)
                    }
                rewritten =
                    rewritten.replace(
                        Regex(
                            "(\\[ext_resource type=\"Script\") uid=\"uid://[^\"]*\" " +
                                "(path=\"res://kanama-web/generated/)"
                        ),
                        "$1 $2",
                    )
                if (rewritten != original) stagedFile.writeText(rewritten)
                check(!rewritten.contains("res://kotlin-src/")) {
                    "Unmapped Kotlin attachment remains in staged file: $stagedFile"
                }
            }

        // Forward+ project: pin the Compatibility renderer for the Web template (FPS precedent).
        val stagedProject = stagingDir.resolve("project.godot")
        val stagedProjectText = stagedProject.readText()
        val forwardFeature = "config/features=PackedStringArray(\"4.7\", \"Forward Plus\")"
        val mobileRenderer = "renderer/rendering_method.mobile=\"mobile\""
        check(stagedProjectText.contains(forwardFeature)) {
            "Third-person renderer feature changed; update the Web staging transform"
        }
        check(stagedProjectText.contains(mobileRenderer)) {
            "Third-person mobile renderer setting changed; update the staging transform"
        }
        stagedProject.writeText(
            stagedProjectText
                .replace(
                    forwardFeature,
                    "config/features=PackedStringArray(\"4.7\", \"GL Compatibility\")",
                )
                .replace(
                    mobileRenderer,
                    "renderer/rendering_method=\"gl_compatibility\"\n" +
                        "renderer/rendering_method.mobile=\"gl_compatibility\"",
                )
        )

        val shell = stagingDir.resolve("kanama-web/shell.html")
        val pageStart = "globalThis.KanamaWebPageStartedAt = performance.now();"
        shell.writeText(
            shell.readText()
                .replace(
                    pageStart,
                    "$pageStart\n      globalThis.KanamaWebMode = \"thirdperson\";",
                )
        )
    }
}

val webDemo = providers.gradleProperty("kanamaWebDemo").orElse("match3")

// The single renderer/thread contract for the Kotlin/Wasm preview backend.
val webRendererName = "gl_compatibility"
val webThreadsSupported = false

fun readProtocolVersion(): Int {
    val protocolFile =
        webProxyResources.get().file("KanamaWebProtocol.generated.json").asFile
    check(protocolFile.isFile) { "Missing generated Web protocol descriptor: $protocolFile" }
    val match = Regex("\"protocolVersion\"\\s*:\\s*(\\d+)").find(protocolFile.readText())
    return match?.groupValues?.get(1)?.toInt()
        ?: error("Could not read protocolVersion from $protocolFile")
}

tasks.register("buildWebScripts") {
    group = "kanama-web"
    description =
        "Generates the Web GDScript proxies + protocol and collects the Kotlin/Wasm runtime bundle."
    dependsOn("kspKotlinWasmJs", "wasmJsBrowserDistribution")
    inputs.dir(webProxyResources)
    inputs.dir(webDistribution)
    inputs.dir(webSpikeAssets)
    outputs.dir(webScriptsBundle)

    doLast {
        val bundle = webScriptsBundle.get().asFile
        delete(bundle)
        bundle.mkdirs()

        // Generated GDScript proxies, manifest, and protocol descriptor.
        val generatedDir = bundle.resolve("generated")
        copy {
            from(webProxyResources)
            into(generatedDir)
        }

        // Kotlin/Wasm runtime: the webpack loader + its content-hashed wasm,
        // plus the versioned JS bridge and HTML shell the project attaches.
        val runtimeDir = bundle.resolve("runtime")
        copy {
            from(webDistribution) {
                include("*.js", "*.wasm")
            }
            into(runtimeDir)
        }
        copy {
            from(webSpikeAssets)
            into(runtimeDir)
        }

        // No source maps ship by default (webpack sourceMaps=false); fail loud
        // if that policy ever regresses into the published bundle.
        val leakedSourceMaps =
            fileTree(bundle) { include("**/*.map") }.files.map { it.name }
        check(leakedSourceMaps.isEmpty()) {
            "Web script bundle must not ship source maps: $leakedSourceMaps"
        }

        val protocolVersion = readProtocolVersion()
        val report = bundle.resolve("build-web-scripts.report.json")
        val runtimeFiles =
            runtimeDir.walkTopDown().filter { it.isFile }.sortedBy { it.name }.toList()
        val proxyFiles =
            generatedDir.walkTopDown().filter { it.isFile }.sortedBy { it.name }.toList()
        report.writeText(
            buildString {
                append("{\n")
                append("  \"protocolVersion\": $protocolVersion,\n")
                append("  \"renderer\": \"$webRendererName\",\n")
                append("  \"threadsSupported\": $webThreadsSupported,\n")
                append("  \"sourceMaps\": false,\n")
                append("  \"proxies\": [")
                append(proxyFiles.joinToString(", ") { "\"${it.name}\"" })
                append("],\n")
                append("  \"runtime\": [")
                append(runtimeFiles.joinToString(", ") { "\"${it.name}\"" })
                append("]\n")
                append("}\n")
            }
        )
        logger.lifecycle(
            "[kanama:web] buildWebScripts published protocol $protocolVersion to $bundle"
        )
    }
}

fun stageTaskFor(demo: String): String =
    when (demo) {
        "match3" -> "stageWebMatch3Project"
        "bunnymark" -> "stageWebBunnymarkProject"
        "dodge" -> "stageWebDodgeProject"
        "web3d" -> "stageWebWeb3dProject"
        "platformer" -> "stageWebPlatformerProject"
        "squash" -> "stageWebSquashProject"
        "fps" -> "stageWebFpsProject"
        "charactercontroller" -> "stageWebCharactercontrollerProject"
        "thirdperson" -> "stageWebThirdpersonProject"
        "racing" -> "stageWebRacingProject"
        "citybuilder" -> "stageWebCitybuilderProject"
        "tpsdemo" -> "stageWebTpsdemoProject"
        else ->
            error(
                "Unsupported -PkanamaWebDemo=$demo (expected match3|bunnymark|dodge|web3d|platformer|squash|fps|charactercontroller|thirdperson|racing|citybuilder|tpsdemo)"
            )
    }

fun stagingDirFor(demo: String): File =
    when (demo) {
        "match3" -> webMatch3Staging.get().asFile
        "bunnymark" -> webBunnymarkStaging.get().asFile
        "dodge" -> webDodgeStaging.get().asFile
        "web3d" -> webWeb3dStaging.get().asFile
        "platformer" -> webPlatformerStaging.get().asFile
        "squash" -> webSquashStaging.get().asFile
        "fps" -> webFpsStaging.get().asFile
        "charactercontroller" -> webCharacterStaging.get().asFile
        "thirdperson" -> webThirdpersonStaging.get().asFile
        "racing" -> webRacingStaging.get().asFile
        "citybuilder" -> webCitybuilderStaging.get().asFile
        "tpsdemo" -> webTpsdemoStaging.get().asFile
        else ->
            error(
                "Unsupported -PkanamaWebDemo=$demo (expected match3|bunnymark|dodge|web3d|platformer|squash|fps|charactercontroller|thirdperson|racing|citybuilder|tpsdemo)"
            )
    }

tasks.register<Exec>("exportWeb") {
    group = "kanama-web"
    description =
        "Exports a validated demo (-PkanamaWebDemo=match3|bunnymark) to a self-contained, " +
            "cache-busted, HTTP-servable directory with a release payload report."
    dependsOn("buildWebScripts", "wasmJsBrowserDistribution")
    dependsOn(webDemo.map(::stageTaskFor))
    inputs.property("kanamaWebDemo", webDemo)
    inputs.dir(webDistribution)
    inputs.dir(webSpikeAssets)
    // The staged project is what Godot actually exports; without it as an input a
    // proxy/staging-only change (e.g. a regenerated GDScript applier) leaves exportWeb UP-TO-DATE
    // and ships a stale export.
    inputs.dir(webDemo.map(::stagingDirFor))
    outputs.dir(webExportRoot)

    doFirst {
        val demo = webDemo.get()
        val godotExecutable =
            providers.gradleProperty("kanamaGodotExecutable").orNull
                ?: error("Pass -PkanamaGodotExecutable=/absolute/path/to/godot")
        val webTemplateRelease =
            providers.gradleProperty("kanamaWebTemplateRelease").orNull
                ?: error("Pass -PkanamaWebTemplateRelease=/absolute/path/to/web_nothreads_release.zip")
        val webTemplateFile = file(webTemplateRelease)
        check(webTemplateFile.isFile) { "Godot Web release template not found: $webTemplateFile" }

        val stagingDir = stagingDirFor(demo)
        val stagedPreset = stagingDir.resolve("export_presets.cfg")
        check(stagedPreset.isFile) { "Staged project has no export_presets.cfg: $stagedPreset" }
        stagedPreset.writeText(
            stagedPreset
                .readText()
                .replace(
                    "custom_template/release=\"\"",
                    "custom_template/release=\"${webTemplateFile.absolutePath}\"",
                )
        )

        val exportDir = webExportRoot.get().dir(demo).asFile
        delete(exportDir)
        exportDir.mkdirs()
        commandLine(
            godotExecutable,
            "--headless",
            "--path",
            stagingDir.absolutePath,
            "--export-release",
            "Web",
            exportDir.resolve("index.html").absolutePath,
        )
    }

    doLast {
        val demo = webDemo.get()
        val exportDir = webExportRoot.get().dir(demo).asFile

        // Install the Kotlin/Wasm runtime, versioned bridge into the export.
        copy {
            from(webDistribution) {
                include("*.js", "*.wasm")
            }
            into(exportDir)
        }
        copy {
            from(webSpikeAssets.file("kanama-web-bridge.js"))
            into(exportDir)
        }

        val indexHtml = exportDir.resolve("index.html")
        check(indexHtml.isFile) { "Godot Web export did not produce index.html for $demo" }
        val spikeLoader = exportDir.resolve("kanama-web-spike.js")
        val bridge = exportDir.resolve("kanama-web-bridge.js")
        check(spikeLoader.isFile) { "Kotlin/Wasm loader missing from $demo export" }
        check(bridge.isFile) { "Versioned JS bridge missing from $demo export" }

        // Cache-busting: the Kotlin gameplay wasm is already content-hashed by
        // webpack (its filename is its hash), and kanama-web-spike.js references
        // it by that name. Only the two fixed-name entry scripts can go stale, so
        // stamp them with a content-derived build id the browser treats as a new
        // resource. This is safe: they are <script src> tags, not module imports.
        val buildId =
            MessageDigest.getInstance("SHA-256").let { digest ->
                digest.update(spikeLoader.readBytes())
                digest.update(bridge.readBytes())
                digest.digest().joinToString("") { "%02x".format(it) }.substring(0, 16)
            }
        val originalIndex = indexHtml.readText()
        check(originalIndex.contains("src=\"kanama-web-spike.js\"")) {
            "Exported shell is missing the kanama-web-spike.js script tag"
        }
        check(originalIndex.contains("src=\"kanama-web-bridge.js\"")) {
            "Exported shell is missing the kanama-web-bridge.js script tag"
        }
        indexHtml.writeText(
            originalIndex
                .replace(
                    "src=\"kanama-web-spike.js\"",
                    "src=\"kanama-web-spike.js?v=$buildId\"",
                )
                .replace(
                    "src=\"kanama-web-bridge.js\"",
                    "src=\"kanama-web-bridge.js?v=$buildId\"",
                )
        )

        // Self-contained check: no workstation-absolute path may leak into the
        // served HTML, and the payload must not reference the staging tree.
        val servedIndex = indexHtml.readText()
        val stagingPath = stagingDirFor(demo).absolutePath
        check(!servedIndex.contains(stagingPath)) {
            "Exported index.html leaks the staging path $stagingPath"
        }
        check(!servedIndex.contains(System.getProperty("user.home"))) {
            "Exported index.html leaks a workstation-absolute path"
        }

        val wasmFiles = exportDir.listFiles { file -> file.extension == "wasm" }?.toList().orEmpty()
        check(wasmFiles.isNotEmpty()) { "Web export has no wasm payload for $demo" }
        check(fileTree(exportDir) { include("**/*.map") }.files.isEmpty()) {
            "Web export must not ship source maps for $demo"
        }

        // Release payload report: every served file and its size, for budget
        // tracking and the export-smoke harness to cross-check.
        val protocolVersion = readProtocolVersion()
        val servedFiles =
            exportDir
                .walkTopDown()
                .filter { it.isFile }
                .map { it.relativeTo(exportDir).invariantSeparatorsPath to it.length() }
                .sortedBy { it.first }
                .toList()
        val totalBytes = servedFiles.sumOf { it.second }
        val reportDir = exportDir.resolve("kanama-web")
        reportDir.mkdirs()
        val report = reportDir.resolve("export-report.json")
        report.writeText(
            buildString {
                append("{\n")
                append("  \"demo\": \"$demo\",\n")
                append("  \"protocolVersion\": $protocolVersion,\n")
                append("  \"buildId\": \"$buildId\",\n")
                append("  \"renderer\": \"$webRendererName\",\n")
                append("  \"threadsSupported\": $webThreadsSupported,\n")
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
        logger.lifecycle(
            "[kanama:web] exportWeb produced $demo export ($totalBytes bytes, protocol " +
                "$protocolVersion, buildId $buildId) at $exportDir"
        )
    }
}
