package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * Object for storing the queries plane alignment result data.
 *
 * Generated from Godot docs: OpenXRSpatialComponentPlaneAlignmentList
 */
class OpenXRSpatialComponentPlaneAlignmentList(handle: GodotHandle) : OpenXRSpatialComponentData(handle) {
    /**
     * Returns the plane alignment for the parent entity at this `index`.
     *
     * Generated from Godot docs: OpenXRSpatialComponentPlaneAlignmentList.get_plane_alignment
     */
    fun getPlaneAlignment(index: Long): OpenXRSpatialComponentPlaneAlignmentList.PlaneAlignment {
        checkOpen()
        val ret = OpenXRSpatialComponentPlaneAlignmentList.PlaneAlignment(ObjectCalls.ptrcallWithLongArgRetLong(Binds.getPlaneAlignmentBind, segment, index))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Godot's `OpenXRSpatialComponentPlaneAlignmentList.PlaneAlignment` enum as a typed value:
     * `.value` is the raw number Godot uses, and the companion holds the named values
     * (`OpenXRSpatialComponentPlaneAlignmentList.PlaneAlignment.<NAME>`).
     *
     * Generated from Godot docs: OpenXRSpatialComponentPlaneAlignmentList.PlaneAlignment
     */
    @JvmInline
    value class PlaneAlignment(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Plane is facing upward.
             *
             * Generated from Godot docs: OpenXRSpatialComponentPlaneAlignmentList.PLANE_ALIGNMENT_HORIZONTAL_UPWARD
             */
            val HORIZONTAL_UPWARD: PlaneAlignment get() = PlaneAlignment(0L)
            /**
             * Plane is facing downwards.
             *
             * Generated from Godot docs: OpenXRSpatialComponentPlaneAlignmentList.PLANE_ALIGNMENT_HORIZONTAL_DOWNWARD
             */
            val HORIZONTAL_DOWNWARD: PlaneAlignment get() = PlaneAlignment(1L)
            /**
             * Plane is vertically aligned.
             *
             * Generated from Godot docs: OpenXRSpatialComponentPlaneAlignmentList.PLANE_ALIGNMENT_VERTICAL
             */
            val VERTICAL: PlaneAlignment get() = PlaneAlignment(2L)
            /**
             * Plane has an arbitrary alignment.
             *
             * Generated from Godot docs: OpenXRSpatialComponentPlaneAlignmentList.PLANE_ALIGNMENT_ARBITRARY
             */
            val ARBITRARY: PlaneAlignment get() = PlaneAlignment(3L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRSpatialComponentPlaneAlignmentList? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): OpenXRSpatialComponentPlaneAlignmentList? =
            if (handle.address() == 0L) null else RefCounted.owned(OpenXRSpatialComponentPlaneAlignmentList(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): OpenXRSpatialComponentPlaneAlignmentList? =
            if (handle.address() == 0L) null else OpenXRSpatialComponentPlaneAlignmentList(GodotHandle(handle))

        /** A new OpenXRSpatialComponentPlaneAlignmentList, GDScript's `OpenXRSpatialComponentPlaneAlignmentList.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): OpenXRSpatialComponentPlaneAlignmentList =
            RefCounted.owned(OpenXRSpatialComponentPlaneAlignmentList(GodotHandle(requireConstructed(ObjectCalls.constructObject("OpenXRSpatialComponentPlaneAlignmentList"), "OpenXRSpatialComponentPlaneAlignmentList"))))
    }

    private object Binds {
        private const val GET_PLANE_ALIGNMENT_HASH = 3340200270L
        @JvmField
        val getPlaneAlignmentBind =
            ObjectCalls.getMethodBind("OpenXRSpatialComponentPlaneAlignmentList", "get_plane_alignment", GET_PLANE_ALIGNMENT_HASH)
    }
}
