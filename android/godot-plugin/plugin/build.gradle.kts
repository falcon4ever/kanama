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
    .orElse("com.github.falcon4ever.PanamaPort:Core:0.1.3-kanama-r8.4")

val prepareAndroidKanamaSources by tasks.registering(Sync::class) {
    into(androidKanamaSources)

    fun CopySpec.remapForeignImports() {
        filter { line: String ->
            KanamaAndroidRemap.remapLine(line)
        }
    }

    from(kanamaRoot.dir("src/main/kotlin")) {
        exclude("example/**")
        remapForeignImports()
    }
    from(kanamaRoot.dir("annotations/src/main/kotlin")) {
        remapForeignImports()
    }

    doLast {
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
            |typealias real_t = Float
            |
            |object GodotReal {
            |    const val SIZE_BYTES: Long = 4L
            |    const val ALIGN_BYTES: Long = 4L
            |
            |    fun fromNumber(value: Number): real_t = value.toFloat()
            |    fun fromDouble(value: Double): real_t = value.toFloat()
            |    fun fromFloat(value: Float): real_t = value
            |
            |    fun byteOffset(index: Long): Long = index * SIZE_BYTES
            |
            |    fun readIndex(segment: MemorySegment, index: Long): real_t =
            |        segment.get(JAVA_FLOAT, byteOffset(index))
            |
            |    fun writeIndex(segment: MemorySegment, index: Long, value: real_t) {
            |        segment.set(JAVA_FLOAT, byteOffset(index), value)
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
