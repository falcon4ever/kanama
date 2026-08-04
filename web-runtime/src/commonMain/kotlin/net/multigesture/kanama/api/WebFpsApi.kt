@file:OptIn(InternalKanamaBackendApi::class)

package net.multigesture.kanama.api

import net.multigesture.kanama.backend.AnimatedSprite3DBackendContractProbe
import net.multigesture.kanama.backend.GodotBackendCalls
import net.multigesture.kanama.backend.GodotObjectBackendContractProbe
import net.multigesture.kanama.backend.GodotVector3
import net.multigesture.kanama.backend.InputEventMouseMotionBackendContractProbe
import net.multigesture.kanama.backend.InternalKanamaBackendApi
import net.multigesture.kanama.backend.NodeBackendContractProbe
import net.multigesture.kanama.backend.RayCast3DBackendContractProbe
import net.multigesture.kanama.backend.TextureRectBackendContractProbe
import net.multigesture.kanama.backend.VisualInstance3DBackendContractProbe
import net.multigesture.kanama.types.Basis
import net.multigesture.kanama.types.Vector2
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

/** 3D billboard sprite (FPS muzzle flashes and impact decals). */
class AnimatedSprite3D(godotObject: GodotHandle) : Node3D(godotObject) {
  /** Play a named animation (custom_speed/from_end baked to Godot defaults). */
  fun play(animation: String) {
    AnimatedSprite3DBackendContractProbe(backendHandle).play(animation)
  }

  /** Write-only on Web: the FPS rewinds muzzle flashes to frame 0 before replaying. */
  var frame: Long
    get() = unsupportedWebGameplayFamily("AnimatedSprite3D.get_frame")
    set(value) {
      AnimatedSprite3DBackendContractProbe(backendHandle).setFrame(value)
    }
}

/** Mesh leaf node: the FPS moves weapon models onto the overlay camera's render layer. */
class MeshInstance3D(godotObject: GodotHandle) : GeometryInstance3D(godotObject) {
  fun setLayerMask(mask: Long) {
    VisualInstance3DBackendContractProbe(backendHandle).setLayerMask(mask)
  }

  /** The instance's Mesh resource as a tracked handle. */
  fun getMesh(): Mesh? =
    GodotBackendCalls.invokeNoArgsRetHandle(
        net.multigesture.kanama.backend.InitialGodotCallDescriptors.MESHINSTANCE3D_GET_MESH,
        backendHandle,
      )
      ?.let { Mesh(WebObjectId(it.backendToken().toInt())) }

  /** Web supports only clearing an override (material baked null in the family). */
  fun setSurfaceOverrideMaterial(surface: Long, material: Nothing?) {
    NodeBackendContractProbe(backendHandle) // keep receiver-liveness semantics uniform
    GodotBackendCalls.invokeLongArg(
      net.multigesture.kanama.backend.InitialGodotCallDescriptors
        .MESHINSTANCE3D_CLEAR_SURFACE_OVERRIDE_MATERIAL,
      backendHandle,
      surface,
    )
  }
}

/** Button base tier for the demo menu; pressed rides the shared connect flow. */
open class BaseButton(godotObject: GodotHandle) : Control(godotObject) {
  object Signals {
    const val pressed: String = "pressed"
  }
}

class TextureButton(godotObject: GodotHandle) : BaseButton(godotObject)

/** Crosshair rect (FPS HUD): texture is write-only on Web. */
class TextureRect(godotObject: GodotHandle) : Control(godotObject) {
  var texture: Texture2D?
    get() = unsupportedWebGameplayFamily("TextureRect.get_texture")
    set(value) {
      TextureRectBackendContractProbe(backendHandle).setTexture(value?.requireOpenHandle())
    }
}

/** Mouse-motion event wrapper: the FPS reads the relative delta for mouse look. */
class InputEventMouseMotion private constructor(godotObject: GodotHandle) :
  GodotObject(godotObject) {
  fun getRelative(): Vector2 =
    InputEventMouseMotionBackendContractProbe(backendHandle).getRelative().let {
      Vector2(it.x.toDouble(), it.y.toDouble())
    }

  companion object {
    fun from(event: GodotObject): InputEventMouseMotion? =
      event
        .takeIf {
          GodotObjectBackendContractProbe(it.backendHandle).isClass("InputEventMouseMotion")
        }
        ?.let { InputEventMouseMotion(it.handle) }
  }
}

/** The FPS-era pure-Kotlin Basis now lives with the value types; same YXZ math. */



/** Import-compat alias for shared demo sources; delegates to the [Node] member (task 64). */
fun Node.getChildren(): List<GodotObject> = getChildren(includeInternal = false)

/**
 * Import-compat alias for shared demo sources; delegates to the engine-backed [Node] member
 * (task 64), which supersedes the first-port manual class-filtered walk.
 */
fun Node.findChildren(pattern: String, type: String): List<GodotObject> =
  findChildren(pattern, type, recursive = true, owned = true)

/** Import-compat alias for shared demo sources; delegates to the [GodotObject] member (task 64). */
@Suppress("EXTENSION_SHADOWED_BY_MEMBER")
fun Node.hasMethod(method: String): Boolean = hasMethod(method)

/** Import-compat alias for shared demo sources; delegates to the [GodotObject] member (task 64). */
@Suppress("EXTENSION_SHADOWED_BY_MEMBER")
fun Node.call(method: String, value: Double) = call(method, value)
