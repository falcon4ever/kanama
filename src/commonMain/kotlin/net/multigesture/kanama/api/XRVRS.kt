package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Rect2i
import net.multigesture.kanama.types.Vector2

/**
 * Helper class for XR interfaces that generates VRS images.
 *
 * Generated from Godot docs: XRVRS
 */
class XRVRS(handle: GodotHandle) : GodotObject(handle) {
    var vrsMinRadius: Double
        @JvmName("vrsMinRadiusProperty")
        get() = getVrsMinRadius()
        @JvmName("setVrsMinRadiusProperty")
        set(value) = setVrsMinRadius(value)

    var vrsStrength: Double
        @JvmName("vrsStrengthProperty")
        get() = getVrsStrength()
        @JvmName("setVrsStrengthProperty")
        set(value) = setVrsStrength(value)

    var vrsRenderRegion: Rect2i
        @JvmName("vrsRenderRegionProperty")
        get() = getVrsRenderRegion()
        @JvmName("setVrsRenderRegionProperty")
        set(value) = setVrsRenderRegion(value)

    /**
     * The minimum radius around the focal point where full quality is guaranteed if VRS is used as a
     * percentage of screen size.
     *
     * Generated from Godot docs: XRVRS.get_vrs_min_radius
     */
    fun getVrsMinRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getVrsMinRadiusBind, segment)
    }

    /**
     * The minimum radius around the focal point where full quality is guaranteed if VRS is used as a
     * percentage of screen size.
     *
     * Generated from Godot docs: XRVRS.set_vrs_min_radius
     */
    fun setVrsMinRadius(radius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setVrsMinRadiusBind, segment, radius)
    }

    /**
     * The strength used to calculate the VRS density map. The greater this value, the more noticeable
     * VRS is.
     *
     * Generated from Godot docs: XRVRS.get_vrs_strength
     */
    fun getVrsStrength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getVrsStrengthBind, segment)
    }

    /**
     * The strength used to calculate the VRS density map. The greater this value, the more noticeable
     * VRS is.
     *
     * Generated from Godot docs: XRVRS.set_vrs_strength
     */
    fun setVrsStrength(strength: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setVrsStrengthBind, segment, strength)
    }

    /**
     * The render region that the VRS texture will be scaled to when generated.
     *
     * Generated from Godot docs: XRVRS.get_vrs_render_region
     */
    fun getVrsRenderRegion(): Rect2i {
        return ObjectCalls.ptrcallNoArgsRetRect2i(Binds.getVrsRenderRegionBind, segment)
    }

    /**
     * The render region that the VRS texture will be scaled to when generated.
     *
     * Generated from Godot docs: XRVRS.set_vrs_render_region
     */
    fun setVrsRenderRegion(renderRegion: Rect2i) {
        ObjectCalls.ptrcallWithRect2iArg(Binds.setVrsRenderRegionBind, segment, renderRegion)
    }

    /**
     * Generates the VRS texture based on a render `target_size` adjusted by our VRS tile size. For
     * each eyes focal point passed in `eye_foci` a layer is created. Focal point should be in NDC. The
     * result will be cached, requesting a VRS texture with unchanged parameters and settings will
     * return the cached RID.
     *
     * Generated from Godot docs: XRVRS.make_vrs_texture
     */
    fun makeVrsTexture(targetSize: Vector2, eyeFoci: List<Vector2>): RID {
        return ObjectCalls.ptrcallWithVector2PackedVector2ListArgsRetRID(Binds.makeVrsTextureBind, segment, targetSize, eyeFoci)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): XRVRS? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): XRVRS? =
            if (handle.address() == 0L) null else XRVRS(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_VRS_MIN_RADIUS_HASH = 1740695150L
        @JvmField
        val getVrsMinRadiusBind =
            ObjectCalls.getMethodBind("XRVRS", "get_vrs_min_radius", GET_VRS_MIN_RADIUS_HASH)

        private const val SET_VRS_MIN_RADIUS_HASH = 373806689L
        @JvmField
        val setVrsMinRadiusBind =
            ObjectCalls.getMethodBind("XRVRS", "set_vrs_min_radius", SET_VRS_MIN_RADIUS_HASH)

        private const val GET_VRS_STRENGTH_HASH = 1740695150L
        @JvmField
        val getVrsStrengthBind =
            ObjectCalls.getMethodBind("XRVRS", "get_vrs_strength", GET_VRS_STRENGTH_HASH)

        private const val SET_VRS_STRENGTH_HASH = 373806689L
        @JvmField
        val setVrsStrengthBind =
            ObjectCalls.getMethodBind("XRVRS", "set_vrs_strength", SET_VRS_STRENGTH_HASH)

        private const val GET_VRS_RENDER_REGION_HASH = 410525958L
        @JvmField
        val getVrsRenderRegionBind =
            ObjectCalls.getMethodBind("XRVRS", "get_vrs_render_region", GET_VRS_RENDER_REGION_HASH)

        private const val SET_VRS_RENDER_REGION_HASH = 1763793166L
        @JvmField
        val setVrsRenderRegionBind =
            ObjectCalls.getMethodBind("XRVRS", "set_vrs_render_region", SET_VRS_RENDER_REGION_HASH)

        private const val MAKE_VRS_TEXTURE_HASH = 3647044786L
        @JvmField
        val makeVrsTextureBind =
            ObjectCalls.getMethodBind("XRVRS", "make_vrs_texture", MAKE_VRS_TEXTURE_HASH)
    }
}
