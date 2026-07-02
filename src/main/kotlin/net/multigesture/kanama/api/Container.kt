package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Rect2

/**
 * Base class for all GUI containers.
 *
 * Generated from Godot docs: Container
 */
open class Container(handle: MemorySegment) : Control(handle) {
    var accessibilityRegion: Boolean
        @JvmName("accessibilityRegionProperty")
        get() = isAccessibilityRegion()
        @JvmName("setAccessibilityRegionProperty")
        set(value) = setAccessibilityRegion(value)

    fun queueSort() {
        ObjectCalls.ptrcallNoArgs(queueSortBind, handle)
    }

    fun fitChildInRect(child: Control, rect: Rect2) {
        ObjectCalls.ptrcallWithObjectAndRect2Arg(fitChildInRectBind, handle, child.handle, rect)
    }

    fun setAccessibilityRegion(region: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAccessibilityRegionBind, handle, region)
    }

    fun isAccessibilityRegion(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAccessibilityRegionBind, handle)
    }

    object Signals {
        const val preSortChildren: String = "pre_sort_children"
        const val sortChildren: String = "sort_children"
    }

    companion object {
        const val NOTIFICATION_PRE_SORT_CHILDREN: Long = 50L
        const val NOTIFICATION_SORT_CHILDREN: Long = 51L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): Container? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Container? =
            if (handle.address() == 0L) null else Container(handle)

        private const val QUEUE_SORT_HASH = 3218959716L
        private val queueSortBind by lazy {
            ObjectCalls.getMethodBind("Container", "queue_sort", QUEUE_SORT_HASH)
        }

        private const val FIT_CHILD_IN_RECT_HASH = 1993438598L
        private val fitChildInRectBind by lazy {
            ObjectCalls.getMethodBind("Container", "fit_child_in_rect", FIT_CHILD_IN_RECT_HASH)
        }

        private const val SET_ACCESSIBILITY_REGION_HASH = 2586408642L
        private val setAccessibilityRegionBind by lazy {
            ObjectCalls.getMethodBind("Container", "set_accessibility_region", SET_ACCESSIBILITY_REGION_HASH)
        }

        private const val IS_ACCESSIBILITY_REGION_HASH = 36873697L
        private val isAccessibilityRegionBind by lazy {
            ObjectCalls.getMethodBind("Container", "is_accessibility_region", IS_ACCESSIBILITY_REGION_HASH)
        }
    }
}
