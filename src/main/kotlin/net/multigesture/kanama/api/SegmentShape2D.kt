package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

/**
 * A 2D line segment shape used for physics collision.
 *
 * Generated from Godot docs: SegmentShape2D
 */
class SegmentShape2D(handle: MemorySegment) : Shape2D(handle) {
    var a: Vector2
        @JvmName("aProperty")
        get() = getA()
        @JvmName("setAProperty")
        set(value) = setA(value)

    var b: Vector2
        @JvmName("bProperty")
        get() = getB()
        @JvmName("setBProperty")
        set(value) = setB(value)

    fun setA(a: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setABind, handle, a)
    }

    fun getA(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getABind, handle)
    }

    fun setB(b: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setBBind, handle, b)
    }

    fun getB(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getBBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): SegmentShape2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): SegmentShape2D? =
            if (handle.address() == 0L) null else SegmentShape2D(handle)

        private const val SET_A_HASH = 743155724L
        private val setABind by lazy {
            ObjectCalls.getMethodBind("SegmentShape2D", "set_a", SET_A_HASH)
        }

        private const val GET_A_HASH = 3341600327L
        private val getABind by lazy {
            ObjectCalls.getMethodBind("SegmentShape2D", "get_a", GET_A_HASH)
        }

        private const val SET_B_HASH = 743155724L
        private val setBBind by lazy {
            ObjectCalls.getMethodBind("SegmentShape2D", "set_b", SET_B_HASH)
        }

        private const val GET_B_HASH = 3341600327L
        private val getBBind by lazy {
            ObjectCalls.getMethodBind("SegmentShape2D", "get_b", GET_B_HASH)
        }
    }
}
