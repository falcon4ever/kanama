package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Plane
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector3

/**
 * Mesh optimized for creating geometry manually.
 *
 * Generated from Godot docs: ImmediateMesh
 */
class ImmediateMesh(handle: MemorySegment) : Mesh(handle) {
    /**
     * Begin a new surface.
     *
     * Generated from Godot docs: ImmediateMesh.surface_begin
     */
    fun surfaceBegin(primitive: Long, material: Material?) {
        ObjectCalls.ptrcallWithLongAndObjectArg(surfaceBeginBind, handle, primitive, material?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Set the color attribute that will be pushed with the next vertex.
     *
     * Generated from Godot docs: ImmediateMesh.surface_set_color
     */
    fun surfaceSetColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(surfaceSetColorBind, handle, color)
    }

    /**
     * Set the normal attribute that will be pushed with the next vertex.
     *
     * Generated from Godot docs: ImmediateMesh.surface_set_normal
     */
    fun surfaceSetNormal(normal: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(surfaceSetNormalBind, handle, normal)
    }

    /**
     * Set the tangent attribute that will be pushed with the next vertex. Note: Even though `tangent`
     * is a `Plane`, it does not directly represent the tangent plane. Its `Plane.x`, `Plane.y`, and
     * `Plane.z` represent the tangent vector and `Plane.d` should be either `-1` or `1`. See also
     * `Mesh.ARRAY_TANGENT`.
     *
     * Generated from Godot docs: ImmediateMesh.surface_set_tangent
     */
    fun surfaceSetTangent(tangent: Plane) {
        ObjectCalls.ptrcallWithPlaneArg(surfaceSetTangentBind, handle, tangent)
    }

    /**
     * Set the UV attribute that will be pushed with the next vertex.
     *
     * Generated from Godot docs: ImmediateMesh.surface_set_uv
     */
    fun surfaceSetUv(uv: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(surfaceSetUvBind, handle, uv)
    }

    /**
     * Set the UV2 attribute that will be pushed with the next vertex.
     *
     * Generated from Godot docs: ImmediateMesh.surface_set_uv2
     */
    fun surfaceSetUv2(uv2: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(surfaceSetUv2Bind, handle, uv2)
    }

    /**
     * Add a 3D vertex using the current attributes previously set.
     *
     * Generated from Godot docs: ImmediateMesh.surface_add_vertex
     */
    fun surfaceAddVertex(vertex: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(surfaceAddVertexBind, handle, vertex)
    }

    /**
     * Add a 2D vertex using the current attributes previously set.
     *
     * Generated from Godot docs: ImmediateMesh.surface_add_vertex_2d
     */
    fun surfaceAddVertex2d(vertex: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(surfaceAddVertex2dBind, handle, vertex)
    }

    /**
     * End and commit current surface. Note that surface being created will not be visible until this
     * function is called.
     *
     * Generated from Godot docs: ImmediateMesh.surface_end
     */
    fun surfaceEnd() {
        ObjectCalls.ptrcallNoArgs(surfaceEndBind, handle)
    }

    /**
     * Clear all surfaces.
     *
     * Generated from Godot docs: ImmediateMesh.clear_surfaces
     */
    fun clearSurfaces() {
        ObjectCalls.ptrcallNoArgs(clearSurfacesBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): ImmediateMesh? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ImmediateMesh? =
            if (handle.address() == 0L) null else ImmediateMesh(handle)

        private const val SURFACE_BEGIN_HASH = 2794442543L
        private val surfaceBeginBind by lazy {
            ObjectCalls.getMethodBind("ImmediateMesh", "surface_begin", SURFACE_BEGIN_HASH)
        }

        private const val SURFACE_SET_COLOR_HASH = 2920490490L
        private val surfaceSetColorBind by lazy {
            ObjectCalls.getMethodBind("ImmediateMesh", "surface_set_color", SURFACE_SET_COLOR_HASH)
        }

        private const val SURFACE_SET_NORMAL_HASH = 3460891852L
        private val surfaceSetNormalBind by lazy {
            ObjectCalls.getMethodBind("ImmediateMesh", "surface_set_normal", SURFACE_SET_NORMAL_HASH)
        }

        private const val SURFACE_SET_TANGENT_HASH = 3505987427L
        private val surfaceSetTangentBind by lazy {
            ObjectCalls.getMethodBind("ImmediateMesh", "surface_set_tangent", SURFACE_SET_TANGENT_HASH)
        }

        private const val SURFACE_SET_UV_HASH = 743155724L
        private val surfaceSetUvBind by lazy {
            ObjectCalls.getMethodBind("ImmediateMesh", "surface_set_uv", SURFACE_SET_UV_HASH)
        }

        private const val SURFACE_SET_UV2_HASH = 743155724L
        private val surfaceSetUv2Bind by lazy {
            ObjectCalls.getMethodBind("ImmediateMesh", "surface_set_uv2", SURFACE_SET_UV2_HASH)
        }

        private const val SURFACE_ADD_VERTEX_HASH = 3460891852L
        private val surfaceAddVertexBind by lazy {
            ObjectCalls.getMethodBind("ImmediateMesh", "surface_add_vertex", SURFACE_ADD_VERTEX_HASH)
        }

        private const val SURFACE_ADD_VERTEX_2D_HASH = 743155724L
        private val surfaceAddVertex2dBind by lazy {
            ObjectCalls.getMethodBind("ImmediateMesh", "surface_add_vertex_2d", SURFACE_ADD_VERTEX_2D_HASH)
        }

        private const val SURFACE_END_HASH = 3218959716L
        private val surfaceEndBind by lazy {
            ObjectCalls.getMethodBind("ImmediateMesh", "surface_end", SURFACE_END_HASH)
        }

        private const val CLEAR_SURFACES_HASH = 3218959716L
        private val clearSurfacesBind by lazy {
            ObjectCalls.getMethodBind("ImmediateMesh", "clear_surfaces", CLEAR_SURFACES_HASH)
        }
    }
}
