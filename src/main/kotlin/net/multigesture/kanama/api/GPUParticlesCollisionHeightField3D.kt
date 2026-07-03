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

    /**
     * The collision heightmap's size in 3D units. To improve heightmap quality, `size` should be set
     * as small as possible while covering the parts of the scene you need.
     *
     * Generated from Godot docs: GPUParticlesCollisionHeightField3D.set_size
     */
    fun setSize(size: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setSizeBind, handle, size)
    }

    /**
     * The collision heightmap's size in 3D units. To improve heightmap quality, `size` should be set
     * as small as possible while covering the parts of the scene you need.
     *
     * Generated from Godot docs: GPUParticlesCollisionHeightField3D.get_size
     */
    fun getSize(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getSizeBind, handle)
    }

    /**
     * Higher resolutions can represent small details more accurately in large scenes, at the cost of
     * lower performance. If `update_mode` is `UPDATE_MODE_ALWAYS`, consider using the lowest
     * resolution possible.
     *
     * Generated from Godot docs: GPUParticlesCollisionHeightField3D.set_resolution
     */
    fun setResolution(resolution: Long) {
        ObjectCalls.ptrcallWithLongArg(setResolutionBind, handle, resolution)
    }

    /**
     * Higher resolutions can represent small details more accurately in large scenes, at the cost of
     * lower performance. If `update_mode` is `UPDATE_MODE_ALWAYS`, consider using the lowest
     * resolution possible.
     *
     * Generated from Godot docs: GPUParticlesCollisionHeightField3D.get_resolution
     */
    fun getResolution(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getResolutionBind, handle)
    }

    /**
     * The update policy to use for the generated heightmap.
     *
     * Generated from Godot docs: GPUParticlesCollisionHeightField3D.set_update_mode
     */
    fun setUpdateMode(updateMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setUpdateModeBind, handle, updateMode)
    }

    /**
     * The update policy to use for the generated heightmap.
     *
     * Generated from Godot docs: GPUParticlesCollisionHeightField3D.get_update_mode
     */
    fun getUpdateMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getUpdateModeBind, handle)
    }

    /**
     * The visual layers to account for when updating the heightmap. Only `MeshInstance3D`s whose
     * `VisualInstance3D.layers` match with this `heightfield_mask` will be included in the heightmap
     * collision update. By default, all 20 user-visible layers are taken into account for updating the
     * heightmap collision. Note: Since the `heightfield_mask` allows for 32 layers to be stored in
     * total, there are an additional 12 layers that are only used internally by the engine and aren't
     * exposed in the editor. Setting `heightfield_mask` using a script allows you to toggle those
     * reserved layers, which can be useful for editor plugins. To adjust `heightfield_mask` more
     * easily using a script, use `get_heightfield_mask_value` and `set_heightfield_mask_value`.
     *
     * Generated from Godot docs: GPUParticlesCollisionHeightField3D.set_heightfield_mask
     */
    fun setHeightfieldMask(heightfieldMask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setHeightfieldMaskBind, handle, heightfieldMask)
    }

    /**
     * The visual layers to account for when updating the heightmap. Only `MeshInstance3D`s whose
     * `VisualInstance3D.layers` match with this `heightfield_mask` will be included in the heightmap
     * collision update. By default, all 20 user-visible layers are taken into account for updating the
     * heightmap collision. Note: Since the `heightfield_mask` allows for 32 layers to be stored in
     * total, there are an additional 12 layers that are only used internally by the engine and aren't
     * exposed in the editor. Setting `heightfield_mask` using a script allows you to toggle those
     * reserved layers, which can be useful for editor plugins. To adjust `heightfield_mask` more
     * easily using a script, use `get_heightfield_mask_value` and `set_heightfield_mask_value`.
     *
     * Generated from Godot docs: GPUParticlesCollisionHeightField3D.get_heightfield_mask
     */
    fun getHeightfieldMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getHeightfieldMaskBind, handle)
    }

    /**
     * Based on `value`, enables or disables the specified layer in the `heightfield_mask`, given a
     * `layer_number` between `1` and `20`, inclusive.
     *
     * Generated from Godot docs: GPUParticlesCollisionHeightField3D.set_heightfield_mask_value
     */
    fun setHeightfieldMaskValue(layerNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setHeightfieldMaskValueBind, handle, layerNumber, value)
    }

    /**
     * Returns `true` if the specified layer of the `heightfield_mask` is enabled, given a
     * `layer_number` between `1` and `20`, inclusive.
     *
     * Generated from Godot docs: GPUParticlesCollisionHeightField3D.get_heightfield_mask_value
     */
    fun getHeightfieldMaskValue(layerNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(getHeightfieldMaskValueBind, handle, layerNumber)
    }

    /**
     * If `true`, the `GPUParticlesCollisionHeightField3D` will follow the current camera in global
     * space. The `GPUParticlesCollisionHeightField3D` does not need to be a child of the `Camera3D`
     * node for this to work. Following the camera has a performance cost, as it will force the
     * heightmap to update whenever the camera moves. Consider lowering `resolution` to improve
     * performance if `follow_camera_enabled` is `true`.
     *
     * Generated from Godot docs: GPUParticlesCollisionHeightField3D.set_follow_camera_enabled
     */
    fun setFollowCameraEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFollowCameraEnabledBind, handle, enabled)
    }

    /**
     * If `true`, the `GPUParticlesCollisionHeightField3D` will follow the current camera in global
     * space. The `GPUParticlesCollisionHeightField3D` does not need to be a child of the `Camera3D`
     * node for this to work. Following the camera has a performance cost, as it will force the
     * heightmap to update whenever the camera moves. Consider lowering `resolution` to improve
     * performance if `follow_camera_enabled` is `true`.
     *
     * Generated from Godot docs: GPUParticlesCollisionHeightField3D.is_follow_camera_enabled
     */
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
