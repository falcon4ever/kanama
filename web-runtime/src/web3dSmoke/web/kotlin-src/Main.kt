package web3d

import kotlin.math.PI
import kotlin.math.abs
import net.multigesture.kanama.annotations.Export
import net.multigesture.kanama.annotations.OnEnterTree
import net.multigesture.kanama.annotations.OnProcess
import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.PropertyHint
import net.multigesture.kanama.annotations.RegisterFunction
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.annotations.ScriptProperty
import net.multigesture.kanama.annotations.Signal
import net.multigesture.kanama.api.AudioStreamPlayer
import net.multigesture.kanama.api.CanvasLayer
import net.multigesture.kanama.api.DirectionalLight3D
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.GodotObject
import net.multigesture.kanama.api.Input
import net.multigesture.kanama.api.KanamaCoroutineOwner
import net.multigesture.kanama.api.KanamaScope
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.MainThread
import net.multigesture.kanama.api.ManualGodotLifetimeApi
import net.multigesture.kanama.api.Node3D
import net.multigesture.kanama.api.OS
import net.multigesture.kanama.api.RenderingServer
import net.multigesture.kanama.api.Resource
import net.multigesture.kanama.api.ResourceLoader
import net.multigesture.kanama.api.SceneTree
import net.multigesture.kanama.api.WorldEnvironment
import net.multigesture.kanama.api.genericWebGameplayFallback
import net.multigesture.kanama.api.lookAt
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.Vector3
import net.multigesture.kanama.web.WebExperimentalGenericCall
import kotlinx.coroutines.launch

/**
 * Kanama Web 3D rendering-foundation smoke (Task 60c).
 *
 * Drives the same families the Starter-Kit-3D-Platformer's main.gd does (mobile-control
 * visibility, Compatibility-renderer light/environment tuning) and additionally spins a Node3D
 * each frame to exercise the read-your-write transform path — all through the Web backend.
 *
 * Task 66b addition: the enter-tree ordering proof. [enterTree] runs from `@OnEnterTree` (the
 * protocol-16 crossing) and records (a) that it ran at all, (b) that the scene-exported
 * `@ScriptProperty` value was already pushed when it ran — `_enter_tree` calls
 * `_kanama_ensure_created()`, which constructs the Kotlin instance and applies every export
 * before dispatching — and (c) via [ready], that it ran BEFORE `_ready`. The driver reads the
 * combined mask through [enterTreeProbe] and fails the smoke unless it is exactly 7.
 *
 * Task 82 addition: [coroutineProbe] / [coroutineProbeMask] are the coroutine conformance probe —
 * the generic, demo-independent proof that the Web frame scheduler is actually pumped, so a
 * `delaySeconds` resumes instead of hanging forever in silence.
 */
@ScriptClass(attachTo = "Node3D")
class Main(godotObject: GodotHandle) :
  KanamaScript<Node3D>(godotObject, ::Node3D), KanamaCoroutineOwner {
  /** Task 82: owns the coroutine conformance probe, so owner teardown cancels it. */
  override val kanamaScope = KanamaScope()

  /** Overridden in main.tscn to [ENTER_TREE_EXPORTED] — never the default — for the 66b proof. */
  @ScriptProperty var enterTreeGreeting: String = "unset"

  /**
   * Task-64 NodePath push proof: overridden in main.tscn to `NodePath("Spinner")` — never the
   * default — and [propertyProbe] both compares the pushed path and resolves a live node
   * through the NodePath [net.multigesture.kanama.api.Node] accessor overloads.
   */
  @ScriptProperty var spinnerPath: NodePath = NodePath("")

  /**
   * Task-64 hint-metadata + one-line-annotation proof: the proxy must declare this as
   * `@export_range(0, 100, 1)`, and the deliberately one-line annotated declaration exercises
   * the initializer parser (the annotation arguments contain `=` before the real initializer).
   * Overridden in main.tscn to 47 — never the default 5.
   */
  @Export(hint = PropertyHint.RANGE, hintString = "0,100,1") var probeRangeValue: Long = 5

  private lateinit var spinner: Node3D
  private var angle = 0.0
  private var enterTreeRan = false
  private var enterTreeSawExportedValue = false
  private var enterTreeBeforeReady = false

  @OnEnterTree
  fun enterTree() {
    enterTreeRan = true
    enterTreeSawExportedValue = enterTreeGreeting == ENTER_TREE_EXPORTED
  }

  @OnReady
  fun ready() {
    enterTreeBeforeReady = enterTreeRan

    self.requireAs("MobileControls", ::CanvasLayer).visible =
      OS.hasFeature("android") || OS.hasFeature("ios")

    if (RenderingServer.getCurrentRenderingMethod() == "gl_compatibility") {
      val sun = self.requireAs("Sun", ::DirectionalLight3D)
      sun.lightEnergy = 0.24
      sun.shadowOpacity = 0.85
      self.requireAs("Environment", ::WorldEnvironment).environment.backgroundEnergyMultiplier =
        0.25
    }

    spinner = self.requireAs("Spinner", ::Node3D)
    armSignalPayloadProbe()
  }

  @OnProcess
  fun process(delta: Double) {
    angle += delta
    spinner.rotation = Vector3(0.0, angle, 0.0)
  }

  @RegisterFunction("_on_jump_button_button_down")
  fun onJumpButtonButtonDown() {
    Input.actionPress("jump")
  }

  @RegisterFunction("_on_jump_button_button_up")
  fun onJumpButtonButtonUp() {
    Input.actionRelease("jump")
  }

  /**
   * Task-64 API-parity probe (driver method #3).
   *
   * Exercises the parity pack end to end: `Resource.fromHandle` identity on an already-held
   * resource handle, `AudioStreamPlayer.setStream` assign + play + null-clear (the desktop
   * stop-all spelling), then aims the root at +X with the DEFAULT forward convention (-Z toward
   * the target, global yaw -PI/2). The driver reads the yaw back over the immediate
   * global-rotation channel; a failed check throws before the aim, so the read-back only matches
   * when everything above it passed (and the throw itself surfaces as a callback fault).
   */
  @OptIn(ManualGodotLifetimeApi::class)
  @RegisterFunction("parity_probe")
  fun parityProbe() {
    // Item 1: Resource.fromHandle round-trips an already-held handle to the same instance.
    val stream =
      checkNotNull(ResourceLoader.loadAudioStream("res://assets/beep.wav")) {
        "parity: beep.wav did not load as an AudioStream"
      }
    val retyped = Resource.fromHandle(stream.handle)
    check(retyped.isSameInstance(stream)) { "parity: fromHandle broke instance identity" }
    check(retyped.handle.value == stream.handle.value) { "parity: fromHandle changed the handle" }

    // Item 2: setStream assigns the held stream, plays, then null-clears; the stream handle is
    // released afterwards, so the teardown's live-handle drain to zero also gates this path.
    val player = AudioStreamPlayer.create()
    self.addChild(player)
    player.setVolumeDb(-60.0)
    player.setStream(stream)
    player.play()
    player.setStream(null)
    player.queueFree()
    stream.close()

    // Item 3 baseline: default look at +X reads back a -PI/2 global yaw.
    self.lookAtFromPosition(self.globalPosition, self.globalPosition + Vector3(1.0, 0.0, 0.0))
    check(abs(self.globalRotation.y + PI / 2) < 1e-3) {
      "parity: default lookAt yaw was ${self.globalRotation.y}, expected -PI/2"
    }
  }

  /**
   * Task-64 API-parity probe (driver method #4): the same +X target with useModelFront = true
   * flips the aim to +Z-forward (global yaw +PI/2) — the fps Enemy's 180-degree gap. The driver
   * asserts the PI flip against method #3's read-back.
   */
  @RegisterFunction("parity_model_front_look")
  fun parityModelFrontLook() {
    self.lookAt(self.globalPosition + Vector3(1.0, 0.0, 0.0), useModelFront = true)
    check(abs(self.globalRotation.y - PI / 2) < 1e-3) {
      "parity: model-front lookAt yaw was ${self.globalRotation.y}, expected +PI/2"
    }
  }

  /**
   * Harness probe (method#5): bit 1 = `@OnEnterTree` dispatched, bit 2 = the exported (non-default)
   * `@ScriptProperty` value was visible inside it, bit 4 = it ran before `@OnReady`. A healthy run
   * returns 7. The argument is unused (the Int->Int shape is what the callInt transport carries;
   * the proxy's ready-path immediate call passes 47 and records the same mask).
   */
  @RegisterFunction("enter_tree_probe")
  fun enterTreeProbe(value: Long): Long {
    var mask = 0L
    if (enterTreeRan) mask = mask or 1L
    if (enterTreeSawExportedValue) mask = mask or 2L
    if (enterTreeBeforeReady) mask = mask or 4L
    return mask
  }

  /**
   * Task-76 generic-callv probe (driver method #6): exercises the generic fallback end to end.
   * The browser driver triggers this and reads the published JSON report.
   *
   * Probed methods with no typed opcode route through the generic tier and carry
   * `genericWebGameplayFallback` markers for the coverage report's slow-path bucket;
   * `is_in_group`/`get_parent` have typed twins, which makes them the cost comparator and the
   * tracked-object-return probe.
   */
  @RegisterFunction("generic_probe")
  fun genericProbe() {
    val generic = WebExperimentalGenericCall
    genericWebGameplayFallback("Node.set_meta")
    genericWebGameplayFallback("Node.get_meta")
    genericWebGameplayFallback("Node.add_to_group")
    genericWebGameplayFallback("Node.get_node")
    genericWebGameplayFallback("Node.get_window")
    genericWebGameplayFallback("Node.get_multiplayer")
    genericWebGameplayFallback("Resource.duplicate")
    genericWebGameplayFallback("WorldEnvironment.set_environment")
    genericWebGameplayFallback("WorldEnvironment.get_environment")

    // (a) Queued generic mutations on methods with no typed opcode.
    generic.queueVoidCall(spinner, "set_meta", listOf("kanama_generic_probe", 42))
    generic.queueVoidCall(spinner, "add_to_group", listOf("kanama_generic_smoke"))

    // (b) Immediate generic read-back proving (a) applied through the queued channel
    // (callImmediate flushes the command buffer first, like every typed immediate family).
    val meta = generic.callImmediate(spinner, "get_meta", listOf("kanama_generic_probe"))
    val inGroup = generic.callImmediate(spinner, "is_in_group", listOf("kanama_generic_smoke"))

    // Escaping: a hostile string (separators, colons, percent-escape look-alikes, quotes)
    // must round-trip through queued args AND the string-return payload unchanged.
    val hostile = "a\u001fb:c%1F\u001fd\\e\"f%25"
    generic.queueVoidCall(spinner, "set_meta", listOf("kanama_generic_hostile", hostile))
    val hostileBack = generic.callImmediate(spinner, "get_meta", listOf("kanama_generic_hostile"))
    val hostileRoundTrip = hostileBack.tag == "s" && hostileBack.asString() == hostile

    // (c) Already-tracked object returns: a script-backed node (this Main) and a tracked
    // engine node (the spinner).
    val parent = generic.callImmediate(spinner, "get_parent")
    val child = generic.callImmediate(self, "get_node", listOf("Spinner"))

    // (d) MINTED object returns, one per kind. The Window (a Node) is deliberately left
    // unclosed: the smoke's liveAfterTeardown === 0 gate proves owner teardown drains
    // minted handles. The MultiplayerAPI (RefCounted, not a Resource -> plain "object")
    // is closed explicitly to exercise the OBJECT-kind release lane.
    val window = generic.callImmediate(self, "get_window")
    val multiplayer = generic.callImmediate(self, "get_multiplayer")
    var closedObjectOk = false
    if (multiplayer.isMintedHandle) {
      multiplayer.close()
      closedObjectOk = true
    }

    // (e) Minted RESOURCE + the task-61 handoff-then-close shape: duplicate the tracked
    // Environment (mint), tag it, hand it to the engine (queued generic set_environment),
    // verify the minted handle is now discoverable as "tracked", CLOSE our handle, then
    // prove the engine's reference kept the resource alive (get_meta through a re-minted
    // handle reads the tag back).
    val envNode = self.requireAs("Environment", ::WorldEnvironment)
    val baseEnv = generic.callImmediate(envNode, "get_environment")
    val baseEnvObject = GodotObject(GodotHandle(baseEnv.asObjectHandle()))
    val dup = generic.callImmediate(baseEnvObject, "duplicate", listOf(false))
    var handoffTrackedOk = false
    var handoffSurvivedClose = false
    if (dup.isMintedHandle && dup.objectKind == "resource") {
      val dupObject = GodotObject(GodotHandle(dup.asObjectHandle()))
      generic.queueVoidCall(dupObject, "set_meta", listOf("kanama_handoff", 7))
      generic.queueVoidCall(envNode, "set_environment", listOf(dupObject))
      // Immediate call flushes the queued handoff, then must find OUR minted handle.
      val afterHandoff = generic.callImmediate(envNode, "get_environment")
      handoffTrackedOk =
        afterHandoff.objectKind == "tracked" &&
          afterHandoff.asObjectHandle() == dup.asObjectHandle()
      dup.close()
      // The engine's own reference must have kept the duplicated resource alive: reading
      // it back re-mints a fresh handle whose meta tag survives.
      val survived = generic.callImmediate(envNode, "get_environment")
      if (survived.objectKind == "resource") {
        val survivedMeta =
          generic.callImmediate(
            GodotObject(GodotHandle(survived.asObjectHandle())),
            "get_meta",
            listOf("kanama_handoff"),
          )
        handoffSurvivedClose = survivedMeta.tag == "i" && survivedMeta.asLong() == 7L
      }
    }

    // Cost: N generic immediate crossings vs N typed immediate crossings of the SAME call
    // (Node.is_in_group) on the same receiver, timed around the whole loop.
    val iterations = 500
    var genericHits = 0
    val genericStart = generic.nowMillis()
    repeat(iterations) {
      if (generic.callImmediate(spinner, "is_in_group", listOf("kanama_generic_smoke")).asBoolean())
        genericHits += 1
    }
    val genericMs = generic.nowMillis() - genericStart
    var typedHits = 0
    val typedStart = generic.nowMillis()
    repeat(iterations) { if (spinner.isInGroup("kanama_generic_smoke")) typedHits += 1 }
    val typedMs = generic.nowMillis() - typedStart

    // Report defensively (bad tags become -1/"") so a policy regression fails the driver's
    // assertions with evidence instead of faulting the callback.
    fun handleOf(result: net.multigesture.kanama.web.WebGenericCallResult): Int =
      if (result.tag == "o") result.asObjectHandle() else -1
    fun kindOf(result: net.multigesture.kanama.web.WebGenericCallResult): String =
      result.objectKind ?: ""
    generic.publishProbeReport(
      "{" +
        "\"metaTag\":\"${meta.tag}\",\"metaValue\":${if (meta.tag == "i") meta.asLong() else -1}," +
        "\"groupTag\":\"${inGroup.tag}\"," +
        "\"groupValue\":${inGroup.tag == "b" && inGroup.asBoolean()}," +
        "\"hostileRoundTrip\":$hostileRoundTrip," +
        "\"parentTag\":\"${parent.tag}\",\"parentKind\":\"${kindOf(parent)}\"," +
        "\"parentHandle\":${handleOf(parent)}," +
        "\"mainHandle\":${self.handle.value}," +
        "\"childTag\":\"${child.tag}\",\"childKind\":\"${kindOf(child)}\"," +
        "\"childHandle\":${handleOf(child)}," +
        "\"spinnerHandle\":${spinner.handle.value}," +
        "\"windowKind\":\"${kindOf(window)}\",\"windowHandle\":${handleOf(window)}," +
        "\"multiplayerKind\":\"${kindOf(multiplayer)}\"," +
        "\"multiplayerHandle\":${handleOf(multiplayer)}," +
        "\"closedObjectOk\":$closedObjectOk," +
        "\"dupKind\":\"${kindOf(dup)}\",\"dupHandle\":${handleOf(dup)}," +
        "\"handoffTrackedOk\":$handoffTrackedOk," +
        "\"handoffSurvivedClose\":$handoffSurvivedClose," +
        "\"iterations\":$iterations," +
        "\"genericHits\":$genericHits,\"typedHits\":$typedHits," +
        "\"genericMs\":$genericMs,\"typedMs\":$typedMs" +
        "}"
    )
  }

  /**
   * Task-64 property-push probe (driver method #7): bit 1 = the scene-exported NodePath value
   * (`NodePath("Spinner")`, never the default) was pushed into Kotlin, bit 2 = the scene value
   * 47 of the RANGE-hinted one-line-annotated export arrived (proving the initializer parser
   * and the hint path end to end), bit 4 = the pushed NodePath resolves a live node through the
   * NodePath accessor overload. A healthy run returns exactly 7.
   */
  @RegisterFunction("property_probe")
  fun propertyProbe(value: Long): Long {
    var mask = 0L
    if (spinnerPath.path == "Spinner") mask = mask or 1L
    if (probeRangeValue == 47L) mask = mask or 2L
    if (self.getAsOrNull(spinnerPath, ::Node3D) != null) mask = mask or 4L
    return mask
  }

  // ---------- Task 80 slice 2: dispatch-shape conformance probe ----------
  //
  // The shapes slice 2 admitted are exercised HERE, through the real crossing, because a shape
  // that only the emitter tests cover is a shape nothing has ever actually run. Each probe method
  // below is reached the way gameplay reaches one: Kotlin asks Godot to call it BY NAME, Godot
  // dispatches to the generated GDScript proxy, and the proxy takes the new arm.
  //
  // This is the miniature of task 80's slice 4 (a backend conformance fixture): it catches what
  // the manifest cannot, namely a shape that dispatches but delivers the WRONG VALUE.

  @Signal("dispatch_probe_scalar") fun dispatchProbeScalar(value: Long) = Unit

  private var probeFloatArgument = 0.0
  private var probeBoolArgument = false
  private var probeImpactSum = Vector3.ZERO
  private var probeSignalPayload = -1L
  private var probeTagArgument = ""
  private var probeTagNodeMatched = false
  private var probeJoinId = -1L
  private var probeJoinNodeWasNull = false
  private var coroutineMask = 0L

  @RegisterFunction("dispatch_probe_float")
  fun dispatchProbeFloat(amount: Double) {
    probeFloatArgument = amount
  }

  @RegisterFunction("dispatch_probe_bool")
  fun dispatchProbeBool(flag: Boolean) {
    probeBoolArgument = flag
  }

  /** The third-person `damage(impactPoint, force)` shape: six numeric slots, the widest one. */
  @RegisterFunction("dispatch_probe_impact")
  fun dispatchProbeImpact(impactPoint: Vector3, force: Vector3) {
    probeImpactSum = impactPoint + force
  }

  @RegisterFunction("dispatch_probe_vector") fun dispatchProbeVector(): Vector3 = PROBE_VECTOR

  @RegisterFunction("dispatch_probe_number") fun dispatchProbeNumber(): Double = PROBE_NUMBER

  @RegisterFunction("dispatch_probe_text") fun dispatchProbeText(): String = PROBE_TEXT

  @RegisterFunction("dispatch_probe_flag") fun dispatchProbeFlag(): Boolean = true

  @RegisterFunction("dispatch_probe_count") fun dispatchProbeCount(): Long = PROBE_COUNT

  /**
   * Task-80 dispatch probe (driver method #16). Bits, all of which a healthy run sets (mask 127):
   *
   * 1 = a single-FLOAT registered function received its argument (task 79's exact hole),
   * 2 = a `(BOOL)` registered function received its argument,
   * 4 = a `(VECTOR3, VECTOR3)` registered function received BOTH vectors intact,
   * 8 = a `() -> VECTOR3` return survived the packed transport AND the proxy's parse,
   * 16 = the `() -> FLOAT`, `() -> STRING`, `() -> BOOL` and `() -> INT` returns all did too,
   * 32 = a one-`int` signal payload reached a Kotlin lambda instead of being discarded,
   * 64 = the slice-3 MIXED shapes: `(STRING, OBJECT)` carried a hostile label and a live object
   * handle, and `(INT, OBJECT?)` carried a number plus a null object.
   *
   * Everything except the signal bit rides `callv` through the generic tier, so the values make a
   * full Kotlin -> GDScript proxy -> Kotlin round trip and the proxy's own parse is under test.
   */
  @RegisterFunction("dispatch_probe")
  fun dispatchProbe(value: Long): Long {
    val generic = WebExperimentalGenericCall
    var mask = 0L

    // The two argument shapes exactly as the corpus reaches them: the FPS's typed
    // call(method, Double) and third-person's typed call(method, Vector3, Vector3). Both are
    // QUEUED mutations, so the first callImmediate below (which flushes) is what applies them.
    self.call("dispatch_probe_float", PROBE_NUMBER)
    self.call("dispatch_probe_impact", PROBE_IMPACT, PROBE_FORCE)
    // A single BOOL has no typed call family; the generic tier carries it, and callv dispatches
    // to the same proxy function the engine would.
    generic.callImmediate(self, "dispatch_probe_bool", listOf(true))
    if (probeFloatArgument == PROBE_NUMBER) mask = mask or 1L
    if (probeBoolArgument) mask = mask or 2L
    if (probeImpactSum == PROBE_IMPACT + PROBE_FORCE) mask = mask or 4L

    // The generic tier tags a Vector3 return "v3" with its three components as the payload.
    val vector = generic.callImmediate(self, "dispatch_probe_vector")
    if (
      vector.tag == "v3" &&
        vector.payload.size == 3 &&
        vector.payload[0].toDouble() == PROBE_VECTOR.x &&
        vector.payload[1].toDouble() == PROBE_VECTOR.y &&
        vector.payload[2].toDouble() == PROBE_VECTOR.z
    ) {
      mask = mask or 8L
    }

    val number = generic.callImmediate(self, "dispatch_probe_number")
    val text = generic.callImmediate(self, "dispatch_probe_text")
    val flag = generic.callImmediate(self, "dispatch_probe_flag")
    val count = generic.callImmediate(self, "dispatch_probe_count")
    if (
      number.asDouble() == PROBE_NUMBER &&
        text.asString() == PROBE_TEXT &&
        flag.asBoolean() &&
        count.asLong() == PROBE_COUNT
    ) {
      mask = mask or 16L
    }

    if (probeSignalPayload == PROBE_COUNT) mask = mask or 32L

    // Task 80 slice 3: the two MIXED-channel shapes. The generic tier is the only caller that can
    // pass a string/int AND an object handle, which is exactly the shape the packed argument list
    // exists for; `null` proves the nullable-object lane delivers null rather than a 0 wrapper.
    generic.callImmediate(self, "dispatch_probe_tag", listOf(PROBE_HOSTILE_TEXT, self))
    generic.callImmediate(self, "dispatch_probe_join", listOf(PROBE_COUNT, null))
    if (
      probeTagArgument == PROBE_HOSTILE_TEXT &&
        probeTagNodeMatched &&
        probeJoinId == PROBE_COUNT &&
        probeJoinNodeWasNull
    ) {
      mask = mask or 64L
    }
    return mask
  }

  // A registered function's method id is its DECLARATION ORDER, and the browser drivers call
  // several of these by id (dispatch_probe is method#16). Append new ones BELOW this line --
  // inserting one above shifts every id after it and the driver silently calls the wrong method.

  /**
   * The match3 `Tile.set_tile_type(String, Texture2D)` shape (task 80 slice 3): text and an object
   * handle in one packed argument list. The label is deliberately HOSTILE — it carries the packed
   * transport's own separator and percent-escape look-alikes — so the escaping is under test, not
   * just the arity.
   */
  @RegisterFunction("dispatch_probe_tag")
  fun dispatchProbeTag(label: String, node: Node3D) {
    probeTagArgument = label
    probeTagNodeMatched = node.handle == self.handle
  }

  /**
   * The tps `Level.add_player(id, spawnPoint: Marker3D?)` shape: a whole number plus a NULLABLE
   * object, so the 0 handle must arrive as Kotlin `null` rather than as a wrapper around nothing.
   */
  @RegisterFunction("dispatch_probe_join")
  fun dispatchProbeJoin(id: Long, spawnPoint: Node3D?) {
    probeJoinId = id
    probeJoinNodeWasNull = spawnPoint == null
  }

  /**
   * Task-82 coroutine conformance probe (driver method #19), armed by the browser driver.
   *
   * The fifteen lines that would have caught task 82 on day one, in every demo: launch a coroutine
   * on this script's own scope, await the two delay shapes gameplay actually uses — the
   * wait-one-frame safe point `delaySeconds(0.0)` (desktop's `MainThread.awaitNextFrame`, which
   * the kill plane and every "restructure after the physics callback unwinds" site depends on) and
   * a real timed delay — and record that each one RESUMED.
   *
   * Nothing here can fail loudly on its own: a scheduler that is never pumped leaves the whole
   * body unrun and throws nothing at all. That is exactly why the assertion has to be a positive
   * observation of resumption, read back through [coroutineProbeMask], and never "no error".
   */
  @RegisterFunction("coroutine_probe")
  fun coroutineProbe() {
    coroutineMask = 1L
    kanamaScope.launch {
      coroutineMask = coroutineMask or 2L
      SceneTree.delaySeconds(0.0)
      coroutineMask = coroutineMask or 4L
      self.getTree().delaySeconds(COROUTINE_PROBE_DELAY_SECONDS)
      coroutineMask = coroutineMask or 8L
      MainThread.post { coroutineMask = coroutineMask or 16L }
    }
  }

  /**
   * Task-82 coroutine conformance readback (driver method #20): bit 1 = [coroutineProbe] ran,
   * 2 = the coroutine body started, 4 = it resumed past `delaySeconds(0.0)`, 8 = it resumed past a
   * timed `delaySeconds`, 16 = a `MainThread.post` from inside the coroutine ran. A pumped
   * scheduler reaches 31; an unpumped one stalls at 3 forever.
   */
  @RegisterFunction("coroutine_probe_mask")
  fun coroutineProbeMask(value: Long): Long = coroutineMask

  /**
   * Connects and fires the scalar-payload signal once. Called from [ready] so the payload has
   * landed long before the driver reads [dispatchProbe]; the connection is one-shot, so it
   * unregisters itself and the smoke's callback-drain assertion is unaffected.
   */
  private fun armSignalPayloadProbe() {
    self
      .signal("dispatch_probe_scalar")
      .connectLong(self, GodotObject.CONNECT_ONE_SHOT) { payload -> probeSignalPayload = payload }
    self.emitSignal("dispatch_probe_scalar", PROBE_COUNT)
  }

  private companion object {
    const val ENTER_TREE_EXPORTED = "web3d-enter-tree"
    const val PROBE_NUMBER = 12.25
    const val PROBE_TEXT = "kanama-packed-return"
    /**
     * Real unit separators, percent-escape look-alikes, a quote and a backslash: the packed
     * argument list must survive every byte that could split or mis-decode it.
     */
    const val PROBE_HOSTILE_TEXT = "a\u001Fb:c%1F\u001Fd\\e\"f%25"
    const val PROBE_COUNT = 4242L
    /** Long enough that a resume proves the timed lane, short enough for a driver poll window. */
    const val COROUTINE_PROBE_DELAY_SECONDS = 0.25
    val PROBE_VECTOR = Vector3(1.5, -2.5, 3.5)
    val PROBE_IMPACT = Vector3(0.25, 0.5, 0.75)
    val PROBE_FORCE = Vector3(-1.25, 2.5, -3.75)
  }
}
