package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.NodePath

/**
 * A `SkeletonModifier3D` for aligning bones along a `Path3D`.
 *
 * Generated from Godot docs: SplineIK3D
 */
class SplineIK3D(handle: MemorySegment) : ChainIK3D(handle) {
    /**
     * Sets the node path of the `Path3D` which is describing the path.
     *
     * Generated from Godot docs: SplineIK3D.set_path_3d
     */
    fun setPath3d(index: Int, path3d: NodePath) {
        ObjectCalls.ptrcallWithIntAndNodePathArg(setPath3dBind, handle, index, path3d)
    }

    /**
     * Returns the node path of the `Path3D` which is describing the path.
     *
     * Generated from Godot docs: SplineIK3D.get_path_3d
     */
    fun getPath3d(index: Int): NodePath {
        return ObjectCalls.ptrcallWithIntArgRetNodePath(getPath3dBind, handle, index)
    }

    /**
     * Sets if the tilt property of the `Curve3D` should affect the bone twist.
     *
     * Generated from Godot docs: SplineIK3D.set_tilt_enabled
     */
    fun setTiltEnabled(index: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setTiltEnabledBind, handle, index, enabled)
    }

    /**
     * Returns if the tilt property of the `Curve3D` affects the bone twist.
     *
     * Generated from Godot docs: SplineIK3D.is_tilt_enabled
     */
    fun isTiltEnabled(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isTiltEnabledBind, handle, index)
    }

    /**
     * If `size` is greater than `0`, the tilt is interpolated between `size` start bones from the
     * start point of the `Curve3D` when they are apart. If `size` is equal `0`, the tilts between the
     * root bone head and the start point of the `Curve3D` are unified with a tilt of the start point
     * of the `Curve3D`. If `size` is less than `0`, the tilts between the root bone and the start
     * point of the `Curve3D` are `0.0`.
     *
     * Generated from Godot docs: SplineIK3D.set_tilt_fade_in
     */
    fun setTiltFadeIn(index: Int, size: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setTiltFadeInBind, handle, index, size)
    }

    /**
     * Returns the tilt interpolation method used between the root bone and the start point of the
     * `Curve3D` when they are apart. See also `set_tilt_fade_in`.
     *
     * Generated from Godot docs: SplineIK3D.get_tilt_fade_in
     */
    fun getTiltFadeIn(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getTiltFadeInBind, handle, index)
    }

    /**
     * If `size` is greater than `0`, the tilt is interpolated between `size` end bones from the end
     * point of the `Curve3D` when they are apart. If `size` is equal `0`, the tilts between the end
     * bone tail and the end point of the `Curve3D` are unified with a tilt of the end point of the
     * `Curve3D`. If `size` is less than `0`, the tilts between the end bone and the end point of the
     * `Curve3D` are `0.0`.
     *
     * Generated from Godot docs: SplineIK3D.set_tilt_fade_out
     */
    fun setTiltFadeOut(index: Int, size: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setTiltFadeOutBind, handle, index, size)
    }

    /**
     * Returns the tilt interpolation method used between the end bone and the end point of the
     * `Curve3D` when they are apart. See also `set_tilt_fade_out`.
     *
     * Generated from Godot docs: SplineIK3D.get_tilt_fade_out
     */
    fun getTiltFadeOut(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getTiltFadeOutBind, handle, index)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): SplineIK3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): SplineIK3D? =
            if (handle.address() == 0L) null else SplineIK3D(handle)

        private const val SET_PATH_3D_HASH = 2761262315L
        private val setPath3dBind by lazy {
            ObjectCalls.getMethodBind("SplineIK3D", "set_path_3d", SET_PATH_3D_HASH)
        }

        private const val GET_PATH_3D_HASH = 408788394L
        private val getPath3dBind by lazy {
            ObjectCalls.getMethodBind("SplineIK3D", "get_path_3d", GET_PATH_3D_HASH)
        }

        private const val SET_TILT_ENABLED_HASH = 300928843L
        private val setTiltEnabledBind by lazy {
            ObjectCalls.getMethodBind("SplineIK3D", "set_tilt_enabled", SET_TILT_ENABLED_HASH)
        }

        private const val IS_TILT_ENABLED_HASH = 1116898809L
        private val isTiltEnabledBind by lazy {
            ObjectCalls.getMethodBind("SplineIK3D", "is_tilt_enabled", IS_TILT_ENABLED_HASH)
        }

        private const val SET_TILT_FADE_IN_HASH = 3937882851L
        private val setTiltFadeInBind by lazy {
            ObjectCalls.getMethodBind("SplineIK3D", "set_tilt_fade_in", SET_TILT_FADE_IN_HASH)
        }

        private const val GET_TILT_FADE_IN_HASH = 923996154L
        private val getTiltFadeInBind by lazy {
            ObjectCalls.getMethodBind("SplineIK3D", "get_tilt_fade_in", GET_TILT_FADE_IN_HASH)
        }

        private const val SET_TILT_FADE_OUT_HASH = 3937882851L
        private val setTiltFadeOutBind by lazy {
            ObjectCalls.getMethodBind("SplineIK3D", "set_tilt_fade_out", SET_TILT_FADE_OUT_HASH)
        }

        private const val GET_TILT_FADE_OUT_HASH = 923996154L
        private val getTiltFadeOutBind by lazy {
            ObjectCalls.getMethodBind("SplineIK3D", "get_tilt_fade_out", GET_TILT_FADE_OUT_HASH)
        }
    }
}
