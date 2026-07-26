package web3d

import kotlin.math.abs
import net.multigesture.kanama.annotations.OnPhysicsProcess
import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.CharacterBody3D
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.RayCast3D
import net.multigesture.kanama.types.Vector3

/**
 * Minimal 3D physics character (Task 60d foundation): from `@OnPhysicsProcess` it accumulates
 * gravity, auto-paces left/right, and calls `move_and_slide` so Godot's physics resolves the fall
 * onto the StaticBody3D floor and the floor contact — all through the Web backend. Read-your-write
 * `position` (physics-driven, refreshed each frame) drives the pacing bounds and the respawn.
 *
 * The DownRay child (Task 60e) probes the RayCast3D query surface on every floor contact: re-aim
 * (read-your-write target mirror), force a synchronous update, then require a floor hit with an
 * up-facing normal and a plausible global hit point. Any inconsistency fails the callback loudly,
 * which the smoke's noCallbackFaults check surfaces.
 */
@ScriptClass(attachTo = "CharacterBody3D")
class Player(godotObject: GodotHandle) :
  KanamaScript<CharacterBody3D>(godotObject, ::CharacterBody3D) {
  private var gravity = 0.0
  private var direction = 1.0
  private lateinit var downRay: RayCast3D

  @OnReady
  fun ready() {
    downRay = self.requireAs("DownRay", ::RayCast3D)
    val seeded = downRay.targetPosition
    check(seeded == Vector3(0.0, -8.0, 0.0)) { "DownRay target_position seed mismatch: $seeded" }
  }

  @OnPhysicsProcess
  fun physicsProcess(delta: Double) {
    gravity += 22.0 * delta

    // Platformer bounce: hop on every floor contact so the character arcs across the level.
    if (self.isOnFloor() && gravity > 0.0) {
      probeRayQueries()
      gravity = -8.0
    }

    val x = self.position.x
    if (x > 3.5) direction = -1.0
    if (x < -3.5) direction = 1.0

    self.velocity = Vector3(direction * 4.0, -gravity, 0.0)
    self.moveAndSlide()

    if (self.position.y < -6.0) self.position = Vector3(-3.0, 4.0, 0.0)
  }

  private fun probeRayQueries() {
    // Read-your-write target mirror: re-aim with a jitter and read it straight back.
    val aimed = Vector3(0.25 * direction, -8.0, 0.0)
    downRay.targetPosition = aimed
    check(downRay.targetPosition == aimed) { "RayCast3D target mirror lost the write" }

    downRay.forceRaycastUpdate()
    check(downRay.isColliding()) { "DownRay should hit the floor from a grounded player" }
    checkNotNull(downRay.getCollider()) { "DownRay hit without a collider handle" }

    val normal = downRay.getCollisionNormal()
    check(normal.y > 0.9) { "Floor hit normal should face up, got $normal" }

    // Floor collision top sits at y = -0.75 (body at -1, box half-height 0.25); allow slack.
    val point = downRay.getCollisionPoint()
    check(abs(point.y + 0.75) < 0.5) { "Floor hit point off-plane: $point" }
  }
}
