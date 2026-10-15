package web3d

import net.multigesture.kanama.annotations.OnInput
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.Button
import net.multigesture.kanama.api.ButtonGroup
import net.multigesture.kanama.api.GD
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.GodotObject
import net.multigesture.kanama.api.InputEvent
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.MainThread
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.api.Node3D
import net.multigesture.kanama.api.PhysicsRayQueryParameters3D
import net.multigesture.kanama.api.RefCounted
import net.multigesture.kanama.api.Resource
import net.multigesture.kanama.api.Signal2
import net.multigesture.kanama.api.SignalArgType
import net.multigesture.kanama.api.getWorld3d
import net.multigesture.kanama.api.newScriptInstance
import net.multigesture.kanama.types.Vector3

/**
 * Harness-only: the lifetime of a RefCounted a script holds only in Kotlin, against OwnTwin
 * (own_twin.gd), which makes RefCounted objects nobody else keeps and watches them through weak
 * references. The rule is desktop's: a RefCounted you keep lives while Godot or one of your Kotlin
 * references holds it, and `close()` ends your reference early. Main's `own_probe` arms [ownArm];
 * the rows run over the following frames and [ownDone] says when they are finished.
 *
 * [mask] (the O rows of the ownership design; 1048575 when healthy):
 * - O1 `rcHeldByKotlinOutlives`: a RefCounted only Kotlin keeps answers 2, 30 and 120 frames after
 *   its dispatch, and the object still lives: 1 = the one-object signal arm, 2 = the packed arm,
 *   4 = `_input`'s event.
 * - O2 `rcReleasedWhenCollected`: 8 = once Kotlin drops them and the browser collects (Chrome:
 *   `gc()`; Firefox: allocation), the three objects are gone within 600 frames, their handles are
 *   released, the live lent handles and pinned RefCounted are back to the baseline and
 *   `rcReleasedByGc` counted them.
 * - O3 `rcCloseReleasesNow`: 16 = a RefCounted argument is `AutoCloseable` and `close()` on its one
 *   wrapper releases it (the object is gone a frame later); 32 = a second wrapper of the same object
 *   made before the close still answers after it; 64 = a use of the closed wrapper throws
 *   "RefCounted handle is closed".
 * - O3b `rcCloseAfterQueuedSet`: 1024 = `ButtonGroup.create().use { button.buttonGroup = it }` (the
 *   set is queued, the close is not): a frame later the button still has the group, as on desktop,
 *   where the set reached the engine before the close.
 * - O5 `rcOutlivesItsReceiver`: 128 = a RefCounted argument a keeper script stored, read 30 frames
 *   after the keeper was freed.
 * - O6 `rcResultOutlivesItsScript`: 256 = a resource a keeper script loaded into its companion, read
 *   10 frames after the keeper was freed; 512 = once dropped and collected, its handle is released.
 * - O4 `rcNeverMaterializedIsReleased`: 2048 = a RefCounted on the packed arm to a handler that
 *   never reads it is gone 4 frames later (nothing wrapped it, so its lend's floor ends).
 * - O7 `weakrefDoesNotGrow`: 4096 = 300 frames of `GD.weakref(node)` nobody keeps leave the live
 *   handles within 64 of where they were once the browser collected; 8192 = `close()` on one
 *   releases its handle at once.
 * - O8 `lookupSurvivesQueueFree`: 16384 = a node looked up and `queueFree()`d still answers a typed
 *   call in the same frame, and a dispatch of it in that frame hands over the same value; 32768 = a
 *   use a frame later throws desktop's "previously freed instance", and the live handles are back.
 * - O9 `rayHitColliderIsLent`: 65536 = a ray that hits a body no script tracks answers
 *   `collider == getNodeOrNull(...)` of that body.
 * - O11 `nodeOutlivesItsReceiver`: 131072 = a node a keeper script looked up into its companion,
 *   read 10 frames after the keeper was freed; 262144 = and one a top-level initializer made while
 *   the keeper ran.
 * - `constructorRollbackAfterFlush`: 524288 = a call queued before a constructor that applies the
 *   queue (a `close()`) and then throws runs once, not again after the failed construction.
 *
 * [cost] (O10 `rcDispatchCost`): the lending's GDScript->JS calls plus the wrapper registrations, and
 * the microseconds, per 1000 dispatches of an already-seen InputEvent (0, 2) and plain Node (1, 3);
 * (4) the frames O2 / O6 waited for the collection.
 */
@ScriptClass(attachTo = "Node")
class OwnProbe(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
  private var mask = 0L
  private var done = 0L
  private val cost = LongArray(5)
  private val notes = mutableListOf<String>()

  private var recording = false
  private var oneArg: GodotObject? = null
  private var twoArg: GodotObject? = null
  private var inputArg: InputEvent? = null
  private val closeArgs = mutableListOf<GodotObject>()
  private var closeSecond: GodotObject? = null
  private var held = 7L
  private var lentBase = 0L
  private var pinnedBase = 0L
  private var gcBase = 0L
  private val o1Handles = mutableListOf<Int>()
  private var loadedHandle = 0
  private var doomedArg: GodotObject? = null
  private var weakBase = 0L

  @OnInput
  fun onInput(event: InputEvent) {
    if (recording) inputArg = event
  }

  fun ownMask(value: Long): Long = mask

  fun ownDone(value: Long): Long = done

  fun ownCost(value: Long): Long = cost[value.toInt()]

  private fun twin(): Node = self.requireAs("../OwnTwin", ::Node)

  private fun twinLong(method: String, arg: Any? = 0L): Long = twin().call(method, arg) as Long

  private fun counter(name: String): Long = twin().call("counter", name) as Long

  private fun alive(name: String): Boolean = twinLong("alive", name) == 1L

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

  private fun resourceAnswers(label: String, value: GodotObject?): Boolean =
    answers(label) { checkNotNull(value) { "nothing stored" }.call("get_class") == "Resource" }

  /** Starts the rows (driver: Main.own_probe). */
  fun ownArm(value: Long): Long {
    val twin = twin()
    lentBase = counter("liveLent")
    pinnedBase = counter("rcPinned")
    gcBase = counter("rcReleasedByGc")
    twin.signal("own_one").connectObject(self) { oneArg = it }
    Signal2(twin, "own_two", SignalArgType.VARIANT, SignalArgType.LONG).connect(self) { arg, _ ->
      twoArg = arg as GodotObject
    }
    twin.signal("own_close").connectObject(self) { closeArgs += it }
    twin.signal("own_cost").connectObject(self) { _ -> }
    twin.signal("own_ignored").connect(self) {}
    twin.signal("own_doomed").connectObject(self) { doomedArg = it }

    // O1: one RefCounted per dispatch path, only Kotlin keeps them.
    recording = true
    twin.call("emit_held", 0L)
    recording = false
    listOfNotNull(oneArg, twoArg, inputArg).forEach { o1Handles += it.handle.value }
    if (o1Handles.size != 3) note("O1 stored ${o1Handles.size} of 3 arguments")
    closeRows()
    queuedSetRow()
    keeperRows()
    ignoredRow()
    queueFreeRow()
    rayRow()
    rollbackRow()
    MainThread.postAfterFrames(2) { checkHeld(2) }
    MainThread.postAfterFrames(30) { checkHeld(30) }
    MainThread.postAfterFrames(120) {
      checkHeld(120)
      if (o1Handles.size == 3) mask = mask or held
      collectRows()
    }
    return 1L
  }

  /** O1: each stored argument still answers, and its object still lives. */
  private fun checkHeld(frames: Int) {
    if (!resourceAnswers("O1 one +$frames", oneArg) || !alive("o1_one")) held = held and 1L.inv()
    if (!resourceAnswers("O1 packed +$frames", twoArg) || !alive("o1_two")) held = held and 2L.inv()
    val input = answers("O1 input +$frames") { checkNotNull(inputArg) { "no event" }.isPressed() }
    if (!input || !alive("o1_input")) held = held and 4L.inv()
    if (held != 7L) note("O1 +$frames: held=$held alive=${alive("o1_one")},${alive("o1_two")},${alive("o1_input")}")
  }

  /** O3: close() on a wrapper ends that wrapper's hold, at once. */
  private fun closeRows() {
    val twin = twin()
    twin.call("emit_close", 0L)
    twin.call("emit_close", 1L)
    if (closeArgs.size != 2) {
      note("O3 ${closeArgs.size} dispatches")
      return
    }
    val lone = closeArgs[0]
    val shared = closeArgs[1]
    closeSecond = Resource(shared.handle)
    val loneClose = lone as? AutoCloseable
    val sharedClose = shared as? AutoCloseable
    if (loneClose == null || sharedClose == null) {
      note("O3 a RefCounted argument is not AutoCloseable")
      return
    }
    loneClose.close()
    sharedClose.close()
    val closedUse =
      try {
        lone.call("get_class")
        note("O3 use after close: did not throw")
        false
      } catch (e: IllegalStateException) {
        (e.message?.contains("RefCounted handle is closed") == true).also {
          if (!it) note("O3 use after close: ${e.message?.take(160)}")
        }
      }
    if (closedUse) mask = mask or 64L
    MainThread.postAfterFrames(1) {
      if (!alive("o3_0")) mask = mask or 16L else note("O3 the closed object still lives")
      if (resourceAnswers("O3 second wrapper", closeSecond) && alive("o3_1")) mask = mask or 32L
    }
  }

  /** O3b: a queued set that hands a RefCounted to the engine applies before its close() releases. */
  private fun queuedSetRow() {
    val button = twin().requireAs("OwnButton", ::Button)
    ButtonGroup.create().use { button.buttonGroup = it }
    MainThread.postAfterFrames(1) {
      if (twinLong("button_has_group") == 1L) mask = mask or 1024L
      else note("O3b the button lost the ButtonGroup closed after its queued set")
    }
  }

  /** O5 / O6: a keeper script stores a RefCounted it received and one it loaded, then is freed. */
  private fun keeperRows() {
    val twin = twin()
    LentKeeper.ownKept = null
    LentKeeper.ownLoaded = null
    twin.call("make_keeper", 0L)
    twin.call("keeper_receives", 0L)
    val loaded = twinLong("keeper_loads") == 1L
    loadedHandle = LentKeeper.ownLoaded?.handle?.value ?: 0
    if (!loaded || LentKeeper.ownKept == null) note("O5/O6 keeper: loaded=$loaded kept=${LentKeeper.ownKept}")
    LentKeeper.nodeKept = null
    LentKeeper.madeKept = null
    if (twinLong("keeper_keeps_nodes") != 1L) note("O11 keeper: lookup found nothing")
    twin.call("free_keeper", 0L)
    MainThread.postAfterFrames(10) {
      if (answers("O6 path") { LentKeeper.ownLoaded?.call("get_path") == LentKeeper.OWN_LOADED_PATH }) {
        mask = mask or 256L
      }
      if (answers("O11 lookup") { checkNotNull(LentKeeper.nodeKept) { "nothing kept" }.getName() == "OwnKeptNode" }) {
        mask = mask or 131072L
      }
      if (answers("O11 made") { checkNotNull(LentKeeper.madeKept) { "nothing kept" }.getName() == "OwnMadeSprite" }) {
        mask = mask or 262144L
      }
    }
    MainThread.postAfterFrames(30) {
      if (resourceAnswers("O5 kept", LentKeeper.ownKept) && alive("o5")) mask = mask or 128L
    }
  }

  /** O4: a RefCounted the handler never reads is released once its lend's floor is over. */
  private fun ignoredRow() {
    twin().call("emit_ignored", 0L)
    MainThread.postAfterFrames(4) {
      if (!alive("o4")) mask = mask or 2048L else note("O4 the ignored argument still lives")
    }
  }

  /** O8: a looked-up node lives to the end of the frame its `queueFree()` was queued in. */
  private fun queueFreeRow() {
    val twin = twin()
    twin.call("make_doomed", 0L)
    val base = counter("liveBrowserHandleCount")
    val doomed = twin.getNodeOrNull("OwnDoomed")
    if (doomed == null) {
      note("O8 lookup found nothing")
      return
    }
    doomed.queueFree()
    val sameFrame = answers("O8 same frame") { doomed.getName() == "OwnDoomed" }
    doomedArg = null
    val dispatched = twinLong("emit_doomed") == 1L
    val handedOver = answers("O8 dispatch") { doomedArg == doomed && doomedArg?.call("get_name") == "OwnDoomed" }
    if (sameFrame && dispatched && handedOver) mask = mask or 16384L
    MainThread.postAfterFrames(1) {
      val freed = throwsFreed("O8 next frame") { doomed.getName() }
      MainThread.postAfterFrames(SWEEP_FRAMES) {
        val live = counter("liveBrowserHandleCount")
        if (freed && live <= base) mask = mask or 32768L
        else note("O8 freed=$freed live $base -> $live")
      }
    }
  }

  private fun throwsFreed(label: String, use: () -> Unit): Boolean =
    try {
      use()
      note("$label: did not throw")
      false
    } catch (e: IllegalStateException) {
      (e.message?.contains("previously freed instance") == true).also {
        if (!it) note("$label: ${e.message?.take(160)}")
      }
    }

  /** O9: a ray hitting a body no script tracks answers that body. */
  private fun rayRow() {
    val twin = twin()
    val from = twin.requireAs("OwnRayFrom", ::Node3D)
    val query = PhysicsRayQueryParameters3D.create(Vector3(500.0, 510.0, 500.0), Vector3(500.0, 490.0, 500.0), 1L)
    val hit = answers("O9 query") {
      val result = from.getWorld3d().directSpaceState.intersectRay(checkNotNull(query))
      val collider = checkNotNull(result) { "the ray missed" }.collider
      val body = twin.getNodeOrNull("OwnRayBody")
      (collider != null && collider == body).also { if (!it) note("O9 collider=$collider body=$body") }
    }
    if (hit) mask = mask or 65536L
  }

  /**
   * The constructor rollback: a group call queued (OwnTwin counts it), then a scripted resource
   * whose constructor applies the queue and throws. The call ran once, never again from the
   * restored queue.
   */
  private fun rollbackRow() {
    val before = twinLong("own_bumps")
    self.getTree().callGroup("own_bump", "own_bump")
    val failed = runCatching { newScriptInstance<ThrowingCloser>() }.isFailure
    MainThread.postAfterFrames(2) {
      val bumps = twinLong("own_bumps") - before
      if (failed && bumps == 1L) mask = mask or 524288L
      else note("rollback failed=$failed ran $bumps times")
    }
  }

  /**
   * O7: `GD.weakref` results nobody keeps do not pile up: 300 frames of one each, then the browser
   * collects; `close()` on one releases its handle at once.
   */
  private fun weakrefRows(frame: Int) {
    if (frame == 0) weakBase = counter("liveBrowserHandleCount")
    if (frame < WEAKREF_FRAMES) {
      GD.weakref(twin())
      MainThread.postAfterFrames(1) { weakrefRows(frame + 1) }
      return
    }
    val closing = GD.weakref(twin()) as? RefCounted
    val handle = closing?.handle?.value ?: 0
    closing?.close()
    if (closing != null && twinLong("handle_live", handle.toLong()) == 0L) mask = mask or 8192L
    else note("O7 close: closeable=${closing != null} still live")
    weakSettle(0)
  }

  private fun weakSettle(frame: Int) {
    churn()
    val live = counter("liveBrowserHandleCount")
    if (live > weakBase + 64 && frame < SETTLE_FRAMES) {
      MainThread.postAfterFrames(1) { weakSettle(frame + 1) }
      return
    }
    garbage = null
    if (live <= weakBase + 64) mask = mask or 4096L
    else note("O7 live $weakBase -> $live after $frame frames")
    twin().call("publish_notes", notes.joinToString(" | "))
    done = 1L
  }

  /** A collection: `gc()` where the page has it (Chrome), else allocation (Firefox). */
  private fun churn() {
    if (twinLong("collect") == 0L) {
      garbage = ByteArray(4 shl 20)
      var sum = 0
      repeat(8000) { sum += IntArray(4) { i -> i + it }.sum() }
      if (sum < 0) note("unreachable")
    }
  }

  /**
   * O2 / O6: Kotlin drops everything it kept; the browser collects (Chrome: `gc()` every frame;
   * Firefox, which has none, collects only under allocation pressure: 4 MB a frame).
   */
  private fun collectRows() {
    oneArg = null
    twoArg = null
    inputArg = null
    closeArgs.clear()
    closeSecond = null
    LentKeeper.ownKept = null
    LentKeeper.ownLoaded = null
    settle(0)
  }

  private var garbage: Any? = null

  private fun settle(frame: Int) {
    churn()
    val gone = !alive("o1_one") && !alive("o1_two") && !alive("o1_input")
    val loadedReleased = loadedHandle != 0 && twinLong("handle_live", loadedHandle.toLong()) == 0L
    if ((!gone || !loadedReleased) && frame < SETTLE_FRAMES) {
      MainThread.postAfterFrames(1) { settle(frame + 1) }
      return
    }
    garbage = null
    cost[4] = frame.toLong()
    val handlesReleased = o1Handles.size == 3 && o1Handles.all { twinLong("handle_live", it.toLong()) == 0L }
    val lent = counter("liveLent")
    val pinned = counter("rcPinned")
    val byGc = counter("rcReleasedByGc") - gcBase
    if (mask and 7L == 7L && gone && handlesReleased && lent <= lentBase && pinned <= pinnedBase && byGc >= 3) {
      mask = mask or 8L
    } else {
      note("O2 after $frame frames: gone=$gone handles=$handlesReleased lent $lentBase->$lent pinned $pinnedBase->$pinned byGc=$byGc")
    }
    if (mask and 256L == 256L && loadedReleased) mask = mask or 512L
    else note("O6 released=$loadedReleased after $frame frames")
    costRows()
    weakrefRows(0)
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

/** The frames O2 / O6 / O7 wait for the browser to collect (the ownership design's teardown bound). */
private const val SETTLE_FRAMES = 600

/** O7: the frames that each make one `GD.weakref` nobody keeps. */
private const val WEAKREF_FRAMES = 300

/** O8: frames for the sweep to release a freed node's handle (32 a frame, so a few). */
private const val SWEEP_FRAMES = 12
