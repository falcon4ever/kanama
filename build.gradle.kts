plugins {
  kotlin("jvm") version "2.3.21" apply false
  kotlin("multiplatform") version "2.3.21" apply false
  id("com.google.devtools.ksp") version "2.3.9" apply false
  id("com.ncorti.ktfmt.gradle") version "0.22.0" apply false
}

group = "net.multigesture.kanama"

version = "0.4.0"

val packageMavenRepositoryDir = layout.buildDirectory.dir("package/maven")
val panamaPortCoreDependency =
  providers
    .gradleProperty("kanamaPanamaPortCore")
    .orElse("com.github.falcon4ever.PanamaPort:Core:0.1.3-kanama-r8.4")

fun PublishingExtension.addKanamaPackageRepository() {
  repositories {
    maven {
      name = "kanamaPackage"
      url = rootProject.layout.buildDirectory.dir("package/maven").get().asFile.toURI()
    }
  }
}

// ---------------------------------------------------------------------------
// ktfmt — Kotlin formatter, applied to every module.
//
// CUSTOMIZE ME: the `ktfmt { ... }` block below is the whole knob set — style,
// width, imports, trailing commas. Change it, then re-run `./gradlew ktfmtFormat`.
//
// IMPORTANT: the generated Godot API wrappers under **/net/multigesture/kanama/api/
// are EXCLUDED. They are byte-compared against scripts/generate_api_wrapper.py by the
// drift gate (check_wrapper_generator.py); formatting them would break it unless the
// generator itself emitted ktfmt-clean output. Only hand-written Kotlin is formatted.
// ---------------------------------------------------------------------------
allprojects {
  apply(plugin = "com.ncorti.ktfmt.gradle")
  configure<com.ncorti.ktfmt.gradle.KtfmtExtension> {
    // --- customizable knobs ---
    googleStyle() // base style; alternatives: kotlinLangStyle() / metaStyle()
    // googleStyle() already implies 2/2; kept explicit so intent survives a plugin bump.
    blockIndent.set(2) // 2-space block indent
    continuationIndent.set(2) // 2-space continuation indent
    // maxWidth.set(100)             // googleStyle default = 100
    // removeUnusedImports.set(true) // default = true
    // manageTrailingCommas.set(true)
  }
  tasks.withType<com.ncorti.ktfmt.gradle.tasks.KtfmtBaseTask>().configureEach {
    // Exclude the drift-gated generated API wrappers (see note above).
    exclude("**/net/multigesture/kanama/api/**")
  }
  // Don't format *.gradle.kts build scripts: ktfmt 0.54 mis-parses the Gradle DSL in
  // ios-runtime/build.gradle.kts, and build scripts aren't the formatting target here.
  tasks.matching { it.name.startsWith("ktfmt") && it.name.endsWith("Scripts") }
    .configureEach { enabled = false }
}

subprojects {
  apply(plugin = "maven-publish")

  group = "net.multigesture.kanama"
  version = rootProject.version

  repositories { mavenCentral() }

  extensions.configure<PublishingExtension> { addKanamaPackageRepository() }

  pluginManager.withPlugin("org.jetbrains.kotlin.jvm") {
    extensions.configure<JavaPluginExtension> { withSourcesJar() }

    extensions.configure<PublishingExtension> {
      publications { create<MavenPublication>("maven") { from(components["java"]) } }
    }
  }
}

// Root project is the main kanama library module.
apply(plugin = "org.jetbrains.kotlin.jvm")

apply(plugin = "com.google.devtools.ksp")

apply(plugin = "maven-publish")

repositories { mavenCentral() }

configure<org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension> {
  jvmToolchain(25)

  sourceSets.named("main") {
    kotlin.srcDir(layout.buildDirectory.dir("generated/sources/kanamaReal/main/kotlin"))
  }
}

val generateKanamaReal by
  tasks.registering {
    val precision = providers.gradleProperty("kanamaPrecision").orElse("single")
    val outputFile =
      layout.buildDirectory.file(
        "generated/sources/kanamaReal/main/kotlin/net/multigesture/kanama/types/Real.kt"
      )
    inputs.property("kanamaPrecision", precision)
    outputs.file(outputFile)

    doLast {
      val selected = precision.get()
      val isDouble =
        when (selected) {
          "single" -> false
          "double" -> true
          else ->
            throw GradleException("kanamaPrecision must be 'single' or 'double', got '$selected'")
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
                |"""
            .trimMargin()
        )
      }
    }
  }

tasks.named("compileKotlin") { dependsOn(generateKanamaReal) }

tasks.matching { it.name == "kspKotlin" }.configureEach { dependsOn(generateKanamaReal) }

configure<JavaPluginExtension> { withSourcesJar() }

configure<PublishingExtension> {
  publications {
    create<MavenPublication>("maven") {
      artifactId = "kanama"
      from(components["java"])
    }
  }
  addKanamaPackageRepository()
}

dependencies {
  "implementation"(project(":annotations"))
  "implementation"(project(":kanama-common-api"))
  "implementation"("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.11.0")
  "ksp"(project(":processor"))
  "testImplementation"(kotlin("test-junit5"))
  "testRuntimeOnly"("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test>().configureEach { useJUnitPlatform() }

fun hostNativeBootstrapArtifactName(): String {
  val osName = System.getProperty("os.name").lowercase()
  return when {
    osName.contains("mac") || osName.contains("darwin") -> "libkanama_bootstrap.dylib"
    osName.contains("windows") -> "kanama_bootstrap.dll"
    else -> "libkanama_bootstrap.so"
  }
}

val nativeBootstrapArtifact =
  layout.projectDirectory.file("example_project/addons/kanama/${hostNativeBootstrapArtifactName()}")
val shouldBuildNativeBootstrap =
  providers.gradleProperty("kanamaBuildNativeBootstrap").map(String::toBoolean).orElse(true)
val nativeBootstrapBuildDir = layout.buildDirectory.dir("bootstrap")

val configureNativeBootstrap by
  tasks.registering(Exec::class) {
    group = "build"
    description = "Configure the host native Kanama bootstrap CMake build."

    inputs.file(layout.projectDirectory.file("bootstrap/CMakeLists.txt"))
    inputs.file(layout.projectDirectory.file("bootstrap/bootstrap.c"))
    inputs.dir(layout.projectDirectory.dir("gdextension"))
    outputs.dir(nativeBootstrapBuildDir)

    onlyIf { shouldBuildNativeBootstrap.get() }

    commandLine(
      "cmake",
      "-S",
      layout.projectDirectory.dir("bootstrap").asFile.absolutePath,
      "-B",
      nativeBootstrapBuildDir.get().asFile.absolutePath,
      "-DCMAKE_BUILD_TYPE=Release",
    )
  }

val buildNativeBootstrap by
  tasks.registering(Exec::class) {
    group = "build"
    description = "Build the host native Kanama bootstrap library with CMake."

    dependsOn(configureNativeBootstrap)
    inputs.file(layout.projectDirectory.file("bootstrap/CMakeLists.txt"))
    inputs.file(layout.projectDirectory.file("bootstrap/bootstrap.c"))
    inputs.dir(layout.projectDirectory.dir("gdextension"))
    inputs.dir(nativeBootstrapBuildDir)

    onlyIf { shouldBuildNativeBootstrap.get() }

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
          "Native bootstrap build completed but ${nativeBootstrapArtifact.asFile.absolutePath} was not created"
        )
      }
    }
  }

fun File.enableAndroidKanamaGdextensionMetadata() {
  val requiredLibraryLines =
    listOf(
      "android.debug.arm64 = \"libkanama_bootstrap.so\"",
      "android.release.arm64 = \"libkanama_bootstrap.so\"",
      "android.debug.x86_64 = \"libkanama_bootstrap.so\"",
      "android.release.x86_64 = \"libkanama_bootstrap.so\"",
    )
  val lines = readLines().toMutableList()
  if ("android_aar_plugin = true" !in lines) {
    val librariesIndex = lines.indexOf("[libraries]").takeIf { it >= 0 } ?: lines.size
    val insertIndex =
      if (librariesIndex > 0 && lines[librariesIndex - 1].isBlank()) {
        librariesIndex - 1
      } else {
        librariesIndex
      }
    lines.add(insertIndex, "android_aar_plugin = true")
  }

  val missingLibraryLines = requiredLibraryLines.filter { it !in lines }
  if (missingLibraryLines.isNotEmpty()) {
    val librariesIndex =
      lines.indexOf("[libraries]").takeIf { it >= 0 }
        ?: run {
          if (lines.lastOrNull()?.isNotBlank() == true) {
            lines.add("")
          }
          lines.add("[libraries]")
          lines.lastIndex
        }
    val insertIndex =
      lines.withIndex().drop(librariesIndex + 1).firstOrNull { it.value.startsWith("[") }?.index
        ?: lines.size
    lines.addAll(insertIndex, missingLibraryLines)
  }
  writeText(lines.joinToString(System.lineSeparator()) + System.lineSeparator())
}

fun File.enableIosKanamaGdextensionMetadata() {
  val requiredLibraryLines =
    listOf(
      "ios.debug.arm64 = \"res://addons/kanama/bin/ios/kanama_ios.debug.xcframework\"",
      "ios.release.arm64 = \"res://addons/kanama/bin/ios/kanama_ios.release.xcframework\"",
    )
  val lines = readLines().toMutableList()
  val missingLibraryLines = requiredLibraryLines.filter { it !in lines }
  if (missingLibraryLines.isNotEmpty()) {
    val librariesIndex =
      lines.indexOf("[libraries]").takeIf { it >= 0 }
        ?: run {
          if (lines.lastOrNull()?.isNotBlank() == true) {
            lines.add("")
          }
          lines.add("[libraries]")
          lines.lastIndex
        }
    val insertIndex =
      lines.withIndex().drop(librariesIndex + 1).firstOrNull { it.value.startsWith("[") }?.index
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
    configurations.named("runtimeClasspath").get().map { if (it.isDirectory) it else zipTree(it) }
  }) {
    exclude("META-INF/*.SF", "META-INF/*.DSA", "META-INF/*.RSA", "META-INF/MANIFEST.MF")
  }
}

tasks.register<Copy>("syncExampleAddonJar") {
  dependsOn(buildNativeBootstrap)
  dependsOn(tasks.named("jar"))
  dependsOn(":project-scripts:jar")
  from(layout.buildDirectory.file("libs/kanama.jar"))
  from(project(":project-scripts").tasks.named<Jar>("jar").flatMap { it.archiveFile })
  into(layout.projectDirectory.dir("example_project/addons/kanama"))
}

tasks.register("publishKanamaToMavenLocal") {
  group = "publishing"
  description = "Publish Kanama runtime, annotations, and KSP processor jars to mavenLocal()."
  dependsOn(
    tasks.named("publishToMavenLocal"),
    ":annotations:publishToMavenLocal",
    ":kanama-common-api:publishJvmPublicationToMavenLocal",
    ":kanama-common-api:publishKotlinMultiplatformPublicationToMavenLocal",
    ":processor:publishToMavenLocal",
  )
}

val cleanKanamaPackageMavenRepository by
  tasks.registering(Delete::class) { delete(packageMavenRepositoryDir) }

allprojects {
  tasks
    .matching {
      it.name.startsWith("publish") && it.name.endsWith("PublicationToKanamaPackageRepository")
    }
    .configureEach { mustRunAfter(rootProject.tasks.named("cleanKanamaPackageMavenRepository")) }
}

val publishKanamaPackageMavenRepository by
  tasks.registering {
    group = "publishing"
    description =
      "Publish Kanama runtime, annotations, and KSP processor jars to the package-local Maven repository."
    dependsOn(cleanKanamaPackageMavenRepository)
    dependsOn(
    tasks.named("publishMavenPublicationToKanamaPackageRepository"),
    ":annotations:publishMavenPublicationToKanamaPackageRepository",
    ":kanama-common-api:publishJvmPublicationToKanamaPackageRepository",
    ":kanama-common-api:publishKotlinMultiplatformPublicationToKanamaPackageRepository",
    ":processor:publishMavenPublicationToKanamaPackageRepository",
  )
  }

fun hostPlatformClassifier(): String {
  val osName = System.getProperty("os.name").lowercase()
  val archName = System.getProperty("os.arch").lowercase()
  val os =
    when {
      osName.contains("mac") || osName.contains("darwin") -> "macos"
      osName.contains("windows") -> "windows"
      osName.contains("linux") -> "linux"
      else -> throw GradleException("Unsupported Kanama package host OS: $osName")
    }
  val arch =
    when (archName) {
      "x86_64",
      "amd64" -> "x64"
      "aarch64",
      "arm64" -> "arm64"
      else -> throw GradleException("Unsupported Kanama package host architecture: $archName")
    }
  return "$os-$arch"
}

val packagePlatformClassifier =
  providers
    .gradleProperty("kanamaPlatformClassifier")
    .orElse(providers.provider { hostPlatformClassifier() })

fun expectedPackageNativeArch(classifier: String): String =
  when {
    classifier.endsWith("-x64") -> "x64"
    classifier.endsWith("-arm64") -> "arm64"
    else -> throw GradleException("Unsupported Kanama package classifier: $classifier")
  }

fun ByteArray.u16le(offset: Int): Int =
  (this[offset].toInt() and 0xff) or ((this[offset + 1].toInt() and 0xff) shl 8)

fun ByteArray.u16be(offset: Int): Int =
  ((this[offset].toInt() and 0xff) shl 8) or (this[offset + 1].toInt() and 0xff)

fun ByteArray.u32le(offset: Int): Long =
  (this[offset].toLong() and 0xff) or
    ((this[offset + 1].toLong() and 0xff) shl 8) or
    ((this[offset + 2].toLong() and 0xff) shl 16) or
    ((this[offset + 3].toLong() and 0xff) shl 24)

fun ByteArray.u32be(offset: Int): Long =
  ((this[offset].toLong() and 0xff) shl 24) or
    ((this[offset + 1].toLong() and 0xff) shl 16) or
    ((this[offset + 2].toLong() and 0xff) shl 8) or
    (this[offset + 3].toLong() and 0xff)

fun detectNativeBootstrapArch(file: File): String {
  val bytes = file.readBytes()
  if (bytes.size < 64) {
    return "unknown"
  }

  if (
    bytes[0] == 0x7f.toByte() &&
      bytes[1] == 'E'.code.toByte() &&
      bytes[2] == 'L'.code.toByte() &&
      bytes[3] == 'F'.code.toByte()
  ) {
    val machine = if (bytes[5].toInt() == 2) bytes.u16be(18) else bytes.u16le(18)
    return when (machine) {
      62 -> "x64"
      183 -> "arm64"
      else -> "unknown-elf-$machine"
    }
  }

  if (bytes[0] == 'M'.code.toByte() && bytes[1] == 'Z'.code.toByte()) {
    val peOffset = bytes.u32le(0x3c).toInt()
    if (peOffset + 6 < bytes.size) {
      val machine = bytes.u16le(peOffset + 4)
      return when (machine) {
        0x8664 -> "x64"
        0xaa64 -> "arm64"
        else -> "unknown-pe-$machine"
      }
    }
  }

  val magicLe = bytes.u32le(0)
  val magicBe = bytes.u32be(0)
  if (magicLe == 0xfeedfacfL || magicLe == 0xfeedfaceL) {
    return when (bytes.u32le(4)) {
      0x01000007L -> "x64"
      0x0100000cL -> "arm64"
      else -> "unknown-mach-o-${bytes.u32le(4)}"
    }
  }
  if (magicBe == 0xcafebabeL || magicBe == 0xcafebabfL) {
    val nfatArch = bytes.u32be(4).toInt()
    val archs =
      (0 until nfatArch).mapNotNull { index ->
        val offset = 8 + (index * 20)
        if (offset + 8 > bytes.size) {
          null
        } else {
          when (bytes.u32be(offset)) {
            0x01000007L -> "x64"
            0x0100000cL -> "arm64"
            else -> null
          }
        }
      }
    if (archs.isNotEmpty()) {
      return archs.distinct().joinToString("+")
    }
  }

  return "unknown"
}

val verifyNativeBootstrapArtifact by
  tasks.registering {
    group = "verification"
    description = "Verify that the native bootstrap artifact matches the selected package platform."
    dependsOn(buildNativeBootstrap)
    inputs.file(nativeBootstrapArtifact)
    inputs.property("kanamaPlatformClassifier", packagePlatformClassifier)

    doLast {
      val classifier = packagePlatformClassifier.get()
      val expectedArch = expectedPackageNativeArch(classifier)
      val actualArch = detectNativeBootstrapArch(nativeBootstrapArtifact.asFile)
      if (expectedArch !in actualArch.split("+")) {
        throw GradleException(
          "Native bootstrap ${nativeBootstrapArtifact.asFile.absolutePath} is $actualArch, " +
            "but $classifier requires $expectedArch"
        )
      }
    }
  }

val packageGdextensionDescriptorFile =
  layout.buildDirectory.file("generated/package/kanama.gdextension")
val generatePackageGdextensionDescriptor by
  tasks.registering {
    outputs.file(packageGdextensionDescriptorFile)
    doLast {
      packageGdextensionDescriptorFile.get().asFile.apply {
        parentFile.mkdirs()
        writeText(
          """
                |[configuration]
                |
                |entry_symbol = "kanama_entry"
                |compatibility_minimum = "4.7"
                |
                |[libraries]
                |
                |macos.debug = "res://addons/kanama/bin/macos-arm64/libkanama_bootstrap.dylib"
                |macos.release = "res://addons/kanama/bin/macos-arm64/libkanama_bootstrap.dylib"
                |linux.debug.x86_64 = "res://addons/kanama/bin/linux-x64/libkanama_bootstrap.so"
                |linux.release.x86_64 = "res://addons/kanama/bin/linux-x64/libkanama_bootstrap.so"
                |linux.debug.arm64 = "res://addons/kanama/bin/linux-arm64/libkanama_bootstrap.so"
                |linux.release.arm64 = "res://addons/kanama/bin/linux-arm64/libkanama_bootstrap.so"
                |windows.debug.x86_64 = "res://addons/kanama/bin/windows-x64/kanama_bootstrap.dll"
                |windows.release.x86_64 = "res://addons/kanama/bin/windows-x64/kanama_bootstrap.dll"
                |ios.debug.arm64 = "res://addons/kanama/bin/ios/kanama_ios.debug.xcframework"
                |ios.release.arm64 = "res://addons/kanama/bin/ios/kanama_ios.release.xcframework"
                |"""
            .trimMargin() + System.lineSeparator()
        )
      }
    }
  }

val packageExtensionListFile = layout.buildDirectory.file("generated/package/extension_list.cfg")
val generatePackageExtensionList by
  tasks.registering {
    outputs.file(packageExtensionListFile)
    doLast {
      packageExtensionListFile.get().asFile.apply {
        parentFile.mkdirs()
        writeText("res://addons/kanama/kanama.gdextension${System.lineSeparator()}")
      }
    }
  }

val storeNativeArtifactDir =
  layout.buildDirectory.dir(packagePlatformClassifier.map { "package/store-native/$it" })
val requiredStoreNativePaths =
  listOf(
    "addons/kanama/bin/macos-arm64/libkanama_bootstrap.dylib",
    "addons/kanama/bin/linux-x64/libkanama_bootstrap.so",
    "addons/kanama/bin/linux-arm64/libkanama_bootstrap.so",
    "addons/kanama/bin/windows-x64/kanama_bootstrap.dll",
  )

val prepareStoreNativeArtifact by
  tasks.registering(Sync::class) {
    group = "distribution"
    description = "Stage this platform's native bootstrap for the all-platform store addon."
    dependsOn(verifyNativeBootstrapArtifact)
    into(storeNativeArtifactDir)
    from(nativeBootstrapArtifact) { into("addons/kanama/bin/${packagePlatformClassifier.get()}") }
  }

tasks.register<Zip>("packageStoreNativeArtifact") {
  group = "distribution"
  description = "Zip this platform's native bootstrap for store addon assembly."
  dependsOn(prepareStoreNativeArtifact)
  archiveFileName.set(
    packagePlatformClassifier.map { "kanama-store-native-v${project.version}-$it.zip" }
  )
  destinationDirectory.set(layout.buildDirectory.dir("distributions"))
  isPreserveFileTimestamps = false
  isReproducibleFileOrder = true
  from(prepareStoreNativeArtifact)
}

tasks.register<Zip>("packageDesktopKit") {
  group = "distribution"
  description = "Build a source-free Kanama desktop starter kit for the selected host platform."
  dependsOn(
    tasks.named("jar"),
    publishKanamaPackageMavenRepository,
    generatePackageGdextensionDescriptor,
    generatePackageExtensionList,
    verifyNativeBootstrapArtifact,
  )
  archiveFileName.set(
    packagePlatformClassifier.map { "kanama-desktop-kit-v${project.version}-$it.zip" }
  )
  destinationDirectory.set(layout.buildDirectory.dir("distributions"))
  duplicatesStrategy = DuplicatesStrategy.FAIL
  isPreserveFileTimestamps = false
  isReproducibleFileOrder = true

  from(layout.projectDirectory.dir("templates/release-kit")) {
    filter { line: String -> line.replace("@KANAMA_VERSION@", project.version.toString()) }
  }
  from(layout.projectDirectory.file("templates/starter/HelloScript.kt")) { into("kotlin-src") }
  from(layout.projectDirectory.dir("templates/starter/addons/kanama_tools")) {
    into("addons/kanama_tools")
  }
  from(layout.projectDirectory.file("LICENSE")) { into("addons/kanama_tools") }
  from(packageGdextensionDescriptorFile) {
    into("addons/kanama")
    rename { "kanama.gdextension" }
  }
  from(layout.buildDirectory.file("libs/kanama.jar")) { into("addons/kanama") }
  from(layout.projectDirectory.file("templates/consumer-gradle/kanama-project.gradle.kts")) {
    into("addons/kanama")
    filter { line: String -> line.replace("@KANAMA_VERSION@", project.version.toString()) }
  }
  from(packageMavenRepositoryDir) { into("addons/kanama/maven") }
  from(nativeBootstrapArtifact) { into("addons/kanama/bin/${packagePlatformClassifier.get()}") }
  from(packageExtensionListFile) {
    into(".godot")
    rename { "extension_list.cfg" }
  }
  from(layout.projectDirectory.file("LICENSE")) { into("addons/kanama") }
  from(layout.projectDirectory.file("gradlew")) { filePermissions { unix("rwxr-xr-x") } }
  from(layout.projectDirectory.file("gradlew.bat"))
  from(layout.projectDirectory.dir("gradle")) { into("gradle") }
}

tasks.register<Zip>("packageStoreAddon") {
  group = "distribution"
  description = "Build an install-safe Kanama addon zip for the Godot Asset Store."
  dependsOn(
    tasks.named("jar"),
    publishKanamaPackageMavenRepository,
    generatePackageGdextensionDescriptor,
  )
  val nativeArtifactsDir =
    providers.gradleProperty("kanamaStoreNativeArtifactsDir").map { file(it) }
  if (nativeArtifactsDir.isPresent) {
    inputs.dir(nativeArtifactsDir)
    from(nativeArtifactsDir) { include("addons/kanama/bin/**") }
    doFirst {
      val nativeDir = nativeArtifactsDir.get()
      val missing = requiredStoreNativePaths.filterNot { nativeDir.resolve(it).isFile }
      if (missing.isNotEmpty()) {
        throw GradleException(
          "Missing store native artifact(s) under ${nativeDir.absolutePath}: ${missing.joinToString()}"
        )
      }
    }
  } else {
    dependsOn(prepareStoreNativeArtifact)
    from(prepareStoreNativeArtifact) { include("addons/kanama/bin/**") }
  }

  archiveFileName.set("kanama-store-addon-v${project.version}.zip")
  destinationDirectory.set(layout.buildDirectory.dir("distributions"))
  duplicatesStrategy = DuplicatesStrategy.FAIL
  isPreserveFileTimestamps = false
  isReproducibleFileOrder = true

  from(layout.projectDirectory.dir("templates/starter/addons/kanama_tools")) {
    into("addons/kanama_tools")
  }
  from(layout.projectDirectory.file("LICENSE")) { into("addons/kanama_tools") }
  from(packageGdextensionDescriptorFile) {
    into("addons/kanama")
    rename { "kanama.gdextension" }
  }
  from(layout.buildDirectory.file("libs/kanama.jar")) { into("addons/kanama") }
  from(layout.projectDirectory.file("templates/consumer-gradle/kanama-project.gradle.kts")) {
    into("addons/kanama")
    filter { line: String -> line.replace("@KANAMA_VERSION@", project.version.toString()) }
  }
  from(packageMavenRepositoryDir) { into("addons/kanama/maven") }
  from(layout.projectDirectory.file("LICENSE")) { into("addons/kanama") }
  from(layout.projectDirectory.file("templates/store-addon/README.md")) { into("addons/kanama") }
  from(layout.projectDirectory.file("templates/store-addon/setup-kanama-project.sh")) {
    into("addons/kanama")
    filePermissions { unix("rwxr-xr-x") }
  }
  from(layout.projectDirectory.file("templates/store-addon/setup-kanama-project.ps1")) {
    into("addons/kanama")
  }
  from(layout.projectDirectory.dir("templates/release-kit")) {
    into("addons/kanama/templates/release-kit")
    exclude("project.godot", "main.tscn")
    filter { line: String -> line.replace("@KANAMA_VERSION@", project.version.toString()) }
  }
  from(layout.projectDirectory.file("templates/starter/HelloScript.kt")) {
    into("addons/kanama/templates/release-kit/kotlin-src")
  }
  from(layout.projectDirectory.file("gradlew")) {
    into("addons/kanama/templates/release-kit")
    filePermissions { unix("rwxr-xr-x") }
  }
  from(layout.projectDirectory.file("gradlew.bat")) { into("addons/kanama/templates/release-kit") }
  from(layout.projectDirectory.dir("gradle")) { into("addons/kanama/templates/release-kit/gradle") }
}

tasks.register("packageDistributions") {
  group = "distribution"
  description = "Build the desktop kit, native store artifact, and local host store addon."
  dependsOn("packageDesktopKit", "packageStoreNativeArtifact", "packageStoreAddon")
}

// ---------------------------------------------------------------------------
// Task 63 (issue #102) — bundled jlink runtime for exported desktop games.
//
// Exported games are unpack-and-play: they ship a jlink-trimmed `runtime/`
// image that the native bootstrap probes app-relative BEFORE JAVA_HOME (see
// bootstrap/bootstrap.c). The module set below is pinned from what kanama.jar
// needs — one recipe for every Kanama game, never recomputed per project.
// Recompute only when kanama.jar's bundled dependency set changes:
//   <jdk-25>/bin/jdeps --print-module-deps --ignore-missing-deps \
//     build/libs/kanama.jar <scripts>/kanama-scripts.jar
// Pinned 2026-08-03 (0.4.x): java.base, java.instrument, jdk.unsupported
// (FFM lives in java.base; java.instrument + jdk.unsupported come from the
// bundled kotlinx-coroutines debug agent + Unsafe usage). The rare game that
// pulls an extra JDK module (e.g. java.net.http, jdk.localedata, or
// jdk.jdwp.agent for JDWP-debugging an exported build) adds it with
//   -PkanamaRuntimeAdditionalModules=java.net.http,jdk.localedata
// The default path must require nothing from the user.
// ---------------------------------------------------------------------------
val kanamaRuntimePinnedModules = listOf("java.base", "java.instrument", "jdk.unsupported")
val kanamaRuntimeAdditionalModules =
  providers.gradleProperty("kanamaRuntimeAdditionalModules").orElse("")
val gameRuntimeImageDir = layout.buildDirectory.dir("game-runtime/runtime")

// Where each platform's runtime image keeps the server JVM. Windows is the odd
// one out: `bin\server\jvm.dll`, NOT `lib/server` — and its CRT dependencies
// (vcruntime140/msvcp140/ucrtbase) sit one level up in `bin\`, which is why
// bootstrap.c loads it with the DLL-load-dir search flags.
fun jvmServerLibraryRelativePath(classifier: String): String =
  when {
    classifier.startsWith("windows") -> "bin/server/jvm.dll"
    classifier.startsWith("macos") -> "lib/server/libjvm.dylib"
    classifier.startsWith("linux") -> "lib/server/libjvm.so"
    else -> throw GradleException("Unsupported Kanama runtime target classifier: $classifier")
  }

fun hostJvmServerLibraryRelativePath(): String =
  jvmServerLibraryRelativePath(hostPlatformClassifier())

tasks.register<Exec>("jlinkGameRuntime") {
  group = "distribution"
  description =
    "Build the jlink-trimmed Java runtime image bundled with exported desktop games (host platform)."

  val toolchainService = project.extensions.getByType<JavaToolchainService>()
  val jdkHome =
    toolchainService
      .launcherFor { languageVersion.set(JavaLanguageVersion.of(25)) }
      .map { it.metadata.installationPath }
  val runtimeModules =
    kanamaRuntimeAdditionalModules.map { extra ->
      val additional = extra.split(',').map(String::trim).filter(String::isNotEmpty)
      (kanamaRuntimePinnedModules + additional).distinct().joinToString(",")
    }

  inputs.property("kanamaRuntimeModules", runtimeModules)
  inputs.property("kanamaRuntimeJdkHome", jdkHome.map { it.asFile.absolutePath })
  outputs.dir(gameRuntimeImageDir)

  doFirst {
    // jlink refuses to write into an existing directory, and Gradle pre-creates
    // declared output directories — clear it right before running.
    gameRuntimeImageDir.get().asFile.deleteRecursively()
    val jlinkName =
      if (System.getProperty("os.name").lowercase().contains("windows")) "jlink.exe" else "jlink"
    commandLine(
      jdkHome.get().asFile.resolve("bin/$jlinkName").absolutePath,
      "--add-modules",
      runtimeModules.get(),
      "--output",
      gameRuntimeImageDir.get().asFile.absolutePath,
      "--strip-debug",
      "--no-header-files",
      "--no-man-pages",
      "--compress",
      "zip-6",
    )
  }

  doLast {
    val imageDir = gameRuntimeImageDir.get().asFile
    val serverLib = imageDir.resolve(hostJvmServerLibraryRelativePath())
    if (!serverLib.isFile) {
      throw GradleException(
        "jlink runtime image completed but ${serverLib.absolutePath} was not created"
      )
    }
    val sizeMb =
      imageDir.walkTopDown().filter { it.isFile }.map { it.length() }.sum() / (1024L * 1024L)
    logger.lifecycle(
      "[kanama] game runtime image: ${imageDir.absolutePath} " +
        "($sizeMb MB, modules=${runtimeModules.get()})"
    )
  }
}

// ---------------------------------------------------------------------------
// Task 63 — CROSS-TARGET runtime images (decided 2026-08-07).
//
// A developer on macOS must be able to export a Windows or Linux game. Godot's
// own export templates are cross-platform, so if Kanama's bundled runtime could
// only be built ON the target OS, a Mac developer could not ship a Windows game
// at all. `jlinkGameRuntimeCross` closes that: it links the TARGET platform's
// Temurin jmods from whatever host you are on.
//
// Two constraints, both measured on macOS arm64 (Temurin 25.0.3+9) 2026-08-07:
//
//   1. Temurin JDK archives no longer contain a `jmods/` directory at all. JDK
//      24's JEP 493 ("Linking Run-Time Images without JMODs") lets jlink link
//      from the JDK's own run-time image, and Temurin ships that way — but a
//      run-time image can only produce an image for its OWN platform. Adoptium
//      publishes the jmods as a SEPARATE per-platform download
//      (`image_type=jmods`, ~80 MB compressed); that is what this fetches.
//      Downloading the target JDK instead would silently get you nothing.
//   2. The jmods must be the same JDK FEATURE version as the jlink doing the
//      linking. Patch levels may differ: jlink 25.0.3+9 linked 25.0.4+7 jmods
//      cleanly. Both sides are JDK 25 here.
//
// Usage: ./gradlew jlinkGameRuntimeCross -PkanamaRuntimeTarget=windows-x64
// Output: build/game-runtime/<classifier>/runtime
// ---------------------------------------------------------------------------

/** One desktop export target: how to fetch its Temurin jmods archive. */
data class KanamaRuntimeTarget(
  val classifier: String,
  val adoptiumOs: String,
  val adoptiumArch: String,
  val archiveExtension: String,
  val jmodsSha256: String,
)

// SHA-256 pinned from the Adoptium API for kanamaTargetJdkRelease (gradle.properties).
// Bumping that release means re-pinning every row:
//   curl -s 'https://api.adoptium.net/v3/assets/feature_releases/25/ga?image_type=jmods&vendor=eclipse'
val kanamaRuntimeTargets =
  listOf(
    KanamaRuntimeTarget(
      "windows-x64",
      "windows",
      "x64",
      "zip",
      "b61df3205a98e941188949a0c4d0832a85868a7fc99ad244b84185b7bf6408ef",
    ),
    KanamaRuntimeTarget(
      "linux-x64",
      "linux",
      "x64",
      "tar.gz",
      "f6ef833f741d549c61f8db740d030ebc4d5bbd6e6b062264d5efd94490fb41ac",
    ),
    KanamaRuntimeTarget(
      "linux-arm64",
      "linux",
      "aarch64",
      "tar.gz",
      "ee699b04cf189e45bea62ff0f8d94d2600d606d41cc9ac84b81b83dc8ea27b6f",
    ),
    KanamaRuntimeTarget(
      "macos-arm64",
      "mac",
      "aarch64",
      "tar.gz",
      "6597e7a69447bf2c1f25b9a15edc54d10304570430b45f6728bbb2e02343f87f",
    ),
  )

val kanamaTargetJdkRelease = providers.gradleProperty("kanamaTargetJdkRelease")
// Downloaded jmods archives are large and version-pinned: cache them outside
// `build/` so `clean` and per-worktree builds never re-download 80 MB.
val kanamaTargetJmodsCacheDir =
  providers
    .gradleProperty("kanamaTargetJmodsCacheDir")
    .orElse(providers.environmentVariable("KANAMA_TARGET_JMODS_CACHE"))
    .orElse(File(gradle.gradleUserHomeDir, "kanama-target-jmods").absolutePath)

fun kanamaRuntimeTargetFor(classifier: String): KanamaRuntimeTarget =
  kanamaRuntimeTargets.firstOrNull { it.classifier == classifier }
    ?: throw GradleException(
      "Unknown Kanama runtime target '$classifier'; known targets: " +
        kanamaRuntimeTargets.joinToString(", ") { it.classifier }
    )

/** `25.0.4+7` -> `OpenJDK25U-jmods_x64_windows_hotspot_25.0.4_7.zip`. */
fun kanamaJmodsArchiveName(target: KanamaRuntimeTarget, release: String): String {
  val feature = release.substringBefore('.')
  val fileVersion = release.replace('+', '_')
  return "OpenJDK${feature}U-jmods_${target.adoptiumArch}_${target.adoptiumOs}_hotspot_" +
    "$fileVersion.${target.archiveExtension}"
}

fun kanamaJmodsDownloadUrl(target: KanamaRuntimeTarget, release: String): String {
  val feature = release.substringBefore('.')
  val tag = "jdk-$release".replace("+", "%2B")
  return "https://github.com/adoptium/temurin$feature-binaries/releases/download/$tag/" +
    kanamaJmodsArchiveName(target, release)
}

fun sha256Of(file: File): String {
  val digest = java.security.MessageDigest.getInstance("SHA-256")
  file.inputStream().buffered().use { input ->
    val buffer = ByteArray(1 shl 16)
    while (true) {
      val read = input.read(buffer)
      if (read <= 0) break
      digest.update(buffer, 0, read)
    }
  }
  return digest.digest().joinToString("") { "%02x".format(it) }
}

/** Resolve an archive entry under [root], refusing paths that escape it (zip slip). */
fun archiveEntryTarget(root: File, entryName: String): File {
  val target = File(root, entryName).canonicalFile
  if (!target.toPath().startsWith(root.canonicalFile.toPath())) {
    throw GradleException("Refusing archive entry outside the extraction root: $entryName")
  }
  return target
}

fun extractZipArchive(archive: File, into: File) {
  java.util.zip.ZipInputStream(archive.inputStream().buffered()).use { zip ->
    while (true) {
      val entry = zip.nextEntry ?: break
      val target = archiveEntryTarget(into, entry.name)
      if (entry.isDirectory) {
        target.mkdirs()
      } else {
        target.parentFile.mkdirs()
        target.outputStream().buffered().use { zip.copyTo(it) }
      }
      zip.closeEntry()
    }
  }
}

/**
 * Minimal ustar/GNU reader — enough for Adoptium's jmods tarballs (plain files and
 * directories, plus GNU long names). Avoids a Commons Compress dependency and any
 * shell-out to a `tar` whose flavour differs per runner.
 */
fun extractTarGzArchive(archive: File, into: File) {
  fun tarField(header: ByteArray, start: Int, length: Int): String {
    val end =
      (start until start + length).firstOrNull { header[it] == 0.toByte() } ?: (start + length)
    return String(header, start, end - start, Charsets.UTF_8).trim()
  }

  java.util.zip.GZIPInputStream(archive.inputStream().buffered()).use { input ->
    val header = ByteArray(512)
    var pendingLongName: String? = null
    while (true) {
      // A tar stream ends with zero-filled blocks (or simply runs out of data).
      var filled = 0
      while (filled < header.size) {
        val read = input.read(header, filled, header.size - filled)
        if (read < 0) break
        filled += read
      }
      if (filled < header.size || header.all { it == 0.toByte() }) break

      val rawName = tarField(header, 0, 100)
      val prefix = tarField(header, 345, 155)
      val name = pendingLongName ?: if (prefix.isEmpty()) rawName else "$prefix/$rawName"
      pendingLongName = null
      val size = tarField(header, 124, 12).ifEmpty { "0" }.toLong(8)
      val padded = ((size + 511) / 512) * 512

      when (header[156].toInt().toChar()) {
        // GNU long name: this entry's payload is the NEXT entry's real path.
        'L' -> {
          val nameBytes = input.readNBytes(size.toInt())
          if (nameBytes.size.toLong() != size) {
            throw GradleException("Truncated tar long-name entry: $archive")
          }
          pendingLongName = String(nameBytes, Charsets.UTF_8).trimEnd('\u0000', ' ')
          input.skipNBytes(padded - size)
        }
        '5' -> {
          archiveEntryTarget(into, name).mkdirs()
          input.skipNBytes(padded)
        }
        // Regular file: type flag '0' or NUL.
        '0',
        '\u0000' -> {
          val target = archiveEntryTarget(into, name)
          target.parentFile.mkdirs()
          target.outputStream().buffered().use { output ->
            var remaining = size
            val buffer = ByteArray(1 shl 16)
            while (remaining > 0) {
              val read = input.read(buffer, 0, minOf(buffer.size.toLong(), remaining).toInt())
              if (read < 0) throw GradleException("Truncated tar entry '$name': $archive")
              output.write(buffer, 0, read)
              remaining -= read
            }
          }
          input.skipNBytes(padded - size)
        }
        // Symlinks, pax headers, everything else: skip the payload. Adoptium's
        // jmods archives are plain files and directories only.
        else -> input.skipNBytes(padded)
      }
    }
  }
}

/**
 * Download (once), checksum, and unpack the target platform's Temurin jmods.
 * Returns the directory that holds `java.base.jmod` — jlink's `--module-path`.
 */
fun resolveTargetJmodsDir(target: KanamaRuntimeTarget, release: String, cacheDir: File): File {
  cacheDir.mkdirs()
  val archiveName = kanamaJmodsArchiveName(target, release)
  val archive = File(cacheDir, archiveName)
  if (archive.isFile && sha256Of(archive) != target.jmodsSha256) {
    logger.lifecycle("[kanama] cached jmods archive failed its checksum; re-downloading $archiveName")
    archive.delete()
  }
  if (!archive.isFile) {
    val url = kanamaJmodsDownloadUrl(target, release)
    logger.lifecycle("[kanama] downloading target jmods: $url")
    val partial = File(cacheDir, "$archiveName.part")
    partial.delete()
    java.net.URI(url).toURL().openStream().use { input ->
      partial.outputStream().buffered().use { output -> input.copyTo(output) }
    }
    val actual = sha256Of(partial)
    if (actual != target.jmodsSha256) {
      partial.delete()
      throw GradleException(
        "SHA-256 mismatch for $archiveName\n  expected ${target.jmodsSha256}\n  actual   $actual"
      )
    }
    partial.renameTo(archive)
  }

  val extractDir = File(cacheDir, "$release-${target.classifier}")
  val marker = File(extractDir, ".kanama-jmods-sha256")
  if (marker.isFile && marker.readText().trim() == target.jmodsSha256) {
    return findJmodsRoot(extractDir, archiveName)
  }
  extractDir.deleteRecursively()
  extractDir.mkdirs()
  if (target.archiveExtension == "zip") {
    extractZipArchive(archive, extractDir)
  } else {
    extractTarGzArchive(archive, extractDir)
  }
  marker.writeText(target.jmodsSha256)
  return findJmodsRoot(extractDir, archiveName)
}

fun findJmodsRoot(extractDir: File, archiveName: String): File =
  extractDir.walkTopDown().maxDepth(3).firstOrNull { File(it, "java.base.jmod").isFile }
    ?: throw GradleException("No java.base.jmod found after unpacking $archiveName into $extractDir")

/** "pe" | "elf" | "macho" | "unknown" from a native library's magic bytes. */
fun detectNativeBinaryFormat(file: File): String {
  val head = ByteArray(8)
  val read = file.inputStream().use { it.read(head) }
  if (read < 4) return "unknown"
  return when {
    head[0] == 0x7f.toByte() && head[1] == 'E'.code.toByte() && head[2] == 'L'.code.toByte() ->
      "elf"
    head[0] == 'M'.code.toByte() && head[1] == 'Z'.code.toByte() -> "pe"
    head.u32le(0) == 0xfeedfacfL || head.u32le(0) == 0xfeedfaceL -> "macho"
    head.u32be(0) == 0xcafebabeL || head.u32be(0) == 0xcafebabfL -> "macho"
    else -> "unknown"
  }
}

fun expectedRuntimeBinaryFormat(classifier: String): String =
  when {
    classifier.startsWith("windows") -> "pe"
    classifier.startsWith("macos") -> "macho"
    else -> "elf"
  }

tasks.register("jlinkGameRuntimeCross") {
  group = "distribution"
  description =
    "Build the bundled game runtime image for another platform " +
      "(-PkanamaRuntimeTarget=windows-x64|linux-x64|linux-arm64|macos-arm64)."

  val toolchainService = project.extensions.getByType<JavaToolchainService>()
  val jdkHome =
    toolchainService
      .launcherFor { languageVersion.set(JavaLanguageVersion.of(25)) }
      .map { it.metadata.installationPath }
  val requestedTarget = providers.gradleProperty("kanamaRuntimeTarget")
  val runtimeModules =
    kanamaRuntimeAdditionalModules.map { extra ->
      val additional = extra.split(',').map(String::trim).filter(String::isNotEmpty)
      (kanamaRuntimePinnedModules + additional).distinct().joinToString(",")
    }
  val crossRuntimeRoot = layout.buildDirectory.dir("game-runtime")

  doLast {
    val classifier =
      requestedTarget.orNull
        ?: throw GradleException(
          "jlinkGameRuntimeCross needs -PkanamaRuntimeTarget=<classifier>; known targets: " +
            kanamaRuntimeTargets.joinToString(", ") { it.classifier }
        )
    val target = kanamaRuntimeTargetFor(classifier)
    val release = kanamaTargetJdkRelease.get()
    val jdk = jdkHome.get().asFile
    val jdkFeature = File(jdk, "release").takeIf { it.isFile }?.let { file ->
      Regex("""JAVA_VERSION="(\d+)""").find(file.readText())?.groupValues?.get(1)
    }
    val targetFeature = release.substringBefore('.')
    if (jdkFeature != null && jdkFeature != targetFeature) {
      throw GradleException(
        "jlink cross-targeting requires matching JDK feature versions: build JDK is " +
          "$jdkFeature, kanamaTargetJdkRelease is $release. Align gradle.properties " +
          "(kanamaTargetJdkRelease) with the build JDK."
      )
    }

    val jmodsDir =
      resolveTargetJmodsDir(target, release, File(kanamaTargetJmodsCacheDir.get()))
    val imageDir = File(crossRuntimeRoot.get().asFile, "$classifier/runtime")
    imageDir.deleteRecursively()
    imageDir.parentFile.mkdirs()

    val isWindowsHost = System.getProperty("os.name").lowercase().contains("windows")
    val jlink = File(jdk, if (isWindowsHost) "bin/jlink.exe" else "bin/jlink")
    val command =
      listOf(
        jlink.absolutePath,
        "--module-path",
        jmodsDir.absolutePath,
        "--add-modules",
        runtimeModules.get(),
        "--output",
        imageDir.absolutePath,
        "--strip-debug",
        "--no-header-files",
        "--no-man-pages",
        "--compress",
        "zip-6",
      )
    logger.lifecycle("[kanama] ${command.joinToString(" ")}")
    val exit =
      ProcessBuilder(command).redirectErrorStream(true).start().let { process ->
        process.inputStream.bufferedReader().forEachLine { logger.lifecycle(it) }
        process.waitFor()
      }
    if (exit != 0) {
      throw GradleException("jlink failed for target $classifier (exit $exit)")
    }

    // Prove the image is actually the TARGET platform's, not the host's: the
    // server JVM must sit at the target's layout and carry the target's object
    // format. A host-built image landing here is exactly the failure this task
    // exists to prevent.
    val serverLib = File(imageDir, jvmServerLibraryRelativePath(classifier))
    if (!serverLib.isFile) {
      throw GradleException(
        "cross jlink produced no ${jvmServerLibraryRelativePath(classifier)} in $imageDir"
      )
    }
    val expectedFormat = expectedRuntimeBinaryFormat(classifier)
    val actualFormat = detectNativeBinaryFormat(serverLib)
    val expectedArch = expectedPackageNativeArch(classifier)
    val actualArch = detectNativeBootstrapArch(serverLib)
    if (actualFormat != expectedFormat || actualArch != expectedArch) {
      throw GradleException(
        "cross jlink produced the wrong platform binary for $classifier: " +
          "${serverLib.absolutePath} is $actualFormat/$actualArch, expected " +
          "$expectedFormat/$expectedArch"
      )
    }

    val sizeMb =
      imageDir.walkTopDown().filter { it.isFile }.map { it.length() }.sum() / (1024L * 1024L)
    logger.lifecycle(
      "[kanama] cross game runtime image: ${imageDir.absolutePath} " +
        "($sizeMb MB, target=$classifier $actualFormat/$actualArch, jdk=$release, " +
        "modules=${runtimeModules.get()})"
    )
  }
}

val xcodeDeveloperDir =
  providers
    .gradleProperty("kanamaXcodeDeveloperDir")
    .orElse("/Applications/Xcode.app/Contents/Developer")
// Single source of truth for the Godot baseline version (see gradle.properties).
val godotVersion = providers.gradleProperty("kanamaGodotVersion").orElse("4.7.stable")
val godotIosTemplateZip =
  providers
    .gradleProperty("kanamaGodotIosTemplateZip")
    .orElse(providers.environmentVariable("GODOT_IOS_TEMPLATE_ZIP"))
    .orElse(
      godotVersion.map { version ->
        "${System.getProperty("user.home")}/Library/Application Support/Godot/export_templates/$version/ios.zip"
      }
    )
val iosMinimumDeploymentTarget = providers.gradleProperty("kanamaIosMinVersion").orElse("14.0")
val iosXcframeworkMode =
  providers
    .gradleProperty("kanamaIosXcframeworkMode")
    .orElse(providers.environmentVariable("KANAMA_IOS_XCFRAMEWORK_MODE"))
    .orElse("device")
val iosBuildDir = layout.buildDirectory.dir("ios")
val iosShimSource = layout.projectDirectory.file("ios/bootstrap/kanama_ios_shim.c")
val iosHeaderDir = layout.projectDirectory.dir("ios/include")
val iosDeviceDebugXcframeworkDir =
  iosBuildDir.map { it.dir("xcframework-device/debug/kanama_ios.debug.xcframework") }
val iosDeviceReleaseXcframeworkDir =
  iosBuildDir.map { it.dir("xcframework-device/release/kanama_ios.release.xcframework") }
val iosDebugXcframeworkDir =
  iosBuildDir.map { it.dir("xcframework/debug/kanama_ios.debug.xcframework") }
val iosReleaseXcframeworkDir =
  iosBuildDir.map { it.dir("xcframework/release/kanama_ios.release.xcframework") }

fun appleSdkPath(sdk: String): String {
  return providers
    .exec {
      environment("DEVELOPER_DIR", xcodeDeveloperDir.get())
      commandLine("xcrun", "--sdk", sdk, "--show-sdk-path")
    }
    .standardOutput
    .asText
    .get()
    .trim()
}

fun registerCompileIosShimTask(
  name: String,
  sdk: String,
  minVersionFlag: String,
  outputObjectPath: Provider<RegularFile>,
) =
  tasks.register<Exec>(name) {
    group = "ios"
    description = "Compile the Kanama iOS GDExtension C shim for $sdk."

    inputs.file(iosShimSource)
    inputs.file(layout.projectDirectory.file("gdextension/gdextension_interface.h"))
    inputs.property("kanamaXcodeDeveloperDir", xcodeDeveloperDir)
    inputs.property("kanamaIosMinVersion", iosMinimumDeploymentTarget)
    outputs.file(outputObjectPath)

    doFirst {
      val outputObject = outputObjectPath.get().asFile
      outputObject.parentFile.mkdirs()
      commandLine(
        "xcrun",
        "--sdk",
        sdk,
        "clang",
        "-arch",
        "arm64",
        "-isysroot",
        appleSdkPath(sdk),
        "$minVersionFlag=${iosMinimumDeploymentTarget.get()}",
        "-fvisibility=hidden",
        "-I",
        layout.projectDirectory.dir("gdextension").asFile.absolutePath,
        "-c",
        iosShimSource.asFile.absolutePath,
        "-o",
        outputObject.absolutePath,
      )
      environment("DEVELOPER_DIR", xcodeDeveloperDir.get())
    }
  }

fun registerCreateIosXcframeworkTask(
  name: String,
  deviceLibTask: TaskProvider<Exec>,
  simulatorLibTask: TaskProvider<Exec>,
  deviceLibPath: Provider<RegularFile>,
  simulatorLibPath: Provider<RegularFile>,
  outputDir: Provider<Directory>,
) =
  tasks.register<Exec>(name) {
    group = "ios"
    description =
      "Create the Kanama iOS ${name.removePrefix("createIos").removeSuffix("Xcframework").lowercase()} xcframework."

    dependsOn(deviceLibTask)
    dependsOn(simulatorLibTask)
    inputs.file(deviceLibPath)
    inputs.file(simulatorLibPath)
    inputs.dir(iosHeaderDir)
    inputs.property("kanamaXcodeDeveloperDir", xcodeDeveloperDir)
    outputs.dir(outputDir)

    doFirst {
      val xcframeworkDir = outputDir.get().asFile
      if (xcframeworkDir.exists()) {
        xcframeworkDir.deleteRecursively()
      }
      commandLine(
        "xcodebuild",
        "-create-xcframework",
        "-library",
        deviceLibPath.get().asFile.absolutePath,
        "-headers",
        iosHeaderDir.asFile.absolutePath,
        "-library",
        simulatorLibPath.get().asFile.absolutePath,
        "-headers",
        iosHeaderDir.asFile.absolutePath,
        "-output",
        xcframeworkDir.absolutePath,
      )
      environment("DEVELOPER_DIR", xcodeDeveloperDir.get())
    }
  }

fun registerCreateIosDeviceXcframeworkTask(
  name: String,
  deviceLibTask: TaskProvider<Exec>,
  deviceLibPath: Provider<RegularFile>,
  outputDir: Provider<Directory>,
) =
  tasks.register<Exec>(name) {
    group = "ios"
    description =
      "Create the Kanama iOS device-only ${name.removePrefix("createIosDevice").removeSuffix("Xcframework").lowercase()} xcframework."

    dependsOn(deviceLibTask)
    inputs.file(deviceLibPath)
    inputs.dir(iosHeaderDir)
    inputs.property("kanamaXcodeDeveloperDir", xcodeDeveloperDir)
    outputs.dir(outputDir)

    doFirst {
      val xcframeworkDir = outputDir.get().asFile
      if (xcframeworkDir.exists()) {
        xcframeworkDir.deleteRecursively()
      }
      commandLine(
        "xcodebuild",
        "-create-xcframework",
        "-library",
        deviceLibPath.get().asFile.absolutePath,
        "-headers",
        iosHeaderDir.asFile.absolutePath,
        "-output",
        xcframeworkDir.absolutePath,
      )
      environment("DEVELOPER_DIR", xcodeDeveloperDir.get())
    }
  }

fun iosRuntimeStaticLib(target: String, buildType: String): Provider<RegularFile> =
  project(":ios-runtime")
    .layout
    .buildDirectory
    .file("bin/$target/${buildType}Static/libkanama_ios_runtime.a")

val compileIosDeviceDebugShim =
  registerCompileIosShimTask(
    "compileIosDeviceDebugShim",
    "iphoneos",
    "-miphoneos-version-min",
    iosBuildDir.map { it.file("shim/iphoneos/debug/kanama_ios_shim.o") },
  )
val compileIosDeviceReleaseShim =
  registerCompileIosShimTask(
    "compileIosDeviceReleaseShim",
    "iphoneos",
    "-miphoneos-version-min",
    iosBuildDir.map { it.file("shim/iphoneos/release/kanama_ios_shim.o") },
  )
val compileIosSimulatorDebugShim =
  registerCompileIosShimTask(
    "compileIosSimulatorDebugShim",
    "iphonesimulator",
    "-mios-simulator-version-min",
    iosBuildDir.map { it.file("shim/iphonesimulator/debug/kanama_ios_shim.o") },
  )
val compileIosSimulatorReleaseShim =
  registerCompileIosShimTask(
    "compileIosSimulatorReleaseShim",
    "iphonesimulator",
    "-mios-simulator-version-min",
    iosBuildDir.map { it.file("shim/iphonesimulator/release/kanama_ios_shim.o") },
  )

val combineIosDeviceDebugLib =
  tasks.register<Exec>("combineIosDeviceDebugLib") {
    group = "ios"
    description = "Combine the Kanama iOS device debug static library."
    dependsOn(":ios-runtime:linkDebugStaticIosArm64")
    dependsOn(compileIosDeviceDebugShim)
    val outputLibPath = iosBuildDir.map { it.file("lib/iphoneos/debug/libkanama_ios.a") }
    inputs.file(iosRuntimeStaticLib("iosArm64", "debug"))
    inputs.file(iosBuildDir.map { it.file("shim/iphoneos/debug/kanama_ios_shim.o") })
    outputs.file(outputLibPath)
    doFirst {
      val outputLib = outputLibPath.get().asFile
      outputLib.parentFile.mkdirs()
      commandLine(
        "xcrun",
        "--sdk",
        "iphoneos",
        "libtool",
        "-static",
        "-o",
        outputLib.absolutePath,
        iosBuildDir.get().file("shim/iphoneos/debug/kanama_ios_shim.o").asFile.absolutePath,
        iosRuntimeStaticLib("iosArm64", "debug").get().asFile.absolutePath,
      )
      environment("DEVELOPER_DIR", xcodeDeveloperDir.get())
    }
  }
val combineIosDeviceReleaseLib =
  tasks.register<Exec>("combineIosDeviceReleaseLib") {
    group = "ios"
    description = "Combine the Kanama iOS device release static library."
    dependsOn(":ios-runtime:linkReleaseStaticIosArm64")
    dependsOn(compileIosDeviceReleaseShim)
    val outputLibPath = iosBuildDir.map { it.file("lib/iphoneos/release/libkanama_ios.a") }
    inputs.file(iosRuntimeStaticLib("iosArm64", "release"))
    inputs.file(iosBuildDir.map { it.file("shim/iphoneos/release/kanama_ios_shim.o") })
    outputs.file(outputLibPath)
    doFirst {
      val outputLib = outputLibPath.get().asFile
      outputLib.parentFile.mkdirs()
      commandLine(
        "xcrun",
        "--sdk",
        "iphoneos",
        "libtool",
        "-static",
        "-o",
        outputLib.absolutePath,
        iosBuildDir.get().file("shim/iphoneos/release/kanama_ios_shim.o").asFile.absolutePath,
        iosRuntimeStaticLib("iosArm64", "release").get().asFile.absolutePath,
      )
      environment("DEVELOPER_DIR", xcodeDeveloperDir.get())
    }
  }
val combineIosSimulatorDebugLib =
  tasks.register<Exec>("combineIosSimulatorDebugLib") {
    group = "ios"
    description = "Combine the Kanama iOS simulator debug static library."
    dependsOn(":ios-runtime:linkDebugStaticIosSimulatorArm64")
    dependsOn(compileIosSimulatorDebugShim)
    val outputLibPath = iosBuildDir.map { it.file("lib/iphonesimulator/debug/libkanama_ios.a") }
    inputs.file(iosRuntimeStaticLib("iosSimulatorArm64", "debug"))
    inputs.file(iosBuildDir.map { it.file("shim/iphonesimulator/debug/kanama_ios_shim.o") })
    outputs.file(outputLibPath)
    doFirst {
      val outputLib = outputLibPath.get().asFile
      outputLib.parentFile.mkdirs()
      commandLine(
        "xcrun",
        "--sdk",
        "iphonesimulator",
        "libtool",
        "-static",
        "-o",
        outputLib.absolutePath,
        iosBuildDir.get().file("shim/iphonesimulator/debug/kanama_ios_shim.o").asFile.absolutePath,
        iosRuntimeStaticLib("iosSimulatorArm64", "debug").get().asFile.absolutePath,
      )
      environment("DEVELOPER_DIR", xcodeDeveloperDir.get())
    }
  }
val combineIosSimulatorReleaseLib =
  tasks.register<Exec>("combineIosSimulatorReleaseLib") {
    group = "ios"
    description = "Combine the Kanama iOS simulator release static library."
    dependsOn(":ios-runtime:linkReleaseStaticIosSimulatorArm64")
    dependsOn(compileIosSimulatorReleaseShim)
    val outputLibPath = iosBuildDir.map { it.file("lib/iphonesimulator/release/libkanama_ios.a") }
    inputs.file(iosRuntimeStaticLib("iosSimulatorArm64", "release"))
    inputs.file(iosBuildDir.map { it.file("shim/iphonesimulator/release/kanama_ios_shim.o") })
    outputs.file(outputLibPath)
    doFirst {
      val outputLib = outputLibPath.get().asFile
      outputLib.parentFile.mkdirs()
      commandLine(
        "xcrun",
        "--sdk",
        "iphonesimulator",
        "libtool",
        "-static",
        "-o",
        outputLib.absolutePath,
        iosBuildDir
          .get()
          .file("shim/iphonesimulator/release/kanama_ios_shim.o")
          .asFile
          .absolutePath,
        iosRuntimeStaticLib("iosSimulatorArm64", "release").get().asFile.absolutePath,
      )
      environment("DEVELOPER_DIR", xcodeDeveloperDir.get())
    }
  }

val createIosDebugXcframework =
  registerCreateIosXcframeworkTask(
    "createIosDebugXcframework",
    combineIosDeviceDebugLib,
    combineIosSimulatorDebugLib,
    iosBuildDir.map { it.file("lib/iphoneos/debug/libkanama_ios.a") },
    iosBuildDir.map { it.file("lib/iphonesimulator/debug/libkanama_ios.a") },
    iosDebugXcframeworkDir,
  )
val createIosReleaseXcframework =
  registerCreateIosXcframeworkTask(
    "createIosReleaseXcframework",
    combineIosDeviceReleaseLib,
    combineIosSimulatorReleaseLib,
    iosBuildDir.map { it.file("lib/iphoneos/release/libkanama_ios.a") },
    iosBuildDir.map { it.file("lib/iphonesimulator/release/libkanama_ios.a") },
    iosReleaseXcframeworkDir,
  )

val createIosDeviceDebugXcframework =
  registerCreateIosDeviceXcframeworkTask(
    "createIosDeviceDebugXcframework",
    combineIosDeviceDebugLib,
    iosBuildDir.map { it.file("lib/iphoneos/debug/libkanama_ios.a") },
    iosDeviceDebugXcframeworkDir,
  )
val createIosDeviceReleaseXcframework =
  registerCreateIosDeviceXcframeworkTask(
    "createIosDeviceReleaseXcframework",
    combineIosDeviceReleaseLib,
    iosBuildDir.map { it.file("lib/iphoneos/release/libkanama_ios.a") },
    iosDeviceReleaseXcframeworkDir,
  )

tasks.register("assembleIosKanamaXcframework") {
  group = "ios"
  description = "Build the experimental Kanama iOS Kotlin/Native device and simulator xcframeworks."
  dependsOn(createIosDebugXcframework)
  dependsOn(createIosReleaseXcframework)
}

tasks.register("assembleIosDeviceKanamaXcframework") {
  group = "ios"
  description = "Build the experimental Kanama iOS Kotlin/Native device-only xcframeworks."
  dependsOn(createIosDeviceDebugXcframework)
  dependsOn(createIosDeviceReleaseXcframework)
}

tasks.register<Exec>("verifyIosGodotTemplate") {
  group = "ios"
  description = "Verify that the Godot iOS export template has arm64 simulator support."
  doFirst {
    commandLine(
      layout.projectDirectory.file("scripts/ios_template_preflight.sh").asFile.absolutePath,
      "--xcode-developer-dir",
      xcodeDeveloperDir.get(),
      godotIosTemplateZip.get(),
    )
    environment("DEVELOPER_DIR", xcodeDeveloperDir.get())
  }
}

val iosGdextensionDescriptorFile = layout.buildDirectory.file("generated/ios/kanama.gdextension")
val generateIosGdextensionDescriptor by
  tasks.registering {
    outputs.file(iosGdextensionDescriptorFile)
    doLast {
      iosGdextensionDescriptorFile.get().asFile.apply {
        parentFile.mkdirs()
        writeText(
          """
                |[configuration]
                |
                |entry_symbol = "kanama_entry"
                |compatibility_minimum = "4.7"
                |
                |[libraries]
                |
                |macos.debug = "res://addons/kanama/libkanama_bootstrap.dylib"
                |macos.release = "res://addons/kanama/libkanama_bootstrap.dylib"
                |linux.debug.x86_64 = "res://addons/kanama/libkanama_bootstrap.so"
                |linux.release.x86_64 = "res://addons/kanama/libkanama_bootstrap.so"
                |linux.debug.arm64 = "res://addons/kanama/libkanama_bootstrap.so"
                |linux.release.arm64 = "res://addons/kanama/libkanama_bootstrap.so"
                |windows.debug.x86_64 = "res://addons/kanama/kanama_bootstrap.dll"
                |windows.release.x86_64 = "res://addons/kanama/kanama_bootstrap.dll"
                |ios.debug.arm64 = "res://addons/kanama/bin/ios/kanama_ios.debug.xcframework"
                |ios.release.arm64 = "res://addons/kanama/bin/ios/kanama_ios.release.xcframework"
                |"""
            .trimMargin() + System.lineSeparator()
        )
      }
    }
  }

tasks.register<Copy>("installIosAddon") {
  group = "ios"
  description = "Install the experimental iOS Kanama addon artifacts into a Godot project."
  val targetProjectDir =
    providers
      .gradleProperty("kanamaIosProjectDir")
      .orElse(providers.gradleProperty("kanamaProjectDir"))
  val extensionListFile = targetProjectDir.map { file(it).resolve(".godot/extension_list.cfg") }
  // Mirror installAddonJar: the iOS install overwrites kanama.gdextension with the
  // iOS+desktop descriptor, which would DROP a project's Android library entries. Detect
  // existing Android metadata before the copy and re-apply it after, so installing the
  // iOS addon never regresses Android support (Match3 / 3D-Platformer ship Android).
  val preserveAndroidExtensionMetadata = objects.property<Boolean>().convention(false)
  val selectedIosXcframeworkMode = iosXcframeworkMode.get().lowercase()
  val useFullIosXcframework =
    when (selectedIosXcframeworkMode) {
      "device",
      "iphoneos" -> false
      "full",
      "all",
      "simulator",
      "iphonesimulator" -> true
      else ->
        throw GradleException(
          "Unsupported kanamaIosXcframeworkMode=$selectedIosXcframeworkMode. Use device or full."
        )
    }
  val selectedDebugXcframeworkDir =
    if (useFullIosXcframework) iosDebugXcframeworkDir else iosDeviceDebugXcframeworkDir
  val selectedReleaseXcframeworkDir =
    if (useFullIosXcframework) iosReleaseXcframeworkDir else iosDeviceReleaseXcframeworkDir

  dependsOn(
    if (useFullIosXcframework) "assembleIosKanamaXcframework"
    else "assembleIosDeviceKanamaXcframework"
  )
  dependsOn(generateIosGdextensionDescriptor)
  dependsOn(buildNativeBootstrap)
  dependsOn(tasks.named("jar"))
  dependsOn(":project-scripts:jar")

  from(layout.projectDirectory.dir("example_project/addons/kanama")) {
    include("*.uid", "*.dylib", "*.so", "*.dll")
  }
  from(iosGdextensionDescriptorFile) { rename { "kanama.gdextension" } }
  from(layout.buildDirectory.file("libs/kanama.jar"))
  from(project(":project-scripts").tasks.named<Jar>("jar").flatMap { it.archiveFile })
  from(selectedDebugXcframeworkDir) { into("bin/ios/kanama_ios.debug.xcframework") }
  from(selectedReleaseXcframeworkDir) { into("bin/ios/kanama_ios.release.xcframework") }
  into(targetProjectDir.map { file(it).resolve("addons/kanama") })
  outputs.file(extensionListFile)

  doFirst {
    if (!targetProjectDir.isPresent) {
      throw GradleException(
        "Missing -PkanamaIosProjectDir=/absolute/path/to/godot_project for installIosAddon"
      )
    }
    val targetProject = file(targetProjectDir.get())
    val existingExtension = targetProject.resolve("addons/kanama/kanama.gdextension")
    preserveAndroidExtensionMetadata.set(
      targetProject.resolve("android/plugins/KanamaAndroid.gdap").isFile ||
        (existingExtension.isFile &&
          existingExtension.readText().contains("android_aar_plugin = true"))
    )
    targetProject.resolve("addons/kanama/bin/ios").deleteRecursively()
  }

  doLast {
    val extensionList = extensionListFile.get()
    val extensionPath = "res://addons/kanama/kanama.gdextension"
    extensionList.parentFile.mkdirs()
    val existing = if (extensionList.isFile) extensionList.readLines() else emptyList()
    if (extensionPath !in existing) {
      extensionList.writeText(
        (existing + extensionPath).joinToString(System.lineSeparator()) + System.lineSeparator()
      )
    }

    val extensionFile = file(targetProjectDir.get()).resolve("addons/kanama/kanama.gdextension")
    extensionFile.enableIosKanamaGdextensionMetadata()
    if (preserveAndroidExtensionMetadata.get()) {
      extensionFile.enableAndroidKanamaGdextensionMetadata()
      check(extensionFile.readText().contains("android.debug.arm64")) {
        "installIosAddon dropped Android gdextension entries for an Android-enabled project " +
          "(${extensionFile.absolutePath}) — the iOS install must not regress Android."
      }
    }
  }
}

tasks.register<Copy>("installAddonJar") {
  val targetProjectDir = providers.gradleProperty("kanamaProjectDir")
  val extensionListFile = targetProjectDir.map { file(it).resolve(".godot/extension_list.cfg") }
  val preserveAndroidExtensionMetadata = objects.property<Boolean>().convention(false)
  val byteStableFiles = setOf("kanama.jar", "kanama-scripts.jar")

  dependsOn(tasks.named("jar"))
  dependsOn(":project-scripts:jar")
  dependsOn(buildNativeBootstrap)

  from(layout.projectDirectory.dir("example_project/addons/kanama")) {
    include("*.gdextension", "*.uid", "*.dylib", "*.so", "*.dll")
  }
  from(layout.buildDirectory.file("libs/kanama.jar"))
  from(project(":project-scripts").tasks.named<Jar>("jar").flatMap { it.archiveFile })
  into(targetProjectDir.map { file(it).resolve("addons/kanama") })
  outputs.file(extensionListFile)

  eachFile(
    object : org.gradle.api.Action<org.gradle.api.file.FileCopyDetails> {
      override fun execute(details: org.gradle.api.file.FileCopyDetails) {
        if (details.name in byteStableFiles && targetProjectDir.isPresent) {
          val installedFile: File =
            file(targetProjectDir.get()).resolve("addons/kanama").resolve(details.path)
          if (
            installedFile.isFile &&
              java.nio.file.Files.mismatch(details.file.toPath(), installedFile.toPath()) == -1L
          ) {
            details.exclude()
          }
        }
      }
    }
  )

  doFirst {
    if (!targetProjectDir.isPresent) {
      throw GradleException(
        "Missing -PkanamaProjectDir=/absolute/path/to/godot_project for installAddonJar"
      )
    }

    val targetProject = file(targetProjectDir.get())
    val existingExtension = targetProject.resolve("addons/kanama/kanama.gdextension")
    preserveAndroidExtensionMetadata.set(
      targetProject.resolve("android/plugins/KanamaAndroid.gdap").isFile ||
        (existingExtension.isFile &&
          existingExtension.readText().contains("android_aar_plugin = true"))
    )
  }

  doLast {
    val extensionList = extensionListFile.get()
    val extensionPath = "res://addons/kanama/kanama.gdextension"
    extensionList.parentFile.mkdirs()
    val existing = if (extensionList.isFile) extensionList.readLines() else emptyList()
    if (extensionPath !in existing) {
      extensionList.writeText(
        (existing + extensionPath).joinToString(System.lineSeparator()) + System.lineSeparator()
      )
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
        "Missing -PkanamaStarterProjectDir=/absolute/path/to/godot_project for installStarterTemplate"
      )
    }
  }
}

tasks.register<Copy>("createStarterProject") {
  val targetProjectDir = providers.gradleProperty("kanamaStarterProjectDir")
  val allowOverwrite =
    providers.gradleProperty("kanamaStarterOverwrite").map(String::toBoolean).orElse(false)

  from(layout.projectDirectory.dir("templates/starter"))
  from(layout.projectDirectory.dir("templates/starter_project"))
  into(targetProjectDir.map { file(it) })

  doFirst {
    if (!targetProjectDir.isPresent) {
      throw GradleException(
        "Missing -PkanamaStarterProjectDir=/absolute/path/to/new_godot_project for createStarterProject"
      )
    }

    val targetProject = file(targetProjectDir.get())
    if (targetProject.resolve("project.godot").exists() && !allowOverwrite.get()) {
      throw GradleException(
        "Refusing to overwrite existing project.godot in ${targetProject.absolutePath}. " +
          "Use installStarterTemplate for existing projects or pass -PkanamaStarterOverwrite=true."
      )
    }
  }
}

val kanamaAndroidDemoDir = providers.gradleProperty("kanamaAndroidDemoDir")
val kanamaAndroidSdkDir =
  providers
    .environmentVariable("ANDROID_HOME")
    .orElse(providers.environmentVariable("ANDROID_SDK_ROOT"))

// Task 36 AAR split: the runtime AAR is project-agnostic (no demo dir); the
// per-project scripts AAR compiles the consumer project's kotlin-src + KSP
// registrars against the runtime classes. installAndroidPluginAar installs
// both, with the scripts AAR pulled in as a `.gdap` local dependency so the
// export preset still only knows the single "KanamaAndroid" plugin.
tasks.register<Exec>("assembleAndroidPluginAar") {
  group = "android"
  description = "Build the project-agnostic Kanama Android runtime plugin AAR (experimental)."

  doFirst {
    if (!kanamaAndroidSdkDir.isPresent) {
      throw GradleException("Missing ANDROID_HOME or ANDROID_SDK_ROOT for Android plugin build")
    }
    environment("ANDROID_HOME", kanamaAndroidSdkDir.get())
    environment("ANDROID_SDK_ROOT", kanamaAndroidSdkDir.get())
    commandLine(
      layout.projectDirectory.file("gradlew").asFile.absolutePath,
      "-p",
      "android/godot-plugin",
      ":plugin:assembleDebug",
      "-PkanamaPanamaPortCore=${panamaPortCoreDependency.get()}",
    )
  }
}

tasks.register<Exec>("assembleAndroidScriptsAar") {
  group = "android"
  description =
    "Build the per-project Kanama Android scripts AAR (kotlin-src + KSP registrars) for a demo project."

  doFirst {
    if (!kanamaAndroidDemoDir.isPresent) {
      throw GradleException("Missing -PkanamaAndroidDemoDir=/absolute/path/to/kanama demo project")
    }
    if (!kanamaAndroidSdkDir.isPresent) {
      throw GradleException("Missing ANDROID_HOME or ANDROID_SDK_ROOT for Android plugin build")
    }
    environment("ANDROID_HOME", kanamaAndroidSdkDir.get())
    environment("ANDROID_SDK_ROOT", kanamaAndroidSdkDir.get())
    commandLine(
      layout.projectDirectory.file("gradlew").asFile.absolutePath,
      "-p",
      "android/godot-plugin",
      ":scripts:assembleDebug",
      "-PkanamaAndroidDemoDir=${kanamaAndroidDemoDir.get()}",
      "-PkanamaPanamaPortCore=${panamaPortCoreDependency.get()}",
    )
  }
}

tasks.register("installAndroidPluginAar") {
  group = "android"
  description =
    "Build and install the Kanama Android runtime + scripts AARs into a demo project (experimental)."
  dependsOn(tasks.named("assembleAndroidPluginAar"))
  dependsOn(tasks.named("assembleAndroidScriptsAar"))

  doLast {
    if (!kanamaAndroidDemoDir.isPresent) {
      throw GradleException("Missing -PkanamaAndroidDemoDir=/absolute/path/to/kanama demo project")
    }

    val pluginsDir = file(kanamaAndroidDemoDir.get()).resolve("android/plugins")
    pluginsDir.mkdirs()

    copy {
      from(
        layout.projectDirectory.file(
          "android/godot-plugin/plugin/build/outputs/aar/plugin-debug.aar"
        )
      )
      into(pluginsDir)
      rename { "KanamaAndroid.debug.aar" }
    }
    copy {
      from(
        layout.projectDirectory.file(
          "android/godot-plugin/scripts/build/outputs/aar/scripts-debug.aar"
        )
      )
      into(pluginsDir)
      rename { "KanamaAndroidScripts.debug.aar" }
    }

    pluginsDir
      .resolve("KanamaAndroid.gdap")
      .writeText(
        """
            |[config]
            |
            |name="KanamaAndroid"
            |binary_type="local"
            |binary="KanamaAndroid.debug.aar"
            |
            |[dependencies]
            |local=["KanamaAndroidScripts.debug.aar"]
            |remote=["${panamaPortCoreDependency.get()}"]
            |"""
          .trimMargin()
      )

    val extensionFile = file(kanamaAndroidDemoDir.get()).resolve("addons/kanama/kanama.gdextension")
    if (!extensionFile.isFile) {
      throw GradleException("Missing Kanama GDExtension descriptor: ${extensionFile.absolutePath}")
    }
    extensionFile.enableAndroidKanamaGdextensionMetadata()
  }
}

// --- Packaged mobile add-on, iOS (task 25 B3; design + exit criteria in
// docs/internals/release-support-decision.md §7). Maintainer-built on macOS
// (needs Xcode), so it does not join packageDistributions or the CI package
// workflow. Validate with scripts/package_install_smoke.sh --ios-addon.

val mobileAddonIosExtrasDir = layout.buildDirectory.dir("generated/mobile-addon/ios")
val prepareMobileAddonIosExtras by
  tasks.registering {
    outputs.dir(mobileAddonIosExtrasDir)
    doLast {
      val dir = mobileAddonIosExtrasDir.get().asFile
      dir.mkdirs()
      dir
        .resolve("kanama.gdextension-ios-entries.txt")
        .writeText(
          """
            |# Merge into [libraries] of addons/kanama/kanama.gdextension
            |# (README step 3). Do not replace the whole descriptor.
            |ios.debug.arm64 = "res://addons/kanama/bin/ios/kanama_ios.debug.xcframework"
            |ios.release.arm64 = "res://addons/kanama/bin/ios/kanama_ios.release.xcframework"
            |"""
            .trimMargin()
        )
      dir
        .resolve("README.md")
        .writeText(
          """
            |# Kanama iOS add-on (experimental, prebuilt runtime)
            |
            |Prebuilt Kanama iOS runtime xcframeworks (device arm64 slices,
            |debug + release; the static libraries are large — roughly 200 MB
            |debug / 88 MB release before zip) for an existing Kanama Godot
            |project.
            |
            |**Honest caveat:** this delivers the *runtime* only. Compiling your
            |project's Kotlin scripts for iOS still requires a Kanama source
            |checkout today (`installIosAddon` runs the Kotlin/Native + KSP
            |build). Use this artifact to update the prebuilt runtime or for
            |script-less evaluation; see docs/exporting/ios.md.
            |
            |1. Unzip at your Godot project root (adds addons/kanama/bin/ios/*).
            |2. The project must already carry the desktop Kanama addon with
            |   matching version ${project.version}.
            |3. Merge kanama.gdextension-ios-entries.txt into
            |   addons/kanama/kanama.gdextension.
            |4. Export with the Godot ${godotVersion.get()} iOS templates.
            |"""
            .trimMargin()
        )
    }
  }

tasks.register<Zip>("packageMobileAddonIos") {
  group = "distribution"
  description =
    "Zip the experimental iOS device xcframeworks (debug+release) + install notes; maintainer-built on macOS, see release-support-decision §7 B3."
  dependsOn(tasks.named("assembleIosDeviceKanamaXcframework"), prepareMobileAddonIosExtras)
  archiveFileName.set("kanama-mobile-addon-ios-v${project.version}.zip")
  destinationDirectory.set(layout.buildDirectory.dir("distributions"))
  isPreserveFileTimestamps = false
  isReproducibleFileOrder = true
  from(iosBuildDir.map { it.dir("xcframework-device/debug/kanama_ios.debug.xcframework") }) {
    into("addons/kanama/bin/ios/kanama_ios.debug.xcframework")
  }
  from(iosBuildDir.map { it.dir("xcframework-device/release/kanama_ios.release.xcframework") }) {
    into("addons/kanama/bin/ios/kanama_ios.release.xcframework")
  }
  from(mobileAddonIosExtrasDir.map { it.file("kanama.gdextension-ios-entries.txt") })
  from(mobileAddonIosExtrasDir.map { it.file("README.md") })
  from(layout.projectDirectory.file("LICENSE"))
}

// --- Packaged mobile add-on, Android (task 25 B3 / task 36 AAR split). The
// runtime AAR is project-agnostic since the task-36 split, so it can ship
// prebuilt. The packaged .gdap deliberately carries NO `local=` scripts entry:
// Godot's plugin-config validation requires listed local dependencies to
// exist, and the per-project scripts AAR is produced by the consumer's own
// installAndroidPluginAar run (which rewrites the .gdap with the local
// entry). Maintainer-built (needs the Android SDK), so it does not join
// packageDistributions or the CI package workflow. Validate with
// scripts/package_install_smoke.sh --android-addon.

val mobileAddonAndroidExtrasDir = layout.buildDirectory.dir("generated/mobile-addon/android")
val prepareMobileAddonAndroidExtras by
  tasks.registering {
    outputs.dir(mobileAddonAndroidExtrasDir)
    doLast {
      val dir = mobileAddonAndroidExtrasDir.get().asFile
      dir.mkdirs()
      dir
        .resolve("KanamaAndroid.gdap")
        .writeText(
          """
            |[config]
            |
            |name="KanamaAndroid"
            |binary_type="local"
            |binary="KanamaAndroid.debug.aar"
            |
            |[dependencies]
            |remote=["${panamaPortCoreDependency.get()}"]
            |"""
            .trimMargin()
        )
      dir
        .resolve("kanama.gdextension-android-entries.txt")
        .writeText(
          """
            |# Merge into addons/kanama/kanama.gdextension (README step 3).
            |# Do not replace the whole descriptor.
            |#
            |# [configuration] section:
            |android_aar_plugin = true
            |# [libraries] section:
            |android.debug.arm64 = "libkanama_bootstrap.so"
            |android.release.arm64 = "libkanama_bootstrap.so"
            |android.debug.x86_64 = "libkanama_bootstrap.so"
            |android.release.x86_64 = "libkanama_bootstrap.so"
            |"""
            .trimMargin()
        )
      dir
        .resolve("README.md")
        .writeText(
          """
            |# Kanama Android add-on (experimental, prebuilt runtime, debug-only)
            |
            |Prebuilt Kanama Android runtime plugin AAR (arm64-v8a + x86_64,
            |debug build) for an existing Kanama Godot project using Godot's
            |Gradle Android build.
            |
            |**Honest caveats:**
            |- Debug-only: no release AAR exists yet (recorded in the Kanama
            |  release-support decision record).
            |- This delivers the *runtime* only. Compiling your project's Kotlin
            |  scripts for Android still requires a Kanama source checkout today
            |  (`installAndroidPluginAar` builds the per-project scripts AAR and
            |  rewrites KanamaAndroid.gdap to reference it). Use this artifact
            |  for script-less evaluation or prebuilt-runtime updates; see
            |  docs/exporting/android.md.
            |- The PanamaPort dependency resolves remotely from JitPack
            |  (declared in KanamaAndroid.gdap); the export machine needs
            |  network access or a local Maven mirror.
            |
            |1. Unzip at your Godot project root (adds android/plugins/*).
            |2. The project must already carry the desktop Kanama addon with
            |   matching version ${project.version}, and the Android export
            |   preset must use the Gradle build with the KanamaAndroid plugin
            |   enabled.
            |3. Merge kanama.gdextension-android-entries.txt into
            |   addons/kanama/kanama.gdextension.
            |4. Export with the Godot ${godotVersion.get()} Android templates.
            |"""
            .trimMargin()
        )
    }
  }

tasks.register<Zip>("packageMobileAddonAndroid") {
  group = "distribution"
  description =
    "Zip the experimental project-agnostic Android runtime AAR + install notes; maintainer-built, see release-support-decision §7 B3."
  dependsOn(tasks.named("assembleAndroidPluginAar"), prepareMobileAddonAndroidExtras)
  archiveFileName.set("kanama-mobile-addon-android-v${project.version}.zip")
  destinationDirectory.set(layout.buildDirectory.dir("distributions"))
  isPreserveFileTimestamps = false
  isReproducibleFileOrder = true
  from(
    layout.projectDirectory.file("android/godot-plugin/plugin/build/outputs/aar/plugin-debug.aar")
  ) {
    into("android/plugins")
    rename { "KanamaAndroid.debug.aar" }
  }
  from(mobileAddonAndroidExtrasDir.map { it.file("KanamaAndroid.gdap") }) {
    into("android/plugins")
  }
  from(mobileAddonAndroidExtrasDir.map { it.file("kanama.gdextension-android-entries.txt") })
  from(mobileAddonAndroidExtrasDir.map { it.file("README.md") })
  from(layout.projectDirectory.file("LICENSE"))
}
