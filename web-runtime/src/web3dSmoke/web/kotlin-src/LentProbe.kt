package web3d

import net.multigesture.kanama.annotations.OnInput
import net.multigesture.kanama.annotations.OnUnhandledInput
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GD
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.GodotObject
import net.multigesture.kanama.api.InputEvent
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.MainThread
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.api.Node3D
import net.multigesture.kanama.api.Signal2
import net.multigesture.kanama.api.SignalArgType
import net.multigesture.kanama.types.Vector3

/**
 * Harness-only: the lifetime of the objects a dispatch lends Kotlin (a signal argument, `_input`'s
 * event, a registered method's object argument), against LentTwin (lent_twin.gd), which owns plain
 * engine objects no Kanama script tracks. Main's `lent_probe` arms [lentArm]; the rows run over
 * the following frames and [lentDone] says when they are finished. The rule they hold Web to is
 * desktop's: a Node or plain Object argument is one value per object, valid until the engine frees
 * it; a RefCounted one while Godot holds it (and, on Web, for the dispatch plus 2 frames when only
 * Kotlin keeps it).
 *
 * [maskA] (L1-L4, 65535 when healthy):
 * - L1 a stored argument still answers 2 frames later: 1 = the one-object signal arm, 2 = the
 *   packed-arguments arm, 4 = a method's object argument, 8 = a packed method argument, 16 =
 *   `_input`'s event, 32 = `_unhandled_input`'s; 64 = all six still answer 8 frames later.
 * - L2 identity: the argument `==` 128 = a typed lookup made before the first dispatch, 256 = one
 *   made inside the handler, 512 = `GD.instanceFromId(arg.instanceId)`, 1024 = the element of
 *   `call("get_children")`; 2048 = every arm handed over the same value (and `_input` /
 *   `_unhandled_input` the same event); 4096 = a later dispatch of the object `==` the stored one.
 * - L3 nested: a dispatch of the same object while the outer one runs: 8192 = inner `==` outer,
 *   16384 = `GD.instanceFromId` `==` the inner argument.
 * - L4: 32768 = an argument LentKeeper stored, used 3 frames after LentKeeper was freed.
 *
 * [maskB] (L5-L9, 65535 when healthy):
 * - L5 the engine frees a stored argument: 1 = `free()` and 2 = `queue_free()` make a use throw
 *   desktop's "previously freed instance" IllegalStateException; 4 = the live lent handles and 8 =
 *   the live browser handles are back where they were within 4 frames.
 * - L6 bounded: 16 = 500 rounds of create / hand over / free keep at most 34 lent handles alive
 *   above the baseline, 32 = back to the baseline after, 64 = 1000 dispatches of one node take one
 *   handle.
 * - L7 RefCounted: 128 = one nobody keeps answers during the dispatch and on the next 2 frames,
 *   256 = after that the engine freed it and a use throws the freed-instance error; 512 = one the
 *   twin keeps still answers after the grace, 1024 = once the twin drops it, a use throws it; 32768
 *   = one nobody keeps but the handler resolved (`GD.instanceFromId`) still answers after the grace.
 * - L8 snapshot: 2048 = `position` of a Node3D argument reads the node's value, 4096 = after the
 *   twin moved it, the next argument reads the new one.
 * - L9: 8192 = `queueFree()` on a method's object argument works (no throw, the node is gone),
 *   16384 = a later use throws an IllegalStateException that is not a handle-kind failure.
 *
 * [lentCost] (L10): the lending's GDScript->JS calls and the microseconds per 1000 dispatches of
 * an already-seen plain Node and Node3D.
 */
@ScriptClass(attachTo = "Node")
class LentProbe(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
  private var maskA = 0L
  private var maskB = 0L
  private var done = 0L
  private val cost = LongArray(4)
  private val notes = mutableListOf<String>()

  private var recording = false
  private var oneArg: GodotObject? = null
  private var twoArg: GodotObject? = null
  private var methodArg: Node? = null
  private var taggedArg: Node? = null
  private var inputEvent: InputEvent? = null
  private var unhandledEvent: InputEvent? = null
  private var beforeLookup: Node? = null
  private var laterSame = false
  private var checkLater = false
  private var identityInHandler = 0L

  private var outerArg: GodotObject? = null
  private var innerArg: GodotObject? = null
  private var innerFound: GodotObject? = null

  private val rcArgs = mutableListOf<GodotObject>()
  private var rcResolved: GodotObject? = null
  private var movedReads = mutableListOf<Vector3?>()
  private val doomedArgs = mutableListOf<GodotObject>()
  private var churnKept: GodotObject? = null
  private var churnCount = 0L
  private var kotlinFreed: Node? = null
  private var kotlinFreeFailure: String? = null

  @OnInput
  fun onInput(event: InputEvent) {
    if (recording) inputEvent = event
  }

  @OnUnhandledInput
  fun onUnhandledInput(event: InputEvent) {
    if (recording) unhandledEvent = event
  }

  /** The method arm (OBJECT_VOID) of L1. */
  fun takeNode(node: Node) {
    if (recording) methodArg = node
  }

  /** The packed method arm (PACKED_ARGS) of L1. */
  fun takeTagged(label: String, node: Node) {
    if (recording && label == "tag") taggedArg = node
  }

  /** L9: a handler that frees the node it was handed, as `body.queueFree()` in a body_entered. */
  fun takeAndFree(node: Node) {
    kotlinFreed = node
    kotlinFreeFailure = runCatching { node.queueFree() }.exceptionOrNull()?.let { "${it::class.simpleName}: ${it.message}" }
  }

  fun lentMaskA(value: Long): Long = maskA

  fun lentMaskB(value: Long): Long = maskB

  fun lentDone(value: Long): Long = done

  fun lentCost(value: Long): Long = cost[value.toInt()]

  private fun twin(): Node = self.requireAs("../LentTwin", ::Node)

  private fun twinLong(method: String, arg: Long = 0L): Long = twin().call(method, arg) as Long

  private fun counter(name: String): Long = twin().call("counter", name) as Long

  private fun note(text: String) {
    if (notes.size < 48) notes += text
  }

  private fun answers(label: String, check: () -> Boolean): Boolean =
    try {
      check().also { if (!it) note("$label: wrong answer") }
    } catch (e: Throwable) {
      note("$label: ${e::class.simpleName}: ${e.message?.take(160)}")
      false
    }

  /** A use that must throw desktop's freed-instance IllegalStateException. */
  private fun throwsFreed(label: String, use: () -> Unit): Boolean =
    try {
      use()
      note("$label: did not throw")
      false
    } catch (e: IllegalStateException) {
      (e.message?.contains("previously freed instance") == true).also {
        if (!it) note("$label: ${e.message?.take(160)}")
      }
    } catch (e: Throwable) {
      note("$label: ${e::class.simpleName}: ${e.message?.take(160)}")
      false
    }

  private fun bodyAnswers(label: String, value: GodotObject?): Boolean =
    answers(label) {
      val node = Node3D(checkNotNull(value) { "no argument stored" }.handle)
      node.getName() == "LentBody" &&
        node.call("get_child_count") == 0L &&
        node.getPosition().x.isFinite()
    }

  private fun eventAnswers(label: String, value: InputEvent?): Boolean =
    answers(label) { checkNotNull(value) { "no event stored" }.isPressed() }

  private fun l1Mask(): Long {
    var mask = 0L
    if (bodyAnswers("L1 one", oneArg)) mask = mask or 1L
    if (bodyAnswers("L1 packed", twoArg)) mask = mask or 2L
    if (bodyAnswers("L1 method", methodArg)) mask = mask or 4L
    if (bodyAnswers("L1 packed method", taggedArg)) mask = mask or 8L
    if (eventAnswers("L1 input", inputEvent)) mask = mask or 16L
    if (eventAnswers("L1 unhandled", unhandledEvent)) mask = mask or 32L
    return mask
  }

  private fun rcAnswers(label: String, value: GodotObject): Boolean =
    answers(label) { value.call("get_class") == "Resource" }

  /** Starts the rows (driver: Main.lent_probe). */
  fun lentArm(value: Long): Long {
    val twin = twin()
    val bodyPath = "../LentTwin/LentBody"
    beforeLookup = self.getNodeOrNull(bodyPath)
    twin.signal("one").connectObject(self) { arg ->
      if (checkLater) {
        laterSame = arg == oneArg
        if (!laterSame) note("L2 later dispatch: a new value")
        return@connectObject
      }
      oneArg = arg
      if (answers("L2 before") { arg == beforeLookup }) identityInHandler = identityInHandler or 128L
      if (answers("L2 lookup") { arg == self.getNodeOrNull(bodyPath) }) identityInHandler = identityInHandler or 256L
      if (answers("L2 instanceFromId") { GD.instanceFromId(arg.instanceId) == arg }) {
        identityInHandler = identityInHandler or 512L
      }
      if (answers("L2 get_children") { (twin.call("get_children") as List<*>).any { it == arg } }) {
        identityInHandler = identityInHandler or 1024L
      }
    }
    Signal2(twin, "two", SignalArgType.VARIANT, SignalArgType.LONG).connect(self) { arg, _ ->
      twoArg = arg as GodotObject
    }
    twin.signal("outer").connectObject(self) { arg ->
      outerArg = arg
      twin.call("emit_inner", 0L)
    }
    twin.signal("inner").connectObject(self) { arg ->
      innerArg = arg
      innerFound = runCatching { GD.instanceFromId(arg.instanceId) }.getOrNull()
    }
    twin.signal("rc").connectObject(self) { arg ->
      rcArgs += arg
      if (rcArgs.size == 3) rcResolved = runCatching { GD.instanceFromId(arg.instanceId) }.getOrNull()
      // Within the dispatch: 128 needs this and the next two frames.
      if (!rcAnswers("L7 in dispatch", arg)) rcWithinGrace = false
    }
    twin.signal("moved").connectObject(self) { arg ->
      movedReads += runCatching { Node3D(arg.handle).getPosition() }.onFailure {
        note("L8 position: ${it::class.simpleName}: ${it.message?.take(160)}")
      }.getOrNull()
    }
    twin.signal("doomed").connectObject(self) { doomedArgs += it }
    twin.signal("churn").connectObject(self) { arg ->
      churnCount++
      churnKept = runCatching { GD.instanceFromId(arg.instanceId) }.getOrNull() ?: arg
    }
    twin.signal("cost").connectObject(self) { _ -> churnCount++ }
    val keeper = self.requireAs("../LentEscapeA", ::Node)
    keeper.call("arm_escape", 0L)

    recording = true
    twin.call("lend_all", self)
    recording = false
    twin.call("emit_outer", 0L)
    twin.call("emit_escape", 0L)
    val rcFrame = twinLong("frames")
    twin.call("emit_rc", 0L)
    twin.call("emit_moved", 1L)

    // A frame later: the RefCounted nobody keeps still answers.
    MainThread.postAfterFrames(1) { checkGrace(rcFrame) }
    MainThread.postAfterFrames(2) {
      checkGrace(rcFrame)
      maskA = maskA or l1Mask() or identityInHandler
      if (oneArg != null && oneArg == twoArg && oneArg == methodArg && oneArg == taggedArg) {
        if (inputEvent != null && inputEvent == unhandledEvent) maskA = maskA or 2048L
        else note("L2 arms: input $inputEvent vs unhandled $unhandledEvent")
      } else {
        note("L2 arms: one=$oneArg two=$twoArg method=$methodArg tagged=$taggedArg")
      }
      checkLater = true
      runCatching { twin.call("emit_one", 0L) }
      checkLater = false
      if (laterSame) maskA = maskA or 4096L
      if (outerArg != null && innerArg == outerArg) maskA = maskA or 8192L
      else note("L3 inner $innerArg vs outer $outerArg")
      if (innerArg != null && innerFound == innerArg) maskA = maskA or 16384L
      else note("L3 found $innerFound vs inner $innerArg")
      // L4: the receiver of `escape` goes away.
      self.requireAs("../LentEscapeA", ::Node).queueFree()
      // L8: move the node, hand it over again.
      runCatching { twin.call("emit_moved", 7L) }
      MainThread.postAfterFrames(1) { checkGrace(rcFrame) }
      MainThread.postAfterFrames(5) { lateRows() }
    }
    return 1L
  }

  private var rcWithinGrace = true
  private var graceFramesChecked = 0L

  /** L7: the RefCounted nobody keeps answers on every check within 2 frames of its dispatch. */
  private fun checkGrace(lentFrame: Long) {
    val unkept = rcArgs.firstOrNull() ?: return
    val delta = twinLong("frames") - lentFrame
    if (delta > 2) return
    graceFramesChecked = maxOf(graceFramesChecked, delta)
    if (!rcAnswers("L7 grace +$delta", unkept)) rcWithinGrace = false
  }

  private fun lateRows() {
    val twin = twin()
    // L4: LentKeeper was freed 5 frames ago.
    if (answers("L4 escape") { LentKeeper.kept?.call("get_name") == "LentEscapee" }) maskA = maskA or 32768L
    // L7.
    if (rcWithinGrace && graceFramesChecked == 2L && rcArgs.size == 3) maskB = maskB or 128L
    else note("L7 grace: checked to +$graceFramesChecked, answered=$rcWithinGrace")
    if (rcArgs.size == 3) {
      val unkeptGone = twinLong("unkept_alive") == 0L
      if (!unkeptGone) note("L7 unkept: still alive")
      if (unkeptGone && throwsFreed("L7 unkept use") { rcArgs[0].call("get_class") }) maskB = maskB or 256L
      if (rcAnswers("L7 held", rcArgs[1])) maskB = maskB or 512L
      // The documented way to keep one: resolve an owned reference inside the handler.
      val resolved = rcResolved
      if (resolved != null && twinLong("resolved_alive") == 1L && rcAnswers("L7 resolved", resolved)) {
        maskB = maskB or 32768L
      } else {
        note("L7 resolved: $resolved alive=${twinLong("resolved_alive")}")
      }
    }
    // L8.
    val reads = movedReads.toList()
    if (reads.size == 2 && reads[0] == Vector3(1.0, 0.5, -1.0)) maskB = maskB or 2048L
    if (reads.size == 2 && reads[1] == Vector3(7.0, 0.5, -1.0)) maskB = maskB or 4096L
    if (maskB and 6144L != 6144L) note("L8 reads $reads")
    // L5: hand two nodes over, then the engine frees them.
    val lentBefore = counter("liveLent")
    val liveBefore = counter("liveBrowserHandleCount")
    runCatching { twin.call("emit_doomed", 0L) }.onFailure { note("L5 emit: ${it.message?.take(120)}") }
    runCatching { twin.call("free_doomed", 0L) }
    // `free()` is immediate: the first stored node is gone now.
    if (doomedArgs.size == 2 && throwsFreed("L5 free") { doomedArgs[0].call("get_name") }) maskB = maskB or 1L
    MainThread.postAfterFrames(4) {
      if (doomedArgs.size == 2 && throwsFreed("L5 queue_free") { doomedArgs[1].call("get_name") }) {
        maskB = maskB or 2L
      }
      val lentAfter = counter("liveLent")
      val liveAfter = counter("liveBrowserHandleCount")
      if (lentAfter == lentBefore) maskB = maskB or 4L else note("L5 lent $lentBefore -> $lentAfter")
      if (liveAfter == liveBefore) maskB = maskB or 8L else note("L5 live $liveBefore -> $liveAfter")
      // L1, much later.
      if (l1Mask() == 63L) maskA = maskA or 64L
      // L7: the twin drops the RefCounted it held; nothing else holds it (checked after L6).
      twin.call("drop_held", 0L)
      churnRows()
    }
  }

  private fun churnRows() {
    val twin = twin()
    val lentBase = counter("liveLent")
    val liveBase = counter("liveBrowserHandleCount")
    twin.call("churn_rounds", 500L)
    val maxLent = twin.call("churn_max", 0L) as Long
    if (maxLent - lentBase <= 34L) maskB = maskB or 16L else note("L6 lent $lentBase -> max $maxLent")
    // One handle for the node however often it is handed over: minted (or taken over from the
    // get_children result of L2) once, not once per dispatch.
    val handlesBefore = counter("lentMints") + counter("lentAdopts")
    twin.call("churn_same", 1000L)
    val handles = counter("lentMints") + counter("lentAdopts") - handlesBefore
    if (handles == 1L) maskB = maskB or 64L else note("L6 one node, 1000 dispatches: $handles handles")
    MainThread.postAfterFrames(4) {
      if (rcArgs.size == 3) {
        val heldGone = twinLong("held_alive") == 0L
        if (!heldGone) note("L7 held: still alive after the twin dropped it")
        if (heldGone && throwsFreed("L7 dropped use") { rcArgs[1].call("get_class") }) maskB = maskB or 1024L
      }
      val lentAfter = counter("liveLent")
      val liveAfter = counter("liveBrowserHandleCount")
      if (lentAfter <= lentBase + 1 && liveAfter <= liveBase + 1) maskB = maskB or 32L
      else note("L6 after: lent $lentBase -> $lentAfter, live $liveBase -> $liveAfter")
      costRows()
      // L9 last: on the old mechanism its handle-kind failure is not a script error.
      runCatching { twin.call("lend_to_free", self) }.onFailure { note("L9 lend: ${it.message?.take(120)}") }
      if (kotlinFreeFailure != null) note("L9 queueFree: $kotlinFreeFailure")
      MainThread.postAfterFrames(3) {
        if (kotlinFreeFailure == null && twinLong("kotlin_freed_gone") == 1L) maskB = maskB or 8192L
        else note("L9 gone=${twinLong("kotlin_freed_gone")}")
        val use = runCatching { kotlinFreed?.getName() }.exceptionOrNull()
        if (use is IllegalStateException && use.message?.contains("kind=") != true) maskB = maskB or 16384L
        else note("L9 use: ${use?.let { "${it::class.simpleName}: ${it.message?.take(120)}" }}")
        // Why a row failed: the driver traces it.
        twin().call("publish_notes", notes.joinToString(" | "))
        done = 1L
      }
    }
  }

  private fun costRows() {
    val twin = twin()
    for (kind in 0..1) {
      val measured = twin.call("measure_cost", kind.toLong(), 2000L)
      val values: List<Long> =
        when (measured) {
          is LongArray -> measured.toList()
          is List<*> -> measured.map { (it as Number).toLong() }
          else -> listOf(-1L, -1L)
        }
      cost[kind] = values[0]
      cost[2 + kind] = values[1]
    }
  }
}
