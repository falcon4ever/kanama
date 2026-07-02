package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: CSGPrimitive3D
 */
open class CSGPrimitive3D(handle: MemorySegment) : CSGShape3D(handle) {
    var flipFaces: Boolean
        @JvmName("flipFacesProperty")
        get() = getFlipFaces()
        @JvmName("setFlipFacesProperty")
        set(value) = setFlipFaces(value)

    fun setFlipFaces(flipFaces: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFlipFacesBind, handle, flipFaces)
    }

    fun getFlipFaces(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getFlipFacesBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): CSGPrimitive3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): CSGPrimitive3D? =
            if (handle.address() == 0L) null else CSGPrimitive3D(handle)

        private const val SET_FLIP_FACES_HASH = 2586408642L
        private val setFlipFacesBind by lazy {
            ObjectCalls.getMethodBind("CSGPrimitive3D", "set_flip_faces", SET_FLIP_FACES_HASH)
        }

        private const val GET_FLIP_FACES_HASH = 2240911060L
        private val getFlipFacesBind by lazy {
            ObjectCalls.getMethodBind("CSGPrimitive3D", "get_flip_faces", GET_FLIP_FACES_HASH)
        }
    }
}
