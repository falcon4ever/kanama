@file:OptIn(InternalKanamaBackendApi::class)

package net.multigesture.kanama.api

import net.multigesture.kanama.backend.GodotVector3
import net.multigesture.kanama.backend.InternalKanamaBackendApi
import net.multigesture.kanama.backend.RayCast3DBackendContractProbe
import net.multigesture.kanama.types.Vector3
import net.multigesture.kanama.web.WebObjectId

/**
 * Web API surface for the FPS carrier demo (Task 60e).
 *
 * Shared Godot classes live in [WebGodotApi]/[WebPlatformerApi]/[WebSquashApi]; the ray-query
 * surface the FPS drives lives here and is scanned by the fail-loud Web gameplay coverage gate.
 */
class RayCast3D(godotObject: GodotHandle) : Node3D(godotObject) {
  /**
   * The ray's local target vector, mirrored read-your-write (seeded when the node is looked
   * up, refreshed on write) — the FPS re-aims it per shot for spread.
   */
  var targetPosition: Vector3
    get() =
      RayCast3DBackendContractProbe(backendHandle).targetPosition.let { Vector3(it.x, it.y, it.z) }
    set(value) {
      RayCast3DBackendContractProbe(backendHandle).targetPosition =
        GodotVector3(value.x.toFloat(), value.y.toFloat(), value.z.toFloat())
    }

  /** Flushes queued mutations, then updates the ray synchronously (fire between ticks). */
  fun forceRaycastUpdate() {
    RayCast3DBackendContractProbe(backendHandle).forceRaycastUpdate()
  }

  fun isColliding(): Boolean = RayCast3DBackendContractProbe(backendHandle).isColliding()

  /** The hit object as an existing tracked handle (a scripted target resolves to its script). */
  fun getCollider(): GodotObject? =
    RayCast3DBackendContractProbe(backendHandle).getCollider()?.let { handle ->
      GodotObject(WebObjectId(handle.backendToken().toInt()))
    }

  /** Global coordinates of the hit point. */
  fun getCollisionPoint(): Vector3 =
    RayCast3DBackendContractProbe(backendHandle).getCollisionPoint().let {
      Vector3(it.x, it.y, it.z)
    }

  fun getCollisionNormal(): Vector3 =
    RayCast3DBackendContractProbe(backendHandle).getCollisionNormal().let {
      Vector3(it.x, it.y, it.z)
    }
}
