package web3d

import net.multigesture.kanama.annotations.OnEnterTree
import net.multigesture.kanama.annotations.OnProcess
import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.RegisterFunction
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.annotations.ScriptProperty
import net.multigesture.kanama.api.CanvasLayer
import net.multigesture.kanama.api.DirectionalLight3D
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.Input
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node3D
import net.multigesture.kanama.api.OS
import net.multigesture.kanama.api.RenderingServer
import net.multigesture.kanama.api.WorldEnvironment
import net.multigesture.kanama.types.Vector3

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
   * Harness probe (method#3): bit 1 = `@OnEnterTree` dispatched, bit 2 = the exported (non-default)
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

  private companion object {
    const val ENTER_TREE_EXPORTED = "web3d-enter-tree"
  }
}
