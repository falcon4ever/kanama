package net.multigesture.kanama.api

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
    var copyMode: Long
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
     * The area covered by the `BackBufferCopy`. Only used if `copy_mode` is `COPY_MODE_RECT`.
     *
     * Generated from Godot docs: BackBufferCopy.set_rect
     */
    fun setRect(rect: Rect2) {
        ObjectCalls.ptrcallWithRect2Arg(setRectBind, segment, rect)
    }

    /**
     * The area covered by the `BackBufferCopy`. Only used if `copy_mode` is `COPY_MODE_RECT`.
     *
     * Generated from Godot docs: BackBufferCopy.get_rect
     */
    fun getRect(): Rect2 {
        return ObjectCalls.ptrcallNoArgsRetRect2(getRectBind, segment)
    }

    /**
     * Buffer mode.
     *
     * Generated from Godot docs: BackBufferCopy.set_copy_mode
     */
    fun setCopyMode(copyMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setCopyModeBind, segment, copyMode)
    }

    /**
     * Buffer mode.
     *
     * Generated from Godot docs: BackBufferCopy.get_copy_mode
     */
    fun getCopyMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getCopyModeBind, segment)
    }

    companion object {
        const val COPY_MODE_DISABLED: Long = 0L
        const val COPY_MODE_RECT: Long = 1L
        const val COPY_MODE_VIEWPORT: Long = 2L

        @JvmStatic
        fun fromHandle(handle: GodotHandle): BackBufferCopy? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): BackBufferCopy? =
            if (handle.address() == 0L) null else BackBufferCopy(GodotHandle(handle))

        private const val SET_RECT_HASH = 2046264180L
        private val setRectBind by lazy {
            ObjectCalls.getMethodBind("BackBufferCopy", "set_rect", SET_RECT_HASH)
        }

        private const val GET_RECT_HASH = 1639390495L
        private val getRectBind by lazy {
            ObjectCalls.getMethodBind("BackBufferCopy", "get_rect", GET_RECT_HASH)
        }

        private const val SET_COPY_MODE_HASH = 1713538590L
        private val setCopyModeBind by lazy {
            ObjectCalls.getMethodBind("BackBufferCopy", "set_copy_mode", SET_COPY_MODE_HASH)
        }

        private const val GET_COPY_MODE_HASH = 3271169440L
        private val getCopyModeBind by lazy {
            ObjectCalls.getMethodBind("BackBufferCopy", "get_copy_mode", GET_COPY_MODE_HASH)
        }
    }
}
