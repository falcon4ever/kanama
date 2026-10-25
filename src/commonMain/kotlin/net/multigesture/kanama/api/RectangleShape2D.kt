package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Vector2

/**
 * A 2D rectangle shape used for physics collision.
 *
 * Generated from Godot docs: RectangleShape2D
 */
class RectangleShape2D(handle: GodotHandle) : Shape2D(handle) {
    var size: Vector2
        @JvmName("sizeProperty")
        get() = getSize()
        @JvmName("setSizeProperty")
        set(value) = setSize(value)

    /**
     * The rectangle's width and height.
     *
     * Generated from Godot docs: RectangleShape2D.set_size
     */
    fun setSize(size: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(Binds.setSizeBind, segment, size)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The rectangle's width and height.
     *
     * Generated from Godot docs: RectangleShape2D.get_size
     */
    fun getSize(): Vector2 {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetVector2(Binds.getSizeBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): RectangleShape2D? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): RectangleShape2D? =
            if (handle.address() == 0L) null else RefCounted.owned(RectangleShape2D(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): RectangleShape2D? =
            if (handle.address() == 0L) null else RectangleShape2D(GodotHandle(handle))

        /** A new RectangleShape2D, GDScript's `RectangleShape2D.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): RectangleShape2D =
            RefCounted.owned(RectangleShape2D(GodotHandle(ObjectCalls.constructObject("RectangleShape2D"))))
    }

    private object Binds {
        private const val SET_SIZE_HASH = 743155724L
        @JvmField
        val setSizeBind =
            ObjectCalls.getMethodBind("RectangleShape2D", "set_size", SET_SIZE_HASH)

        private const val GET_SIZE_HASH = 3341600327L
        @JvmField
        val getSizeBind =
            ObjectCalls.getMethodBind("RectangleShape2D", "get_size", GET_SIZE_HASH)
    }
}
