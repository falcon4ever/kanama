package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * An object representing the state of an OpenXR spatial container.
 *
 * Generated from Godot docs: OpenXRSpatialContainerState
 */
class OpenXRSpatialContainerState(handle: GodotHandle) : RefCounted(handle) {
    /**
     * Returns the current bounds mode of the spatial container.
     *
     * Generated from Godot docs: OpenXRSpatialContainerState.get_bounds_mode
     */
    fun getBoundsMode(): OpenXRSpatialContainerState.BoundsMode {
        checkOpen()
        val ret = OpenXRSpatialContainerState.BoundsMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getBoundsModeBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the current interactability of the spatial container.
     *
     * Generated from Godot docs: OpenXRSpatialContainerState.is_interactable
     */
    fun isInteractable(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.isInteractableBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the current visibility state of the spatial container.
     *
     * Generated from Godot docs: OpenXRSpatialContainerState.is_visible
     */
    fun isVisible(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.isVisibleBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Godot's `OpenXRSpatialContainerState.BoundsMode` enum as a typed value: `.value` is the raw
     * number Godot uses, and the companion holds the named values
     * (`OpenXRSpatialContainerState.BoundsMode.<NAME>`).
     *
     * Generated from Godot docs: OpenXRSpatialContainerState.BoundsMode
     */
    @JvmInline
    value class BoundsMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The spatial container is in bounded mode.
             *
             * Generated from Godot docs: OpenXRSpatialContainerState.BOUNDS_MODE_BOUNDED
             */
            val BOUNDED: BoundsMode get() = BoundsMode(0L)
            /**
             * The spatial container is in immersive mode.
             *
             * Generated from Godot docs: OpenXRSpatialContainerState.BOUNDS_MODE_IMMERSIVE
             */
            val IMMERSIVE: BoundsMode get() = BoundsMode(1L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRSpatialContainerState? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): OpenXRSpatialContainerState? =
            if (handle.address() == 0L) null else RefCounted.owned(OpenXRSpatialContainerState(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): OpenXRSpatialContainerState? =
            if (handle.address() == 0L) null else OpenXRSpatialContainerState(GodotHandle(handle))

        /** A new OpenXRSpatialContainerState, GDScript's `OpenXRSpatialContainerState.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): OpenXRSpatialContainerState =
            RefCounted.owned(OpenXRSpatialContainerState(GodotHandle(requireConstructed(ObjectCalls.constructObject("OpenXRSpatialContainerState"), "OpenXRSpatialContainerState"))))
    }

    private object Binds {
        private const val GET_BOUNDS_MODE_HASH = 1708123309L
        @JvmField
        val getBoundsModeBind =
            ObjectCalls.getMethodBind("OpenXRSpatialContainerState", "get_bounds_mode", GET_BOUNDS_MODE_HASH)

        private const val IS_INTERACTABLE_HASH = 36873697L
        @JvmField
        val isInteractableBind =
            ObjectCalls.getMethodBind("OpenXRSpatialContainerState", "is_interactable", IS_INTERACTABLE_HASH)

        private const val IS_VISIBLE_HASH = 36873697L
        @JvmField
        val isVisibleBind =
            ObjectCalls.getMethodBind("OpenXRSpatialContainerState", "is_visible", IS_VISIBLE_HASH)
    }
}
