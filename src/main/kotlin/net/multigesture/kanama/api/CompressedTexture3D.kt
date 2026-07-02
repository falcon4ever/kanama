package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Texture with 3 dimensions, optionally compressed.
 *
 * Generated from Godot docs: CompressedTexture3D
 */
class CompressedTexture3D(handle: MemorySegment) : Texture3D(handle) {
    val loadPath: String
        @JvmName("loadPathProperty")
        get() = getLoadPath()

    /**
     * The `CompressedTexture3D`'s file path to a `.ctex3d` file.
     *
     * Generated from Godot docs: CompressedTexture3D.load
     */
    fun load(path: String): Long {
        return ObjectCalls.ptrcallWithStringArgRetLong(loadBind, handle, path)
    }

    fun getLoadPath(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getLoadPathBind, handle)
    }

    companion object {
        @JvmStatic
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
