package web3d

import net.multigesture.kanama.annotations.OnPhysicsProcess
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.CharacterBody3D
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.types.Vector3

/**
 * Minimal 3D physics character (Task 60d foundation): from `@OnPhysicsProcess` it accumulates
 * gravity, auto-paces left/right, and calls `move_and_slide` so Godot's physics resolves the fall
 * onto the StaticBody3D floor and the floor contact — all through the Web backend. Read-your-write
 * `position` (physics-driven, refreshed each frame) drives the pacing bounds and the respawn.
 */
@ScriptClass(attachTo = "CharacterBody3D")
class Player(godotObject: GodotHandle) :
  KanamaScript<CharacterBody3D>(godotObject, ::CharacterBody3D) {
  private var gravity = 0.0
  private var direction = 1.0

  @OnPhysicsProcess
  fun physicsProcess(delta: Double) {
    gravity += 22.0 * delta

    // Platformer bounce: hop on every floor contact so the character arcs across the level.
    if (self.isOnFloor() && gravity > 0.0) gravity = -8.0

    val x = self.position.x
    if (x > 3.5) direction = -1.0
    if (x < -3.5) direction = 1.0

    self.velocity = Vector3(direction * 4.0, -gravity, 0.0)
    self.moveAndSlide()

    if (self.position.y < -6.0) self.position = Vector3(-3.0, 4.0, 0.0)
  }
}
