package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: ImageFormatLoaderExtension
 */
class ImageFormatLoaderExtension(handle: MemorySegment) : ImageFormatLoader(handle) {
    fun addFormatLoader() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(addFormatLoaderBind, handle)
    }

    fun removeFormatLoader() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(removeFormatLoaderBind, handle)
    }

    companion object {
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
