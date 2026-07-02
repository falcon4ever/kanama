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
    fun setPath3d(index: Int, path3d: NodePath) {
        ObjectCalls.ptrcallWithIntAndNodePathArg(setPath3dBind, handle, index, path3d)
    }

    fun getPath3d(index: Int): NodePath {
        return ObjectCalls.ptrcallWithIntArgRetNodePath(getPath3dBind, handle, index)
    }

    fun setTiltEnabled(index: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setTiltEnabledBind, handle, index, enabled)
    }

    fun isTiltEnabled(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isTiltEnabledBind, handle, index)
    }

    fun setTiltFadeIn(index: Int, size: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setTiltFadeInBind, handle, index, size)
    }

    fun getTiltFadeIn(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getTiltFadeInBind, handle, index)
    }

    fun setTiltFadeOut(index: Int, size: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setTiltFadeOutBind, handle, index, size)
    }

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
