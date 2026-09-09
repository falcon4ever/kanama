package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Vector2i

/**
 * A `Resource` that contains vertex array-based geometry during the import process.
 *
 * Generated from Godot docs: ImporterMesh
 */
class ImporterMesh(handle: MemorySegment) : Resource(handle) {
    /**
     * Adds name for a blend shape that will be added with `add_surface`. Must be called before surface
     * is added.
     *
     * Generated from Godot docs: ImporterMesh.add_blend_shape
     */
    fun addBlendShape(name: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(addBlendShapeBind, handle, name)
    }

    /**
     * Returns the number of blend shapes that the mesh holds.
     *
     * Generated from Godot docs: ImporterMesh.get_blend_shape_count
     */
    fun getBlendShapeCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getBlendShapeCountBind, handle)
    }

    /**
     * Sets the blend shape mode.
     *
     * Generated from Godot docs: ImporterMesh.set_blend_shape_mode
     */
    fun setBlendShapeMode(mode: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setBlendShapeModeBind, handle, mode)
    }

    /**
     * Returns the blend shape mode for this Mesh.
     *
     * Generated from Godot docs: ImporterMesh.get_blend_shape_mode
     */
    fun getBlendShapeMode(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getBlendShapeModeBind, handle)
    }

    /**
     * Returns the number of surfaces that the mesh holds.
     *
     * Generated from Godot docs: ImporterMesh.get_surface_count
     */
    fun getSurfaceCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getSurfaceCountBind, handle)
    }

    /**
     * Returns the primitive type of the requested surface (see `add_surface`).
     *
     * Generated from Godot docs: ImporterMesh.get_surface_primitive_type
     */
    fun getSurfacePrimitiveType(surfaceIdx: Int): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetLong(getSurfacePrimitiveTypeBind, handle, surfaceIdx)
    }

    /**
     * Returns the number of lods that the mesh holds on a given surface.
     *
     * Generated from Godot docs: ImporterMesh.get_surface_lod_count
     */
    fun getSurfaceLodCount(surfaceIdx: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetInt(getSurfaceLodCountBind, handle, surfaceIdx)
    }

    /**
     * Returns the screen ratio which activates a lod for a surface.
     *
     * Generated from Godot docs: ImporterMesh.get_surface_lod_size
     */
    fun getSurfaceLodSize(surfaceIdx: Int, lodIdx: Int): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntArgsRetDouble(getSurfaceLodSizeBind, handle, surfaceIdx, lodIdx)
    }

    /**
     * Returns a `Material` in a given surface. Surface is rendered using this material.
     *
     * Generated from Godot docs: ImporterMesh.get_surface_material
     */
    fun getSurfaceMaterial(surfaceIdx: Int): Material? {
        checkOpen()
        return Material.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getSurfaceMaterialBind, handle, surfaceIdx))
    }

    /**
     * Returns the format of the surface that the mesh holds.
     *
     * Generated from Godot docs: ImporterMesh.get_surface_format
     */
    fun getSurfaceFormat(surfaceIdx: Int): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetLong(getSurfaceFormatBind, handle, surfaceIdx)
    }

    /**
     * Sets a name for a given surface.
     *
     * Generated from Godot docs: ImporterMesh.set_surface_name
     */
    fun setSurfaceName(surfaceIdx: Int, name: String) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndStringArg(setSurfaceNameBind, handle, surfaceIdx, name)
    }

    /**
     * Sets a `Material` for a given surface. Surface will be rendered using this material.
     *
     * Generated from Godot docs: ImporterMesh.set_surface_material
     */
    fun setSurfaceMaterial(surfaceIdx: Int, material: Material?) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndObjectArg(setSurfaceMaterialBind, handle, surfaceIdx, material?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Returns the mesh data represented by this `ImporterMesh` as a usable `ArrayMesh`. This method
     * caches the returned mesh, and subsequent calls will return the cached data until `clear` is
     * called. If not yet cached and `base_mesh` is provided, `base_mesh` will be used and mutated.
     *
     * Generated from Godot docs: ImporterMesh.get_mesh
     */
    fun getMesh(baseMesh: ArrayMesh?): ArrayMesh? {
        checkOpen()
        return ArrayMesh.wrap(ObjectCalls.ptrcallWithObjectArgRetObject(getMeshBind, handle, baseMesh?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Removes all surfaces and blend shapes from this `ImporterMesh`.
     *
     * Generated from Godot docs: ImporterMesh.clear
     */
    fun clear() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(clearBind, handle)
    }

    /**
     * Sets the size hint of this mesh for lightmap-unwrapping in UV-space.
     *
     * Generated from Godot docs: ImporterMesh.set_lightmap_size_hint
     */
    fun setLightmapSizeHint(size: Vector2i) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2iArg(setLightmapSizeHintBind, handle, size)
    }

    /**
     * Returns the size hint of this mesh for lightmap-unwrapping in UV-space.
     *
     * Generated from Godot docs: ImporterMesh.get_lightmap_size_hint
     */
    fun getLightmapSizeHint(): Vector2i {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2i(getLightmapSizeHintBind, handle)
    }

    companion object {
        /**
         * Converts the given `Mesh` into an `ImporterMesh` by copying all its surfaces, blend shapes,
         * materials, and metadata into a new `ImporterMesh` object.
         *
         * Generated from Godot docs: ImporterMesh.from_mesh
         */
        fun fromMesh(mesh: Mesh?): ImporterMesh? {
            return ImporterMesh.wrap(ObjectCalls.ptrcallWithObjectArgRetObject(fromMeshBind, MemorySegment.NULL, mesh?.requireOpenHandle() ?: MemorySegment.NULL))
        }

        @JvmStatic
        fun fromHandle(handle: MemorySegment): ImporterMesh? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ImporterMesh? =
            if (handle.address() == 0L) null else ImporterMesh(handle)

        private const val ADD_BLEND_SHAPE_HASH = 83702148L
        private val addBlendShapeBind by lazy {
            ObjectCalls.getMethodBind("ImporterMesh", "add_blend_shape", ADD_BLEND_SHAPE_HASH)
        }

        private const val GET_BLEND_SHAPE_COUNT_HASH = 3905245786L
        private val getBlendShapeCountBind by lazy {
            ObjectCalls.getMethodBind("ImporterMesh", "get_blend_shape_count", GET_BLEND_SHAPE_COUNT_HASH)
        }

        private const val SET_BLEND_SHAPE_MODE_HASH = 227983991L
        private val setBlendShapeModeBind by lazy {
            ObjectCalls.getMethodBind("ImporterMesh", "set_blend_shape_mode", SET_BLEND_SHAPE_MODE_HASH)
        }

        private const val GET_BLEND_SHAPE_MODE_HASH = 836485024L
        private val getBlendShapeModeBind by lazy {
            ObjectCalls.getMethodBind("ImporterMesh", "get_blend_shape_mode", GET_BLEND_SHAPE_MODE_HASH)
        }

        private const val GET_SURFACE_COUNT_HASH = 3905245786L
        private val getSurfaceCountBind by lazy {
            ObjectCalls.getMethodBind("ImporterMesh", "get_surface_count", GET_SURFACE_COUNT_HASH)
        }

        private const val GET_SURFACE_PRIMITIVE_TYPE_HASH = 3552571330L
        private val getSurfacePrimitiveTypeBind by lazy {
            ObjectCalls.getMethodBind("ImporterMesh", "get_surface_primitive_type", GET_SURFACE_PRIMITIVE_TYPE_HASH)
        }

        private const val GET_SURFACE_LOD_COUNT_HASH = 923996154L
        private val getSurfaceLodCountBind by lazy {
            ObjectCalls.getMethodBind("ImporterMesh", "get_surface_lod_count", GET_SURFACE_LOD_COUNT_HASH)
        }

        private const val GET_SURFACE_LOD_SIZE_HASH = 3085491603L
        private val getSurfaceLodSizeBind by lazy {
            ObjectCalls.getMethodBind("ImporterMesh", "get_surface_lod_size", GET_SURFACE_LOD_SIZE_HASH)
        }

        private const val GET_SURFACE_MATERIAL_HASH = 2897466400L
        private val getSurfaceMaterialBind by lazy {
            ObjectCalls.getMethodBind("ImporterMesh", "get_surface_material", GET_SURFACE_MATERIAL_HASH)
        }

        private const val GET_SURFACE_FORMAT_HASH = 923996154L
        private val getSurfaceFormatBind by lazy {
            ObjectCalls.getMethodBind("ImporterMesh", "get_surface_format", GET_SURFACE_FORMAT_HASH)
        }

        private const val SET_SURFACE_NAME_HASH = 501894301L
        private val setSurfaceNameBind by lazy {
            ObjectCalls.getMethodBind("ImporterMesh", "set_surface_name", SET_SURFACE_NAME_HASH)
        }

        private const val SET_SURFACE_MATERIAL_HASH = 3671737478L
        private val setSurfaceMaterialBind by lazy {
            ObjectCalls.getMethodBind("ImporterMesh", "set_surface_material", SET_SURFACE_MATERIAL_HASH)
        }

        private const val GET_MESH_HASH = 1457573577L
        private val getMeshBind by lazy {
            ObjectCalls.getMethodBind("ImporterMesh", "get_mesh", GET_MESH_HASH)
        }

        private const val FROM_MESH_HASH = 283226343L
        private val fromMeshBind by lazy {
            ObjectCalls.getMethodBind("ImporterMesh", "from_mesh", FROM_MESH_HASH)
        }

        private const val CLEAR_HASH = 3218959716L
        private val clearBind by lazy {
            ObjectCalls.getMethodBind("ImporterMesh", "clear", CLEAR_HASH)
        }

        private const val SET_LIGHTMAP_SIZE_HINT_HASH = 1130785943L
        private val setLightmapSizeHintBind by lazy {
            ObjectCalls.getMethodBind("ImporterMesh", "set_lightmap_size_hint", SET_LIGHTMAP_SIZE_HINT_HASH)
        }

        private const val GET_LIGHTMAP_SIZE_HINT_HASH = 3690982128L
        private val getLightmapSizeHintBind by lazy {
            ObjectCalls.getMethodBind("ImporterMesh", "get_lightmap_size_hint", GET_LIGHTMAP_SIZE_HINT_HASH)
        }
    }
}
