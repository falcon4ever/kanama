package net.multigesture.kanama.scripterrors

import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout.JAVA_INT
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertSame
import kotlin.test.assertTrue
import net.multigesture.kanama.binding.ChildFirstClassLoader
import net.multigesture.kanama.binding.KanamaHotReload
import net.multigesture.kanama.binding.ScriptBridge
import net.multigesture.kanama.binding.runtime.ScriptErrorReport
import net.multigesture.kanama.binding.runtime.ScriptErrors
import net.multigesture.kanama.binding.runtime.SignalCallables
import net.multigesture.kanama.binding.runtime.SignalCallbackRegistry

/**
 * Task 131 item 3 (F9): a lambda connection's closure lives until Godot drops the connection's
 * custom Callable. These drive the Callable's two upcall targets the way Godot does -- `call_func`
 * ([SignalCallables.call]) and `free_func` ([SignalCallables.free]) -- without an engine. WHY Godot
 * drops a Callable (receiver freed, emitter freed, one-shot fired, disconnected) is engine
 * behaviour, proved per case by `signal_leak_smoke.tscn` in the runtime smoke; every case ends in
 * the same `free_func`. The registry is process-wide, so assertions are deltas.
 */
class SignalCallbackRegistryTest {
  private val registered = mutableListOf<Long>()
  private val reports = mutableListOf<ScriptErrorReport>()

  private val builds = mutableListOf<ChildFirstClassLoader>()

  private fun register(argumentCount: Int, callback: (List<Any?>) -> Unit): Long =
    SignalCallbackRegistry.register(argumentCount, callback).also { registered += it }

  @AfterTest
  fun cleanUp() {
    registered.forEach(SignalCallbackRegistry::unregister)
    ScriptErrors.sinkOverride = null
    builds.forEach(ChildFirstClassLoader::close)
  }

  /**
   * A script build: a class loader of its own (as each hot reload makes one) that defines
   * [ScriptBuildCallback], and an instance of that class -- what a script lambda is to the runtime.
   */
  private fun scriptBuild(reloadId: Long): Pair<ChildFirstClassLoader, Any> {
    val name = ScriptBuildCallback::class.java.name
    val loader =
      ChildFirstClassLoader(
        urls = arrayOf(ScriptBuildCallback::class.java.protectionDomain.codeSource.location),
        parent = SignalCallbackRegistryTest::class.java.classLoader,
        childFirstPrefixes = listOf(name),
        reloadId = reloadId,
      )
    builds += loader
    val callback = loader.loadClass(name).getDeclaredConstructor().newInstance()
    assertSame(loader, callback.javaClass.classLoader)
    return loader to callback
  }

  private fun connect(
    origin: Any?,
    emitter: String,
    signal: String,
    onRelease: (() -> Unit)? = null,
  ): Long {
    val id = SignalCallbackRegistry.register(0, onRelease, origin) {}
    registered += id
    SignalCallbackRegistry.noteConnection(
      id,
      SignalCallbackRegistry.Connection(emitter.hashCode().toLong(), 1L, signal, 2L),
    )
    emitterNames[emitter.hashCode().toLong()] = emitter
    return id
  }

  private val emitterNames = mutableMapOf<Long, String>()
  private val disconnected = mutableListOf<Long>()
  private val warnings = mutableListOf<String>()

  private fun sweep(vararg loaders: ChildFirstClassLoader): Int =
    KanamaHotReload.sweepBuilds(
      loaders.toList(),
      describe = { "${emitterNames[it.emitterAddress]}.${it.signal}" },
      disconnect = { id, _ -> disconnected += id },
      warn = { warnings += it },
    )

  /** Calls [SignalCallables.call] like Godot's CallableCustomExtension::call; returns the error. */
  private fun godotCall(id: Long, argCount: Long = 0): Triple<Int, Int, Int> =
    Arena.ofConfined().use { arena ->
      val error = arena.allocate(12L, 4L)
      error.set(JAVA_INT, 0, 0x7777) // Godot does not initialise r_error: call_func must
      val ret = arena.allocate(24L, 8L)
      SignalCallables.call(MemorySegment.ofAddress(id), MemorySegment.NULL, argCount, ret, error)
      Triple(error.get(JAVA_INT, 0), error.get(JAVA_INT, 4), error.get(JAVA_INT, 8))
    }

  @Test
  fun theCallableFreeFuncReleasesTheClosure() {
    val before = SignalCallbackRegistry.size
    var fired = 0
    val id = register(0) { fired++ }
    assertEquals(before + 1, SignalCallbackRegistry.size)

    assertEquals(Triple(0, 0, 0), godotCall(id))
    assertEquals(1, fired)

    // Godot dropped the connection: receiver freed, emitter freed, one-shot fired, disconnected.
    SignalCallables.free(MemorySegment.ofAddress(id))

    assertEquals(before, SignalCallbackRegistry.size)
    assertEquals(Triple(0, 0, 0), godotCall(id)) // a late call is a harmless no-op
    assertEquals(1, fired)
  }

  @Test
  fun releaseIsIdempotentAcrossCloseAndFreeFunc() {
    val before = SignalCallbackRegistry.size
    val id = register(0) {}

    SignalCallbackRegistry.unregister(id) // SignalConnection.close()
    SignalCallables.free(MemorySegment.ofAddress(id)) // the original Callable's free_func
    SignalCallables.free(MemorySegment.ofAddress(id)) // the disconnect temporary's free_func

    assertEquals(before, SignalCallbackRegistry.size)
  }

  @Test
  fun tooFewEmittedArgumentsIsACallErrorNotACrash() {
    var fired = 0
    val id = register(2) { fired++ }

    // CALL_ERROR_TOO_FEW_ARGUMENTS = 4, expected = 2
    assertEquals(Triple(4, 0, 2), godotCall(id, argCount = 1))
    assertEquals(0, fired)
  }

  @Test
  fun aThrowingLambdaIsContainedReportedAndTheCallSucceeds() {
    ScriptErrors.sinkOverride = { reports += it }
    val id = register(0) { error("lambda failure") }

    assertEquals(Triple(0, 0, 0), godotCall(id)) // CALL_OK, as a GDScript lambda's runtime error

    assertEquals("java.lang.IllegalStateException: lambda failure", reports.single().message)
    assertEquals("SignalCallbackRegistryTest.kt", reports.single().file)
  }

  // ── Hot reload (lambda connections die with the build that made them) ──────────────────────

  @Test
  fun aConnectionIsTaggedWithTheBuildThatMadeItsCallback() {
    val (build, callback) = scriptBuild(reloadId = 5)
    val scriptMade = connect(callback, "KanamaSmokeAutoload", "probe")
    // A runtime closure (here: one defined by this test, outside any script build) is untagged,
    // even when it captures script state: Godot's own lifetime rules release it, as before.
    val runtimeMade = connect({ callback.hashCode() }, "KanamaSmokeAutoload", "probe")

    assertSame(build, SignalCallbackRegistry.entry(scriptMade)?.build)
    assertNull(SignalCallbackRegistry.entry(runtimeMade)?.build)
    assertEquals(setOf(scriptMade), SignalCallbackRegistry.connectionsOf(build).keys)
  }

  @Test
  fun aSweepDropsTheRetiredBuildsConnectionsWithOneWarningAndNoReleaseHook() {
    val (build, callback) = scriptBuild(reloadId = 2)
    var released = 0
    val ids =
      listOf(
        connect(callback, "SceneTree", "process_frame") { released++ },
        connect(callback, "KanamaSmokeAutoload", "probe"),
        connect(callback, "KanamaSmokeAutoload", "probe"),
      )
    val other = connect({}, "KanamaSmokeAutoload", "probe")

    assertEquals(3, sweep(build))

    ids.forEach { assertNull(SignalCallbackRegistry.entry(it)) }
    assertTrue(SignalCallbackRegistry.entry(other) != null)
    assertEquals(ids.toSet(), disconnected.toSet())
    assertEquals(0, released) // unregistered, not released: the connection's owner is gone
    assertEquals(
      listOf(
        "hot-reload: dropped 3 lambda connection(s) of build 2 whose script objects are gone " +
          "(SceneTree.process_frame x1, KanamaSmokeAutoload.probe x2); the new build's _ready " +
          "connects again"
      ),
      warnings,
    )
    assertEquals(0, sweep(build)) // nothing left: no second warning
    assertEquals(1, warnings.size)
  }

  @Test
  fun aBuildWithALiveScriptObjectKeepsItsConnections() {
    val (build, callback) = scriptBuild(reloadId = 3)
    val owner = 0x7e57_0001L
    ScriptBridge.noteScriptObject(owner, callback)
    try {
      val id = connect(callback, "KanamaSmokeAutoload", "probe")
      assertEquals(1, ScriptBridge.liveScriptObjects(build))

      // A game whose scene was not reloaded keeps running the old objects, connections included.
      assertEquals(0, sweep(build))
      assertTrue(SignalCallbackRegistry.entry(id) != null)
      assertTrue(warnings.isEmpty())

      ScriptBridge.forgetScriptObject(owner)
      assertEquals(0, ScriptBridge.liveScriptObjects(build))
      assertEquals(1, sweep(build))
      assertNull(SignalCallbackRegistry.entry(id))
    } finally {
      ScriptBridge.forgetScriptObject(owner)
    }
  }
}

/**
 * Loaded by a per-test [ChildFirstClassLoader]: stands in for a lambda compiled into a script jar.
 */
class ScriptBuildCallback : () -> Unit {
  override fun invoke() {}
}
