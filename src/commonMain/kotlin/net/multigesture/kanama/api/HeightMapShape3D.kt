package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A 3D heightmap shape used for physics collision.
 *
 * Generated from Godot docs: HeightMapShape3D
 */
class HeightMapShape3D(handle: GodotHandle) : Shape3D(handle) {
    var mapWidth: Int
        @JvmName("mapWidthProperty")
        get() = getMapWidth()
        @JvmName("setMapWidthProperty")
        set(value) = setMapWidth(value)

    var mapDepth: Int
        @JvmName("mapDepthProperty")
        get() = getMapDepth()
        @JvmName("setMapDepthProperty")
        set(value) = setMapDepth(value)

    var mapData: List<Float>
        @JvmName("mapDataProperty")
        get() = getMapData()
        @JvmName("setMapDataProperty")
        set(value) = setMapData(value)

    /**
     * Number of vertices in the width of the heightmap. Changing this will resize the `map_data`.
     *
     * Generated from Godot docs: HeightMapShape3D.set_map_width
     */
    fun setMapWidth(width: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setMapWidthBind, segment, width)
    }

    /**
     * Number of vertices in the width of the heightmap. Changing this will resize the `map_data`.
     *
     * Generated from Godot docs: HeightMapShape3D.get_map_width
     */
    fun getMapWidth(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getMapWidthBind, segment)
    }

    /**
     * Number of vertices in the depth of the heightmap. Changing this will resize the `map_data`.
     *
     * Generated from Godot docs: HeightMapShape3D.set_map_depth
     */
    fun setMapDepth(height: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setMapDepthBind, segment, height)
    }

    /**
     * Number of vertices in the depth of the heightmap. Changing this will resize the `map_data`.
     *
     * Generated from Godot docs: HeightMapShape3D.get_map_depth
     */
    fun getMapDepth(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getMapDepthBind, segment)
    }

    /**
     * Heightmap data. The array's size must be equal to `map_width` multiplied by `map_depth`.
     *
     * Generated from Godot docs: HeightMapShape3D.set_map_data
     */
    fun setMapData(data: List<Float>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedFloat32ListArg(Binds.setMapDataBind, segment, data)
    }

    /**
     * Heightmap data. The array's size must be equal to `map_width` multiplied by `map_depth`.
     *
     * Generated from Godot docs: HeightMapShape3D.get_map_data
     */
    fun getMapData(): List<Float> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedFloat32List(Binds.getMapDataBind, segment)
    }

    /**
     * Returns the smallest height value found in `map_data`. Recalculates only when `map_data`
     * changes.
     *
     * Generated from Godot docs: HeightMapShape3D.get_min_height
     */
    fun getMinHeight(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getMinHeightBind, segment)
    }

    /**
     * Returns the largest height value found in `map_data`. Recalculates only when `map_data` changes.
     *
     * Generated from Godot docs: HeightMapShape3D.get_max_height
     */
    fun getMaxHeight(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getMaxHeightBind, segment)
    }

    /**
     * Updates `map_data` with data read from an `Image` reference. Automatically resizes heightmap
     * `map_width` and `map_depth` to fit the full image width and height. The image needs to be in
     * either `Image.Format.RF` (32 bit), `Image.Format.RH` (16 bit), or `Image.Format.R8` (8 bit).
     * Each image pixel is read in as a float on the range from `0.0` (black pixel) to `1.0` (white
     * pixel). This range value gets remapped to `height_min` and `height_max` to form the final height
     * value. Note: Using a heightmap with 16-bit or 32-bit data, stored in EXR or HDR format is
     * recommended. Using 8-bit height data, or a format like PNG that Godot imports as 8-bit, will
     * result in a terraced terrain.
     *
     * Generated from Godot docs: HeightMapShape3D.update_map_data_from_image
     */
    fun updateMapDataFromImage(image: Image?, heightMin: Double, heightMax: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectTwoDoubleArgs(Binds.updateMapDataFromImageBind, segment, image?.requireOpenHandle() ?: NULL_SEGMENT, heightMin, heightMax)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): HeightMapShape3D? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): HeightMapShape3D? =
            if (handle.address() == 0L) null else RefCounted.owned(HeightMapShape3D(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): HeightMapShape3D? =
            if (handle.address() == 0L) null else HeightMapShape3D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_MAP_WIDTH_HASH = 1286410249L
        @JvmField
        val setMapWidthBind =
            ObjectCalls.getMethodBind("HeightMapShape3D", "set_map_width", SET_MAP_WIDTH_HASH)

        private const val GET_MAP_WIDTH_HASH = 3905245786L
        @JvmField
        val getMapWidthBind =
            ObjectCalls.getMethodBind("HeightMapShape3D", "get_map_width", GET_MAP_WIDTH_HASH)

        private const val SET_MAP_DEPTH_HASH = 1286410249L
        @JvmField
        val setMapDepthBind =
            ObjectCalls.getMethodBind("HeightMapShape3D", "set_map_depth", SET_MAP_DEPTH_HASH)

        private const val GET_MAP_DEPTH_HASH = 3905245786L
        @JvmField
        val getMapDepthBind =
            ObjectCalls.getMethodBind("HeightMapShape3D", "get_map_depth", GET_MAP_DEPTH_HASH)

        private const val SET_MAP_DATA_HASH = 2899603908L
        @JvmField
        val setMapDataBind =
            ObjectCalls.getMethodBind("HeightMapShape3D", "set_map_data", SET_MAP_DATA_HASH)

        private const val GET_MAP_DATA_HASH = 675695659L
        @JvmField
        val getMapDataBind =
            ObjectCalls.getMethodBind("HeightMapShape3D", "get_map_data", GET_MAP_DATA_HASH)

        private const val GET_MIN_HEIGHT_HASH = 1740695150L
        @JvmField
        val getMinHeightBind =
            ObjectCalls.getMethodBind("HeightMapShape3D", "get_min_height", GET_MIN_HEIGHT_HASH)

        private const val GET_MAX_HEIGHT_HASH = 1740695150L
        @JvmField
        val getMaxHeightBind =
            ObjectCalls.getMethodBind("HeightMapShape3D", "get_max_height", GET_MAX_HEIGHT_HASH)

        private const val UPDATE_MAP_DATA_FROM_IMAGE_HASH = 2636652979L
        @JvmField
        val updateMapDataFromImageBind =
            ObjectCalls.getMethodBind("HeightMapShape3D", "update_map_data_from_image", UPDATE_MAP_DATA_FROM_IMAGE_HASH)
    }
}
