package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector3

/**
 * Generated from Godot docs: PhysicsServer3D
 */
object PhysicsServer3D {
    private val singleton: MemorySegment by lazy {
        ObjectCalls.getSingleton("PhysicsServer3D")
    }

    const val JOINT_TYPE_PIN: Long = 0L
    const val JOINT_TYPE_HINGE: Long = 1L
    const val JOINT_TYPE_SLIDER: Long = 2L
    const val JOINT_TYPE_CONE_TWIST: Long = 3L
    const val JOINT_TYPE_6DOF: Long = 4L
    const val JOINT_TYPE_MAX: Long = 5L
    const val PIN_JOINT_BIAS: Long = 0L
    const val PIN_JOINT_DAMPING: Long = 1L
    const val PIN_JOINT_IMPULSE_CLAMP: Long = 2L
    const val HINGE_JOINT_BIAS: Long = 0L
    const val HINGE_JOINT_LIMIT_UPPER: Long = 1L
    const val HINGE_JOINT_LIMIT_LOWER: Long = 2L
    const val HINGE_JOINT_LIMIT_BIAS: Long = 3L
    const val HINGE_JOINT_LIMIT_SOFTNESS: Long = 4L
    const val HINGE_JOINT_LIMIT_RELAXATION: Long = 5L
    const val HINGE_JOINT_MOTOR_TARGET_VELOCITY: Long = 6L
    const val HINGE_JOINT_MOTOR_MAX_IMPULSE: Long = 7L
    const val HINGE_JOINT_FLAG_USE_LIMIT: Long = 0L
    const val HINGE_JOINT_FLAG_ENABLE_MOTOR: Long = 1L
    const val SLIDER_JOINT_LINEAR_LIMIT_UPPER: Long = 0L
    const val SLIDER_JOINT_LINEAR_LIMIT_LOWER: Long = 1L
    const val SLIDER_JOINT_LINEAR_LIMIT_SOFTNESS: Long = 2L
    const val SLIDER_JOINT_LINEAR_LIMIT_RESTITUTION: Long = 3L
    const val SLIDER_JOINT_LINEAR_LIMIT_DAMPING: Long = 4L
    const val SLIDER_JOINT_LINEAR_MOTION_SOFTNESS: Long = 5L
    const val SLIDER_JOINT_LINEAR_MOTION_RESTITUTION: Long = 6L
    const val SLIDER_JOINT_LINEAR_MOTION_DAMPING: Long = 7L
    const val SLIDER_JOINT_LINEAR_ORTHOGONAL_SOFTNESS: Long = 8L
    const val SLIDER_JOINT_LINEAR_ORTHOGONAL_RESTITUTION: Long = 9L
    const val SLIDER_JOINT_LINEAR_ORTHOGONAL_DAMPING: Long = 10L
    const val SLIDER_JOINT_ANGULAR_LIMIT_UPPER: Long = 11L
    const val SLIDER_JOINT_ANGULAR_LIMIT_LOWER: Long = 12L
    const val SLIDER_JOINT_ANGULAR_LIMIT_SOFTNESS: Long = 13L
    const val SLIDER_JOINT_ANGULAR_LIMIT_RESTITUTION: Long = 14L
    const val SLIDER_JOINT_ANGULAR_LIMIT_DAMPING: Long = 15L
    const val SLIDER_JOINT_ANGULAR_MOTION_SOFTNESS: Long = 16L
    const val SLIDER_JOINT_ANGULAR_MOTION_RESTITUTION: Long = 17L
    const val SLIDER_JOINT_ANGULAR_MOTION_DAMPING: Long = 18L
    const val SLIDER_JOINT_ANGULAR_ORTHOGONAL_SOFTNESS: Long = 19L
    const val SLIDER_JOINT_ANGULAR_ORTHOGONAL_RESTITUTION: Long = 20L
    const val SLIDER_JOINT_ANGULAR_ORTHOGONAL_DAMPING: Long = 21L
    const val SLIDER_JOINT_MAX: Long = 22L
    const val CONE_TWIST_JOINT_SWING_SPAN: Long = 0L
    const val CONE_TWIST_JOINT_TWIST_SPAN: Long = 1L
    const val CONE_TWIST_JOINT_BIAS: Long = 2L
    const val CONE_TWIST_JOINT_SOFTNESS: Long = 3L
    const val CONE_TWIST_JOINT_RELAXATION: Long = 4L
    const val G6DOF_JOINT_LINEAR_LOWER_LIMIT: Long = 0L
    const val G6DOF_JOINT_LINEAR_UPPER_LIMIT: Long = 1L
    const val G6DOF_JOINT_LINEAR_LIMIT_SOFTNESS: Long = 2L
    const val G6DOF_JOINT_LINEAR_RESTITUTION: Long = 3L
    const val G6DOF_JOINT_LINEAR_DAMPING: Long = 4L
    const val G6DOF_JOINT_LINEAR_MOTOR_TARGET_VELOCITY: Long = 5L
    const val G6DOF_JOINT_LINEAR_MOTOR_FORCE_LIMIT: Long = 6L
    const val G6DOF_JOINT_LINEAR_SPRING_STIFFNESS: Long = 7L
    const val G6DOF_JOINT_LINEAR_SPRING_DAMPING: Long = 8L
    const val G6DOF_JOINT_LINEAR_SPRING_EQUILIBRIUM_POINT: Long = 9L
    const val G6DOF_JOINT_ANGULAR_LOWER_LIMIT: Long = 10L
    const val G6DOF_JOINT_ANGULAR_UPPER_LIMIT: Long = 11L
    const val G6DOF_JOINT_ANGULAR_LIMIT_SOFTNESS: Long = 12L
    const val G6DOF_JOINT_ANGULAR_DAMPING: Long = 13L
    const val G6DOF_JOINT_ANGULAR_RESTITUTION: Long = 14L
    const val G6DOF_JOINT_ANGULAR_FORCE_LIMIT: Long = 15L
    const val G6DOF_JOINT_ANGULAR_ERP: Long = 16L
    const val G6DOF_JOINT_ANGULAR_MOTOR_TARGET_VELOCITY: Long = 17L
    const val G6DOF_JOINT_ANGULAR_MOTOR_FORCE_LIMIT: Long = 18L
    const val G6DOF_JOINT_ANGULAR_SPRING_STIFFNESS: Long = 19L
    const val G6DOF_JOINT_ANGULAR_SPRING_DAMPING: Long = 20L
    const val G6DOF_JOINT_ANGULAR_SPRING_EQUILIBRIUM_POINT: Long = 21L
    const val G6DOF_JOINT_MAX: Long = 22L
    const val G6DOF_JOINT_FLAG_ENABLE_LINEAR_LIMIT: Long = 0L
    const val G6DOF_JOINT_FLAG_ENABLE_ANGULAR_LIMIT: Long = 1L
    const val G6DOF_JOINT_FLAG_ENABLE_ANGULAR_SPRING: Long = 2L
    const val G6DOF_JOINT_FLAG_ENABLE_LINEAR_SPRING: Long = 3L
    const val G6DOF_JOINT_FLAG_ENABLE_MOTOR: Long = 4L
    const val G6DOF_JOINT_FLAG_ENABLE_LINEAR_MOTOR: Long = 5L
    const val G6DOF_JOINT_FLAG_MAX: Long = 6L
    const val SHAPE_WORLD_BOUNDARY: Long = 0L
    const val SHAPE_SEPARATION_RAY: Long = 1L
    const val SHAPE_SPHERE: Long = 2L
    const val SHAPE_BOX: Long = 3L
    const val SHAPE_CAPSULE: Long = 4L
    const val SHAPE_CYLINDER: Long = 5L
    const val SHAPE_CONVEX_POLYGON: Long = 6L
    const val SHAPE_CONCAVE_POLYGON: Long = 7L
    const val SHAPE_HEIGHTMAP: Long = 8L
    const val SHAPE_SOFT_BODY: Long = 9L
    const val SHAPE_CUSTOM: Long = 10L
    const val AREA_PARAM_GRAVITY_OVERRIDE_MODE: Long = 0L
    const val AREA_PARAM_GRAVITY: Long = 1L
    const val AREA_PARAM_GRAVITY_VECTOR: Long = 2L
    const val AREA_PARAM_GRAVITY_IS_POINT: Long = 3L
    const val AREA_PARAM_GRAVITY_POINT_UNIT_DISTANCE: Long = 4L
    const val AREA_PARAM_LINEAR_DAMP_OVERRIDE_MODE: Long = 5L
    const val AREA_PARAM_LINEAR_DAMP: Long = 6L
    const val AREA_PARAM_ANGULAR_DAMP_OVERRIDE_MODE: Long = 7L
    const val AREA_PARAM_ANGULAR_DAMP: Long = 8L
    const val AREA_PARAM_PRIORITY: Long = 9L
    const val AREA_PARAM_WIND_FORCE_MAGNITUDE: Long = 10L
    const val AREA_PARAM_WIND_SOURCE: Long = 11L
    const val AREA_PARAM_WIND_DIRECTION: Long = 12L
    const val AREA_PARAM_WIND_ATTENUATION_FACTOR: Long = 13L
    const val AREA_SPACE_OVERRIDE_DISABLED: Long = 0L
    const val AREA_SPACE_OVERRIDE_COMBINE: Long = 1L
    const val AREA_SPACE_OVERRIDE_COMBINE_REPLACE: Long = 2L
    const val AREA_SPACE_OVERRIDE_REPLACE: Long = 3L
    const val AREA_SPACE_OVERRIDE_REPLACE_COMBINE: Long = 4L
    const val BODY_MODE_STATIC: Long = 0L
    const val BODY_MODE_KINEMATIC: Long = 1L
    const val BODY_MODE_RIGID: Long = 2L
    const val BODY_MODE_RIGID_LINEAR: Long = 3L
    const val BODY_PARAM_BOUNCE: Long = 0L
    const val BODY_PARAM_FRICTION: Long = 1L
    const val BODY_PARAM_MASS: Long = 2L
    const val BODY_PARAM_INERTIA: Long = 3L
    const val BODY_PARAM_CENTER_OF_MASS: Long = 4L
    const val BODY_PARAM_GRAVITY_SCALE: Long = 5L
    const val BODY_PARAM_LINEAR_DAMP_MODE: Long = 6L
    const val BODY_PARAM_ANGULAR_DAMP_MODE: Long = 7L
    const val BODY_PARAM_LINEAR_DAMP: Long = 8L
    const val BODY_PARAM_ANGULAR_DAMP: Long = 9L
    const val BODY_PARAM_MAX: Long = 10L
    const val BODY_DAMP_MODE_COMBINE: Long = 0L
    const val BODY_DAMP_MODE_REPLACE: Long = 1L
    const val BODY_STATE_TRANSFORM: Long = 0L
    const val BODY_STATE_LINEAR_VELOCITY: Long = 1L
    const val BODY_STATE_ANGULAR_VELOCITY: Long = 2L
    const val BODY_STATE_SLEEPING: Long = 3L
    const val BODY_STATE_CAN_SLEEP: Long = 4L
    const val AREA_BODY_ADDED: Long = 0L
    const val AREA_BODY_REMOVED: Long = 1L
    const val INFO_ACTIVE_OBJECTS: Long = 0L
    const val INFO_COLLISION_PAIRS: Long = 1L
    const val INFO_ISLAND_COUNT: Long = 2L
    const val SPACE_PARAM_CONTACT_RECYCLE_RADIUS: Long = 0L
    const val SPACE_PARAM_CONTACT_MAX_SEPARATION: Long = 1L
    const val SPACE_PARAM_CONTACT_MAX_ALLOWED_PENETRATION: Long = 2L
    const val SPACE_PARAM_CONTACT_DEFAULT_BIAS: Long = 3L
    const val SPACE_PARAM_BODY_LINEAR_VELOCITY_SLEEP_THRESHOLD: Long = 4L
    const val SPACE_PARAM_BODY_ANGULAR_VELOCITY_SLEEP_THRESHOLD: Long = 5L
    const val SPACE_PARAM_BODY_TIME_TO_SLEEP: Long = 6L
    const val SPACE_PARAM_SOLVER_ITERATIONS: Long = 7L
    const val BODY_AXIS_LINEAR_X: Long = 1L
    const val BODY_AXIS_LINEAR_Y: Long = 2L
    const val BODY_AXIS_LINEAR_Z: Long = 4L
    const val BODY_AXIS_ANGULAR_X: Long = 8L
    const val BODY_AXIS_ANGULAR_Y: Long = 16L
    const val BODY_AXIS_ANGULAR_Z: Long = 32L

    fun worldBoundaryShapeCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(worldBoundaryShapeCreateBind, singleton)
    }

    fun separationRayShapeCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(separationRayShapeCreateBind, singleton)
    }

    fun sphereShapeCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(sphereShapeCreateBind, singleton)
    }

    fun boxShapeCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(boxShapeCreateBind, singleton)
    }

    fun capsuleShapeCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(capsuleShapeCreateBind, singleton)
    }

    fun cylinderShapeCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(cylinderShapeCreateBind, singleton)
    }

    fun convexPolygonShapeCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(convexPolygonShapeCreateBind, singleton)
    }

    fun concavePolygonShapeCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(concavePolygonShapeCreateBind, singleton)
    }

    fun heightmapShapeCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(heightmapShapeCreateBind, singleton)
    }

    fun customShapeCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(customShapeCreateBind, singleton)
    }

    fun shapeSetMargin(shape: RID, margin: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(shapeSetMarginBind, singleton, shape, margin)
    }

    fun shapeGetType(shape: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(shapeGetTypeBind, singleton, shape)
    }

    fun shapeGetMargin(shape: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(shapeGetMarginBind, singleton, shape)
    }

    fun spaceCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(spaceCreateBind, singleton)
    }

    fun spaceSetActive(space: RID, active: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(spaceSetActiveBind, singleton, space, active)
    }

    fun spaceIsActive(space: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(spaceIsActiveBind, singleton, space)
    }

    fun spaceSetParam(space: RID, param: Long, value: Double) {
        ObjectCalls.ptrcallWithRIDLongAndDoubleArgs(spaceSetParamBind, singleton, space, param, value)
    }

    fun spaceGetParam(space: RID, param: Long): Double {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetDouble(spaceGetParamBind, singleton, space, param)
    }

    fun spaceGetDirectState(space: RID): PhysicsDirectSpaceState3D? {
        return PhysicsDirectSpaceState3D.wrap(ObjectCalls.ptrcallWithRIDArgRetObject(spaceGetDirectStateBind, singleton, space))
    }

    fun areaCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(areaCreateBind, singleton)
    }

    fun areaSetSpace(area: RID, space: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(areaSetSpaceBind, singleton, area, space)
    }

    fun areaGetSpace(area: RID): RID {
        return ObjectCalls.ptrcallWithRIDArgRetRID(areaGetSpaceBind, singleton, area)
    }

    fun areaAddShape(area: RID, shape: RID, transform: Transform3D, disabled: Boolean = false) {
        ObjectCalls.ptrcallWithTwoRIDTransform3DBoolArgs(areaAddShapeBind, singleton, area, shape, transform, disabled)
    }

    fun areaSetShape(area: RID, shapeIdx: Int, shape: RID) {
        ObjectCalls.ptrcallWithRIDIntAndRIDArgs(areaSetShapeBind, singleton, area, shapeIdx, shape)
    }

    fun areaSetShapeTransform(area: RID, shapeIdx: Int, transform: Transform3D) {
        ObjectCalls.ptrcallWithRIDIntAndTransform3DArg(areaSetShapeTransformBind, singleton, area, shapeIdx, transform)
    }

    fun areaSetShapeDisabled(area: RID, shapeIdx: Int, disabled: Boolean) {
        ObjectCalls.ptrcallWithRIDIntAndBoolArgs(areaSetShapeDisabledBind, singleton, area, shapeIdx, disabled)
    }

    fun areaGetShapeCount(area: RID): Int {
        return ObjectCalls.ptrcallWithRIDArgRetInt(areaGetShapeCountBind, singleton, area)
    }

    fun areaGetShape(area: RID, shapeIdx: Int): RID {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetRID(areaGetShapeBind, singleton, area, shapeIdx)
    }

    fun areaGetShapeTransform(area: RID, shapeIdx: Int): Transform3D {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetTransform3D(areaGetShapeTransformBind, singleton, area, shapeIdx)
    }

    fun areaRemoveShape(area: RID, shapeIdx: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(areaRemoveShapeBind, singleton, area, shapeIdx)
    }

    fun areaClearShapes(area: RID) {
        ObjectCalls.ptrcallWithRIDArg(areaClearShapesBind, singleton, area)
    }

    fun areaSetCollisionLayer(area: RID, layer: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(areaSetCollisionLayerBind, singleton, area, layer)
    }

    fun areaGetCollisionLayer(area: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetUInt32(areaGetCollisionLayerBind, singleton, area)
    }

    fun areaSetCollisionMask(area: RID, mask: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(areaSetCollisionMaskBind, singleton, area, mask)
    }

    fun areaGetCollisionMask(area: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetUInt32(areaGetCollisionMaskBind, singleton, area)
    }

    fun areaSetTransform(area: RID, transform: Transform3D) {
        ObjectCalls.ptrcallWithRIDAndTransform3DArg(areaSetTransformBind, singleton, area, transform)
    }

    fun areaGetTransform(area: RID): Transform3D {
        return ObjectCalls.ptrcallWithRIDArgRetTransform3D(areaGetTransformBind, singleton, area)
    }

    fun areaAttachObjectInstanceId(area: RID, id: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(areaAttachObjectInstanceIdBind, singleton, area, id)
    }

    fun areaGetObjectInstanceId(area: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(areaGetObjectInstanceIdBind, singleton, area)
    }

    fun areaSetMonitorCallback(area: RID, callback: GodotCallable) {
        ObjectCalls.ptrcallWithRIDCallableArgs(areaSetMonitorCallbackBind, singleton, area, callback.target.handle, callback.method)
    }

    fun areaSetAreaMonitorCallback(area: RID, callback: GodotCallable) {
        ObjectCalls.ptrcallWithRIDCallableArgs(areaSetAreaMonitorCallbackBind, singleton, area, callback.target.handle, callback.method)
    }

    fun areaSetMonitorable(area: RID, monitorable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(areaSetMonitorableBind, singleton, area, monitorable)
    }

    fun areaSetRayPickable(area: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(areaSetRayPickableBind, singleton, area, enable)
    }

    fun bodyCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(bodyCreateBind, singleton)
    }

    fun bodySetSpace(body: RID, space: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(bodySetSpaceBind, singleton, body, space)
    }

    fun bodyGetSpace(body: RID): RID {
        return ObjectCalls.ptrcallWithRIDArgRetRID(bodyGetSpaceBind, singleton, body)
    }

    fun bodySetMode(body: RID, mode: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(bodySetModeBind, singleton, body, mode)
    }

    fun bodyGetMode(body: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(bodyGetModeBind, singleton, body)
    }

    fun bodySetCollisionLayer(body: RID, layer: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(bodySetCollisionLayerBind, singleton, body, layer)
    }

    fun bodyGetCollisionLayer(body: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetUInt32(bodyGetCollisionLayerBind, singleton, body)
    }

    fun bodySetCollisionMask(body: RID, mask: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(bodySetCollisionMaskBind, singleton, body, mask)
    }

    fun bodyGetCollisionMask(body: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetUInt32(bodyGetCollisionMaskBind, singleton, body)
    }

    fun bodySetCollisionPriority(body: RID, priority: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(bodySetCollisionPriorityBind, singleton, body, priority)
    }

    fun bodyGetCollisionPriority(body: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(bodyGetCollisionPriorityBind, singleton, body)
    }

    fun bodyAddShape(body: RID, shape: RID, transform: Transform3D, disabled: Boolean = false) {
        ObjectCalls.ptrcallWithTwoRIDTransform3DBoolArgs(bodyAddShapeBind, singleton, body, shape, transform, disabled)
    }

    fun bodySetShape(body: RID, shapeIdx: Int, shape: RID) {
        ObjectCalls.ptrcallWithRIDIntAndRIDArgs(bodySetShapeBind, singleton, body, shapeIdx, shape)
    }

    fun bodySetShapeTransform(body: RID, shapeIdx: Int, transform: Transform3D) {
        ObjectCalls.ptrcallWithRIDIntAndTransform3DArg(bodySetShapeTransformBind, singleton, body, shapeIdx, transform)
    }

    fun bodySetShapeDisabled(body: RID, shapeIdx: Int, disabled: Boolean) {
        ObjectCalls.ptrcallWithRIDIntAndBoolArgs(bodySetShapeDisabledBind, singleton, body, shapeIdx, disabled)
    }

    fun bodyGetShapeCount(body: RID): Int {
        return ObjectCalls.ptrcallWithRIDArgRetInt(bodyGetShapeCountBind, singleton, body)
    }

    fun bodyGetShape(body: RID, shapeIdx: Int): RID {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetRID(bodyGetShapeBind, singleton, body, shapeIdx)
    }

    fun bodyGetShapeTransform(body: RID, shapeIdx: Int): Transform3D {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetTransform3D(bodyGetShapeTransformBind, singleton, body, shapeIdx)
    }

    fun bodyRemoveShape(body: RID, shapeIdx: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(bodyRemoveShapeBind, singleton, body, shapeIdx)
    }

    fun bodyClearShapes(body: RID) {
        ObjectCalls.ptrcallWithRIDArg(bodyClearShapesBind, singleton, body)
    }

    fun bodyAttachObjectInstanceId(body: RID, id: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(bodyAttachObjectInstanceIdBind, singleton, body, id)
    }

    fun bodyGetObjectInstanceId(body: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(bodyGetObjectInstanceIdBind, singleton, body)
    }

    fun bodySetEnableContinuousCollisionDetection(body: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(bodySetEnableContinuousCollisionDetectionBind, singleton, body, enable)
    }

    fun bodyIsContinuousCollisionDetectionEnabled(body: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(bodyIsContinuousCollisionDetectionEnabledBind, singleton, body)
    }

    fun bodyResetMassProperties(body: RID) {
        ObjectCalls.ptrcallWithRIDArg(bodyResetMassPropertiesBind, singleton, body)
    }

    fun bodyApplyCentralImpulse(body: RID, impulse: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(bodyApplyCentralImpulseBind, singleton, body, impulse)
    }

    fun bodyApplyImpulse(body: RID, impulse: Vector3, position: Vector3) {
        ObjectCalls.ptrcallWithRIDAndTwoVector3Args(bodyApplyImpulseBind, singleton, body, impulse, position)
    }

    fun bodyApplyTorqueImpulse(body: RID, impulse: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(bodyApplyTorqueImpulseBind, singleton, body, impulse)
    }

    fun bodyApplyCentralForce(body: RID, force: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(bodyApplyCentralForceBind, singleton, body, force)
    }

    fun bodyApplyForce(body: RID, force: Vector3, position: Vector3) {
        ObjectCalls.ptrcallWithRIDAndTwoVector3Args(bodyApplyForceBind, singleton, body, force, position)
    }

    fun bodyApplyTorque(body: RID, torque: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(bodyApplyTorqueBind, singleton, body, torque)
    }

    fun bodyAddConstantCentralForce(body: RID, force: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(bodyAddConstantCentralForceBind, singleton, body, force)
    }

    fun bodyAddConstantForce(body: RID, force: Vector3, position: Vector3) {
        ObjectCalls.ptrcallWithRIDAndTwoVector3Args(bodyAddConstantForceBind, singleton, body, force, position)
    }

    fun bodyAddConstantTorque(body: RID, torque: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(bodyAddConstantTorqueBind, singleton, body, torque)
    }

    fun bodySetConstantForce(body: RID, force: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(bodySetConstantForceBind, singleton, body, force)
    }

    fun bodyGetConstantForce(body: RID): Vector3 {
        return ObjectCalls.ptrcallWithRIDArgRetVector3(bodyGetConstantForceBind, singleton, body)
    }

    fun bodySetConstantTorque(body: RID, torque: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(bodySetConstantTorqueBind, singleton, body, torque)
    }

    fun bodyGetConstantTorque(body: RID): Vector3 {
        return ObjectCalls.ptrcallWithRIDArgRetVector3(bodyGetConstantTorqueBind, singleton, body)
    }

    fun bodySetAxisVelocity(body: RID, axisVelocity: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(bodySetAxisVelocityBind, singleton, body, axisVelocity)
    }

    fun bodySetAxisLock(body: RID, axis: Long, lock: Boolean) {
        ObjectCalls.ptrcallWithRIDLongAndBoolArgs(bodySetAxisLockBind, singleton, body, axis, lock)
    }

    fun bodyIsAxisLocked(body: RID, axis: Long): Boolean {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetBool(bodyIsAxisLockedBind, singleton, body, axis)
    }

    fun bodyAddCollisionException(body: RID, exceptedBody: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(bodyAddCollisionExceptionBind, singleton, body, exceptedBody)
    }

    fun bodyRemoveCollisionException(body: RID, exceptedBody: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(bodyRemoveCollisionExceptionBind, singleton, body, exceptedBody)
    }

    fun bodySetMaxContactsReported(body: RID, amount: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(bodySetMaxContactsReportedBind, singleton, body, amount)
    }

    fun bodyGetMaxContactsReported(body: RID): Int {
        return ObjectCalls.ptrcallWithRIDArgRetInt(bodyGetMaxContactsReportedBind, singleton, body)
    }

    fun bodySetOmitForceIntegration(body: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(bodySetOmitForceIntegrationBind, singleton, body, enable)
    }

    fun bodyIsOmittingForceIntegration(body: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(bodyIsOmittingForceIntegrationBind, singleton, body)
    }

    fun bodySetStateSyncCallback(body: RID, callable: GodotCallable) {
        ObjectCalls.ptrcallWithRIDCallableArgs(bodySetStateSyncCallbackBind, singleton, body, callable.target.handle, callable.method)
    }

    fun bodySetRayPickable(body: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(bodySetRayPickableBind, singleton, body, enable)
    }

    fun bodyTestMotion(body: RID, parameters: PhysicsTestMotionParameters3D, result: PhysicsTestMotionResult3D?): Boolean {
        return ObjectCalls.ptrcallWithRIDAndTwoObjectArgsRetBool(bodyTestMotionBind, singleton, body, parameters.requireOpenHandle(), result?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun bodyGetDirectState(body: RID): PhysicsDirectBodyState3D? {
        return PhysicsDirectBodyState3D.wrap(ObjectCalls.ptrcallWithRIDArgRetObject(bodyGetDirectStateBind, singleton, body))
    }

    fun softBodyCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(softBodyCreateBind, singleton)
    }

    fun softBodyUpdateRenderingServer(body: RID, renderingServerHandler: PhysicsServer3DRenderingServerHandler) {
        ObjectCalls.ptrcallWithRIDAndObjectArg(softBodyUpdateRenderingServerBind, singleton, body, renderingServerHandler.handle)
    }

    fun softBodySetSpace(body: RID, space: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(softBodySetSpaceBind, singleton, body, space)
    }

    fun softBodyGetSpace(body: RID): RID {
        return ObjectCalls.ptrcallWithRIDArgRetRID(softBodyGetSpaceBind, singleton, body)
    }

    fun softBodySetMesh(body: RID, mesh: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(softBodySetMeshBind, singleton, body, mesh)
    }

    fun softBodyGetBounds(body: RID): AABB {
        return ObjectCalls.ptrcallWithRIDArgRetAABB(softBodyGetBoundsBind, singleton, body)
    }

    fun softBodySetCollisionLayer(body: RID, layer: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(softBodySetCollisionLayerBind, singleton, body, layer)
    }

    fun softBodyGetCollisionLayer(body: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetUInt32(softBodyGetCollisionLayerBind, singleton, body)
    }

    fun softBodySetCollisionMask(body: RID, mask: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(softBodySetCollisionMaskBind, singleton, body, mask)
    }

    fun softBodyGetCollisionMask(body: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetUInt32(softBodyGetCollisionMaskBind, singleton, body)
    }

    fun softBodyAddCollisionException(body: RID, bodyB: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(softBodyAddCollisionExceptionBind, singleton, body, bodyB)
    }

    fun softBodyRemoveCollisionException(body: RID, bodyB: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(softBodyRemoveCollisionExceptionBind, singleton, body, bodyB)
    }

    fun softBodySetTransform(body: RID, transform: Transform3D) {
        ObjectCalls.ptrcallWithRIDAndTransform3DArg(softBodySetTransformBind, singleton, body, transform)
    }

    fun softBodySetRayPickable(body: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(softBodySetRayPickableBind, singleton, body, enable)
    }

    fun softBodySetSimulationPrecision(body: RID, simulationPrecision: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(softBodySetSimulationPrecisionBind, singleton, body, simulationPrecision)
    }

    fun softBodyGetSimulationPrecision(body: RID): Int {
        return ObjectCalls.ptrcallWithRIDArgRetInt(softBodyGetSimulationPrecisionBind, singleton, body)
    }

    fun softBodySetTotalMass(body: RID, totalMass: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(softBodySetTotalMassBind, singleton, body, totalMass)
    }

    fun softBodyGetTotalMass(body: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(softBodyGetTotalMassBind, singleton, body)
    }

    fun softBodySetLinearStiffness(body: RID, stiffness: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(softBodySetLinearStiffnessBind, singleton, body, stiffness)
    }

    fun softBodyGetLinearStiffness(body: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(softBodyGetLinearStiffnessBind, singleton, body)
    }

    fun softBodySetShrinkingFactor(body: RID, shrinkingFactor: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(softBodySetShrinkingFactorBind, singleton, body, shrinkingFactor)
    }

    fun softBodyGetShrinkingFactor(body: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(softBodyGetShrinkingFactorBind, singleton, body)
    }

    fun softBodySetPressureCoefficient(body: RID, pressureCoefficient: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(softBodySetPressureCoefficientBind, singleton, body, pressureCoefficient)
    }

    fun softBodyGetPressureCoefficient(body: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(softBodyGetPressureCoefficientBind, singleton, body)
    }

    fun softBodySetDampingCoefficient(body: RID, dampingCoefficient: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(softBodySetDampingCoefficientBind, singleton, body, dampingCoefficient)
    }

    fun softBodyGetDampingCoefficient(body: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(softBodyGetDampingCoefficientBind, singleton, body)
    }

    fun softBodySetDragCoefficient(body: RID, dragCoefficient: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(softBodySetDragCoefficientBind, singleton, body, dragCoefficient)
    }

    fun softBodyGetDragCoefficient(body: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(softBodyGetDragCoefficientBind, singleton, body)
    }

    fun softBodyMovePoint(body: RID, pointIndex: Int, globalPosition: Vector3) {
        ObjectCalls.ptrcallWithRIDIntAndVector3Arg(softBodyMovePointBind, singleton, body, pointIndex, globalPosition)
    }

    fun softBodyGetPointGlobalPosition(body: RID, pointIndex: Int): Vector3 {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetVector3(softBodyGetPointGlobalPositionBind, singleton, body, pointIndex)
    }

    fun softBodyRemoveAllPinnedPoints(body: RID) {
        ObjectCalls.ptrcallWithRIDArg(softBodyRemoveAllPinnedPointsBind, singleton, body)
    }

    fun softBodyPinPoint(body: RID, pointIndex: Int, pin: Boolean) {
        ObjectCalls.ptrcallWithRIDIntAndBoolArgs(softBodyPinPointBind, singleton, body, pointIndex, pin)
    }

    fun softBodyIsPointPinned(body: RID, pointIndex: Int): Boolean {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetBool(softBodyIsPointPinnedBind, singleton, body, pointIndex)
    }

    fun softBodyApplyPointImpulse(body: RID, pointIndex: Int, impulse: Vector3) {
        ObjectCalls.ptrcallWithRIDIntAndVector3Arg(softBodyApplyPointImpulseBind, singleton, body, pointIndex, impulse)
    }

    fun softBodyApplyPointForce(body: RID, pointIndex: Int, force: Vector3) {
        ObjectCalls.ptrcallWithRIDIntAndVector3Arg(softBodyApplyPointForceBind, singleton, body, pointIndex, force)
    }

    fun softBodyApplyCentralImpulse(body: RID, impulse: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(softBodyApplyCentralImpulseBind, singleton, body, impulse)
    }

    fun softBodyApplyCentralForce(body: RID, force: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(softBodyApplyCentralForceBind, singleton, body, force)
    }

    fun jointCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(jointCreateBind, singleton)
    }

    fun jointClear(joint: RID) {
        ObjectCalls.ptrcallWithRIDArg(jointClearBind, singleton, joint)
    }

    fun jointMakePin(joint: RID, bodyA: RID, localA: Vector3, bodyB: RID, localB: Vector3) {
        ObjectCalls.ptrcallWithTwoRIDVector3RIDVector3Args(jointMakePinBind, singleton, joint, bodyA, localA, bodyB, localB)
    }

    fun pinJointSetParam(joint: RID, param: Long, value: Double) {
        ObjectCalls.ptrcallWithRIDLongAndDoubleArgs(pinJointSetParamBind, singleton, joint, param, value)
    }

    fun pinJointGetParam(joint: RID, param: Long): Double {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetDouble(pinJointGetParamBind, singleton, joint, param)
    }

    fun pinJointSetLocalA(joint: RID, localA: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(pinJointSetLocalABind, singleton, joint, localA)
    }

    fun pinJointGetLocalA(joint: RID): Vector3 {
        return ObjectCalls.ptrcallWithRIDArgRetVector3(pinJointGetLocalABind, singleton, joint)
    }

    fun pinJointSetLocalB(joint: RID, localB: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(pinJointSetLocalBBind, singleton, joint, localB)
    }

    fun pinJointGetLocalB(joint: RID): Vector3 {
        return ObjectCalls.ptrcallWithRIDArgRetVector3(pinJointGetLocalBBind, singleton, joint)
    }

    fun jointMakeHinge(joint: RID, bodyA: RID, hingeA: Transform3D, bodyB: RID, hingeB: Transform3D) {
        ObjectCalls.ptrcallWithRIDRIDTransform3DRIDTransform3DArgs(jointMakeHingeBind, singleton, joint, bodyA, hingeA, bodyB, hingeB)
    }

    fun hingeJointSetParam(joint: RID, param: Long, value: Double) {
        ObjectCalls.ptrcallWithRIDLongAndDoubleArgs(hingeJointSetParamBind, singleton, joint, param, value)
    }

    fun hingeJointGetParam(joint: RID, param: Long): Double {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetDouble(hingeJointGetParamBind, singleton, joint, param)
    }

    fun hingeJointSetFlag(joint: RID, flag: Long, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDLongAndBoolArgs(hingeJointSetFlagBind, singleton, joint, flag, enabled)
    }

    fun hingeJointGetFlag(joint: RID, flag: Long): Boolean {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetBool(hingeJointGetFlagBind, singleton, joint, flag)
    }

    fun jointMakeSlider(joint: RID, bodyA: RID, localRefA: Transform3D, bodyB: RID, localRefB: Transform3D) {
        ObjectCalls.ptrcallWithRIDRIDTransform3DRIDTransform3DArgs(jointMakeSliderBind, singleton, joint, bodyA, localRefA, bodyB, localRefB)
    }

    fun sliderJointSetParam(joint: RID, param: Long, value: Double) {
        ObjectCalls.ptrcallWithRIDLongAndDoubleArgs(sliderJointSetParamBind, singleton, joint, param, value)
    }

    fun sliderJointGetParam(joint: RID, param: Long): Double {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetDouble(sliderJointGetParamBind, singleton, joint, param)
    }

    fun jointMakeConeTwist(joint: RID, bodyA: RID, localRefA: Transform3D, bodyB: RID, localRefB: Transform3D) {
        ObjectCalls.ptrcallWithRIDRIDTransform3DRIDTransform3DArgs(jointMakeConeTwistBind, singleton, joint, bodyA, localRefA, bodyB, localRefB)
    }

    fun coneTwistJointSetParam(joint: RID, param: Long, value: Double) {
        ObjectCalls.ptrcallWithRIDLongAndDoubleArgs(coneTwistJointSetParamBind, singleton, joint, param, value)
    }

    fun coneTwistJointGetParam(joint: RID, param: Long): Double {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetDouble(coneTwistJointGetParamBind, singleton, joint, param)
    }

    fun jointGetType(joint: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(jointGetTypeBind, singleton, joint)
    }

    fun jointSetSolverPriority(joint: RID, priority: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(jointSetSolverPriorityBind, singleton, joint, priority)
    }

    fun jointGetSolverPriority(joint: RID): Int {
        return ObjectCalls.ptrcallWithRIDArgRetInt(jointGetSolverPriorityBind, singleton, joint)
    }

    fun jointDisableCollisionsBetweenBodies(joint: RID, disable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(jointDisableCollisionsBetweenBodiesBind, singleton, joint, disable)
    }

    fun jointIsDisabledCollisionsBetweenBodies(joint: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(jointIsDisabledCollisionsBetweenBodiesBind, singleton, joint)
    }

    fun jointMakeGeneric6dof(joint: RID, bodyA: RID, localRefA: Transform3D, bodyB: RID, localRefB: Transform3D) {
        ObjectCalls.ptrcallWithRIDRIDTransform3DRIDTransform3DArgs(jointMakeGeneric6dofBind, singleton, joint, bodyA, localRefA, bodyB, localRefB)
    }

    fun generic6dofJointSetParam(joint: RID, axis: Long, param: Long, value: Double) {
        ObjectCalls.ptrcallWithRIDTwoLongDoubleArgs(generic6dofJointSetParamBind, singleton, joint, axis, param, value)
    }

    fun generic6dofJointGetParam(joint: RID, axis: Long, param: Long): Double {
        return ObjectCalls.ptrcallWithRIDTwoLongArgsRetDouble(generic6dofJointGetParamBind, singleton, joint, axis, param)
    }

    fun generic6dofJointSetFlag(joint: RID, axis: Long, flag: Long, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDTwoLongBoolArgs(generic6dofJointSetFlagBind, singleton, joint, axis, flag, enable)
    }

    fun generic6dofJointGetFlag(joint: RID, axis: Long, flag: Long): Boolean {
        return ObjectCalls.ptrcallWithRIDTwoLongArgsRetBool(generic6dofJointGetFlagBind, singleton, joint, axis, flag)
    }

    fun freeRid(rid: RID) {
        ObjectCalls.ptrcallWithRIDArg(freeRidBind, singleton, rid)
    }

    fun setActive(active: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setActiveBind, singleton, active)
    }

    fun getProcessInfo(processInfo: Long): Int {
        return ObjectCalls.ptrcallWithLongArgRetInt(getProcessInfoBind, singleton, processInfo)
    }

    fun fromHandle(handle: MemorySegment): PhysicsServer3D? =
        wrap(handle)

    internal fun wrap(handle: MemorySegment): PhysicsServer3D? =
        if (handle.address() == 0L) null else this

    private const val WORLD_BOUNDARY_SHAPE_CREATE_HASH = 529393457L
    private val worldBoundaryShapeCreateBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "world_boundary_shape_create", WORLD_BOUNDARY_SHAPE_CREATE_HASH)
    }

    private const val SEPARATION_RAY_SHAPE_CREATE_HASH = 529393457L
    private val separationRayShapeCreateBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "separation_ray_shape_create", SEPARATION_RAY_SHAPE_CREATE_HASH)
    }

    private const val SPHERE_SHAPE_CREATE_HASH = 529393457L
    private val sphereShapeCreateBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "sphere_shape_create", SPHERE_SHAPE_CREATE_HASH)
    }

    private const val BOX_SHAPE_CREATE_HASH = 529393457L
    private val boxShapeCreateBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "box_shape_create", BOX_SHAPE_CREATE_HASH)
    }

    private const val CAPSULE_SHAPE_CREATE_HASH = 529393457L
    private val capsuleShapeCreateBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "capsule_shape_create", CAPSULE_SHAPE_CREATE_HASH)
    }

    private const val CYLINDER_SHAPE_CREATE_HASH = 529393457L
    private val cylinderShapeCreateBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "cylinder_shape_create", CYLINDER_SHAPE_CREATE_HASH)
    }

    private const val CONVEX_POLYGON_SHAPE_CREATE_HASH = 529393457L
    private val convexPolygonShapeCreateBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "convex_polygon_shape_create", CONVEX_POLYGON_SHAPE_CREATE_HASH)
    }

    private const val CONCAVE_POLYGON_SHAPE_CREATE_HASH = 529393457L
    private val concavePolygonShapeCreateBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "concave_polygon_shape_create", CONCAVE_POLYGON_SHAPE_CREATE_HASH)
    }

    private const val HEIGHTMAP_SHAPE_CREATE_HASH = 529393457L
    private val heightmapShapeCreateBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "heightmap_shape_create", HEIGHTMAP_SHAPE_CREATE_HASH)
    }

    private const val CUSTOM_SHAPE_CREATE_HASH = 529393457L
    private val customShapeCreateBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "custom_shape_create", CUSTOM_SHAPE_CREATE_HASH)
    }

    private const val SHAPE_SET_MARGIN_HASH = 1794382983L
    private val shapeSetMarginBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "shape_set_margin", SHAPE_SET_MARGIN_HASH)
    }

    private const val SHAPE_GET_TYPE_HASH = 3418923367L
    private val shapeGetTypeBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "shape_get_type", SHAPE_GET_TYPE_HASH)
    }

    private const val SHAPE_GET_MARGIN_HASH = 866169185L
    private val shapeGetMarginBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "shape_get_margin", SHAPE_GET_MARGIN_HASH)
    }

    private const val SPACE_CREATE_HASH = 529393457L
    private val spaceCreateBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "space_create", SPACE_CREATE_HASH)
    }

    private const val SPACE_SET_ACTIVE_HASH = 1265174801L
    private val spaceSetActiveBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "space_set_active", SPACE_SET_ACTIVE_HASH)
    }

    private const val SPACE_IS_ACTIVE_HASH = 4155700596L
    private val spaceIsActiveBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "space_is_active", SPACE_IS_ACTIVE_HASH)
    }

    private const val SPACE_SET_PARAM_HASH = 2406017470L
    private val spaceSetParamBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "space_set_param", SPACE_SET_PARAM_HASH)
    }

    private const val SPACE_GET_PARAM_HASH = 1523206731L
    private val spaceGetParamBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "space_get_param", SPACE_GET_PARAM_HASH)
    }

    private const val SPACE_GET_DIRECT_STATE_HASH = 2048616813L
    private val spaceGetDirectStateBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "space_get_direct_state", SPACE_GET_DIRECT_STATE_HASH)
    }

    private const val AREA_CREATE_HASH = 529393457L
    private val areaCreateBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "area_create", AREA_CREATE_HASH)
    }

    private const val AREA_SET_SPACE_HASH = 395945892L
    private val areaSetSpaceBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "area_set_space", AREA_SET_SPACE_HASH)
    }

    private const val AREA_GET_SPACE_HASH = 3814569979L
    private val areaGetSpaceBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "area_get_space", AREA_GET_SPACE_HASH)
    }

    private const val AREA_ADD_SHAPE_HASH = 3711419014L
    private val areaAddShapeBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "area_add_shape", AREA_ADD_SHAPE_HASH)
    }

    private const val AREA_SET_SHAPE_HASH = 2310537182L
    private val areaSetShapeBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "area_set_shape", AREA_SET_SHAPE_HASH)
    }

    private const val AREA_SET_SHAPE_TRANSFORM_HASH = 675327471L
    private val areaSetShapeTransformBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "area_set_shape_transform", AREA_SET_SHAPE_TRANSFORM_HASH)
    }

    private const val AREA_SET_SHAPE_DISABLED_HASH = 2658558584L
    private val areaSetShapeDisabledBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "area_set_shape_disabled", AREA_SET_SHAPE_DISABLED_HASH)
    }

    private const val AREA_GET_SHAPE_COUNT_HASH = 2198884583L
    private val areaGetShapeCountBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "area_get_shape_count", AREA_GET_SHAPE_COUNT_HASH)
    }

    private const val AREA_GET_SHAPE_HASH = 1066463050L
    private val areaGetShapeBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "area_get_shape", AREA_GET_SHAPE_HASH)
    }

    private const val AREA_GET_SHAPE_TRANSFORM_HASH = 1050775521L
    private val areaGetShapeTransformBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "area_get_shape_transform", AREA_GET_SHAPE_TRANSFORM_HASH)
    }

    private const val AREA_REMOVE_SHAPE_HASH = 3411492887L
    private val areaRemoveShapeBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "area_remove_shape", AREA_REMOVE_SHAPE_HASH)
    }

    private const val AREA_CLEAR_SHAPES_HASH = 2722037293L
    private val areaClearShapesBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "area_clear_shapes", AREA_CLEAR_SHAPES_HASH)
    }

    private const val AREA_SET_COLLISION_LAYER_HASH = 3411492887L
    private val areaSetCollisionLayerBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "area_set_collision_layer", AREA_SET_COLLISION_LAYER_HASH)
    }

    private const val AREA_GET_COLLISION_LAYER_HASH = 2198884583L
    private val areaGetCollisionLayerBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "area_get_collision_layer", AREA_GET_COLLISION_LAYER_HASH)
    }

    private const val AREA_SET_COLLISION_MASK_HASH = 3411492887L
    private val areaSetCollisionMaskBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "area_set_collision_mask", AREA_SET_COLLISION_MASK_HASH)
    }

    private const val AREA_GET_COLLISION_MASK_HASH = 2198884583L
    private val areaGetCollisionMaskBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "area_get_collision_mask", AREA_GET_COLLISION_MASK_HASH)
    }

    private const val AREA_SET_TRANSFORM_HASH = 3935195649L
    private val areaSetTransformBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "area_set_transform", AREA_SET_TRANSFORM_HASH)
    }

    private const val AREA_GET_TRANSFORM_HASH = 1128465797L
    private val areaGetTransformBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "area_get_transform", AREA_GET_TRANSFORM_HASH)
    }

    private const val AREA_ATTACH_OBJECT_INSTANCE_ID_HASH = 3411492887L
    private val areaAttachObjectInstanceIdBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "area_attach_object_instance_id", AREA_ATTACH_OBJECT_INSTANCE_ID_HASH)
    }

    private const val AREA_GET_OBJECT_INSTANCE_ID_HASH = 2198884583L
    private val areaGetObjectInstanceIdBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "area_get_object_instance_id", AREA_GET_OBJECT_INSTANCE_ID_HASH)
    }

    private const val AREA_SET_MONITOR_CALLBACK_HASH = 3379118538L
    private val areaSetMonitorCallbackBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "area_set_monitor_callback", AREA_SET_MONITOR_CALLBACK_HASH)
    }

    private const val AREA_SET_AREA_MONITOR_CALLBACK_HASH = 3379118538L
    private val areaSetAreaMonitorCallbackBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "area_set_area_monitor_callback", AREA_SET_AREA_MONITOR_CALLBACK_HASH)
    }

    private const val AREA_SET_MONITORABLE_HASH = 1265174801L
    private val areaSetMonitorableBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "area_set_monitorable", AREA_SET_MONITORABLE_HASH)
    }

    private const val AREA_SET_RAY_PICKABLE_HASH = 1265174801L
    private val areaSetRayPickableBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "area_set_ray_pickable", AREA_SET_RAY_PICKABLE_HASH)
    }

    private const val BODY_CREATE_HASH = 529393457L
    private val bodyCreateBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_create", BODY_CREATE_HASH)
    }

    private const val BODY_SET_SPACE_HASH = 395945892L
    private val bodySetSpaceBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_set_space", BODY_SET_SPACE_HASH)
    }

    private const val BODY_GET_SPACE_HASH = 3814569979L
    private val bodyGetSpaceBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_get_space", BODY_GET_SPACE_HASH)
    }

    private const val BODY_SET_MODE_HASH = 606803466L
    private val bodySetModeBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_set_mode", BODY_SET_MODE_HASH)
    }

    private const val BODY_GET_MODE_HASH = 2488819728L
    private val bodyGetModeBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_get_mode", BODY_GET_MODE_HASH)
    }

    private const val BODY_SET_COLLISION_LAYER_HASH = 3411492887L
    private val bodySetCollisionLayerBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_set_collision_layer", BODY_SET_COLLISION_LAYER_HASH)
    }

    private const val BODY_GET_COLLISION_LAYER_HASH = 2198884583L
    private val bodyGetCollisionLayerBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_get_collision_layer", BODY_GET_COLLISION_LAYER_HASH)
    }

    private const val BODY_SET_COLLISION_MASK_HASH = 3411492887L
    private val bodySetCollisionMaskBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_set_collision_mask", BODY_SET_COLLISION_MASK_HASH)
    }

    private const val BODY_GET_COLLISION_MASK_HASH = 2198884583L
    private val bodyGetCollisionMaskBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_get_collision_mask", BODY_GET_COLLISION_MASK_HASH)
    }

    private const val BODY_SET_COLLISION_PRIORITY_HASH = 1794382983L
    private val bodySetCollisionPriorityBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_set_collision_priority", BODY_SET_COLLISION_PRIORITY_HASH)
    }

    private const val BODY_GET_COLLISION_PRIORITY_HASH = 866169185L
    private val bodyGetCollisionPriorityBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_get_collision_priority", BODY_GET_COLLISION_PRIORITY_HASH)
    }

    private const val BODY_ADD_SHAPE_HASH = 3711419014L
    private val bodyAddShapeBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_add_shape", BODY_ADD_SHAPE_HASH)
    }

    private const val BODY_SET_SHAPE_HASH = 2310537182L
    private val bodySetShapeBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_set_shape", BODY_SET_SHAPE_HASH)
    }

    private const val BODY_SET_SHAPE_TRANSFORM_HASH = 675327471L
    private val bodySetShapeTransformBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_set_shape_transform", BODY_SET_SHAPE_TRANSFORM_HASH)
    }

    private const val BODY_SET_SHAPE_DISABLED_HASH = 2658558584L
    private val bodySetShapeDisabledBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_set_shape_disabled", BODY_SET_SHAPE_DISABLED_HASH)
    }

    private const val BODY_GET_SHAPE_COUNT_HASH = 2198884583L
    private val bodyGetShapeCountBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_get_shape_count", BODY_GET_SHAPE_COUNT_HASH)
    }

    private const val BODY_GET_SHAPE_HASH = 1066463050L
    private val bodyGetShapeBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_get_shape", BODY_GET_SHAPE_HASH)
    }

    private const val BODY_GET_SHAPE_TRANSFORM_HASH = 1050775521L
    private val bodyGetShapeTransformBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_get_shape_transform", BODY_GET_SHAPE_TRANSFORM_HASH)
    }

    private const val BODY_REMOVE_SHAPE_HASH = 3411492887L
    private val bodyRemoveShapeBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_remove_shape", BODY_REMOVE_SHAPE_HASH)
    }

    private const val BODY_CLEAR_SHAPES_HASH = 2722037293L
    private val bodyClearShapesBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_clear_shapes", BODY_CLEAR_SHAPES_HASH)
    }

    private const val BODY_ATTACH_OBJECT_INSTANCE_ID_HASH = 3411492887L
    private val bodyAttachObjectInstanceIdBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_attach_object_instance_id", BODY_ATTACH_OBJECT_INSTANCE_ID_HASH)
    }

    private const val BODY_GET_OBJECT_INSTANCE_ID_HASH = 2198884583L
    private val bodyGetObjectInstanceIdBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_get_object_instance_id", BODY_GET_OBJECT_INSTANCE_ID_HASH)
    }

    private const val BODY_SET_ENABLE_CONTINUOUS_COLLISION_DETECTION_HASH = 1265174801L
    private val bodySetEnableContinuousCollisionDetectionBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_set_enable_continuous_collision_detection", BODY_SET_ENABLE_CONTINUOUS_COLLISION_DETECTION_HASH)
    }

    private const val BODY_IS_CONTINUOUS_COLLISION_DETECTION_ENABLED_HASH = 4155700596L
    private val bodyIsContinuousCollisionDetectionEnabledBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_is_continuous_collision_detection_enabled", BODY_IS_CONTINUOUS_COLLISION_DETECTION_ENABLED_HASH)
    }

    private const val BODY_RESET_MASS_PROPERTIES_HASH = 2722037293L
    private val bodyResetMassPropertiesBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_reset_mass_properties", BODY_RESET_MASS_PROPERTIES_HASH)
    }

    private const val BODY_APPLY_CENTRAL_IMPULSE_HASH = 3227306858L
    private val bodyApplyCentralImpulseBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_apply_central_impulse", BODY_APPLY_CENTRAL_IMPULSE_HASH)
    }

    private const val BODY_APPLY_IMPULSE_HASH = 390416203L
    private val bodyApplyImpulseBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_apply_impulse", BODY_APPLY_IMPULSE_HASH)
    }

    private const val BODY_APPLY_TORQUE_IMPULSE_HASH = 3227306858L
    private val bodyApplyTorqueImpulseBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_apply_torque_impulse", BODY_APPLY_TORQUE_IMPULSE_HASH)
    }

    private const val BODY_APPLY_CENTRAL_FORCE_HASH = 3227306858L
    private val bodyApplyCentralForceBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_apply_central_force", BODY_APPLY_CENTRAL_FORCE_HASH)
    }

    private const val BODY_APPLY_FORCE_HASH = 390416203L
    private val bodyApplyForceBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_apply_force", BODY_APPLY_FORCE_HASH)
    }

    private const val BODY_APPLY_TORQUE_HASH = 3227306858L
    private val bodyApplyTorqueBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_apply_torque", BODY_APPLY_TORQUE_HASH)
    }

    private const val BODY_ADD_CONSTANT_CENTRAL_FORCE_HASH = 3227306858L
    private val bodyAddConstantCentralForceBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_add_constant_central_force", BODY_ADD_CONSTANT_CENTRAL_FORCE_HASH)
    }

    private const val BODY_ADD_CONSTANT_FORCE_HASH = 390416203L
    private val bodyAddConstantForceBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_add_constant_force", BODY_ADD_CONSTANT_FORCE_HASH)
    }

    private const val BODY_ADD_CONSTANT_TORQUE_HASH = 3227306858L
    private val bodyAddConstantTorqueBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_add_constant_torque", BODY_ADD_CONSTANT_TORQUE_HASH)
    }

    private const val BODY_SET_CONSTANT_FORCE_HASH = 3227306858L
    private val bodySetConstantForceBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_set_constant_force", BODY_SET_CONSTANT_FORCE_HASH)
    }

    private const val BODY_GET_CONSTANT_FORCE_HASH = 531438156L
    private val bodyGetConstantForceBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_get_constant_force", BODY_GET_CONSTANT_FORCE_HASH)
    }

    private const val BODY_SET_CONSTANT_TORQUE_HASH = 3227306858L
    private val bodySetConstantTorqueBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_set_constant_torque", BODY_SET_CONSTANT_TORQUE_HASH)
    }

    private const val BODY_GET_CONSTANT_TORQUE_HASH = 531438156L
    private val bodyGetConstantTorqueBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_get_constant_torque", BODY_GET_CONSTANT_TORQUE_HASH)
    }

    private const val BODY_SET_AXIS_VELOCITY_HASH = 3227306858L
    private val bodySetAxisVelocityBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_set_axis_velocity", BODY_SET_AXIS_VELOCITY_HASH)
    }

    private const val BODY_SET_AXIS_LOCK_HASH = 2020836892L
    private val bodySetAxisLockBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_set_axis_lock", BODY_SET_AXIS_LOCK_HASH)
    }

    private const val BODY_IS_AXIS_LOCKED_HASH = 587853580L
    private val bodyIsAxisLockedBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_is_axis_locked", BODY_IS_AXIS_LOCKED_HASH)
    }

    private const val BODY_ADD_COLLISION_EXCEPTION_HASH = 395945892L
    private val bodyAddCollisionExceptionBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_add_collision_exception", BODY_ADD_COLLISION_EXCEPTION_HASH)
    }

    private const val BODY_REMOVE_COLLISION_EXCEPTION_HASH = 395945892L
    private val bodyRemoveCollisionExceptionBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_remove_collision_exception", BODY_REMOVE_COLLISION_EXCEPTION_HASH)
    }

    private const val BODY_SET_MAX_CONTACTS_REPORTED_HASH = 3411492887L
    private val bodySetMaxContactsReportedBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_set_max_contacts_reported", BODY_SET_MAX_CONTACTS_REPORTED_HASH)
    }

    private const val BODY_GET_MAX_CONTACTS_REPORTED_HASH = 2198884583L
    private val bodyGetMaxContactsReportedBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_get_max_contacts_reported", BODY_GET_MAX_CONTACTS_REPORTED_HASH)
    }

    private const val BODY_SET_OMIT_FORCE_INTEGRATION_HASH = 1265174801L
    private val bodySetOmitForceIntegrationBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_set_omit_force_integration", BODY_SET_OMIT_FORCE_INTEGRATION_HASH)
    }

    private const val BODY_IS_OMITTING_FORCE_INTEGRATION_HASH = 4155700596L
    private val bodyIsOmittingForceIntegrationBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_is_omitting_force_integration", BODY_IS_OMITTING_FORCE_INTEGRATION_HASH)
    }

    private const val BODY_SET_STATE_SYNC_CALLBACK_HASH = 3379118538L
    private val bodySetStateSyncCallbackBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_set_state_sync_callback", BODY_SET_STATE_SYNC_CALLBACK_HASH)
    }

    private const val BODY_SET_RAY_PICKABLE_HASH = 1265174801L
    private val bodySetRayPickableBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_set_ray_pickable", BODY_SET_RAY_PICKABLE_HASH)
    }

    private const val BODY_TEST_MOTION_HASH = 1944921792L
    private val bodyTestMotionBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_test_motion", BODY_TEST_MOTION_HASH)
    }

    private const val BODY_GET_DIRECT_STATE_HASH = 3029727957L
    private val bodyGetDirectStateBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_get_direct_state", BODY_GET_DIRECT_STATE_HASH)
    }

    private const val SOFT_BODY_CREATE_HASH = 529393457L
    private val softBodyCreateBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "soft_body_create", SOFT_BODY_CREATE_HASH)
    }

    private const val SOFT_BODY_UPDATE_RENDERING_SERVER_HASH = 2218179753L
    private val softBodyUpdateRenderingServerBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "soft_body_update_rendering_server", SOFT_BODY_UPDATE_RENDERING_SERVER_HASH)
    }

    private const val SOFT_BODY_SET_SPACE_HASH = 395945892L
    private val softBodySetSpaceBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "soft_body_set_space", SOFT_BODY_SET_SPACE_HASH)
    }

    private const val SOFT_BODY_GET_SPACE_HASH = 3814569979L
    private val softBodyGetSpaceBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "soft_body_get_space", SOFT_BODY_GET_SPACE_HASH)
    }

    private const val SOFT_BODY_SET_MESH_HASH = 395945892L
    private val softBodySetMeshBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "soft_body_set_mesh", SOFT_BODY_SET_MESH_HASH)
    }

    private const val SOFT_BODY_GET_BOUNDS_HASH = 974181306L
    private val softBodyGetBoundsBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "soft_body_get_bounds", SOFT_BODY_GET_BOUNDS_HASH)
    }

    private const val SOFT_BODY_SET_COLLISION_LAYER_HASH = 3411492887L
    private val softBodySetCollisionLayerBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "soft_body_set_collision_layer", SOFT_BODY_SET_COLLISION_LAYER_HASH)
    }

    private const val SOFT_BODY_GET_COLLISION_LAYER_HASH = 2198884583L
    private val softBodyGetCollisionLayerBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "soft_body_get_collision_layer", SOFT_BODY_GET_COLLISION_LAYER_HASH)
    }

    private const val SOFT_BODY_SET_COLLISION_MASK_HASH = 3411492887L
    private val softBodySetCollisionMaskBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "soft_body_set_collision_mask", SOFT_BODY_SET_COLLISION_MASK_HASH)
    }

    private const val SOFT_BODY_GET_COLLISION_MASK_HASH = 2198884583L
    private val softBodyGetCollisionMaskBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "soft_body_get_collision_mask", SOFT_BODY_GET_COLLISION_MASK_HASH)
    }

    private const val SOFT_BODY_ADD_COLLISION_EXCEPTION_HASH = 395945892L
    private val softBodyAddCollisionExceptionBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "soft_body_add_collision_exception", SOFT_BODY_ADD_COLLISION_EXCEPTION_HASH)
    }

    private const val SOFT_BODY_REMOVE_COLLISION_EXCEPTION_HASH = 395945892L
    private val softBodyRemoveCollisionExceptionBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "soft_body_remove_collision_exception", SOFT_BODY_REMOVE_COLLISION_EXCEPTION_HASH)
    }

    private const val SOFT_BODY_SET_TRANSFORM_HASH = 3935195649L
    private val softBodySetTransformBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "soft_body_set_transform", SOFT_BODY_SET_TRANSFORM_HASH)
    }

    private const val SOFT_BODY_SET_RAY_PICKABLE_HASH = 1265174801L
    private val softBodySetRayPickableBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "soft_body_set_ray_pickable", SOFT_BODY_SET_RAY_PICKABLE_HASH)
    }

    private const val SOFT_BODY_SET_SIMULATION_PRECISION_HASH = 3411492887L
    private val softBodySetSimulationPrecisionBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "soft_body_set_simulation_precision", SOFT_BODY_SET_SIMULATION_PRECISION_HASH)
    }

    private const val SOFT_BODY_GET_SIMULATION_PRECISION_HASH = 2198884583L
    private val softBodyGetSimulationPrecisionBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "soft_body_get_simulation_precision", SOFT_BODY_GET_SIMULATION_PRECISION_HASH)
    }

    private const val SOFT_BODY_SET_TOTAL_MASS_HASH = 1794382983L
    private val softBodySetTotalMassBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "soft_body_set_total_mass", SOFT_BODY_SET_TOTAL_MASS_HASH)
    }

    private const val SOFT_BODY_GET_TOTAL_MASS_HASH = 866169185L
    private val softBodyGetTotalMassBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "soft_body_get_total_mass", SOFT_BODY_GET_TOTAL_MASS_HASH)
    }

    private const val SOFT_BODY_SET_LINEAR_STIFFNESS_HASH = 1794382983L
    private val softBodySetLinearStiffnessBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "soft_body_set_linear_stiffness", SOFT_BODY_SET_LINEAR_STIFFNESS_HASH)
    }

    private const val SOFT_BODY_GET_LINEAR_STIFFNESS_HASH = 866169185L
    private val softBodyGetLinearStiffnessBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "soft_body_get_linear_stiffness", SOFT_BODY_GET_LINEAR_STIFFNESS_HASH)
    }

    private const val SOFT_BODY_SET_SHRINKING_FACTOR_HASH = 1794382983L
    private val softBodySetShrinkingFactorBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "soft_body_set_shrinking_factor", SOFT_BODY_SET_SHRINKING_FACTOR_HASH)
    }

    private const val SOFT_BODY_GET_SHRINKING_FACTOR_HASH = 866169185L
    private val softBodyGetShrinkingFactorBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "soft_body_get_shrinking_factor", SOFT_BODY_GET_SHRINKING_FACTOR_HASH)
    }

    private const val SOFT_BODY_SET_PRESSURE_COEFFICIENT_HASH = 1794382983L
    private val softBodySetPressureCoefficientBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "soft_body_set_pressure_coefficient", SOFT_BODY_SET_PRESSURE_COEFFICIENT_HASH)
    }

    private const val SOFT_BODY_GET_PRESSURE_COEFFICIENT_HASH = 866169185L
    private val softBodyGetPressureCoefficientBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "soft_body_get_pressure_coefficient", SOFT_BODY_GET_PRESSURE_COEFFICIENT_HASH)
    }

    private const val SOFT_BODY_SET_DAMPING_COEFFICIENT_HASH = 1794382983L
    private val softBodySetDampingCoefficientBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "soft_body_set_damping_coefficient", SOFT_BODY_SET_DAMPING_COEFFICIENT_HASH)
    }

    private const val SOFT_BODY_GET_DAMPING_COEFFICIENT_HASH = 866169185L
    private val softBodyGetDampingCoefficientBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "soft_body_get_damping_coefficient", SOFT_BODY_GET_DAMPING_COEFFICIENT_HASH)
    }

    private const val SOFT_BODY_SET_DRAG_COEFFICIENT_HASH = 1794382983L
    private val softBodySetDragCoefficientBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "soft_body_set_drag_coefficient", SOFT_BODY_SET_DRAG_COEFFICIENT_HASH)
    }

    private const val SOFT_BODY_GET_DRAG_COEFFICIENT_HASH = 866169185L
    private val softBodyGetDragCoefficientBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "soft_body_get_drag_coefficient", SOFT_BODY_GET_DRAG_COEFFICIENT_HASH)
    }

    private const val SOFT_BODY_MOVE_POINT_HASH = 831953689L
    private val softBodyMovePointBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "soft_body_move_point", SOFT_BODY_MOVE_POINT_HASH)
    }

    private const val SOFT_BODY_GET_POINT_GLOBAL_POSITION_HASH = 3440143363L
    private val softBodyGetPointGlobalPositionBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "soft_body_get_point_global_position", SOFT_BODY_GET_POINT_GLOBAL_POSITION_HASH)
    }

    private const val SOFT_BODY_REMOVE_ALL_PINNED_POINTS_HASH = 2722037293L
    private val softBodyRemoveAllPinnedPointsBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "soft_body_remove_all_pinned_points", SOFT_BODY_REMOVE_ALL_PINNED_POINTS_HASH)
    }

    private const val SOFT_BODY_PIN_POINT_HASH = 2658558584L
    private val softBodyPinPointBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "soft_body_pin_point", SOFT_BODY_PIN_POINT_HASH)
    }

    private const val SOFT_BODY_IS_POINT_PINNED_HASH = 3120086654L
    private val softBodyIsPointPinnedBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "soft_body_is_point_pinned", SOFT_BODY_IS_POINT_PINNED_HASH)
    }

    private const val SOFT_BODY_APPLY_POINT_IMPULSE_HASH = 831953689L
    private val softBodyApplyPointImpulseBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "soft_body_apply_point_impulse", SOFT_BODY_APPLY_POINT_IMPULSE_HASH)
    }

    private const val SOFT_BODY_APPLY_POINT_FORCE_HASH = 831953689L
    private val softBodyApplyPointForceBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "soft_body_apply_point_force", SOFT_BODY_APPLY_POINT_FORCE_HASH)
    }

    private const val SOFT_BODY_APPLY_CENTRAL_IMPULSE_HASH = 3227306858L
    private val softBodyApplyCentralImpulseBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "soft_body_apply_central_impulse", SOFT_BODY_APPLY_CENTRAL_IMPULSE_HASH)
    }

    private const val SOFT_BODY_APPLY_CENTRAL_FORCE_HASH = 3227306858L
    private val softBodyApplyCentralForceBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "soft_body_apply_central_force", SOFT_BODY_APPLY_CENTRAL_FORCE_HASH)
    }

    private const val JOINT_CREATE_HASH = 529393457L
    private val jointCreateBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "joint_create", JOINT_CREATE_HASH)
    }

    private const val JOINT_CLEAR_HASH = 2722037293L
    private val jointClearBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "joint_clear", JOINT_CLEAR_HASH)
    }

    private const val JOINT_MAKE_PIN_HASH = 4280171926L
    private val jointMakePinBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "joint_make_pin", JOINT_MAKE_PIN_HASH)
    }

    private const val PIN_JOINT_SET_PARAM_HASH = 810685294L
    private val pinJointSetParamBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "pin_joint_set_param", PIN_JOINT_SET_PARAM_HASH)
    }

    private const val PIN_JOINT_GET_PARAM_HASH = 2817972347L
    private val pinJointGetParamBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "pin_joint_get_param", PIN_JOINT_GET_PARAM_HASH)
    }

    private const val PIN_JOINT_SET_LOCAL_A_HASH = 3227306858L
    private val pinJointSetLocalABind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "pin_joint_set_local_a", PIN_JOINT_SET_LOCAL_A_HASH)
    }

    private const val PIN_JOINT_GET_LOCAL_A_HASH = 531438156L
    private val pinJointGetLocalABind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "pin_joint_get_local_a", PIN_JOINT_GET_LOCAL_A_HASH)
    }

    private const val PIN_JOINT_SET_LOCAL_B_HASH = 3227306858L
    private val pinJointSetLocalBBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "pin_joint_set_local_b", PIN_JOINT_SET_LOCAL_B_HASH)
    }

    private const val PIN_JOINT_GET_LOCAL_B_HASH = 531438156L
    private val pinJointGetLocalBBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "pin_joint_get_local_b", PIN_JOINT_GET_LOCAL_B_HASH)
    }

    private const val JOINT_MAKE_HINGE_HASH = 1684107643L
    private val jointMakeHingeBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "joint_make_hinge", JOINT_MAKE_HINGE_HASH)
    }

    private const val HINGE_JOINT_SET_PARAM_HASH = 3165502333L
    private val hingeJointSetParamBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "hinge_joint_set_param", HINGE_JOINT_SET_PARAM_HASH)
    }

    private const val HINGE_JOINT_GET_PARAM_HASH = 2129207581L
    private val hingeJointGetParamBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "hinge_joint_get_param", HINGE_JOINT_GET_PARAM_HASH)
    }

    private const val HINGE_JOINT_SET_FLAG_HASH = 1601626188L
    private val hingeJointSetFlagBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "hinge_joint_set_flag", HINGE_JOINT_SET_FLAG_HASH)
    }

    private const val HINGE_JOINT_GET_FLAG_HASH = 4165147865L
    private val hingeJointGetFlagBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "hinge_joint_get_flag", HINGE_JOINT_GET_FLAG_HASH)
    }

    private const val JOINT_MAKE_SLIDER_HASH = 1684107643L
    private val jointMakeSliderBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "joint_make_slider", JOINT_MAKE_SLIDER_HASH)
    }

    private const val SLIDER_JOINT_SET_PARAM_HASH = 2264833593L
    private val sliderJointSetParamBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "slider_joint_set_param", SLIDER_JOINT_SET_PARAM_HASH)
    }

    private const val SLIDER_JOINT_GET_PARAM_HASH = 3498644957L
    private val sliderJointGetParamBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "slider_joint_get_param", SLIDER_JOINT_GET_PARAM_HASH)
    }

    private const val JOINT_MAKE_CONE_TWIST_HASH = 1684107643L
    private val jointMakeConeTwistBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "joint_make_cone_twist", JOINT_MAKE_CONE_TWIST_HASH)
    }

    private const val CONE_TWIST_JOINT_SET_PARAM_HASH = 808587618L
    private val coneTwistJointSetParamBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "cone_twist_joint_set_param", CONE_TWIST_JOINT_SET_PARAM_HASH)
    }

    private const val CONE_TWIST_JOINT_GET_PARAM_HASH = 1134789658L
    private val coneTwistJointGetParamBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "cone_twist_joint_get_param", CONE_TWIST_JOINT_GET_PARAM_HASH)
    }

    private const val JOINT_GET_TYPE_HASH = 4290791900L
    private val jointGetTypeBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "joint_get_type", JOINT_GET_TYPE_HASH)
    }

    private const val JOINT_SET_SOLVER_PRIORITY_HASH = 3411492887L
    private val jointSetSolverPriorityBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "joint_set_solver_priority", JOINT_SET_SOLVER_PRIORITY_HASH)
    }

    private const val JOINT_GET_SOLVER_PRIORITY_HASH = 2198884583L
    private val jointGetSolverPriorityBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "joint_get_solver_priority", JOINT_GET_SOLVER_PRIORITY_HASH)
    }

    private const val JOINT_DISABLE_COLLISIONS_BETWEEN_BODIES_HASH = 1265174801L
    private val jointDisableCollisionsBetweenBodiesBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "joint_disable_collisions_between_bodies", JOINT_DISABLE_COLLISIONS_BETWEEN_BODIES_HASH)
    }

    private const val JOINT_IS_DISABLED_COLLISIONS_BETWEEN_BODIES_HASH = 4155700596L
    private val jointIsDisabledCollisionsBetweenBodiesBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "joint_is_disabled_collisions_between_bodies", JOINT_IS_DISABLED_COLLISIONS_BETWEEN_BODIES_HASH)
    }

    private const val JOINT_MAKE_GENERIC_6DOF_HASH = 1684107643L
    private val jointMakeGeneric6dofBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "joint_make_generic_6dof", JOINT_MAKE_GENERIC_6DOF_HASH)
    }

    private const val GENERIC_6DOF_JOINT_SET_PARAM_HASH = 2600081391L
    private val generic6dofJointSetParamBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "generic_6dof_joint_set_param", GENERIC_6DOF_JOINT_SET_PARAM_HASH)
    }

    private const val GENERIC_6DOF_JOINT_GET_PARAM_HASH = 467122058L
    private val generic6dofJointGetParamBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "generic_6dof_joint_get_param", GENERIC_6DOF_JOINT_GET_PARAM_HASH)
    }

    private const val GENERIC_6DOF_JOINT_SET_FLAG_HASH = 3570926903L
    private val generic6dofJointSetFlagBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "generic_6dof_joint_set_flag", GENERIC_6DOF_JOINT_SET_FLAG_HASH)
    }

    private const val GENERIC_6DOF_JOINT_GET_FLAG_HASH = 4158090196L
    private val generic6dofJointGetFlagBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "generic_6dof_joint_get_flag", GENERIC_6DOF_JOINT_GET_FLAG_HASH)
    }

    private const val FREE_RID_HASH = 2722037293L
    private val freeRidBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "free_rid", FREE_RID_HASH)
    }

    private const val SET_ACTIVE_HASH = 2586408642L
    private val setActiveBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "set_active", SET_ACTIVE_HASH)
    }

    private const val GET_PROCESS_INFO_HASH = 1332958745L
    private val getProcessInfoBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "get_process_info", GET_PROCESS_INFO_HASH)
    }
}
