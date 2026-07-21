package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector3

/**
 * A server interface for low-level 3D physics access.
 *
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

    /**
     * Creates a 3D world boundary shape in the physics server, and returns the `RID` that identifies
     * it. Use `shape_set_data` to set the shape's normal direction and distance properties.
     *
     * Generated from Godot docs: PhysicsServer3D.world_boundary_shape_create
     */
    @JvmStatic
    fun worldBoundaryShapeCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(worldBoundaryShapeCreateBind, singleton)
    }

    /**
     * Creates a 3D separation ray shape in the physics server, and returns the `RID` that identifies
     * it. Use `shape_set_data` to set the shape's `length` and `slide_on_slope` properties.
     *
     * Generated from Godot docs: PhysicsServer3D.separation_ray_shape_create
     */
    @JvmStatic
    fun separationRayShapeCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(separationRayShapeCreateBind, singleton)
    }

    /**
     * Creates a 3D sphere shape in the physics server, and returns the `RID` that identifies it. Use
     * `shape_set_data` to set the sphere's radius.
     *
     * Generated from Godot docs: PhysicsServer3D.sphere_shape_create
     */
    @JvmStatic
    fun sphereShapeCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(sphereShapeCreateBind, singleton)
    }

    /**
     * Creates a 3D box shape in the physics server, and returns the `RID` that identifies it. Use
     * `shape_set_data` to set the box's half-extents.
     *
     * Generated from Godot docs: PhysicsServer3D.box_shape_create
     */
    @JvmStatic
    fun boxShapeCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(boxShapeCreateBind, singleton)
    }

    /**
     * Creates a 3D capsule shape in the physics server, and returns the `RID` that identifies it. Use
     * `shape_set_data` to set the capsule's height and radius.
     *
     * Generated from Godot docs: PhysicsServer3D.capsule_shape_create
     */
    @JvmStatic
    fun capsuleShapeCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(capsuleShapeCreateBind, singleton)
    }

    /**
     * Creates a 3D cylinder shape in the physics server, and returns the `RID` that identifies it. Use
     * `shape_set_data` to set the cylinder's height and radius.
     *
     * Generated from Godot docs: PhysicsServer3D.cylinder_shape_create
     */
    @JvmStatic
    fun cylinderShapeCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(cylinderShapeCreateBind, singleton)
    }

    /**
     * Creates a 3D convex polygon shape in the physics server, and returns the `RID` that identifies
     * it. Use `shape_set_data` to set the convex polygon's points.
     *
     * Generated from Godot docs: PhysicsServer3D.convex_polygon_shape_create
     */
    @JvmStatic
    fun convexPolygonShapeCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(convexPolygonShapeCreateBind, singleton)
    }

    /**
     * Creates a 3D concave polygon shape in the physics server, and returns the `RID` that identifies
     * it. Use `shape_set_data` to set the concave polygon's triangles.
     *
     * Generated from Godot docs: PhysicsServer3D.concave_polygon_shape_create
     */
    @JvmStatic
    fun concavePolygonShapeCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(concavePolygonShapeCreateBind, singleton)
    }

    /**
     * Creates a 3D heightmap shape in the physics server, and returns the `RID` that identifies it.
     * Use `shape_set_data` to set the heightmap's data.
     *
     * Generated from Godot docs: PhysicsServer3D.heightmap_shape_create
     */
    @JvmStatic
    fun heightmapShapeCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(heightmapShapeCreateBind, singleton)
    }

    /**
     * Creates a custom shape in the physics server, and returns the `RID` that identifies it. Use
     * `shape_set_data` to set the shape's data. Note: Custom shapes are not supported by the built-in
     * physics servers, so calling this method always produces an error when using Godot Physics or
     * Jolt Physics. Custom physics servers implemented as GDExtensions may support a custom shape.
     *
     * Generated from Godot docs: PhysicsServer3D.custom_shape_create
     */
    @JvmStatic
    fun customShapeCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(customShapeCreateBind, singleton)
    }

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
    @JvmStatic
    fun shapeSetData(shape: RID, data: Any?) {
        ObjectCalls.ptrcallWithRIDAndVariantArg(shapeSetDataBind, singleton, shape, data)
    }

    /**
     * Sets the collision margin for the shape. Note: This is not used in Godot Physics.
     *
     * Generated from Godot docs: PhysicsServer3D.shape_set_margin
     */
    @JvmStatic
    fun shapeSetMargin(shape: RID, margin: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(shapeSetMarginBind, singleton, shape, margin)
    }

    /**
     * Returns the shape's type.
     *
     * Generated from Godot docs: PhysicsServer3D.shape_get_type
     */
    @JvmStatic
    fun shapeGetType(shape: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(shapeGetTypeBind, singleton, shape)
    }

    /**
     * Returns the shape data that configures the shape, such as the half-extents of a box or the
     * triangles of a concave (trimesh) shape. See `shape_set_data` for the precise format of this data
     * in each case.
     *
     * Generated from Godot docs: PhysicsServer3D.shape_get_data
     */
    @JvmStatic
    fun shapeGetData(shape: RID): Any? {
        return ObjectCalls.ptrcallWithRIDArgRetVariantScalar(shapeGetDataBind, singleton, shape)
    }

    /**
     * Returns the collision margin for the shape. Note: This is not used in Godot Physics, so will
     * always return `0`.
     *
     * Generated from Godot docs: PhysicsServer3D.shape_get_margin
     */
    @JvmStatic
    fun shapeGetMargin(shape: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(shapeGetMarginBind, singleton, shape)
    }

    /**
     * Creates a space. A space is a collection of parameters for the physics engine that can be
     * assigned to an area or a body. It can be assigned to an area with `area_set_space`, or to a body
     * with `body_set_space`.
     *
     * Generated from Godot docs: PhysicsServer3D.space_create
     */
    @JvmStatic
    fun spaceCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(spaceCreateBind, singleton)
    }

    /**
     * Marks a space as active. It will not have an effect, unless it is assigned to an area or body.
     *
     * Generated from Godot docs: PhysicsServer3D.space_set_active
     */
    @JvmStatic
    fun spaceSetActive(space: RID, active: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(spaceSetActiveBind, singleton, space, active)
    }

    /**
     * Returns whether the space is active.
     *
     * Generated from Godot docs: PhysicsServer3D.space_is_active
     */
    @JvmStatic
    fun spaceIsActive(space: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(spaceIsActiveBind, singleton, space)
    }

    /**
     * Sets the value for a space parameter. A list of available parameters is on the `SpaceParameter`
     * constants.
     *
     * Generated from Godot docs: PhysicsServer3D.space_set_param
     */
    @JvmStatic
    fun spaceSetParam(space: RID, param: Long, value: Double) {
        ObjectCalls.ptrcallWithRIDLongAndDoubleArgs(spaceSetParamBind, singleton, space, param, value)
    }

    /**
     * Returns the value of a space parameter.
     *
     * Generated from Godot docs: PhysicsServer3D.space_get_param
     */
    @JvmStatic
    fun spaceGetParam(space: RID, param: Long): Double {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetDouble(spaceGetParamBind, singleton, space, param)
    }

    /**
     * Returns the state of a space, a `PhysicsDirectSpaceState3D`. This object can be used to make
     * collision/intersection queries.
     *
     * Generated from Godot docs: PhysicsServer3D.space_get_direct_state
     */
    @JvmStatic
    fun spaceGetDirectState(space: RID): PhysicsDirectSpaceState3D? {
        return PhysicsDirectSpaceState3D.wrap(ObjectCalls.ptrcallWithRIDArgRetObject(spaceGetDirectStateBind, singleton, space))
    }

    /**
     * Creates a 3D area object in the physics server, and returns the `RID` that identifies it. The
     * default settings for the created area include a collision layer and mask set to `1`, and
     * `monitorable` set to `false`. Use `area_add_shape` to add shapes to it, use `area_set_transform`
     * to set its transform, and use `area_set_space` to add the area to a space. If you want the area
     * to be detectable use `area_set_monitorable`.
     *
     * Generated from Godot docs: PhysicsServer3D.area_create
     */
    @JvmStatic
    fun areaCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(areaCreateBind, singleton)
    }

    /**
     * Assigns a space to the area.
     *
     * Generated from Godot docs: PhysicsServer3D.area_set_space
     */
    @JvmStatic
    fun areaSetSpace(area: RID, space: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(areaSetSpaceBind, singleton, area, space)
    }

    /**
     * Returns the space assigned to the area.
     *
     * Generated from Godot docs: PhysicsServer3D.area_get_space
     */
    @JvmStatic
    fun areaGetSpace(area: RID): RID {
        return ObjectCalls.ptrcallWithRIDArgRetRID(areaGetSpaceBind, singleton, area)
    }

    /**
     * Adds a shape to the area, along with a transform matrix. Shapes are usually referenced by their
     * index, so you should track which shape has a given index.
     *
     * Generated from Godot docs: PhysicsServer3D.area_add_shape
     */
    @JvmStatic
    fun areaAddShape(area: RID, shape: RID, transform: Transform3D, disabled: Boolean = false) {
        ObjectCalls.ptrcallWithTwoRIDTransform3DBoolArgs(areaAddShapeBind, singleton, area, shape, transform, disabled)
    }

    /**
     * Substitutes a given area shape by another. The old shape is selected by its index, the new one
     * by its `RID`.
     *
     * Generated from Godot docs: PhysicsServer3D.area_set_shape
     */
    @JvmStatic
    fun areaSetShape(area: RID, shapeIdx: Int, shape: RID) {
        ObjectCalls.ptrcallWithRIDIntAndRIDArgs(areaSetShapeBind, singleton, area, shapeIdx, shape)
    }

    /**
     * Sets the transform matrix for an area shape.
     *
     * Generated from Godot docs: PhysicsServer3D.area_set_shape_transform
     */
    @JvmStatic
    fun areaSetShapeTransform(area: RID, shapeIdx: Int, transform: Transform3D) {
        ObjectCalls.ptrcallWithRIDIntAndTransform3DArg(areaSetShapeTransformBind, singleton, area, shapeIdx, transform)
    }

    @JvmStatic
    fun areaSetShapeDisabled(area: RID, shapeIdx: Int, disabled: Boolean) {
        ObjectCalls.ptrcallWithRIDIntAndBoolArgs(areaSetShapeDisabledBind, singleton, area, shapeIdx, disabled)
    }

    /**
     * Returns the number of shapes assigned to an area.
     *
     * Generated from Godot docs: PhysicsServer3D.area_get_shape_count
     */
    @JvmStatic
    fun areaGetShapeCount(area: RID): Int {
        return ObjectCalls.ptrcallWithRIDArgRetInt(areaGetShapeCountBind, singleton, area)
    }

    /**
     * Returns the `RID` of the nth shape of an area.
     *
     * Generated from Godot docs: PhysicsServer3D.area_get_shape
     */
    @JvmStatic
    fun areaGetShape(area: RID, shapeIdx: Int): RID {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetRID(areaGetShapeBind, singleton, area, shapeIdx)
    }

    /**
     * Returns the transform matrix of a shape within an area.
     *
     * Generated from Godot docs: PhysicsServer3D.area_get_shape_transform
     */
    @JvmStatic
    fun areaGetShapeTransform(area: RID, shapeIdx: Int): Transform3D {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetTransform3D(areaGetShapeTransformBind, singleton, area, shapeIdx)
    }

    /**
     * Removes a shape from an area. It does not delete the shape, so it can be reassigned later.
     *
     * Generated from Godot docs: PhysicsServer3D.area_remove_shape
     */
    @JvmStatic
    fun areaRemoveShape(area: RID, shapeIdx: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(areaRemoveShapeBind, singleton, area, shapeIdx)
    }

    /**
     * Removes all shapes from an area. It does not delete the shapes, so they can be reassigned later.
     *
     * Generated from Godot docs: PhysicsServer3D.area_clear_shapes
     */
    @JvmStatic
    fun areaClearShapes(area: RID) {
        ObjectCalls.ptrcallWithRIDArg(areaClearShapesBind, singleton, area)
    }

    /**
     * Assigns the area to one or many physics layers.
     *
     * Generated from Godot docs: PhysicsServer3D.area_set_collision_layer
     */
    @JvmStatic
    fun areaSetCollisionLayer(area: RID, layer: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(areaSetCollisionLayerBind, singleton, area, layer)
    }

    /**
     * Returns the physics layer or layers an area belongs to.
     *
     * Generated from Godot docs: PhysicsServer3D.area_get_collision_layer
     */
    @JvmStatic
    fun areaGetCollisionLayer(area: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetUInt32(areaGetCollisionLayerBind, singleton, area)
    }

    /**
     * Sets which physics layers the area will monitor.
     *
     * Generated from Godot docs: PhysicsServer3D.area_set_collision_mask
     */
    @JvmStatic
    fun areaSetCollisionMask(area: RID, mask: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(areaSetCollisionMaskBind, singleton, area, mask)
    }

    /**
     * Returns the physics layer or layers an area can contact with.
     *
     * Generated from Godot docs: PhysicsServer3D.area_get_collision_mask
     */
    @JvmStatic
    fun areaGetCollisionMask(area: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetUInt32(areaGetCollisionMaskBind, singleton, area)
    }

    /**
     * Sets the value for an area parameter. A list of available parameters is on the `AreaParameter`
     * constants.
     *
     * Generated from Godot docs: PhysicsServer3D.area_set_param
     */
    @JvmStatic
    fun areaSetParam(area: RID, param: Long, value: Any?) {
        ObjectCalls.ptrcallWithRIDLongAndVariantArgs(areaSetParamBind, singleton, area, param, value)
    }

    /**
     * Sets the transform matrix for an area.
     *
     * Generated from Godot docs: PhysicsServer3D.area_set_transform
     */
    @JvmStatic
    fun areaSetTransform(area: RID, transform: Transform3D) {
        ObjectCalls.ptrcallWithRIDAndTransform3DArg(areaSetTransformBind, singleton, area, transform)
    }

    /**
     * Returns an area parameter value. A list of available parameters is on the `AreaParameter`
     * constants.
     *
     * Generated from Godot docs: PhysicsServer3D.area_get_param
     */
    @JvmStatic
    fun areaGetParam(area: RID, param: Long): Any? {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetVariantScalar(areaGetParamBind, singleton, area, param)
    }

    /**
     * Returns the transform matrix for an area.
     *
     * Generated from Godot docs: PhysicsServer3D.area_get_transform
     */
    @JvmStatic
    fun areaGetTransform(area: RID): Transform3D {
        return ObjectCalls.ptrcallWithRIDArgRetTransform3D(areaGetTransformBind, singleton, area)
    }

    /**
     * Assigns the area to a descendant of `Object`, so it can exist in the node tree.
     *
     * Generated from Godot docs: PhysicsServer3D.area_attach_object_instance_id
     */
    @JvmStatic
    fun areaAttachObjectInstanceId(area: RID, id: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(areaAttachObjectInstanceIdBind, singleton, area, id)
    }

    /**
     * Gets the instance ID of the object the area is assigned to.
     *
     * Generated from Godot docs: PhysicsServer3D.area_get_object_instance_id
     */
    @JvmStatic
    fun areaGetObjectInstanceId(area: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(areaGetObjectInstanceIdBind, singleton, area)
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
     * Generated from Godot docs: PhysicsServer3D.area_set_monitor_callback
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
     * Generated from Godot docs: PhysicsServer3D.area_set_area_monitor_callback
     */
    @JvmStatic
    fun areaSetAreaMonitorCallback(area: RID, callback: GodotCallable) {
        ObjectCalls.ptrcallWithRIDCallableArgs(areaSetAreaMonitorCallbackBind, singleton, area, callback.target.handle, callback.method)
    }

    @JvmStatic
    fun areaSetMonitorable(area: RID, monitorable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(areaSetMonitorableBind, singleton, area, monitorable)
    }

    /**
     * Sets object pickable with rays.
     *
     * Generated from Godot docs: PhysicsServer3D.area_set_ray_pickable
     */
    @JvmStatic
    fun areaSetRayPickable(area: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(areaSetRayPickableBind, singleton, area, enable)
    }

    /**
     * Creates a 3D body object in the physics server, and returns the `RID` that identifies it. The
     * default settings for the created area include a collision layer and mask set to `1`, and body
     * mode set to `BODY_MODE_RIGID`. Use `body_add_shape` to add shapes to it, use `body_set_state` to
     * set its transform, and use `body_set_space` to add the body to a space.
     *
     * Generated from Godot docs: PhysicsServer3D.body_create
     */
    @JvmStatic
    fun bodyCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(bodyCreateBind, singleton)
    }

    /**
     * Assigns a space to the body (see `space_create`).
     *
     * Generated from Godot docs: PhysicsServer3D.body_set_space
     */
    @JvmStatic
    fun bodySetSpace(body: RID, space: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(bodySetSpaceBind, singleton, body, space)
    }

    /**
     * Returns the `RID` of the space assigned to a body.
     *
     * Generated from Godot docs: PhysicsServer3D.body_get_space
     */
    @JvmStatic
    fun bodyGetSpace(body: RID): RID {
        return ObjectCalls.ptrcallWithRIDArgRetRID(bodyGetSpaceBind, singleton, body)
    }

    /**
     * Sets the body mode.
     *
     * Generated from Godot docs: PhysicsServer3D.body_set_mode
     */
    @JvmStatic
    fun bodySetMode(body: RID, mode: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(bodySetModeBind, singleton, body, mode)
    }

    /**
     * Returns the body mode.
     *
     * Generated from Godot docs: PhysicsServer3D.body_get_mode
     */
    @JvmStatic
    fun bodyGetMode(body: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(bodyGetModeBind, singleton, body)
    }

    /**
     * Sets the physics layer or layers a body belongs to.
     *
     * Generated from Godot docs: PhysicsServer3D.body_set_collision_layer
     */
    @JvmStatic
    fun bodySetCollisionLayer(body: RID, layer: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(bodySetCollisionLayerBind, singleton, body, layer)
    }

    /**
     * Returns the physics layer or layers a body belongs to.
     *
     * Generated from Godot docs: PhysicsServer3D.body_get_collision_layer
     */
    @JvmStatic
    fun bodyGetCollisionLayer(body: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetUInt32(bodyGetCollisionLayerBind, singleton, body)
    }

    /**
     * Sets the physics layer or layers a body can collide with.
     *
     * Generated from Godot docs: PhysicsServer3D.body_set_collision_mask
     */
    @JvmStatic
    fun bodySetCollisionMask(body: RID, mask: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(bodySetCollisionMaskBind, singleton, body, mask)
    }

    /**
     * Returns the physics layer or layers a body can collide with.
     *
     * Generated from Godot docs: PhysicsServer3D.body_get_collision_mask
     */
    @JvmStatic
    fun bodyGetCollisionMask(body: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetUInt32(bodyGetCollisionMaskBind, singleton, body)
    }

    /**
     * Sets the body's collision priority.
     *
     * Generated from Godot docs: PhysicsServer3D.body_set_collision_priority
     */
    @JvmStatic
    fun bodySetCollisionPriority(body: RID, priority: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(bodySetCollisionPriorityBind, singleton, body, priority)
    }

    /**
     * Returns the body's collision priority.
     *
     * Generated from Godot docs: PhysicsServer3D.body_get_collision_priority
     */
    @JvmStatic
    fun bodyGetCollisionPriority(body: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(bodyGetCollisionPriorityBind, singleton, body)
    }

    /**
     * Adds a shape to the body, along with a transform matrix. Shapes are usually referenced by their
     * index, so you should track which shape has a given index.
     *
     * Generated from Godot docs: PhysicsServer3D.body_add_shape
     */
    @JvmStatic
    fun bodyAddShape(body: RID, shape: RID, transform: Transform3D, disabled: Boolean = false) {
        ObjectCalls.ptrcallWithTwoRIDTransform3DBoolArgs(bodyAddShapeBind, singleton, body, shape, transform, disabled)
    }

    /**
     * Substitutes a given body shape by another. The old shape is selected by its index, the new one
     * by its `RID`.
     *
     * Generated from Godot docs: PhysicsServer3D.body_set_shape
     */
    @JvmStatic
    fun bodySetShape(body: RID, shapeIdx: Int, shape: RID) {
        ObjectCalls.ptrcallWithRIDIntAndRIDArgs(bodySetShapeBind, singleton, body, shapeIdx, shape)
    }

    /**
     * Sets the transform matrix for a body shape.
     *
     * Generated from Godot docs: PhysicsServer3D.body_set_shape_transform
     */
    @JvmStatic
    fun bodySetShapeTransform(body: RID, shapeIdx: Int, transform: Transform3D) {
        ObjectCalls.ptrcallWithRIDIntAndTransform3DArg(bodySetShapeTransformBind, singleton, body, shapeIdx, transform)
    }

    @JvmStatic
    fun bodySetShapeDisabled(body: RID, shapeIdx: Int, disabled: Boolean) {
        ObjectCalls.ptrcallWithRIDIntAndBoolArgs(bodySetShapeDisabledBind, singleton, body, shapeIdx, disabled)
    }

    /**
     * Returns the number of shapes assigned to a body.
     *
     * Generated from Godot docs: PhysicsServer3D.body_get_shape_count
     */
    @JvmStatic
    fun bodyGetShapeCount(body: RID): Int {
        return ObjectCalls.ptrcallWithRIDArgRetInt(bodyGetShapeCountBind, singleton, body)
    }

    /**
     * Returns the `RID` of the nth shape of a body.
     *
     * Generated from Godot docs: PhysicsServer3D.body_get_shape
     */
    @JvmStatic
    fun bodyGetShape(body: RID, shapeIdx: Int): RID {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetRID(bodyGetShapeBind, singleton, body, shapeIdx)
    }

    /**
     * Returns the transform matrix of a body shape.
     *
     * Generated from Godot docs: PhysicsServer3D.body_get_shape_transform
     */
    @JvmStatic
    fun bodyGetShapeTransform(body: RID, shapeIdx: Int): Transform3D {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetTransform3D(bodyGetShapeTransformBind, singleton, body, shapeIdx)
    }

    /**
     * Removes a shape from a body. The shape is not deleted, so it can be reused afterwards.
     *
     * Generated from Godot docs: PhysicsServer3D.body_remove_shape
     */
    @JvmStatic
    fun bodyRemoveShape(body: RID, shapeIdx: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(bodyRemoveShapeBind, singleton, body, shapeIdx)
    }

    /**
     * Removes all shapes from a body.
     *
     * Generated from Godot docs: PhysicsServer3D.body_clear_shapes
     */
    @JvmStatic
    fun bodyClearShapes(body: RID) {
        ObjectCalls.ptrcallWithRIDArg(bodyClearShapesBind, singleton, body)
    }

    /**
     * Assigns the area to a descendant of `Object`, so it can exist in the node tree.
     *
     * Generated from Godot docs: PhysicsServer3D.body_attach_object_instance_id
     */
    @JvmStatic
    fun bodyAttachObjectInstanceId(body: RID, id: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(bodyAttachObjectInstanceIdBind, singleton, body, id)
    }

    /**
     * Gets the instance ID of the object the area is assigned to.
     *
     * Generated from Godot docs: PhysicsServer3D.body_get_object_instance_id
     */
    @JvmStatic
    fun bodyGetObjectInstanceId(body: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(bodyGetObjectInstanceIdBind, singleton, body)
    }

    /**
     * If `true`, the continuous collision detection mode is enabled. Continuous collision detection
     * tries to predict where a moving body will collide, instead of moving it and correcting its
     * movement if it collided.
     *
     * Generated from Godot docs: PhysicsServer3D.body_set_enable_continuous_collision_detection
     */
    @JvmStatic
    fun bodySetEnableContinuousCollisionDetection(body: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(bodySetEnableContinuousCollisionDetectionBind, singleton, body, enable)
    }

    /**
     * If `true`, the continuous collision detection mode is enabled.
     *
     * Generated from Godot docs: PhysicsServer3D.body_is_continuous_collision_detection_enabled
     */
    @JvmStatic
    fun bodyIsContinuousCollisionDetectionEnabled(body: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(bodyIsContinuousCollisionDetectionEnabledBind, singleton, body)
    }

    /**
     * Sets a body parameter. A list of available parameters is on the `BodyParameter` constants.
     *
     * Generated from Godot docs: PhysicsServer3D.body_set_param
     */
    @JvmStatic
    fun bodySetParam(body: RID, param: Long, value: Any?) {
        ObjectCalls.ptrcallWithRIDLongAndVariantArgs(bodySetParamBind, singleton, body, param, value)
    }

    /**
     * Returns the value of a body parameter. A list of available parameters is on the `BodyParameter`
     * constants.
     *
     * Generated from Godot docs: PhysicsServer3D.body_get_param
     */
    @JvmStatic
    fun bodyGetParam(body: RID, param: Long): Any? {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetVariantScalar(bodyGetParamBind, singleton, body, param)
    }

    /**
     * Restores the default inertia and center of mass based on shapes to cancel any custom values
     * previously set using `body_set_param`.
     *
     * Generated from Godot docs: PhysicsServer3D.body_reset_mass_properties
     */
    @JvmStatic
    fun bodyResetMassProperties(body: RID) {
        ObjectCalls.ptrcallWithRIDArg(bodyResetMassPropertiesBind, singleton, body)
    }

    /**
     * Sets a body state.
     *
     * Generated from Godot docs: PhysicsServer3D.body_set_state
     */
    @JvmStatic
    fun bodySetState(body: RID, state: Long, value: Any?) {
        ObjectCalls.ptrcallWithRIDLongAndVariantArgs(bodySetStateBind, singleton, body, state, value)
    }

    /**
     * Returns a body state.
     *
     * Generated from Godot docs: PhysicsServer3D.body_get_state
     */
    @JvmStatic
    fun bodyGetState(body: RID, state: Long): Any? {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetVariantScalar(bodyGetStateBind, singleton, body, state)
    }

    /**
     * Applies a directional impulse without affecting rotation. An impulse is time-independent!
     * Applying an impulse every frame would result in a framerate-dependent force. For this reason, it
     * should only be used when simulating one-time impacts (use the "_force" functions otherwise).
     * This is equivalent to using `body_apply_impulse` at the body's center of mass.
     *
     * Generated from Godot docs: PhysicsServer3D.body_apply_central_impulse
     */
    @JvmStatic
    fun bodyApplyCentralImpulse(body: RID, impulse: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(bodyApplyCentralImpulseBind, singleton, body, impulse)
    }

    /**
     * Applies a positioned impulse to the body. An impulse is time-independent! Applying an impulse
     * every frame would result in a framerate-dependent force. For this reason, it should only be used
     * when simulating one-time impacts (use the "_force" functions otherwise). `position` is the
     * offset from the body origin in global coordinates.
     *
     * Generated from Godot docs: PhysicsServer3D.body_apply_impulse
     */
    @JvmStatic
    fun bodyApplyImpulse(body: RID, impulse: Vector3, position: Vector3) {
        ObjectCalls.ptrcallWithRIDAndTwoVector3Args(bodyApplyImpulseBind, singleton, body, impulse, position)
    }

    /**
     * Applies a rotational impulse to the body without affecting the position. An impulse is
     * time-independent! Applying an impulse every frame would result in a framerate-dependent force.
     * For this reason, it should only be used when simulating one-time impacts (use the "_force"
     * functions otherwise).
     *
     * Generated from Godot docs: PhysicsServer3D.body_apply_torque_impulse
     */
    @JvmStatic
    fun bodyApplyTorqueImpulse(body: RID, impulse: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(bodyApplyTorqueImpulseBind, singleton, body, impulse)
    }

    /**
     * Applies a directional force without affecting rotation. A force is time dependent and meant to
     * be applied every physics update. This is equivalent to using `body_apply_force` at the body's
     * center of mass.
     *
     * Generated from Godot docs: PhysicsServer3D.body_apply_central_force
     */
    @JvmStatic
    fun bodyApplyCentralForce(body: RID, force: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(bodyApplyCentralForceBind, singleton, body, force)
    }

    /**
     * Applies a positioned force to the body. A force is time dependent and meant to be applied every
     * physics update. `position` is the offset from the body origin in global coordinates.
     *
     * Generated from Godot docs: PhysicsServer3D.body_apply_force
     */
    @JvmStatic
    fun bodyApplyForce(body: RID, force: Vector3, position: Vector3) {
        ObjectCalls.ptrcallWithRIDAndTwoVector3Args(bodyApplyForceBind, singleton, body, force, position)
    }

    /**
     * Applies a rotational force without affecting position. A force is time dependent and meant to be
     * applied every physics update.
     *
     * Generated from Godot docs: PhysicsServer3D.body_apply_torque
     */
    @JvmStatic
    fun bodyApplyTorque(body: RID, torque: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(bodyApplyTorqueBind, singleton, body, torque)
    }

    /**
     * Adds a constant directional force without affecting rotation that keeps being applied over time
     * until cleared with `body_set_constant_force(body, Vector3(0, 0, 0))`. This is equivalent to
     * using `body_add_constant_force` at the body's center of mass.
     *
     * Generated from Godot docs: PhysicsServer3D.body_add_constant_central_force
     */
    @JvmStatic
    fun bodyAddConstantCentralForce(body: RID, force: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(bodyAddConstantCentralForceBind, singleton, body, force)
    }

    /**
     * Adds a constant positioned force to the body that keeps being applied over time until cleared
     * with `body_set_constant_force(body, Vector3(0, 0, 0))`. `position` is the offset from the body
     * origin in global coordinates.
     *
     * Generated from Godot docs: PhysicsServer3D.body_add_constant_force
     */
    @JvmStatic
    fun bodyAddConstantForce(body: RID, force: Vector3, position: Vector3) {
        ObjectCalls.ptrcallWithRIDAndTwoVector3Args(bodyAddConstantForceBind, singleton, body, force, position)
    }

    /**
     * Adds a constant rotational force without affecting position that keeps being applied over time
     * until cleared with `body_set_constant_torque(body, Vector3(0, 0, 0))`.
     *
     * Generated from Godot docs: PhysicsServer3D.body_add_constant_torque
     */
    @JvmStatic
    fun bodyAddConstantTorque(body: RID, torque: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(bodyAddConstantTorqueBind, singleton, body, torque)
    }

    /**
     * Sets the body's total constant positional forces applied during each physics update. See
     * `body_add_constant_force` and `body_add_constant_central_force`.
     *
     * Generated from Godot docs: PhysicsServer3D.body_set_constant_force
     */
    @JvmStatic
    fun bodySetConstantForce(body: RID, force: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(bodySetConstantForceBind, singleton, body, force)
    }

    /**
     * Returns the body's total constant positional forces applied during each physics update. See
     * `body_add_constant_force` and `body_add_constant_central_force`.
     *
     * Generated from Godot docs: PhysicsServer3D.body_get_constant_force
     */
    @JvmStatic
    fun bodyGetConstantForce(body: RID): Vector3 {
        return ObjectCalls.ptrcallWithRIDArgRetVector3(bodyGetConstantForceBind, singleton, body)
    }

    /**
     * Sets the body's total constant rotational forces applied during each physics update. See
     * `body_add_constant_torque`.
     *
     * Generated from Godot docs: PhysicsServer3D.body_set_constant_torque
     */
    @JvmStatic
    fun bodySetConstantTorque(body: RID, torque: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(bodySetConstantTorqueBind, singleton, body, torque)
    }

    /**
     * Returns the body's total constant rotational forces applied during each physics update. See
     * `body_add_constant_torque`.
     *
     * Generated from Godot docs: PhysicsServer3D.body_get_constant_torque
     */
    @JvmStatic
    fun bodyGetConstantTorque(body: RID): Vector3 {
        return ObjectCalls.ptrcallWithRIDArgRetVector3(bodyGetConstantTorqueBind, singleton, body)
    }

    /**
     * Sets an axis velocity. The velocity in the given vector axis will be set as the given vector
     * length. This is useful for jumping behavior.
     *
     * Generated from Godot docs: PhysicsServer3D.body_set_axis_velocity
     */
    @JvmStatic
    fun bodySetAxisVelocity(body: RID, axisVelocity: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(bodySetAxisVelocityBind, singleton, body, axisVelocity)
    }

    @JvmStatic
    fun bodySetAxisLock(body: RID, axis: Long, lock: Boolean) {
        ObjectCalls.ptrcallWithRIDLongAndBoolArgs(bodySetAxisLockBind, singleton, body, axis, lock)
    }

    @JvmStatic
    fun bodyIsAxisLocked(body: RID, axis: Long): Boolean {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetBool(bodyIsAxisLockedBind, singleton, body, axis)
    }

    /**
     * Adds a body to the list of bodies exempt from collisions.
     *
     * Generated from Godot docs: PhysicsServer3D.body_add_collision_exception
     */
    @JvmStatic
    fun bodyAddCollisionException(body: RID, exceptedBody: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(bodyAddCollisionExceptionBind, singleton, body, exceptedBody)
    }

    /**
     * Removes a body from the list of bodies exempt from collisions. Continuous collision detection
     * tries to predict where a moving body will collide, instead of moving it and correcting its
     * movement if it collided.
     *
     * Generated from Godot docs: PhysicsServer3D.body_remove_collision_exception
     */
    @JvmStatic
    fun bodyRemoveCollisionException(body: RID, exceptedBody: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(bodyRemoveCollisionExceptionBind, singleton, body, exceptedBody)
    }

    /**
     * Sets the maximum contacts to report. Bodies can keep a log of the contacts with other bodies.
     * This is enabled by setting the maximum number of contacts reported to a number greater than 0.
     *
     * Generated from Godot docs: PhysicsServer3D.body_set_max_contacts_reported
     */
    @JvmStatic
    fun bodySetMaxContactsReported(body: RID, amount: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(bodySetMaxContactsReportedBind, singleton, body, amount)
    }

    /**
     * Returns the maximum contacts that can be reported. See `body_set_max_contacts_reported`.
     *
     * Generated from Godot docs: PhysicsServer3D.body_get_max_contacts_reported
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
     * `RigidBody3D.custom_integrator` is set.
     *
     * Generated from Godot docs: PhysicsServer3D.body_set_omit_force_integration
     */
    @JvmStatic
    fun bodySetOmitForceIntegration(body: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(bodySetOmitForceIntegrationBind, singleton, body, enable)
    }

    /**
     * Returns `true` if the body is omitting the standard force integration. See
     * `body_set_omit_force_integration`.
     *
     * Generated from Godot docs: PhysicsServer3D.body_is_omitting_force_integration
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
     * parameters: 1. `state`: a `PhysicsDirectBodyState3D`, used to retrieve the body's state.
     *
     * Generated from Godot docs: PhysicsServer3D.body_set_state_sync_callback
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
     * `callable` must take the following two parameters: 1. `state`: a `PhysicsDirectBodyState3D`,
     * used to retrieve and modify the body's state, 2. `userdata`: a `Variant`; its value will be the
     * `userdata` passed into this method. If `userdata` is `null`, then `callable` must take only the
     * `state` parameter.
     *
     * Generated from Godot docs: PhysicsServer3D.body_set_force_integration_callback
     */
    @JvmStatic
    fun bodySetForceIntegrationCallback(body: RID, callable: GodotCallable, userdata: Any? = null) {
        ObjectCalls.ptrcallWithRIDCallableVariantArgs(bodySetForceIntegrationCallbackBind, singleton, body, callable.target.handle, callable.method, userdata)
    }

    /**
     * Sets the body pickable with rays if `enable` is set.
     *
     * Generated from Godot docs: PhysicsServer3D.body_set_ray_pickable
     */
    @JvmStatic
    fun bodySetRayPickable(body: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(bodySetRayPickableBind, singleton, body, enable)
    }

    /**
     * Returns `true` if a collision would result from moving along a motion vector from a given point
     * in space. `PhysicsTestMotionParameters3D` is passed to set motion parameters.
     * `PhysicsTestMotionResult3D` can be passed to return additional information.
     *
     * Generated from Godot docs: PhysicsServer3D.body_test_motion
     */
    @JvmStatic
    fun bodyTestMotion(body: RID, parameters: PhysicsTestMotionParameters3D, result: PhysicsTestMotionResult3D?): Boolean {
        return ObjectCalls.ptrcallWithRIDAndTwoObjectArgsRetBool(bodyTestMotionBind, singleton, body, parameters.requireOpenHandle(), result?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Returns the `PhysicsDirectBodyState3D` of the body. Returns `null` if the body is destroyed or
     * removed from the physics space.
     *
     * Generated from Godot docs: PhysicsServer3D.body_get_direct_state
     */
    @JvmStatic
    fun bodyGetDirectState(body: RID): PhysicsDirectBodyState3D? {
        return PhysicsDirectBodyState3D.wrap(ObjectCalls.ptrcallWithRIDArgRetObject(bodyGetDirectStateBind, singleton, body))
    }

    /**
     * Creates a new soft body and returns its internal `RID`.
     *
     * Generated from Godot docs: PhysicsServer3D.soft_body_create
     */
    @JvmStatic
    fun softBodyCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(softBodyCreateBind, singleton)
    }

    /**
     * Requests that the physics server updates the rendering server with the latest positions of the
     * given soft body's points through the `rendering_server_handler` interface.
     *
     * Generated from Godot docs: PhysicsServer3D.soft_body_update_rendering_server
     */
    @JvmStatic
    fun softBodyUpdateRenderingServer(body: RID, renderingServerHandler: PhysicsServer3DRenderingServerHandler) {
        ObjectCalls.ptrcallWithRIDAndObjectArg(softBodyUpdateRenderingServerBind, singleton, body, renderingServerHandler.handle)
    }

    /**
     * Assigns a space to the given soft body (see `space_create`).
     *
     * Generated from Godot docs: PhysicsServer3D.soft_body_set_space
     */
    @JvmStatic
    fun softBodySetSpace(body: RID, space: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(softBodySetSpaceBind, singleton, body, space)
    }

    /**
     * Returns the `RID` of the space assigned to the given soft body.
     *
     * Generated from Godot docs: PhysicsServer3D.soft_body_get_space
     */
    @JvmStatic
    fun softBodyGetSpace(body: RID): RID {
        return ObjectCalls.ptrcallWithRIDArgRetRID(softBodyGetSpaceBind, singleton, body)
    }

    /**
     * Sets the mesh of the given soft body.
     *
     * Generated from Godot docs: PhysicsServer3D.soft_body_set_mesh
     */
    @JvmStatic
    fun softBodySetMesh(body: RID, mesh: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(softBodySetMeshBind, singleton, body, mesh)
    }

    /**
     * Returns the bounds of the given soft body in global coordinates.
     *
     * Generated from Godot docs: PhysicsServer3D.soft_body_get_bounds
     */
    @JvmStatic
    fun softBodyGetBounds(body: RID): AABB {
        return ObjectCalls.ptrcallWithRIDArgRetAABB(softBodyGetBoundsBind, singleton, body)
    }

    /**
     * Sets the physics layer or layers the given soft body belongs to.
     *
     * Generated from Godot docs: PhysicsServer3D.soft_body_set_collision_layer
     */
    @JvmStatic
    fun softBodySetCollisionLayer(body: RID, layer: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(softBodySetCollisionLayerBind, singleton, body, layer)
    }

    /**
     * Returns the physics layer or layers that the given soft body belongs to.
     *
     * Generated from Godot docs: PhysicsServer3D.soft_body_get_collision_layer
     */
    @JvmStatic
    fun softBodyGetCollisionLayer(body: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetUInt32(softBodyGetCollisionLayerBind, singleton, body)
    }

    /**
     * Sets the physics layer or layers the given soft body can collide with.
     *
     * Generated from Godot docs: PhysicsServer3D.soft_body_set_collision_mask
     */
    @JvmStatic
    fun softBodySetCollisionMask(body: RID, mask: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(softBodySetCollisionMaskBind, singleton, body, mask)
    }

    /**
     * Returns the physics layer or layers that the given soft body can collide with.
     *
     * Generated from Godot docs: PhysicsServer3D.soft_body_get_collision_mask
     */
    @JvmStatic
    fun softBodyGetCollisionMask(body: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetUInt32(softBodyGetCollisionMaskBind, singleton, body)
    }

    /**
     * Adds the given body to the list of bodies exempt from collisions.
     *
     * Generated from Godot docs: PhysicsServer3D.soft_body_add_collision_exception
     */
    @JvmStatic
    fun softBodyAddCollisionException(body: RID, bodyB: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(softBodyAddCollisionExceptionBind, singleton, body, bodyB)
    }

    /**
     * Removes the given body from the list of bodies exempt from collisions.
     *
     * Generated from Godot docs: PhysicsServer3D.soft_body_remove_collision_exception
     */
    @JvmStatic
    fun softBodyRemoveCollisionException(body: RID, bodyB: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(softBodyRemoveCollisionExceptionBind, singleton, body, bodyB)
    }

    /**
     * Sets the given body state for the given body. Note: Godot's default physics implementation does
     * not support `BODY_STATE_LINEAR_VELOCITY`, `BODY_STATE_ANGULAR_VELOCITY`, `BODY_STATE_SLEEPING`,
     * or `BODY_STATE_CAN_SLEEP`.
     *
     * Generated from Godot docs: PhysicsServer3D.soft_body_set_state
     */
    @JvmStatic
    fun softBodySetState(body: RID, state: Long, variant: Any?) {
        ObjectCalls.ptrcallWithRIDLongAndVariantArgs(softBodySetStateBind, singleton, body, state, variant)
    }

    /**
     * Returns the given soft body state. Note: Godot's default physics implementation does not support
     * `BODY_STATE_LINEAR_VELOCITY`, `BODY_STATE_ANGULAR_VELOCITY`, `BODY_STATE_SLEEPING`, or
     * `BODY_STATE_CAN_SLEEP`.
     *
     * Generated from Godot docs: PhysicsServer3D.soft_body_get_state
     */
    @JvmStatic
    fun softBodyGetState(body: RID, state: Long): Any? {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetVariantScalar(softBodyGetStateBind, singleton, body, state)
    }

    /**
     * Sets the global transform of the given soft body.
     *
     * Generated from Godot docs: PhysicsServer3D.soft_body_set_transform
     */
    @JvmStatic
    fun softBodySetTransform(body: RID, transform: Transform3D) {
        ObjectCalls.ptrcallWithRIDAndTransform3DArg(softBodySetTransformBind, singleton, body, transform)
    }

    /**
     * Sets whether the given soft body will be pickable when using object picking.
     *
     * Generated from Godot docs: PhysicsServer3D.soft_body_set_ray_pickable
     */
    @JvmStatic
    fun softBodySetRayPickable(body: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(softBodySetRayPickableBind, singleton, body, enable)
    }

    /**
     * Sets the simulation precision of the given soft body. Increasing this value will improve the
     * resulting simulation, but can affect performance. Use with care.
     *
     * Generated from Godot docs: PhysicsServer3D.soft_body_set_simulation_precision
     */
    @JvmStatic
    fun softBodySetSimulationPrecision(body: RID, simulationPrecision: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(softBodySetSimulationPrecisionBind, singleton, body, simulationPrecision)
    }

    /**
     * Returns the simulation precision of the given soft body.
     *
     * Generated from Godot docs: PhysicsServer3D.soft_body_get_simulation_precision
     */
    @JvmStatic
    fun softBodyGetSimulationPrecision(body: RID): Int {
        return ObjectCalls.ptrcallWithRIDArgRetInt(softBodyGetSimulationPrecisionBind, singleton, body)
    }

    /**
     * Sets the total mass for the given soft body.
     *
     * Generated from Godot docs: PhysicsServer3D.soft_body_set_total_mass
     */
    @JvmStatic
    fun softBodySetTotalMass(body: RID, totalMass: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(softBodySetTotalMassBind, singleton, body, totalMass)
    }

    /**
     * Returns the total mass assigned to the given soft body.
     *
     * Generated from Godot docs: PhysicsServer3D.soft_body_get_total_mass
     */
    @JvmStatic
    fun softBodyGetTotalMass(body: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(softBodyGetTotalMassBind, singleton, body)
    }

    /**
     * Sets the linear stiffness of the given soft body. Higher values will result in a stiffer body,
     * while lower values will increase the body's ability to bend. The value can be between `0.0` and
     * `1.0` (inclusive).
     *
     * Generated from Godot docs: PhysicsServer3D.soft_body_set_linear_stiffness
     */
    @JvmStatic
    fun softBodySetLinearStiffness(body: RID, stiffness: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(softBodySetLinearStiffnessBind, singleton, body, stiffness)
    }

    /**
     * Returns the linear stiffness of the given soft body.
     *
     * Generated from Godot docs: PhysicsServer3D.soft_body_get_linear_stiffness
     */
    @JvmStatic
    fun softBodyGetLinearStiffness(body: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(softBodyGetLinearStiffnessBind, singleton, body)
    }

    /**
     * Sets the shrinking factor of the given soft body.
     *
     * Generated from Godot docs: PhysicsServer3D.soft_body_set_shrinking_factor
     */
    @JvmStatic
    fun softBodySetShrinkingFactor(body: RID, shrinkingFactor: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(softBodySetShrinkingFactorBind, singleton, body, shrinkingFactor)
    }

    /**
     * Returns the shrinking factor of the given soft body.
     *
     * Generated from Godot docs: PhysicsServer3D.soft_body_get_shrinking_factor
     */
    @JvmStatic
    fun softBodyGetShrinkingFactor(body: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(softBodyGetShrinkingFactorBind, singleton, body)
    }

    /**
     * Sets the pressure coefficient of the given soft body. Simulates pressure build-up from inside
     * this body. Higher values increase the strength of this effect.
     *
     * Generated from Godot docs: PhysicsServer3D.soft_body_set_pressure_coefficient
     */
    @JvmStatic
    fun softBodySetPressureCoefficient(body: RID, pressureCoefficient: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(softBodySetPressureCoefficientBind, singleton, body, pressureCoefficient)
    }

    /**
     * Returns the pressure coefficient of the given soft body.
     *
     * Generated from Godot docs: PhysicsServer3D.soft_body_get_pressure_coefficient
     */
    @JvmStatic
    fun softBodyGetPressureCoefficient(body: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(softBodyGetPressureCoefficientBind, singleton, body)
    }

    /**
     * Sets the damping coefficient of the given soft body. Higher values will slow down the body more
     * noticeably when forces are applied.
     *
     * Generated from Godot docs: PhysicsServer3D.soft_body_set_damping_coefficient
     */
    @JvmStatic
    fun softBodySetDampingCoefficient(body: RID, dampingCoefficient: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(softBodySetDampingCoefficientBind, singleton, body, dampingCoefficient)
    }

    /**
     * Returns the damping coefficient of the given soft body.
     *
     * Generated from Godot docs: PhysicsServer3D.soft_body_get_damping_coefficient
     */
    @JvmStatic
    fun softBodyGetDampingCoefficient(body: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(softBodyGetDampingCoefficientBind, singleton, body)
    }

    /**
     * Sets the drag coefficient of the given soft body. Higher values increase this body's air
     * resistance. Note: This value is currently unused by Godot's default physics implementation.
     *
     * Generated from Godot docs: PhysicsServer3D.soft_body_set_drag_coefficient
     */
    @JvmStatic
    fun softBodySetDragCoefficient(body: RID, dragCoefficient: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(softBodySetDragCoefficientBind, singleton, body, dragCoefficient)
    }

    /**
     * Returns the drag coefficient of the given soft body.
     *
     * Generated from Godot docs: PhysicsServer3D.soft_body_get_drag_coefficient
     */
    @JvmStatic
    fun softBodyGetDragCoefficient(body: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(softBodyGetDragCoefficientBind, singleton, body)
    }

    /**
     * Moves the given soft body point to a position in global coordinates.
     *
     * Generated from Godot docs: PhysicsServer3D.soft_body_move_point
     */
    @JvmStatic
    fun softBodyMovePoint(body: RID, pointIndex: Int, globalPosition: Vector3) {
        ObjectCalls.ptrcallWithRIDIntAndVector3Arg(softBodyMovePointBind, singleton, body, pointIndex, globalPosition)
    }

    /**
     * Returns the current position of the given soft body point in global coordinates.
     *
     * Generated from Godot docs: PhysicsServer3D.soft_body_get_point_global_position
     */
    @JvmStatic
    fun softBodyGetPointGlobalPosition(body: RID, pointIndex: Int): Vector3 {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetVector3(softBodyGetPointGlobalPositionBind, singleton, body, pointIndex)
    }

    /**
     * Unpins all points of the given soft body.
     *
     * Generated from Godot docs: PhysicsServer3D.soft_body_remove_all_pinned_points
     */
    @JvmStatic
    fun softBodyRemoveAllPinnedPoints(body: RID) {
        ObjectCalls.ptrcallWithRIDArg(softBodyRemoveAllPinnedPointsBind, singleton, body)
    }

    /**
     * Pins or unpins the given soft body point based on the value of `pin`. Note: Pinning a point
     * effectively makes it kinematic, preventing it from being affected by forces, but you can still
     * move it using `soft_body_move_point`.
     *
     * Generated from Godot docs: PhysicsServer3D.soft_body_pin_point
     */
    @JvmStatic
    fun softBodyPinPoint(body: RID, pointIndex: Int, pin: Boolean) {
        ObjectCalls.ptrcallWithRIDIntAndBoolArgs(softBodyPinPointBind, singleton, body, pointIndex, pin)
    }

    /**
     * Returns whether the given soft body point is pinned.
     *
     * Generated from Godot docs: PhysicsServer3D.soft_body_is_point_pinned
     */
    @JvmStatic
    fun softBodyIsPointPinned(body: RID, pointIndex: Int): Boolean {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetBool(softBodyIsPointPinnedBind, singleton, body, pointIndex)
    }

    /**
     * Applies an impulse to a point. An impulse is time-independent! Applying an impulse every frame
     * would result in a framerate-dependent force. For this reason, it should only be used when
     * simulating one-time impacts (use the "_force" functions otherwise).
     *
     * Generated from Godot docs: PhysicsServer3D.soft_body_apply_point_impulse
     */
    @JvmStatic
    fun softBodyApplyPointImpulse(body: RID, pointIndex: Int, impulse: Vector3) {
        ObjectCalls.ptrcallWithRIDIntAndVector3Arg(softBodyApplyPointImpulseBind, singleton, body, pointIndex, impulse)
    }

    /**
     * Applies a force to a point. A force is time dependent and meant to be applied every physics
     * update.
     *
     * Generated from Godot docs: PhysicsServer3D.soft_body_apply_point_force
     */
    @JvmStatic
    fun softBodyApplyPointForce(body: RID, pointIndex: Int, force: Vector3) {
        ObjectCalls.ptrcallWithRIDIntAndVector3Arg(softBodyApplyPointForceBind, singleton, body, pointIndex, force)
    }

    /**
     * Distributes and applies an impulse to all points. An impulse is time-independent! Applying an
     * impulse every frame would result in a framerate-dependent force. For this reason, it should only
     * be used when simulating one-time impacts (use the "_force" functions otherwise).
     *
     * Generated from Godot docs: PhysicsServer3D.soft_body_apply_central_impulse
     */
    @JvmStatic
    fun softBodyApplyCentralImpulse(body: RID, impulse: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(softBodyApplyCentralImpulseBind, singleton, body, impulse)
    }

    /**
     * Distributes and applies a force to all points. A force is time dependent and meant to be applied
     * every physics update.
     *
     * Generated from Godot docs: PhysicsServer3D.soft_body_apply_central_force
     */
    @JvmStatic
    fun softBodyApplyCentralForce(body: RID, force: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(softBodyApplyCentralForceBind, singleton, body, force)
    }

    @JvmStatic
    fun jointCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(jointCreateBind, singleton)
    }

    @JvmStatic
    fun jointClear(joint: RID) {
        ObjectCalls.ptrcallWithRIDArg(jointClearBind, singleton, joint)
    }

    @JvmStatic
    fun jointMakePin(joint: RID, bodyA: RID, localA: Vector3, bodyB: RID, localB: Vector3) {
        ObjectCalls.ptrcallWithTwoRIDVector3RIDVector3Args(jointMakePinBind, singleton, joint, bodyA, localA, bodyB, localB)
    }

    /**
     * Sets a pin joint parameter.
     *
     * Generated from Godot docs: PhysicsServer3D.pin_joint_set_param
     */
    @JvmStatic
    fun pinJointSetParam(joint: RID, param: Long, value: Double) {
        ObjectCalls.ptrcallWithRIDLongAndDoubleArgs(pinJointSetParamBind, singleton, joint, param, value)
    }

    /**
     * Gets a pin joint parameter.
     *
     * Generated from Godot docs: PhysicsServer3D.pin_joint_get_param
     */
    @JvmStatic
    fun pinJointGetParam(joint: RID, param: Long): Double {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetDouble(pinJointGetParamBind, singleton, joint, param)
    }

    /**
     * Sets position of the joint in the local space of body a of the joint.
     *
     * Generated from Godot docs: PhysicsServer3D.pin_joint_set_local_a
     */
    @JvmStatic
    fun pinJointSetLocalA(joint: RID, localA: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(pinJointSetLocalABind, singleton, joint, localA)
    }

    /**
     * Returns position of the joint in the local space of body a of the joint.
     *
     * Generated from Godot docs: PhysicsServer3D.pin_joint_get_local_a
     */
    @JvmStatic
    fun pinJointGetLocalA(joint: RID): Vector3 {
        return ObjectCalls.ptrcallWithRIDArgRetVector3(pinJointGetLocalABind, singleton, joint)
    }

    /**
     * Sets position of the joint in the local space of body b of the joint.
     *
     * Generated from Godot docs: PhysicsServer3D.pin_joint_set_local_b
     */
    @JvmStatic
    fun pinJointSetLocalB(joint: RID, localB: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(pinJointSetLocalBBind, singleton, joint, localB)
    }

    /**
     * Returns position of the joint in the local space of body b of the joint.
     *
     * Generated from Godot docs: PhysicsServer3D.pin_joint_get_local_b
     */
    @JvmStatic
    fun pinJointGetLocalB(joint: RID): Vector3 {
        return ObjectCalls.ptrcallWithRIDArgRetVector3(pinJointGetLocalBBind, singleton, joint)
    }

    @JvmStatic
    fun jointMakeHinge(joint: RID, bodyA: RID, hingeA: Transform3D, bodyB: RID, hingeB: Transform3D) {
        ObjectCalls.ptrcallWithRIDRIDTransform3DRIDTransform3DArgs(jointMakeHingeBind, singleton, joint, bodyA, hingeA, bodyB, hingeB)
    }

    /**
     * Sets a hinge joint parameter.
     *
     * Generated from Godot docs: PhysicsServer3D.hinge_joint_set_param
     */
    @JvmStatic
    fun hingeJointSetParam(joint: RID, param: Long, value: Double) {
        ObjectCalls.ptrcallWithRIDLongAndDoubleArgs(hingeJointSetParamBind, singleton, joint, param, value)
    }

    /**
     * Gets a hinge joint parameter.
     *
     * Generated from Godot docs: PhysicsServer3D.hinge_joint_get_param
     */
    @JvmStatic
    fun hingeJointGetParam(joint: RID, param: Long): Double {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetDouble(hingeJointGetParamBind, singleton, joint, param)
    }

    /**
     * Sets a hinge joint flag.
     *
     * Generated from Godot docs: PhysicsServer3D.hinge_joint_set_flag
     */
    @JvmStatic
    fun hingeJointSetFlag(joint: RID, flag: Long, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDLongAndBoolArgs(hingeJointSetFlagBind, singleton, joint, flag, enabled)
    }

    /**
     * Gets a hinge joint flag.
     *
     * Generated from Godot docs: PhysicsServer3D.hinge_joint_get_flag
     */
    @JvmStatic
    fun hingeJointGetFlag(joint: RID, flag: Long): Boolean {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetBool(hingeJointGetFlagBind, singleton, joint, flag)
    }

    @JvmStatic
    fun jointMakeSlider(joint: RID, bodyA: RID, localRefA: Transform3D, bodyB: RID, localRefB: Transform3D) {
        ObjectCalls.ptrcallWithRIDRIDTransform3DRIDTransform3DArgs(jointMakeSliderBind, singleton, joint, bodyA, localRefA, bodyB, localRefB)
    }

    /**
     * Gets a slider joint parameter.
     *
     * Generated from Godot docs: PhysicsServer3D.slider_joint_set_param
     */
    @JvmStatic
    fun sliderJointSetParam(joint: RID, param: Long, value: Double) {
        ObjectCalls.ptrcallWithRIDLongAndDoubleArgs(sliderJointSetParamBind, singleton, joint, param, value)
    }

    /**
     * Gets a slider joint parameter.
     *
     * Generated from Godot docs: PhysicsServer3D.slider_joint_get_param
     */
    @JvmStatic
    fun sliderJointGetParam(joint: RID, param: Long): Double {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetDouble(sliderJointGetParamBind, singleton, joint, param)
    }

    @JvmStatic
    fun jointMakeConeTwist(joint: RID, bodyA: RID, localRefA: Transform3D, bodyB: RID, localRefB: Transform3D) {
        ObjectCalls.ptrcallWithRIDRIDTransform3DRIDTransform3DArgs(jointMakeConeTwistBind, singleton, joint, bodyA, localRefA, bodyB, localRefB)
    }

    /**
     * Sets a cone twist joint parameter.
     *
     * Generated from Godot docs: PhysicsServer3D.cone_twist_joint_set_param
     */
    @JvmStatic
    fun coneTwistJointSetParam(joint: RID, param: Long, value: Double) {
        ObjectCalls.ptrcallWithRIDLongAndDoubleArgs(coneTwistJointSetParamBind, singleton, joint, param, value)
    }

    /**
     * Gets a cone twist joint parameter.
     *
     * Generated from Godot docs: PhysicsServer3D.cone_twist_joint_get_param
     */
    @JvmStatic
    fun coneTwistJointGetParam(joint: RID, param: Long): Double {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetDouble(coneTwistJointGetParamBind, singleton, joint, param)
    }

    /**
     * Returns the type of the Joint3D.
     *
     * Generated from Godot docs: PhysicsServer3D.joint_get_type
     */
    @JvmStatic
    fun jointGetType(joint: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(jointGetTypeBind, singleton, joint)
    }

    /**
     * Sets the priority value of the Joint3D. Note: Only supported when using GodotPhysics3D. This
     * method has no effect when using Jolt Physics, as it does not support joint solver priority.
     *
     * Generated from Godot docs: PhysicsServer3D.joint_set_solver_priority
     */
    @JvmStatic
    fun jointSetSolverPriority(joint: RID, priority: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(jointSetSolverPriorityBind, singleton, joint, priority)
    }

    /**
     * Gets the priority value of the Joint3D. Note: Only supported when using GodotPhysics3D. This
     * method always returns `1` when using Jolt Physics, as it does not support joint solver priority.
     *
     * Generated from Godot docs: PhysicsServer3D.joint_get_solver_priority
     */
    @JvmStatic
    fun jointGetSolverPriority(joint: RID): Int {
        return ObjectCalls.ptrcallWithRIDArgRetInt(jointGetSolverPriorityBind, singleton, joint)
    }

    /**
     * Sets whether the bodies attached to the `Joint3D` will collide with each other.
     *
     * Generated from Godot docs: PhysicsServer3D.joint_disable_collisions_between_bodies
     */
    @JvmStatic
    fun jointDisableCollisionsBetweenBodies(joint: RID, disable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(jointDisableCollisionsBetweenBodiesBind, singleton, joint, disable)
    }

    /**
     * Returns whether the bodies attached to the `Joint3D` will collide with each other.
     *
     * Generated from Godot docs: PhysicsServer3D.joint_is_disabled_collisions_between_bodies
     */
    @JvmStatic
    fun jointIsDisabledCollisionsBetweenBodies(joint: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(jointIsDisabledCollisionsBetweenBodiesBind, singleton, joint)
    }

    /**
     * Make the joint a generic six degrees of freedom (6DOF) joint. Use `generic_6dof_joint_set_flag`
     * and `generic_6dof_joint_set_param` to set the joint's flags and parameters respectively.
     *
     * Generated from Godot docs: PhysicsServer3D.joint_make_generic_6dof
     */
    @JvmStatic
    fun jointMakeGeneric6dof(joint: RID, bodyA: RID, localRefA: Transform3D, bodyB: RID, localRefB: Transform3D) {
        ObjectCalls.ptrcallWithRIDRIDTransform3DRIDTransform3DArgs(jointMakeGeneric6dofBind, singleton, joint, bodyA, localRefA, bodyB, localRefB)
    }

    /**
     * Sets the value of a given generic 6DOF joint parameter.
     *
     * Generated from Godot docs: PhysicsServer3D.generic_6dof_joint_set_param
     */
    @JvmStatic
    fun generic6dofJointSetParam(joint: RID, axis: Long, param: Long, value: Double) {
        ObjectCalls.ptrcallWithRIDTwoLongDoubleArgs(generic6dofJointSetParamBind, singleton, joint, axis, param, value)
    }

    /**
     * Returns the value of a generic 6DOF joint parameter.
     *
     * Generated from Godot docs: PhysicsServer3D.generic_6dof_joint_get_param
     */
    @JvmStatic
    fun generic6dofJointGetParam(joint: RID, axis: Long, param: Long): Double {
        return ObjectCalls.ptrcallWithRIDTwoLongArgsRetDouble(generic6dofJointGetParamBind, singleton, joint, axis, param)
    }

    /**
     * Sets the value of a given generic 6DOF joint flag.
     *
     * Generated from Godot docs: PhysicsServer3D.generic_6dof_joint_set_flag
     */
    @JvmStatic
    fun generic6dofJointSetFlag(joint: RID, axis: Long, flag: Long, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDTwoLongBoolArgs(generic6dofJointSetFlagBind, singleton, joint, axis, flag, enable)
    }

    /**
     * Returns the value of a generic 6DOF joint flag.
     *
     * Generated from Godot docs: PhysicsServer3D.generic_6dof_joint_get_flag
     */
    @JvmStatic
    fun generic6dofJointGetFlag(joint: RID, axis: Long, flag: Long): Boolean {
        return ObjectCalls.ptrcallWithRIDTwoLongArgsRetBool(generic6dofJointGetFlagBind, singleton, joint, axis, flag)
    }

    /**
     * Destroys any of the objects created by PhysicsServer3D. If the `RID` passed is not one of the
     * objects that can be created by PhysicsServer3D, an error will be sent to the console.
     *
     * Generated from Godot docs: PhysicsServer3D.free_rid
     */
    @JvmStatic
    fun freeRid(rid: RID) {
        ObjectCalls.ptrcallWithRIDArg(freeRidBind, singleton, rid)
    }

    /**
     * Activates or deactivates the 3D physics engine.
     *
     * Generated from Godot docs: PhysicsServer3D.set_active
     */
    @JvmStatic
    fun setActive(active: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setActiveBind, singleton, active)
    }

    /**
     * Returns the value of a physics engine state specified by `process_info`.
     *
     * Generated from Godot docs: PhysicsServer3D.get_process_info
     */
    @JvmStatic
    fun getProcessInfo(processInfo: Long): Int {
        return ObjectCalls.ptrcallWithLongArgRetInt(getProcessInfoBind, singleton, processInfo)
    }

    @JvmStatic
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

    private const val SHAPE_SET_DATA_HASH = 3175752987L
    private val shapeSetDataBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "shape_set_data", SHAPE_SET_DATA_HASH)
    }

    private const val SHAPE_SET_MARGIN_HASH = 1794382983L
    private val shapeSetMarginBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "shape_set_margin", SHAPE_SET_MARGIN_HASH)
    }

    private const val SHAPE_GET_TYPE_HASH = 3418923367L
    private val shapeGetTypeBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "shape_get_type", SHAPE_GET_TYPE_HASH)
    }

    private const val SHAPE_GET_DATA_HASH = 4171304767L
    private val shapeGetDataBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "shape_get_data", SHAPE_GET_DATA_HASH)
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

    private const val AREA_SET_PARAM_HASH = 2980114638L
    private val areaSetParamBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "area_set_param", AREA_SET_PARAM_HASH)
    }

    private const val AREA_SET_TRANSFORM_HASH = 3935195649L
    private val areaSetTransformBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "area_set_transform", AREA_SET_TRANSFORM_HASH)
    }

    private const val AREA_GET_PARAM_HASH = 890056067L
    private val areaGetParamBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "area_get_param", AREA_GET_PARAM_HASH)
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

    private const val BODY_SET_PARAM_HASH = 910941953L
    private val bodySetParamBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_set_param", BODY_SET_PARAM_HASH)
    }

    private const val BODY_GET_PARAM_HASH = 3385027841L
    private val bodyGetParamBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_get_param", BODY_GET_PARAM_HASH)
    }

    private const val BODY_RESET_MASS_PROPERTIES_HASH = 2722037293L
    private val bodyResetMassPropertiesBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_reset_mass_properties", BODY_RESET_MASS_PROPERTIES_HASH)
    }

    private const val BODY_SET_STATE_HASH = 599977762L
    private val bodySetStateBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_set_state", BODY_SET_STATE_HASH)
    }

    private const val BODY_GET_STATE_HASH = 1850449534L
    private val bodyGetStateBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_get_state", BODY_GET_STATE_HASH)
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

    private const val BODY_SET_FORCE_INTEGRATION_CALLBACK_HASH = 3059434249L
    private val bodySetForceIntegrationCallbackBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_set_force_integration_callback", BODY_SET_FORCE_INTEGRATION_CALLBACK_HASH)
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

    private const val SOFT_BODY_SET_STATE_HASH = 599977762L
    private val softBodySetStateBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "soft_body_set_state", SOFT_BODY_SET_STATE_HASH)
    }

    private const val SOFT_BODY_GET_STATE_HASH = 1850449534L
    private val softBodyGetStateBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "soft_body_get_state", SOFT_BODY_GET_STATE_HASH)
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
