@file:OptIn(InternalKanamaBackendApi::class)

package net.multigesture.kanama.api

import net.multigesture.kanama.backend.CharacterBody3DBackendContractProbe
import net.multigesture.kanama.backend.DirectionalLight3DBackendContractProbe
import net.multigesture.kanama.backend.EnvironmentBackendContractProbe
import net.multigesture.kanama.backend.GodotBackendCalls
import net.multigesture.kanama.backend.GodotHandle as BackendGodotHandle
import net.multigesture.kanama.backend.GodotVector2
import net.multigesture.kanama.backend.GodotVector3
import net.multigesture.kanama.backend.InitialGodotCallDescriptors as D
import net.multigesture.kanama.backend.InternalKanamaBackendApi
import net.multigesture.kanama.backend.Light3DBackendContractProbe
import net.multigesture.kanama.backend.Node3DBackendContractProbe
import net.multigesture.kanama.backend.OSBackendContractProbe
import net.multigesture.kanama.backend.RenderingServerBackendContractProbe
import net.multigesture.kanama.backend.WorldEnvironmentBackendContractProbe
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector3
import net.multigesture.kanama.web.WebObjectId

private fun BackendGodotHandle.bool3(descriptor: net.multigesture.kanama.backend.GodotCallDescriptor, value: Boolean) =
  GodotBackendCalls.invokeBoolArg(descriptor, this, value)

private fun composeBasis(rotation: Vector3, scale: Vector3): Basis {
  val rotationBasis = Basis.fromEuler(rotation)
  // Node basis = R * S: columns scaled (Godot composes scale on the right of rotation).
  return Basis(
    rotationBasis.getColumn(0) * scale.x,
    rotationBasis.getColumn(1) * scale.y,
    rotationBasis.getColumn(2) * scale.z,
  )
}

/**
 * Web API surface for the 3D rendering foundation (Task 60c).
 *
 * Shared Godot classes live in [WebGodotApi]; the 3D scene-graph, lighting, and environment classes
 * the 3D platformer drives live here and are scanned by the fail-loud Web gameplay coverage gate.
 * Transform reads round-trip through the read-your-write snapshot; write-only render-tuning
 * properties (whose getters the demo never calls) surface as nonblocking unsupported families.
 */
open class Node3D(godotObject: GodotHandle) : Node(godotObject.toBackendHandle()) {
  var position: Vector3
    get() =
      Node3DBackendContractProbe(backendHandle).position.let { Vector3(it.x, it.y, it.z) }
    set(value) {
      Node3DBackendContractProbe(backendHandle).position =
        GodotVector3(value.x.toFloat(), value.y.toFloat(), value.z.toFloat())
    }

  var rotation: Vector3
    get() =
      Node3DBackendContractProbe(backendHandle).rotation.let { Vector3(it.x, it.y, it.z) }
    set(value) {
      Node3DBackendContractProbe(backendHandle).rotation =
        GodotVector3(value.x.toFloat(), value.y.toFloat(), value.z.toFloat())
    }

  var scale: Vector3
    get() = Node3DBackendContractProbe(backendHandle).scale.let { Vector3(it.x, it.y, it.z) }
    set(value) {
      Node3DBackendContractProbe(backendHandle).scale =
        GodotVector3(value.x.toFloat(), value.y.toFloat(), value.z.toFloat())
    }

  var rotationDegrees: Vector3
    get() =
      GodotBackendCalls.invokeNoArgsRetVector3(D.NODE3D_GET_ROTATION_DEGREES, backendHandle).let {
        Vector3(it.x, it.y, it.z)
      }
    set(value) {
      GodotBackendCalls.invokeVector3Arg(
        D.NODE3D_SET_ROTATION_DEGREES,
        backendHandle,
        GodotVector3(value.x.toFloat(), value.y.toFloat(), value.z.toFloat()),
      )
    }

  /** Write-only on Web (the demos only set visibility). */
  var visible: Boolean
    get() = unsupportedWebGameplayFamily("Node3D.get_visible")
    set(value) {
      backendHandle.bool3(D.NODE3D_SET_VISIBLE, value)
    }

  fun hide() {
    visible = false
  }

  /**
   * Position the node and orient the forward axis at [target]: -Z (camera forward) by default,
   * +Z (asset front) when [useModelFront] is true — the desktop/Godot signature (task 64). Web
   * bakes the up vector to Godot's default; the rotation snapshot is refreshed synchronously so
   * a following rotation read sees the new orientation.
   *
   * Transport note: the admitted opcode carries only position+target with the engine bool baked
   * to false, so [useModelFront] rides the same opcode by mirroring the target through
   * [position]. That is exact, not approximate: `Basis::looking_at(d, up, true)` and
   * `Basis::looking_at(-d, up, false)` normalize to the identical `v_z = d / |d|` and hence the
   * identical basis (core/math/basis.cpp), and the engine's error guards (target == position,
   * up collinearity) map one-to-one under the mirror.
   */
  fun lookAtFromPosition(
    position: Vector3,
    target: Vector3,
    up: Vector3 = Vector3.UP,
    useModelFront: Boolean = false,
  ) {
    require(up == Vector3.UP) {
      "Web look_at_from_position supports only the default up vector Vector3.UP"
    }
    val engineTarget = if (useModelFront) position - (target - position) else target
    Node3DBackendContractProbe(backendHandle)
      .lookAtFromPosition(
        GodotVector3(position.x.toFloat(), position.y.toFloat(), position.z.toFloat()),
        GodotVector3(engineTarget.x.toFloat(), engineTarget.y.toFloat(), engineTarget.z.toFloat()),
      )
  }

  /** Rotate around global Y; the rotation snapshot is refreshed synchronously. */
  fun rotateY(angle: Double) {
    Node3DBackendContractProbe(backendHandle).rotateY(angle)
  }

  /** The node's world-space position; reads and writes cross synchronously. */
  var globalPosition: Vector3
    get() =
      Node3DBackendContractProbe(backendHandle).getGlobalPosition().let {
        Vector3(it.x, it.y, it.z)
      }
    set(value) {
      Node3DBackendContractProbe(backendHandle)
        .setGlobalPosition(GodotVector3(value.x.toFloat(), value.y.toFloat(), value.z.toFloat()))
    }

  /** The node's world-space Euler rotation; reads and writes cross synchronously. */
  var globalRotation: Vector3
    get() =
      Node3DBackendContractProbe(backendHandle).getGlobalRotation().let {
        Vector3(it.x, it.y, it.z)
      }
    set(value) {
      Node3DBackendContractProbe(backendHandle)
        .setGlobalRotation(GodotVector3(value.x.toFloat(), value.y.toFloat(), value.z.toFloat()))
    }

  /** The node's local rotation+scale basis; writes decompose to the two snapshot families. */
  var basis: Basis
    get() = composeBasis(rotation, scale)
    set(value) {
      rotation = value.getEuler()
      scale = value.getScale()
    }

  /** The node's local transform (basis + origin over the mirrored snapshots). */
  var transform: Transform3D
    get() = Transform3D(basis, position)
    set(value) {
      position = value.origin
      basis = value.basis
    }

  /** The node's world transform; rotation/origin cross synchronously, global scale assumed 1. */
  var globalTransform: Transform3D
    get() = Transform3D(Basis.fromEuler(globalRotation), globalPosition)
    set(value) {
      globalPosition = value.origin
      globalRotation = value.basis.getEuler()
    }

  /**
   * World-space rotation basis over the synchronous global-rotation reads (scale-1 rigs, the
   * same contract as [globalTransform]); writes decompose to the global-rotation family.
   */
  var globalBasis: Basis
    get() = Basis.fromEuler(globalRotation)
    set(value) {
      globalRotation = value.getEuler()
    }

  /**
   * Orient the forward axis at [target] from the current position — -Z by default, +Z when
   * [useModelFront] (the desktop signature; rides look_at_from_position, task 64).
   */
  fun lookAt(target: Vector3, up: Vector3 = Vector3.UP, useModelFront: Boolean = false) {
    lookAtFromPosition(globalPosition, target, up, useModelFront)
  }

  /** Node3D visibility read (synchronous immediate). */
  fun isVisible(): Boolean =
    GodotBackendCalls.invokeNoArgsRetBool(D.NODE3D_IS_VISIBLE, backendHandle)

  /** Godot's rotate_object_local: right-multiply the local basis by an axis-angle rotation. */
  fun rotateObjectLocal(axis: Vector3, angle: Double) {
    basis = basis * Basis.fromAxisAngle(axis, angle)
  }
}

class GPUParticles3D(godotObject: GodotHandle) : GeometryInstance3D(godotObject) {
  fun setEmitting(emitting: Boolean) {
    backendHandle.bool3(D.GPUPARTICLES3D_SET_EMITTING, emitting)
  }

  fun restart(keepSeed: Boolean = false) {
    backendHandle.bool3(D.GPUPARTICLES3D_RESTART, keepSeed)
  }
}

class CollisionShape3D(godotObject: GodotHandle) : Node3D(godotObject) {
  fun setDisabled(disabled: Boolean) {
    backendHandle.bool3(D.COLLISIONSHAPE3D_SET_DISABLED, disabled)
  }
}

class AnimationPlayer(godotObject: GodotHandle) : Node(godotObject.toBackendHandle()) {
  fun setSpeedScale(scale: Double) {
    GodotBackendCalls.invokeDoubleArg(D.ANIMATIONPLAYER_SET_SPEED_SCALE, backendHandle, scale)
  }

  /** Play a named animation (custom_speed/from_end baked to Godot defaults in the proxy). */
  fun play(animation: String, customBlend: Double = -1.0) {
    GodotBackendCalls.invokeStringNameDoubleArg(
      D.ANIMATIONPLAYER_PLAY,
      backendHandle,
      animation,
      customBlend,
    )
  }

  fun isPlaying(): Boolean =
    GodotBackendCalls.invokeNoArgsRetBool(D.ANIMATIONPLAYER_IS_PLAYING, backendHandle)

  /** keep_state baked to Godot's default false. */
  fun stop() {
    GodotBackendCalls.invokeNoArgsVoid(D.ANIMATIONPLAYER_STOP, backendHandle)
  }

  /** update baked true (the only value the corpus passes). */
  fun seek(seconds: Double, update: Boolean = true) {
    require(update) { "Web AnimationPlayer.seek bakes update=true" }
    GodotBackendCalls.invokeDoubleArg(D.ANIMATIONPLAYER_SEEK, backendHandle, seconds)
  }

  fun setDefaultBlendTime(seconds: Double) {
    GodotBackendCalls.invokeDoubleArg(
      D.ANIMATIONPLAYER_SET_DEFAULT_BLEND_TIME,
      backendHandle,
      seconds,
    )
  }

  /** AnimationPlayer is an AnimationMixer engine-side; rides the same family. */
  fun getAnimation(name: String): Animation? =
    GodotBackendCalls.invokeNodePathRetHandle(D.ANIMATIONMIXER_GET_ANIMATION, backendHandle, name)
      ?.let { Animation(WebObjectId(it.backendToken().toInt())) }

  /**
   * The name of the currently playing animation (GDScript's `current_animation` read; empty when
   * nothing plays). No typed opcode carries this string read, so it rides the task-76 generic
   * `callv` tier through the engine's `get_current_animation` property getter.
   */
  fun getCurrentAnimation(): String {
    genericWebGameplayFallback("AnimationPlayer.get_current_animation")
    return webGenericImmediateStringCall(this, "get_current_animation")
  }
}

open class Control(godotObject: GodotHandle) : CanvasItem(godotObject.toBackendHandle())

class Camera3D(godotObject: GodotHandle) : Node3D(godotObject) {
  /** World-space origin of the ray through the given screen point. */
  fun projectRayOrigin(screenPoint: Vector2): Vector3 =
    GodotBackendCalls.invokeVector2RetVector3(
        D.CAMERA3D_PROJECT_RAY_ORIGIN,
        backendHandle,
        GodotVector2(screenPoint.x.toFloat(), screenPoint.y.toFloat()),
      )
      .let { Vector3(it.x.toDouble(), it.y.toDouble(), it.z.toDouble()) }

  /** World-space direction of the ray through the given screen point. */
  fun projectRayNormal(screenPoint: Vector2): Vector3 =
    GodotBackendCalls.invokeVector2RetVector3(
        D.CAMERA3D_PROJECT_RAY_NORMAL,
        backendHandle,
        GodotVector2(screenPoint.x.toFloat(), screenPoint.y.toFloat()),
      )
      .let { Vector3(it.x.toDouble(), it.y.toDouble(), it.z.toDouble()) }
}

/** 3D physics body base (Task 60d). */
open class PhysicsBody3D(godotObject: GodotHandle) : Node3D(godotObject) {
  /**
   * First collision of a motion sweep; close it when done (KinematicCollision3D rule). Web
   * supports only Godot's defaults for the trailing arguments — the admitted family carries
   * just the motion — so non-default values fail loud instead of being silently ignored.
   */
  fun moveAndCollide(
    motion: Vector3,
    testOnly: Boolean = false,
    safeMargin: Double = 0.001,
    recoveryAsCollision: Boolean = false,
    maxCollisions: Int = 1,
  ): KinematicCollision3D? {
    require(!testOnly) { "Web move_and_collide supports only test_only=false" }
    require(safeMargin == 0.001) { "Web move_and_collide supports only the default safe_margin" }
    require(!recoveryAsCollision) { "Web move_and_collide supports only recovery_as_collision=false" }
    require(maxCollisions == 1) { "Web move_and_collide supports only max_collisions=1" }
    return GodotBackendCalls.invokeVector3RetHandle(
        D.PHYSICSBODY3D_MOVE_AND_COLLIDE,
        backendHandle,
        GodotVector3(motion.x.toFloat(), motion.y.toFloat(), motion.z.toFloat()),
      )
      ?.let { KinematicCollision3D(it) }
  }

  /** Adds a body to the list of bodies this body can't collide with. */
  fun addCollisionExceptionWith(body: Node) {
    GodotBackendCalls.invokeObjectArg(
      D.PHYSICSBODY3D_ADD_COLLISION_EXCEPTION_WITH,
      backendHandle,
      body.backendHandle,
    )
  }

  /** Locks or unlocks the given PhysicsServer3D.BodyAxis (see the BODY_AXIS_* constants). */
  fun setAxisLock(axis: Long, lock: Boolean) {
    GodotBackendCalls.invokeLongBoolArg(D.PHYSICSBODY3D_SET_AXIS_LOCK, backendHandle, axis, lock)
  }

  /**
   * Enables or disables the given 1-based layer in `collision_layer` — desktop declares this on
   * CollisionObject3D; web's CollisionObject3D tier is aliased to this class.
   */
  fun setCollisionLayerValue(layerNumber: Int, value: Boolean) {
    GodotBackendCalls.invokeLongBoolArg(
      D.COLLISIONOBJECT3D_SET_COLLISION_LAYER_VALUE,
      backendHandle,
      layerNumber.toLong(),
      value,
    )
  }

  companion object {
    /** PhysicsServer3D.BodyAxis constants (set_axis_lock), matching desktop's PhysicsBody3D. */
    const val BODY_AXIS_ANGULAR_X: Long = 8L
    const val BODY_AXIS_ANGULAR_Y: Long = 16L
    const val BODY_AXIS_ANGULAR_Z: Long = 32L
  }
}

open class StaticBody3D(godotObject: GodotHandle) : PhysicsBody3D(godotObject)

/** Kinematic character controller: set [velocity] then [moveAndSlide] from `@OnPhysicsProcess`. */
class CharacterBody3D(godotObject: GodotHandle) : PhysicsBody3D(godotObject) {
  var velocity: Vector3
    get() =
      CharacterBody3DBackendContractProbe(backendHandle).velocity.let { Vector3(it.x, it.y, it.z) }
    set(value) {
      CharacterBody3DBackendContractProbe(backendHandle).velocity =
        GodotVector3(value.x.toFloat(), value.y.toFloat(), value.z.toFloat())
    }

  fun moveAndSlide(): Boolean = CharacterBody3DBackendContractProbe(backendHandle).moveAndSlide()

  fun isOnFloor(): Boolean = CharacterBody3DBackendContractProbe(backendHandle).isOnFloor()

  fun isOnCeiling(): Boolean = CharacterBody3DBackendContractProbe(backendHandle).isOnCeiling()

  /** Wall normal from the last move_and_slide (anti-stuck nudge). */
  fun getWallNormal(): Vector3 =
    CharacterBody3DBackendContractProbe(backendHandle).getWallNormal().let {
      Vector3(it.x, it.y, it.z)
    }

  fun getSlideCollisionCount(): Long =
    CharacterBody3DBackendContractProbe(backendHandle).getSlideCollisionCount()

  /** One collision record from the last move_and_slide; the caller closes it when done. */
  fun getSlideCollision(index: Long): KinematicCollision3D? =
    CharacterBody3DBackendContractProbe(backendHandle).getSlideCollision(index)?.let {
      KinematicCollision3D(it)
    }
}

/** 3D area monitor: emits body_entered when a physics body overlaps (coin/trigger pickups). */
open class Area3D(godotObject: GodotHandle) : Node3D(godotObject) {
  object Signals {
    const val bodyEntered: String = "body_entered"
    const val bodyExited: String = "body_exited"
  }

  /**
   * Overlapping scripted bodies as [Node3D] handles, desktop's return type (bodies without
   * Kanama scripts are omitted by contract).
   */
  fun getOverlappingBodies(): List<Node3D> =
    GodotBackendCalls.invokeNoArgsRetHandleList(D.AREA3D_GET_OVERLAPPING_BODIES, backendHandle)
      .map { Node3D(WebObjectId(it.backendToken().toInt())) }
}

open class VisualInstance3D(godotObject: GodotHandle) : Node3D(godotObject)

open class GeometryInstance3D(godotObject: GodotHandle) : VisualInstance3D(godotObject)

open class Light3D(godotObject: GodotHandle) : VisualInstance3D(godotObject) {
  protected fun setParam(param: Long, value: Double) {
    Light3DBackendContractProbe(backendHandle).setParam(param, value)
  }

  companion object {
    const val PARAM_ENERGY: Long = 0L
    const val PARAM_SHADOW_OPACITY: Long = 17L
  }
}

class DirectionalLight3D(godotObject: GodotHandle) : Light3D(godotObject) {
  /** Write-only on Web: light_energy is Light3D.set_param(PARAM_ENERGY, value). */
  var lightEnergy: Double
    get() = unsupportedWebGameplayFamily("Light3D.get_light_energy")
    set(value) {
      setParam(PARAM_ENERGY, value)
    }

  /** Write-only on Web: shadow_opacity is Light3D.set_param(PARAM_SHADOW_OPACITY, value). */
  var shadowOpacity: Double
    get() = unsupportedWebGameplayFamily("Light3D.get_shadow_opacity")
    set(value) {
      setParam(PARAM_SHADOW_OPACITY, value)
    }

  /** Write-only on Web: squash splits the light into sky-only + light-only halves. */
  var skyMode: Long
    get() = unsupportedWebGameplayFamily("DirectionalLight3D.get_sky_mode")
    set(value) {
      DirectionalLight3DBackendContractProbe(backendHandle).setSkyMode(value)
    }

  companion object {
    const val SKY_MODE_LIGHT_AND_SKY: Long = 0L
    const val SKY_MODE_LIGHT_ONLY: Long = 1L
    const val SKY_MODE_SKY_ONLY: Long = 2L
  }
}

class Environment(godotObject: GodotHandle) : Resource(godotObject.toBackendHandle()) {
  /** Write-only on Web: the platformer lowers the Compatibility-renderer background energy. */
  var backgroundEnergyMultiplier: Double
    get() = unsupportedWebGameplayFamily("Environment.get_bg_energy_multiplier")
    set(value) {
      EnvironmentBackendContractProbe(backendHandle).setBgEnergyMultiplier(value)
    }
}

class WorldEnvironment(godotObject: GodotHandle) : Node(godotObject.toBackendHandle()) {
  val environment: Environment
    get() =
      WorldEnvironmentBackendContractProbe(backendHandle).getEnvironment()?.let { handle ->
        Environment(WebObjectId(handle.backendToken().toInt()))
      } ?: error("WorldEnvironment has no Environment resource")
}

object OS {
  fun hasFeature(tagName: String): Boolean = OSBackendContractProbe.hasFeature(tagName)

  /** Web ships the release template; debug-gated tooling stays off. */
  fun isDebugBuild(): Boolean = false

  fun shellOpen(url: String) {
    GodotBackendCalls.invokeStringNameArgSingleton(D.OS_SHELL_OPEN, url)
  }
}

object RenderingServer {
  const val SHADOW_QUALITY_SOFT_HIGH: Long = 4L

  fun getCurrentRenderingMethod(): String =
    RenderingServerBackendContractProbe.getCurrentRenderingMethod()

  fun directionalSoftShadowFilterSetQuality(quality: Long) {
    RenderingServerBackendContractProbe.directionalSoftShadowFilterSetQuality(quality)
  }
}
