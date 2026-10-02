package net.multigesture.kanama.api

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
        ObjectCalls.ptrcallWithVector2Arg(setSizeBind, segment, size)
    }

    /**
     * The rectangle's width and height.
     *
     * Generated from Godot docs: RectangleShape2D.get_size
     */
    fun getSize(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(getSizeBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): RectangleShape2D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): RectangleShape2D? =
            if (handle.address() == 0L) null else RectangleShape2D(GodotHandle(handle))

        private const val SET_SIZE_HASH = 743155724L
        private val setSizeBind by lazy {
            ObjectCalls.getMethodBind("RectangleShape2D", "set_size", SET_SIZE_HASH)
        }

        private const val GET_SIZE_HASH = 3341600327L
        private val getSizeBind by lazy {
            ObjectCalls.getMethodBind("RectangleShape2D", "get_size", GET_SIZE_HASH)
        }
    }
}
