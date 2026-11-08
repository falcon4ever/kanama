package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed
import net.multigesture.kanama.types.Color

/**
 * A node to draw lines behind moving objects.
 *
 * Generated from Godot docs: Trail3D
 */
class Trail3D(handle: GodotHandle) : Line3D(handle) {
    var emitting: Boolean
        @JvmName("emittingProperty")
        get() = isEmitting()
        @JvmName("setEmittingProperty")
        set(value) = setEmitting(value)

    var limitMode: Trail3D.LimitMode
        @JvmName("limitModeProperty")
        get() = getLimitMode()
        @JvmName("setLimitModeProperty")
        set(value) = setLimitMode(value)

    var lifetime: Double
        @JvmName("lifetimeProperty")
        get() = getLifetime()
        @JvmName("setLifetimeProperty")
        set(value) = setLifetime(value)

    var maxLength: Double
        @JvmName("maxLengthProperty")
        get() = getMaxLength()
        @JvmName("setMaxLengthProperty")
        set(value) = setMaxLength(value)

    var width: Double
        @JvmName("widthProperty")
        get() = getWidth()
        @JvmName("setWidthProperty")
        set(value) = setWidth(value)

    var widthCurve: Curve?
        @JvmName("widthCurveProperty")
        get() = getWidthCurve()
        @JvmName("setWidthCurveProperty")
        set(value) = setWidthCurve(value)

    var color: Color
        @JvmName("colorProperty")
        get() = getColor()
        @JvmName("setColorProperty")
        set(value) = setColor(value)

    var colorGradient: Gradient?
        @JvmName("colorGradientProperty")
        get() = getColorGradient()
        @JvmName("setColorGradientProperty")
        set(value) = setColorGradient(value)

    var meshAlignment: Line3D.MeshAlignment
        @JvmName("meshAlignmentProperty")
        get() = getMeshAlignment()
        @JvmName("setMeshAlignmentProperty")
        set(value) = setMeshAlignment(value)

    var materialMode: Line3D.MaterialMode
        @JvmName("materialModeProperty")
        get() = getMaterialMode()
        @JvmName("setMaterialModeProperty")
        set(value) = setMaterialMode(value)

    var material: ShaderMaterial?
        @JvmName("materialProperty")
        get() = getMaterial()
        @JvmName("setMaterialProperty")
        set(value) = setMaterial(value)

    var tilingMode: Line3D.TilingMode
        @JvmName("tilingModeProperty")
        get() = getTilingMode()
        @JvmName("setTilingModeProperty")
        set(value) = setTilingMode(value)

    var tilingMultiplier: Double
        @JvmName("tilingMultiplierProperty")
        get() = getTilingMultiplier()
        @JvmName("setTilingMultiplierProperty")
        set(value) = setTilingMultiplier(value)

    var pinUv: Boolean
        @JvmName("pinUvProperty")
        get() = getPinUv()
        @JvmName("setPinUvProperty")
        set(value) = setPinUv(value)

    var minSectionLength: Double
        @JvmName("minSectionLengthProperty")
        get() = getMinSectionLength()
        @JvmName("setMinSectionLengthProperty")
        set(value) = setMinSectionLength(value)

    /**
     * Width of the trail.
     *
     * Generated from Godot docs: Trail3D.set_width
     */
    fun setWidth(width: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setWidthBind, segment, width)
    }

    /**
     * Width of the trail.
     *
     * Generated from Godot docs: Trail3D.get_width
     */
    fun getWidth(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getWidthBind, segment)
    }

    /**
     * Width across the length of the trail.
     *
     * Generated from Godot docs: Trail3D.set_width_curve
     */
    fun setWidthCurve(curve: Curve?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setWidthCurveBind, segment, listOf(curve?.requireOpenHandle() ?: NULL_SEGMENT))
        ObjectCalls.keepReachable(curve)
    }

    /**
     * Width across the length of the trail.
     *
     * Generated from Godot docs: Trail3D.get_width_curve
     */
    fun getWidthCurve(): Curve? {
        return Curve.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getWidthCurveBind, segment))
    }

    /**
     * If `true`, the trail will generate new points from its head. Note: Only one active trail is
     * supported. Setting this property to `true` will cause the existing trail mesh to be cleared.
     *
     * Generated from Godot docs: Trail3D.set_emitting
     */
    fun setEmitting(emitting: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setEmittingBind, segment, emitting)
    }

    /**
     * If `true`, the trail will generate new points from its head. Note: Only one active trail is
     * supported. Setting this property to `true` will cause the existing trail mesh to be cleared.
     *
     * Generated from Godot docs: Trail3D.is_emitting
     */
    fun isEmitting(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isEmittingBind, segment)
    }

    /**
     * The color of the trail. Note: Colors with values above `1.0` (overbright) are not supported.
     * Note: To have a visible effect on a `BaseMaterial3D`,
     * `BaseMaterial3D.vertex_color_use_as_albedo` must be `true`. For a `ShaderMaterial`, `ALBEDO *=
     * COLOR.rgb;` must be inserted in the shader's `fragment()` function. Otherwise, `color` will have
     * no visible effect.
     *
     * Generated from Godot docs: Trail3D.set_color
     */
    fun setColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(Binds.setColorBind, segment, color)
    }

    /**
     * The color of the trail. Note: Colors with values above `1.0` (overbright) are not supported.
     * Note: To have a visible effect on a `BaseMaterial3D`,
     * `BaseMaterial3D.vertex_color_use_as_albedo` must be `true`. For a `ShaderMaterial`, `ALBEDO *=
     * COLOR.rgb;` must be inserted in the shader's `fragment()` function. Otherwise, `color` will have
     * no visible effect.
     *
     * Generated from Godot docs: Trail3D.get_color
     */
    fun getColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(Binds.getColorBind, segment)
    }

    /**
     * The color gradient over the trail length. Note: Colors with values above `1.0` (overbright) are
     * not supported. Note: To have a visible effect on a `BaseMaterial3D`,
     * `BaseMaterial3D.vertex_color_use_as_albedo` must be `true`. For a `ShaderMaterial`, `ALBEDO *=
     * COLOR.rgb;` must be inserted in the shader's `fragment()` function. Otherwise, `color` will have
     * no visible effect.
     *
     * Generated from Godot docs: Trail3D.set_color_gradient
     */
    fun setColorGradient(gradient: Gradient?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setColorGradientBind, segment, listOf(gradient?.requireOpenHandle() ?: NULL_SEGMENT))
        ObjectCalls.keepReachable(gradient)
    }

    /**
     * The color gradient over the trail length. Note: Colors with values above `1.0` (overbright) are
     * not supported. Note: To have a visible effect on a `BaseMaterial3D`,
     * `BaseMaterial3D.vertex_color_use_as_albedo` must be `true`. For a `ShaderMaterial`, `ALBEDO *=
     * COLOR.rgb;` must be inserted in the shader's `fragment()` function. Otherwise, `color` will have
     * no visible effect.
     *
     * Generated from Godot docs: Trail3D.get_color_gradient
     */
    fun getColorGradient(): Gradient? {
        return Gradient.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getColorGradientBind, segment))
    }

    /**
     * The material mode for the trail.
     *
     * Generated from Godot docs: Trail3D.set_material_mode
     */
    fun setMaterialMode(materialMode: Line3D.MaterialMode) {
        ObjectCalls.ptrcallWithLongArg(Binds.setMaterialModeBind, segment, materialMode.value)
    }

    /**
     * The material mode for the trail.
     *
     * Generated from Godot docs: Trail3D.get_material_mode
     */
    fun getMaterialMode(): Line3D.MaterialMode {
        return Line3D.MaterialMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getMaterialModeBind, segment))
    }

    /**
     * The material to assign to the trail mesh when `material_mode` is `Line3D.MaterialMode.CUSTOM`.
     *
     * Generated from Godot docs: Trail3D.set_material
     */
    fun setMaterial(material: ShaderMaterial?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setMaterialBind, segment, listOf(material?.requireOpenHandle() ?: NULL_SEGMENT))
        ObjectCalls.keepReachable(material)
    }

    /**
     * The material to assign to the trail mesh when `material_mode` is `Line3D.MaterialMode.CUSTOM`.
     *
     * Generated from Godot docs: Trail3D.get_material
     */
    fun getMaterial(): ShaderMaterial? {
        return ShaderMaterial.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getMaterialBind, segment))
    }

    /**
     * Alignment of the trail. Possible options are `Line3D.MeshAlignment.LOCAL` for a 3D line, and
     * `Line3D.MeshAlignment.BILLBOARD` for a billboarded line.
     *
     * Generated from Godot docs: Trail3D.set_mesh_alignment
     */
    fun setMeshAlignment(alignment: Line3D.MeshAlignment) {
        ObjectCalls.ptrcallWithLongArg(Binds.setMeshAlignmentBind, segment, alignment.value)
    }

    /**
     * Alignment of the trail. Possible options are `Line3D.MeshAlignment.LOCAL` for a 3D line, and
     * `Line3D.MeshAlignment.BILLBOARD` for a billboarded line.
     *
     * Generated from Godot docs: Trail3D.get_mesh_alignment
     */
    fun getMeshAlignment(): Line3D.MeshAlignment {
        return Line3D.MeshAlignment(ObjectCalls.ptrcallNoArgsRetLong(Binds.getMeshAlignmentBind, segment))
    }

    /**
     * UV multiplier across the length of the trail.
     *
     * Generated from Godot docs: Trail3D.set_tiling_multiplier
     */
    fun setTilingMultiplier(tilingMultiplier: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setTilingMultiplierBind, segment, tilingMultiplier)
    }

    /**
     * UV multiplier across the length of the trail.
     *
     * Generated from Godot docs: Trail3D.get_tiling_multiplier
     */
    fun getTilingMultiplier(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getTilingMultiplierBind, segment)
    }

    /**
     * How to tile the UVs across the length of the trail.
     *
     * Generated from Godot docs: Trail3D.set_tiling_mode
     */
    fun setTilingMode(tilingMode: Line3D.TilingMode) {
        ObjectCalls.ptrcallWithLongArg(Binds.setTilingModeBind, segment, tilingMode.value)
    }

    /**
     * How to tile the UVs across the length of the trail.
     *
     * Generated from Godot docs: Trail3D.get_tiling_mode
     */
    fun getTilingMode(): Line3D.TilingMode {
        return Line3D.TilingMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getTilingModeBind, segment))
    }

    /**
     * Clears the trail.
     *
     * Generated from Godot docs: Trail3D.clear
     */
    fun clear() {
        ObjectCalls.ptrcallNoArgs(Binds.clearBind, segment)
    }

    /**
     * Minimum required distance before adding a new section.
     *
     * Generated from Godot docs: Trail3D.set_min_section_length
     */
    fun setMinSectionLength(minSectionLength: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setMinSectionLengthBind, segment, minSectionLength)
    }

    /**
     * Minimum required distance before adding a new section.
     *
     * Generated from Godot docs: Trail3D.get_min_section_length
     */
    fun getMinSectionLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getMinSectionLengthBind, segment)
    }

    /**
     * Whether to limit the trail by lifetime or length.
     *
     * Generated from Godot docs: Trail3D.set_limit_mode
     */
    fun setLimitMode(limitMode: Trail3D.LimitMode) {
        ObjectCalls.ptrcallWithLongArg(Binds.setLimitModeBind, segment, limitMode.value)
    }

    /**
     * Whether to limit the trail by lifetime or length.
     *
     * Generated from Godot docs: Trail3D.get_limit_mode
     */
    fun getLimitMode(): Trail3D.LimitMode {
        return Trail3D.LimitMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getLimitModeBind, segment))
    }

    /**
     * The lifetime of the trail when `limit_mode` is `LimitMode.LIFETIME`.
     *
     * Generated from Godot docs: Trail3D.set_lifetime
     */
    fun setLifetime(lifetime: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setLifetimeBind, segment, lifetime)
    }

    /**
     * The lifetime of the trail when `limit_mode` is `LimitMode.LIFETIME`.
     *
     * Generated from Godot docs: Trail3D.get_lifetime
     */
    fun getLifetime(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getLifetimeBind, segment)
    }

    /**
     * The maximum length of the trail when `limit_mode` is `LimitMode.MAX_LENGTH`.
     *
     * Generated from Godot docs: Trail3D.set_max_length
     */
    fun setMaxLength(maxLength: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setMaxLengthBind, segment, maxLength)
    }

    /**
     * The maximum length of the trail when `limit_mode` is `LimitMode.MAX_LENGTH`.
     *
     * Generated from Godot docs: Trail3D.get_max_length
     */
    fun getMaxLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getMaxLengthBind, segment)
    }

    /**
     * If `true`, pins the UVs to their positions in space.
     *
     * Generated from Godot docs: Trail3D.set_pin_uv
     */
    fun setPinUv(pinUv: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setPinUvBind, segment, pinUv)
    }

    /**
     * If `true`, pins the UVs to their positions in space.
     *
     * Generated from Godot docs: Trail3D.get_pin_uv
     */
    fun getPinUv(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getPinUvBind, segment)
    }

    /**
     * Returns the current length of the trail.
     *
     * Generated from Godot docs: Trail3D.get_current_length
     */
    fun getCurrentLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getCurrentLengthBind, segment)
    }

    /**
     * Godot's `Trail3D.LimitMode` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`Trail3D.LimitMode.<NAME>`).
     *
     * Generated from Godot docs: Trail3D.LimitMode
     */
    @JvmInline
    value class LimitMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Limit the trail length in time.
             *
             * Generated from Godot docs: Trail3D.LIMIT_MODE_LIFETIME
             */
            val LIFETIME: LimitMode get() = LimitMode(0L)
            /**
             * Limit the trail length in size.
             *
             * Generated from Godot docs: Trail3D.LIMIT_MODE_MAX_LENGTH
             */
            val MAX_LENGTH: LimitMode get() = LimitMode(1L)
            /**
             * Represents the size of the `LimitMode` enum.
             *
             * Generated from Godot docs: Trail3D.LIMIT_MODE_MAX
             */
            val MAX: LimitMode get() = LimitMode(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Trail3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): Trail3D? =
            if (handle.address() == 0L) null else Trail3D(GodotHandle(handle))

        /** A new Trail3D, GDScript's `Trail3D.new()`. Add it to the tree, or `queueFree()` it. */
        @JvmStatic
        fun create(): Trail3D =
            Trail3D(GodotHandle(requireConstructed(ObjectCalls.constructObject("Trail3D"), "Trail3D")))
    }

    private object Binds {
        private const val SET_WIDTH_HASH = 373806689L
        @JvmField
        val setWidthBind =
            ObjectCalls.getMethodBind("Trail3D", "set_width", SET_WIDTH_HASH)

        private const val GET_WIDTH_HASH = 1740695150L
        @JvmField
        val getWidthBind =
            ObjectCalls.getMethodBind("Trail3D", "get_width", GET_WIDTH_HASH)

        private const val SET_WIDTH_CURVE_HASH = 270443179L
        @JvmField
        val setWidthCurveBind =
            ObjectCalls.getMethodBind("Trail3D", "set_width_curve", SET_WIDTH_CURVE_HASH)

        private const val GET_WIDTH_CURVE_HASH = 2460114913L
        @JvmField
        val getWidthCurveBind =
            ObjectCalls.getMethodBind("Trail3D", "get_width_curve", GET_WIDTH_CURVE_HASH)

        private const val SET_EMITTING_HASH = 2586408642L
        @JvmField
        val setEmittingBind =
            ObjectCalls.getMethodBind("Trail3D", "set_emitting", SET_EMITTING_HASH)

        private const val IS_EMITTING_HASH = 36873697L
        @JvmField
        val isEmittingBind =
            ObjectCalls.getMethodBind("Trail3D", "is_emitting", IS_EMITTING_HASH)

        private const val SET_COLOR_HASH = 2920490490L
        @JvmField
        val setColorBind =
            ObjectCalls.getMethodBind("Trail3D", "set_color", SET_COLOR_HASH)

        private const val GET_COLOR_HASH = 3444240500L
        @JvmField
        val getColorBind =
            ObjectCalls.getMethodBind("Trail3D", "get_color", GET_COLOR_HASH)

        private const val SET_COLOR_GRADIENT_HASH = 2756054477L
        @JvmField
        val setColorGradientBind =
            ObjectCalls.getMethodBind("Trail3D", "set_color_gradient", SET_COLOR_GRADIENT_HASH)

        private const val GET_COLOR_GRADIENT_HASH = 132272999L
        @JvmField
        val getColorGradientBind =
            ObjectCalls.getMethodBind("Trail3D", "get_color_gradient", GET_COLOR_GRADIENT_HASH)

        private const val SET_MATERIAL_MODE_HASH = 2165032565L
        @JvmField
        val setMaterialModeBind =
            ObjectCalls.getMethodBind("Trail3D", "set_material_mode", SET_MATERIAL_MODE_HASH)

        private const val GET_MATERIAL_MODE_HASH = 3629890196L
        @JvmField
        val getMaterialModeBind =
            ObjectCalls.getMethodBind("Trail3D", "get_material_mode", GET_MATERIAL_MODE_HASH)

        private const val SET_MATERIAL_HASH = 1356176850L
        @JvmField
        val setMaterialBind =
            ObjectCalls.getMethodBind("Trail3D", "set_material", SET_MATERIAL_HASH)

        private const val GET_MATERIAL_HASH = 953001827L
        @JvmField
        val getMaterialBind =
            ObjectCalls.getMethodBind("Trail3D", "get_material", GET_MATERIAL_HASH)

        private const val SET_MESH_ALIGNMENT_HASH = 3254932473L
        @JvmField
        val setMeshAlignmentBind =
            ObjectCalls.getMethodBind("Trail3D", "set_mesh_alignment", SET_MESH_ALIGNMENT_HASH)

        private const val GET_MESH_ALIGNMENT_HASH = 603151927L
        @JvmField
        val getMeshAlignmentBind =
            ObjectCalls.getMethodBind("Trail3D", "get_mesh_alignment", GET_MESH_ALIGNMENT_HASH)

        private const val SET_TILING_MULTIPLIER_HASH = 373806689L
        @JvmField
        val setTilingMultiplierBind =
            ObjectCalls.getMethodBind("Trail3D", "set_tiling_multiplier", SET_TILING_MULTIPLIER_HASH)

        private const val GET_TILING_MULTIPLIER_HASH = 1740695150L
        @JvmField
        val getTilingMultiplierBind =
            ObjectCalls.getMethodBind("Trail3D", "get_tiling_multiplier", GET_TILING_MULTIPLIER_HASH)

        private const val SET_TILING_MODE_HASH = 3698560511L
        @JvmField
        val setTilingModeBind =
            ObjectCalls.getMethodBind("Trail3D", "set_tiling_mode", SET_TILING_MODE_HASH)

        private const val GET_TILING_MODE_HASH = 1009472955L
        @JvmField
        val getTilingModeBind =
            ObjectCalls.getMethodBind("Trail3D", "get_tiling_mode", GET_TILING_MODE_HASH)

        private const val CLEAR_HASH = 3218959716L
        @JvmField
        val clearBind =
            ObjectCalls.getMethodBind("Trail3D", "clear", CLEAR_HASH)

        private const val SET_MIN_SECTION_LENGTH_HASH = 373806689L
        @JvmField
        val setMinSectionLengthBind =
            ObjectCalls.getMethodBind("Trail3D", "set_min_section_length", SET_MIN_SECTION_LENGTH_HASH)

        private const val GET_MIN_SECTION_LENGTH_HASH = 1740695150L
        @JvmField
        val getMinSectionLengthBind =
            ObjectCalls.getMethodBind("Trail3D", "get_min_section_length", GET_MIN_SECTION_LENGTH_HASH)

        private const val SET_LIMIT_MODE_HASH = 4181898111L
        @JvmField
        val setLimitModeBind =
            ObjectCalls.getMethodBind("Trail3D", "set_limit_mode", SET_LIMIT_MODE_HASH)

        private const val GET_LIMIT_MODE_HASH = 2679766905L
        @JvmField
        val getLimitModeBind =
            ObjectCalls.getMethodBind("Trail3D", "get_limit_mode", GET_LIMIT_MODE_HASH)

        private const val SET_LIFETIME_HASH = 373806689L
        @JvmField
        val setLifetimeBind =
            ObjectCalls.getMethodBind("Trail3D", "set_lifetime", SET_LIFETIME_HASH)

        private const val GET_LIFETIME_HASH = 1740695150L
        @JvmField
        val getLifetimeBind =
            ObjectCalls.getMethodBind("Trail3D", "get_lifetime", GET_LIFETIME_HASH)

        private const val SET_MAX_LENGTH_HASH = 373806689L
        @JvmField
        val setMaxLengthBind =
            ObjectCalls.getMethodBind("Trail3D", "set_max_length", SET_MAX_LENGTH_HASH)

        private const val GET_MAX_LENGTH_HASH = 1740695150L
        @JvmField
        val getMaxLengthBind =
            ObjectCalls.getMethodBind("Trail3D", "get_max_length", GET_MAX_LENGTH_HASH)

        private const val SET_PIN_UV_HASH = 2586408642L
        @JvmField
        val setPinUvBind =
            ObjectCalls.getMethodBind("Trail3D", "set_pin_uv", SET_PIN_UV_HASH)

        private const val GET_PIN_UV_HASH = 36873697L
        @JvmField
        val getPinUvBind =
            ObjectCalls.getMethodBind("Trail3D", "get_pin_uv", GET_PIN_UV_HASH)

        private const val GET_CURRENT_LENGTH_HASH = 1740695150L
        @JvmField
        val getCurrentLengthBind =
            ObjectCalls.getMethodBind("Trail3D", "get_current_length", GET_CURRENT_LENGTH_HASH)
    }
}
