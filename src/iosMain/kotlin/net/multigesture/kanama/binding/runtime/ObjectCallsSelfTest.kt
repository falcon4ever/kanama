@file:OptIn(ExperimentalForeignApi::class)

package net.multigesture.kanama.binding.runtime

import java.lang.foreign.MemorySegment
import kotlin.experimental.ExperimentalNativeApi
import kotlin.native.CName
import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.DoubleVar
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.FloatVar
import kotlinx.cinterop.IntVar
import kotlinx.cinterop.LongVar
import kotlinx.cinterop.alloc
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.get
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.plus
import kotlinx.cinterop.ptr
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.set
import kotlinx.cinterop.value
import kotlinx.coroutines.launch
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.GodotObject
import net.multigesture.kanama.api.IosCallableRegistry
import net.multigesture.kanama.api.IosGodot
import net.multigesture.kanama.api.RefCounted
import net.multigesture.kanama.ios.KanamaIosProjectRegistry
import net.multigesture.kanama.ios.KanamaIosRpcConfig
import net.multigesture.kanama.ios.KanamaIosRuntime
import net.multigesture.kanama.ios.KanamaIosScriptDescriptor
import net.multigesture.kanama.ios.allocIosRawValue
import net.multigesture.kanama.ios.cinterop.kanama_ios_fault_set_probe
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_take_pending_blob
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_take_pending_container_blob
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_take_pending_packed
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_take_pending_utf8
import net.multigesture.kanama.ios.decodeIosCallArg
import net.multigesture.kanama.ios.decodeIosPropertyValue
import net.multigesture.kanama.ios.encodeIosRawValue
import net.multigesture.kanama.ios.kanamaIosRuntimeScriptInstanceCallV
import net.multigesture.kanama.ios.kanamaIosRuntimeScriptInstanceGetProperty
import net.multigesture.kanama.ios.kanamaIosRuntimeScriptInstanceSetProperty
import net.multigesture.kanama.ios.kanamaIosRuntimeScriptInstanceSetPropertyString
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.Basis
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.GodotRealVar
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.Plane
import net.multigesture.kanama.types.Projection
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

// The OBJECTCALLS self-test (task 138 item 24): a Kanama test hook, compiled only into a build made
// with -PkanamaIosSelfTest=true (the device runners pass it; build.gradle.kts excludes every
// *SelfTest.kt otherwise), so users' builds neither run nor carry it.

// Debug-gated self-test (called from the C scene-init self-test): validates the full
// Kotlin->C->Godot path through ObjectCalls — Vector3 float32 component layout, bool,
// int64 return, and scalar-float-as-double — against real Godot on device.
//
// THE PROBE RULE (task 117 P2' follow-up 5). A self-test probe must assert a value THE NO-OP PATH
// CANNOT PRODUCE. A static routed with a zero instance used to hit the C guard and return null /
// 0 / false / "" / an empty list / a zeroed struct, so a probe whose EXPECTED value is any of
// those cannot tell the working call from the call that never happened: it is not a probe, it is a
// row that keeps passing on the day the feature breaks. Two shipped that way and both are replaced
// below — ShaderIncludeDB.listBuiltInIncludeFiles / getBuiltInIncludeFile expected a non-empty list
// and a non-empty source, but Godot registers the built-in includes only from the RenderingDevice
// renderer (servers/rendering/renderer_rd/renderer_scene_render_rd.cpp:1796-1798), so under the GL
// Compatibility renderer the honest answer IS the empty list, which is also the no-op answer.
// For an INSTANCE row the rule is satisfied by requireSingleton / requireObject below: they prove
// the instance is non-zero, so the static route is not taken and a default return is a real answer.
// Task 124 — the number of DELIBERATE faults a healthy DEBUG build produces. Every one of them is
// raised by runFaultProbes below, with the sink in probe mode so each prints as `FAULT-PROBE`,
// and nothing else in either self-test phase raises one. The seven, in the order they fire:
//
//   1  bind-lookup-failed  getMethodBind("Node3D", "set_visible", 1L) — a deliberately wrong hash
//   2  null-bind           ptrcallWithBoolArg through the null bind probe 1 produced
//   3  pending-protocol    take_pending_utf8 on the drained g_pending_utf8 slot (utf8-ret row)
//   4  pending-protocol    take_pending_utf8 on the drained g_pending_utf8 slot (variant-ret row)
//   5  pending-protocol    take_pending_packed on the drained g_pending_packed slot
//   6  pending-protocol    take_pending_container_blob on the drained g_pending_container_blob slot
//   7  pending-protocol    take_pending_blob on the drained g_pending_blob slot
//
// Both summary lines print this constant as `expected=<N>` beside the live `faults=<N>` so
// kanama-demos/scripts/ios_device_run.sh can compare the two without knowing anything about this
// file. Change it only when you change the probes — and the probes are all in one place, directly
// below, so that the constant and the thing it counts cannot drift apart.
private const val SELFTEST_EXPECTED_FAULTS = 7

// ==============================================================================================
// Task 124 — THE PERMANENT RED RUN. Seven deliberate wrong calls into the C shim, each asserted to
// be REPORTED. Every other row in this file proves a call WORKS; these seven prove that a call that
// does NOT work says so, which is the property that was missing when every Godot static was a
// silent no-op on iOS for six days and 205 green checks noticed nothing (119 item 35).
//
// They run on every debug build, on the device, forever — a red run you cannot forget to
// re-execute. The cost is seven lines of console noise and the `faults=7 expected=7` contract on
// the summary lines; the benefit is that the day the fault sink stops reporting, these rows go red
// instead of the whole mechanism quietly ceasing to exist.
//
// Probes 3-7 used to sit inline beside their producers as `…(pending slot drained)` rows that
// asserted only `take_pending_*(null, 0) == -1`. That -1 is reachable ONLY through the guard that
// now reports `pending-protocol`, so those rows were always red runs of that guard — a healthy
// build printed five unexpected FAULT lines and `faults=7 expected=2`. They are fault
// probes, so they now say so, and they assert the REPORT as well as the -1. Their producers, and
// the non-default assertions that proved those producers worked (`…, pending slot` / `…, pending
// blob`), stay exactly where they were: what each row proved about its own producer is still
// proved in its own place, and what is asserted here is the guard.
//
// The probes are MARKED BY THE SINK, not by the lines around them: runFaultProbes turns the shim's
// probe mode on before the first probe and off after the last (in a finally), and while it is on
// the sink prints `[kanama][ios][c] FAULT-PROBE …` instead of `[kanama][ios][c] FAULT …`. The
// device runner fails the run on any `[kanama][ios][c] FAULT ` line (with the space), so a real
// fault — `null-bind` included — stays fatal while the seven probes do not trip it. An earlier
// design bracketed the probes with `fault-probes begin`/`end` println lines and trusted the FAULT
// lines between them; the first device run showed why that cannot work: these println lines go to
// stdout, the sink writes stderr, and `devicectl --console` merges the two without preserving their
// relative order, so all seven probe lines landed after the `end` marker. The begin/end lines stay
// only as human-readable markers in the log; nothing parses them, and nothing may rely on where the
// FAULT-PROBE lines appear relative to them. What proves that the probes and nothing else fired is
// the count: `faults=7 expected=7` on both summary lines.
// The set_script row: a Resource with the probe script attached through Godot, cash set through the
// engine, its owning wrapper closed (Godot calls refcount_decremented: the link goes weak), only
// the script object kept. Returns (owner address, owner instance id).
private fun ownerLinkAttachAndKeepOnlyScriptObject(scriptObject: Long): Pair<Long, Long> {
  val owner = net.multigesture.kanama.api.Resource.create()
  val ownerAddress = owner.handle.segment.address()
  owner.setScript(
    net.multigesture.kanama.api.Resource.fromHandle(
      GodotHandle(MemorySegment.ofAddress(scriptObject))
    )
  )
  OwnerLinkHolder.held = net.multigesture.kanama.ios.iosScriptInstanceForOwner(ownerAddress)
  GodotObject(GodotHandle(MemorySegment.ofAddress(ownerAddress))).set("cash", 4242L)
  val ownerId = owner.instanceId
  owner.close()
  return ownerAddress to ownerId
}

// Task 132 owner-link row: the only reference the row keeps to the script object.
private object OwnerLinkHolder {
  var held: Any? = null
}

// A resource with the owner-link probe script; its owning wrapper is closed and only the script
// object is kept. Returns (instance handle, owner instance id).
private fun ownerLinkKeepOnlyScriptObject(script: Long): Pair<Long, Long> {
  val owner = net.multigesture.kanama.api.Resource.create()
  val ownerAddress = owner.handle.segment.address()
  val instance = KanamaIosRuntime.createScriptInstance(script, ownerAddress)
  OwnerLinkHolder.held = net.multigesture.kanama.ios.iosScriptInstanceForOwner(ownerAddress)
  val ownerId = owner.instanceId
  owner.close()
  if (instance != 0L)
    net.multigesture.kanama.ios.kanamaIosRuntimeScriptInstanceRefcountDecremented(instance)
  return instance to ownerId
}

// Task 132 self-test helpers: each is its own function so no stack slot of the caller keeps a
// dropped wrapper reachable.
private fun dropOwnedResources(count: Int): LongArray =
  LongArray(count) { net.multigesture.kanama.api.Resource.create().instanceId }

// Task 138 item 6 (R6.6): [body] on a new thread (an NSThread, what a Godot `Thread` is on iOS),
// polled by the calling thread every millisecond through [whileWaiting]; returns once [body] ended.
@OptIn(kotlin.concurrent.atomics.ExperimentalAtomicApi::class)
private fun onWorkerThread(body: () -> Unit, whileWaiting: () -> Unit = {}) {
  val done = kotlin.concurrent.atomics.AtomicInt(0)
  platform.Foundation.NSThread {
      try {
        body()
      } finally {
        done.store(1)
      }
    }
    .start()
  while (done.load() == 0) {
    whileWaiting()
    platform.posix.usleep(1_000u)
  }
}

// A second owned +1 on [kept], closed: refcount back to 1, its cleanup cancelled.
private fun closeSecondOwnedReference(kept: RefCounted) {
  RefCounted.fromHandle(kept.handle)!!.apply { retainForKotlinWrapper() }.close()
}

// A second owned +1 on [kept], dropped unclosed: refcount 2 until the GC fallback releases it.
private fun dropSecondOwnedReference(kept: RefCounted) {
  RefCounted.fromHandle(kept.handle)!!.retainForKotlinWrapper()
}

private fun runFaultProbes(n3: MemorySegment, check: (String, Boolean) -> Unit) {
  println(
    "[kanama][ios][kn] OBJECTCALLS SELFTEST fault-probes begin " +
      "(expect $SELFTEST_EXPECTED_FAULTS FAULT-PROBE lines; stdout/stderr order is not preserved)"
  )
  kanama_ios_fault_set_probe(1)
  try {
    runFaultProbesInProbeMode(n3, check)
  } finally {
    kanama_ios_fault_set_probe(0)
  }
  println("[kanama][ios][kn] OBJECTCALLS SELFTEST fault-probes end")
}

private fun runFaultProbesInProbeMode(n3: MemorySegment, check: (String, Boolean) -> Unit) {
  val faultsBeforeProbes = ObjectCalls.faultCount()
  var raised = 0

  // Probe 1: a method bind lookup with a WRONG HASH. Godot returns NULL and the lookup reports
  // `bind-lookup-failed` with the names and the hash — the one place where that detail exists.
  val deliberatelyNullBind = ObjectCalls.getMethodBind("Node3D", "set_visible", 1L)
  raised++
  check(
    "fault-probe(bind-lookup-failed counted)",
    ObjectCalls.faultCount() == faultsBeforeProbes + raised,
  )
  check(
    "fault-probe(bind-lookup-failed names Node3D.set_visible)",
    ObjectCalls.lastFault().contains("bind-lookup-failed") &&
      ObjectCalls.lastFault().contains("Node3D.set_visible"),
  )

  // Probe 2: call through that null bind on a REAL instance. The dispatch body's `method_bind == 0`
  // guard is the one that used to swallow the whole call; it now reports `null-bind` (no names are
  // available at call time — probe 1 is where they were).
  ObjectCalls.ptrcallWithBoolArg(deliberatelyNullBind, n3, false)
  raised++
  check("fault-probe(null-bind counted)", ObjectCalls.faultCount() == faultsBeforeProbes + raised)
  check("fault-probe(null-bind reason)", ObjectCalls.lastFault().contains("null-bind"))

  // Probes 3-7: take from a DRAINED pending slot. The Kotlin read-back takes only after a
  // producer parked something (every `take_pending_*` call outside this block sits in the `else`
  // branch that the producer's own over-capacity length selected), so "nothing pending" is a
  // protocol violation by construction — which is exactly what each of these five asks for. Each
  // asserts the documented default (-1), that the sink counted exactly one new fault, and that
  // lastFault() names both `pending-protocol` and the buffer the shim says was empty.
  fun pendingProbe(label: String, buffer: String, drained: Long) {
    raised++
    check("fault-probe(pending-protocol $label) == -1", drained == -1L)
    check(
      "fault-probe(pending-protocol $label) counted",
      ObjectCalls.faultCount() == faultsBeforeProbes + raised,
    )
    val last = ObjectCalls.lastFault()
    check(
      "fault-probe(pending-protocol $label) reported",
      last.contains("pending-protocol") && last.contains(buffer),
    )
  }

  pendingProbe(
    "g_pending_utf8 drained: utf8-ret",
    "g_pending_utf8",
    kanama_ios_godot_take_pending_utf8(null, 0L),
  )
  pendingProbe(
    "g_pending_utf8 drained: variant-ret",
    "g_pending_utf8",
    kanama_ios_godot_take_pending_utf8(null, 0L),
  )
  pendingProbe(
    "g_pending_packed drained: packed-ret",
    "g_pending_packed",
    kanama_ios_godot_take_pending_packed(ObjectCalls.VT_PACKED_BYTE_ARRAY, null, 0L),
  )
  pendingProbe(
    "g_pending_container_blob drained: container-ret",
    "g_pending_container_blob",
    kanama_ios_godot_take_pending_container_blob(null, 0L),
  )
  pendingProbe(
    "g_pending_blob drained: array-ret",
    "g_pending_blob",
    kanama_ios_godot_take_pending_blob(null, 0L),
  )

  check(
    "fault-probe(exactly $SELFTEST_EXPECTED_FAULTS deliberate faults)",
    raised == SELFTEST_EXPECTED_FAULTS &&
      ObjectCalls.faultCount() == faultsBeforeProbes + SELFTEST_EXPECTED_FAULTS,
  )
}

/**
 * One row per Variant type per generic path (the Variant path of `Object.get` / `call` / `callv` /
 * signals), each against a value the ENGINE built or compared, so a Kotlin-side encode/decode pair
 * that is wrong the same way both directions cannot pass:
 * - `ret` — a GDScript literal evaluated by an Expression comes back as the Kotlin value
 *   (kanama_ios_decode_variant_scalar + decodeVariantScalarReturn);
 * - `elem` — the same literal inside an Array and a Dictionary (kanama_ios_blob_encode_variant +
 *   ContainerBlob);
 * - `arg` — the Kotlin value passed to set_meta through Object.call, at the top level and inside an
 *   Array and a Dictionary, compared with the literal by the engine (encodeVariantArgs +
 *   kanama_ios_pt_arg_to_variant, IosReturnContainerScratch + kanama_ios_pt_blob_value_to_variant);
 * - `in` — the literal emitted by the engine to a Variant signal argument, at the top level and
 *   inside an Array and a Dictionary (kanama_ios_marshal_variant_args + decodeIosCallArg). Nil,
 *   Callable and Signal decode to null on every backend (desktop's variantToScalar too); their rows
 *   pin that, and a Callable / Signal signal argument is refused loudly (the `call-arg(Any?)` row).
 *   Types Kotlin has no distinct value for (StringName is a String; the packed arrays other than
 *   PackedByteArray are Lists, which encode as Array) and Nil at the top level (set_meta(null)
 *   erases the entry) have no `arg` row; Callable and Signal have no `arg` or `in` row.
 */
@OptIn(ExperimentalForeignApi::class)
private fun runVariantTypeRows(
  callBind: MemorySegment,
  callNode: MemorySegment,
  check: (String, Boolean) -> Unit,
) {
  val node = GodotObject(GodotHandle(callNode))
  class Case(val type: String, val literal: String, val value: Any?, val arg: Boolean = true)
  val xform3 =
    Transform3D(
      Basis(Vector3(1.0, 2.0, 3.0), Vector3(4.0, 5.0, 6.0), Vector3(7.0, 8.0, 9.0)),
      Vector3(10.0, 11.0, 12.0),
    )
  val cases =
    listOf(
      Case("Nil", "null", null, arg = false),
      Case("bool", "true", true),
      Case("int", "7", 7L),
      Case("float", "0.5", 0.5),
      Case("String", "\"kanama\"", "kanama"),
      Case("Vector2", "Vector2(0.5, 1.5)", Vector2(0.5, 1.5)),
      Case("Vector2i", "Vector2i(-3, 4)", Vector2i(-3, 4)),
      Case("Rect2", "Rect2(0.5, 1.5, 2.25, 3.125)", Rect2(Vector2(0.5, 1.5), Vector2(2.25, 3.125))),
      Case("Rect2i", "Rect2i(-1, 2, 30, 40)", Rect2i(Vector2i(-1, 2), Vector2i(30, 40))),
      Case("Vector3", "Vector3(1, 2.5, -3)", Vector3(1.0, 2.5, -3.0)),
      Case("Vector3i", "Vector3i(7, -8, 9)", Vector3i(7, -8, 9)),
      Case(
        "Transform2D",
        "Transform2D(Vector2(1, 2), Vector2(3, 4), Vector2(5, 6))",
        Transform2D(Vector2(1.0, 2.0), Vector2(3.0, 4.0), Vector2(5.0, 6.0)),
      ),
      Case("Vector4", "Vector4(0.5, -0.25, 8, 2)", Vector4(0.5, -0.25, 8.0, 2.0)),
      Case(
        "Vector4i",
        "Vector4i(-2147483648, 2147483647, 0, 7)",
        net.multigesture.kanama.types.Vector4i(Int.MIN_VALUE, Int.MAX_VALUE, 0, 7),
      ),
      Case("Plane", "Plane(0, 1, 0, 2.5)", Plane(Vector3(0.0, 1.0, 0.0), 2.5)),
      Case("Quaternion", "Quaternion(0, 0.6, 0, 0.8)", Quaternion(0.0, 0.6, 0.0, 0.8)),
      Case(
        "AABB",
        "AABB(Vector3(-1, -2, -3), Vector3(4, 5, 6))",
        AABB(Vector3(-1.0, -2.0, -3.0), Vector3(4.0, 5.0, 6.0)),
      ),
      Case(
        "Basis",
        "Basis(Vector3(1, 0, 0), Vector3(0, 0, 1), Vector3(0, -1, 0))",
        Basis(Vector3(1.0, 0.0, 0.0), Vector3(0.0, 0.0, 1.0), Vector3(0.0, -1.0, 0.0)),
      ),
      Case(
        "Transform3D",
        "Transform3D(Basis(Vector3(1, 2, 3), Vector3(4, 5, 6), Vector3(7, 8, 9)), " +
          "Vector3(10, 11, 12))",
        xform3,
      ),
      Case(
        "Projection",
        "Projection(Vector4(1, 2, 3, 4), Vector4(5, 6, 7, 8), Vector4(9, 10, 11, 12), " +
          "Vector4(13, 14, 15, 16))",
        Projection(
          Vector4(1.0, 2.0, 3.0, 4.0),
          Vector4(5.0, 6.0, 7.0, 8.0),
          Vector4(9.0, 10.0, 11.0, 12.0),
          Vector4(13.0, 14.0, 15.0, 16.0),
        ),
      ),
      Case("Color", "Color(0.5, 0.25, 0, 1)", Color(0.5, 0.25, 0.0, 1.0)),
      Case("StringName", "StringName(\"sn\")", "sn", arg = false),
      Case("NodePath", "NodePath(\"A/B\")", NodePath("A/B")),
      Case("RID", "rid_from_int64(77)", RID(77L)),
      Case("Object", "self", node),
      Case("Callable", "Callable(self, \"get_name\")", null, arg = false),
      Case("Signal", "Signal(self, \"ready\")", null, arg = false),
      Case("Dictionary", "{\"k\": 1}", mapOf("k" to 1L)),
      Case("Array", "[1, \"s\"]", listOf(1L, "s")),
      Case("PackedByteArray", "PackedByteArray([1, 2, 255])", byteArrayOf(1, 2, -1)),
      Case("PackedInt32Array", "PackedInt32Array([1, -2])", listOf(1, -2), arg = false),
      Case("PackedInt64Array", "PackedInt64Array([1, -2])", listOf(1L, -2L), arg = false),
      Case("PackedFloat32Array", "PackedFloat32Array([0.5, 2])", listOf(0.5f, 2f), arg = false),
      Case("PackedFloat64Array", "PackedFloat64Array([0.5, 2])", listOf(0.5, 2.0), arg = false),
      Case(
        "PackedStringArray",
        "PackedStringArray([\"a\", \"bb\"])",
        listOf("a", "bb"),
        arg = false,
      ),
      Case(
        "PackedVector2Array",
        "PackedVector2Array([Vector2(1, 2)])",
        listOf(Vector2(1.0, 2.0)),
        arg = false,
      ),
      Case(
        "PackedVector3Array",
        "PackedVector3Array([Vector3(1, 2, 3)])",
        listOf(Vector3(1.0, 2.0, 3.0)),
        arg = false,
      ),
      Case(
        "PackedColorArray",
        "PackedColorArray([Color(0.5, 0.25, 0, 1)])",
        listOf(Color(0.5, 0.25, 0.0, 1.0)),
        arg = false,
      ),
      Case(
        "PackedVector4Array",
        "PackedVector4Array([Vector4(1, 2, 3, 4)])",
        listOf(Vector4(1.0, 2.0, 3.0, 4.0)),
        arg = false,
      ),
    )
  check("variant-types(one case per Godot Variant type) n=${cases.size}", cases.size == 39)

  fun same(a: Any?, b: Any?): Boolean =
    when {
      a is ByteArray && b is ByteArray -> a.contentEquals(b)
      a is GodotObject && b is GodotObject -> a.segment.address() == b.segment.address()
      a is List<*> && b is List<*> -> a.size == b.size && a.indices.all { same(a[it], b[it]) }
      a is Map<*, *> && b is Map<*, *> ->
        a.size == b.size && a.keys.all { b.containsKey(it) && same(a[it], b[it]) }
      else -> a == b
    }
  fun show(v: Any?): String =
    when (v) {
      is ByteArray -> v.toList().toString()
      is GodotObject -> "Object@" + v.segment.address()
      is List<*> -> v.joinToString(prefix = "[", postfix = "]") { show(it) }
      is Map<*, *> ->
        v.entries.joinToString(prefix = "{", postfix = "}") { "${it.key}=${show(it.value)}" }
      else -> v.toString()
    }

  val expressionHandle = ObjectCalls.constructObject("Expression")
  // A raw construct this function destroys itself: the typed view is the borrowed one.
  val expression =
    net.multigesture.kanama.api.Expression.fromHandle(GodotHandle(expressionHandle))!!
  fun eval(code: String): Any? {
    // A literal the Expression cannot parse fails its row here, not as a wrong value.
    val parsed = expression.parse(code, emptyList())
    if (parsed != net.multigesture.kanama.api.GodotError.OK) error("Expression cannot parse $code")
    val result = expression.execute(emptyList(), node)
    if (expression.hasExecuteFailed()) error("Expression failed: $code")
    return result
  }
  val failed =
    linkedMapOf(
      "ret" to mutableListOf<String>(),
      "elem" to mutableListOf(),
      "arg" to mutableListOf(),
      "in" to mutableListOf(),
    )
  fun row(path: String, case: Case, body: () -> Pair<Boolean, String>) {
    val (ok, detail) =
      runCatching(body).getOrElse { false to "threw ${it::class.simpleName}: ${it.message}" }
    if (!ok) failed.getValue(path).add(case.type)
    check("variant-type(${case.type}) $path $detail", ok)
  }
  // The Expression, the signal connection and the probe metas are released even when a setup
  // call throws outside a row (that failure is its own FAIL line).
  var connection: net.multigesture.kanama.api.SignalConnection? = null
  try {
    ObjectCalls.callWithVariantArgs(
      ObjectCalls.getMethodBind("Object", "add_user_signal", 85656714L),
      callNode,
      listOf("kanamaVariantIn"),
    )
    val received = mutableListOf<Any?>()
    val inbound =
      net.multigesture.kanama.api.Signal1(
        node,
        "kanamaVariantIn",
        net.multigesture.kanama.api.SignalArgType.VARIANT,
      )
    connection = inbound.connect(node) { received.add(it) }

    for (case in cases) {
      val v = case.value
      val l = case.literal
      row("ret", case) {
        val got = eval(l)
        same(got, v) to "got=${show(got)}"
      }
      row("elem", case) {
        val array = eval("[$l]")
        val dictionary = eval("{\"k\": $l}")
        (same(array, listOf(v)) && same(dictionary, mapOf("k" to v))) to
          "array=${show(array)} dictionary=${show(dictionary)}"
      }
      if (case.arg) {
        row("arg", case) {
          ObjectCalls.callWithVariantArgs(callBind, callNode, listOf("set_meta", "t140", v))
          ObjectCalls.callWithVariantArgs(
            callBind,
            callNode,
            listOf("set_meta", "t140a", listOf(v)),
          )
          ObjectCalls.callWithVariantArgs(
            callBind,
            callNode,
            listOf("set_meta", "t140d", mapOf("k" to v)),
          )
          val equal =
            eval(
              "get_meta(\"t140\") == $l and get_meta(\"t140a\") == [$l] and " +
                "get_meta(\"t140d\") == {\"k\": $l}"
            )
          (equal == true) to "engine-equal=$equal"
        }
      }
      if (case.type != "Callable" && case.type != "Signal") {
        row("in", case) {
          received.clear()
          eval("emit_signal(\"kanamaVariantIn\", $l)")
          eval("emit_signal(\"kanamaVariantIn\", [$l])")
          eval("emit_signal(\"kanamaVariantIn\", {\"k\": $l})")
          same(received, listOf(v, listOf(v), mapOf("k" to v))) to "got=${show(received)}"
        }
      }
    }
  } catch (e: Throwable) {
    check("variant-types(setup) threw ${e::class.simpleName}: ${e.message}", false)
  } finally {
    runCatching { connection?.close() }
    for (key in listOf("t140", "t140a", "t140d")) {
      runCatching {
        ObjectCalls.callWithVariantArgs(callBind, callNode, listOf("remove_meta", key))
      }
    }
    ObjectCalls.destroyObject(expressionHandle)
  }
  println(
    "[kanama][ios][kn] OBJECTCALLS SELFTEST variant-types " +
      failed.entries.joinToString(" ") { (path, types) -> "$path-failed=${types.size}$types" }
  )

  // The three Object.get reads the generated API exercise found null on the iPhone: a RID
  // property, an Array[RID] property and an Array[Transform3D] property, each set through its
  // typed setter and read back through the Variant path. Each probe is a raw construct the row
  // destroys itself, so its typed view is the borrowed one (fromHandle).
  fun getRow(label: String, className: String, body: (GodotObject) -> Pair<Boolean, String>) {
    val handle = ObjectCalls.constructObject(className)
    val (ok, detail) =
      if (handle.address() == 0L) false to "not constructed"
      else
        runCatching { body(GodotObject(GodotHandle(handle))) }
          .getOrElse { false to "threw ${it::class.simpleName}: ${it.message}" }
    if (handle.address() != 0L) ObjectCalls.destroyObject(handle)
    check("variant-get($label) $detail", ok)
  }
  getRow("CharFXTransform.font RID", "CharFXTransform") { obj ->
    net.multigesture.kanama.api.CharFXTransform.fromHandle(obj.handle)!!.setFont(
      RID(0x1_0000_0005L)
    )
    val got = obj.get("font")
    (got == RID(0x1_0000_0005L)) to "got=${show(got)}"
  }
  getRow(
    "NavigationPathQueryParameters2D.excluded_regions Array[RID]",
    "NavigationPathQueryParameters2D",
  ) { obj ->
    net.multigesture.kanama.api.NavigationPathQueryParameters2D.fromHandle(obj.handle)!!
      .setExcludedRegions(listOf(RID(41L), RID(42L)))
    val got = obj.get("excluded_regions")
    (got == listOf(RID(41L), RID(42L))) to "got=${show(got)}"
  }
  getRow("GLTFSkin.inverse_binds Array[Transform3D]", "GLTFSkin") { obj ->
    net.multigesture.kanama.api.GLTFSkin.fromHandle(obj.handle)!!.setInverseBinds(listOf(xform3))
    val got = obj.get("inverse_binds")
    (got == listOf(xform3)) to "got=${show(got)}"
  }

  // The generic Map -> Dictionary path is String-key-only in every direction, with desktop's
  // message (BuiltinTypes.variantFromDictionaryInto): a script's Map<Long, V> return, a Map
  // inside a List return and a Map argument with a Long or Vector2i key are refused, never sent
  // to Godot with "1"-style String keys.
  val keyRefusals =
    listOf<Pair<String, () -> Unit>>(
      "kotlin.Long" to
        {
          net.multigesture.kanama.ios.kanamaIosVariantReturnSelfTest(mapOf(1L to "a"))
        },
      "kotlin.Long" to
        {
          net.multigesture.kanama.ios.kanamaIosVariantReturnSelfTest(listOf(mapOf(2L to "b")))
        },
      "net.multigesture.kanama.types.Vector2i" to
        {
          net.multigesture.kanama.ios.kanamaIosVariantReturnSelfTest(mapOf(Vector2i(1, 2) to 3L))
        },
      "kotlin.Long" to
        {
          ObjectCalls.callWithVariantArgs(
            callBind,
            callNode,
            listOf("set_meta", "kanama_key_probe", mapOf(4L to "d")),
          )
        },
    )
  val keyMessages = keyRefusals.map { (_, body) -> runCatching(body).exceptionOrNull()?.message }
  check(
    "dictionary-keys(generic Map with non-String keys refused like desktop) got=$keyMessages",
    keyMessages == keyRefusals.map { (type, _) -> "Unsupported Dictionary key type: $type" },
  )
}

@OptIn(ExperimentalNativeApi::class)
@CName("kanama_ios_runtime_objectcalls_selftest")
fun kanamaIosRuntimeObjectCallsSelfTest() {
  var pass = 0
  var fail = 0
  fun check(label: String, cond: Boolean) {
    if (cond) {
      pass++
    } else {
      fail++
      println("[kanama][ios][kn] OBJECTCALLS SELFTEST FAIL: $label")
    }
  }

  // Every singleton row goes through this. Godot registers singletons in stages, so a lookup
  // can legitimately return 0 at the level this self-test runs at, and since task 117 P2' D19
  // a zero instance is no longer a silent no-op: ObjectCalls routes it to the static entry
  // point and Godot gets a null `this` (that is how the RenderingServer row below crashed the
  // phone). Record the presence as its own check and let the row skip its dependent calls —
  // recorded as FAILED with a reason, never silently passed, never executed with a zero
  // instance.
  fun requireSingleton(name: String): MemorySegment {
    val segment = ObjectCalls.getSingleton(name)
    check("singleton-present($name)", segment.address() != 0L)
    return segment
  }

  // The same protection for a CONSTRUCTED instance, used by the rows whose own assertion is a
  // DEFAULT value (an empty list, an empty string, a zero Rect2i, "no signal fired") and so
  // cannot tell a working call from a call that never happened — the shape of the false pass the
  // RenderingServer row shipped with. Every other row asserts a round-trip value a zero instance
  // cannot produce, so its own check is the guard; see the audit in the commit message.
  fun requireObject(className: String): MemorySegment {
    val segment = ObjectCalls.constructObject(className)
    check("object-constructed($className)", segment.address() != 0L)
    return segment
  }

  val n3 = ObjectCalls.constructObject("Node3D")
  ObjectCalls.ptrcallWithVector3Arg(
    ObjectCalls.getMethodBind("Node3D", "set_position", 3460891852L),
    n3,
    Vector3(1.0, 2.0, 3.0),
  )
  val p =
    ObjectCalls.ptrcallNoArgsRetVector3(
      ObjectCalls.getMethodBind("Node3D", "get_position", 3360562783L),
      n3,
    )
  check("vector3", p.x.toDouble() == 1.0 && p.y.toDouble() == 2.0 && p.z.toDouble() == 3.0)

  // Generated-helper round-trip (T3.1): ptrcallWithVector3ArgRetVector3 lives in the
  // GENERATED MEMBERS region at the end of this object, not hand-written —
  // it exercises the generator's Vector3 arg-in + Vector3 ret-out codegen against real
  // Godot. Use a FRESH Node3D (identity transform) so to_global is the identity map
  // and the assertion is independent of in-tree global-transform semantics; this
  // isolates the marshalling (arg float32 layout in, ret float32 layout out).
  val n3b = ObjectCalls.constructObject("Node3D")
  val g =
    ObjectCalls.ptrcallWithVector3ArgRetVector3(
      ObjectCalls.getMethodBind("Node3D", "to_global", 192990374L),
      n3b,
      Vector3(10.0, 20.0, 30.0),
    )
  println("[kanama][ios][kn] OBJECTCALLS SELFTEST generated-vector3 g=(${g.x}, ${g.y}, ${g.z})")
  check(
    "generated-vector3-arg-ret",
    g.x.toDouble() == 10.0 && g.y.toDouble() == 20.0 && g.z.toDouble() == 30.0,
  )

  ObjectCalls.ptrcallWithBoolArg(
    ObjectCalls.getMethodBind("Node3D", "set_visible", 2586408642L),
    n3,
    false,
  )
  check(
    "bool",
    !ObjectCalls.ptrcallNoArgsRetBool(
      ObjectCalls.getMethodBind("Node3D", "is_visible", 36873697L),
      n3,
    ),
  )

  val id =
    ObjectCalls.ptrcallNoArgsRetLong(
      ObjectCalls.getMethodBind("Object", "get_instance_id", 3905245786L),
      n3,
    )
  check("int64-ret", id != 0L)

  val n2 = ObjectCalls.constructObject("Node2D")
  ObjectCalls.ptrcallWithDoubleArg(
    ObjectCalls.getMethodBind("Node2D", "set_rotation", 373806689L),
    n2,
    0.5,
  )
  check(
    "scalar-float-as-double",
    ObjectCalls.ptrcallNoArgsRetDouble(
      ObjectCalls.getMethodBind("Node2D", "get_rotation", 1740695150L),
      n2,
    ) == 0.5,
  )

  val parts = ObjectCalls.constructObject("GPUParticles3D")
  ObjectCalls.ptrcallWithIntArg(
    ObjectCalls.getMethodBind("GPUParticles3D", "set_amount", 1286410249L),
    parts,
    1234567,
  )
  check(
    "scalar-int(8-byte)",
    ObjectCalls.ptrcallNoArgsRetInt(
      ObjectCalls.getMethodBind("GPUParticles3D", "get_amount", 3905245786L),
      parts,
    ) == 1234567,
  )

  // Byte-array round-trip: STATIC dispatch (create_from_data ptrcalls with a NULL instance)
  // + BUILD-tagged PT_PACKED_BYTE_ARRAY arg + bulk byte-array return in one row.
  // Width-sensitive values (0/1/127/-128/-1) catch sign or width slips in the byte cells.
  val pattern = byteArrayOf(0, 1, 127, -128, -1, 66, 7, -103) // 2x1 RGBA8 texels
  val imgHandle =
    ObjectCalls.ptrcallStaticWithTwoLongBoolLongByteArrayArgsRetObject(
      ObjectCalls.getMethodBind("Image", "create_from_data", 299398494L),
      2L,
      1L,
      false,
      5L /* Image.FORMAT_RGBA8 */,
      pattern,
    )
  check("static-byte-arg-create", imgHandle.address() != 0L)
  if (imgHandle.address() != 0L) {
    val back =
      ObjectCalls.ptrcallNoArgsRetByteArray(
        ObjectCalls.getMethodBind("Image", "get_data", 2362200018L),
        imgHandle,
      )
    check("byte-array-round-trip", back.contentEquals(pattern))
    if (!back.contentEquals(pattern)) {
      println(
        "[kanama][ios][kn] OBJECTCALLS SELFTEST byte-array got ${back.size} bytes: ${back.joinToString()}"
      )
    }
  }

  // T3.3: Input singleton + action polling marshalling. With no live input the values
  // are 0.0/false, but this validates getSingleton + the generated StringName-arg
  // helpers (ptrcallWithTwoStringNameArgsRetDouble / ptrcallWithStringNameAndBoolArgRetBool)
  // on device. The gameplay assertion (nonzero under input) is T3.4's full-demo run.
  val inputSingleton = requireSingleton("Input")
  if (inputSingleton.address() != 0L) {
    val axis =
      ObjectCalls.ptrcallWithTwoStringNameArgsRetDouble(
        ObjectCalls.getMethodBind("Input", "get_axis", 1958752504L),
        inputSingleton,
        "ui_left",
        "ui_right",
      )
    check("input-get_axis(no-input==0)", axis == 0.0)
    val jumped =
      ObjectCalls.ptrcallWithStringNameAndBoolArgRetBool(
        ObjectCalls.getMethodBind("Input", "is_action_just_pressed", 1558498928L),
        inputSingleton,
        "ui_accept",
        false,
      )
    check("input-is_action_just_pressed(no-input==false)", !jumped)
  } else check("input-action-polling (singleton absent)", false)

  // Vector2i (2x int32, 8B): Sprite2D.set_frame_coords(Vector2i(3,7)) -> get_frame_coords()
  // Godot rejects frame_coords outside [0,hframes)x[0,vframes) (defaults 1x1) via
  // ERR_FAIL_INDEX, so widen the frame grid first or (3,7) is a silent no-op.
  // DEVICE-VALIDATED 2026-06-12 (iPhone 12, iOS 26.5) — Phase 2.1
  val sprite2d = ObjectCalls.constructObject("Sprite2D")
  ObjectCalls.ptrcallWithLongArg(
    ObjectCalls.getMethodBind("Sprite2D", "set_hframes", 1286410249L),
    sprite2d,
    4L,
  )
  ObjectCalls.ptrcallWithLongArg(
    ObjectCalls.getMethodBind("Sprite2D", "set_vframes", 1286410249L),
    sprite2d,
    8L,
  )
  ObjectCalls.ptrcallWithVector2iArg(
    ObjectCalls.getMethodBind("Sprite2D", "set_frame_coords", 1130785943L),
    sprite2d,
    Vector2i(3, 7),
  )
  val v2i =
    ObjectCalls.ptrcallNoArgsRetVector2i(
      ObjectCalls.getMethodBind("Sprite2D", "get_frame_coords", 3690982128L),
      sprite2d,
    )
  check("vector2i", v2i.x == 3 && v2i.y == 7)

  // Vector3i (3x int32, 12B): PlaceholderTexture3D.set_size(Vector3i(5,11,17)) -> get_size()
  // DEVICE-VALIDATED 2026-06-12 (iPhone 12, iOS 26.5) — Phase 2.1
  val tex3d = ObjectCalls.constructObject("PlaceholderTexture3D")
  ObjectCalls.ptrcallWithVector3iArg(
    ObjectCalls.getMethodBind("PlaceholderTexture3D", "set_size", 560364750L),
    tex3d,
    Vector3i(5, 11, 17),
  )
  val v3i =
    ObjectCalls.ptrcallNoArgsRetVector3i(
      ObjectCalls.getMethodBind("PlaceholderTexture3D", "get_size", 2785653706L),
      tex3d,
    )
  check("vector3i", v3i.x == 5 && v3i.y == 11 && v3i.z == 17)

  // Color (4x float32, 16B): CanvasItem.set_modulate(Color(0.125,0.25,0.5,0.75)) -> get_modulate()
  // Validation-free: modulate is a stored Color with no engine clamping or index checks.
  // Component values 0.125/0.25/0.5/0.75 are exact in float32 (powers of 2), so
  // equality checks are stable without epsilon. Node2D is a CanvasItem.
  // DEVICE-VALIDATED 2026-06-12 (iPhone 12, iOS 26.5) — Phase 2.2
  val n2c = ObjectCalls.constructObject("Node2D")
  ObjectCalls.ptrcallWithColorArg(
    ObjectCalls.getMethodBind("CanvasItem", "set_modulate", 2920490490L),
    n2c,
    Color(0.125, 0.25, 0.5, 0.75),
  )
  val col =
    ObjectCalls.ptrcallNoArgsRetColor(
      ObjectCalls.getMethodBind("CanvasItem", "get_modulate", 3444240500L),
      n2c,
    )
  check("color", col.r == 0.125 && col.g == 0.25 && col.b == 0.5 && col.a == 0.75)

  // Rect2 (4x float32, 16B): GPUParticles2D.set_visibility_rect -> get_visibility_rect()
  // Validation-free: visibility_rect is a stored Rect2 with no engine clamping.
  // Component values 1.5/2.5/3.5/4.5 are exact in float32, so equality is stable.
  // DEVICE-VALIDATED 2026-06-12 (iPhone 12, iOS 26.5) — Phase 2.2
  val gp2d = ObjectCalls.constructObject("GPUParticles2D")
  ObjectCalls.ptrcallWithRect2Arg(
    ObjectCalls.getMethodBind("GPUParticles2D", "set_visibility_rect", 2046264180L),
    gp2d,
    Rect2(Vector2(1.5, 2.5), Vector2(3.5, 4.5)),
  )
  val r2 =
    ObjectCalls.ptrcallNoArgsRetRect2(
      ObjectCalls.getMethodBind("GPUParticles2D", "get_visibility_rect", 1639390495L),
      gp2d,
    )
  check(
    "rect2",
    r2.position.x.toDouble() == 1.5 &&
      r2.position.y.toDouble() == 2.5 &&
      r2.size.x.toDouble() == 3.5 &&
      r2.size.y.toDouble() == 4.5,
  )

  // Rect2i return (4x int32, 16B): a fresh TileMap's get_used_rect is exactly
  // Rect2i(0, 0, 0, 0) — deterministic, validation-free. Exercises the generated
  // ptrcallNoArgsRetRect2i read-back (the shape that unlocks
  // DisplayServer.get_display_safe_area for the mobile safe-area work, task 26).
  val tilemap = requireObject("TileMap")
  if (tilemap.address() != 0L) {
    val r2i =
      ObjectCalls.ptrcallNoArgsRetRect2i(
        ObjectCalls.getMethodBind("TileMap", "get_used_rect", 410525958L),
        tilemap,
      )
    check(
      "rect2i-ret(used_rect==0,0,0,0)",
      r2i.position.x == 0 && r2i.position.y == 0 && r2i.size.x == 0 && r2i.size.y == 0,
    )
    ObjectCalls.destroyObject(tilemap)
  } else check("rect2i-ret(TileMap.get_used_rect) (instance absent)", false)

  // String-return (Object.get_class -> "Node2D"): exercises ptrcallNoArgsRetString
  // through the full Kotlin -> dedicated C helper -> Godot path (string_to_utf8_chars
  // round-trip). Deterministic, validation-free class name.
  // DEVICE-VALIDATED 2026-06-12 (iPhone 12, iOS 26.5) — Phase 2.3a
  val n2cls = ObjectCalls.constructObject("Node2D")
  val cls =
    ObjectCalls.ptrcallNoArgsRetString(
      ObjectCalls.getMethodBind("Object", "get_class", 201670096L),
      n2cls,
    )
  check("string-ret(get_class==Node2D)", cls == "Node2D")

  // Virtual String-RETURN encode width (task 13 — non-POD virtual returns). A value-returning
  // @OverrideVirtual whose Kotlin result is a String hands it back through a pointer in the fixed
  // return scratch (kanama_ios_runtime_script_instance_call_v -> encodeIosReturn). A long (>32B)
  // string must NOT truncate to the 32-byte buffer. This asserts the Kotlin encoder half; the C
  // half (build the Godot Variant + destroy-after-read) is the "virtual-string-ret roundtrip"
  // row in kanama_ios_ptrcall_selftest.
  val vRetLong = "kanama-virtual-string-return-selftest-0123456789-abcdefghijklmnopqrstuvwxyz"
  check(
    "virtual-string-ret-encode(long>32B)",
    net.multigesture.kanama.ios.kanamaIosVirtualStringReturnSelfTest(vRetLong) == vRetLong,
  )
  check(
    "virtual-string-ret-encode(empty)",
    net.multigesture.kanama.ios.kanamaIosVirtualStringReturnSelfTest("") == "",
  )

  // Virtual PackedStringArray-RETURN encode (task 13). A List<String>-returning @OverrideVirtual
  // ships a length-prefixed blob the C shim rebuilds into a Godot PackedStringArray. Assert the
  // Kotlin encoder half preserves element boundaries — including a >16B element and the empty
  // list; the C half (build + destroy-after-read) is the "virtual-packed-string-ret" C row.
  val vPsa = listOf("kanama-config-warning-alpha-0123456789", "beta", "")
  check(
    "virtual-packed-string-ret-encode(mixed)",
    net.multigesture.kanama.ios.kanamaIosVirtualPackedStringArrayReturnSelfTest(vPsa) == vPsa,
  )
  check(
    "virtual-packed-string-ret-encode(empty-list)",
    net.multigesture.kanama.ios.kanamaIosVirtualPackedStringArrayReturnSelfTest(emptyList()) ==
      emptyList<String>(),
  )

  // Virtual Variant-RETURN routing (task 13). A Variant-returning @OverrideVirtual (e.g.
  // Control._get_drag_data) returns Any?; encodeIosReturn dispatches on the runtime type to the
  // right PT tag, and the C side builds the concrete Variant. Assert the routing for the audited
  // inner types (PT tags: BOOL=1, INT64=3, OBJECT=13, STRING=16, PACKED_STRING_ARRAY=28). null
  // routes to OBJECT with a 0 handle, which the C side boxes as a nil Variant — not VOID, which the
  // property-get export would report as "property not found" (task 115).
  check(
    "virtual-variant-ret(null->nil)",
    net.multigesture.kanama.ios.kanamaIosVariantReturnSelfTest(null) == 13,
  )
  check(
    "virtual-variant-ret(String->STRING)",
    net.multigesture.kanama.ios.kanamaIosVariantReturnSelfTest("drag:7") == 16,
  )
  check(
    "virtual-variant-ret(Long->INT64)",
    net.multigesture.kanama.ios.kanamaIosVariantReturnSelfTest(42L) == 3,
  )
  check(
    "virtual-variant-ret(Bool->BOOL)",
    net.multigesture.kanama.ios.kanamaIosVariantReturnSelfTest(true) == 1,
  )
  check(
    "virtual-variant-ret(List<String>->PACKED_STRING_ARRAY)",
    net.multigesture.kanama.ios.kanamaIosVariantReturnSelfTest(listOf("a", "bb")) == 28,
  )
  // Task 128 B: a Godot enum value class reaching the script-return encoder boxed (an `Any?`
  // Variant return holding one; the generated bridge unwraps `.value` itself for enum-typed
  // @Export / registered-function / @OverrideVirtual slots) leaves as the INT64 it stands
  // for.
  check(
    "virtual-variant-ret(Node.ProcessMode->INT64)",
    net.multigesture.kanama.ios.kanamaIosVariantReturnSelfTest(
      net.multigesture.kanama.api.Node.ProcessMode.ALWAYS
    ) == 3,
  )

  // Virtual packed/container-RETURN encoders (task 29). Each row encodes through the live
  // return scratch and parses the buffer back the way the C side reads it — width-sensitive
  // values (full-range bytes, Int.MIN/MAX, a >32-bit long, 1.0e308) catch element-width
  // regressions before the C half (the "virtual-packed-*-ret roundtrip" C rows) even runs.
  val vInts = intArrayOf(0, Int.MAX_VALUE, Int.MIN_VALUE)
  check(
    "virtual-packed-int32-ret-encode(INT32_MIN/MAX)",
    net.multigesture.kanama.ios.kanamaIosVirtualPackedIntsReturnSelfTest(vInts) == vInts.toList(),
  )
  val vLongs = longArrayOf(72623859790382856L, -1L)
  check(
    "virtual-packed-int64-ret-encode(>32-bit)",
    net.multigesture.kanama.ios.kanamaIosVirtualPackedLongsReturnSelfTest(vLongs) == vLongs.toList(),
  )
  val vBytes = byteArrayOf(0x7F, 0x00, -0x80)
  check(
    "virtual-packed-byte-ret-encode(full-range)",
    net.multigesture.kanama.ios.kanamaIosVirtualPackedBytesReturnSelfTest(vBytes).toList() ==
      vBytes.toList(),
  )
  val vDoubles = doubleArrayOf(0.5, 1.0e308)
  check(
    "virtual-packed-float64-ret-encode(1e308)",
    net.multigesture.kanama.ios.kanamaIosVirtualPackedDoublesReturnSelfTest(vDoubles) ==
      vDoubles.toList(),
  )
  val vVec3 = listOf(Vector3(1.0, 2.0, 3.0), Vector3(4.0, 5.0, 6.0))
  check(
    "virtual-packed-vector3-ret-encode",
    net.multigesture.kanama.ios.kanamaIosVirtualPackedVector3ReturnSelfTest(vVec3) == vVec3,
  )
  // Dictionary/Array blobs: assert entry order, tag routing (INT64=3, STRING=16, BOOL=1,
  // VOID=0), and payload byte widths survive the blob encode.
  val vDictString = "kanama-dictionary-return-selftest" // >16B
  check(
    "virtual-dictionary-ret-encode(int64+string)",
    net.multigesture.kanama.ios.kanamaIosVirtualDictionaryReturnSelfTest(
      linkedMapOf("i" to 7L, "s" to vDictString)
    ) == listOf("i=3:8", "s=16:${vDictString.encodeToByteArray().size}"),
  )
  check(
    "virtual-array-ret-encode(int64+string+bool+nil)",
    net.multigesture.kanama.ios.kanamaIosVirtualArrayReturnSelfTest(
      listOf(5L, "kanama", true, null)
    ) == listOf("3:8", "16:6", "1:1", "0:0"),
  )
  // Variant-return routing for the task-29 runtime types (PT tags: VECTOR3=8, RID=14,
  // PACKED_VECTOR2_ARRAY=23, DICTIONARY=29, ARRAY=30, PACKED_BYTE=31, PACKED_INT32=32,
  // PACKED_INT64=33, PACKED_FLOAT32=34, PACKED_FLOAT64=35, PACKED_VECTOR3=36; the empty
  // list ships as a generic empty ARRAY, which converts to any packed/typed array engine-side).
  check(
    "virtual-variant-ret(Vector3->VECTOR3)",
    net.multigesture.kanama.ios.kanamaIosVariantReturnSelfTest(Vector3(1.0, 2.0, 3.0)) == 8,
  )
  check(
    "virtual-variant-ret(RID->RID)",
    net.multigesture.kanama.ios.kanamaIosVariantReturnSelfTest(
      net.multigesture.kanama.types.RID(7L)
    ) == 14,
  )
  check(
    "virtual-variant-ret(Map->DICTIONARY)",
    net.multigesture.kanama.ios.kanamaIosVariantReturnSelfTest(mapOf("k" to 1L)) == 29,
  )
  check(
    "virtual-variant-ret(empty-List->ARRAY)",
    net.multigesture.kanama.ios.kanamaIosVariantReturnSelfTest(emptyList<Any?>()) == 30,
  )
  check(
    "virtual-variant-ret(List<Long>->ARRAY)",
    net.multigesture.kanama.ios.kanamaIosVariantReturnSelfTest(listOf(1L, 2L)) == 30,
  )
  check(
    "virtual-variant-ret(ByteArray->PACKED_BYTE_ARRAY)",
    net.multigesture.kanama.ios.kanamaIosVariantReturnSelfTest(byteArrayOf(1)) == 31,
  )
  check(
    "virtual-variant-ret(IntArray->PACKED_INT32_ARRAY)",
    net.multigesture.kanama.ios.kanamaIosVariantReturnSelfTest(intArrayOf(1)) == 32,
  )
  check(
    "virtual-variant-ret(LongArray->PACKED_INT64_ARRAY)",
    net.multigesture.kanama.ios.kanamaIosVariantReturnSelfTest(longArrayOf(1L)) == 33,
  )
  check(
    "virtual-variant-ret(FloatArray->PACKED_FLOAT32_ARRAY)",
    net.multigesture.kanama.ios.kanamaIosVariantReturnSelfTest(floatArrayOf(1.0f)) == 34,
  )
  check(
    "virtual-variant-ret(DoubleArray->PACKED_FLOAT64_ARRAY)",
    net.multigesture.kanama.ios.kanamaIosVariantReturnSelfTest(doubleArrayOf(1.0)) == 35,
  )
  check(
    "virtual-variant-ret(List<Vector2>->PACKED_VECTOR2_ARRAY)",
    net.multigesture.kanama.ios.kanamaIosVariantReturnSelfTest(listOf(Vector2(1.0, 2.0))) == 23,
  )
  check(
    "virtual-variant-ret(List<Vector3>->PACKED_VECTOR3_ARRAY)",
    net.multigesture.kanama.ios.kanamaIosVariantReturnSelfTest(listOf(Vector3(1.0, 2.0, 3.0))) == 36,
  )

  // StringName-return (Node.get_name): set a known name (StringName arg) then read it
  // back through ptrcallNoArgsRetStringName — exercises the StringName->String ctor
  // hop end to end. Deterministic; node name has no validation.
  // DEVICE-VALIDATED 2026-06-13 (iPhone 12, iOS 26.5) — Phase 2.3b
  val nName = ObjectCalls.constructObject("Node")
  ObjectCalls.ptrcallWithStringNameArg(
    ObjectCalls.getMethodBind("Node", "set_name", 3304788590L),
    nName,
    "KanamaSN",
  )
  val nm =
    ObjectCalls.ptrcallNoArgsRetStringName(
      ObjectCalls.getMethodBind("Node", "get_name", 2002593661L),
      nName,
    )
  check("string-name-ret(get_name==KanamaSN)", nm == "KanamaSN")

  // String arg (Node.set_scene_file_path -> get_scene_file_path): exercises the
  // generated ptrcallWithStringArg → PT_STRING construction (init_string C-side)
  // round-trip. Node (not a Control) avoids the treeless-Control post-init Theme
  // segfault — unrelated to String marshalling.
  // DEVICE-VALIDATED 2026-06-13 (iPhone 12, iOS 26.5) — Phase 2.4
  val sNode = ObjectCalls.constructObject("Node")
  ObjectCalls.ptrcallWithStringArg(
    ObjectCalls.getMethodBind("Node", "set_scene_file_path", 83702148L),
    sNode,
    "HelloKanama",
  )
  val textBack =
    ObjectCalls.ptrcallNoArgsRetString(
      ObjectCalls.getMethodBind("Node", "get_scene_file_path", 201670096L),
      sNode,
    )
  check("string-arg(set/get_scene_file_path==HelloKanama)", textBack == "HelloKanama")

  // NodePath arg (Node.get_node_or_null): add a named child, then look it up by
  // NodePath — the path must round-trip through PT_NODE_PATH construction
  // (init_node_path C-side) for the lookup to resolve to the same child handle.
  // DEVICE-VALIDATED 2026-06-13 (iPhone 12, iOS 26.5) — Phase 2.4
  val npParent = ObjectCalls.constructObject("Node")
  val npChild = ObjectCalls.constructObject("Node")
  ObjectCalls.ptrcallWithStringNameArg(
    ObjectCalls.getMethodBind("Node", "set_name", 3304788590L),
    npChild,
    "KPChild",
  )
  ObjectCalls.ptrcallWithObjectBoolLongArgs(
    ObjectCalls.getMethodBind("Node", "add_child", 3863233950L),
    npParent,
    npChild,
    false,
    0L,
  )
  val gotNode =
    ObjectCalls.ptrcallWithNodePathArgRetObject(
      ObjectCalls.getMethodBind("Node", "get_node_or_null", 2734337346L),
      npParent,
      NodePath("KPChild"),
    )
  check("nodepath-arg(get_node_or_null==child)", gotNode.address() == npChild.address())

  // NodePath-return (GPUParticles2D.set_sub_emitter -> get_sub_emitter): set a known path
  // (NodePath arg) then read it back through ptrcallNoArgsRetNodePath — exercises the
  // NodePath->String ctor hop end to end, wrapped back into a NodePath. Phase 2.7b.
  val gpNp = ObjectCalls.constructObject("GPUParticles2D")
  ObjectCalls.ptrcallWithNodePathArg(
    ObjectCalls.getMethodBind("GPUParticles2D", "set_sub_emitter", 1348162250L),
    gpNp,
    NodePath("../Emitter"),
  )
  val subEmitter =
    ObjectCalls.ptrcallNoArgsRetNodePath(
      ObjectCalls.getMethodBind("GPUParticles2D", "get_sub_emitter", 4075236667L),
      gpNp,
    )
  check("nodepath-ret(set/get_sub_emitter==../Emitter)", subEmitter == NodePath("../Emitter"))

  // PackedInt32Array-return (MeshLibrary.get_item_list): create two items (via the Variant
  // call path — ids 7 and 11), then read the id list back through ptrcallNoArgsRetPackedInt32List
  // (ptrcallRetPackedInt32List -> ..._ptrcall_ret_packed: one ptrcall, a long list drained from the
  // pending slot). The item-id map is
  // sorted, so the result is [7, 11] — known, distinct, non-zero values. MeshLibrary is a plain
  // Resource, safe
  // to construct at init time — unlike a physics body (the only emitted PackedInt32Array getter,
  // CollisionObject2D/3D.get_shape_owners) which segfaults in its constructor this early. Proves
  // the Kotlin -> C -> ptrcall -> read-back path end to end. Phase 2.7c.
  val meshLib = ObjectCalls.constructObject("MeshLibrary")
  val meshLibCallBind = ObjectCalls.getMethodBind("Object", "call", 3400424181L)
  ObjectCalls.callWithVariantArgs(meshLibCallBind, meshLib, listOf("create_item", 7L))
  ObjectCalls.callWithVariantArgs(meshLibCallBind, meshLib, listOf("create_item", 11L))
  val itemList =
    ObjectCalls.ptrcallNoArgsRetPackedInt32List(
      ObjectCalls.getMethodBind("MeshLibrary", "get_item_list", 1930428628L),
      meshLib,
    )
  check("packed-int32-ret(get_item_list==[7,11])", itemList == listOf(7, 11))

  // Typed-object-array-return (Node.get_children): a fresh parent Node with two child Nodes ->
  // get_children(false) returns [child0, child1] in add order. Exercises the Kotlin generic helper
  // ptrcallWithBoolArgRetTypedObjectList end to end (C Array read-back + handle -> MemorySegment ->
  // fromHandle, one ptrcall with a pending-slot drain). The self-test fromHandle is identity
  // (handle
  // passthrough,
  // null-filtered) so we can compare raw addresses without importing the api layer. Plain Node is
  // safe to construct at init time. Phase 2.7d.
  val gcAddChildBind = ObjectCalls.getMethodBind("Node", "add_child", 3863233950L)
  val gcParent = ObjectCalls.constructObject("Node")
  val gcChild0 = ObjectCalls.constructObject("Node")
  val gcChild1 = ObjectCalls.constructObject("Node")
  ObjectCalls.ptrcallWithObjectBoolLongArgs(gcAddChildBind, gcParent, gcChild0, false, 0L)
  ObjectCalls.ptrcallWithObjectBoolLongArgs(gcAddChildBind, gcParent, gcChild1, false, 0L)
  val gcKids =
    ObjectCalls.ptrcallWithBoolArgRetTypedObjectList(
      ObjectCalls.getMethodBind("Node", "get_children", 873284517L),
      gcParent,
      false,
    ) {
      if (it.address() == 0L) null else it
    }
  check(
    "typed-object-array-ret(get_children==[c0,c1])",
    gcKids.size == 2 &&
      gcKids[0].address() == gcChild0.address() &&
      gcKids[1].address() == gcChild1.address(),
  )

  // (String,String,bool,bool)->Array[Node] (Node.find_children): a parent with two "Coin*"-named
  // children plus one "Door" child; find_children("Coin*", "", recursive=true, owned=false) returns
  // the two coins in tree order. Exercises ptrcallWithTwoStringAndTwoBoolArgsRetTypedObjectList —
  // the
  // PT_STRING x2 + PT_BOOL x2 arg layout feeding the same Array read-back. owned=false so the
  // unowned
  // children still match; the "Door" child is filtered out by the name pattern. Identity fromHandle
  // to
  // compare addresses without importing the api layer. Plain Node is safe at init. Phase 2.7d-3.
  val fcAddChildBind = ObjectCalls.getMethodBind("Node", "add_child", 3863233950L)
  val fcSetNameBind = ObjectCalls.getMethodBind("Node", "set_name", 3304788590L)
  val fcParent = ObjectCalls.constructObject("Node")
  val fcCoin0 = ObjectCalls.constructObject("Node")
  ObjectCalls.ptrcallWithStringNameArg(fcSetNameBind, fcCoin0, "Coin0")
  val fcCoin1 = ObjectCalls.constructObject("Node")
  ObjectCalls.ptrcallWithStringNameArg(fcSetNameBind, fcCoin1, "Coin1")
  val fcDoor = ObjectCalls.constructObject("Node")
  ObjectCalls.ptrcallWithStringNameArg(fcSetNameBind, fcDoor, "Door")
  ObjectCalls.ptrcallWithObjectBoolLongArgs(fcAddChildBind, fcParent, fcCoin0, false, 0L)
  ObjectCalls.ptrcallWithObjectBoolLongArgs(fcAddChildBind, fcParent, fcCoin1, false, 0L)
  ObjectCalls.ptrcallWithObjectBoolLongArgs(fcAddChildBind, fcParent, fcDoor, false, 0L)
  val fcFound =
    ObjectCalls.ptrcallWithTwoStringAndTwoBoolArgsRetTypedObjectList(
      ObjectCalls.getMethodBind("Node", "find_children", 2560337219L),
      fcParent,
      "Coin*",
      "",
      true,
      false,
    ) {
      if (it.address() == 0L) null else it
    }
  check(
    "typed-object-array-ret(find_children(Coin*)==[c0,c1])",
    fcFound.size == 2 &&
      fcFound[0].address() == fcCoin0.address() &&
      fcFound[1].address() == fcCoin1.address(),
  )

  // Variant (scalar) return (Phase 2.7e). StringName-arg getter: Object.set_meta("kparam", 1234)
  // then get_meta("kparam") via ptrcallWithStringNameArgRetVariantScalar — metadata is stored on
  // the Object directly (no tree/RenderingServer dependency), and the Variant call honours the
  // default 2nd arg, so a 1-arg invocation round-trips the int. No-arg getter: a plain Node's
  // get_node_rpc_config() is an empty Dictionary -> scalar decode surfaces null (confirms the
  // no-arg routing + correct null, no crash). Both ride on the device-proven Object-call decode.
  val varMetaObj = ObjectCalls.constructObject("Node")
  ObjectCalls.callWithVariantArgs(
    ObjectCalls.getMethodBind("Object", "set_meta", 3776071444L),
    varMetaObj,
    listOf("kparam", 1234L),
  )
  val varMeta =
    ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(
      ObjectCalls.getMethodBind("Object", "get_meta", 3990617847L),
      varMetaObj,
      "kparam",
    )
  check("variant-scalar-ret(get_meta==1234)", varMeta == 1234L)
  val varRpcCfg =
    ObjectCalls.ptrcallNoArgsRetVariantScalar(
      ObjectCalls.getMethodBind("Node", "get_node_rpc_config", 1214101251L),
      varMetaObj,
    )
  check("variant-scalar-ret(get_node_rpc_config==null)", varRpcCfg == null)

  // Arg-bearing String return (Phase 2.7f-1). Node.atr(message, context) translates a message;
  // with no TranslationServer domain set it returns the message unchanged, so atr("KanamaAtr","")
  // == "KanamaAtr". Exercises ptrcallWithStringAndStringNameArgRetString (String + StringName args
  // -> STRING return decode) through the same Object-call path. Plain Node, no tree dependency.
  val atrNode = ObjectCalls.constructObject("Node")
  val atrBack =
    ObjectCalls.ptrcallWithStringAndStringNameArgRetString(
      ObjectCalls.getMethodBind("Node", "atr", 3344478075L),
      atrNode,
      "KanamaAtr",
      "",
    )
  check("string-arg-ret(atr==KanamaAtr)", atrBack == "KanamaAtr")

  // Arg-bearing NodePath return (Phase 2.7f-2). Build parent + a named child, then
  // parent.get_path_to(child, false) == NodePath("KPathTarget") — exercises the new NODE_PATH
  // return decode (Object + bool args -> Object-call -> String(from: NodePath) -> NodePath).
  val pathParent = ObjectCalls.constructObject("Node")
  val pathChild = ObjectCalls.constructObject("Node")
  ObjectCalls.ptrcallWithStringNameArg(
    ObjectCalls.getMethodBind("Node", "set_name", 3304788590L),
    pathChild,
    "KPathTarget",
  )
  ObjectCalls.ptrcallWithObjectBoolLongArgs(
    ObjectCalls.getMethodBind("Node", "add_child", 3863233950L),
    pathParent,
    pathChild,
    false,
    0L,
  )
  val pathTo =
    ObjectCalls.ptrcallWithObjectAndBoolArgRetNodePath(
      ObjectCalls.getMethodBind("Node", "get_path_to", 498846349L),
      pathParent,
      pathChild,
      false,
    )
  check("nodepath-arg-ret(get_path_to==KPathTarget)", pathTo == NodePath("KPathTarget"))

  // Arg-bearing StringName return (Phase 2.7f-2). A fresh AnimationPlayer has no next-animation
  // queued, so animation_get_next("none") == "" — exercises the new STRING_NAME return decode
  // (StringName arg -> Object-call -> String(from: StringName), empty path) without crashing.
  val animPlayer = requireObject("AnimationPlayer")
  if (animPlayer.address() != 0L) {
    val animNext =
      ObjectCalls.ptrcallWithStringNameArgRetStringName(
        ObjectCalls.getMethodBind("AnimationPlayer", "animation_get_next", 1965194235L),
        animPlayer,
        "none",
      )
    check("stringname-arg-ret(animation_get_next==\"\")", animNext == "")
  } else check("stringname-arg-ret(AnimationPlayer.animation_get_next) (instance absent)", false)

  // task 100 (parcel 1) — String / StringName / NodePath returns on arg-bearing shapes through the
  // generated UTF-8 read-back (ptrcallRetUtf8 over kanama_ios_godot_ptrcall_ret_utf8). One row per
  // return builtin, plus a >1 KiB String that must come back whole through the C-side pending slot
  // (the inline buffer is 1 KiB). StreamPeerBuffer round-trips exactly the bytes put in, so the
  // rows assert equality, not just non-emptiness; the multi-byte character checks the length is
  // counted in UTF-8 bytes. get_utf8_string CONSUMES the stream — a second invocation would read
  // nothing — so the row also proves the return is captured in one call.
  val utf8Short = "KanamaUtf8 \u00e9"
  val utf8Peer = ObjectCalls.constructObject("StreamPeerBuffer")
  ObjectCalls.callWithVariantArgs(
    ObjectCalls.getMethodBind("StreamPeer", "put_utf8_string", 83702148L),
    utf8Peer,
    listOf(utf8Short),
  )
  ObjectCalls.callWithVariantArgs(
    ObjectCalls.getMethodBind("StreamPeerBuffer", "seek", 1286410249L),
    utf8Peer,
    listOf(0),
  )
  val utf8ShortBack =
    ObjectCalls.ptrcallWithIntArgRetString(
      ObjectCalls.getMethodBind("StreamPeer", "get_utf8_string", 2309358862L),
      utf8Peer,
      -1,
    )
  check("utf8-ret(get_utf8_string short==put)", utf8ShortBack == utf8Short)
  ObjectCalls.destroyObject(utf8Peer)

  val utf8Long = buildString {
    repeat(300) { append("Kanama").append(it.toString().padStart(4, '0')) }
  }
  val utf8LongPeer = ObjectCalls.constructObject("StreamPeerBuffer")
  ObjectCalls.callWithVariantArgs(
    ObjectCalls.getMethodBind("StreamPeer", "put_utf8_string", 83702148L),
    utf8LongPeer,
    listOf(utf8Long),
  )
  ObjectCalls.callWithVariantArgs(
    ObjectCalls.getMethodBind("StreamPeerBuffer", "seek", 1286410249L),
    utf8LongPeer,
    listOf(0),
  )
  val utf8LongBack =
    ObjectCalls.ptrcallWithIntArgRetString(
      ObjectCalls.getMethodBind("StreamPeer", "get_utf8_string", 2309358862L),
      utf8LongPeer,
      -1,
    )
  check("utf8-ret(get_utf8_string 3000B==put, pending slot)", utf8LongBack == utf8Long)
  // The drained-slot assertion that used to follow this row is a fault probe (it reaches the
  // shim's `pending-protocol` guard by design): it now runs in runFaultProbes as
  // `fault-probe(pending-protocol g_pending_utf8 drained: utf8-ret)`.
  ObjectCalls.destroyObject(utf8LongPeer)

  // StringName return with an int arg: Skin.set_bind_name(0, "KBind") then get_bind_name(0).
  val utf8Skin = ObjectCalls.constructObject("Skin")
  ObjectCalls.callWithVariantArgs(
    ObjectCalls.getMethodBind("Skin", "set_bind_count", 1286410249L),
    utf8Skin,
    listOf(1),
  )
  ObjectCalls.callWithVariantArgs(
    ObjectCalls.getMethodBind("Skin", "set_bind_name", 3780747571L),
    utf8Skin,
    listOf(0, "KBind"),
  )
  val utf8Bind =
    ObjectCalls.ptrcallWithIntArgRetStringName(
      ObjectCalls.getMethodBind("Skin", "get_bind_name", 659327637L),
      utf8Skin,
      0,
    )
  check("utf8-ret(Skin.get_bind_name(0)==KBind)", utf8Bind == "KBind")
  ObjectCalls.destroyObject(utf8Skin)

  // NodePath return with an int arg: Animation.add_track(VALUE) + track_set_path(0, path), then
  // track_get_path(0) must re-wrap the decoded path (the Variant call coerces String -> NodePath).
  val utf8Anim = ObjectCalls.constructObject("Animation")
  ObjectCalls.callWithVariantArgs(
    ObjectCalls.getMethodBind("Animation", "add_track", 3843682357L),
    utf8Anim,
    listOf(0L, -1),
  )
  ObjectCalls.callWithVariantArgs(
    ObjectCalls.getMethodBind("Animation", "track_set_path", 2761262315L),
    utf8Anim,
    listOf(0, "KTrack:position"),
  )
  val utf8Path =
    ObjectCalls.ptrcallWithIntArgRetNodePath(
      ObjectCalls.getMethodBind("Animation", "track_get_path", 408788394L),
      utf8Anim,
      0,
    )
  check(
    "utf8-ret(Animation.track_get_path(0)==KTrack:position)",
    utf8Path == NodePath("KTrack:position"),
  )
  ObjectCalls.destroyObject(utf8Anim)

  // task 100 (parcel 2) — Variant-scalar returns on arg-bearing shapes through the generated
  // ptrcall route (ptrcallRetVariantScalar over kanama_ios_godot_ptrcall_ret_variant_scalar).
  // StreamPeerBuffer.put_var / get_var(allow_objects) round-trips one Variant per row: int, float,
  // bool, a short String and a 3000-byte String (parked C-side, drained whole), then null. Each
  // get_var consumes the stream, so a re-issued call would read nothing.
  fun variantRoundTrip(value: Any?): Any? {
    val peer = ObjectCalls.constructObject("StreamPeerBuffer")
    ObjectCalls.callWithVariantArgs(
      ObjectCalls.getMethodBind("StreamPeer", "put_var", 738511890L),
      peer,
      listOf(value, false),
    )
    ObjectCalls.callWithVariantArgs(
      ObjectCalls.getMethodBind("StreamPeerBuffer", "seek", 1286410249L),
      peer,
      listOf(0),
    )
    val back =
      ObjectCalls.ptrcallWithBoolArgRetVariantScalar(
        ObjectCalls.getMethodBind("StreamPeer", "get_var", 3442865206L),
        peer,
        false,
      )
    ObjectCalls.destroyObject(peer)
    return back
  }
  check("variant-ret(get_var int==42)", variantRoundTrip(42L) == 42L)
  check("variant-ret(get_var float==2.5)", variantRoundTrip(2.5) == 2.5)
  check("variant-ret(get_var bool==true)", variantRoundTrip(true) == true)
  check(
    "variant-ret(get_var short string)",
    variantRoundTrip("KanamaVar \u00e9") == "KanamaVar \u00e9",
  )
  val variantLong = buildString {
    repeat(300) { append("KVar").append(it.toString().padStart(6, '0')) }
  }
  check(
    "variant-ret(get_var 3000B string, pending slot)",
    variantRoundTrip(variantLong) == variantLong,
  )
  // Drained-slot assertion moved to runFaultProbes as
  // `fault-probe(pending-protocol g_pending_utf8 drained: variant-ret)` — it is a fault probe.
  check("variant-ret(get_var null==null)", variantRoundTrip(null) == null)

  // task 100 (parcel 3) — Packed*Array returns on arg-bearing shapes through the generated
  // read-back (ptrcallRet<Kind> over kanama_ios_godot_ptrcall_ret_packed). AStar2D with two
  // connected points: get_point_ids() (PackedInt64Array, no-arg — previously unwired),
  // get_point_connections(1) (int64 arg), get_point_path(1, 2, false) (PackedVector2Array).
  // Crypto.generate_random_bytes(3000): PackedByteArray longer than the 256-element inline
  // capacity, parked C-side and drained whole. Curve3D: get_baked_points() (PackedVector3Array).
  val packedAstar = ObjectCalls.constructObject("AStar2D")
  ObjectCalls.callWithVariantArgs(
    ObjectCalls.getMethodBind("AStar2D", "add_point", 4074201818L),
    packedAstar,
    listOf(1L, Vector2(0.0, 0.0), 1.0),
  )
  ObjectCalls.callWithVariantArgs(
    ObjectCalls.getMethodBind("AStar2D", "add_point", 4074201818L),
    packedAstar,
    listOf(2L, Vector2(3.0, 4.0), 1.0),
  )
  ObjectCalls.callWithVariantArgs(
    ObjectCalls.getMethodBind("AStar2D", "connect_points", 3710494224L),
    packedAstar,
    listOf(1L, 2L, true),
  )
  val packedIds =
    ObjectCalls.ptrcallNoArgsRetPackedInt64List(
      ObjectCalls.getMethodBind("AStar2D", "get_point_ids", 3851388692L),
      packedAstar,
    )
  check("packed-ret(AStar2D.get_point_ids==[1,2])", packedIds.sorted() == listOf(1L, 2L))
  val packedConn =
    ObjectCalls.ptrcallWithLongArgRetPackedInt64List(
      ObjectCalls.getMethodBind("AStar2D", "get_point_connections", 2865087369L),
      packedAstar,
      1L,
    )
  check("packed-ret(AStar2D.get_point_connections(1)==[2])", packedConn == listOf(2L))
  val packedPath =
    ObjectCalls.ptrcallWithTwoLongAndBoolArgsRetPackedVector2List(
      ObjectCalls.getMethodBind("AStar2D", "get_point_path", 3427490392L),
      packedAstar,
      1L,
      2L,
      false,
    )
  check(
    "packed-ret(AStar2D.get_point_path(1,2)==[(0,0),(3,4)])",
    packedPath == listOf(Vector2(0.0, 0.0), Vector2(3.0, 4.0)),
  )
  ObjectCalls.destroyObject(packedAstar)

  val packedCrypto = ObjectCalls.constructObject("Crypto")
  val packedBytes =
    ObjectCalls.ptrcallWithIntArgRetByteArray(
      ObjectCalls.getMethodBind("Crypto", "generate_random_bytes", 47165747L),
      packedCrypto,
      3000,
    )
  check(
    "packed-ret(Crypto.generate_random_bytes(3000).size==3000, pending slot)",
    packedBytes.size == 3000,
  )
  check("packed-ret(random bytes not all zero)", packedBytes.any { it != 0.toByte() })
  // Drained-slot assertion moved to runFaultProbes as
  // `fault-probe(pending-protocol g_pending_packed drained: packed-ret)` — it is a fault probe.
  ObjectCalls.destroyObject(packedCrypto)

  val packedCurve = ObjectCalls.constructObject("Curve3D")
  ObjectCalls.callWithVariantArgs(
    ObjectCalls.getMethodBind("Curve3D", "add_point", 2931053748L),
    packedCurve,
    listOf(Vector3(0.0, 0.0, 0.0), Vector3(0.0, 0.0, 0.0), Vector3(0.0, 0.0, 0.0), -1),
  )
  ObjectCalls.callWithVariantArgs(
    ObjectCalls.getMethodBind("Curve3D", "add_point", 2931053748L),
    packedCurve,
    listOf(Vector3(1.0, 2.0, 3.0), Vector3(0.0, 0.0, 0.0), Vector3(0.0, 0.0, 0.0), -1),
  )
  val packedBaked =
    ObjectCalls.ptrcallNoArgsRetPackedVector3List(
      ObjectCalls.getMethodBind("Curve3D", "get_baked_points", 497664490L),
      packedCurve,
    )
  fun near(a: Vector3, b: Vector3): Boolean =
    kotlin.math.abs(a.x - b.x) < 1e-3f &&
      kotlin.math.abs(a.y - b.y) < 1e-3f &&
      kotlin.math.abs(a.z - b.z) < 1e-3f
  check(
    "packed-ret(Curve3D.get_baked_points first==(0,0,0), last==(1,2,3))",
    packedBaked.size >= 2 &&
      near(packedBaked.first(), Vector3(0.0, 0.0, 0.0)) &&
      near(packedBaked.last(), Vector3(1.0, 2.0, 3.0)),
  )
  ObjectCalls.destroyObject(packedCurve)

  // task 100 (parcel 6) — Dictionary / Array returns on arg-bearing shapes through the generated
  // container blob (ptrcallRetDictionary / ptrcallRetArray / ptrcallRetDictionaryList over
  // kanama_ios_godot_ptrcall_ret_container_blob). Time / OS / ClassDB singletons and a
  // StreamPeerBuffer only — nothing that needs the scene tree or the render / physics servers.
  val containerTime = requireSingleton("Time")
  if (containerTime.address() != 0L) {
    val containerEpoch =
      ObjectCalls.ptrcallWithLongArgRetDictionary(
        ObjectCalls.getMethodBind("Time", "get_datetime_dict_from_unix_time", 3485342025L),
        containerTime,
        0L,
      )
    check(
      "container-ret(Time.get_datetime_dict_from_unix_time(0)==1970-01-01 00:00)",
      containerEpoch["year"] == 1970L &&
        containerEpoch["month"] == 1L &&
        containerEpoch["day"] == 1L &&
        containerEpoch["hour"] == 0L &&
        containerEpoch["minute"] == 0L,
    )
  } else check("container-ret(Time.get_datetime_dict_from_unix_time) (singleton absent)", false)
  val containerOs = requireSingleton("OS")
  if (containerOs.address() != 0L) {
    val containerMem =
      ObjectCalls.ptrcallNoArgsRetDictionary(
        ObjectCalls.getMethodBind("OS", "get_memory_info", 3102165223L),
        containerOs,
      )
    check(
      "container-ret(OS.get_memory_info physical>0)",
      (containerMem["physical"] as? Long ?: 0L) > 0L,
    )
  } else check("container-ret(OS.get_memory_info) (singleton absent)", false)

  // Generic Array with an int arg: three bytes in, get_data(3) -> [OK, PackedByteArray]; the packed
  // element is not a blob scalar, so it surfaces null and the list keeps its two slots.
  val containerPeer = ObjectCalls.constructObject("StreamPeerBuffer")
  repeat(3) {
    ObjectCalls.callWithVariantArgs(
      ObjectCalls.getMethodBind("StreamPeer", "put_u8", 1286410249L),
      containerPeer,
      listOf(7),
    )
  }
  ObjectCalls.callWithVariantArgs(
    ObjectCalls.getMethodBind("StreamPeerBuffer", "seek", 1286410249L),
    containerPeer,
    listOf(0),
  )
  val containerData =
    ObjectCalls.ptrcallWithIntArgRetArray(
      ObjectCalls.getMethodBind("StreamPeer", "get_data", 1171824711L),
      containerPeer,
      3,
    )
  check(
    "container-ret(StreamPeer.get_data(3)==[OK, packed])",
    containerData.size == 2 && containerData[0] == 0L,
  )
  ObjectCalls.destroyObject(containerPeer)

  // Array[Dictionary] far larger than the 4 KiB inline buffer, with nested "args" Arrays and a
  // nested "return" Dictionary per method: proves the pending drain and the recursive records.
  val containerClassDb = requireSingleton("ClassDB")
  if (containerClassDb.address() != 0L) {
    val containerMethods =
      ObjectCalls.ptrcallWithStringNameAndBoolArgRetDictionaryList(
        ObjectCalls.getMethodBind("ClassDB", "class_get_method_list", 3504980660L),
        containerClassDb,
        "Node",
        false,
      )
    val containerAddChild = containerMethods.firstOrNull { it["name"] == "add_child" }
    check(
      "container-ret(ClassDB.class_get_method_list(Node) add_child args+return, pending slot)",
      containerMethods.size > 50 &&
        containerAddChild != null &&
        (containerAddChild["args"] as? List<*>)?.size == 3 &&
        ((containerAddChild["args"] as List<*>)[0] as? Map<*, *>)?.get("name") == "node" &&
        (containerAddChild["return"] as? Map<*, *>) != null,
    )
    // Drained-slot assertion moved to runFaultProbes as
    // `fault-probe(pending-protocol g_pending_container_blob drained: container-ret)`.
    val containerSignal =
      ObjectCalls.ptrcallWithTwoStringNameArgsRetDictionary(
        ObjectCalls.getMethodBind("ClassDB", "class_get_signal", 3061114238L),
        containerClassDb,
        "Node",
        "ready",
      )
    check(
      "container-ret(ClassDB.class_get_signal(Node, ready).name==ready)",
      containerSignal["name"] == "ready",
    )
  } else
    check("container-ret(ClassDB.class_get_method_list/class_get_signal) (singleton absent)", false)

  // task 100 (parcel 4) — Packed*Array ARGUMENTS through the generated BUILD-tagged descriptors
  // (ObjectCalls.pack<Kind>Desc -> kanama_ios_build_packed_arg -> ptrcall). Each row sets an array
  // through the GENERATED setter and reads it back through a read-back helper, asserting equality
  // element for element. The Vector2 row uses 300 points and the int64 row 1000 ids, so both also
  // cross the 256-element inline read-back capacity; the string row carries a non-ASCII and an
  // empty element to pin the blob's byte-length framing.
  val argPoly = ObjectCalls.constructObject("Polygon2D")
  val argPoints = List(300) { Vector2(it.toDouble(), (it * 2).toDouble()) }
  ObjectCalls.ptrcallWithPackedVector2ListArg(
    ObjectCalls.getMethodBind("Polygon2D", "set_polygon", 1509147220L),
    argPoly,
    argPoints,
  )
  val argPointsBack =
    ObjectCalls.ptrcallNoArgsRetPackedVector2List(
      ObjectCalls.getMethodBind("Polygon2D", "get_polygon", 2961356807L),
      argPoly,
    )
  check("packed-arg(Polygon2D.set_polygon 300 Vector2 round-trip)", argPointsBack == argPoints)
  ObjectCalls.destroyObject(argPoly)

  val argPeer = ObjectCalls.constructObject("StreamPeerBuffer")
  val argBytes = ByteArray(700) { ((it * 7) and 0xff).toByte() }
  val argPutErr =
    ObjectCalls.ptrcallWithByteArrayArgRetLong(
      ObjectCalls.getMethodBind("StreamPeer", "put_data", 680677267L),
      argPeer,
      argBytes,
    )
  val argBytesBack =
    ObjectCalls.ptrcallNoArgsRetByteArray(
      ObjectCalls.getMethodBind("StreamPeerBuffer", "get_data_array", 2362200018L),
      argPeer,
    )
  check(
    "packed-arg(StreamPeer.put_data 700 B == get_data_array, Error.OK)",
    argPutErr == 0L && argBytesBack.contentEquals(argBytes),
  )
  ObjectCalls.destroyObject(argPeer)

  val argNav = ObjectCalls.constructObject("NavigationPolygon")
  val argIdx = listOf(3, 1, 4, 1, 5, 9, 2, 6)
  ObjectCalls.ptrcallWithPackedInt32ListArg(
    ObjectCalls.getMethodBind("NavigationPolygon", "add_polygon", 3614634198L),
    argNav,
    argIdx,
  )
  val argIdxBack =
    ObjectCalls.ptrcallWithIntArgRetPackedInt32List(
      ObjectCalls.getMethodBind("NavigationPolygon", "get_polygon", 3668444399L),
      argNav,
      0,
    )
  check("packed-arg(NavigationPolygon.add_polygon int32 round-trip)", argIdxBack == argIdx)
  ObjectCalls.destroyObject(argNav)

  // NavigationMesh, not a Shape3D: every Shape3D constructor calls PhysicsServer3D::shape_create
  // and
  // the physics servers do not exist yet when this self-test runs (scene-level extension init is
  // before Main::setup2 creates them) — a ConvexPolygonShape3D here segfaulted on the iPhone 12
  // (2026-09-10). NavigationMesh is a plain data resource with the same PackedVector3Array shape.
  val argNavMesh = ObjectCalls.constructObject("NavigationMesh")
  val argVerts =
    listOf(
      Vector3(0.0, 0.0, 0.0),
      Vector3(1.0, 0.0, 0.0),
      Vector3(0.0, 1.0, 0.0),
      Vector3(0.0, 0.0, 1.0),
    )
  ObjectCalls.ptrcallWithPackedVector3ListArg(
    ObjectCalls.getMethodBind("NavigationMesh", "set_vertices", 334873810L),
    argNavMesh,
    argVerts,
  )
  val argVertsBack =
    ObjectCalls.ptrcallNoArgsRetPackedVector3List(
      ObjectCalls.getMethodBind("NavigationMesh", "get_vertices", 497664490L),
      argNavMesh,
    )
  check("packed-arg(NavigationMesh.set_vertices Vector3 round-trip)", argVertsBack == argVerts)
  ObjectCalls.destroyObject(argNavMesh)

  val argParticles = ObjectCalls.constructObject("CPUParticles2D")
  val argColors = listOf(Color(1.0, 0.0, 0.0, 1.0), Color(0.0, 0.5, 0.0, 0.25))
  ObjectCalls.ptrcallWithPackedColorListArg(
    ObjectCalls.getMethodBind("CPUParticles2D", "set_emission_colors", 3546319833L),
    argParticles,
    argColors,
  )
  val argColorsBack =
    ObjectCalls.ptrcallNoArgsRetPackedColorList(
      ObjectCalls.getMethodBind("CPUParticles2D", "get_emission_colors", 1392750486L),
      argParticles,
    )
  check(
    "packed-arg(CPUParticles2D.set_emission_colors Color round-trip)",
    argColorsBack == argColors,
  )
  ObjectCalls.destroyObject(argParticles)

  val argNavRes = ObjectCalls.constructObject("NavigationPathQueryResult2D")
  val argIds = List(1000) { it.toLong() * 1_000_000_007L }
  ObjectCalls.ptrcallWithPackedInt64ListArg(
    ObjectCalls.getMethodBind("NavigationPathQueryResult2D", "set_path_owner_ids", 3709968205L),
    argNavRes,
    argIds,
  )
  val argIdsBack =
    ObjectCalls.ptrcallNoArgsRetPackedInt64List(
      ObjectCalls.getMethodBind("NavigationPathQueryResult2D", "get_path_owner_ids", 235988956L),
      argNavRes,
    )
  check(
    "packed-arg(NavigationPathQueryResult2D.set_path_owner_ids 1000 int64 round-trip)",
    argIdsBack == argIds,
  )
  ObjectCalls.destroyObject(argNavRes)

  val argAcc = ObjectCalls.constructObject("GLTFAccessor")
  val argMin = listOf(-1.5, 2.25, 1e10)
  ObjectCalls.ptrcallWithPackedFloat64ListArg(
    ObjectCalls.getMethodBind("GLTFAccessor", "set_min", 2576592201L),
    argAcc,
    argMin,
  )
  val argMinBack =
    ObjectCalls.ptrcallNoArgsRetPackedFloat64List(
      ObjectCalls.getMethodBind("GLTFAccessor", "get_min", 547233126L),
      argAcc,
    )
  check("packed-arg(GLTFAccessor.set_min float64 round-trip)", argMinBack == argMin)
  ObjectCalls.destroyObject(argAcc)

  val argWs = ObjectCalls.constructObject("WebSocketPeer")
  val argProtos = listOf("kanama", "g\u00fcltig", "")
  ObjectCalls.ptrcallWithPackedStringListArg(
    ObjectCalls.getMethodBind("WebSocketPeer", "set_supported_protocols", 4015028928L),
    argWs,
    argProtos,
  )
  val argProtosBack =
    ObjectCalls.ptrcallNoArgsRetPackedStringList(
      ObjectCalls.getMethodBind("WebSocketPeer", "get_supported_protocols", 1139954409L),
      argWs,
    )
  check(
    "packed-arg(WebSocketPeer.set_supported_protocols strings round-trip)",
    argProtosBack == argProtos,
  )
  ObjectCalls.destroyObject(argWs)

  // task 100 (parcel 5) — string-list / typed-Array returns on arg-bearing shapes through the
  // generated blob read-back (ptrcallRetTyped<Kind>List / ptrcallRetPackedStringList over
  // kanama_ios_godot_ptrcall_ret_array_blob). ClassDB.get_inheriters_from_class(StringName):
  // PackedStringArray with an arg (short) and, for "Object", thousands of names — far past the 4
  // KiB
  // inline buffer, so it proves the pending-blob drain; TileMapLayer.set_cell + get_used_cells /
  // get_used_cells_by_id (Array[Vector2i], no-arg and int+Vector2i+int args); Skeleton3D bone meta
  // (Array[StringName] with an int arg); Geometry3D.build_box_planes (Array[Plane] with a Vector3
  // arg); GridMap.set_cell_item + get_used_cells (Array[Vector3i]).
  val arrayClassDb = requireSingleton("ClassDB")
  if (arrayClassDb.address() != 0L) {
    val arrayNode2dHeirs =
      ObjectCalls.ptrcallWithStringNameArgRetPackedStringList(
        ObjectCalls.getMethodBind("ClassDB", "get_inheriters_from_class", 1761182771L),
        arrayClassDb,
        "Node2D",
      )
    check(
      "array-ret(ClassDB.get_inheriters_from_class(Node2D) has Sprite2D)",
      "Sprite2D" in arrayNode2dHeirs,
    )
    val arrayObjectHeirs =
      ObjectCalls.ptrcallWithStringNameArgRetPackedStringList(
        ObjectCalls.getMethodBind("ClassDB", "get_inheriters_from_class", 1761182771L),
        arrayClassDb,
        "Object",
      )
    check(
      "array-ret(ClassDB.get_inheriters_from_class(Object) >500 names, pending blob)",
      arrayObjectHeirs.size > 500 && "Node" in arrayObjectHeirs && "AStar2D" in arrayObjectHeirs,
    )
    // Drained-slot assertion moved to runFaultProbes as
    // `fault-probe(pending-protocol g_pending_blob drained: array-ret)` — it is a fault probe.
  } else check("array-ret(ClassDB.get_inheriters_from_class) (singleton absent)", false)

  val arrayTiles = ObjectCalls.constructObject("TileMapLayer")
  ObjectCalls.callWithVariantArgs(
    ObjectCalls.getMethodBind("TileMapLayer", "set_cell", 2428518503L),
    arrayTiles,
    listOf(Vector2i(2, 3), 0, Vector2i(0, 0), 0),
  )
  val arrayUsed =
    ObjectCalls.ptrcallNoArgsRetVector2iList(
      ObjectCalls.getMethodBind("TileMapLayer", "get_used_cells", 3995934104L),
      arrayTiles,
    )
  check("array-ret(TileMapLayer.get_used_cells==[(2,3)])", arrayUsed == listOf(Vector2i(2, 3)))
  val arrayUsedById =
    ObjectCalls.ptrcallWithIntVector2iAndIntArgsRetVector2iList(
      ObjectCalls.getMethodBind("TileMapLayer", "get_used_cells_by_id", 4175304538L),
      arrayTiles,
      0,
      Vector2i(0, 0),
      -1,
    )
  check(
    "array-ret(TileMapLayer.get_used_cells_by_id(0,(0,0),-1)==[(2,3)])",
    arrayUsedById == listOf(Vector2i(2, 3)),
  )
  ObjectCalls.destroyObject(arrayTiles)

  val arraySkel = ObjectCalls.constructObject("Skeleton3D")
  ObjectCalls.callWithVariantArgs(
    ObjectCalls.getMethodBind("Skeleton3D", "add_bone", 1597066294L),
    arraySkel,
    listOf("KBone"),
  )
  ObjectCalls.callWithVariantArgs(
    ObjectCalls.getMethodBind("Skeleton3D", "set_bone_meta", 702482756L),
    arraySkel,
    listOf(0, "kmeta", 1L),
  )
  val arrayMeta =
    ObjectCalls.ptrcallWithIntArgRetStringNameList(
      ObjectCalls.getMethodBind("Skeleton3D", "get_bone_meta_list", 663333327L),
      arraySkel,
      0,
    )
  check("array-ret(Skeleton3D.get_bone_meta_list(0)==[kmeta])", arrayMeta == listOf("kmeta"))
  ObjectCalls.destroyObject(arraySkel)

  val arrayGeo = requireSingleton("Geometry3D")
  if (arrayGeo.address() != 0L) {
    val arrayPlanes =
      ObjectCalls.ptrcallWithVector3ArgRetPlaneList(
        ObjectCalls.getMethodBind("Geometry3D", "build_box_planes", 3622277145L),
        arrayGeo,
        Vector3(1.0, 2.0, 3.0),
      )
    check("array-ret(Geometry3D.build_box_planes has 6 planes)", arrayPlanes.size == 6)
  } else check("array-ret(Geometry3D.build_box_planes) (singleton absent)", false)

  // GridMap is the only class returning Array[Vector3i] without an Array argument, but
  // set_cell_item creates the cell's octant with PhysicsServer3D bodies, and the physics servers
  // do not exist yet at scene-level extension init (iPhone 12, 2026-09-10: SIGSEGV at address 0 in
  // GridMap::set_cell_item). Reads on an EMPTY GridMap create no octant: they exercise the
  // Array[Vector3i] encoder (count 0) and the Vector3i ARGUMENT cell without touching physics. The
  // Vector3i element decode itself is 3 x i32LE, the same stride logic as the Vector2i row above.
  val arrayGrid = requireObject("GridMap")
  if (arrayGrid.address() != 0L) {
    val arrayCells =
      ObjectCalls.ptrcallNoArgsRetVector3iList(
        ObjectCalls.getMethodBind("GridMap", "get_used_cells", 3995934104L),
        arrayGrid,
      )
    check("array-ret(empty GridMap.get_used_cells==[])", arrayCells.isEmpty())
    val arrayOctantCells =
      ObjectCalls.ptrcallWithVector3iArgRetVector3iList(
        ObjectCalls.getMethodBind("GridMap", "get_used_cells_in_octant", 2658725580L),
        arrayGrid,
        Vector3i(0, 0, 0),
      )
    check(
      "array-ret(empty GridMap.get_used_cells_in_octant((0,0,0))==[])",
      arrayOctantCells.isEmpty(),
    )
    ObjectCalls.destroyObject(arrayGrid)
  } else check("array-ret(GridMap.get_used_cells) (instance absent)", false)

  // task 100 (parcel 7) — Variant / Dictionary / Array ARGUMENTS through the generated helpers
  // (packVariantDesc / packDictionaryBlob / packArrayBlob; the dispatch boxes or rebuilds the Godot
  // value for the call and destroys it afterwards). ConfigFile and Expression are RefCounted,
  // AudioStreamWAV a data Resource, ProjectSettings a core singleton — none of them reach the
  // physics / navigation servers, which do not exist at scene-level init.
  val argCfg = ObjectCalls.constructObject("ConfigFile")
  fun cfgSet(key: String, value: Any?) =
    ObjectCalls.ptrcallWithTwoStringAndVariantArg(
      ObjectCalls.getMethodBind("ConfigFile", "set_value", 2504492430L),
      argCfg,
      "s",
      key,
      value,
    )
  fun cfgGet(key: String, default: Any?): Any? =
    ObjectCalls.ptrcallWithTwoStringAndVariantArgRetVariantScalar(
      ObjectCalls.getMethodBind("ConfigFile", "get_value", 89809366L),
      argCfg,
      "s",
      key,
      default,
    )
  fun cfgHas(key: String): Boolean =
    ObjectCalls.callWithVariantArgs(
      ObjectCalls.getMethodBind("ConfigFile", "has_section_key", 820780508L),
      argCfg,
      listOf("s", key),
    ) == true
  cfgSet("i", 42L)
  check("arg-variant(ConfigFile int==42)", cfgGet("i", null) == 42L)
  cfgSet("f", 2.5)
  check("arg-variant(ConfigFile float==2.5)", cfgGet("f", null) == 2.5)
  cfgSet("str", "Kanama \u00e9")
  check("arg-variant(ConfigFile string round-trips utf8)", cfgGet("str", null) == "Kanama \u00e9")
  cfgSet("b", true)
  check("arg-variant(ConfigFile bool==true)", cfgGet("b", null) == true)
  cfgSet("v", Vector2(1.0, 2.0))
  check("arg-variant(ConfigFile Vector2 round-trip)", cfgGet("v", null) == Vector2(1.0, 2.0))
  // A null Variant must arrive as nil: ConfigFile.set_value(null) erases the key.
  cfgSet("i", null)
  check(
    "arg-variant(null erases the key; default 7 returned)",
    !cfgHas("i") && cfgGet("i", 7L) == 7L,
  )
  // One level of Map / List inside a Variant: stored (a nil would have erased the key); the Variant
  // scalar decode surfaces containers as null on both backends, so read back through
  // has_section_key.
  cfgSet("m", mapOf("a" to 1L, "b" to "x"))
  check("arg-variant(Map inside a Variant is stored)", cfgHas("m"))
  cfgSet("l", listOf(1L, 2L, 3L))
  check("arg-variant(List inside a Variant is stored)", cfgHas("l"))
  ObjectCalls.destroyObject(argCfg)

  // Array argument whose element VALUE must reach the engine: Expression x + 1 with inputs [41].
  val argExpr = ObjectCalls.constructObject("Expression")
  val argParse =
    ObjectCalls.ptrcallWithStringAndPackedStringListArgRetLong(
      ObjectCalls.getMethodBind("Expression", "parse", 3069722906L),
      argExpr,
      "x + 1",
      listOf("x"),
    )
  val argResult =
    ObjectCalls.ptrcallWithArrayObjectTwoBoolArgsRetVariantScalar(
      ObjectCalls.getMethodBind("Expression", "execute", 3712471238L),
      argExpr,
      listOf(41L),
      MemorySegment.NULL,
      true,
      false,
    )
  check("arg-array(Expression.execute([41]) x + 1 == 42)", argParse == 0L && argResult == 42L)
  ObjectCalls.destroyObject(argExpr)

  // Dictionary argument read back through the parcel-6 Dictionary return.
  val argWav = ObjectCalls.constructObject("AudioStreamWAV")
  ObjectCalls.ptrcallWithDictionaryArg(
    ObjectCalls.getMethodBind("AudioStreamWAV", "set_tags", 4155329257L),
    argWav,
    mapOf("title" to "Kanama", "track" to 3L, "gain" to 0.5),
  )
  val argTags =
    ObjectCalls.ptrcallNoArgsRetDictionary(
      ObjectCalls.getMethodBind("AudioStreamWAV", "get_tags", 3102165223L),
      argWav,
    )
  check(
    "arg-dictionary(AudioStreamWAV.set_tags/get_tags round-trip)",
    argTags["title"] == "Kanama" &&
      argTags["track"] == 3L &&
      argTags["gain"] == 0.5 &&
      argTags.size == 3,
  )
  ObjectCalls.destroyObject(argWav)

  // String + Variant on a singleton: ProjectSettings.set_setting then has_setting.
  val argPs = requireSingleton("ProjectSettings")
  if (argPs.address() != 0L) {
    ObjectCalls.ptrcallWithStringAndVariantArg(
      ObjectCalls.getMethodBind("ProjectSettings", "set_setting", 402577236L),
      argPs,
      "kanama/selftest_parcel7",
      42L,
    )
    check(
      "arg-variant(ProjectSettings.set_setting stored)",
      ObjectCalls.callWithVariantArgs(
        ObjectCalls.getMethodBind("ProjectSettings", "has_setting", 3927539163L),
        argPs,
        listOf("kanama/selftest_parcel7"),
      ) == true,
    )
  } else check("arg-variant(ProjectSettings.set_setting) (singleton absent)", false)

  // task 100 (parcel 8) — typed-Array / Rect2i / Vector4 ARGUMENTS through the GENERATED helpers
  // (packTyped<Kind>ArrayDesc -> PT_TYPED_ARRAY_BLOB -> array_set_typed + tagged elements in the
  // dispatch). Every class below is a plain data holder, a server-free node or a core singleton:
  // no Shape*, physics body, GridMap cell or navigation region — the physics / navigation servers
  // do not exist at scene-init (two device crashes on 2026-09-10 taught that).
  // Array[RID] round trip on a RefCounted parameter bag; 1000 elements: the argument blob has no
  // inline cap, so a long array proves the length handling end to end.
  val typedPq = ObjectCalls.constructObject("PhysicsPointQueryParameters2D")
  val typedRids = List(1000) { RID(1000L + it) }
  ObjectCalls.ptrcallWithRIDListArg(
    ObjectCalls.getMethodBind("PhysicsPointQueryParameters2D", "set_exclude", 381264803L),
    typedPq,
    typedRids,
  )
  val typedRidsBack =
    ObjectCalls.ptrcallNoArgsRetRIDList(
      ObjectCalls.getMethodBind("PhysicsPointQueryParameters2D", "get_exclude", 3995934104L),
      typedPq,
    )
  check(
    "arg-typed(PhysicsPointQueryParameters2D.set_exclude 1000 RIDs round-trip)",
    typedRidsBack == typedRids,
  )
  ObjectCalls.destroyObject(typedPq)

  // Array[String] and Array[GLTFNode] (object elements; the Array is built untyped and the
  // setter's TypedArray<GLTFNode> conversion validates each element) on a GLTFState.
  // The GLTFNode handles are referenced only by the state's array: destroying the state frees
  // them, so they are not destroyed here (a second destroy would be a double free).
  val typedGltf = ObjectCalls.constructObject("GLTFState")
  ObjectCalls.ptrcallWithTypedStringListArg(
    ObjectCalls.getMethodBind("GLTFState", "set_unique_names", 381264803L),
    typedGltf,
    listOf("alpha", "b\u00eata", ""),
  )
  val typedNamesBack =
    ObjectCalls.ptrcallNoArgsRetTypedStringList(
      ObjectCalls.getMethodBind("GLTFState", "get_unique_names", 3995934104L),
      typedGltf,
    )
  check(
    "arg-typed(GLTFState.set_unique_names String round-trip)",
    typedNamesBack == listOf("alpha", "b\u00eata", ""),
  )
  val typedNodeA = ObjectCalls.constructObject("GLTFNode")
  val typedNodeB = ObjectCalls.constructObject("GLTFNode")
  ObjectCalls.ptrcallWithObjectListArg(
    ObjectCalls.getMethodBind("GLTFState", "set_nodes", 381264803L),
    typedGltf,
    listOf(RefCounted(GodotHandle(typedNodeA)), RefCounted(GodotHandle(typedNodeB))),
  )
  val typedNodesBack =
    ObjectCalls.ptrcallNoArgsRetTypedObjectList(
      ObjectCalls.getMethodBind("GLTFState", "get_nodes", 3995934104L),
      typedGltf,
    ) {
      RefCounted(GodotHandle(it))
    }
  check(
    "arg-typed(GLTFState.set_nodes Array[GLTFNode] round-trip by handle)",
    typedNodesBack.map { it.segment.address() } ==
      listOf(typedNodeA.address(), typedNodeB.address()),
  )
  ObjectCalls.destroyObject(typedGltf)

  // Array[NodePath] on a GLTFObjectModelProperty (data holder).
  val typedOmp = ObjectCalls.constructObject("GLTFObjectModelProperty")
  ObjectCalls.ptrcallWithNodePathListArg(
    ObjectCalls.getMethodBind("GLTFObjectModelProperty", "set_node_paths", 381264803L),
    typedOmp,
    listOf(NodePath("A/B"), NodePath("C:prop")),
  )
  val typedPathsBack =
    ObjectCalls.ptrcallNoArgsRetNodePathList(
      ObjectCalls.getMethodBind("GLTFObjectModelProperty", "get_node_paths", 3995934104L),
      typedOmp,
    )
  check(
    "arg-typed(GLTFObjectModelProperty.set_node_paths NodePath round-trip)",
    typedPathsBack == listOf(NodePath("A/B"), NodePath("C:prop")),
  )
  ObjectCalls.destroyObject(typedOmp)

  // Array[int] on an RDPipelineMultisampleState (a data object; no RenderingDevice involved).
  val typedMs = ObjectCalls.constructObject("RDPipelineMultisampleState")
  ObjectCalls.ptrcallWithTypedIntListArg(
    ObjectCalls.getMethodBind("RDPipelineMultisampleState", "set_sample_masks", 381264803L),
    typedMs,
    listOf(1L, 255L, -7L),
  )
  val typedMasksBack =
    ObjectCalls.ptrcallNoArgsRetLongList(
      ObjectCalls.getMethodBind("RDPipelineMultisampleState", "get_sample_masks", 3995934104L),
      typedMs,
    )
  check(
    "arg-typed(RDPipelineMultisampleState.set_sample_masks int round-trip)",
    typedMasksBack == listOf(1L, 255L, -7L),
  )
  ObjectCalls.destroyObject(typedMs)

  // Array[Plane]: the six planes of the unit cube -> Geometry3D.compute_convex_mesh_points gives
  // its 8 corners (Geometry3D is a core singleton, available at scene-init).
  val typedGeo = requireSingleton("Geometry3D")
  if (typedGeo.address() != 0L) {
    val typedCube =
      listOf(
        Plane(Vector3(1.0, 0.0, 0.0), 1.0),
        Plane(Vector3(-1.0, 0.0, 0.0), 1.0),
        Plane(Vector3(0.0, 1.0, 0.0), 1.0),
        Plane(Vector3(0.0, -1.0, 0.0), 1.0),
        Plane(Vector3(0.0, 0.0, 1.0), 1.0),
        Plane(Vector3(0.0, 0.0, -1.0), 1.0),
      )
    val typedCorners =
      ObjectCalls.ptrcallWithPlaneListArgRetPackedVector3List(
        ObjectCalls.getMethodBind("Geometry3D", "compute_convex_mesh_points", 1936902142L),
        typedGeo,
        typedCube,
      )
    check(
      "arg-typed(Geometry3D.compute_convex_mesh_points unit cube -> 8 corners)",
      typedCorners.size == 8,
    )
  } else check("arg-typed(Geometry3D.compute_convex_mesh_points) (singleton absent)", false)

  // Array[PackedVector2Array] (nested packed elements) on a NavigationMeshSourceGeometryData2D
  // (a data holder; nothing is baked, no NavigationServer call).
  val typedNav = ObjectCalls.constructObject("NavigationMeshSourceGeometryData2D")
  val typedOutlines =
    listOf(
      listOf(Vector2(0.0, 0.0), Vector2(1.0, 0.0), Vector2(1.0, 1.0)),
      listOf(Vector2(2.0, 2.0), Vector2(3.0, 2.0)),
    )
  ObjectCalls.ptrcallWithPackedVector2ListListArg(
    ObjectCalls.getMethodBind(
      "NavigationMeshSourceGeometryData2D",
      "set_traversable_outlines",
      381264803L,
    ),
    typedNav,
    typedOutlines,
  )
  val typedOutlinesBack =
    ObjectCalls.ptrcallNoArgsRetPackedVector2ListList(
      ObjectCalls.getMethodBind(
        "NavigationMeshSourceGeometryData2D",
        "get_traversable_outlines",
        3995934104L,
      ),
      typedNav,
    )
  check(
    "arg-typed(NavigationMeshSourceGeometryData2D.set_traversable_outlines nested round-trip)",
    typedOutlinesBack == typedOutlines,
  )
  ObjectCalls.destroyObject(typedNav)

  // Rect2i argument (4 x int32 POD) round trip on an AStarGrid2D (RefCounted, no server).
  val typedGrid = ObjectCalls.constructObject("AStarGrid2D")
  ObjectCalls.ptrcallWithRect2iArg(
    ObjectCalls.getMethodBind("AStarGrid2D", "set_region", 1763793166L),
    typedGrid,
    Rect2i(Vector2i(-2, 3), Vector2i(10, 20)),
  )
  val typedRegion =
    ObjectCalls.ptrcallNoArgsRetRect2i(
      ObjectCalls.getMethodBind("AStarGrid2D", "get_region", 410525958L),
      typedGrid,
    )
  check(
    "arg-rect2i(AStarGrid2D.set_region round-trip)",
    typedRegion == Rect2i(Vector2i(-2, 3), Vector2i(10, 20)),
  )
  ObjectCalls.destroyObject(typedGrid)

  // Vector4 argument + return (4 x real_t POD) round trip on a VisualShaderNodeVec4Parameter.
  val typedVec4 = ObjectCalls.constructObject("VisualShaderNodeVec4Parameter")
  ObjectCalls.ptrcallWithVector4Arg(
    ObjectCalls.getMethodBind("VisualShaderNodeVec4Parameter", "set_default_value", 643568085L),
    typedVec4,
    Vector4(1.0, 2.0, 3.0, 4.0),
  )
  val typedVec4Back =
    ObjectCalls.ptrcallNoArgsRetVector4(
      ObjectCalls.getMethodBind("VisualShaderNodeVec4Parameter", "get_default_value", 2435802345L),
      typedVec4,
    )
  check(
    "arg-vector4(VisualShaderNodeVec4Parameter.set_default_value round-trip)",
    typedVec4Back == Vector4(1.0, 2.0, 3.0, 4.0),
  )
  ObjectCalls.destroyObject(typedVec4)

  // task 100 (parcel 9) — typed-object-list RETURNS on every audited arg shape through the
  // GENERATED helpers (retTypedObjectList: the method runs ONCE, lists longer than the 64-handle
  // inline buffer drain from the C pending slot). RegEx / RegExMatch are pure data, InputMap and
  // TranslationServer are core singletons: nothing here needs a physics or navigation server.
  val p9Regex = ObjectCalls.constructObject("RegEx")
  ObjectCalls.callWithVariantArgs(
    ObjectCalls.getMethodBind("RegEx", "compile", 3565188097L),
    p9Regex,
    listOf("a", true),
  )
  val p9SearchAllBind = ObjectCalls.getMethodBind("RegEx", "search_all", 849021363L)
  val p9Short =
    ObjectCalls.ptrcallWithStringTwoIntArgsRetTypedObjectList(
      p9SearchAllBind,
      p9Regex,
      "banana",
      0,
      -1,
    ) {
      it
    }
  check(
    "ret-typed-object-list(RegEx.search_all 3 matches, inline buffer)",
    p9Short.size == 3 && p9Short.all { it.address() != 0L },
  )
  val p9Long =
    ObjectCalls.ptrcallWithStringTwoIntArgsRetTypedObjectList(
      p9SearchAllBind,
      p9Regex,
      "a".repeat(200),
      0,
      -1,
    ) {
      it
    }
  check(
    "ret-typed-object-list(RegEx.search_all 200 matches, pending slot)",
    p9Long.size == 200 && p9Long.map { it.address() }.toSet().size == 200,
  )
  ObjectCalls.destroyObject(p9Regex)
  // StringName arg on a singleton: InputMap.action_get_events returns the one event we added,
  // by handle identity.
  val p9InputMap = requireSingleton("InputMap")
  if (p9InputMap.address() != 0L) {
    ObjectCalls.callWithVariantArgs(
      ObjectCalls.getMethodBind("InputMap", "add_action", 1195233573L),
      p9InputMap,
      listOf("kanama_selftest_p9", 0.5),
    )
    val p9Event = ObjectCalls.constructObject("InputEventKey")
    ObjectCalls.callWithVariantArgs(
      ObjectCalls.getMethodBind("InputMap", "action_add_event", 518302593L),
      p9InputMap,
      listOf("kanama_selftest_p9", p9Event),
    )
    val p9Events =
      ObjectCalls.ptrcallWithStringNameArgRetTypedObjectList(
        ObjectCalls.getMethodBind("InputMap", "action_get_events", 689397652L),
        p9InputMap,
        "kanama_selftest_p9",
      ) {
        it
      }
    check(
      "ret-typed-object-list(InputMap.action_get_events by handle)",
      p9Events.size == 1 && p9Events[0].address() == p9Event.address(),
    )
    ObjectCalls.callWithVariantArgs(
      ObjectCalls.getMethodBind("InputMap", "erase_action", 3304788590L),
      p9InputMap,
      listOf("kanama_selftest_p9"),
    )
    ObjectCalls.destroyObject(p9Event)
  } else check("ret-typed-object-list(InputMap.action_get_events) (singleton absent)", false)
  // (String, bool) arg on a singleton: an empty typed list is a valid round-trip (no translations
  // are loaded in the self-test project).
  val p9Translation = requireSingleton("TranslationServer")
  if (p9Translation.address() != 0L) {
    val p9Translations =
      ObjectCalls.ptrcallWithStringAndBoolArgRetTypedObjectList(
        ObjectCalls.getMethodBind("TranslationServer", "find_translations", 2109650934L),
        p9Translation,
        "xx",
        true,
      ) {
        it
      }
    check(
      "ret-typed-object-list(TranslationServer.find_translations empty)",
      p9Translations.isEmpty(),
    )
  } else
    check("ret-typed-object-list(TranslationServer.find_translations) (singleton absent)", false)

  // task 100 (parcel 10) — typed arrays whose elements are containers / packed arrays, through
  // the GENERATED helpers (packTyped{Dictionary,Array,ByteArray,PackedStringList}ArrayDesc ->
  // PT_TYPED_ARRAY_BLOB -> nested blobs the boxer rebuilds). Data holders only — nothing that
  // needs a physics / navigation server, and no Control, at scene-init: constructing a Control
  // at scene-init runs ThemeDB::update_class_instance_items before the theme contexts exist and
  // segfaults (the 10+11 stack gate caught GraphEdit doing exactly that). The Array[Dictionary]
  // row (RenderingServer.mesh_create_from_surfaces) is NOT here: the RenderingServer singleton
  // does not exist at scene-level extension init, so it lives in the frame-1 phase below
  // (kanamaIosRuntimeObjectCallsSelfTestFrame).

  // Array[Array] whose inner Arrays hold PackedByteArrays: OggPacketSequence packet data, stored
  // as-is by the setter. Proves the nested blob both ways (arg boxer + return encoder), including
  // a 300-byte packet and a page with two packets.
  val nestedOgg = ObjectCalls.constructObject("OggPacketSequence")
  val nestedPackets: List<List<Any?>> =
    listOf(listOf(byteArrayOf(1, 2, 3), byteArrayOf(4, 5)), listOf(ByteArray(300) { it.toByte() }))
  ObjectCalls.ptrcallWithArrayListArg(
    ObjectCalls.getMethodBind("OggPacketSequence", "set_packet_data", 381264803L),
    nestedOgg,
    nestedPackets,
  )
  val nestedPacketsBack =
    ObjectCalls.ptrcallNoArgsRetArrayList(
      ObjectCalls.getMethodBind("OggPacketSequence", "get_packet_data", 3995934104L),
      nestedOgg,
    )
  check(
    "arg-nested(OggPacketSequence.set_packet_data Array[Array] of PackedByteArray: 2 pages, 2+1 packets)",
    nestedPacketsBack.size == 2 && nestedPacketsBack[0].size == 2 && nestedPacketsBack[1].size == 1,
  )
  check(
    "ret-nested(OggPacketSequence.get_packet_data packets byte-exact)",
    nestedPacketsBack.size == 2 &&
      (nestedPacketsBack[0].getOrNull(0) as? ByteArray)?.contentEquals(byteArrayOf(1, 2, 3)) ==
        true &&
      (nestedPacketsBack[0].getOrNull(1) as? ByteArray)?.contentEquals(byteArrayOf(4, 5)) == true &&
      (nestedPacketsBack[1].getOrNull(0) as? ByteArray)?.contentEquals(
        ByteArray(300) { it.toByte() }
      ) == true,
  )
  ObjectCalls.destroyObject(nestedOgg)

  // Array[PackedByteArray]: GLTFState.set_buffers / get_buffers, with an empty buffer and a
  // 5000-byte one (the argument blob has no inline cap; the typed-list read-back parks beyond it).
  val nestedGltf = ObjectCalls.constructObject("GLTFState")
  val nestedBuffers =
    listOf(byteArrayOf(9, 8, 7), ByteArray(0), ByteArray(5000) { (it % 251).toByte() })
  ObjectCalls.ptrcallWithByteArrayListArg(
    ObjectCalls.getMethodBind("GLTFState", "set_buffers", 381264803L),
    nestedGltf,
    nestedBuffers,
  )
  val nestedBuffersBack =
    ObjectCalls.ptrcallNoArgsRetByteArrayList(
      ObjectCalls.getMethodBind("GLTFState", "get_buffers", 3995934104L),
      nestedGltf,
    )
  check(
    "arg-nested(GLTFState.set_buffers Array[PackedByteArray] round-trip, empty + 5000-byte elements)",
    nestedBuffersBack.size == 3 &&
      nestedBuffersBack.zip(nestedBuffers).all { (a, b) -> a.contentEquals(b) },
  )
  ObjectCalls.destroyObject(nestedGltf)

  // Array[PackedStringArray]: GLTFObjectModelProperty.set_json_pointers / get_json_pointers, with
  // a non-ASCII element, an empty inner array and an empty string.
  val nestedOmp = ObjectCalls.constructObject("GLTFObjectModelProperty")
  val nestedPointers = listOf(listOf("/nodes/0/translation", "b\u00eata"), listOf(), listOf(""))
  ObjectCalls.ptrcallWithPackedStringListListArg(
    ObjectCalls.getMethodBind("GLTFObjectModelProperty", "set_json_pointers", 381264803L),
    nestedOmp,
    nestedPointers,
  )
  val nestedPointersBack =
    ObjectCalls.ptrcallNoArgsRetPackedStringListList(
      ObjectCalls.getMethodBind("GLTFObjectModelProperty", "get_json_pointers", 3995934104L),
      nestedOmp,
    )
  check(
    "arg-nested(GLTFObjectModelProperty.set_json_pointers Array[PackedStringArray] round-trip)",
    nestedPointersBack == nestedPointers,
  )
  ObjectCalls.destroyObject(nestedOmp)

  // Bound-Callable connect (Phase 4.1). emitter.add_user_signal("kanamaBound"); connectBound it to
  // receiver.set_name bound with "BoundName"; emit -> the bound Callable runs receiver.set_name(
  // "BoundName"), so get_name() == "BoundName". Fully self-contained — user signals + synchronous
  // emit work off-tree. Proves the bound arg is actually delivered (not just that connect returns
  // OK).
  val cbEmitter = ObjectCalls.constructObject("Node")
  val cbReceiver = ObjectCalls.constructObject("Node")
  ObjectCalls.callWithVariantArgs(
    ObjectCalls.getMethodBind("Object", "add_user_signal", 85656714L),
    cbEmitter,
    listOf("kanamaBound"),
  )
  val cbConnErr =
    ObjectCalls.connectBound(
      cbEmitter,
      "kanamaBound",
      cbReceiver,
      "set_name",
      listOf("BoundName"),
      0L,
    )
  ObjectCalls.callWithVariantArgs(
    ObjectCalls.getMethodBind("Object", "emit_signal", 4047867050L),
    cbEmitter,
    listOf("kanamaBound"),
  )
  val cbName =
    ObjectCalls.ptrcallNoArgsRetStringName(
      ObjectCalls.getMethodBind("Node", "get_name", 2002593661L),
      cbReceiver,
    )
  check("connect-bound(emit -> set_name(BoundName))", cbConnErr == 0L && cbName == "BoundName")

  // disconnectBound rebuilds the same bound Callable to remove it; rename the receiver to a
  // sentinel
  // and re-emit — the disconnected Callable must NOT fire, so the sentinel name survives.
  ObjectCalls.disconnectBound(cbEmitter, "kanamaBound", cbReceiver, "set_name", listOf("BoundName"))
  ObjectCalls.ptrcallWithStringNameArg(
    ObjectCalls.getMethodBind("Node", "set_name", 3304788590L),
    cbReceiver,
    "Sentinel",
  )
  ObjectCalls.callWithVariantArgs(
    ObjectCalls.getMethodBind("Object", "emit_signal", 4047867050L),
    cbEmitter,
    listOf("kanamaBound"),
  )
  val cbName2 =
    ObjectCalls.ptrcallNoArgsRetStringName(
      ObjectCalls.getMethodBind("Node", "get_name", 2002593661L),
      cbReceiver,
    )
  check("disconnect-bound(re-emit no-op -> name stays Sentinel)", cbName2 == "Sentinel")

  // Lambda Callable connect + close/disconnect (Phase 4.1b). Register a Kotlin lambda, connect it
  // to a user signal as a custom Callable, emit -> the trampoline runs the lambda (counter++).
  // Then objectDisconnectCallable recreates the identity-equal custom Callable (call_func +
  // callback_id) and disconnects it; a re-emit must NOT run the lambda. Exercises the close() path.
  var lamFires = 0
  val lamId = IosCallableRegistry.register { lamFires++ }
  val lamEmitter = ObjectCalls.constructObject("Node")
  ObjectCalls.callWithVariantArgs(
    ObjectCalls.getMethodBind("Object", "add_user_signal", 85656714L),
    lamEmitter,
    listOf("kanamaLambda"),
  )
  IosGodot.objectConnectCallable(
    lamEmitter.address(),
    "kanamaLambda",
    lamEmitter.address(),
    lamId,
    0L,
  )
  ObjectCalls.callWithVariantArgs(
    ObjectCalls.getMethodBind("Object", "emit_signal", 4047867050L),
    lamEmitter,
    listOf("kanamaLambda"),
  )
  val lamFiredOnce = lamFires
  IosGodot.objectDisconnectCallable(
    lamEmitter.address(),
    "kanamaLambda",
    lamEmitter.address(),
    lamId,
  )
  ObjectCalls.callWithVariantArgs(
    ObjectCalls.getMethodBind("Object", "emit_signal", 4047867050L),
    lamEmitter,
    listOf("kanamaLambda"),
  )
  check(
    "lambda-callable(connect fires once, disconnect stops it)",
    lamFiredOnce == 1 && lamFires == 1,
  )

  // Lambda Callable scalar-argument delivery. MultiplayerAPI.peer_connected emits the peer id
  // as a Variant int; use a self-contained user signal to exercise that same C trampoline path.
  var lamIntArg: Any? = null
  val lamIntId = IosCallableRegistry.register { args -> lamIntArg = args.firstOrNull() }
  ObjectCalls.callWithVariantArgs(
    ObjectCalls.getMethodBind("Object", "add_user_signal", 85656714L),
    lamEmitter,
    listOf("kanamaLambdaInt"),
  )
  IosGodot.objectConnectCallable(
    lamEmitter.address(),
    "kanamaLambdaInt",
    lamEmitter.address(),
    lamIntId,
    0L,
  )
  ObjectCalls.callWithVariantArgs(
    ObjectCalls.getMethodBind("Object", "emit_signal", 4047867050L),
    lamEmitter,
    listOf("kanamaLambdaInt", 4_294_967_001L),
  )
  check("lambda-callable(int argument delivered)", lamIntArg == 4_294_967_001L)
  IosGodot.objectDisconnectCallable(
    lamEmitter.address(),
    "kanamaLambdaInt",
    lamEmitter.address(),
    lamIntId,
  )

  // Auto-disconnect on receiver free: a lambda Callable connected with a receiver must be
  // disconnected by Godot when that receiver is freed, so a later emission neither fires it nor
  // touches freed memory. Regression for the iOS host-disconnect crash — the Callable was created
  // with object_id=0, so Godot could not associate it with its receiver and never disconnected it;
  // when the freed receiver's script later ran on emit it was a use-after-free
  // (__cxa_pure_virtual).
  var lamFreeFires = 0
  val lamFreeReceiver = requireObject("Node")
  if (lamFreeReceiver.address() != 0L) {
    val lamFreeBefore = IosCallableRegistry.size
    val lamFreeId = IosCallableRegistry.register { lamFreeFires++ }
    ObjectCalls.callWithVariantArgs(
      ObjectCalls.getMethodBind("Object", "add_user_signal", 85656714L),
      lamEmitter,
      listOf("kanamaLambdaFree"),
    )
    IosGodot.objectConnectCallable(
      lamEmitter.address(),
      "kanamaLambdaFree",
      lamFreeReceiver.address(),
      lamFreeId,
      0L,
    )
    ObjectCalls.destroyObject(
      lamFreeReceiver
    ) // free the receiver; Godot must auto-disconnect the bound Callable
    ObjectCalls.callWithVariantArgs(
      ObjectCalls.getMethodBind("Object", "emit_signal", 4047867050L),
      lamEmitter,
      listOf("kanamaLambdaFree"),
    )
    check("lambda-callable(auto-disconnect on receiver free)", lamFreeFires == 0)
    // Task 131 (F9): the dropped connection's free_func releases the closure, so the registry is
    // back to its size before the connect (desktop needed ScriptBridge.siFree for this).
    check(
      "lambda-callable(receiver free releases the registry entry)",
      IosCallableRegistry.size == lamFreeBefore,
    )
  } else check("lambda-callable(auto-disconnect on receiver free) (instance absent)", false)

  // Task 131 (F9): a CONNECT_ONE_SHOT lambda fires once, and Godot's one-shot disconnect frees the
  // Callable, whose free_func releases the closure: the registry returns to its size before.
  var lamOneShotFires = 0
  val lamOneShotBefore = IosCallableRegistry.size
  val lamOneShotId = IosCallableRegistry.register { lamOneShotFires++ }
  ObjectCalls.callWithVariantArgs(
    ObjectCalls.getMethodBind("Object", "add_user_signal", 85656714L),
    lamEmitter,
    listOf("kanamaLambdaOneShot"),
  )
  IosGodot.objectConnectCallable(
    lamEmitter.address(),
    "kanamaLambdaOneShot",
    lamEmitter.address(),
    lamOneShotId,
    4L, // CONNECT_ONE_SHOT
  )
  repeat(2) {
    ObjectCalls.callWithVariantArgs(
      ObjectCalls.getMethodBind("Object", "emit_signal", 4047867050L),
      lamEmitter,
      listOf("kanamaLambdaOneShot"),
    )
  }
  check(
    "lambda-callable(one-shot fires once and releases its entry)",
    lamOneShotFires == 1 && IosCallableRegistry.size == lamOneShotBefore,
  )

  // Task 134 D4: typed signals over the PT-tagged trampoline. A five-argument emission (the shim
  // forwarded at most four scalars before) with an int, a String, a StringName (decoded as String,
  // as desktop does), a Vector2 and an object, emitted engine-side by an Expression; the generated
  // `renamed: Signal0` with CONNECT_ONE_SHOT; and both entries released afterwards.
  run {
    val api = net.multigesture.kanama.api.SignalArgType
    val typedBefore = IosCallableRegistry.size
    val emitter =
      net.multigesture.kanama.api.Node(net.multigesture.kanama.api.GodotHandle(lamEmitter))
    ObjectCalls.callWithVariantArgs(
      ObjectCalls.getMethodBind("Object", "add_user_signal", 85656714L),
      lamEmitter,
      listOf("kanamaTyped"),
    )
    val typed =
      net.multigesture.kanama.api.Signal5(
        emitter,
        "kanamaTyped",
        api.LONG,
        api.STRING,
        api.STRING,
        api.valueOf<net.multigesture.kanama.types.Vector2>(
          "Vector2",
          net.multigesture.kanama.types.Vector2::class,
        ),
        api.objectOf("Node") { net.multigesture.kanama.api.Node(it) },
      )
    var typedSeen = ""
    val typedConnection =
      typed.connect(emitter) { n, s, sn, v, node ->
        typedSeen = "$n;$s;$sn;$v;${node.instanceId == emitter.instanceId}"
      }
    val expressionHandle = ObjectCalls.constructObject("Expression")
    val expression =
      net.multigesture.kanama.api.Expression(
        net.multigesture.kanama.api.GodotHandle(expressionHandle)
      )
    expression.parse(
      "emit_signal(\"kanamaTyped\", 5, \"s\", StringName(\"sn\"), Vector2(1.5, 2), self)",
      emptyList(),
    )
    expression.execute(emptyList(), emitter)
    ObjectCalls.destroyObject(expressionHandle)
    typedConnection.close()
    check(
      "typed-signal(five arguments incl. StringName, Vector2 and an object) seen=$typedSeen",
      typedSeen == "5;s;sn;(1.5, 2.0);true",
    )
    // The generated `renamed: Signal0`. Godot emits `renamed` from Node::set_name only for a node
    // inside the tree, and this self-test node is an orphan (so the first version of this row,
    // which renamed it, never fired: fires=0, the one-shot entry still registered). The signal is
    // emitted through its typed handle instead, which also covers the typed emit on iOS.
    // `registered` proves the counter sees a live connection; `leaked` catches a one-shot entry
    // Godot's free_func did not release, and `closed` one that close() did not.
    var renamedFires = 0
    emitter.renamed.connect(
      emitter,
      net.multigesture.kanama.api.GodotObject.ConnectFlags.ONE_SHOT,
    ) {
      renamedFires++
    }
    val registered = IosCallableRegistry.size - typedBefore
    emitter.renamed.emit()
    emitter.renamed.emit()
    val leaked = IosCallableRegistry.size - typedBefore
    emitter.renamed.connect(emitter) { renamedFires += 100 }.close()
    emitter.renamed.emit()
    val closed = IosCallableRegistry.size - typedBefore
    println(
      "[kanama][ios][kn] OBJECTCALLS SELFTEST typed-signal renamed registered=$registered " +
        "fires=$renamedFires leaked=$leaked closed=$closed"
    )
    check(
      "typed-signal(generated renamed: Signal0, one-shot fires once and is released, close releases) " +
        "registered=$registered fires=$renamedFires leaked=$leaked closed=$closed",
      registered == 1 && renamedFires == 1 && leaked == 0 && closed == 0,
    )
  }

  // Task 133: the value types as script types cross the shim as their raw Godot bytes. Values an
  // Expression emits (GDScript literals) arrive through kanama_ios_marshal_variant_args +
  // decodeIosCallArg and must equal the same values built in Kotlin (so a Basis' rows/columns and
  // a Projection's columns land right); the same values emitted from Kotlin go out through
  // encodeVariantArgs + kanama_ios_pt_arg_to_variant and come back equal; the property set
  // (decodeIosPropertyValue) and get / return (encodeIosReturn) layouts round-trip every type.
  run {
    val api = net.multigesture.kanama.api.SignalArgType
    val vtHandle = ObjectCalls.constructObject("Node")
    val vtNode = net.multigesture.kanama.api.Node(net.multigesture.kanama.api.GodotHandle(vtHandle))
    for (name in listOf("kanamaFlats", "kanamaSolids", "kanamaFrames")) {
      ObjectCalls.callWithVariantArgs(
        ObjectCalls.getMethodBind("Object", "add_user_signal", 85656714L),
        vtHandle,
        listOf(name),
      )
    }
    fun <T : Any> type(name: String, kClass: kotlin.reflect.KClass<*>) =
      api.valueOf<T>(name, kClass)
    val flats =
      net.multigesture.kanama.api.Signal4(
        vtNode,
        "kanamaFlats",
        type<Rect2>("Rect2", Rect2::class),
        type<Rect2i>("Rect2i", Rect2i::class),
        type<Vector4>("Vector4", Vector4::class),
        type<net.multigesture.kanama.types.Vector4i>(
          "Vector4i",
          net.multigesture.kanama.types.Vector4i::class,
        ),
      )
    val solids =
      net.multigesture.kanama.api.Signal4(
        vtNode,
        "kanamaSolids",
        type<Plane>("Plane", Plane::class),
        type<AABB>("AABB", AABB::class),
        type<Vector3i>("Vector3i", Vector3i::class),
        type<Quaternion>("Quaternion", Quaternion::class),
      )
    val frames =
      net.multigesture.kanama.api.Signal4(
        vtNode,
        "kanamaFrames",
        type<Transform2D>("Transform2D", Transform2D::class),
        type<Transform3D>("Transform3D", Transform3D::class),
        type<Projection>("Projection", Projection::class),
        type<Basis>("Basis", Basis::class),
      )
    val rect = Rect2(Vector2(0.5, 1.5), Vector2(2.25, 3.125))
    val recti = Rect2i(Vector2i(-1, 2), Vector2i(30, 40))
    val vec4 = Vector4(0.5, -0.25, 8.0, 1e-10)
    val vec4i = net.multigesture.kanama.types.Vector4i(Int.MIN_VALUE, Int.MAX_VALUE, 0, 7)
    val plane = Plane(Vector3(0.0, 1.0, 0.0), 2.5)
    val box = AABB(Vector3(-1.0, -2.0, -3.0), Vector3(4.0, 5.0, 6.0))
    val cell = Vector3i(7, -8, 9)
    val rot = Quaternion(0.0, 0.6, 0.0, 0.8)
    val xform2 = Transform2D(Vector2(1.0, 2.0), Vector2(3.0, 4.0), Vector2(5.0, 6.0))
    val xform3 =
      Transform3D(
        Basis(Vector3(1.0, 2.0, 3.0), Vector3(4.0, 5.0, 6.0), Vector3(7.0, 8.0, 9.0)),
        Vector3(10.0, 11.0, 12.0),
      )
    val proj =
      Projection(
        Vector4(1.0, 2.0, 3.0, 4.0),
        Vector4(5.0, 6.0, 7.0, 8.0),
        Vector4(9.0, 10.0, 11.0, 12.0),
        Vector4(13.0, 14.0, 15.0, 16.0),
      )
    val basis = Basis(Vector3(1.0, 0.0, 0.0), Vector3(0.0, 0.0, 1.0), Vector3(0.0, -1.0, 0.0))
    val expected =
      listOf<Any>(rect, recti, vec4, vec4i, plane, box, cell, rot, xform2, xform3, proj, basis)
    val seen = mutableListOf<Any>()
    val connections =
      listOf(
        flats.connect(vtNode) { a, b, c, d -> seen.addAll(listOf(a, b, c, d)) },
        solids.connect(vtNode) { a, b, c, d -> seen.addAll(listOf(a, b, c, d)) },
        frames.connect(vtNode) { a, b, c, d -> seen.addAll(listOf(a, b, c, d)) },
      )
    val expressionHandle = ObjectCalls.constructObject("Expression")
    val expression =
      net.multigesture.kanama.api.Expression(
        net.multigesture.kanama.api.GodotHandle(expressionHandle)
      )
    for (call in
      listOf(
        "emit_signal(\"kanamaFlats\", Rect2(0.5, 1.5, 2.25, 3.125), Rect2i(-1, 2, 30, 40), " +
          "Vector4(0.5, -0.25, 8, 1e-10), Vector4i(-2147483648, 2147483647, 0, 7))",
        "emit_signal(\"kanamaSolids\", Plane(0, 1, 0, 2.5), AABB(Vector3(-1, -2, -3), " +
          "Vector3(4, 5, 6)), Vector3i(7, -8, 9), Quaternion(0, 0.6, 0, 0.8))",
        "emit_signal(\"kanamaFrames\", Transform2D(Vector2(1, 2), Vector2(3, 4), Vector2(5, 6)), " +
          "Transform3D(Basis(Vector3(1, 2, 3), Vector3(4, 5, 6), Vector3(7, 8, 9)), " +
          "Vector3(10, 11, 12)), Projection(Vector4(1, 2, 3, 4), Vector4(5, 6, 7, 8), " +
          "Vector4(9, 10, 11, 12), Vector4(13, 14, 15, 16)), Basis(Vector3(1, 0, 0), " +
          "Vector3(0, 0, 1), Vector3(0, -1, 0)))",
      )) {
      expression.parse(call, emptyList())
      expression.execute(emptyList(), vtNode)
    }
    ObjectCalls.destroyObject(expressionHandle)
    val fromEngine = seen.toList()
    seen.clear()
    flats.emit(rect, recti, vec4, vec4i)
    solids.emit(plane, box, cell, rot)
    frames.emit(xform2, xform3, proj, basis)
    val fromKotlin = seen.toList()
    connections.forEach { it.close() }
    ObjectCalls.destroyObject(vtHandle)
    check("value-types(engine emit decoded) seen=$fromEngine", fromEngine == expected)
    check("value-types(Kotlin emit round trip) seen=$fromKotlin", fromKotlin == expected)
    val layoutOk = memScoped {
      val buf = allocIosRawValue()
      val roundTrips =
        expected.all { value ->
          val tag = net.multigesture.kanama.ios.encodeIosRawValue(value, buf)
          tag >= 0 &&
            net.multigesture.kanama.ios.decodeIosPropertyValue(
              tag,
              buf,
              net.multigesture.kanama.ios.IOS_RAW_VALUE_MAX_BYTES,
            ) == value
        }
      // Godot's memory order: a Transform3D's basis is three ROWS, then the origin.
      net.multigesture.kanama.ios.encodeIosRawValue(xform3, buf)
      val f = buf.reinterpret<GodotRealVar>()
      roundTrips && f[0] == 1f && f[1] == 4f && f[2] == 7f && f[3] == 2f && f[9] == 10f
    }
    check("value-types(property set/get byte layout)", layoutOk)
    // The get / return encode tags every type (Color too: an iOS Color export read back nil).
    val tags =
      (expected + net.multigesture.kanama.types.Color.RED).map {
        net.multigesture.kanama.ios.kanamaIosVariantReturnSelfTest(it)
      }
    check(
      "value-types(return tags) tags=$tags",
      tags == listOf(12, 37, 10, 40, 26, 21, 9, 20, 22, 19, 25, 18, 11),
    )
  }

  // Task 131 N7: a script method taking a RID is dispatched on iOS now; its argument decodes to the
  // RID with all 64 bits of the id (a value above 32 bits fails a narrowed cell).
  val ridArg = net.multigesture.kanama.ios.kanamaIosRidCallArgSelfTest(0x1_0000_0002L)
  check("call-arg(RID, 64-bit id) got=$ridArg", ridArg == RID(0x1_0000_0002L))
  // An `Any?` parameter is dispatched too: it receives every marshalled value, and a Variant type
  // the shim does not marshal fails the call loudly instead of arriving as null.
  val (anyArg, anyRefused) = net.multigesture.kanama.ios.kanamaIosAnyCallArgSelfTest()
  check(
    "call-arg(Any?: Long decoded, Callable refused, container OOM reported) got=$anyArg refused=$anyRefused",
    anyArg == 0x1_0000_0003L &&
      anyRefused?.contains("Variant type 25 is not marshalled") == true &&
      anyRefused.contains("Variant type 28 ran out of memory") == true,
  )

  // Task 131 (F4): the report a contained script exception sends to Godot. Built, not sent -- a
  // sent one prints `SCRIPT ERROR`, which the visual smoke treats as a failure; delivery is the
  // documented device check (a throwing _ready shows `SCRIPT ERROR:` in the device log). Task 131
  // item 13: the report names the throwing function's Kotlin file and line, from the debug build's
  // source-line table (device and simulator).
  val scriptErrorProbe =
    runCatching { net.multigesture.kanama.ios.throwSelfTestScriptError() }.exceptionOrNull()
  val scriptErrorReport = scriptErrorProbe?.let { IosScriptErrors.reportFor(it, "selfTest") }
  // The lookup is keyed by the frame's symbol: an archived app (Godot's .ipa export, one-click
  // deploy) is stripped, its frames name no function, and no report can carry a line there.
  val probeSymbolized =
    scriptErrorProbe?.getStackTrace()?.any { "#throwSelfTestScriptError(" in it } == true
  println(
    "[kanama][ios][kn] OBJECTCALLS SELFTEST script-error report=$scriptErrorReport " +
      "symbolized=$probeSymbolized"
  )
  // Task 131 (F4) through the REAL containment path: a built-in script whose method throws is
  // called via the call_v @CName export the shim uses. Contained (the self-test survives), the call
  // reports success with a nil return (as GDScript does for a runtime error in a called function),
  // and one script error reached Godot -- the device log shows its SCRIPT ERROR line.
  run {
    val throwingOwner = requireObject("Node")
    if (throwingOwner.address() != 0L) {
      val script =
        KanamaIosRuntime.createScriptResource(KanamaIosRuntime.THROWING_PROBE_SCRIPT_PATH)
      val instance = KanamaIosRuntime.createScriptInstance(script, throwingOwner.address())
      val reportsBefore = IosScriptErrors.reportCount
      val callResult =
        if (instance != 0L) {
          kanamaIosRuntimeScriptInstanceCallV(instance, 0, null, null, 0, null, null)
        } else -1
      val reported = IosScriptErrors.reportCount - reportsBefore
      println(
        "[kanama][ios][kn] OBJECTCALLS SELFTEST script-error containment call=$callResult " +
          "reported=$reported"
      )
      check(
        "script-error(throwing script method contained, call ok, one report)",
        instance != 0L && callResult == 1 && reported == 1,
      )
      if (instance != 0L) KanamaIosRuntime.freeScriptInstance(instance)
      KanamaIosRuntime.freeScriptResource(script)
      ObjectCalls.destroyObject(throwingOwner)
    } else check("script-error(throwing script method contained) (instance absent)", false)
  }
  check(
    "script-error(report carries class and message)",
    scriptErrorReport?.description == "kotlin.IllegalStateException" &&
      scriptErrorReport.message ==
        "kotlin.IllegalStateException: kanama self-test: deliberate script error",
  )
  if (probeSymbolized) {
    check(
      "script-error(report carries the Kotlin file:line) at=${scriptErrorReport?.file}:" +
        "${scriptErrorReport?.line}",
      scriptErrorReport?.function == "throwSelfTestScriptError" &&
        scriptErrorReport.file == "KanamaIosScriptErrorProbe.kt" &&
        scriptErrorReport.line > 0,
    )
  } else {
    // A stripped app: the report must still not invent a line.
    check(
      "script-error(stripped app: report names the containment site, line 0) " +
        "at=${scriptErrorReport?.function} (${scriptErrorReport?.file}:${scriptErrorReport?.line})",
      scriptErrorReport?.function == "selfTest" && scriptErrorReport.line == 0,
    )
  }

  // Task 131 item 11: a throwing property setter reached through the property-set @CName exports
  // (Long and String paths) is contained -- before, it crossed the export and terminated the app --
  // answers 1 (the property is the script's, the write was rejected) and is reported once each.
  run {
    val setterOwner = requireObject("Node")
    if (setterOwner.address() != 0L) {
      val script =
        KanamaIosRuntime.createScriptResource(KanamaIosRuntime.THROWING_PROBE_SCRIPT_PATH)
      val instance = KanamaIosRuntime.createScriptInstance(script, setterOwner.address())
      val reportsBefore = IosScriptErrors.reportCount
      val longResult =
        if (instance != 0L) kanamaIosRuntimeScriptInstanceSetProperty(instance, 0, 7L) else -1
      val stringResult =
        if (instance != 0L) kanamaIosRuntimeScriptInstanceSetPropertyString(instance, 0, null)
        else -1
      // Task 131 B1: a throwing getter reached through the get @CName export reads as nil (a 0
      // handle PT_OBJECT-tagged) and is reported, like desktop's siGet.
      val (getResult, getTag, getHandle) =
        memScoped {
          val tag = alloc<IntVar>()
          val buf = allocArray<ByteVar>(32)
          val r =
            if (instance != 0L) {
              kanamaIosRuntimeScriptInstanceGetProperty(instance, 0, tag.ptr, buf)
            } else -1
          Triple(r, tag.value, buf.reinterpret<LongVar>()[0])
        }
      val reported = IosScriptErrors.reportCount - reportsBefore
      println(
        "[kanama][ios][kn] OBJECTCALLS SELFTEST property-set containment long=$longResult " +
          "string=$stringResult get=$getResult/$getTag/$getHandle reported=$reported"
      )
      check(
        "script-error(throwing property setter contained, set ok, reported)",
        instance != 0L && longResult == 1 && stringResult == 1,
      )
      check(
        "script-error(throwing property getter contained, reads nil, reported)",
        getResult == 1 && getTag == 13 && getHandle == 0L && reported == 3,
      )
      if (instance != 0L) KanamaIosRuntime.freeScriptInstance(instance)
      KanamaIosRuntime.freeScriptResource(script)
      ObjectCalls.destroyObject(setterOwner)
    } else check("script-error(throwing property setter contained) (instance absent)", false)
  }

  // Task 131 item 10: an exception escaping a KanamaScope coroutine is reported as a script error
  // (one report; the self-test survives). Unconfined runs the coroutine inline, so the row can
  // count.
  run {
    val reportsBefore = IosScriptErrors.reportCount
    val scope = net.multigesture.kanama.api.KanamaScope()
    scope.launch(kotlinx.coroutines.Dispatchers.Unconfined) {
      throw IllegalStateException("kanama self-test: deliberate coroutine failure")
    }
    scope.cancel()
    val reported = IosScriptErrors.reportCount - reportsBefore
    println("[kanama][ios][kn] OBJECTCALLS SELFTEST coroutine-error reported=$reported")
    check("script-error(KanamaScope coroutine exception reported once)", reported == 1)
  }

  // Task 131 item 6: two wrappers of one object are equal (instance id), hash alike and collapse in
  // a Set, whatever their class; a wrapper of another object is not equal.
  // Task 131 item 2: after the object is freed, a call through the wrapper throws
  // IllegalStateException (the freed-object check; forced on here so the row does not depend on
  // the build type) instead of dereferencing the dead pointer; equality, toString and
  // isInstanceValid stay safe.
  run {
    val first = requireObject("Node3D")
    val second = requireObject("Node")
    if (first.address() != 0L && second.address() != 0L) {
      val asNode3D = net.multigesture.kanama.api.Node3D(GodotHandle(first))
      val asNode = net.multigesture.kanama.api.Node(GodotHandle(first))
      val other = net.multigesture.kanama.api.Node(GodotHandle(second))
      check(
        "wrapper-equality(two wrappers of one object are equal, hash alike, one Set entry)",
        asNode3D == asNode &&
          asNode == asNode3D &&
          asNode3D.hashCode() == asNode.hashCode() &&
          setOf(asNode3D, asNode, other).size == 2,
      )
      check("wrapper-equality(wrappers of two objects differ)", asNode != other)
      ObjectCalls.destroyObject(first)
      ObjectCalls.destroyObject(second)
      val checksBefore = FreedObjectChecks.enabled
      FreedObjectChecks.enabled = true
      try {
        val thrown = runCatching { asNode3D.getName() }.exceptionOrNull()
        println("[kanama][ios][kn] OBJECTCALLS SELFTEST freed-object call threw=$thrown")
        check(
          "freed-object(call through a freed wrapper throws IllegalStateException)",
          thrown is IllegalStateException &&
            thrown.message.orEmpty().startsWith("Invalid access to previously freed instance"),
        )
        check(
          "freed-object(equality, toString and isInstanceValid stay safe)",
          asNode3D == asNode &&
            asNode3D.toString() == "<Freed Object>" &&
            !IosGodot.isInstanceIdValid(asNode3D.instanceId),
        )
        // Holding is silent (GDScript semantics): encoded as a value -- a property read, a return,
        // a Variant argument -- the freed wrapper is a 0 handle (nil), with no exception.
        check(
          "freed-object(value encoding is nil, no error)",
          runCatching { FreedObjectChecks.valueSegment(asNode3D).address() }.getOrNull() == 0L,
        )
      } finally {
        FreedObjectChecks.enabled = checksBefore
      }
    } else check("wrapper-equality(two objects constructed)", false)
  }

  // Task 132 blocker 1: a KanamaScript object keeps its RefCounted owner alive. The City-Builder
  // pattern at the runtime level: a resource with a KanamaScript instance, its owning wrapper
  // closed (the engine would call refcount_decremented at count 1: the link goes weak), only the
  // script object kept -- after a GC and a drain the owner is alive; once the script object is
  // dropped too, the owner dies. (The shim's refcount callbacks are driven here through their
  // @CName exports, as Godot calls them for an attached instance.)
  run {
    val enabledBefore = OwnedReleases.enabled
    OwnedReleases.enabled = true
    try {
      val script =
        KanamaIosRuntime.createScriptResource(KanamaIosRuntime.OWNER_LINK_PROBE_SCRIPT_PATH)
      val (instance, ownerId) = ownerLinkKeepOnlyScriptObject(script)
      repeat(3) {
        OwnedReleaseCleaner.collectGarbage(1_000)
        OwnedReleases.drain()
      }
      val aliveWhileHeld = IosGodot.isInstanceIdValid(ownerId)
      OwnerLinkHolder.held = null
      repeat(5) {
        if (!IosGodot.isInstanceIdValid(ownerId)) return@repeat
        OwnedReleaseCleaner.collectGarbage(1_000)
        OwnedReleases.drain()
      }
      val diesAfterDrop = !IosGodot.isInstanceIdValid(ownerId)
      println(
        "[kanama][ios][kn] OBJECTCALLS SELFTEST owner-link instance=$instance " +
          "alive_while_held=$aliveWhileHeld dies_after_drop=$diesAfterDrop"
      )
      check(
        "owner-link(a held KanamaScript object keeps its resource alive across GC)",
        aliveWhileHeld,
      )
      check("owner-link(the resource dies once the script object is dropped)", diesAfterDrop)
      if (instance != 0L) KanamaIosRuntime.freeScriptInstance(instance)
      KanamaIosRuntime.freeScriptResource(script)
    } finally {
      OwnedReleases.enabled = enabledBefore
    }
  }

  // Task 132 round 2: the same through Godot. The probe script is attached with set_script, so the
  // engine creates the instance and calls the shim's refcount callbacks itself: the resource stays
  // alive while only its script object is held across GCs, reads its value through the engine,
  // and dies once the script object is dropped.
  run {
    val enabledBefore = OwnedReleases.enabled
    OwnedReleases.enabled = true
    try {
      val scriptObject =
        net.multigesture.kanama.ios.cinterop.kanama_ios_godot_create_script_object(
          KanamaIosRuntime.OWNER_LINK_PROBE_SCRIPT_PATH
        )
      if (scriptObject == 0L) {
        check("owner-link-attached(probe script object created)", false)
      } else {
        val (ownerAddress, ownerId) = ownerLinkAttachAndKeepOnlyScriptObject(scriptObject)
        repeat(3) {
          OwnedReleaseCleaner.collectGarbage(1_000)
          OwnedReleases.drain()
        }
        val alive = IosGodot.isInstanceIdValid(ownerId)
        val engineRead =
          if (alive) GodotObject(GodotHandle(MemorySegment.ofAddress(ownerAddress))).get("cash")
          else null
        OwnerLinkHolder.held = null
        repeat(5) {
          if (!IosGodot.isInstanceIdValid(ownerId)) return@repeat
          OwnedReleaseCleaner.collectGarbage(1_000)
          OwnedReleases.drain()
        }
        val dies = !IosGodot.isInstanceIdValid(ownerId)
        println(
          "[kanama][ios][kn] OBJECTCALLS SELFTEST owner-link-attached alive_while_held=$alive " +
            "engine_read=$engineRead dies_after_drop=$dies"
        )
        check("owner-link-attached(alive while only the script object is held across GC)", alive)
        check(
          "owner-link-attached(engine reads the script property)",
          (engineRead as? Number)?.toLong() == 4242L,
        )
        check("owner-link-attached(dies once the script object is dropped)", dies)
        RefCounted.releaseHandle(MemorySegment.ofAddress(scriptObject))
      }
    } finally {
      OwnedReleases.enabled = enabledBefore
    }
  }

  // Map @Export properties (desktop: initVariantDictionaryAny / readVariantDictionaryAny): a
  // Map<Int, String> and a Map<Vector2i, Long> of a built-in probe script, set by the engine
  // (Object.set from an Expression, as a scene or GDScript sets them) and read back by the engine
  // (Object.get compared with the literal engine-side), so the generic String-keyed Dictionary
  // decode cannot stand in for them. Keys and values of another type drop, as on desktop.
  run {
    val scriptObject =
      net.multigesture.kanama.ios.cinterop.kanama_ios_godot_create_script_object(
        net.multigesture.kanama.ios.DICTIONARY_PROBE_SCRIPT_PATH
      )
    val ownerHandle = ObjectCalls.constructObject("Node")
    if (scriptObject == 0L || ownerHandle.address() == 0L) {
      check("map-export(probe script and owner created)", false)
    } else {
      net.multigesture.kanama.ios.DictionaryProbeScript.last = null
      val owner = GodotObject(GodotHandle(ownerHandle))
      owner.setScript(
        net.multigesture.kanama.api.Resource.fromHandle(
          GodotHandle(MemorySegment.ofAddress(scriptObject))
        )
      )
      val probe = net.multigesture.kanama.ios.DictionaryProbeScript.last
      val expressionHandle = ObjectCalls.constructObject("Expression")
      val expression =
        net.multigesture.kanama.api.Expression.fromHandle(GodotHandle(expressionHandle))!!
      fun eval(code: String): Any? {
        if (expression.parse(code, emptyList()) != net.multigesture.kanama.api.GodotError.OK) {
          return "parse-failed"
        }
        return expression.execute(emptyList(), owner)
      }
      try {
        eval("set(\"names\", {1: \"one\", 2: \"two\"})")
        eval("set(\"cells\", {Vector2i(1, 2): 3, Vector2i(-4, 5): 6})")
        val namesSet = probe?.names
        val cellsSet = probe?.cells
        check(
          "map-export(Map<Int, String> set by the engine keeps its int keys) got=$namesSet",
          namesSet == mapOf(1 to "one", 2 to "two"),
        )
        check(
          "map-export(Map<Vector2i, Long> set by the engine keeps its Vector2i keys) got=$cellsSet",
          cellsSet == mapOf(Vector2i(1, 2) to 3L, Vector2i(-4, 5) to 6L),
        )
        probe?.names = mapOf(7 to "seven")
        probe?.cells = mapOf(Vector2i(0, -1) to 9L)
        val readBack =
          eval("get(\"names\") == {7: \"seven\"} and get(\"cells\") == {Vector2i(0, -1): 9}")
        check(
          "map-export(both Maps read back by the engine with their keys) got=$readBack",
          readBack == true,
        )
        eval("set(\"names\", {1: \"one\", \"x\": \"bad key\", 3: 4})")
        check(
          "map-export(a key or value of another type drops its entry) got=${probe?.names}",
          probe?.names == mapOf(1 to "one"),
        )
        // An enum key (stored as its ordinal) and a nullable value, whose nil keeps its key.
        eval("set(\"tiers\", {0: 0.5, 1: null, \"x\": 1.0})")
        val tiersSet = probe?.tiers
        check(
          "map-export(Map<Enum, Double?> keeps an enum key and a nil value) got=$tiersSet",
          tiersSet ==
            mapOf(
              net.multigesture.kanama.ios.DictionaryProbeTier.LOW to 0.5,
              net.multigesture.kanama.ios.DictionaryProbeTier.HIGH to null,
            ),
        )
        probe?.tiers = mapOf(net.multigesture.kanama.ios.DictionaryProbeTier.HIGH to 2.5)
        val tierRead = eval("get(\"tiers\") == {1: 2.5}")
        probe?.tiers = mapOf(net.multigesture.kanama.ios.DictionaryProbeTier.LOW to null)
        val nilRead = eval("get(\"tiers\") == {0: null}")
        check(
          "map-export(Map<Enum, Double?> reads back ordinals and a nil value) got=$tierRead/$nilRead",
          tierRead == true && nilRead == true,
        )
        // An object-valued Map (Map<String, Texture2D>): the texture stays alive while only the
        // property holds it (the Kotlin wrapper is closed right after the set), reads back as the
        // same object, and is released when the property is set again, as desktop's retained
        // Dictionary read (readVariantDictionaryObjectValuesRetained) does.
        val resource =
          net.multigesture.kanama.api.ClassDB.instantiate("PlaceholderTexture2D")
            as net.multigesture.kanama.api.RefCounted
        val resourceId = resource.instanceId
        val resourceAddress = resource.handle.segment.address()
        owner.set("textures", mapOf("a" to resource))
        resource.close()
        val heldAlive = IosGodot.isInstanceIdValid(resourceId)
        val heldValue = probe?.textures?.get("a")?.handle?.segment?.address()
        val readValue =
          ((owner.get("textures") as? Map<*, *>)?.get("a") as? GodotObject)?.segment?.address()
        owner.set("textures", emptyMap<String, Any?>())
        val releasedAfterReset = !IosGodot.isInstanceIdValid(resourceId)
        check(
          "map-export(Map<String, Texture2D> holds its texture, reads it back, releases it on reset) " +
            "alive=$heldAlive held=${heldValue == resourceAddress} read=${readValue == resourceAddress} " +
            "released=$releasedAfterReset",
          heldAlive &&
            heldValue == resourceAddress &&
            readValue == resourceAddress &&
            releasedAfterReset,
        )
      } finally {
        ObjectCalls.destroyObject(expressionHandle)
        ObjectCalls.destroyObject(ownerHandle)
      }
    }
  }

  // Task 132 review: the shim's property set path. A Node (or List<Node>) set into a script
  // property takes no reference -- before, the shim called RefCounted.reference on every object,
  // and
  // Godot's ptrcall casts blindly, so the Node's scene_file_path was overwritten (and unreference
  // later made it crash). More than 16 Resources set into a List property are each referenced once
  // (the old record had 16 slots), a new set releases the old ones, and the owner's free releases
  // the rest. The probe script is a plain class, so the owner dies as soon as it is closed.
  run {
    val scriptObject =
      net.multigesture.kanama.ios.cinterop.kanama_ios_godot_create_script_object(
        KanamaIosRuntime.PROPERTY_RETAIN_PROBE_SCRIPT_PATH
      )
    if (scriptObject == 0L) {
      check("property-retain(probe script object created)", false)
    } else {
      val owner = net.multigesture.kanama.api.Resource.create()
      val ownerAddress = owner.handle.segment.address()
      owner.setScript(
        net.multigesture.kanama.api.Resource.fromHandle(
          GodotHandle(MemorySegment.ofAddress(scriptObject))
        )
      )
      val target = GodotObject(GodotHandle(MemorySegment.ofAddress(ownerAddress)))
      val nodeA =
        net.multigesture.kanama.api.Node3D(GodotHandle(ObjectCalls.constructObject("Node3D")))
      val nodeB =
        net.multigesture.kanama.api.Node3D(GodotHandle(ObjectCalls.constructObject("Node3D")))
      nodeA.setSceneFilePath("res://kanama_probe_a.tscn")
      nodeB.setSceneFilePath("res://kanama_probe_b.tscn")
      target.set("node", nodeA)
      target.set("nodes", listOf(nodeA, nodeB))
      val pathsKept =
        nodeA.getSceneFilePath() == "res://kanama_probe_a.tscn" &&
          nodeB.getSceneFilePath() == "res://kanama_probe_b.tscn"
      val nodeRefs = ScriptPropertyRetains.countFor(ownerAddress)
      target.set("node", null)
      target.set("nodes", emptyList<Any?>())
      val pathsKeptAfterClear =
        nodeA.getSceneFilePath() == "res://kanama_probe_a.tscn" &&
          nodeB.getSceneFilePath() == "res://kanama_probe_b.tscn"
      ObjectCalls.destroyObject(nodeA.handle.segment)
      ObjectCalls.destroyObject(nodeB.handle.segment)

      // (instance id, handle) of each element; the row's own wrappers are closed right after the
      // set, so only the property can hold them. The counts are read through a borrowed view.
      fun setItems(count: Int): List<Pair<Long, Long>> {
        val items = (1..count).map { net.multigesture.kanama.api.Resource.create() }
        target.set("items", items)
        val elements = items.map { it.instanceId to it.handle.segment.address() }
        items.forEach { it.close() }
        return elements
      }
      fun refs(element: Pair<Long, Long>): Int =
        if (!IosGodot.isInstanceIdValid(element.first)) 0
        else
          net.multigesture.kanama.api.Resource.fromHandle(
              GodotHandle(MemorySegment.ofAddress(element.second))
            )!!
            .getReferenceCount()
      val first = setItems(20)
      val firstAlive = first.all { IosGodot.isInstanceIdValid(it.first) }
      // Only the registry's reference is left (expected 2 on 0467069e: the shim's leaked
      // Array copy of the set value held one on every element).
      val refsAfterSet = refs(first[0])
      val second = setItems(20)
      val resetDrop = refsAfterSet - refs(first[0])
      val firstReleased = first.none { IosGodot.isInstanceIdValid(it.first) }
      val secondAlive = second.all { IosGodot.isInstanceIdValid(it.first) }
      val held = ScriptPropertyRetains.countFor(ownerAddress)
      val ownerId = owner.instanceId
      val refsBeforeFree = refs(second[0])
      owner.close()
      val ownerDead = !IosGodot.isInstanceIdValid(ownerId)
      val freeDrop = refsBeforeFree - refs(second[0])
      val secondReleased = second.none { IosGodot.isInstanceIdValid(it.first) }
      println(
        "[kanama][ios][kn] OBJECTCALLS SELFTEST property-retain node_paths_kept=$pathsKept " +
          "node_refs=$nodeRefs paths_kept_after_clear=$pathsKeptAfterClear first_alive=$firstAlive " +
          "refs_after_set=$refsAfterSet reset_drop=$resetDrop reset_releases_old=$firstReleased " +
          "second_alive=$secondAlive held=$held owner_dead=$ownerDead free_drop=$freeDrop " +
          "free_releases_all=$secondReleased"
      )
      check(
        "property-retain(a Node property set keeps scene_file_path)",
        pathsKept && pathsKeptAfterClear,
      )
      check("property-retain(a Node property set takes no reference)", nodeRefs == 0)
      check("property-retain(20 Resources set into a List are all held)", firstAlive && held == 20)
      check(
        "property-retain(the property holds exactly one reference per element)",
        refsAfterSet == 1,
      )
      check(
        "property-retain(a new set releases the old values)",
        resetDrop == 1 && firstReleased && secondAlive,
      )
      check(
        "property-retain(the owner's free releases every value)",
        ownerDead && freeDrop == 1 && secondReleased,
      )
      RefCounted.releaseHandle(MemorySegment.ofAddress(scriptObject))
    }
  }

  // Task 132: a forgotten close() is a late release, not a leak. 1,000 owned Resources dropped
  // without close() are released once a GC has collected their wrappers and the main thread has
  // drained the releases (D2): none of their instance ids resolves any more. A second owned +1 that
  // was closed and then collected is released once (D3: close() cancels the cleanup), and one that
  // was dropped unclosed is released by the GC: the kept Resource ends at refcount 1.
  run {
    val enabledBefore = OwnedReleases.enabled
    OwnedReleases.enabled = true
    try {
      val dropped = dropOwnedResources(1_000)
      val kept = net.multigesture.kanama.api.Resource.create()
      closeSecondOwnedReference(kept)
      dropSecondOwnedReference(kept)
      val refsBefore = kept.getReferenceCount()
      var released = 0
      repeat(5) {
        if (dropped.all { !IosGodot.isInstanceIdValid(it) } && kept.getReferenceCount() == 1)
          return@repeat
        OwnedReleaseCleaner.collectGarbage(1_000)
        released += OwnedReleases.drain()
      }
      val gone = dropped.count { !IosGodot.isInstanceIdValid(it) }
      val refsAfter = kept.getReferenceCount()
      println(
        "[kanama][ios][kn] OBJECTCALLS SELFTEST owned-release dropped=${dropped.size} gone=$gone " +
          "released=$released kept_refs_before=$refsBefore kept_refs_after=$refsAfter"
      )
      check(
        "owned-release(1000 dropped owned Resources released by the GC fallback)",
        gone == dropped.size,
      )
      check(
        "owned-release(closed then collected: released once; dropped: released)",
        refsBefore == 2 && refsAfter == 1,
      )
      kept.close()
    } finally {
      OwnedReleases.enabled = enabledBefore
    }
  }

  // Task 138 item 6 (R6.6): owned wrappers made OFF the main thread register their release too.
  // A worker thread drops 10,000 owned Resources; the GC + main-thread drain releases them all
  // (before task 138 a worker's wrapper registered nothing: released=0). Then, for 3 seconds, a
  // worker calls getRid() on fresh owned Resources -- the call is each wrapper's last use -- while
  // this thread collects and drains every 16 ms: no fault, no release inside a call (Kotlin/Native
  // keeps a local rooted while its frame is live, which is why the iOS keepReachable is empty).
  run {
    val enabledBefore = OwnedReleases.enabled
    OwnedReleases.enabled = true
    try {
      var ids = LongArray(0)
      onWorkerThread({ ids = dropOwnedResources(10_000) })
      var released = 0
      repeat(8) {
        released = ids.count { !IosGodot.isInstanceIdValid(it) }
        if (released == ids.size) return@repeat
        OwnedReleaseCleaner.collectGarbage(1_000)
        while (OwnedReleases.drain() > 0) {}
      }
      released = ids.count { !IosGodot.isInstanceIdValid(it) }
      println(
        "[kanama][ios][kn] OBJECTCALLS SELFTEST worker-owned GC releases: created=${ids.size} " +
          "released=$released leaked=${ids.size - released}"
      )
      check(
        "worker-owned(10000 Resources dropped on a worker released by the GC fallback)",
        ids.size == 10_000 && released == ids.size,
      )

      var calls = 0L
      var faults = 0
      val faultsBefore = ObjectCalls.faultCount()
      val deadline = kotlin.time.TimeSource.Monotonic.markNow() + kotlin.time.Duration.parse("3s")
      var sink = 0L
      var lastCollect = kotlin.time.TimeSource.Monotonic.markNow()
      onWorkerThread(
        {
          while (deadline.hasNotPassedNow()) {
            repeat(100) {
              try {
                sink += net.multigesture.kanama.api.Resource.create().getRid().value
                calls++
              } catch (t: Throwable) {
                faults++
              }
            }
            platform.posix.usleep(1_000u)
          }
        },
        whileWaiting = {
          if (lastCollect.elapsedNow().inWholeMilliseconds >= 16) {
            @OptIn(kotlin.native.runtime.NativeRuntimeApi::class) kotlin.native.runtime.GC.collect()
            OwnedReleases.drain()
            lastCollect = kotlin.time.TimeSource.Monotonic.markNow()
          }
        },
      )
      val newFaults = ObjectCalls.faultCount() - faultsBefore
      println(
        "[kanama][ios][kn] OBJECTCALLS SELFTEST worker GC stress: seconds=3 calls=$calls " +
          "faults=${faults + newFaults}${if (sink == Long.MIN_VALUE) " sink" else ""}"
      )
      check(
        "worker-gc-stress(3 s of calls through fresh owned wrappers while collecting: no fault)",
        calls > 0 && faults == 0 && newFaults == 0,
      )
    } finally {
      OwnedReleases.enabled = enabledBefore
    }
  }

  // Task 113 — the custom-Callable lifetime ORDERING MATRIX. Task 108 was one ordering (row 4)
  // among the handful a lambda connection can see; each row here is one more, with a lambda bound
  // to a receiver Node and a user signal on an emitter Node (no Control, no physics: scene init).
  // Every row logs one `callable-lifetime row=N` line and asserts that it returned (no crash), that
  // the lambda fired exactly as Godot's semantics say, and that the closure's registry entry is
  // released exactly when Godot drops the last Callable of it.
  //
  // Row 4 is task 108's: explicit disconnect, then free the EMITTER before the RECEIVER.
  // Object::_disconnect erases the receiver-side connection entry only via the receiver it finds
  // on the Callable it is given; the old receiver-less temp left that entry dangling, and freeing
  // the receiver after its emitter made Object::~Object call `_disconnect` on the freed emitter
  // through a null pointer (third-person BeeBot: the child Area3D dies before the RigidBody3D that
  // connected to it).
  //
  // Row 5 documents Godot's reference counting: a second connect of the same Callable is refused
  // (ERR_INVALID_PARAMETER) unless CONNECT_REFERENCE_COUNTED, which counts it instead, and then one
  // disconnect only counts down — the connection stays and fires once. Before task 113 the FIRST
  // free of any Callable of the id (the counted connect's temp) released the closure, so the live
  // connection fired nothing.
  //
  // Row 6: a CONNECT_ONE_SHOT connection is dropped by Godot when it fires; the close() after it
  // is a no-op, not an Object.disconnect of a nonexistent connection (an engine error line, and a
  // stale release of a closure already gone, which is what the row counts).
  run {
    val addUserSignal = ObjectCalls.getMethodBind("Object", "add_user_signal", 85656714L)
    val emitSignal = ObjectCalls.getMethodBind("Object", "emit_signal", 4047867050L)
    val signal = "kanamaLifetime"
    fun pair(): Pair<MemorySegment, MemorySegment>? {
      val emitter = requireObject("Node")
      val receiver = requireObject("Node")
      if (emitter.address() == 0L || receiver.address() == 0L) return null
      ObjectCalls.callWithVariantArgs(addUserSignal, emitter, listOf(signal))
      return emitter to receiver
    }
    fun emit(emitter: MemorySegment) {
      ObjectCalls.callWithVariantArgs(emitSignal, emitter, listOf(signal))
    }
    fun connect(emitter: MemorySegment, receiver: MemorySegment, id: Long, flags: Long = 0L) =
      IosGodot.objectConnectCallable(emitter.address(), signal, receiver.address(), id, flags)
    fun disconnect(emitter: MemorySegment, receiver: MemorySegment, id: Long) =
      IosGodot.objectDisconnectCallable(emitter.address(), signal, receiver.address(), id)
    fun row(number: Int, sequence: String, ok: Boolean, details: String) {
      println(
        "[kanama][ios][kn] OBJECTCALLS SELFTEST callable-lifetime row=$number ($sequence) $details"
      )
      check("callable-lifetime($number: $sequence)", ok)
    }
    fun absent(number: Int, sequence: String) =
      check("callable-lifetime($number: $sequence) (instance absent)", false)

    run {
      val sequence = "connect -> free emitter -> free receiver"
      val nodes = pair() ?: return@run absent(1, sequence)
      val (emitter, receiver) = nodes
      val before = IosCallableRegistry.size
      var fires = 0
      val id = IosCallableRegistry.register { fires++ }
      val connected = connect(emitter, receiver, id)
      ObjectCalls.destroyObject(emitter)
      val released = IosCallableRegistry.size == before
      ObjectCalls.destroyObject(receiver)
      row(
        1,
        sequence,
        connected == 0L && fires == 0 && released,
        "connected=$connected fires=$fires released_by_emitter_free=$released",
      )
    }
    run {
      val sequence = "connect -> free receiver -> emit -> free emitter"
      val nodes = pair() ?: return@run absent(2, sequence)
      val (emitter, receiver) = nodes
      val before = IosCallableRegistry.size
      var fires = 0
      val id = IosCallableRegistry.register { fires++ }
      val connected = connect(emitter, receiver, id)
      ObjectCalls.destroyObject(receiver)
      val released = IosCallableRegistry.size == before
      emit(emitter)
      ObjectCalls.destroyObject(emitter)
      row(
        2,
        sequence,
        connected == 0L && fires == 0 && released,
        "connected=$connected fires=$fires released_by_receiver_free=$released",
      )
    }
    run {
      val sequence = "connect -> disconnect -> free receiver -> free emitter"
      val nodes = pair() ?: return@run absent(3, sequence)
      val (emitter, receiver) = nodes
      val before = IosCallableRegistry.size
      var fires = 0
      val id = IosCallableRegistry.register { fires++ }
      val connected = connect(emitter, receiver, id)
      val disconnected = disconnect(emitter, receiver, id)
      val released = IosCallableRegistry.size == before
      ObjectCalls.destroyObject(receiver)
      ObjectCalls.destroyObject(emitter)
      row(
        3,
        sequence,
        connected == 0L && disconnected == 0 && fires == 0 && released,
        "connected=$connected disconnected=$disconnected fires=$fires released_by_disconnect=$released",
      )
    }
    run {
      val sequence = "connect -> disconnect -> free emitter -> free receiver"
      val nodes = pair() ?: return@run absent(4, sequence)
      val (emitter, receiver) = nodes
      val before = IosCallableRegistry.size
      var fires = 0
      val id = IosCallableRegistry.register { fires++ }
      val connected = connect(emitter, receiver, id)
      val disconnected = disconnect(emitter, receiver, id)
      val released = IosCallableRegistry.size == before
      ObjectCalls.destroyObject(emitter) // emitter first: its slot is already gone
      ObjectCalls.destroyObject(receiver) // receiver second: must find no dangling connection
      row(
        4,
        sequence,
        connected == 0L && disconnected == 0 && fires == 0 && released,
        "connected=$connected disconnected=$disconnected fires=$fires released_by_disconnect=$released",
      )
    }
    run {
      val sequence = "connect twice REFERENCE_COUNTED -> disconnect once -> emit"
      val nodes = pair() ?: return@run absent(5, sequence)
      val (emitter, receiver) = nodes
      val before = IosCallableRegistry.size
      var fires = 0
      val id = IosCallableRegistry.register { fires++ }
      val counted = 8L // CONNECT_REFERENCE_COUNTED
      val first = connect(emitter, receiver, id, counted)
      val second = connect(emitter, receiver, id, counted)
      val firstDisconnect = disconnect(emitter, receiver, id)
      emit(emitter)
      val firesWhileCounted = fires
      val keptWhileCounted = IosCallableRegistry.entry(id) != null
      val lastDisconnect = disconnect(emitter, receiver, id)
      emit(emitter)
      val released = IosCallableRegistry.size == before
      ObjectCalls.destroyObject(emitter)
      ObjectCalls.destroyObject(receiver)
      row(
        5,
        sequence,
        first == 0L &&
          second == 0L &&
          firstDisconnect == 0 &&
          lastDisconnect == 0 &&
          firesWhileCounted == 1 &&
          keptWhileCounted &&
          fires == 1 &&
          released,
        "connects=$first,$second fires_after_one_disconnect=$firesWhileCounted " +
          "kept=$keptWhileCounted fires_after_last_disconnect=$fires released=$released",
      )
    }
    run {
      val sequence = "connect ONE_SHOT -> emit -> close()"
      val nodes = pair() ?: return@run absent(6, sequence)
      val (emitter, receiver) = nodes
      val before = IosCallableRegistry.size
      var fires = 0
      val connection =
        net.multigesture.kanama.api.GodotSignal(GodotObject(GodotHandle(emitter)), signal).connect(
          GodotObject(GodotHandle(receiver)),
          0,
          GodotObject.ConnectFlags.ONE_SHOT,
        ) {
          fires++
        }
      emit(emitter)
      emit(emitter)
      val released = IosCallableRegistry.size == before
      val staleBefore = net.multigesture.kanama.ios.IosCallableLifetimes.staleReleases
      connection.close()
      val stale = net.multigesture.kanama.ios.IosCallableLifetimes.staleReleases - staleBefore
      ObjectCalls.destroyObject(emitter)
      ObjectCalls.destroyObject(receiver)
      row(
        6,
        sequence,
        connection.error == net.multigesture.kanama.api.GodotError.OK &&
          fires == 1 &&
          released &&
          stale == 0,
        "fires=$fires released_by_fire=$released close_stale_releases=$stale",
      )
    }
    run {
      val sequence = "connect -> emitter == receiver -> free it"
      val node = requireObject("Node")
      if (node.address() == 0L) return@run absent(7, sequence)
      ObjectCalls.callWithVariantArgs(addUserSignal, node, listOf(signal))
      val before = IosCallableRegistry.size
      var fires = 0
      val id = IosCallableRegistry.register { fires++ }
      val connected = connect(node, node, id)
      emit(node)
      val firedBeforeFree = fires
      ObjectCalls.destroyObject(node)
      val released = IosCallableRegistry.size == before
      row(
        7,
        sequence,
        connected == 0L && firedBeforeFree == 1 && released,
        "connected=$connected fires=$firedBeforeFree released_by_free=$released",
      )
    }
  }

  // Typed Array[StringName] return (Phase 2.7g). add_to_group("kgrp") then get_groups() == ["kgrp"]
  // — exercises ptrcallNoArgsRetStringNameList (Array size/get + StringName->utf8 blob). Plain
  // Node.
  val grpNode = ObjectCalls.constructObject("Node")
  ObjectCalls.callWithVariantArgs(
    ObjectCalls.getMethodBind("Node", "add_to_group", 3683006648L),
    grpNode,
    listOf("kgrp", false),
  )
  val groups =
    ObjectCalls.ptrcallNoArgsRetStringNameList(
      ObjectCalls.getMethodBind("Node", "get_groups", 3995934104L),
      grpNode,
    )
  check("typed-array-ret(get_groups==[kgrp])", groups == listOf("kgrp"))

  // Typed Array[int] return (Phase 2.7g). A freshly constructed (treeless) Node is an orphan, so
  // get_orphan_node_ids() contains its instance id — exercises ptrcallNoArgsRetLongList, which
  // reads the int64 blob through retTypedArrayBlob: one engine call (task 129 D).
  val orphan = ObjectCalls.constructObject("Node")
  val orphanId =
    ObjectCalls.ptrcallNoArgsRetLong(
      ObjectCalls.getMethodBind("Object", "get_instance_id", 3905245786L),
      orphan,
    )
  val orphanIds =
    ObjectCalls.ptrcallNoArgsRetLongList(
      ObjectCalls.getMethodBind("Node", "get_orphan_node_ids", 2915620761L),
      orphan,
    )
  check("typed-array-ret(get_orphan_node_ids contains id)", orphanIds.contains(orphanId))

  // Projection POD return (Phase 2.7h). Camera3D.get_camera_projection() — 16 float32 (4
  // column-major Vector4 columns), the same POD-return path as the device-proven Transform3D (12
  // floats). Camera3D is a Node3D (safe at scene init).
  //
  // The old assertion was `isFinite()` on the four diagonal cells, which 0.0f satisfies — so the
  // zeroed 64-byte return buffer of the no-op path passed it, and it was not a probe (task 117
  // P2' follow-up 5). Its stated reason, "treeless camera projection values aren't deterministic
  // (no viewport aspect)", is also wrong: Camera3D::get_camera_projection opens with
  // ERR_FAIL_COND_V_MSG(!is_inside_tree(), Projection(), ...) (scene/3d/camera_3d.cpp:299-302),
  // and `Projection` is `= default` over member initialisers that spell the IDENTITY matrix
  // (core/math/projection.h:55-60, 159). A treeless camera therefore returns exactly the identity
  // — deterministic, and a diagonal of 1.0 with a 0.0 off-diagonal is a value the zeroed buffer
  // cannot produce. Godot also prints "Camera is not inside the scene tree." for this call; that
  // is the expected engine error for the row, not a Kanama fault.
  val projCam = requireObject("Camera3D")
  if (projCam.address() != 0L) {
    val proj =
      ObjectCalls.ptrcallNoArgsRetProjection(
        ObjectCalls.getMethodBind("Camera3D", "get_camera_projection", 2910717950L),
        projCam,
      )
    check(
      "projection-ret(get_camera_projection == identity)",
      proj.x.x.toDouble() == 1.0 &&
        proj.y.y.toDouble() == 1.0 &&
        proj.z.z.toDouble() == 1.0 &&
        proj.w.w.toDouble() == 1.0 &&
        proj.x.y.toDouble() == 0.0 &&
        proj.w.z.toDouble() == 0.0,
    )
  } else check("projection-ret(Camera3D.get_camera_projection) (instance absent)", false)

  // Array[Plane] return (Phase 2.7i). Camera3D.get_frustum() on a treeless camera returns an EMPTY
  // list, deterministically: ERR_FAIL_COND_V(!is_inside_world(), Vector<Plane>())
  // (scene/3d/camera_3d.cpp:792-798). This row therefore exercises the Array size=0 path only, and
  // by the probe rule its `all { isFinite() }` is vacuously true — it is NOT on its own evidence
  // that the Plane record decode works. That evidence is the non-default
  // `array-ret(Geometry3D.build_box_planes has 6 planes)` row above, which drives the same
  // fixed-size-record blob machinery. Kept for the size=0 path; a populated frustum needs a
  // viewport, which does not exist at scene-level extension init.
  val frustumCam = requireObject("Camera3D")
  if (frustumCam.address() != 0L) {
    val frustum =
      ObjectCalls.ptrcallNoArgsRetPlaneList(
        ObjectCalls.getMethodBind("Camera3D", "get_frustum", 3995934104L),
        frustumCam,
      )
    // Off-world the engine answers an EMPTY list (scene/3d/camera_3d.cpp:792-798), so that is the
    // falsifiable expectation here; `all {}` on an empty list was vacuously true (review nit).
    check("plane-array-ret(get_frustum off-world == empty)", frustum.isEmpty())
  } else check("plane-array-ret(Camera3D.get_frustum) (instance absent)", false)

  // Generic Array return (Phase 2.7j). parent + named child; parent.get_node_and_resource("Kid")
  // returns [node, resource, subpath] == [childHandle, null, NodePath("")]. Exercises the
  // variant-array blob (mixed Object/null/NodePath element decode) with a NodePath arg.
  val ndrParent = ObjectCalls.constructObject("Node")
  val ndrChild = ObjectCalls.constructObject("Node")
  ObjectCalls.ptrcallWithStringNameArg(
    ObjectCalls.getMethodBind("Node", "set_name", 3304788590L),
    ndrChild,
    "Kid",
  )
  ObjectCalls.ptrcallWithObjectBoolLongArgs(
    ObjectCalls.getMethodBind("Node", "add_child", 3863233950L),
    ndrParent,
    ndrChild,
    false,
    0L,
  )
  val ndr =
    ObjectCalls.ptrcallWithNodePathArgRetArray(
      ObjectCalls.getMethodBind("Node", "get_node_and_resource", 502563882L),
      ndrParent,
      NodePath("Kid"),
    )
  check(
    "variant-array-ret(get_node_and_resource==[child,null,path])",
    ndr.size == 3 &&
      (ndr[0] as? GodotObject)?.segment?.address() == ndrChild.address() &&
      ndr[1] == null &&
      ndr[2] is NodePath,
  )

  // PackedFloat32Array-return (Gradient.get_offsets): a fresh Gradient seeds two default
  // points at offsets 0.0 and 1.0, so get_offsets() == [0.0, 1.0] — read back through
  // ptrcallNoArgsRetPackedFloat32List (float32 element variant). Gradient is a plain
  // Resource, safe to construct at init. Phase 2.7c-2.
  val gradient = ObjectCalls.constructObject("Gradient")
  val offsets =
    ObjectCalls.ptrcallNoArgsRetPackedFloat32List(
      ObjectCalls.getMethodBind("Gradient", "get_offsets", 675695659L),
      gradient,
    )
  check("packed-float32-ret(get_offsets==[0,1])", offsets == listOf(0.0f, 1.0f))

  // PackedFloat32Array-arg (Gradient.set_offsets): build a list C-side and set it, then read
  // it back. Exercises the build-from-list arg path end to end. Phase 2.7c-4.
  val gradientA = ObjectCalls.constructObject("Gradient")
  ObjectCalls.ptrcallWithPackedFloat32ListArg(
    ObjectCalls.getMethodBind("Gradient", "set_offsets", 2899603908L),
    gradientA,
    listOf(0.25f, 0.5f, 0.75f),
  )
  val offsetsBack =
    ObjectCalls.ptrcallNoArgsRetPackedFloat32List(
      ObjectCalls.getMethodBind("Gradient", "get_offsets", 675695659L),
      gradientA,
    )
  check(
    "packed-float32-arg(set/get_offsets==[0.25,0.5,0.75])",
    offsetsBack == listOf(0.25f, 0.5f, 0.75f),
  )

  // PackedVector2Array-return (Line2D.get_points): add two points via the Vector2 arg path,
  // then read them back through ptrcallNoArgsRetPackedVector2List (2 float32 per element).
  // Line2D is a Node2D, safe at init. Phase 2.7c-3.
  val line2d = ObjectCalls.constructObject("Line2D")
  val line2dCallBind = ObjectCalls.getMethodBind("Object", "call", 3400424181L)
  ObjectCalls.callWithVariantArgs(
    line2dCallBind,
    line2d,
    listOf("add_point", Vector2(1.5, 2.5), -1L),
  )
  ObjectCalls.callWithVariantArgs(
    line2dCallBind,
    line2d,
    listOf("add_point", Vector2(3.5, 4.5), -1L),
  )
  val points =
    ObjectCalls.ptrcallNoArgsRetPackedVector2List(
      ObjectCalls.getMethodBind("Line2D", "get_points", 2961356807L),
      line2d,
    )
  check(
    "packed-vector2-ret(get_points==[(1.5,2.5),(3.5,4.5)])",
    points == listOf(Vector2(1.5, 2.5), Vector2(3.5, 4.5)),
  )

  // PackedColorArray-return (Gradient.get_colors): a fresh Gradient seeds default colors
  // black and white -> read back through ptrcallNoArgsRetPackedColorList (4 float32 per
  // element). Phase 2.7c-3.
  val gradientC = ObjectCalls.constructObject("Gradient")
  val colors =
    ObjectCalls.ptrcallNoArgsRetPackedColorList(
      ObjectCalls.getMethodBind("Gradient", "get_colors", 1392750486L),
      gradientC,
    )
  check(
    "packed-color-ret(get_colors==[black,white])",
    colors == listOf(Color(0.0, 0.0, 0.0, 1.0), Color(1.0, 1.0, 1.0, 1.0)),
  )

  // PackedStringArray-return (Translation.get_message_list): add two messages via the Variant
  // call path, then read the source keys back through ptrcallNoArgsRetPackedStringList (the
  // variable-length blob decode). Order-independent (map iteration order isn't guaranteed).
  // Translation is a plain Resource, safe at init. Phase 2.7c-5.
  val translation = ObjectCalls.constructObject("Translation")
  val translationCallBind = ObjectCalls.getMethodBind("Object", "call", 3400424181L)
  ObjectCalls.callWithVariantArgs(
    translationCallBind,
    translation,
    listOf("add_message", "hello", "bonjour", ""),
  )
  ObjectCalls.callWithVariantArgs(
    translationCallBind,
    translation,
    listOf("add_message", "bye", "au revoir", ""),
  )
  val messages =
    ObjectCalls.ptrcallNoArgsRetPackedStringList(
      ObjectCalls.getMethodBind("Translation", "get_message_list", 1139954409L),
      translation,
    )
  check(
    "packed-string-ret(get_message_list has hello+bye)",
    messages.size == 2 && messages.toSet() == setOf("hello", "bye"),
  )

  // PackedVector2Array-arg via the generic dispatch (Line2D.set_points): build from List<Vector2>,
  // read back. Validates the inline packed-arg build path the draw_* methods use. Phase 2.7c-6a.
  val line2dArg = ObjectCalls.constructObject("Line2D")
  ObjectCalls.ptrcallWithPackedVector2ListArg(
    ObjectCalls.getMethodBind("Line2D", "set_points", 1509147220L),
    line2dArg,
    listOf(Vector2(1.5, 2.5), Vector2(3.5, 4.5)),
  )
  val pointsArg =
    ObjectCalls.ptrcallNoArgsRetPackedVector2List(
      ObjectCalls.getMethodBind("Line2D", "get_points", 2961356807L),
      line2dArg,
    )
  check(
    "packed-vector2-arg(set/get_points)",
    pointsArg == listOf(Vector2(1.5, 2.5), Vector2(3.5, 4.5)),
  )

  // PackedColorArray-arg via the generic dispatch (Gradient.set_colors): a fresh Gradient has
  // 2 points, so set 2 colors. Phase 2.7c-6a.
  val gradientArg = ObjectCalls.constructObject("Gradient")
  ObjectCalls.ptrcallWithPackedColorListArg(
    ObjectCalls.getMethodBind("Gradient", "set_colors", 3546319833L),
    gradientArg,
    listOf(Color(1.0, 0.0, 0.0, 1.0), Color(0.0, 1.0, 0.0, 1.0)),
  )
  val colorsArg =
    ObjectCalls.ptrcallNoArgsRetPackedColorList(
      ObjectCalls.getMethodBind("Gradient", "get_colors", 1392750486L),
      gradientArg,
    )
  check(
    "packed-color-arg(set/get_colors)",
    colorsArg == listOf(Color(1.0, 0.0, 0.0, 1.0), Color(0.0, 1.0, 0.0, 1.0)),
  )

  // Transform3D arg+return (Node3D.set_transform -> get_transform): 12x float32
  // (9 column-major basis + 3 origin) round-trip through the generated helpers. Pure-
  // scale diagonal basis; all components exact in float32 so data-class == is stable.
  // DEVICE-VALIDATED 2026-06-13 (iPhone 12, iOS 26.5) — Phase 2.5
  val n3t = ObjectCalls.constructObject("Node3D")
  val tIn =
    Transform3D(
      Basis(Vector3(2.0, 0.0, 0.0), Vector3(0.0, 4.0, 0.0), Vector3(0.0, 0.0, 0.5)),
      Vector3(1.25, 2.5, 4.75),
    )
  ObjectCalls.ptrcallWithTransform3DArg(
    ObjectCalls.getMethodBind("Node3D", "set_transform", 2952846383L),
    n3t,
    tIn,
  )
  val tOut =
    ObjectCalls.ptrcallNoArgsRetTransform3D(
      ObjectCalls.getMethodBind("Node3D", "get_transform", 3229777777L),
      n3t,
    )
  check("transform3d(set/get_transform)", tOut == tIn)

  // Transform2D arg+return (Node2D.set_transform -> CanvasItem.get_transform): 6x float32
  // (columns x, y, origin — each Vector2). Node2D overrides CanvasItem.get_transform to
  // return the local _transform, so a bare node round-trips. x=(2,0) y=(0,4) origin=(1.25,2.5)
  // — exact in float32; the asymmetric layout also catches a wrong component order. Phase 2.7a.
  val n2t = ObjectCalls.constructObject("Node2D")
  val t2In = Transform2D(Vector2(2.0, 0.0), Vector2(0.0, 4.0), Vector2(1.25, 2.5))
  ObjectCalls.ptrcallWithTransform2DArg(
    ObjectCalls.getMethodBind("Node2D", "set_transform", 2761652528L),
    n2t,
    t2In,
  )
  val t2Out =
    ObjectCalls.ptrcallNoArgsRetTransform2D(
      ObjectCalls.getMethodBind("CanvasItem", "get_transform", 3814499831L),
      n2t,
    )
  check("transform2d(set/get_transform)", t2Out == t2In)

  // Basis arg+return (Node3D.set_basis -> get_basis): 9x float32 column-major.
  // DEVICE-VALIDATED 2026-06-13 (iPhone 12, iOS 26.5) — Phase 2.5
  val basisNode = ObjectCalls.constructObject("Node3D")
  val bIn = Basis(Vector3(0.5, 0.0, 0.0), Vector3(0.0, 8.0, 0.0), Vector3(0.0, 0.0, 0.25))
  ObjectCalls.ptrcallWithBasisArg(
    ObjectCalls.getMethodBind("Node3D", "set_basis", 1055510324L),
    basisNode,
    bIn,
  )
  val bOut =
    ObjectCalls.ptrcallNoArgsRetBasis(
      ObjectCalls.getMethodBind("Node3D", "get_basis", 2716978435L),
      basisNode,
    )
  check("basis(set/get_basis)", bOut == bIn)

  // RID arg+return (GPUParticles3D get_base/set_base): the auto-assigned particles
  // base RID is read, set back, and read again — round-trips the uint64.
  // DEVICE-VALIDATED 2026-06-13 (iPhone 12, iOS 26.5) — RID/Quaternion/AABB kinds
  val gpRid = ObjectCalls.constructObject("GPUParticles3D")
  val rid1 =
    ObjectCalls.ptrcallNoArgsRetRID(
      ObjectCalls.getMethodBind("VisualInstance3D", "get_base", 2944877500L),
      gpRid,
    )
  ObjectCalls.ptrcallWithRIDArg(
    ObjectCalls.getMethodBind("VisualInstance3D", "set_base", 2722037293L),
    gpRid,
    rid1,
  )
  val rid2 =
    ObjectCalls.ptrcallNoArgsRetRID(
      ObjectCalls.getMethodBind("VisualInstance3D", "get_base", 2944877500L),
      gpRid,
    )
  check("rid(get/set_base)", rid1.value != 0L && rid2 == rid1)

  // AABB arg+return (GPUParticles3D set/get_custom_aabb): 6x float32, exact round-trip.
  // DEVICE-VALIDATED 2026-06-13 (iPhone 12, iOS 26.5) — RID/Quaternion/AABB kinds
  val gpAabb = ObjectCalls.constructObject("GPUParticles3D")
  val aIn = AABB(Vector3(1.5, 2.5, 3.5), Vector3(4.5, 5.5, 6.5))
  ObjectCalls.ptrcallWithAABBArg(
    ObjectCalls.getMethodBind("GeometryInstance3D", "set_custom_aabb", 259215842L),
    gpAabb,
    aIn,
  )
  val aOut =
    ObjectCalls.ptrcallNoArgsRetAABB(
      ObjectCalls.getMethodBind("GeometryInstance3D", "get_custom_aabb", 1068685055L),
      gpAabb,
    )
  check("aabb(set/get_custom_aabb)", aOut == aIn)

  // Quaternion arg+return (Node3D set/get_quaternion): 4x float32 [x,y,z,w]. Godot
  // re-derives the quaternion from a basis, so compare with epsilon. Unit (0.5×4).
  // DEVICE-VALIDATED 2026-06-13 (iPhone 12, iOS 26.5) — RID/Quaternion/AABB kinds
  val quatNode = ObjectCalls.constructObject("Node3D")
  val qIn = Quaternion(0.5, 0.5, 0.5, 0.5)
  ObjectCalls.ptrcallWithQuaternionArg(
    ObjectCalls.getMethodBind("Node3D", "set_quaternion", 1727505552L),
    quatNode,
    qIn,
  )
  val qOut =
    ObjectCalls.ptrcallNoArgsRetQuaternion(
      ObjectCalls.getMethodBind("Node3D", "get_quaternion", 1222331677L),
      quatNode,
    )
  fun nearly(a: Number, b: Number): Boolean {
    val left = a.toDouble()
    val right = b.toDouble()
    return (if (left > right) left - right else right - left) < 1e-4
  }
  check(
    "quaternion(set/get_quaternion)",
    nearly(qOut.x, qIn.x) && nearly(qOut.y, qIn.y) && nearly(qOut.z, qIn.z) && nearly(qOut.w, qIn.w),
  )

  // Variant Object.call dispatch (ObjectCalls.callWithVariantArgs): the Kotlin marshalling
  // path — Any? args -> PT-tagged Variants C-side -> object_method_bind_call -> scalar return
  // decode. Node (not a Control) avoids the treeless-Control post-init Theme segfault.
  // (iOS Variant Object.call dispatch — the 5 IosGodotApi STUBs)
  val callBind = ObjectCalls.getMethodBind("Object", "call", 3400424181L)
  val callNode = ObjectCalls.constructObject("Node")
  // String value arg: call("set_name", "KCall") then read it back via ptrcall.
  ObjectCalls.callWithVariantArgs(callBind, callNode, listOf("set_name", "KCall"))
  check(
    "variant-call-arg(set_name)",
    ObjectCalls.ptrcallNoArgsRetStringName(
      ObjectCalls.getMethodBind("Node", "get_name", 2002593661L),
      callNode,
    ) == "KCall",
  )
  // String return decode: call("get_class") -> "Node".
  check(
    "variant-call-ret-string(get_class==Node)",
    ObjectCalls.callWithVariantArgs(callBind, callNode, listOf("get_class")) == "Node",
  )
  // Int return decode: call("get_child_count") -> 0L on a fresh node.
  check(
    "variant-call-ret-int(get_child_count==0)",
    ObjectCalls.callWithVariantArgs(callBind, callNode, listOf("get_child_count")) == 0L,
  )
  // Bool return decode: call("is_inside_tree") -> false (not in a tree).
  check(
    "variant-call-ret-bool(is_inside_tree==false)",
    ObjectCalls.callWithVariantArgs(callBind, callNode, listOf("is_inside_tree")) == false,
  )
  // Int VALUE arg + int return via set_meta/get_meta (the boxing path set_deferred uses).
  ObjectCalls.callWithVariantArgs(callBind, callNode, listOf("set_meta", "kcall", 4242L))
  check(
    "variant-call-value-int(set_meta/get_meta)",
    ObjectCalls.callWithVariantArgs(callBind, callNode, listOf("get_meta", "kcall")) == 4242L,
  )
  // Object VALUE arg (GodotObject) + Object return via set_meta/get_meta.
  val metaObj = ObjectCalls.constructObject("Node")
  ObjectCalls.callWithVariantArgs(
    callBind,
    callNode,
    listOf("set_meta", "kobj", GodotObject(GodotHandle(metaObj))),
  )
  val gotObj = ObjectCalls.callWithVariantArgs(callBind, callNode, listOf("get_meta", "kobj"))
  check(
    "variant-call-value-object(set_meta/get_meta object)",
    gotObj is GodotObject && gotObj.segment.address() == metaObj.address(),
  )
  // task 121 — container RETURNS through the Variant call path (before, every container return
  // surfaced null on iOS). Container ARGS are exercised by the task-122 rows below; these fixtures
  // come from the engine: an int Array round
  // trip via set_meta/get_meta, Object.get_property_list (Array of Dictionaries with String
  // values) and Engine.get_singleton_list (a PackedStringArray -> List<String>).
  ObjectCalls.callWithVariantArgs(callBind, callNode, listOf("set_meta", "kints", listOf(1L, 2L)))
  check(
    "variant-call-ret-array(set_meta/get_meta [1,2])",
    ObjectCalls.callWithVariantArgs(callBind, callNode, listOf("get_meta", "kints")) ==
      listOf(1L, 2L),
  )
  val propertyList =
    ObjectCalls.callWithVariantArgs(callBind, callNode, listOf("get_property_list"))
  val firstProperty = (propertyList as? List<*>)?.firstOrNull() as? Map<*, *>
  check(
    "variant-call-ret-array-of-dictionaries(get_property_list)",
    propertyList is List<*> && propertyList.isNotEmpty() && firstProperty?.get("name") is String,
  )
  // Engine.get_singleton_list() -> PackedStringArray (Node has only the virtual
  // _get_configuration_warnings; calling that through Object.call is an invalid-method error).
  val callEngine = requireSingleton("Engine")
  if (callEngine.address() != 0L) {
    val singletonNames =
      ObjectCalls.callWithVariantArgs(callBind, callEngine, listOf("get_singleton_list"))
    check(
      "variant-call-ret-packed-strings(Engine.get_singleton_list contains Engine)",
      singletonNames is List<*> && singletonNames.contains("Engine"),
    )
  } else
    check("variant-call-ret-packed-strings(Engine.get_singleton_list) (singleton absent)", false)
  // task 122 — container ARGUMENTS: List<String>, List<Double>, List<Vector2>, a Dictionary and a
  // nested container round-trip set_meta/get_meta (the arg encoder used to throw on all of them).
  ObjectCalls.callWithVariantArgs(
    callBind,
    callNode,
    listOf("set_meta", "kstrs", listOf("a", "bb")),
  )
  check(
    "variant-call-arg-array-strings(set_meta/get_meta [a,bb])",
    ObjectCalls.callWithVariantArgs(callBind, callNode, listOf("get_meta", "kstrs")) ==
      listOf("a", "bb"),
  )
  ObjectCalls.callWithVariantArgs(callBind, callNode, listOf("set_meta", "kdbl", listOf(1.5, 2.5)))
  check(
    "variant-call-arg-array-doubles(set_meta/get_meta [1.5,2.5])",
    ObjectCalls.callWithVariantArgs(callBind, callNode, listOf("get_meta", "kdbl")) ==
      listOf(1.5, 2.5),
  )
  ObjectCalls.callWithVariantArgs(
    callBind,
    callNode,
    listOf("set_meta", "kvec", listOf(Vector2(1.0, 2.0), Vector2(3.0, 4.0))),
  )
  check(
    "variant-call-arg-array-vector2(set_meta/get_meta)",
    ObjectCalls.callWithVariantArgs(callBind, callNode, listOf("get_meta", "kvec")) ==
      listOf(Vector2(1.0, 2.0), Vector2(3.0, 4.0)),
  )
  ObjectCalls.callWithVariantArgs(
    callBind,
    callNode,
    listOf("set_meta", "kdict", mapOf("k" to 1L, "s" to "v")),
  )
  check(
    "variant-call-arg-dictionary(set_meta/get_meta {k:1,s:v})",
    ObjectCalls.callWithVariantArgs(callBind, callNode, listOf("get_meta", "kdict")) ==
      mapOf("k" to 1L, "s" to "v"),
  )
  ObjectCalls.callWithVariantArgs(
    callBind,
    callNode,
    listOf("set_meta", "knest", listOf(listOf(1L, 2L), mapOf("a" to "b"))),
  )
  check(
    "variant-call-arg-nested(set_meta/get_meta [[1,2],{a:b}])",
    ObjectCalls.callWithVariantArgs(callBind, callNode, listOf("get_meta", "knest")) ==
      listOf(listOf(1L, 2L), mapOf("a" to "b")),
  )
  // task 122 — an object ELEMENT of a container argument round-trips as a borrowed wrapper of the
  // same instance (the meta Array keeps it alive; sole-reference elements are the 122 follow-up).
  val refElem = ObjectCalls.constructObject("RefCounted")
  ObjectCalls.callWithVariantArgs(
    callBind,
    callNode,
    listOf("set_meta", "kref", listOf(GodotObject(GodotHandle(refElem)))),
  )
  val refBack =
    (ObjectCalls.callWithVariantArgs(callBind, callNode, listOf("get_meta", "kref")) as? List<*>)
      ?.firstOrNull()
  check(
    "variant-call-arg-array-object-element(set_meta/get_meta same instance)",
    refBack is GodotObject && refBack.segment.address() == refElem.address(),
  )
  // task 122 — top-level NodePath argument (was accepted only inside containers). The scalar
  // return path hands a NODE_PATH back as a NodePath, as desktop does (it was the path's String).
  ObjectCalls.callWithVariantArgs(callBind, callNode, listOf("set_meta", "knp", NodePath("A/B")))
  check(
    "variant-call-arg-nodepath(set_meta/get_meta A/B)",
    ObjectCalls.callWithVariantArgs(callBind, callNode, listOf("get_meta", "knp")) ==
      NodePath("A/B"),
  )
  runVariantTypeRows(callBind, callNode, ::check)

  // Value-type builtin method (BuiltinCalls) via variant_get_ptr_builtin_method + builtin_call.
  // Basis.inverse() is a TRUE inverse: diag(2,4,8) -> diag(0.5,0.25,0.125) (powers of 2, exact
  // float32). Transform3D.inverse() assumes orthonormal — it transposes the basis (diag stays
  // diag) and sets origin = basisᵀ·(-origin); diag(2,4,8) + origin(1,2,3) -> diag(2,4,8) +
  // origin(-2,-8,-24). Both exact in float32 so data-class == is stable; the nonzero origin
  // proves the 12-float layout + that the engine actually transformed it (not a passthrough).
  val bScale = Basis(Vector3(2.0, 0.0, 0.0), Vector3(0.0, 4.0, 0.0), Vector3(0.0, 0.0, 8.0))
  check(
    "builtin-call(Basis.inverse diag)",
    bScale.inverse() ==
      Basis(Vector3(0.5, 0.0, 0.0), Vector3(0.0, 0.25, 0.0), Vector3(0.0, 0.0, 0.125)),
  )
  val tScale = Transform3D(bScale, Vector3(1.0, 2.0, 3.0))
  check(
    "builtin-call(Transform3D.inverse orthonormal-transpose)",
    tScale.inverse() == Transform3D(bScale, Vector3(-2.0, -8.0, -24.0)),
  )

  // affine_inverse() is the TRUE inverse (incl. scale): basis diag(2,4,8)->diag(0.5,0.25,0.125),
  // origin = inv_basis·(-origin) = (0.5·-1, 0.25·-2, 0.125·-3) = (-0.5,-0.5,-0.375). Exact.
  check(
    "builtin-call(Transform3D.affineInverse scale)",
    tScale.affineInverse() ==
      Transform3D(
        Basis(Vector3(0.5, 0.0, 0.0), Vector3(0.0, 0.25, 0.0), Vector3(0.0, 0.0, 0.125)),
        Vector3(-0.5, -0.5, -0.375),
      ),
  )
  // Basis.transposed(): shear x=(1,0,0),y=(2,1,0),z=(0,0,1) -> x=(1,2,0),y=(0,1,0),z=(0,0,1).
  // Asymmetric so a no-op/wrong-layout fails; exact float32.
  val bShear = Basis(Vector3(1.0, 0.0, 0.0), Vector3(2.0, 1.0, 0.0), Vector3(0.0, 0.0, 1.0))
  check(
    "builtin-call(Basis.transposed shear)",
    bShear.transposed() ==
      Basis(Vector3(1.0, 2.0, 0.0), Vector3(0.0, 1.0, 0.0), Vector3(0.0, 0.0, 1.0)),
  )
  // Basis.orthonormalized(): normalizes each axis — diag(2,4,8) -> IDENTITY. Exact.
  check("builtin-call(Basis.orthonormalized scale)", bScale.orthonormalized() == Basis.IDENTITY)
  // Transform3D.looking_at (arg path: Vector3,Vector3,bool): discards rotation, points -Z at
  // target. From origin 0 a 90°-about-Z base, looking at (0,0,-4) up +Y -> IDENTITY (origin 0).
  // Compared component-wise with `==` (not data-class equals): looking_at's cross products
  // yield -0.0 in some zero slots, and Double.equals treats -0.0 != 0.0 (but `==` doesn't).
  val tRot =
    Transform3D(
      Basis(Vector3(0.0, 1.0, 0.0), Vector3(-1.0, 0.0, 0.0), Vector3(0.0, 0.0, 1.0)),
      Vector3.ZERO,
    )
  val look = tRot.lookingAt(Vector3(0.0, 0.0, -4.0), Vector3(0.0, 1.0, 0.0), false)
  fun vecEq(a: Vector3, b: Vector3) = a.x == b.x && a.y == b.y && a.z == b.z
  val idT = Transform3D.IDENTITY
  check(
    "builtin-call(Transform3D.lookingAt -Z)",
    vecEq(look.basis.x, idT.basis.x) &&
      vecEq(look.basis.y, idT.basis.y) &&
      vecEq(look.basis.z, idT.basis.z) &&
      vecEq(look.origin, idT.origin),
  )
  // Transform3D.interpolate_with (arg path: Transform3D,double) at weight 0.5: midpoint of
  // origin0 and origin(2,4,8) is (1,2,4); identity bases slerp to identity. Exact.
  check(
    "builtin-call(Transform3D.interpolateWith 0.5)",
    Transform3D.IDENTITY.interpolateWith(
      Transform3D(Basis.IDENTITY, Vector3(2.0, 4.0, 8.0)),
      0.5,
    ) == Transform3D(Basis.IDENTITY, Vector3(1.0, 2.0, 4.0)),
  )

  // Vector3.cross (arg path: Vector3 base + Vector3 arg, new VT_VECTOR3): x̂×ŷ = ẑ. The
  // cross product yields -0.0 in the zero slots, so compare component-wise with `==`
  // (numeric, treats -0.0 == 0.0) rather than data-class equals (Double.equals: -0.0 != 0.0).
  val cross = Vector3(1.0, 0.0, 0.0).cross(Vector3(0.0, 1.0, 0.0))
  check(
    "builtin-call(Vector3.cross x*y=z)",
    cross.x.toDouble() == 0.0 && cross.y.toDouble() == 0.0 && cross.z.toDouble() == 1.0,
  )

  // Vector2.rotated (arg path: Vector2 base + double scalar, new VT_VECTOR2): (1,0) by π/2 ->
  // (~0,1). Float trig, so component-wise tolerance ε (also avoids the -0.0 data-class trap).
  val rot = Vector2(1.0, 0.0).rotated(kotlin.math.PI / 2.0)
  check(
    "builtin-call(Vector2.rotated pi/2)",
    kotlin.math.abs(rot.x.toDouble()) < 1e-4 && kotlin.math.abs(rot.y.toDouble() - 1.0) < 1e-4,
  )

  // Quaternion.inverse (no-arg->Self, new VT_QUATERNION): for the unit quaternion 90° about Z
  // = (0,0,sin45,cos45) the inverse is the conjugate (z flips sign, w kept). Float divide by
  // length² so component-wise tolerance ε.
  val s = 0.7071067811865476
  val qInv = Quaternion(0.0, 0.0, s, s).inverse()
  check(
    "builtin-call(Quaternion.inverse 90z)",
    kotlin.math.abs(qInv.x.toDouble()) < 1e-4 &&
      kotlin.math.abs(qInv.y.toDouble()) < 1e-4 &&
      kotlin.math.abs(qInv.z.toDouble() + s) < 1e-4 &&
      kotlin.math.abs(qInv.w.toDouble() - s) < 1e-4,
  )

  // Basis.scaled (arg path: Basis base + Vector3 arg): IDENTITY scaled by (2,3,4) -> diag(2,3,4).
  // Exact float32, but compare component-wise with `==` to dodge any -0.0 in the off-diagonal.
  val scaled = Basis.IDENTITY.scaled(Vector3(2.0, 3.0, 4.0))
  check(
    "builtin-call(Basis.scaled diag)",
    vecEq(scaled.x, Vector3(2.0, 0.0, 0.0)) &&
      vecEq(scaled.y, Vector3(0.0, 3.0, 0.0)) &&
      vecEq(scaled.z, Vector3(0.0, 0.0, 4.0)),
  )

  // Transform3D.translated (arg path: Transform3D base + Vector3 arg): IDENTITY+origin0
  // translated by (1,2,3) -> IDENTITY+origin(1,2,3). Exact; component-wise `==`.
  val moved = Transform3D.IDENTITY.translated(Vector3(1.0, 2.0, 3.0))
  check(
    "builtin-call(Transform3D.translated offset)",
    vecEq(moved.basis.x, Vector3(1.0, 0.0, 0.0)) &&
      vecEq(moved.basis.y, Vector3(0.0, 1.0, 0.0)) &&
      vecEq(moved.basis.z, Vector3(0.0, 0.0, 1.0)) &&
      vecEq(moved.origin, Vector3(1.0, 2.0, 3.0)),
  )

  // Scalar-return builtin (real_t -> float32 on iOS, decoded to Double via callScalar).
  // Vector3.dot: (1,2,3)·(4,5,6) = 32; exact integer in float32 so `==` is stable.
  check(
    "builtin-call(Vector3.dot scalar)",
    Vector3(1.0, 2.0, 3.0).dot(Vector3(4.0, 5.0, 6.0)) == 32.0,
  )
  // Basis.determinant: diag(2,4,8) -> 64; exact integer.
  check(
    "builtin-call(Basis.determinant scalar)",
    Basis(Vector3(2.0, 0.0, 0.0), Vector3(0.0, 4.0, 0.0), Vector3(0.0, 0.0, 8.0)).determinant() ==
      64.0,
  )

  // Bool-return builtin (ptr-ABI uint8 -> Boolean via callBool): Vector3.is_normalized().
  // (0,0,1) is normalized -> true; (2,0,0) is not -> false. Both directions guard the decode.
  check(
    "builtin-call(Vector3.is_normalized bool)",
    Vector3(0.0, 0.0, 1.0).isNormalized() && !Vector3(2.0, 0.0, 0.0).isNormalized(),
  )
  // Int-return value-type method: Vector3.max_axis_index() (Kotlin since task 134 B, a Long like
  // every Godot `int`). (1,5,2) -> Y = 1; (1,2,9) -> Z = 2.
  check(
    "builtin-call(Vector3.max_axis_index int)",
    Vector3(1.0, 5.0, 2.0).maxAxisIndex() == 1L && Vector3(1.0, 2.0, 9.0).maxAxisIndex() == 2L,
  )

  // Value-type @Export decode (Phase 3.2 Step 5 / 2.6): exercise decodeIosPropertyValue's
  // tag dispatch — the Kotlin side of the set-property value path. The C side ships float32
  // component buffers (Vector2/Vector3) and utf8 path bytes (NodePath) with PT_* tags; this
  // reproduces those exact buffers and asserts the decoded value. Components compared numerically
  // (1.5/-2.5/1/2/3 are exact in float32->double, no -0.0 in play).
  memScoped {
    val v2 = allocArray<FloatVar>(2)
    v2[0] = 1.5f
    v2[1] = -2.5f
    val dv2 = decodeIosPropertyValue(6, v2.reinterpret(), 8) as? Vector2
    check(
      "setprop-decode(Vector2)",
      dv2 != null && dv2.x.toDouble() == 1.5 && dv2.y.toDouble() == -2.5,
    )

    val v3 = allocArray<FloatVar>(3)
    v3[0] = 1.0f
    v3[1] = 2.0f
    v3[2] = 3.0f
    val dv3 = decodeIosPropertyValue(8, v3.reinterpret(), 12) as? Vector3
    check(
      "setprop-decode(Vector3)",
      dv3 != null && dv3.x.toDouble() == 1.0 && dv3.y.toDouble() == 2.0 && dv3.z.toDouble() == 3.0,
    )

    val path = "../SceneTarget3D"
    val pathBytes = path.encodeToByteArray()
    val pb = allocArray<ByteVar>(pathBytes.size)
    for (i in pathBytes.indices) pb[i] = pathBytes[i]
    val dnp = decodeIosPropertyValue(17, pb, pathBytes.size) as? NodePath
    check("setprop-decode(NodePath)", dnp != null && dnp.path == path)
  }

  // Inbound call-arg decode (Phase 3.3): decodeIosCallArg over the scalar/vector2i/string/object
  // tags — the Kotlin side of the generic method-call path (the C side ships these exact buffers
  // in kanama_ios_script_instance_call). Tags mirror KANAMA_IOS_PT_* (INT64=3, BOOL=1, FLOAT64=5,
  // VECTOR2I=7, OBJECT=13, STRING=16).
  memScoped {
    val li = alloc<LongVar>()
    li.value = 1234567L
    check("callarg-decode(Long)", decodeIosCallArg(3, li.ptr.reinterpret()) == 1234567L)
    val bo = alloc<ByteVar>()
    bo.value = 1
    check("callarg-decode(Bool)", decodeIosCallArg(1, bo.ptr.reinterpret()) == true)
    val db = alloc<DoubleVar>()
    db.value = 2.5
    check("callarg-decode(Double)", decodeIosCallArg(5, db.ptr.reinterpret()) == 2.5)
    val v2i = allocArray<IntVar>(2)
    v2i[0] = 3
    v2i[1] = 4
    check("callarg-decode(Vector2i)", decodeIosCallArg(7, v2i.reinterpret()) == Vector2i(3, 4))
    val ob = alloc<LongVar>()
    ob.value = 99L
    check("callarg-decode(Object handle)", decodeIosCallArg(13, ob.ptr.reinterpret()) == 99L)
    val sb = "hello".encodeToByteArray()
    val sp = allocArray<ByteVar>(sb.size + 1)
    for (i in sb.indices) sp[i] = sb[i]
    sp[sb.size] = 0
    check("callarg-decode(String)", decodeIosCallArg(16, sp) == "hello")
  }

  // @Rpc config delivery (descriptor -> accessor seam): register a synthetic descriptor with
  // distinct non-zero values and assert the scriptResourceRpcConfig* accessors read them back in
  // order. Pairs with the C-side _get_rpc_config Dictionary readback in
  // kanama_ios_ptrcall_selftest. Distinct non-zero values catch field-swaps (mode/transferMode/
  // channel) and out-of-order indexing.
  run {
    val rpcPath = "res://kanama_ios_rpc_selftest.kt"
    KanamaIosProjectRegistry.register(
      KanamaIosScriptDescriptor(
        path = rpcPath,
        baseType = "Node",
        methods = emptyList(),
        properties = emptyList(),
        signals = emptyList(),
        rpcConfigs =
          listOf(
            KanamaIosRpcConfig(
              "net_score",
              mode = 1,
              callLocal = true,
              transferMode = 2,
              channel = 3,
            ),
            KanamaIosRpcConfig(
              "sync_pos",
              mode = 2,
              callLocal = false,
              transferMode = 0,
              channel = 5,
            ),
          ),
        factory = { null },
      )
    )
    val handle = KanamaIosRuntime.createScriptResource(rpcPath)
    check("rpc-config-count", KanamaIosRuntime.scriptResourceRpcConfigCount(handle) == 2)
    check(
      "rpc-config[0]",
      KanamaIosRuntime.scriptResourceRpcConfigMethodName(handle, 0) == "net_score" &&
        KanamaIosRuntime.scriptResourceRpcConfigMode(handle, 0) == 1 &&
        KanamaIosRuntime.scriptResourceRpcConfigCallLocal(handle, 0) == 1 &&
        KanamaIosRuntime.scriptResourceRpcConfigTransferMode(handle, 0) == 2 &&
        KanamaIosRuntime.scriptResourceRpcConfigChannel(handle, 0) == 3,
    )
    check(
      "rpc-config[1]",
      KanamaIosRuntime.scriptResourceRpcConfigMethodName(handle, 1) == "sync_pos" &&
        KanamaIosRuntime.scriptResourceRpcConfigMode(handle, 1) == 2 &&
        KanamaIosRuntime.scriptResourceRpcConfigCallLocal(handle, 1) == 0 &&
        KanamaIosRuntime.scriptResourceRpcConfigTransferMode(handle, 1) == 0 &&
        KanamaIosRuntime.scriptResourceRpcConfigChannel(handle, 1) == 5,
    )
    KanamaIosRuntime.freeScriptResource(handle)
  }

  // Callable-arg helper (task 09 / Phase 1.4): the generated ptrcallWithThreeCallableArgs builds a
  // KanamaIosCallableArgDesc from each (target.handle, method) pair and drives the PT_CALLABLE
  // ptrcall path. Control has a lightweight constructor (safe at SCENE-init, unlike TextEdit), and
  // set_drag_forwarding takes three Callables -> void, exercising the multi-cell layout end to end
  // from Kotlin. Fire-later sink with no getter, so this checks the Kotlin helper -> C construct/
  // pass/destroy round-trip survives (a marshalling or destroy bug crashes here or on a later
  // alloc).
  run {
    val ctrl = ObjectCalls.constructObject("Control")
    ObjectCalls.ptrcallWithThreeCallableArgs(
      ObjectCalls.getMethodBind("Control", "set_drag_forwarding", 1076571380L),
      ctrl,
      ctrl,
      "get_class",
      ctrl,
      "get_class",
      ctrl,
      "get_class",
    )
    check("callable-args-x3 (ptrcallWithThreeCallableArgs)", ctrl.address() != 0L)
  }

  // Typed Godot enums (task 128 A): one enum setter/getter pair and one bitfield round trip through
  // the generated wrappers' real ptrcall path (`.value` at the argument boundary, `X(raw)` at the
  // return). A detached Node starts at ProcessMode.INHERIT (0) and no thread-message flags (0), so
  // a
  // no-op setter or a getter that returns 0 fails both rows.
  run {
    val node = net.multigesture.kanama.api.Node(GodotHandle(ObjectCalls.constructObject("Node")))
    node.setProcessMode(net.multigesture.kanama.api.Node.ProcessMode.ALWAYS)
    val mode = node.getProcessMode()
    check(
      "typed-enum(Node.setProcessMode/getProcessMode ALWAYS round-trip)",
      mode == net.multigesture.kanama.api.Node.ProcessMode.ALWAYS && mode.value == 3L,
    )
    val flags =
      net.multigesture.kanama.api.Node.ProcessThreadMessages.MESSAGES or
        net.multigesture.kanama.api.Node.ProcessThreadMessages.MESSAGES_PHYSICS
    node.setProcessThreadMessages(flags)
    val back = node.getProcessThreadMessages()
    check(
      "typed-bitfield(Node.setProcessThreadMessages/get MESSAGES|MESSAGES_PHYSICS round-trip)",
      back.value == 3L &&
        net.multigesture.kanama.api.Node.ProcessThreadMessages.MESSAGES_PHYSICS in back &&
        back == net.multigesture.kanama.api.Node.ProcessThreadMessages.MESSAGES_ALL,
    )
    // The dynamic Variant path (task 128 A follow-up): a typed enum handed to an `Any?` argument is
    // encoded as INT with its value. PAUSABLE (1) differs from the ALWAYS (3) set above and from a
    // nil/no-op 0; before the encoder knew GodotEnumValue this threw "unsupported Variant
    // argument".
    node.set("process_mode", net.multigesture.kanama.api.Node.ProcessMode.PAUSABLE)
    check(
      "typed-enum-dynamic(Object.set process_mode PAUSABLE -> INT 1)",
      node.getProcessMode() == net.multigesture.kanama.api.Node.ProcessMode.PAUSABLE,
    )
    ObjectCalls.destroyObject(node.segment)
    net.multigesture.kanama.api.ConfigFile.create().use { config ->
      config.setValue("video", "display_mode", net.multigesture.kanama.api.Window.Mode.FULLSCREEN)
      check(
        "typed-enum-dynamic(ConfigFile.setValue Window.Mode.FULLSCREEN -> Long 3)",
        (config.getValue("video", "display_mode") as? Number)?.toLong() == 3L,
      )
    }
    // InputEventMouseButton.create() (task 128 C): an owned event with a typed button, added to an
    // action. The action keeps its own reference, so after the wrapper's close() a fresh RIGHT
    // event still matches the action (the event survived); then the scratch action is erased.
    run {
      val im = net.multigesture.kanama.api.InputMap
      val rmb = net.multigesture.kanama.api.MouseButton.RIGHT
      val action = "kanama_selftest_mouse_action"
      im.addAction(action)
      val button = net.multigesture.kanama.api.InputEventMouseButton.create()
      button.setButtonIndex(rmb)
      val readBack = button.getButtonIndex() == rmb
      im.actionAddEvent(action, button)
      val hasEvent = im.actionHasEvent(action, button)
      button.close()
      val probe = net.multigesture.kanama.api.InputEventMouseButton.create()
      probe.setButtonIndex(rmb)
      val survives = probe.isAction(action)
      probe.close()
      im.eraseAction(action)
      check(
        "input-mouse-button(create + setButtonIndex RIGHT + actionAddEvent, survives close, erase)",
        readBack && hasEvent && survives && !im.hasAction(action),
      )
    }
  }

  // RefCounted return-slot ownership (task 31 iOS mirror): every RefCounted-typed ptrcall
  // return transfers a +1 reference the wrapper owns (meta:"required" included — measured on
  // 4.7-stable, task 31). duplicate() hands back a fresh Resource whose only reference is the
  // return-slot +1, so its engine refcount must read exactly 1; close() then releases it
  // (unreference -> zero -> object_destroy). A wrong convention shows up as refcount != 1
  // (probe fails) or a double-free crash right here.
  run {
    val res =
      net.multigesture.kanama.api.Resource(GodotHandle(ObjectCalls.constructObject("Resource")))
    val dup = res.duplicate()
    check("refcounted-ret-owns-plus1", dup != null && dup.getReferenceCount() == 1)
    dup?.close() // unreference() -> true at zero -> destroyObject; crash/guardrail-noise = fail
    dup?.close() // second close must be a released-guard no-op, not a double unreference
    ObjectCalls.destroyObject(res.segment) // probe object was never referenced (rc 0)
  }

  // Task 61 / issue #91 (iOS ownership guard): unlike the desktop/JVM backend (which constructs via
  // classdb_construct_object2 -> an unclaimed placeholder that needs an init_ref claim), the iOS
  // backend constructs via classdb_construct_object3 (instantiate_..._with_refcount), so an
  // X.create() wrapper is ALREADY owning — no claim is added on iOS. This guards that ownership:
  // create() reads refcount 1, and after handing the event to the engine and close()ing the
  // wrapper, the event survives (the engine keeps its own reference). A regression that made iOS
  // construct non-owning, or that re-added an init_ref claim (double-reference/leak), trips here.
  run {
    val action = "kanama_t61_selftest"
    if (!net.multigesture.kanama.api.InputMap.hasAction(action)) {
      net.multigesture.kanama.api.InputMap.addAction(action)
    }
    net.multigesture.kanama.api.InputMap.actionEraseEvents(action)
    val key = net.multigesture.kanama.api.InputEventKey.create()
    key.setKeycode(net.multigesture.kanama.api.Key.A)
    // construct_object3 already claimed the reference: the fresh event's refcount reads exactly 1
    // (a re-added init_ref claim would over-reference to 2 and leak).
    check("t61-create-owns-plus1", key.getReferenceCount() == 1)
    net.multigesture.kanama.api.InputMap.actionAddEvent(action, key) // engine takes its own +1
    key.close() // releases the wrapper's +1; pre-fix this freed the engine's event (rc 1 -> 0)
    val probe = net.multigesture.kanama.api.InputEventKey.create()
    probe.setKeycode(net.multigesture.kanama.api.Key.A)
    // The event survived the close and is still held by InputMap (an equal event is found).
    check(
      "t61-create-close-survives-handoff",
      net.multigesture.kanama.api.InputMap.actionHasEvent(action, probe),
    )
    probe.close()
    net.multigesture.kanama.api.InputMap.actionEraseEvents(action)
  }

  // task 100 (parcel 11) — Callable RETURNS through the GENERATED helpers (ptrcallRetCallable ->
  // kanama_ios_godot_ptrcall_ret_callable: one ptrcall into a Callable cell, get_object /
  // get_method, borrowed handle + UTF-8 name back; an empty Callable is null, as on desktop).
  // MultiplayerSpawner is a plain Node and NativeMenu a singleton: nothing here touches a physics
  // or navigation server, and no Control is constructed (a Control's _postinitialize runs
  // ThemeDB::update_class_instance_items before the theme contexts exist and segfaults — the
  // first version of these rows used Tree/TreeItem and the 10+11 stack gate caught it).
  run {
    val spawner = ObjectCalls.constructObject("MultiplayerSpawner")
    val setBind = ObjectCalls.getMethodBind("MultiplayerSpawner", "set_spawn_function", 1611583062L)
    val getBind = ObjectCalls.getMethodBind("MultiplayerSpawner", "get_spawn_function", 1307783378L)
    val empty = ObjectCalls.ptrcallNoArgsRetCallable(getBind, spawner)
    check("ret-callable(MultiplayerSpawner.get_spawn_function empty -> null)", empty == null)
    ObjectCalls.ptrcallWithCallableArg(setBind, spawner, spawner, "queue_free")
    val back = ObjectCalls.ptrcallNoArgsRetCallable(getBind, spawner)
    check(
      "ret-callable(MultiplayerSpawner.get_spawn_function target handle round-trip)",
      back != null && back.target.segment.address() == spawner.address(),
    )
    check(
      "ret-callable(MultiplayerSpawner.get_spawn_function method name round-trip)",
      back?.method == "queue_free",
    )
    // A method name longer than the 256-byte inline buffer comes back whole through the pending
    // slot (402 chars, 602 UTF-8 bytes: the non-ASCII half proves the byte/char accounting).
    val longName = "m_" + "\u00e9x".repeat(200)
    ObjectCalls.ptrcallWithCallableArg(setBind, spawner, spawner, longName)
    val backLong = ObjectCalls.ptrcallNoArgsRetCallable(getBind, spawner)
    check(
      "ret-callable(MultiplayerSpawner.get_spawn_function 602-byte method via pending slot)",
      backLong != null &&
        backLong.method == longName &&
        backLong.target.segment.address() == spawner.address(),
    )
    ObjectCalls.destroyObject(spawner)
    // The RID-argument Callable shape on the NativeMenu singleton MOVED to the frame-1 phase
    // (task 117 P2' follow-up 5). Engine gets its "NativeMenu" entry from
    // register_server_singletons() (servers/register_server_types.cpp:400) — the same late step
    // that registers RenderingServer, run at main/main.cpp:3864, long after
    // initialize_extensions(INITIALIZATION_LEVEL_SCENE) at main/main.cpp:3793 — so at scene init
    // the lookup returns 0 and the row was a second false pass (guard -> null callable == null).
  }

  // task 117 P2' follow-up — SHARED-TREE STATIC dispatch (the ptrcallDispatch fix above). These
  // rows call the generated wrapper API, NOT the hand-written ptrcallStatic* helpers: every
  // `is_static` method in the shared tree renders `NULL_SEGMENT` as the instance, and before the
  // fix the iOS C instance entry point early-returned on a null instance, so all 72 such call
  // sites across 36 shared classes were silent no-ops (null / 0 / default) on device while passing
  // on desktop. A regression that drops the dispatcher fails every row here.
  run {
    // Image.create_from_data: 2x2 RGBA8 needs exactly 2*2*4 = 16 bytes.
    val fromData =
      net.multigesture.kanama.api.Image.createFromData(
        2,
        2,
        false,
        net.multigesture.kanama.api.Image.Format.RGBA8,
        ByteArray(16),
      )
    check("shared-static(Image.createFromData -> non-null)", fromData != null)
    check("shared-static(Image.createFromData width == 2)", fromData?.getWidth() == 2)
    check("shared-static(Image.createFromData height == 2)", fromData?.getHeight() == 2)
    fromData?.close()

    val created =
      net.multigesture.kanama.api.Image.create(
        4,
        3,
        false,
        net.multigesture.kanama.api.Image.Format.RGBA8,
      )
    check("shared-static(Image.create -> non-null)", created != null)
    check("shared-static(Image.create width == 4)", created?.getWidth() == 4)
    created?.close()

    // A non-Image shared static on a primitive return shape: the self-test runs from the C
    // scene-init hook on Godot's main thread, so this is deterministically true. Pre-fix the
    // ret_out cell was never written and the zeroed byte decoded as `false`.
    check(
      "shared-static(Thread.isMainThread -> true)",
      net.multigesture.kanama.api.Thread.isMainThread(),
    )

    // A non-Image shared static returning an Object, with an observable result.
    val re = net.multigesture.kanama.api.RegEx.createFromString("a+b", false)
    check("shared-static(RegEx.createFromString -> valid)", re != null && re.isValid())
    re?.close()
  }

  // task 117 P2' follow-up 2 — the shared-tree statics that do NOT marshal through the generic
  // ptrcall entry point. Their helpers end in a SPECIALISED C entry (ret_utf8, ret_variant_scalar,
  // ret_array_blob, object_call; since task 129 D the no-arg String / StringName / NodePath
  // returns share ret_utf8), each of which kept its own null-instance guard, so these thirteen call
  // sites stayed silent
  // no-ops after the generic dispatcher landed. Every entry point below now has a `_dispatch`
  // body and a `_static` sibling in the shim, reached through its private *Dispatch function
  // here; a regression fails these rows on device rather than returning ""/empty/default.
  run {
    // JSON.stringify -> ptrcallWithVariantStringTwoBoolArgsRetString -> ..._ptrcall_ret_utf8,
    // JSON.parseString -> ptrcallWithStringArgRetVariantScalar -> ..._ptrcall_ret_variant_scalar.
    // A string value, not a number: Godot's JSON decodes an integer literal as int or float
    // depending on the value, and this row is about the static dispatch, not number typing.
    val encoded = net.multigesture.kanama.api.JSON.stringify(mapOf("k" to "v"))
    check("shared-static(JSON.stringify -> encodes the key)", encoded.contains("\"k\""))
    val decoded = net.multigesture.kanama.api.JSON.parseString(encoded)
    check(
      "shared-static(JSON.parseString round-trip k == v)",
      (decoded as? Map<*, *>)?.get("k") == "v",
    )

    // GLTFDocument.getSupportedGltfExtensions -> ptrcallNoArgsRetPackedStringList ->
    // ptrcallRetPackedStringList -> ..._ptrcall_ret_array_blob. Same helper, same NULL_SEGMENT
    // static marker as
    // the ShaderIncludeDB.listBuiltInIncludeFiles row it replaces (task 117 P2' follow-up 5) — but
    // a value the no-op path cannot produce. Godot hard-codes this set in
    // GLTFDocument::get_supported_gltf_extensions_hashset (modules/gltf/gltf_document.cpp:6968),
    // bound as a static method by initialize_gltf_module at MODULE_INITIALIZATION_LEVEL_SCENE,
    // which main/main.cpp runs IMMEDIATELY BEFORE initialize_extensions(INITIALIZATION_LEVEL_SCENE)
    // (main/main.cpp:3792-3793, and 834-835 on the other setup path). It does not depend on the
    // renderer, which is exactly what the ShaderIncludeDB row did. Assert a NAMED element too, so a
    // list that came back with the right length but the wrong bytes still fails.
    val gltfExtensions = net.multigesture.kanama.api.GLTFDocument.getSupportedGltfExtensions()
    check(
      "shared-static(GLTFDocument.getSupportedGltfExtensions -> non-empty)",
      gltfExtensions.isNotEmpty(),
    )
    check(
      "shared-static(GLTFDocument.getSupportedGltfExtensions contains KHR_lights_punctual)",
      gltfExtensions.contains("KHR_lights_punctual"),
    )

    // ptrcallWithStringArgRetString -> callWithVariantArgs -> kanama_ios_godot_object_call, the
    // last of the four entry points the thirteen sites reach, through the shared-tree static
    // FileAccess.getSha256 (FileAccess is generated once since task 129 D). The dispatch route is
    // objectCallDispatch -> kanama_ios_godot_object_call_static. It replaces
    // ShaderIncludeDB.getBuiltInIncludeFile, whose "non-empty source" was the no-op answer under
    // the GL Compatibility renderer. A SHA-256 is 64 lowercase hex characters; the no-op path
    // yields "" (the `as? String ?: ""` fallback in ptrcallWithStringArgRetString), so the length
    // and the alphabet are both evidence. res://project.binary exists in every exported project;
    // if it is missing that is a recorded FAILURE, not a skip.
    val sha256Path = "res://project.binary"
    if (net.multigesture.kanama.api.FileAccess.fileExists(sha256Path)) {
      val sha256 = net.multigesture.kanama.api.FileAccess.getSha256(sha256Path)
      check("shared-static(FileAccess.get_sha256 -> 64 characters)", sha256.length == 64)
      check(
        "shared-static(FileAccess.get_sha256 -> lowercase hex)",
        sha256.isNotEmpty() && sha256.all { it in '0'..'9' || it in 'a'..'f' },
      )
    } else {
      check("shared-static(FileAccess.get_sha256) (res://project.binary missing)", false)
      check("shared-static(FileAccess.get_sha256 alphabet) (res://project.binary missing)", false)
    }

    // Resource.generateSceneUniqueId -> ptrcallNoArgsRetString -> ptrcallRetUtf8 ->
    // ..._ptrcall_ret_utf8.
    // Documented to be letters a-y and digits 0-8 only, so a non-empty result is the assertion.
    check(
      "shared-static(Resource.generateSceneUniqueId -> non-empty)",
      net.multigesture.kanama.api.Resource.generateSceneUniqueId().isNotEmpty(),
    )

    // MultiplayerAPI.getDefaultInterface -> ptrcallNoArgsRetStringName -> ptrcallRetUtf8
    // (PT_STRING_NAME)
    // -> ..._ptrcall_ret_utf8. SceneMultiplayer ships in every non-headless-server build.
    check(
      "shared-static(MultiplayerAPI.getDefaultInterface == SceneMultiplayer)",
      net.multigesture.kanama.api.MultiplayerAPI.getDefaultInterface() == "SceneMultiplayer",
    )
  }

  // Task 117 P3′ — the members iOS gains from the shared GodotObject (D20/D22), driven through the
  // public wrapper API rather than the helpers, so the shared body, the generated or hand-written
  // iOS helper behind it and the shim are all on the path. THE PROBE RULE holds row by row: every
  // assertion is a value the no-op path cannot produce (it would return null / false / "" / 0), or
  // a default that is only reachable through a state an earlier row proved non-default (hasMeta
  // false only AFTER hasMeta true; isConnected false only AFTER isConnected true).
  run {
    val rootSeg = requireObject("Node2D")
    val targetSeg = requireObject("Node")
    if (rootSeg.address() != 0L && targetSeg.address() != 0L) {
      val root = GodotObject(GodotHandle(rootSeg))
      val target = GodotObject(GodotHandle(targetSeg))

      // set_meta / get_meta / has_meta / remove_meta (generated ptrcallWithStringNameAndVariantArg
      // + ptrcallWithStringNameAndVariantArgRetVariantScalar, generated StringName->bool, void).
      root.setMeta("kanama_p3_meta", 42L)
      check(
        "root(setMeta/getMeta round trip == 42)",
        (root.getMeta("kanama_p3_meta") as? Number)?.toLong() == 42L,
      )
      check("root(hasMeta after setMeta)", root.hasMeta("kanama_p3_meta"))
      root.removeMeta("kanama_p3_meta")
      check("root(hasMeta false after removeMeta)", !root.hasMeta("kanama_p3_meta"))
      check(
        "root(getMeta default after removeMeta == 7)",
        (root.getMeta("kanama_p3_meta", 7L) as? Number)?.toLong() == 7L,
      )

      // set_indexed / get_indexed on a Node2D sub-property (generated NodePath + Variant shapes).
      root.setIndexed("position:x", 12.5)
      check(
        "root(setIndexed/getIndexed position:x == 12.5)",
        (root.getIndexed("position:x") as? Number)?.toDouble() == 12.5,
      )

      // callv (generated StringName + Array -> Variant) and tr (generated two-StringName ->
      // String).
      check("root(callv get_class == Node2D)", root.callv("get_class", emptyList()) == "Node2D")
      check("root(tr(x) == x, no translation loaded)", root.tr("x") == "x")

      // add_user_signal (hand-written, Variant path) / has_user_signal.
      root.addUserSignal("kanama_p3_user")
      check("root(addUserSignal -> hasUserSignal)", root.hasUserSignal("kanama_p3_user"))

      // connect (hand-written -> IosGodot.objectConnect) + is_connected (generated PT_CALLABLE) +
      // disconnect (generated PT_CALLABLE), user signal -> a no-arg Node method.
      val method = "update_configuration_warnings"
      val connectError = root.connect("kanama_p3_user", target, method)
      check(
        "root(connect user signal -> OK and isConnected)",
        connectError == net.multigesture.kanama.api.GodotError.OK &&
          root.isConnected("kanama_p3_user", target, method),
      )
      root.disconnect("kanama_p3_user", target, method)
      check(
        "root(disconnect -> isConnected false)",
        !root.isConnected("kanama_p3_user", target, method),
      )

      // GodotSignal.disconnect (iOS gains it, D21): connect through the signal, disconnect through
      // it.
      val userSignal = root.signal("kanama_p3_user")
      userSignal.connect(target, method)
      check(
        "root(GodotSignal.connect -> isConnected)",
        root.isConnected("kanama_p3_user", target, method),
      )
      userSignal.disconnect(target, method)
      check(
        "root(GodotSignal.disconnect -> isConnected false)",
        !root.isConnected("kanama_p3_user", target, method),
      )

      // Task 117 P3′ follow-up 2 — the members whose iOS PATH changed (they existed on iOS before
      // P3′ but went through other shim entries). Same probe rule: each row asserts a value the
      // no-op path cannot produce, or a default only after an earlier row proved the non-default.

      // set / get (Variant path -> generated Object.set / Object.get ptrcall). The setIndexed row
      // above left position.x at 12.5, so (3, 4) is a fresh value in both components.
      root.set("position", Vector2(3.0, 4.0))
      check("root(set/get position == (3, 4))", root.get("position") == Vector2(3.0, 4.0))

      // setDeferred is not covered: Object.set_deferred queues the set on the MessageQueue, which
      // is flushed only at the end of an iteration of the main loop, so nothing is observable
      // synchronously at scene init.

      // is_class (ptrcall, no isNotBlank guard any more) — the `false` answer only after `true`.
      check("root(isClass(Node2D))", root.isClass("Node2D"))
      check("root(isClass(Node3D) false after isClass(Node2D) true)", !root.isClass("Node3D"))

      // get_instance_id (ptrcall) agrees with the id ObjectRuntime.instanceIdOf captured at
      // construction (object_get_instance_id), and neither is the no-op 0.
      check(
        "root(getInstanceId == instanceId, non-zero)",
        root.instanceId != 0L && root.getInstanceId() == root.instanceId,
      )

      // to_string (engine ptrcall; it was Kotlin's Any.toString on iOS before P3′). Godot 4.7.2:
      // Object::to_string (core/object/object.cpp:948) falls through to the virtual _to_string;
      // Node::_to_string (scene/main/node.cpp:3610-3612) prefixes "<name>:" only when the node
      // has a name, and Object::_to_string (core/object/object.cpp:1832-1833) is
      // "<" + get_class() + "#" + itos(get_instance_id()) + ">". This Node2D is unnamed.
      check("root(toString == <Node2D#id>)", root.toString() == "<Node2D#${root.instanceId}>")

      // emitSignal through ObjectRuntime, Long fast path (kanama_ios_godot_object_emit_signal_int)
      // into a Kotlin lambda connected through GodotSignal.connect(target, argumentCount). Godot
      // does not check a user signal's declared arity on emit, so one argument on the zero-arg
      // kanama_p3_user is delivered as is.
      var longArgs: List<Any?>? = null
      val longConnection =
        root.signal("kanama_p3_user").connect(target, 1) { args -> longArgs = args }
      root.emitSignal("kanama_p3_user", 5L)
      val receivedLong = longArgs
      check(
        "root(emitSignal Long fast path -> lambda received 5)",
        longConnection.error == net.multigesture.kanama.api.GodotError.OK &&
          receivedLong != null &&
          receivedLong.size == 1 &&
          (receivedLong[0] as? Number)?.toLong() == 5L,
      )

      // add_user_signal WITH arguments (the hand-written Array-of-Dictionaries helper on the
      // Variant path): Variant.Type TYPE_STRING = 4, TYPE_INT = 2 (extension_api.json global enum
      // "Variant.Type"). Godot 4.7.2: Object::_add_user_signal (core/object/object.cpp:1353-1382)
      // reads "name" and "type" from each Dictionary; Object::get_signal_list (1438-1453) appends
      // the user signals, and _get_signal_list (1384-1394) returns them as MethodInfo dictionaries
      // whose "args" are PropertyInfo dictionaries (core/object/method_info.cpp:37-39,
      // core/object/property_info.cpp:36-43).
      root.addUserSignal(
        "kanama_p3_args",
        listOf(mapOf("name" to "a", "type" to 4L), mapOf("name" to "b", "type" to 2L)),
      )
      val argsEntry = root.getSignalList().firstOrNull { it["name"] == "kanama_p3_args" }
      val declaredArgs = (argsEntry?.get("args") as? List<*>)?.map { it as? Map<*, *> }
      check(
        "root(addUserSignal with args -> getSignalList kanama_p3_args(a: String, b: int))",
        declaredArgs != null &&
          declaredArgs.size == 2 &&
          declaredArgs[0]?.get("name") == "a" &&
          (declaredArgs[0]?.get("type") as? Number)?.toLong() == 4L &&
          declaredArgs[1]?.get("name") == "b" &&
          (declaredArgs[1]?.get("type") as? Number)?.toLong() == 2L,
      )

      // emitSignal with two arguments takes the Variant path (Object.call("emit_signal", ...)).
      // The lambda trampoline (kanama_ios_callable_trampoline -> kanama_ios_marshal_variant_args
      // -> IosSignalArgReader) once decoded only bool / int / float / Object and handed the String
      // over as null; since task 134 D4 the cells carry Strings, and the row asserts both values.
      var pairArgs: List<Any?>? = null
      val pairConnection =
        root.signal("kanama_p3_args").connect(target, 2) { args -> pairArgs = args }
      root.emitSignal("kanama_p3_args", "a", 2L)
      val receivedPair = pairArgs
      check(
        "root(emitSignal two args, Variant path -> lambda received (a, 2))",
        pairConnection.error == net.multigesture.kanama.api.GodotError.OK &&
          receivedPair != null &&
          receivedPair.size == 2 &&
          receivedPair[0] == "a" &&
          (receivedPair[1] as? Number)?.toLong() == 2L,
      )

      // Task 122 item 4: every argument type a lambda can receive arrives with its value, desktop
      // parity, one row per type so a failure names it. String, Vector3, Color and Vector3i (a
      // task-133 raw value type) cross as their cells; Array and Dictionary as one blob record
      // (PT_VARIANT_RECORD), decoded by the container-return reader. Before, every type but bool
      // / int / float / Object reached the lambda as null.
      root.addUserSignal("kanama_122_args", emptyList())
      var typedArgs: List<Any?>? = null
      val typedConnection =
        root.signal("kanama_122_args").connect(target, 6) { args -> typedArgs = args }
      root.emitSignal(
        "kanama_122_args",
        "text",
        Vector3(1.0, 2.0, 3.0),
        Color(0.25, 0.5, 0.75, 1.0),
        listOf(1L, "two", Vector2(3.0, 4.0)),
        mapOf("k" to 5L, "nested" to listOf("x")),
        Vector3i(6, 7, 8),
      )
      val got = typedArgs
      fun lambdaArg(index: Int): Any? = got?.getOrNull(index)
      println(
        "[kanama][ios][kn] OBJECTCALLS SELFTEST lambda-args " +
          "count=${got?.size} values=${got?.joinToString(" | ")}"
      )
      check(
        "lambda-args(emitSignal six args -> lambda called with 6)",
        typedConnection.error == net.multigesture.kanama.api.GodotError.OK && got?.size == 6,
      )
      check("lambda-args(String -> \"text\")", lambdaArg(0) == "text")
      check("lambda-args(Vector3 -> (1, 2, 3))", lambdaArg(1) == Vector3(1.0, 2.0, 3.0))
      check(
        "lambda-args(Color -> (0.25, 0.5, 0.75, 1))",
        lambdaArg(2) == Color(0.25, 0.5, 0.75, 1.0),
      )
      check(
        "lambda-args(Array -> [1, two, (3, 4)])",
        lambdaArg(3) == listOf(1L, "two", Vector2(3.0, 4.0)),
      )
      check(
        "lambda-args(Dictionary -> {k: 5, nested: [x]})",
        lambdaArg(4) == mapOf("k" to 5L, "nested" to listOf("x")),
      )
      check("lambda-args(Vector3i value type -> (6, 7, 8))", lambdaArg(5) == Vector3i(6, 7, 8))
      typedConnection.close()
      root.removeUserSignal("kanama_122_args")

      // Close both lambda connections; "no connections" is admissible only because the two rows
      // above proved each connection delivered.
      longConnection.close()
      pairConnection.close()
      check(
        "root(SignalConnection.close -> no connections left)",
        !root.hasConnections("kanama_p3_user") && !root.hasConnections("kanama_p3_args"),
      )

      // setScript(null) / getScript is not covered: a `getScript() == null` row is admissible only
      // after a row proving getScript returns non-null for something, and no scripted object
      // exists at scene-level extension init (no scene is loaded yet, and a script built here
      // would need a compiled, instantiable Script — Object::set_script creates no instance
      // otherwise, so get_script would still answer null; core/object/object.cpp:984-992,
      // 1010-1011).
    }
    if (rootSeg.address() != 0L) ObjectCalls.destroyObject(rootSeg)
    if (targetSeg.address() != 0L) ObjectCalls.destroyObject(targetSeg)
  }

  // Task 129 B: the generated common GD through the iOS utility-call seam, and the common Mathf.
  utilitySelfTestRows(::check)

  // Task 134 D2: Godot's String / NodePath / PackedByteArray methods through the boxed builtin
  // call.
  builtinBoxedSelfTestRows(::check)

  // Task 124 — THE PERMANENT RED RUN: seven deliberate faults, raised in the sink's probe mode.
  // See runFaultProbes (declared beside SELFTEST_EXPECTED_FAULTS at the top of this section) for
  // what each probe proves and why the five `pending slot drained` rows live there, not inline.
  runFaultProbes(n3) { label, cond -> check(label, cond) }

  println(
    "[kanama][ios][kn] OBJECTCALLS SELFTEST: $pass passed, $fail failed " +
      "faults=${ObjectCalls.faultCount()} expected=$SELFTEST_EXPECTED_FAULTS"
  )
}

// Debug-gated FIRST-FRAME self-test phase (called exactly once from kanama_ios_frame, when the
// frame counter first reaches 1, before kanama_ios_runtime_frame()). It holds the rows whose
// singleton the engine registers AFTER scene-level extension init, so they cannot run in
// kanamaIosRuntimeObjectCallsSelfTest above: Godot 4.7.2 main/main.cpp calls
// initialize_extensions(INITIALIZATION_LEVEL_SCENE) at line 835 (3793 on the setup2 path) and
// register_server_singletons() — which adds the RenderingServer AND the NativeMenu singleton
// entries (servers/register_server_types.cpp:400-401) — only at line 3864. Until task 117 P2'
// follow-up 4 the RenderingServer row sat in the scene-init phase and
// "passed" because getSingleton returned 0 and the C instance entry point's null-instance guard
// turned the whole call into a no-op returning RID 0, which is exactly what it asserted. With
// the D19 dispatcher a zero instance is routed to the STATIC entry point instead, so the call
// reached Godot with a null `this` and SIGSEGV'd in RenderingServer::_mesh_create_from_surfaces.
@OptIn(ExperimentalNativeApi::class)
@CName("kanama_ios_runtime_objectcalls_selftest_frame")
fun kanamaIosRuntimeObjectCallsSelfTestFrame() {
  var pass = 0
  var fail = 0
  fun check(label: String, cond: Boolean) {
    if (cond) {
      pass++
    } else {
      fail++
      println("[kanama][ios][kn] OBJECTCALLS SELFTEST (frame 1) FAIL: $label")
    }
  }

  // Same contract as the scene-init phase: a missing singleton is a recorded FAILURE and the
  // dependent call is skipped, never executed with a zero instance.
  fun requireSingleton(name: String): MemorySegment {
    val segment = ObjectCalls.getSingleton(name)
    check("singleton-present($name)", segment.address() != 0L)
    return segment
  }

  // Task 128 A review — iOS Tween.tweenProperty with a Variant final value the old Vector2/Color
  // C-shim pair could not carry: a Double rotation. A valid Tween needs the running SceneTree, so
  // the
  // row lives in this frame-1 phase. The tween is stepped past its duration with custom_step, so
  // the
  // property must read the target 1.25; the old path tweened a float property to Vector2(0, 0),
  // which Godot refuses, leaving rotation at 0.
  run {
    val node =
      net.multigesture.kanama.api.Node2D(GodotHandle(ObjectCalls.constructObject("Node2D")))
    val tween = net.multigesture.kanama.api.SceneTree.active().createTween()
    tween.tweenProperty(node, "rotation", 1.25, 0.5)
    tween.call("custom_step", 1.0)
    check(
      "tween-property(Double rotation -> 1.25 after custom_step)",
      kotlin.math.abs(node.getRotation() - 1.25) < 1e-4,
    )
    tween.kill()
    tween.close()
    ObjectCalls.destroyObject(node.segment)
  }

  // Task 122 item 3 — the RefCounted ELEMENTS of an Object.call container result. RegEx.search_all
  // through the Variant `call` returns a fresh Array of fresh RegExMatch objects whose only
  // reference is that Array, which the C side destroys before Kotlin decodes the blob. They must
  // come back as owning wrappers: alive (get_string answers), one reference each (ours), and freed
  // by their one close(). Before, they were borrowed handles of objects already freed. A frame-1
  // row: RegEx is a module class, fully registered once the main loop runs.
  // Three rows share the body: `call` (inline blob), `callv` (same owned path as `call` since the
  // task-122 review; it used to keep borrowed elements), and `call` over 100 matches, whose blob
  // (>= 13 bytes per OBJECT record) is larger than the 1024-byte inline buffer, so it takes the
  // park -> take path with its retained list.
  fun callContainerElementsRow(
    row: String,
    subject: String,
    invoke: (net.multigesture.kanama.api.RegEx, String) -> Any?,
  ) {
    val regex = net.multigesture.kanama.api.RegEx.createFromString("\\d")
    check("$row(RegEx compiled)", regex != null)
    if (regex == null) return
    val expected = subject.filter { it.isDigit() }.map { it.toString() }
    val matches = invoke(regex, subject) as? List<*> ?: emptyList<Any?>()
    val owned = matches.isNotEmpty() && matches.all { it is RefCounted }
    val strings =
      if (owned) matches.map { (it as GodotObject).call("get_string", 0) } else emptyList()
    val refcounts =
      if (owned) matches.map { (it as RefCounted).getReferenceCount() } else emptyList()
    val ids = if (owned) matches.map { (it as GodotObject).getInstanceId() } else emptyList()
    if (owned) matches.forEach { (it as RefCounted).close() }
    val released = ids.isNotEmpty() && ids.none { IosGodot.isInstanceIdValid(it) }
    regex.close()
    val shown = if (matches.size <= 3) strings.joinToString(",") else "${strings.size} strings"
    val counts =
      if (matches.size <= 3) refcounts.joinToString(",")
      else
        refcounts
          .groupingBy { it }
          .eachCount()
          .entries
          .joinToString(",") { "${it.key}x${it.value}" }
    println(
      "[kanama][ios][kn] OBJECTCALLS SELFTEST $row count=${matches.size} " +
        "owned=$owned strings=$shown refcounts=$counts released=$released"
    )
    check(
      "$row(${expected.size} owned RefCounted elements, alive after the call)",
      matches.size == expected.size && owned && strings == expected,
    )
    check(
      "$row(one reference each, freed by their one close())",
      refcounts.size == expected.size && refcounts.all { it == 1 } && released,
    )
  }
  callContainerElementsRow("call-container-elements", "a1b2c3") { regex, subject ->
    regex.call("search_all", subject)
  }
  callContainerElementsRow("callv-container-elements", "a1b2c3") { regex, subject ->
    regex.callv("search_all", listOf(subject))
  }
  callContainerElementsRow("call-container-elements-parked", "0123456789".repeat(10)) {
    regex,
    subject ->
    regex.call("search_all", subject)
  }

  // Task 129 A — a Signal ARGUMENT through the generated ptrcallWithSignalArgRetObject (PT_SIGNAL:
  // the shim builds Signal(Object, StringName) for the call and destroys it after). The sequence
  // await(node.renamed) -> callback(node.queue_free) must PARK on the await and run the callback
  // only once the signal fires, so the rows tell a working Signal argument from a Signal the
  // engine never connected to (callback never runs) and from one it ignored (callback runs at
  // once). The bounded spin mirrors the desktop HelloScript probe: the await's start() resets its
  // received flag, so the emission must come after the tween reached it.
  run {
    val faultsBefore = ObjectCalls.faultCount()
    val node = net.multigesture.kanama.api.Node(GodotHandle(ObjectCalls.constructObject("Node")))
    val tween = net.multigesture.kanama.api.SceneTree.active().createTween()
    val awaitTweener = tween.tweenAwait(node.signal("renamed"))
    check("tween-await(Signal arg -> AwaitTweener)", awaitTweener.isClass("AwaitTweener"))
    tween.tweenCallback(node, "queue_free")
    var spin = 0
    while (spin < 8 && tween.customStep(0.05)) spin++
    check(
      "tween-await(parks on the await: callback not run before the signal)",
      !node.isQueuedForDeletion(),
    )
    node.emitSignal("renamed")
    // Two steps, as Godot 4.7.2 runs them (scene/animation/tween.cpp): the first finds the await
    // received and finishes it, but AwaitTweener::step has set r_delta = 0 (line 976), so
    // Tween::step's `while (running && rem_delta > 0)` loop (line 384) only starts the next step's
    // tweeners and exits; the second step runs the CallbackTweener. The first device run stepped
    // once and read the callback as not run.
    tween.customStep(0.05)
    tween.customStep(0.05)
    val resumed = node.isQueuedForDeletion()
    check("tween-await(signal fires -> the callback after the await runs)", resumed)
    check("tween-await(no kanama_ios_fault raised)", ObjectCalls.faultCount() == faultsBefore)
    awaitTweener.close()
    tween.kill()
    tween.close()
    // queue_free frees the node at the end of this frame; free it here only if it never ran.
    if (!resumed) ObjectCalls.destroyObject(node.segment)
  }

  // task 100 (parcel 10), moved here by task 117 P2' follow-up 4 — Array[Dictionary] ARGUMENT
  // through the GENERATED helper (packTypedDictionaryArrayDesc -> PT_TYPED_ARRAY_BLOB -> nested
  // blobs the boxer rebuilds), against a REAL RenderingServer instance: the typed Array[Dictionary]
  // cell is built, set_typed, handed over and inspected by the engine, and the call returns. A
  // non-empty Dictionary element cannot be exercised here without a Control or a full surface
  // dictionary (AABB values), so the DICTIONARY boxer case rides on the ARRAY case the
  // OggPacketSequence row in the scene-init phase proves.
  //
  // The EXPECTATION is task 117 P2' follow-up 5's correction. Follow-up 4 kept `RID(0L)` on the
  // claim that "the engine rejects an empty surface list with ERR_FAIL_COND_V" — that claim was
  // never checked against 4.7.2 and is false. RenderingServer::_mesh_create_from_surfaces
  // (servers/rendering/rendering_server.cpp:1996-2002) has NO guard: it converts each element and
  // calls mesh_create_from_surfaces(surfaces), whose default implementation
  // (servers/rendering/rendering_server_default.h:363) opens with mesh_allocate() —
  // mesh_owner.allocate_rid() in the GL Compatibility mesh storage
  // (drivers/gles3/storage/mesh_storage.cpp:65-67) — so an EMPTY list yields a VALID, EMPTY mesh.
  // An invalid RID was also precisely the no-op answer, which is what made the old row unable to
  // tell the fix from the defect. Assert the valid RID and free it again.
  val nestedRs = requireSingleton("RenderingServer")
  if (nestedRs.address() != 0L) {
    val nestedMesh =
      ObjectCalls.ptrcallWithDictionaryListIntArgsRetRID(
        ObjectCalls.getMethodBind("RenderingServer", "mesh_create_from_surfaces", 4291747531L),
        nestedRs,
        emptyList(),
        0,
      )
    check(
      "arg-nested(RenderingServer.mesh_create_from_surfaces empty Array[Dictionary] -> valid RID)",
      nestedMesh != RID(0L),
    )
    if (nestedMesh != RID(0L)) {
      ObjectCalls.ptrcallWithRIDArg(
        ObjectCalls.getMethodBind("RenderingServer", "free_rid", 2722037293L),
        nestedRs,
        nestedMesh,
      )
    }
  } else check("arg-nested(RenderingServer.mesh_create_from_surfaces) (singleton absent)", false)

  // task 100 (parcel 11), moved here by task 117 P2' follow-up 5 — a RID-ARGUMENT shape on a
  // singleton: NativeMenu has no popup for an invalid RID, so the returned Callable is empty ->
  // null (the arg cells and the run-once entry are exercised). The row sat in the scene-init phase
  // and was a false pass of exactly the RenderingServer kind: Engine gets its "NativeMenu" entry
  // from register_server_singletons() (servers/register_server_types.cpp:400), run at
  // main/main.cpp:3864 — the same late step as RenderingServer and long after
  // initialize_extensions(INITIALIZATION_LEVEL_SCENE) at main/main.cpp:3793 — so the lookup
  // returned 0 and the guard answered with the null Callable the row asserted. The NativeMenu
  // OBJECT itself exists much earlier on iOS and the row can therefore MOVE rather than go:
  // DisplayServerAppleEmbedded (the display server platform/ios inherits through
  // drivers/apple_embedded) does `native_menu = memnew(NativeMenu)` in its constructor
  // (drivers/apple_embedded/display_server_apple_embedded.mm:65) and NativeMenu's constructor sets
  // its own singleton (servers/display/native_menu.h:154); DisplayServer::create runs at
  // main/main.cpp:3368, before both. The expected null is a DEFAULT value, so per the probe rule it
  // is evidence only because singleton-present(NativeMenu) proves the instance is non-zero (the
  // static route is not taken) — and this phase carries a non-default assertion of its own in the
  // RenderingServer RID above.
  val nativeMenu = requireSingleton("NativeMenu")
  if (nativeMenu.address() != 0L) {
    val noPopup =
      ObjectCalls.ptrcallWithRIDArgRetCallable(
        ObjectCalls.getMethodBind("NativeMenu", "get_popup_open_callback", 3170603026L),
        nativeMenu,
        RID(0L),
      )
    check("ret-callable(NativeMenu.get_popup_open_callback invalid RID -> null)", noPopup == null)
  } else check("ret-callable(NativeMenu.get_popup_open_callback) (singleton absent)", false)

  // Task 133: class tokens, checked casts, script checks, tree accessors, preload, script scope.
  net.multigesture.kanama.api.scriptAccessSelfTestRows(::check)

  // Task 129 C: the classes generated once whose iOS path is new (AudioStreamPlayer,
  // ResourceLoader, Engine, ProjectSettings, ParticleProcessMaterial, ...).
  wrappersOnceSelfTestRows(::check)

  // The fault counter is process-wide and never reset, so by frame 1 it still holds exactly the
  // seven deliberate probes from the level-2 phase — unless something in between failed quietly,
  // which is precisely what this line exists to show.
  println(
    "[kanama][ios][kn] OBJECTCALLS SELFTEST (frame 1): $pass passed, $fail failed " +
      "faults=${ObjectCalls.faultCount()} expected=$SELFTEST_EXPECTED_FAULTS"
  )
}
