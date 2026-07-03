package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Transform3D

/**
 * Point sampler for a `Path3D`.
 *
 * Generated from Godot docs: PathFollow3D
 */
class PathFollow3D(handle: MemorySegment) : Node3D(handle) {
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

    var rotationMode: Long
        @JvmName("rotationModeProperty")
        get() = getRotationMode()
        @JvmName("setRotationModeProperty")
        set(value) = setRotationMode(value)

    var useModelFront: Boolean
        @JvmName("useModelFrontProperty")
        get() = isUsingModelFront()
        @JvmName("setUseModelFrontProperty")
        set(value) = setUseModelFront(value)

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

    var tiltEnabled: Boolean
        @JvmName("tiltEnabledProperty")
        get() = isTiltEnabled()
        @JvmName("setTiltEnabledProperty")
        set(value) = setTiltEnabled(value)

    /**
     * The distance from the first vertex, measured in 3D units along the path. Changing this value
     * sets this node's position to a point within the path.
     *
     * Generated from Godot docs: PathFollow3D.set_progress
     */
    fun setProgress(progress: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setProgressBind, handle, progress)
    }

    /**
     * The distance from the first vertex, measured in 3D units along the path. Changing this value
     * sets this node's position to a point within the path.
     *
     * Generated from Godot docs: PathFollow3D.get_progress
     */
    fun getProgress(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getProgressBind, handle)
    }

    /**
     * The node's offset along the curve.
     *
     * Generated from Godot docs: PathFollow3D.set_h_offset
     */
    fun setHOffset(hOffset: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setHOffsetBind, handle, hOffset)
    }

    /**
     * The node's offset along the curve.
     *
     * Generated from Godot docs: PathFollow3D.get_h_offset
     */
    fun getHOffset(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getHOffsetBind, handle)
    }

    /**
     * The node's offset perpendicular to the curve.
     *
     * Generated from Godot docs: PathFollow3D.set_v_offset
     */
    fun setVOffset(vOffset: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVOffsetBind, handle, vOffset)
    }

    /**
     * The node's offset perpendicular to the curve.
     *
     * Generated from Godot docs: PathFollow3D.get_v_offset
     */
    fun getVOffset(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVOffsetBind, handle)
    }

    /**
     * The distance from the first vertex, considering 0.0 as the first vertex and 1.0 as the last.
     * This is just another way of expressing the progress within the path, as the progress supplied is
     * multiplied internally by the path's length. It can be set or get only if the `PathFollow3D` is
     * the child of a `Path3D` which is part of the scene tree, and that this `Path3D` has a `Curve3D`
     * with a non-zero length. Otherwise, trying to set this field will print an error, and getting
     * this field will return `0.0`.
     *
     * Generated from Godot docs: PathFollow3D.set_progress_ratio
     */
    fun setProgressRatio(ratio: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setProgressRatioBind, handle, ratio)
    }

    /**
     * The distance from the first vertex, considering 0.0 as the first vertex and 1.0 as the last.
     * This is just another way of expressing the progress within the path, as the progress supplied is
     * multiplied internally by the path's length. It can be set or get only if the `PathFollow3D` is
     * the child of a `Path3D` which is part of the scene tree, and that this `Path3D` has a `Curve3D`
     * with a non-zero length. Otherwise, trying to set this field will print an error, and getting
     * this field will return `0.0`.
     *
     * Generated from Godot docs: PathFollow3D.get_progress_ratio
     */
    fun getProgressRatio(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getProgressRatioBind, handle)
    }

    /**
     * Allows or forbids rotation on one or more axes, depending on the `RotationMode` constants being
     * used.
     *
     * Generated from Godot docs: PathFollow3D.set_rotation_mode
     */
    fun setRotationMode(rotationMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setRotationModeBind, handle, rotationMode)
    }

    /**
     * Allows or forbids rotation on one or more axes, depending on the `RotationMode` constants being
     * used.
     *
     * Generated from Godot docs: PathFollow3D.get_rotation_mode
     */
    fun getRotationMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getRotationModeBind, handle)
    }

    /**
     * If `true`, the position between two cached points is interpolated cubically, and linearly
     * otherwise. The points along the `Curve3D` of the `Path3D` are precomputed before use, for faster
     * calculations. The point at the requested offset is then calculated interpolating between two
     * adjacent cached points. This may present a problem if the curve makes sharp turns, as the cached
     * points may not follow the curve closely enough. There are two answers to this problem: either
     * increase the number of cached points and increase memory consumption, or make a cubic
     * interpolation between two points at the cost of (slightly) slower calculations.
     *
     * Generated from Godot docs: PathFollow3D.set_cubic_interpolation
     */
    fun setCubicInterpolation(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCubicInterpolationBind, handle, enabled)
    }

    /**
     * If `true`, the position between two cached points is interpolated cubically, and linearly
     * otherwise. The points along the `Curve3D` of the `Path3D` are precomputed before use, for faster
     * calculations. The point at the requested offset is then calculated interpolating between two
     * adjacent cached points. This may present a problem if the curve makes sharp turns, as the cached
     * points may not follow the curve closely enough. There are two answers to this problem: either
     * increase the number of cached points and increase memory consumption, or make a cubic
     * interpolation between two points at the cost of (slightly) slower calculations.
     *
     * Generated from Godot docs: PathFollow3D.get_cubic_interpolation
     */
    fun getCubicInterpolation(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getCubicInterpolationBind, handle)
    }

    /**
     * If `true`, the node moves on the travel path with orienting the +Z axis as forward. See also
     * `Vector3.FORWARD` and `Vector3.MODEL_FRONT`.
     *
     * Generated from Godot docs: PathFollow3D.set_use_model_front
     */
    fun setUseModelFront(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseModelFrontBind, handle, enabled)
    }

    /**
     * If `true`, the node moves on the travel path with orienting the +Z axis as forward. See also
     * `Vector3.FORWARD` and `Vector3.MODEL_FRONT`.
     *
     * Generated from Godot docs: PathFollow3D.is_using_model_front
     */
    fun isUsingModelFront(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingModelFrontBind, handle)
    }

    /**
     * If `true`, any offset outside the path's length will wrap around, instead of stopping at the
     * ends. Use it for cyclic paths.
     *
     * Generated from Godot docs: PathFollow3D.set_loop
     */
    fun setLoop(loop: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setLoopBind, handle, loop)
    }

    /**
     * If `true`, any offset outside the path's length will wrap around, instead of stopping at the
     * ends. Use it for cyclic paths.
     *
     * Generated from Godot docs: PathFollow3D.has_loop
     */
    fun hasLoop(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasLoopBind, handle)
    }

    /**
     * If `true`, the tilt property of `Curve3D` takes effect.
     *
     * Generated from Godot docs: PathFollow3D.set_tilt_enabled
     */
    fun setTiltEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setTiltEnabledBind, handle, enabled)
    }

    /**
     * If `true`, the tilt property of `Curve3D` takes effect.
     *
     * Generated from Godot docs: PathFollow3D.is_tilt_enabled
     */
    fun isTiltEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isTiltEnabledBind, handle)
    }

    companion object {
        /**
         * Correct the `transform`. `rotation_mode` implicitly specifies how posture (forward, up and
         * sideway direction) is calculated.
         *
         * Generated from Godot docs: PathFollow3D.correct_posture
         */
        fun correctPosture(transform: Transform3D, rotationMode: Long): Transform3D {
            return ObjectCalls.ptrcallWithTransform3DAndLongArgsRetTransform3D(correctPostureBind, MemorySegment.NULL, transform, rotationMode)
        }

        const val ROTATION_NONE: Long = 0L
        const val ROTATION_Y: Long = 1L
        const val ROTATION_XY: Long = 2L
        const val ROTATION_XYZ: Long = 3L
        const val ROTATION_ORIENTED: Long = 4L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): PathFollow3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PathFollow3D? =
            if (handle.address() == 0L) null else PathFollow3D(handle)

        private const val SET_PROGRESS_HASH = 373806689L
        private val setProgressBind by lazy {
            ObjectCalls.getMethodBind("PathFollow3D", "set_progress", SET_PROGRESS_HASH)
        }

        private const val GET_PROGRESS_HASH = 1740695150L
        private val getProgressBind by lazy {
            ObjectCalls.getMethodBind("PathFollow3D", "get_progress", GET_PROGRESS_HASH)
        }

        private const val SET_H_OFFSET_HASH = 373806689L
        private val setHOffsetBind by lazy {
            ObjectCalls.getMethodBind("PathFollow3D", "set_h_offset", SET_H_OFFSET_HASH)
        }

        private const val GET_H_OFFSET_HASH = 1740695150L
        private val getHOffsetBind by lazy {
            ObjectCalls.getMethodBind("PathFollow3D", "get_h_offset", GET_H_OFFSET_HASH)
        }

        private const val SET_V_OFFSET_HASH = 373806689L
        private val setVOffsetBind by lazy {
            ObjectCalls.getMethodBind("PathFollow3D", "set_v_offset", SET_V_OFFSET_HASH)
        }

        private const val GET_V_OFFSET_HASH = 1740695150L
        private val getVOffsetBind by lazy {
            ObjectCalls.getMethodBind("PathFollow3D", "get_v_offset", GET_V_OFFSET_HASH)
        }

        private const val SET_PROGRESS_RATIO_HASH = 373806689L
        private val setProgressRatioBind by lazy {
            ObjectCalls.getMethodBind("PathFollow3D", "set_progress_ratio", SET_PROGRESS_RATIO_HASH)
        }

        private const val GET_PROGRESS_RATIO_HASH = 1740695150L
        private val getProgressRatioBind by lazy {
            ObjectCalls.getMethodBind("PathFollow3D", "get_progress_ratio", GET_PROGRESS_RATIO_HASH)
        }

        private const val SET_ROTATION_MODE_HASH = 1640311967L
        private val setRotationModeBind by lazy {
            ObjectCalls.getMethodBind("PathFollow3D", "set_rotation_mode", SET_ROTATION_MODE_HASH)
        }

        private const val GET_ROTATION_MODE_HASH = 3814010545L
        private val getRotationModeBind by lazy {
            ObjectCalls.getMethodBind("PathFollow3D", "get_rotation_mode", GET_ROTATION_MODE_HASH)
        }

        private const val SET_CUBIC_INTERPOLATION_HASH = 2586408642L
        private val setCubicInterpolationBind by lazy {
            ObjectCalls.getMethodBind("PathFollow3D", "set_cubic_interpolation", SET_CUBIC_INTERPOLATION_HASH)
        }

        private const val GET_CUBIC_INTERPOLATION_HASH = 36873697L
        private val getCubicInterpolationBind by lazy {
            ObjectCalls.getMethodBind("PathFollow3D", "get_cubic_interpolation", GET_CUBIC_INTERPOLATION_HASH)
        }

        private const val SET_USE_MODEL_FRONT_HASH = 2586408642L
        private val setUseModelFrontBind by lazy {
            ObjectCalls.getMethodBind("PathFollow3D", "set_use_model_front", SET_USE_MODEL_FRONT_HASH)
        }

        private const val IS_USING_MODEL_FRONT_HASH = 36873697L
        private val isUsingModelFrontBind by lazy {
            ObjectCalls.getMethodBind("PathFollow3D", "is_using_model_front", IS_USING_MODEL_FRONT_HASH)
        }

        private const val SET_LOOP_HASH = 2586408642L
        private val setLoopBind by lazy {
            ObjectCalls.getMethodBind("PathFollow3D", "set_loop", SET_LOOP_HASH)
        }

        private const val HAS_LOOP_HASH = 36873697L
        private val hasLoopBind by lazy {
            ObjectCalls.getMethodBind("PathFollow3D", "has_loop", HAS_LOOP_HASH)
        }

        private const val SET_TILT_ENABLED_HASH = 2586408642L
        private val setTiltEnabledBind by lazy {
            ObjectCalls.getMethodBind("PathFollow3D", "set_tilt_enabled", SET_TILT_ENABLED_HASH)
        }

        private const val IS_TILT_ENABLED_HASH = 36873697L
        private val isTiltEnabledBind by lazy {
            ObjectCalls.getMethodBind("PathFollow3D", "is_tilt_enabled", IS_TILT_ENABLED_HASH)
        }

        private const val CORRECT_POSTURE_HASH = 2686588690L
        private val correctPostureBind by lazy {
            ObjectCalls.getMethodBind("PathFollow3D", "correct_posture", CORRECT_POSTURE_HASH)
        }
    }
}
