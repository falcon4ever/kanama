package web3d

import kotlin.math.PI
import kotlin.math.abs
import net.multigesture.kanama.annotations.OnEnterTree
import net.multigesture.kanama.annotations.OnProcess
import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.RegisterFunction
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.annotations.ScriptProperty
import net.multigesture.kanama.api.AudioStreamPlayer
import net.multigesture.kanama.api.CanvasLayer
import net.multigesture.kanama.api.DirectionalLight3D
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.Input
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.ManualGodotLifetimeApi
import net.multigesture.kanama.api.Node3D
import net.multigesture.kanama.api.OS
import net.multigesture.kanama.api.RenderingServer
import net.multigesture.kanama.api.Resource
import net.multigesture.kanama.api.ResourceLoader
import net.multigesture.kanama.api.WorldEnvironment
import net.multigesture.kanama.api.lookAt
import net.multigesture.kanama.types.Vector3
import net.multigesture.kanama.web.WebExperimentalGenericCall

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
 */
@ScriptClass(attachTo = "Node3D")
class Main(godotObject: GodotHandle) : KanamaScript<Node3D>(godotObject, ::Node3D) {
  /** Overridden in main.tscn to [ENTER_TREE_EXPORTED] — never the default — for the 66b proof. */
  @ScriptProperty var enterTreeGreeting: String = "unset"

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
   * All probed methods (`set_meta`/`get_meta`/`add_to_group`/`get_node`/`get_window`) are
   * OUTSIDE the admitted typed opcodes; `is_in_group`/`get_parent` have typed twins,
   * which makes them the cost comparator and the tracked-object-return probe.
   */
  @RegisterFunction("generic_probe")
  fun genericProbe() {
    val generic = WebExperimentalGenericCall

    // (a) Queued generic mutations on methods with no typed opcode.
    generic.queueVoidCall(spinner, "set_meta", listOf("kanama_generic_probe", 42))
    generic.queueVoidCall(spinner, "add_to_group", listOf("kanama_generic_smoke"))

    // (b) Immediate generic read-back proving (a) applied through the queued channel
    // (callImmediate flushes the command buffer first, like every typed immediate family).
    val meta = generic.callImmediate(spinner, "get_meta", listOf("kanama_generic_probe"))
    val inGroup = generic.callImmediate(spinner, "is_in_group", listOf("kanama_generic_smoke"))

    // (c) Object returns: a script-backed node (this Main), an already-tracked engine node
    // (the spinner), and an untracked engine object (the root Window).
    val parent = generic.callImmediate(spinner, "get_parent")
    val child = generic.callImmediate(self, "get_node", listOf("Spinner"))
    val window = generic.callImmediate(self, "get_window")

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

    // Report defensively (bad tags become -1) so a policy regression fails the driver's
    // assertions with evidence instead of faulting the callback.
    fun handleOf(result: net.multigesture.kanama.web.WebGenericCallResult): Int =
      if (result.tag == "o" || result.tag == "object-untracked") result.asObjectHandle() else -1
    generic.publishProbeReport(
      "{" +
        "\"metaTag\":\"${meta.tag}\",\"metaValue\":${if (meta.tag == "i") meta.asLong() else -1}," +
        "\"groupTag\":\"${inGroup.tag}\"," +
        "\"groupValue\":${inGroup.tag == "b" && inGroup.asBoolean()}," +
        "\"parentTag\":\"${parent.tag}\",\"parentHandle\":${handleOf(parent)}," +
        "\"mainHandle\":${self.handle.value}," +
        "\"childTag\":\"${child.tag}\",\"childHandle\":${handleOf(child)}," +
        "\"spinnerHandle\":${spinner.handle.value}," +
        "\"windowTag\":\"${window.tag}\",\"windowHandle\":${handleOf(window)}," +
        "\"iterations\":$iterations," +
        "\"genericHits\":$genericHits,\"typedHits\":$typedHits," +
        "\"genericMs\":$genericMs,\"typedMs\":$typedMs" +
        "}"
    )
  }

  private companion object {
    const val ENTER_TREE_EXPORTED = "web3d-enter-tree"
  }
}
