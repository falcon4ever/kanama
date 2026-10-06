package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Plane
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector3

/**
 * Mesh optimized for creating geometry manually.
 *
 * Generated from Godot docs: ImmediateMesh
 */
class ImmediateMesh(handle: GodotHandle) : Mesh(handle) {
    /**
     * Begin a new surface.
     *
     * Generated from Godot docs: ImmediateMesh.surface_begin
     */
    fun surfaceBegin(primitive: Mesh.PrimitiveType, material: Material?) {
        checkOpen()
        ObjectCalls.ptrcallWithLongAndObjectArg(Binds.surfaceBeginBind, segment, primitive.value, material?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Set the color attribute that will be pushed with the next vertex.
     *
     * Generated from Godot docs: ImmediateMesh.surface_set_color
     */
    fun surfaceSetColor(color: Color) {
        checkOpen()
        ObjectCalls.ptrcallWithColorArg(Binds.surfaceSetColorBind, segment, color)
    }

    /**
     * Set the normal attribute that will be pushed with the next vertex.
     *
     * Generated from Godot docs: ImmediateMesh.surface_set_normal
     */
    fun surfaceSetNormal(normal: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(Binds.surfaceSetNormalBind, segment, normal)
    }

    /**
     * Set the tangent attribute that will be pushed with the next vertex. Note: Even though `tangent`
     * is a `Plane`, it does not directly represent the tangent plane. Its `Plane.x`, `Plane.y`, and
     * `Plane.z` represent the tangent vector and `Plane.d` should be either `-1` or `1`. See also
     * `Mesh.ArrayType.TANGENT`.
     *
     * Generated from Godot docs: ImmediateMesh.surface_set_tangent
     */
    fun surfaceSetTangent(tangent: Plane) {
        checkOpen()
        ObjectCalls.ptrcallWithPlaneArg(Binds.surfaceSetTangentBind, segment, tangent)
    }

    /**
     * Set the UV attribute that will be pushed with the next vertex.
     *
     * Generated from Godot docs: ImmediateMesh.surface_set_uv
     */
    fun surfaceSetUv(uv: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(Binds.surfaceSetUvBind, segment, uv)
    }

    /**
     * Set the UV2 attribute that will be pushed with the next vertex.
     *
     * Generated from Godot docs: ImmediateMesh.surface_set_uv2
     */
    fun surfaceSetUv2(uv2: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(Binds.surfaceSetUv2Bind, segment, uv2)
    }

    /**
     * Add a 3D vertex using the current attributes previously set.
     *
     * Generated from Godot docs: ImmediateMesh.surface_add_vertex
     */
    fun surfaceAddVertex(vertex: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(Binds.surfaceAddVertexBind, segment, vertex)
    }

    /**
     * Add a 2D vertex using the current attributes previously set.
     *
     * Generated from Godot docs: ImmediateMesh.surface_add_vertex_2d
     */
    fun surfaceAddVertex2d(vertex: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(Binds.surfaceAddVertex2dBind, segment, vertex)
    }

    /**
     * End and commit current surface. Note that surface being created will not be visible until this
     * function is called.
     *
     * Generated from Godot docs: ImmediateMesh.surface_end
     */
    fun surfaceEnd() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.surfaceEndBind, segment)
    }

    /**
     * Clear all surfaces.
     *
     * Generated from Godot docs: ImmediateMesh.clear_surfaces
     */
    fun clearSurfaces() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.clearSurfacesBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ImmediateMesh? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): ImmediateMesh? =
            if (handle.address() == 0L) null else RefCounted.owned(ImmediateMesh(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): ImmediateMesh? =
            if (handle.address() == 0L) null else ImmediateMesh(GodotHandle(handle))
    }

    private object Binds {
        private const val SURFACE_BEGIN_HASH = 2794442543L
        @JvmField
        val surfaceBeginBind =
            ObjectCalls.getMethodBind("ImmediateMesh", "surface_begin", SURFACE_BEGIN_HASH)

        private const val SURFACE_SET_COLOR_HASH = 2920490490L
        @JvmField
        val surfaceSetColorBind =
            ObjectCalls.getMethodBind("ImmediateMesh", "surface_set_color", SURFACE_SET_COLOR_HASH)

        private const val SURFACE_SET_NORMAL_HASH = 3460891852L
        @JvmField
        val surfaceSetNormalBind =
            ObjectCalls.getMethodBind("ImmediateMesh", "surface_set_normal", SURFACE_SET_NORMAL_HASH)

        private const val SURFACE_SET_TANGENT_HASH = 3505987427L
        @JvmField
        val surfaceSetTangentBind =
            ObjectCalls.getMethodBind("ImmediateMesh", "surface_set_tangent", SURFACE_SET_TANGENT_HASH)

        private const val SURFACE_SET_UV_HASH = 743155724L
        @JvmField
        val surfaceSetUvBind =
            ObjectCalls.getMethodBind("ImmediateMesh", "surface_set_uv", SURFACE_SET_UV_HASH)

        private const val SURFACE_SET_UV2_HASH = 743155724L
        @JvmField
        val surfaceSetUv2Bind =
            ObjectCalls.getMethodBind("ImmediateMesh", "surface_set_uv2", SURFACE_SET_UV2_HASH)

        private const val SURFACE_ADD_VERTEX_HASH = 3460891852L
        @JvmField
        val surfaceAddVertexBind =
            ObjectCalls.getMethodBind("ImmediateMesh", "surface_add_vertex", SURFACE_ADD_VERTEX_HASH)

        private const val SURFACE_ADD_VERTEX_2D_HASH = 743155724L
        @JvmField
        val surfaceAddVertex2dBind =
            ObjectCalls.getMethodBind("ImmediateMesh", "surface_add_vertex_2d", SURFACE_ADD_VERTEX_2D_HASH)

        private const val SURFACE_END_HASH = 3218959716L
        @JvmField
        val surfaceEndBind =
            ObjectCalls.getMethodBind("ImmediateMesh", "surface_end", SURFACE_END_HASH)

        private const val CLEAR_SURFACES_HASH = 3218959716L
        @JvmField
        val clearSurfacesBind =
            ObjectCalls.getMethodBind("ImmediateMesh", "clear_surfaces", CLEAR_SURFACES_HASH)
    }
}
