package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * A node to draw 3D lines in space. Note: This class currently has no functionality and only
 * serves as a base class for `Trail3D`.
 *
 * Generated from Godot docs: Line3D
 */
open class Line3D(handle: GodotHandle) : GeometryInstance3D(handle) {
    // No conservative instance methods emitted yet.

    /**
     * Godot's `Line3D.MeshAlignment` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`Line3D.MeshAlignment.<NAME>`).
     *
     * Generated from Godot docs: Line3D.MeshAlignment
     */
    @JvmInline
    value class MeshAlignment(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Align the line normal to the local Y axis of the line node.
             *
             * Generated from Godot docs: Line3D.MESH_ALIGNMENT_LOCAL
             */
            val LOCAL: MeshAlignment get() = MeshAlignment(0L)
            /**
             * Align the normal of each section of the Line3D to face the camera. See also
             * `SpriteBase3D.billboard` and `BaseMaterial3D.BillboardMode`
             *
             * Generated from Godot docs: Line3D.MESH_ALIGNMENT_BILLBOARD
             */
            val BILLBOARD: MeshAlignment get() = MeshAlignment(1L)
            /**
             * Represents the size of the `MeshAlignment` enum.
             *
             * Generated from Godot docs: Line3D.MESH_ALIGNMENT_MAX
             */
            val MAX: MeshAlignment get() = MeshAlignment(2L)
        }
    }

    /**
     * Godot's `Line3D.TilingMode` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`Line3D.TilingMode.<NAME>`).
     *
     * Generated from Godot docs: Line3D.TilingMode
     */
    @JvmInline
    value class TilingMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Assign UVs from `0` to `1` across the length of the line.
             *
             * Generated from Godot docs: Line3D.TILING_MODE_STRETCH
             */
            val MODE_STRETCH: TilingMode get() = TilingMode(0L)
            /**
             * Assign UVs from `0` to current length, across the length of the line.
             *
             * Generated from Godot docs: Line3D.TILING_MODE_TILE
             */
            val MODE_TILE: TilingMode get() = TilingMode(1L)
            /**
             * Represents the size of the `TilingMode` enum.
             *
             * Generated from Godot docs: Line3D.TILING_MAX
             */
            val MAX: TilingMode get() = TilingMode(2L)
        }
    }

    /**
     * Godot's `Line3D.MaterialMode` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`Line3D.MaterialMode.<NAME>`).
     *
     * Generated from Godot docs: Line3D.MaterialMode
     */
    @JvmInline
    value class MaterialMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Assign a built-in mix material to the line.
             *
             * Generated from Godot docs: Line3D.MATERIAL_MODE_MIX
             */
            val MIX: MaterialMode get() = MaterialMode(0L)
            /**
             * Assign a built-in additive material to the line.
             *
             * Generated from Godot docs: Line3D.MATERIAL_MODE_ADD
             */
            val ADD: MaterialMode get() = MaterialMode(1L)
            /**
             * Assign a custom material to the line.
             *
             * Generated from Godot docs: Line3D.MATERIAL_MODE_CUSTOM
             */
            val CUSTOM: MaterialMode get() = MaterialMode(2L)
            /**
             * Represents the size of the `MaterialMode` enum.
             *
             * Generated from Godot docs: Line3D.MATERIAL_MODE_MAX
             */
            val MAX: MaterialMode get() = MaterialMode(3L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Line3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): Line3D? =
            if (handle.address() == 0L) null else Line3D(GodotHandle(handle))

        /** A new Line3D, GDScript's `Line3D.new()`. Add it to the tree, or `queueFree()` it. */
        @JvmStatic
        fun create(): Line3D =
            Line3D(GodotHandle(requireConstructed(ObjectCalls.constructObject("Line3D"), "Line3D")))
    }
}
