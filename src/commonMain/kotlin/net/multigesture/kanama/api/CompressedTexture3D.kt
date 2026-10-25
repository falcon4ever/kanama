package net.multigesture.kanama.api

import kotlin.jvm.JvmField
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
    fun load(path: String): GodotError {
        checkOpen()
        val ret = GodotError(ObjectCalls.ptrcallWithStringArgRetLong(Binds.loadBind, segment, path))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The `CompressedTexture3D`'s file path to a `.ctex3d` file.
     *
     * Generated from Godot docs: CompressedTexture3D.get_load_path
     */
    fun getLoadPath(): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetString(Binds.getLoadPathBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): CompressedTexture3D? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): CompressedTexture3D? =
            if (handle.address() == 0L) null else RefCounted.owned(CompressedTexture3D(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): CompressedTexture3D? =
            if (handle.address() == 0L) null else CompressedTexture3D(GodotHandle(handle))

        /** A new CompressedTexture3D, GDScript's `CompressedTexture3D.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): CompressedTexture3D =
            RefCounted.owned(CompressedTexture3D(GodotHandle(ObjectCalls.constructObject("CompressedTexture3D"))))
    }

    private object Binds {
        private const val LOAD_HASH = 166001499L
        @JvmField
        val loadBind =
            ObjectCalls.getMethodBind("CompressedTexture3D", "load", LOAD_HASH)

        private const val GET_LOAD_PATH_HASH = 201670096L
        @JvmField
        val getLoadPathBind =
            ObjectCalls.getMethodBind("CompressedTexture3D", "get_load_path", GET_LOAD_PATH_HASH)
    }
}
