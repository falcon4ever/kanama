@file:OptIn(InternalKanamaBackendApi::class)

package net.multigesture.kanama.api

import net.multigesture.kanama.backend.AnimatedSprite3DBackendContractProbe
import net.multigesture.kanama.backend.GodotObjectBackendContractProbe
import net.multigesture.kanama.backend.GodotVector3
import net.multigesture.kanama.backend.InputEventMouseMotionBackendContractProbe
import net.multigesture.kanama.backend.InternalKanamaBackendApi
import net.multigesture.kanama.backend.NodeBackendContractProbe
import net.multigesture.kanama.backend.RayCast3DBackendContractProbe
import net.multigesture.kanama.backend.TextureRectBackendContractProbe
import net.multigesture.kanama.backend.VisualInstance3DBackendContractProbe
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
}

/** Crosshair rect (FPS HUD): texture is write-only on Web. */
class TextureRect(godotObject: GodotHandle) : CanvasItem(godotObject.toBackendHandle()) {
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

/** Godot-order (YXZ) rotation basis derived from Euler angles — enough for the FPS's
 * camera-relative movement on scale-1 bodies; not a general Transform3D. */
class Basis private constructor(private val rows: Array<DoubleArray>) {
  operator fun times(v: Vector3): Vector3 =
    Vector3(
      rows[0][0] * v.x + rows[0][1] * v.y + rows[0][2] * v.z,
      rows[1][0] * v.x + rows[1][1] * v.y + rows[1][2] * v.z,
      rows[2][0] * v.x + rows[2][1] * v.y + rows[2][2] * v.z,
    )

  /** Rotation-only basis: the inverse is the transpose. */
  fun inverse(): Basis =
    Basis(
      arrayOf(
        doubleArrayOf(rows[0][0], rows[1][0], rows[2][0]),
        doubleArrayOf(rows[0][1], rows[1][1], rows[2][1]),
        doubleArrayOf(rows[0][2], rows[1][2], rows[2][2]),
      )
    )

  companion object {
    /** Godot's default Euler order YXZ: R = Ry * Rx * Rz. */
    fun fromEuler(euler: Vector3): Basis {
      val cx = kotlin.math.cos(euler.x)
      val sx = kotlin.math.sin(euler.x)
      val cy = kotlin.math.cos(euler.y)
      val sy = kotlin.math.sin(euler.y)
      val cz = kotlin.math.cos(euler.z)
      val sz = kotlin.math.sin(euler.z)
      return Basis(
        arrayOf(
          doubleArrayOf(cy * cz + sy * sx * sz, -cy * sz + sy * sx * cz, sy * cx),
          doubleArrayOf(cx * sz, cx * cz, -sx),
          doubleArrayOf(-sy * cz + cy * sx * sz, sy * sz + cy * sx * cz, cy * cx),
        )
      )
    }
  }
}

/** The node's rotation basis derived from the mirrored rotation snapshot (scale-1 bodies). */
val Node3D.basis: Basis
  get() = Basis.fromEuler(rotation)

/** get_children/find_children walks backed by get_child_count + get_child. */
fun Node.getChildren(): List<GodotObject> {
  val probe = NodeBackendContractProbe(backendHandle)
  return (0 until probe.getChildCount()).mapNotNull { index ->
    probe.getChild(index)?.let { GodotObject(WebObjectId(it.backendToken().toInt())) }
  }
}

/**
 * Recursive class-filtered child walk (pattern is baked to "*", the only value the FPS
 * passes): returns every descendant whose Godot class matches [type].
 */
fun Node.findChildren(pattern: String, type: String): List<GodotObject> {
  require(pattern == "*") { "Web find_children supports only the \"*\" pattern" }
  val found = mutableListOf<GodotObject>()
  fun walk(node: GodotObject) {
    Node(node.handle).getChildren().forEach { child ->
      if (GodotObjectBackendContractProbe(child.backendHandle).isClass(type)) found.add(child)
      walk(child)
    }
  }
  walk(GodotObject(handle))
  return found
}

fun Node.hasMethod(method: String): Boolean =
  NodeBackendContractProbe(backendHandle).hasMethod(method)

/** Dynamic one-Double-argument method call (FPS damage(amount) on ray hits). */
fun Node.call(method: String, value: Double) {
  NodeBackendContractProbe(backendHandle).callDouble(method, value)
}
