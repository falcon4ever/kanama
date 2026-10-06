package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Helper class for creating and clearing navigation meshes.
 *
 * Generated from Godot docs: NavigationMeshGenerator
 */
object NavigationMeshGenerator {
    private inline val singleton: RawSegment
        get() = Binds.singleton

    /**
     * Bakes the `navigation_mesh` with source geometry collected starting from the `root_node`.
     *
     * Generated from Godot docs: NavigationMeshGenerator.bake
     */
    @JvmStatic
    fun bake(navigationMesh: NavigationMesh?, rootNode: Node) {
        ObjectCalls.ptrcallWithTwoObjectArgs(Binds.bakeBind, singleton, navigationMesh?.requireOpenHandle() ?: NULL_SEGMENT, rootNode.segment)
    }

    /**
     * Removes all polygons and vertices from the provided `navigation_mesh` resource.
     *
     * Generated from Godot docs: NavigationMeshGenerator.clear
     */
    @JvmStatic
    fun clear(navigationMesh: NavigationMesh?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.clearBind, singleton, listOf(navigationMesh?.requireOpenHandle() ?: NULL_SEGMENT))
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
        ObjectCalls.ptrcallWithThreeObjectCallableArgs(Binds.parseSourceGeometryDataBind, singleton, navigationMesh?.requireOpenHandle() ?: NULL_SEGMENT, sourceGeometryData?.requireOpenHandle() ?: NULL_SEGMENT, rootNode.segment, callback.target.segment, callback.method)
    }

    /**
     * Bakes the provided `navigation_mesh` with the data from the provided `source_geometry_data`.
     * After the process is finished the optional `callback` will be called.
     *
     * Generated from Godot docs: NavigationMeshGenerator.bake_from_source_geometry_data
     */
    @JvmStatic
    fun bakeFromSourceGeometryData(navigationMesh: NavigationMesh?, sourceGeometryData: NavigationMeshSourceGeometryData3D?, callback: GodotCallable) {
        ObjectCalls.ptrcallWithTwoObjectCallableArgs(Binds.bakeFromSourceGeometryDataBind, singleton, navigationMesh?.requireOpenHandle() ?: NULL_SEGMENT, sourceGeometryData?.requireOpenHandle() ?: NULL_SEGMENT, callback.target.segment, callback.method)
    }

    @JvmStatic
    fun fromHandle(handle: GodotHandle): NavigationMeshGenerator? =
        wrap(handle.segment)

    internal fun wrap(handle: RawSegment): NavigationMeshGenerator? =
        if (handle.address() == 0L) null else this

    private object Binds {
        @JvmField
        val singleton = ObjectCalls.getSingleton("NavigationMeshGenerator")

        private const val BAKE_HASH = 1401173477L
        @JvmField
        val bakeBind =
            ObjectCalls.getMethodBind("NavigationMeshGenerator", "bake", BAKE_HASH)

        private const val CLEAR_HASH = 2923361153L
        @JvmField
        val clearBind =
            ObjectCalls.getMethodBind("NavigationMeshGenerator", "clear", CLEAR_HASH)

        private const val PARSE_SOURCE_GEOMETRY_DATA_HASH = 3172802542L
        @JvmField
        val parseSourceGeometryDataBind =
            ObjectCalls.getMethodBind("NavigationMeshGenerator", "parse_source_geometry_data", PARSE_SOURCE_GEOMETRY_DATA_HASH)

        private const val BAKE_FROM_SOURCE_GEOMETRY_DATA_HASH = 1286748856L
        @JvmField
        val bakeFromSourceGeometryDataBind =
            ObjectCalls.getMethodBind("NavigationMeshGenerator", "bake_from_source_geometry_data", BAKE_FROM_SOURCE_GEOMETRY_DATA_HASH)
    }
}
