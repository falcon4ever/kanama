package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A 1D texture where the red, green, and blue color channels correspond to points on 3 curves.
 *
 * Generated from Godot docs: CurveXYZTexture
 */
class CurveXYZTexture(handle: GodotHandle) : Texture2D(handle) {
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

    /**
     * The width of the texture (in pixels). Higher values make it possible to represent high-frequency
     * data better (such as sudden direction changes), at the cost of increased generation time and
     * memory usage.
     *
     * Generated from Godot docs: CurveXYZTexture.set_width
     */
    fun setWidth(width: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setWidthBind, segment, width)
    }

    /**
     * The `Curve` that is rendered onto the texture's red channel. Should be a unit `Curve`.
     *
     * Generated from Godot docs: CurveXYZTexture.set_curve_x
     */
    fun setCurveX(curve: Curve?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setCurveXBind, segment, listOf(curve?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The `Curve` that is rendered onto the texture's red channel. Should be a unit `Curve`.
     *
     * Generated from Godot docs: CurveXYZTexture.get_curve_x
     */
    fun getCurveX(): Curve? {
        checkOpen()
        return Curve.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getCurveXBind, segment))
    }

    /**
     * The `Curve` that is rendered onto the texture's green channel. Should be a unit `Curve`.
     *
     * Generated from Godot docs: CurveXYZTexture.set_curve_y
     */
    fun setCurveY(curve: Curve?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setCurveYBind, segment, listOf(curve?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The `Curve` that is rendered onto the texture's green channel. Should be a unit `Curve`.
     *
     * Generated from Godot docs: CurveXYZTexture.get_curve_y
     */
    fun getCurveY(): Curve? {
        checkOpen()
        return Curve.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getCurveYBind, segment))
    }

    /**
     * The `Curve` that is rendered onto the texture's blue channel. Should be a unit `Curve`.
     *
     * Generated from Godot docs: CurveXYZTexture.set_curve_z
     */
    fun setCurveZ(curve: Curve?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setCurveZBind, segment, listOf(curve?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The `Curve` that is rendered onto the texture's blue channel. Should be a unit `Curve`.
     *
     * Generated from Godot docs: CurveXYZTexture.get_curve_z
     */
    fun getCurveZ(): Curve? {
        checkOpen()
        return Curve.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getCurveZBind, segment))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): CurveXYZTexture? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): CurveXYZTexture? =
            if (handle.address() == 0L) null else RefCounted.owned(CurveXYZTexture(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): CurveXYZTexture? =
            if (handle.address() == 0L) null else CurveXYZTexture(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_WIDTH_HASH = 1286410249L
        @JvmField
        val setWidthBind =
            ObjectCalls.getMethodBind("CurveXYZTexture", "set_width", SET_WIDTH_HASH)

        private const val SET_CURVE_X_HASH = 270443179L
        @JvmField
        val setCurveXBind =
            ObjectCalls.getMethodBind("CurveXYZTexture", "set_curve_x", SET_CURVE_X_HASH)

        private const val GET_CURVE_X_HASH = 2460114913L
        @JvmField
        val getCurveXBind =
            ObjectCalls.getMethodBind("CurveXYZTexture", "get_curve_x", GET_CURVE_X_HASH)

        private const val SET_CURVE_Y_HASH = 270443179L
        @JvmField
        val setCurveYBind =
            ObjectCalls.getMethodBind("CurveXYZTexture", "set_curve_y", SET_CURVE_Y_HASH)

        private const val GET_CURVE_Y_HASH = 2460114913L
        @JvmField
        val getCurveYBind =
            ObjectCalls.getMethodBind("CurveXYZTexture", "get_curve_y", GET_CURVE_Y_HASH)

        private const val SET_CURVE_Z_HASH = 270443179L
        @JvmField
        val setCurveZBind =
            ObjectCalls.getMethodBind("CurveXYZTexture", "set_curve_z", SET_CURVE_Z_HASH)

        private const val GET_CURVE_Z_HASH = 2460114913L
        @JvmField
        val getCurveZBind =
            ObjectCalls.getMethodBind("CurveXYZTexture", "get_curve_z", GET_CURVE_Z_HASH)
    }
}
