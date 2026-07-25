package web3d

import net.multigesture.kanama.annotations.OnProcess
import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.RegisterFunction
import net.multigesture.kanama.annotations.ScriptClass
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
}
