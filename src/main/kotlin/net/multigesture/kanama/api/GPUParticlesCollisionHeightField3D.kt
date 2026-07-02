package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector3

/**
 * A real-time heightmap-shaped 3D particle collision shape affecting `GPUParticles3D` nodes.
 *
 * Generated from Godot docs: GPUParticlesCollisionHeightField3D
 */
class GPUParticlesCollisionHeightField3D(handle: MemorySegment) : GPUParticlesCollision3D(handle) {
    var size: Vector3
        @JvmName("sizeProperty")
        get() = getSize()
        @JvmName("setSizeProperty")
        set(value) = setSize(value)

    var resolution: Long
        @JvmName("resolutionProperty")
        get() = getResolution()
        @JvmName("setResolutionProperty")
        set(value) = setResolution(value)

    var updateMode: Long
        @JvmName("updateModeProperty")
        get() = getUpdateMode()
        @JvmName("setUpdateModeProperty")
        set(value) = setUpdateMode(value)

    var followCameraEnabled: Boolean
        @JvmName("followCameraEnabledProperty")
        get() = isFollowCameraEnabled()
        @JvmName("setFollowCameraEnabledProperty")
        set(value) = setFollowCameraEnabled(value)

    var heightfieldMask: Long
        @JvmName("heightfieldMaskProperty")
        get() = getHeightfieldMask()
        @JvmName("setHeightfieldMaskProperty")
        set(value) = setHeightfieldMask(value)

    fun setSize(size: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setSizeBind, handle, size)
    }

    fun getSize(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getSizeBind, handle)
    }

    fun setResolution(resolution: Long) {
        ObjectCalls.ptrcallWithLongArg(setResolutionBind, handle, resolution)
    }

    fun getResolution(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getResolutionBind, handle)
    }

    fun setUpdateMode(updateMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setUpdateModeBind, handle, updateMode)
    }

    fun getUpdateMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getUpdateModeBind, handle)
    }

    fun setHeightfieldMask(heightfieldMask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setHeightfieldMaskBind, handle, heightfieldMask)
    }

    fun getHeightfieldMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getHeightfieldMaskBind, handle)
    }

    fun setHeightfieldMaskValue(layerNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setHeightfieldMaskValueBind, handle, layerNumber, value)
    }

    fun getHeightfieldMaskValue(layerNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(getHeightfieldMaskValueBind, handle, layerNumber)
    }

    fun setFollowCameraEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFollowCameraEnabledBind, handle, enabled)
    }

    fun isFollowCameraEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isFollowCameraEnabledBind, handle)
    }

    companion object {
        const val RESOLUTION_256: Long = 0L
        const val RESOLUTION_512: Long = 1L
        const val RESOLUTION_1024: Long = 2L
        const val RESOLUTION_2048: Long = 3L
        const val RESOLUTION_4096: Long = 4L
        const val RESOLUTION_8192: Long = 5L
        const val RESOLUTION_MAX: Long = 6L
        const val UPDATE_MODE_WHEN_MOVED: Long = 0L
        const val UPDATE_MODE_ALWAYS: Long = 1L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): GPUParticlesCollisionHeightField3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): GPUParticlesCollisionHeightField3D? =
            if (handle.address() == 0L) null else GPUParticlesCollisionHeightField3D(handle)

        private const val SET_SIZE_HASH = 3460891852L
        private val setSizeBind by lazy {
            ObjectCalls.getMethodBind("GPUParticlesCollisionHeightField3D", "set_size", SET_SIZE_HASH)
        }

        private const val GET_SIZE_HASH = 3360562783L
        private val getSizeBind by lazy {
            ObjectCalls.getMethodBind("GPUParticlesCollisionHeightField3D", "get_size", GET_SIZE_HASH)
        }

        private const val SET_RESOLUTION_HASH = 1009996517L
        private val setResolutionBind by lazy {
            ObjectCalls.getMethodBind("GPUParticlesCollisionHeightField3D", "set_resolution", SET_RESOLUTION_HASH)
        }

        private const val GET_RESOLUTION_HASH = 1156065644L
        private val getResolutionBind by lazy {
            ObjectCalls.getMethodBind("GPUParticlesCollisionHeightField3D", "get_resolution", GET_RESOLUTION_HASH)
        }

        private const val SET_UPDATE_MODE_HASH = 673680859L
        private val setUpdateModeBind by lazy {
            ObjectCalls.getMethodBind("GPUParticlesCollisionHeightField3D", "set_update_mode", SET_UPDATE_MODE_HASH)
        }

        private const val GET_UPDATE_MODE_HASH = 1998141380L
        private val getUpdateModeBind by lazy {
            ObjectCalls.getMethodBind("GPUParticlesCollisionHeightField3D", "get_update_mode", GET_UPDATE_MODE_HASH)
        }

        private const val SET_HEIGHTFIELD_MASK_HASH = 1286410249L
        private val setHeightfieldMaskBind by lazy {
            ObjectCalls.getMethodBind("GPUParticlesCollisionHeightField3D", "set_heightfield_mask", SET_HEIGHTFIELD_MASK_HASH)
        }

        private const val GET_HEIGHTFIELD_MASK_HASH = 3905245786L
        private val getHeightfieldMaskBind by lazy {
            ObjectCalls.getMethodBind("GPUParticlesCollisionHeightField3D", "get_heightfield_mask", GET_HEIGHTFIELD_MASK_HASH)
        }

        private const val SET_HEIGHTFIELD_MASK_VALUE_HASH = 300928843L
        private val setHeightfieldMaskValueBind by lazy {
            ObjectCalls.getMethodBind("GPUParticlesCollisionHeightField3D", "set_heightfield_mask_value", SET_HEIGHTFIELD_MASK_VALUE_HASH)
        }

        private const val GET_HEIGHTFIELD_MASK_VALUE_HASH = 1116898809L
        private val getHeightfieldMaskValueBind by lazy {
            ObjectCalls.getMethodBind("GPUParticlesCollisionHeightField3D", "get_heightfield_mask_value", GET_HEIGHTFIELD_MASK_VALUE_HASH)
        }

        private const val SET_FOLLOW_CAMERA_ENABLED_HASH = 2586408642L
        private val setFollowCameraEnabledBind by lazy {
            ObjectCalls.getMethodBind("GPUParticlesCollisionHeightField3D", "set_follow_camera_enabled", SET_FOLLOW_CAMERA_ENABLED_HASH)
        }

        private const val IS_FOLLOW_CAMERA_ENABLED_HASH = 36873697L
        private val isFollowCameraEnabledBind by lazy {
            ObjectCalls.getMethodBind("GPUParticlesCollisionHeightField3D", "is_follow_camera_enabled", IS_FOLLOW_CAMERA_ENABLED_HASH)
        }
    }
}
