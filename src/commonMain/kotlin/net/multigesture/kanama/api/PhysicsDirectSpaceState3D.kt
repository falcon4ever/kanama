package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Vector3

/**
 * Provides direct access to a physics space in the `PhysicsServer3D`.
 *
 * Generated from Godot docs: PhysicsDirectSpaceState3D
 */
open class PhysicsDirectSpaceState3D(handle: GodotHandle) : GodotObject(handle) {
    /**
     * Intersects a ray in a given space. Ray position and other parameters are defined through
     * `PhysicsRayQueryParameters3D`. The returned object is a dictionary with the following fields:
     * `collider`: The colliding object. `collider_id`: The colliding object's ID. `normal`: The
     * object's surface normal at the intersection point, or `Vector3(0, 0, 0)` if the ray starts
     * inside the shape and `PhysicsRayQueryParameters3D.hit_from_inside` is `true`. `position`: The
     * intersection point. `face_index`: The face index at the intersection point. Note: Returns a
     * valid number only if the intersected shape is a `ConcavePolygonShape3D`. Otherwise, `-1` is
     * returned. `rid`: The intersecting object's `RID`. `shape`: The shape index of the colliding
     * shape. If the ray did not intersect anything, then an empty dictionary is returned instead.
     *
     * Generated from Godot docs: PhysicsDirectSpaceState3D.intersect_ray
     */
    fun intersectRay(parameters: PhysicsRayQueryParameters3D): Map<String, Any?> {
        val ret = ObjectCalls.ptrcallWithObjectArgRetDictionary(Binds.intersectRayBind, segment, parameters.requireOpenHandle())
        ObjectCalls.keepReachable(parameters)
        return ret
    }

    /**
     * Checks whether a point is inside any solid shape. Position and other parameters are defined
     * through `PhysicsPointQueryParameters3D`. The shapes the point is inside of are returned in an
     * array containing dictionaries with the following fields: `collider`: The colliding object.
     * `collider_id`: The colliding object's ID. `rid`: The intersecting object's `RID`. `shape`: The
     * shape index of the colliding shape. The number of intersections can be limited with the
     * `max_results` parameter, to reduce the processing time.
     *
     * Generated from Godot docs: PhysicsDirectSpaceState3D.intersect_point
     */
    fun intersectPoint(parameters: PhysicsPointQueryParameters3D, maxResults: Int = 32): List<Map<String, Any?>> {
        val ret = ObjectCalls.ptrcallWithObjectAndIntArgRetDictionaryList(Binds.intersectPointBind, segment, parameters.requireOpenHandle(), maxResults)
        ObjectCalls.keepReachable(parameters)
        return ret
    }

    /**
     * Checks the intersections of a shape, given through a `PhysicsShapeQueryParameters3D` object,
     * against the space. The intersected shapes are returned in an array containing dictionaries with
     * the following fields: `collider`: The colliding object. `collider_id`: The colliding object's
     * ID. `rid`: The intersecting object's `RID`. `shape`: The shape index of the colliding shape. The
     * number of intersections can be limited with the `max_results` parameter, to reduce the
     * processing time. Note: This method does not take into account the `motion` property of the
     * object.
     *
     * Generated from Godot docs: PhysicsDirectSpaceState3D.intersect_shape
     */
    fun intersectShape(parameters: PhysicsShapeQueryParameters3D, maxResults: Int = 32): List<Map<String, Any?>> {
        val ret = ObjectCalls.ptrcallWithObjectAndIntArgRetDictionaryList(Binds.intersectShapeBind, segment, parameters.requireOpenHandle(), maxResults)
        ObjectCalls.keepReachable(parameters)
        return ret
    }

    /**
     * Checks how far a `Shape3D` can move without colliding. All the parameters for the query,
     * including the shape and the motion, are supplied through a `PhysicsShapeQueryParameters3D`
     * object. Returns an array with the safe and unsafe proportions (between 0 and 1) of the motion.
     * The safe proportion is the maximum fraction of the motion that can be made without a collision.
     * The unsafe proportion is the minimum fraction of the distance that must be moved for a
     * collision. If no collision is detected a result of `[1.0, 1.0]` will be returned. Note: Any
     * `Shape3D`s that the shape is already colliding with e.g. inside of, will be ignored. Use
     * `collide_shape` to determine the `Shape3D`s that the shape is already colliding with.
     *
     * Generated from Godot docs: PhysicsDirectSpaceState3D.cast_motion
     */
    fun castMotion(parameters: PhysicsShapeQueryParameters3D): List<Float> {
        val ret = ObjectCalls.ptrcallWithObjectArgRetPackedFloat32List(Binds.castMotionBind, segment, parameters.requireOpenHandle())
        ObjectCalls.keepReachable(parameters)
        return ret
    }

    /**
     * Checks the intersections of a shape, given through a `PhysicsShapeQueryParameters3D` object,
     * against the space. The resulting array contains a list of points where the shape intersects
     * another. Like with `intersect_shape`, the number of returned results can be limited to save
     * processing time. Returned points are a list of pairs of contact points. For each pair the first
     * one is in the shape passed in `PhysicsShapeQueryParameters3D` object, second one is in the
     * collided shape from the physics space. Note: This method does not take into account the `motion`
     * property of the object.
     *
     * Generated from Godot docs: PhysicsDirectSpaceState3D.collide_shape
     */
    fun collideShape(parameters: PhysicsShapeQueryParameters3D, maxResults: Int = 32): List<Vector3> {
        val ret = ObjectCalls.ptrcallWithObjectAndIntArgRetVector3List(Binds.collideShapeBind, segment, parameters.requireOpenHandle(), maxResults)
        ObjectCalls.keepReachable(parameters)
        return ret
    }

    /**
     * Checks the intersections of a shape, given through a `PhysicsShapeQueryParameters3D` object,
     * against the space. If it collides with more than one shape, the nearest one is selected. The
     * returned object is a dictionary containing the following fields: `collider_id`: The colliding
     * object's ID. `linear_velocity`: The colliding object's velocity `Vector3`. If the object is an
     * `Area3D`, the result is `(0, 0, 0)`. `normal`: The collision normal of the query shape at the
     * intersection point, pointing away from the intersecting object. `point`: The intersection point.
     * `rid`: The intersecting object's `RID`. `shape`: The shape index of the colliding shape. If the
     * shape did not intersect anything, then an empty dictionary is returned instead. Note: This
     * method does not take into account the `motion` property of the object.
     *
     * Generated from Godot docs: PhysicsDirectSpaceState3D.get_rest_info
     */
    fun getRestInfo(parameters: PhysicsShapeQueryParameters3D): Map<String, Any?> {
        val ret = ObjectCalls.ptrcallWithObjectArgRetDictionary(Binds.getRestInfoBind, segment, parameters.requireOpenHandle())
        ObjectCalls.keepReachable(parameters)
        return ret
    }

    /**
     * Intersects a ray in a given space. Ray position and other parameters are defined through
     * `PhysicsRayQueryParameters3D`. If the ray hits a shape, this method returns `true` and writes
     * the intersection information into the `PhysicsIntersectRayResult3D` object passed in `result`.
     * If nothing is hit, it returns `false`.
     *
     * Generated from Godot docs: PhysicsDirectSpaceState3D.intersect_ray_into
     */
    fun intersectRayInto(parameters: PhysicsRayQueryParameters3D, result: PhysicsIntersectRayResult3D): Boolean {
        val ret = ObjectCalls.ptrcallWithTwoObjectArgsRetBool(Binds.intersectRayIntoBind, segment, parameters.requireOpenHandle(), result.requireOpenHandle())
        ObjectCalls.keepReachable(parameters)
        ObjectCalls.keepReachable(result)
        return ret
    }

    /**
     * Checks whether a point is inside any solid shape. Position and other parameters are defined
     * through `PhysicsPointQueryParameters3D`. If one or more shapes overlap the point, this method
     * returns `true` and writes the intersection data into the `PhysicsIntersectPointResult3D` object
     * passed in `result`. If no intersections are found, it returns `false`. The number of
     * intersections can be limited using `PhysicsIntersectPointResult3D.max_intersections`, to reduce
     * the processing time.
     *
     * Generated from Godot docs: PhysicsDirectSpaceState3D.intersect_point_into
     */
    fun intersectPointInto(parameters: PhysicsPointQueryParameters3D, result: PhysicsIntersectPointResult3D): Boolean {
        val ret = ObjectCalls.ptrcallWithTwoObjectArgsRetBool(Binds.intersectPointIntoBind, segment, parameters.requireOpenHandle(), result.requireOpenHandle())
        ObjectCalls.keepReachable(parameters)
        ObjectCalls.keepReachable(result)
        return ret
    }

    /**
     * Checks the intersections of a shape, given through a `PhysicsShapeQueryParameters3D` object,
     * against the space. If one or more shapes are found to intersect, this method returns `true` and
     * writes the intersection data into the `PhysicsIntersectShapeResult3D` object passed in `result`.
     * If no shapes are found to intersect, it returns `false`. The number of intersections can be
     * limited using `PhysicsIntersectShapeResult3D.max_intersections`, to reduce the processing time.
     * Note: This method does not take into account the `motion` property of the object.
     *
     * Generated from Godot docs: PhysicsDirectSpaceState3D.intersect_shape_into
     */
    fun intersectShapeInto(parameters: PhysicsShapeQueryParameters3D, result: PhysicsIntersectShapeResult3D): Boolean {
        val ret = ObjectCalls.ptrcallWithTwoObjectArgsRetBool(Binds.intersectShapeIntoBind, segment, parameters.requireOpenHandle(), result.requireOpenHandle())
        ObjectCalls.keepReachable(parameters)
        ObjectCalls.keepReachable(result)
        return ret
    }

    /**
     * Checks how far a `Shape3D` can move without colliding. All the parameters for the query,
     * including the shape and the motion, are supplied through a `PhysicsShapeQueryParameters3D`
     * object. If the query is successful, this method returns `true` and writes the motion results
     * into the `PhysicsCastMotionResult3D` object passed in `result`. If no collision occurs, it
     * returns `false`. Note: Any `Shape3D`s that the shape is already colliding with e.g. inside of,
     * will be ignored. Use `collide_shape_into` to determine the `Shape3D`s that the shape is already
     * colliding with.
     *
     * Generated from Godot docs: PhysicsDirectSpaceState3D.cast_motion_into
     */
    fun castMotionInto(parameters: PhysicsShapeQueryParameters3D, result: PhysicsCastMotionResult3D): Boolean {
        val ret = ObjectCalls.ptrcallWithTwoObjectArgsRetBool(Binds.castMotionIntoBind, segment, parameters.requireOpenHandle(), result.requireOpenHandle())
        ObjectCalls.keepReachable(parameters)
        ObjectCalls.keepReachable(result)
        return ret
    }

    /**
     * Checks the intersections of a shape, given through a `PhysicsShapeQueryParameters3D` object,
     * against the space. If intersections are found, this method returns `true` and writes the
     * collision points into the `PhysicsCollideShapeResult3D` object passed in `result`. If no
     * intersections are found, it returns `false`. The number of returned results can be limited using
     * `PhysicsCollideShapeResult3D.max_collisions`, to reduce the processing time. Note: This method
     * does not take into account the `motion` property of the object.
     *
     * Generated from Godot docs: PhysicsDirectSpaceState3D.collide_shape_into
     */
    fun collideShapeInto(parameters: PhysicsShapeQueryParameters3D, result: PhysicsCollideShapeResult3D): Boolean {
        val ret = ObjectCalls.ptrcallWithTwoObjectArgsRetBool(Binds.collideShapeIntoBind, segment, parameters.requireOpenHandle(), result.requireOpenHandle())
        ObjectCalls.keepReachable(parameters)
        ObjectCalls.keepReachable(result)
        return ret
    }

    /**
     * Checks the intersections of a shape, given through a `PhysicsShapeQueryParameters3D` object,
     * against the space. If the shape collides with one or more shapes, this method returns `true` and
     * writes the nearest collision information into the `PhysicsGetRestInfoResult3D` object passed in
     * `result`. If no collisions occur, it returns `false`. Note: This method does not take into
     * account the `motion` property of the object.
     *
     * Generated from Godot docs: PhysicsDirectSpaceState3D.get_rest_info_into
     */
    fun getRestInfoInto(parameters: PhysicsShapeQueryParameters3D, result: PhysicsGetRestInfoResult3D): Boolean {
        val ret = ObjectCalls.ptrcallWithTwoObjectArgsRetBool(Binds.getRestInfoIntoBind, segment, parameters.requireOpenHandle(), result.requireOpenHandle())
        ObjectCalls.keepReachable(parameters)
        ObjectCalls.keepReachable(result)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): PhysicsDirectSpaceState3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): PhysicsDirectSpaceState3D? =
            if (handle.address() == 0L) null else PhysicsDirectSpaceState3D(GodotHandle(handle))
    }

    private object Binds {
        private const val INTERSECT_RAY_HASH = 3957970750L
        @JvmField
        val intersectRayBind =
            ObjectCalls.getMethodBind("PhysicsDirectSpaceState3D", "intersect_ray", INTERSECT_RAY_HASH)

        private const val INTERSECT_POINT_HASH = 975173756L
        @JvmField
        val intersectPointBind =
            ObjectCalls.getMethodBind("PhysicsDirectSpaceState3D", "intersect_point", INTERSECT_POINT_HASH)

        private const val INTERSECT_SHAPE_HASH = 3762137681L
        @JvmField
        val intersectShapeBind =
            ObjectCalls.getMethodBind("PhysicsDirectSpaceState3D", "intersect_shape", INTERSECT_SHAPE_HASH)

        private const val CAST_MOTION_HASH = 1778757334L
        @JvmField
        val castMotionBind =
            ObjectCalls.getMethodBind("PhysicsDirectSpaceState3D", "cast_motion", CAST_MOTION_HASH)

        private const val COLLIDE_SHAPE_HASH = 3762137681L
        @JvmField
        val collideShapeBind =
            ObjectCalls.getMethodBind("PhysicsDirectSpaceState3D", "collide_shape", COLLIDE_SHAPE_HASH)

        private const val GET_REST_INFO_HASH = 1376751592L
        @JvmField
        val getRestInfoBind =
            ObjectCalls.getMethodBind("PhysicsDirectSpaceState3D", "get_rest_info", GET_REST_INFO_HASH)

        private const val INTERSECT_RAY_INTO_HASH = 1101524092L
        @JvmField
        val intersectRayIntoBind =
            ObjectCalls.getMethodBind("PhysicsDirectSpaceState3D", "intersect_ray_into", INTERSECT_RAY_INTO_HASH)

        private const val INTERSECT_POINT_INTO_HASH = 4224456912L
        @JvmField
        val intersectPointIntoBind =
            ObjectCalls.getMethodBind("PhysicsDirectSpaceState3D", "intersect_point_into", INTERSECT_POINT_INTO_HASH)

        private const val INTERSECT_SHAPE_INTO_HASH = 2386936143L
        @JvmField
        val intersectShapeIntoBind =
            ObjectCalls.getMethodBind("PhysicsDirectSpaceState3D", "intersect_shape_into", INTERSECT_SHAPE_INTO_HASH)

        private const val CAST_MOTION_INTO_HASH = 1750186052L
        @JvmField
        val castMotionIntoBind =
            ObjectCalls.getMethodBind("PhysicsDirectSpaceState3D", "cast_motion_into", CAST_MOTION_INTO_HASH)

        private const val COLLIDE_SHAPE_INTO_HASH = 117908487L
        @JvmField
        val collideShapeIntoBind =
            ObjectCalls.getMethodBind("PhysicsDirectSpaceState3D", "collide_shape_into", COLLIDE_SHAPE_INTO_HASH)

        private const val GET_REST_INFO_INTO_HASH = 383425697L
        @JvmField
        val getRestInfoIntoBind =
            ObjectCalls.getMethodBind("PhysicsDirectSpaceState3D", "get_rest_info_into", GET_REST_INFO_INTO_HASH)
    }
}
