package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: CSGPrimitive3D
 */
open class CSGPrimitive3D(handle: GodotHandle) : CSGShape3D(handle) {
    var flipFaces: Boolean
        @JvmName("flipFacesProperty")
        get() = getFlipFaces()
        @JvmName("setFlipFacesProperty")
        set(value) = setFlipFaces(value)

    fun setFlipFaces(flipFaces: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFlipFacesBind, segment, flipFaces)
    }

    fun getFlipFaces(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getFlipFacesBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): CSGPrimitive3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): CSGPrimitive3D? =
            if (handle.address() == 0L) null else CSGPrimitive3D(GodotHandle(handle))

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
