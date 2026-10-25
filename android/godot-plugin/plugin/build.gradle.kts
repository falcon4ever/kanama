plugins {
    id("com.android.library")
    `maven-publish`
}

// Runtime half of the Kanama Android deliverable (task 36 AAR split): the
// remapped Kanama runtime + annotations, the native bootstrap, and the Godot
// plugin classes — project-agnostic, no consumer sources. The consumer
// project's scripts + KSP registrars build separately in `:scripts`.
val kanamaRoot = rootProject.layout.projectDirectory.dir("../..")
val androidKanamaSources = layout.buildDirectory.dir("generated/kanamaAndroidSources")
val kanamaApiExercise = providers.gradleProperty("kanamaApiExercise").map { it.trim() == "true" }.getOrElse(false)
val panamaPortCoreDependency = providers.gradleProperty("kanamaPanamaPortCore")
    .orElse("com.github.falcon4ever.PanamaPort:Core:0.1.5-kanama-r8.1")

val prepareAndroidKanamaSources by tasks.registering(Sync::class) {
    into(androidKanamaSources)

    fun CopySpec.remapForeignImports() {
        filter { line: String ->
            KanamaAndroidRemap.remapLine(line)
        }
    }

    from(kanamaRoot.dir("src/jvmMain/kotlin")) {
        exclude("example/**")
        remapForeignImports()
    }
    // The root module's KMP common fragment (task 104 step 3 parcel C'): the value types,
    // GodotHandle, the expect seams and, since task 117 P4', the whole generated wrapper tree
    // (net/multigesture/kanama/api) -- this one copy carries it; there is no second tree root.
    // `*.expect.kt` files are skipped -- an expect declaration has no body to remap and Android
    // compiles the jvmMain actual with its `actual ` stripped.
    from(kanamaRoot.dir("src/commonMain/kotlin")) {
        exclude("**/*${KanamaAndroidRemap.EXPECT_FILE_SUFFIX}")
        // The generated API exercise rows: a Kanama test hook, compiled only into a runtime AAR
        // built with -PkanamaApiExercise=true (as the root build.gradle.kts does for the jar).
        if (!kanamaApiExercise) exclude("net/multigesture/kanama/exercise/**")
        remapForeignImports()
    }
    from(kanamaRoot.dir("annotations/src/main/kotlin")) {
        remapForeignImports()
    }

    doLast {
        // The root module generates the real_t storage width into commonMain (`generateKanamaReal`)
        // and the Panama accessors into jvmMain (`generateKanamaRealSegment`); neither is a tracked
        // source file, so the Android tree writes both here, single precision, with the remapped
        // FFM package. Value types store components at this width and expose them as Double (task 134).
        val realFile = androidKanamaSources.get().file(
            "net/multigesture/kanama/types/Real.kt",
        ).asFile
        realFile.parentFile.mkdirs()
        realFile.writeText(
            """
            |package net.multigesture.kanama.types
            |
            |import com.v7878.foreign.MemorySegment
            |import com.v7878.foreign.ValueLayout.JAVA_FLOAT
            |
            |typealias GodotRealArray = FloatArray
            |
            |internal typealias GodotRealStorage = Float
            |
            |internal const val REAL_IS_SINGLE: Boolean = true
            |
            |@Suppress("NOTHING_TO_INLINE")
            |internal inline fun narrowReal(value: Double): Float = value.toFloat()
            |
            |@Suppress("NOTHING_TO_INLINE")
            |internal inline fun widenReal(value: Float): Double = value.toDouble()
            |
            |object GodotReal {
            |    const val SIZE_BYTES: Long = 4L
            |    const val ALIGN_BYTES: Long = 4L
            |
            |    fun toC(value: Double): Float = value.toFloat()
            |    fun fromC(value: Float): Double = value.toDouble()
            |
            |    fun byteOffset(index: Long): Long = index * SIZE_BYTES
            |}
            |
            |object GodotRealSegment {
            |    internal fun readRaw(segment: MemorySegment, index: Long): Float =
            |        segment.get(JAVA_FLOAT, index * GodotReal.SIZE_BYTES)
            |
            |    internal fun writeRaw(segment: MemorySegment, index: Long, value: Float) {
            |        segment.set(JAVA_FLOAT, index * GodotReal.SIZE_BYTES, value)
            |    }
            |
            |    fun readIndex(segment: MemorySegment, index: Long): Double =
            |        segment.get(JAVA_FLOAT, index * GodotReal.SIZE_BYTES).toDouble()
            |
            |    fun writeIndex(segment: MemorySegment, index: Long, value: Double) {
            |        segment.set(JAVA_FLOAT, index * GodotReal.SIZE_BYTES, value.toFloat())
            |    }
            |}
            |""".trimMargin(),
        )
    }
}

val auditAndroidKanamaSources by tasks.registering {
    dependsOn(prepareAndroidKanamaSources)
    inputs.dir(androidKanamaSources)

    doLast {
        KanamaAndroidRemap.auditGeneratedSources(androidKanamaSources.get().asFile, "runtime")
    }
}

android {
    namespace = "net.multigesture.kanama.android"
    compileSdk = 36

    defaultConfig {
        // Android 13: release builds crash at startup below it (PanamaPort). The export guard's
        // ANDROID_MIN_SDK (templates/starter/addons/kanama_tools/kanama_export_guard.gd) must match.
        minSdk = 33
        consumerProguardFiles("consumer-rules.pro")
        externalNativeBuild {
            cmake {
                abiFilters += listOf("arm64-v8a", "x86_64")
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    externalNativeBuild {
        cmake {
            path = file("src/main/cpp/CMakeLists.txt")
        }
    }

    sourceSets {
        named("main") {
            jniLibs.setSrcDirs(emptyList<File>())
        }
    }

    // The runtime AAR as the Maven artifact kits and the store addon carry (task 135 item 5).
    publishing { singleVariant("debug") }
}

// net.multigesture.kanama:kanama-android, published into the root build's package repository
// (build/package/maven, which becomes addons/kanama/maven) by the root task
// publishKanamaAndroidPackage, which passes -PkanamaVersion. A kit project's buildAndroidScripts
// compiles the scripts AAR against it and installs it as android/plugins/KanamaAndroid.debug.aar.
// Debug only, as the checkout's installAndroidPluginAar: a release APK is Godot's R8 over it.
val kanamaVersion = providers.gradleProperty("kanamaVersion")
afterEvaluate {
    extensions.configure<PublishingExtension> {
        publications {
            create<MavenPublication>("kanamaAndroid") {
                from(components["debug"])
                groupId = "net.multigesture.kanama"
                artifactId = "kanama-android"
                version = kanamaVersion.getOrElse("unspecified")
            }
        }
        repositories {
            maven {
                name = "kanamaPackage"
                url = kanamaRoot.dir("build/package/maven").asFile.toURI()
            }
        }
    }
}
tasks.matching { it.name.startsWith("publish") }.configureEach {
    doFirst {
        if (!kanamaVersion.isPresent) {
            throw GradleException("Publish kanama-android through the root build: ./gradlew publishKanamaAndroidPackage")
        }
    }
}

// The APK runs on the Kotlin libraries of Godot's Android build template (4.7.2: kotlin-stdlib
// 2.1.21 and kotlinx-coroutines 1.6.4 on its runtime classpath), not on the newer stdlib this
// compiles against: a stdlib API newer than 2.1 would compile here and fail with NoSuchMethodError
// on the device, so the API level keeps it out. On a Godot re-pin, re-check the template's
// kotlinVersion (config.gradle in android_source.zip; docs/contributing/godot-upgrade.md).
// Kotlin 2.4 already warns that API version 2.1 is deprecated (its first non-deprecated one is 2.2);
// if the usual cycle holds, a Kotlin around 2.6 refuses it, so a root Kotlin bump then breaks this
// build until Godot's template moves past 2.1.21. That is the signal to re-check, not to drop this.
kotlin {
    compilerOptions {
        apiVersion.set(org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_2_1)
    }
}

androidComponents {
    onVariants(selector().all()) { variant ->
        variant.sources.kotlin?.addStaticSourceDirectory("build/generated/kanamaAndroidSources")
    }
}

tasks.named("preBuild") {
    dependsOn(auditAndroidKanamaSources)
}

// A runtime AAR with the API exercise rows (-PkanamaApiExercise=true) is for the debug APK of
// scripts/android_smoke.sh only: no release variant or publication of it is built (the root build's
// publishKanamaAndroidPackage also reads the AAR it publishes for exercise classes).
gradle.taskGraph.whenReady {
    if (!kanamaApiExercise) return@whenReady
    val refused = allTasks.map { it.path }.filter { it.contains("Release") || it.substringAfterLast(':').startsWith("publish") }
    if (refused.isNotEmpty()) {
        throw GradleException(
            "-PkanamaApiExercise=true builds the API exercise rows into the runtime AAR (a Kanama test hook " +
                "for android_smoke.sh's debug APK); refusing: ${refused.take(3).joinToString()}" +
                (if (refused.size > 3) " and ${refused.size - 3} more" else "") + ". Run without the flag.",
        )
    }
}

dependencies {
    compileOnly(project(":godot-stubs"))
    implementation(panamaPortCoreDependency)
    // The APK's coroutines come from Godot's template, so compile against that version (gradle.properties).
    compileOnly("org.jetbrains.kotlinx:kotlinx-coroutines-core:${providers.gradleProperty("kanamaAndroidCoroutinesVersion").get()}")
}
