package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Vector3

/**
 * Captures its surroundings to create fast, accurate reflections from a given point.
 *
 * Generated from Godot docs: ReflectionProbe
 */
class ReflectionProbe(handle: MemorySegment) : VisualInstance3D(handle) {
    var updateMode: Long
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

    var ambientMode: Long
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

    fun setIntensity(intensity: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setIntensityBind, handle, intensity)
    }

    fun getIntensity(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getIntensityBind, handle)
    }

    fun setBlendDistance(blendDistance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setBlendDistanceBind, handle, blendDistance)
    }

    fun getBlendDistance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getBlendDistanceBind, handle)
    }

    fun setAmbientMode(ambient: Long) {
        ObjectCalls.ptrcallWithLongArg(setAmbientModeBind, handle, ambient)
    }

    fun getAmbientMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAmbientModeBind, handle)
    }

    fun setAmbientColor(ambient: Color) {
        ObjectCalls.ptrcallWithColorArg(setAmbientColorBind, handle, ambient)
    }

    fun getAmbientColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getAmbientColorBind, handle)
    }

    fun setAmbientColorEnergy(ambientEnergy: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAmbientColorEnergyBind, handle, ambientEnergy)
    }

    fun getAmbientColorEnergy(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAmbientColorEnergyBind, handle)
    }

    fun setMaxDistance(maxDistance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMaxDistanceBind, handle, maxDistance)
    }

    fun getMaxDistance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMaxDistanceBind, handle)
    }

    fun setMeshLodThreshold(ratio: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMeshLodThresholdBind, handle, ratio)
    }

    fun getMeshLodThreshold(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMeshLodThresholdBind, handle)
    }

    fun setSize(size: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setSizeBind, handle, size)
    }

    fun getSize(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getSizeBind, handle)
    }

    fun setOriginOffset(originOffset: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setOriginOffsetBind, handle, originOffset)
    }

    fun getOriginOffset(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getOriginOffsetBind, handle)
    }

    fun setAsInterior(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAsInteriorBind, handle, enable)
    }

    fun isSetAsInterior(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSetAsInteriorBind, handle)
    }

    fun setEnableBoxProjection(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEnableBoxProjectionBind, handle, enable)
    }

    fun isBoxProjectionEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isBoxProjectionEnabledBind, handle)
    }

    fun setEnableShadows(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEnableShadowsBind, handle, enable)
    }

    fun areShadowsEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(areShadowsEnabledBind, handle)
    }

    fun setCullMask(layers: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setCullMaskBind, handle, layers)
    }

    fun getCullMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getCullMaskBind, handle)
    }

    fun setReflectionMask(layers: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setReflectionMaskBind, handle, layers)
    }

    fun getReflectionMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getReflectionMaskBind, handle)
    }

    fun setUpdateMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setUpdateModeBind, handle, mode)
    }

    fun getUpdateMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getUpdateModeBind, handle)
    }

    companion object {
        const val UPDATE_ONCE: Long = 0L
        const val UPDATE_ALWAYS: Long = 1L
        const val AMBIENT_DISABLED: Long = 0L
        const val AMBIENT_ENVIRONMENT: Long = 1L
        const val AMBIENT_COLOR: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): ReflectionProbe? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ReflectionProbe? =
            if (handle.address() == 0L) null else ReflectionProbe(handle)

        private const val SET_INTENSITY_HASH = 373806689L
        private val setIntensityBind by lazy {
            ObjectCalls.getMethodBind("ReflectionProbe", "set_intensity", SET_INTENSITY_HASH)
        }

        private const val GET_INTENSITY_HASH = 1740695150L
        private val getIntensityBind by lazy {
            ObjectCalls.getMethodBind("ReflectionProbe", "get_intensity", GET_INTENSITY_HASH)
        }

        private const val SET_BLEND_DISTANCE_HASH = 373806689L
        private val setBlendDistanceBind by lazy {
            ObjectCalls.getMethodBind("ReflectionProbe", "set_blend_distance", SET_BLEND_DISTANCE_HASH)
        }

        private const val GET_BLEND_DISTANCE_HASH = 1740695150L
        private val getBlendDistanceBind by lazy {
            ObjectCalls.getMethodBind("ReflectionProbe", "get_blend_distance", GET_BLEND_DISTANCE_HASH)
        }

        private const val SET_AMBIENT_MODE_HASH = 1748981278L
        private val setAmbientModeBind by lazy {
            ObjectCalls.getMethodBind("ReflectionProbe", "set_ambient_mode", SET_AMBIENT_MODE_HASH)
        }

        private const val GET_AMBIENT_MODE_HASH = 1014607621L
        private val getAmbientModeBind by lazy {
            ObjectCalls.getMethodBind("ReflectionProbe", "get_ambient_mode", GET_AMBIENT_MODE_HASH)
        }

        private const val SET_AMBIENT_COLOR_HASH = 2920490490L
        private val setAmbientColorBind by lazy {
            ObjectCalls.getMethodBind("ReflectionProbe", "set_ambient_color", SET_AMBIENT_COLOR_HASH)
        }

        private const val GET_AMBIENT_COLOR_HASH = 3444240500L
        private val getAmbientColorBind by lazy {
            ObjectCalls.getMethodBind("ReflectionProbe", "get_ambient_color", GET_AMBIENT_COLOR_HASH)
        }

        private const val SET_AMBIENT_COLOR_ENERGY_HASH = 373806689L
        private val setAmbientColorEnergyBind by lazy {
            ObjectCalls.getMethodBind("ReflectionProbe", "set_ambient_color_energy", SET_AMBIENT_COLOR_ENERGY_HASH)
        }

        private const val GET_AMBIENT_COLOR_ENERGY_HASH = 1740695150L
        private val getAmbientColorEnergyBind by lazy {
            ObjectCalls.getMethodBind("ReflectionProbe", "get_ambient_color_energy", GET_AMBIENT_COLOR_ENERGY_HASH)
        }

        private const val SET_MAX_DISTANCE_HASH = 373806689L
        private val setMaxDistanceBind by lazy {
            ObjectCalls.getMethodBind("ReflectionProbe", "set_max_distance", SET_MAX_DISTANCE_HASH)
        }

        private const val GET_MAX_DISTANCE_HASH = 1740695150L
        private val getMaxDistanceBind by lazy {
            ObjectCalls.getMethodBind("ReflectionProbe", "get_max_distance", GET_MAX_DISTANCE_HASH)
        }

        private const val SET_MESH_LOD_THRESHOLD_HASH = 373806689L
        private val setMeshLodThresholdBind by lazy {
            ObjectCalls.getMethodBind("ReflectionProbe", "set_mesh_lod_threshold", SET_MESH_LOD_THRESHOLD_HASH)
        }

        private const val GET_MESH_LOD_THRESHOLD_HASH = 1740695150L
        private val getMeshLodThresholdBind by lazy {
            ObjectCalls.getMethodBind("ReflectionProbe", "get_mesh_lod_threshold", GET_MESH_LOD_THRESHOLD_HASH)
        }

        private const val SET_SIZE_HASH = 3460891852L
        private val setSizeBind by lazy {
            ObjectCalls.getMethodBind("ReflectionProbe", "set_size", SET_SIZE_HASH)
        }

        private const val GET_SIZE_HASH = 3360562783L
        private val getSizeBind by lazy {
            ObjectCalls.getMethodBind("ReflectionProbe", "get_size", GET_SIZE_HASH)
        }

        private const val SET_ORIGIN_OFFSET_HASH = 3460891852L
        private val setOriginOffsetBind by lazy {
            ObjectCalls.getMethodBind("ReflectionProbe", "set_origin_offset", SET_ORIGIN_OFFSET_HASH)
        }

        private const val GET_ORIGIN_OFFSET_HASH = 3360562783L
        private val getOriginOffsetBind by lazy {
            ObjectCalls.getMethodBind("ReflectionProbe", "get_origin_offset", GET_ORIGIN_OFFSET_HASH)
        }

        private const val SET_AS_INTERIOR_HASH = 2586408642L
        private val setAsInteriorBind by lazy {
            ObjectCalls.getMethodBind("ReflectionProbe", "set_as_interior", SET_AS_INTERIOR_HASH)
        }

        private const val IS_SET_AS_INTERIOR_HASH = 36873697L
        private val isSetAsInteriorBind by lazy {
            ObjectCalls.getMethodBind("ReflectionProbe", "is_set_as_interior", IS_SET_AS_INTERIOR_HASH)
        }

        private const val SET_ENABLE_BOX_PROJECTION_HASH = 2586408642L
        private val setEnableBoxProjectionBind by lazy {
            ObjectCalls.getMethodBind("ReflectionProbe", "set_enable_box_projection", SET_ENABLE_BOX_PROJECTION_HASH)
        }

        private const val IS_BOX_PROJECTION_ENABLED_HASH = 36873697L
        private val isBoxProjectionEnabledBind by lazy {
            ObjectCalls.getMethodBind("ReflectionProbe", "is_box_projection_enabled", IS_BOX_PROJECTION_ENABLED_HASH)
        }

        private const val SET_ENABLE_SHADOWS_HASH = 2586408642L
        private val setEnableShadowsBind by lazy {
            ObjectCalls.getMethodBind("ReflectionProbe", "set_enable_shadows", SET_ENABLE_SHADOWS_HASH)
        }

        private const val ARE_SHADOWS_ENABLED_HASH = 36873697L
        private val areShadowsEnabledBind by lazy {
            ObjectCalls.getMethodBind("ReflectionProbe", "are_shadows_enabled", ARE_SHADOWS_ENABLED_HASH)
        }

        private const val SET_CULL_MASK_HASH = 1286410249L
        private val setCullMaskBind by lazy {
            ObjectCalls.getMethodBind("ReflectionProbe", "set_cull_mask", SET_CULL_MASK_HASH)
        }

        private const val GET_CULL_MASK_HASH = 3905245786L
        private val getCullMaskBind by lazy {
            ObjectCalls.getMethodBind("ReflectionProbe", "get_cull_mask", GET_CULL_MASK_HASH)
        }

        private const val SET_REFLECTION_MASK_HASH = 1286410249L
        private val setReflectionMaskBind by lazy {
            ObjectCalls.getMethodBind("ReflectionProbe", "set_reflection_mask", SET_REFLECTION_MASK_HASH)
        }

        private const val GET_REFLECTION_MASK_HASH = 3905245786L
        private val getReflectionMaskBind by lazy {
            ObjectCalls.getMethodBind("ReflectionProbe", "get_reflection_mask", GET_REFLECTION_MASK_HASH)
        }

        private const val SET_UPDATE_MODE_HASH = 4090221187L
        private val setUpdateModeBind by lazy {
            ObjectCalls.getMethodBind("ReflectionProbe", "set_update_mode", SET_UPDATE_MODE_HASH)
        }

        private const val GET_UPDATE_MODE_HASH = 2367550552L
        private val getUpdateModeBind by lazy {
            ObjectCalls.getMethodBind("ReflectionProbe", "get_update_mode", GET_UPDATE_MODE_HASH)
        }
    }
}
