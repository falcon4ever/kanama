package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color

/**
 * Casts light in a 2D environment.
 *
 * Generated from Godot docs: Light2D
 */
open class Light2D(handle: MemorySegment) : Node2D(handle) {
    var enabled: Boolean
        @JvmName("enabledProperty")
        get() = isEnabled()
        @JvmName("setEnabledProperty")
        set(value) = setEnabled(value)

    var editorOnly: Boolean
        @JvmName("editorOnlyProperty")
        get() = isEditorOnly()
        @JvmName("setEditorOnlyProperty")
        set(value) = setEditorOnly(value)

    var color: Color
        @JvmName("colorProperty")
        get() = getColor()
        @JvmName("setColorProperty")
        set(value) = setColor(value)

    var energy: Double
        @JvmName("energyProperty")
        get() = getEnergy()
        @JvmName("setEnergyProperty")
        set(value) = setEnergy(value)

    var blendMode: Long
        @JvmName("blendModeProperty")
        get() = getBlendMode()
        @JvmName("setBlendModeProperty")
        set(value) = setBlendMode(value)

    var rangeZMin: Int
        @JvmName("rangeZMinProperty")
        get() = getZRangeMin()
        @JvmName("setRangeZMinProperty")
        set(value) = setZRangeMin(value)

    var rangeZMax: Int
        @JvmName("rangeZMaxProperty")
        get() = getZRangeMax()
        @JvmName("setRangeZMaxProperty")
        set(value) = setZRangeMax(value)

    var rangeLayerMin: Int
        @JvmName("rangeLayerMinProperty")
        get() = getLayerRangeMin()
        @JvmName("setRangeLayerMinProperty")
        set(value) = setLayerRangeMin(value)

    var rangeLayerMax: Int
        @JvmName("rangeLayerMaxProperty")
        get() = getLayerRangeMax()
        @JvmName("setRangeLayerMaxProperty")
        set(value) = setLayerRangeMax(value)

    var rangeItemCullMask: Int
        @JvmName("rangeItemCullMaskProperty")
        get() = getItemCullMask()
        @JvmName("setRangeItemCullMaskProperty")
        set(value) = setItemCullMask(value)

    var shadowEnabled: Boolean
        @JvmName("shadowEnabledProperty")
        get() = isShadowEnabled()
        @JvmName("setShadowEnabledProperty")
        set(value) = setShadowEnabled(value)

    var shadowColor: Color
        @JvmName("shadowColorProperty")
        get() = getShadowColor()
        @JvmName("setShadowColorProperty")
        set(value) = setShadowColor(value)

    var shadowFilter: Long
        @JvmName("shadowFilterProperty")
        get() = getShadowFilter()
        @JvmName("setShadowFilterProperty")
        set(value) = setShadowFilter(value)

    var shadowFilterSmooth: Double
        @JvmName("shadowFilterSmoothProperty")
        get() = getShadowSmooth()
        @JvmName("setShadowFilterSmoothProperty")
        set(value) = setShadowSmooth(value)

    var shadowItemCullMask: Int
        @JvmName("shadowItemCullMaskProperty")
        get() = getItemShadowCullMask()
        @JvmName("setShadowItemCullMaskProperty")
        set(value) = setItemShadowCullMask(value)

    /**
     * If `true`, Light2D will emit light.
     *
     * Generated from Godot docs: Light2D.set_enabled
     */
    fun setEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEnabledBind, handle, enabled)
    }

    /**
     * If `true`, Light2D will emit light.
     *
     * Generated from Godot docs: Light2D.is_enabled
     */
    fun isEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isEnabledBind, handle)
    }

    /**
     * If `true`, Light2D will only appear when editing the scene.
     *
     * Generated from Godot docs: Light2D.set_editor_only
     */
    fun setEditorOnly(editorOnly: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEditorOnlyBind, handle, editorOnly)
    }

    /**
     * If `true`, Light2D will only appear when editing the scene.
     *
     * Generated from Godot docs: Light2D.is_editor_only
     */
    fun isEditorOnly(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isEditorOnlyBind, handle)
    }

    /**
     * The Light2D's `Color`.
     *
     * Generated from Godot docs: Light2D.set_color
     */
    fun setColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setColorBind, handle, color)
    }

    /**
     * The Light2D's `Color`.
     *
     * Generated from Godot docs: Light2D.get_color
     */
    fun getColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getColorBind, handle)
    }

    /**
     * The Light2D's energy value. The larger the value, the stronger the light.
     *
     * Generated from Godot docs: Light2D.set_energy
     */
    fun setEnergy(energy: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setEnergyBind, handle, energy)
    }

    /**
     * The Light2D's energy value. The larger the value, the stronger the light.
     *
     * Generated from Godot docs: Light2D.get_energy
     */
    fun getEnergy(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getEnergyBind, handle)
    }

    /**
     * Minimum `z` value of objects that are affected by the Light2D.
     *
     * Generated from Godot docs: Light2D.set_z_range_min
     */
    fun setZRangeMin(z: Int) {
        ObjectCalls.ptrcallWithIntArg(setZRangeMinBind, handle, z)
    }

    /**
     * Minimum `z` value of objects that are affected by the Light2D.
     *
     * Generated from Godot docs: Light2D.get_z_range_min
     */
    fun getZRangeMin(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getZRangeMinBind, handle)
    }

    /**
     * Maximum `z` value of objects that are affected by the Light2D.
     *
     * Generated from Godot docs: Light2D.set_z_range_max
     */
    fun setZRangeMax(z: Int) {
        ObjectCalls.ptrcallWithIntArg(setZRangeMaxBind, handle, z)
    }

    /**
     * Maximum `z` value of objects that are affected by the Light2D.
     *
     * Generated from Godot docs: Light2D.get_z_range_max
     */
    fun getZRangeMax(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getZRangeMaxBind, handle)
    }

    /**
     * Minimum layer value of objects that are affected by the Light2D.
     *
     * Generated from Godot docs: Light2D.set_layer_range_min
     */
    fun setLayerRangeMin(layer: Int) {
        ObjectCalls.ptrcallWithIntArg(setLayerRangeMinBind, handle, layer)
    }

    /**
     * Minimum layer value of objects that are affected by the Light2D.
     *
     * Generated from Godot docs: Light2D.get_layer_range_min
     */
    fun getLayerRangeMin(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getLayerRangeMinBind, handle)
    }

    /**
     * Maximum layer value of objects that are affected by the Light2D.
     *
     * Generated from Godot docs: Light2D.set_layer_range_max
     */
    fun setLayerRangeMax(layer: Int) {
        ObjectCalls.ptrcallWithIntArg(setLayerRangeMaxBind, handle, layer)
    }

    /**
     * Maximum layer value of objects that are affected by the Light2D.
     *
     * Generated from Godot docs: Light2D.get_layer_range_max
     */
    fun getLayerRangeMax(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getLayerRangeMaxBind, handle)
    }

    /**
     * The layer mask. Only objects with a matching `CanvasItem.light_mask` will be affected by the
     * Light2D. See also `shadow_item_cull_mask`, which affects which objects can cast shadows. Note:
     * `range_item_cull_mask` is ignored by `DirectionalLight2D`, which will always light a 2D node
     * regardless of the 2D node's `CanvasItem.light_mask`.
     *
     * Generated from Godot docs: Light2D.set_item_cull_mask
     */
    fun setItemCullMask(itemCullMask: Int) {
        ObjectCalls.ptrcallWithIntArg(setItemCullMaskBind, handle, itemCullMask)
    }

    /**
     * The layer mask. Only objects with a matching `CanvasItem.light_mask` will be affected by the
     * Light2D. See also `shadow_item_cull_mask`, which affects which objects can cast shadows. Note:
     * `range_item_cull_mask` is ignored by `DirectionalLight2D`, which will always light a 2D node
     * regardless of the 2D node's `CanvasItem.light_mask`.
     *
     * Generated from Godot docs: Light2D.get_item_cull_mask
     */
    fun getItemCullMask(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getItemCullMaskBind, handle)
    }

    /**
     * The shadow mask. Used with `LightOccluder2D` to cast shadows. Only occluders with a matching
     * `CanvasItem.light_mask` will cast shadows. See also `range_item_cull_mask`, which affects which
     * objects can receive the light.
     *
     * Generated from Godot docs: Light2D.set_item_shadow_cull_mask
     */
    fun setItemShadowCullMask(itemShadowCullMask: Int) {
        ObjectCalls.ptrcallWithIntArg(setItemShadowCullMaskBind, handle, itemShadowCullMask)
    }

    /**
     * The shadow mask. Used with `LightOccluder2D` to cast shadows. Only occluders with a matching
     * `CanvasItem.light_mask` will cast shadows. See also `range_item_cull_mask`, which affects which
     * objects can receive the light.
     *
     * Generated from Godot docs: Light2D.get_item_shadow_cull_mask
     */
    fun getItemShadowCullMask(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getItemShadowCullMaskBind, handle)
    }

    /**
     * If `true`, the Light2D will cast shadows.
     *
     * Generated from Godot docs: Light2D.set_shadow_enabled
     */
    fun setShadowEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setShadowEnabledBind, handle, enabled)
    }

    /**
     * If `true`, the Light2D will cast shadows.
     *
     * Generated from Godot docs: Light2D.is_shadow_enabled
     */
    fun isShadowEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isShadowEnabledBind, handle)
    }

    /**
     * Smoothing value for shadows. Higher values will result in softer shadows, at the cost of visible
     * streaks that can appear in shadow rendering. `shadow_filter_smooth` only has an effect if
     * `shadow_filter` is `SHADOW_FILTER_PCF5` or `SHADOW_FILTER_PCF13`.
     *
     * Generated from Godot docs: Light2D.set_shadow_smooth
     */
    fun setShadowSmooth(smooth: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setShadowSmoothBind, handle, smooth)
    }

    /**
     * Smoothing value for shadows. Higher values will result in softer shadows, at the cost of visible
     * streaks that can appear in shadow rendering. `shadow_filter_smooth` only has an effect if
     * `shadow_filter` is `SHADOW_FILTER_PCF5` or `SHADOW_FILTER_PCF13`.
     *
     * Generated from Godot docs: Light2D.get_shadow_smooth
     */
    fun getShadowSmooth(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getShadowSmoothBind, handle)
    }

    /**
     * Shadow filter type.
     *
     * Generated from Godot docs: Light2D.set_shadow_filter
     */
    fun setShadowFilter(filter: Long) {
        ObjectCalls.ptrcallWithLongArg(setShadowFilterBind, handle, filter)
    }

    /**
     * Shadow filter type.
     *
     * Generated from Godot docs: Light2D.get_shadow_filter
     */
    fun getShadowFilter(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getShadowFilterBind, handle)
    }

    /**
     * `Color` of shadows cast by the Light2D.
     *
     * Generated from Godot docs: Light2D.set_shadow_color
     */
    fun setShadowColor(shadowColor: Color) {
        ObjectCalls.ptrcallWithColorArg(setShadowColorBind, handle, shadowColor)
    }

    /**
     * `Color` of shadows cast by the Light2D.
     *
     * Generated from Godot docs: Light2D.get_shadow_color
     */
    fun getShadowColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getShadowColorBind, handle)
    }

    /**
     * The Light2D's blend mode.
     *
     * Generated from Godot docs: Light2D.set_blend_mode
     */
    fun setBlendMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setBlendModeBind, handle, mode)
    }

    /**
     * The Light2D's blend mode.
     *
     * Generated from Godot docs: Light2D.get_blend_mode
     */
    fun getBlendMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getBlendModeBind, handle)
    }

    /**
     * Sets the light's height, which is used in 2D normal mapping. See `PointLight2D.height` and
     * `DirectionalLight2D.height`.
     *
     * Generated from Godot docs: Light2D.set_height
     */
    fun setHeight(height: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setHeightBind, handle, height)
    }

    /**
     * Returns the light's height, which is used in 2D normal mapping. See `PointLight2D.height` and
     * `DirectionalLight2D.height`.
     *
     * Generated from Godot docs: Light2D.get_height
     */
    fun getHeight(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getHeightBind, handle)
    }

    companion object {
        const val SHADOW_FILTER_NONE: Long = 0L
        const val SHADOW_FILTER_PCF5: Long = 1L
        const val SHADOW_FILTER_PCF13: Long = 2L
        const val BLEND_MODE_ADD: Long = 0L
        const val BLEND_MODE_SUB: Long = 1L
        const val BLEND_MODE_MIX: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): Light2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Light2D? =
            if (handle.address() == 0L) null else Light2D(handle)

        private const val SET_ENABLED_HASH = 2586408642L
        private val setEnabledBind by lazy {
            ObjectCalls.getMethodBind("Light2D", "set_enabled", SET_ENABLED_HASH)
        }

        private const val IS_ENABLED_HASH = 36873697L
        private val isEnabledBind by lazy {
            ObjectCalls.getMethodBind("Light2D", "is_enabled", IS_ENABLED_HASH)
        }

        private const val SET_EDITOR_ONLY_HASH = 2586408642L
        private val setEditorOnlyBind by lazy {
            ObjectCalls.getMethodBind("Light2D", "set_editor_only", SET_EDITOR_ONLY_HASH)
        }

        private const val IS_EDITOR_ONLY_HASH = 36873697L
        private val isEditorOnlyBind by lazy {
            ObjectCalls.getMethodBind("Light2D", "is_editor_only", IS_EDITOR_ONLY_HASH)
        }

        private const val SET_COLOR_HASH = 2920490490L
        private val setColorBind by lazy {
            ObjectCalls.getMethodBind("Light2D", "set_color", SET_COLOR_HASH)
        }

        private const val GET_COLOR_HASH = 3444240500L
        private val getColorBind by lazy {
            ObjectCalls.getMethodBind("Light2D", "get_color", GET_COLOR_HASH)
        }

        private const val SET_ENERGY_HASH = 373806689L
        private val setEnergyBind by lazy {
            ObjectCalls.getMethodBind("Light2D", "set_energy", SET_ENERGY_HASH)
        }

        private const val GET_ENERGY_HASH = 1740695150L
        private val getEnergyBind by lazy {
            ObjectCalls.getMethodBind("Light2D", "get_energy", GET_ENERGY_HASH)
        }

        private const val SET_Z_RANGE_MIN_HASH = 1286410249L
        private val setZRangeMinBind by lazy {
            ObjectCalls.getMethodBind("Light2D", "set_z_range_min", SET_Z_RANGE_MIN_HASH)
        }

        private const val GET_Z_RANGE_MIN_HASH = 3905245786L
        private val getZRangeMinBind by lazy {
            ObjectCalls.getMethodBind("Light2D", "get_z_range_min", GET_Z_RANGE_MIN_HASH)
        }

        private const val SET_Z_RANGE_MAX_HASH = 1286410249L
        private val setZRangeMaxBind by lazy {
            ObjectCalls.getMethodBind("Light2D", "set_z_range_max", SET_Z_RANGE_MAX_HASH)
        }

        private const val GET_Z_RANGE_MAX_HASH = 3905245786L
        private val getZRangeMaxBind by lazy {
            ObjectCalls.getMethodBind("Light2D", "get_z_range_max", GET_Z_RANGE_MAX_HASH)
        }

        private const val SET_LAYER_RANGE_MIN_HASH = 1286410249L
        private val setLayerRangeMinBind by lazy {
            ObjectCalls.getMethodBind("Light2D", "set_layer_range_min", SET_LAYER_RANGE_MIN_HASH)
        }

        private const val GET_LAYER_RANGE_MIN_HASH = 3905245786L
        private val getLayerRangeMinBind by lazy {
            ObjectCalls.getMethodBind("Light2D", "get_layer_range_min", GET_LAYER_RANGE_MIN_HASH)
        }

        private const val SET_LAYER_RANGE_MAX_HASH = 1286410249L
        private val setLayerRangeMaxBind by lazy {
            ObjectCalls.getMethodBind("Light2D", "set_layer_range_max", SET_LAYER_RANGE_MAX_HASH)
        }

        private const val GET_LAYER_RANGE_MAX_HASH = 3905245786L
        private val getLayerRangeMaxBind by lazy {
            ObjectCalls.getMethodBind("Light2D", "get_layer_range_max", GET_LAYER_RANGE_MAX_HASH)
        }

        private const val SET_ITEM_CULL_MASK_HASH = 1286410249L
        private val setItemCullMaskBind by lazy {
            ObjectCalls.getMethodBind("Light2D", "set_item_cull_mask", SET_ITEM_CULL_MASK_HASH)
        }

        private const val GET_ITEM_CULL_MASK_HASH = 3905245786L
        private val getItemCullMaskBind by lazy {
            ObjectCalls.getMethodBind("Light2D", "get_item_cull_mask", GET_ITEM_CULL_MASK_HASH)
        }

        private const val SET_ITEM_SHADOW_CULL_MASK_HASH = 1286410249L
        private val setItemShadowCullMaskBind by lazy {
            ObjectCalls.getMethodBind("Light2D", "set_item_shadow_cull_mask", SET_ITEM_SHADOW_CULL_MASK_HASH)
        }

        private const val GET_ITEM_SHADOW_CULL_MASK_HASH = 3905245786L
        private val getItemShadowCullMaskBind by lazy {
            ObjectCalls.getMethodBind("Light2D", "get_item_shadow_cull_mask", GET_ITEM_SHADOW_CULL_MASK_HASH)
        }

        private const val SET_SHADOW_ENABLED_HASH = 2586408642L
        private val setShadowEnabledBind by lazy {
            ObjectCalls.getMethodBind("Light2D", "set_shadow_enabled", SET_SHADOW_ENABLED_HASH)
        }

        private const val IS_SHADOW_ENABLED_HASH = 36873697L
        private val isShadowEnabledBind by lazy {
            ObjectCalls.getMethodBind("Light2D", "is_shadow_enabled", IS_SHADOW_ENABLED_HASH)
        }

        private const val SET_SHADOW_SMOOTH_HASH = 373806689L
        private val setShadowSmoothBind by lazy {
            ObjectCalls.getMethodBind("Light2D", "set_shadow_smooth", SET_SHADOW_SMOOTH_HASH)
        }

        private const val GET_SHADOW_SMOOTH_HASH = 1740695150L
        private val getShadowSmoothBind by lazy {
            ObjectCalls.getMethodBind("Light2D", "get_shadow_smooth", GET_SHADOW_SMOOTH_HASH)
        }

        private const val SET_SHADOW_FILTER_HASH = 3209356555L
        private val setShadowFilterBind by lazy {
            ObjectCalls.getMethodBind("Light2D", "set_shadow_filter", SET_SHADOW_FILTER_HASH)
        }

        private const val GET_SHADOW_FILTER_HASH = 1973619177L
        private val getShadowFilterBind by lazy {
            ObjectCalls.getMethodBind("Light2D", "get_shadow_filter", GET_SHADOW_FILTER_HASH)
        }

        private const val SET_SHADOW_COLOR_HASH = 2920490490L
        private val setShadowColorBind by lazy {
            ObjectCalls.getMethodBind("Light2D", "set_shadow_color", SET_SHADOW_COLOR_HASH)
        }

        private const val GET_SHADOW_COLOR_HASH = 3444240500L
        private val getShadowColorBind by lazy {
            ObjectCalls.getMethodBind("Light2D", "get_shadow_color", GET_SHADOW_COLOR_HASH)
        }

        private const val SET_BLEND_MODE_HASH = 2916638796L
        private val setBlendModeBind by lazy {
            ObjectCalls.getMethodBind("Light2D", "set_blend_mode", SET_BLEND_MODE_HASH)
        }

        private const val GET_BLEND_MODE_HASH = 936255250L
        private val getBlendModeBind by lazy {
            ObjectCalls.getMethodBind("Light2D", "get_blend_mode", GET_BLEND_MODE_HASH)
        }

        private const val SET_HEIGHT_HASH = 373806689L
        private val setHeightBind by lazy {
            ObjectCalls.getMethodBind("Light2D", "set_height", SET_HEIGHT_HASH)
        }

        private const val GET_HEIGHT_HASH = 1740695150L
        private val getHeightBind by lazy {
            ObjectCalls.getMethodBind("Light2D", "get_height", GET_HEIGHT_HASH)
        }
    }
}
