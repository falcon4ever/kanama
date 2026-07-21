plugins {
    kotlin("multiplatform")
    id("com.google.devtools.ksp")
}

val webScriptSourceRoot =
    layout.projectDirectory.dir("src/commonMain/kotlin/net/multigesture/kanama/web")

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
    }
}

dependencies {
    add("kspWasmJs", project(":processor"))
}

ksp {
    arg("kanamaScriptRoots", webScriptSourceRoot.asFile.absolutePath)
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
