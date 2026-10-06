package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Rect2

/**
 * A node that copies a region of the screen to a buffer for access in shader code.
 *
 * Generated from Godot docs: BackBufferCopy
 */
class BackBufferCopy(handle: GodotHandle) : Node2D(handle) {
    var copyMode: BackBufferCopy.CopyMode
        @JvmName("copyModeProperty")
        get() = getCopyMode()
        @JvmName("setCopyModeProperty")
        set(value) = setCopyMode(value)

    var rect: Rect2
        @JvmName("rectProperty")
        get() = getRect()
        @JvmName("setRectProperty")
        set(value) = setRect(value)

    /**
     * The area covered by the `BackBufferCopy`. Only used if `copy_mode` is `CopyMode.RECT`.
     *
     * Generated from Godot docs: BackBufferCopy.set_rect
     */
    fun setRect(rect: Rect2) {
        ObjectCalls.ptrcallWithRect2Arg(Binds.setRectBind, segment, rect)
    }

    /**
     * The area covered by the `BackBufferCopy`. Only used if `copy_mode` is `CopyMode.RECT`.
     *
     * Generated from Godot docs: BackBufferCopy.get_rect
     */
    fun getRect(): Rect2 {
        return ObjectCalls.ptrcallNoArgsRetRect2(Binds.getRectBind, segment)
    }

    /**
     * Buffer mode.
     *
     * Generated from Godot docs: BackBufferCopy.set_copy_mode
     */
    fun setCopyMode(copyMode: BackBufferCopy.CopyMode) {
        ObjectCalls.ptrcallWithLongArg(Binds.setCopyModeBind, segment, copyMode.value)
    }

    /**
     * Buffer mode.
     *
     * Generated from Godot docs: BackBufferCopy.get_copy_mode
     */
    fun getCopyMode(): BackBufferCopy.CopyMode {
        return BackBufferCopy.CopyMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getCopyModeBind, segment))
    }

    /**
     * Godot's `BackBufferCopy.CopyMode` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`BackBufferCopy.CopyMode.<NAME>`).
     *
     * Generated from Godot docs: BackBufferCopy.CopyMode
     */
    @JvmInline
    value class CopyMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Disables the buffering mode. This means the `BackBufferCopy` node will directly use the portion
             * of screen it covers.
             *
             * Generated from Godot docs: BackBufferCopy.COPY_MODE_DISABLED
             */
            val DISABLED: CopyMode get() = CopyMode(0L)
            /**
             * `BackBufferCopy` buffers a rectangular region.
             *
             * Generated from Godot docs: BackBufferCopy.COPY_MODE_RECT
             */
            val RECT: CopyMode get() = CopyMode(1L)
            /**
             * `BackBufferCopy` buffers the entire screen.
             *
             * Generated from Godot docs: BackBufferCopy.COPY_MODE_VIEWPORT
             */
            val VIEWPORT: CopyMode get() = CopyMode(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): BackBufferCopy? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): BackBufferCopy? =
            if (handle.address() == 0L) null else BackBufferCopy(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_RECT_HASH = 2046264180L
        @JvmField
        val setRectBind =
            ObjectCalls.getMethodBind("BackBufferCopy", "set_rect", SET_RECT_HASH)

        private const val GET_RECT_HASH = 1639390495L
        @JvmField
        val getRectBind =
            ObjectCalls.getMethodBind("BackBufferCopy", "get_rect", GET_RECT_HASH)

        private const val SET_COPY_MODE_HASH = 1713538590L
        @JvmField
        val setCopyModeBind =
            ObjectCalls.getMethodBind("BackBufferCopy", "set_copy_mode", SET_COPY_MODE_HASH)

        private const val GET_COPY_MODE_HASH = 3271169440L
        @JvmField
        val getCopyModeBind =
            ObjectCalls.getMethodBind("BackBufferCopy", "get_copy_mode", GET_COPY_MODE_HASH)
    }
}
