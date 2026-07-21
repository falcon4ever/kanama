package net.multigesture.kanama

import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.MemoryLayout
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout.ADDRESS
import java.lang.foreign.ValueLayout.JAVA_INT
import java.lang.invoke.MethodHandles
import java.lang.invoke.MethodType
import java.lang.reflect.Modifier
import net.multigesture.kanama.binding.KanamaHotReload
import net.multigesture.kanama.binding.KanamaResourceFormatLoader
import net.multigesture.kanama.binding.KanamaResourceFormatSaver
import net.multigesture.kanama.binding.KanamaScript
import net.multigesture.kanama.binding.KanamaScriptLanguage
import net.multigesture.kanama.binding.runtime.ClassDB
import net.multigesture.kanama.binding.runtime.GodotStrings
import net.multigesture.kanama.binding.runtime.installCommonGodotBackend
import net.multigesture.kanama.ffi.GodotFFI

/**
 * Entry point for the Kotlin side of Kanama.
 *
 * `bootstrap.c` calls [init] exactly once, immediately after starting the JVM, via the JNI
 * invocation API (`FindClass` + `CallStaticVoidMethod`). After this method returns, JNI is never
 * used again — all subsequent traffic between Godot and Kotlin goes through Panama (FFM).
 */
object KanamaBinding {

  private lateinit var library: MemorySegment

  @JvmStatic
  fun init(procAddr: Long, library: Long, initPtr: Long) {
    System.err.println(
      "[kanama:kt] init procAddr=0x${procAddr.toString(16)} " +
        "library=0x${library.toString(16)} initPtr=0x${initPtr.toString(16)}"
    )

    this.library = MemorySegment.ofAddress(library)
    GodotFFI.bootstrap(procAddr)
    installCommonGodotBackend()
    val version = fetchGodotVersion()
    System.err.println(
      "[kanama:kt] Godot version: " +
        "${version.major}.${version.minor}.${version.patch} (${version.string})"
    )

    installInitCallbacks(initPtr)
  }

  /**
   * Layout of `GDExtensionGodotVersion`:
   * ```
   * typedef struct {
   *     uint32_t major;     // offset 0
   *     uint32_t minor;     // offset 4
   *     uint32_t patch;     // offset 8
   *     // 4 bytes padding — pointer is 8-byte aligned on 64-bit
   *     const char *string; // offset 16
   * } GDExtensionGodotVersion;
   * ```
   */
  private val godotVersionLayout: MemoryLayout =
    MemoryLayout.structLayout(
      JAVA_INT.withName("major"),
      JAVA_INT.withName("minor"),
      JAVA_INT.withName("patch"),
      MemoryLayout.paddingLayout(4),
      ADDRESS.withName("string"),
    )

  private data class GodotVersion(
    val major: Int,
    val minor: Int,
    val patch: Int,
    val string: String,
  )

  private fun fetchGodotVersion(): GodotVersion {
    val getVersion = GodotFFI.lookup("get_godot_version", FunctionDescriptor.ofVoid(ADDRESS))
    Arena.ofConfined().use { arena ->
      val struct = arena.allocate(godotVersionLayout)
      getVersion.invoke(struct)

      val major = struct.get(JAVA_INT, 0)
      val minor = struct.get(JAVA_INT, 4)
      val patch = struct.get(JAVA_INT, 8)
      val stringPtr: MemorySegment = struct.get(ADDRESS, 16)
      val stringValue = stringPtr.reinterpret(Long.MAX_VALUE).getString(0)

      return GodotVersion(major, minor, patch, stringValue)
    }
  }

  /**
   * Overwrite `initialize` and `deinitialize` in the `GDExtensionInitialization` struct with Panama
   * upcall stubs that land in Kotlin. bootstrap.c pre-filled the struct with C-side defaults as a
   * fallback; after this runs, Godot calls us through Panama instead.
   *
   * Layout of `GDExtensionInitialization`:
   * ```
   * typedef struct {
   *     GDExtensionInitializationLevel minimum_initialization_level; // int32, offset 0
   *     // 4 bytes padding
   *     void *userdata;     // offset 8
   *     void *initialize;   // offset 16
   *     void *deinitialize; // offset 24
   * } GDExtensionInitialization;
   * ```
   */
  private fun installInitCallbacks(initPtr: Long) {
    val lifecycleDescriptor = FunctionDescriptor.ofVoid(ADDRESS, JAVA_INT)
    val lookup = MethodHandles.lookup()
    val methodType =
      MethodType.methodType(Void.TYPE, MemorySegment::class.java, Int::class.javaPrimitiveType)
    val initializeHandle =
      lookup.findStatic(KanamaBinding::class.java, "initializeCallback", methodType)
    val deinitializeHandle =
      lookup.findStatic(KanamaBinding::class.java, "deinitializeCallback", methodType)

    val initializeStub =
      GodotFFI.linker.upcallStub(initializeHandle, lifecycleDescriptor, GodotFFI.arena)
    val deinitializeStub =
      GodotFFI.linker.upcallStub(deinitializeHandle, lifecycleDescriptor, GodotFFI.arena)

    val initStruct = MemorySegment.ofAddress(initPtr).reinterpret(32)
    initStruct.set(ADDRESS, 16, initializeStub)
    initStruct.set(ADDRESS, 24, deinitializeStub)

    System.err.println("[kanama:kt] installed Panama upcall stubs for init/deinit")
  }

  private const val INITIALIZATION_SCENE = 2

  @JvmStatic
  fun initializeCallback(userdata: MemorySegment, level: Int) {
    System.err.println("[kanama:kt] initialize: level=$level")
    if (level == INITIALIZATION_SCENE) {
      try {
        // Register Script resource class before the language (language creates scripts).
        KanamaScript.register(library)
        // Register and add the Kanama script language to the engine.
        KanamaScriptLanguage.register(library)
        // Register ResourceFormatLoader for `.kt` files.
        KanamaResourceFormatLoader.register(library)
        // Register ResourceFormatSaver for `.kt` files.
        KanamaResourceFormatSaver.register(library)
        // Register runtime-bundled @RegisterClass / @ScriptClass types, if any.
        registerRuntimeClasses()
        // Start editor/runtime script hot-reload loop.
        KanamaHotReload.initialize(library)
      } catch (t: Throwable) {
        System.err.println(
          "[kanama:kt] initialize callback failed: ${t::class.qualifiedName}: ${t.message}"
        )
        t.printStackTrace(System.err)
        throw t
      }
    }
  }

  private fun registerRuntimeClasses() {
    val registryClass =
      try {
        Class.forName("net.multigesture.kanama.generated.KanamaRegistry")
      } catch (_: ClassNotFoundException) {
        return
      }
    val registerAll = registryClass.getMethod("registerAll", MemorySegment::class.java)
    if (Modifier.isStatic(registerAll.modifiers)) {
      registerAll.invoke(null, library)
    } else {
      val instance = registryClass.getField("INSTANCE").get(null)
      registerAll.invoke(instance, library)
    }
  }

  @JvmStatic
  fun deinitializeCallback(userdata: MemorySegment, level: Int) {
    System.err.println("[kanama:kt] deinitialize: level=$level")
    if (level == INITIALIZATION_SCENE) {
      KanamaHotReload.shutdown()
      KanamaResourceFormatLoader.unregister()
      KanamaResourceFormatSaver.unregister()
      KanamaScript.destroyConstructedScripts()
      KanamaScriptLanguage.unregister()
      ClassDB.unregisterRegisteredClasses(library)
      GodotStrings.destroyOwnedStringNames()
    }
  }
}
