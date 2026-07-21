package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Transform2D
import net.multigesture.kanama.types.Vector2

/**
 * A server interface for low-level 2D physics access.
 *
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

    /**
     * Creates a 2D world boundary shape in the physics server, and returns the `RID` that identifies
     * it. Use `shape_set_data` to set the shape's normal direction and distance properties.
     *
     * Generated from Godot docs: PhysicsServer2D.world_boundary_shape_create
     */
    @JvmStatic
    fun worldBoundaryShapeCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(worldBoundaryShapeCreateBind, singleton)
    }

    /**
     * Creates a 2D separation ray shape in the physics server, and returns the `RID` that identifies
     * it. Use `shape_set_data` to set the shape's `length` and `slide_on_slope` properties.
     *
     * Generated from Godot docs: PhysicsServer2D.separation_ray_shape_create
     */
    @JvmStatic
    fun separationRayShapeCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(separationRayShapeCreateBind, singleton)
    }

    /**
     * Creates a 2D segment shape in the physics server, and returns the `RID` that identifies it. Use
     * `shape_set_data` to set the segment's start and end points.
     *
     * Generated from Godot docs: PhysicsServer2D.segment_shape_create
     */
    @JvmStatic
    fun segmentShapeCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(segmentShapeCreateBind, singleton)
    }

    /**
     * Creates a 2D circle shape in the physics server, and returns the `RID` that identifies it. Use
     * `shape_set_data` to set the circle's radius.
     *
     * Generated from Godot docs: PhysicsServer2D.circle_shape_create
     */
    @JvmStatic
    fun circleShapeCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(circleShapeCreateBind, singleton)
    }

    /**
     * Creates a 2D rectangle shape in the physics server, and returns the `RID` that identifies it.
     * Use `shape_set_data` to set the rectangle's half-extents.
     *
     * Generated from Godot docs: PhysicsServer2D.rectangle_shape_create
     */
    @JvmStatic
    fun rectangleShapeCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(rectangleShapeCreateBind, singleton)
    }

    /**
     * Creates a 2D capsule shape in the physics server, and returns the `RID` that identifies it. Use
     * `shape_set_data` to set the capsule's height and radius.
     *
     * Generated from Godot docs: PhysicsServer2D.capsule_shape_create
     */
    @JvmStatic
    fun capsuleShapeCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(capsuleShapeCreateBind, singleton)
    }

    /**
     * Creates a 2D convex polygon shape in the physics server, and returns the `RID` that identifies
     * it. Use `shape_set_data` to set the convex polygon's points.
     *
     * Generated from Godot docs: PhysicsServer2D.convex_polygon_shape_create
     */
    @JvmStatic
    fun convexPolygonShapeCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(convexPolygonShapeCreateBind, singleton)
    }

    /**
     * Creates a 2D concave polygon shape in the physics server, and returns the `RID` that identifies
     * it. Use `shape_set_data` to set the concave polygon's segments.
     *
     * Generated from Godot docs: PhysicsServer2D.concave_polygon_shape_create
     */
    @JvmStatic
    fun concavePolygonShapeCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(concavePolygonShapeCreateBind, singleton)
    }

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
    @JvmStatic
    fun shapeSetData(shape: RID, data: Any?) {
        ObjectCalls.ptrcallWithRIDAndVariantArg(shapeSetDataBind, singleton, shape, data)
    }

    /**
     * Returns the shape's type.
     *
     * Generated from Godot docs: PhysicsServer2D.shape_get_type
     */
    @JvmStatic
    fun shapeGetType(shape: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(shapeGetTypeBind, singleton, shape)
    }

    /**
     * Returns the shape data that defines the configuration of the shape, such as the half-extents of
     * a rectangle or the segments of a concave shape. See `shape_set_data` for the precise format of
     * this data in each case.
     *
     * Generated from Godot docs: PhysicsServer2D.shape_get_data
     */
    @JvmStatic
    fun shapeGetData(shape: RID): Any? {
        return ObjectCalls.ptrcallWithRIDArgRetVariantScalar(shapeGetDataBind, singleton, shape)
    }

    /**
     * Creates a 2D space in the physics server, and returns the `RID` that identifies it. A space
     * contains bodies and areas, and controls the stepping of the physics simulation of the objects in
     * it.
     *
     * Generated from Godot docs: PhysicsServer2D.space_create
     */
    @JvmStatic
    fun spaceCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(spaceCreateBind, singleton)
    }

    /**
     * Activates or deactivates the space. If `active` is `false`, then the physics server will not do
     * anything with this space in its physics step.
     *
     * Generated from Godot docs: PhysicsServer2D.space_set_active
     */
    @JvmStatic
    fun spaceSetActive(space: RID, active: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(spaceSetActiveBind, singleton, space, active)
    }

    /**
     * Returns `true` if the space is active.
     *
     * Generated from Godot docs: PhysicsServer2D.space_is_active
     */
    @JvmStatic
    fun spaceIsActive(space: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(spaceIsActiveBind, singleton, space)
    }

    /**
     * Sets the value of the given space parameter.
     *
     * Generated from Godot docs: PhysicsServer2D.space_set_param
     */
    @JvmStatic
    fun spaceSetParam(space: RID, param: Long, value: Double) {
        ObjectCalls.ptrcallWithRIDLongAndDoubleArgs(spaceSetParamBind, singleton, space, param, value)
    }

    /**
     * Returns the value of the given space parameter.
     *
     * Generated from Godot docs: PhysicsServer2D.space_get_param
     */
    @JvmStatic
    fun spaceGetParam(space: RID, param: Long): Double {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetDouble(spaceGetParamBind, singleton, space, param)
    }

    /**
     * Returns the state of a space, a `PhysicsDirectSpaceState2D`. This object can be used for
     * collision/intersection queries.
     *
     * Generated from Godot docs: PhysicsServer2D.space_get_direct_state
     */
    @JvmStatic
    fun spaceGetDirectState(space: RID): PhysicsDirectSpaceState2D? {
        return PhysicsDirectSpaceState2D.wrap(ObjectCalls.ptrcallWithRIDArgRetObject(spaceGetDirectStateBind, singleton, space))
    }

    /**
     * Creates a 2D area object in the physics server, and returns the `RID` that identifies it. The
     * default settings for the created area include a collision layer and mask set to `1`, and
     * `monitorable` set to `false`. Use `area_add_shape` to add shapes to it, use `area_set_transform`
     * to set its transform, and use `area_set_space` to add the area to a space. If you want the area
     * to be detectable use `area_set_monitorable`.
     *
     * Generated from Godot docs: PhysicsServer2D.area_create
     */
    @JvmStatic
    fun areaCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(areaCreateBind, singleton)
    }

    /**
     * Adds the area to the given space, after removing the area from the previously assigned space (if
     * any). Note: To remove an area from a space without immediately adding it back elsewhere, use
     * `PhysicsServer2D.area_set_space(area, RID())`.
     *
     * Generated from Godot docs: PhysicsServer2D.area_set_space
     */
    @JvmStatic
    fun areaSetSpace(area: RID, space: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(areaSetSpaceBind, singleton, area, space)
    }

    /**
     * Returns the `RID` of the space assigned to the area. Returns an empty `RID` if no space is
     * assigned.
     *
     * Generated from Godot docs: PhysicsServer2D.area_get_space
     */
    @JvmStatic
    fun areaGetSpace(area: RID): RID {
        return ObjectCalls.ptrcallWithRIDArgRetRID(areaGetSpaceBind, singleton, area)
    }

    /**
     * Adds a shape to the area, with the given local transform. The shape (together with its
     * `transform` and `disabled` properties) is added to an array of shapes, and the shapes of an area
     * are usually referenced by their index in this array.
     *
     * Generated from Godot docs: PhysicsServer2D.area_add_shape
     */
    @JvmStatic
    fun areaAddShape(area: RID, shape: RID, transform: Transform2D, disabled: Boolean = false) {
        ObjectCalls.ptrcallWithTwoRIDTransform2DBoolArgs(areaAddShapeBind, singleton, area, shape, transform, disabled)
    }

    /**
     * Replaces the area's shape at the given index by another shape, while not affecting the
     * `transform` and `disabled` properties at the same index.
     *
     * Generated from Godot docs: PhysicsServer2D.area_set_shape
     */
    @JvmStatic
    fun areaSetShape(area: RID, shapeIdx: Int, shape: RID) {
        ObjectCalls.ptrcallWithRIDIntAndRIDArgs(areaSetShapeBind, singleton, area, shapeIdx, shape)
    }

    /**
     * Sets the local transform matrix of the area's shape with the given index.
     *
     * Generated from Godot docs: PhysicsServer2D.area_set_shape_transform
     */
    @JvmStatic
    fun areaSetShapeTransform(area: RID, shapeIdx: Int, transform: Transform2D) {
        ObjectCalls.ptrcallWithRIDIntAndTransform2DArg(areaSetShapeTransformBind, singleton, area, shapeIdx, transform)
    }

    /**
     * Sets the disabled property of the area's shape with the given index. If `disabled` is `true`,
     * then the shape will not detect any other shapes entering or exiting it.
     *
     * Generated from Godot docs: PhysicsServer2D.area_set_shape_disabled
     */
    @JvmStatic
    fun areaSetShapeDisabled(area: RID, shapeIdx: Int, disabled: Boolean) {
        ObjectCalls.ptrcallWithRIDIntAndBoolArgs(areaSetShapeDisabledBind, singleton, area, shapeIdx, disabled)
    }

    /**
     * Returns the number of shapes added to the area.
     *
     * Generated from Godot docs: PhysicsServer2D.area_get_shape_count
     */
    @JvmStatic
    fun areaGetShapeCount(area: RID): Int {
        return ObjectCalls.ptrcallWithRIDArgRetInt(areaGetShapeCountBind, singleton, area)
    }

    /**
     * Returns the `RID` of the shape with the given index in the area's array of shapes.
     *
     * Generated from Godot docs: PhysicsServer2D.area_get_shape
     */
    @JvmStatic
    fun areaGetShape(area: RID, shapeIdx: Int): RID {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetRID(areaGetShapeBind, singleton, area, shapeIdx)
    }

    /**
     * Returns the local transform matrix of the shape with the given index in the area's array of
     * shapes.
     *
     * Generated from Godot docs: PhysicsServer2D.area_get_shape_transform
     */
    @JvmStatic
    fun areaGetShapeTransform(area: RID, shapeIdx: Int): Transform2D {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetTransform2D(areaGetShapeTransformBind, singleton, area, shapeIdx)
    }

    /**
     * Removes the shape with the given index from the area's array of shapes. The shape itself is not
     * deleted, so it can continue to be used elsewhere or added back later. As a result of this
     * operation, the area's shapes which used to have indices higher than `shape_idx` will have their
     * index decreased by one.
     *
     * Generated from Godot docs: PhysicsServer2D.area_remove_shape
     */
    @JvmStatic
    fun areaRemoveShape(area: RID, shapeIdx: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(areaRemoveShapeBind, singleton, area, shapeIdx)
    }

    /**
     * Removes all shapes from the area. This does not delete the shapes themselves, so they can
     * continue to be used elsewhere or added back later.
     *
     * Generated from Godot docs: PhysicsServer2D.area_clear_shapes
     */
    @JvmStatic
    fun areaClearShapes(area: RID) {
        ObjectCalls.ptrcallWithRIDArg(areaClearShapesBind, singleton, area)
    }

    /**
     * Assigns the area to one or many physics layers, via a bitmask.
     *
     * Generated from Godot docs: PhysicsServer2D.area_set_collision_layer
     */
    @JvmStatic
    fun areaSetCollisionLayer(area: RID, layer: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(areaSetCollisionLayerBind, singleton, area, layer)
    }

    /**
     * Returns the physics layer or layers the area belongs to, as a bitmask.
     *
     * Generated from Godot docs: PhysicsServer2D.area_get_collision_layer
     */
    @JvmStatic
    fun areaGetCollisionLayer(area: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetUInt32(areaGetCollisionLayerBind, singleton, area)
    }

    /**
     * Sets which physics layers the area will monitor, via a bitmask.
     *
     * Generated from Godot docs: PhysicsServer2D.area_set_collision_mask
     */
    @JvmStatic
    fun areaSetCollisionMask(area: RID, mask: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(areaSetCollisionMaskBind, singleton, area, mask)
    }

    /**
     * Returns the physics layer or layers the area can contact with, as a bitmask.
     *
     * Generated from Godot docs: PhysicsServer2D.area_get_collision_mask
     */
    @JvmStatic
    fun areaGetCollisionMask(area: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetUInt32(areaGetCollisionMaskBind, singleton, area)
    }

    /**
     * Sets the value of the given area parameter.
     *
     * Generated from Godot docs: PhysicsServer2D.area_set_param
     */
    @JvmStatic
    fun areaSetParam(area: RID, param: Long, value: Any?) {
        ObjectCalls.ptrcallWithRIDLongAndVariantArgs(areaSetParamBind, singleton, area, param, value)
    }

    /**
     * Sets the transform matrix of the area.
     *
     * Generated from Godot docs: PhysicsServer2D.area_set_transform
     */
    @JvmStatic
    fun areaSetTransform(area: RID, transform: Transform2D) {
        ObjectCalls.ptrcallWithRIDAndTransform2DArg(areaSetTransformBind, singleton, area, transform)
    }

    /**
     * Returns the value of the given area parameter.
     *
     * Generated from Godot docs: PhysicsServer2D.area_get_param
     */
    @JvmStatic
    fun areaGetParam(area: RID, param: Long): Any? {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetVariantScalar(areaGetParamBind, singleton, area, param)
    }

    /**
     * Returns the transform matrix of the area.
     *
     * Generated from Godot docs: PhysicsServer2D.area_get_transform
     */
    @JvmStatic
    fun areaGetTransform(area: RID): Transform2D {
        return ObjectCalls.ptrcallWithRIDArgRetTransform2D(areaGetTransformBind, singleton, area)
    }

    /**
     * Attaches the `ObjectID` of an `Object` to the area. Use `Object.get_instance_id` to get the
     * `ObjectID` of a `CollisionObject2D`.
     *
     * Generated from Godot docs: PhysicsServer2D.area_attach_object_instance_id
     */
    @JvmStatic
    fun areaAttachObjectInstanceId(area: RID, id: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(areaAttachObjectInstanceIdBind, singleton, area, id)
    }

    /**
     * Returns the `ObjectID` attached to the area. Use `@GlobalScope.instance_from_id` to retrieve an
     * `Object` from a nonzero `ObjectID`.
     *
     * Generated from Godot docs: PhysicsServer2D.area_get_object_instance_id
     */
    @JvmStatic
    fun areaGetObjectInstanceId(area: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(areaGetObjectInstanceIdBind, singleton, area)
    }

    /**
     * Attaches the `ObjectID` of a canvas to the area. Use `Object.get_instance_id` to get the
     * `ObjectID` of a `CanvasLayer`.
     *
     * Generated from Godot docs: PhysicsServer2D.area_attach_canvas_instance_id
     */
    @JvmStatic
    fun areaAttachCanvasInstanceId(area: RID, id: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(areaAttachCanvasInstanceIdBind, singleton, area, id)
    }

    /**
     * Returns the `ObjectID` of the canvas attached to the area. Use `@GlobalScope.instance_from_id`
     * to retrieve a `CanvasLayer` from a nonzero `ObjectID`.
     *
     * Generated from Godot docs: PhysicsServer2D.area_get_canvas_instance_id
     */
    @JvmStatic
    fun areaGetCanvasInstanceId(area: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(areaGetCanvasInstanceIdBind, singleton, area)
    }

    /**
     * Sets the area's body monitor callback. This callback will be called when any other (shape of a)
     * body enters or exits (a shape of) the given area, and must take the following five parameters:
     * 1. an integer `status`: either `AREA_BODY_ADDED` or `AREA_BODY_REMOVED` depending on whether the
     * other body shape entered or exited the area, 2. an `RID` `body_rid`: the `RID` of the body that
     * entered or exited the area, 3. an integer `instance_id`: the `ObjectID` attached to the body, 4.
     * an integer `body_shape_idx`: the index of the shape of the body that entered or exited the area,
     * 5. an integer `self_shape_idx`: the index of the shape of the area where the body entered or
     * exited. By counting (or keeping track of) the shapes that enter and exit, it can be determined
     * if a body (with all its shapes) is entering for the first time or exiting for the last time.
     *
     * Generated from Godot docs: PhysicsServer2D.area_set_monitor_callback
     */
    @JvmStatic
    fun areaSetMonitorCallback(area: RID, callback: GodotCallable) {
        ObjectCalls.ptrcallWithRIDCallableArgs(areaSetMonitorCallbackBind, singleton, area, callback.target.handle, callback.method)
    }

    /**
     * Sets the area's area monitor callback. This callback will be called when any other (shape of an)
     * area enters or exits (a shape of) the given area, and must take the following five parameters:
     * 1. an integer `status`: either `AREA_BODY_ADDED` or `AREA_BODY_REMOVED` depending on whether the
     * other area's shape entered or exited the area, 2. an `RID` `area_rid`: the `RID` of the other
     * area that entered or exited the area, 3. an integer `instance_id`: the `ObjectID` attached to
     * the other area, 4. an integer `area_shape_idx`: the index of the shape of the other area that
     * entered or exited the area, 5. an integer `self_shape_idx`: the index of the shape of the area
     * where the other area entered or exited. By counting (or keeping track of) the shapes that enter
     * and exit, it can be determined if an area (with all its shapes) is entering for the first time
     * or exiting for the last time.
     *
     * Generated from Godot docs: PhysicsServer2D.area_set_area_monitor_callback
     */
    @JvmStatic
    fun areaSetAreaMonitorCallback(area: RID, callback: GodotCallable) {
        ObjectCalls.ptrcallWithRIDCallableArgs(areaSetAreaMonitorCallbackBind, singleton, area, callback.target.handle, callback.method)
    }

    /**
     * Sets whether the area is monitorable or not. If `monitorable` is `true`, the area monitoring
     * callback of other areas will be called when this area enters or exits them.
     *
     * Generated from Godot docs: PhysicsServer2D.area_set_monitorable
     */
    @JvmStatic
    fun areaSetMonitorable(area: RID, monitorable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(areaSetMonitorableBind, singleton, area, monitorable)
    }

    /**
     * Creates a 2D body object in the physics server, and returns the `RID` that identifies it. The
     * default settings for the created area include a collision layer and mask set to `1`, and body
     * mode set to `BODY_MODE_RIGID`. Use `body_add_shape` to add shapes to it, use `body_set_state` to
     * set its transform, and use `body_set_space` to add the body to a space.
     *
     * Generated from Godot docs: PhysicsServer2D.body_create
     */
    @JvmStatic
    fun bodyCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(bodyCreateBind, singleton)
    }

    /**
     * Adds the body to the given space, after removing the body from the previously assigned space (if
     * any). If the body's mode is set to `BODY_MODE_RIGID`, then adding the body to a space will have
     * the following additional effects: - If the parameter `BODY_PARAM_CENTER_OF_MASS` has never been
     * set explicitly, then the value of that parameter will be recalculated based on the body's
     * shapes. - If the parameter `BODY_PARAM_INERTIA` is set to a value `<= 0.0`, then the value of
     * that parameter will be recalculated based on the body's shapes, mass, and center of mass. Note:
     * To remove a body from a space without immediately adding it back elsewhere, use
     * `PhysicsServer2D.body_set_space(body, RID())`.
     *
     * Generated from Godot docs: PhysicsServer2D.body_set_space
     */
    @JvmStatic
    fun bodySetSpace(body: RID, space: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(bodySetSpaceBind, singleton, body, space)
    }

    /**
     * Returns the `RID` of the space assigned to the body. Returns an empty `RID` if no space is
     * assigned.
     *
     * Generated from Godot docs: PhysicsServer2D.body_get_space
     */
    @JvmStatic
    fun bodyGetSpace(body: RID): RID {
        return ObjectCalls.ptrcallWithRIDArgRetRID(bodyGetSpaceBind, singleton, body)
    }

    /**
     * Sets the body's mode.
     *
     * Generated from Godot docs: PhysicsServer2D.body_set_mode
     */
    @JvmStatic
    fun bodySetMode(body: RID, mode: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(bodySetModeBind, singleton, body, mode)
    }

    /**
     * Returns the body's mode.
     *
     * Generated from Godot docs: PhysicsServer2D.body_get_mode
     */
    @JvmStatic
    fun bodyGetMode(body: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(bodyGetModeBind, singleton, body)
    }

    /**
     * Adds a shape to the area, with the given local transform. The shape (together with its
     * `transform` and `disabled` properties) is added to an array of shapes, and the shapes of a body
     * are usually referenced by their index in this array.
     *
     * Generated from Godot docs: PhysicsServer2D.body_add_shape
     */
    @JvmStatic
    fun bodyAddShape(body: RID, shape: RID, transform: Transform2D, disabled: Boolean = false) {
        ObjectCalls.ptrcallWithTwoRIDTransform2DBoolArgs(bodyAddShapeBind, singleton, body, shape, transform, disabled)
    }

    /**
     * Replaces the body's shape at the given index by another shape, while not affecting the
     * `transform`, `disabled`, and one-way collision properties at the same index.
     *
     * Generated from Godot docs: PhysicsServer2D.body_set_shape
     */
    @JvmStatic
    fun bodySetShape(body: RID, shapeIdx: Int, shape: RID) {
        ObjectCalls.ptrcallWithRIDIntAndRIDArgs(bodySetShapeBind, singleton, body, shapeIdx, shape)
    }

    /**
     * Sets the local transform matrix of the body's shape with the given index.
     *
     * Generated from Godot docs: PhysicsServer2D.body_set_shape_transform
     */
    @JvmStatic
    fun bodySetShapeTransform(body: RID, shapeIdx: Int, transform: Transform2D) {
        ObjectCalls.ptrcallWithRIDIntAndTransform2DArg(bodySetShapeTransformBind, singleton, body, shapeIdx, transform)
    }

    /**
     * Returns the number of shapes added to the body.
     *
     * Generated from Godot docs: PhysicsServer2D.body_get_shape_count
     */
    @JvmStatic
    fun bodyGetShapeCount(body: RID): Int {
        return ObjectCalls.ptrcallWithRIDArgRetInt(bodyGetShapeCountBind, singleton, body)
    }

    /**
     * Returns the `RID` of the shape with the given index in the body's array of shapes.
     *
     * Generated from Godot docs: PhysicsServer2D.body_get_shape
     */
    @JvmStatic
    fun bodyGetShape(body: RID, shapeIdx: Int): RID {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetRID(bodyGetShapeBind, singleton, body, shapeIdx)
    }

    /**
     * Returns the local transform matrix of the shape with the given index in the area's array of
     * shapes.
     *
     * Generated from Godot docs: PhysicsServer2D.body_get_shape_transform
     */
    @JvmStatic
    fun bodyGetShapeTransform(body: RID, shapeIdx: Int): Transform2D {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetTransform2D(bodyGetShapeTransformBind, singleton, body, shapeIdx)
    }

    /**
     * Removes the shape with the given index from the body's array of shapes. The shape itself is not
     * deleted, so it can continue to be used elsewhere or added back later. As a result of this
     * operation, the body's shapes which used to have indices higher than `shape_idx` will have their
     * index decreased by one.
     *
     * Generated from Godot docs: PhysicsServer2D.body_remove_shape
     */
    @JvmStatic
    fun bodyRemoveShape(body: RID, shapeIdx: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(bodyRemoveShapeBind, singleton, body, shapeIdx)
    }

    /**
     * Removes all shapes from the body. This does not delete the shapes themselves, so they can
     * continue to be used elsewhere or added back later.
     *
     * Generated from Godot docs: PhysicsServer2D.body_clear_shapes
     */
    @JvmStatic
    fun bodyClearShapes(body: RID) {
        ObjectCalls.ptrcallWithRIDArg(bodyClearShapesBind, singleton, body)
    }

    /**
     * Sets the disabled property of the body's shape with the given index. If `disabled` is `true`,
     * then the shape will be ignored in all collision detection.
     *
     * Generated from Godot docs: PhysicsServer2D.body_set_shape_disabled
     */
    @JvmStatic
    fun bodySetShapeDisabled(body: RID, shapeIdx: Int, disabled: Boolean) {
        ObjectCalls.ptrcallWithRIDIntAndBoolArgs(bodySetShapeDisabledBind, singleton, body, shapeIdx, disabled)
    }

    /**
     * Sets the one-way collision properties of the body's shape with the given index. If `enable` is
     * `true`, the one-way collision direction given by `direction` in the shape's local space (that is
     * `body_get_shape_transform(body, shape_idx).basis_xform(direction).normalized()` in the body's
     * local space) will be used to ignore collisions with the shape in the opposite direction, and to
     * ensure depenetration of kinematic bodies happens in this direction.
     *
     * Generated from Godot docs: PhysicsServer2D.body_set_shape_as_one_way_collision
     */
    @JvmStatic
    fun bodySetShapeAsOneWayCollision(body: RID, shapeIdx: Int, enable: Boolean, margin: Double, direction: Vector2) {
        ObjectCalls.ptrcallWithRIDIntBoolDoubleVector2Args(bodySetShapeAsOneWayCollisionBind, singleton, body, shapeIdx, enable, margin, direction)
    }

    /**
     * Attaches the `ObjectID` of an `Object` to the body. Use `Object.get_instance_id` to get the
     * `ObjectID` of a `CollisionObject2D`.
     *
     * Generated from Godot docs: PhysicsServer2D.body_attach_object_instance_id
     */
    @JvmStatic
    fun bodyAttachObjectInstanceId(body: RID, id: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(bodyAttachObjectInstanceIdBind, singleton, body, id)
    }

    /**
     * Returns the `ObjectID` attached to the body. Use `@GlobalScope.instance_from_id` to retrieve an
     * `Object` from a nonzero `ObjectID`.
     *
     * Generated from Godot docs: PhysicsServer2D.body_get_object_instance_id
     */
    @JvmStatic
    fun bodyGetObjectInstanceId(body: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(bodyGetObjectInstanceIdBind, singleton, body)
    }

    /**
     * Attaches the `ObjectID` of a canvas to the body. Use `Object.get_instance_id` to get the
     * `ObjectID` of a `CanvasLayer`.
     *
     * Generated from Godot docs: PhysicsServer2D.body_attach_canvas_instance_id
     */
    @JvmStatic
    fun bodyAttachCanvasInstanceId(body: RID, id: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(bodyAttachCanvasInstanceIdBind, singleton, body, id)
    }

    /**
     * Returns the `ObjectID` of the canvas attached to the body. Use `@GlobalScope.instance_from_id`
     * to retrieve a `CanvasLayer` from a nonzero `ObjectID`.
     *
     * Generated from Godot docs: PhysicsServer2D.body_get_canvas_instance_id
     */
    @JvmStatic
    fun bodyGetCanvasInstanceId(body: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(bodyGetCanvasInstanceIdBind, singleton, body)
    }

    /**
     * Sets the continuous collision detection mode. Continuous collision detection tries to predict
     * where a moving body would collide in between physics updates, instead of moving it and
     * correcting its movement if it collided.
     *
     * Generated from Godot docs: PhysicsServer2D.body_set_continuous_collision_detection_mode
     */
    @JvmStatic
    fun bodySetContinuousCollisionDetectionMode(body: RID, mode: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(bodySetContinuousCollisionDetectionModeBind, singleton, body, mode)
    }

    /**
     * Returns the body's continuous collision detection mode.
     *
     * Generated from Godot docs: PhysicsServer2D.body_get_continuous_collision_detection_mode
     */
    @JvmStatic
    fun bodyGetContinuousCollisionDetectionMode(body: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(bodyGetContinuousCollisionDetectionModeBind, singleton, body)
    }

    /**
     * Sets the physics layer or layers the body belongs to, via a bitmask.
     *
     * Generated from Godot docs: PhysicsServer2D.body_set_collision_layer
     */
    @JvmStatic
    fun bodySetCollisionLayer(body: RID, layer: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(bodySetCollisionLayerBind, singleton, body, layer)
    }

    /**
     * Returns the physics layer or layers the body belongs to, as a bitmask.
     *
     * Generated from Godot docs: PhysicsServer2D.body_get_collision_layer
     */
    @JvmStatic
    fun bodyGetCollisionLayer(body: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetUInt32(bodyGetCollisionLayerBind, singleton, body)
    }

    /**
     * Sets the physics layer or layers the body can collide with, via a bitmask.
     *
     * Generated from Godot docs: PhysicsServer2D.body_set_collision_mask
     */
    @JvmStatic
    fun bodySetCollisionMask(body: RID, mask: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(bodySetCollisionMaskBind, singleton, body, mask)
    }

    /**
     * Returns the physics layer or layers the body can collide with, as a bitmask.
     *
     * Generated from Godot docs: PhysicsServer2D.body_get_collision_mask
     */
    @JvmStatic
    fun bodyGetCollisionMask(body: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetUInt32(bodyGetCollisionMaskBind, singleton, body)
    }

    /**
     * Sets the body's collision priority. This is used in the depenetration phase of
     * `body_test_motion`. The higher the priority is, the lower the penetration into the body will be.
     *
     * Generated from Godot docs: PhysicsServer2D.body_set_collision_priority
     */
    @JvmStatic
    fun bodySetCollisionPriority(body: RID, priority: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(bodySetCollisionPriorityBind, singleton, body, priority)
    }

    /**
     * Returns the body's collision priority. This is used in the depenetration phase of
     * `body_test_motion`. The higher the priority is, the lower the penetration into the body will be.
     *
     * Generated from Godot docs: PhysicsServer2D.body_get_collision_priority
     */
    @JvmStatic
    fun bodyGetCollisionPriority(body: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(bodyGetCollisionPriorityBind, singleton, body)
    }

    /**
     * Sets the value of the given body parameter.
     *
     * Generated from Godot docs: PhysicsServer2D.body_set_param
     */
    @JvmStatic
    fun bodySetParam(body: RID, param: Long, value: Any?) {
        ObjectCalls.ptrcallWithRIDLongAndVariantArgs(bodySetParamBind, singleton, body, param, value)
    }

    /**
     * Returns the value of the given body parameter.
     *
     * Generated from Godot docs: PhysicsServer2D.body_get_param
     */
    @JvmStatic
    fun bodyGetParam(body: RID, param: Long): Any? {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetVariantScalar(bodyGetParamBind, singleton, body, param)
    }

    /**
     * Restores the default inertia and center of mass of the body based on its shapes. This undoes any
     * custom values previously set using `body_set_param`.
     *
     * Generated from Godot docs: PhysicsServer2D.body_reset_mass_properties
     */
    @JvmStatic
    fun bodyResetMassProperties(body: RID) {
        ObjectCalls.ptrcallWithRIDArg(bodyResetMassPropertiesBind, singleton, body)
    }

    /**
     * Sets the value of a body's state. Note: The state change doesn't take effect immediately. The
     * state will change on the next physics frame.
     *
     * Generated from Godot docs: PhysicsServer2D.body_set_state
     */
    @JvmStatic
    fun bodySetState(body: RID, state: Long, value: Any?) {
        ObjectCalls.ptrcallWithRIDLongAndVariantArgs(bodySetStateBind, singleton, body, state, value)
    }

    /**
     * Returns the value of the given state of the body.
     *
     * Generated from Godot docs: PhysicsServer2D.body_get_state
     */
    @JvmStatic
    fun bodyGetState(body: RID, state: Long): Any? {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetVariantScalar(bodyGetStateBind, singleton, body, state)
    }

    /**
     * Applies a directional impulse to the body, at the body's center of mass. The impulse does not
     * affect rotation. An impulse is time-independent! Applying an impulse every frame would result in
     * a framerate-dependent force. For this reason, it should only be used when simulating one-time
     * impacts (use the "_force" functions otherwise). This is equivalent to using `body_apply_impulse`
     * at the body's center of mass.
     *
     * Generated from Godot docs: PhysicsServer2D.body_apply_central_impulse
     */
    @JvmStatic
    fun bodyApplyCentralImpulse(body: RID, impulse: Vector2) {
        ObjectCalls.ptrcallWithRIDAndVector2Arg(bodyApplyCentralImpulseBind, singleton, body, impulse)
    }

    /**
     * Applies a rotational impulse to the body. The impulse does not affect position. An impulse is
     * time-independent! Applying an impulse every frame would result in a framerate-dependent force.
     * For this reason, it should only be used when simulating one-time impacts (use the "_force"
     * functions otherwise).
     *
     * Generated from Godot docs: PhysicsServer2D.body_apply_torque_impulse
     */
    @JvmStatic
    fun bodyApplyTorqueImpulse(body: RID, impulse: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(bodyApplyTorqueImpulseBind, singleton, body, impulse)
    }

    /**
     * Applies a positioned impulse to the body. The impulse can affect rotation if `position` is
     * different from the body's center of mass. An impulse is time-independent! Applying an impulse
     * every frame would result in a framerate-dependent force. For this reason, it should only be used
     * when simulating one-time impacts (use the "_force" functions otherwise). `position` is the
     * offset from the body origin in global coordinates.
     *
     * Generated from Godot docs: PhysicsServer2D.body_apply_impulse
     */
    @JvmStatic
    fun bodyApplyImpulse(body: RID, impulse: Vector2, position: Vector2 = Vector2(0f, 0f)) {
        ObjectCalls.ptrcallWithRIDAndTwoVector2Args(bodyApplyImpulseBind, singleton, body, impulse, position)
    }

    /**
     * Applies a directional force to the body, at the body's center of mass. The force does not affect
     * rotation. A force is time dependent and meant to be applied every physics update. This is
     * equivalent to using `body_apply_force` at the body's center of mass.
     *
     * Generated from Godot docs: PhysicsServer2D.body_apply_central_force
     */
    @JvmStatic
    fun bodyApplyCentralForce(body: RID, force: Vector2) {
        ObjectCalls.ptrcallWithRIDAndVector2Arg(bodyApplyCentralForceBind, singleton, body, force)
    }

    /**
     * Applies a positioned force to the body. The force can affect rotation if `position` is different
     * from the body's center of mass. A force is time dependent and meant to be applied every physics
     * update. `position` is the offset from the body origin in global coordinates.
     *
     * Generated from Godot docs: PhysicsServer2D.body_apply_force
     */
    @JvmStatic
    fun bodyApplyForce(body: RID, force: Vector2, position: Vector2 = Vector2(0f, 0f)) {
        ObjectCalls.ptrcallWithRIDAndTwoVector2Args(bodyApplyForceBind, singleton, body, force, position)
    }

    /**
     * Applies a rotational force to the body. The force does not affect position. A force is time
     * dependent and meant to be applied every physics update.
     *
     * Generated from Godot docs: PhysicsServer2D.body_apply_torque
     */
    @JvmStatic
    fun bodyApplyTorque(body: RID, torque: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(bodyApplyTorqueBind, singleton, body, torque)
    }

    /**
     * Adds a constant directional force to the body. The force does not affect rotation. The force
     * remains applied over time until cleared with `PhysicsServer2D.body_set_constant_force(body,
     * Vector2(0, 0))`. This is equivalent to using `body_add_constant_force` at the body's center of
     * mass.
     *
     * Generated from Godot docs: PhysicsServer2D.body_add_constant_central_force
     */
    @JvmStatic
    fun bodyAddConstantCentralForce(body: RID, force: Vector2) {
        ObjectCalls.ptrcallWithRIDAndVector2Arg(bodyAddConstantCentralForceBind, singleton, body, force)
    }

    /**
     * Adds a constant positioned force to the body. The force can affect rotation if `position` is
     * different from the body's center of mass. The force remains applied over time until cleared with
     * `PhysicsServer2D.body_set_constant_force(body, Vector2(0, 0))`. `position` is the offset from
     * the body origin in global coordinates.
     *
     * Generated from Godot docs: PhysicsServer2D.body_add_constant_force
     */
    @JvmStatic
    fun bodyAddConstantForce(body: RID, force: Vector2, position: Vector2 = Vector2(0f, 0f)) {
        ObjectCalls.ptrcallWithRIDAndTwoVector2Args(bodyAddConstantForceBind, singleton, body, force, position)
    }

    /**
     * Adds a constant rotational force to the body. The force does not affect position. The force
     * remains applied over time until cleared with `PhysicsServer2D.body_set_constant_torque(body,
     * 0)`.
     *
     * Generated from Godot docs: PhysicsServer2D.body_add_constant_torque
     */
    @JvmStatic
    fun bodyAddConstantTorque(body: RID, torque: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(bodyAddConstantTorqueBind, singleton, body, torque)
    }

    /**
     * Sets the body's total constant positional force applied during each physics update. See
     * `body_add_constant_force` and `body_add_constant_central_force`.
     *
     * Generated from Godot docs: PhysicsServer2D.body_set_constant_force
     */
    @JvmStatic
    fun bodySetConstantForce(body: RID, force: Vector2) {
        ObjectCalls.ptrcallWithRIDAndVector2Arg(bodySetConstantForceBind, singleton, body, force)
    }

    /**
     * Returns the body's total constant positional force applied during each physics update. See
     * `body_add_constant_force` and `body_add_constant_central_force`.
     *
     * Generated from Godot docs: PhysicsServer2D.body_get_constant_force
     */
    @JvmStatic
    fun bodyGetConstantForce(body: RID): Vector2 {
        return ObjectCalls.ptrcallWithRIDArgRetVector2(bodyGetConstantForceBind, singleton, body)
    }

    /**
     * Sets the body's total constant rotational force applied during each physics update. See
     * `body_add_constant_torque`.
     *
     * Generated from Godot docs: PhysicsServer2D.body_set_constant_torque
     */
    @JvmStatic
    fun bodySetConstantTorque(body: RID, torque: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(bodySetConstantTorqueBind, singleton, body, torque)
    }

    /**
     * Returns the body's total constant rotational force applied during each physics update. See
     * `body_add_constant_torque`.
     *
     * Generated from Godot docs: PhysicsServer2D.body_get_constant_torque
     */
    @JvmStatic
    fun bodyGetConstantTorque(body: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(bodyGetConstantTorqueBind, singleton, body)
    }

    /**
     * Modifies the body's linear velocity so that its projection to the axis
     * `axis_velocity.normalized()` is exactly `axis_velocity.length()`. This is useful for jumping
     * behavior.
     *
     * Generated from Godot docs: PhysicsServer2D.body_set_axis_velocity
     */
    @JvmStatic
    fun bodySetAxisVelocity(body: RID, axisVelocity: Vector2) {
        ObjectCalls.ptrcallWithRIDAndVector2Arg(bodySetAxisVelocityBind, singleton, body, axisVelocity)
    }

    /**
     * Adds `excepted_body` to the body's list of collision exceptions, so that collisions with it are
     * ignored.
     *
     * Generated from Godot docs: PhysicsServer2D.body_add_collision_exception
     */
    @JvmStatic
    fun bodyAddCollisionException(body: RID, exceptedBody: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(bodyAddCollisionExceptionBind, singleton, body, exceptedBody)
    }

    /**
     * Removes `excepted_body` from the body's list of collision exceptions, so that collisions with it
     * are no longer ignored.
     *
     * Generated from Godot docs: PhysicsServer2D.body_remove_collision_exception
     */
    @JvmStatic
    fun bodyRemoveCollisionException(body: RID, exceptedBody: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(bodyRemoveCollisionExceptionBind, singleton, body, exceptedBody)
    }

    /**
     * Sets the maximum number of contacts that the body can report. If `amount` is greater than zero,
     * then the body will keep track of at most this many contacts with other bodies.
     *
     * Generated from Godot docs: PhysicsServer2D.body_set_max_contacts_reported
     */
    @JvmStatic
    fun bodySetMaxContactsReported(body: RID, amount: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(bodySetMaxContactsReportedBind, singleton, body, amount)
    }

    /**
     * Returns the maximum number of contacts that the body can report. See
     * `body_set_max_contacts_reported`.
     *
     * Generated from Godot docs: PhysicsServer2D.body_get_max_contacts_reported
     */
    @JvmStatic
    fun bodyGetMaxContactsReported(body: RID): Int {
        return ObjectCalls.ptrcallWithRIDArgRetInt(bodyGetMaxContactsReportedBind, singleton, body)
    }

    /**
     * Sets whether the body omits the standard force integration. If `enable` is `true`, the body will
     * not automatically use applied forces, torques, and damping to update the body's linear and
     * angular velocity. In this case, `body_set_force_integration_callback` can be used to manually
     * update the linear and angular velocity instead. This method is called when the property
     * `RigidBody2D.custom_integrator` is set.
     *
     * Generated from Godot docs: PhysicsServer2D.body_set_omit_force_integration
     */
    @JvmStatic
    fun bodySetOmitForceIntegration(body: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(bodySetOmitForceIntegrationBind, singleton, body, enable)
    }

    /**
     * Returns `true` if the body is omitting the standard force integration. See
     * `body_set_omit_force_integration`.
     *
     * Generated from Godot docs: PhysicsServer2D.body_is_omitting_force_integration
     */
    @JvmStatic
    fun bodyIsOmittingForceIntegration(body: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(bodyIsOmittingForceIntegrationBind, singleton, body)
    }

    /**
     * Sets the body's state synchronization callback function to `callable`. Use an empty `Callable`
     * (`Callable()`) to clear the callback. The function `callable` will be called every physics
     * frame, assuming that the body was active during the previous physics tick, and can be used to
     * fetch the latest state from the physics server. The function `callable` must take the following
     * parameters: 1. `state`: a `PhysicsDirectBodyState2D`, used to retrieve the body's state.
     *
     * Generated from Godot docs: PhysicsServer2D.body_set_state_sync_callback
     */
    @JvmStatic
    fun bodySetStateSyncCallback(body: RID, callable: GodotCallable) {
        ObjectCalls.ptrcallWithRIDCallableArgs(bodySetStateSyncCallbackBind, singleton, body, callable.target.handle, callable.method)
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
    @JvmStatic
    fun bodySetForceIntegrationCallback(body: RID, callable: GodotCallable, userdata: Any? = null) {
        ObjectCalls.ptrcallWithRIDCallableVariantArgs(bodySetForceIntegrationCallbackBind, singleton, body, callable.target.handle, callable.method, userdata)
    }

    /**
     * Returns `true` if a collision would result from moving the body along a motion vector from a
     * given point in space. See `PhysicsTestMotionParameters2D` for the available motion parameters.
     * Optionally a `PhysicsTestMotionResult2D` object can be passed, which will be used to store the
     * information about the resulting collision.
     *
     * Generated from Godot docs: PhysicsServer2D.body_test_motion
     */
    @JvmStatic
    fun bodyTestMotion(body: RID, parameters: PhysicsTestMotionParameters2D, result: PhysicsTestMotionResult2D?): Boolean {
        return ObjectCalls.ptrcallWithRIDAndTwoObjectArgsRetBool(bodyTestMotionBind, singleton, body, parameters.requireOpenHandle(), result?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Returns the `PhysicsDirectBodyState2D` of the body. Returns `null` if the body is destroyed or
     * not assigned to a space.
     *
     * Generated from Godot docs: PhysicsServer2D.body_get_direct_state
     */
    @JvmStatic
    fun bodyGetDirectState(body: RID): PhysicsDirectBodyState2D? {
        return PhysicsDirectBodyState2D.wrap(ObjectCalls.ptrcallWithRIDArgRetObject(bodyGetDirectStateBind, singleton, body))
    }

    /**
     * Creates a 2D joint in the physics server, and returns the `RID` that identifies it. To set the
     * joint type, use `joint_make_damped_spring`, `joint_make_groove` or `joint_make_pin`. Use
     * `joint_set_param` to set generic joint parameters.
     *
     * Generated from Godot docs: PhysicsServer2D.joint_create
     */
    @JvmStatic
    fun jointCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(jointCreateBind, singleton)
    }

    /**
     * Destroys the joint with the given `RID`, creates a new uninitialized joint, and makes the `RID`
     * refer to this new joint.
     *
     * Generated from Godot docs: PhysicsServer2D.joint_clear
     */
    @JvmStatic
    fun jointClear(joint: RID) {
        ObjectCalls.ptrcallWithRIDArg(jointClearBind, singleton, joint)
    }

    /**
     * Sets the value of the given joint parameter.
     *
     * Generated from Godot docs: PhysicsServer2D.joint_set_param
     */
    @JvmStatic
    fun jointSetParam(joint: RID, param: Long, value: Double) {
        ObjectCalls.ptrcallWithRIDLongAndDoubleArgs(jointSetParamBind, singleton, joint, param, value)
    }

    /**
     * Returns the value of the given joint parameter.
     *
     * Generated from Godot docs: PhysicsServer2D.joint_get_param
     */
    @JvmStatic
    fun jointGetParam(joint: RID, param: Long): Double {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetDouble(jointGetParamBind, singleton, joint, param)
    }

    /**
     * Sets whether the bodies attached to the `Joint2D` will collide with each other.
     *
     * Generated from Godot docs: PhysicsServer2D.joint_disable_collisions_between_bodies
     */
    @JvmStatic
    fun jointDisableCollisionsBetweenBodies(joint: RID, disable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(jointDisableCollisionsBetweenBodiesBind, singleton, joint, disable)
    }

    /**
     * Returns whether the bodies attached to the `Joint2D` will collide with each other.
     *
     * Generated from Godot docs: PhysicsServer2D.joint_is_disabled_collisions_between_bodies
     */
    @JvmStatic
    fun jointIsDisabledCollisionsBetweenBodies(joint: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(jointIsDisabledCollisionsBetweenBodiesBind, singleton, joint)
    }

    /**
     * Makes the joint a pin joint. If `body_b` is an empty `RID`, then `body_a` is pinned to the point
     * `anchor` (given in global coordinates); otherwise, `body_a` is pinned to `body_b` at the point
     * `anchor` (given in global coordinates). To set the parameters which are specific to the pin
     * joint, see `pin_joint_set_param`.
     *
     * Generated from Godot docs: PhysicsServer2D.joint_make_pin
     */
    @JvmStatic
    fun jointMakePin(joint: RID, anchor: Vector2, bodyA: RID, bodyB: RID) {
        ObjectCalls.ptrcallWithRIDVector2TwoRIDArgs(jointMakePinBind, singleton, joint, anchor, bodyA, bodyB)
    }

    /**
     * Makes the joint a groove joint.
     *
     * Generated from Godot docs: PhysicsServer2D.joint_make_groove
     */
    @JvmStatic
    fun jointMakeGroove(joint: RID, groove1A: Vector2, groove2A: Vector2, anchorB: Vector2, bodyA: RID, bodyB: RID) {
        ObjectCalls.ptrcallWithRIDThreeVector2TwoRIDArgs(jointMakeGrooveBind, singleton, joint, groove1A, groove2A, anchorB, bodyA, bodyB)
    }

    /**
     * Makes the joint a damped spring joint, attached at the point `anchor_a` (given in global
     * coordinates) on the body `body_a` and at the point `anchor_b` (given in global coordinates) on
     * the body `body_b`. To set the parameters which are specific to the damped spring, see
     * `damped_spring_joint_set_param`.
     *
     * Generated from Godot docs: PhysicsServer2D.joint_make_damped_spring
     */
    @JvmStatic
    fun jointMakeDampedSpring(joint: RID, anchorA: Vector2, anchorB: Vector2, bodyA: RID, bodyB: RID) {
        ObjectCalls.ptrcallWithRIDTwoVector2TwoRIDArgs(jointMakeDampedSpringBind, singleton, joint, anchorA, anchorB, bodyA, bodyB)
    }

    /**
     * Sets a pin joint flag.
     *
     * Generated from Godot docs: PhysicsServer2D.pin_joint_set_flag
     */
    @JvmStatic
    fun pinJointSetFlag(joint: RID, flag: Long, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDLongAndBoolArgs(pinJointSetFlagBind, singleton, joint, flag, enabled)
    }

    /**
     * Gets a pin joint flag.
     *
     * Generated from Godot docs: PhysicsServer2D.pin_joint_get_flag
     */
    @JvmStatic
    fun pinJointGetFlag(joint: RID, flag: Long): Boolean {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetBool(pinJointGetFlagBind, singleton, joint, flag)
    }

    /**
     * Sets a pin joint parameter.
     *
     * Generated from Godot docs: PhysicsServer2D.pin_joint_set_param
     */
    @JvmStatic
    fun pinJointSetParam(joint: RID, param: Long, value: Double) {
        ObjectCalls.ptrcallWithRIDLongAndDoubleArgs(pinJointSetParamBind, singleton, joint, param, value)
    }

    /**
     * Returns the value of a pin joint parameter.
     *
     * Generated from Godot docs: PhysicsServer2D.pin_joint_get_param
     */
    @JvmStatic
    fun pinJointGetParam(joint: RID, param: Long): Double {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetDouble(pinJointGetParamBind, singleton, joint, param)
    }

    /**
     * Sets the value of the given damped spring joint parameter.
     *
     * Generated from Godot docs: PhysicsServer2D.damped_spring_joint_set_param
     */
    @JvmStatic
    fun dampedSpringJointSetParam(joint: RID, param: Long, value: Double) {
        ObjectCalls.ptrcallWithRIDLongAndDoubleArgs(dampedSpringJointSetParamBind, singleton, joint, param, value)
    }

    /**
     * Returns the value of the given damped spring joint parameter.
     *
     * Generated from Godot docs: PhysicsServer2D.damped_spring_joint_get_param
     */
    @JvmStatic
    fun dampedSpringJointGetParam(joint: RID, param: Long): Double {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetDouble(dampedSpringJointGetParamBind, singleton, joint, param)
    }

    /**
     * Returns the joint's type.
     *
     * Generated from Godot docs: PhysicsServer2D.joint_get_type
     */
    @JvmStatic
    fun jointGetType(joint: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(jointGetTypeBind, singleton, joint)
    }

    /**
     * Destroys any of the objects created by PhysicsServer2D. If the `RID` passed is not one of the
     * objects that can be created by PhysicsServer2D, an error will be printed to the console.
     *
     * Generated from Godot docs: PhysicsServer2D.free_rid
     */
    @JvmStatic
    fun freeRid(rid: RID) {
        ObjectCalls.ptrcallWithRIDArg(freeRidBind, singleton, rid)
    }

    /**
     * Activates or deactivates the 2D physics server. If `active` is `false`, then the physics server
     * will not do anything in its physics step.
     *
     * Generated from Godot docs: PhysicsServer2D.set_active
     */
    @JvmStatic
    fun setActive(active: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setActiveBind, singleton, active)
    }

    /**
     * Returns the value of a physics engine state specified by `process_info`.
     *
     * Generated from Godot docs: PhysicsServer2D.get_process_info
     */
    @JvmStatic
    fun getProcessInfo(processInfo: Long): Int {
        return ObjectCalls.ptrcallWithLongArgRetInt(getProcessInfoBind, singleton, processInfo)
    }

    @JvmStatic
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

    private const val SHAPE_SET_DATA_HASH = 3175752987L
    private val shapeSetDataBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "shape_set_data", SHAPE_SET_DATA_HASH)
    }

    private const val SHAPE_GET_TYPE_HASH = 1240598777L
    private val shapeGetTypeBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "shape_get_type", SHAPE_GET_TYPE_HASH)
    }

    private const val SHAPE_GET_DATA_HASH = 4171304767L
    private val shapeGetDataBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "shape_get_data", SHAPE_GET_DATA_HASH)
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

    private const val AREA_SET_PARAM_HASH = 1257146028L
    private val areaSetParamBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "area_set_param", AREA_SET_PARAM_HASH)
    }

    private const val AREA_SET_TRANSFORM_HASH = 1246044741L
    private val areaSetTransformBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "area_set_transform", AREA_SET_TRANSFORM_HASH)
    }

    private const val AREA_GET_PARAM_HASH = 3047435120L
    private val areaGetParamBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "area_get_param", AREA_GET_PARAM_HASH)
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

    private const val BODY_SET_PARAM_HASH = 2715630609L
    private val bodySetParamBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_set_param", BODY_SET_PARAM_HASH)
    }

    private const val BODY_GET_PARAM_HASH = 3208033526L
    private val bodyGetParamBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_get_param", BODY_GET_PARAM_HASH)
    }

    private const val BODY_RESET_MASS_PROPERTIES_HASH = 2722037293L
    private val bodyResetMassPropertiesBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_reset_mass_properties", BODY_RESET_MASS_PROPERTIES_HASH)
    }

    private const val BODY_SET_STATE_HASH = 1706355209L
    private val bodySetStateBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_set_state", BODY_SET_STATE_HASH)
    }

    private const val BODY_GET_STATE_HASH = 4036367961L
    private val bodyGetStateBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_get_state", BODY_GET_STATE_HASH)
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

    private const val BODY_SET_FORCE_INTEGRATION_CALLBACK_HASH = 3059434249L
    private val bodySetForceIntegrationCallbackBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer2D", "body_set_force_integration_callback", BODY_SET_FORCE_INTEGRATION_CALLBACK_HASH)
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
