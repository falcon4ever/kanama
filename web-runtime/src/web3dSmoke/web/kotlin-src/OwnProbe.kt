package web3d

import net.multigesture.kanama.annotations.OnInput
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.Area3D
import net.multigesture.kanama.api.Button
import net.multigesture.kanama.api.ButtonGroup
import net.multigesture.kanama.api.CollisionObject3D
import net.multigesture.kanama.api.ColorRect
import net.multigesture.kanama.api.ENetMultiplayerPeer
import net.multigesture.kanama.api.GD
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.GodotObject
import net.multigesture.kanama.api.InputEvent
import net.multigesture.kanama.api.InputEventMouseMotion
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.MainThread
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.api.Node3D
import net.multigesture.kanama.api.PhysicsRayQueryParameters3D
import net.multigesture.kanama.api.RayCast3D
import net.multigesture.kanama.api.RefCounted
import net.multigesture.kanama.api.Resource
import net.multigesture.kanama.api.MeshInstance3D
import net.multigesture.kanama.api.MultiplayerPeer
import net.multigesture.kanama.api.Signal2
import net.multigesture.kanama.api.SignalArgType
import net.multigesture.kanama.api.SpringArm3D
import net.multigesture.kanama.api.StaticBody3D
import net.multigesture.kanama.api.newScriptInstance
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector3
import net.multigesture.kanama.web.WebExperimentalGenericCall
import net.multigesture.kanama.web.WebGenericCallResult

/**
 * Harness-only: the lifetime of a RefCounted a script holds only in Kotlin, against OwnTwin
 * (own_twin.gd), which makes RefCounted objects nobody else keeps and watches them through weak
 * references. The rule is desktop's: a RefCounted you keep lives while Godot or one of your Kotlin
 * references holds it, and `close()` ends your reference early. Main's `own_probe` arms [ownArm];
 * the rows run over the following frames and [ownDone] says when they are finished.
 *
 * [mask] (the O rows of the ownership design; 16777215 when healthy):
 * - O1 `rcHeldByKotlinOutlives`: a RefCounted only Kotlin keeps answers 2, 30 and 120 frames after
 *   its dispatch, and the object still lives: 1 = the one-object signal arm, 2 = the packed arm,
 *   4 = `_input`'s event.
 * - O2 `rcReleasedWhenCollected`: 8 = once Kotlin drops them and the browser collects (Chrome:
 *   `gc()`; Firefox: allocation), the three objects are gone within 600 frames, their handles are
 *   released, the live lent handles and pinned RefCounted are back to the baseline and
 *   `rcReleasedByGc` counted them. Where no collection can be forced (Firefox, which collects only
 *   when it decides to, or a run whose bridge ignores collections) each handle is released or
 *   still pinned under the lent cap: the bound the design keeps without a collection.
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
 *   10 frames after the keeper was freed; 512 = once dropped and collected, its handle is released
 *   (where no collection can be forced: released, or still held as a RefCounted for its collection;
 *   a load is never capped).
 * - O4 `rcNeverMaterializedIsReleased`: 2048 = a RefCounted on the packed arm to a handler that
 *   never reads it is gone 4 frames later (nothing wrapped it, so its lend's floor ends).
 * - O7 `weakrefDoesNotGrow`: 4096 = 300 frames of `GD.weakref(node)` nobody keeps leave the live
 *   handles within 64 of where they were once the browser collected, or, where no collection can
 *   be forced, every handle above that is a RefCounted under the lent cap (a Variant result is
 *   capped like a lend); 8192 = `close()` on one releases its handle at once.
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
 * - `uint32Masks`: 1048576 = `PhysicsRayQueryParameters3D.create(from, to)` (the default mask
 *   0xffffffff) hits; 2097152 = `collisionLayer` / `collisionMask = 0xffffffff` and a render
 *   `layers` with bit 31 set reach the engine whole.
 * - `genericCloseKeepsSharedNode`: 4194304 = a script closes a generic call's minted result for a
 *   node another script also holds: that script's handle still answers (a node's handle lives as
 *   long as the node), after its own script is freed too.
 * - `handleRingBounded`: 8388608 = 300 frames of a generic call for one node, closed when minted,
 *   leave the sweep's ring within 64 entries of where it was.
 *
 * [cost] (O10 `rcDispatchCost`): the lending's GDScript->JS calls plus the wrapper registrations, and
 * the microseconds, per 1000 dispatches of an already-seen InputEvent (0, 2) and plain Node (1, 3);
 * (4) the frames O2 / O6 waited for the collection.
 *
 * [rayMask] (the R and N rows: ray queries and RIDs with desktop's API, and the other members a
 * shared raycasting file needs; 2097151 when healthy). A RID is a
 * value that crosses as its id in text, the query parameters stay a Kotlin value the applier builds
 * the engine object from, and a ray is one engine crossing that mints no handle:
 * - R1 `rayHitIsDictionary`: 1 = `intersectRay` answers a Map with exactly `position, normal,
 *   face_index, collider_id, collider, shape, rid`, `position` / `normal` Vector3, `face_index` /
 *   `collider_id` / `shape` Long, and `collider_id` the collider's `instanceId`.
 * - R2 `rayExcludesByRid`: 2 = a ray over OwnRayBody (A) and OwnRayBody2 (B) hits A, and with
 *   `exclude = listOf(a.getRid())` hits B.
 * - R3 `rayMissIsEmptyMap`: 4 = a ray into empty space answers `emptyMap()` (desktop parity).
 * - R4 `rayRidMatchesCollider`: 8 = `hit["rid"]` is a RID equal to the collider's `getRid()`, and
 *   the collider `==` its lookup (O9's identity).
 * - R5 `ridIsCachedPerHandle`: 16 = 1000 `getRid()` from two wrappers of one node cross once; 32 =
 *   10 000 rays leave the live browser handles where they were (no handle per ray).
 * - R6 `staleRidIsHarmless`: 64 = excluding a freed body's RID still hits, reports no error and
 *   mints no handle (Godot's own validator answers a stale RID).
 * - R7 `rayIsOneCrossing`: 128 = 100 rays are 100 engine crossings and no builtin call.
 * - R8 `rayFlags`: 256 = `collideWithAreas` hits OwnRayArea; 512 = `collisionMask = 0b10` misses
 *   the layer-1 bodies; 1024 = `hitFromInside` toggles the hit on the body the ray starts in;
 *   2048 = `collideWithBodies = false` misses.
 * - R9 `rayCastExceptionRid`: 4096 = `RayCast3D.addExceptionRid(body.getRid())` stops the cast
 *   colliding; 8192 = `SpringArm3D.addExcludedObject(body.getRid())` applies with no error reported
 *   and lets the arm reach its full length.
 * - R10 `ridSignalIsSameCodec`: 16384 = `Area3D.bodyShapeEntered`'s RID argument `==`
 *   `body.getRid()`.
 * - N1 `objectGetIsGeneric`: 32768 = `GodotObject.get(property)` answers the engine's value.
 * - N2 `modulateReadsWithoutSnapshot`: 65536 = a ColorRect whose handle came without the canvas
 *   mirror reads its `modulate` from the engine once, then its own writes.
 * - N3 `multiplayerFacadeMembers`: 131072 = `getMultiplayer()` is a member with desktop's defaults
 *   (`hasMultiplayerPeer()`, a connected `OfflineMultiplayerPeer`, peer 1, the server); ENet never
 *   connects.
 * - N4 `screenRelativeReads`: 262144 = `InputEventMouseMotion.getScreenRelative()` reads the event.
 * - R11 `ridPast2To53RoundTrips`: 524288 = the RID `0x7fffffff12345678` (past 2^53) comes back from
 *   a generic call and goes back as a generic argument with its id whole.
 * - R8b `rayHitBackFaces`: 1048576 = on a one-quad concave shape `hitBackFaces = false` leaves one
 *   of the two sides hitting, `true` (the default) both.
 *
 * [rayCost]: (0) engine crossings of 1000 `getRid()`, (1) of 100 rays, (2) live browser handles
 * gained by 10 000 rays at once and (3) a sweep later (reported only: other rows run meanwhile),
 * (4) builtin calls of 100 rays, (5) the microseconds of 10 000 rays.
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
  private var cappedBase = 0L

  /** False once the page could not force a collection (Firefox, or a bridge ignoring them). */
  private var forced = true
  private var ringBase = 0L
  private var ringHeld: WebGenericCallResult? = null

  private var rayMask = 0L
  private var rayDone = 0L
  private val rayCost = LongArray(6)

  @OnInput
  fun onInput(event: InputEvent) {
    if (recording) inputArg = event
  }

  fun ownMask(value: Long): Long = mask

  fun ownDone(value: Long): Long = done

  fun ownCost(value: Long): Long = cost[value.toInt()]

  fun ownRayMask(value: Long): Long = rayMask

  fun ownRayDone(value: Long): Long = rayDone

  fun ownRayCost(value: Long): Long = rayCost[value.toInt()]

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
    // O9 first: the body it hands over stays lent (it lives), so O2's baseline comes after it.
    rayRow()
    uint32Rows()
    rayRows()
    orphanWindowRow()
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
    rollbackRow()
    ringRows(0)
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
    val shared = sharedRow()
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
      if (shared && answers("shared +10") { LentKeeper.sharedKept?.getName() == "OwnSharedNode" }) {
        mask = mask or 4194304L
      }
    }
    MainThread.postAfterFrames(30) {
      if (resourceAnswers("O5 kept", LentKeeper.ownKept) && alive("o5")) mask = mask or 128L
    }
  }

  /**
   * `genericCloseKeepsSharedNode`: this script mints a generic call's handle for a node, the keeper
   * (another script) looks the same node up (the same handle), and this script closes its result.
   * True when the keeper's handle still answers.
   */
  private fun sharedRow(): Boolean {
    val minted = WebExperimentalGenericCall.callImmediate(twin(), "get_node", listOf("OwnSharedNode"))
    if (!minted.isMintedHandle) {
      note("shared: the generic result was ${minted.objectKind}, not minted")
      return false
    }
    LentKeeper.sharedKept = null
    if (twinLong("keeper_keeps_shared") != 1L) note("shared: the keeper's lookup found nothing")
    val same = LentKeeper.sharedKept?.handle?.value == minted.asObjectHandle()
    if (!same) note("shared: the keeper's handle is not the minted one")
    val closed = answers("shared close") { minted.close().let { true } }
    return same && closed && answers("shared after close") { LentKeeper.sharedKept?.getName() == "OwnSharedNode" }
  }

  /**
   * `handleRingBounded`: a frame's generic call for one node, its result kept to the next frame and
   * closed there (when it was minted) before the next call; the sweep's ring stays bounded.
   */
  private fun ringRows(frame: Int) {
    if (frame == 0) ringBase = twinLong("ring_size")
    runCatching {
      ringHeld?.let { if (it.isMintedHandle) it.close() }
      ringHeld = null
      if (frame < WEAKREF_FRAMES) {
        ringHeld = WebExperimentalGenericCall.callImmediate(twin(), "get_node", listOf("OwnRingNode"))
      }
    }.onFailure { note("ring +$frame: ${it::class.simpleName}: ${it.message?.take(120)}") }
    if (frame < WEAKREF_FRAMES) {
      MainThread.postAfterFrames(1) { ringRows(frame + 1) }
      return
    }
    val ring = twinLong("ring_size")
    if (ring <= ringBase + 64) mask = mask or 8388608L else note("ring $ringBase -> $ring")
  }

  /**
   * The envelope's `pageHandlesAtMostTwo`: a Window outside the tree that a script holds is an
   * ordinary node, not a third page handle beside the SceneTree's and the root Window's.
   */
  private fun orphanWindowRow() {
    val result = WebExperimentalGenericCall.callImmediate(twin(), "orphan_window")
    if (result.objectKind != "node") note("orphan window: ${result.tag} ${result.objectKind}")
  }

  /**
   * `uint32Masks`: a ray query with the default mask (0xffffffff) hits, and uint32 layer / mask
   * writes with the top bit set reach the engine unchanged (OwnTwin reads them back).
   */
  private fun uint32Rows() {
    val twin = twin()
    val from = twin.requireAs("OwnRayFrom", ::Node3D)
    val hit = answers("u32 default mask") {
      val query = checkNotNull(PhysicsRayQueryParameters3D.create(Vector3(500.0, 510.0, 500.0), Vector3(500.0, 490.0, 500.0)))
      ray(from, query).isNotEmpty()
    }
    if (hit) mask = mask or 1048576L
    val written = answers("u32 writes") {
      val body = twin.requireAs("OwnMaskBody", ::StaticBody3D)
      body.collisionLayer = 0xffffffffL
      body.collisionMask = 0xffffffffL
      twin.requireAs("OwnLayered", ::MeshInstance3D).layers = 0x80000001L
      true
    }
    MainThread.postAfterFrames(1) {
      if (written && twinLong("uint32_masks") == 1L) mask = mask or 2097152L
      else note("u32 written=$written read back ${twinLong("uint32_masks")}")
    }
  }

  /** Desktop's spelling, so this is the same code on every platform. */
  private fun ray(from: Node3D, query: PhysicsRayQueryParameters3D): Map<String, Any?> =
    from.getWorld3d()?.directSpaceState?.intersectRay(query) ?: emptyMap()

  /** A vertical ray at ([x], 500) from [fromY] down to [toY]. */
  private fun down(
    x: Double,
    fromY: Double = 510.0,
    toY: Double = 490.0,
    mask: Long = 0xffffffffL,
    exclude: List<RID> = emptyList(),
  ): PhysicsRayQueryParameters3D =
    checkNotNull(PhysicsRayQueryParameters3D.create(Vector3(x, fromY, 500.0), Vector3(x, toY, 500.0), mask, exclude))

  /** The bridge's synchronous engine crossings so far (this read is one of them). */
  private fun queries(twin: Node): Long = twin.call("counter", "immediateCrossings") as Long

  private fun body(twin: Node, name: String): CollisionObject3D =
    CollisionObject3D(checkNotNull(twin.getNodeOrNull(name)) { "no $name" }.handle)

  /** The R rows (see [rayMask]). */
  private fun rayRows() {
    val twin = twin()
    val from = twin.requireAs("OwnRayFrom", ::Node3D)
    // R5 first: the cached getRid of a body nobody asked yet.
    ridCacheRow(twin, from)
    val a = body(twin, "OwnRayBody")
    val b = body(twin, "OwnRayBody2")
    val keys = setOf("position", "normal", "face_index", "collider_id", "collider", "shape", "rid")
    if (answers("R1 dictionary") {
        val hit = ray(from, down(500.0))
        val collider = hit["collider"] as? GodotObject
        (hit.keys == keys && hit["position"] is Vector3 && hit["normal"] is Vector3 &&
          hit["face_index"] is Long && hit["collider_id"] is Long && hit["shape"] is Long &&
          collider != null && hit["collider_id"] == collider.instanceId).also {
          if (!it) note("R1 $hit")
        }
      }) rayMask = rayMask or 1L
    if (answers("R2 exclude") {
        val plain = ray(from, down(500.0))["collider"]
        val excluded = ray(from, down(500.0, exclude = listOf(a.getRid())))["collider"]
        (plain == a && excluded == b).also { if (!it) note("R2 plain=$plain excluded=$excluded") }
      }) rayMask = rayMask or 2L
    if (answers("R3 miss") {
        val miss = ray(from, down(700.0))
        miss.isEmpty() && miss == emptyMap<String, Any?>()
      }) rayMask = rayMask or 4L
    if (answers("R4 rid") {
        val hit = ray(from, down(500.0))
        val collider = hit["collider"] as? GodotObject
        val rid = hit["rid"]
        (rid is RID && collider != null && rid == CollisionObject3D(collider.handle).getRid() &&
          collider == twin.getNodeOrNull("OwnRayBody")).also { if (!it) note("R4 rid=$rid collider=$collider") }
      }) rayMask = rayMask or 8L
    staleRidRow(twin, from)
    if (answers("R11 big rid") {
        // An id past 2^53 (a double would round it) crosses whole both ways.
        val big = twin.call("big_rid")
        (big is RID && big.value == 0x7fffffff12345678L && twinLong("rid_is_big", big) == 1L).also {
          if (!it) note("R11 big=$big")
        }
      }) rayMask = rayMask or 524288L
    crossingRow(twin, from, a)
    flagRows(from, a, b, twin)
    exceptionRows(twin)
    if (answers("R10 signal") {
        val area = twin.requireAs("OwnRidArea", ::Area3D)
        var got: RID? = null
        area.bodyShapeEntered.connect(self, GodotObject.ConnectFlags.ONE_SHOT) { rid, _, _, _ -> got = rid }
        twinLong("emit_rid_signal")
        (got != null && got == a.getRid()).also { if (!it) note("R10 got=$got rid=${a.getRid()}") }
      }) rayMask = rayMask or 16384L
    parityRows(twin)
  }

  /** N1-N4: the other members a shared raycasting file needs (see [rayMask]). */
  private fun parityRows(twin: Node) {
    if (answers("N1 get") {
        val target = twin.requireAs("OwnRayCast", ::RayCast3D).get("target_position")
        (target == Vector3(0.0, -20.0, 0.0)).also { if (!it) note("N1 get=$target") }
      }) rayMask = rayMask or 32768L
    if (answers("N2 modulate") {
        // A generic call's node carries no canvas mirror: the first read asks the engine.
        val rect = ColorRect((twin.call("fade_rect") as GodotObject).handle)
        val first = rect.modulate.a
        rect.modulate = Color(1.0, 1.0, 1.0, 0.5)
        val written = rect.modulate.a
        (first == 0.25 && written == 0.5 && twinLong("fade_rect_alpha_x100") == 50L).also {
          if (!it) note("N2 modulate $first -> $written")
        }
      }) rayMask = rayMask or 65536L
    if (answers("N3 multiplayer") {
        // Desktop's defaults before a game sets a peer: a connected offline peer, peer 1, the server.
        val api = checkNotNull(self.getMultiplayer()) { "no multiplayer" }
        val peer = api.getMultiplayerPeer()
        val enet: MultiplayerPeer = ENetMultiplayerPeer.create()
        (api.hasMultiplayerPeer() && peer != null && peer.isClass("OfflineMultiplayerPeer") &&
          peer.isClass("MultiplayerPeer") && !peer.isClass("ENetMultiplayerPeer") &&
          peer.getConnectionStatus() == MultiplayerPeer.ConnectionStatus.CONNECTED &&
          api.getUniqueId() == 1 && api.isServer() && enet.isClass("ENetMultiplayerPeer") &&
          enet.getConnectionStatus() == MultiplayerPeer.ConnectionStatus.DISCONNECTED).also {
          if (!it) note("N3 has=${api.hasMultiplayerPeer()} peer=$peer")
        }
      }) rayMask = rayMask or 131072L
    if (answers("N4 screen relative") {
        var motion: Vector2? = null
        twin.signal("own_motion").connectObject(self, GodotObject.ConnectFlags.ONE_SHOT) {
          motion = InputEventMouseMotion(it.handle).getScreenRelative()
        }
        twinLong("emit_motion")
        (motion == Vector2(3.0, 4.0)).also { if (!it) note("N4 screen relative=$motion") }
      }) rayMask = rayMask or 262144L
  }

  /** R5: `getRid()` crosses once per handle; 10 000 rays mint no handle. */
  private fun ridCacheRow(twin: Node, from: Node3D) {
    val node = checkNotNull(twin.getNodeOrNull("OwnRidBody")) { "no OwnRidBody" }
    val one = CollisionObject3D(node.handle)
    val two = CollisionObject3D(node.handle)
    val cached = answers("R5 cached") {
      val before = queries(twin)
      val first = one.getRid()
      var same = first.isValid()
      for (index in 0 until 1000) {
        if ((if (index % 2 == 0) one else two).getRid() != first) same = false
      }
      rayCost[0] = queries(twin) - before - 1
      (same && rayCost[0] == 1L).also { if (!it) note("R5 same=$same crossings=${rayCost[0]}") }
    }
    if (cached) rayMask = rayMask or 16L
    val liveBefore = counter("liveBrowserHandleCount")
    val started = WebExperimentalGenericCall.nowMillis()
    val swept = answers("R5 rays") {
      val query = down(500.0)
      var hits = 0
      repeat(10_000) { if (ray(from, query).isNotEmpty()) hits += 1 }
      hits == 10_000
    }
    rayCost[5] = ((WebExperimentalGenericCall.nowMillis() - started) * 1000.0).toLong()
    rayCost[2] = counter("liveBrowserHandleCount") - liveBefore
    if (swept && rayCost[2] <= 0L) rayMask = rayMask or 32L else note("R5 live +${rayCost[2]}")
    // Reported only: the other rows run in the same frames and make handles of their own.
    MainThread.postAfterFrames(SWEEP_FRAMES) { rayCost[3] = counter("liveBrowserHandleCount") - liveBefore }
  }

  /** R6: a freed body's RID in `exclude` is Godot's to judge: the ray still hits, nothing fails. */
  private fun staleRidRow(twin: Node, from: Node3D) {
    if (answers("R6 stale") {
        val stale = twinLong("stale_rid")
        val errors = counter("callbackErrors")
        val live = counter("liveBrowserHandleCount")
        val hit = ray(from, down(500.0, exclude = listOf(RID(stale))))
        (stale != 0L && hit["collider"] == twin.getNodeOrNull("OwnRayBody") &&
          counter("callbackErrors") == errors && counter("liveBrowserHandleCount") == live).also {
          if (!it) note("R6 stale=$stale collider=${hit["collider"]}")
        }
      }) rayMask = rayMask or 64L
  }

  /** R7: a ray is one engine crossing (the cached getRid aside) and no builtin call. */
  private fun crossingRow(twin: Node, from: Node3D, a: CollisionObject3D) {
    if (answers("R7 crossings") {
        val query = down(500.0, exclude = listOf(a.getRid()))
        val builtins = counter("builtinCalls")
        val before = queries(twin)
        repeat(100) { ray(from, query) }
        rayCost[1] = queries(twin) - before - 1
        rayCost[4] = counter("builtinCalls") - builtins
        (rayCost[1] == 100L && rayCost[4] == 0L).also { if (!it) note("R7 ${rayCost[1]} crossings, ${rayCost[4]} builtin") }
      }) rayMask = rayMask or 128L
  }

  /** R8: the four query flags reach the engine. */
  private fun flagRows(from: Node3D, a: CollisionObject3D, b: CollisionObject3D, twin: Node) {
    if (answers("R8 areas") {
        val query = down(500.0).also { it.collideWithAreas = true }
        ray(from, query)["collider"] == twin.getNodeOrNull("OwnRayArea")
      }) rayMask = rayMask or 256L
    if (answers("R8 mask") { ray(from, down(500.0, mask = 0b10L)).isEmpty() }) rayMask = rayMask or 512L
    if (answers("R8 inside") {
        val outside = ray(from, down(500.0, fromY = 500.5))["collider"]
        val inside = ray(from, down(500.0, fromY = 500.5).also { it.hitFromInside = true })["collider"]
        (outside == b && inside == a).also { if (!it) note("R8 inside: off=$outside on=$inside") }
      }) rayMask = rayMask or 1024L
    if (answers("R8 bodies") {
        ray(from, down(500.0).also { it.collideWithBodies = false }).isEmpty()
      }) rayMask = rayMask or 2048L
    if (answers("R8 back faces") {
        // OwnBackFaceBody is one concave (trimesh) quad with back-face collision: whichever side is
        // its back face, a ray from that side hits it only with hitBackFaces (Godot's default, true).
        val q = { fromY: Double, toY: Double, back: Boolean ->
          ray(from, down(620.0, fromY = fromY, toY = toY).also { it.hitBackFaces = back }).isNotEmpty()
        }
        val withBack = q(510.0, 490.0, true) && q(490.0, 510.0, true)
        val without = listOf(q(510.0, 490.0, false), q(490.0, 510.0, false)).count { it }
        (withBack && without == 1).also { if (!it) note("R8 back faces: both=$withBack one-sided hits=$without") }
      }) rayMask = rayMask or 1048576L
  }

  /** R9: the RID members of RayCast3D and SpringArm3D. */
  private fun exceptionRows(twin: Node) {
    if (answers("R9 raycast") {
        val cast = twin.requireAs("OwnRayCast", ::RayCast3D)
        cast.forceRaycastUpdate()
        val before = cast.isColliding()
        cast.addExceptionRid(body(twin, "OwnCastBody").getRid())
        cast.forceRaycastUpdate()
        val after = cast.isColliding()
        (before && !after).also { if (!it) note("R9 raycast colliding $before -> $after") }
      }) rayMask = rayMask or 4096L
    val arm = twin.requireAs("OwnSpringArm", ::SpringArm3D)
    val reached = twinLong("spring_hit_length_mm")
    val rid = body(twin, "OwnSpringBody").getRid()
    val errors = counter("callbackErrors")
    // The queued exclusion applies with the next immediate call (the read below): no error then.
    arm.addExcludedObject(rid)
    twinLong("spring_hit_length_mm")
    val clean = counter("callbackErrors") == errors
    MainThread.postAfterFrames(4) {
      val length = twinLong("spring_hit_length_mm")
      if (clean && reached < 15_000L && length > 19_000L) rayMask = rayMask or 8192L
      else note("R9 spring $reached -> $length mm, clean=$clean")
      rayDone = 1L
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
      val result = ray(from, checkNotNull(query))
      check(result.isNotEmpty()) { "the ray missed" }
      val collider = result["collider"]
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
    if (frame == 0) {
      weakBase = counter("liveBrowserHandleCount")
      cappedBase = twinLong("capped_count")
    }
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
    val capped = twinLong("capped_count")
    if (live <= weakBase + 64) {
      mask = mask or 4096L
    } else if (!forced && live - weakBase <= capped - cappedBase + 64) {
      // Nothing collected them: the lent cap bounds them (released above 4096, least recently
      // wrapped first), the design's bound without a collection.
      mask = mask or 4096L
      note("O7 capped: live $weakBase -> $live, capped $cappedBase -> $capped after $frame frames")
    } else {
      note("O7 live $weakBase -> $live, capped $cappedBase -> $capped after $frame frames")
    }
    twin().call("publish_notes", notes.joinToString(" | "))
    done = 1L
  }

  /** A collection: `gc()` where the page has it (Chrome), else allocation (Firefox). */
  private fun churn() {
    if (twinLong("collect") == 0L) {
      forced = false
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
    // Without a forced collection: each handle released, or live and pinned under the lent cap.
    val states = o1Handles.map { twinLong("handle_state", it.toLong()) }
    val capped = !forced && o1Handles.size == 3 && states.all { it == 0L || it == HANDLE_CAPPED }
    if (mask and 7L == 7L && gone && handlesReleased && lent <= lentBase && pinned <= pinnedBase && byGc >= 3) {
      mask = mask or 8L
    } else if (mask and 7L == 7L && capped) {
      mask = mask or 8L
      note("O2 capped after $frame frames: states=$states")
    } else {
      note("O2 after $frame frames: gone=$gone handles=$handlesReleased states=$states lent $lentBase->$lent pinned $pinnedBase->$pinned byGc=$byGc")
    }
    // A load is never capped: without a forced collection it must still be held for its collection.
    val loadedState = if (loadedHandle == 0) 0L else twinLong("handle_state", loadedHandle.toLong())
    if (mask and 256L == 256L && loadedReleased) {
      mask = mask or 512L
    } else if (mask and 256L == 256L && !forced && loadedState and HANDLE_HELD == HANDLE_HELD) {
      mask = mask or 512L
      note("O6 held for its collection after $frame frames: state=$loadedState")
    } else {
      note("O6 released=$loadedReleased state=$loadedState after $frame frames")
    }
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

/** OwnTwin.handle_state: live and a RefCounted Kotlin wrappers hold. */
private const val HANDLE_HELD = 3L

/** OwnTwin.handle_state: live, a RefCounted Kotlin wrappers hold, and under the lent cap. */
private const val HANDLE_CAPPED = 7L

/** O8: frames for the sweep to release a freed node's handle (32 a frame, so a few). */
private const val SWEEP_FRAMES = 12
