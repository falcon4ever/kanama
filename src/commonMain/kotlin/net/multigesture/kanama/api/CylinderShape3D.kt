package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * A 3D cylinder shape used for physics collision.
 *
 * Generated from Godot docs: CylinderShape3D
 */
class CylinderShape3D(handle: GodotHandle) : Shape3D(handle) {
    var height: Double
        @JvmName("heightProperty")
        get() = getHeight()
        @JvmName("setHeightProperty")
        set(value) = setHeight(value)

    var radius: Double
        @JvmName("radiusProperty")
        get() = getRadius()
        @JvmName("setRadiusProperty")
        set(value) = setRadius(value)

    /**
     * The cylinder's radius.
     *
     * Generated from Godot docs: CylinderShape3D.set_radius
     */
    fun setRadius(radius: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setRadiusBind, segment, radius)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The cylinder's radius.
     *
     * Generated from Godot docs: CylinderShape3D.get_radius
     */
    fun getRadius(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getRadiusBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The cylinder's height.
     *
     * Generated from Godot docs: CylinderShape3D.set_height
     */
    fun setHeight(height: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setHeightBind, segment, height)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The cylinder's height.
     *
     * Generated from Godot docs: CylinderShape3D.get_height
     */
    fun getHeight(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getHeightBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): CylinderShape3D? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): CylinderShape3D? =
            if (handle.address() == 0L) null else RefCounted.owned(CylinderShape3D(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): CylinderShape3D? =
            if (handle.address() == 0L) null else CylinderShape3D(GodotHandle(handle))

        /** A new CylinderShape3D, GDScript's `CylinderShape3D.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): CylinderShape3D =
            RefCounted.owned(CylinderShape3D(GodotHandle(requireConstructed(ObjectCalls.constructObject("CylinderShape3D"), "CylinderShape3D"))))
    }

    private object Binds {
        private const val SET_RADIUS_HASH = 373806689L
        @JvmField
        val setRadiusBind =
            ObjectCalls.getMethodBind("CylinderShape3D", "set_radius", SET_RADIUS_HASH)

        private const val GET_RADIUS_HASH = 1740695150L
        @JvmField
        val getRadiusBind =
            ObjectCalls.getMethodBind("CylinderShape3D", "get_radius", GET_RADIUS_HASH)

        private const val SET_HEIGHT_HASH = 373806689L
        @JvmField
        val setHeightBind =
            ObjectCalls.getMethodBind("CylinderShape3D", "set_height", SET_HEIGHT_HASH)

        private const val GET_HEIGHT_HASH = 1740695150L
        @JvmField
        val getHeightBind =
            ObjectCalls.getMethodBind("CylinderShape3D", "get_height", GET_HEIGHT_HASH)
    }
}
