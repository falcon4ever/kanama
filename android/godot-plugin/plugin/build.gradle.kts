plugins {
    id("com.android.library")
}

// Runtime half of the Kanama Android deliverable (task 36 AAR split): the
// remapped Kanama runtime + annotations, the native bootstrap, and the Godot
// plugin classes — project-agnostic, no consumer sources. The consumer
// project's scripts + KSP registrars build separately in `:scripts`.
val kanamaRoot = rootProject.layout.projectDirectory.dir("../..")
val androidKanamaSources = layout.buildDirectory.dir("generated/kanamaAndroidSources")
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
        remapForeignImports()
    }
    from(kanamaRoot.dir("annotations/src/main/kotlin")) {
        remapForeignImports()
    }

    doLast {
        // The root module generates the real_t storage width into commonMain (`generateKanamaReal`)
        // and the Panama accessors into jvmMain (`generateKanamaRealSegment`); neither is a tracked
        // source file, so the Android tree writes both here, single precision, with the remapped
        // FFM package. Components are Double (task 134); only the engine buffers are float32.
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
        minSdk = 26
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
}

androidComponents {
    onVariants(selector().all()) { variant ->
        variant.sources.kotlin?.addStaticSourceDirectory("build/generated/kanamaAndroidSources")
    }
}

tasks.named("preBuild") {
    dependsOn(auditAndroidKanamaSources)
}

dependencies {
    compileOnly(project(":godot-stubs"))
    implementation(panamaPortCoreDependency)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.11.0")
}
