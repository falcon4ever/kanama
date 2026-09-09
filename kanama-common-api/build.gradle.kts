// The Web call contract: opcodes, call shapes, and the generated `InitialGodotCallDescriptors`
// derived from `extension_api.json` (via `scripts/platform_backend_calls.json`). Only the Web
// backend dispatches through it — native backends (desktop/Android JVM, iOS Kotlin/Native) call
// Godot in-process from generated ptrcall wrappers and never depended on this module beyond two
// adapters that were removed in task 95. Targets: `wasmJs` for `web-runtime`, `jvm` for the KSP
// processor's Web emitter and for the contract test (`jvmTest`).
plugins { kotlin("multiplatform") }

kotlin {
  jvm()
  jvmToolchain(25)

  @OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)
  wasmJs { browser() }

  sourceSets { commonTest.dependencies { implementation(kotlin("test")) } }
}

val checkPlatformBackendContract by
  tasks.registering(Exec::class) {
    group = "verification"
    description =
      "Checks the generated Web call descriptors against extension_api.json (hash and signature drift)."
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
