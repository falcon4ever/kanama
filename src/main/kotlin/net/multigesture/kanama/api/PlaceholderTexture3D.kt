package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector3i

/**
 * Placeholder class for a 3-dimensional texture.
 *
 * Generated from Godot docs: PlaceholderTexture3D
 */
class PlaceholderTexture3D(handle: MemorySegment) : Texture3D(handle) {
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
        ObjectCalls.ptrcallWithVector3iArg(setSizeBind, handle, size)
    }

    /**
     * The texture's size (in pixels).
     *
     * Generated from Godot docs: PlaceholderTexture3D.get_size
     */
    fun getSize(): Vector3i {
        return ObjectCalls.ptrcallNoArgsRetVector3i(getSizeBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): PlaceholderTexture3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PlaceholderTexture3D? =
            if (handle.address() == 0L) null else PlaceholderTexture3D(handle)

        private const val SET_SIZE_HASH = 560364750L
        private val setSizeBind by lazy {
            ObjectCalls.getMethodBind("PlaceholderTexture3D", "set_size", SET_SIZE_HASH)
        }

        private const val GET_SIZE_HASH = 2785653706L
        private val getSizeBind by lazy {
            ObjectCalls.getMethodBind("PlaceholderTexture3D", "get_size", GET_SIZE_HASH)
        }
    }
}
