package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Base class for creating `ImageFormatLoader` extensions (adding support for extra image formats).
 *
 * Generated from Godot docs: ImageFormatLoaderExtension
 */
class ImageFormatLoaderExtension(handle: GodotHandle) : ImageFormatLoader(handle) {
    /**
     * Add this format loader to the engine, allowing it to recognize the file extensions returned by
     * `_get_recognized_extensions`.
     *
     * Generated from Godot docs: ImageFormatLoaderExtension.add_format_loader
     */
    fun addFormatLoader() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.addFormatLoaderBind, segment)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Remove this format loader from the engine.
     *
     * Generated from Godot docs: ImageFormatLoaderExtension.remove_format_loader
     */
    fun removeFormatLoader() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.removeFormatLoaderBind, segment)
        ObjectCalls.keepReachable(this)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ImageFormatLoaderExtension? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): ImageFormatLoaderExtension? =
            if (handle.address() == 0L) null else RefCounted.owned(ImageFormatLoaderExtension(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): ImageFormatLoaderExtension? =
            if (handle.address() == 0L) null else ImageFormatLoaderExtension(GodotHandle(handle))

        /** A new ImageFormatLoaderExtension, GDScript's `ImageFormatLoaderExtension.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): ImageFormatLoaderExtension =
            RefCounted.owned(ImageFormatLoaderExtension(GodotHandle(ObjectCalls.constructObject("ImageFormatLoaderExtension"))))
    }

    private object Binds {
        private const val ADD_FORMAT_LOADER_HASH = 3218959716L
        @JvmField
        val addFormatLoaderBind =
            ObjectCalls.getMethodBind("ImageFormatLoaderExtension", "add_format_loader", ADD_FORMAT_LOADER_HASH)

        private const val REMOVE_FORMAT_LOADER_HASH = 3218959716L
        @JvmField
        val removeFormatLoaderBind =
            ObjectCalls.getMethodBind("ImageFormatLoaderExtension", "remove_format_loader", REMOVE_FORMAT_LOADER_HASH)
    }
}
