package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Vector3

/**
 * A real-time heightmap-shaped 3D particle collision shape affecting `GPUParticles3D` nodes.
 *
 * Generated from Godot docs: GPUParticlesCollisionHeightField3D
 */
class GPUParticlesCollisionHeightField3D(handle: GodotHandle) : GPUParticlesCollision3D(handle) {
    var size: Vector3
        @JvmName("sizeProperty")
        get() = getSize()
        @JvmName("setSizeProperty")
        set(value) = setSize(value)

    var resolution: GPUParticlesCollisionHeightField3D.Resolution
        @JvmName("resolutionProperty")
        get() = getResolution()
        @JvmName("setResolutionProperty")
        set(value) = setResolution(value)

    var updateMode: GPUParticlesCollisionHeightField3D.UpdateMode
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
        ObjectCalls.ptrcallWithVector3Arg(Binds.setSizeBind, segment, size)
    }

    /**
     * The collision heightmap's size in 3D units. To improve heightmap quality, `size` should be set
     * as small as possible while covering the parts of the scene you need.
     *
     * Generated from Godot docs: GPUParticlesCollisionHeightField3D.get_size
     */
    fun getSize(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getSizeBind, segment)
    }

    /**
     * Higher resolutions can represent small details more accurately in large scenes, at the cost of
     * lower performance. If `update_mode` is `UpdateMode.ALWAYS`, consider using the lowest resolution
     * possible.
     *
     * Generated from Godot docs: GPUParticlesCollisionHeightField3D.set_resolution
     */
    fun setResolution(resolution: GPUParticlesCollisionHeightField3D.Resolution) {
        ObjectCalls.ptrcallWithLongArg(Binds.setResolutionBind, segment, resolution.value)
    }

    /**
     * Higher resolutions can represent small details more accurately in large scenes, at the cost of
     * lower performance. If `update_mode` is `UpdateMode.ALWAYS`, consider using the lowest resolution
     * possible.
     *
     * Generated from Godot docs: GPUParticlesCollisionHeightField3D.get_resolution
     */
    fun getResolution(): GPUParticlesCollisionHeightField3D.Resolution {
        return GPUParticlesCollisionHeightField3D.Resolution(ObjectCalls.ptrcallNoArgsRetLong(Binds.getResolutionBind, segment))
    }

    /**
     * The update policy to use for the generated heightmap.
     *
     * Generated from Godot docs: GPUParticlesCollisionHeightField3D.set_update_mode
     */
    fun setUpdateMode(updateMode: GPUParticlesCollisionHeightField3D.UpdateMode) {
        ObjectCalls.ptrcallWithLongArg(Binds.setUpdateModeBind, segment, updateMode.value)
    }

    /**
     * The update policy to use for the generated heightmap.
     *
     * Generated from Godot docs: GPUParticlesCollisionHeightField3D.get_update_mode
     */
    fun getUpdateMode(): GPUParticlesCollisionHeightField3D.UpdateMode {
        return GPUParticlesCollisionHeightField3D.UpdateMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getUpdateModeBind, segment))
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
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setHeightfieldMaskBind, segment, heightfieldMask)
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
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getHeightfieldMaskBind, segment)
    }

    /**
     * Based on `value`, enables or disables the specified layer in the `heightfield_mask`, given a
     * `layer_number` between `1` and `20`, inclusive.
     *
     * Generated from Godot docs: GPUParticlesCollisionHeightField3D.set_heightfield_mask_value
     */
    fun setHeightfieldMaskValue(layerNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setHeightfieldMaskValueBind, segment, layerNumber, value)
    }

    /**
     * Returns `true` if the specified layer of the `heightfield_mask` is enabled, given a
     * `layer_number` between `1` and `20`, inclusive.
     *
     * Generated from Godot docs: GPUParticlesCollisionHeightField3D.get_heightfield_mask_value
     */
    fun getHeightfieldMaskValue(layerNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.getHeightfieldMaskValueBind, segment, layerNumber)
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
        ObjectCalls.ptrcallWithBoolArg(Binds.setFollowCameraEnabledBind, segment, enabled)
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
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isFollowCameraEnabledBind, segment)
    }

    /**
     * Godot's `GPUParticlesCollisionHeightField3D.Resolution` enum as a typed value: `.value` is the
     * raw number Godot uses, and the companion holds the named values
     * (`GPUParticlesCollisionHeightField3D.Resolution.<NAME>`).
     *
     * Generated from Godot docs: GPUParticlesCollisionHeightField3D.Resolution
     */
    @JvmInline
    value class Resolution(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Generate a 256×256 heightmap. Intended for small-scale scenes, or larger scenes with no distant
             * particles.
             *
             * Generated from Godot docs: GPUParticlesCollisionHeightField3D.RESOLUTION_256
             */
            val RESOLUTION_256: Resolution get() = Resolution(0L)
            /**
             * Generate a 512×512 heightmap. Intended for medium-scale scenes, or larger scenes with no distant
             * particles.
             *
             * Generated from Godot docs: GPUParticlesCollisionHeightField3D.RESOLUTION_512
             */
            val RESOLUTION_512: Resolution get() = Resolution(1L)
            /**
             * Generate a 1024×1024 heightmap. Intended for large scenes with distant particles.
             *
             * Generated from Godot docs: GPUParticlesCollisionHeightField3D.RESOLUTION_1024
             */
            val RESOLUTION_1024: Resolution get() = Resolution(2L)
            /**
             * Generate a 2048×2048 heightmap. Intended for very large scenes with distant particles.
             *
             * Generated from Godot docs: GPUParticlesCollisionHeightField3D.RESOLUTION_2048
             */
            val RESOLUTION_2048: Resolution get() = Resolution(3L)
            /**
             * Generate a 4096×4096 heightmap. Intended for huge scenes with distant particles.
             *
             * Generated from Godot docs: GPUParticlesCollisionHeightField3D.RESOLUTION_4096
             */
            val RESOLUTION_4096: Resolution get() = Resolution(4L)
            /**
             * Generate a 8192×8192 heightmap. Intended for gigantic scenes with distant particles.
             *
             * Generated from Godot docs: GPUParticlesCollisionHeightField3D.RESOLUTION_8192
             */
            val RESOLUTION_8192: Resolution get() = Resolution(5L)
            /**
             * Represents the size of the `Resolution` enum.
             *
             * Generated from Godot docs: GPUParticlesCollisionHeightField3D.RESOLUTION_MAX
             */
            val MAX: Resolution get() = Resolution(6L)
        }
    }

    /**
     * Godot's `GPUParticlesCollisionHeightField3D.UpdateMode` enum as a typed value: `.value` is the
     * raw number Godot uses, and the companion holds the named values
     * (`GPUParticlesCollisionHeightField3D.UpdateMode.<NAME>`).
     *
     * Generated from Godot docs: GPUParticlesCollisionHeightField3D.UpdateMode
     */
    @JvmInline
    value class UpdateMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Only update the heightmap when the `GPUParticlesCollisionHeightField3D` node is moved, or when
             * the camera moves if `follow_camera_enabled` is `true`. An update can be forced by slightly
             * moving the `GPUParticlesCollisionHeightField3D` in any direction, or by calling
             * `RenderingServer.particles_collision_height_field_update`.
             *
             * Generated from Godot docs: GPUParticlesCollisionHeightField3D.UPDATE_MODE_WHEN_MOVED
             */
            val WHEN_MOVED: UpdateMode get() = UpdateMode(0L)
            /**
             * Update the heightmap every frame. This has a significant performance cost. This update should
             * only be used when geometry that particles can collide with changes significantly during
             * gameplay.
             *
             * Generated from Godot docs: GPUParticlesCollisionHeightField3D.UPDATE_MODE_ALWAYS
             */
            val ALWAYS: UpdateMode get() = UpdateMode(1L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): GPUParticlesCollisionHeightField3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): GPUParticlesCollisionHeightField3D? =
            if (handle.address() == 0L) null else GPUParticlesCollisionHeightField3D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_SIZE_HASH = 3460891852L
        @JvmField
        val setSizeBind =
            ObjectCalls.getMethodBind("GPUParticlesCollisionHeightField3D", "set_size", SET_SIZE_HASH)

        private const val GET_SIZE_HASH = 3360562783L
        @JvmField
        val getSizeBind =
            ObjectCalls.getMethodBind("GPUParticlesCollisionHeightField3D", "get_size", GET_SIZE_HASH)

        private const val SET_RESOLUTION_HASH = 1009996517L
        @JvmField
        val setResolutionBind =
            ObjectCalls.getMethodBind("GPUParticlesCollisionHeightField3D", "set_resolution", SET_RESOLUTION_HASH)

        private const val GET_RESOLUTION_HASH = 1156065644L
        @JvmField
        val getResolutionBind =
            ObjectCalls.getMethodBind("GPUParticlesCollisionHeightField3D", "get_resolution", GET_RESOLUTION_HASH)

        private const val SET_UPDATE_MODE_HASH = 673680859L
        @JvmField
        val setUpdateModeBind =
            ObjectCalls.getMethodBind("GPUParticlesCollisionHeightField3D", "set_update_mode", SET_UPDATE_MODE_HASH)

        private const val GET_UPDATE_MODE_HASH = 1998141380L
        @JvmField
        val getUpdateModeBind =
            ObjectCalls.getMethodBind("GPUParticlesCollisionHeightField3D", "get_update_mode", GET_UPDATE_MODE_HASH)

        private const val SET_HEIGHTFIELD_MASK_HASH = 1286410249L
        @JvmField
        val setHeightfieldMaskBind =
            ObjectCalls.getMethodBind("GPUParticlesCollisionHeightField3D", "set_heightfield_mask", SET_HEIGHTFIELD_MASK_HASH)

        private const val GET_HEIGHTFIELD_MASK_HASH = 3905245786L
        @JvmField
        val getHeightfieldMaskBind =
            ObjectCalls.getMethodBind("GPUParticlesCollisionHeightField3D", "get_heightfield_mask", GET_HEIGHTFIELD_MASK_HASH)

        private const val SET_HEIGHTFIELD_MASK_VALUE_HASH = 300928843L
        @JvmField
        val setHeightfieldMaskValueBind =
            ObjectCalls.getMethodBind("GPUParticlesCollisionHeightField3D", "set_heightfield_mask_value", SET_HEIGHTFIELD_MASK_VALUE_HASH)

        private const val GET_HEIGHTFIELD_MASK_VALUE_HASH = 1116898809L
        @JvmField
        val getHeightfieldMaskValueBind =
            ObjectCalls.getMethodBind("GPUParticlesCollisionHeightField3D", "get_heightfield_mask_value", GET_HEIGHTFIELD_MASK_VALUE_HASH)

        private const val SET_FOLLOW_CAMERA_ENABLED_HASH = 2586408642L
        @JvmField
        val setFollowCameraEnabledBind =
            ObjectCalls.getMethodBind("GPUParticlesCollisionHeightField3D", "set_follow_camera_enabled", SET_FOLLOW_CAMERA_ENABLED_HASH)

        private const val IS_FOLLOW_CAMERA_ENABLED_HASH = 36873697L
        @JvmField
        val isFollowCameraEnabledBind =
            ObjectCalls.getMethodBind("GPUParticlesCollisionHeightField3D", "is_follow_camera_enabled", IS_FOLLOW_CAMERA_ENABLED_HASH)
    }
}
