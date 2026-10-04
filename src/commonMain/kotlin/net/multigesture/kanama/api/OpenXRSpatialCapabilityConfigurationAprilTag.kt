package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationAprilTag
 */
class OpenXRSpatialCapabilityConfigurationAprilTag(handle: GodotHandle) : OpenXRSpatialCapabilityConfigurationBaseHeader(handle) {
    var aprilDict: OpenXRSpatialCapabilityConfigurationAprilTag.AprilTagDict
        @JvmName("aprilDictProperty")
        get() = getAprilDict()
        @JvmName("setAprilDictProperty")
        set(value) = setAprilDict(value)

    fun getEnabledComponents(): List<Long> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedInt64List(getEnabledComponentsBind, segment)
    }

    fun setAprilDict(aprilDict: OpenXRSpatialCapabilityConfigurationAprilTag.AprilTagDict) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setAprilDictBind, segment, aprilDict.value)
    }

    fun getAprilDict(): OpenXRSpatialCapabilityConfigurationAprilTag.AprilTagDict {
        checkOpen()
        return OpenXRSpatialCapabilityConfigurationAprilTag.AprilTagDict(ObjectCalls.ptrcallNoArgsRetLong(getAprilDictBind, segment))
    }

    @JvmInline
    value class AprilTagDict(override val value: Long) : GodotEnumValue {
        companion object {
            val DICT_16H5: AprilTagDict get() = AprilTagDict(1L)
            val DICT_25H9: AprilTagDict get() = AprilTagDict(2L)
            val DICT_36H10: AprilTagDict get() = AprilTagDict(3L)
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

        private const val GET_ENABLED_COMPONENTS_HASH = 235988956L
        private val getEnabledComponentsBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialCapabilityConfigurationAprilTag", "get_enabled_components", GET_ENABLED_COMPONENTS_HASH)
        }

        private const val SET_APRIL_DICT_HASH = 3902905799L
        private val setAprilDictBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialCapabilityConfigurationAprilTag", "set_april_dict", SET_APRIL_DICT_HASH)
        }

        private const val GET_APRIL_DICT_HASH = 440273016L
        private val getAprilDictBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialCapabilityConfigurationAprilTag", "get_april_dict", GET_APRIL_DICT_HASH)
        }
    }
}
