package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.Vector2

/**
 * A 2D polygon.
 *
 * Generated from Godot docs: Polygon2D
 */
class Polygon2D(handle: GodotHandle) : Node2D(handle) {
    var color: Color
        @JvmName("colorProperty")
        get() = getColor()
        @JvmName("setColorProperty")
        set(value) = setColor(value)

    var offset: Vector2
        @JvmName("offsetProperty")
        get() = getOffset()
        @JvmName("setOffsetProperty")
        set(value) = setOffset(value)

    var antialiased: Boolean
        @JvmName("antialiasedProperty")
        get() = getAntialiased()
        @JvmName("setAntialiasedProperty")
        set(value) = setAntialiased(value)

    var texture: Texture2D?
        @JvmName("textureProperty")
        get() = getTexture()
        @JvmName("setTextureProperty")
        set(value) = setTexture(value)

    var textureOffset: Vector2
        @JvmName("textureOffsetProperty")
        get() = getTextureOffset()
        @JvmName("setTextureOffsetProperty")
        set(value) = setTextureOffset(value)

    var textureScale: Vector2
        @JvmName("textureScaleProperty")
        get() = getTextureScale()
        @JvmName("setTextureScaleProperty")
        set(value) = setTextureScale(value)

    var textureRotation: Double
        @JvmName("textureRotationProperty")
        get() = getTextureRotation()
        @JvmName("setTextureRotationProperty")
        set(value) = setTextureRotation(value)

    var skeleton: NodePath
        @JvmName("skeletonProperty")
        get() = getSkeleton()
        @JvmName("setSkeletonProperty")
        set(value) = setSkeleton(value)

    var invertEnabled: Boolean
        @JvmName("invertEnabledProperty")
        get() = getInvertEnabled()
        @JvmName("setInvertEnabledProperty")
        set(value) = setInvertEnabled(value)

    var invertBorder: Double
        @JvmName("invertBorderProperty")
        get() = getInvertBorder()
        @JvmName("setInvertBorderProperty")
        set(value) = setInvertBorder(value)

    var polygon: List<Vector2>
        @JvmName("polygonProperty")
        get() = getPolygon()
        @JvmName("setPolygonProperty")
        set(value) = setPolygon(value)

    var uv: List<Vector2>
        @JvmName("uvProperty")
        get() = getUv()
        @JvmName("setUvProperty")
        set(value) = setUv(value)

    var vertexColors: List<Color>
        @JvmName("vertexColorsProperty")
        get() = getVertexColors()
        @JvmName("setVertexColorsProperty")
        set(value) = setVertexColors(value)

    var polygons: List<Any?>
        @JvmName("polygonsProperty")
        get() = getPolygons()
        @JvmName("setPolygonsProperty")
        set(value) = setPolygons(value)

    var internalVertexCount: Int
        @JvmName("internalVertexCountProperty")
        get() = getInternalVertexCount()
        @JvmName("setInternalVertexCountProperty")
        set(value) = setInternalVertexCount(value)

    /**
     * The polygon's list of vertices. The final point will be connected to the first.
     *
     * Generated from Godot docs: Polygon2D.set_polygon
     */
    fun setPolygon(polygon: List<Vector2>) {
        ObjectCalls.ptrcallWithPackedVector2ListArg(setPolygonBind, segment, polygon)
    }

    /**
     * The polygon's list of vertices. The final point will be connected to the first.
     *
     * Generated from Godot docs: Polygon2D.get_polygon
     */
    fun getPolygon(): List<Vector2> {
        return ObjectCalls.ptrcallNoArgsRetPackedVector2List(getPolygonBind, segment)
    }

    /**
     * Texture coordinates for each vertex of the polygon. There should be one UV value per polygon
     * vertex. If there are fewer, undefined vertices will use `Vector2(0, 0)`.
     *
     * Generated from Godot docs: Polygon2D.set_uv
     */
    fun setUv(uv: List<Vector2>) {
        ObjectCalls.ptrcallWithPackedVector2ListArg(setUvBind, segment, uv)
    }

    /**
     * Texture coordinates for each vertex of the polygon. There should be one UV value per polygon
     * vertex. If there are fewer, undefined vertices will use `Vector2(0, 0)`.
     *
     * Generated from Godot docs: Polygon2D.get_uv
     */
    fun getUv(): List<Vector2> {
        return ObjectCalls.ptrcallNoArgsRetPackedVector2List(getUvBind, segment)
    }

    /**
     * The polygon's fill color. If `texture` is set, it will be multiplied by this color. It will also
     * be the default color for vertices not set in `vertex_colors`.
     *
     * Generated from Godot docs: Polygon2D.set_color
     */
    fun setColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setColorBind, segment, color)
    }

    /**
     * The polygon's fill color. If `texture` is set, it will be multiplied by this color. It will also
     * be the default color for vertices not set in `vertex_colors`.
     *
     * Generated from Godot docs: Polygon2D.get_color
     */
    fun getColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getColorBind, segment)
    }

    /**
     * The list of polygons, in case more than one is being represented. Every individual polygon is
     * stored as a `PackedInt32Array` where each `int` is an index to a point in `polygon`. If empty,
     * this property will be ignored, and the resulting single polygon will be composed of all points
     * in `polygon`, using the order they are stored in.
     *
     * Generated from Godot docs: Polygon2D.set_polygons
     */
    fun setPolygons(polygons: List<Any?>) {
        ObjectCalls.ptrcallWithArrayArg(setPolygonsBind, segment, polygons)
    }

    /**
     * The list of polygons, in case more than one is being represented. Every individual polygon is
     * stored as a `PackedInt32Array` where each `int` is an index to a point in `polygon`. If empty,
     * this property will be ignored, and the resulting single polygon will be composed of all points
     * in `polygon`, using the order they are stored in.
     *
     * Generated from Godot docs: Polygon2D.get_polygons
     */
    fun getPolygons(): List<Any?> {
        return ObjectCalls.ptrcallNoArgsRetArray(getPolygonsBind, segment)
    }

    /**
     * Color for each vertex. Colors are interpolated between vertices, resulting in smooth gradients.
     * There should be one per polygon vertex. If there are fewer, undefined vertices will use `color`.
     *
     * Generated from Godot docs: Polygon2D.set_vertex_colors
     */
    fun setVertexColors(vertexColors: List<Color>) {
        ObjectCalls.ptrcallWithPackedColorListArg(setVertexColorsBind, segment, vertexColors)
    }

    /**
     * Color for each vertex. Colors are interpolated between vertices, resulting in smooth gradients.
     * There should be one per polygon vertex. If there are fewer, undefined vertices will use `color`.
     *
     * Generated from Godot docs: Polygon2D.get_vertex_colors
     */
    fun getVertexColors(): List<Color> {
        return ObjectCalls.ptrcallNoArgsRetPackedColorList(getVertexColorsBind, segment)
    }

    /**
     * The polygon's fill texture. Use `uv` to set texture coordinates.
     *
     * Generated from Godot docs: Polygon2D.set_texture
     */
    fun setTexture(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setTextureBind, segment, listOf(texture?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The polygon's fill texture. Use `uv` to set texture coordinates.
     *
     * Generated from Godot docs: Polygon2D.get_texture
     */
    fun getTexture(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getTextureBind, segment))
    }

    /**
     * Amount to offset the polygon's `texture`. If set to `Vector2(0, 0)`, the texture's origin (its
     * top-left corner) will be placed at the polygon's position.
     *
     * Generated from Godot docs: Polygon2D.set_texture_offset
     */
    fun setTextureOffset(textureOffset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setTextureOffsetBind, segment, textureOffset)
    }

    /**
     * Amount to offset the polygon's `texture`. If set to `Vector2(0, 0)`, the texture's origin (its
     * top-left corner) will be placed at the polygon's position.
     *
     * Generated from Godot docs: Polygon2D.get_texture_offset
     */
    fun getTextureOffset(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getTextureOffsetBind, segment)
    }

    /**
     * The texture's rotation in radians.
     *
     * Generated from Godot docs: Polygon2D.set_texture_rotation
     */
    fun setTextureRotation(textureRotation: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTextureRotationBind, segment, textureRotation)
    }

    /**
     * The texture's rotation in radians.
     *
     * Generated from Godot docs: Polygon2D.get_texture_rotation
     */
    fun getTextureRotation(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTextureRotationBind, segment)
    }

    /**
     * Amount to multiply the `uv` coordinates when using `texture`. Larger values make the texture
     * smaller, and vice versa.
     *
     * Generated from Godot docs: Polygon2D.set_texture_scale
     */
    fun setTextureScale(textureScale: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setTextureScaleBind, segment, textureScale)
    }

    /**
     * Amount to multiply the `uv` coordinates when using `texture`. Larger values make the texture
     * smaller, and vice versa.
     *
     * Generated from Godot docs: Polygon2D.get_texture_scale
     */
    fun getTextureScale(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getTextureScaleBind, segment)
    }

    /**
     * If `true`, the polygon will be inverted, containing the area outside the defined points and
     * extending to the `invert_border`.
     *
     * Generated from Godot docs: Polygon2D.set_invert_enabled
     */
    fun setInvertEnabled(invert: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setInvertEnabledBind, segment, invert)
    }

    /**
     * If `true`, the polygon will be inverted, containing the area outside the defined points and
     * extending to the `invert_border`.
     *
     * Generated from Godot docs: Polygon2D.get_invert_enabled
     */
    fun getInvertEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getInvertEnabledBind, segment)
    }

    /**
     * If `true`, polygon edges will be anti-aliased.
     *
     * Generated from Godot docs: Polygon2D.set_antialiased
     */
    fun setAntialiased(antialiased: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAntialiasedBind, segment, antialiased)
    }

    /**
     * If `true`, polygon edges will be anti-aliased.
     *
     * Generated from Godot docs: Polygon2D.get_antialiased
     */
    fun getAntialiased(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getAntialiasedBind, segment)
    }

    /**
     * Added padding applied to the bounding box when `invert_enabled` is set to `true`. Setting this
     * value too small may result in a "Bad Polygon" error.
     *
     * Generated from Godot docs: Polygon2D.set_invert_border
     */
    fun setInvertBorder(invertBorder: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setInvertBorderBind, segment, invertBorder)
    }

    /**
     * Added padding applied to the bounding box when `invert_enabled` is set to `true`. Setting this
     * value too small may result in a "Bad Polygon" error.
     *
     * Generated from Godot docs: Polygon2D.get_invert_border
     */
    fun getInvertBorder(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getInvertBorderBind, segment)
    }

    /**
     * The offset applied to each vertex.
     *
     * Generated from Godot docs: Polygon2D.set_offset
     */
    fun setOffset(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setOffsetBind, segment, offset)
    }

    /**
     * The offset applied to each vertex.
     *
     * Generated from Godot docs: Polygon2D.get_offset
     */
    fun getOffset(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getOffsetBind, segment)
    }

    /**
     * Adds a bone with the specified `path` and `weights`.
     *
     * Generated from Godot docs: Polygon2D.add_bone
     */
    fun addBone(path: NodePath, weights: List<Float>) {
        ObjectCalls.ptrcallWithNodePathAndPackedFloat32ListArgs(addBoneBind, segment, path, weights)
    }

    /**
     * Returns the number of bones in this `Polygon2D`.
     *
     * Generated from Godot docs: Polygon2D.get_bone_count
     */
    fun getBoneCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getBoneCountBind, segment)
    }

    /**
     * Returns the path to the node associated with the specified bone.
     *
     * Generated from Godot docs: Polygon2D.get_bone_path
     */
    fun getBonePath(index: Int): NodePath {
        return ObjectCalls.ptrcallWithIntArgRetNodePath(getBonePathBind, segment, index)
    }

    /**
     * Returns the weight values of the specified bone.
     *
     * Generated from Godot docs: Polygon2D.get_bone_weights
     */
    fun getBoneWeights(index: Int): List<Float> {
        return ObjectCalls.ptrcallWithIntArgRetPackedFloat32List(getBoneWeightsBind, segment, index)
    }

    /**
     * Removes the specified bone from this `Polygon2D`.
     *
     * Generated from Godot docs: Polygon2D.erase_bone
     */
    fun eraseBone(index: Int) {
        ObjectCalls.ptrcallWithIntArg(eraseBoneBind, segment, index)
    }

    /**
     * Removes all bones from this `Polygon2D`.
     *
     * Generated from Godot docs: Polygon2D.clear_bones
     */
    fun clearBones() {
        ObjectCalls.ptrcallNoArgs(clearBonesBind, segment)
    }

    /**
     * Sets the path to the node associated with the specified bone.
     *
     * Generated from Godot docs: Polygon2D.set_bone_path
     */
    fun setBonePath(index: Int, path: NodePath) {
        ObjectCalls.ptrcallWithIntAndNodePathArg(setBonePathBind, segment, index, path)
    }

    /**
     * Sets the weight values for the specified bone.
     *
     * Generated from Godot docs: Polygon2D.set_bone_weights
     */
    fun setBoneWeights(index: Int, weights: List<Float>) {
        ObjectCalls.ptrcallWithIntAndPackedFloat32ListArgs(setBoneWeightsBind, segment, index, weights)
    }

    /**
     * Path to a `Skeleton2D` node used for skeleton-based deformations of this polygon. If empty or
     * invalid, skeletal deformations will not be used.
     *
     * Generated from Godot docs: Polygon2D.set_skeleton
     */
    fun setSkeleton(skeleton: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setSkeletonBind, segment, skeleton)
    }

    /**
     * Path to a `Skeleton2D` node used for skeleton-based deformations of this polygon. If empty or
     * invalid, skeletal deformations will not be used.
     *
     * Generated from Godot docs: Polygon2D.get_skeleton
     */
    fun getSkeleton(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getSkeletonBind, segment)
    }

    /**
     * Number of internal vertices, used for UV mapping.
     *
     * Generated from Godot docs: Polygon2D.set_internal_vertex_count
     */
    fun setInternalVertexCount(internalVertexCount: Int) {
        ObjectCalls.ptrcallWithIntArg(setInternalVertexCountBind, segment, internalVertexCount)
    }

    /**
     * Number of internal vertices, used for UV mapping.
     *
     * Generated from Godot docs: Polygon2D.get_internal_vertex_count
     */
    fun getInternalVertexCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getInternalVertexCountBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Polygon2D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): Polygon2D? =
            if (handle.address() == 0L) null else Polygon2D(GodotHandle(handle))

        private const val SET_POLYGON_HASH = 1509147220L
        private val setPolygonBind by lazy {
            ObjectCalls.getMethodBind("Polygon2D", "set_polygon", SET_POLYGON_HASH)
        }

        private const val GET_POLYGON_HASH = 2961356807L
        private val getPolygonBind by lazy {
            ObjectCalls.getMethodBind("Polygon2D", "get_polygon", GET_POLYGON_HASH)
        }

        private const val SET_UV_HASH = 1509147220L
        private val setUvBind by lazy {
            ObjectCalls.getMethodBind("Polygon2D", "set_uv", SET_UV_HASH)
        }

        private const val GET_UV_HASH = 2961356807L
        private val getUvBind by lazy {
            ObjectCalls.getMethodBind("Polygon2D", "get_uv", GET_UV_HASH)
        }

        private const val SET_COLOR_HASH = 2920490490L
        private val setColorBind by lazy {
            ObjectCalls.getMethodBind("Polygon2D", "set_color", SET_COLOR_HASH)
        }

        private const val GET_COLOR_HASH = 3444240500L
        private val getColorBind by lazy {
            ObjectCalls.getMethodBind("Polygon2D", "get_color", GET_COLOR_HASH)
        }

        private const val SET_POLYGONS_HASH = 381264803L
        private val setPolygonsBind by lazy {
            ObjectCalls.getMethodBind("Polygon2D", "set_polygons", SET_POLYGONS_HASH)
        }

        private const val GET_POLYGONS_HASH = 3995934104L
        private val getPolygonsBind by lazy {
            ObjectCalls.getMethodBind("Polygon2D", "get_polygons", GET_POLYGONS_HASH)
        }

        private const val SET_VERTEX_COLORS_HASH = 3546319833L
        private val setVertexColorsBind by lazy {
            ObjectCalls.getMethodBind("Polygon2D", "set_vertex_colors", SET_VERTEX_COLORS_HASH)
        }

        private const val GET_VERTEX_COLORS_HASH = 1392750486L
        private val getVertexColorsBind by lazy {
            ObjectCalls.getMethodBind("Polygon2D", "get_vertex_colors", GET_VERTEX_COLORS_HASH)
        }

        private const val SET_TEXTURE_HASH = 4051416890L
        private val setTextureBind by lazy {
            ObjectCalls.getMethodBind("Polygon2D", "set_texture", SET_TEXTURE_HASH)
        }

        private const val GET_TEXTURE_HASH = 3635182373L
        private val getTextureBind by lazy {
            ObjectCalls.getMethodBind("Polygon2D", "get_texture", GET_TEXTURE_HASH)
        }

        private const val SET_TEXTURE_OFFSET_HASH = 743155724L
        private val setTextureOffsetBind by lazy {
            ObjectCalls.getMethodBind("Polygon2D", "set_texture_offset", SET_TEXTURE_OFFSET_HASH)
        }

        private const val GET_TEXTURE_OFFSET_HASH = 3341600327L
        private val getTextureOffsetBind by lazy {
            ObjectCalls.getMethodBind("Polygon2D", "get_texture_offset", GET_TEXTURE_OFFSET_HASH)
        }

        private const val SET_TEXTURE_ROTATION_HASH = 373806689L
        private val setTextureRotationBind by lazy {
            ObjectCalls.getMethodBind("Polygon2D", "set_texture_rotation", SET_TEXTURE_ROTATION_HASH)
        }

        private const val GET_TEXTURE_ROTATION_HASH = 1740695150L
        private val getTextureRotationBind by lazy {
            ObjectCalls.getMethodBind("Polygon2D", "get_texture_rotation", GET_TEXTURE_ROTATION_HASH)
        }

        private const val SET_TEXTURE_SCALE_HASH = 743155724L
        private val setTextureScaleBind by lazy {
            ObjectCalls.getMethodBind("Polygon2D", "set_texture_scale", SET_TEXTURE_SCALE_HASH)
        }

        private const val GET_TEXTURE_SCALE_HASH = 3341600327L
        private val getTextureScaleBind by lazy {
            ObjectCalls.getMethodBind("Polygon2D", "get_texture_scale", GET_TEXTURE_SCALE_HASH)
        }

        private const val SET_INVERT_ENABLED_HASH = 2586408642L
        private val setInvertEnabledBind by lazy {
            ObjectCalls.getMethodBind("Polygon2D", "set_invert_enabled", SET_INVERT_ENABLED_HASH)
        }

        private const val GET_INVERT_ENABLED_HASH = 36873697L
        private val getInvertEnabledBind by lazy {
            ObjectCalls.getMethodBind("Polygon2D", "get_invert_enabled", GET_INVERT_ENABLED_HASH)
        }

        private const val SET_ANTIALIASED_HASH = 2586408642L
        private val setAntialiasedBind by lazy {
            ObjectCalls.getMethodBind("Polygon2D", "set_antialiased", SET_ANTIALIASED_HASH)
        }

        private const val GET_ANTIALIASED_HASH = 36873697L
        private val getAntialiasedBind by lazy {
            ObjectCalls.getMethodBind("Polygon2D", "get_antialiased", GET_ANTIALIASED_HASH)
        }

        private const val SET_INVERT_BORDER_HASH = 373806689L
        private val setInvertBorderBind by lazy {
            ObjectCalls.getMethodBind("Polygon2D", "set_invert_border", SET_INVERT_BORDER_HASH)
        }

        private const val GET_INVERT_BORDER_HASH = 1740695150L
        private val getInvertBorderBind by lazy {
            ObjectCalls.getMethodBind("Polygon2D", "get_invert_border", GET_INVERT_BORDER_HASH)
        }

        private const val SET_OFFSET_HASH = 743155724L
        private val setOffsetBind by lazy {
            ObjectCalls.getMethodBind("Polygon2D", "set_offset", SET_OFFSET_HASH)
        }

        private const val GET_OFFSET_HASH = 3341600327L
        private val getOffsetBind by lazy {
            ObjectCalls.getMethodBind("Polygon2D", "get_offset", GET_OFFSET_HASH)
        }

        private const val ADD_BONE_HASH = 703042815L
        private val addBoneBind by lazy {
            ObjectCalls.getMethodBind("Polygon2D", "add_bone", ADD_BONE_HASH)
        }

        private const val GET_BONE_COUNT_HASH = 3905245786L
        private val getBoneCountBind by lazy {
            ObjectCalls.getMethodBind("Polygon2D", "get_bone_count", GET_BONE_COUNT_HASH)
        }

        private const val GET_BONE_PATH_HASH = 408788394L
        private val getBonePathBind by lazy {
            ObjectCalls.getMethodBind("Polygon2D", "get_bone_path", GET_BONE_PATH_HASH)
        }

        private const val GET_BONE_WEIGHTS_HASH = 1542882410L
        private val getBoneWeightsBind by lazy {
            ObjectCalls.getMethodBind("Polygon2D", "get_bone_weights", GET_BONE_WEIGHTS_HASH)
        }

        private const val ERASE_BONE_HASH = 1286410249L
        private val eraseBoneBind by lazy {
            ObjectCalls.getMethodBind("Polygon2D", "erase_bone", ERASE_BONE_HASH)
        }

        private const val CLEAR_BONES_HASH = 3218959716L
        private val clearBonesBind by lazy {
            ObjectCalls.getMethodBind("Polygon2D", "clear_bones", CLEAR_BONES_HASH)
        }

        private const val SET_BONE_PATH_HASH = 2761262315L
        private val setBonePathBind by lazy {
            ObjectCalls.getMethodBind("Polygon2D", "set_bone_path", SET_BONE_PATH_HASH)
        }

        private const val SET_BONE_WEIGHTS_HASH = 1345852415L
        private val setBoneWeightsBind by lazy {
            ObjectCalls.getMethodBind("Polygon2D", "set_bone_weights", SET_BONE_WEIGHTS_HASH)
        }

        private const val SET_SKELETON_HASH = 1348162250L
        private val setSkeletonBind by lazy {
            ObjectCalls.getMethodBind("Polygon2D", "set_skeleton", SET_SKELETON_HASH)
        }

        private const val GET_SKELETON_HASH = 4075236667L
        private val getSkeletonBind by lazy {
            ObjectCalls.getMethodBind("Polygon2D", "get_skeleton", GET_SKELETON_HASH)
        }

        private const val SET_INTERNAL_VERTEX_COUNT_HASH = 1286410249L
        private val setInternalVertexCountBind by lazy {
            ObjectCalls.getMethodBind("Polygon2D", "set_internal_vertex_count", SET_INTERNAL_VERTEX_COUNT_HASH)
        }

        private const val GET_INTERNAL_VERTEX_COUNT_HASH = 3905245786L
        private val getInternalVertexCountBind by lazy {
            ObjectCalls.getMethodBind("Polygon2D", "get_internal_vertex_count", GET_INTERNAL_VERTEX_COUNT_HASH)
        }
    }
}
