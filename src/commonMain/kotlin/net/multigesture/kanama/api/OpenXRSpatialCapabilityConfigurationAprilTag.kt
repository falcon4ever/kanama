package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * Configuration header for April tag markers.
 *
 * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationAprilTag
 */
class OpenXRSpatialCapabilityConfigurationAprilTag(handle: GodotHandle) : OpenXRSpatialCapabilityConfigurationBaseHeader(handle) {
    var aprilDict: OpenXRSpatialCapabilityConfigurationAprilTag.AprilTagDict
        @JvmName("aprilDictProperty")
        get() = getAprilDict()
        @JvmName("setAprilDictProperty")
        set(value) = setAprilDict(value)

    /**
     * Returns the components enabled by this configuration. Note: Only valid after this configuration
     * was used to create a spatial context.
     *
     * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationAprilTag.get_enabled_components
     */
    fun getEnabledComponents(): List<Long> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetPackedInt64List(Binds.getEnabledComponentsBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Dictionary to use to decode April tags. Note: Must be set before using this configuration to
     * create a spatial context.
     *
     * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationAprilTag.set_april_dict
     */
    fun setAprilDict(aprilDict: OpenXRSpatialCapabilityConfigurationAprilTag.AprilTagDict) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setAprilDictBind, segment, aprilDict.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Dictionary to use to decode April tags. Note: Must be set before using this configuration to
     * create a spatial context.
     *
     * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationAprilTag.get_april_dict
     */
    fun getAprilDict(): OpenXRSpatialCapabilityConfigurationAprilTag.AprilTagDict {
        checkOpen()
        val ret = OpenXRSpatialCapabilityConfigurationAprilTag.AprilTagDict(ObjectCalls.ptrcallNoArgsRetLong(Binds.getAprilDictBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Godot's `OpenXRSpatialCapabilityConfigurationAprilTag.AprilTagDict` enum as a typed value:
     * `.value` is the raw number Godot uses, and the companion holds the named values
     * (`OpenXRSpatialCapabilityConfigurationAprilTag.AprilTagDict.<NAME>`).
     *
     * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationAprilTag.AprilTagDict
     */
    @JvmInline
    value class AprilTagDict(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * 4 by 4 bits, minimum Hamming distance between any two codes = 5, 30 codes.
             *
             * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationAprilTag.APRIL_TAG_DICT_16H5
             */
            val DICT_16H5: AprilTagDict get() = AprilTagDict(1L)
            /**
             * 5 by 5 bits, minimum Hamming distance between any two codes = 9, 35 codes.
             *
             * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationAprilTag.APRIL_TAG_DICT_25H9
             */
            val DICT_25H9: AprilTagDict get() = AprilTagDict(2L)
            /**
             * 6 by 6 bits, minimum Hamming distance between any two codes = 10, 2320 codes.
             *
             * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationAprilTag.APRIL_TAG_DICT_36H10
             */
            val DICT_36H10: AprilTagDict get() = AprilTagDict(3L)
            /**
             * 6 by 6 bits, minimum Hamming distance between any two codes = 11, 587 codes.
             *
             * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationAprilTag.APRIL_TAG_DICT_36H11
             */
            val DICT_36H11: AprilTagDict get() = AprilTagDict(4L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRSpatialCapabilityConfigurationAprilTag? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): OpenXRSpatialCapabilityConfigurationAprilTag? =
            if (handle.address() == 0L) null else RefCounted.owned(OpenXRSpatialCapabilityConfigurationAprilTag(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): OpenXRSpatialCapabilityConfigurationAprilTag? =
            if (handle.address() == 0L) null else OpenXRSpatialCapabilityConfigurationAprilTag(GodotHandle(handle))

        /** A new OpenXRSpatialCapabilityConfigurationAprilTag, GDScript's `OpenXRSpatialCapabilityConfigurationAprilTag.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): OpenXRSpatialCapabilityConfigurationAprilTag =
            RefCounted.owned(OpenXRSpatialCapabilityConfigurationAprilTag(GodotHandle(requireConstructed(ObjectCalls.constructObject("OpenXRSpatialCapabilityConfigurationAprilTag"), "OpenXRSpatialCapabilityConfigurationAprilTag"))))
    }

    private object Binds {
        private const val GET_ENABLED_COMPONENTS_HASH = 235988956L
        @JvmField
        val getEnabledComponentsBind =
            ObjectCalls.getMethodBind("OpenXRSpatialCapabilityConfigurationAprilTag", "get_enabled_components", GET_ENABLED_COMPONENTS_HASH)

        private const val SET_APRIL_DICT_HASH = 3902905799L
        @JvmField
        val setAprilDictBind =
            ObjectCalls.getMethodBind("OpenXRSpatialCapabilityConfigurationAprilTag", "set_april_dict", SET_APRIL_DICT_HASH)

        private const val GET_APRIL_DICT_HASH = 440273016L
        @JvmField
        val getAprilDictBind =
            ObjectCalls.getMethodBind("OpenXRSpatialCapabilityConfigurationAprilTag", "get_april_dict", GET_APRIL_DICT_HASH)
    }
}
