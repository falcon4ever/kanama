package net.multigesture.kanama.exercise

import net.multigesture.kanama.api.PhysicsDirectSpaceState2D
import net.multigesture.kanama.api.PhysicsDirectSpaceState3D
import net.multigesture.kanama.api.PhysicsPointQueryParameters2D
import net.multigesture.kanama.api.PhysicsPointQueryParameters3D
import net.multigesture.kanama.api.PhysicsRayQueryParameters2D
import net.multigesture.kanama.api.PhysicsRayQueryParameters3D
import net.multigesture.kanama.api.PhysicsServer2D
import net.multigesture.kanama.api.PhysicsServer3D
import net.multigesture.kanama.api.PhysicsShapeQueryParameters2D
import net.multigesture.kanama.api.PhysicsShapeQueryParameters3D
import net.multigesture.kanama.types.Basis
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Transform2D
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector3

private const val ALL_LAYERS = 4294967295L

/**
 * The worlds the SCENE_ROWS physics rows query (`scripts/api_exercise_policy.py`): a private space
 * holding one static box at x = 10, made on the server and never stepped, so a query fills its
 * result in the frame the row runs in, on Godot Physics and on Jolt, on every platform. A fresh
 * object cannot answer these queries: an empty space leaves the result untouched, and so does a
 * call that never reached the engine.
 *
 * The body carries the probe node's instance id, so `collider_id` and `collider` have an answer.
 * [ApiExerciseRunner.end] calls [release].
 */
internal class PhysicsScene2D(private val r: ApiExerciseRunner) {
  private val space: RID = PhysicsServer2D.spaceCreate()
  private val shape: RID = PhysicsServer2D.rectangleShapeCreate()
  val body: RID = PhysicsServer2D.bodyCreate()
  val state: PhysicsDirectSpaceState2D

  init {
    PhysicsServer2D.spaceSetActive(space, true)
    PhysicsServer2D.shapeSetData(shape, Vector2(4.0, 4.0))
    PhysicsServer2D.bodySetMode(body, PhysicsServer2D.BodyMode.STATIC)
    PhysicsServer2D.bodyAddShape(body, shape, Transform2D.IDENTITY)
    PhysicsServer2D.bodyAttachObjectInstanceId(body, r.typeObject().instanceId)
    PhysicsServer2D.bodySetSpace(body, space)
    PhysicsServer2D.bodySetState(body, PhysicsServer2D.BodyState.TRANSFORM, at(10.0))
    state = checkNotNull(PhysicsServer2D.spaceGetDirectState(space)) { "no direct space state" }
  }

  private fun at(x: Double) = Transform2D(Vector2(1.0, 0.0), Vector2(0.0, 1.0), Vector2(x, 0.0))

  fun rayQuery(): PhysicsRayQueryParameters2D =
    r.track(
      checkNotNull(
        PhysicsRayQueryParameters2D.create(
          Vector2(0.0, 0.0),
          Vector2(20.0, 0.0),
          ALL_LAYERS,
          emptyList(),
        )
      )
    )

  fun pointQuery(): PhysicsPointQueryParameters2D {
    val query = r.track(PhysicsPointQueryParameters2D.create())
    query.position = Vector2(10.0, 0.0)
    return query
  }

  private fun shapeQuery(x: Double, motion: Vector2): PhysicsShapeQueryParameters2D {
    val query = r.track(PhysicsShapeQueryParameters2D.create())
    query.shapeRid = shape
    query.transform = at(x)
    query.motion = motion
    return query
  }

  /** The box's own shape, right on top of it. */
  fun overlapQuery(): PhysicsShapeQueryParameters2D = shapeQuery(10.0, Vector2.ZERO)

  /** The same shape, far to the left, moving through the box. */
  fun castQuery(): PhysicsShapeQueryParameters2D = shapeQuery(-20.0, Vector2(40.0, 0.0))

  /** The same shape, overlapping the box's right side. */
  fun touchQuery(): PhysicsShapeQueryParameters2D = shapeQuery(13.0, Vector2.ZERO)

  fun release() {
    PhysicsServer2D.freeRid(body)
    PhysicsServer2D.freeRid(shape)
    PhysicsServer2D.freeRid(space)
  }
}

/** [PhysicsScene2D] in 3D. */
internal class PhysicsScene3D(private val r: ApiExerciseRunner) {
  private val space: RID = PhysicsServer3D.spaceCreate()
  private val shape: RID = PhysicsServer3D.boxShapeCreate()
  val body: RID = PhysicsServer3D.bodyCreate()
  val state: PhysicsDirectSpaceState3D

  init {
    PhysicsServer3D.spaceSetActive(space, true)
    PhysicsServer3D.shapeSetData(shape, Vector3(2.0, 2.0, 2.0))
    PhysicsServer3D.bodySetMode(body, PhysicsServer3D.BodyMode.STATIC)
    PhysicsServer3D.bodyAddShape(body, shape, Transform3D.IDENTITY)
    PhysicsServer3D.bodyAttachObjectInstanceId(body, r.typeObject().instanceId)
    PhysicsServer3D.bodySetSpace(body, space)
    PhysicsServer3D.bodySetState(body, PhysicsServer3D.BodyState.TRANSFORM, at(10.0))
    state = checkNotNull(PhysicsServer3D.spaceGetDirectState(space)) { "no direct space state" }
  }

  private fun at(x: Double) =
    Transform3D(
      Basis(Vector3(1.0, 0.0, 0.0), Vector3(0.0, 1.0, 0.0), Vector3(0.0, 0.0, 1.0)),
      Vector3(x, 0.0, 0.0),
    )

  fun rayQuery(): PhysicsRayQueryParameters3D =
    r.track(
      checkNotNull(
        PhysicsRayQueryParameters3D.create(
          Vector3(0.0, 0.0, 0.0),
          Vector3(20.0, 0.0, 0.0),
          ALL_LAYERS,
          emptyList(),
        )
      )
    )

  fun pointQuery(): PhysicsPointQueryParameters3D {
    val query = r.track(PhysicsPointQueryParameters3D.create())
    query.position = Vector3(10.0, 0.0, 0.0)
    return query
  }

  private fun shapeQuery(x: Double, motion: Vector3): PhysicsShapeQueryParameters3D {
    val query = r.track(PhysicsShapeQueryParameters3D.create())
    query.shapeRid = shape
    query.transform = at(x)
    query.motion = motion
    return query
  }

  fun overlapQuery(): PhysicsShapeQueryParameters3D = shapeQuery(10.0, Vector3.ZERO)

  fun castQuery(): PhysicsShapeQueryParameters3D = shapeQuery(-20.0, Vector3(40.0, 0.0, 0.0))

  fun touchQuery(): PhysicsShapeQueryParameters3D = shapeQuery(11.0, Vector3.ZERO)

  fun release() {
    PhysicsServer3D.freeRid(body)
    PhysicsServer3D.freeRid(shape)
    PhysicsServer3D.freeRid(space)
  }
}

/**
 * A real Generic 6DOF joint between two bodies: a fresh joint answers its target rotation with an
 * engine error. Only Jolt keeps the target; Godot Physics ignores it and answers the identity.
 */
internal class JointScene {
  private val bodyA: RID = PhysicsServer3D.bodyCreate()
  private val bodyB: RID = PhysicsServer3D.bodyCreate()
  val joint: RID = PhysicsServer3D.jointCreate()

  init {
    PhysicsServer3D.jointMakeGeneric6dof(
      joint,
      bodyA,
      Transform3D.IDENTITY,
      bodyB,
      Transform3D.IDENTITY,
    )
  }

  fun release() {
    PhysicsServer3D.freeRid(joint)
    PhysicsServer3D.freeRid(bodyB)
    PhysicsServer3D.freeRid(bodyA)
  }
}
