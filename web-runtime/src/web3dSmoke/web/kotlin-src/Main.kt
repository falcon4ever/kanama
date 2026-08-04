package web3d

import kotlin.math.PI
import kotlin.math.abs
import net.multigesture.kanama.annotations.OnProcess
import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.RegisterFunction
import net.multigesture.kanama.annotations.ScriptClass
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

/**
 * Kanama Web 3D rendering-foundation smoke (Task 60c).
 *
 * Drives the same families the Starter-Kit-3D-Platformer's main.gd does (mobile-control
 * visibility, Compatibility-renderer light/environment tuning) and additionally spins a Node3D
 * each frame to exercise the read-your-write transform path — all through the Web backend.
 */
@ScriptClass(attachTo = "Node3D")
class Main(godotObject: GodotHandle) : KanamaScript<Node3D>(godotObject, ::Node3D) {
  private lateinit var spinner: Node3D
  private var angle = 0.0

  @OnReady
  fun ready() {
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
}
