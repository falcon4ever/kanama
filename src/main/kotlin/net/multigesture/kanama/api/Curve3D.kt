package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector3

/**
 * Describes a Bézier curve in 3D space.
 *
 * Generated from Godot docs: Curve3D
 */
class Curve3D(handle: MemorySegment) : Resource(handle) {
    var curveClosed: Boolean
        @JvmName("curveClosedProperty")
        get() = isClosed()
        @JvmName("setCurveClosedProperty")
        set(value) = setCurveClosed(value)

    var bakeInterval: Double
        @JvmName("bakeIntervalProperty")
        get() = getBakeInterval()
        @JvmName("setBakeIntervalProperty")
        set(value) = setBakeInterval(value)

    var pointCount: Int
        @JvmName("pointCountProperty")
        get() = getPointCount()
        @JvmName("setPointCountProperty")
        set(value) = setPointCount(value)

    var upVectorEnabled: Boolean
        @JvmName("upVectorEnabledProperty")
        get() = isUpVectorEnabled()
        @JvmName("setUpVectorEnabledProperty")
        set(value) = setUpVectorEnabled(value)

    /**
     * The number of points describing the curve.
     *
     * Generated from Godot docs: Curve3D.get_point_count
     */
    fun getPointCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getPointCountBind, handle)
    }

    /**
     * The number of points describing the curve.
     *
     * Generated from Godot docs: Curve3D.set_point_count
     */
    fun setPointCount(count: Int) {
        ObjectCalls.ptrcallWithIntArg(setPointCountBind, handle, count)
    }

    /**
     * Adds a point with the specified `position` relative to the curve's own position, with control
     * points `in` and `out`. Appends the new point at the end of the point list. If `index` is given,
     * the new point is inserted before the existing point identified by index `index`. Every existing
     * point starting from `index` is shifted further down the list of points. The index must be
     * greater than or equal to `0` and must not exceed the number of existing points in the line. See
     * `point_count`.
     *
     * Generated from Godot docs: Curve3D.add_point
     */
    fun addPoint(position: Vector3, inValue: Vector3, out: Vector3, index: Int = -1) {
        ObjectCalls.ptrcallWithThreeVector3AndIntArg(addPointBind, handle, position, inValue, out, index)
    }

    /**
     * Sets the position for the vertex `idx`. If the index is out of bounds, the function sends an
     * error to the console.
     *
     * Generated from Godot docs: Curve3D.set_point_position
     */
    fun setPointPosition(idx: Int, position: Vector3) {
        ObjectCalls.ptrcallWithIntAndVector3Arg(setPointPositionBind, handle, idx, position)
    }

    /**
     * Returns the position of the vertex `idx`. If the index is out of bounds, the function sends an
     * error to the console, and returns `(0, 0, 0)`.
     *
     * Generated from Godot docs: Curve3D.get_point_position
     */
    fun getPointPosition(idx: Int): Vector3 {
        return ObjectCalls.ptrcallWithIntArgRetVector3(getPointPositionBind, handle, idx)
    }

    /**
     * Sets the tilt angle in radians for the point `idx`. If the index is out of bounds, the function
     * sends an error to the console. The tilt controls the rotation along the look-at axis an object
     * traveling the path would have. In the case of a curve controlling a `PathFollow3D`, this tilt is
     * an offset over the natural tilt the `PathFollow3D` calculates.
     *
     * Generated from Godot docs: Curve3D.set_point_tilt
     */
    fun setPointTilt(idx: Int, tilt: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setPointTiltBind, handle, idx, tilt)
    }

    /**
     * Returns the tilt angle in radians for the point `idx`. If the index is out of bounds, the
     * function sends an error to the console, and returns `0`.
     *
     * Generated from Godot docs: Curve3D.get_point_tilt
     */
    fun getPointTilt(idx: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getPointTiltBind, handle, idx)
    }

    /**
     * Sets the position of the control point leading to the vertex `idx`. If the index is out of
     * bounds, the function sends an error to the console. The position is relative to the vertex.
     *
     * Generated from Godot docs: Curve3D.set_point_in
     */
    fun setPointIn(idx: Int, position: Vector3) {
        ObjectCalls.ptrcallWithIntAndVector3Arg(setPointInBind, handle, idx, position)
    }

    /**
     * Returns the position of the control point leading to the vertex `idx`. The returned position is
     * relative to the vertex `idx`. If the index is out of bounds, the function sends an error to the
     * console, and returns `(0, 0, 0)`.
     *
     * Generated from Godot docs: Curve3D.get_point_in
     */
    fun getPointIn(idx: Int): Vector3 {
        return ObjectCalls.ptrcallWithIntArgRetVector3(getPointInBind, handle, idx)
    }

    /**
     * Sets the position of the control point leading out of the vertex `idx`. If the index is out of
     * bounds, the function sends an error to the console. The position is relative to the vertex.
     *
     * Generated from Godot docs: Curve3D.set_point_out
     */
    fun setPointOut(idx: Int, position: Vector3) {
        ObjectCalls.ptrcallWithIntAndVector3Arg(setPointOutBind, handle, idx, position)
    }

    /**
     * Returns the position of the control point leading out of the vertex `idx`. The returned position
     * is relative to the vertex `idx`. If the index is out of bounds, the function sends an error to
     * the console, and returns `(0, 0, 0)`.
     *
     * Generated from Godot docs: Curve3D.get_point_out
     */
    fun getPointOut(idx: Int): Vector3 {
        return ObjectCalls.ptrcallWithIntArgRetVector3(getPointOutBind, handle, idx)
    }

    /**
     * Deletes the point `idx` from the curve. Sends an error to the console if `idx` is out of bounds.
     *
     * Generated from Godot docs: Curve3D.remove_point
     */
    fun removePoint(idx: Int) {
        ObjectCalls.ptrcallWithIntArg(removePointBind, handle, idx)
    }

    /**
     * Removes all points from the curve.
     *
     * Generated from Godot docs: Curve3D.clear_points
     */
    fun clearPoints() {
        ObjectCalls.ptrcallNoArgs(clearPointsBind, handle)
    }

    /**
     * Returns the position between the vertex `idx` and the vertex `idx + 1`, where `t` controls if
     * the point is the first vertex (`t = 0.0`), the last vertex (`t = 1.0`), or in between. Values of
     * `t` outside the range (`0.0 >= t <=1`) give strange, but predictable results. If `idx` is out of
     * bounds it is truncated to the first or last vertex, and `t` is ignored. If the curve has no
     * points, the function sends an error to the console, and returns `(0, 0, 0)`.
     *
     * Generated from Godot docs: Curve3D.sample
     */
    fun sample(idx: Int, t: Double): Vector3 {
        return ObjectCalls.ptrcallWithIntAndDoubleArgRetVector3(sampleBind, handle, idx, t)
    }

    /**
     * Returns the position at the vertex `fofs`. It calls `sample` using the integer part of `fofs` as
     * `idx`, and its fractional part as `t`.
     *
     * Generated from Godot docs: Curve3D.samplef
     */
    fun samplef(fofs: Double): Vector3 {
        return ObjectCalls.ptrcallWithDoubleArgRetVector3(samplefBind, handle, fofs)
    }

    fun setCurveClosed(closed: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCurveClosedBind, handle, closed)
    }

    /**
     * If `true`, and the curve has more than 2 control points, the last point and the first one will
     * be connected in a loop.
     *
     * Generated from Godot docs: Curve3D.is_closed
     */
    fun isClosed(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isClosedBind, handle)
    }

    /**
     * The distance in meters between two adjacent cached points. Changing it forces the cache to be
     * recomputed the next time the `get_baked_points` or `get_baked_length` function is called. The
     * smaller the distance, the more points in the cache and the more memory it will consume, so use
     * with care.
     *
     * Generated from Godot docs: Curve3D.set_bake_interval
     */
    fun setBakeInterval(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setBakeIntervalBind, handle, distance)
    }

    /**
     * The distance in meters between two adjacent cached points. Changing it forces the cache to be
     * recomputed the next time the `get_baked_points` or `get_baked_length` function is called. The
     * smaller the distance, the more points in the cache and the more memory it will consume, so use
     * with care.
     *
     * Generated from Godot docs: Curve3D.get_bake_interval
     */
    fun getBakeInterval(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getBakeIntervalBind, handle)
    }

    /**
     * If `true`, the curve will bake up vectors used for orientation. This is used when
     * `PathFollow3D.rotation_mode` is set to `PathFollow3D.ROTATION_ORIENTED`. Changing it forces the
     * cache to be recomputed.
     *
     * Generated from Godot docs: Curve3D.set_up_vector_enabled
     */
    fun setUpVectorEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUpVectorEnabledBind, handle, enable)
    }

    /**
     * If `true`, the curve will bake up vectors used for orientation. This is used when
     * `PathFollow3D.rotation_mode` is set to `PathFollow3D.ROTATION_ORIENTED`. Changing it forces the
     * cache to be recomputed.
     *
     * Generated from Godot docs: Curve3D.is_up_vector_enabled
     */
    fun isUpVectorEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUpVectorEnabledBind, handle)
    }

    /**
     * Returns the total length of the curve, based on the cached points. Given enough density (see
     * `bake_interval`), it should be approximate enough.
     *
     * Generated from Godot docs: Curve3D.get_baked_length
     */
    fun getBakedLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getBakedLengthBind, handle)
    }

    /**
     * Returns a point within the curve at position `offset`, where `offset` is measured as a distance
     * in 3D units along the curve. To do that, it finds the two cached points where the `offset` lies
     * between, then interpolates the values. This interpolation is cubic if `cubic` is set to `true`,
     * or linear if set to `false`. Cubic interpolation tends to follow the curves better, but linear
     * is faster (and often, precise enough).
     *
     * Generated from Godot docs: Curve3D.sample_baked
     */
    fun sampleBaked(offset: Double = 0.0, cubic: Boolean = false): Vector3 {
        return ObjectCalls.ptrcallWithDoubleAndBoolArgRetVector3(sampleBakedBind, handle, offset, cubic)
    }

    /**
     * Returns a `Transform3D` with `origin` as point position, `basis.x` as sideway vector, `basis.y`
     * as up vector, `basis.z` as forward vector. When the curve length is 0, there is no reasonable
     * way to calculate the rotation, all vectors aligned with global space axes. See also
     * `sample_baked`.
     *
     * Generated from Godot docs: Curve3D.sample_baked_with_rotation
     */
    fun sampleBakedWithRotation(offset: Double = 0.0, cubic: Boolean = false, applyTilt: Boolean = false): Transform3D {
        return ObjectCalls.ptrcallWithDoubleAndTwoBoolArgsRetTransform3D(sampleBakedWithRotationBind, handle, offset, cubic, applyTilt)
    }

    /**
     * Returns an up vector within the curve at position `offset`, where `offset` is measured as a
     * distance in 3D units along the curve. To do that, it finds the two cached up vectors where the
     * `offset` lies between, then interpolates the values. If `apply_tilt` is `true`, an interpolated
     * tilt is applied to the interpolated up vector. If the curve has no up vectors, the function
     * sends an error to the console, and returns `(0, 1, 0)`.
     *
     * Generated from Godot docs: Curve3D.sample_baked_up_vector
     */
    fun sampleBakedUpVector(offset: Double, applyTilt: Boolean = false): Vector3 {
        return ObjectCalls.ptrcallWithDoubleAndBoolArgRetVector3(sampleBakedUpVectorBind, handle, offset, applyTilt)
    }

    /**
     * Returns the cache of points as a `PackedVector3Array`.
     *
     * Generated from Godot docs: Curve3D.get_baked_points
     */
    fun getBakedPoints(): List<Vector3> {
        return ObjectCalls.ptrcallNoArgsRetPackedVector3List(getBakedPointsBind, handle)
    }

    /**
     * Returns the cache of tilts as a `PackedFloat32Array`.
     *
     * Generated from Godot docs: Curve3D.get_baked_tilts
     */
    fun getBakedTilts(): List<Float> {
        return ObjectCalls.ptrcallNoArgsRetPackedFloat32List(getBakedTiltsBind, handle)
    }

    /**
     * Returns the cache of up vectors as a `PackedVector3Array`. If `up_vector_enabled` is `false`,
     * the cache will be empty.
     *
     * Generated from Godot docs: Curve3D.get_baked_up_vectors
     */
    fun getBakedUpVectors(): List<Vector3> {
        return ObjectCalls.ptrcallNoArgsRetPackedVector3List(getBakedUpVectorsBind, handle)
    }

    /**
     * Returns the closest point on baked segments (in curve's local space) to `to_point`. `to_point`
     * must be in this curve's local space.
     *
     * Generated from Godot docs: Curve3D.get_closest_point
     */
    fun getClosestPoint(toPoint: Vector3): Vector3 {
        return ObjectCalls.ptrcallWithVector3ArgRetVector3(getClosestPointBind, handle, toPoint)
    }

    /**
     * Returns the closest offset to `to_point`. This offset is meant to be used in `sample_baked` or
     * `sample_baked_up_vector`. `to_point` must be in this curve's local space.
     *
     * Generated from Godot docs: Curve3D.get_closest_offset
     */
    fun getClosestOffset(toPoint: Vector3): Double {
        return ObjectCalls.ptrcallWithVector3ArgRetDouble(getClosestOffsetBind, handle, toPoint)
    }

    /**
     * Returns a list of points along the curve, with a curvature controlled point density. That is,
     * the curvier parts will have more points than the straighter parts. This approximation makes
     * straight segments between each point, then subdivides those segments until the resulting shape
     * is similar enough. `max_stages` controls how many subdivisions a curve segment may face before
     * it is considered approximate enough. Each subdivision splits the segment in half, so the default
     * 5 stages may mean up to 32 subdivisions per curve segment. Increase with care!
     * `tolerance_degrees` controls how many degrees the midpoint of a segment may deviate from the
     * real curve, before the segment has to be subdivided.
     *
     * Generated from Godot docs: Curve3D.tessellate
     */
    fun tessellate(maxStages: Int = 5, toleranceDegrees: Double = 4.0): List<Vector3> {
        return ObjectCalls.ptrcallWithIntAndDoubleArgRetPackedVector3List(tessellateBind, handle, maxStages, toleranceDegrees)
    }

    /**
     * Returns a list of points along the curve, with almost uniform density. `max_stages` controls how
     * many subdivisions a curve segment may face before it is considered approximate enough. Each
     * subdivision splits the segment in half, so the default 5 stages may mean up to 32 subdivisions
     * per curve segment. Increase with care! `tolerance_length` controls the maximal distance between
     * two neighboring points, before the segment has to be subdivided.
     *
     * Generated from Godot docs: Curve3D.tessellate_even_length
     */
    fun tessellateEvenLength(maxStages: Int = 5, toleranceLength: Double = 0.2): List<Vector3> {
        return ObjectCalls.ptrcallWithIntAndDoubleArgRetPackedVector3List(tessellateEvenLengthBind, handle, maxStages, toleranceLength)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): Curve3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Curve3D? =
            if (handle.address() == 0L) null else Curve3D(handle)

        private const val GET_POINT_COUNT_HASH = 3905245786L
        private val getPointCountBind by lazy {
            ObjectCalls.getMethodBind("Curve3D", "get_point_count", GET_POINT_COUNT_HASH)
        }

        private const val SET_POINT_COUNT_HASH = 1286410249L
        private val setPointCountBind by lazy {
            ObjectCalls.getMethodBind("Curve3D", "set_point_count", SET_POINT_COUNT_HASH)
        }

        private const val ADD_POINT_HASH = 2931053748L
        private val addPointBind by lazy {
            ObjectCalls.getMethodBind("Curve3D", "add_point", ADD_POINT_HASH)
        }

        private const val SET_POINT_POSITION_HASH = 1530502735L
        private val setPointPositionBind by lazy {
            ObjectCalls.getMethodBind("Curve3D", "set_point_position", SET_POINT_POSITION_HASH)
        }

        private const val GET_POINT_POSITION_HASH = 711720468L
        private val getPointPositionBind by lazy {
            ObjectCalls.getMethodBind("Curve3D", "get_point_position", GET_POINT_POSITION_HASH)
        }

        private const val SET_POINT_TILT_HASH = 1602489585L
        private val setPointTiltBind by lazy {
            ObjectCalls.getMethodBind("Curve3D", "set_point_tilt", SET_POINT_TILT_HASH)
        }

        private const val GET_POINT_TILT_HASH = 2339986948L
        private val getPointTiltBind by lazy {
            ObjectCalls.getMethodBind("Curve3D", "get_point_tilt", GET_POINT_TILT_HASH)
        }

        private const val SET_POINT_IN_HASH = 1530502735L
        private val setPointInBind by lazy {
            ObjectCalls.getMethodBind("Curve3D", "set_point_in", SET_POINT_IN_HASH)
        }

        private const val GET_POINT_IN_HASH = 711720468L
        private val getPointInBind by lazy {
            ObjectCalls.getMethodBind("Curve3D", "get_point_in", GET_POINT_IN_HASH)
        }

        private const val SET_POINT_OUT_HASH = 1530502735L
        private val setPointOutBind by lazy {
            ObjectCalls.getMethodBind("Curve3D", "set_point_out", SET_POINT_OUT_HASH)
        }

        private const val GET_POINT_OUT_HASH = 711720468L
        private val getPointOutBind by lazy {
            ObjectCalls.getMethodBind("Curve3D", "get_point_out", GET_POINT_OUT_HASH)
        }

        private const val REMOVE_POINT_HASH = 1286410249L
        private val removePointBind by lazy {
            ObjectCalls.getMethodBind("Curve3D", "remove_point", REMOVE_POINT_HASH)
        }

        private const val CLEAR_POINTS_HASH = 3218959716L
        private val clearPointsBind by lazy {
            ObjectCalls.getMethodBind("Curve3D", "clear_points", CLEAR_POINTS_HASH)
        }

        private const val SAMPLE_HASH = 3285246857L
        private val sampleBind by lazy {
            ObjectCalls.getMethodBind("Curve3D", "sample", SAMPLE_HASH)
        }

        private const val SAMPLEF_HASH = 2553580215L
        private val samplefBind by lazy {
            ObjectCalls.getMethodBind("Curve3D", "samplef", SAMPLEF_HASH)
        }

        private const val SET_CLOSED_HASH = 2586408642L
        private val setCurveClosedBind by lazy {
            ObjectCalls.getMethodBind("Curve3D", "set_closed", SET_CLOSED_HASH)
        }

        private const val IS_CLOSED_HASH = 36873697L
        private val isClosedBind by lazy {
            ObjectCalls.getMethodBind("Curve3D", "is_closed", IS_CLOSED_HASH)
        }

        private const val SET_BAKE_INTERVAL_HASH = 373806689L
        private val setBakeIntervalBind by lazy {
            ObjectCalls.getMethodBind("Curve3D", "set_bake_interval", SET_BAKE_INTERVAL_HASH)
        }

        private const val GET_BAKE_INTERVAL_HASH = 1740695150L
        private val getBakeIntervalBind by lazy {
            ObjectCalls.getMethodBind("Curve3D", "get_bake_interval", GET_BAKE_INTERVAL_HASH)
        }

        private const val SET_UP_VECTOR_ENABLED_HASH = 2586408642L
        private val setUpVectorEnabledBind by lazy {
            ObjectCalls.getMethodBind("Curve3D", "set_up_vector_enabled", SET_UP_VECTOR_ENABLED_HASH)
        }

        private const val IS_UP_VECTOR_ENABLED_HASH = 36873697L
        private val isUpVectorEnabledBind by lazy {
            ObjectCalls.getMethodBind("Curve3D", "is_up_vector_enabled", IS_UP_VECTOR_ENABLED_HASH)
        }

        private const val GET_BAKED_LENGTH_HASH = 1740695150L
        private val getBakedLengthBind by lazy {
            ObjectCalls.getMethodBind("Curve3D", "get_baked_length", GET_BAKED_LENGTH_HASH)
        }

        private const val SAMPLE_BAKED_HASH = 1350085894L
        private val sampleBakedBind by lazy {
            ObjectCalls.getMethodBind("Curve3D", "sample_baked", SAMPLE_BAKED_HASH)
        }

        private const val SAMPLE_BAKED_WITH_ROTATION_HASH = 1939359131L
        private val sampleBakedWithRotationBind by lazy {
            ObjectCalls.getMethodBind("Curve3D", "sample_baked_with_rotation", SAMPLE_BAKED_WITH_ROTATION_HASH)
        }

        private const val SAMPLE_BAKED_UP_VECTOR_HASH = 1362627031L
        private val sampleBakedUpVectorBind by lazy {
            ObjectCalls.getMethodBind("Curve3D", "sample_baked_up_vector", SAMPLE_BAKED_UP_VECTOR_HASH)
        }

        private const val GET_BAKED_POINTS_HASH = 497664490L
        private val getBakedPointsBind by lazy {
            ObjectCalls.getMethodBind("Curve3D", "get_baked_points", GET_BAKED_POINTS_HASH)
        }

        private const val GET_BAKED_TILTS_HASH = 675695659L
        private val getBakedTiltsBind by lazy {
            ObjectCalls.getMethodBind("Curve3D", "get_baked_tilts", GET_BAKED_TILTS_HASH)
        }

        private const val GET_BAKED_UP_VECTORS_HASH = 497664490L
        private val getBakedUpVectorsBind by lazy {
            ObjectCalls.getMethodBind("Curve3D", "get_baked_up_vectors", GET_BAKED_UP_VECTORS_HASH)
        }

        private const val GET_CLOSEST_POINT_HASH = 192990374L
        private val getClosestPointBind by lazy {
            ObjectCalls.getMethodBind("Curve3D", "get_closest_point", GET_CLOSEST_POINT_HASH)
        }

        private const val GET_CLOSEST_OFFSET_HASH = 1109078154L
        private val getClosestOffsetBind by lazy {
            ObjectCalls.getMethodBind("Curve3D", "get_closest_offset", GET_CLOSEST_OFFSET_HASH)
        }

        private const val TESSELLATE_HASH = 1519759391L
        private val tessellateBind by lazy {
            ObjectCalls.getMethodBind("Curve3D", "tessellate", TESSELLATE_HASH)
        }

        private const val TESSELLATE_EVEN_LENGTH_HASH = 133237049L
        private val tessellateEvenLengthBind by lazy {
            ObjectCalls.getMethodBind("Curve3D", "tessellate_even_length", TESSELLATE_EVEN_LENGTH_HASH)
        }
    }
}
