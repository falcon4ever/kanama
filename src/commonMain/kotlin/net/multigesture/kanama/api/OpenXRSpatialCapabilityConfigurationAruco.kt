package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationAruco
 */
class OpenXRSpatialCapabilityConfigurationAruco(handle: GodotHandle) : OpenXRSpatialCapabilityConfigurationBaseHeader(handle) {
    var arucoDict: OpenXRSpatialCapabilityConfigurationAruco.ArucoDict
        @JvmName("arucoDictProperty")
        get() = getArucoDict()
        @JvmName("setArucoDictProperty")
        set(value) = setArucoDict(value)

    fun getEnabledComponents(): List<Long> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedInt64List(getEnabledComponentsBind, segment)
    }

    fun setArucoDict(arucoDict: OpenXRSpatialCapabilityConfigurationAruco.ArucoDict) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setArucoDictBind, segment, arucoDict.value)
    }

    fun getArucoDict(): OpenXRSpatialCapabilityConfigurationAruco.ArucoDict {
        checkOpen()
        return OpenXRSpatialCapabilityConfigurationAruco.ArucoDict(ObjectCalls.ptrcallNoArgsRetLong(getArucoDictBind, segment))
    }

    @JvmInline
    value class ArucoDict(override val value: Long) : GodotEnumValue {
        companion object {
            val DICT_4X4_50: ArucoDict get() = ArucoDict(1L)
            val DICT_4X4_100: ArucoDict get() = ArucoDict(2L)
            val DICT_4X4_250: ArucoDict get() = ArucoDict(3L)
            val DICT_4X4_1000: ArucoDict get() = ArucoDict(4L)
            val DICT_5X5_50: ArucoDict get() = ArucoDict(5L)
            val DICT_5X5_100: ArucoDict get() = ArucoDict(6L)
            val DICT_5X5_250: ArucoDict get() = ArucoDict(7L)
            val DICT_5X5_1000: ArucoDict get() = ArucoDict(8L)
            val DICT_6X6_50: ArucoDict get() = ArucoDict(9L)
            val DICT_6X6_100: ArucoDict get() = ArucoDict(10L)
            val DICT_6X6_250: ArucoDict get() = ArucoDict(11L)
            val DICT_6X6_1000: ArucoDict get() = ArucoDict(12L)
            val DICT_7X7_50: ArucoDict get() = ArucoDict(13L)
            val DICT_7X7_100: ArucoDict get() = ArucoDict(14L)
            val DICT_7X7_250: ArucoDict get() = ArucoDict(15L)
            val DICT_7X7_1000: ArucoDict get() = ArucoDict(16L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRSpatialCapabilityConfigurationAruco? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): OpenXRSpatialCapabilityConfigurationAruco? =
            if (handle.address() == 0L) null else RefCounted.owned(OpenXRSpatialCapabilityConfigurationAruco(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): OpenXRSpatialCapabilityConfigurationAruco? =
            if (handle.address() == 0L) null else OpenXRSpatialCapabilityConfigurationAruco(GodotHandle(handle))

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
