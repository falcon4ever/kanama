package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Vector2

/**
 * Provides direct access to a physics space in the `PhysicsServer2D`.
 *
 * Generated from Godot docs: PhysicsDirectSpaceState2D
 */
open class PhysicsDirectSpaceState2D(handle: GodotHandle) : GodotObject(handle) {
    /**
     * Intersects a ray in a given space. Ray position and other parameters are defined through
     * `PhysicsRayQueryParameters2D`. The returned object is a dictionary with the following fields:
     * `collider`: The colliding object. `collider_id`: The colliding object's ID. `normal`: The
     * object's surface normal at the intersection point, or `Vector2(0, 0)` if the ray starts inside
     * the shape and `PhysicsRayQueryParameters2D.hit_from_inside` is `true`. `position`: The
     * intersection point. `rid`: The intersecting object's `RID`. `shape`: The shape index of the
     * colliding shape. If the ray did not intersect anything, then an empty dictionary is returned
     * instead.
     *
     * Generated from Godot docs: PhysicsDirectSpaceState2D.intersect_ray
     */
    fun intersectRay(parameters: PhysicsRayQueryParameters2D): Map<String, Any?> {
        val ret = ObjectCalls.ptrcallWithObjectArgRetDictionary(Binds.intersectRayBind, segment, parameters.requireOpenHandle())
        ObjectCalls.keepReachable(parameters)
        return ret
    }

    /**
     * Checks whether a point is inside any solid shape. Position and other parameters are defined
     * through `PhysicsPointQueryParameters2D`. The shapes the point is inside of are returned in an
     * array containing dictionaries with the following fields: `collider`: The colliding object.
     * `collider_id`: The colliding object's ID. `rid`: The intersecting object's `RID`. `shape`: The
     * shape index of the colliding shape. The number of intersections can be limited with the
     * `max_results` parameter, to reduce the processing time. Note: `ConcavePolygonShape2D`s and
     * `CollisionPolygon2D`s in `Segments` build mode are not solid shapes. Therefore, they will not be
     * detected.
     *
     * Generated from Godot docs: PhysicsDirectSpaceState2D.intersect_point
     */
    fun intersectPoint(parameters: PhysicsPointQueryParameters2D, maxResults: Int = 32): List<Map<String, Any?>> {
        val ret = ObjectCalls.ptrcallWithObjectAndIntArgRetDictionaryList(Binds.intersectPointBind, segment, parameters.requireOpenHandle(), maxResults)
        ObjectCalls.keepReachable(parameters)
        return ret
    }

    /**
     * Checks the intersections of a shape, given through a `PhysicsShapeQueryParameters2D` object,
     * against the space. The intersected shapes are returned in an array containing dictionaries with
     * the following fields: `collider`: The colliding object. `collider_id`: The colliding object's
     * ID. `rid`: The intersecting object's `RID`. `shape`: The shape index of the colliding shape. The
     * number of intersections can be limited with the `max_results` parameter, to reduce the
     * processing time.
     *
     * Generated from Godot docs: PhysicsDirectSpaceState2D.intersect_shape
     */
    fun intersectShape(parameters: PhysicsShapeQueryParameters2D, maxResults: Int = 32): List<Map<String, Any?>> {
        val ret = ObjectCalls.ptrcallWithObjectAndIntArgRetDictionaryList(Binds.intersectShapeBind, segment, parameters.requireOpenHandle(), maxResults)
        ObjectCalls.keepReachable(parameters)
        return ret
    }

    /**
     * Checks how far a `Shape2D` can move without colliding. All the parameters for the query,
     * including the shape and the motion, are supplied through a `PhysicsShapeQueryParameters2D`
     * object. Returns an array with the safe and unsafe proportions (between 0 and 1) of the motion.
     * The safe proportion is the maximum fraction of the motion that can be made without a collision.
     * The unsafe proportion is the minimum fraction of the distance that must be moved for a
     * collision. If no collision is detected a result of `[1.0, 1.0]` will be returned. Note: Any
     * `Shape2D`s that the shape is already colliding with e.g. inside of, will be ignored. Use
     * `collide_shape` to determine the `Shape2D`s that the shape is already colliding with.
     *
     * Generated from Godot docs: PhysicsDirectSpaceState2D.cast_motion
     */
    fun castMotion(parameters: PhysicsShapeQueryParameters2D): List<Float> {
        val ret = ObjectCalls.ptrcallWithObjectArgRetPackedFloat32List(Binds.castMotionBind, segment, parameters.requireOpenHandle())
        ObjectCalls.keepReachable(parameters)
        return ret
    }

    /**
     * Checks the intersections of a shape, given through a `PhysicsShapeQueryParameters2D` object,
     * against the space. The resulting array contains a list of points where the shape intersects
     * another. Like with `intersect_shape`, the number of returned results can be limited to save
     * processing time. Returned points are a list of pairs of contact points. For each pair the first
     * one is in the shape passed in `PhysicsShapeQueryParameters2D` object, second one is in the
     * collided shape from the physics space.
     *
     * Generated from Godot docs: PhysicsDirectSpaceState2D.collide_shape
     */
    fun collideShape(parameters: PhysicsShapeQueryParameters2D, maxResults: Int = 32): List<Vector2> {
        val ret = ObjectCalls.ptrcallWithObjectAndIntArgRetVector2List(Binds.collideShapeBind, segment, parameters.requireOpenHandle(), maxResults)
        ObjectCalls.keepReachable(parameters)
        return ret
    }

    /**
     * Checks the intersections of a shape, given through a `PhysicsShapeQueryParameters2D` object,
     * against the space. If it collides with more than one shape, the nearest one is selected. The
     * returned object is a dictionary containing the following fields: `collider_id`: The colliding
     * object's ID. `linear_velocity`: The colliding object's velocity `Vector2`. If the object is an
     * `Area2D`, the result is `(0, 0)`. `normal`: The collision normal of the query shape at the
     * intersection point, pointing away from the intersecting object. `point`: The intersection point.
     * `rid`: The intersecting object's `RID`. `shape`: The shape index of the colliding shape. If the
     * shape did not intersect anything, then an empty dictionary is returned instead.
     *
     * Generated from Godot docs: PhysicsDirectSpaceState2D.get_rest_info
     */
    fun getRestInfo(parameters: PhysicsShapeQueryParameters2D): Map<String, Any?> {
        val ret = ObjectCalls.ptrcallWithObjectArgRetDictionary(Binds.getRestInfoBind, segment, parameters.requireOpenHandle())
        ObjectCalls.keepReachable(parameters)
        return ret
    }

    /**
     * Intersects a ray in a given space. Ray position and other parameters are defined through
     * `PhysicsRayQueryParameters2D`. If the ray hits a shape, this method returns `true` and writes
     * the intersection information into the `PhysicsIntersectRayResult2D` object passed in `result`.
     * If nothing is hit, it returns `false`.
     *
     * Generated from Godot docs: PhysicsDirectSpaceState2D.intersect_ray_into
     */
    fun intersectRayInto(parameters: PhysicsRayQueryParameters2D, result: PhysicsIntersectRayResult2D): Boolean {
        val ret = ObjectCalls.ptrcallWithTwoObjectArgsRetBool(Binds.intersectRayIntoBind, segment, parameters.requireOpenHandle(), result.requireOpenHandle())
        ObjectCalls.keepReachable(parameters)
        ObjectCalls.keepReachable(result)
        return ret
    }

    /**
     * Checks whether a point is inside any solid shape. Position and other parameters are defined
     * through `PhysicsPointQueryParameters2D`. If one or more shapes overlap the point, this method
     * returns `true` and writes the intersection data into the `PhysicsIntersectPointResult2D` object
     * passed in `result`. If no intersections are found, it returns `false`. The number of
     * intersections can be limited using `PhysicsIntersectPointResult2D.max_intersections`, to reduce
     * the processing time. Note: `ConcavePolygonShape2D`s and `CollisionPolygon2D`s in `Segments`
     * build mode are not solid shapes. Therefore, they will not be detected.
     *
     * Generated from Godot docs: PhysicsDirectSpaceState2D.intersect_point_into
     */
    fun intersectPointInto(parameters: PhysicsPointQueryParameters2D, result: PhysicsIntersectPointResult2D): Boolean {
        val ret = ObjectCalls.ptrcallWithTwoObjectArgsRetBool(Binds.intersectPointIntoBind, segment, parameters.requireOpenHandle(), result.requireOpenHandle())
        ObjectCalls.keepReachable(parameters)
        ObjectCalls.keepReachable(result)
        return ret
    }

    /**
     * Checks the intersections of a shape, given through a `PhysicsShapeQueryParameters2D` object,
     * against the space. If one or more shapes are found to intersect, this method returns `true` and
     * writes the intersection data into the `PhysicsIntersectShapeResult2D` object passed in `result`.
     * If no shapes are found to intersect, it returns `false`. The number of intersections can be
     * limited using `PhysicsIntersectShapeResult2D.max_intersections`, to reduce the processing time.
     *
     * Generated from Godot docs: PhysicsDirectSpaceState2D.intersect_shape_into
     */
    fun intersectShapeInto(parameters: PhysicsShapeQueryParameters2D, result: PhysicsIntersectShapeResult2D): Boolean {
        val ret = ObjectCalls.ptrcallWithTwoObjectArgsRetBool(Binds.intersectShapeIntoBind, segment, parameters.requireOpenHandle(), result.requireOpenHandle())
        ObjectCalls.keepReachable(parameters)
        ObjectCalls.keepReachable(result)
        return ret
    }

    /**
     * Checks how far a `Shape2D` can move without colliding. All the parameters for the query,
     * including the shape and the motion, are supplied through a `PhysicsShapeQueryParameters2D`
     * object. If the query is successful, this method returns `true` and writes the motion results
     * into the `PhysicsCastMotionResult2D` object passed in `result`. If no collision occurs, it
     * returns `false`. Note: Any `Shape2D`s that the shape is already colliding with e.g. inside of,
     * will be ignored. Use `collide_shape_into` to determine the `Shape2D`s that the shape is already
     * colliding with.
     *
     * Generated from Godot docs: PhysicsDirectSpaceState2D.cast_motion_into
     */
    fun castMotionInto(parameters: PhysicsShapeQueryParameters2D, result: PhysicsCastMotionResult2D): Boolean {
        val ret = ObjectCalls.ptrcallWithTwoObjectArgsRetBool(Binds.castMotionIntoBind, segment, parameters.requireOpenHandle(), result.requireOpenHandle())
        ObjectCalls.keepReachable(parameters)
        ObjectCalls.keepReachable(result)
        return ret
    }

    /**
     * Checks the intersections of a shape, given through a `PhysicsShapeQueryParameters2D` object,
     * against the space. If intersections are found, this method returns `true` and writes the
     * collision points into the `PhysicsCollideShapeResult2D` object passed in `result`. If no
     * intersections are found, it returns `false`. The number of returned results can be limited using
     * `PhysicsCollideShapeResult2D.max_collisions`, to reduce the processing time.
     *
     * Generated from Godot docs: PhysicsDirectSpaceState2D.collide_shape_into
     */
    fun collideShapeInto(parameters: PhysicsShapeQueryParameters2D, result: PhysicsCollideShapeResult2D): Boolean {
        val ret = ObjectCalls.ptrcallWithTwoObjectArgsRetBool(Binds.collideShapeIntoBind, segment, parameters.requireOpenHandle(), result.requireOpenHandle())
        ObjectCalls.keepReachable(parameters)
        ObjectCalls.keepReachable(result)
        return ret
    }

    /**
     * Checks the intersections of a shape, given through a `PhysicsShapeQueryParameters2D` object,
     * against the space. If the shape collides with one or more shapes, this method returns `true` and
     * writes the nearest collision information into the `PhysicsGetRestInfoResult2D` object passed in
     * `result`. If no collisions occur, it returns `false`.
     *
     * Generated from Godot docs: PhysicsDirectSpaceState2D.get_rest_info_into
     */
    fun getRestInfoInto(parameters: PhysicsShapeQueryParameters2D, result: PhysicsGetRestInfoResult2D): Boolean {
        val ret = ObjectCalls.ptrcallWithTwoObjectArgsRetBool(Binds.getRestInfoIntoBind, segment, parameters.requireOpenHandle(), result.requireOpenHandle())
        ObjectCalls.keepReachable(parameters)
        ObjectCalls.keepReachable(result)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): PhysicsDirectSpaceState2D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): PhysicsDirectSpaceState2D? =
            if (handle.address() == 0L) null else PhysicsDirectSpaceState2D(GodotHandle(handle))
    }

    private object Binds {
        private const val INTERSECT_RAY_HASH = 1590275562L
        @JvmField
        val intersectRayBind =
            ObjectCalls.getMethodBind("PhysicsDirectSpaceState2D", "intersect_ray", INTERSECT_RAY_HASH)

        private const val INTERSECT_POINT_HASH = 2118456068L
        @JvmField
        val intersectPointBind =
            ObjectCalls.getMethodBind("PhysicsDirectSpaceState2D", "intersect_point", INTERSECT_POINT_HASH)

        private const val INTERSECT_SHAPE_HASH = 2488867228L
        @JvmField
        val intersectShapeBind =
            ObjectCalls.getMethodBind("PhysicsDirectSpaceState2D", "intersect_shape", INTERSECT_SHAPE_HASH)

        private const val CAST_MOTION_HASH = 711275086L
        @JvmField
        val castMotionBind =
            ObjectCalls.getMethodBind("PhysicsDirectSpaceState2D", "cast_motion", CAST_MOTION_HASH)

        private const val COLLIDE_SHAPE_HASH = 2488867228L
        @JvmField
        val collideShapeBind =
            ObjectCalls.getMethodBind("PhysicsDirectSpaceState2D", "collide_shape", COLLIDE_SHAPE_HASH)

        private const val GET_REST_INFO_HASH = 2803666496L
        @JvmField
        val getRestInfoBind =
            ObjectCalls.getMethodBind("PhysicsDirectSpaceState2D", "get_rest_info", GET_REST_INFO_HASH)

        private const val INTERSECT_RAY_INTO_HASH = 2480877099L
        @JvmField
        val intersectRayIntoBind =
            ObjectCalls.getMethodBind("PhysicsDirectSpaceState2D", "intersect_ray_into", INTERSECT_RAY_INTO_HASH)

        private const val INTERSECT_POINT_INTO_HASH = 2338147429L
        @JvmField
        val intersectPointIntoBind =
            ObjectCalls.getMethodBind("PhysicsDirectSpaceState2D", "intersect_point_into", INTERSECT_POINT_INTO_HASH)

        private const val INTERSECT_SHAPE_INTO_HASH = 1052395953L
        @JvmField
        val intersectShapeIntoBind =
            ObjectCalls.getMethodBind("PhysicsDirectSpaceState2D", "intersect_shape_into", INTERSECT_SHAPE_INTO_HASH)

        private const val CAST_MOTION_INTO_HASH = 1136743640L
        @JvmField
        val castMotionIntoBind =
            ObjectCalls.getMethodBind("PhysicsDirectSpaceState2D", "cast_motion_into", CAST_MOTION_INTO_HASH)

        private const val COLLIDE_SHAPE_INTO_HASH = 2406737026L
        @JvmField
        val collideShapeIntoBind =
            ObjectCalls.getMethodBind("PhysicsDirectSpaceState2D", "collide_shape_into", COLLIDE_SHAPE_INTO_HASH)

        private const val GET_REST_INFO_INTO_HASH = 328042613L
        @JvmField
        val getRestInfoIntoBind =
            ObjectCalls.getMethodBind("PhysicsDirectSpaceState2D", "get_rest_info_into", GET_REST_INFO_INTO_HASH)
    }
}
