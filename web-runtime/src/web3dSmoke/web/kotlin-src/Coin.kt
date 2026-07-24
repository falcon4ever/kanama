package web3d

import net.multigesture.kanama.annotations.OnProcess
import net.multigesture.kanama.annotations.RegisterFunction
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.Area3D
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node3D
import net.multigesture.kanama.types.Vector3

/**
 * A collectible coin (Area3D): spins in place, and frees itself when the physics character overlaps
 * it — the platformer's core pickup loop, driven by a scene body_entered signal through the Web
 * backend's object-arg registered-function dispatch.
 */
@ScriptClass(attachTo = "Area3D")
class Coin(godotObject: GodotHandle) : KanamaScript<Area3D>(godotObject, ::Area3D) {
  private var grabbed = false
  private var angle = 0.0

  @OnProcess
  fun process(delta: Double) {
    angle += delta * 3.0
    self.rotation = Vector3(0.0, angle, 0.0)
  }

  @RegisterFunction("_on_body_entered")
  fun onBodyEntered(body: Node3D) {
    if (grabbed) return
    grabbed = true
    self.queueFree()
  }
}
