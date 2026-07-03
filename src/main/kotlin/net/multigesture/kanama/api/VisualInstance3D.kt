package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.RID

/**
 * Parent of all visual 3D nodes.
 *
 * Generated from Godot docs: VisualInstance3D
 */
open class VisualInstance3D(handle: MemorySegment) : Node3D(handle) {
    var layers: Long
        @JvmName("layersProperty")
        get() = getLayerMask()
        @JvmName("setLayersProperty")
        set(value) = setLayerMask(value)

    var sortingOffset: Double
        @JvmName("sortingOffsetProperty")
        get() = getSortingOffset()
        @JvmName("setSortingOffsetProperty")
        set(value) = setSortingOffset(value)

    var sortingUseAabbCenter: Boolean
        @JvmName("sortingUseAabbCenterProperty")
        get() = isSortingUseAabbCenter()
        @JvmName("setSortingUseAabbCenterProperty")
        set(value) = setSortingUseAabbCenter(value)

    /**
     * Sets the resource that is instantiated by this `VisualInstance3D`, which changes how the engine
     * handles the `VisualInstance3D` under the hood. Equivalent to
     * `RenderingServer.instance_set_base`.
     *
     * Generated from Godot docs: VisualInstance3D.set_base
     */
    fun setBase(base: RID) {
        ObjectCalls.ptrcallWithRIDArg(setBaseBind, handle, base)
    }

    /**
     * Returns the RID of the resource associated with this `VisualInstance3D`. For example, if the
     * Node is a `MeshInstance3D`, this will return the RID of the associated `Mesh`.
     *
     * Generated from Godot docs: VisualInstance3D.get_base
     */
    fun getBase(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getBaseBind, handle)
    }

    /**
     * Returns the RID of this instance. This RID is the same as the RID returned by
     * `RenderingServer.instance_create`. This RID is needed if you want to call `RenderingServer`
     * functions directly on this `VisualInstance3D`.
     *
     * Generated from Godot docs: VisualInstance3D.get_instance
     */
    fun getInstance(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getInstanceBind, handle)
    }

    /**
     * The render layer(s) this `VisualInstance3D` is drawn on. This object will only be visible for
     * `Camera3D`s whose cull mask includes any of the render layers this `VisualInstance3D` is set to.
     * For `Light3D`s, this can be used to control which `VisualInstance3D`s are affected by a specific
     * light. For `GPUParticles3D`, this can be used to control which particles are effected by a
     * specific attractor. For `Decal`s, this can be used to control which `VisualInstance3D`s are
     * affected by a specific decal. To adjust `layers` more easily using a script, use
     * `get_layer_mask_value` and `set_layer_mask_value`. Note: `VoxelGI`, SDFGI and `LightmapGI` will
     * always take all layers into account to determine what contributes to global illumination. If
     * this is an issue, set `GeometryInstance3D.gi_mode` to `GeometryInstance3D.GI_MODE_DISABLED` for
     * meshes and `Light3D.light_bake_mode` to `Light3D.BAKE_DISABLED` for lights to exclude them from
     * global illumination.
     *
     * Generated from Godot docs: VisualInstance3D.set_layer_mask
     */
    fun setLayerMask(mask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setLayerMaskBind, handle, mask)
    }

    /**
     * The render layer(s) this `VisualInstance3D` is drawn on. This object will only be visible for
     * `Camera3D`s whose cull mask includes any of the render layers this `VisualInstance3D` is set to.
     * For `Light3D`s, this can be used to control which `VisualInstance3D`s are affected by a specific
     * light. For `GPUParticles3D`, this can be used to control which particles are effected by a
     * specific attractor. For `Decal`s, this can be used to control which `VisualInstance3D`s are
     * affected by a specific decal. To adjust `layers` more easily using a script, use
     * `get_layer_mask_value` and `set_layer_mask_value`. Note: `VoxelGI`, SDFGI and `LightmapGI` will
     * always take all layers into account to determine what contributes to global illumination. If
     * this is an issue, set `GeometryInstance3D.gi_mode` to `GeometryInstance3D.GI_MODE_DISABLED` for
     * meshes and `Light3D.light_bake_mode` to `Light3D.BAKE_DISABLED` for lights to exclude them from
     * global illumination.
     *
     * Generated from Godot docs: VisualInstance3D.get_layer_mask
     */
    fun getLayerMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getLayerMaskBind, handle)
    }

    /**
     * Based on `value`, enables or disables the specified layer in the `layers`, given a
     * `layer_number` between 1 and 20.
     *
     * Generated from Godot docs: VisualInstance3D.set_layer_mask_value
     */
    fun setLayerMaskValue(layerNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setLayerMaskValueBind, handle, layerNumber, value)
    }

    /**
     * Returns whether or not the specified layer of the `layers` is enabled, given a `layer_number`
     * between 1 and 20.
     *
     * Generated from Godot docs: VisualInstance3D.get_layer_mask_value
     */
    fun getLayerMaskValue(layerNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(getLayerMaskValueBind, handle, layerNumber)
    }

    /**
     * The amount by which the depth of this `VisualInstance3D` will be adjusted when sorting by depth.
     * Uses the same units as the engine (which are typically meters). Adjusting it to a higher value
     * will make the `VisualInstance3D` reliably draw on top of other `VisualInstance3D`s that are
     * otherwise positioned at the same spot. To ensure it always draws on top of other objects around
     * it (not positioned at the same spot), set the value to be greater than the distance between this
     * `VisualInstance3D` and the other nearby `VisualInstance3D`s.
     *
     * Generated from Godot docs: VisualInstance3D.set_sorting_offset
     */
    fun setSortingOffset(offset: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSortingOffsetBind, handle, offset)
    }

    /**
     * The amount by which the depth of this `VisualInstance3D` will be adjusted when sorting by depth.
     * Uses the same units as the engine (which are typically meters). Adjusting it to a higher value
     * will make the `VisualInstance3D` reliably draw on top of other `VisualInstance3D`s that are
     * otherwise positioned at the same spot. To ensure it always draws on top of other objects around
     * it (not positioned at the same spot), set the value to be greater than the distance between this
     * `VisualInstance3D` and the other nearby `VisualInstance3D`s.
     *
     * Generated from Godot docs: VisualInstance3D.get_sorting_offset
     */
    fun getSortingOffset(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSortingOffsetBind, handle)
    }

    /**
     * If `true`, the object is sorted based on the `AABB` center. The object will be sorted based on
     * the global position otherwise. The `AABB` center based sorting is generally more accurate for 3D
     * models. The position based sorting instead allows to better control the drawing order when
     * working with `GPUParticles3D` and `CPUParticles3D`.
     *
     * Generated from Godot docs: VisualInstance3D.set_sorting_use_aabb_center
     */
    fun setSortingUseAabbCenter(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSortingUseAabbCenterBind, handle, enabled)
    }

    /**
     * If `true`, the object is sorted based on the `AABB` center. The object will be sorted based on
     * the global position otherwise. The `AABB` center based sorting is generally more accurate for 3D
     * models. The position based sorting instead allows to better control the drawing order when
     * working with `GPUParticles3D` and `CPUParticles3D`.
     *
     * Generated from Godot docs: VisualInstance3D.is_sorting_use_aabb_center
     */
    fun isSortingUseAabbCenter(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSortingUseAabbCenterBind, handle)
    }

    /**
     * Returns the `AABB` (also known as the bounding box) for this `VisualInstance3D`.
     *
     * Generated from Godot docs: VisualInstance3D.get_aabb
     */
    fun getAabb(): AABB {
        return ObjectCalls.ptrcallNoArgsRetAABB(getAabbBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualInstance3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualInstance3D? =
            if (handle.address() == 0L) null else VisualInstance3D(handle)

        private const val SET_BASE_HASH = 2722037293L
        private val setBaseBind by lazy {
            ObjectCalls.getMethodBind("VisualInstance3D", "set_base", SET_BASE_HASH)
        }

        private const val GET_BASE_HASH = 2944877500L
        private val getBaseBind by lazy {
            ObjectCalls.getMethodBind("VisualInstance3D", "get_base", GET_BASE_HASH)
        }

        private const val GET_INSTANCE_HASH = 2944877500L
        private val getInstanceBind by lazy {
            ObjectCalls.getMethodBind("VisualInstance3D", "get_instance", GET_INSTANCE_HASH)
        }

        private const val SET_LAYER_MASK_HASH = 1286410249L
        private val setLayerMaskBind by lazy {
            ObjectCalls.getMethodBind("VisualInstance3D", "set_layer_mask", SET_LAYER_MASK_HASH)
        }

        private const val GET_LAYER_MASK_HASH = 3905245786L
        private val getLayerMaskBind by lazy {
            ObjectCalls.getMethodBind("VisualInstance3D", "get_layer_mask", GET_LAYER_MASK_HASH)
        }

        private const val SET_LAYER_MASK_VALUE_HASH = 300928843L
        private val setLayerMaskValueBind by lazy {
            ObjectCalls.getMethodBind("VisualInstance3D", "set_layer_mask_value", SET_LAYER_MASK_VALUE_HASH)
        }

        private const val GET_LAYER_MASK_VALUE_HASH = 1116898809L
        private val getLayerMaskValueBind by lazy {
            ObjectCalls.getMethodBind("VisualInstance3D", "get_layer_mask_value", GET_LAYER_MASK_VALUE_HASH)
        }

        private const val SET_SORTING_OFFSET_HASH = 373806689L
        private val setSortingOffsetBind by lazy {
            ObjectCalls.getMethodBind("VisualInstance3D", "set_sorting_offset", SET_SORTING_OFFSET_HASH)
        }

        private const val GET_SORTING_OFFSET_HASH = 1740695150L
        private val getSortingOffsetBind by lazy {
            ObjectCalls.getMethodBind("VisualInstance3D", "get_sorting_offset", GET_SORTING_OFFSET_HASH)
        }

        private const val SET_SORTING_USE_AABB_CENTER_HASH = 2586408642L
        private val setSortingUseAabbCenterBind by lazy {
            ObjectCalls.getMethodBind("VisualInstance3D", "set_sorting_use_aabb_center", SET_SORTING_USE_AABB_CENTER_HASH)
        }

        private const val IS_SORTING_USE_AABB_CENTER_HASH = 36873697L
        private val isSortingUseAabbCenterBind by lazy {
            ObjectCalls.getMethodBind("VisualInstance3D", "is_sorting_use_aabb_center", IS_SORTING_USE_AABB_CENTER_HASH)
        }

        private const val GET_AABB_HASH = 1068685055L
        private val getAabbBind by lazy {
            ObjectCalls.getMethodBind("VisualInstance3D", "get_aabb", GET_AABB_HASH)
        }
    }
}
