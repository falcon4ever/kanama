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

    @JvmStatic
    fun parseSourceGeometryData(navigationMesh: NavigationMesh?, sourceGeometryData: NavigationMeshSourceGeometryData3D?, rootNode: Node, callback: GodotCallable) {
        ObjectCalls.ptrcallWithThreeObjectCallableArgs(parseSourceGeometryDataBind, singleton, navigationMesh?.requireOpenHandle() ?: MemorySegment.NULL, sourceGeometryData?.requireOpenHandle() ?: MemorySegment.NULL, rootNode.handle, callback.target.handle, callback.method)
    }

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
