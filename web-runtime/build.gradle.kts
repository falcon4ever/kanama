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
val webBunnymarkStaging = layout.buildDirectory.dir("web-bunnymark/godot-project")
val webBunnymarkExport = layout.buildDirectory.dir("web-bunnymark/export")
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
    description = "Stages the real Bunnymark draw-texture demo with its generated Web proxy."
    dependsOn("kspKotlinWasmJs")
    webBunnymarkSourceProject?.let(inputs::dir)
    inputs.dir(webSpikeAssets)
    inputs.dir(webProxyResources)
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

        val stagingDir = webBunnymarkStaging.get().asFile
        delete(stagingDir)
        copy {
            from(sourceProject) {
                include("Benchmarker.tscn")
                include("project.godot")
                include("images/godot_bunny.png")
                include("scripts/Benchmarker.gd")
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
        val sourcePath = "res://kotlin-src/BunnymarkV1DrawTextureKanama.kt"
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
        check(originalBenchmarker.contains(defaultBenchmark)) {
            "Bunnymark harness default changed; update the Web staging transform"
        }
        check(originalBenchmarker.contains(dynamicKotlinScriptPath)) {
            "Bunnymark Kotlin script lookup changed; update the Web staging transform"
        }
        val stagedBenchmarker =
            originalBenchmarker
                .replace(defaultBenchmark, "var benchmark: String = \"BunnymarkV1DrawTexture\"")
                .replace(
                    dynamicKotlinScriptPath,
                    "return \"$proxyPath\"",
                )
        check(stagedBenchmarker != originalBenchmarker) {
            "Bunnymark staging did not select the draw-texture Web proxy"
        }
        check(!stagedBenchmarker.contains("res://kotlin-src/")) {
            "A Kotlin script path remains in staged Bunnymark Benchmarker.gd"
        }
        benchmarker.writeText(stagedBenchmarker)

        val shell = stagingDir.resolve("kanama-web/shell.html")
        val originalShell = shell.readText()
        val pageStart = "globalThis.KanamaWebPageStartedAt = performance.now();"
        check(originalShell.contains(pageStart)) { "Missing Web shell bootstrap marker" }
        shell.writeText(
            originalShell.replace(
                pageStart,
                "$pageStart\n      globalThis.KanamaWebMode = \"bunnymark\";",
            )
        )
    }
}

tasks.register<Exec>("exportWebBunnymark") {
    group = "verification"
    description = "Exports the real Kotlin/Wasm Bunnymark draw-texture demo."
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
