package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Transform2D
import net.multigesture.kanama.types.Transform3D

/**
 * Provides high-performance drawing of a mesh multiple times using GPU instancing.
 *
 * Generated from Godot docs: MultiMesh
 */
class MultiMesh(handle: MemorySegment) : Resource(handle) {
    var transformFormat: Long
        @JvmName("transformFormatProperty")
        get() = getTransformFormat()
        @JvmName("setTransformFormatProperty")
        set(value) = setTransformFormat(value)

    var useColors: Boolean
        @JvmName("useColorsProperty")
        get() = isUsingColors()
        @JvmName("setUseColorsProperty")
        set(value) = setUseColors(value)

    var useCustomData: Boolean
        @JvmName("useCustomDataProperty")
        get() = isUsingCustomData()
        @JvmName("setUseCustomDataProperty")
        set(value) = setUseCustomData(value)

    var customAabb: AABB
        @JvmName("customAabbProperty")
        get() = getCustomAabb()
        @JvmName("setCustomAabbProperty")
        set(value) = setCustomAabb(value)

    var instanceCount: Int
        @JvmName("instanceCountProperty")
        get() = getInstanceCount()
        @JvmName("setInstanceCountProperty")
        set(value) = setInstanceCount(value)

    var visibleInstanceCount: Int
        @JvmName("visibleInstanceCountProperty")
        get() = getVisibleInstanceCount()
        @JvmName("setVisibleInstanceCountProperty")
        set(value) = setVisibleInstanceCount(value)

    var mesh: Mesh?
        @JvmName("meshProperty")
        get() = getMesh()
        @JvmName("setMeshProperty")
        set(value) = setMesh(value)

    var buffer: List<Float>
        @JvmName("bufferProperty")
        get() = getBuffer()
        @JvmName("setBufferProperty")
        set(value) = setBuffer(value)

    var physicsInterpolationQuality: Long
        @JvmName("physicsInterpolationQualityProperty")
        get() = getPhysicsInterpolationQuality()
        @JvmName("setPhysicsInterpolationQualityProperty")
        set(value) = setPhysicsInterpolationQuality(value)

    /**
     * `Mesh` resource to be instanced. The looks of the individual instances can be modified using
     * `set_instance_color` and `set_instance_custom_data`.
     *
     * Generated from Godot docs: MultiMesh.set_mesh
     */
    fun setMesh(mesh: Mesh?) {
        ObjectCalls.ptrcallWithObjectArgs(setMeshBind, handle, listOf(mesh?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * `Mesh` resource to be instanced. The looks of the individual instances can be modified using
     * `set_instance_color` and `set_instance_custom_data`.
     *
     * Generated from Godot docs: MultiMesh.get_mesh
     */
    fun getMesh(): Mesh? {
        return Mesh.wrap(ObjectCalls.ptrcallNoArgsRetObject(getMeshBind, handle))
    }

    /**
     * If `true`, the `MultiMesh` will use color data (see `set_instance_color`). Can only be set when
     * `instance_count` is `0` or less. This means that you need to call this method before setting the
     * instance count, or temporarily reset it to `0`.
     *
     * Generated from Godot docs: MultiMesh.set_use_colors
     */
    fun setUseColors(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseColorsBind, handle, enable)
    }

    /**
     * If `true`, the `MultiMesh` will use color data (see `set_instance_color`). Can only be set when
     * `instance_count` is `0` or less. This means that you need to call this method before setting the
     * instance count, or temporarily reset it to `0`.
     *
     * Generated from Godot docs: MultiMesh.is_using_colors
     */
    fun isUsingColors(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingColorsBind, handle)
    }

    /**
     * If `true`, the `MultiMesh` will use custom data (see `set_instance_custom_data`). Can only be
     * set when `instance_count` is `0` or less. This means that you need to call this method before
     * setting the instance count, or temporarily reset it to `0`.
     *
     * Generated from Godot docs: MultiMesh.set_use_custom_data
     */
    fun setUseCustomData(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseCustomDataBind, handle, enable)
    }

    /**
     * If `true`, the `MultiMesh` will use custom data (see `set_instance_custom_data`). Can only be
     * set when `instance_count` is `0` or less. This means that you need to call this method before
     * setting the instance count, or temporarily reset it to `0`.
     *
     * Generated from Godot docs: MultiMesh.is_using_custom_data
     */
    fun isUsingCustomData(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingCustomDataBind, handle)
    }

    /**
     * Format of transform used to transform mesh, either 2D or 3D.
     *
     * Generated from Godot docs: MultiMesh.set_transform_format
     */
    fun setTransformFormat(format: Long) {
        ObjectCalls.ptrcallWithLongArg(setTransformFormatBind, handle, format)
    }

    /**
     * Format of transform used to transform mesh, either 2D or 3D.
     *
     * Generated from Godot docs: MultiMesh.get_transform_format
     */
    fun getTransformFormat(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTransformFormatBind, handle)
    }

    /**
     * Number of instances that will get drawn. This clears and (re)sizes the buffers. Setting data
     * format or flags afterwards will have no effect. By default, all instances are drawn but you can
     * limit this with `visible_instance_count`.
     *
     * Generated from Godot docs: MultiMesh.set_instance_count
     */
    fun setInstanceCount(count: Int) {
        ObjectCalls.ptrcallWithIntArg(setInstanceCountBind, handle, count)
    }

    /**
     * Number of instances that will get drawn. This clears and (re)sizes the buffers. Setting data
     * format or flags afterwards will have no effect. By default, all instances are drawn but you can
     * limit this with `visible_instance_count`.
     *
     * Generated from Godot docs: MultiMesh.get_instance_count
     */
    fun getInstanceCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getInstanceCountBind, handle)
    }

    /**
     * Limits the number of instances drawn, -1 draws all instances. Changing this does not change the
     * sizes of the buffers.
     *
     * Generated from Godot docs: MultiMesh.set_visible_instance_count
     */
    fun setVisibleInstanceCount(count: Int) {
        ObjectCalls.ptrcallWithIntArg(setVisibleInstanceCountBind, handle, count)
    }

    /**
     * Limits the number of instances drawn, -1 draws all instances. Changing this does not change the
     * sizes of the buffers.
     *
     * Generated from Godot docs: MultiMesh.get_visible_instance_count
     */
    fun getVisibleInstanceCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getVisibleInstanceCountBind, handle)
    }

    /**
     * Choose whether to use an interpolation method that favors speed or quality. When using low
     * physics tick rates (typically below 20) or high rates of object rotation, you may get better
     * results from the high quality setting. Note: Fast quality does not equate to low quality. Except
     * in the special cases mentioned above, the quality should be comparable to high quality.
     *
     * Generated from Godot docs: MultiMesh.set_physics_interpolation_quality
     */
    fun setPhysicsInterpolationQuality(quality: Long) {
        ObjectCalls.ptrcallWithLongArg(setPhysicsInterpolationQualityBind, handle, quality)
    }

    /**
     * Choose whether to use an interpolation method that favors speed or quality. When using low
     * physics tick rates (typically below 20) or high rates of object rotation, you may get better
     * results from the high quality setting. Note: Fast quality does not equate to low quality. Except
     * in the special cases mentioned above, the quality should be comparable to high quality.
     *
     * Generated from Godot docs: MultiMesh.get_physics_interpolation_quality
     */
    fun getPhysicsInterpolationQuality(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getPhysicsInterpolationQualityBind, handle)
    }

    /**
     * Sets the `Transform3D` for a specific instance.
     *
     * Generated from Godot docs: MultiMesh.set_instance_transform
     */
    fun setInstanceTransform(instance: Int, transform: Transform3D) {
        ObjectCalls.ptrcallWithIntAndTransform3DArg(setInstanceTransformBind, handle, instance, transform)
    }

    /**
     * Sets the `Transform2D` for a specific instance.
     *
     * Generated from Godot docs: MultiMesh.set_instance_transform_2d
     */
    fun setInstanceTransform2d(instance: Int, transform: Transform2D) {
        ObjectCalls.ptrcallWithIntAndTransform2DArg(setInstanceTransform2dBind, handle, instance, transform)
    }

    /**
     * Returns the `Transform3D` of a specific instance.
     *
     * Generated from Godot docs: MultiMesh.get_instance_transform
     */
    fun getInstanceTransform(instance: Int): Transform3D {
        return ObjectCalls.ptrcallWithIntArgRetTransform3D(getInstanceTransformBind, handle, instance)
    }

    /**
     * Returns the `Transform2D` of a specific instance.
     *
     * Generated from Godot docs: MultiMesh.get_instance_transform_2d
     */
    fun getInstanceTransform2d(instance: Int): Transform2D {
        return ObjectCalls.ptrcallWithIntArgRetTransform2D(getInstanceTransform2dBind, handle, instance)
    }

    /**
     * Sets the color of a specific instance by multiplying the mesh's existing vertex colors. This
     * allows for different color tinting per instance. Note: Each component is stored in 32 bits in
     * the Forward+ and Mobile rendering methods, but is packed into 16 bits in the Compatibility
     * rendering method. For the color to take effect, ensure that `use_colors` is `true` on the
     * `MultiMesh` and `BaseMaterial3D.vertex_color_use_as_albedo` is `true` on the material. If you
     * intend to set an absolute color instead of tinting, make sure the material's albedo color is set
     * to pure white (`Color(1, 1, 1)`).
     *
     * Generated from Godot docs: MultiMesh.set_instance_color
     */
    fun setInstanceColor(instance: Int, color: Color) {
        ObjectCalls.ptrcallWithIntAndColorArg(setInstanceColorBind, handle, instance, color)
    }

    /**
     * Gets a specific instance's color multiplier.
     *
     * Generated from Godot docs: MultiMesh.get_instance_color
     */
    fun getInstanceColor(instance: Int): Color {
        return ObjectCalls.ptrcallWithIntArgRetColor(getInstanceColorBind, handle, instance)
    }

    /**
     * Sets custom data for a specific instance. `custom_data` is a `Color` type only to contain 4
     * floating-point numbers. Note: Each number is stored in 32 bits in the Forward+ and Mobile
     * rendering methods, but is packed into 16 bits in the Compatibility rendering method. For the
     * custom data to be used, ensure that `use_custom_data` is `true`. This custom instance data has
     * to be manually accessed in your custom shader using `INSTANCE_CUSTOM`.
     *
     * Generated from Godot docs: MultiMesh.set_instance_custom_data
     */
    fun setInstanceCustomData(instance: Int, customData: Color) {
        ObjectCalls.ptrcallWithIntAndColorArg(setInstanceCustomDataBind, handle, instance, customData)
    }

    /**
     * Returns the custom data that has been set for a specific instance.
     *
     * Generated from Godot docs: MultiMesh.get_instance_custom_data
     */
    fun getInstanceCustomData(instance: Int): Color {
        return ObjectCalls.ptrcallWithIntArgRetColor(getInstanceCustomDataBind, handle, instance)
    }

    /**
     * When using physics interpolation, this function allows you to prevent interpolation on an
     * instance in the current physics tick. This allows you to move instances instantaneously, and
     * should usually be used when initially placing an instance such as a bullet to prevent graphical
     * glitches.
     *
     * Generated from Godot docs: MultiMesh.reset_instance_physics_interpolation
     */
    fun resetInstancePhysicsInterpolation(instance: Int) {
        ObjectCalls.ptrcallWithIntArg(resetInstancePhysicsInterpolationBind, handle, instance)
    }

    /**
     * When using physics interpolation, this function allows you to prevent interpolation for all
     * instances in the current physics tick. This allows you to move all instances instantaneously,
     * and should usually be used when initially placing instances to prevent graphical glitches.
     *
     * Generated from Godot docs: MultiMesh.reset_instances_physics_interpolation
     */
    fun resetInstancesPhysicsInterpolation() {
        ObjectCalls.ptrcallNoArgs(resetInstancesPhysicsInterpolationBind, handle)
    }

    /**
     * Custom AABB for this MultiMesh resource. Setting this manually prevents costly runtime AABB
     * recalculations.
     *
     * Generated from Godot docs: MultiMesh.set_custom_aabb
     */
    fun setCustomAabb(aabb: AABB) {
        ObjectCalls.ptrcallWithAABBArg(setCustomAabbBind, handle, aabb)
    }

    /**
     * Custom AABB for this MultiMesh resource. Setting this manually prevents costly runtime AABB
     * recalculations.
     *
     * Generated from Godot docs: MultiMesh.get_custom_aabb
     */
    fun getCustomAabb(): AABB {
        return ObjectCalls.ptrcallNoArgsRetAABB(getCustomAabbBind, handle)
    }

    /**
     * Returns the visibility axis-aligned bounding box in local space.
     *
     * Generated from Godot docs: MultiMesh.get_aabb
     */
    fun getAabb(): AABB {
        return ObjectCalls.ptrcallNoArgsRetAABB(getAabbBind, handle)
    }

    fun getBuffer(): List<Float> {
        return ObjectCalls.ptrcallNoArgsRetPackedFloat32List(getBufferBind, handle)
    }

    fun setBuffer(buffer: List<Float>) {
        ObjectCalls.ptrcallWithPackedFloat32ListArg(setBufferBind, handle, buffer)
    }

    /**
     * An alternative to setting the `buffer` property, which can be used with physics interpolation.
     * This method takes two arrays, and can set the data for the current and previous tick in one go.
     * The renderer will automatically interpolate the data at each frame. This is useful for
     * situations where the order of instances may change from physics tick to tick, such as particle
     * systems. When the order of instances is coherent, the simpler alternative of setting `buffer`
     * can still be used with interpolation.
     *
     * Generated from Godot docs: MultiMesh.set_buffer_interpolated
     */
    fun setBufferInterpolated(bufferCurr: List<Float>, bufferPrev: List<Float>) {
        ObjectCalls.ptrcallWithTwoPackedFloat32ListArgs(setBufferInterpolatedBind, handle, bufferCurr, bufferPrev)
    }

    companion object {
        const val TRANSFORM_2D: Long = 0L
        const val TRANSFORM_3D: Long = 1L
        const val INTERP_QUALITY_FAST: Long = 0L
        const val INTERP_QUALITY_HIGH: Long = 1L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): MultiMesh? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): MultiMesh? =
            if (handle.address() == 0L) null else MultiMesh(handle)

        private const val SET_MESH_HASH = 194775623L
        private val setMeshBind by lazy {
            ObjectCalls.getMethodBind("MultiMesh", "set_mesh", SET_MESH_HASH)
        }

        private const val GET_MESH_HASH = 1808005922L
        private val getMeshBind by lazy {
            ObjectCalls.getMethodBind("MultiMesh", "get_mesh", GET_MESH_HASH)
        }

        private const val SET_USE_COLORS_HASH = 2586408642L
        private val setUseColorsBind by lazy {
            ObjectCalls.getMethodBind("MultiMesh", "set_use_colors", SET_USE_COLORS_HASH)
        }

        private const val IS_USING_COLORS_HASH = 36873697L
        private val isUsingColorsBind by lazy {
            ObjectCalls.getMethodBind("MultiMesh", "is_using_colors", IS_USING_COLORS_HASH)
        }

        private const val SET_USE_CUSTOM_DATA_HASH = 2586408642L
        private val setUseCustomDataBind by lazy {
            ObjectCalls.getMethodBind("MultiMesh", "set_use_custom_data", SET_USE_CUSTOM_DATA_HASH)
        }

        private const val IS_USING_CUSTOM_DATA_HASH = 36873697L
        private val isUsingCustomDataBind by lazy {
            ObjectCalls.getMethodBind("MultiMesh", "is_using_custom_data", IS_USING_CUSTOM_DATA_HASH)
        }

        private const val SET_TRANSFORM_FORMAT_HASH = 2404750322L
        private val setTransformFormatBind by lazy {
            ObjectCalls.getMethodBind("MultiMesh", "set_transform_format", SET_TRANSFORM_FORMAT_HASH)
        }

        private const val GET_TRANSFORM_FORMAT_HASH = 2444156481L
        private val getTransformFormatBind by lazy {
            ObjectCalls.getMethodBind("MultiMesh", "get_transform_format", GET_TRANSFORM_FORMAT_HASH)
        }

        private const val SET_INSTANCE_COUNT_HASH = 1286410249L
        private val setInstanceCountBind by lazy {
            ObjectCalls.getMethodBind("MultiMesh", "set_instance_count", SET_INSTANCE_COUNT_HASH)
        }

        private const val GET_INSTANCE_COUNT_HASH = 3905245786L
        private val getInstanceCountBind by lazy {
            ObjectCalls.getMethodBind("MultiMesh", "get_instance_count", GET_INSTANCE_COUNT_HASH)
        }

        private const val SET_VISIBLE_INSTANCE_COUNT_HASH = 1286410249L
        private val setVisibleInstanceCountBind by lazy {
            ObjectCalls.getMethodBind("MultiMesh", "set_visible_instance_count", SET_VISIBLE_INSTANCE_COUNT_HASH)
        }

        private const val GET_VISIBLE_INSTANCE_COUNT_HASH = 3905245786L
        private val getVisibleInstanceCountBind by lazy {
            ObjectCalls.getMethodBind("MultiMesh", "get_visible_instance_count", GET_VISIBLE_INSTANCE_COUNT_HASH)
        }

        private const val SET_PHYSICS_INTERPOLATION_QUALITY_HASH = 1819488408L
        private val setPhysicsInterpolationQualityBind by lazy {
            ObjectCalls.getMethodBind("MultiMesh", "set_physics_interpolation_quality", SET_PHYSICS_INTERPOLATION_QUALITY_HASH)
        }

        private const val GET_PHYSICS_INTERPOLATION_QUALITY_HASH = 1465701882L
        private val getPhysicsInterpolationQualityBind by lazy {
            ObjectCalls.getMethodBind("MultiMesh", "get_physics_interpolation_quality", GET_PHYSICS_INTERPOLATION_QUALITY_HASH)
        }

        private const val SET_INSTANCE_TRANSFORM_HASH = 3616898986L
        private val setInstanceTransformBind by lazy {
            ObjectCalls.getMethodBind("MultiMesh", "set_instance_transform", SET_INSTANCE_TRANSFORM_HASH)
        }

        private const val SET_INSTANCE_TRANSFORM_2D_HASH = 30160968L
        private val setInstanceTransform2dBind by lazy {
            ObjectCalls.getMethodBind("MultiMesh", "set_instance_transform_2d", SET_INSTANCE_TRANSFORM_2D_HASH)
        }

        private const val GET_INSTANCE_TRANSFORM_HASH = 1965739696L
        private val getInstanceTransformBind by lazy {
            ObjectCalls.getMethodBind("MultiMesh", "get_instance_transform", GET_INSTANCE_TRANSFORM_HASH)
        }

        private const val GET_INSTANCE_TRANSFORM_2D_HASH = 3836996910L
        private val getInstanceTransform2dBind by lazy {
            ObjectCalls.getMethodBind("MultiMesh", "get_instance_transform_2d", GET_INSTANCE_TRANSFORM_2D_HASH)
        }

        private const val SET_INSTANCE_COLOR_HASH = 2878471219L
        private val setInstanceColorBind by lazy {
            ObjectCalls.getMethodBind("MultiMesh", "set_instance_color", SET_INSTANCE_COLOR_HASH)
        }

        private const val GET_INSTANCE_COLOR_HASH = 3457211756L
        private val getInstanceColorBind by lazy {
            ObjectCalls.getMethodBind("MultiMesh", "get_instance_color", GET_INSTANCE_COLOR_HASH)
        }

        private const val SET_INSTANCE_CUSTOM_DATA_HASH = 2878471219L
        private val setInstanceCustomDataBind by lazy {
            ObjectCalls.getMethodBind("MultiMesh", "set_instance_custom_data", SET_INSTANCE_CUSTOM_DATA_HASH)
        }

        private const val GET_INSTANCE_CUSTOM_DATA_HASH = 3457211756L
        private val getInstanceCustomDataBind by lazy {
            ObjectCalls.getMethodBind("MultiMesh", "get_instance_custom_data", GET_INSTANCE_CUSTOM_DATA_HASH)
        }

        private const val RESET_INSTANCE_PHYSICS_INTERPOLATION_HASH = 1286410249L
        private val resetInstancePhysicsInterpolationBind by lazy {
            ObjectCalls.getMethodBind("MultiMesh", "reset_instance_physics_interpolation", RESET_INSTANCE_PHYSICS_INTERPOLATION_HASH)
        }

        private const val RESET_INSTANCES_PHYSICS_INTERPOLATION_HASH = 3218959716L
        private val resetInstancesPhysicsInterpolationBind by lazy {
            ObjectCalls.getMethodBind("MultiMesh", "reset_instances_physics_interpolation", RESET_INSTANCES_PHYSICS_INTERPOLATION_HASH)
        }

        private const val SET_CUSTOM_AABB_HASH = 259215842L
        private val setCustomAabbBind by lazy {
            ObjectCalls.getMethodBind("MultiMesh", "set_custom_aabb", SET_CUSTOM_AABB_HASH)
        }

        private const val GET_CUSTOM_AABB_HASH = 1068685055L
        private val getCustomAabbBind by lazy {
            ObjectCalls.getMethodBind("MultiMesh", "get_custom_aabb", GET_CUSTOM_AABB_HASH)
        }

        private const val GET_AABB_HASH = 1068685055L
        private val getAabbBind by lazy {
            ObjectCalls.getMethodBind("MultiMesh", "get_aabb", GET_AABB_HASH)
        }

        private const val GET_BUFFER_HASH = 675695659L
        private val getBufferBind by lazy {
            ObjectCalls.getMethodBind("MultiMesh", "get_buffer", GET_BUFFER_HASH)
        }

        private const val SET_BUFFER_HASH = 2899603908L
        private val setBufferBind by lazy {
            ObjectCalls.getMethodBind("MultiMesh", "set_buffer", SET_BUFFER_HASH)
        }

        private const val SET_BUFFER_INTERPOLATED_HASH = 3514430332L
        private val setBufferInterpolatedBind by lazy {
            ObjectCalls.getMethodBind("MultiMesh", "set_buffer_interpolated", SET_BUFFER_INTERPOLATED_HASH)
        }
    }
}
