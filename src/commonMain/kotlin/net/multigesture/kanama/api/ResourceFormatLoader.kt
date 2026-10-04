package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Loads a specific resource type from a file.
 *
 * Generated from Godot docs: ResourceFormatLoader
 */
class ResourceFormatLoader(handle: GodotHandle) : RefCounted(handle) {
    // No conservative instance methods emitted yet.

    /**
     * Godot's `ResourceFormatLoader.CacheMode` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`ResourceFormatLoader.CacheMode.<NAME>`).
     *
     * Generated from Godot docs: ResourceFormatLoader.CacheMode
     */
    @JvmInline
    value class CacheMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Neither the main resource (the one requested to be loaded) nor any of its subresources are
             * retrieved from cache nor stored into it. Dependencies (external resources) are loaded with
             * `CacheMode.REUSE`.
             *
             * Generated from Godot docs: ResourceFormatLoader.CACHE_MODE_IGNORE
             */
            val IGNORE: CacheMode get() = CacheMode(0L)
            /**
             * The main resource (the one requested to be loaded), its subresources, and its dependencies
             * (external resources) are retrieved from cache if present, instead of loaded. Those not cached
             * are loaded and then stored into the cache. The same rules are propagated recursively down the
             * tree of dependencies (external resources).
             *
             * Generated from Godot docs: ResourceFormatLoader.CACHE_MODE_REUSE
             */
            val REUSE: CacheMode get() = CacheMode(1L)
            /**
             * Like `CacheMode.REUSE`, but the cache is checked for the main resource (the one requested to be
             * loaded) as well as for each of its subresources. Those already in the cache, as long as the
             * loaded and cached types match, have their data refreshed from storage into the already existing
             * instances. Otherwise, they are recreated as completely new objects.
             *
             * Generated from Godot docs: ResourceFormatLoader.CACHE_MODE_REPLACE
             */
            val REPLACE: CacheMode get() = CacheMode(2L)
            /**
             * Like `CacheMode.IGNORE`, but propagated recursively down the tree of dependencies (external
             * resources).
             *
             * Generated from Godot docs: ResourceFormatLoader.CACHE_MODE_IGNORE_DEEP
             */
            val IGNORE_DEEP: CacheMode get() = CacheMode(3L)
            /**
             * Like `CacheMode.REPLACE`, but propagated recursively down the tree of dependencies (external
             * resources).
             *
             * Generated from Godot docs: ResourceFormatLoader.CACHE_MODE_REPLACE_DEEP
             */
            val REPLACE_DEEP: CacheMode get() = CacheMode(4L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ResourceFormatLoader? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): ResourceFormatLoader? =
            if (handle.address() == 0L) null else RefCounted.owned(ResourceFormatLoader(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): ResourceFormatLoader? =
            if (handle.address() == 0L) null else ResourceFormatLoader(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
