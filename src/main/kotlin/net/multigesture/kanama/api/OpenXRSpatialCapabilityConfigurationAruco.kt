package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationAruco
 */
class OpenXRSpatialCapabilityConfigurationAruco(handle: MemorySegment) : OpenXRSpatialCapabilityConfigurationBaseHeader(handle) {
    var arucoDict: Long
        @JvmName("arucoDictProperty")
        get() = getArucoDict()
        @JvmName("setArucoDictProperty")
        set(value) = setArucoDict(value)

    fun getEnabledComponents(): List<Long> {
        return ObjectCalls.ptrcallNoArgsRetPackedInt64List(getEnabledComponentsBind, handle)
    }

    fun setArucoDict(arucoDict: Long) {
        ObjectCalls.ptrcallWithLongArg(setArucoDictBind, handle, arucoDict)
    }

    fun getArucoDict(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getArucoDictBind, handle)
    }

    companion object {
        const val ARUCO_DICT_4X4_50: Long = 1L
        const val ARUCO_DICT_4X4_100: Long = 2L
        const val ARUCO_DICT_4X4_250: Long = 3L
        const val ARUCO_DICT_4X4_1000: Long = 4L
        const val ARUCO_DICT_5X5_50: Long = 5L
        const val ARUCO_DICT_5X5_100: Long = 6L
        const val ARUCO_DICT_5X5_250: Long = 7L
        const val ARUCO_DICT_5X5_1000: Long = 8L
        const val ARUCO_DICT_6X6_50: Long = 9L
        const val ARUCO_DICT_6X6_100: Long = 10L
        const val ARUCO_DICT_6X6_250: Long = 11L
        const val ARUCO_DICT_6X6_1000: Long = 12L
        const val ARUCO_DICT_7X7_50: Long = 13L
        const val ARUCO_DICT_7X7_100: Long = 14L
        const val ARUCO_DICT_7X7_250: Long = 15L
        const val ARUCO_DICT_7X7_1000: Long = 16L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): OpenXRSpatialCapabilityConfigurationAruco? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRSpatialCapabilityConfigurationAruco? =
            if (handle.address() == 0L) null else OpenXRSpatialCapabilityConfigurationAruco(handle)

        private const val GET_ENABLED_COMPONENTS_HASH = 235988956L
        private val getEnabledComponentsBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialCapabilityConfigurationAruco", "get_enabled_components", GET_ENABLED_COMPONENTS_HASH)
        }

        private const val SET_ARUCO_DICT_HASH = 2268055963L
        private val setArucoDictBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialCapabilityConfigurationAruco", "set_aruco_dict", SET_ARUCO_DICT_HASH)
        }

        private const val GET_ARUCO_DICT_HASH = 1080386209L
        private val getArucoDictBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialCapabilityConfigurationAruco", "get_aruco_dict", GET_ARUCO_DICT_HASH)
        }
    }
}
