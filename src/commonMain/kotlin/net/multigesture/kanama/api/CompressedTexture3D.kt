package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Texture with 3 dimensions, optionally compressed.
 *
 * Generated from Godot docs: CompressedTexture3D
 */
class CompressedTexture3D(handle: GodotHandle) : Texture3D(handle) {
    val loadPath: String
        @JvmName("loadPathProperty")
        get() = getLoadPath()

    /**
     * The `CompressedTexture3D`'s file path to a `.ctex3d` file.
     *
     * Generated from Godot docs: CompressedTexture3D.load
     */
    fun load(path: String): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetLong(loadBind, segment, path)
    }

    /**
     * The `CompressedTexture3D`'s file path to a `.ctex3d` file.
     *
     * Generated from Godot docs: CompressedTexture3D.get_load_path
     */
    fun getLoadPath(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getLoadPathBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): CompressedTexture3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): CompressedTexture3D? =
            if (handle.address() == 0L) null else CompressedTexture3D(GodotHandle(handle))

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
