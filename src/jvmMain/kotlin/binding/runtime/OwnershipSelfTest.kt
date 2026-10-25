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
 * off). It spans a few seconds of frames and blocks none for long: each frame does a bounded step
 * (a slice of the id checks, one microbench round), so it runs the same on ART, where an FFI call
 * costs several times what it does on HotSpot.
 *
 * Lines (`[kanama] OWNERSHIP SELFTEST ...`):
 * - `worker-owned GC releases: created=10000 released=<n> leaked=<m>`: a worker thread drops 10,000
 *   owned `Resource`s without `close()`; the main thread requests a collection every
 *   [GC_EVERY_FRAMES] frames and checks [CHECKS_PER_FRAME] of the ids still alive per frame until
 *   all are gone or [RELEASE_WAIT_MILLIS] pass, then reports either way (with `waited_ms`, the
 *   frames and how many releases the drain made meanwhile, so a slow release reads differently from
 *   a stuck one).
 * - `worker GC stress: seconds=5 calls=<n> faults=<m>`: for [STRESS_MILLIS] a worker calls
 *   `getRid()` on fresh owned `Resource`s (the call is each wrapper's last use) while every frame
 *   starts with a full collection ([GcStress.window]).
 * - `getter microbench ns/call: fence_off=<a> fence_on=<b> getter=<c> owned_get_close=<d>
 *   fence_alone=<e> loop_alone=<f>`: the same raw ptrcall without and with
 *   `ObjectCalls.keepReachable` after it, a generated getter, an owned getter result closed, and
 *   the fence alone against the same loop without it (the fence's absolute cost: zero where it is
 *   an intrinsic). One round a frame, each loop timed over [SLICE_NANOS]; the best of the rounds
 *   after the warm-up.
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
  private const val RELEASE_WAIT_MILLIS = 30_000L
  private const val CHECKS_PER_FRAME = 256
  private const val GC_EVERY_FRAMES = 10
  private const val SLICE_NANOS = 20_000_000L
  private const val BENCH_ROUNDS = 10 // one a frame: the warm-up rounds, then the five medians
  private const val BENCH_WARMUP = 5
  private const val STRESS_MILLIS = 5_000L
  private const val PREFIX = "[kanama] OWNERSHIP SELFTEST"
  private const val WORKER_JOIN_MILLIS = 5_000L

  @Volatile private var decided = false
  private var run: Run? = null

  // Set by [shutdown]; the worker loops stop at the next wrapper they would make.
  @Volatile private var stopping = false
  private val workers = java.util.concurrent.CopyOnWriteArrayList<Thread>()

  /**
   * Engine shutdown (KanamaBinding.deinitializeCallback, at the first level): stops the worker
   * threads and waits for them. A run that quits while the test is going (an editor `--script`
   * check quits after a few frames) otherwise left a worker making `Resource`s while Godot tore
   * down the ObjectDB and its allocator: thousands of leaked instances at exit, and now and then an
   * abort in `realloc` ("pointer being freed was not allocated").
   */
  fun shutdown() {
    stopping = true
    run = null
    for (worker in workers) worker.join(WORKER_JOIN_MILLIS)
  }

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
    private val fallback = OwnedReleases.enabled
    private var phase = if (fallback) Phase.DROP_START else Phase.BENCH

    // Phase DROP: the worker writes the ids, then sets dropped; the main thread reads after.
    private val ids = LongArray(CREATED)
    @Volatile private var dropped = false
    @Volatile private var dropError: Throwable? = null
    private var waitStarted = 0L
    private var waitFrames = 0
    private var drainedBefore = 0L
    // ids[0 until alive] are not yet seen released (swap-removed as they go); main thread only.
    private var alive = CREATED
    private var cursor = 0

    // Phase BENCH: one round per frame.
    private var bench: Bench? = null

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
                for (i in 0 until CREATED) {
                  if (stopping) break
                  ids[i] = Resource.create().instanceId
                }
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
          Phase.BENCH -> if (benchRound()) return finish()
        }
      } catch (t: Throwable) {
        fail("exception", "${t::class.qualifiedName}: ${t.message}")
        return finish()
      }
      return false
    }

    private fun checkDropped() {
      if (waitStarted == 0L) {
        waitStarted = System.nanoTime()
        drainedBefore = OwnedReleases.releasedByGc
      }
      waitFrames++
      val waited = (System.nanoTime() - waitStarted) / 1_000_000
      val timedOut = waited >= RELEASE_WAIT_MILLIS || dropError != null
      // A bounded slice per frame (all of them once, at the end): one is an FFI call, ~1 us on ART.
      var budget = if (timedOut) alive else minOf(CHECKS_PER_FRAME, alive)
      while (budget-- > 0 && alive > 0) {
        if (cursor >= alive) cursor = 0
        if (!GD.isInstanceIdValid(ids[cursor])) ids[cursor] = ids[--alive] else cursor++
      }
      if (alive > 0 && !timedOut) {
        // The cleaner queues them; a later frame's drain releases them.
        if (waitFrames % GC_EVERY_FRAMES == 1) Runtime.getRuntime().gc()
        return
      }
      val released = CREATED - alive
      print(
        "worker-owned GC releases: created=$CREATED released=$released " +
          "leaked=$alive waited_ms=$waited frames=$waitFrames " +
          "drained=${OwnedReleases.releasedByGc - drainedBefore} backlog=${OwnedReleases.backlogSize}"
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
          while (!stopping && System.nanoTime() < deadline) {
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

    private class Bench {
      val material = StandardMaterial3D.create()
      val mesh = BoxMesh.create()
      val node = MeshInstance3D(GodotHandle(ObjectCalls.constructObject("MeshInstance3D")))
      val bind = ObjectCalls.getMethodBind("BaseMaterial3D", "get_metallic", GET_METALLIC_HASH)
      val raw = material.handle.segment
      var round = 0
      var sink = 0.0
      val fenceOff = ArrayList<Double>()
      val fenceOn = ArrayList<Double>()
      val getter = ArrayList<Double>()
      val owned = ArrayList<Double>()
      val fenceAlone = ArrayList<Double>()
      val loopAlone = ArrayList<Double>()
      var count = 0L

      init {
        node.setMesh(mesh)
      }

      fun close() {
        node.setMesh(null)
        ObjectCalls.destroyObject(node.handle.segment)
        mesh.close()
        material.close()
      }
    }

    /** One microbench round per frame; true once the last one has printed the line. */
    private fun benchRound(): Boolean {
      val b =
        bench
          ?: run {
            if (!fallback) {
              print(
                "worker-owned GC releases: skipped (owned-reference GC releases are off: " +
                  "${OwnedReleases.ENVIRONMENT_VARIABLE}=0)"
              )
            }
            Bench().also { bench = it }
          }
      try {
        // One small function per loop, so the JIT compiles each on its own (one big method with
        // four loops entered once a frame measured its first loop 3x slow on HotSpot).
        // Alternate which of the pair runs first, so neither always pays a collection's tail.
        val fenceOff: Double
        val fenceOn: Double
        if (b.round % 2 == 0) {
          fenceOff = timeRaw(b)
          fenceOn = timeFenced(b)
        } else {
          fenceOn = timeFenced(b)
          fenceOff = timeRaw(b)
        }
        val getter = timeGetter(b)
        val owned = timeGetAndClose(b)
        val fenceAlone = timeFenceAlone(b)
        val loopAlone = timeLoopAlone(b)
        if (b.round >= BENCH_WARMUP) {
          b.fenceOff += fenceOff
          b.fenceOn += fenceOn
          b.getter += getter
          b.owned += owned
          b.fenceAlone += fenceAlone
          b.loopAlone += loopAlone
        }
        if (b.round == 0) Runtime.getRuntime().gc() // start clean after the stress phase's garbage
        if (++b.round < BENCH_ROUNDS) return false
        print(
          "getter microbench ns/call: fence_off=${best(b.fenceOff)} fence_on=${best(b.fenceOn)} " +
            "getter=${best(b.getter)} owned_get_close=${best(b.owned)} " +
            "fence_alone=${best(b.fenceAlone)} loop_alone=${best(b.loopAlone)} " +
            "(best of ${BENCH_ROUNDS - BENCH_WARMUP} rounds of ${SLICE_NANOS / 1_000_000} ms per loop)" +
            if (b.sink.isNaN() || b.count == Long.MIN_VALUE) " sink=NaN" else ""
        )
        passed++
      } catch (t: Throwable) {
        bench = null
        b.close()
        throw t
      }
      bench = null
      b.close()
      return true
    }

    // Each loop runs batches until [SLICE_NANOS] have passed and reports ns per call, so a round
    // costs the same wall time on HotSpot (about 10 ns a ptrcall) and on a debuggable ART build
    // (about 10 us): a fixed count took a minute on the Pixel.
    private inline fun timeSlice(batch: Int, body: () -> Unit): Double {
      var calls = 0L
      val start = System.nanoTime()
      var elapsed: Long
      do {
        for (i in 0 until batch) body()
        calls += batch
        elapsed = System.nanoTime() - start
      } while (elapsed < SLICE_NANOS)
      return elapsed.toDouble() / calls
    }

    private fun timeRaw(b: Bench): Double =
      timeSlice(64) { b.sink += ObjectCalls.ptrcallNoArgsRetDouble(b.bind, b.raw) }

    private fun timeFenced(b: Bench): Double =
      timeSlice(64) {
        b.sink += ObjectCalls.ptrcallNoArgsRetDouble(b.bind, b.raw)
        ObjectCalls.keepReachable(b.material)
      }

    private fun timeGetter(b: Bench): Double = timeSlice(64) { b.sink += b.material.getMetallic() }

    private fun timeGetAndClose(b: Bench): Double = timeSlice(8) { b.node.getMesh()?.close() }

    // The fence on its own against the same loop without it: the absolute cost of one fence.
    private fun timeFenceAlone(b: Bench): Double =
      timeSlice(1024) {
        b.count++
        ObjectCalls.keepReachable(b.material)
      }

    private fun timeLoopAlone(b: Bench): Double = timeSlice(1024) { b.count++ }

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
      if (stopping) return
      val thread = Thread(body, "kanama-ownership-selftest-$name").apply { isDaemon = true }
      workers += thread
      thread.start()
    }

    // The best round: a collection or a scheduler hiccup only ever makes a round slower.
    private fun best(values: List<Double>): String =
      String.format(java.util.Locale.ROOT, "%.1f", values.min())
  }

  private enum class Phase {
    DROP_START,
    DROP_WAIT,
    STRESS,
    BENCH,
  }

  private const val GET_METALLIC_HASH = 1740695150L
}
