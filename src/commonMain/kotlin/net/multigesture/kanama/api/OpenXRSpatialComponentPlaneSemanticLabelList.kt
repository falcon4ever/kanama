package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * Object for storing the queries plane semantic label result data.
 *
 * Generated from Godot docs: OpenXRSpatialComponentPlaneSemanticLabelList
 */
class OpenXRSpatialComponentPlaneSemanticLabelList(handle: GodotHandle) : OpenXRSpatialComponentData(handle) {
    /**
     * Returns the plane semantic label for the parent entity at this `index`.
     *
     * Generated from Godot docs: OpenXRSpatialComponentPlaneSemanticLabelList.get_plane_semantic_label
     */
    fun getPlaneSemanticLabel(index: Long): OpenXRSpatialComponentPlaneSemanticLabelList.PlaneSemanticLabel {
        checkOpen()
        val ret = OpenXRSpatialComponentPlaneSemanticLabelList.PlaneSemanticLabel(ObjectCalls.ptrcallWithLongArgRetLong(Binds.getPlaneSemanticLabelBind, segment, index))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Godot's `OpenXRSpatialComponentPlaneSemanticLabelList.PlaneSemanticLabel` enum as a typed value:
     * `.value` is the raw number Godot uses, and the companion holds the named values
     * (`OpenXRSpatialComponentPlaneSemanticLabelList.PlaneSemanticLabel.<NAME>`).
     *
     * Generated from Godot docs: OpenXRSpatialComponentPlaneSemanticLabelList.PlaneSemanticLabel
     */
    @JvmInline
    value class PlaneSemanticLabel(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Uncategorized plane.
             *
             * Generated from Godot docs: OpenXRSpatialComponentPlaneSemanticLabelList.PLANE_SEMANTIC_LABEL_UNCATEGORIZED
             */
            val UNCATEGORIZED: PlaneSemanticLabel get() = PlaneSemanticLabel(1L)
            /**
             * Plane represents a floor.
             *
             * Generated from Godot docs: OpenXRSpatialComponentPlaneSemanticLabelList.PLANE_SEMANTIC_LABEL_FLOOR
             */
            val FLOOR: PlaneSemanticLabel get() = PlaneSemanticLabel(2L)
            /**
             * Plane represents a wall.
             *
             * Generated from Godot docs: OpenXRSpatialComponentPlaneSemanticLabelList.PLANE_SEMANTIC_LABEL_WALL
             */
            val WALL: PlaneSemanticLabel get() = PlaneSemanticLabel(3L)
            /**
             * Plane represents a ceiling.
             *
             * Generated from Godot docs: OpenXRSpatialComponentPlaneSemanticLabelList.PLANE_SEMANTIC_LABEL_CEILING
             */
            val CEILING: PlaneSemanticLabel get() = PlaneSemanticLabel(4L)
            /**
             * Plane represents the surface of a table.
             *
             * Generated from Godot docs: OpenXRSpatialComponentPlaneSemanticLabelList.PLANE_SEMANTIC_LABEL_TABLE
             */
            val TABLE: PlaneSemanticLabel get() = PlaneSemanticLabel(5L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRSpatialComponentPlaneSemanticLabelList? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): OpenXRSpatialComponentPlaneSemanticLabelList? =
            if (handle.address() == 0L) null else RefCounted.owned(OpenXRSpatialComponentPlaneSemanticLabelList(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): OpenXRSpatialComponentPlaneSemanticLabelList? =
            if (handle.address() == 0L) null else OpenXRSpatialComponentPlaneSemanticLabelList(GodotHandle(handle))

        /** A new OpenXRSpatialComponentPlaneSemanticLabelList, GDScript's `OpenXRSpatialComponentPlaneSemanticLabelList.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): OpenXRSpatialComponentPlaneSemanticLabelList =
            RefCounted.owned(OpenXRSpatialComponentPlaneSemanticLabelList(GodotHandle(requireConstructed(ObjectCalls.constructObject("OpenXRSpatialComponentPlaneSemanticLabelList"), "OpenXRSpatialComponentPlaneSemanticLabelList"))))
    }

    private object Binds {
        private const val GET_PLANE_SEMANTIC_LABEL_HASH = 1889332427L
        @JvmField
        val getPlaneSemanticLabelBind =
            ObjectCalls.getMethodBind("OpenXRSpatialComponentPlaneSemanticLabelList", "get_plane_semantic_label", GET_PLANE_SEMANTIC_LABEL_HASH)
    }
}
