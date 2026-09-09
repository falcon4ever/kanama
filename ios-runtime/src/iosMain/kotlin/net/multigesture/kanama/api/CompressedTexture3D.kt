package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: CompressedTexture3D
 */
class CompressedTexture3D(handle: MemorySegment) : Texture3D(handle) {
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
        fun fromHandle(handle: MemorySegment): CompressedTexture3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): CompressedTexture3D? =
            if (handle.address() == 0L) null else CompressedTexture3D(handle)

        private const val LOAD_HASH = 166001499L
        private val loadBind by lazy {
            ObjectCalls.getMethodBind("CompressedTexture3D", "load", LOAD_HASH)
        }

        private const val GET_LOAD_PATH_HASH = 201670096L
        private val getLoadPathBind by lazy {
            ObjectCalls.getMethodBind("CompressedTexture3D", "get_load_path", GET_LOAD_PATH_HASH)
        }
    }
}
