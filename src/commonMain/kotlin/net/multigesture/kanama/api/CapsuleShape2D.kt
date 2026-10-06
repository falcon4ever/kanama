package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A 2D capsule shape used for physics collision.
 *
 * Generated from Godot docs: CapsuleShape2D
 */
class CapsuleShape2D(handle: GodotHandle) : Shape2D(handle) {
    var radius: Double
        @JvmName("radiusProperty")
        get() = getRadius()
        @JvmName("setRadiusProperty")
        set(value) = setRadius(value)

    var height: Double
        @JvmName("heightProperty")
        get() = getHeight()
        @JvmName("setHeightProperty")
        set(value) = setHeight(value)

    var midHeight: Double
        @JvmName("midHeightProperty")
        get() = getMidHeight()
        @JvmName("setMidHeightProperty")
        set(value) = setMidHeight(value)

    /**
     * The capsule's radius. Note: The `radius` of a capsule cannot be greater than half of its
     * `height`. Otherwise, the capsule becomes a circle. If the `radius` is greater than half of the
     * `height`, the properties adjust to a valid value.
     *
     * Generated from Godot docs: CapsuleShape2D.set_radius
     */
    fun setRadius(radius: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setRadiusBind, segment, radius)
    }

    /**
     * The capsule's radius. Note: The `radius` of a capsule cannot be greater than half of its
     * `height`. Otherwise, the capsule becomes a circle. If the `radius` is greater than half of the
     * `height`, the properties adjust to a valid value.
     *
     * Generated from Godot docs: CapsuleShape2D.get_radius
     */
    fun getRadius(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getRadiusBind, segment)
    }

    /**
     * The capsule's full height, including the semicircles. Note: The `height` of a capsule must be at
     * least twice its `radius`. Otherwise, the capsule becomes a circle. If the `height` is less than
     * twice the `radius`, the properties adjust to a valid value.
     *
     * Generated from Godot docs: CapsuleShape2D.set_height
     */
    fun setHeight(height: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setHeightBind, segment, height)
    }

    /**
     * The capsule's full height, including the semicircles. Note: The `height` of a capsule must be at
     * least twice its `radius`. Otherwise, the capsule becomes a circle. If the `height` is less than
     * twice the `radius`, the properties adjust to a valid value.
     *
     * Generated from Godot docs: CapsuleShape2D.get_height
     */
    fun getHeight(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getHeightBind, segment)
    }

    /**
     * The capsule's height, excluding the semicircles. This is the height of the central rectangular
     * part in the middle of the capsule, and is the distance between the centers of the two
     * semicircles. This is a wrapper for `height`.
     *
     * Generated from Godot docs: CapsuleShape2D.set_mid_height
     */
    fun setMidHeight(midHeight: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setMidHeightBind, segment, midHeight)
    }

    /**
     * The capsule's height, excluding the semicircles. This is the height of the central rectangular
     * part in the middle of the capsule, and is the distance between the centers of the two
     * semicircles. This is a wrapper for `height`.
     *
     * Generated from Godot docs: CapsuleShape2D.get_mid_height
     */
    fun getMidHeight(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getMidHeightBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): CapsuleShape2D? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): CapsuleShape2D? =
            if (handle.address() == 0L) null else RefCounted.owned(CapsuleShape2D(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): CapsuleShape2D? =
            if (handle.address() == 0L) null else CapsuleShape2D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_RADIUS_HASH = 373806689L
        @JvmField
        val setRadiusBind =
            ObjectCalls.getMethodBind("CapsuleShape2D", "set_radius", SET_RADIUS_HASH)

        private const val GET_RADIUS_HASH = 1740695150L
        @JvmField
        val getRadiusBind =
            ObjectCalls.getMethodBind("CapsuleShape2D", "get_radius", GET_RADIUS_HASH)

        private const val SET_HEIGHT_HASH = 373806689L
        @JvmField
        val setHeightBind =
            ObjectCalls.getMethodBind("CapsuleShape2D", "set_height", SET_HEIGHT_HASH)

        private const val GET_HEIGHT_HASH = 1740695150L
        @JvmField
        val getHeightBind =
            ObjectCalls.getMethodBind("CapsuleShape2D", "get_height", GET_HEIGHT_HASH)

        private const val SET_MID_HEIGHT_HASH = 373806689L
        @JvmField
        val setMidHeightBind =
            ObjectCalls.getMethodBind("CapsuleShape2D", "set_mid_height", SET_MID_HEIGHT_HASH)

        private const val GET_MID_HEIGHT_HASH = 1740695150L
        @JvmField
        val getMidHeightBind =
            ObjectCalls.getMethodBind("CapsuleShape2D", "get_mid_height", GET_MID_HEIGHT_HASH)
    }
}
