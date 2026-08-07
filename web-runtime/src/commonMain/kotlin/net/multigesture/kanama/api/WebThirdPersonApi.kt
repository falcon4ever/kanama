@file:OptIn(InternalKanamaBackendApi::class)

package net.multigesture.kanama.api

import net.multigesture.kanama.backend.GodotBackendCalls
import net.multigesture.kanama.backend.GodotVector3
import net.multigesture.kanama.backend.InitialGodotCallDescriptors as D
import net.multigesture.kanama.backend.InternalKanamaBackendApi
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector3
import net.multigesture.kanama.web.WebObjectId

/**
 * Web API surface for the third-person controller (Task 60f).
 *
 * Transform composition is pure Kotlin over the mirrored position/rotation/scale snapshots:
 * the engine side only ever sees decomposed property writes. Global transforms assume
 * unit global scale (true for every rig in the corpus; only the character's rotation root
 * carries a local scale).
 */

/** FPS-era alias: the pure-Kotlin Basis moved in with the value types. */
typealias Basis = net.multigesture.kanama.types.Basis

/** Import-compat alias for shared demo sources; delegates to the [Node3D] member (task 64). */
@Suppress("EXTENSION_SHADOWED_BY_MEMBER")
var Node3D.basis: Basis
  get() = basis
  set(value) {
    basis = value
  }

/** Import-compat alias for shared demo sources; delegates to the [Node3D] member (task 64). */
@Suppress("EXTENSION_SHADOWED_BY_MEMBER")
var Node3D.transform: Transform3D
  get() = transform
  set(value) {
    transform = value
  }

/** Import-compat alias for shared demo sources; delegates to the [Node3D] member (task 64). */
@Suppress("EXTENSION_SHADOWED_BY_MEMBER")
var Node3D.globalTransform: Transform3D
  get() = globalTransform
  set(value) {
    globalTransform = value
  }

/** Import-compat alias for shared demo sources; delegates to the [Node3D] member (task 64). */
@Suppress("EXTENSION_SHADOWED_BY_MEMBER")
fun Node3D.lookAt(target: Vector3, up: Vector3 = Vector3.UP, useModelFront: Boolean = false) =
  lookAt(target, up, useModelFront)

/** Import-compat alias for shared demo sources; delegates to the [Node3D] member (task 64). */
@Suppress("EXTENSION_SHADOWED_BY_MEMBER")
fun Node3D.isVisible(): Boolean = isVisible()

/** Import-compat alias for shared demo sources; delegates to the [Node3D] member (task 64). */
@Suppress("EXTENSION_SHADOWED_BY_MEMBER")
fun Node3D.rotateObjectLocal(axis: Vector3, angle: Double) = rotateObjectLocal(axis, angle)

private fun Vector3.toGodot(): GodotVector3 = GodotVector3(x.toFloat(), y.toFloat(), z.toFloat())

private fun GodotVector3.toApi(): Vector3 = Vector3(x, y, z)

/** Import-compat alias for shared demo sources; delegates to the [PhysicsBody3D] member (task 64). */
fun PhysicsBody3D.moveAndCollide(motion: Vector3): KinematicCollision3D? = moveAndCollide(motion)

/**
 * Import-compat alias for shared demo sources; widens desktop's Node parameter for the ported
 * corpus and delegates to the [PhysicsBody3D] member (task 64).
 */
fun PhysicsBody3D.addCollisionExceptionWith(body: GodotObject) =
  addCollisionExceptionWith(Node(body.handle))

/** Import-compat alias for shared demo sources; delegates to the [PhysicsBody3D] member (task 64). */
@Suppress("EXTENSION_SHADOWED_BY_MEMBER")
fun PhysicsBody3D.setAxisLock(axis: Long, lock: Boolean) = setAxisLock(axis, lock)

/** Import-compat alias for shared demo sources; mirrors the [PhysicsBody3D] companion (task 64). */
object BodyAxis {
  const val ANGULAR_X: Long = PhysicsBody3D.BODY_AXIS_ANGULAR_X
  const val ANGULAR_Y: Long = PhysicsBody3D.BODY_AXIS_ANGULAR_Y
  const val ANGULAR_Z: Long = PhysicsBody3D.BODY_AXIS_ANGULAR_Z
}

/**
 * Import-compat alias for shared demo sources; keeps the ported corpus's Long layer parameter and
 * delegates to the [PhysicsBody3D] member, which takes desktop's Int layerNumber (task 64).
 */
fun CollisionObject3D.setCollisionLayerValue(layer: Long, value: Boolean) =
  setCollisionLayerValue(layer.toInt(), value)

/** CollisionObject3D alias tier for the third-person port (layer/mask + exceptions). */
typealias CollisionObject3D = PhysicsBody3D

/** 3D shape sweep node (ground probe + grenade aim). Reads are synchronous immediates. */
class ShapeCast3D(godotObject: GodotHandle) : Node3D(godotObject) {
  var targetPosition: Vector3
    get() =
      GodotBackendCalls.invokeNoArgsRetVector3(D.SHAPECAST3D_GET_TARGET_POSITION, backendHandle)
        .toApi()
    set(value) {
      GodotBackendCalls.invokeVector3Arg(
        D.SHAPECAST3D_SET_TARGET_POSITION,
        backendHandle,
        value.toGodot(),
      )
    }

  fun getCollisionCount(): Long =
    GodotBackendCalls.invokeNoArgsRetLong(D.SHAPECAST3D_GET_COLLISION_COUNT, backendHandle)

  fun getCollisionPoint(index: Long): Vector3 =
    GodotBackendCalls.invokeLongRetVector3(D.SHAPECAST3D_GET_COLLISION_POINT, backendHandle, index)
      .toApi()

  /** The indexed hit as an existing tracked handle (script-or-known, node-lookup rule). */
  fun getCollider(index: Long): GodotObject? =
    GodotBackendCalls.invokeLongRetHandle(D.SHAPECAST3D_GET_COLLIDER, backendHandle, index)?.let {
      GodotObject(WebObjectId(it.backendToken().toInt()))
    }
}

/** Navigation agent (beetle-bot pathing); requires a baked navmesh in the exported level. */
class NavigationAgent3D(godotObject: GodotHandle) : Node(godotObject.toBackendHandle()) {
  var targetPosition: Vector3
    get() = unsupportedWebGameplayFamily("NavigationAgent3D.get_target_position")
    set(value) {
      GodotBackendCalls.invokeVector3Arg(
        D.NAVIGATIONAGENT3D_SET_TARGET_POSITION,
        backendHandle,
        value.toGodot(),
      )
    }

  fun getNextPathPosition(): Vector3 =
    GodotBackendCalls.invokeNoArgsRetVector3(
        D.NAVIGATIONAGENT3D_GET_NEXT_PATH_POSITION,
        backendHandle,
      )
      .toApi()

  fun isTargetReached(): Boolean =
    GodotBackendCalls.invokeNoArgsRetBool(D.NAVIGATIONAGENT3D_IS_TARGET_REACHED, backendHandle)
}

/**
 * Camera collision arm. Web adaptation: exclusion takes the collision OBJECT (the applier
 * derives the RID engine-side; RIDs never cross the boundary).
 */
class SpringArm3D(godotObject: GodotHandle) : Node3D(godotObject) {
  fun addExcludedObject(body: GodotObject) {
    GodotBackendCalls.invokeObjectArg(
      D.SPRINGARM3D_ADD_EXCLUDED_OBJECT,
      backendHandle,
      body.backendHandle,
    )
  }
}

/**
 * Import-compat alias for shared demo sources; widens desktop's CollisionObject3D parameter for
 * the ported corpus and delegates to the [RayCast3D] member (task 64).
 */
fun RayCast3D.addException(body: GodotObject) = addException(CollisionObject3D(body.handle))

/** Position marker (grenade launch point) — pure transform surface. */
class Marker3D(godotObject: GodotHandle) : Node3D(godotObject)

/** Grass scatter host: the Web port keeps the node inert (no MultiMesh introspection yet). */
class MultiMeshInstance3D(godotObject: GodotHandle) : GeometryInstance3D(godotObject)

/** Animation resource resolved through AnimationMixer.get_animation. */
class Animation internal constructor(godotObject: GodotHandle) : GodotObject(godotObject) {
  fun setLoopMode(mode: Long) {
    GodotBackendCalls.invokeLongArg(D.ANIMATION_SET_LOOP_MODE, backendHandle, mode)
  }

  companion object {
    const val LOOP_LINEAR: Long = 1L
  }
}

/**
 * Import-compat alias for shared demo sources; delegates to the [Area3D] member and keeps the
 * ported corpus's List<GodotObject> return type, covariant over the member's List<Node3D>
 * (task 64).
 */
@Suppress("EXTENSION_SHADOWED_BY_MEMBER")
fun Area3D.getOverlappingBodies(): List<GodotObject> = getOverlappingBodies()

/** Import-compat alias for shared demo sources; delegates to the [GodotObject] member (task 64). */
@Suppress("EXTENSION_SHADOWED_BY_MEMBER")
fun GodotObject.call(method: String, first: Vector3, second: Vector3) =
  call(method, first, second)

/** Import-compat alias for shared demo sources; delegates to the [Node] member (task 64). */
@Suppress("EXTENSION_SHADOWED_BY_MEMBER")
fun Node.setProcess(enable: Boolean) = setProcess(enable)

/** Import-compat alias for shared demo sources; delegates to the [Node] member (task 64). */
@Suppress("EXTENSION_SHADOWED_BY_MEMBER")
fun Node.setProcessInput(enable: Boolean) = setProcessInput(enable)

/** Import-compat alias for shared demo sources; delegates to the [Node] member (task 64). */
@Suppress("EXTENSION_SHADOWED_BY_MEMBER")
fun Node.setProcessUnhandledInput(enable: Boolean) = setProcessUnhandledInput(enable)

object ProjectSettings {
  /** Float-valued setting read (x1000 integer transport; millis precision). */
  fun getSettingDouble(name: String): Double =
    GodotBackendCalls.invokeStringNameRetDoubleSingleton(
      D.PROJECTSETTINGS_GET_SETTING_DOUBLE,
      name,
    )
}

/** Import-compat alias for shared demo sources; delegates to the [Node] member (task 64). */
@Suppress("EXTENSION_SHADOWED_BY_MEMBER")
fun Node.getPhysicsProcessDeltaTime(): Double = getPhysicsProcessDeltaTime()

object Time {
  private val origin = kotlin.time.TimeSource.Monotonic.markNow()

  /** Web adaptation: a Kotlin monotonic clock (only relative time is consumed). */
  fun getTicksMsec(): Long = origin.elapsedNow().inWholeMilliseconds
}
