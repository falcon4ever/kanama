package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Vector3

/**
 * Captures its surroundings to create fast, accurate reflections from a given point.
 *
 * Generated from Godot docs: ReflectionProbe
 */
class ReflectionProbe(handle: GodotHandle) : VisualInstance3D(handle) {
    var updateMode: ReflectionProbe.UpdateMode
        @JvmName("updateModeProperty")
        get() = getUpdateMode()
        @JvmName("setUpdateModeProperty")
        set(value) = setUpdateMode(value)

    var intensity: Double
        @JvmName("intensityProperty")
        get() = getIntensity()
        @JvmName("setIntensityProperty")
        set(value) = setIntensity(value)

    var blendDistance: Double
        @JvmName("blendDistanceProperty")
        get() = getBlendDistance()
        @JvmName("setBlendDistanceProperty")
        set(value) = setBlendDistance(value)

    var maxDistance: Double
        @JvmName("maxDistanceProperty")
        get() = getMaxDistance()
        @JvmName("setMaxDistanceProperty")
        set(value) = setMaxDistance(value)

    var size: Vector3
        @JvmName("sizeProperty")
        get() = getSize()
        @JvmName("setSizeProperty")
        set(value) = setSize(value)

    var originOffset: Vector3
        @JvmName("originOffsetProperty")
        get() = getOriginOffset()
        @JvmName("setOriginOffsetProperty")
        set(value) = setOriginOffset(value)

    var boxProjection: Boolean
        @JvmName("boxProjectionProperty")
        get() = isBoxProjectionEnabled()
        @JvmName("setBoxProjectionProperty")
        set(value) = setEnableBoxProjection(value)

    var interior: Boolean
        @JvmName("interiorProperty")
        get() = isSetAsInterior()
        @JvmName("setInteriorProperty")
        set(value) = setAsInterior(value)

    var enableShadows: Boolean
        @JvmName("enableShadowsProperty")
        get() = areShadowsEnabled()
        @JvmName("setEnableShadowsProperty")
        set(value) = setEnableShadows(value)

    var cullMask: Long
        @JvmName("cullMaskProperty")
        get() = getCullMask()
        @JvmName("setCullMaskProperty")
        set(value) = setCullMask(value)

    var reflectionMask: Long
        @JvmName("reflectionMaskProperty")
        get() = getReflectionMask()
        @JvmName("setReflectionMaskProperty")
        set(value) = setReflectionMask(value)

    var meshLodThreshold: Double
        @JvmName("meshLodThresholdProperty")
        get() = getMeshLodThreshold()
        @JvmName("setMeshLodThresholdProperty")
        set(value) = setMeshLodThreshold(value)

    var ambientMode: ReflectionProbe.AmbientMode
        @JvmName("ambientModeProperty")
        get() = getAmbientMode()
        @JvmName("setAmbientModeProperty")
        set(value) = setAmbientMode(value)

    var ambientColor: Color
        @JvmName("ambientColorProperty")
        get() = getAmbientColor()
        @JvmName("setAmbientColorProperty")
        set(value) = setAmbientColor(value)

    var ambientColorEnergy: Double
        @JvmName("ambientColorEnergyProperty")
        get() = getAmbientColorEnergy()
        @JvmName("setAmbientColorEnergyProperty")
        set(value) = setAmbientColorEnergy(value)

    /**
     * Defines the reflection intensity. Intensity modulates the strength of the reflection.
     *
     * Generated from Godot docs: ReflectionProbe.set_intensity
     */
    fun setIntensity(intensity: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setIntensityBind, segment, intensity)
    }

    /**
     * Defines the reflection intensity. Intensity modulates the strength of the reflection.
     *
     * Generated from Godot docs: ReflectionProbe.get_intensity
     */
    fun getIntensity(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getIntensityBind, segment)
    }

    /**
     * Defines the distance in meters over which a probe blends into the scene.
     *
     * Generated from Godot docs: ReflectionProbe.set_blend_distance
     */
    fun setBlendDistance(blendDistance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setBlendDistanceBind, segment, blendDistance)
    }

    /**
     * Defines the distance in meters over which a probe blends into the scene.
     *
     * Generated from Godot docs: ReflectionProbe.get_blend_distance
     */
    fun getBlendDistance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getBlendDistanceBind, segment)
    }

    /**
     * The ambient color to use within the `ReflectionProbe`'s box defined by its `size`. The ambient
     * color will smoothly blend with other `ReflectionProbe`s and the rest of the scene (outside the
     * `ReflectionProbe`'s box defined by its `size`).
     *
     * Generated from Godot docs: ReflectionProbe.set_ambient_mode
     */
    fun setAmbientMode(ambient: ReflectionProbe.AmbientMode) {
        ObjectCalls.ptrcallWithLongArg(Binds.setAmbientModeBind, segment, ambient.value)
    }

    /**
     * The ambient color to use within the `ReflectionProbe`'s box defined by its `size`. The ambient
     * color will smoothly blend with other `ReflectionProbe`s and the rest of the scene (outside the
     * `ReflectionProbe`'s box defined by its `size`).
     *
     * Generated from Godot docs: ReflectionProbe.get_ambient_mode
     */
    fun getAmbientMode(): ReflectionProbe.AmbientMode {
        return ReflectionProbe.AmbientMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getAmbientModeBind, segment))
    }

    /**
     * The custom ambient color to use within the `ReflectionProbe`'s box defined by its `size`. Only
     * effective if `ambient_mode` is `AmbientMode.COLOR`.
     *
     * Generated from Godot docs: ReflectionProbe.set_ambient_color
     */
    fun setAmbientColor(ambient: Color) {
        ObjectCalls.ptrcallWithColorArg(Binds.setAmbientColorBind, segment, ambient)
    }

    /**
     * The custom ambient color to use within the `ReflectionProbe`'s box defined by its `size`. Only
     * effective if `ambient_mode` is `AmbientMode.COLOR`.
     *
     * Generated from Godot docs: ReflectionProbe.get_ambient_color
     */
    fun getAmbientColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(Binds.getAmbientColorBind, segment)
    }

    /**
     * The custom ambient color energy to use within the `ReflectionProbe`'s box defined by its `size`.
     * Only effective if `ambient_mode` is `AmbientMode.COLOR`.
     *
     * Generated from Godot docs: ReflectionProbe.set_ambient_color_energy
     */
    fun setAmbientColorEnergy(ambientEnergy: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setAmbientColorEnergyBind, segment, ambientEnergy)
    }

    /**
     * The custom ambient color energy to use within the `ReflectionProbe`'s box defined by its `size`.
     * Only effective if `ambient_mode` is `AmbientMode.COLOR`.
     *
     * Generated from Godot docs: ReflectionProbe.get_ambient_color_energy
     */
    fun getAmbientColorEnergy(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getAmbientColorEnergyBind, segment)
    }

    /**
     * The maximum distance away from the `ReflectionProbe` an object can be before it is culled.
     * Decrease this to improve performance, especially when using the `UpdateMode.ALWAYS`
     * `update_mode`. Note: The maximum reflection distance is always at least equal to the probe's
     * extents. This means that decreasing `max_distance` will not always cull objects from
     * reflections, especially if the reflection probe's box defined by its `size` is already large.
     *
     * Generated from Godot docs: ReflectionProbe.set_max_distance
     */
    fun setMaxDistance(maxDistance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setMaxDistanceBind, segment, maxDistance)
    }

    /**
     * The maximum distance away from the `ReflectionProbe` an object can be before it is culled.
     * Decrease this to improve performance, especially when using the `UpdateMode.ALWAYS`
     * `update_mode`. Note: The maximum reflection distance is always at least equal to the probe's
     * extents. This means that decreasing `max_distance` will not always cull objects from
     * reflections, especially if the reflection probe's box defined by its `size` is already large.
     *
     * Generated from Godot docs: ReflectionProbe.get_max_distance
     */
    fun getMaxDistance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getMaxDistanceBind, segment)
    }

    /**
     * The automatic LOD bias to use for meshes rendered within the `ReflectionProbe` (this is analog
     * to `Viewport.mesh_lod_threshold`). Higher values will use less detailed versions of meshes that
     * have LOD variations generated. If set to `0.0`, automatic LOD is disabled. Increase
     * `mesh_lod_threshold` to improve performance at the cost of geometry detail, especially when
     * using the `UpdateMode.ALWAYS` `update_mode`. Note: `mesh_lod_threshold` does not affect
     * `GeometryInstance3D` visibility ranges (also known as "manual" LOD or hierarchical LOD).
     *
     * Generated from Godot docs: ReflectionProbe.set_mesh_lod_threshold
     */
    fun setMeshLodThreshold(ratio: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setMeshLodThresholdBind, segment, ratio)
    }

    /**
     * The automatic LOD bias to use for meshes rendered within the `ReflectionProbe` (this is analog
     * to `Viewport.mesh_lod_threshold`). Higher values will use less detailed versions of meshes that
     * have LOD variations generated. If set to `0.0`, automatic LOD is disabled. Increase
     * `mesh_lod_threshold` to improve performance at the cost of geometry detail, especially when
     * using the `UpdateMode.ALWAYS` `update_mode`. Note: `mesh_lod_threshold` does not affect
     * `GeometryInstance3D` visibility ranges (also known as "manual" LOD or hierarchical LOD).
     *
     * Generated from Godot docs: ReflectionProbe.get_mesh_lod_threshold
     */
    fun getMeshLodThreshold(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getMeshLodThresholdBind, segment)
    }

    /**
     * The size of the reflection probe. The larger the size, the more space covered by the probe,
     * which will lower the perceived resolution. It is best to keep the size only as large as you need
     * it. Note: To better fit areas that are not aligned to the grid, you can rotate the
     * `ReflectionProbe` node.
     *
     * Generated from Godot docs: ReflectionProbe.set_size
     */
    fun setSize(size: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(Binds.setSizeBind, segment, size)
    }

    /**
     * The size of the reflection probe. The larger the size, the more space covered by the probe,
     * which will lower the perceived resolution. It is best to keep the size only as large as you need
     * it. Note: To better fit areas that are not aligned to the grid, you can rotate the
     * `ReflectionProbe` node.
     *
     * Generated from Godot docs: ReflectionProbe.get_size
     */
    fun getSize(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getSizeBind, segment)
    }

    /**
     * Sets the origin offset to be used when this `ReflectionProbe` is in `box_projection` mode. This
     * can be set to a non-zero value to ensure a reflection fits a rectangle-shaped room, while
     * reducing the number of objects that "get in the way" of the reflection.
     *
     * Generated from Godot docs: ReflectionProbe.set_origin_offset
     */
    fun setOriginOffset(originOffset: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(Binds.setOriginOffsetBind, segment, originOffset)
    }

    /**
     * Sets the origin offset to be used when this `ReflectionProbe` is in `box_projection` mode. This
     * can be set to a non-zero value to ensure a reflection fits a rectangle-shaped room, while
     * reducing the number of objects that "get in the way" of the reflection.
     *
     * Generated from Godot docs: ReflectionProbe.get_origin_offset
     */
    fun getOriginOffset(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getOriginOffsetBind, segment)
    }

    /**
     * If `true`, reflections will ignore sky contribution.
     *
     * Generated from Godot docs: ReflectionProbe.set_as_interior
     */
    fun setAsInterior(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setAsInteriorBind, segment, enable)
    }

    /**
     * If `true`, reflections will ignore sky contribution.
     *
     * Generated from Godot docs: ReflectionProbe.is_set_as_interior
     */
    fun isSetAsInterior(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isSetAsInteriorBind, segment)
    }

    /**
     * If `true`, enables box projection. This makes reflections look more correct in rectangle-shaped
     * rooms by offsetting the reflection center depending on the camera's location. Note: To better
     * fit rectangle-shaped rooms that are not aligned to the grid, you can rotate the
     * `ReflectionProbe` node.
     *
     * Generated from Godot docs: ReflectionProbe.set_enable_box_projection
     */
    fun setEnableBoxProjection(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setEnableBoxProjectionBind, segment, enable)
    }

    /**
     * If `true`, enables box projection. This makes reflections look more correct in rectangle-shaped
     * rooms by offsetting the reflection center depending on the camera's location. Note: To better
     * fit rectangle-shaped rooms that are not aligned to the grid, you can rotate the
     * `ReflectionProbe` node.
     *
     * Generated from Godot docs: ReflectionProbe.is_box_projection_enabled
     */
    fun isBoxProjectionEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isBoxProjectionEnabledBind, segment)
    }

    /**
     * If `true`, computes shadows in the reflection probe. This makes the reflection probe slower to
     * render; you may want to disable this if using the `UpdateMode.ALWAYS` `update_mode`.
     *
     * Generated from Godot docs: ReflectionProbe.set_enable_shadows
     */
    fun setEnableShadows(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setEnableShadowsBind, segment, enable)
    }

    /**
     * If `true`, computes shadows in the reflection probe. This makes the reflection probe slower to
     * render; you may want to disable this if using the `UpdateMode.ALWAYS` `update_mode`.
     *
     * Generated from Godot docs: ReflectionProbe.are_shadows_enabled
     */
    fun areShadowsEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.areShadowsEnabledBind, segment)
    }

    /**
     * Sets the cull mask which determines what objects are drawn by this probe. Every
     * `VisualInstance3D` with a layer included in this cull mask will be rendered by the probe. It is
     * best to only include large objects which are likely to take up a lot of space in the reflection
     * in order to save on rendering cost. This can also be used to prevent an object from reflecting
     * upon itself (for instance, a `ReflectionProbe` centered on a vehicle).
     *
     * Generated from Godot docs: ReflectionProbe.set_cull_mask
     */
    fun setCullMask(layers: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setCullMaskBind, segment, layers)
    }

    /**
     * Sets the cull mask which determines what objects are drawn by this probe. Every
     * `VisualInstance3D` with a layer included in this cull mask will be rendered by the probe. It is
     * best to only include large objects which are likely to take up a lot of space in the reflection
     * in order to save on rendering cost. This can also be used to prevent an object from reflecting
     * upon itself (for instance, a `ReflectionProbe` centered on a vehicle).
     *
     * Generated from Godot docs: ReflectionProbe.get_cull_mask
     */
    fun getCullMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getCullMaskBind, segment)
    }

    /**
     * Sets the reflection mask which determines what objects have reflections applied from this probe.
     * Every `VisualInstance3D` with a layer included in this reflection mask will have reflections
     * applied from this probe. See also `cull_mask`, which can be used to exclude objects from
     * appearing in the reflection while still making them affected by the `ReflectionProbe`.
     *
     * Generated from Godot docs: ReflectionProbe.set_reflection_mask
     */
    fun setReflectionMask(layers: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setReflectionMaskBind, segment, layers)
    }

    /**
     * Sets the reflection mask which determines what objects have reflections applied from this probe.
     * Every `VisualInstance3D` with a layer included in this reflection mask will have reflections
     * applied from this probe. See also `cull_mask`, which can be used to exclude objects from
     * appearing in the reflection while still making them affected by the `ReflectionProbe`.
     *
     * Generated from Godot docs: ReflectionProbe.get_reflection_mask
     */
    fun getReflectionMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getReflectionMaskBind, segment)
    }

    /**
     * Sets how frequently the `ReflectionProbe` is updated. Can be `UpdateMode.ONCE` or
     * `UpdateMode.ALWAYS`.
     *
     * Generated from Godot docs: ReflectionProbe.set_update_mode
     */
    fun setUpdateMode(mode: ReflectionProbe.UpdateMode) {
        ObjectCalls.ptrcallWithLongArg(Binds.setUpdateModeBind, segment, mode.value)
    }

    /**
     * Sets how frequently the `ReflectionProbe` is updated. Can be `UpdateMode.ONCE` or
     * `UpdateMode.ALWAYS`.
     *
     * Generated from Godot docs: ReflectionProbe.get_update_mode
     */
    fun getUpdateMode(): ReflectionProbe.UpdateMode {
        return ReflectionProbe.UpdateMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getUpdateModeBind, segment))
    }

    /**
     * Godot's `ReflectionProbe.UpdateMode` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`ReflectionProbe.UpdateMode.<NAME>`).
     *
     * Generated from Godot docs: ReflectionProbe.UpdateMode
     */
    @JvmInline
    value class UpdateMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Update the probe once on the next frame (recommended for most objects). The corresponding
             * radiance map will be generated over the following six frames. This takes more time to update
             * than `UpdateMode.ALWAYS`, but it has a lower performance cost and can result in higher-quality
             * reflections. The ReflectionProbe is updated when its transform changes, but not when nearby
             * geometry changes. You can force a `ReflectionProbe` update by moving the `ReflectionProbe`
             * slightly in any direction.
             *
             * Generated from Godot docs: ReflectionProbe.UPDATE_ONCE
             */
            val ONCE: UpdateMode get() = UpdateMode(0L)
            /**
             * Update the probe every frame. This provides better results for fast-moving dynamic objects (such
             * as cars). However, it has a significant performance cost. Due to the cost, it's recommended to
             * only use one ReflectionProbe with `UpdateMode.ALWAYS` at most per scene. For all other use
             * cases, use `UpdateMode.ONCE`.
             *
             * Generated from Godot docs: ReflectionProbe.UPDATE_ALWAYS
             */
            val ALWAYS: UpdateMode get() = UpdateMode(1L)
        }
    }

    /**
     * Godot's `ReflectionProbe.AmbientMode` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`ReflectionProbe.AmbientMode.<NAME>`).
     *
     * Generated from Godot docs: ReflectionProbe.AmbientMode
     */
    @JvmInline
    value class AmbientMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Do not apply any ambient lighting inside the `ReflectionProbe`'s box defined by its `size`.
             *
             * Generated from Godot docs: ReflectionProbe.AMBIENT_DISABLED
             */
            val DISABLED: AmbientMode get() = AmbientMode(0L)
            /**
             * Apply automatically-sourced environment lighting inside the `ReflectionProbe`'s box defined by
             * its `size`.
             *
             * Generated from Godot docs: ReflectionProbe.AMBIENT_ENVIRONMENT
             */
            val ENVIRONMENT: AmbientMode get() = AmbientMode(1L)
            /**
             * Apply custom ambient lighting inside the `ReflectionProbe`'s box defined by its `size`. See
             * `ambient_color` and `ambient_color_energy`.
             *
             * Generated from Godot docs: ReflectionProbe.AMBIENT_COLOR
             */
            val COLOR: AmbientMode get() = AmbientMode(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ReflectionProbe? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): ReflectionProbe? =
            if (handle.address() == 0L) null else ReflectionProbe(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_INTENSITY_HASH = 373806689L
        @JvmField
        val setIntensityBind =
            ObjectCalls.getMethodBind("ReflectionProbe", "set_intensity", SET_INTENSITY_HASH)

        private const val GET_INTENSITY_HASH = 1740695150L
        @JvmField
        val getIntensityBind =
            ObjectCalls.getMethodBind("ReflectionProbe", "get_intensity", GET_INTENSITY_HASH)

        private const val SET_BLEND_DISTANCE_HASH = 373806689L
        @JvmField
        val setBlendDistanceBind =
            ObjectCalls.getMethodBind("ReflectionProbe", "set_blend_distance", SET_BLEND_DISTANCE_HASH)

        private const val GET_BLEND_DISTANCE_HASH = 1740695150L
        @JvmField
        val getBlendDistanceBind =
            ObjectCalls.getMethodBind("ReflectionProbe", "get_blend_distance", GET_BLEND_DISTANCE_HASH)

        private const val SET_AMBIENT_MODE_HASH = 1748981278L
        @JvmField
        val setAmbientModeBind =
            ObjectCalls.getMethodBind("ReflectionProbe", "set_ambient_mode", SET_AMBIENT_MODE_HASH)

        private const val GET_AMBIENT_MODE_HASH = 1014607621L
        @JvmField
        val getAmbientModeBind =
            ObjectCalls.getMethodBind("ReflectionProbe", "get_ambient_mode", GET_AMBIENT_MODE_HASH)

        private const val SET_AMBIENT_COLOR_HASH = 2920490490L
        @JvmField
        val setAmbientColorBind =
            ObjectCalls.getMethodBind("ReflectionProbe", "set_ambient_color", SET_AMBIENT_COLOR_HASH)

        private const val GET_AMBIENT_COLOR_HASH = 3444240500L
        @JvmField
        val getAmbientColorBind =
            ObjectCalls.getMethodBind("ReflectionProbe", "get_ambient_color", GET_AMBIENT_COLOR_HASH)

        private const val SET_AMBIENT_COLOR_ENERGY_HASH = 373806689L
        @JvmField
        val setAmbientColorEnergyBind =
            ObjectCalls.getMethodBind("ReflectionProbe", "set_ambient_color_energy", SET_AMBIENT_COLOR_ENERGY_HASH)

        private const val GET_AMBIENT_COLOR_ENERGY_HASH = 1740695150L
        @JvmField
        val getAmbientColorEnergyBind =
            ObjectCalls.getMethodBind("ReflectionProbe", "get_ambient_color_energy", GET_AMBIENT_COLOR_ENERGY_HASH)

        private const val SET_MAX_DISTANCE_HASH = 373806689L
        @JvmField
        val setMaxDistanceBind =
            ObjectCalls.getMethodBind("ReflectionProbe", "set_max_distance", SET_MAX_DISTANCE_HASH)

        private const val GET_MAX_DISTANCE_HASH = 1740695150L
        @JvmField
        val getMaxDistanceBind =
            ObjectCalls.getMethodBind("ReflectionProbe", "get_max_distance", GET_MAX_DISTANCE_HASH)

        private const val SET_MESH_LOD_THRESHOLD_HASH = 373806689L
        @JvmField
        val setMeshLodThresholdBind =
            ObjectCalls.getMethodBind("ReflectionProbe", "set_mesh_lod_threshold", SET_MESH_LOD_THRESHOLD_HASH)

        private const val GET_MESH_LOD_THRESHOLD_HASH = 1740695150L
        @JvmField
        val getMeshLodThresholdBind =
            ObjectCalls.getMethodBind("ReflectionProbe", "get_mesh_lod_threshold", GET_MESH_LOD_THRESHOLD_HASH)

        private const val SET_SIZE_HASH = 3460891852L
        @JvmField
        val setSizeBind =
            ObjectCalls.getMethodBind("ReflectionProbe", "set_size", SET_SIZE_HASH)

        private const val GET_SIZE_HASH = 3360562783L
        @JvmField
        val getSizeBind =
            ObjectCalls.getMethodBind("ReflectionProbe", "get_size", GET_SIZE_HASH)

        private const val SET_ORIGIN_OFFSET_HASH = 3460891852L
        @JvmField
        val setOriginOffsetBind =
            ObjectCalls.getMethodBind("ReflectionProbe", "set_origin_offset", SET_ORIGIN_OFFSET_HASH)

        private const val GET_ORIGIN_OFFSET_HASH = 3360562783L
        @JvmField
        val getOriginOffsetBind =
            ObjectCalls.getMethodBind("ReflectionProbe", "get_origin_offset", GET_ORIGIN_OFFSET_HASH)

        private const val SET_AS_INTERIOR_HASH = 2586408642L
        @JvmField
        val setAsInteriorBind =
            ObjectCalls.getMethodBind("ReflectionProbe", "set_as_interior", SET_AS_INTERIOR_HASH)

        private const val IS_SET_AS_INTERIOR_HASH = 36873697L
        @JvmField
        val isSetAsInteriorBind =
            ObjectCalls.getMethodBind("ReflectionProbe", "is_set_as_interior", IS_SET_AS_INTERIOR_HASH)

        private const val SET_ENABLE_BOX_PROJECTION_HASH = 2586408642L
        @JvmField
        val setEnableBoxProjectionBind =
            ObjectCalls.getMethodBind("ReflectionProbe", "set_enable_box_projection", SET_ENABLE_BOX_PROJECTION_HASH)

        private const val IS_BOX_PROJECTION_ENABLED_HASH = 36873697L
        @JvmField
        val isBoxProjectionEnabledBind =
            ObjectCalls.getMethodBind("ReflectionProbe", "is_box_projection_enabled", IS_BOX_PROJECTION_ENABLED_HASH)

        private const val SET_ENABLE_SHADOWS_HASH = 2586408642L
        @JvmField
        val setEnableShadowsBind =
            ObjectCalls.getMethodBind("ReflectionProbe", "set_enable_shadows", SET_ENABLE_SHADOWS_HASH)

        private const val ARE_SHADOWS_ENABLED_HASH = 36873697L
        @JvmField
        val areShadowsEnabledBind =
            ObjectCalls.getMethodBind("ReflectionProbe", "are_shadows_enabled", ARE_SHADOWS_ENABLED_HASH)

        private const val SET_CULL_MASK_HASH = 1286410249L
        @JvmField
        val setCullMaskBind =
            ObjectCalls.getMethodBind("ReflectionProbe", "set_cull_mask", SET_CULL_MASK_HASH)

        private const val GET_CULL_MASK_HASH = 3905245786L
        @JvmField
        val getCullMaskBind =
            ObjectCalls.getMethodBind("ReflectionProbe", "get_cull_mask", GET_CULL_MASK_HASH)

        private const val SET_REFLECTION_MASK_HASH = 1286410249L
        @JvmField
        val setReflectionMaskBind =
            ObjectCalls.getMethodBind("ReflectionProbe", "set_reflection_mask", SET_REFLECTION_MASK_HASH)

        private const val GET_REFLECTION_MASK_HASH = 3905245786L
        @JvmField
        val getReflectionMaskBind =
            ObjectCalls.getMethodBind("ReflectionProbe", "get_reflection_mask", GET_REFLECTION_MASK_HASH)

        private const val SET_UPDATE_MODE_HASH = 4090221187L
        @JvmField
        val setUpdateModeBind =
            ObjectCalls.getMethodBind("ReflectionProbe", "set_update_mode", SET_UPDATE_MODE_HASH)

        private const val GET_UPDATE_MODE_HASH = 2367550552L
        @JvmField
        val getUpdateModeBind =
            ObjectCalls.getMethodBind("ReflectionProbe", "get_update_mode", GET_UPDATE_MODE_HASH)
    }
}
