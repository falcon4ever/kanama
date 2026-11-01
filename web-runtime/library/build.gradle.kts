// The Kanama Web runtime as a library (task 135 item 5): the Kotlin/Wasm runtime with no project
// script in it, published as net.multigesture.kanama:kanama-web (the `kanama-web-wasm-js` klib)
// into the package repository, so a kit or store-addon project's `exportWeb` links its own scripts
// against it without a Kanama checkout. The checkout's executable (:web-runtime) depends on it the
// same way: every Web export, the demo matrix's and a kit user's, links one runtime klib with the
// project's scripts and the registry and main() KSP generates for them.
//
// The sources stay where they always were (web-runtime/src/commonMain, wasmJsMain, wasmJsTest);
// this module compiles them. No KSP runs here: a registry in this klib would collide with the
// project's own at the link ("IrClassSymbolImpl is already bound").
plugins { kotlin("multiplatform") }

val runtimeDir: File = rootProject.file("web-runtime")

// Task 129 E: the shared api sources Web compiles. The directory is the native wrapper tree's;
// everything else in it is excluded from the Web build and from ktfmt (the wrapper tree is
// drift-gated, not formatted: see the root build's ktfmt note).
val sharedApiDir: File = rootProject.file("src/commonMain/kotlin/net/multigesture/kanama/api")
val sharedApiFiles = setOf("GD.kt", "Mathf.kt", "GodotCallable.kt")

tasks.withType<com.ncorti.ktfmt.gradle.tasks.KtfmtBaseTask>().configureEach {
  exclude { element -> element.file.parentFile == sharedApiDir }
}

kotlin {
  // A sources jar is not shipped: nothing in a kit project reads it.
  withSourcesJar(publish = false)

  @OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)
  wasmJs {
    browser {
      // The unit tests run on Node (below); a browser test run would need Karma + Chrome.
      testTask { enabled = false }
    }
    // Task 134 A2: the value types' unit test (src/wasmJsTest) runs on Node, not in a browser.
    nodejs()
  }

  sourceSets {
    commonMain {
      kotlin.setSrcDirs(listOf(runtimeDir.resolve("src/commonMain/kotlin")))
      resources.setSrcDirs(emptyList<Any>())
      // Phase 0 keeps the existing JVM annotations module untouched while proving that the
      // same source declarations compile for Wasm. A post-GO common API migration can turn
      // :annotations into a real KMP dependency without coupling that refactor to the spike.
      kotlin.srcDir(rootProject.file("annotations/src/main/kotlin"))
      // Task 134 A2: the value types' storage, printing and real_t formulas are ONE source with
      // the native types (src/commonMain/.../types/shared); WebReal.kt supplies the float32
      // `GodotRealStorage`, `narrowReal` and `widenReal` the native side generates.
      kotlin.srcDir(rootProject.file("src/commonMain/kotlin/net/multigesture/kanama/types"))
      // Task 134 D2: Godot's String / PackedByteArray methods on Kotlin's String and ByteArray,
      // the same generated source as native; their engine calls cross through WebBuiltinRemote.
      kotlin.srcDir(rootProject.file("src/commonMain/kotlin/net/multigesture/kanama/builtins"))
      // Task 131 item 14: the script-error report (its fields and the game-frame rule) is ONE
      // source with desktop, Android and iOS; Web supplies only its frames and its way out.
      kotlin.srcDir(rootProject.file("src/commonMain/kotlin/net/multigesture/kanama/scripterrors"))
      // Task 129 E: GD (generated from extension_api.json's utility functions), Mathf and
      // GodotCallable are ONE source with desktop and iOS. The shared api directory is added
      // and every other file in it excluded; GD's engine calls cross through
      // WebBuiltinRemote (the builtin opcode's utility form).
      kotlin.srcDir(sharedApiDir)
      kotlin.exclude { element ->
        !element.isDirectory &&
          element.file.parentFile == sharedApiDir &&
          element.name !in sharedApiFiles
      }
      dependencies {
        implementation(kotlin("stdlib"))
        // `api`: a project's scripts and its generated code compile against both (the backend
        // contract's opt-in marker, the coroutine types the script API returns).
        api(project(":kanama-common-api"))
        api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.11.0")
      }
    }
    wasmJsMain {
      kotlin.setSrcDirs(listOf(runtimeDir.resolve("src/wasmJsMain/kotlin")))
      resources.setSrcDirs(emptyList<Any>())
    }
    wasmJsTest {
      kotlin.setSrcDirs(listOf(runtimeDir.resolve("src/wasmJsTest/kotlin")))
      dependencies { implementation(kotlin("test")) }
    }
  }
}

// A project script in the runtime's own sources would be compiled into the klib with no registry
// entry (no KSP runs here), and would be one more class the user never wrote: built-in scripts live
// beside the fixtures that use them (src/webSpikeGodot/kotlin, src/web3dSmoke).
val checkNoScriptClassInRuntime by
  tasks.registering {
    group = "verification"
    description = "Fails when the Web runtime's own sources declare a @ScriptClass."
    val roots = listOf("src/commonMain/kotlin", "src/wasmJsMain/kotlin").map(runtimeDir::resolve)
    inputs.files(roots.map { fileTree(it) { include("**/*.kt") } })
    doLast {
      val found =
        roots.flatMap { root ->
          root.walkTopDown().filter { file ->
            file.extension == "kt" &&
              file.readLines().any { it.trimStart().startsWith("@ScriptClass") }
          }.map { it.relativeTo(runtimeDir).invariantSeparatorsPath }.toList()
        }
      if (found.isNotEmpty()) {
        throw GradleException(
          "The Web runtime's sources declare @ScriptClass in ${found.joinToString()}: move the " +
            "script beside the fixture that uses it (web-runtime/src/webSpikeGodot/kotlin)."
        )
      }
    }
  }

tasks.named("compileKotlinWasmJs") { dependsOn(checkNoScriptClassInRuntime) }
