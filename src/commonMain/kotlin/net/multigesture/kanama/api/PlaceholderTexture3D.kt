package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Vector3i

/**
 * Placeholder class for a 3-dimensional texture.
 *
 * Generated from Godot docs: PlaceholderTexture3D
 */
class PlaceholderTexture3D(handle: GodotHandle) : Texture3D(handle) {
    var size: Vector3i
        @JvmName("sizeProperty")
        get() = getSize()
        @JvmName("setSizeProperty")
        set(value) = setSize(value)

    /**
     * The texture's size (in pixels).
     *
     * Generated from Godot docs: PlaceholderTexture3D.set_size
     */
    fun setSize(size: Vector3i) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3iArg(Binds.setSizeBind, segment, size)
    }

    /**
     * The texture's size (in pixels).
     *
     * Generated from Godot docs: PlaceholderTexture3D.get_size
     */
    fun getSize(): Vector3i {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector3i(Binds.getSizeBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): PlaceholderTexture3D? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): PlaceholderTexture3D? =
            if (handle.address() == 0L) null else RefCounted.owned(PlaceholderTexture3D(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): PlaceholderTexture3D? =
            if (handle.address() == 0L) null else PlaceholderTexture3D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_SIZE_HASH = 560364750L
        @JvmField
        val setSizeBind =
            ObjectCalls.getMethodBind("PlaceholderTexture3D", "set_size", SET_SIZE_HASH)

        private const val GET_SIZE_HASH = 2785653706L
        @JvmField
        val getSizeBind =
            ObjectCalls.getMethodBind("PlaceholderTexture3D", "get_size", GET_SIZE_HASH)
    }
}
