package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Transform2D
import net.multigesture.kanama.types.Vector2

/**
 * Generated from Godot docs: PhysicsServer2D
 */
object PhysicsServer2D {
    private val singleton: MemorySegment by lazy {
        ObjectCalls.getSingleton("PhysicsServer2D")
    }

    const val SPACE_PARAM_CONTACT_RECYCLE_RADIUS: Long = 0L
    const val SPACE_PARAM_CONTACT_MAX_SEPARATION: Long = 1L
    const val SPACE_PARAM_CONTACT_MAX_ALLOWED_PENETRATION: Long = 2L
    const val SPACE_PARAM_CONTACT_DEFAULT_BIAS: Long = 3L
    const val SPACE_PARAM_BODY_LINEAR_VELOCITY_SLEEP_THRESHOLD: Long = 4L
    const val SPACE_PARAM_BODY_ANGULAR_VELOCITY_SLEEP_THRESHOLD: Long = 5L
    const val SPACE_PARAM_BODY_TIME_TO_SLEEP: Long = 6L
    const val SPACE_PARAM_CONSTRAINT_DEFAULT_BIAS: Long = 7L
    const val SPACE_PARAM_SOLVER_ITERATIONS: Long = 8L
    const val SHAPE_WORLD_BOUNDARY: Long = 0L
    const val SHAPE_SEPARATION_RAY: Long = 1L
    const val SHAPE_SEGMENT: Long = 2L
    const val SHAPE_CIRCLE: Long = 3L
    const val SHAPE_RECTANGLE: Long = 4L
    const val SHAPE_CAPSULE: Long = 5L
    const val SHAPE_CONVEX_POLYGON: Long = 6L
    const val SHAPE_CONCAVE_POLYGON: Long = 7L
    const val SHAPE_CUSTOM: Long = 8L
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
    const val JOINT_TYPE_PIN: Long = 0L
    const val JOINT_TYPE_GROOVE: Long = 1L
    const val JOINT_TYPE_DAMPED_SPRING: Long = 2L
    const val JOINT_TYPE_MAX: Long = 3L
    const val JOINT_PARAM_BIAS: Long = 0L
    const val JOINT_PARAM_MAX_BIAS: Long = 1L
    const val JOINT_PARAM_MAX_FORCE: Long = 2L
    const val PIN_JOINT_SOFTNESS: Long = 0L
    const val PIN_JOINT_LIMIT_UPPER: Long = 1L
    const val PIN_JOINT_LIMIT_LOWER: Long = 2L
    const val PIN_JOINT_MOTOR_TARGET_VELOCITY: Long = 3L
    const val PIN_JOINT_FLAG_ANGULAR_LIMIT_ENABLED: Long = 0L
    const val PIN_JOINT_FLAG_MOTOR_ENABLED: Long = 1L
    const val DAMPED_SPRING_REST_LENGTH: Long = 0L
    const val DAMPED_SPRING_STIFFNESS: Long = 1L
    const val DAMPED_SPRING_DAMPING: Long = 2L
    const val CCD_MODE_DISABLED: Long = 0L
    const val CCD_MODE_CAST_RAY: Long = 1L
    const val CCD_MODE_CAST_SHAPE: Long = 2L
    const val AREA_BODY_ADDED: Long = 0L
    const val AREA_BODY_REMOVED: Long = 1L
    const val INFO_ACTIVE_OBJECTS: Long = 0L
    const val INFO_COLLISION_PAIRS: Long = 1L
    const val INFO_ISLAND_COUNT: Long = 2L

    fun worldBoundaryShapeCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(worldBoundaryShapeCreateBind, singleton)
    }

    fun separationRayShapeCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(separationRayShapeCreateBind, singleton)
    }

    fun segmentShapeCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(segmentShapeCreateBind, singleton)
    }

    fun circleShapeCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(circleShapeCreateBind, singleton)
    }

    fun rectangleShapeCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(rectangleShapeCreateBind, singleton)
    }

    fun capsuleShapeCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(capsuleShapeCreateBind, singleton)
    }

    fun convexPolygonShapeCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(convexPolygonShapeCreateBind, singleton)
    }

    fun concavePolygonShapeCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(concavePolygonShapeCreateBind, singleton)
    }

    fun shapeGetType(shape: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(shapeGetTypeBind, singleton, shape)
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

    fun spaceGetDirectState(space: RID): PhysicsDirectSpaceState2D? {
        return PhysicsDirectSpaceState2D.wrap(ObjectCalls.ptrcallWithRIDArgRetObject(spaceGetDirectStateBind, singleton, space))
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

    fun areaAddShape(area: RID, shape: RID, transform: Transform2D, disabled: Boolean = false) {
        ObjectCalls.ptrcallWithTwoRIDTransform2DBoolArgs(areaAddShapeBind, singleton, area, shape, transform, disabled)
    }

    fun areaSetShape(area: RID, shapeIdx: Int, shape: RID) {
        ObjectCalls.ptrcallWithRIDIntAndRIDArgs(areaSetShapeBind, singleton, area, shapeIdx, shape)
    }

    fun areaSetShapeTransform(area: RID, shapeIdx: Int, transform: Transform2D) {
        ObjectCalls.ptrcallWithRIDIntAndTransform2DArg(areaSetShapeTransformBind, singleton, area, shapeIdx, transform)
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

    fun areaGetShapeTransform(area: RID, shapeIdx: Int): Transform2D {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetTransform2D(areaGetShapeTransformBind, singleton, area, shapeIdx)
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

    fun areaSetTransform(area: RID, transform: Transform2D) {
        ObjectCalls.ptrcallWithRIDAndTransform2DArg(areaSetTransformBind, singleton, area, transform)
    }

    fun areaGetTransform(area: RID): Transform2D {
        return ObjectCalls.ptrcallWithRIDArgRetTransform2D(areaGetTransformBind, singleton, area)
    }

    fun areaAttachObjectInstanceId(area: RID, id: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(areaAttachObjectInstanceIdBind, singleton, area, id)
    }

    fun areaGetObjectInstanceId(area: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(areaGetObjectInstanceIdBind, singleton, area)
    }

    fun areaAttachCanvasInstanceId(area: RID, id: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(areaAttachCanvasInstanceIdBind, singleton, area, id)
    }

    fun areaGetCanvasInstanceId(area: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(areaGetCanvasInstanceIdBind, singleton, area)
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

    fun bodyAddShape(body: RID, shape: RID, transform: Transform2D, disabled: Boolean = false) {
        ObjectCalls.ptrcallWithTwoRIDTransform2DBoolArgs(bodyAddShapeBind, singleton, body, shape, transform, disabled)
    }

    fun bodySetShape(body: RID, shapeIdx: Int, shape: RID) {
        ObjectCalls.ptrcallWithRIDIntAndRIDArgs(bodySetShapeBind, singleton, body, shapeIdx, shape)
    }

    fun bodySetShapeTransform(body: RID, shapeIdx: Int, transform: Transform2D) {
        ObjectCalls.ptrcallWithRIDIntAndTransform2DArg(bodySetShapeTransformBind, singleton, body, shapeIdx, transform)
    }

    fun bodyGetShapeCount(body: RID): Int {
        return ObjectCalls.ptrcallWithRIDArgRetInt(bodyGetShapeCountBind, singleton, body)
    }

    fun bodyGetShape(body: RID, shapeIdx: Int): RID {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetRID(bodyGetShapeBind, singleton, body, shapeIdx)
    }

    fun bodyGetShapeTransform(body: RID, shapeIdx: Int): Transform2D {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetTransform2D(bodyGetShapeTransformBind, singleton, body, shapeIdx)
    }

    fun bodyRemoveShape(body: RID, shapeIdx: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(bodyRemoveShapeBind, singleton, body, shapeIdx)
    }

    fun bodyClearShapes(body: RID) {
        ObjectCalls.ptrcallWithRIDArg(bodyClearShapesBind, singleton, body)
    }

    fun bodySetShapeDisabled(body: RID, shapeIdx: Int, disabled: Boolean) {
        ObjectCalls.ptrcallWithRIDIntAndBoolArgs(bodySetShapeDisabledBind, singleton, body, shapeIdx, disabled)
    }

    fun bodySetShapeAsOneWayCollision(body: RID, shapeIdx: Int, enable: Boolean, margin: Double, direction: Vector2) {
        ObjectCalls.ptrcallWithRIDIntBoolDoubleVector2Args(bodySetShapeAsOneWayCollisionBind, singleton, body, shapeIdx, enable, margin, direction)
    }

    fun bodyAttachObjectInstanceId(body: RID, id: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(bodyAttachObjectInstanceIdBind, singleton, body, id)
    }

    fun bodyGetObjectInstanceId(body: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(bodyGetObjectInstanceIdBind, singleton, body)
    }

    fun bodyAttachCanvasInstanceId(body: RID, id: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(bodyAttachCanvasInstanceIdBind, singleton, body, id)
    }

    fun bodyGetCanvasInstanceId(body: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(bodyGetCanvasInstanceIdBind, singleton, body)
    }

    fun bodySetContinuousCollisionDetectionMode(body: RID, mode: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(bodySetContinuousCollisionDetectionModeBind, singleton, body, mode)
    }

    fun bodyGetContinuousCollisionDetectionMode(body: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(bodyGetContinuousCollisionDetectionModeBind, singleton, body)
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

    fun bodyResetMassProperties(body: RID) {
        ObjectCalls.ptrcallWithRIDArg(bodyResetMassPropertiesBind, singleton, body)
    }

    fun bodyApplyCentralImpulse(body: RID, impulse: Vector2) {
        ObjectCalls.ptrcallWithRIDAndVector2Arg(bodyApplyCentralImpulseBind, singleton, body, impulse)
    }

    fun bodyApplyTorqueImpulse(body: RID, impulse: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(bodyApplyTorqueImpulseBind, singleton, body, impulse)
    }

    fun bodyApplyImpulse(body: RID, impulse: Vector2, position: Vector2 = Vector2(0f, 0f)) {
        ObjectCalls.ptrcallWithRIDAndTwoVector2Args(bodyApplyImpulseBind, singleton, body, impulse, position)
    }

    fun bodyApplyCentralForce(body: RID, force: Vector2) {
        ObjectCalls.ptrcallWithRIDAndVector2Arg(bodyApplyCentralForceBind, singleton, body, force)
    }

    fun bodyApplyForce(body: RID, force: Vector2, position: Vector2 = Vector2(0f, 0f)) {
        ObjectCalls.ptrcallWithRIDAndTwoVector2Args(bodyApplyForceBind, singleton, body, force, position)
    }

    fun bodyApplyTorque(body: RID, torque: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(bodyApplyTorqueBind, singleton, body, torque)
    }

    fun bodyAddConstantCentralForce(body: RID, force: Vector2) {
        ObjectCalls.ptrcallWithRIDAndVector2Arg(bodyAddConstantCentralForceBind, singleton, body, force)
    }

    fun bodyAddConstantForce(body: RID, force: Vector2, position: Vector2 = Vector2(0f, 0f)) {
        ObjectCalls.ptrcallWithRIDAndTwoVector2Args(bodyAddConstantForceBind, singleton, body, force, position)
    }

    fun bodyAddConstantTorque(body: RID, torque: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(bodyAddConstantTorqueBind, singleton, body, torque)
    }

    fun bodySetConstantForce(body: RID, force: Vector2) {
        ObjectCalls.ptrcallWithRIDAndVector2Arg(bodySetConstantForceBind, singleton, body, force)
    }

    fun bodyGetConstantForce(body: RID): Vector2 {
        return ObjectCalls.ptrcallWithRIDArgRetVector2(bodyGetConstantForceBind, singleton, body)
    }

    fun bodySetConstantTorque(body: RID, torque: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(bodySetConstantTorqueBind, singleton, body, torque)
    }

    fun bodyGetConstantTorque(body: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(bodyGetConstantTorqueBind, singleton, body)
    }

    fun bodySetAxisVelocity(body: RID, axisVelocity: Vector2) {
        ObjectCalls.ptrcallWithRIDAndVector2Arg(bodySetAxisVelocityBind, singleton, body, axisVelocity)
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

    fun bodyTestMotion(body: RID, parameters: PhysicsTestMotionParameters2D, result: PhysicsTestMotionResult2D?): Boolean {
        return ObjectCalls.ptrcallWithRIDAndTwoObjectArgsRetBool(bodyTestMotionBind, singleton, body, parameters.requireOpenHandle(), result?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun bodyGetDirectState(body: RID): PhysicsDirectBodyState2D? {
        return PhysicsDirectBodyState2D.wrap(ObjectCalls.ptrcallWithRIDArgRetObject(bodyGetDirectStateBind, singleton, body))
    }

    fun jointCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(jointCreateBind, singleton)
    }

    fun jointClear(joint: RID) {
        ObjectCalls.ptrcallWithRIDArg(jointClearBind, singleton, joint)
    }

    fun jointSetParam(joint: RID, param: Long, value: Double) {
        ObjectCalls.ptrcallWithRIDLongAndDoubleArgs(jointSetParamBind, singleton, joint, param, value)
    }

    fun jointGetParam(joint: RID, param: Long): Double {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetDouble(jointGetParamBind, singleton, joint, param)
    }

    fun jointDisableCollisionsBetweenBodies(joint: RID, disable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(jointDisableCollisionsBetweenBodiesBind, singleton, joint, disable)
    }

    fun jointIsDisabledCollisionsBetweenBodies(joint: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(jointIsDisabledCollisionsBetweenBodiesBind, singleton, joint)
    }

    fun jointMakePin(joint: RID, anchor: Vector2, bodyA: RID, bodyB: RID) {
        ObjectCalls.ptrcallWithRIDVector2TwoRIDArgs(jointMakePinBind, singleton, joint, anchor, bodyA, bodyB)
    }

    fun jointMakeGroove(joint: RID, groove1A: Vector2, groove2A: Vector2, anchorB: Vector2, bodyA: RID, bodyB: RID) {
        ObjectCalls.ptrcallWithRIDThreeVector2TwoRIDArgs(jointMakeGrooveBind, singleton, joint, groove1A, groove2A, anchorB, bodyA, bodyB)
    }

    fun jointMakeDampedSpring(joint: RID, anchorA: Vector2, anchorB: Vector2, bodyA: RID, bodyB: RID) {
        ObjectCalls.ptrcallWithRIDTwoVector2TwoRIDArgs(jointMakeDampedSpringBind, singleton, joint, anchorA, anchorB, bodyA, bodyB)
    }

    fun pinJointSetFlag(joint: RID, flag: Long, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDLongAndBoolArgs(pinJointSetFlagBind, singleton, joint, flag, enabled)
    }

    fun pinJointGetFlag(joint: RID, flag: Long): Boolean {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetBool(pinJointGetFlagBind, singleton, joint, flag)
    }

    fun pinJointSetParam(joint: RID, param: Long, value: Double) {
        ObjectCalls.ptrcallWithRIDLongAndDoubleArgs(pinJointSetParamBind, singleton, joint, param, value)
    }

    fun pinJointGetParam(joint: RID, param: Long): Double {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetDouble(pinJointGetParamBind, singleton, joint, param)
    }

    fun dampedSpringJointSetParam(joint: RID, param: Long, value: Double) {
        ObjectCalls.ptrcallWithRIDLongAndDoubleArgs(dampedSpringJointSetParamBind, singleton, joint, param, value)
    }

    fun dampedSpringJointGetParam(joint: RID, param: Long): Double {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetDouble(dampedSpringJointGetParamBind, singleton, joint, param)
    }

    fun jointGetType(joint: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(jointGetTypeBind, singleton, joint)
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

    fun fromHandle(handle: MemorySegment): PhysicsServer2D? =
        wrap(handle)

    internal fun wrap(handle: MemorySegment): PhysicsServer2D? =
        if (handle.address() == 0L) null else this

    private const val WORLD_BOUNDARY_SHAPE_CREATE_HASH = 529393457L
    private val worldBoundaryShapeCreateBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "world_boundary_shape_create", WORLD_BOUNDARY_SHAPE_CREATE_HASH)
    }

    private const val SEPARATION_RAY_SHAPE_CREATE_HASH = 529393457L
    private val separationRayShapeCreateBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "separation_ray_shape_create", SEPARATION_RAY_SHAPE_CREATE_HASH)
    }

    private const val SEGMENT_SHAPE_CREATE_HASH = 529393457L
    private val segmentShapeCreateBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "segment_shape_create", SEGMENT_SHAPE_CREATE_HASH)
    }

    private const val CIRCLE_SHAPE_CREATE_HASH = 529393457L
    private val circleShapeCreateBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "circle_shape_create", CIRCLE_SHAPE_CREATE_HASH)
    }

    private const val RECTANGLE_SHAPE_CREATE_HASH = 529393457L
    private val rectangleShapeCreateBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "rectangle_shape_create", RECTANGLE_SHAPE_CREATE_HASH)
    }

    private const val CAPSULE_SHAPE_CREATE_HASH = 529393457L
    private val capsuleShapeCreateBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "capsule_shape_create", CAPSULE_SHAPE_CREATE_HASH)
    }

    private const val CONVEX_POLYGON_SHAPE_CREATE_HASH = 529393457L
    private val convexPolygonShapeCreateBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "convex_polygon_shape_create", CONVEX_POLYGON_SHAPE_CREATE_HASH)
    }

    private const val CONCAVE_POLYGON_SHAPE_CREATE_HASH = 529393457L
    private val concavePolygonShapeCreateBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "concave_polygon_shape_create", CONCAVE_POLYGON_SHAPE_CREATE_HASH)
    }

    private const val SHAPE_GET_TYPE_HASH = 1240598777L
    private val shapeGetTypeBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "shape_get_type", SHAPE_GET_TYPE_HASH)
    }

    private const val SPACE_CREATE_HASH = 529393457L
    private val spaceCreateBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "space_create", SPACE_CREATE_HASH)
    }

    private const val SPACE_SET_ACTIVE_HASH = 1265174801L
    private val spaceSetActiveBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "space_set_active", SPACE_SET_ACTIVE_HASH)
    }

    private const val SPACE_IS_ACTIVE_HASH = 4155700596L
    private val spaceIsActiveBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "space_is_active", SPACE_IS_ACTIVE_HASH)
    }

    private const val SPACE_SET_PARAM_HASH = 949194586L
    private val spaceSetParamBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "space_set_param", SPACE_SET_PARAM_HASH)
    }

    private const val SPACE_GET_PARAM_HASH = 874111783L
    private val spaceGetParamBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "space_get_param", SPACE_GET_PARAM_HASH)
    }

    private const val SPACE_GET_DIRECT_STATE_HASH = 3160173886L
    private val spaceGetDirectStateBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "space_get_direct_state", SPACE_GET_DIRECT_STATE_HASH)
    }

    private const val AREA_CREATE_HASH = 529393457L
    private val areaCreateBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "area_create", AREA_CREATE_HASH)
    }

    private const val AREA_SET_SPACE_HASH = 395945892L
    private val areaSetSpaceBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "area_set_space", AREA_SET_SPACE_HASH)
    }

    private const val AREA_GET_SPACE_HASH = 3814569979L
    private val areaGetSpaceBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "area_get_space", AREA_GET_SPACE_HASH)
    }

    private const val AREA_ADD_SHAPE_HASH = 339056240L
    private val areaAddShapeBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "area_add_shape", AREA_ADD_SHAPE_HASH)
    }

    private const val AREA_SET_SHAPE_HASH = 2310537182L
    private val areaSetShapeBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "area_set_shape", AREA_SET_SHAPE_HASH)
    }

    private const val AREA_SET_SHAPE_TRANSFORM_HASH = 736082694L
    private val areaSetShapeTransformBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "area_set_shape_transform", AREA_SET_SHAPE_TRANSFORM_HASH)
    }

    private const val AREA_SET_SHAPE_DISABLED_HASH = 2658558584L
    private val areaSetShapeDisabledBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "area_set_shape_disabled", AREA_SET_SHAPE_DISABLED_HASH)
    }

    private const val AREA_GET_SHAPE_COUNT_HASH = 2198884583L
    private val areaGetShapeCountBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "area_get_shape_count", AREA_GET_SHAPE_COUNT_HASH)
    }

    private const val AREA_GET_SHAPE_HASH = 1066463050L
    private val areaGetShapeBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "area_get_shape", AREA_GET_SHAPE_HASH)
    }

    private const val AREA_GET_SHAPE_TRANSFORM_HASH = 1324854622L
    private val areaGetShapeTransformBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "area_get_shape_transform", AREA_GET_SHAPE_TRANSFORM_HASH)
    }

    private const val AREA_REMOVE_SHAPE_HASH = 3411492887L
    private val areaRemoveShapeBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "area_remove_shape", AREA_REMOVE_SHAPE_HASH)
    }

    private const val AREA_CLEAR_SHAPES_HASH = 2722037293L
    private val areaClearShapesBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "area_clear_shapes", AREA_CLEAR_SHAPES_HASH)
    }

    private const val AREA_SET_COLLISION_LAYER_HASH = 3411492887L
    private val areaSetCollisionLayerBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "area_set_collision_layer", AREA_SET_COLLISION_LAYER_HASH)
    }

    private const val AREA_GET_COLLISION_LAYER_HASH = 2198884583L
    private val areaGetCollisionLayerBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "area_get_collision_layer", AREA_GET_COLLISION_LAYER_HASH)
    }

    private const val AREA_SET_COLLISION_MASK_HASH = 3411492887L
    private val areaSetCollisionMaskBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "area_set_collision_mask", AREA_SET_COLLISION_MASK_HASH)
    }

    private const val AREA_GET_COLLISION_MASK_HASH = 2198884583L
    private val areaGetCollisionMaskBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "area_get_collision_mask", AREA_GET_COLLISION_MASK_HASH)
    }

    private const val AREA_SET_TRANSFORM_HASH = 1246044741L
    private val areaSetTransformBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "area_set_transform", AREA_SET_TRANSFORM_HASH)
    }

    private const val AREA_GET_TRANSFORM_HASH = 213527486L
    private val areaGetTransformBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "area_get_transform", AREA_GET_TRANSFORM_HASH)
    }

    private const val AREA_ATTACH_OBJECT_INSTANCE_ID_HASH = 3411492887L
    private val areaAttachObjectInstanceIdBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "area_attach_object_instance_id", AREA_ATTACH_OBJECT_INSTANCE_ID_HASH)
    }

    private const val AREA_GET_OBJECT_INSTANCE_ID_HASH = 2198884583L
    private val areaGetObjectInstanceIdBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "area_get_object_instance_id", AREA_GET_OBJECT_INSTANCE_ID_HASH)
    }

    private const val AREA_ATTACH_CANVAS_INSTANCE_ID_HASH = 3411492887L
    private val areaAttachCanvasInstanceIdBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "area_attach_canvas_instance_id", AREA_ATTACH_CANVAS_INSTANCE_ID_HASH)
    }

    private const val AREA_GET_CANVAS_INSTANCE_ID_HASH = 2198884583L
    private val areaGetCanvasInstanceIdBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "area_get_canvas_instance_id", AREA_GET_CANVAS_INSTANCE_ID_HASH)
    }

    private const val AREA_SET_MONITOR_CALLBACK_HASH = 3379118538L
    private val areaSetMonitorCallbackBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "area_set_monitor_callback", AREA_SET_MONITOR_CALLBACK_HASH)
    }

    private const val AREA_SET_AREA_MONITOR_CALLBACK_HASH = 3379118538L
    private val areaSetAreaMonitorCallbackBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "area_set_area_monitor_callback", AREA_SET_AREA_MONITOR_CALLBACK_HASH)
    }

    private const val AREA_SET_MONITORABLE_HASH = 1265174801L
    private val areaSetMonitorableBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "area_set_monitorable", AREA_SET_MONITORABLE_HASH)
    }

    private const val BODY_CREATE_HASH = 529393457L
    private val bodyCreateBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_create", BODY_CREATE_HASH)
    }

    private const val BODY_SET_SPACE_HASH = 395945892L
    private val bodySetSpaceBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_set_space", BODY_SET_SPACE_HASH)
    }

    private const val BODY_GET_SPACE_HASH = 3814569979L
    private val bodyGetSpaceBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_get_space", BODY_GET_SPACE_HASH)
    }

    private const val BODY_SET_MODE_HASH = 1658067650L
    private val bodySetModeBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_set_mode", BODY_SET_MODE_HASH)
    }

    private const val BODY_GET_MODE_HASH = 3261702585L
    private val bodyGetModeBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_get_mode", BODY_GET_MODE_HASH)
    }

    private const val BODY_ADD_SHAPE_HASH = 339056240L
    private val bodyAddShapeBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_add_shape", BODY_ADD_SHAPE_HASH)
    }

    private const val BODY_SET_SHAPE_HASH = 2310537182L
    private val bodySetShapeBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_set_shape", BODY_SET_SHAPE_HASH)
    }

    private const val BODY_SET_SHAPE_TRANSFORM_HASH = 736082694L
    private val bodySetShapeTransformBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_set_shape_transform", BODY_SET_SHAPE_TRANSFORM_HASH)
    }

    private const val BODY_GET_SHAPE_COUNT_HASH = 2198884583L
    private val bodyGetShapeCountBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_get_shape_count", BODY_GET_SHAPE_COUNT_HASH)
    }

    private const val BODY_GET_SHAPE_HASH = 1066463050L
    private val bodyGetShapeBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_get_shape", BODY_GET_SHAPE_HASH)
    }

    private const val BODY_GET_SHAPE_TRANSFORM_HASH = 1324854622L
    private val bodyGetShapeTransformBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_get_shape_transform", BODY_GET_SHAPE_TRANSFORM_HASH)
    }

    private const val BODY_REMOVE_SHAPE_HASH = 3411492887L
    private val bodyRemoveShapeBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_remove_shape", BODY_REMOVE_SHAPE_HASH)
    }

    private const val BODY_CLEAR_SHAPES_HASH = 2722037293L
    private val bodyClearShapesBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_clear_shapes", BODY_CLEAR_SHAPES_HASH)
    }

    private const val BODY_SET_SHAPE_DISABLED_HASH = 2658558584L
    private val bodySetShapeDisabledBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_set_shape_disabled", BODY_SET_SHAPE_DISABLED_HASH)
    }

    private const val BODY_SET_SHAPE_AS_ONE_WAY_COLLISION_HASH = 2389283141L
    private val bodySetShapeAsOneWayCollisionBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_set_shape_as_one_way_collision", BODY_SET_SHAPE_AS_ONE_WAY_COLLISION_HASH)
    }

    private const val BODY_ATTACH_OBJECT_INSTANCE_ID_HASH = 3411492887L
    private val bodyAttachObjectInstanceIdBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_attach_object_instance_id", BODY_ATTACH_OBJECT_INSTANCE_ID_HASH)
    }

    private const val BODY_GET_OBJECT_INSTANCE_ID_HASH = 2198884583L
    private val bodyGetObjectInstanceIdBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_get_object_instance_id", BODY_GET_OBJECT_INSTANCE_ID_HASH)
    }

    private const val BODY_ATTACH_CANVAS_INSTANCE_ID_HASH = 3411492887L
    private val bodyAttachCanvasInstanceIdBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_attach_canvas_instance_id", BODY_ATTACH_CANVAS_INSTANCE_ID_HASH)
    }

    private const val BODY_GET_CANVAS_INSTANCE_ID_HASH = 2198884583L
    private val bodyGetCanvasInstanceIdBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_get_canvas_instance_id", BODY_GET_CANVAS_INSTANCE_ID_HASH)
    }

    private const val BODY_SET_CONTINUOUS_COLLISION_DETECTION_MODE_HASH = 1882257015L
    private val bodySetContinuousCollisionDetectionModeBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_set_continuous_collision_detection_mode", BODY_SET_CONTINUOUS_COLLISION_DETECTION_MODE_HASH)
    }

    private const val BODY_GET_CONTINUOUS_COLLISION_DETECTION_MODE_HASH = 2661282217L
    private val bodyGetContinuousCollisionDetectionModeBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_get_continuous_collision_detection_mode", BODY_GET_CONTINUOUS_COLLISION_DETECTION_MODE_HASH)
    }

    private const val BODY_SET_COLLISION_LAYER_HASH = 3411492887L
    private val bodySetCollisionLayerBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_set_collision_layer", BODY_SET_COLLISION_LAYER_HASH)
    }

    private const val BODY_GET_COLLISION_LAYER_HASH = 2198884583L
    private val bodyGetCollisionLayerBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_get_collision_layer", BODY_GET_COLLISION_LAYER_HASH)
    }

    private const val BODY_SET_COLLISION_MASK_HASH = 3411492887L
    private val bodySetCollisionMaskBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_set_collision_mask", BODY_SET_COLLISION_MASK_HASH)
    }

    private const val BODY_GET_COLLISION_MASK_HASH = 2198884583L
    private val bodyGetCollisionMaskBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_get_collision_mask", BODY_GET_COLLISION_MASK_HASH)
    }

    private const val BODY_SET_COLLISION_PRIORITY_HASH = 1794382983L
    private val bodySetCollisionPriorityBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_set_collision_priority", BODY_SET_COLLISION_PRIORITY_HASH)
    }

    private const val BODY_GET_COLLISION_PRIORITY_HASH = 866169185L
    private val bodyGetCollisionPriorityBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_get_collision_priority", BODY_GET_COLLISION_PRIORITY_HASH)
    }

    private const val BODY_RESET_MASS_PROPERTIES_HASH = 2722037293L
    private val bodyResetMassPropertiesBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_reset_mass_properties", BODY_RESET_MASS_PROPERTIES_HASH)
    }

    private const val BODY_APPLY_CENTRAL_IMPULSE_HASH = 3201125042L
    private val bodyApplyCentralImpulseBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_apply_central_impulse", BODY_APPLY_CENTRAL_IMPULSE_HASH)
    }

    private const val BODY_APPLY_TORQUE_IMPULSE_HASH = 1794382983L
    private val bodyApplyTorqueImpulseBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_apply_torque_impulse", BODY_APPLY_TORQUE_IMPULSE_HASH)
    }

    private const val BODY_APPLY_IMPULSE_HASH = 205485391L
    private val bodyApplyImpulseBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_apply_impulse", BODY_APPLY_IMPULSE_HASH)
    }

    private const val BODY_APPLY_CENTRAL_FORCE_HASH = 3201125042L
    private val bodyApplyCentralForceBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_apply_central_force", BODY_APPLY_CENTRAL_FORCE_HASH)
    }

    private const val BODY_APPLY_FORCE_HASH = 205485391L
    private val bodyApplyForceBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_apply_force", BODY_APPLY_FORCE_HASH)
    }

    private const val BODY_APPLY_TORQUE_HASH = 1794382983L
    private val bodyApplyTorqueBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_apply_torque", BODY_APPLY_TORQUE_HASH)
    }

    private const val BODY_ADD_CONSTANT_CENTRAL_FORCE_HASH = 3201125042L
    private val bodyAddConstantCentralForceBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_add_constant_central_force", BODY_ADD_CONSTANT_CENTRAL_FORCE_HASH)
    }

    private const val BODY_ADD_CONSTANT_FORCE_HASH = 205485391L
    private val bodyAddConstantForceBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_add_constant_force", BODY_ADD_CONSTANT_FORCE_HASH)
    }

    private const val BODY_ADD_CONSTANT_TORQUE_HASH = 1794382983L
    private val bodyAddConstantTorqueBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_add_constant_torque", BODY_ADD_CONSTANT_TORQUE_HASH)
    }

    private const val BODY_SET_CONSTANT_FORCE_HASH = 3201125042L
    private val bodySetConstantForceBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_set_constant_force", BODY_SET_CONSTANT_FORCE_HASH)
    }

    private const val BODY_GET_CONSTANT_FORCE_HASH = 2440833711L
    private val bodyGetConstantForceBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_get_constant_force", BODY_GET_CONSTANT_FORCE_HASH)
    }

    private const val BODY_SET_CONSTANT_TORQUE_HASH = 1794382983L
    private val bodySetConstantTorqueBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_set_constant_torque", BODY_SET_CONSTANT_TORQUE_HASH)
    }

    private const val BODY_GET_CONSTANT_TORQUE_HASH = 866169185L
    private val bodyGetConstantTorqueBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_get_constant_torque", BODY_GET_CONSTANT_TORQUE_HASH)
    }

    private const val BODY_SET_AXIS_VELOCITY_HASH = 3201125042L
    private val bodySetAxisVelocityBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_set_axis_velocity", BODY_SET_AXIS_VELOCITY_HASH)
    }

    private const val BODY_ADD_COLLISION_EXCEPTION_HASH = 395945892L
    private val bodyAddCollisionExceptionBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_add_collision_exception", BODY_ADD_COLLISION_EXCEPTION_HASH)
    }

    private const val BODY_REMOVE_COLLISION_EXCEPTION_HASH = 395945892L
    private val bodyRemoveCollisionExceptionBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_remove_collision_exception", BODY_REMOVE_COLLISION_EXCEPTION_HASH)
    }

    private const val BODY_SET_MAX_CONTACTS_REPORTED_HASH = 3411492887L
    private val bodySetMaxContactsReportedBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_set_max_contacts_reported", BODY_SET_MAX_CONTACTS_REPORTED_HASH)
    }

    private const val BODY_GET_MAX_CONTACTS_REPORTED_HASH = 2198884583L
    private val bodyGetMaxContactsReportedBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_get_max_contacts_reported", BODY_GET_MAX_CONTACTS_REPORTED_HASH)
    }

    private const val BODY_SET_OMIT_FORCE_INTEGRATION_HASH = 1265174801L
    private val bodySetOmitForceIntegrationBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_set_omit_force_integration", BODY_SET_OMIT_FORCE_INTEGRATION_HASH)
    }

    private const val BODY_IS_OMITTING_FORCE_INTEGRATION_HASH = 4155700596L
    private val bodyIsOmittingForceIntegrationBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_is_omitting_force_integration", BODY_IS_OMITTING_FORCE_INTEGRATION_HASH)
    }

    private const val BODY_SET_STATE_SYNC_CALLBACK_HASH = 3379118538L
    private val bodySetStateSyncCallbackBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_set_state_sync_callback", BODY_SET_STATE_SYNC_CALLBACK_HASH)
    }

    private const val BODY_TEST_MOTION_HASH = 1699844009L
    private val bodyTestMotionBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_test_motion", BODY_TEST_MOTION_HASH)
    }

    private const val BODY_GET_DIRECT_STATE_HASH = 1191931871L
    private val bodyGetDirectStateBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_get_direct_state", BODY_GET_DIRECT_STATE_HASH)
    }

    private const val JOINT_CREATE_HASH = 529393457L
    private val jointCreateBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "joint_create", JOINT_CREATE_HASH)
    }

    private const val JOINT_CLEAR_HASH = 2722037293L
    private val jointClearBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "joint_clear", JOINT_CLEAR_HASH)
    }

    private const val JOINT_SET_PARAM_HASH = 3972556514L
    private val jointSetParamBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "joint_set_param", JOINT_SET_PARAM_HASH)
    }

    private const val JOINT_GET_PARAM_HASH = 4016448949L
    private val jointGetParamBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "joint_get_param", JOINT_GET_PARAM_HASH)
    }

    private const val JOINT_DISABLE_COLLISIONS_BETWEEN_BODIES_HASH = 1265174801L
    private val jointDisableCollisionsBetweenBodiesBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "joint_disable_collisions_between_bodies", JOINT_DISABLE_COLLISIONS_BETWEEN_BODIES_HASH)
    }

    private const val JOINT_IS_DISABLED_COLLISIONS_BETWEEN_BODIES_HASH = 4155700596L
    private val jointIsDisabledCollisionsBetweenBodiesBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "joint_is_disabled_collisions_between_bodies", JOINT_IS_DISABLED_COLLISIONS_BETWEEN_BODIES_HASH)
    }

    private const val JOINT_MAKE_PIN_HASH = 1612646186L
    private val jointMakePinBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "joint_make_pin", JOINT_MAKE_PIN_HASH)
    }

    private const val JOINT_MAKE_GROOVE_HASH = 481430435L
    private val jointMakeGrooveBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "joint_make_groove", JOINT_MAKE_GROOVE_HASH)
    }

    private const val JOINT_MAKE_DAMPED_SPRING_HASH = 1994657646L
    private val jointMakeDampedSpringBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "joint_make_damped_spring", JOINT_MAKE_DAMPED_SPRING_HASH)
    }

    private const val PIN_JOINT_SET_FLAG_HASH = 3520002352L
    private val pinJointSetFlagBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "pin_joint_set_flag", PIN_JOINT_SET_FLAG_HASH)
    }

    private const val PIN_JOINT_GET_FLAG_HASH = 2647867364L
    private val pinJointGetFlagBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "pin_joint_get_flag", PIN_JOINT_GET_FLAG_HASH)
    }

    private const val PIN_JOINT_SET_PARAM_HASH = 550574241L
    private val pinJointSetParamBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "pin_joint_set_param", PIN_JOINT_SET_PARAM_HASH)
    }

    private const val PIN_JOINT_GET_PARAM_HASH = 348281383L
    private val pinJointGetParamBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "pin_joint_get_param", PIN_JOINT_GET_PARAM_HASH)
    }

    private const val DAMPED_SPRING_JOINT_SET_PARAM_HASH = 220564071L
    private val dampedSpringJointSetParamBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "damped_spring_joint_set_param", DAMPED_SPRING_JOINT_SET_PARAM_HASH)
    }

    private const val DAMPED_SPRING_JOINT_GET_PARAM_HASH = 2075871277L
    private val dampedSpringJointGetParamBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "damped_spring_joint_get_param", DAMPED_SPRING_JOINT_GET_PARAM_HASH)
    }

    private const val JOINT_GET_TYPE_HASH = 4262502231L
    private val jointGetTypeBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "joint_get_type", JOINT_GET_TYPE_HASH)
    }

    private const val FREE_RID_HASH = 2722037293L
    private val freeRidBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "free_rid", FREE_RID_HASH)
    }

    private const val SET_ACTIVE_HASH = 2586408642L
    private val setActiveBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "set_active", SET_ACTIVE_HASH)
    }

    private const val GET_PROCESS_INFO_HASH = 576496006L
    private val getProcessInfoBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "get_process_info", GET_PROCESS_INFO_HASH)
    }
}
