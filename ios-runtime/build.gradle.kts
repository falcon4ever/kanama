plugins {
    kotlin("multiplatform")
}

import java.io.File
import java.security.MessageDigest

data class IosScriptMethodModel(
    val godotName: String,
    val kotlinName: String,
    val argumentCount: Int,
)

data class IosScriptModel(
    val sourceFile: File,
    val resourcePath: String,
    val packageName: String?,
    val className: String,
    val baseType: String,
    val methods: List<IosScriptMethodModel>,
)

fun iosScriptDirs(raw: String?): List<String> =
    raw
        ?.split(File.pathSeparator, ",")
        ?.map { it.trim() }
        ?.filter { it.isNotEmpty() }
        .orEmpty()

fun shortHash(value: String): String =
    MessageDigest.getInstance("SHA-256")
        .digest(value.toByteArray(Charsets.UTF_8))
        .take(8)
        .joinToString("") { "%02x".format(it) }

fun kotlinString(value: String): String =
    buildString {
        append('"')
        value.forEach { ch ->
            when (ch) {
                '\\' -> append("\\\\")
                '"' -> append("\\\"")
                '\n' -> append("\\n")
                '\r' -> append("\\r")
                '\t' -> append("\\t")
                else -> append(ch)
            }
        }
        append('"')
    }

fun resourcePathFor(root: File, sourceFile: File): String {
    val relativePath = sourceFile.relativeTo(root).path.replace(File.separatorChar, '/')
    return if (root.name == "kotlin-src") {
        "res://kotlin-src/$relativePath"
    } else {
        "res://$relativePath"
    }
}

fun registeredMethodName(annotation: String, functionName: String): String {
    val explicit = Regex(""""([^"]+)"""").find(annotation)?.groupValues?.get(1)
    return explicit?.takeIf { it.isNotBlank() } ?: functionName
}

fun parseIosScript(sourceRoot: File, sourceFile: File): IosScriptModel? {
    val text = sourceFile.readText()
    if (!text.contains("@ScriptClass")) {
        return null
    }
    val packageName = Regex("""(?m)^\s*package\s+([A-Za-z_][A-Za-z0-9_.]*)\s*$""")
        .find(text)
        ?.groupValues
        ?.get(1)
    val baseType = Regex("""@ScriptClass\s*\(\s*attachTo\s*=\s*"([^"]+)"""")
        .find(text)
        ?.groupValues
        ?.get(1)
        ?: "Node"
    val className = Regex("""\bclass\s+([A-Za-z_][A-Za-z0-9_]*)\s*\(""")
        .find(text)
        ?.groupValues
        ?.get(1)
        ?: return null

    val methods = mutableListOf<IosScriptMethodModel>()
    val pendingAnnotations = mutableListOf<String>()
    text.lineSequence().forEach { rawLine ->
        val line = rawLine.trim()
        when {
            line.startsWith("@") -> pendingAnnotations += line
            line.startsWith("fun ") || line.contains(" fun ") -> {
                val functionMatch = Regex("""\bfun\s+([A-Za-z_][A-Za-z0-9_]*)\s*\(([^)]*)\)""").find(line)
                if (functionMatch != null) {
                    val functionName = functionMatch.groupValues[1]
                    val params = functionMatch.groupValues[2].trim()
                    val functionArgCount = if (params.isBlank()) 0 else params.split(",").size
                    pendingAnnotations.forEach { annotation ->
                        when {
                            annotation.contains("OnReady") || annotation.contains("@Ready") ->
                                methods += IosScriptMethodModel("_ready", functionName, 0)
                            annotation.contains("OnExitTree") || annotation.contains("@ExitTree") ->
                                methods += IosScriptMethodModel("_exit_tree", functionName, 0)
                            annotation.contains("OnProcess") || annotation.contains("@Process") ->
                                methods += IosScriptMethodModel("_process", functionName, 1)
                            annotation.contains("OnPhysicsProcess") || annotation.contains("@PhysicsProcess") ->
                                methods += IosScriptMethodModel("_physics_process", functionName, 1)
                            annotation.contains("RegisterFunction") || annotation.contains("@Method") ->
                                methods += IosScriptMethodModel(
                                    registeredMethodName(annotation, functionName),
                                    functionName,
                                    functionArgCount,
                                )
                        }
                    }
                }
                pendingAnnotations.clear()
            }
            line.isNotBlank() && !line.startsWith("//") -> pendingAnnotations.clear()
        }
    }

    val dedupedMethods = methods.distinctBy { it.godotName }
    return IosScriptModel(
        sourceFile = sourceFile,
        resourcePath = resourcePathFor(sourceRoot, sourceFile),
        packageName = packageName,
        className = className,
        baseType = baseType,
        methods = dedupedMethods,
    )
}

fun generateIosRegistrySource(models: List<IosScriptModel>): String {
    val imports = models
        .mapNotNull { model -> model.packageName?.let { "$it.${model.className}" } }
        .distinct()
        .sorted()
    val builder = StringBuilder()
    builder.appendLine("package net.multigesture.kanama.ios")
    builder.appendLine()
    builder.appendLine("import java.lang.foreign.MemorySegment")
    imports.forEach { builder.appendLine("import $it") }
    builder.appendLine()
    builder.appendLine("@Suppress(\"unused\")")
    builder.appendLine("internal fun registerKanamaIosProjectScripts() {")
    builder.appendLine("    if (KanamaIosGeneratedProjectScripts.registered) return")
    builder.appendLine("    KanamaIosGeneratedProjectScripts.registered = true")
    models.forEach { model ->
        val bridgeName = "${model.className}IosBridge_${shortHash(model.resourcePath)}"
        builder.appendLine("    KanamaIosProjectRegistry.register(")
        builder.appendLine("        KanamaIosScriptDescriptor(")
        builder.appendLine("            path = ${kotlinString(model.resourcePath)},")
        builder.appendLine("            baseType = ${kotlinString(model.baseType)},")
        builder.appendLine("            methods = listOf(")
        model.methods.forEach { method ->
            builder.appendLine(
                "                KanamaIosScriptMethod(${kotlinString(method.godotName)}, ${method.argumentCount}),",
            )
        }
        builder.appendLine("            ),")
        builder.appendLine("            factory = { ownerObject ->")
        builder.appendLine(
            "                $bridgeName(${model.className}(MemorySegment.ofAddress(ownerObject)))",
        )
        builder.appendLine("            },")
        builder.appendLine("        ),")
        builder.appendLine("    )")
    }
    builder.appendLine("}")
    builder.appendLine()
    builder.appendLine("private object KanamaIosGeneratedProjectScripts {")
    builder.appendLine("    var registered: Boolean = false")
    builder.appendLine("}")
    models.forEach { model ->
        val bridgeName = "${model.className}IosBridge_${shortHash(model.resourcePath)}"
        builder.appendLine()
        builder.appendLine("private class $bridgeName(")
        builder.appendLine("    private val script: ${model.className},")
        builder.appendLine(") : KanamaIosScriptBridge {")
        builder.appendLine("    override fun call(methodName: String, firstArg: Double): Boolean = when (methodName) {")
        model.methods.forEach { method ->
            val invocation = if (method.argumentCount == 1) {
                "script.${method.kotlinName}(firstArg)"
            } else {
                "script.${method.kotlinName}()"
            }
            builder.appendLine("        ${kotlinString(method.godotName)} -> { $invocation; true }")
        }
        builder.appendLine("        else -> false")
        builder.appendLine("    }")
        builder.appendLine("}")
    }
    return builder.toString()
}

val configuredIosScriptDirs =
    providers.gradleProperty("kanamaIosProjectScriptsDirs")
        .orElse(providers.gradleProperty("kanamaIosProjectScriptsDir"))
        .orElse(providers.gradleProperty("kanamaProjectScriptsDirs"))
        .orElse(providers.gradleProperty("kanamaProjectScriptsDir"))
val activeIosScriptDirs = configuredIosScriptDirs.orElse("")
val generatedIosProjectScriptsDir = layout.buildDirectory.dir("generated/iosProjectScripts/kotlin")
val generatedIosProjectScriptsSourceDir = generatedIosProjectScriptsDir.get().asFile
val generateIosProjectScriptRegistry by tasks.registering {
    val outputFile = generatedIosProjectScriptsDir.map {
        it.file("net/multigesture/kanama/ios/KanamaIosProjectRegistry.generated.kt")
    }
    inputs.property("kanamaIosProjectScriptsDirs", activeIosScriptDirs)
    inputs.files(activeIosScriptDirs.map { raw ->
        iosScriptDirs(raw).map { file(it) }
    })
    outputs.file(outputFile)
    doLast {
        val sourceRoots = iosScriptDirs(activeIosScriptDirs.get()).map { file(it) }.filter { it.exists() }
        val models = sourceRoots
            .flatMap { root ->
                root.walkTopDown()
                    .filter { it.isFile && it.extension == "kt" }
                    .mapNotNull { parseIosScript(root, it) }
                    .toList()
            }
            .sortedBy { it.resourcePath }
        val packageLessScripts = models.filter { it.packageName == null }
        check(packageLessScripts.isEmpty()) {
            "Kanama iOS project scripts must declare a package; default-package scripts cannot be " +
                "referenced from the generated iOS registry: " +
                packageLessScripts.joinToString { it.sourceFile.absolutePath }
        }
        outputFile.get().asFile.apply {
            parentFile.mkdirs()
            writeText(generateIosRegistrySource(models))
        }
    }
}

kotlin {
    iosArm64()
    iosSimulatorArm64()

    targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget>().configureEach {
        compilations.getByName("main") {
            cinterops {
                val kanama_ios by creating {
                    defFile(project.file("src/nativeInterop/cinterop/kanama_ios.def"))
                    includeDirs(rootProject.file("ios/include"))
                }
            }
        }

        binaries {
            staticLib {
                baseName = "kanama_ios_runtime"
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib"))
            }
        }
        val iosMain by creating {
            dependsOn(commonMain)
            kotlin.srcDir(generatedIosProjectScriptsSourceDir)
            iosScriptDirs(configuredIosScriptDirs.orNull).forEach { kotlin.srcDir(file(it)) }
        }
        val iosArm64Main by getting {
            dependsOn(iosMain)
        }
        val iosSimulatorArm64Main by getting {
            dependsOn(iosMain)
        }
    }
}

tasks.matching { it.name.startsWith("compileKotlinIos") }.configureEach {
    dependsOn(generateIosProjectScriptRegistry)
}
