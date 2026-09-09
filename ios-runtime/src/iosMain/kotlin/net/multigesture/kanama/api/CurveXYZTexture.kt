package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: CurveXYZTexture
 */
class CurveXYZTexture(handle: MemorySegment) : Texture2D(handle) {
    var curveX: Curve?
        @JvmName("curveXProperty")
        get() = getCurveX()
        @JvmName("setCurveXProperty")
        set(value) = setCurveX(value)

    var curveY: Curve?
        @JvmName("curveYProperty")
        get() = getCurveY()
        @JvmName("setCurveYProperty")
        set(value) = setCurveY(value)

    var curveZ: Curve?
        @JvmName("curveZProperty")
        get() = getCurveZ()
        @JvmName("setCurveZProperty")
        set(value) = setCurveZ(value)

    fun setWidth(width: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setWidthBind, handle, width)
    }

    fun setCurveX(curve: Curve?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(setCurveXBind, handle, listOf(curve?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getCurveX(): Curve? {
        checkOpen()
        return Curve.wrap(ObjectCalls.ptrcallNoArgsRetObject(getCurveXBind, handle))
    }

    fun setCurveY(curve: Curve?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(setCurveYBind, handle, listOf(curve?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getCurveY(): Curve? {
        checkOpen()
        return Curve.wrap(ObjectCalls.ptrcallNoArgsRetObject(getCurveYBind, handle))
    }

    fun setCurveZ(curve: Curve?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(setCurveZBind, handle, listOf(curve?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getCurveZ(): Curve? {
        checkOpen()
        return Curve.wrap(ObjectCalls.ptrcallNoArgsRetObject(getCurveZBind, handle))
    }

    companion object {
        fun fromHandle(handle: MemorySegment): CurveXYZTexture? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): CurveXYZTexture? =
            if (handle.address() == 0L) null else CurveXYZTexture(handle)

        private const val SET_WIDTH_HASH = 1286410249L
        private val setWidthBind by lazy {
            ObjectCalls.getMethodBind("CurveXYZTexture", "set_width", SET_WIDTH_HASH)
        }

        private const val SET_CURVE_X_HASH = 270443179L
        private val setCurveXBind by lazy {
            ObjectCalls.getMethodBind("CurveXYZTexture", "set_curve_x", SET_CURVE_X_HASH)
        }

        private const val GET_CURVE_X_HASH = 2460114913L
        private val getCurveXBind by lazy {
            ObjectCalls.getMethodBind("CurveXYZTexture", "get_curve_x", GET_CURVE_X_HASH)
        }

        private const val SET_CURVE_Y_HASH = 270443179L
        private val setCurveYBind by lazy {
            ObjectCalls.getMethodBind("CurveXYZTexture", "set_curve_y", SET_CURVE_Y_HASH)
        }

        private const val GET_CURVE_Y_HASH = 2460114913L
        private val getCurveYBind by lazy {
            ObjectCalls.getMethodBind("CurveXYZTexture", "get_curve_y", GET_CURVE_Y_HASH)
        }

        private const val SET_CURVE_Z_HASH = 270443179L
        private val setCurveZBind by lazy {
            ObjectCalls.getMethodBind("CurveXYZTexture", "set_curve_z", SET_CURVE_Z_HASH)
        }

        private const val GET_CURVE_Z_HASH = 2460114913L
        private val getCurveZBind by lazy {
            ObjectCalls.getMethodBind("CurveXYZTexture", "get_curve_z", GET_CURVE_Z_HASH)
        }
    }
}
