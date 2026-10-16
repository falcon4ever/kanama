package web3d

import net.multigesture.kanama.annotations.OnInput
import net.multigesture.kanama.annotations.OnUnhandledInput
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.AudioStreamPlayer
import net.multigesture.kanama.api.GD
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.GodotObject
import net.multigesture.kanama.api.InputEvent
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.MainThread
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.api.Node2D
import net.multigesture.kanama.api.Node3D
import net.multigesture.kanama.api.Signal2
import net.multigesture.kanama.api.SignalArgType
import net.multigesture.kanama.api.Sprite2D
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector3

/**
 * Harness-only: the lifetime of the objects a dispatch lends Kotlin (a signal argument, `_input`'s
 * event, a registered method's object argument), against LentTwin (lent_twin.gd), which owns plain
 * engine objects no Kanama script tracks. Main's `lent_probe` arms [lentArm]; the rows run over
 * the following frames and [lentDone] says when they are finished. The rule they hold Web to is
 * desktop's: a Node or plain Object argument is one value per object, valid until the engine frees
 * it; a RefCounted one while Godot or a Kotlin reference holds it (protocol 37; OwnProbe has the
 * rows for its release).
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
 *   desktop's "previously freed instance" IllegalStateException (`free()`: at once, and again once
 *   the handle was released); 4 = the live lent handles and 8 = the live browser handles are back
 *   where they were within 4 frames, although a setter was queued on the freed node in the frame
 *   it was freed (a turret's target died this frame).
 * - L6 bounded: 16 = 500 rounds of create / hand over / free keep at most 34 lent handles alive
 *   above the baseline, 32 = back to the baseline after, 64 = 1000 dispatches of one node take one
 *   handle.
 * - L7 RefCounted: 128 = one only Kotlin keeps answers during the dispatch and on the next 2
 *   frames, 256 = and still lives after them (no frame count expires it), until `close()` on its
 *   wrapper: a frame later the object is gone; 512 = one the twin keeps too still answers after
 *   2 frames, 1024 = once the twin drops it, Kotlin's reference keeps it alive and answering; 32768
 *   = one the handler also resolved (`GD.instanceFromId`) still answers after 2 frames.
 * - L8 snapshot: 2048 = `position` of a Node3D argument reads the node's value, 4096 = after the
 *   twin moved it, the next argument reads the new one.
 * - L9: 8192 = `queueFree()` on a method's object argument works (no throw, the node is gone),
 *   16384 = a later use throws desktop's "previously freed instance" IllegalStateException.
 *
 * [maskC] (L11-L19, 511 when healthy):
 * - L11: 1 = one node handed to a handler twice in one frame, the first call `queueFree()`s it:
 *   the second call reads its name (the node lives to the end of the frame, as on desktop), and
 *   a use after the frame throws the freed-instance error.
 * - L12: 2 = a stored argument freed before 4200 more freed lent handles (more than the bridge and
 *   Kotlin remember) still throws an IllegalStateException, typed and through `call`.
 * - L13: 4 = a RefCounted only Kotlin keeps, handed over again 2 frames later: the same value, still
 *   alive 4 and 8 frames after the first dispatch (no frame count expires it); `close()` on both
 *   wrappers releases it.
 * - L14: 8 = 256 nodes handed over and kept, then freed at once: the lent handles are back to the
 *   baseline within K + 3 frames (the sweep checks 32 a frame: K = ceil(lent handles / 32), 9 for
 *   the ~263 this row holds, so 12 frames).
 * - L15: 16 = while the tree is paused (no Kanama script processes) a freed node's handle is still
 *   released within 6 frames, and a RefCounted Kotlin keeps stays.
 * - L16: 32 = a Sprite2D this script created, moved by the twin and handed over: the argument is
 *   the created value and its `position` reads the moved one (the dispatch refreshes the snapshot
 *   of the handle the object already has, whatever its bridge kind).
 * - L17: 64 = an AudioStreamPlayer this script created, handed over, then freed by the engine:
 *   the release drops the bridge's audio state for it, and a use throws the freed-instance error.
 * - L18: 128 = a short-lived proxy (a LentKeeper made at run time) lends a node and is freed. The
 *   node is freed later with no lend in between: its handle is released within K + 3 frames (the
 *   sweep is the shared class's frame hook, no proxy's).
 * - L19: 256 = the tree is paused (no proxy processes); a handle this script created (it lives as
 *   long as the node) is handed over, the node freed: the handle is released while paused.
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
  private var maskC = 0L
  private val twiceArgs = mutableListOf<GodotObject>()
  private var twiceRead: String? = null
  private var cappedArg: GodotObject? = null
  private val renewArgs = mutableListOf<GodotObject>()
  private val pausedArgs = mutableListOf<GodotObject>()
  private val adoptedArgs = mutableListOf<GodotObject>()
  private var adoptedRead: Vector2? = null
  private val pausedAdoptArgs = mutableListOf<GodotObject>()

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

  fun lentMaskC(value: Long): Long = maskC

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
    twin.signal("twice").connectObject(self) { arg ->
      twiceArgs += arg
      if (twiceArgs.size == 1) {
        runCatching { Node(arg.handle).queueFree() }.onFailure {
          note("L11 queueFree: ${it::class.simpleName}: ${it.message?.take(120)}")
        }
      } else {
        twiceRead = runCatching { Node(arg.handle).getName() }.getOrElse {
          note("L11 second read: ${it::class.simpleName}: ${it.message?.take(120)}")
          null
        }
      }
    }
    twin.signal("capped").connectObject(self) { cappedArg = it }
    twin.signal("renew").connectObject(self) { renewArgs += it }
    twin.signal("paused_lend").connectObject(self) { pausedArgs += it }
    twin.signal("adopted").connectObject(self) { arg ->
      adoptedArgs += arg
      if (adoptedArgs.size == 1) {
        adoptedRead = runCatching { Node2D(arg.handle).getPosition() }.onFailure {
          note("L16 position: ${it::class.simpleName}: ${it.message?.take(120)}")
        }.getOrNull()
      }
    }
    twin.signal("paused_adopt").connectObject(self) { pausedAdoptArgs += it }
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
    var unkeptClosed = false
    if (rcArgs.size == 3) {
      // Kotlin keeps it, so it outlives the 2 frames; close() on its one wrapper ends it.
      val unkeptAlive = twinLong("unkept_alive") == 1L
      if (!unkeptAlive) note("L7 unkept: gone while Kotlin keeps it")
      if (unkeptAlive && rcAnswers("L7 unkept after 2 frames", rcArgs[0])) {
        val closeable = rcArgs[0] as? AutoCloseable
        if (closeable == null) note("L7 unkept: not AutoCloseable") else closeable.close()
        unkeptClosed = closeable != null
      }
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
    // A setter queued on the node the engine just freed (a turret's target died this frame): it is
    // skipped and reported as the freed instance, and the handle must still be released below.
    if (doomedArgs.size == 2) {
      runCatching { Node(doomedArgs[0].handle).setProcessMode(Node.ProcessMode.DISABLED) }.onFailure {
        note("L5 queued setter: ${it::class.simpleName}: ${it.message?.take(120)}")
      }
    }
    // `free()` is immediate: the first stored node is gone now (this call applies the setter first).
    val freedAtOnce = doomedArgs.size == 2 && throwsFreed("L5 free") { doomedArgs[0].call("get_name") }
    MainThread.postAfterFrames(4) {
      if (unkeptClosed && twinLong("unkept_alive") == 0L) maskB = maskB or 256L
      else if (unkeptClosed) note("L7 unkept: still alive after close()")
      if (freedAtOnce && throwsFreed("L5 free, released") { Node(doomedArgs[0].handle).getName() }) {
        maskB = maskB or 1L
      }
      if (doomedArgs.size == 2 && throwsFreed("L5 queue_free") { doomedArgs[1].call("get_name") }) {
        maskB = maskB or 2L
      }
      val lentAfter = counter("liveLent")
      val liveAfter = counter("liveBrowserHandleCount")
      // Not more than before: the two freed nodes' handles are released. Fewer is fine — the browser's
      // collector may release earlier rows' RefCounted handles in the same frames (Linux CI Firefox did).
      if (lentAfter <= lentBefore) maskB = maskB or 4L else note("L5 lent $lentBefore -> $lentAfter")
      if (liveAfter <= liveBefore) maskB = maskB or 8L else note("L5 live $liveBefore -> $liveAfter")
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
    // One handle for the node however often it is handed over (the one the get_children result of
    // L2 made), not one per dispatch.
    val handlesBefore = counter("liveBrowserHandleCount")
    twin.call("churn_same", 1000L)
    val handles = counter("liveBrowserHandleCount") - handlesBefore
    if (handles <= 1L) maskB = maskB or 64L else note("L6 one node, 1000 dispatches: $handles handles")
    MainThread.postAfterFrames(4) {
      if (rcArgs.size == 3) {
        val heldAlive = twinLong("held_alive") == 1L
        if (!heldAlive) note("L7 held: gone after the twin dropped it, while Kotlin keeps it")
        if (heldAlive && rcAnswers("L7 dropped by the twin", rcArgs[1])) maskB = maskB or 1024L
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
        val freedNode = kotlinFreed
        if (freedNode != null && throwsFreed("L9 use") { freedNode.getName() }) maskB = maskB or 16384L
        twiceRows()
      }
    }
  }

  /** L11, then L12. */
  private fun twiceRows() {
    val twin = twin()
    runCatching { twin.call("emit_twice", 0L) }.onFailure { note("L11 emit: ${it.message?.take(120)}") }
    val readInFrame = twiceRead == "LentTwice" && twiceArgs.size == 2 && twiceArgs[0] == twiceArgs[1]
    if (!readInFrame) note("L11 read in frame: $twiceRead (${twiceArgs.size} dispatches)")
    MainThread.postAfterFrames(3) {
      val gone = twinLong("twice_gone") == 1L
      if (!gone) note("L11: the node was not freed")
      if (readInFrame && gone && throwsFreed("L11 after") { Node(twiceArgs[0].handle).getName() }) {
        maskC = maskC or 1L
      }
      cappedRows()
    }
  }

  /** L12: more freed lent handles than the bridge and Kotlin remember. */
  private fun cappedRows() {
    val twin = twin()
    runCatching { twin.call("emit_capped", 4200L) }.onFailure { note("L12 emit: ${it.message?.take(120)}") }
    val first = cappedArg
    val typed = runCatching { Node(checkNotNull(first).handle).getName() }.exceptionOrNull()
    val generic = runCatching { checkNotNull(first).call("get_name") }.exceptionOrNull()
    if (first != null && typed is IllegalStateException && generic is IllegalStateException) maskC = maskC or 2L
    else note("L12 typed=${typed?.let { "${it::class.simpleName}: ${it.message?.take(100)}" }} generic=${generic?.let { "${it::class.simpleName}: ${it.message?.take(100)}" }}")
    MainThread.postAfterFrames(2) { renewRows() }
  }

  /** L13: a RefCounted Kotlin keeps, handed over twice, lives until its wrappers are closed. */
  private fun renewRows() {
    twin().call("emit_renew", 0L)
    MainThread.postAfterFrames(10) {
      val alive = twinLong("renew_state", 0L)
      val gone = twinLong("renew_state", 1L)
      val kept = alive == 1L && gone == 0L && renewArgs.size == 2 && renewArgs[0] == renewArgs[1]
      if (!kept) note("L13 alive at +4=$alive gone by +8=$gone dispatches=${renewArgs.size}")
      if (kept) renewArgs.forEach { (it as? AutoCloseable)?.close() }
      MainThread.postAfterFrames(1) {
        if (kept && twinLong("renew_state", 2L) == 0L) maskC = maskC or 4L
        else if (kept) note("L13: still alive after close() on both wrappers")
        burstRows()
      }
    }
  }

  /** L14: a burst of lends, then all freed at once. */
  private fun burstRows() {
    val twin = twin()
    val lentBase = counter("liveLent")
    val liveBase = counter("liveBrowserHandleCount")
    val lent = twinLong("emit_burst", 256L)
    // The sweep checks SWEEP_PER_FRAME handles a frame: K frames drain what is lent; 3 frames' margin.
    val frames = drainFrames(counter("liveLent"))
    twin.call("burst_free", 0L)
    MainThread.postAfterFrames(frames) {
      val lentAfter = counter("liveLent")
      val liveAfter = counter("liveBrowserHandleCount")
      if (lent == 256L && lentAfter <= lentBase && liveAfter <= liveBase) maskC = maskC or 8L
      else note("L14 burst of $lent: lent $lentBase -> $lentAfter, live $liveBase -> $liveAfter after $frames frames")
      pausedRows()
    }
  }

  /** K + 3: the frames in which the sweep (32 handles a frame) is allowed to drain [lent] handles. */
  private fun drainFrames(lent: Long): Int = ((lent + SWEEP_PER_FRAME - 1) / SWEEP_PER_FRAME).toInt() + 3

  /** L15: the sweep runs while the tree is paused. The twin resumes it 6 frames later. */
  private fun pausedRows() {
    twin().call("emit_paused", 0L)
    // Coroutines do not resume while the tree is paused: this runs once the twin resumed it.
    MainThread.postAfterFrames(1) {
      val result = twinLong("paused_state", 0L)
      if (result == 1L && pausedArgs.size == 2 && throwsFreed("L15 node") { pausedArgs[0].call("get_name") }) {
        maskC = maskC or 16L
      } else {
        note("L15 paused: result=$result lent ${twinLong("paused_state", 1L)} -> ${twinLong("paused_state", 2L)}")
      }
      adoptedRows()
    }
  }

  /** L16 / L17: handles this script created, handed over by a dispatch. */
  private fun adoptedRows() {
    val twin = twin()
    val statesBefore = counter("audioPlayerStates.size")
    val sprite = Sprite2D.create()
    val player = AudioStreamPlayer.create()
    twin.addChild(sprite)
    twin.addChild(player)
    twin.call("emit_adopted", sprite, 9L)
    twin.call("emit_adopted", player, 0L)
    if (adoptedArgs.size == 2 && adoptedArgs[0] == sprite && adoptedRead == Vector2(9.0, 5.0)) {
      maskC = maskC or 32L
    } else {
      note("L16 ${adoptedArgs.size} dispatches, same=${adoptedArgs.firstOrNull() == sprite}, read $adoptedRead")
    }
    val statesWith = counter("audioPlayerStates.size")
    twin.call("free_adopted", 0L)
    MainThread.postAfterFrames(3) {
      val statesAfter = counter("audioPlayerStates.size")
      if (adoptedArgs.size == 2 && statesWith == statesBefore + 1 && statesAfter == statesBefore &&
        throwsFreed("L17 use") { adoptedArgs[1].call("get_name") }
      ) {
        maskC = maskC or 64L
      } else {
        note("L17 audio states $statesBefore -> $statesWith -> $statesAfter")
      }
      mintedRows()
    }
  }

  /** L18: the proxy that made the sweep's connection is freed; a later free is still released. */
  private fun mintedRows() {
    val twin = twin()
    val base = twinLong("emit_minter")
    MainThread.postAfterFrames(3) {
      val lentWith = counter("liveLent")
      twin.call("free_minted", 0L)
      MainThread.postAfterFrames(drainFrames(lentWith)) {
        val after = counter("liveLent")
        if (lentWith == base + 1 && after == base) maskC = maskC or 128L
        else note("L18 lent $base -> $lentWith -> $after after ${drainFrames(lentWith)} frames")
        pausedAdoptRows()
      }
    }
  }

  /** L19: a created handle is released while the tree is paused. */
  private fun pausedAdoptRows() {
    val twin = twin()
    val sprite = Sprite2D.create()
    twin.addChild(sprite)
    twin.call("emit_paused_adopt", sprite)
    // Coroutines do not resume while the tree is paused: this runs once the twin resumed it.
    MainThread.postAfterFrames(1) {
      val result = twinLong("paused_adopt_state", 0L)
      if (result == 1L && pausedAdoptArgs.size == 1) {
        maskC = maskC or 256L
      } else {
        note("L19 paused: result=$result handle=${twinLong("paused_adopt_state", 1L)}")
      }
      // Why a row failed: the driver traces it.
      twin.call("publish_notes", notes.joinToString(" | "))
      done = 1L
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

/** The lent handles the sweep checks each frame (the emitter's FRAME_SWEEP_BUDGET). */
private const val SWEEP_PER_FRAME = 32L
