package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Base class for resource importers.
 *
 * Generated from Godot docs: ResourceImporter
 */
open class ResourceImporter(handle: GodotHandle) : RefCounted(handle) {
    // No conservative instance methods emitted yet.

    /**
     * Godot's `ResourceImporter.ImportOrder` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`ResourceImporter.ImportOrder.<NAME>`).
     *
     * Generated from Godot docs: ResourceImporter.ImportOrder
     */
    @JvmInline
    value class ImportOrder(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The default import order.
             *
             * Generated from Godot docs: ResourceImporter.IMPORT_ORDER_DEFAULT
             */
            val DEFAULT: ImportOrder get() = ImportOrder(0L)
            /**
             * The import order for scenes, which ensures scenes are imported after all other core resources
             * such as textures. Custom importers should generally have an import order lower than `100` to
             * avoid issues when importing scenes that rely on custom resources.
             *
             * Generated from Godot docs: ResourceImporter.IMPORT_ORDER_SCENE
             */
            val SCENE: ImportOrder get() = ImportOrder(100L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ResourceImporter? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): ResourceImporter? =
            if (handle.address() == 0L) null else RefCounted.owned(ResourceImporter(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): ResourceImporter? =
            if (handle.address() == 0L) null else ResourceImporter(GodotHandle(handle))
    }
}
