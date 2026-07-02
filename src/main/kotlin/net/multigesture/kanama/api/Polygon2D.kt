package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.Vector2

/**
 * A 2D polygon.
 *
 * Generated from Godot docs: Polygon2D
 */
class Polygon2D(handle: MemorySegment) : Node2D(handle) {
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

    fun setPolygon(polygon: List<Vector2>) {
        ObjectCalls.ptrcallWithPackedVector2ListArg(setPolygonBind, handle, polygon)
    }

    fun getPolygon(): List<Vector2> {
        return ObjectCalls.ptrcallNoArgsRetPackedVector2List(getPolygonBind, handle)
    }

    fun setUv(uv: List<Vector2>) {
        ObjectCalls.ptrcallWithPackedVector2ListArg(setUvBind, handle, uv)
    }

    fun getUv(): List<Vector2> {
        return ObjectCalls.ptrcallNoArgsRetPackedVector2List(getUvBind, handle)
    }

    fun setColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setColorBind, handle, color)
    }

    fun getColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getColorBind, handle)
    }

    fun setPolygons(polygons: List<Any?>) {
        ObjectCalls.ptrcallWithArrayArg(setPolygonsBind, handle, polygons)
    }

    fun getPolygons(): List<Any?> {
        return ObjectCalls.ptrcallNoArgsRetArray(getPolygonsBind, handle)
    }

    fun setVertexColors(vertexColors: List<Color>) {
        ObjectCalls.ptrcallWithPackedColorListArg(setVertexColorsBind, handle, vertexColors)
    }

    fun getVertexColors(): List<Color> {
        return ObjectCalls.ptrcallNoArgsRetPackedColorList(getVertexColorsBind, handle)
    }

    fun setTexture(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setTextureBind, handle, listOf(texture?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getTexture(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getTextureBind, handle))
    }

    fun setTextureOffset(textureOffset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setTextureOffsetBind, handle, textureOffset)
    }

    fun getTextureOffset(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getTextureOffsetBind, handle)
    }

    fun setTextureRotation(textureRotation: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTextureRotationBind, handle, textureRotation)
    }

    fun getTextureRotation(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTextureRotationBind, handle)
    }

    fun setTextureScale(textureScale: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setTextureScaleBind, handle, textureScale)
    }

    fun getTextureScale(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getTextureScaleBind, handle)
    }

    fun setInvertEnabled(invert: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setInvertEnabledBind, handle, invert)
    }

    fun getInvertEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getInvertEnabledBind, handle)
    }

    fun setAntialiased(antialiased: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAntialiasedBind, handle, antialiased)
    }

    fun getAntialiased(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getAntialiasedBind, handle)
    }

    fun setInvertBorder(invertBorder: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setInvertBorderBind, handle, invertBorder)
    }

    fun getInvertBorder(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getInvertBorderBind, handle)
    }

    fun setOffset(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setOffsetBind, handle, offset)
    }

    fun getOffset(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getOffsetBind, handle)
    }

    fun addBone(path: NodePath, weights: List<Float>) {
        ObjectCalls.ptrcallWithNodePathAndPackedFloat32ListArgs(addBoneBind, handle, path, weights)
    }

    fun getBoneCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getBoneCountBind, handle)
    }

    fun getBonePath(index: Int): NodePath {
        return ObjectCalls.ptrcallWithIntArgRetNodePath(getBonePathBind, handle, index)
    }

    fun getBoneWeights(index: Int): List<Float> {
        return ObjectCalls.ptrcallWithIntArgRetPackedFloat32List(getBoneWeightsBind, handle, index)
    }

    fun eraseBone(index: Int) {
        ObjectCalls.ptrcallWithIntArg(eraseBoneBind, handle, index)
    }

    fun clearBones() {
        ObjectCalls.ptrcallNoArgs(clearBonesBind, handle)
    }

    fun setBonePath(index: Int, path: NodePath) {
        ObjectCalls.ptrcallWithIntAndNodePathArg(setBonePathBind, handle, index, path)
    }

    fun setBoneWeights(index: Int, weights: List<Float>) {
        ObjectCalls.ptrcallWithIntAndPackedFloat32ListArgs(setBoneWeightsBind, handle, index, weights)
    }

    fun setSkeleton(skeleton: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setSkeletonBind, handle, skeleton)
    }

    fun getSkeleton(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getSkeletonBind, handle)
    }

    fun setInternalVertexCount(internalVertexCount: Int) {
        ObjectCalls.ptrcallWithIntArg(setInternalVertexCountBind, handle, internalVertexCount)
    }

    fun getInternalVertexCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getInternalVertexCountBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): Polygon2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Polygon2D? =
            if (handle.address() == 0L) null else Polygon2D(handle)

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
