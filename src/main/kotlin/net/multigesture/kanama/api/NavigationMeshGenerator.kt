package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Helper class for creating and clearing navigation meshes.
 *
 * Generated from Godot docs: NavigationMeshGenerator
 */
object NavigationMeshGenerator {
    private val singleton: MemorySegment by lazy {
        ObjectCalls.getSingleton("NavigationMeshGenerator")
    }

    @JvmStatic
    /**
     * Bakes the `navigation_mesh` with source geometry collected starting from the `root_node`.
     *
     * Generated from Godot docs: NavigationMeshGenerator.bake
     */
    fun bake(navigationMesh: NavigationMesh?, rootNode: Node) {
        ObjectCalls.ptrcallWithTwoObjectArgs(bakeBind, singleton, navigationMesh?.requireOpenHandle() ?: MemorySegment.NULL, rootNode.handle)
    }

    @JvmStatic
    /**
     * Removes all polygons and vertices from the provided `navigation_mesh` resource.
     *
     * Generated from Godot docs: NavigationMeshGenerator.clear
     */
    fun clear(navigationMesh: NavigationMesh?) {
        ObjectCalls.ptrcallWithObjectArgs(clearBind, singleton, listOf(navigationMesh?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Parses the `SceneTree` for source geometry according to the properties of `navigation_mesh`.
     * Updates the provided `source_geometry_data` resource with the resulting data. The resource can
     * then be used to bake a navigation mesh with `bake_from_source_geometry_data`. After the process
     * is finished the optional `callback` will be called. Note: This function needs to run on the main
     * thread or with a deferred call as the SceneTree is not thread-safe. Performance: While
     * convenient, reading data arrays from `Mesh` resources can affect the frame rate negatively. The
     * data needs to be received from the GPU, stalling the `RenderingServer` in the process. For
     * performance prefer the use of e.g. collision shapes or creating the data arrays entirely in
     * code.
     *
     * Generated from Godot docs: NavigationMeshGenerator.parse_source_geometry_data
     */
    @JvmStatic
    fun parseSourceGeometryData(navigationMesh: NavigationMesh?, sourceGeometryData: NavigationMeshSourceGeometryData3D?, rootNode: Node, callback: GodotCallable) {
        ObjectCalls.ptrcallWithThreeObjectCallableArgs(parseSourceGeometryDataBind, singleton, navigationMesh?.requireOpenHandle() ?: MemorySegment.NULL, sourceGeometryData?.requireOpenHandle() ?: MemorySegment.NULL, rootNode.handle, callback.target.handle, callback.method)
    }

    /**
     * Bakes the provided `navigation_mesh` with the data from the provided `source_geometry_data`.
     * After the process is finished the optional `callback` will be called.
     *
     * Generated from Godot docs: NavigationMeshGenerator.bake_from_source_geometry_data
     */
    @JvmStatic
    fun bakeFromSourceGeometryData(navigationMesh: NavigationMesh?, sourceGeometryData: NavigationMeshSourceGeometryData3D?, callback: GodotCallable) {
        ObjectCalls.ptrcallWithTwoObjectCallableArgs(bakeFromSourceGeometryDataBind, singleton, navigationMesh?.requireOpenHandle() ?: MemorySegment.NULL, sourceGeometryData?.requireOpenHandle() ?: MemorySegment.NULL, callback.target.handle, callback.method)
    }

    @JvmStatic
    fun fromHandle(handle: MemorySegment): NavigationMeshGenerator? =
        wrap(handle)

    internal fun wrap(handle: MemorySegment): NavigationMeshGenerator? =
        if (handle.address() == 0L) null else this

    private const val BAKE_HASH = 1401173477L
    private val bakeBind by lazy {
        ObjectCalls.getMethodBind("NavigationMeshGenerator", "bake", BAKE_HASH)
    }

    private const val CLEAR_HASH = 2923361153L
    private val clearBind by lazy {
        ObjectCalls.getMethodBind("NavigationMeshGenerator", "clear", CLEAR_HASH)
    }

    private const val PARSE_SOURCE_GEOMETRY_DATA_HASH = 3172802542L
    private val parseSourceGeometryDataBind by lazy {
        ObjectCalls.getMethodBind("NavigationMeshGenerator", "parse_source_geometry_data", PARSE_SOURCE_GEOMETRY_DATA_HASH)
    }

    private const val BAKE_FROM_SOURCE_GEOMETRY_DATA_HASH = 1286748856L
    private val bakeFromSourceGeometryDataBind by lazy {
        ObjectCalls.getMethodBind("NavigationMeshGenerator", "bake_from_source_geometry_data", BAKE_FROM_SOURCE_GEOMETRY_DATA_HASH)
    }
}
