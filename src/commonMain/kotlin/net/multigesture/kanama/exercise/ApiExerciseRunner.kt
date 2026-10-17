package net.multigesture.kanama.exercise

import kotlin.jvm.JvmStatic
import kotlin.math.abs
import kotlin.math.max
import kotlin.time.TimeSource
import net.multigesture.kanama.api.DirAccess
import net.multigesture.kanama.api.FileAccess
import net.multigesture.kanama.api.GodotEnumValue
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.GodotObject
import net.multigesture.kanama.api.RefCounted
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
 * Row kinds so far: C (a static: the wrapper's result equals `ClassDB.class_call_static` and is not
 * its type's zero; an object result is a non-null object of the declared class from both paths; an
 * `Error` result is the same non-OK value from both) and A (a property pair: a value set through
 * the wrapper's setter reads back through its getter and through `Object.get`, and differs from the
 * value read before). A **weak** row cannot tell a working call from a no-op by its value (an
 * argument-free static that can return zero): it checks the two paths agree and is counted apart.
 *
 * Every path a row touches is under [SCRATCH], which [run] creates (with the fixture files the rows
 * read) before the first row and removes after the last.
 */
internal object ApiExercise {
  const val SCRATCH: String = "user://kanama-api-exercise/"

  /**
   * The verdict of one run. [reach] counts reach-only rows (none yet), [excluded] listed helpers.
   */
  class Result(
    val passed: Int,
    val failed: Int,
    val weak: Int,
    val reach: Int,
    val excluded: Int,
    val millis: Long,
  )

  /**
   * Runs every row once. [fail] gets one line per failed row (`<helpers> <row> <what>`), [trace]
   * (when not null) one line before each row, for the headless sweep that attributes engine errors
   * and crashes to the row that printed them.
   */
  fun run(fail: (String) -> Unit, trace: ((String) -> Unit)?): Result {
    val started = TimeSource.Monotonic.markNow()
    val runner = ApiExerciseRunner(fail, trace)
    runner.setUp()
    try {
      ApiExerciseRows.runStatic(runner)
      ApiExerciseRows.runPairs(runner)
    } finally {
      runner.tearDown()
    }
    return Result(
      runner.passed,
      runner.failed,
      runner.weak,
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
  fun runDesktop(trace: Boolean, print: (String) -> Unit) {
    val result =
      try {
        run(
          fail = { print("[kanama] API EXERCISE FAIL: $it") },
          trace = if (trace) ({ print("[kanama] API EXERCISE row $it") }) else null,
        )
      } catch (t: Throwable) {
        print(
          "[kanama] API EXERCISE FAIL: the run itself threw ${t::class.simpleName}: ${t.message}"
        )
        throw t
      }
    print(
      "[kanama] API EXERCISE: ${result.passed} passed, ${result.failed} failed, ${result.weak} weak, " +
        "${result.reach} reach, ${result.excluded} excluded in ${result.millis} ms"
    )
  }
}

/**
 * What the generated rows report into: construction and release of the objects a row uses, the
 * comparisons, and the tally. One verdict per row; a row that throws, or ends without a verdict,
 * fails.
 */
internal class ApiExerciseRunner
internal constructor(
  private val onFail: (String) -> Unit,
  private val onTrace: ((String) -> Unit)?,
) {
  var passed: Int = 0
    private set

  var failed: Int = 0
    private set

  var weak: Int = 0
    private set

  var reach: Int = 0
    private set

  private var helpers = ""
  private var label = ""
  private var verdict: Char = ' '
  private val toClose = ArrayList<RefCounted>()
  private val toFree = ArrayList<GodotObject>()

  // ---- the scratch directory and its fixtures --------------------------------------------------

  fun scratch(name: String): String = ApiExercise.SCRATCH + name

  fun fixtureBytes(name: String): ByteArray =
    when (name) {
      "mp3" -> mp3()
      "wav" -> wav()
      "key32" -> ByteArray(32) { it.toByte() }
      "rgba8_4x4" -> ByteArray(64) { (it * 4).toByte() }
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
      removeTree(ApiExercise.SCRATCH)
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
    removeTree(ApiExercise.SCRATCH)
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

  fun begin(case: Char, helpers: String, label: String) {
    this.helpers = helpers
    this.label = "$case $label"
    verdict = ' '
    onTrace?.let { it(this.label) }
  }

  fun end() {
    try {
      for (value in toClose.asReversed()) runCatching { value.close() }
      for (value in toFree.asReversed()) {
        runCatching { if (value.isInstanceLive()) ObjectCalls.destroyObject(value.segment) }
      }
    } finally {
      toClose.clear()
      toFree.clear()
    }
    when (verdict) {
      'P' -> passed++
      'W' -> weak++
      'F' -> failed++
      'S' -> Unit
      else -> fail("ended without a verdict")
    }
  }

  /** A fresh object's handle (the constructing `+1` of a RefCounted goes to [ref]). */
  fun construct(className: String): GodotHandle {
    val raw = ObjectCalls.constructObject(className)
    check(raw.address() != 0L) { "could not construct $className" }
    return GodotHandle(raw)
  }

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
    onFail("$helpers $label $what")
  }

  private fun pass() {
    if (verdict == ' ') verdict = 'P'
  }

  // ---- verdicts --------------------------------------------------------------------------------

  /** C row: equal to the Variant path and not its type's zero (only equal, for a weak row). */
  fun staticValue(got: Any?, oracle: Any?, weak: Boolean) {
    when {
      !same(got, oracle) ->
        fail("expected ${show(oracle)} (ClassDB.class_call_static) got ${show(got)}")
      weak -> if (verdict == ' ') verdict = 'W'
      isZero(got) ->
        fail("expected a value a no-op cannot produce, got its type's zero ${show(got)}")
      else -> pass()
    }
  }

  /**
   * C row returning an object: a non-null [className] from the wrapper and from the Variant path.
   */
  fun staticObject(got: GodotObject?, oracle: Any?, className: String) {
    when {
      got == null -> fail("expected a $className got null")
      !got.isClass(className) -> fail("expected a $className got a ${got.getClassName()}")
      oracle !is GodotObject || !oracle.isClass(className) ->
        fail("expected a $className from ClassDB.class_call_static got ${show(oracle)}")
      else -> pass()
    }
  }

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
