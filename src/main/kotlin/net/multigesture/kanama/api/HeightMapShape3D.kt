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

    fun setMapWidth(width: Int) {
        ObjectCalls.ptrcallWithIntArg(setMapWidthBind, handle, width)
    }

    fun getMapWidth(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMapWidthBind, handle)
    }

    fun setMapDepth(height: Int) {
        ObjectCalls.ptrcallWithIntArg(setMapDepthBind, handle, height)
    }

    fun getMapDepth(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMapDepthBind, handle)
    }

    fun setMapData(data: List<Float>) {
        ObjectCalls.ptrcallWithPackedFloat32ListArg(setMapDataBind, handle, data)
    }

    fun getMapData(): List<Float> {
        return ObjectCalls.ptrcallNoArgsRetPackedFloat32List(getMapDataBind, handle)
    }

    fun getMinHeight(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMinHeightBind, handle)
    }

    fun getMaxHeight(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMaxHeightBind, handle)
    }

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
