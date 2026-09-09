package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID

// GENERATED desktop/Android companion for PhysicsServer3D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP PhysicsServer3D waits on: ptrcallWithRIDAndLongArgRetVariantScalar,
//   ptrcallWithRIDAndVariantArg, ptrcallWithRIDArgRetVariantScalar,
//   ptrcallWithRIDCallableVariantArgs, ptrcallWithRIDLongAndVariantArgs
// Index: docs/contributing/ios-shape-gap.md

/**
 * Sets the shape data that configures the shape. The `data` to be passed depends on the shape's
 * type (see `shape_get_type`): - `SHAPE_WORLD_BOUNDARY`: a `Plane`, - `SHAPE_SEPARATION_RAY`: a
 * dictionary containing the key `"length"` with a `float` value and the key `"slide_on_slope"`
 * with a `bool` value, - `SHAPE_SPHERE`: a `float` that is the radius of the sphere, -
 * `SHAPE_BOX`: a `Vector3` containing the half-extents of the box, - `SHAPE_CAPSULE`: a dictionary
 * containing the keys `"height"` and `"radius"` with `float` values, - `SHAPE_CYLINDER`: a
 * dictionary containing the keys `"height"` and `"radius"` with `float` values, -
 * `SHAPE_CONVEX_POLYGON`: a `PackedVector3Array` of points defining a convex polygon (the shape
 * will be the convex hull of the points), - `SHAPE_CONCAVE_POLYGON`: a dictionary containing the
 * key `"faces"` with a `PackedVector3Array` value (with a length divisible by 3, so that each
 * 3-tuple of points forms a face) and the key `"backface_collision"` with a `bool` value, -
 * `SHAPE_HEIGHTMAP`: a dictionary containing the keys `"width"` and `"depth"` with `int` values,
 * and the key `"heights"` with a value that is a packed array of `float`s of length `width *
 * depth` (that is a `PackedFloat32Array`, or a `PackedFloat64Array` if Godot was compiled with the
 * `precision=double` option), and optionally the keys `"min_height"` and `"max_height"` with
 * `float` values, - `SHAPE_SOFT_BODY`: the input `data` is ignored and this method has no effect,
 * - `SHAPE_CUSTOM`: the input `data` is interpreted by a custom physics server, if it supports
 * custom shapes.
 *
 * Generated from Godot docs: PhysicsServer3D.shape_set_data
 */
fun PhysicsServer3D.shapeSetData(shape: RID, data: Any?) {
    ObjectCalls.ptrcallWithRIDAndVariantArg(shapeSetDataBind, physicsServer3DSingleton, shape, data)
}

/**
 * Returns the shape data that configures the shape, such as the half-extents of a box or the
 * triangles of a concave (trimesh) shape. See `shape_set_data` for the precise format of this data
 * in each case.
 *
 * Generated from Godot docs: PhysicsServer3D.shape_get_data
 */
fun PhysicsServer3D.shapeGetData(shape: RID): Any? {
    return ObjectCalls.ptrcallWithRIDArgRetVariantScalar(shapeGetDataBind, physicsServer3DSingleton, shape)
}

/**
 * Sets the value for an area parameter. A list of available parameters is on the `AreaParameter`
 * constants.
 *
 * Generated from Godot docs: PhysicsServer3D.area_set_param
 */
fun PhysicsServer3D.areaSetParam(area: RID, param: Long, value: Any?) {
    ObjectCalls.ptrcallWithRIDLongAndVariantArgs(areaSetParamBind, physicsServer3DSingleton, area, param, value)
}

/**
 * Returns an area parameter value. A list of available parameters is on the `AreaParameter`
 * constants.
 *
 * Generated from Godot docs: PhysicsServer3D.area_get_param
 */
fun PhysicsServer3D.areaGetParam(area: RID, param: Long): Any? {
    return ObjectCalls.ptrcallWithRIDAndLongArgRetVariantScalar(areaGetParamBind, physicsServer3DSingleton, area, param)
}

/**
 * Sets a body parameter. A list of available parameters is on the `BodyParameter` constants.
 *
 * Generated from Godot docs: PhysicsServer3D.body_set_param
 */
fun PhysicsServer3D.bodySetParam(body: RID, param: Long, value: Any?) {
    ObjectCalls.ptrcallWithRIDLongAndVariantArgs(bodySetParamBind, physicsServer3DSingleton, body, param, value)
}

/**
 * Returns the value of a body parameter. A list of available parameters is on the `BodyParameter`
 * constants.
 *
 * Generated from Godot docs: PhysicsServer3D.body_get_param
 */
fun PhysicsServer3D.bodyGetParam(body: RID, param: Long): Any? {
    return ObjectCalls.ptrcallWithRIDAndLongArgRetVariantScalar(bodyGetParamBind, physicsServer3DSingleton, body, param)
}

/**
 * Sets a body state.
 *
 * Generated from Godot docs: PhysicsServer3D.body_set_state
 */
fun PhysicsServer3D.bodySetState(body: RID, state: Long, value: Any?) {
    ObjectCalls.ptrcallWithRIDLongAndVariantArgs(bodySetStateBind, physicsServer3DSingleton, body, state, value)
}

/**
 * Returns a body state.
 *
 * Generated from Godot docs: PhysicsServer3D.body_get_state
 */
fun PhysicsServer3D.bodyGetState(body: RID, state: Long): Any? {
    return ObjectCalls.ptrcallWithRIDAndLongArgRetVariantScalar(bodyGetStateBind, physicsServer3DSingleton, body, state)
}

/**
 * Sets the body's custom force integration callback function to `callable`. Use an empty
 * `Callable` (`Callable()`) to clear the custom callback. The function `callable` will be called
 * every physics tick, before the standard force integration (see
 * `body_set_omit_force_integration`). It can be used for example to update the body's linear and
 * angular velocity based on contact with other bodies. If `userdata` is not `null`, the function
 * `callable` must take the following two parameters: 1. `state`: a `PhysicsDirectBodyState3D`,
 * used to retrieve and modify the body's state, 2. `userdata`: a `Variant`; its value will be the
 * `userdata` passed into this method. If `userdata` is `null`, then `callable` must take only the
 * `state` parameter.
 *
 * Generated from Godot docs: PhysicsServer3D.body_set_force_integration_callback
 */
fun PhysicsServer3D.bodySetForceIntegrationCallback(body: RID, callable: GodotCallable, userdata: Any? = null) {
    ObjectCalls.ptrcallWithRIDCallableVariantArgs(bodySetForceIntegrationCallbackBind, physicsServer3DSingleton, body, callable.target.handle, callable.method, userdata)
}

/**
 * Sets the given body state for the given body. Note: Godot's default physics implementation does
 * not support `BODY_STATE_LINEAR_VELOCITY`, `BODY_STATE_ANGULAR_VELOCITY`, `BODY_STATE_SLEEPING`,
 * or `BODY_STATE_CAN_SLEEP`.
 *
 * Generated from Godot docs: PhysicsServer3D.soft_body_set_state
 */
fun PhysicsServer3D.softBodySetState(body: RID, state: Long, variant: Any?) {
    ObjectCalls.ptrcallWithRIDLongAndVariantArgs(softBodySetStateBind, physicsServer3DSingleton, body, state, variant)
}

/**
 * Returns the given soft body state. Note: Godot's default physics implementation does not support
 * `BODY_STATE_LINEAR_VELOCITY`, `BODY_STATE_ANGULAR_VELOCITY`, `BODY_STATE_SLEEPING`, or
 * `BODY_STATE_CAN_SLEEP`.
 *
 * Generated from Godot docs: PhysicsServer3D.soft_body_get_state
 */
fun PhysicsServer3D.softBodyGetState(body: RID, state: Long): Any? {
    return ObjectCalls.ptrcallWithRIDAndLongArgRetVariantScalar(softBodyGetStateBind, physicsServer3DSingleton, body, state)
}

private val physicsServer3DSingleton: MemorySegment by lazy {
    ObjectCalls.getSingleton("PhysicsServer3D")
}

private const val SHAPE_SET_DATA_HASH = 3175752987L
private val shapeSetDataBind by lazy {
    ObjectCalls.getMethodBind("PhysicsServer3D", "shape_set_data", SHAPE_SET_DATA_HASH)
}

private const val SHAPE_GET_DATA_HASH = 4171304767L
private val shapeGetDataBind by lazy {
    ObjectCalls.getMethodBind("PhysicsServer3D", "shape_get_data", SHAPE_GET_DATA_HASH)
}

private const val AREA_SET_PARAM_HASH = 2980114638L
private val areaSetParamBind by lazy {
    ObjectCalls.getMethodBind("PhysicsServer3D", "area_set_param", AREA_SET_PARAM_HASH)
}

private const val AREA_GET_PARAM_HASH = 890056067L
private val areaGetParamBind by lazy {
    ObjectCalls.getMethodBind("PhysicsServer3D", "area_get_param", AREA_GET_PARAM_HASH)
}

private const val BODY_SET_PARAM_HASH = 910941953L
private val bodySetParamBind by lazy {
    ObjectCalls.getMethodBind("PhysicsServer3D", "body_set_param", BODY_SET_PARAM_HASH)
}

private const val BODY_GET_PARAM_HASH = 3385027841L
private val bodyGetParamBind by lazy {
    ObjectCalls.getMethodBind("PhysicsServer3D", "body_get_param", BODY_GET_PARAM_HASH)
}

private const val BODY_SET_STATE_HASH = 599977762L
private val bodySetStateBind by lazy {
    ObjectCalls.getMethodBind("PhysicsServer3D", "body_set_state", BODY_SET_STATE_HASH)
}

private const val BODY_GET_STATE_HASH = 1850449534L
private val bodyGetStateBind by lazy {
    ObjectCalls.getMethodBind("PhysicsServer3D", "body_get_state", BODY_GET_STATE_HASH)
}

private const val BODY_SET_FORCE_INTEGRATION_CALLBACK_HASH = 3059434249L
private val bodySetForceIntegrationCallbackBind by lazy {
    ObjectCalls.getMethodBind("PhysicsServer3D", "body_set_force_integration_callback", BODY_SET_FORCE_INTEGRATION_CALLBACK_HASH)
}

private const val SOFT_BODY_SET_STATE_HASH = 599977762L
private val softBodySetStateBind by lazy {
    ObjectCalls.getMethodBind("PhysicsServer3D", "soft_body_set_state", SOFT_BODY_SET_STATE_HASH)
}

private const val SOFT_BODY_GET_STATE_HASH = 1850449534L
private val softBodyGetStateBind by lazy {
    ObjectCalls.getMethodBind("PhysicsServer3D", "soft_body_get_state", SOFT_BODY_GET_STATE_HASH)
}
