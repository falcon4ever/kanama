package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A 2D capsule shape used for physics collision.
 *
 * Generated from Godot docs: CapsuleShape2D
 */
class CapsuleShape2D(handle: MemorySegment) : Shape2D(handle) {
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
        ObjectCalls.ptrcallWithDoubleArg(setRadiusBind, handle, radius)
    }

    /**
     * The capsule's radius. Note: The `radius` of a capsule cannot be greater than half of its
     * `height`. Otherwise, the capsule becomes a circle. If the `radius` is greater than half of the
     * `height`, the properties adjust to a valid value.
     *
     * Generated from Godot docs: CapsuleShape2D.get_radius
     */
    fun getRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRadiusBind, handle)
    }

    /**
     * The capsule's full height, including the semicircles. Note: The `height` of a capsule must be at
     * least twice its `radius`. Otherwise, the capsule becomes a circle. If the `height` is less than
     * twice the `radius`, the properties adjust to a valid value.
     *
     * Generated from Godot docs: CapsuleShape2D.set_height
     */
    fun setHeight(height: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setHeightBind, handle, height)
    }

    /**
     * The capsule's full height, including the semicircles. Note: The `height` of a capsule must be at
     * least twice its `radius`. Otherwise, the capsule becomes a circle. If the `height` is less than
     * twice the `radius`, the properties adjust to a valid value.
     *
     * Generated from Godot docs: CapsuleShape2D.get_height
     */
    fun getHeight(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getHeightBind, handle)
    }

    /**
     * The capsule's height, excluding the semicircles. This is the height of the central rectangular
     * part in the middle of the capsule, and is the distance between the centers of the two
     * semicircles. This is a wrapper for `height`.
     *
     * Generated from Godot docs: CapsuleShape2D.set_mid_height
     */
    fun setMidHeight(midHeight: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMidHeightBind, handle, midHeight)
    }

    /**
     * The capsule's height, excluding the semicircles. This is the height of the central rectangular
     * part in the middle of the capsule, and is the distance between the centers of the two
     * semicircles. This is a wrapper for `height`.
     *
     * Generated from Godot docs: CapsuleShape2D.get_mid_height
     */
    fun getMidHeight(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMidHeightBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): CapsuleShape2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): CapsuleShape2D? =
            if (handle.address() == 0L) null else CapsuleShape2D(handle)

        private const val SET_RADIUS_HASH = 373806689L
        private val setRadiusBind by lazy {
            ObjectCalls.getMethodBind("CapsuleShape2D", "set_radius", SET_RADIUS_HASH)
        }

        private const val GET_RADIUS_HASH = 1740695150L
        private val getRadiusBind by lazy {
            ObjectCalls.getMethodBind("CapsuleShape2D", "get_radius", GET_RADIUS_HASH)
        }

        private const val SET_HEIGHT_HASH = 373806689L
        private val setHeightBind by lazy {
            ObjectCalls.getMethodBind("CapsuleShape2D", "set_height", SET_HEIGHT_HASH)
        }

        private const val GET_HEIGHT_HASH = 1740695150L
        private val getHeightBind by lazy {
            ObjectCalls.getMethodBind("CapsuleShape2D", "get_height", GET_HEIGHT_HASH)
        }

        private const val SET_MID_HEIGHT_HASH = 373806689L
        private val setMidHeightBind by lazy {
            ObjectCalls.getMethodBind("CapsuleShape2D", "set_mid_height", SET_MID_HEIGHT_HASH)
        }

        private const val GET_MID_HEIGHT_HASH = 1740695150L
        private val getMidHeightBind by lazy {
            ObjectCalls.getMethodBind("CapsuleShape2D", "get_mid_height", GET_MID_HEIGHT_HASH)
        }
    }
}
