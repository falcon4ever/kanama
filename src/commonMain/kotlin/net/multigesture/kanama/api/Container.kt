package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Rect2

/**
 * Base class for all GUI containers.
 *
 * Generated from Godot docs: Container
 */
open class Container(handle: GodotHandle) : Control(handle) {
    var accessibilityRegion: Boolean
        @JvmName("accessibilityRegionProperty")
        get() = isAccessibilityRegion()
        @JvmName("setAccessibilityRegionProperty")
        set(value) = setAccessibilityRegion(value)

    /**
     * Queue resort of the contained children. This is called automatically anyway, but can be called
     * upon request.
     *
     * Generated from Godot docs: Container.queue_sort
     */
    fun queueSort() {
        ObjectCalls.ptrcallNoArgs(Binds.queueSortBind, segment)
    }

    /**
     * Fit a child control in a given rect. This is mainly a helper for creating custom container
     * classes.
     *
     * Generated from Godot docs: Container.fit_child_in_rect
     */
    fun fitChildInRect(child: Control, rect: Rect2) {
        ObjectCalls.ptrcallWithObjectAndRect2Arg(Binds.fitChildInRectBind, segment, child.segment, rect)
    }

    /**
     * If `true`, this container is marked as a region for accessibility. Use
     * `Control.accessibility_name` to give the region a descriptive name. Screen readers can navigate
     * between regions using landmark navigation.
     *
     * Generated from Godot docs: Container.set_accessibility_region
     */
    fun setAccessibilityRegion(region: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setAccessibilityRegionBind, segment, region)
    }

    /**
     * If `true`, this container is marked as a region for accessibility. Use
     * `Control.accessibility_name` to give the region a descriptive name. Screen readers can navigate
     * between regions using landmark navigation.
     *
     * Generated from Godot docs: Container.is_accessibility_region
     */
    fun isAccessibilityRegion(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isAccessibilityRegionBind, segment)
    }

    /** Signal `pre_sort_children()`; see [TypedSignal]. */
    val preSortChildren: Signal0
        @JvmName("preSortChildrenTypedSignal")
        get() = Signal0(this, "pre_sort_children")

    /** Signal `sort_children()`; see [TypedSignal]. */
    val sortChildren: Signal0
        @JvmName("sortChildrenTypedSignal")
        get() = Signal0(this, "sort_children")

    object Signals {
        const val preSortChildren: String = "pre_sort_children"
        const val sortChildren: String = "sort_children"
    }

    companion object {
        const val NOTIFICATION_PRE_SORT_CHILDREN: Long = 50L
        const val NOTIFICATION_SORT_CHILDREN: Long = 51L

        @JvmStatic
        fun fromHandle(handle: GodotHandle): Container? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): Container? =
            if (handle.address() == 0L) null else Container(GodotHandle(handle))
    }

    private object Binds {
        private const val QUEUE_SORT_HASH = 3218959716L
        @JvmField
        val queueSortBind =
            ObjectCalls.getMethodBind("Container", "queue_sort", QUEUE_SORT_HASH)

        private const val FIT_CHILD_IN_RECT_HASH = 1993438598L
        @JvmField
        val fitChildInRectBind =
            ObjectCalls.getMethodBind("Container", "fit_child_in_rect", FIT_CHILD_IN_RECT_HASH)

        private const val SET_ACCESSIBILITY_REGION_HASH = 2586408642L
        @JvmField
        val setAccessibilityRegionBind =
            ObjectCalls.getMethodBind("Container", "set_accessibility_region", SET_ACCESSIBILITY_REGION_HASH)

        private const val IS_ACCESSIBILITY_REGION_HASH = 36873697L
        @JvmField
        val isAccessibilityRegionBind =
            ObjectCalls.getMethodBind("Container", "is_accessibility_region", IS_ACCESSIBILITY_REGION_HASH)
    }
}
