package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationAprilTag
 */
class OpenXRSpatialCapabilityConfigurationAprilTag(handle: MemorySegment) : OpenXRSpatialCapabilityConfigurationBaseHeader(handle) {
    var aprilDict: Long
        @JvmName("aprilDictProperty")
        get() = getAprilDict()
        @JvmName("setAprilDictProperty")
        set(value) = setAprilDict(value)

    fun getEnabledComponents(): List<Long> {
        return ObjectCalls.ptrcallNoArgsRetPackedInt64List(getEnabledComponentsBind, handle)
    }

    fun setAprilDict(aprilDict: Long) {
        ObjectCalls.ptrcallWithLongArg(setAprilDictBind, handle, aprilDict)
    }

    fun getAprilDict(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAprilDictBind, handle)
    }

    companion object {
        const val APRIL_TAG_DICT_16H5: Long = 1L
        const val APRIL_TAG_DICT_25H9: Long = 2L
        const val APRIL_TAG_DICT_36H10: Long = 3L
        const val APRIL_TAG_DICT_36H11: Long = 4L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): OpenXRSpatialCapabilityConfigurationAprilTag? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRSpatialCapabilityConfigurationAprilTag? =
            if (handle.address() == 0L) null else OpenXRSpatialCapabilityConfigurationAprilTag(handle)

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
