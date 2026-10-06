package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Color

/**
 * Casts light in a 2D environment.
 *
 * Generated from Godot docs: Light2D
 */
open class Light2D(handle: GodotHandle) : Node2D(handle) {
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

    var blendMode: Light2D.BlendMode
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

    var shadowFilter: Light2D.ShadowFilter
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
        ObjectCalls.ptrcallWithBoolArg(Binds.setEnabledBind, segment, enabled)
    }

    /**
     * If `true`, Light2D will emit light.
     *
     * Generated from Godot docs: Light2D.is_enabled
     */
    fun isEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isEnabledBind, segment)
    }

    /**
     * If `true`, Light2D will only appear when editing the scene.
     *
     * Generated from Godot docs: Light2D.set_editor_only
     */
    fun setEditorOnly(editorOnly: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setEditorOnlyBind, segment, editorOnly)
    }

    /**
     * If `true`, Light2D will only appear when editing the scene.
     *
     * Generated from Godot docs: Light2D.is_editor_only
     */
    fun isEditorOnly(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isEditorOnlyBind, segment)
    }

    /**
     * The Light2D's `Color`.
     *
     * Generated from Godot docs: Light2D.set_color
     */
    fun setColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(Binds.setColorBind, segment, color)
    }

    /**
     * The Light2D's `Color`.
     *
     * Generated from Godot docs: Light2D.get_color
     */
    fun getColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(Binds.getColorBind, segment)
    }

    /**
     * The Light2D's energy value. The larger the value, the stronger the light.
     *
     * Generated from Godot docs: Light2D.set_energy
     */
    fun setEnergy(energy: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setEnergyBind, segment, energy)
    }

    /**
     * The Light2D's energy value. The larger the value, the stronger the light.
     *
     * Generated from Godot docs: Light2D.get_energy
     */
    fun getEnergy(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getEnergyBind, segment)
    }

    /**
     * Minimum `z` value of objects that are affected by the Light2D.
     *
     * Generated from Godot docs: Light2D.set_z_range_min
     */
    fun setZRangeMin(z: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setZRangeMinBind, segment, z)
    }

    /**
     * Minimum `z` value of objects that are affected by the Light2D.
     *
     * Generated from Godot docs: Light2D.get_z_range_min
     */
    fun getZRangeMin(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getZRangeMinBind, segment)
    }

    /**
     * Maximum `z` value of objects that are affected by the Light2D.
     *
     * Generated from Godot docs: Light2D.set_z_range_max
     */
    fun setZRangeMax(z: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setZRangeMaxBind, segment, z)
    }

    /**
     * Maximum `z` value of objects that are affected by the Light2D.
     *
     * Generated from Godot docs: Light2D.get_z_range_max
     */
    fun getZRangeMax(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getZRangeMaxBind, segment)
    }

    /**
     * Minimum layer value of objects that are affected by the Light2D.
     *
     * Generated from Godot docs: Light2D.set_layer_range_min
     */
    fun setLayerRangeMin(layer: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setLayerRangeMinBind, segment, layer)
    }

    /**
     * Minimum layer value of objects that are affected by the Light2D.
     *
     * Generated from Godot docs: Light2D.get_layer_range_min
     */
    fun getLayerRangeMin(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getLayerRangeMinBind, segment)
    }

    /**
     * Maximum layer value of objects that are affected by the Light2D.
     *
     * Generated from Godot docs: Light2D.set_layer_range_max
     */
    fun setLayerRangeMax(layer: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setLayerRangeMaxBind, segment, layer)
    }

    /**
     * Maximum layer value of objects that are affected by the Light2D.
     *
     * Generated from Godot docs: Light2D.get_layer_range_max
     */
    fun getLayerRangeMax(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getLayerRangeMaxBind, segment)
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
        ObjectCalls.ptrcallWithIntArg(Binds.setItemCullMaskBind, segment, itemCullMask)
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
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getItemCullMaskBind, segment)
    }

    /**
     * The shadow mask. Used with `LightOccluder2D` to cast shadows. Only occluders with a matching
     * `CanvasItem.light_mask` will cast shadows. See also `range_item_cull_mask`, which affects which
     * objects can receive the light.
     *
     * Generated from Godot docs: Light2D.set_item_shadow_cull_mask
     */
    fun setItemShadowCullMask(itemShadowCullMask: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setItemShadowCullMaskBind, segment, itemShadowCullMask)
    }

    /**
     * The shadow mask. Used with `LightOccluder2D` to cast shadows. Only occluders with a matching
     * `CanvasItem.light_mask` will cast shadows. See also `range_item_cull_mask`, which affects which
     * objects can receive the light.
     *
     * Generated from Godot docs: Light2D.get_item_shadow_cull_mask
     */
    fun getItemShadowCullMask(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getItemShadowCullMaskBind, segment)
    }

    /**
     * If `true`, the Light2D will cast shadows.
     *
     * Generated from Godot docs: Light2D.set_shadow_enabled
     */
    fun setShadowEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setShadowEnabledBind, segment, enabled)
    }

    /**
     * If `true`, the Light2D will cast shadows.
     *
     * Generated from Godot docs: Light2D.is_shadow_enabled
     */
    fun isShadowEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isShadowEnabledBind, segment)
    }

    /**
     * Smoothing value for shadows. Higher values will result in softer shadows, at the cost of visible
     * streaks that can appear in shadow rendering. `shadow_filter_smooth` only has an effect if
     * `shadow_filter` is `ShadowFilter.PCF5` or `ShadowFilter.PCF13`.
     *
     * Generated from Godot docs: Light2D.set_shadow_smooth
     */
    fun setShadowSmooth(smooth: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setShadowSmoothBind, segment, smooth)
    }

    /**
     * Smoothing value for shadows. Higher values will result in softer shadows, at the cost of visible
     * streaks that can appear in shadow rendering. `shadow_filter_smooth` only has an effect if
     * `shadow_filter` is `ShadowFilter.PCF5` or `ShadowFilter.PCF13`.
     *
     * Generated from Godot docs: Light2D.get_shadow_smooth
     */
    fun getShadowSmooth(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getShadowSmoothBind, segment)
    }

    /**
     * Shadow filter type.
     *
     * Generated from Godot docs: Light2D.set_shadow_filter
     */
    fun setShadowFilter(filter: Light2D.ShadowFilter) {
        ObjectCalls.ptrcallWithLongArg(Binds.setShadowFilterBind, segment, filter.value)
    }

    /**
     * Shadow filter type.
     *
     * Generated from Godot docs: Light2D.get_shadow_filter
     */
    fun getShadowFilter(): Light2D.ShadowFilter {
        return Light2D.ShadowFilter(ObjectCalls.ptrcallNoArgsRetLong(Binds.getShadowFilterBind, segment))
    }

    /**
     * `Color` of shadows cast by the Light2D.
     *
     * Generated from Godot docs: Light2D.set_shadow_color
     */
    fun setShadowColor(shadowColor: Color) {
        ObjectCalls.ptrcallWithColorArg(Binds.setShadowColorBind, segment, shadowColor)
    }

    /**
     * `Color` of shadows cast by the Light2D.
     *
     * Generated from Godot docs: Light2D.get_shadow_color
     */
    fun getShadowColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(Binds.getShadowColorBind, segment)
    }

    /**
     * The Light2D's blend mode.
     *
     * Generated from Godot docs: Light2D.set_blend_mode
     */
    fun setBlendMode(mode: Light2D.BlendMode) {
        ObjectCalls.ptrcallWithLongArg(Binds.setBlendModeBind, segment, mode.value)
    }

    /**
     * The Light2D's blend mode.
     *
     * Generated from Godot docs: Light2D.get_blend_mode
     */
    fun getBlendMode(): Light2D.BlendMode {
        return Light2D.BlendMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getBlendModeBind, segment))
    }

    /**
     * Sets the light's height, which is used in 2D normal mapping. See `PointLight2D.height` and
     * `DirectionalLight2D.height`.
     *
     * Generated from Godot docs: Light2D.set_height
     */
    fun setHeight(height: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setHeightBind, segment, height)
    }

    /**
     * Returns the light's height, which is used in 2D normal mapping. See `PointLight2D.height` and
     * `DirectionalLight2D.height`.
     *
     * Generated from Godot docs: Light2D.get_height
     */
    fun getHeight(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getHeightBind, segment)
    }

    /**
     * Godot's `Light2D.ShadowFilter` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`Light2D.ShadowFilter.<NAME>`).
     *
     * Generated from Godot docs: Light2D.ShadowFilter
     */
    @JvmInline
    value class ShadowFilter(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * No filter applies to the shadow map. This provides hard shadow edges and is the fastest to
             * render. See `shadow_filter`.
             *
             * Generated from Godot docs: Light2D.SHADOW_FILTER_NONE
             */
            val NONE: ShadowFilter get() = ShadowFilter(0L)
            /**
             * Percentage closer filtering (5 samples) applies to the shadow map. This is slower compared to
             * hard shadow rendering. See `shadow_filter`.
             *
             * Generated from Godot docs: Light2D.SHADOW_FILTER_PCF5
             */
            val PCF5: ShadowFilter get() = ShadowFilter(1L)
            /**
             * Percentage closer filtering (13 samples) applies to the shadow map. This is the slowest shadow
             * filtering mode, and should be used sparingly. See `shadow_filter`.
             *
             * Generated from Godot docs: Light2D.SHADOW_FILTER_PCF13
             */
            val PCF13: ShadowFilter get() = ShadowFilter(2L)
        }
    }

    /**
     * Godot's `Light2D.BlendMode` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`Light2D.BlendMode.<NAME>`).
     *
     * Generated from Godot docs: Light2D.BlendMode
     */
    @JvmInline
    value class BlendMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Adds the value of pixels corresponding to the Light2D to the values of pixels under it. This is
             * the common behavior of a light.
             *
             * Generated from Godot docs: Light2D.BLEND_MODE_ADD
             */
            val ADD: BlendMode get() = BlendMode(0L)
            /**
             * Subtracts the value of pixels corresponding to the Light2D to the values of pixels under it,
             * resulting in inversed light effect.
             *
             * Generated from Godot docs: Light2D.BLEND_MODE_SUB
             */
            val SUB: BlendMode get() = BlendMode(1L)
            /**
             * Mix the value of pixels corresponding to the Light2D to the values of pixels under it by linear
             * interpolation.
             *
             * Generated from Godot docs: Light2D.BLEND_MODE_MIX
             */
            val MIX: BlendMode get() = BlendMode(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Light2D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): Light2D? =
            if (handle.address() == 0L) null else Light2D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_ENABLED_HASH = 2586408642L
        @JvmField
        val setEnabledBind =
            ObjectCalls.getMethodBind("Light2D", "set_enabled", SET_ENABLED_HASH)

        private const val IS_ENABLED_HASH = 36873697L
        @JvmField
        val isEnabledBind =
            ObjectCalls.getMethodBind("Light2D", "is_enabled", IS_ENABLED_HASH)

        private const val SET_EDITOR_ONLY_HASH = 2586408642L
        @JvmField
        val setEditorOnlyBind =
            ObjectCalls.getMethodBind("Light2D", "set_editor_only", SET_EDITOR_ONLY_HASH)

        private const val IS_EDITOR_ONLY_HASH = 36873697L
        @JvmField
        val isEditorOnlyBind =
            ObjectCalls.getMethodBind("Light2D", "is_editor_only", IS_EDITOR_ONLY_HASH)

        private const val SET_COLOR_HASH = 2920490490L
        @JvmField
        val setColorBind =
            ObjectCalls.getMethodBind("Light2D", "set_color", SET_COLOR_HASH)

        private const val GET_COLOR_HASH = 3444240500L
        @JvmField
        val getColorBind =
            ObjectCalls.getMethodBind("Light2D", "get_color", GET_COLOR_HASH)

        private const val SET_ENERGY_HASH = 373806689L
        @JvmField
        val setEnergyBind =
            ObjectCalls.getMethodBind("Light2D", "set_energy", SET_ENERGY_HASH)

        private const val GET_ENERGY_HASH = 1740695150L
        @JvmField
        val getEnergyBind =
            ObjectCalls.getMethodBind("Light2D", "get_energy", GET_ENERGY_HASH)

        private const val SET_Z_RANGE_MIN_HASH = 1286410249L
        @JvmField
        val setZRangeMinBind =
            ObjectCalls.getMethodBind("Light2D", "set_z_range_min", SET_Z_RANGE_MIN_HASH)

        private const val GET_Z_RANGE_MIN_HASH = 3905245786L
        @JvmField
        val getZRangeMinBind =
            ObjectCalls.getMethodBind("Light2D", "get_z_range_min", GET_Z_RANGE_MIN_HASH)

        private const val SET_Z_RANGE_MAX_HASH = 1286410249L
        @JvmField
        val setZRangeMaxBind =
            ObjectCalls.getMethodBind("Light2D", "set_z_range_max", SET_Z_RANGE_MAX_HASH)

        private const val GET_Z_RANGE_MAX_HASH = 3905245786L
        @JvmField
        val getZRangeMaxBind =
            ObjectCalls.getMethodBind("Light2D", "get_z_range_max", GET_Z_RANGE_MAX_HASH)

        private const val SET_LAYER_RANGE_MIN_HASH = 1286410249L
        @JvmField
        val setLayerRangeMinBind =
            ObjectCalls.getMethodBind("Light2D", "set_layer_range_min", SET_LAYER_RANGE_MIN_HASH)

        private const val GET_LAYER_RANGE_MIN_HASH = 3905245786L
        @JvmField
        val getLayerRangeMinBind =
            ObjectCalls.getMethodBind("Light2D", "get_layer_range_min", GET_LAYER_RANGE_MIN_HASH)

        private const val SET_LAYER_RANGE_MAX_HASH = 1286410249L
        @JvmField
        val setLayerRangeMaxBind =
            ObjectCalls.getMethodBind("Light2D", "set_layer_range_max", SET_LAYER_RANGE_MAX_HASH)

        private const val GET_LAYER_RANGE_MAX_HASH = 3905245786L
        @JvmField
        val getLayerRangeMaxBind =
            ObjectCalls.getMethodBind("Light2D", "get_layer_range_max", GET_LAYER_RANGE_MAX_HASH)

        private const val SET_ITEM_CULL_MASK_HASH = 1286410249L
        @JvmField
        val setItemCullMaskBind =
            ObjectCalls.getMethodBind("Light2D", "set_item_cull_mask", SET_ITEM_CULL_MASK_HASH)

        private const val GET_ITEM_CULL_MASK_HASH = 3905245786L
        @JvmField
        val getItemCullMaskBind =
            ObjectCalls.getMethodBind("Light2D", "get_item_cull_mask", GET_ITEM_CULL_MASK_HASH)

        private const val SET_ITEM_SHADOW_CULL_MASK_HASH = 1286410249L
        @JvmField
        val setItemShadowCullMaskBind =
            ObjectCalls.getMethodBind("Light2D", "set_item_shadow_cull_mask", SET_ITEM_SHADOW_CULL_MASK_HASH)

        private const val GET_ITEM_SHADOW_CULL_MASK_HASH = 3905245786L
        @JvmField
        val getItemShadowCullMaskBind =
            ObjectCalls.getMethodBind("Light2D", "get_item_shadow_cull_mask", GET_ITEM_SHADOW_CULL_MASK_HASH)

        private const val SET_SHADOW_ENABLED_HASH = 2586408642L
        @JvmField
        val setShadowEnabledBind =
            ObjectCalls.getMethodBind("Light2D", "set_shadow_enabled", SET_SHADOW_ENABLED_HASH)

        private const val IS_SHADOW_ENABLED_HASH = 36873697L
        @JvmField
        val isShadowEnabledBind =
            ObjectCalls.getMethodBind("Light2D", "is_shadow_enabled", IS_SHADOW_ENABLED_HASH)

        private const val SET_SHADOW_SMOOTH_HASH = 373806689L
        @JvmField
        val setShadowSmoothBind =
            ObjectCalls.getMethodBind("Light2D", "set_shadow_smooth", SET_SHADOW_SMOOTH_HASH)

        private const val GET_SHADOW_SMOOTH_HASH = 1740695150L
        @JvmField
        val getShadowSmoothBind =
            ObjectCalls.getMethodBind("Light2D", "get_shadow_smooth", GET_SHADOW_SMOOTH_HASH)

        private const val SET_SHADOW_FILTER_HASH = 3209356555L
        @JvmField
        val setShadowFilterBind =
            ObjectCalls.getMethodBind("Light2D", "set_shadow_filter", SET_SHADOW_FILTER_HASH)

        private const val GET_SHADOW_FILTER_HASH = 1973619177L
        @JvmField
        val getShadowFilterBind =
            ObjectCalls.getMethodBind("Light2D", "get_shadow_filter", GET_SHADOW_FILTER_HASH)

        private const val SET_SHADOW_COLOR_HASH = 2920490490L
        @JvmField
        val setShadowColorBind =
            ObjectCalls.getMethodBind("Light2D", "set_shadow_color", SET_SHADOW_COLOR_HASH)

        private const val GET_SHADOW_COLOR_HASH = 3444240500L
        @JvmField
        val getShadowColorBind =
            ObjectCalls.getMethodBind("Light2D", "get_shadow_color", GET_SHADOW_COLOR_HASH)

        private const val SET_BLEND_MODE_HASH = 2916638796L
        @JvmField
        val setBlendModeBind =
            ObjectCalls.getMethodBind("Light2D", "set_blend_mode", SET_BLEND_MODE_HASH)

        private const val GET_BLEND_MODE_HASH = 936255250L
        @JvmField
        val getBlendModeBind =
            ObjectCalls.getMethodBind("Light2D", "get_blend_mode", GET_BLEND_MODE_HASH)

        private const val SET_HEIGHT_HASH = 373806689L
        @JvmField
        val setHeightBind =
            ObjectCalls.getMethodBind("Light2D", "set_height", SET_HEIGHT_HASH)

        private const val GET_HEIGHT_HASH = 1740695150L
        @JvmField
        val getHeightBind =
            ObjectCalls.getMethodBind("Light2D", "get_height", GET_HEIGHT_HASH)
    }
}
