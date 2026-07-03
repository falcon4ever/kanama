package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A 3D heightmap shape used for physics collision.
 *
 * Generated from Godot docs: HeightMapShape3D
 */
class HeightMapShape3D(handle: MemorySegment) : Shape3D(handle) {
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
        ObjectCalls.ptrcallWithIntArg(setMapWidthBind, handle, width)
    }

    /**
     * Number of vertices in the width of the heightmap. Changing this will resize the `map_data`.
     *
     * Generated from Godot docs: HeightMapShape3D.get_map_width
     */
    fun getMapWidth(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMapWidthBind, handle)
    }

    /**
     * Number of vertices in the depth of the heightmap. Changing this will resize the `map_data`.
     *
     * Generated from Godot docs: HeightMapShape3D.set_map_depth
     */
    fun setMapDepth(height: Int) {
        ObjectCalls.ptrcallWithIntArg(setMapDepthBind, handle, height)
    }

    /**
     * Number of vertices in the depth of the heightmap. Changing this will resize the `map_data`.
     *
     * Generated from Godot docs: HeightMapShape3D.get_map_depth
     */
    fun getMapDepth(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMapDepthBind, handle)
    }

    /**
     * Heightmap data. The array's size must be equal to `map_width` multiplied by `map_depth`.
     *
     * Generated from Godot docs: HeightMapShape3D.set_map_data
     */
    fun setMapData(data: List<Float>) {
        ObjectCalls.ptrcallWithPackedFloat32ListArg(setMapDataBind, handle, data)
    }

    /**
     * Heightmap data. The array's size must be equal to `map_width` multiplied by `map_depth`.
     *
     * Generated from Godot docs: HeightMapShape3D.get_map_data
     */
    fun getMapData(): List<Float> {
        return ObjectCalls.ptrcallNoArgsRetPackedFloat32List(getMapDataBind, handle)
    }

    /**
     * Returns the smallest height value found in `map_data`. Recalculates only when `map_data`
     * changes.
     *
     * Generated from Godot docs: HeightMapShape3D.get_min_height
     */
    fun getMinHeight(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMinHeightBind, handle)
    }

    /**
     * Returns the largest height value found in `map_data`. Recalculates only when `map_data` changes.
     *
     * Generated from Godot docs: HeightMapShape3D.get_max_height
     */
    fun getMaxHeight(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMaxHeightBind, handle)
    }

    /**
     * Updates `map_data` with data read from an `Image` reference. Automatically resizes heightmap
     * `map_width` and `map_depth` to fit the full image width and height. The image needs to be in
     * either `Image.FORMAT_RF` (32 bit), `Image.FORMAT_RH` (16 bit), or `Image.FORMAT_R8` (8 bit).
     * Each image pixel is read in as a float on the range from `0.0` (black pixel) to `1.0` (white
     * pixel). This range value gets remapped to `height_min` and `height_max` to form the final height
     * value. Note: Using a heightmap with 16-bit or 32-bit data, stored in EXR or HDR format is
     * recommended. Using 8-bit height data, or a format like PNG that Godot imports as 8-bit, will
     * result in a terraced terrain.
     *
     * Generated from Godot docs: HeightMapShape3D.update_map_data_from_image
     */
    fun updateMapDataFromImage(image: Image?, heightMin: Double, heightMax: Double) {
        ObjectCalls.ptrcallWithObjectTwoDoubleArgs(updateMapDataFromImageBind, handle, image?.requireOpenHandle() ?: MemorySegment.NULL, heightMin, heightMax)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): HeightMapShape3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): HeightMapShape3D? =
            if (handle.address() == 0L) null else HeightMapShape3D(handle)

        private const val SET_MAP_WIDTH_HASH = 1286410249L
        private val setMapWidthBind by lazy {
            ObjectCalls.getMethodBind("HeightMapShape3D", "set_map_width", SET_MAP_WIDTH_HASH)
        }

        private const val GET_MAP_WIDTH_HASH = 3905245786L
        private val getMapWidthBind by lazy {
            ObjectCalls.getMethodBind("HeightMapShape3D", "get_map_width", GET_MAP_WIDTH_HASH)
        }

        private const val SET_MAP_DEPTH_HASH = 1286410249L
        private val setMapDepthBind by lazy {
            ObjectCalls.getMethodBind("HeightMapShape3D", "set_map_depth", SET_MAP_DEPTH_HASH)
        }

        private const val GET_MAP_DEPTH_HASH = 3905245786L
        private val getMapDepthBind by lazy {
            ObjectCalls.getMethodBind("HeightMapShape3D", "get_map_depth", GET_MAP_DEPTH_HASH)
        }

        private const val SET_MAP_DATA_HASH = 2899603908L
        private val setMapDataBind by lazy {
            ObjectCalls.getMethodBind("HeightMapShape3D", "set_map_data", SET_MAP_DATA_HASH)
        }

        private const val GET_MAP_DATA_HASH = 675695659L
        private val getMapDataBind by lazy {
            ObjectCalls.getMethodBind("HeightMapShape3D", "get_map_data", GET_MAP_DATA_HASH)
        }

        private const val GET_MIN_HEIGHT_HASH = 1740695150L
        private val getMinHeightBind by lazy {
            ObjectCalls.getMethodBind("HeightMapShape3D", "get_min_height", GET_MIN_HEIGHT_HASH)
        }

        private const val GET_MAX_HEIGHT_HASH = 1740695150L
        private val getMaxHeightBind by lazy {
            ObjectCalls.getMethodBind("HeightMapShape3D", "get_max_height", GET_MAX_HEIGHT_HASH)
        }

        private const val UPDATE_MAP_DATA_FROM_IMAGE_HASH = 2636652979L
        private val updateMapDataFromImageBind by lazy {
            ObjectCalls.getMethodBind("HeightMapShape3D", "update_map_data_from_image", UPDATE_MAP_DATA_FROM_IMAGE_HASH)
        }
    }
}
