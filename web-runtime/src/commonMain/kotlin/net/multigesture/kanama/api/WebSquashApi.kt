@file:OptIn(InternalKanamaBackendApi::class)

package net.multigesture.kanama.api

import net.multigesture.kanama.backend.GodotHandle as BackendGodotHandle
import net.multigesture.kanama.backend.InputEventBackendContractProbe
import net.multigesture.kanama.backend.InternalKanamaBackendApi
import net.multigesture.kanama.backend.KinematicCollision3DBackendContractProbe
import net.multigesture.kanama.backend.PathFollow3DBackendContractProbe
import net.multigesture.kanama.types.Vector3
import net.multigesture.kanama.web.WebObjectId

/**
 * Web API surface specific to the squash-the-creeps 3D demo (Task 60d).
 *
 * Shared Godot classes live in [WebGodotApi]; the 3D classes the platformer introduced live in
 * [WebPlatformerApi]. The slide-collision query surface (the demo's squash-bounce check) and the
 * mob spawn-path classes live here and are scanned by the fail-loud Web gameplay coverage gate.
 */
class PathFollow3D(godotObject: GodotHandle) : Node3D(godotObject) {
  var progressRatio: Double
    get() = unsupportedWebGameplayFamily("PathFollow3D.get_progress_ratio")
    set(value) {
      PathFollow3DBackendContractProbe(backendHandle).setProgressRatio(value)
    }
}

/**
 * One collision record from CharacterBody3D.get_slide_collision.
 *
 * The record holds a browser handle: call [close] when done with it (the squash bounce check
 * reads it within one physics tick), or the handle stays live until scene teardown.
 */
class KinematicCollision3D internal constructor(private var collisionHandle: BackendGodotHandle?) :
  GodotObject(checkNotNull(collisionHandle)) {
  /** The colliding object as an existing tracked handle (a scripted Mob, the Ground body). */
  fun getCollider(): GodotObject? =
    KinematicCollision3DBackendContractProbe(requireOpenHandle()).getCollider()?.let { handle ->
      GodotObject(WebObjectId(handle.backendToken().toInt()))
    }

  /** Collision normal at the deepest collision (index baked to Godot's default 0). */
  fun getNormal(): Vector3 =
    KinematicCollision3DBackendContractProbe(requireOpenHandle()).getNormal().let {
      Vector3(it.x, it.y, it.z)
    }

  fun close() {
    val handle = collisionHandle ?: return
    releaseWebCollision(handle.backendToken().toInt())
    collisionHandle = null
  }

  private fun requireOpenHandle(): BackendGodotHandle =
    checkNotNull(collisionHandle) { "KinematicCollision3D is closed" }
}

/** Generic input event wrapper: squash retries on ui_accept from _unhandled_input. */
class InputEvent(godotObject: GodotHandle) : GodotObject(godotObject) {
  /** allow_echo/exact_match are baked to Godot defaults (false), the only values demos pass. */
  fun isActionPressed(action: String): Boolean =
    InputEventBackendContractProbe(backendHandle).isActionPressed(action)
}

internal expect fun releaseWebCollision(collisionHandle: Int)
