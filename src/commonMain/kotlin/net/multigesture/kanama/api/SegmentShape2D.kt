package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Vector2

/**
 * A 2D line segment shape used for physics collision.
 *
 * Generated from Godot docs: SegmentShape2D
 */
class SegmentShape2D(handle: GodotHandle) : Shape2D(handle) {
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

    /**
     * The segment's first point position.
     *
     * Generated from Godot docs: SegmentShape2D.set_a
     */
    fun setA(a: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(setABind, segment, a)
    }

    /**
     * The segment's first point position.
     *
     * Generated from Godot docs: SegmentShape2D.get_a
     */
    fun getA(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(getABind, segment)
    }

    /**
     * The segment's second point position.
     *
     * Generated from Godot docs: SegmentShape2D.set_b
     */
    fun setB(b: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(setBBind, segment, b)
    }

    /**
     * The segment's second point position.
     *
     * Generated from Godot docs: SegmentShape2D.get_b
     */
    fun getB(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(getBBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): SegmentShape2D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): SegmentShape2D? =
            if (handle.address() == 0L) null else SegmentShape2D(GodotHandle(handle))

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
