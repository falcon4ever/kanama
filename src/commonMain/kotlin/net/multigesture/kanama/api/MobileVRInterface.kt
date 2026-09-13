package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Rect2

/**
 * Generated from Godot docs: MobileVRInterface
 */
class MobileVRInterface(handle: GodotHandle) : XRInterface(handle) {
    var eyeHeight: Double
        @JvmName("eyeHeightProperty")
        get() = getEyeHeight()
        @JvmName("setEyeHeightProperty")
        set(value) = setEyeHeight(value)

    var iod: Double
        @JvmName("iodProperty")
        get() = getIod()
        @JvmName("setIodProperty")
        set(value) = setIod(value)

    var displayWidth: Double
        @JvmName("displayWidthProperty")
        get() = getDisplayWidth()
        @JvmName("setDisplayWidthProperty")
        set(value) = setDisplayWidth(value)

    var displayToLens: Double
        @JvmName("displayToLensProperty")
        get() = getDisplayToLens()
        @JvmName("setDisplayToLensProperty")
        set(value) = setDisplayToLens(value)

    var offsetRect: Rect2
        @JvmName("offsetRectProperty")
        get() = getOffsetRect()
        @JvmName("setOffsetRectProperty")
        set(value) = setOffsetRect(value)

    var oversample: Double
        @JvmName("oversampleProperty")
        get() = getOversample()
        @JvmName("setOversampleProperty")
        set(value) = setOversample(value)

    var k1: Double
        @JvmName("k1Property")
        get() = getK1()
        @JvmName("setK1Property")
        set(value) = setK1(value)

    var k2: Double
        @JvmName("k2Property")
        get() = getK2()
        @JvmName("setK2Property")
        set(value) = setK2(value)

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

    fun setEyeHeight(eyeHeight: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setEyeHeightBind, segment, eyeHeight)
    }

    fun getEyeHeight(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getEyeHeightBind, segment)
    }

    fun setIod(iod: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setIodBind, segment, iod)
    }

    fun getIod(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getIodBind, segment)
    }

    fun setDisplayWidth(displayWidth: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setDisplayWidthBind, segment, displayWidth)
    }

    fun getDisplayWidth(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getDisplayWidthBind, segment)
    }

    fun setDisplayToLens(displayToLens: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setDisplayToLensBind, segment, displayToLens)
    }

    fun getDisplayToLens(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getDisplayToLensBind, segment)
    }

    fun setOffsetRect(offsetRect: Rect2) {
        checkOpen()
        ObjectCalls.ptrcallWithRect2Arg(setOffsetRectBind, segment, offsetRect)
    }

    fun getOffsetRect(): Rect2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetRect2(getOffsetRectBind, segment)
    }

    fun setOversample(oversample: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setOversampleBind, segment, oversample)
    }

    fun getOversample(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getOversampleBind, segment)
    }

    fun setK1(k: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setK1Bind, segment, k)
    }

    fun getK1(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getK1Bind, segment)
    }

    fun setK2(k: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setK2Bind, segment, k)
    }

    fun getK2(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getK2Bind, segment)
    }

    fun getVrsMinRadius(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getVrsMinRadiusBind, segment)
    }

    fun setVrsMinRadius(radius: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setVrsMinRadiusBind, segment, radius)
    }

    fun getVrsStrength(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getVrsStrengthBind, segment)
    }

    fun setVrsStrength(strength: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setVrsStrengthBind, segment, strength)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): MobileVRInterface? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): MobileVRInterface? =
            if (handle.address() == 0L) null else MobileVRInterface(GodotHandle(handle))

        private const val SET_EYE_HEIGHT_HASH = 373806689L
        private val setEyeHeightBind by lazy {
            ObjectCalls.getMethodBind("MobileVRInterface", "set_eye_height", SET_EYE_HEIGHT_HASH)
        }

        private const val GET_EYE_HEIGHT_HASH = 1740695150L
        private val getEyeHeightBind by lazy {
            ObjectCalls.getMethodBind("MobileVRInterface", "get_eye_height", GET_EYE_HEIGHT_HASH)
        }

        private const val SET_IOD_HASH = 373806689L
        private val setIodBind by lazy {
            ObjectCalls.getMethodBind("MobileVRInterface", "set_iod", SET_IOD_HASH)
        }

        private const val GET_IOD_HASH = 1740695150L
        private val getIodBind by lazy {
            ObjectCalls.getMethodBind("MobileVRInterface", "get_iod", GET_IOD_HASH)
        }

        private const val SET_DISPLAY_WIDTH_HASH = 373806689L
        private val setDisplayWidthBind by lazy {
            ObjectCalls.getMethodBind("MobileVRInterface", "set_display_width", SET_DISPLAY_WIDTH_HASH)
        }

        private const val GET_DISPLAY_WIDTH_HASH = 1740695150L
        private val getDisplayWidthBind by lazy {
            ObjectCalls.getMethodBind("MobileVRInterface", "get_display_width", GET_DISPLAY_WIDTH_HASH)
        }

        private const val SET_DISPLAY_TO_LENS_HASH = 373806689L
        private val setDisplayToLensBind by lazy {
            ObjectCalls.getMethodBind("MobileVRInterface", "set_display_to_lens", SET_DISPLAY_TO_LENS_HASH)
        }

        private const val GET_DISPLAY_TO_LENS_HASH = 1740695150L
        private val getDisplayToLensBind by lazy {
            ObjectCalls.getMethodBind("MobileVRInterface", "get_display_to_lens", GET_DISPLAY_TO_LENS_HASH)
        }

        private const val SET_OFFSET_RECT_HASH = 2046264180L
        private val setOffsetRectBind by lazy {
            ObjectCalls.getMethodBind("MobileVRInterface", "set_offset_rect", SET_OFFSET_RECT_HASH)
        }

        private const val GET_OFFSET_RECT_HASH = 1639390495L
        private val getOffsetRectBind by lazy {
            ObjectCalls.getMethodBind("MobileVRInterface", "get_offset_rect", GET_OFFSET_RECT_HASH)
        }

        private const val SET_OVERSAMPLE_HASH = 373806689L
        private val setOversampleBind by lazy {
            ObjectCalls.getMethodBind("MobileVRInterface", "set_oversample", SET_OVERSAMPLE_HASH)
        }

        private const val GET_OVERSAMPLE_HASH = 1740695150L
        private val getOversampleBind by lazy {
            ObjectCalls.getMethodBind("MobileVRInterface", "get_oversample", GET_OVERSAMPLE_HASH)
        }

        private const val SET_K1_HASH = 373806689L
        private val setK1Bind by lazy {
            ObjectCalls.getMethodBind("MobileVRInterface", "set_k1", SET_K1_HASH)
        }

        private const val GET_K1_HASH = 1740695150L
        private val getK1Bind by lazy {
            ObjectCalls.getMethodBind("MobileVRInterface", "get_k1", GET_K1_HASH)
        }

        private const val SET_K2_HASH = 373806689L
        private val setK2Bind by lazy {
            ObjectCalls.getMethodBind("MobileVRInterface", "set_k2", SET_K2_HASH)
        }

        private const val GET_K2_HASH = 1740695150L
        private val getK2Bind by lazy {
            ObjectCalls.getMethodBind("MobileVRInterface", "get_k2", GET_K2_HASH)
        }

        private const val GET_VRS_MIN_RADIUS_HASH = 1740695150L
        private val getVrsMinRadiusBind by lazy {
            ObjectCalls.getMethodBind("MobileVRInterface", "get_vrs_min_radius", GET_VRS_MIN_RADIUS_HASH)
        }

        private const val SET_VRS_MIN_RADIUS_HASH = 373806689L
        private val setVrsMinRadiusBind by lazy {
            ObjectCalls.getMethodBind("MobileVRInterface", "set_vrs_min_radius", SET_VRS_MIN_RADIUS_HASH)
        }

        private const val GET_VRS_STRENGTH_HASH = 1740695150L
        private val getVrsStrengthBind by lazy {
            ObjectCalls.getMethodBind("MobileVRInterface", "get_vrs_strength", GET_VRS_STRENGTH_HASH)
        }

        private const val SET_VRS_STRENGTH_HASH = 373806689L
        private val setVrsStrengthBind by lazy {
            ObjectCalls.getMethodBind("MobileVRInterface", "set_vrs_strength", SET_VRS_STRENGTH_HASH)
        }
    }
}
