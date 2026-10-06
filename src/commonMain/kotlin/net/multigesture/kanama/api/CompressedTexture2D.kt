package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Texture with 2 dimensions, optionally compressed.
 *
 * Generated from Godot docs: CompressedTexture2D
 */
class CompressedTexture2D(handle: GodotHandle) : Texture2D(handle) {
    val loadPath: String
        @JvmName("loadPathProperty")
        get() = getLoadPath()

    /**
     * The `CompressedTexture2D`'s file path to a `.ctex` file.
     *
     * Generated from Godot docs: CompressedTexture2D.load
     */
    fun load(path: String): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithStringArgRetLong(Binds.loadBind, segment, path))
    }

    /**
     * The `CompressedTexture2D`'s file path to a `.ctex` file.
     *
     * Generated from Godot docs: CompressedTexture2D.get_load_path
     */
    fun getLoadPath(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getLoadPathBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): CompressedTexture2D? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): CompressedTexture2D? =
            if (handle.address() == 0L) null else RefCounted.owned(CompressedTexture2D(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): CompressedTexture2D? =
            if (handle.address() == 0L) null else CompressedTexture2D(GodotHandle(handle))
    }

    private object Binds {
        private const val LOAD_HASH = 166001499L
        @JvmField
        val loadBind =
            ObjectCalls.getMethodBind("CompressedTexture2D", "load", LOAD_HASH)

        private const val GET_LOAD_PATH_HASH = 201670096L
        @JvmField
        val getLoadPathBind =
            ObjectCalls.getMethodBind("CompressedTexture2D", "get_load_path", GET_LOAD_PATH_HASH)
    }
}
