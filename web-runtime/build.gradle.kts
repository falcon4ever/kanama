plugins {
    kotlin("multiplatform")
    id("com.google.devtools.ksp")
}

val webScriptSourceRoot =
    layout.projectDirectory.dir("src/commonMain/kotlin/net/multigesture/kanama/web")
val extraWebScriptSourceRoot =
    providers.gradleProperty("kanamaWebExtraScriptSourceDir").orNull?.let(rootProject::file)

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
            extraWebScriptSourceRoot?.let(kotlin::srcDir)
            dependencies {
                implementation(kotlin("stdlib"))
                implementation(project(":kanama-common-api"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.11.0")
            }
        }
    }
}

dependencies {
    add("kspWasmJs", project(":processor"))
}

ksp {
    val scriptRoots =
        listOfNotNull(webScriptSourceRoot.asFile, extraWebScriptSourceRoot)
            .joinToString(System.getProperty("path.separator")) { it.absolutePath }
    arg("kanamaScriptRoots", scriptRoots)
    arg("kanamaRuntimeTarget", "web")
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
val webDistribution = layout.buildDirectory.dir("dist/wasmJs/productionExecutable")

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
