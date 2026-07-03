package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Rect2i
import net.multigesture.kanama.types.Vector2

/**
 * Helper class for XR interfaces that generates VRS images.
 *
 * Generated from Godot docs: XRVRS
 */
class XRVRS(handle: MemorySegment) : GodotObject(handle) {
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
        return ObjectCalls.ptrcallNoArgsRetDouble(getVrsMinRadiusBind, handle)
    }

    /**
     * The minimum radius around the focal point where full quality is guaranteed if VRS is used as a
     * percentage of screen size.
     *
     * Generated from Godot docs: XRVRS.set_vrs_min_radius
     */
    fun setVrsMinRadius(radius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVrsMinRadiusBind, handle, radius)
    }

    /**
     * The strength used to calculate the VRS density map. The greater this value, the more noticeable
     * VRS is.
     *
     * Generated from Godot docs: XRVRS.get_vrs_strength
     */
    fun getVrsStrength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVrsStrengthBind, handle)
    }

    /**
     * The strength used to calculate the VRS density map. The greater this value, the more noticeable
     * VRS is.
     *
     * Generated from Godot docs: XRVRS.set_vrs_strength
     */
    fun setVrsStrength(strength: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVrsStrengthBind, handle, strength)
    }

    /**
     * The render region that the VRS texture will be scaled to when generated.
     *
     * Generated from Godot docs: XRVRS.get_vrs_render_region
     */
    fun getVrsRenderRegion(): Rect2i {
        return ObjectCalls.ptrcallNoArgsRetRect2i(getVrsRenderRegionBind, handle)
    }

    /**
     * The render region that the VRS texture will be scaled to when generated.
     *
     * Generated from Godot docs: XRVRS.set_vrs_render_region
     */
    fun setVrsRenderRegion(renderRegion: Rect2i) {
        ObjectCalls.ptrcallWithRect2iArg(setVrsRenderRegionBind, handle, renderRegion)
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
        return ObjectCalls.ptrcallWithVector2PackedVector2ListArgsRetRID(makeVrsTextureBind, handle, targetSize, eyeFoci)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): XRVRS? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): XRVRS? =
            if (handle.address() == 0L) null else XRVRS(handle)

        private const val GET_VRS_MIN_RADIUS_HASH = 1740695150L
        private val getVrsMinRadiusBind by lazy {
            ObjectCalls.getMethodBind("XRVRS", "get_vrs_min_radius", GET_VRS_MIN_RADIUS_HASH)
        }

        private const val SET_VRS_MIN_RADIUS_HASH = 373806689L
        private val setVrsMinRadiusBind by lazy {
            ObjectCalls.getMethodBind("XRVRS", "set_vrs_min_radius", SET_VRS_MIN_RADIUS_HASH)
        }

        private const val GET_VRS_STRENGTH_HASH = 1740695150L
        private val getVrsStrengthBind by lazy {
            ObjectCalls.getMethodBind("XRVRS", "get_vrs_strength", GET_VRS_STRENGTH_HASH)
        }

        private const val SET_VRS_STRENGTH_HASH = 373806689L
        private val setVrsStrengthBind by lazy {
            ObjectCalls.getMethodBind("XRVRS", "set_vrs_strength", SET_VRS_STRENGTH_HASH)
        }

        private const val GET_VRS_RENDER_REGION_HASH = 410525958L
        private val getVrsRenderRegionBind by lazy {
            ObjectCalls.getMethodBind("XRVRS", "get_vrs_render_region", GET_VRS_RENDER_REGION_HASH)
        }

        private const val SET_VRS_RENDER_REGION_HASH = 1763793166L
        private val setVrsRenderRegionBind by lazy {
            ObjectCalls.getMethodBind("XRVRS", "set_vrs_render_region", SET_VRS_RENDER_REGION_HASH)
        }

        private const val MAKE_VRS_TEXTURE_HASH = 3647044786L
        private val makeVrsTextureBind by lazy {
            ObjectCalls.getMethodBind("XRVRS", "make_vrs_texture", MAKE_VRS_TEXTURE_HASH)
        }
    }
}
