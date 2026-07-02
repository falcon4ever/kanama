package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector3

/**
 * A 3D trimesh shape used for physics collision.
 *
 * Generated from Godot docs: ConcavePolygonShape3D
 */
class ConcavePolygonShape3D(handle: MemorySegment) : Shape3D(handle) {
    var data: List<Vector3>
        @JvmName("dataProperty")
        get() = getFaces()
        @JvmName("setDataProperty")
        set(value) = setFaces(value)

    var backfaceCollision: Boolean
        @JvmName("backfaceCollisionProperty")
        get() = isBackfaceCollisionEnabled()
        @JvmName("setBackfaceCollisionProperty")
        set(value) = setBackfaceCollisionEnabled(value)

    fun setFaces(faces: List<Vector3>) {
        ObjectCalls.ptrcallWithPackedVector3ListArg(setFacesBind, handle, faces)
    }

    fun getFaces(): List<Vector3> {
        return ObjectCalls.ptrcallNoArgsRetPackedVector3List(getFacesBind, handle)
    }

    fun setBackfaceCollisionEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setBackfaceCollisionEnabledBind, handle, enabled)
    }

    fun isBackfaceCollisionEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isBackfaceCollisionEnabledBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): ConcavePolygonShape3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ConcavePolygonShape3D? =
            if (handle.address() == 0L) null else ConcavePolygonShape3D(handle)

        private const val SET_FACES_HASH = 334873810L
        private val setFacesBind by lazy {
            ObjectCalls.getMethodBind("ConcavePolygonShape3D", "set_faces", SET_FACES_HASH)
        }

        private const val GET_FACES_HASH = 497664490L
        private val getFacesBind by lazy {
            ObjectCalls.getMethodBind("ConcavePolygonShape3D", "get_faces", GET_FACES_HASH)
        }

        private const val SET_BACKFACE_COLLISION_ENABLED_HASH = 2586408642L
        private val setBackfaceCollisionEnabledBind by lazy {
            ObjectCalls.getMethodBind("ConcavePolygonShape3D", "set_backface_collision_enabled", SET_BACKFACE_COLLISION_ENABLED_HASH)
        }

        private const val IS_BACKFACE_COLLISION_ENABLED_HASH = 36873697L
        private val isBackfaceCollisionEnabledBind by lazy {
            ObjectCalls.getMethodBind("ConcavePolygonShape3D", "is_backface_collision_enabled", IS_BACKFACE_COLLISION_ENABLED_HASH)
        }
    }
}
