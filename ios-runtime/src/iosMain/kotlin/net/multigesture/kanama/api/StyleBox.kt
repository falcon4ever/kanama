package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Vector2

/**
 * Generated from Godot docs: StyleBox
 */
open class StyleBox(handle: MemorySegment) : Resource(handle) {
    var contentMarginLeft: Double
        @JvmName("contentMarginLeftProperty")
        get() = getContentMargin(0L)
        @JvmName("setContentMarginLeftProperty")
        set(value) = setContentMargin(0L, value)

    var contentMarginTop: Double
        @JvmName("contentMarginTopProperty")
        get() = getContentMargin(1L)
        @JvmName("setContentMarginTopProperty")
        set(value) = setContentMargin(1L, value)

    var contentMarginRight: Double
        @JvmName("contentMarginRightProperty")
        get() = getContentMargin(2L)
        @JvmName("setContentMarginRightProperty")
        set(value) = setContentMargin(2L, value)

    var contentMarginBottom: Double
        @JvmName("contentMarginBottomProperty")
        get() = getContentMargin(3L)
        @JvmName("setContentMarginBottomProperty")
        set(value) = setContentMargin(3L, value)

    fun getMinimumSize(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getMinimumSizeBind, handle)
    }

    fun setContentMargin(margin: Long, offset: Double) {
        ObjectCalls.ptrcallWithLongAndDoubleArg(setContentMarginBind, handle, margin, offset)
    }

    fun setContentMarginAll(offset: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setContentMarginAllBind, handle, offset)
    }

    fun getContentMargin(margin: Long): Double {
        return ObjectCalls.ptrcallWithLongArgRetDouble(getContentMarginBind, handle, margin)
    }

    fun getMargin(margin: Long): Double {
        return ObjectCalls.ptrcallWithLongArgRetDouble(getMarginBind, handle, margin)
    }

    fun getOffset(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getOffsetBind, handle)
    }

    fun draw(canvasItem: RID, rect: Rect2) {
        ObjectCalls.ptrcallWithRIDAndRect2Arg(drawBind, handle, canvasItem, rect)
    }

    fun getCurrentItemDrawn(): CanvasItem? {
        return CanvasItem.wrap(ObjectCalls.ptrcallNoArgsRetObject(getCurrentItemDrawnBind, handle))
    }

    fun testMask(point: Vector2, rect: Rect2): Boolean {
        return ObjectCalls.ptrcallWithVector2Rect2ArgsRetBool(testMaskBind, handle, point, rect)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): StyleBox? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): StyleBox? =
            if (handle.address() == 0L) null else StyleBox(handle)

        private const val GET_MINIMUM_SIZE_HASH = 3341600327L
        private val getMinimumSizeBind by lazy {
            ObjectCalls.getMethodBind("StyleBox", "get_minimum_size", GET_MINIMUM_SIZE_HASH)
        }

        private const val SET_CONTENT_MARGIN_HASH = 4290182280L
        private val setContentMarginBind by lazy {
            ObjectCalls.getMethodBind("StyleBox", "set_content_margin", SET_CONTENT_MARGIN_HASH)
        }

        private const val SET_CONTENT_MARGIN_ALL_HASH = 373806689L
        private val setContentMarginAllBind by lazy {
            ObjectCalls.getMethodBind("StyleBox", "set_content_margin_all", SET_CONTENT_MARGIN_ALL_HASH)
        }

        private const val GET_CONTENT_MARGIN_HASH = 2869120046L
        private val getContentMarginBind by lazy {
            ObjectCalls.getMethodBind("StyleBox", "get_content_margin", GET_CONTENT_MARGIN_HASH)
        }

        private const val GET_MARGIN_HASH = 2869120046L
        private val getMarginBind by lazy {
            ObjectCalls.getMethodBind("StyleBox", "get_margin", GET_MARGIN_HASH)
        }

        private const val GET_OFFSET_HASH = 3341600327L
        private val getOffsetBind by lazy {
            ObjectCalls.getMethodBind("StyleBox", "get_offset", GET_OFFSET_HASH)
        }

        private const val DRAW_HASH = 2275962004L
        private val drawBind by lazy {
            ObjectCalls.getMethodBind("StyleBox", "draw", DRAW_HASH)
        }

        private const val GET_CURRENT_ITEM_DRAWN_HASH = 3213695180L
        private val getCurrentItemDrawnBind by lazy {
            ObjectCalls.getMethodBind("StyleBox", "get_current_item_drawn", GET_CURRENT_ITEM_DRAWN_HASH)
        }

        private const val TEST_MASK_HASH = 3735564539L
        private val testMaskBind by lazy {
            ObjectCalls.getMethodBind("StyleBox", "test_mask", TEST_MASK_HASH)
        }
    }
}
