package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Base class for CSG primitives.
 *
 * Generated from Godot docs: CSGPrimitive3D
 */
open class CSGPrimitive3D(handle: GodotHandle) : CSGShape3D(handle) {
    var flipFaces: Boolean
        @JvmName("flipFacesProperty")
        get() = getFlipFaces()
        @JvmName("setFlipFacesProperty")
        set(value) = setFlipFaces(value)

    /**
     * If set, the order of the vertices in each triangle are reversed resulting in the backside of the
     * mesh being drawn.
     *
     * Generated from Godot docs: CSGPrimitive3D.set_flip_faces
     */
    fun setFlipFaces(flipFaces: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setFlipFacesBind, segment, flipFaces)
    }

    /**
     * If set, the order of the vertices in each triangle are reversed resulting in the backside of the
     * mesh being drawn.
     *
     * Generated from Godot docs: CSGPrimitive3D.get_flip_faces
     */
    fun getFlipFaces(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getFlipFacesBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): CSGPrimitive3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): CSGPrimitive3D? =
            if (handle.address() == 0L) null else CSGPrimitive3D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_FLIP_FACES_HASH = 2586408642L
        @JvmField
        val setFlipFacesBind =
            ObjectCalls.getMethodBind("CSGPrimitive3D", "set_flip_faces", SET_FLIP_FACES_HASH)

        private const val GET_FLIP_FACES_HASH = 2240911060L
        @JvmField
        val getFlipFacesBind =
            ObjectCalls.getMethodBind("CSGPrimitive3D", "get_flip_faces", GET_FLIP_FACES_HASH)
    }
}
