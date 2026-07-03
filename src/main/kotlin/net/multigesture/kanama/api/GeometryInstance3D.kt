package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.AABB

/**
 * Base node for geometry-based visual instances.
 *
 * Generated from Godot docs: GeometryInstance3D
 */
open class GeometryInstance3D(handle: MemorySegment) : VisualInstance3D(handle) {
    var materialOverride: Material?
        @JvmName("materialOverrideProperty")
        get() = getMaterialOverride()
        @JvmName("setMaterialOverrideProperty")
        set(value) = setMaterialOverride(value)

    var materialOverlay: Material?
        @JvmName("materialOverlayProperty")
        get() = getMaterialOverlay()
        @JvmName("setMaterialOverlayProperty")
        set(value) = setMaterialOverlay(value)

    var transparency: Double
        @JvmName("transparencyProperty")
        get() = getTransparency()
        @JvmName("setTransparencyProperty")
        set(value) = setTransparency(value)

    var castShadow: Long
        @JvmName("castShadowProperty")
        get() = getCastShadowsSetting()
        @JvmName("setCastShadowProperty")
        set(value) = setCastShadowsSetting(value)

    var extraCullMargin: Double
        @JvmName("extraCullMarginProperty")
        get() = getExtraCullMargin()
        @JvmName("setExtraCullMarginProperty")
        set(value) = setExtraCullMargin(value)

    var customAabb: AABB
        @JvmName("customAabbProperty")
        get() = getCustomAabb()
        @JvmName("setCustomAabbProperty")
        set(value) = setCustomAabb(value)

    var lodBias: Double
        @JvmName("lodBiasProperty")
        get() = getLodBias()
        @JvmName("setLodBiasProperty")
        set(value) = setLodBias(value)

    var ignoreOcclusionCulling: Boolean
        @JvmName("ignoreOcclusionCullingProperty")
        get() = isIgnoringOcclusionCulling()
        @JvmName("setIgnoreOcclusionCullingProperty")
        set(value) = setIgnoreOcclusionCulling(value)

    var giMode: Long
        @JvmName("giModeProperty")
        get() = getGiMode()
        @JvmName("setGiModeProperty")
        set(value) = setGiMode(value)

    var giLightmapTexelScale: Double
        @JvmName("giLightmapTexelScaleProperty")
        get() = getLightmapTexelScale()
        @JvmName("setGiLightmapTexelScaleProperty")
        set(value) = setLightmapTexelScale(value)

    var giLightmapScale: Long
        @JvmName("giLightmapScaleProperty")
        get() = getLightmapScale()
        @JvmName("setGiLightmapScaleProperty")
        set(value) = setLightmapScale(value)

    var visibilityRangeBegin: Double
        @JvmName("visibilityRangeBeginProperty")
        get() = getVisibilityRangeBegin()
        @JvmName("setVisibilityRangeBeginProperty")
        set(value) = setVisibilityRangeBegin(value)

    var visibilityRangeBeginMargin: Double
        @JvmName("visibilityRangeBeginMarginProperty")
        get() = getVisibilityRangeBeginMargin()
        @JvmName("setVisibilityRangeBeginMarginProperty")
        set(value) = setVisibilityRangeBeginMargin(value)

    var visibilityRangeEnd: Double
        @JvmName("visibilityRangeEndProperty")
        get() = getVisibilityRangeEnd()
        @JvmName("setVisibilityRangeEndProperty")
        set(value) = setVisibilityRangeEnd(value)

    var visibilityRangeEndMargin: Double
        @JvmName("visibilityRangeEndMarginProperty")
        get() = getVisibilityRangeEndMargin()
        @JvmName("setVisibilityRangeEndMarginProperty")
        set(value) = setVisibilityRangeEndMargin(value)

    var visibilityRangeFadeMode: Long
        @JvmName("visibilityRangeFadeModeProperty")
        get() = getVisibilityRangeFadeMode()
        @JvmName("setVisibilityRangeFadeModeProperty")
        set(value) = setVisibilityRangeFadeMode(value)

    /**
     * The material override for the whole geometry. If a material is assigned to this property, it
     * will be used instead of any material set in any material slot of the mesh.
     *
     * Generated from Godot docs: GeometryInstance3D.set_material_override
     */
    fun setMaterialOverride(material: Material?) {
        ObjectCalls.ptrcallWithObjectArgs(setMaterialOverrideBind, handle, listOf(material?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * The material override for the whole geometry. If a material is assigned to this property, it
     * will be used instead of any material set in any material slot of the mesh.
     *
     * Generated from Godot docs: GeometryInstance3D.get_material_override
     */
    fun getMaterialOverride(): Material? {
        return Material.wrap(ObjectCalls.ptrcallNoArgsRetObject(getMaterialOverrideBind, handle))
    }

    /**
     * The material overlay for the whole geometry. If a material is assigned to this property, it will
     * be rendered on top of any other active material for all the surfaces.
     *
     * Generated from Godot docs: GeometryInstance3D.set_material_overlay
     */
    fun setMaterialOverlay(material: Material?) {
        ObjectCalls.ptrcallWithObjectArgs(setMaterialOverlayBind, handle, listOf(material?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * The material overlay for the whole geometry. If a material is assigned to this property, it will
     * be rendered on top of any other active material for all the surfaces.
     *
     * Generated from Godot docs: GeometryInstance3D.get_material_overlay
     */
    fun getMaterialOverlay(): Material? {
        return Material.wrap(ObjectCalls.ptrcallNoArgsRetObject(getMaterialOverlayBind, handle))
    }

    /**
     * The mode used to cast shadows from this instance.
     *
     * Generated from Godot docs: GeometryInstance3D.set_cast_shadows_setting
     */
    fun setCastShadowsSetting(shadowCastingSetting: Long) {
        ObjectCalls.ptrcallWithLongArg(setCastShadowsSettingBind, handle, shadowCastingSetting)
    }

    /**
     * The mode used to cast shadows from this instance.
     *
     * Generated from Godot docs: GeometryInstance3D.get_cast_shadows_setting
     */
    fun getCastShadowsSetting(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getCastShadowsSettingBind, handle)
    }

    /**
     * Changes how quickly the mesh transitions to a lower level of detail. A value of 0 will force the
     * mesh to its lowest level of detail, a value of 1 will use the default settings, and larger
     * values will keep the mesh in a higher level of detail at farther distances. Useful for testing
     * level of detail transitions in the editor.
     *
     * Generated from Godot docs: GeometryInstance3D.set_lod_bias
     */
    fun setLodBias(bias: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setLodBiasBind, handle, bias)
    }

    /**
     * Changes how quickly the mesh transitions to a lower level of detail. A value of 0 will force the
     * mesh to its lowest level of detail, a value of 1 will use the default settings, and larger
     * values will keep the mesh in a higher level of detail at farther distances. Useful for testing
     * level of detail transitions in the editor.
     *
     * Generated from Godot docs: GeometryInstance3D.get_lod_bias
     */
    fun getLodBias(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getLodBiasBind, handle)
    }

    /**
     * The transparency applied to the whole geometry (as a multiplier of the materials' existing
     * transparency). `0.0` is fully opaque, while `1.0` is fully transparent. Values greater than
     * `0.0` (exclusive) will force the geometry's materials to go through the transparent pipeline,
     * which is slower to render and can exhibit rendering issues due to incorrect transparency
     * sorting. However, unlike using a transparent material, setting `transparency` to a value greater
     * than `0.0` (exclusive) will not disable shadow rendering. In spatial shaders, `1.0 -
     * transparency` is set as the default value of the `ALPHA` built-in. Note: `transparency` is
     * clamped between `0.0` and `1.0`, so this property cannot be used to make transparent materials
     * more opaque than they originally are. Note: Only supported when using the Forward+ rendering
     * method. When using the Mobile or Compatibility rendering method, `transparency` is ignored and
     * is considered as always being `0.0`.
     *
     * Generated from Godot docs: GeometryInstance3D.set_transparency
     */
    fun setTransparency(transparency: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTransparencyBind, handle, transparency)
    }

    /**
     * The transparency applied to the whole geometry (as a multiplier of the materials' existing
     * transparency). `0.0` is fully opaque, while `1.0` is fully transparent. Values greater than
     * `0.0` (exclusive) will force the geometry's materials to go through the transparent pipeline,
     * which is slower to render and can exhibit rendering issues due to incorrect transparency
     * sorting. However, unlike using a transparent material, setting `transparency` to a value greater
     * than `0.0` (exclusive) will not disable shadow rendering. In spatial shaders, `1.0 -
     * transparency` is set as the default value of the `ALPHA` built-in. Note: `transparency` is
     * clamped between `0.0` and `1.0`, so this property cannot be used to make transparent materials
     * more opaque than they originally are. Note: Only supported when using the Forward+ rendering
     * method. When using the Mobile or Compatibility rendering method, `transparency` is ignored and
     * is considered as always being `0.0`.
     *
     * Generated from Godot docs: GeometryInstance3D.get_transparency
     */
    fun getTransparency(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTransparencyBind, handle)
    }

    /**
     * Margin for the `visibility_range_end` threshold. The GeometryInstance3D will only change its
     * visibility state when it goes over or under the `visibility_range_end` threshold by this amount.
     * If `visibility_range_fade_mode` is `VISIBILITY_RANGE_FADE_DISABLED`, this acts as a hysteresis
     * distance. If `visibility_range_fade_mode` is `VISIBILITY_RANGE_FADE_SELF` or
     * `VISIBILITY_RANGE_FADE_DEPENDENCIES`, this acts as a fade transition distance and must be set to
     * a value greater than `0.0` for the effect to be noticeable.
     *
     * Generated from Godot docs: GeometryInstance3D.set_visibility_range_end_margin
     */
    fun setVisibilityRangeEndMargin(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVisibilityRangeEndMarginBind, handle, distance)
    }

    /**
     * Margin for the `visibility_range_end` threshold. The GeometryInstance3D will only change its
     * visibility state when it goes over or under the `visibility_range_end` threshold by this amount.
     * If `visibility_range_fade_mode` is `VISIBILITY_RANGE_FADE_DISABLED`, this acts as a hysteresis
     * distance. If `visibility_range_fade_mode` is `VISIBILITY_RANGE_FADE_SELF` or
     * `VISIBILITY_RANGE_FADE_DEPENDENCIES`, this acts as a fade transition distance and must be set to
     * a value greater than `0.0` for the effect to be noticeable.
     *
     * Generated from Godot docs: GeometryInstance3D.get_visibility_range_end_margin
     */
    fun getVisibilityRangeEndMargin(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVisibilityRangeEndMarginBind, handle)
    }

    /**
     * Distance from which the GeometryInstance3D will be hidden, taking `visibility_range_end_margin`
     * into account as well. The default value of 0 is used to disable the range check.
     *
     * Generated from Godot docs: GeometryInstance3D.set_visibility_range_end
     */
    fun setVisibilityRangeEnd(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVisibilityRangeEndBind, handle, distance)
    }

    /**
     * Distance from which the GeometryInstance3D will be hidden, taking `visibility_range_end_margin`
     * into account as well. The default value of 0 is used to disable the range check.
     *
     * Generated from Godot docs: GeometryInstance3D.get_visibility_range_end
     */
    fun getVisibilityRangeEnd(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVisibilityRangeEndBind, handle)
    }

    /**
     * Margin for the `visibility_range_begin` threshold. The GeometryInstance3D will only change its
     * visibility state when it goes over or under the `visibility_range_begin` threshold by this
     * amount. If `visibility_range_fade_mode` is `VISIBILITY_RANGE_FADE_DISABLED`, this acts as a
     * hysteresis distance. If `visibility_range_fade_mode` is `VISIBILITY_RANGE_FADE_SELF` or
     * `VISIBILITY_RANGE_FADE_DEPENDENCIES`, this acts as a fade transition distance and must be set to
     * a value greater than `0.0` for the effect to be noticeable.
     *
     * Generated from Godot docs: GeometryInstance3D.set_visibility_range_begin_margin
     */
    fun setVisibilityRangeBeginMargin(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVisibilityRangeBeginMarginBind, handle, distance)
    }

    /**
     * Margin for the `visibility_range_begin` threshold. The GeometryInstance3D will only change its
     * visibility state when it goes over or under the `visibility_range_begin` threshold by this
     * amount. If `visibility_range_fade_mode` is `VISIBILITY_RANGE_FADE_DISABLED`, this acts as a
     * hysteresis distance. If `visibility_range_fade_mode` is `VISIBILITY_RANGE_FADE_SELF` or
     * `VISIBILITY_RANGE_FADE_DEPENDENCIES`, this acts as a fade transition distance and must be set to
     * a value greater than `0.0` for the effect to be noticeable.
     *
     * Generated from Godot docs: GeometryInstance3D.get_visibility_range_begin_margin
     */
    fun getVisibilityRangeBeginMargin(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVisibilityRangeBeginMarginBind, handle)
    }

    /**
     * Starting distance from which the GeometryInstance3D will be visible, taking
     * `visibility_range_begin_margin` into account as well. The default value of 0 is used to disable
     * the range check.
     *
     * Generated from Godot docs: GeometryInstance3D.set_visibility_range_begin
     */
    fun setVisibilityRangeBegin(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVisibilityRangeBeginBind, handle, distance)
    }

    /**
     * Starting distance from which the GeometryInstance3D will be visible, taking
     * `visibility_range_begin_margin` into account as well. The default value of 0 is used to disable
     * the range check.
     *
     * Generated from Godot docs: GeometryInstance3D.get_visibility_range_begin
     */
    fun getVisibilityRangeBegin(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVisibilityRangeBeginBind, handle)
    }

    /**
     * Controls which instances will be faded when approaching the limits of the visibility range.
     *
     * Generated from Godot docs: GeometryInstance3D.set_visibility_range_fade_mode
     */
    fun setVisibilityRangeFadeMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setVisibilityRangeFadeModeBind, handle, mode)
    }

    /**
     * Controls which instances will be faded when approaching the limits of the visibility range.
     *
     * Generated from Godot docs: GeometryInstance3D.get_visibility_range_fade_mode
     */
    fun getVisibilityRangeFadeMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getVisibilityRangeFadeModeBind, handle)
    }

    /**
     * Set the value of a shader uniform for this instance only (per-instance uniform
     * ($DOCS_URL/tutorials/shaders/shader_reference/shading_language.html#per-instance-uniforms)). See
     * also `ShaderMaterial.set_shader_parameter` to assign a uniform on all instances using the same
     * `ShaderMaterial`. Note: For a shader uniform to be assignable on a per-instance basis, it must
     * be defined with `instance uniform ...` rather than `uniform ...` in the shader code. Note:
     * `name` is case-sensitive and must match the name of the uniform in the code exactly (not the
     * capitalized name in the inspector). Note: Per-instance shader uniforms are only available in
     * Spatial and CanvasItem shaders, but not for Fog, Sky, or Particles shaders.
     *
     * Generated from Godot docs: GeometryInstance3D.set_instance_shader_parameter
     */
    fun setInstanceShaderParameter(name: String, value: Any?) {
        ObjectCalls.ptrcallWithStringNameAndVariantArg(setInstanceShaderParameterBind, handle, name, value)
    }

    /**
     * Get the value of a shader parameter as set on this instance.
     *
     * Generated from Godot docs: GeometryInstance3D.get_instance_shader_parameter
     */
    fun getInstanceShaderParameter(name: String): Any? {
        return ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(getInstanceShaderParameterBind, handle, name)
    }

    /**
     * The extra distance added to the GeometryInstance3D's bounding box (`AABB`) to increase its cull
     * box.
     *
     * Generated from Godot docs: GeometryInstance3D.set_extra_cull_margin
     */
    fun setExtraCullMargin(margin: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setExtraCullMarginBind, handle, margin)
    }

    /**
     * The extra distance added to the GeometryInstance3D's bounding box (`AABB`) to increase its cull
     * box.
     *
     * Generated from Godot docs: GeometryInstance3D.get_extra_cull_margin
     */
    fun getExtraCullMargin(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getExtraCullMarginBind, handle)
    }

    /**
     * The texel density to use for lightmapping in `LightmapGI`. Greater scale values provide higher
     * resolution in the lightmap, which can result in sharper shadows for lights that have both direct
     * and indirect light baked. However, greater scale values will also increase the space taken by
     * the mesh in the lightmap texture, which increases the memory, storage, and bake time
     * requirements. When using a single mesh at different scales, consider adjusting this value to
     * keep the lightmap texel density consistent across meshes. For example, doubling
     * `gi_lightmap_texel_scale` doubles the lightmap texture resolution for this object on each axis,
     * so it will quadruple the texel count.
     *
     * Generated from Godot docs: GeometryInstance3D.set_lightmap_texel_scale
     */
    fun setLightmapTexelScale(scale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setLightmapTexelScaleBind, handle, scale)
    }

    /**
     * The texel density to use for lightmapping in `LightmapGI`. Greater scale values provide higher
     * resolution in the lightmap, which can result in sharper shadows for lights that have both direct
     * and indirect light baked. However, greater scale values will also increase the space taken by
     * the mesh in the lightmap texture, which increases the memory, storage, and bake time
     * requirements. When using a single mesh at different scales, consider adjusting this value to
     * keep the lightmap texel density consistent across meshes. For example, doubling
     * `gi_lightmap_texel_scale` doubles the lightmap texture resolution for this object on each axis,
     * so it will quadruple the texel count.
     *
     * Generated from Godot docs: GeometryInstance3D.get_lightmap_texel_scale
     */
    fun getLightmapTexelScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getLightmapTexelScaleBind, handle)
    }

    /**
     * The texel density to use for lightmapping in `LightmapGI`.
     *
     * Generated from Godot docs: GeometryInstance3D.set_lightmap_scale
     */
    fun setLightmapScale(scale: Long) {
        ObjectCalls.ptrcallWithLongArg(setLightmapScaleBind, handle, scale)
    }

    /**
     * The texel density to use for lightmapping in `LightmapGI`.
     *
     * Generated from Godot docs: GeometryInstance3D.get_lightmap_scale
     */
    fun getLightmapScale(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getLightmapScaleBind, handle)
    }

    /**
     * The global illumination mode to use for the whole geometry. To avoid inconsistent results, use a
     * mode that matches the purpose of the mesh during gameplay (static/dynamic). Note: Lights' bake
     * mode will also affect the global illumination rendering. See `Light3D.light_bake_mode`.
     *
     * Generated from Godot docs: GeometryInstance3D.set_gi_mode
     */
    fun setGiMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setGiModeBind, handle, mode)
    }

    /**
     * The global illumination mode to use for the whole geometry. To avoid inconsistent results, use a
     * mode that matches the purpose of the mesh during gameplay (static/dynamic). Note: Lights' bake
     * mode will also affect the global illumination rendering. See `Light3D.light_bake_mode`.
     *
     * Generated from Godot docs: GeometryInstance3D.get_gi_mode
     */
    fun getGiMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getGiModeBind, handle)
    }

    /**
     * If `true`, disables occlusion culling for this instance. Useful for gizmos that must be rendered
     * even when occlusion culling is in use. Note: `ignore_occlusion_culling` does not affect frustum
     * culling (which is what happens when an object is not visible given the camera's angle). To avoid
     * frustum culling, set `custom_aabb` to a very large AABB that covers your entire game world such
     * as `AABB(-10000, -10000, -10000, 20000, 20000, 20000)`.
     *
     * Generated from Godot docs: GeometryInstance3D.set_ignore_occlusion_culling
     */
    fun setIgnoreOcclusionCulling(ignoreCulling: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setIgnoreOcclusionCullingBind, handle, ignoreCulling)
    }

    /**
     * If `true`, disables occlusion culling for this instance. Useful for gizmos that must be rendered
     * even when occlusion culling is in use. Note: `ignore_occlusion_culling` does not affect frustum
     * culling (which is what happens when an object is not visible given the camera's angle). To avoid
     * frustum culling, set `custom_aabb` to a very large AABB that covers your entire game world such
     * as `AABB(-10000, -10000, -10000, 20000, 20000, 20000)`.
     *
     * Generated from Godot docs: GeometryInstance3D.is_ignoring_occlusion_culling
     */
    fun isIgnoringOcclusionCulling(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isIgnoringOcclusionCullingBind, handle)
    }

    /**
     * Overrides the bounding box of this node with a custom one. This can be used to avoid the
     * expensive `AABB` recalculation that happens when a skeleton is used with a `MeshInstance3D` or
     * to have precise control over the `MeshInstance3D`'s bounding box. To use the default AABB, set
     * value to an `AABB` with all fields set to `0.0`. To avoid frustum culling, set `custom_aabb` to
     * a very large AABB that covers your entire game world such as `AABB(-10000, -10000, -10000,
     * 20000, 20000, 20000)`. To disable all forms of culling (including occlusion and layer culling),
     * call `RenderingServer.instance_set_ignore_culling` on the `GeometryInstance3D`'s `RID`.
     *
     * Generated from Godot docs: GeometryInstance3D.set_custom_aabb
     */
    fun setCustomAabb(aabb: AABB) {
        ObjectCalls.ptrcallWithAABBArg(setCustomAabbBind, handle, aabb)
    }

    /**
     * Overrides the bounding box of this node with a custom one. This can be used to avoid the
     * expensive `AABB` recalculation that happens when a skeleton is used with a `MeshInstance3D` or
     * to have precise control over the `MeshInstance3D`'s bounding box. To use the default AABB, set
     * value to an `AABB` with all fields set to `0.0`. To avoid frustum culling, set `custom_aabb` to
     * a very large AABB that covers your entire game world such as `AABB(-10000, -10000, -10000,
     * 20000, 20000, 20000)`. To disable all forms of culling (including occlusion and layer culling),
     * call `RenderingServer.instance_set_ignore_culling` on the `GeometryInstance3D`'s `RID`.
     *
     * Generated from Godot docs: GeometryInstance3D.get_custom_aabb
     */
    fun getCustomAabb(): AABB {
        return ObjectCalls.ptrcallNoArgsRetAABB(getCustomAabbBind, handle)
    }

    companion object {
        const val SHADOW_CASTING_SETTING_OFF: Long = 0L
        const val SHADOW_CASTING_SETTING_ON: Long = 1L
        const val SHADOW_CASTING_SETTING_DOUBLE_SIDED: Long = 2L
        const val SHADOW_CASTING_SETTING_SHADOWS_ONLY: Long = 3L
        const val GI_MODE_DISABLED: Long = 0L
        const val GI_MODE_STATIC: Long = 1L
        const val GI_MODE_DYNAMIC: Long = 2L
        const val LIGHTMAP_SCALE_1X: Long = 0L
        const val LIGHTMAP_SCALE_2X: Long = 1L
        const val LIGHTMAP_SCALE_4X: Long = 2L
        const val LIGHTMAP_SCALE_8X: Long = 3L
        const val LIGHTMAP_SCALE_MAX: Long = 4L
        const val VISIBILITY_RANGE_FADE_DISABLED: Long = 0L
        const val VISIBILITY_RANGE_FADE_SELF: Long = 1L
        const val VISIBILITY_RANGE_FADE_DEPENDENCIES: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): GeometryInstance3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): GeometryInstance3D? =
            if (handle.address() == 0L) null else GeometryInstance3D(handle)

        private const val SET_MATERIAL_OVERRIDE_HASH = 2757459619L
        private val setMaterialOverrideBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "set_material_override", SET_MATERIAL_OVERRIDE_HASH)
        }

        private const val GET_MATERIAL_OVERRIDE_HASH = 5934680L
        private val getMaterialOverrideBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "get_material_override", GET_MATERIAL_OVERRIDE_HASH)
        }

        private const val SET_MATERIAL_OVERLAY_HASH = 2757459619L
        private val setMaterialOverlayBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "set_material_overlay", SET_MATERIAL_OVERLAY_HASH)
        }

        private const val GET_MATERIAL_OVERLAY_HASH = 5934680L
        private val getMaterialOverlayBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "get_material_overlay", GET_MATERIAL_OVERLAY_HASH)
        }

        private const val SET_CAST_SHADOWS_SETTING_HASH = 856677339L
        private val setCastShadowsSettingBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "set_cast_shadows_setting", SET_CAST_SHADOWS_SETTING_HASH)
        }

        private const val GET_CAST_SHADOWS_SETTING_HASH = 3383019359L
        private val getCastShadowsSettingBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "get_cast_shadows_setting", GET_CAST_SHADOWS_SETTING_HASH)
        }

        private const val SET_LOD_BIAS_HASH = 373806689L
        private val setLodBiasBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "set_lod_bias", SET_LOD_BIAS_HASH)
        }

        private const val GET_LOD_BIAS_HASH = 1740695150L
        private val getLodBiasBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "get_lod_bias", GET_LOD_BIAS_HASH)
        }

        private const val SET_TRANSPARENCY_HASH = 373806689L
        private val setTransparencyBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "set_transparency", SET_TRANSPARENCY_HASH)
        }

        private const val GET_TRANSPARENCY_HASH = 1740695150L
        private val getTransparencyBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "get_transparency", GET_TRANSPARENCY_HASH)
        }

        private const val SET_VISIBILITY_RANGE_END_MARGIN_HASH = 373806689L
        private val setVisibilityRangeEndMarginBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "set_visibility_range_end_margin", SET_VISIBILITY_RANGE_END_MARGIN_HASH)
        }

        private const val GET_VISIBILITY_RANGE_END_MARGIN_HASH = 1740695150L
        private val getVisibilityRangeEndMarginBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "get_visibility_range_end_margin", GET_VISIBILITY_RANGE_END_MARGIN_HASH)
        }

        private const val SET_VISIBILITY_RANGE_END_HASH = 373806689L
        private val setVisibilityRangeEndBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "set_visibility_range_end", SET_VISIBILITY_RANGE_END_HASH)
        }

        private const val GET_VISIBILITY_RANGE_END_HASH = 1740695150L
        private val getVisibilityRangeEndBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "get_visibility_range_end", GET_VISIBILITY_RANGE_END_HASH)
        }

        private const val SET_VISIBILITY_RANGE_BEGIN_MARGIN_HASH = 373806689L
        private val setVisibilityRangeBeginMarginBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "set_visibility_range_begin_margin", SET_VISIBILITY_RANGE_BEGIN_MARGIN_HASH)
        }

        private const val GET_VISIBILITY_RANGE_BEGIN_MARGIN_HASH = 1740695150L
        private val getVisibilityRangeBeginMarginBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "get_visibility_range_begin_margin", GET_VISIBILITY_RANGE_BEGIN_MARGIN_HASH)
        }

        private const val SET_VISIBILITY_RANGE_BEGIN_HASH = 373806689L
        private val setVisibilityRangeBeginBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "set_visibility_range_begin", SET_VISIBILITY_RANGE_BEGIN_HASH)
        }

        private const val GET_VISIBILITY_RANGE_BEGIN_HASH = 1740695150L
        private val getVisibilityRangeBeginBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "get_visibility_range_begin", GET_VISIBILITY_RANGE_BEGIN_HASH)
        }

        private const val SET_VISIBILITY_RANGE_FADE_MODE_HASH = 1440117808L
        private val setVisibilityRangeFadeModeBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "set_visibility_range_fade_mode", SET_VISIBILITY_RANGE_FADE_MODE_HASH)
        }

        private const val GET_VISIBILITY_RANGE_FADE_MODE_HASH = 2067221882L
        private val getVisibilityRangeFadeModeBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "get_visibility_range_fade_mode", GET_VISIBILITY_RANGE_FADE_MODE_HASH)
        }

        private const val SET_INSTANCE_SHADER_PARAMETER_HASH = 3776071444L
        private val setInstanceShaderParameterBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "set_instance_shader_parameter", SET_INSTANCE_SHADER_PARAMETER_HASH)
        }

        private const val GET_INSTANCE_SHADER_PARAMETER_HASH = 2760726917L
        private val getInstanceShaderParameterBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "get_instance_shader_parameter", GET_INSTANCE_SHADER_PARAMETER_HASH)
        }

        private const val SET_EXTRA_CULL_MARGIN_HASH = 373806689L
        private val setExtraCullMarginBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "set_extra_cull_margin", SET_EXTRA_CULL_MARGIN_HASH)
        }

        private const val GET_EXTRA_CULL_MARGIN_HASH = 1740695150L
        private val getExtraCullMarginBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "get_extra_cull_margin", GET_EXTRA_CULL_MARGIN_HASH)
        }

        private const val SET_LIGHTMAP_TEXEL_SCALE_HASH = 373806689L
        private val setLightmapTexelScaleBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "set_lightmap_texel_scale", SET_LIGHTMAP_TEXEL_SCALE_HASH)
        }

        private const val GET_LIGHTMAP_TEXEL_SCALE_HASH = 1740695150L
        private val getLightmapTexelScaleBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "get_lightmap_texel_scale", GET_LIGHTMAP_TEXEL_SCALE_HASH)
        }

        private const val SET_LIGHTMAP_SCALE_HASH = 2462696582L
        private val setLightmapScaleBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "set_lightmap_scale", SET_LIGHTMAP_SCALE_HASH)
        }

        private const val GET_LIGHTMAP_SCALE_HASH = 798767852L
        private val getLightmapScaleBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "get_lightmap_scale", GET_LIGHTMAP_SCALE_HASH)
        }

        private const val SET_GI_MODE_HASH = 2548557163L
        private val setGiModeBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "set_gi_mode", SET_GI_MODE_HASH)
        }

        private const val GET_GI_MODE_HASH = 2188566509L
        private val getGiModeBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "get_gi_mode", GET_GI_MODE_HASH)
        }

        private const val SET_IGNORE_OCCLUSION_CULLING_HASH = 2586408642L
        private val setIgnoreOcclusionCullingBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "set_ignore_occlusion_culling", SET_IGNORE_OCCLUSION_CULLING_HASH)
        }

        private const val IS_IGNORING_OCCLUSION_CULLING_HASH = 2240911060L
        private val isIgnoringOcclusionCullingBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "is_ignoring_occlusion_culling", IS_IGNORING_OCCLUSION_CULLING_HASH)
        }

        private const val SET_CUSTOM_AABB_HASH = 259215842L
        private val setCustomAabbBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "set_custom_aabb", SET_CUSTOM_AABB_HASH)
        }

        private const val GET_CUSTOM_AABB_HASH = 1068685055L
        private val getCustomAabbBind by lazy {
            ObjectCalls.getMethodBind("GeometryInstance3D", "get_custom_aabb", GET_CUSTOM_AABB_HASH)
        }
    }
}
