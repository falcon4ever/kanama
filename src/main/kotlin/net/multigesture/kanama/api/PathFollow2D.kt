package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Point sampler for a `Path2D`.
 *
 * Generated from Godot docs: PathFollow2D
 */
class PathFollow2D(handle: MemorySegment) : Node2D(handle) {
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
        ObjectCalls.ptrcallWithDoubleArg(setProgressBind, handle, progress)
    }

    /**
     * The distance along the path, in pixels. Changing this value sets this node's position to a point
     * within the path.
     *
     * Generated from Godot docs: PathFollow2D.get_progress
     */
    fun getProgress(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getProgressBind, handle)
    }

    /**
     * The node's offset along the curve.
     *
     * Generated from Godot docs: PathFollow2D.set_h_offset
     */
    fun setHOffset(hOffset: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setHOffsetBind, handle, hOffset)
    }

    /**
     * The node's offset along the curve.
     *
     * Generated from Godot docs: PathFollow2D.get_h_offset
     */
    fun getHOffset(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getHOffsetBind, handle)
    }

    /**
     * The node's offset perpendicular to the curve.
     *
     * Generated from Godot docs: PathFollow2D.set_v_offset
     */
    fun setVOffset(vOffset: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVOffsetBind, handle, vOffset)
    }

    /**
     * The node's offset perpendicular to the curve.
     *
     * Generated from Godot docs: PathFollow2D.get_v_offset
     */
    fun getVOffset(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVOffsetBind, handle)
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
        ObjectCalls.ptrcallWithDoubleArg(setProgressRatioBind, handle, ratio)
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
        return ObjectCalls.ptrcallNoArgsRetDouble(getProgressRatioBind, handle)
    }

    /**
     * If `true`, this node rotates to follow the path, with the +X direction facing forward on the
     * path.
     *
     * Generated from Godot docs: PathFollow2D.set_rotates
     */
    fun setRotates(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setRotatesBind, handle, enabled)
    }

    /**
     * If `true`, this node rotates to follow the path, with the +X direction facing forward on the
     * path.
     *
     * Generated from Godot docs: PathFollow2D.is_rotating
     */
    fun isRotating(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isRotatingBind, handle)
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
        ObjectCalls.ptrcallWithBoolArg(setCubicInterpolationBind, handle, enabled)
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
        return ObjectCalls.ptrcallNoArgsRetBool(getCubicInterpolationBind, handle)
    }

    /**
     * If `true`, any offset outside the path's length will wrap around, instead of stopping at the
     * ends. Use it for cyclic paths.
     *
     * Generated from Godot docs: PathFollow2D.set_loop
     */
    fun setLoop(loop: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setLoopBind, handle, loop)
    }

    /**
     * If `true`, any offset outside the path's length will wrap around, instead of stopping at the
     * ends. Use it for cyclic paths.
     *
     * Generated from Godot docs: PathFollow2D.has_loop
     */
    fun hasLoop(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasLoopBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): PathFollow2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PathFollow2D? =
            if (handle.address() == 0L) null else PathFollow2D(handle)

        private const val SET_PROGRESS_HASH = 373806689L
        private val setProgressBind by lazy {
            ObjectCalls.getMethodBind("PathFollow2D", "set_progress", SET_PROGRESS_HASH)
        }

        private const val GET_PROGRESS_HASH = 1740695150L
        private val getProgressBind by lazy {
            ObjectCalls.getMethodBind("PathFollow2D", "get_progress", GET_PROGRESS_HASH)
        }

        private const val SET_H_OFFSET_HASH = 373806689L
        private val setHOffsetBind by lazy {
            ObjectCalls.getMethodBind("PathFollow2D", "set_h_offset", SET_H_OFFSET_HASH)
        }

        private const val GET_H_OFFSET_HASH = 1740695150L
        private val getHOffsetBind by lazy {
            ObjectCalls.getMethodBind("PathFollow2D", "get_h_offset", GET_H_OFFSET_HASH)
        }

        private const val SET_V_OFFSET_HASH = 373806689L
        private val setVOffsetBind by lazy {
            ObjectCalls.getMethodBind("PathFollow2D", "set_v_offset", SET_V_OFFSET_HASH)
        }

        private const val GET_V_OFFSET_HASH = 1740695150L
        private val getVOffsetBind by lazy {
            ObjectCalls.getMethodBind("PathFollow2D", "get_v_offset", GET_V_OFFSET_HASH)
        }

        private const val SET_PROGRESS_RATIO_HASH = 373806689L
        private val setProgressRatioBind by lazy {
            ObjectCalls.getMethodBind("PathFollow2D", "set_progress_ratio", SET_PROGRESS_RATIO_HASH)
        }

        private const val GET_PROGRESS_RATIO_HASH = 1740695150L
        private val getProgressRatioBind by lazy {
            ObjectCalls.getMethodBind("PathFollow2D", "get_progress_ratio", GET_PROGRESS_RATIO_HASH)
        }

        private const val SET_ROTATES_HASH = 2586408642L
        private val setRotatesBind by lazy {
            ObjectCalls.getMethodBind("PathFollow2D", "set_rotates", SET_ROTATES_HASH)
        }

        private const val IS_ROTATING_HASH = 36873697L
        private val isRotatingBind by lazy {
            ObjectCalls.getMethodBind("PathFollow2D", "is_rotating", IS_ROTATING_HASH)
        }

        private const val SET_CUBIC_INTERPOLATION_HASH = 2586408642L
        private val setCubicInterpolationBind by lazy {
            ObjectCalls.getMethodBind("PathFollow2D", "set_cubic_interpolation", SET_CUBIC_INTERPOLATION_HASH)
        }

        private const val GET_CUBIC_INTERPOLATION_HASH = 36873697L
        private val getCubicInterpolationBind by lazy {
            ObjectCalls.getMethodBind("PathFollow2D", "get_cubic_interpolation", GET_CUBIC_INTERPOLATION_HASH)
        }

        private const val SET_LOOP_HASH = 2586408642L
        private val setLoopBind by lazy {
            ObjectCalls.getMethodBind("PathFollow2D", "set_loop", SET_LOOP_HASH)
        }

        private const val HAS_LOOP_HASH = 36873697L
        private val hasLoopBind by lazy {
            ObjectCalls.getMethodBind("PathFollow2D", "has_loop", HAS_LOOP_HASH)
        }
    }
}
