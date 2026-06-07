plugins {
    kotlin("multiplatform")
}

import java.io.File
import java.security.MessageDigest

data class IosScriptMethodModel(
    val godotName: String,
    val kotlinName: String,
    val argumentCount: Int,
    val bridgeKind: IosScriptBridgeKind,
)

enum class IosScriptBridgeKind {
    ZERO_ARG,
    DOUBLE_ARG,
    OBJECT_ARG,
    UNSUPPORTED,
}

data class IosScriptPropertyModel(
    val godotName: String,
    val kotlinName: String,
)

data class IosScriptSignalModel(
    val godotName: String,
    val kotlinName: String,
)

data class IosScriptModel(
    val sourceFile: File,
    val resourcePath: String,
    val packageName: String?,
    val className: String,
    val baseType: String,
    val methods: List<IosScriptMethodModel>,
    val properties: List<IosScriptPropertyModel>,
    val signals: List<IosScriptSignalModel>,
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
    return explicit?.takeIf { it.isNotBlank() } ?: camelToSnake(functionName)
}

fun camelToSnake(name: String): String =
    buildString {
        name.forEachIndexed { index, ch ->
            if (index > 0 && ch.isUpperCase()) append('_')
            append(ch.lowercaseChar())
        }
    }

fun constantIdentifier(name: String): String {
    val stripped = name.trim('_')
    if (stripped.isBlank()) return "value"
    val words = stripped
        .split('_', '-', ' ')
        .filter { it.isNotBlank() }
    val candidate = words.drop(1).fold(words.first()) { acc, part ->
        acc + part.replaceFirstChar { it.uppercaseChar() }
    }
    return candidate.replace(Regex("""[^A-Za-z0-9_]"""), "_")
        .let { if (it.firstOrNull()?.isDigit() == true) "_$it" else it }
}

fun functionParams(raw: String): List<String> {
    val trimmed = raw.trim()
    if (trimmed.isBlank()) return emptyList()
    return trimmed
        .split(",")
        .map { it.trim() }
        .filter { it.isNotEmpty() }
}

fun bridgeKindFor(godotName: String, params: List<String>): IosScriptBridgeKind =
    when {
        params.isEmpty() -> IosScriptBridgeKind.ZERO_ARG
        params.size == 1 && (godotName == "_process" || godotName == "_physics_process") ->
            IosScriptBridgeKind.DOUBLE_ARG
        params.size == 1 && godotName == "_input" ->
            IosScriptBridgeKind.OBJECT_ARG
        else -> IosScriptBridgeKind.UNSUPPORTED
    }

fun isIosScriptPropertyAnnotation(annotation: String): Boolean =
    annotation.contains("ScriptProperty") ||
        Regex("""@\s*(?:net\.multigesture\.kanama\.annotations\.)?Export\s*(?:\(|$)""")
            .containsMatchIn(annotation)

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
    val properties = mutableListOf<IosScriptPropertyModel>()
    val signals = mutableListOf<IosScriptSignalModel>()
    val pendingAnnotations = mutableListOf<String>()
    text.lineSequence().forEach { rawLine ->
        val line = rawLine.trim()
        when {
            line.startsWith("@") -> pendingAnnotations += line
            line.startsWith("fun ") || line.contains(" fun ") -> {
                val functionMatch = Regex("""\bfun\s+([A-Za-z_][A-Za-z0-9_]*)\s*\(([^)]*)\)""").find(line)
                if (functionMatch != null) {
                    val functionName = functionMatch.groupValues[1]
                    val params = functionParams(functionMatch.groupValues[2])
                    pendingAnnotations.forEach { annotation ->
                        when {
                            annotation.contains("OnReady") || annotation.contains("@Ready") ->
                                methods += IosScriptMethodModel("_ready", functionName, 0, IosScriptBridgeKind.ZERO_ARG)
                            annotation.contains("OnExitTree") || annotation.contains("@ExitTree") ->
                                methods += IosScriptMethodModel("_exit_tree", functionName, 0, IosScriptBridgeKind.ZERO_ARG)
                            annotation.contains("OnProcess") || annotation.contains("@Process") ->
                                methods += IosScriptMethodModel("_process", functionName, 1, IosScriptBridgeKind.DOUBLE_ARG)
                            annotation.contains("OnPhysicsProcess") || annotation.contains("@PhysicsProcess") ->
                                methods += IosScriptMethodModel("_physics_process", functionName, 1, IosScriptBridgeKind.DOUBLE_ARG)
                            annotation.contains("OnInput") || annotation.contains("@Input") ->
                                methods += IosScriptMethodModel("_input", functionName, 1, IosScriptBridgeKind.OBJECT_ARG)
                            annotation.contains("RegisterFunction") || annotation.contains("@Method") ->
                                registeredMethodName(annotation, functionName).let { godotName ->
                                    methods += IosScriptMethodModel(
                                        godotName,
                                        functionName,
                                        params.size,
                                        bridgeKindFor(godotName, params),
                                    )
                                }
                            annotation.contains("Signal") ->
                                signals += IosScriptSignalModel(
                                    registeredMethodName(annotation, functionName),
                                    functionName,
                                )
                        }
                    }
                }
                pendingAnnotations.clear()
            }
            line.startsWith("var ") || line.startsWith("val ") || line.contains(" var ") || line.contains(" val ") -> {
                val propertyMatch = Regex("""\b(?:var|val)\s+([A-Za-z_][A-Za-z0-9_]*)\b""").find(line)
                if (propertyMatch != null && pendingAnnotations.any(::isIosScriptPropertyAnnotation)) {
                    val propertyName = propertyMatch.groupValues[1]
                    val annotation = pendingAnnotations.firstOrNull(::isIosScriptPropertyAnnotation).orEmpty()
                    properties += IosScriptPropertyModel(
                        godotName = registeredMethodName(annotation, propertyName),
                        kotlinName = propertyName,
                    )
                }
                pendingAnnotations.clear()
            }
            line.isNotBlank() && !line.startsWith("//") -> pendingAnnotations.clear()
        }
    }

    val dedupedMethods = methods.distinctBy { it.godotName }
    val dedupedProperties = properties.distinctBy { it.godotName }
    val dedupedSignals = signals.distinctBy { it.godotName }
    return IosScriptModel(
        sourceFile = sourceFile,
        resourcePath = resourcePathFor(sourceRoot, sourceFile),
        packageName = packageName,
        className = className,
        baseType = baseType,
        methods = dedupedMethods,
        properties = dedupedProperties,
        signals = dedupedSignals,
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
            val invocation = when (method.bridgeKind) {
                IosScriptBridgeKind.ZERO_ARG -> "script.${method.kotlinName}()"
                IosScriptBridgeKind.DOUBLE_ARG -> "script.${method.kotlinName}(firstArg)"
                IosScriptBridgeKind.OBJECT_ARG -> null
                IosScriptBridgeKind.UNSUPPORTED -> null
            }
            if (invocation != null) {
                builder.appendLine("        ${kotlinString(method.godotName)} -> { $invocation; true }")
            }
        }
        builder.appendLine("        else -> false")
        builder.appendLine("    }")
        builder.appendLine()
        builder.appendLine("    override fun callObject(methodName: String, objectArg: Long): Boolean = when (methodName) {")
        model.methods.forEach { method ->
            if (method.bridgeKind == IosScriptBridgeKind.OBJECT_ARG) {
                builder.appendLine(
                    "        ${kotlinString(method.godotName)} -> { script.${method.kotlinName}(net.multigesture.kanama.api.GodotObject(MemorySegment.ofAddress(objectArg))); true }",
                )
            }
        }
        builder.appendLine("        else -> false")
        builder.appendLine("    }")
        builder.appendLine("}")
    }
    return builder.toString()
}

fun generateIosGeneratedConstantsSource(models: List<IosScriptModel>): String {
    val builder = StringBuilder()
    builder.appendLine("package net.multigesture.kanama.generated")
    builder.appendLine()
    builder.appendLine("import net.multigesture.kanama.api.kotlinScriptInstance")
    builder.appendLine()
    builder.appendLine("@Suppress(\"unused\")")
    builder.appendLine("private fun emitIosSignal(instance: Any, signalName: String, args: Array<out Any?>) {")
    builder.appendLine("    val script = instance as? net.multigesture.kanama.api.KanamaScript<*> ?: return")
    builder.appendLine("    net.multigesture.kanama.api.GodotObject(script.godotObject).emitSignal(signalName, *args)")
    builder.appendLine("}")
    models.forEach { model ->
        if (model.signals.isNotEmpty()) {
            builder.appendLine()
            builder.appendLine("object ${model.className}Signals {")
            model.signals.forEach { signal ->
                val functionName = constantIdentifier(signal.kotlinName)
                val signalLiteral = kotlinString(signal.godotName)
                val fqClassName = "${model.packageName}.${model.className}"
                builder.appendLine("    fun $functionName(instance: $fqClassName, vararg args: Any?) {")
                builder.appendLine("        emitIosSignal(instance, $signalLiteral, args)")
                builder.appendLine("    }")
            }
            builder.appendLine("}")
        }
        val helperMethods = model.methods
            .filter { it.argumentCount == 0 && !it.godotName.startsWith("_") }
        if (helperMethods.isNotEmpty()) {
            val fqClassName = "${model.packageName}.${model.className}"
            builder.appendLine()
            builder.appendLine("object ${model.className}Methods {")
            helperMethods.forEach { method ->
                val functionName = constantIdentifier(method.kotlinName)
                builder.appendLine("    fun $functionName(instance: $fqClassName) {")
                builder.appendLine("        instance.${method.kotlinName}()")
                builder.appendLine("    }")
                builder.appendLine()
                builder.appendLine("    fun $functionName(target: net.multigesture.kanama.api.GodotObject): Boolean {")
                builder.appendLine("        val instance = target.kotlinScriptInstance<$fqClassName>() ?: return false")
                builder.appendLine("        $functionName(instance)")
                builder.appendLine("        return true")
                builder.appendLine("    }")
            }
            builder.appendLine("}")
        }
        val methods = model.methods
        val properties = model.properties
        val signals = model.signals
        if (methods.isNotEmpty() || properties.isNotEmpty() || signals.isNotEmpty()) {
            builder.appendLine()
            builder.appendLine("object ${model.className}Names {")
            if (methods.isNotEmpty()) {
                builder.appendLine("    object Methods {")
                methods.forEach { method ->
                    builder.appendLine(
                        "        const val ${constantIdentifier(method.kotlinName)}: String = ${kotlinString(method.godotName)}",
                    )
                }
                builder.appendLine("    }")
            }
            if (properties.isNotEmpty()) {
                builder.appendLine("    object Properties {")
                properties.forEach { property ->
                    builder.appendLine(
                        "        const val ${constantIdentifier(property.kotlinName)}: String = ${kotlinString(property.godotName)}",
                    )
                }
                builder.appendLine("    }")
            }
            if (signals.isNotEmpty()) {
                builder.appendLine("    object Signals {")
                signals.forEach { signal ->
                    builder.appendLine(
                        "        const val ${constantIdentifier(signal.kotlinName)}: String = ${kotlinString(signal.godotName)}",
                    )
                }
                builder.appendLine("    }")
            }
            builder.appendLine("}")
        }
    }
    return builder.toString()
}

fun generateIosCompatibilitySources(models: List<IosScriptModel>): Map<String, String> =
    models
        .mapNotNull { it.packageName }
        .distinct()
        .sorted()
        .associate { packageName ->
            val relativePath = "${packageName.replace('.', '/')}/KanamaIosCompatibility.generated.kt"
            val source = buildString {
                appendLine("package $packageName")
                appendLine()
                appendLine("@Suppress(\"unused\")")
                appendLine("internal fun <T> MutableCollection<T>.removeIf(predicate: (T) -> Boolean): Boolean {")
                appendLine("    val originalSize = size")
                appendLine("    removeAll(predicate)")
                appendLine("    return size != originalSize")
                appendLine("}")
                appendLine()
                appendLine("@Suppress(\"unused\")")
                appendLine("internal object System {")
                appendLine("    fun getenv(name: String): String? = null")
                appendLine("}")
            }
            relativePath to source
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
    val constantsOutputFile = generatedIosProjectScriptsDir.map {
        it.file("net/multigesture/kanama/generated/KanamaIosScriptConstants.generated.kt")
    }
    val compatibilityOutputDir = generatedIosProjectScriptsDir.map {
        it.dir("kanama-ios-compat")
    }
    inputs.property("kanamaIosProjectScriptsDirs", activeIosScriptDirs)
    inputs.files(activeIosScriptDirs.map { raw ->
        iosScriptDirs(raw).map { file(it) }
    })
    outputs.file(outputFile)
    outputs.file(constantsOutputFile)
    outputs.dir(compatibilityOutputDir)
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
        constantsOutputFile.get().asFile.apply {
            parentFile.mkdirs()
            writeText(generateIosGeneratedConstantsSource(models))
        }
        val compatibilityRoot = compatibilityOutputDir.get().asFile
        compatibilityRoot.deleteRecursively()
        generateIosCompatibilitySources(models).forEach { (relativePath, source) ->
            compatibilityRoot.resolve(relativePath).apply {
                parentFile.mkdirs()
                writeText(source)
            }
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
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.11.0")
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
