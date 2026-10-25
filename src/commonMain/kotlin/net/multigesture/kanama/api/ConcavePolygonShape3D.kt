package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Vector3

/**
 * A 3D trimesh shape used for physics collision.
 *
 * Generated from Godot docs: ConcavePolygonShape3D
 */
class ConcavePolygonShape3D(handle: GodotHandle) : Shape3D(handle) {
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

    /**
     * Sets the faces of the trimesh shape from an array of vertices. The `faces` array should be
     * composed of triples such that each triple of vertices defines a triangle.
     *
     * Generated from Godot docs: ConcavePolygonShape3D.set_faces
     */
    fun setFaces(faces: List<Vector3>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedVector3ListArg(Binds.setFacesBind, segment, faces)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns the faces of the trimesh shape as an array of vertices. The array (of length divisible
     * by three) is naturally divided into triples; each triple of vertices defines a triangle.
     *
     * Generated from Godot docs: ConcavePolygonShape3D.get_faces
     */
    fun getFaces(): List<Vector3> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetPackedVector3List(Binds.getFacesBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * If set to `true`, collisions occur on both sides of the concave shape faces. Otherwise they
     * occur only along the face normals.
     *
     * Generated from Godot docs: ConcavePolygonShape3D.set_backface_collision_enabled
     */
    fun setBackfaceCollisionEnabled(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setBackfaceCollisionEnabledBind, segment, enabled)
        ObjectCalls.keepReachable(this)
    }

    /**
     * If set to `true`, collisions occur on both sides of the concave shape faces. Otherwise they
     * occur only along the face normals.
     *
     * Generated from Godot docs: ConcavePolygonShape3D.is_backface_collision_enabled
     */
    fun isBackfaceCollisionEnabled(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.isBackfaceCollisionEnabledBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ConcavePolygonShape3D? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): ConcavePolygonShape3D? =
            if (handle.address() == 0L) null else RefCounted.owned(ConcavePolygonShape3D(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): ConcavePolygonShape3D? =
            if (handle.address() == 0L) null else ConcavePolygonShape3D(GodotHandle(handle))

        /** A new ConcavePolygonShape3D, GDScript's `ConcavePolygonShape3D.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): ConcavePolygonShape3D =
            RefCounted.owned(ConcavePolygonShape3D(GodotHandle(ObjectCalls.constructObject("ConcavePolygonShape3D"))))
    }

    private object Binds {
        private const val SET_FACES_HASH = 334873810L
        @JvmField
        val setFacesBind =
            ObjectCalls.getMethodBind("ConcavePolygonShape3D", "set_faces", SET_FACES_HASH)

        private const val GET_FACES_HASH = 497664490L
        @JvmField
        val getFacesBind =
            ObjectCalls.getMethodBind("ConcavePolygonShape3D", "get_faces", GET_FACES_HASH)

        private const val SET_BACKFACE_COLLISION_ENABLED_HASH = 2586408642L
        @JvmField
        val setBackfaceCollisionEnabledBind =
            ObjectCalls.getMethodBind("ConcavePolygonShape3D", "set_backface_collision_enabled", SET_BACKFACE_COLLISION_ENABLED_HASH)

        private const val IS_BACKFACE_COLLISION_ENABLED_HASH = 36873697L
        @JvmField
        val isBackfaceCollisionEnabledBind =
            ObjectCalls.getMethodBind("ConcavePolygonShape3D", "is_backface_collision_enabled", IS_BACKFACE_COLLISION_ENABLED_HASH)
    }
}
