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

private fun composeBasis(rotation: Vector3, scale: Vector3): Basis {
  val rotationBasis = Basis.fromEuler(rotation)
  // Node basis = R * S: columns scaled (Godot composes scale on the right of rotation).
  return Basis(
    rotationBasis.getColumn(0) * scale.x,
    rotationBasis.getColumn(1) * scale.y,
    rotationBasis.getColumn(2) * scale.z,
  )
}

/** The node's local rotation+scale basis; writes decompose to the two snapshot families. */
var Node3D.basis: Basis
  get() = composeBasis(rotation, scale)
  set(value) {
    rotation = value.getEuler()
    scale = value.getScale()
  }

/** The node's local transform (basis + origin over the mirrored snapshots). */
var Node3D.transform: Transform3D
  get() = Transform3D(basis, position)
  set(value) {
    position = value.origin
    basis = value.basis
  }

/** The node's world transform; rotation/origin cross synchronously, global scale assumed 1. */
var Node3D.globalTransform: Transform3D
  get() = Transform3D(Basis.fromEuler(globalRotation), globalPosition)
  set(value) {
    globalPosition = value.origin
    globalRotation = value.basis.getEuler()
  }

/** Orient -Z at [target] from the current position (rides look_at_from_position). */
fun Node3D.lookAt(target: Vector3, up: Vector3 = Vector3.UP) {
  lookAtFromPosition(globalPosition, target, up)
}

/** Node3D visibility read (synchronous immediate). */
fun Node3D.isVisible(): Boolean =
  GodotBackendCalls.invokeNoArgsRetBool(D.NODE3D_IS_VISIBLE, backendHandle)

/** Godot's rotate_object_local: right-multiply the local basis by an axis-angle rotation. */
fun Node3D.rotateObjectLocal(axis: Vector3, angle: Double) {
  basis = basis * Basis.fromAxisAngle(axis, angle)
}

private fun Vector3.toGodot(): GodotVector3 = GodotVector3(x.toFloat(), y.toFloat(), z.toFloat())

private fun GodotVector3.toApi(): Vector3 = Vector3(x, y, z)

/** First collision of a motion sweep; close it when done (KinematicCollision3D rule). */
fun PhysicsBody3D.moveAndCollide(motion: Vector3): KinematicCollision3D? =
  GodotBackendCalls.invokeVector3RetHandle(
      D.PHYSICSBODY3D_MOVE_AND_COLLIDE,
      backendHandle,
      motion.toGodot(),
    )
    ?.let { KinematicCollision3D(it) }

fun PhysicsBody3D.addCollisionExceptionWith(body: GodotObject) {
  GodotBackendCalls.invokeObjectArg(
    D.PHYSICSBODY3D_ADD_COLLISION_EXCEPTION_WITH,
    backendHandle,
    body.backendHandle,
  )
}

fun PhysicsBody3D.setAxisLock(axis: Long, lock: Boolean) {
  GodotBackendCalls.invokeLongBoolArg(D.PHYSICSBODY3D_SET_AXIS_LOCK, backendHandle, axis, lock)
}

/** PhysicsServer3D.BodyAxis constants (PhysicsBody3D.set_axis_lock). */
object BodyAxis {
  const val ANGULAR_X: Long = 8L
  const val ANGULAR_Y: Long = 16L
  const val ANGULAR_Z: Long = 32L
}

fun CollisionObject3D.setCollisionLayerValue(layer: Long, value: Boolean) {
  GodotBackendCalls.invokeLongBoolArg(
    D.COLLISIONOBJECT3D_SET_COLLISION_LAYER_VALUE,
    backendHandle,
    layer,
    value,
  )
}

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

fun RayCast3D.addException(body: GodotObject) {
  GodotBackendCalls.invokeObjectArg(D.RAYCAST3D_ADD_EXCEPTION, backendHandle, body.backendHandle)
}

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

/** Overlapping scripted bodies (bodies without Kanama scripts are omitted by contract). */
fun Area3D.getOverlappingBodies(): List<GodotObject> =
  GodotBackendCalls.invokeNoArgsRetHandleList(D.AREA3D_GET_OVERLAPPING_BODIES, backendHandle).map {
    GodotObject(WebObjectId(it.backendToken().toInt()))
  }

/** Dynamic two-Vector3 dispatch (the corpus's damage(impact, force) convention). */
fun GodotObject.call(method: String, first: Vector3, second: Vector3) {
  GodotBackendCalls.invokeStringNameVector3Vector3Arg(
    D.OBJECT_CALL_VECTOR3_VECTOR3,
    backendHandle,
    method,
    first.toGodot(),
    second.toGodot(),
  )
}

fun Node.setProcess(enable: Boolean) {
  GodotBackendCalls.invokeBoolArg(D.NODE_SET_PROCESS, backendHandle, enable)
}

/** Web no-ops: generated proxies dispatch input only to scripts that declare handlers. */
fun Node.setProcessInput(@Suppress("UNUSED_PARAMETER") enable: Boolean) = Unit

fun Node.setProcessUnhandledInput(@Suppress("UNUSED_PARAMETER") enable: Boolean) = Unit

object ProjectSettings {
  /** Float-valued setting read (x1000 integer transport; millis precision). */
  fun getSettingDouble(name: String): Double =
    GodotBackendCalls.invokeStringNameRetDoubleSingleton(
      D.PROJECTSETTINGS_GET_SETTING_DOUBLE,
      name,
    )
}

object Time {
  private val origin = kotlin.time.TimeSource.Monotonic.markNow()

  /** Web adaptation: a Kotlin monotonic clock (only relative time is consumed). */
  fun getTicksMsec(): Long = origin.elapsedNow().inWholeMilliseconds
}
