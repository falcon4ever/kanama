plugins {
    kotlin("jvm") version "2.3.0" apply false
    id("com.google.devtools.ksp") version "2.3.0" apply false
}

group = "net.multigesture.kanama"
version = "0.1.0"

subprojects {
    apply(plugin = "maven-publish")

    group = "net.multigesture.kanama"
    version = rootProject.version

    repositories {
        mavenCentral()
    }

    pluginManager.withPlugin("org.jetbrains.kotlin.jvm") {
        extensions.configure<JavaPluginExtension> {
            withSourcesJar()
        }

        extensions.configure<PublishingExtension> {
            publications {
                create<MavenPublication>("maven") {
                    from(components["java"])
                }
            }
        }
    }
}

// Root project is the main kanama library module.
apply(plugin = "org.jetbrains.kotlin.jvm")
apply(plugin = "com.google.devtools.ksp")
apply(plugin = "maven-publish")

repositories {
    mavenCentral()
}

configure<org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension> {
    jvmToolchain(25)

    sourceSets.named("main") {
        kotlin.srcDir(layout.buildDirectory.dir("generated/sources/kanamaReal/main/kotlin"))
    }
}

val generateKanamaReal by tasks.registering {
    val precision = providers.gradleProperty("kanamaPrecision").orElse("single")
    val outputFile = layout.buildDirectory.file(
        "generated/sources/kanamaReal/main/kotlin/net/multigesture/kanama/types/Real.kt",
    )
    inputs.property("kanamaPrecision", precision)
    outputs.file(outputFile)

    doLast {
        val selected = precision.get()
        val isDouble = when (selected) {
            "single" -> false
            "double" -> true
            else -> throw GradleException("kanamaPrecision must be 'single' or 'double', got '$selected'")
        }
        outputFile.get().asFile.apply {
            parentFile.mkdirs()
            writeText(
                """
                |package net.multigesture.kanama.types
                |
                |import java.lang.foreign.MemorySegment
                |import java.lang.foreign.ValueLayout.${if (isDouble) "JAVA_DOUBLE" else "JAVA_FLOAT"}
                |
                |/**
                | * Godot's `real_t` scalar.
                | *
                | * Kanama builds default to single precision (`Float`), matching normal Godot desktop
                | * builds. Compile with `-PkanamaPrecision=double` for Godot builds made with
                | * `precision=double`.
                | */
                |typealias real_t = ${if (isDouble) "Double" else "Float"}
                |
                |object GodotReal {
                |    const val SIZE_BYTES: Long = ${if (isDouble) "8L" else "4L"}
                |    const val ALIGN_BYTES: Long = ${if (isDouble) "8L" else "4L"}
                |
                |    fun fromNumber(value: Number): real_t = value.${if (isDouble) "toDouble()" else "toFloat()"}
                |    fun fromDouble(value: Double): real_t = ${if (isDouble) "value" else "value.toFloat()"}
                |    fun fromFloat(value: Float): real_t = ${if (isDouble) "value.toDouble()" else "value"}
                |
                |    fun byteOffset(index: Long): Long = index * SIZE_BYTES
                |
                |    fun readIndex(segment: MemorySegment, index: Long): real_t =
                |        segment.get(${if (isDouble) "JAVA_DOUBLE" else "JAVA_FLOAT"}, byteOffset(index))
                |
                |    fun writeIndex(segment: MemorySegment, index: Long, value: real_t) {
                |        segment.set(${if (isDouble) "JAVA_DOUBLE" else "JAVA_FLOAT"}, byteOffset(index), value)
                |    }
                |}
                |""".trimMargin(),
            )
        }
    }
}

tasks.named("compileKotlin") {
    dependsOn(generateKanamaReal)
}

tasks.matching { it.name == "kspKotlin" }.configureEach {
    dependsOn(generateKanamaReal)
}

configure<JavaPluginExtension> {
    withSourcesJar()
}

configure<PublishingExtension> {
    publications {
        create<MavenPublication>("maven") {
            artifactId = "kanama"
            from(components["java"])
        }
    }
}

dependencies {
    "implementation"(project(":annotations"))
    "implementation"("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
    "ksp"(project(":processor"))
}

fun hostNativeBootstrapArtifactName(): String {
    val osName = System.getProperty("os.name").lowercase()
    return when {
        osName.contains("mac") || osName.contains("darwin") -> "libkanama_bootstrap.dylib"
        osName.contains("windows") -> "kanama_bootstrap.dll"
        else -> "libkanama_bootstrap.so"
    }
}

val nativeBootstrapArtifact = layout.projectDirectory.file(
    "example_project/addons/kanama/${hostNativeBootstrapArtifactName()}",
)
val shouldBuildNativeBootstrap = providers.gradleProperty("kanamaBuildNativeBootstrap")
    .map(String::toBoolean)
    .orElse(true)
val nativeBootstrapBuildDir = layout.buildDirectory.dir("bootstrap")

val configureNativeBootstrap by tasks.registering(Exec::class) {
    group = "build"
    description = "Configure the host native Kanama bootstrap CMake build."

    inputs.file(layout.projectDirectory.file("bootstrap/CMakeLists.txt"))
    inputs.file(layout.projectDirectory.file("bootstrap/bootstrap.c"))
    inputs.dir(layout.projectDirectory.dir("gdextension"))
    outputs.dir(nativeBootstrapBuildDir)

    onlyIf {
        shouldBuildNativeBootstrap.get()
    }

    commandLine(
        "cmake",
        "-S",
        layout.projectDirectory.dir("bootstrap").asFile.absolutePath,
        "-B",
        nativeBootstrapBuildDir.get().asFile.absolutePath,
        "-DCMAKE_BUILD_TYPE=Release",
    )
}

val buildNativeBootstrap by tasks.registering(Exec::class) {
    group = "build"
    description = "Build the host native Kanama bootstrap library with CMake."

    dependsOn(configureNativeBootstrap)
    inputs.file(layout.projectDirectory.file("bootstrap/CMakeLists.txt"))
    inputs.file(layout.projectDirectory.file("bootstrap/bootstrap.c"))
    inputs.dir(layout.projectDirectory.dir("gdextension"))
    inputs.dir(nativeBootstrapBuildDir)

    onlyIf {
        shouldBuildNativeBootstrap.get()
    }

    commandLine(
        "cmake",
        "--build",
        nativeBootstrapBuildDir.get().asFile.absolutePath,
        "--config",
        "Release",
    )

    doLast {
        if (!nativeBootstrapArtifact.asFile.isFile) {
            throw GradleException(
                "Native bootstrap build completed but ${nativeBootstrapArtifact.asFile.absolutePath} was not created",
            )
        }
    }
}

fun File.enableAndroidKanamaGdextensionMetadata() {
    val requiredLibraryLines = listOf(
        "android.debug.arm64 = \"libkanama_bootstrap.so\"",
        "android.release.arm64 = \"libkanama_bootstrap.so\"",
        "android.debug.x86_64 = \"libkanama_bootstrap.so\"",
        "android.release.x86_64 = \"libkanama_bootstrap.so\"",
    )
    val lines = readLines().toMutableList()
    if ("android_aar_plugin = true" !in lines) {
        val librariesIndex = lines.indexOf("[libraries]").takeIf { it >= 0 } ?: lines.size
        val insertIndex = if (librariesIndex > 0 && lines[librariesIndex - 1].isBlank()) {
            librariesIndex - 1
        } else {
            librariesIndex
        }
        lines.add(insertIndex, "android_aar_plugin = true")
    }

    val missingLibraryLines = requiredLibraryLines.filter { it !in lines }
    if (missingLibraryLines.isNotEmpty()) {
        val librariesIndex = lines.indexOf("[libraries]").takeIf { it >= 0 }
            ?: run {
                if (lines.lastOrNull()?.isNotBlank() == true) {
                    lines.add("")
                }
                lines.add("[libraries]")
                lines.lastIndex
            }
        val insertIndex = lines.withIndex()
            .drop(librariesIndex + 1)
            .firstOrNull { it.value.startsWith("[") }
            ?.index
            ?: lines.size
        lines.addAll(insertIndex, missingLibraryLines)
    }
    writeText(lines.joinToString(System.lineSeparator()) + System.lineSeparator())
}

tasks.named<Jar>("jar") {
    archiveFileName.set("kanama.jar")
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    isPreserveFileTimestamps = false
    isReproducibleFileOrder = true
    // Bundle kotlin-stdlib (and any other runtime deps) into a single
    // self-contained jar. bootstrap.c puts this jar alone on the
    // classpath, so anything Kanama or its users need must be inside.
    from({
        configurations.named("runtimeClasspath").get()
            .map { if (it.isDirectory) it else zipTree(it) }
    }) {
        exclude("META-INF/*.SF", "META-INF/*.DSA", "META-INF/*.RSA", "META-INF/MANIFEST.MF")
    }
}

tasks.register<Copy>("syncExampleAddonJar") {
    dependsOn(buildNativeBootstrap)
    dependsOn(tasks.named("jar"))
    dependsOn(":project-scripts:jar")
    from(layout.buildDirectory.file("libs/kanama.jar"))
    from(layout.projectDirectory.file("project-scripts/build/libs/kanama-scripts.jar"))
    into(layout.projectDirectory.dir("example_project/addons/kanama"))
}

tasks.register("publishKanamaToMavenLocal") {
    group = "publishing"
    description = "Publish Kanama runtime, annotations, and KSP processor jars to mavenLocal()."
    dependsOn(
        tasks.named("publishToMavenLocal"),
        ":annotations:publishToMavenLocal",
        ":processor:publishToMavenLocal",
    )
}

tasks.register<Copy>("installAddonJar") {
    val targetProjectDir = providers.gradleProperty("kanamaProjectDir")
    val extensionListFile = targetProjectDir.map {
        file(it).resolve(".godot/extension_list.cfg")
    }
    val preserveAndroidExtensionMetadata = objects.property<Boolean>().convention(false)
    val byteStableFiles = setOf("kanama.jar", "kanama-scripts.jar")

    dependsOn(tasks.named("jar"))
    dependsOn(":project-scripts:jar")
    dependsOn(buildNativeBootstrap)

    from(layout.projectDirectory.dir("example_project/addons/kanama")) {
        include("*.gdextension", "*.uid", "*.dylib", "*.so", "*.dll")
    }
    from(layout.buildDirectory.file("libs/kanama.jar"))
    from(layout.projectDirectory.file("project-scripts/build/libs/kanama-scripts.jar"))
    into(targetProjectDir.map { file(it).resolve("addons/kanama") })
    outputs.file(extensionListFile)

    eachFile(object : org.gradle.api.Action<org.gradle.api.file.FileCopyDetails> {
        override fun execute(details: org.gradle.api.file.FileCopyDetails) {
            if (details.name in byteStableFiles && targetProjectDir.isPresent) {
                val installedFile: File = file(targetProjectDir.get())
                    .resolve("addons/kanama")
                    .resolve(details.path)
                if (
                    installedFile.isFile &&
                    java.nio.file.Files.mismatch(details.file.toPath(), installedFile.toPath()) == -1L
                ) {
                    details.exclude()
                }
            }
        }
    })

    doFirst {
        if (!targetProjectDir.isPresent) {
            throw GradleException(
                "Missing -PkanamaProjectDir=/absolute/path/to/godot_project for installAddonJar",
            )
        }

        val targetProject = file(targetProjectDir.get())
        val existingExtension = targetProject.resolve("addons/kanama/kanama.gdextension")
        preserveAndroidExtensionMetadata.set(
            targetProject.resolve("android/plugins/KanamaAndroid.gdap").isFile ||
                (existingExtension.isFile && existingExtension.readText().contains("android_aar_plugin = true")),
        )
    }

    doLast {
        val extensionList = extensionListFile.get()
        val extensionPath = "res://addons/kanama/kanama.gdextension"
        extensionList.parentFile.mkdirs()
        val existing = if (extensionList.isFile) extensionList.readLines() else emptyList()
        if (extensionPath !in existing) {
            extensionList.writeText((existing + extensionPath).joinToString(System.lineSeparator()) + System.lineSeparator())
        }

        if (preserveAndroidExtensionMetadata.get()) {
            val extensionFile = file(targetProjectDir.get()).resolve("addons/kanama/kanama.gdextension")
            extensionFile.enableAndroidKanamaGdextensionMetadata()
        }
    }
}

tasks.register<Copy>("installStarterTemplate") {
    val targetProjectDir = providers.gradleProperty("kanamaStarterProjectDir")
    from(layout.projectDirectory.dir("templates/starter"))
    into(targetProjectDir.map { file(it) })

    doFirst {
        if (!targetProjectDir.isPresent) {
            throw GradleException(
                "Missing -PkanamaStarterProjectDir=/absolute/path/to/godot_project for installStarterTemplate",
            )
        }
    }
}

val kanamaAndroidDemoDir = providers.gradleProperty("kanamaAndroidDemoDir")
val kanamaAndroidSdkDir = providers.environmentVariable("ANDROID_HOME")
    .orElse(providers.environmentVariable("ANDROID_SDK_ROOT"))

tasks.register<Exec>("assembleAndroidPluginAar") {
    group = "android"
    description = "Build the experimental Kanama Android Godot plugin AAR for a demo project."

    doFirst {
        if (!kanamaAndroidDemoDir.isPresent) {
            throw GradleException(
                "Missing -PkanamaAndroidDemoDir=/absolute/path/to/kanama demo project",
            )
        }
        if (!kanamaAndroidSdkDir.isPresent) {
            throw GradleException(
                "Missing ANDROID_HOME or ANDROID_SDK_ROOT for Android plugin build",
            )
        }
        environment("ANDROID_HOME", kanamaAndroidSdkDir.get())
        environment("ANDROID_SDK_ROOT", kanamaAndroidSdkDir.get())
        commandLine(
            layout.projectDirectory.file("gradlew").asFile.absolutePath,
            "-p",
            "android/godot-plugin",
            ":plugin:assembleDebug",
            "-PkanamaAndroidDemoDir=${kanamaAndroidDemoDir.get()}",
        )
    }
}

tasks.register("installAndroidPluginAar") {
    group = "android"
    description = "Build and install the experimental Kanama Android plugin AAR into a demo project."
    dependsOn(tasks.named("assembleAndroidPluginAar"))

    doLast {
        if (!kanamaAndroidDemoDir.isPresent) {
            throw GradleException(
                "Missing -PkanamaAndroidDemoDir=/absolute/path/to/kanama demo project",
            )
        }

        val pluginsDir = file(kanamaAndroidDemoDir.get()).resolve("android/plugins")
        pluginsDir.mkdirs()

        copy {
            from(layout.projectDirectory.file("android/godot-plugin/plugin/build/outputs/aar/plugin-debug.aar"))
            into(pluginsDir)
            rename { "KanamaAndroid.debug.aar" }
        }

        pluginsDir.resolve("KanamaAndroid.gdap").writeText(
            """
            |[config]
            |
            |name="KanamaAndroid"
            |binary_type="local"
            |binary="KanamaAndroid.debug.aar"
            |
            |[dependencies]
            |remote=["io.github.vova7878.panama:Core:v0.1.3"]
            |""".trimMargin(),
        )

        val extensionFile = file(kanamaAndroidDemoDir.get()).resolve("addons/kanama/kanama.gdextension")
        if (!extensionFile.isFile) {
            throw GradleException("Missing Kanama GDExtension descriptor: ${extensionFile.absolutePath}")
        }
        extensionFile.enableAndroidKanamaGdextensionMetadata()
    }
}
