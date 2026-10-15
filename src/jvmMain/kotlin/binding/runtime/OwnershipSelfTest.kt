package net.multigesture.kanama.binding.runtime

import net.multigesture.kanama.api.BoxMesh
import net.multigesture.kanama.api.Engine
import net.multigesture.kanama.api.GD
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.GodotObject
import net.multigesture.kanama.api.MeshInstance3D
import net.multigesture.kanama.api.OS
import net.multigesture.kanama.api.Resource
import net.multigesture.kanama.api.StandardMaterial3D

/**
 * `KANAMA_GC_STRESS=1` (task 138 item 6): a full collection at the start of every frame, before the
 * owned-release drain, for the whole run. A wrapper whose engine call is its last use is then
 * collected as early as the JVM allows, so a missing reachability fence shows up as a release
 * inside a call. A measurement and test knob, read once at the SCENE initialization level; the
 * ownership self-test turns the same per-frame collection on for its stress window.
 */
internal object GcStress {
  const val ENVIRONMENT_VARIABLE: String = "KANAMA_GC_STRESS"

  @Volatile private var fromEnvironment = false
  @Volatile internal var window = false

  /** Reads [ENVIRONMENT_VARIABLE]; returns the line to log, or null when it is off. */
  fun configureFromEngine(): String? {
    val value = runCatching { OS.getEnvironment(ENVIRONMENT_VARIABLE) }.getOrDefault("")
    fromEnvironment = value.trim().lowercase() in setOf("1", "true", "on")
    return if (fromEnvironment) "a full collection every frame ($ENVIRONMENT_VARIABLE=1)" else null
  }

  /** Main thread, at the start of each frame. */
  fun onFrame() {
    if (fromEnvironment || window) Runtime.getRuntime().gc()
  }
}

/**
 * The ownership self-test (task 138 item 6), in the shared desktop/Android JVM code: owned
 * `RefCounted` wrappers made on a worker thread are released by the collector and the main-thread
 * drain, and a worker calling through fresh owned wrappers while the main thread collects every
 * frame never has one released under it. Off for users, like [SignalSelfTest]: it runs once, from
 * the first frame, only when the project setting [SETTING] (`kanama/debug/ownership_self_test`,
 * default false) is true in a debug game run, or when `KANAMA_OWNERSHIP_SELFTEST=1` forces it (`0`
 * off). It spans a few seconds of frames and never blocks one for long, except the microbench.
 *
 * Lines (`[kanama] OWNERSHIP SELFTEST ...`):
 * - `worker-owned GC releases: created=10000 released=<n> leaked=<m>`: a worker thread drops 10,000
 *   owned `Resource`s without `close()`; the main thread requests a collection each frame until
 *   their instance ids are gone (at most [RELEASE_WAIT_MILLIS]).
 * - `worker GC stress: seconds=5 calls=<n> faults=<m>`: for [STRESS_MILLIS] a worker calls
 *   `getRid()` on fresh owned `Resource`s (the call is each wrapper's last use) while every frame
 *   starts with a full collection ([GcStress.window]).
 * - `getter microbench ns/call: fence_off=<a> fence_on=<b> getter=<c> owned_get_close=<d>`: the
 *   same raw ptrcall without and with `ObjectCalls.keepReachable` after it (the cost of the fence:
 *   zero where it is an intrinsic), a generated getter, and an owned getter result closed.
 * - the verdict, `OWNERSHIP SELFTEST: <n> passed, <m> failed in <ms> ms`, and one `OWNERSHIP
 *   SELFTEST FAIL: <row> got <values>` per failed row. Where the fallback is off (Android before
 *   13, `KANAMA_GC_RELEASES=0`) the two worker rows report `skipped` and count as neither.
 *
 * When it is done it sets the `Engine` metadata [DONE_META], which a smoke scene can wait for.
 */
internal object OwnershipSelfTest {
  const val ENVIRONMENT_VARIABLE: String = "KANAMA_OWNERSHIP_SELFTEST"
  const val SETTING: String = "kanama/debug/ownership_self_test"
  const val DONE_META: String = "kanama_ownership_self_test"

  private const val CREATED = 10_000
  private const val RELEASE_WAIT_MILLIS = 15_000L
  private const val STRESS_MILLIS = 5_000L
  private const val PREFIX = "[kanama] OWNERSHIP SELFTEST"

  @Volatile private var decided = false
  private var run: Run? = null

  /** Called on every main-loop frame (after the drain); the test advances one step per frame. */
  fun onFrame() {
    if (!decided) {
      decided = true
      if (!enabled()) return
      run = Run()
    }
    val current = run ?: return
    if (current.step()) run = null
  }

  private fun enabled(): Boolean =
    runCatching {
        SignalSelfTest.decide(
          OS.getEnvironment(ENVIRONMENT_VARIABLE),
          setting = { boolSetting(SETTING) },
          debugBuild = { OS.isDebugBuild() },
          editorHint = { Engine.isEditorHint() },
        )
      }
      .getOrDefault(false)

  private fun boolSetting(name: String): Boolean {
    val settings = GodotObject(GodotHandle(ObjectCalls.getSingleton("ProjectSettings")))
    return settings.call("has_setting", name) == true && settings.call("get_setting", name) == true
  }

  private fun print(line: String) = System.err.println("$PREFIX $line")

  private class Run {
    private val started = System.nanoTime()
    private var passed = 0
    private var failed = 0
    private val fallback = OwnedReleases.enabled && OwnedReleaseCleaner.available
    private var phase = if (fallback) Phase.DROP_START else Phase.BENCH

    // Phase DROP: the worker writes the ids, then sets dropped; the main thread reads after.
    private val ids = LongArray(CREATED)
    @Volatile private var dropped = false
    @Volatile private var dropError: Throwable? = null
    private var waitStarted = 0L

    // Phase STRESS.
    @Volatile private var stressDone = false
    @Volatile private var calls = 0L
    @Volatile private var faults = 0
    @Volatile private var firstFault: String? = null

    /** One step on the main thread; true once the test is finished. */
    fun step(): Boolean {
      try {
        when (phase) {
          Phase.DROP_START -> {
            worker("drop") {
              try {
                for (i in 0 until CREATED) ids[i] = Resource.create().instanceId
              } catch (t: Throwable) {
                dropError = t
              } finally {
                dropped = true
              }
            }
            phase = Phase.DROP_WAIT
          }
          Phase.DROP_WAIT -> if (dropped) checkDropped()
          Phase.STRESS -> if (stressDone) checkStress()
          Phase.BENCH -> {
            bench()
            return finish()
          }
        }
      } catch (t: Throwable) {
        fail("exception", "${t::class.qualifiedName}: ${t.message}")
        GcStress.window = false
        return finish()
      }
      return false
    }

    private fun checkDropped() {
      if (waitStarted == 0L) waitStarted = System.nanoTime()
      val released = ids.count { it != 0L && !GD.isInstanceIdValid(it) }
      val waited = (System.nanoTime() - waitStarted) / 1_000_000
      if (released < CREATED && waited < RELEASE_WAIT_MILLIS && dropError == null) {
        Runtime.getRuntime().gc() // the cleaner queues them; a later frame's drain releases them
        return
      }
      print(
        "worker-owned GC releases: created=$CREATED released=$released " +
          "leaked=${CREATED - released} waited_ms=$waited"
      )
      check("worker-owned GC releases", released == CREATED && dropError == null) {
        "released=$released of $CREATED${dropError?.let { " error=$it" } ?: ""}"
      }
      startStress()
    }

    private fun startStress() {
      GcStress.window = true
      phase = Phase.STRESS
      worker("stress") {
        val deadline = System.nanoTime() + STRESS_MILLIS * 1_000_000
        var sink = 0L
        try {
          while (System.nanoTime() < deadline) {
            // Bounded so the drain (about 1 ms a frame) keeps up and the registration cap is
            // never reached: about 100,000 wrappers a second.
            repeat(100) {
              try {
                sink += Resource.create().getRid().value // the call is the wrapper's last use
                calls++
              } catch (t: Throwable) {
                if (faults++ == 0) firstFault = "${t::class.simpleName}: ${t.message}"
              }
            }
            Thread.sleep(1)
          }
        } finally {
          if (sink == Long.MIN_VALUE) print("sink=$sink")
          stressDone = true
        }
      }
    }

    private fun checkStress() {
      GcStress.window = false
      print("worker GC stress: seconds=${STRESS_MILLIS / 1000} calls=$calls faults=$faults")
      check("worker GC stress", calls > 0 && faults == 0) {
        "calls=$calls faults=$faults $firstFault"
      }
      phase = Phase.BENCH
    }

    private fun bench() {
      if (!fallback) {
        print(
          "worker-owned GC releases: skipped (owned-reference GC releases are off: " +
            "${OwnedReleaseCleaner.unavailableReason.ifEmpty { OwnedReleases.ENVIRONMENT_VARIABLE + "=0" }})"
        )
      }
      val material = StandardMaterial3D.create()
      val mesh = BoxMesh.create()
      val node = MeshInstance3D(GodotHandle(ObjectCalls.constructObject("MeshInstance3D")))
      try {
        node.setMesh(mesh)
        val bind = ObjectCalls.getMethodBind("BaseMaterial3D", "get_metallic", GET_METALLIC_HASH)
        val raw = material.handle.segment
        var sink = 0.0
        val n = 500_000
        val fenceOff = ArrayList<Double>()
        val fenceOn = ArrayList<Double>()
        val getter = ArrayList<Double>()
        val owned = ArrayList<Double>()
        repeat(8) { round ->
          val keep = round >= 3 // three warm-up rounds
          var t = System.nanoTime()
          for (i in 0 until n) sink += ObjectCalls.ptrcallNoArgsRetDouble(bind, raw)
          if (keep) fenceOff += (System.nanoTime() - t).toDouble() / n
          t = System.nanoTime()
          for (i in 0 until n) {
            sink += ObjectCalls.ptrcallNoArgsRetDouble(bind, raw)
            ObjectCalls.keepReachable(material)
          }
          if (keep) fenceOn += (System.nanoTime() - t).toDouble() / n
          t = System.nanoTime()
          for (i in 0 until n) sink += material.getMetallic()
          if (keep) getter += (System.nanoTime() - t).toDouble() / n
          t = System.nanoTime()
          for (i in 0 until n / 8) node.getMesh()?.close()
          if (keep) owned += (System.nanoTime() - t).toDouble() / (n / 8)
        }
        print(
          "getter microbench ns/call: fence_off=${median(fenceOff)} fence_on=${median(fenceOn)} " +
            "getter=${median(getter)} owned_get_close=${median(owned)} (median of 5, n=$n)" +
            if (sink.isNaN()) " sink=NaN" else ""
        )
        passed++
      } finally {
        node.setMesh(null)
        ObjectCalls.destroyObject(node.handle.segment)
        mesh.close()
        material.close()
      }
    }

    private fun finish(): Boolean {
      GcStress.window = false
      val ms = (System.nanoTime() - started) / 1_000_000
      System.err.println("$PREFIX: $passed passed, $failed failed in $ms ms")
      runCatching {
        GodotObject(GodotHandle(ObjectCalls.getSingleton("Engine")))
          .setMeta(DONE_META, if (failed == 0) "pass" else "fail")
      }
      return true
    }

    private inline fun check(row: String, ok: Boolean, got: () -> String) {
      if (ok) passed++ else fail(row, got())
    }

    private fun fail(row: String, values: String) {
      failed++
      System.err.println("$PREFIX FAIL: $row got $values")
    }

    private fun worker(name: String, body: () -> Unit) {
      Thread(body, "kanama-ownership-selftest-$name").apply { isDaemon = true }.start()
    }

    private fun median(values: List<Double>): String =
      String.format(java.util.Locale.ROOT, "%.1f", values.sorted()[values.size / 2])
  }

  private enum class Phase {
    DROP_START,
    DROP_WAIT,
    STRESS,
    BENCH,
  }

  private const val GET_METALLIC_HASH = 1740695150L
}
