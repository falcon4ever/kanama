package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID

// GENERATED desktop/Android companion for PhysicsServer2D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP PhysicsServer2D waits on: ptrcallWithRIDAndLongArgRetVariantScalar,
//   ptrcallWithRIDAndVariantArg, ptrcallWithRIDArgRetVariantScalar,
//   ptrcallWithRIDCallableVariantArgs, ptrcallWithRIDLongAndVariantArgs
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Sets the shape data that defines the configuration of the shape. The `data` to be passed depends
 * on the shape's type (see `shape_get_type`): - `SHAPE_WORLD_BOUNDARY`: an array of length two
 * containing a `Vector2` `normal` direction and a `float` distance `d`, - `SHAPE_SEPARATION_RAY`:
 * a dictionary containing the key `length` with a `float` value and the key `slide_on_slope` with
 * a `bool` value, - `SHAPE_SEGMENT`: a `Rect2` `rect` containing the first point of the segment in
 * `rect.position` and the second point of the segment in `rect.size`, - `SHAPE_CIRCLE`: a `float`
 * `radius`, - `SHAPE_RECTANGLE`: a `Vector2` `half_extents`, - `SHAPE_CAPSULE`: an array of length
 * two (or a `Vector2`) containing a `float` `height` and a `float` `radius`, -
 * `SHAPE_CONVEX_POLYGON`: either a `PackedVector2Array` of points defining a convex polygon in
 * counterclockwise order (the clockwise outward normal of each segment formed by consecutive
 * points is calculated internally), or a `PackedFloat32Array` of length divisible by four so that
 * every 4-tuple of `float`s contains the coordinates of a point followed by the coordinates of the
 * clockwise outward normal vector to the segment between the current point and the next point, -
 * `SHAPE_CONCAVE_POLYGON`: a `PackedVector2Array` of length divisible by two (each pair of points
 * forms one segment). Warning: In the case of `SHAPE_CONVEX_POLYGON`, this method does not check
 * if the points supplied actually form a convex polygon (unlike the `CollisionPolygon2D.polygon`
 * property).
 *
 * Generated from Godot docs: PhysicsServer2D.shape_set_data
 */
fun PhysicsServer2D.shapeSetData(shape: RID, data: Any?) {
    ObjectCalls.ptrcallWithRIDAndVariantArg(shapeSetDataBind, physicsServer2DSingleton, shape, data)
}

/**
 * Returns the shape data that defines the configuration of the shape, such as the half-extents of
 * a rectangle or the segments of a concave shape. See `shape_set_data` for the precise format of
 * this data in each case.
 *
 * Generated from Godot docs: PhysicsServer2D.shape_get_data
 */
fun PhysicsServer2D.shapeGetData(shape: RID): Any? {
    return ObjectCalls.ptrcallWithRIDArgRetVariantScalar(shapeGetDataBind, physicsServer2DSingleton, shape)
}

/**
 * Sets the value of the given area parameter.
 *
 * Generated from Godot docs: PhysicsServer2D.area_set_param
 */
fun PhysicsServer2D.areaSetParam(area: RID, param: Long, value: Any?) {
    ObjectCalls.ptrcallWithRIDLongAndVariantArgs(areaSetParamBind, physicsServer2DSingleton, area, param, value)
}

/**
 * Returns the value of the given area parameter.
 *
 * Generated from Godot docs: PhysicsServer2D.area_get_param
 */
fun PhysicsServer2D.areaGetParam(area: RID, param: Long): Any? {
    return ObjectCalls.ptrcallWithRIDAndLongArgRetVariantScalar(areaGetParamBind, physicsServer2DSingleton, area, param)
}

/**
 * Sets the value of the given body parameter.
 *
 * Generated from Godot docs: PhysicsServer2D.body_set_param
 */
fun PhysicsServer2D.bodySetParam(body: RID, param: Long, value: Any?) {
    ObjectCalls.ptrcallWithRIDLongAndVariantArgs(bodySetParamBind, physicsServer2DSingleton, body, param, value)
}

/**
 * Returns the value of the given body parameter.
 *
 * Generated from Godot docs: PhysicsServer2D.body_get_param
 */
fun PhysicsServer2D.bodyGetParam(body: RID, param: Long): Any? {
    return ObjectCalls.ptrcallWithRIDAndLongArgRetVariantScalar(bodyGetParamBind, physicsServer2DSingleton, body, param)
}

/**
 * Sets the value of a body's state. Note: The state change doesn't take effect immediately. The
 * state will change on the next physics frame.
 *
 * Generated from Godot docs: PhysicsServer2D.body_set_state
 */
fun PhysicsServer2D.bodySetState(body: RID, state: Long, value: Any?) {
    ObjectCalls.ptrcallWithRIDLongAndVariantArgs(bodySetStateBind, physicsServer2DSingleton, body, state, value)
}

/**
 * Returns the value of the given state of the body.
 *
 * Generated from Godot docs: PhysicsServer2D.body_get_state
 */
fun PhysicsServer2D.bodyGetState(body: RID, state: Long): Any? {
    return ObjectCalls.ptrcallWithRIDAndLongArgRetVariantScalar(bodyGetStateBind, physicsServer2DSingleton, body, state)
}

/**
 * Sets the body's custom force integration callback function to `callable`. Use an empty
 * `Callable` (`Callable()`) to clear the custom callback. The function `callable` will be called
 * every physics tick, before the standard force integration (see
 * `body_set_omit_force_integration`). It can be used for example to update the body's linear and
 * angular velocity based on contact with other bodies. If `userdata` is not `null`, the function
 * `callable` must take the following two parameters: 1. `state`: a `PhysicsDirectBodyState2D` used
 * to retrieve and modify the body's state, 2. `userdata`: a `Variant`; its value will be the
 * `userdata` passed into this method. If `userdata` is `null`, then `callable` must take only the
 * `state` parameter.
 *
 * Generated from Godot docs: PhysicsServer2D.body_set_force_integration_callback
 */
fun PhysicsServer2D.bodySetForceIntegrationCallback(body: RID, callable: GodotCallable, userdata: Any? = null) {
    ObjectCalls.ptrcallWithRIDCallableVariantArgs(bodySetForceIntegrationCallbackBind, physicsServer2DSingleton, body, callable.target.handle, callable.method, userdata)
}

private val physicsServer2DSingleton: MemorySegment by lazy {
    ObjectCalls.getSingleton("PhysicsServer2D")
}

private const val SHAPE_SET_DATA_HASH = 3175752987L
private val shapeSetDataBind by lazy {
    ObjectCalls.getMethodBind("PhysicsServer2D", "shape_set_data", SHAPE_SET_DATA_HASH)
}

private const val SHAPE_GET_DATA_HASH = 4171304767L
private val shapeGetDataBind by lazy {
    ObjectCalls.getMethodBind("PhysicsServer2D", "shape_get_data", SHAPE_GET_DATA_HASH)
}

private const val AREA_SET_PARAM_HASH = 1257146028L
private val areaSetParamBind by lazy {
    ObjectCalls.getMethodBind("PhysicsServer2D", "area_set_param", AREA_SET_PARAM_HASH)
}

private const val AREA_GET_PARAM_HASH = 3047435120L
private val areaGetParamBind by lazy {
    ObjectCalls.getMethodBind("PhysicsServer2D", "area_get_param", AREA_GET_PARAM_HASH)
}

private const val BODY_SET_PARAM_HASH = 2715630609L
private val bodySetParamBind by lazy {
    ObjectCalls.getMethodBind("PhysicsServer2D", "body_set_param", BODY_SET_PARAM_HASH)
}

private const val BODY_GET_PARAM_HASH = 3208033526L
private val bodyGetParamBind by lazy {
    ObjectCalls.getMethodBind("PhysicsServer2D", "body_get_param", BODY_GET_PARAM_HASH)
}

private const val BODY_SET_STATE_HASH = 1706355209L
private val bodySetStateBind by lazy {
    ObjectCalls.getMethodBind("PhysicsServer2D", "body_set_state", BODY_SET_STATE_HASH)
}

private const val BODY_GET_STATE_HASH = 4036367961L
private val bodyGetStateBind by lazy {
    ObjectCalls.getMethodBind("PhysicsServer2D", "body_get_state", BODY_GET_STATE_HASH)
}

private const val BODY_SET_FORCE_INTEGRATION_CALLBACK_HASH = 3059434249L
private val bodySetForceIntegrationCallbackBind by lazy {
    ObjectCalls.getMethodBind("PhysicsServer2D", "body_set_force_integration_callback", BODY_SET_FORCE_INTEGRATION_CALLBACK_HASH)
}
