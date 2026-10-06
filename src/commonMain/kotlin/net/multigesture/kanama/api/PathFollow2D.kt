package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Point sampler for a `Path2D`.
 *
 * Generated from Godot docs: PathFollow2D
 */
class PathFollow2D(handle: GodotHandle) : Node2D(handle) {
    var progress: Double
        @JvmName("progressProperty")
        get() = getProgress()
        @JvmName("setProgressProperty")
        set(value) = setProgress(value)

    var progressRatio: Double
        @JvmName("progressRatioProperty")
        get() = getProgressRatio()
        @JvmName("setProgressRatioProperty")
        set(value) = setProgressRatio(value)

    var hOffset: Double
        @JvmName("hOffsetProperty")
        get() = getHOffset()
        @JvmName("setHOffsetProperty")
        set(value) = setHOffset(value)

    var vOffset: Double
        @JvmName("vOffsetProperty")
        get() = getVOffset()
        @JvmName("setVOffsetProperty")
        set(value) = setVOffset(value)

    var rotates: Boolean
        @JvmName("rotatesProperty")
        get() = isRotating()
        @JvmName("setRotatesProperty")
        set(value) = setRotates(value)

    var cubicInterp: Boolean
        @JvmName("cubicInterpProperty")
        get() = getCubicInterpolation()
        @JvmName("setCubicInterpProperty")
        set(value) = setCubicInterpolation(value)

    var loop: Boolean
        @JvmName("loopProperty")
        get() = hasLoop()
        @JvmName("setLoopProperty")
        set(value) = setLoop(value)

    /**
     * The distance along the path, in pixels. Changing this value sets this node's position to a point
     * within the path.
     *
     * Generated from Godot docs: PathFollow2D.set_progress
     */
    fun setProgress(progress: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setProgressBind, segment, progress)
    }

    /**
     * The distance along the path, in pixels. Changing this value sets this node's position to a point
     * within the path.
     *
     * Generated from Godot docs: PathFollow2D.get_progress
     */
    fun getProgress(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getProgressBind, segment)
    }

    /**
     * The node's offset along the curve.
     *
     * Generated from Godot docs: PathFollow2D.set_h_offset
     */
    fun setHOffset(hOffset: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setHOffsetBind, segment, hOffset)
    }

    /**
     * The node's offset along the curve.
     *
     * Generated from Godot docs: PathFollow2D.get_h_offset
     */
    fun getHOffset(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getHOffsetBind, segment)
    }

    /**
     * The node's offset perpendicular to the curve.
     *
     * Generated from Godot docs: PathFollow2D.set_v_offset
     */
    fun setVOffset(vOffset: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setVOffsetBind, segment, vOffset)
    }

    /**
     * The node's offset perpendicular to the curve.
     *
     * Generated from Godot docs: PathFollow2D.get_v_offset
     */
    fun getVOffset(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getVOffsetBind, segment)
    }

    /**
     * The distance along the path as a number in the range 0.0 (for the first vertex) to 1.0 (for the
     * last). This is just another way of expressing the progress within the path, as the offset
     * supplied is multiplied internally by the path's length. It can be set or get only if the
     * `PathFollow2D` is the child of a `Path2D` which is part of the scene tree, and that this
     * `Path2D` has a `Curve2D` with a non-zero length. Otherwise, trying to set this field will print
     * an error, and getting this field will return `0.0`.
     *
     * Generated from Godot docs: PathFollow2D.set_progress_ratio
     */
    fun setProgressRatio(ratio: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setProgressRatioBind, segment, ratio)
    }

    /**
     * The distance along the path as a number in the range 0.0 (for the first vertex) to 1.0 (for the
     * last). This is just another way of expressing the progress within the path, as the offset
     * supplied is multiplied internally by the path's length. It can be set or get only if the
     * `PathFollow2D` is the child of a `Path2D` which is part of the scene tree, and that this
     * `Path2D` has a `Curve2D` with a non-zero length. Otherwise, trying to set this field will print
     * an error, and getting this field will return `0.0`.
     *
     * Generated from Godot docs: PathFollow2D.get_progress_ratio
     */
    fun getProgressRatio(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getProgressRatioBind, segment)
    }

    /**
     * If `true`, this node rotates to follow the path, with the +X direction facing forward on the
     * path.
     *
     * Generated from Godot docs: PathFollow2D.set_rotates
     */
    fun setRotates(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setRotatesBind, segment, enabled)
    }

    /**
     * If `true`, this node rotates to follow the path, with the +X direction facing forward on the
     * path.
     *
     * Generated from Godot docs: PathFollow2D.is_rotating
     */
    fun isRotating(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isRotatingBind, segment)
    }

    /**
     * If `true`, the position between two cached points is interpolated cubically, and linearly
     * otherwise. The points along the `Curve2D` of the `Path2D` are precomputed before use, for faster
     * calculations. The point at the requested offset is then calculated interpolating between two
     * adjacent cached points. This may present a problem if the curve makes sharp turns, as the cached
     * points may not follow the curve closely enough. There are two answers to this problem: either
     * increase the number of cached points and increase memory consumption, or make a cubic
     * interpolation between two points at the cost of (slightly) slower calculations.
     *
     * Generated from Godot docs: PathFollow2D.set_cubic_interpolation
     */
    fun setCubicInterpolation(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setCubicInterpolationBind, segment, enabled)
    }

    /**
     * If `true`, the position between two cached points is interpolated cubically, and linearly
     * otherwise. The points along the `Curve2D` of the `Path2D` are precomputed before use, for faster
     * calculations. The point at the requested offset is then calculated interpolating between two
     * adjacent cached points. This may present a problem if the curve makes sharp turns, as the cached
     * points may not follow the curve closely enough. There are two answers to this problem: either
     * increase the number of cached points and increase memory consumption, or make a cubic
     * interpolation between two points at the cost of (slightly) slower calculations.
     *
     * Generated from Godot docs: PathFollow2D.get_cubic_interpolation
     */
    fun getCubicInterpolation(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getCubicInterpolationBind, segment)
    }

    /**
     * If `true`, any offset outside the path's length will wrap around, instead of stopping at the
     * ends. Use it for cyclic paths.
     *
     * Generated from Godot docs: PathFollow2D.set_loop
     */
    fun setLoop(loop: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setLoopBind, segment, loop)
    }

    /**
     * If `true`, any offset outside the path's length will wrap around, instead of stopping at the
     * ends. Use it for cyclic paths.
     *
     * Generated from Godot docs: PathFollow2D.has_loop
     */
    fun hasLoop(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.hasLoopBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): PathFollow2D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): PathFollow2D? =
            if (handle.address() == 0L) null else PathFollow2D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_PROGRESS_HASH = 373806689L
        @JvmField
        val setProgressBind =
            ObjectCalls.getMethodBind("PathFollow2D", "set_progress", SET_PROGRESS_HASH)

        private const val GET_PROGRESS_HASH = 1740695150L
        @JvmField
        val getProgressBind =
            ObjectCalls.getMethodBind("PathFollow2D", "get_progress", GET_PROGRESS_HASH)

        private const val SET_H_OFFSET_HASH = 373806689L
        @JvmField
        val setHOffsetBind =
            ObjectCalls.getMethodBind("PathFollow2D", "set_h_offset", SET_H_OFFSET_HASH)

        private const val GET_H_OFFSET_HASH = 1740695150L
        @JvmField
        val getHOffsetBind =
            ObjectCalls.getMethodBind("PathFollow2D", "get_h_offset", GET_H_OFFSET_HASH)

        private const val SET_V_OFFSET_HASH = 373806689L
        @JvmField
        val setVOffsetBind =
            ObjectCalls.getMethodBind("PathFollow2D", "set_v_offset", SET_V_OFFSET_HASH)

        private const val GET_V_OFFSET_HASH = 1740695150L
        @JvmField
        val getVOffsetBind =
            ObjectCalls.getMethodBind("PathFollow2D", "get_v_offset", GET_V_OFFSET_HASH)

        private const val SET_PROGRESS_RATIO_HASH = 373806689L
        @JvmField
        val setProgressRatioBind =
            ObjectCalls.getMethodBind("PathFollow2D", "set_progress_ratio", SET_PROGRESS_RATIO_HASH)

        private const val GET_PROGRESS_RATIO_HASH = 1740695150L
        @JvmField
        val getProgressRatioBind =
            ObjectCalls.getMethodBind("PathFollow2D", "get_progress_ratio", GET_PROGRESS_RATIO_HASH)

        private const val SET_ROTATES_HASH = 2586408642L
        @JvmField
        val setRotatesBind =
            ObjectCalls.getMethodBind("PathFollow2D", "set_rotates", SET_ROTATES_HASH)

        private const val IS_ROTATING_HASH = 36873697L
        @JvmField
        val isRotatingBind =
            ObjectCalls.getMethodBind("PathFollow2D", "is_rotating", IS_ROTATING_HASH)

        private const val SET_CUBIC_INTERPOLATION_HASH = 2586408642L
        @JvmField
        val setCubicInterpolationBind =
            ObjectCalls.getMethodBind("PathFollow2D", "set_cubic_interpolation", SET_CUBIC_INTERPOLATION_HASH)

        private const val GET_CUBIC_INTERPOLATION_HASH = 36873697L
        @JvmField
        val getCubicInterpolationBind =
            ObjectCalls.getMethodBind("PathFollow2D", "get_cubic_interpolation", GET_CUBIC_INTERPOLATION_HASH)

        private const val SET_LOOP_HASH = 2586408642L
        @JvmField
        val setLoopBind =
            ObjectCalls.getMethodBind("PathFollow2D", "set_loop", SET_LOOP_HASH)

        private const val HAS_LOOP_HASH = 36873697L
        @JvmField
        val hasLoopBind =
            ObjectCalls.getMethodBind("PathFollow2D", "has_loop", HAS_LOOP_HASH)
    }
}
