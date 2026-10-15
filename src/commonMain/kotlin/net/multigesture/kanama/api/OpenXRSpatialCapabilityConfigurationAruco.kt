package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Configuration header for Aruco markers.
 *
 * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationAruco
 */
class OpenXRSpatialCapabilityConfigurationAruco(handle: GodotHandle) : OpenXRSpatialCapabilityConfigurationBaseHeader(handle) {
    var arucoDict: OpenXRSpatialCapabilityConfigurationAruco.ArucoDict
        @JvmName("arucoDictProperty")
        get() = getArucoDict()
        @JvmName("setArucoDictProperty")
        set(value) = setArucoDict(value)

    /**
     * Returns the components enabled by this configuration. Note: Only valid after this configuration
     * was used to create a spatial context.
     *
     * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationAruco.get_enabled_components
     */
    fun getEnabledComponents(): List<Long> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetPackedInt64List(Binds.getEnabledComponentsBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Dictionary to use to decode Aruco markers. Note: Must be set before using this configuration to
     * create a spatial context.
     *
     * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationAruco.set_aruco_dict
     */
    fun setArucoDict(arucoDict: OpenXRSpatialCapabilityConfigurationAruco.ArucoDict) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setArucoDictBind, segment, arucoDict.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Dictionary to use to decode Aruco markers. Note: Must be set before using this configuration to
     * create a spatial context.
     *
     * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationAruco.get_aruco_dict
     */
    fun getArucoDict(): OpenXRSpatialCapabilityConfigurationAruco.ArucoDict {
        checkOpen()
        val ret = OpenXRSpatialCapabilityConfigurationAruco.ArucoDict(ObjectCalls.ptrcallNoArgsRetLong(Binds.getArucoDictBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Godot's `OpenXRSpatialCapabilityConfigurationAruco.ArucoDict` enum as a typed value: `.value` is
     * the raw number Godot uses, and the companion holds the named values
     * (`OpenXRSpatialCapabilityConfigurationAruco.ArucoDict.<NAME>`).
     *
     * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationAruco.ArucoDict
     */
    @JvmInline
    value class ArucoDict(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * 4 by 4 pixel Aruco marker dictionary with 50 IDs.
             *
             * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationAruco.ARUCO_DICT_4X4_50
             */
            val DICT_4X4_50: ArucoDict get() = ArucoDict(1L)
            /**
             * 4 by 4 pixel Aruco marker dictionary with 100 IDs.
             *
             * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationAruco.ARUCO_DICT_4X4_100
             */
            val DICT_4X4_100: ArucoDict get() = ArucoDict(2L)
            /**
             * 4 by 4 pixel Aruco marker dictionary with 250 IDs.
             *
             * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationAruco.ARUCO_DICT_4X4_250
             */
            val DICT_4X4_250: ArucoDict get() = ArucoDict(3L)
            /**
             * 4 by 4 pixel Aruco marker dictionary with 1000 IDs.
             *
             * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationAruco.ARUCO_DICT_4X4_1000
             */
            val DICT_4X4_1000: ArucoDict get() = ArucoDict(4L)
            /**
             * 5 by 5 pixel Aruco marker dictionary with 50 IDs.
             *
             * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationAruco.ARUCO_DICT_5X5_50
             */
            val DICT_5X5_50: ArucoDict get() = ArucoDict(5L)
            /**
             * 5 by 5 pixel Aruco marker dictionary with 100 IDs.
             *
             * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationAruco.ARUCO_DICT_5X5_100
             */
            val DICT_5X5_100: ArucoDict get() = ArucoDict(6L)
            /**
             * 5 by 5 pixel Aruco marker dictionary with 250 IDs.
             *
             * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationAruco.ARUCO_DICT_5X5_250
             */
            val DICT_5X5_250: ArucoDict get() = ArucoDict(7L)
            /**
             * 5 by 5 pixel Aruco marker dictionary with 1000 IDs.
             *
             * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationAruco.ARUCO_DICT_5X5_1000
             */
            val DICT_5X5_1000: ArucoDict get() = ArucoDict(8L)
            /**
             * 6 by 6 pixel Aruco marker dictionary with 50 IDs.
             *
             * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationAruco.ARUCO_DICT_6X6_50
             */
            val DICT_6X6_50: ArucoDict get() = ArucoDict(9L)
            /**
             * 6 by 6 pixel Aruco marker dictionary with 100 IDs.
             *
             * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationAruco.ARUCO_DICT_6X6_100
             */
            val DICT_6X6_100: ArucoDict get() = ArucoDict(10L)
            /**
             * 6 by 6 pixel Aruco marker dictionary with 250 IDs.
             *
             * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationAruco.ARUCO_DICT_6X6_250
             */
            val DICT_6X6_250: ArucoDict get() = ArucoDict(11L)
            /**
             * 6 by 6 pixel Aruco marker dictionary with 1000 IDs.
             *
             * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationAruco.ARUCO_DICT_6X6_1000
             */
            val DICT_6X6_1000: ArucoDict get() = ArucoDict(12L)
            /**
             * 7 by 7 pixel Aruco marker dictionary with 50 IDs.
             *
             * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationAruco.ARUCO_DICT_7X7_50
             */
            val DICT_7X7_50: ArucoDict get() = ArucoDict(13L)
            /**
             * 7 by 7 pixel Aruco marker dictionary with 100 IDs.
             *
             * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationAruco.ARUCO_DICT_7X7_100
             */
            val DICT_7X7_100: ArucoDict get() = ArucoDict(14L)
            /**
             * 7 by 7 pixel Aruco marker dictionary with 250 IDs.
             *
             * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationAruco.ARUCO_DICT_7X7_250
             */
            val DICT_7X7_250: ArucoDict get() = ArucoDict(15L)
            /**
             * 7 by 7 pixel Aruco marker dictionary with 1000 IDs.
             *
             * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationAruco.ARUCO_DICT_7X7_1000
             */
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
    }

    private object Binds {
        private const val GET_ENABLED_COMPONENTS_HASH = 235988956L
        @JvmField
        val getEnabledComponentsBind =
            ObjectCalls.getMethodBind("OpenXRSpatialCapabilityConfigurationAruco", "get_enabled_components", GET_ENABLED_COMPONENTS_HASH)

        private const val SET_ARUCO_DICT_HASH = 2268055963L
        @JvmField
        val setArucoDictBind =
            ObjectCalls.getMethodBind("OpenXRSpatialCapabilityConfigurationAruco", "set_aruco_dict", SET_ARUCO_DICT_HASH)

        private const val GET_ARUCO_DICT_HASH = 1080386209L
        @JvmField
        val getArucoDictBind =
            ObjectCalls.getMethodBind("OpenXRSpatialCapabilityConfigurationAruco", "get_aruco_dict", GET_ARUCO_DICT_HASH)
    }
}
