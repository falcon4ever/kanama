package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
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
        ObjectCalls.ptrcallWithVector2Arg(Binds.setABind, segment, a)
    }

    /**
     * The segment's first point position.
     *
     * Generated from Godot docs: SegmentShape2D.get_a
     */
    fun getA(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getABind, segment)
    }

    /**
     * The segment's second point position.
     *
     * Generated from Godot docs: SegmentShape2D.set_b
     */
    fun setB(b: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(Binds.setBBind, segment, b)
    }

    /**
     * The segment's second point position.
     *
     * Generated from Godot docs: SegmentShape2D.get_b
     */
    fun getB(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getBBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): SegmentShape2D? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): SegmentShape2D? =
            if (handle.address() == 0L) null else RefCounted.owned(SegmentShape2D(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): SegmentShape2D? =
            if (handle.address() == 0L) null else SegmentShape2D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_A_HASH = 743155724L
        @JvmField
        val setABind =
            ObjectCalls.getMethodBind("SegmentShape2D", "set_a", SET_A_HASH)

        private const val GET_A_HASH = 3341600327L
        @JvmField
        val getABind =
            ObjectCalls.getMethodBind("SegmentShape2D", "get_a", GET_A_HASH)

        private const val SET_B_HASH = 743155724L
        @JvmField
        val setBBind =
            ObjectCalls.getMethodBind("SegmentShape2D", "set_b", SET_B_HASH)

        private const val GET_B_HASH = 3341600327L
        @JvmField
        val getBBind =
            ObjectCalls.getMethodBind("SegmentShape2D", "get_b", GET_B_HASH)
    }
}
