package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Merges several 2D nodes into a single draw operation.
 *
 * Generated from Godot docs: CanvasGroup
 */
class CanvasGroup(handle: MemorySegment) : Node2D(handle) {
    var fitMargin: Double
        @JvmName("fitMarginProperty")
        get() = getFitMargin()
        @JvmName("setFitMarginProperty")
        set(value) = setFitMargin(value)

    var clearMargin: Double
        @JvmName("clearMarginProperty")
        get() = getClearMargin()
        @JvmName("setClearMarginProperty")
        set(value) = setClearMargin(value)

    var useMipmaps: Boolean
        @JvmName("useMipmapsProperty")
        get() = isUsingMipmaps()
        @JvmName("setUseMipmapsProperty")
        set(value) = setUseMipmaps(value)

    /**
     * Sets the size of a margin used to expand the drawable rect of this `CanvasGroup`. The size of
     * the `CanvasGroup` is determined by fitting a rect around its children then expanding that rect
     * by `fit_margin`. This increases both the backbuffer area used and the area covered by the
     * `CanvasGroup` both of which can reduce performance. This should be kept as small as possible and
     * should only be expanded when an increased size is needed (e.g. for custom shader effects).
     *
     * Generated from Godot docs: CanvasGroup.set_fit_margin
     */
    fun setFitMargin(fitMargin: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFitMarginBind, handle, fitMargin)
    }

    /**
     * Sets the size of a margin used to expand the drawable rect of this `CanvasGroup`. The size of
     * the `CanvasGroup` is determined by fitting a rect around its children then expanding that rect
     * by `fit_margin`. This increases both the backbuffer area used and the area covered by the
     * `CanvasGroup` both of which can reduce performance. This should be kept as small as possible and
     * should only be expanded when an increased size is needed (e.g. for custom shader effects).
     *
     * Generated from Godot docs: CanvasGroup.get_fit_margin
     */
    fun getFitMargin(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFitMarginBind, handle)
    }

    /**
     * Sets the size of the margin used to expand the clearing rect of this `CanvasGroup`. This expands
     * the area of the backbuffer that will be used by the `CanvasGroup`. A smaller margin will reduce
     * the area of the backbuffer used which can increase performance, however if `use_mipmaps` is
     * enabled, a small margin may result in mipmap errors at the edge of the `CanvasGroup`.
     * Accordingly, this should be left as small as possible, but should be increased if artifacts
     * appear along the edges of the canvas group.
     *
     * Generated from Godot docs: CanvasGroup.set_clear_margin
     */
    fun setClearMargin(clearMargin: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setClearMarginBind, handle, clearMargin)
    }

    /**
     * Sets the size of the margin used to expand the clearing rect of this `CanvasGroup`. This expands
     * the area of the backbuffer that will be used by the `CanvasGroup`. A smaller margin will reduce
     * the area of the backbuffer used which can increase performance, however if `use_mipmaps` is
     * enabled, a small margin may result in mipmap errors at the edge of the `CanvasGroup`.
     * Accordingly, this should be left as small as possible, but should be increased if artifacts
     * appear along the edges of the canvas group.
     *
     * Generated from Godot docs: CanvasGroup.get_clear_margin
     */
    fun getClearMargin(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getClearMarginBind, handle)
    }

    /**
     * If `true`, calculates mipmaps for the backbuffer before drawing the `CanvasGroup` so that
     * mipmaps can be used in a custom `ShaderMaterial` attached to the `CanvasGroup`. Generating
     * mipmaps has a performance cost so this should not be enabled unless required.
     *
     * Generated from Godot docs: CanvasGroup.set_use_mipmaps
     */
    fun setUseMipmaps(useMipmaps: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseMipmapsBind, handle, useMipmaps)
    }

    /**
     * If `true`, calculates mipmaps for the backbuffer before drawing the `CanvasGroup` so that
     * mipmaps can be used in a custom `ShaderMaterial` attached to the `CanvasGroup`. Generating
     * mipmaps has a performance cost so this should not be enabled unless required.
     *
     * Generated from Godot docs: CanvasGroup.is_using_mipmaps
     */
    fun isUsingMipmaps(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingMipmapsBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): CanvasGroup? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): CanvasGroup? =
            if (handle.address() == 0L) null else CanvasGroup(handle)

        private const val SET_FIT_MARGIN_HASH = 373806689L
        private val setFitMarginBind by lazy {
            ObjectCalls.getMethodBind("CanvasGroup", "set_fit_margin", SET_FIT_MARGIN_HASH)
        }

        private const val GET_FIT_MARGIN_HASH = 1740695150L
        private val getFitMarginBind by lazy {
            ObjectCalls.getMethodBind("CanvasGroup", "get_fit_margin", GET_FIT_MARGIN_HASH)
        }

        private const val SET_CLEAR_MARGIN_HASH = 373806689L
        private val setClearMarginBind by lazy {
            ObjectCalls.getMethodBind("CanvasGroup", "set_clear_margin", SET_CLEAR_MARGIN_HASH)
        }

        private const val GET_CLEAR_MARGIN_HASH = 1740695150L
        private val getClearMarginBind by lazy {
            ObjectCalls.getMethodBind("CanvasGroup", "get_clear_margin", GET_CLEAR_MARGIN_HASH)
        }

        private const val SET_USE_MIPMAPS_HASH = 2586408642L
        private val setUseMipmapsBind by lazy {
            ObjectCalls.getMethodBind("CanvasGroup", "set_use_mipmaps", SET_USE_MIPMAPS_HASH)
        }

        private const val IS_USING_MIPMAPS_HASH = 36873697L
        private val isUsingMipmapsBind by lazy {
            ObjectCalls.getMethodBind("CanvasGroup", "is_using_mipmaps", IS_USING_MIPMAPS_HASH)
        }
    }
}
