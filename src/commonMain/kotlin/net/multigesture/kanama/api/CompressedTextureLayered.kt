package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Base class for texture arrays that can optionally be compressed.
 *
 * Generated from Godot docs: CompressedTextureLayered
 */
open class CompressedTextureLayered(handle: GodotHandle) : TextureLayered(handle) {
    val loadPath: String
        @JvmName("loadPathProperty")
        get() = getLoadPath()

    /**
     * The path the texture should be loaded from.
     *
     * Generated from Godot docs: CompressedTextureLayered.load
     */
    fun load(path: String): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithStringArgRetLong(Binds.loadBind, segment, path))
    }

    /**
     * The path the texture should be loaded from.
     *
     * Generated from Godot docs: CompressedTextureLayered.get_load_path
     */
    fun getLoadPath(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getLoadPathBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): CompressedTextureLayered? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): CompressedTextureLayered? =
            if (handle.address() == 0L) null else RefCounted.owned(CompressedTextureLayered(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): CompressedTextureLayered? =
            if (handle.address() == 0L) null else CompressedTextureLayered(GodotHandle(handle))
    }

    private object Binds {
        private const val LOAD_HASH = 166001499L
        @JvmField
        val loadBind =
            ObjectCalls.getMethodBind("CompressedTextureLayered", "load", LOAD_HASH)

        private const val GET_LOAD_PATH_HASH = 201670096L
        @JvmField
        val getLoadPathBind =
            ObjectCalls.getMethodBind("CompressedTextureLayered", "get_load_path", GET_LOAD_PATH_HASH)
    }
}
