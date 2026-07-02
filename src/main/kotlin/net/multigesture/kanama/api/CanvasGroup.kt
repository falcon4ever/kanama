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

    fun setFitMargin(fitMargin: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFitMarginBind, handle, fitMargin)
    }

    fun getFitMargin(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFitMarginBind, handle)
    }

    fun setClearMargin(clearMargin: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setClearMarginBind, handle, clearMargin)
    }

    fun getClearMargin(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getClearMarginBind, handle)
    }

    fun setUseMipmaps(useMipmaps: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseMipmapsBind, handle, useMipmaps)
    }

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
