package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Base class for creating `ImageFormatLoader` extensions (adding support for extra image formats).
 *
 * Generated from Godot docs: ImageFormatLoaderExtension
 */
class ImageFormatLoaderExtension(handle: MemorySegment) : ImageFormatLoader(handle) {
    fun addFormatLoader() {
        ObjectCalls.ptrcallNoArgs(addFormatLoaderBind, handle)
    }

    fun removeFormatLoader() {
        ObjectCalls.ptrcallNoArgs(removeFormatLoaderBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): ImageFormatLoaderExtension? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ImageFormatLoaderExtension? =
            if (handle.address() == 0L) null else ImageFormatLoaderExtension(handle)

        private const val ADD_FORMAT_LOADER_HASH = 3218959716L
        private val addFormatLoaderBind by lazy {
            ObjectCalls.getMethodBind("ImageFormatLoaderExtension", "add_format_loader", ADD_FORMAT_LOADER_HASH)
        }

        private const val REMOVE_FORMAT_LOADER_HASH = 3218959716L
        private val removeFormatLoaderBind by lazy {
            ObjectCalls.getMethodBind("ImageFormatLoaderExtension", "remove_format_loader", REMOVE_FORMAT_LOADER_HASH)
        }
    }
}
