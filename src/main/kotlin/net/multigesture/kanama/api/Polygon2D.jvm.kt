package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.Vector2

// GENERATED desktop/Android companion for Polygon2D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP Polygon2D waits on: ptrcallWithArrayArg, ptrcallWithIntAndPackedFloat32ListArgs,
//   ptrcallWithIntArgRetNodePath, ptrcallWithIntArgRetPackedFloat32List,
//   ptrcallWithNodePathAndPackedFloat32ListArgs, ptrcallWithPackedColorListArg,
//   ptrcallWithPackedVector2ListArg
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * The polygon's list of vertices. The final point will be connected to the first.
 *
 * Generated from Godot docs: Polygon2D.set_polygon
 */
fun Polygon2D.setPolygon(polygon: List<Vector2>) {
    ObjectCalls.ptrcallWithPackedVector2ListArg(setPolygonBind, handle, polygon)
}

/**
 * Texture coordinates for each vertex of the polygon. There should be one UV value per polygon
 * vertex. If there are fewer, undefined vertices will use `Vector2(0, 0)`.
 *
 * Generated from Godot docs: Polygon2D.set_uv
 */
fun Polygon2D.setUv(uv: List<Vector2>) {
    ObjectCalls.ptrcallWithPackedVector2ListArg(setUvBind, handle, uv)
}

/**
 * The list of polygons, in case more than one is being represented. Every individual polygon is
 * stored as a `PackedInt32Array` where each `int` is an index to a point in `polygon`. If empty,
 * this property will be ignored, and the resulting single polygon will be composed of all points
 * in `polygon`, using the order they are stored in.
 *
 * Generated from Godot docs: Polygon2D.set_polygons
 */
fun Polygon2D.setPolygons(polygons: List<Any?>) {
    ObjectCalls.ptrcallWithArrayArg(setPolygonsBind, handle, polygons)
}

/**
 * Color for each vertex. Colors are interpolated between vertices, resulting in smooth gradients.
 * There should be one per polygon vertex. If there are fewer, undefined vertices will use `color`.
 *
 * Generated from Godot docs: Polygon2D.set_vertex_colors
 */
fun Polygon2D.setVertexColors(vertexColors: List<Color>) {
    ObjectCalls.ptrcallWithPackedColorListArg(setVertexColorsBind, handle, vertexColors)
}

/**
 * Adds a bone with the specified `path` and `weights`.
 *
 * Generated from Godot docs: Polygon2D.add_bone
 */
fun Polygon2D.addBone(path: NodePath, weights: List<Float>) {
    ObjectCalls.ptrcallWithNodePathAndPackedFloat32ListArgs(addBoneBind, handle, path, weights)
}

/**
 * Returns the path to the node associated with the specified bone.
 *
 * Generated from Godot docs: Polygon2D.get_bone_path
 */
fun Polygon2D.getBonePath(index: Int): NodePath {
    return ObjectCalls.ptrcallWithIntArgRetNodePath(getBonePathBind, handle, index)
}

/**
 * Returns the weight values of the specified bone.
 *
 * Generated from Godot docs: Polygon2D.get_bone_weights
 */
fun Polygon2D.getBoneWeights(index: Int): List<Float> {
    return ObjectCalls.ptrcallWithIntArgRetPackedFloat32List(getBoneWeightsBind, handle, index)
}

/**
 * Sets the weight values for the specified bone.
 *
 * Generated from Godot docs: Polygon2D.set_bone_weights
 */
fun Polygon2D.setBoneWeights(index: Int, weights: List<Float>) {
    ObjectCalls.ptrcallWithIntAndPackedFloat32ListArgs(setBoneWeightsBind, handle, index, weights)
}

private const val SET_POLYGON_HASH = 1509147220L
private val setPolygonBind by lazy {
    ObjectCalls.getMethodBind("Polygon2D", "set_polygon", SET_POLYGON_HASH)
}

private const val SET_UV_HASH = 1509147220L
private val setUvBind by lazy {
    ObjectCalls.getMethodBind("Polygon2D", "set_uv", SET_UV_HASH)
}

private const val SET_POLYGONS_HASH = 381264803L
private val setPolygonsBind by lazy {
    ObjectCalls.getMethodBind("Polygon2D", "set_polygons", SET_POLYGONS_HASH)
}

private const val SET_VERTEX_COLORS_HASH = 3546319833L
private val setVertexColorsBind by lazy {
    ObjectCalls.getMethodBind("Polygon2D", "set_vertex_colors", SET_VERTEX_COLORS_HASH)
}

private const val ADD_BONE_HASH = 703042815L
private val addBoneBind by lazy {
    ObjectCalls.getMethodBind("Polygon2D", "add_bone", ADD_BONE_HASH)
}

private const val GET_BONE_PATH_HASH = 408788394L
private val getBonePathBind by lazy {
    ObjectCalls.getMethodBind("Polygon2D", "get_bone_path", GET_BONE_PATH_HASH)
}

private const val GET_BONE_WEIGHTS_HASH = 1542882410L
private val getBoneWeightsBind by lazy {
    ObjectCalls.getMethodBind("Polygon2D", "get_bone_weights", GET_BONE_WEIGHTS_HASH)
}

private const val SET_BONE_WEIGHTS_HASH = 1345852415L
private val setBoneWeightsBind by lazy {
    ObjectCalls.getMethodBind("Polygon2D", "set_bone_weights", SET_BONE_WEIGHTS_HASH)
}
