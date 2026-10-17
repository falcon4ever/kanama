package net.multigesture.kanama.exercise

import kotlin.jvm.JvmStatic
import kotlin.math.abs
import kotlin.math.max
import kotlin.random.Random
import kotlin.time.TimeSource
import net.multigesture.kanama.api.DirAccess
import net.multigesture.kanama.api.Expression
import net.multigesture.kanama.api.FileAccess
import net.multigesture.kanama.api.GodotCallable
import net.multigesture.kanama.api.GodotEnumValue
import net.multigesture.kanama.api.GodotError
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.GodotObject
import net.multigesture.kanama.api.GodotSignal
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.api.OS
import net.multigesture.kanama.api.RefCounted
import net.multigesture.kanama.api.SceneTree
import net.multigesture.kanama.api.Signal1
import net.multigesture.kanama.api.SignalArgType
import net.multigesture.kanama.api.SignalConnection
import net.multigesture.kanama.api.Time
import net.multigesture.kanama.api.Tween
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.Basis
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.Plane
import net.multigesture.kanama.types.Quaternion
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Rect2i
import net.multigesture.kanama.types.Transform2D
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector2i
import net.multigesture.kanama.types.Vector3
import net.multigesture.kanama.types.Vector3i
import net.multigesture.kanama.types.Vector4

/**
 * The API exercise (a Kanama test hook, never in a user's build): the generated rows of
 * [ApiExerciseRows] (`scripts/generate_api_exercise.py`, one row per `ObjectCalls` helper the
 * shared wrapper tree calls, see `docs/reference/generated/api-exercise.md`) run against the live
 * engine, each asserting a value a silent no-op cannot produce.
 *
 * This source set (`net/multigesture/kanama/exercise/`) is compiled only into a build made with
 * `-PkanamaApiExercise=true` (desktop, Android) or `-PkanamaIosSelfTest=true` (iOS, the device
 * self-test build); the publish and install tasks refuse such a build. The platforms call [run]
 * from their own hooks and print the verdict in their own words: the desktop `ApiExerciseHook`
 * (project setting `kanama/debug/api_exercise` or `KANAMA_API_EXERCISE=1`, first frame) and the iOS
 * `ApiExerciseSelfTest` (frame-1 self-test phase).
 *
 * Row kinds: C (a static: the wrapper's result equals `ClassDB.class_call_static` and is not its
 * type's zero; an object result is a non-null object of the declared class from both paths; an
 * `Error` result is the same non-OK value from both), A (a property pair: a value set through the
 * wrapper's setter reads back through its getter and through `Object.get`, and differs from the
 * value read before; on a singleton the value read first is set back in a `finally`), B (a const
 * method of a fresh object) and D (a const method of a singleton, or any method of a stateless
 * one): the wrapper's result equals `Object.call` on the same object, and B/D RID factories: a
 * `*_create` returns a valid RID, one read of it agrees on both paths, and the RID is freed. A
 * **weak** row cannot tell a working call from a no-op by its value (an argument-free static, or a
 * B/D result equal to its type's zero): it checks the two paths agree and is counted apart. T (one
 * value per Godot Variant type through each generic Variant path: a return, a container element, an
 * argument, an inbound signal argument; against a value the engine built or compared) and F (reach
 * only: the helper is called once on a valid receiver and must return without an exception or a
 * backend fault, counted as `reach`). On every row, a backend fault (iOS: the shim's FAULT count,
 * [ApiExercise.run]'s `faults`) raised during the row fails it.
 *
 * Every path a row touches is under the run's own directory below [SCRATCH_ROOT]
 * (`<pid>-<random>/`, [ApiExerciseRunner.scratchDir]), which [run] creates (with the fixture files
 * the rows read) before the first row and removes after the last: two runs of one project at once
 * (two worktrees of the example project share its `user://`) never touch each other's files.
 */
internal object ApiExercise {
  const val SCRATCH_ROOT: String = "user://kanama-api-exercise/"

  /** The verdict of one run. [reach] counts the reach-only rows, [excluded] the listed helpers. */
  class Result(
    val passed: Int,
    val failed: Int,
    val weak: Int,
    val known: Int,
    val reach: Int,
    val excluded: Int,
    val millis: Long,
  )

  /**
   * Runs every row once. [fail] gets one line per failed row (`<helpers> <row> <what>`), [trace]
   * (when not null) one line before each row, for the headless sweep that attributes engine errors
   * and crashes to the row that printed them.
   */
  fun run(
    platform: String,
    fail: (String) -> Unit,
    known: (String) -> Unit,
    trace: ((String) -> Unit)?,
    weak: (String) -> Unit = {},
    faults: () -> Int = { 0 },
  ): Result {
    val started = TimeSource.Monotonic.markNow()
    val runner = ApiExerciseRunner(platform, fail, known, trace, weak, faults)
    runner.setUp()
    try {
      ApiExerciseRows.runStatic(runner)
      ApiExerciseRows.runPairs(runner)
      ApiExerciseRows.runConst(runner)
      ApiExerciseRows.runSingletons(runner)
      ApiExerciseRows.runTypes(runner)
      // Last: a reach row may leave the state it touched changed (a singleton's, a server's).
      ApiExerciseRows.runReach(runner)
    } finally {
      runner.tearDown()
    }
    return Result(
      runner.passed,
      runner.failed,
      runner.weak,
      runner.known,
      runner.reach,
      ApiExerciseRows.EXCLUDED,
      started.elapsedNow().inWholeMilliseconds,
    )
  }

  /**
   * The desktop/Android entry point the JVM hook reaches by reflection (the hook is compiled into
   * every build, these sources only into a flagged one). Prints through [print] (stderr on the
   * desktop, so trace lines interleave with the engine's own error output).
   */
  @JvmStatic
  fun runDesktop(trace: Boolean, platform: String, print: (String) -> Unit) {
    val result =
      try {
        run(
          platform = platform,
          fail = { print("[kanama] API EXERCISE FAIL: $it") },
          known = { print("[kanama] API EXERCISE KNOWN: $it") },
          weak = { print("[kanama] API EXERCISE WEAK: $it") },
          trace = if (trace) ({ print("[kanama] API EXERCISE row $it") }) else null,
          // The JVM backends have no fault counter: a refused call throws, which fails its row.
          faults = { 0 },
        )
      } catch (t: Throwable) {
        print(
          "[kanama] API EXERCISE FAIL: the run itself threw ${t::class.simpleName}: ${t.message}"
        )
        throw t
      }
    print(
      "[kanama] API EXERCISE: ${result.passed} passed, ${result.failed} failed, ${result.weak} weak, " +
        "${result.known} known, ${result.reach} reach, ${result.excluded} excluded in ${result.millis} ms"
    )
  }
}

/**
 * What the generated rows report into: construction and release of the objects a row uses, the
 * comparisons, and the tally. One verdict per row; a row that throws, or ends without a verdict,
 * fails.
 */
private const val PROBE_SIGNAL = "kanama_api_exercise_probe"

internal class ApiExerciseRunner
internal constructor(
  private val platform: String,
  private val onFail: (String) -> Unit,
  private val onKnown: (String) -> Unit,
  private val onTrace: ((String) -> Unit)?,
  private val onWeak: (String) -> Unit = {},
  private val faults: () -> Int = { 0 },
) {
  var passed: Int = 0
    private set

  var failed: Int = 0
    private set

  var weak: Int = 0
    private set

  var known: Int = 0
    private set

  var reach: Int = 0
    private set

  private var helpers = ""
  private var label = ""
  private var verdict: Char = ' '
  private var failure = ""
  private var knownHere = false
  private var knownWhy = ""
  private var knownFailure = ""
  private var inOracle = false
  private var failureInOracle = false
  private var faultsAtBegin = 0
  private val toClose = ArrayList<RefCounted>()
  private val toFree = ArrayList<GodotObject>()

  // ---- the scratch directory and its fixtures --------------------------------------------------

  private val STALE_SECONDS = 3600L

  /** This run's scratch directory: [ApiExercise.SCRATCH_ROOT] + `<pid>-<random>/`. */
  val scratchDir: String =
    ApiExercise.SCRATCH_ROOT +
      "${runCatching { OS.getProcessId() }.getOrDefault(0)}-${Random.nextInt(0, Int.MAX_VALUE).toString(36)}/"

  fun scratch(name: String): String = scratchDir + name

  fun fixtureBytes(name: String): ByteArray =
    when (name) {
      "mp3" -> mp3()
      "wav" -> wav()
      "key32" -> ByteArray(32) { it.toByte() }
      "rgba8_4x2" -> ByteArray(32) { (it * 7).toByte() }
      else -> error("no byte fixture '$name'")
    }

  fun fixtureText(name: String): String =
    when (name) {
      "svg" -> "<svg xmlns='http://www.w3.org/2000/svg' width='8' height='8'/>"
      else -> error("no text fixture '$name'")
    }

  internal fun setUp() {
    begin('S', "-", "setup(scratch directory and fixtures)")
    try {
      removeTree(scratchDir)
      removeStaleRuns()
      check(DirAccess.makeDirRecursiveAbsolute(scratch("sub")).value == 0L) {
        "cannot create ${scratch("sub")}"
      }
      writeFile("fixture.txt", "kanama125".encodeToByteArray())
      writeFile("fixture.mp3", mp3())
      writeFile("fixture.wav", wav())
      // The extended attribute the get_extended_attribute rows read back (FAILED where the
      // filesystem has none: those rows then fail, which is the finding).
      FileAccess.setExtendedAttributeString(scratch("fixture.txt"), "kanama", "kanama125")
      verdict = 'S'
    } catch (t: Throwable) {
      threw(t)
    }
    end()
  }

  internal fun tearDown() {
    begin('S', "-", "teardown(scratch directory removed)")
    try {
      releaseTypeProbe()
      removeTree(scratchDir)
      if (DirAccess.dirExistsAbsolute(scratchDir)) {
        fail("$scratchDir still exists after the run removed it")
      } else {
        verdict = 'S'
      }
      // The shared root goes too once it is empty; a run still going elsewhere keeps it (the call
      // then returns an error, which is not this run's failure).
      DirAccess.removeAbsolute(ApiExercise.SCRATCH_ROOT)
    } catch (t: Throwable) {
      threw(t)
    }
    end()
  }

  /**
   * Removes the directories of earlier runs that crashed before their teardown: a sibling whose
   * fixture was written more than an hour ago (no run lasts that long). Decided on age alone:
   * Godot's `OS.is_process_running` only knows the caller's own child processes, so it would call a
   * concurrent smoke in another process dead. A sibling without a fixture yet is still in its setup
   * and is kept.
   */
  private fun removeStaleRuns() {
    val root = ApiExercise.SCRATCH_ROOT
    if (!DirAccess.dirExistsAbsolute(root)) return
    val now = Time.getUnixTimeFromSystem().toLong()
    for (sub in DirAccess.getDirectoriesAt(root)) {
      val path = root + sub
      if ("$path/" == scratchDir) continue
      val fixture = "$path/fixture.txt"
      if (!FileAccess.fileExists(fixture)) continue
      val modified = FileAccess.getModifiedTime(fixture)
      if (modified != 0L && now - modified > STALE_SECONDS) removeTree(path)
    }
  }

  private fun writeFile(name: String, bytes: ByteArray) {
    val file =
      checkNotNull(FileAccess.open(scratch(name), FileAccess.ModeFlags(2L))) {
        "cannot write ${scratch(name)}"
      }
    try {
      check(file.storeBuffer(bytes)) { "cannot write ${scratch(name)}" }
    } finally {
      file.close()
    }
  }

  private fun removeTree(path: String) {
    if (!DirAccess.dirExistsAbsolute(path)) return
    val dir = if (path.endsWith("/")) path else "$path/"
    for (sub in DirAccess.getDirectoriesAt(dir)) removeTree(dir + sub)
    for (file in DirAccess.getFilesAt(dir)) DirAccess.removeAbsolute(dir + file)
    DirAccess.removeAbsolute(dir)
  }

  // Four frames of a silent MPEG-1 Layer III stream (128 kbit/s, 44.1 kHz, mono): the smallest
  // input AudioStreamMP3 decodes.
  private fun mp3(): ByteArray {
    val frame = 417
    val bytes = ByteArray(frame * 4)
    for (i in 0 until 4) {
      bytes[i * frame] = 0xFF.toByte()
      bytes[i * frame + 1] = 0xFB.toByte()
      bytes[i * frame + 2] = 0x90.toByte()
      bytes[i * frame + 3] = 0xC4.toByte()
    }
    return bytes
  }

  // A RIFF/WAVE file: 16-bit mono PCM at 8 kHz, four samples.
  private fun wav(): ByteArray {
    val data = byteArrayOf(4, 5, 6, 7, 8, 9, 10, 11)
    val out = ArrayList<Byte>()
    fun ascii(text: String) = text.forEach { out.add(it.code.toByte()) }
    fun u32(value: Int) = (0 until 4).forEach { out.add((value ushr (8 * it)).toByte()) }
    fun u16(value: Int) = (0 until 2).forEach { out.add((value ushr (8 * it)).toByte()) }
    ascii("RIFF")
    u32(36 + data.size)
    ascii("WAVEfmt ")
    u32(16)
    u16(1)
    u16(1)
    u32(8000)
    u32(16000)
    u16(2)
    u16(16)
    ascii("data")
    u32(data.size)
    data.forEach { out.add(it) }
    return out.toByteArray()
  }

  // ---- one row ---------------------------------------------------------------------------------

  /**
   * Starts a row. [known] lists the platforms where the row is a known finding (KNOWN_FINDINGS in
   * scripts/api_exercise_policy.py, with [knownWhy]): there a failure whose message contains
   * [knownFailure], raised in the oracle half of the row, is reported as known; any other failure
   * fails, and a pass fails the run until the entry is removed. A C row's oracle half starts at
   * [oracle] (after the wrapper's own result was checked), and so do the B and D rows' (after the
   * wrapper call); an A row's is the whole row.
   */
  fun begin(
    case: Char,
    helpers: String,
    label: String,
    known: String = "",
    knownWhy: String = "",
    knownFailure: String = "",
  ) {
    this.helpers = helpers
    this.label = "$case $label"
    verdict = ' '
    failure = ""
    failureInOracle = false
    inOracle = case == 'A'
    knownHere = known.split(',').contains(platform)
    this.knownWhy = knownWhy
    this.knownFailure = knownFailure
    onTrace?.let { it(this.label) }
    faultsAtBegin = faults()
  }

  /** A C, B or D row's wrapper half is done: what fails from here on is the Variant path's. */
  fun oracle() {
    inOracle = true
  }

  fun end() {
    try {
      for (tween in tweens) runCatching { tween.kill() }
      for (value in toClose.asReversed()) runCatching { value.close() }
      for (value in toFree.asReversed()) {
        runCatching { if (value.isInstanceLive()) ObjectCalls.destroyObject(value.segment) }
      }
    } finally {
      tweens.clear()
      toClose.clear()
      toFree.clear()
    }
    val raised = faults() - faultsAtBegin
    if (raised > 0) {
      fail("the backend refused a call: $raised FAULT line(s) during the row (see the FAULT lines)")
    }
    if (verdict == ' ') fail("ended without a verdict")
    val asKnown =
      knownHere && failureInOracle && knownFailure.isNotEmpty() && failure.contains(knownFailure)
    when {
      verdict == 'F' && asKnown -> {
        known++
        onKnown("$helpers $label $failure (known finding on $platform: $knownWhy)")
      }
      verdict == 'F' && knownHere -> {
        failed++
        onFail(
          "$helpers $label $failure (not the known finding on $platform, which fails in the " +
            "Variant path with '$knownFailure')"
        )
      }
      verdict == 'F' -> {
        failed++
        onFail("$helpers $label $failure")
      }
      knownHere -> {
        failed++
        onFail(
          "$helpers $label known finding no longer reproduces on $platform: remove it from " +
            "KNOWN_FINDINGS in scripts/api_exercise_policy.py"
        )
      }
      verdict == 'P' -> passed++
      verdict == 'W' -> weak++
      verdict == 'R' -> reach++
    }
    // The sweep reads each row's verdict back (P passed, W weak, R reach, F failed, S
    // setup/teardown).
    onTrace?.let { it("$label = $verdict") }
  }

  /** A fresh object's handle (the constructing `+1` of a RefCounted goes to [ref]). */
  fun construct(className: String): GodotHandle {
    val raw = ObjectCalls.constructObject(className)
    check(raw.address() != 0L) { "could not construct $className" }
    return GodotHandle(raw)
  }

  /** The engine singleton [name] as an object, for the Variant-path half of a singleton row. */
  fun singleton(name: String): GodotObject =
    checkNotNull(GodotObject.wrap(ObjectCalls.getSingleton(name))) { "no singleton $name" }

  /** A fresh RefCounted: owned (its constructing `+1`), released when the row ends. */
  fun <T : RefCounted> ref(value: T): T {
    RefCounted.owned(value)
    toClose.add(value)
    return value
  }

  /** A fresh non-RefCounted object, freed when the row ends. */
  fun <T : GodotObject> obj(value: T): T {
    toFree.add(value)
    return value
  }

  /** [value], with every owned RefCounted wrapper in it closed when the row ends. */
  fun <T> track(value: T): T {
    collect(value)
    return value
  }

  private fun collect(value: Any?) {
    when (value) {
      is RefCounted -> if (value.isOwned && toClose.none { it === value }) toClose.add(value)
      is List<*> -> value.forEach { collect(it) }
      is Map<*, *> -> value.values.forEach { collect(it) }
    }
  }

  fun threw(t: Throwable) {
    fail("threw ${t::class.simpleName}: ${t.message}")
  }

  private fun fail(what: String) {
    if (verdict == 'F') return
    verdict = 'F'
    failure = what
    failureInOracle = inOracle
  }

  private fun pass() {
    if (verdict == ' ') verdict = 'P'
  }

  // ---- verdicts --------------------------------------------------------------------------------

  /** C row, wrapper half: a non-null object of [className]. */
  fun wrapperObject(got: GodotObject?, className: String) {
    when {
      got == null -> fail("expected a $className got null")
      !got.isClass(className) -> fail("expected a $className got a ${got.getClassName()}")
    }
  }

  /**
   * C row, wrapper half: a value a no-op cannot produce (not checked for a weak row, nor on the
   * platforms [zeroOn] lists: STATIC_ZERO_ON, where the host can answer its type's zero).
   */
  fun wrapperValue(got: Any?, weak: Boolean, zeroOn: String = "") {
    if (!weak && isZero(got) && !zeroOn.split(',').contains(platform)) {
      fail("expected a value a no-op cannot produce, got ${show(got)}")
    }
  }

  /** C row, wrapper half: a non-OK `Error`. */
  fun wrapperError(got: Long) {
    if (got == 0L) fail("expected a non-OK Error got OK (0)")
  }

  /**
   * C row: equal to the Variant path and not its type's zero (only equal, for a weak row; a zero is
   * weak on the platforms [zeroOn] lists).
   */
  fun staticValue(got: Any?, oracle: Any?, weak: Boolean, zeroOn: String = "") {
    when {
      !same(got, oracle) ->
        fail("expected ${show(oracle)} (ClassDB.class_call_static) got ${show(got)}")
      weak || isZero(got) && zeroOn.split(',').contains(platform) ->
        if (verdict == ' ') verdict = 'W'
      isZero(got) ->
        fail("expected a value a no-op cannot produce, got its type's zero ${show(got)}")
      else -> pass()
    }
  }

  /**
   * C row returning an object: a non-null [className] from the wrapper and from the Variant path.
   */
  fun staticObject(got: GodotObject?, oracle: Any?, className: String, vararg reads: String) {
    when {
      got == null -> fail("expected a $className got null")
      !got.isClass(className) -> fail("expected a $className got a ${got.getClassName()}")
      oracle !is GodotObject || !oracle.isClass(className) ->
        fail("expected a $className from ClassDB.class_call_static got ${show(oracle)}")
      else -> {
        // A new object per call: what the arguments made of it (STATIC_OBJECT_CHECKS) must agree.
        for (name in reads) {
          val mine = track(read(got, name))
          val theirs = track(read(oracle, name))
          if (!same(mine, theirs)) {
            fail(
              "expected $name ${show(theirs)} (ClassDB.class_call_static's result) got ${show(mine)}"
            )
            return
          }
        }
        pass()
      }
    }
  }

  private fun read(value: GodotObject, name: String): Any? =
    if (name.endsWith("()")) value.call(name.dropLast(2)) else value.get(name)

  /**
   * C row returning `Error`: the same non-OK value from both paths (OK is what a no-op returns).
   */
  fun staticError(got: Long, oracle: Any?) {
    when {
      got == 0L -> fail("expected a non-OK Error got OK (0)")
      !same(got, oracle) -> fail("expected ${show(oracle)} (ClassDB.class_call_static) got $got")
      else -> pass()
    }
  }

  /** C row whose result is new per call: a non-zero value of one type from both paths. */
  fun staticFresh(got: Any?, oracle: Any?) {
    when {
      isZero(got) -> fail("expected a value a no-op cannot produce, got ${show(got)}")
      isZero(oracle) -> fail("expected a value from ClassDB.class_call_static, got ${show(oracle)}")
      got!!::class != oracle!!::class ->
        fail("expected one type from both paths, got ${show(got)} and ${show(oracle)}")
      else -> pass()
    }
  }

  /**
   * B or D row: the wrapper's result equals `Object.call` of the same method with the same
   * arguments on the same object; a result equal to its type's zero is **weak** (a no-op could
   * produce it).
   */
  fun sameAsCall(got: Any?, oracle: Any?) {
    when {
      !same(got, oracle) -> fail("expected ${show(oracle)} (Object.call) got ${show(got)}")
      isZero(got) -> if (verdict == ' ') verdict = 'W'
      else -> pass()
    }
  }

  /**
   * RID factory row: the wrapper's RID is valid (a no-op returns the invalid RID 0). When it is
   * not, [viaCall] (the same factory through `Object.call`) decides: invalid too means the host
   * makes no such resource (a renderer without decals, fog volumes or VoxelGI, the headless dummy
   * renderer) and the row is weak, with that reason printed; valid means the wrapper lost a RID the
   * engine made, and the row fails.
   */
  fun created(rid: RID, viaCall: RID) {
    when {
      rid.value != 0L -> pass()
      viaCall.value != 0L ->
        fail(
          "expected a valid RID got RID(0); Object.call made ${viaCall.value} (the wrapper's fault)"
        )
      else -> {
        if (verdict == ' ') verdict = 'W'
        onWeak("$helpers $label RID(0) from both paths: the renderer does not support this")
      }
    }
  }

  /** RID factory row: one read of the created RID agrees between the wrapper and `Object.call`. */
  fun readBack(got: Any?, oracle: Any?, method: String) {
    if (!same(got, oracle)) fail("expected $method ${show(oracle)} (Object.call) got ${show(got)}")
  }

  /**
   * Singleton row, after its `finally` put [original] back through the Variant path: [now] (read
   * back the same way) must equal it, or the row fails, since a silently failed restore would leave
   * process-wide state changed.
   */
  fun restored(original: Any?, now: Any?) {
    if (!same(original, now))
      fail("the restore left ${show(now)}, not the original ${show(original)}")
  }

  /** A row: the value differs from [before] and reads back through the getter and `Object.get`. */
  fun pair(value: Any?, before: Any?, got: Any?, viaGet: Any?) {
    when {
      same(value, before) -> fail("the value set equals the value before the set: ${show(value)}")
      !same(got, value) -> fail("expected ${show(value)} from the getter got ${show(got)}")
      !same(viaGet, value) -> fail("expected ${show(value)} from Object.get got ${show(viaGet)}")
      else -> pass()
    }
  }

  /** The candidate an A row sets: [a], unless the object already holds it. */
  fun <T> pick(before: Any?, a: T, b: T): T = if (same(before, a)) b else a

  /**
   * F row: the call returned. Owned RefCounted results are closed when the row ends, and a `Node`
   * result outside any tree (a new node the call made, `GLTFDocument.generate_scene`) is freed.
   */
  fun reached(value: Any? = null) {
    track(value)
    if (value is Node && value.isInstanceLive() && toFree.none { it === value }) {
      if (value.getParent() == null && !value.isInsideTree()) toFree.add(value)
    }
    if (verdict == ' ') verdict = 'R'
  }

  /** A Callable argument of an F row: a method of the run's probe node that takes no argument. */
  fun noopCallable(): GodotCallable = GodotCallable(typeObject(), "get_instance_id")

  private val tweens = ArrayList<Tween>()

  /**
   * A Tween of the running SceneTree (`create_tween()`, the only valid kind): killed when the row
   * ends, so nothing it was given runs later.
   */
  fun tween(): Tween {
    val tween = track(SceneTree.createTween())
    tweens.add(tween)
    return tween
  }

  /** A Signal argument of an F row: the run's probe node's user signal. */
  fun probeSignal(): GodotSignal = typeObject().signal(PROBE_SIGNAL)

  // ---- Variant type rows (T) -------------------------------------------------------------------

  private var probeNode: Node? = null
  private var probeExpression: Expression? = null
  private var probeConnection: SignalConnection? = null
  private val received = ArrayList<Any?>()

  /** The run's probe node: the Expression's base instance (`self`), the type rows' meta holder. */
  fun typeObject(): Node {
    probeNode?.let {
      return it
    }
    val node = Node(construct("Node"))
    probeNode = node
    val expression = Expression(construct("Expression"))
    RefCounted.owned(expression)
    probeExpression = expression
    node.addUserSignal(PROBE_SIGNAL)
    probeConnection =
      Signal1(node, PROBE_SIGNAL, SignalArgType.VARIANT).connect(node) { received.add(it) }
    return node
  }

  private fun releaseTypeProbe() {
    runCatching { probeConnection?.close() }
    runCatching { probeExpression?.close() }
    runCatching {
      probeNode?.let { if (it.isInstanceLive()) ObjectCalls.destroyObject(it.segment) }
    }
    probeConnection = null
    probeExpression = null
    probeNode = null
  }

  private fun eval(code: String): Any? {
    val node = typeObject()
    val expression = checkNotNull(probeExpression)
    // A literal the Expression cannot parse fails its row here, not as a wrong value.
    check(expression.parse(code, emptyList()) == GodotError.OK) { "Expression cannot parse $code" }
    val result = track(expression.execute(emptyList(), node))
    check(!expression.hasExecuteFailed()) { "Expression failed: $code" }
    return result
  }

  /** T row `ret`: the literal the engine evaluated comes back as [value]. */
  fun typeRet(literal: String, value: Any?) {
    val got = eval(literal)
    if (exact(got, value)) pass() else fail("expected ${shown(value)} got ${shown(got)}")
  }

  /** T row `elem`: the literal inside an Array and a Dictionary comes back as [value]. */
  fun typeElem(literal: String, value: Any?) {
    val array = eval("[$literal]")
    val dictionary = eval("{\"k\": $literal}")
    when {
      !exact(array, listOf(value)) -> fail("expected [${shown(value)}] got ${shown(array)}")
      !exact(dictionary, mapOf("k" to value)) ->
        fail("expected {k=${shown(value)}} got ${shown(dictionary)}")
      else -> pass()
    }
  }

  /**
   * T row `arg`: [value] passed through `Object.call("set_meta", …)` at the top level and inside an
   * Array and a Dictionary equals the literal, by the engine's own comparison.
   */
  fun typeArg(literal: String, value: Any?) {
    val node = typeObject()
    try {
      node.call("set_meta", "kanama_t", value)
      node.call("set_meta", "kanama_ta", listOf(value))
      node.call("set_meta", "kanama_td", mapOf("k" to value))
      val equal =
        eval(
          "get_meta(\"kanama_t\") == $literal and get_meta(\"kanama_ta\") == [$literal] and " +
            "get_meta(\"kanama_td\") == {\"k\": $literal}"
        )
      if (equal == true) pass()
      else fail("the engine reads back what Kotlin passed as unequal to $literal")
    } finally {
      for (key in listOf("kanama_t", "kanama_ta", "kanama_td")) {
        runCatching { node.removeMeta(key) }
      }
    }
  }

  /**
   * T row `in`: the literal emitted by the engine to a Kotlin lambda's Variant argument, at the top
   * level and inside an Array and a Dictionary, arrives as [value].
   */
  fun typeIn(literal: String, value: Any?) {
    typeObject()
    received.clear()
    eval("emit_signal(\"$PROBE_SIGNAL\", $literal)")
    eval("emit_signal(\"$PROBE_SIGNAL\", [$literal])")
    eval("emit_signal(\"$PROBE_SIGNAL\", {\"k\": $literal})")
    val expected = listOf(value, listOf(value), mapOf("k" to value))
    val got = ArrayList(received)
    received.clear()
    track(got)
    if (exact(got, expected)) pass() else fail("expected ${shown(expected)} got ${shown(got)}")
  }

  /**
   * The type rows' comparison: the same Kotlin type and value (a PackedInt32Array element is an
   * `Int`, a PackedFloat32Array one a `Float`), byte arrays by content, objects by instance id.
   */
  private fun exact(a: Any?, b: Any?): Boolean =
    when {
      a is ByteArray && b is ByteArray -> a.contentEquals(b)
      a is GodotObject && b is GodotObject -> a.instanceId == b.instanceId
      a is List<*> && b is List<*> -> a.size == b.size && a.indices.all { exact(a[it], b[it]) }
      a is Map<*, *> && b is Map<*, *> ->
        a.size == b.size && a.keys.all { b.containsKey(it) && exact(a[it], b[it]) }
      a == null || b == null -> a == b
      else -> a::class == b::class && a == b
    }

  private fun shown(value: Any?): String {
    val text =
      when (value) {
        null -> "null"
        is ByteArray -> value.toList().toString() + ":ByteArray"
        is GodotObject -> "Object#${value.instanceId}"
        is List<*> -> value.joinToString(prefix = "[", postfix = "]") { shown(it) }
        is Map<*, *> ->
          value.entries.joinToString(prefix = "{", postfix = "}") { "${it.key}=${shown(it.value)}" }
        is String -> "\"$value\""
        else -> "$value:${value::class.simpleName}"
      }
    return if (text.length > 200) text.take(197) + "..." else text
  }

  // ---- comparison ------------------------------------------------------------------------------

  private class ObjectRef(val id: Long) {
    override fun equals(other: Any?): Boolean = other is ObjectRef && other.id == id

    override fun hashCode(): Int = id.hashCode()

    override fun toString(): String = "Object#$id"
  }

  /** One spelling per value: numbers as Long or Double, containers as lists, objects by id. */
  private fun norm(value: Any?): Any? =
    when (value) {
      null -> null
      is GodotEnumValue -> value.value
      is Byte -> value.toLong()
      is Short -> value.toLong()
      is Int -> value.toLong()
      is Long -> value
      is Float -> value.toDouble()
      is Double -> value
      is ByteArray -> value.map { it.toLong() }
      is IntArray -> value.map { it.toLong() }
      is LongArray -> value.toList()
      is FloatArray -> value.map { it.toDouble() }
      is DoubleArray -> value.toList()
      is List<*> -> value.map { norm(it) }
      is Map<*, *> -> value.entries.associate { norm(it.key) to norm(it.value) }
      is NodePath -> value.path
      is GodotObject -> ObjectRef(value.instanceId)
      else -> value
    }

  private fun same(a: Any?, b: Any?): Boolean = equal(norm(a), norm(b))

  private fun equal(a: Any?, b: Any?): Boolean =
    when {
      a is Double && b is Double -> abs(a - b) <= 1e-6 * max(1.0, max(abs(a), abs(b)))
      a is Long && b is Double || a is Double && b is Long -> false
      a is List<*> && b is List<*> -> a.size == b.size && a.indices.all { equal(a[it], b[it]) }
      a is Map<*, *> && b is Map<*, *> ->
        a.size == b.size && a.all { (k, v) -> b.containsKey(k) && equal(v, b[k]) }
      else -> a == b
    }

  private fun isZero(value: Any?): Boolean =
    when (val n = norm(value)) {
      null -> true
      false -> true
      is Long -> n == 0L
      is Double -> n == 0.0
      is String -> n.isEmpty()
      is List<*> -> n.isEmpty()
      is Map<*, *> -> n.isEmpty()
      is RID -> n.value == 0L
      is Vector2 -> n == Vector2.ZERO
      is Vector2i -> n == Vector2i.ZERO
      is Vector3 -> n == Vector3.ZERO
      is Vector3i -> n == Vector3i.ZERO
      is Vector4 -> n == Vector4.ZERO
      is Rect2 -> n == Rect2.ZERO
      is Rect2i -> n == Rect2i.ZERO
      is AABB -> n == AABB.ZERO
      is Plane -> n == Plane.ZERO
      is Color -> n == Color(0.0, 0.0, 0.0, 0.0)
      is Quaternion -> n == Quaternion(0.0, 0.0, 0.0, 0.0)
      is Basis -> n == Basis(Vector3.ZERO, Vector3.ZERO, Vector3.ZERO)
      is Transform2D -> n == Transform2D(Vector2.ZERO, Vector2.ZERO, Vector2.ZERO)
      is Transform3D ->
        n == Transform3D(Basis(Vector3.ZERO, Vector3.ZERO, Vector3.ZERO), Vector3.ZERO)
      else -> false
    }

  private fun show(value: Any?): String {
    val text =
      when (val n = norm(value)) {
        is String -> "\"$n\""
        null -> "null"
        else -> n.toString()
      }
    return if (text.length > 160) text.take(157) + "..." else text
  }
}
