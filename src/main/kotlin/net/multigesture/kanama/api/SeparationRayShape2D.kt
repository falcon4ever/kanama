package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A 2D ray shape used for physics collision that tries to separate itself from any collider.
 *
 * Generated from Godot docs: SeparationRayShape2D
 */
class SeparationRayShape2D(handle: MemorySegment) : Shape2D(handle) {
    var length: Double
        @JvmName("lengthProperty")
        get() = getLength()
        @JvmName("setLengthProperty")
        set(value) = setLength(value)

    var slideOnSlope: Boolean
        @JvmName("slideOnSlopeProperty")
        get() = getSlideOnSlope()
        @JvmName("setSlideOnSlopeProperty")
        set(value) = setSlideOnSlope(value)

    /**
     * The ray's length.
     *
     * Generated from Godot docs: SeparationRayShape2D.set_length
     */
    fun setLength(length: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setLengthBind, handle, length)
    }

    /**
     * The ray's length.
     *
     * Generated from Godot docs: SeparationRayShape2D.get_length
     */
    fun getLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getLengthBind, handle)
    }

    /**
     * If `false` (default), the shape always separates and returns a normal along its own direction.
     * If `true`, the shape can return the correct normal and separate in any direction, allowing
     * sliding motion on slopes.
     *
     * Generated from Godot docs: SeparationRayShape2D.set_slide_on_slope
     */
    fun setSlideOnSlope(active: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSlideOnSlopeBind, handle, active)
    }

    /**
     * If `false` (default), the shape always separates and returns a normal along its own direction.
     * If `true`, the shape can return the correct normal and separate in any direction, allowing
     * sliding motion on slopes.
     *
     * Generated from Godot docs: SeparationRayShape2D.get_slide_on_slope
     */
    fun getSlideOnSlope(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getSlideOnSlopeBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): SeparationRayShape2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): SeparationRayShape2D? =
            if (handle.address() == 0L) null else SeparationRayShape2D(handle)

        private const val SET_LENGTH_HASH = 373806689L
        private val setLengthBind by lazy {
            ObjectCalls.getMethodBind("SeparationRayShape2D", "set_length", SET_LENGTH_HASH)
        }

        private const val GET_LENGTH_HASH = 1740695150L
        private val getLengthBind by lazy {
            ObjectCalls.getMethodBind("SeparationRayShape2D", "get_length", GET_LENGTH_HASH)
        }

        private const val SET_SLIDE_ON_SLOPE_HASH = 2586408642L
        private val setSlideOnSlopeBind by lazy {
            ObjectCalls.getMethodBind("SeparationRayShape2D", "set_slide_on_slope", SET_SLIDE_ON_SLOPE_HASH)
        }

        private const val GET_SLIDE_ON_SLOPE_HASH = 36873697L
        private val getSlideOnSlopeBind by lazy {
            ObjectCalls.getMethodBind("SeparationRayShape2D", "get_slide_on_slope", GET_SLIDE_ON_SLOPE_HASH)
        }
    }
}
