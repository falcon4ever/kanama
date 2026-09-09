package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: CompressedTextureLayered
 */
open class CompressedTextureLayered(handle: MemorySegment) : TextureLayered(handle) {
    val loadPath: String
        @JvmName("loadPathProperty")
        get() = getLoadPath()

    fun load(path: String): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetLong(loadBind, handle, path)
    }

    fun getLoadPath(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getLoadPathBind, handle)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): CompressedTextureLayered? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): CompressedTextureLayered? =
            if (handle.address() == 0L) null else CompressedTextureLayered(handle)

        private const val LOAD_HASH = 166001499L
        private val loadBind by lazy {
            ObjectCalls.getMethodBind("CompressedTextureLayered", "load", LOAD_HASH)
        }

        private const val GET_LOAD_PATH_HASH = 201670096L
        private val getLoadPathBind by lazy {
            ObjectCalls.getMethodBind("CompressedTextureLayered", "get_load_path", GET_LOAD_PATH_HASH)
        }
    }
}
