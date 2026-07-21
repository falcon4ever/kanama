plugins { kotlin("multiplatform") }

kotlin {
  jvm()
  jvmToolchain(25)
  iosArm64()
  iosSimulatorArm64()

  @OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)
  wasmJs { browser() }

  sourceSets {
    val commonMain by getting
    val iosMain by creating { dependsOn(commonMain) }
    val iosArm64Main by getting { dependsOn(iosMain) }
    val iosSimulatorArm64Main by getting { dependsOn(iosMain) }
    commonTest.dependencies { implementation(kotlin("test")) }
  }
}

val checkPlatformBackendContract by
  tasks.registering(Exec::class) {
    group = "verification"
    description = "Checks the generated initial platform-backend call descriptors."
    commandLine(
      "python3",
      rootProject.file("scripts/generate_platform_backend_contract.py").absolutePath,
      "--api",
      rootProject.file("extension_api.json").absolutePath,
      "--output",
      project.file(
          "src/commonMain/kotlin/net/multigesture/kanama/backend/InitialGodotCallDescriptors.generated.kt"
        )
        .absolutePath,
      "--check",
    )
  }

tasks.named("check") { dependsOn(checkPlatformBackendContract) }
