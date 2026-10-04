package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Attachment format (used by `RenderingDevice`).
 *
 * Generated from Godot docs: RDAttachmentFormat
 */
class RDAttachmentFormat(handle: GodotHandle) : RefCounted(handle) {
    var format: RenderingDevice.DataFormat
        @JvmName("formatProperty")
        get() = getFormat()
        @JvmName("setFormatProperty")
        set(value) = setFormat(value)

    var samples: RenderingDevice.TextureSamples
        @JvmName("samplesProperty")
        get() = getSamples()
        @JvmName("setSamplesProperty")
        set(value) = setSamples(value)

    var usageFlags: Long
        @JvmName("usageFlagsProperty")
        get() = getUsageFlags()
        @JvmName("setUsageFlagsProperty")
        set(value) = setUsageFlags(value)

    /**
     * The attachment's data format.
     *
     * Generated from Godot docs: RDAttachmentFormat.set_format
     */
    fun setFormat(pMember: RenderingDevice.DataFormat) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setFormatBind, segment, pMember.value)
    }

    /**
     * The attachment's data format.
     *
     * Generated from Godot docs: RDAttachmentFormat.get_format
     */
    fun getFormat(): RenderingDevice.DataFormat {
        checkOpen()
        return RenderingDevice.DataFormat(ObjectCalls.ptrcallNoArgsRetLong(getFormatBind, segment))
    }

    /**
     * The number of samples used when sampling the attachment.
     *
     * Generated from Godot docs: RDAttachmentFormat.set_samples
     */
    fun setSamples(pMember: RenderingDevice.TextureSamples) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setSamplesBind, segment, pMember.value)
    }

    /**
     * The number of samples used when sampling the attachment.
     *
     * Generated from Godot docs: RDAttachmentFormat.get_samples
     */
    fun getSamples(): RenderingDevice.TextureSamples {
        checkOpen()
        return RenderingDevice.TextureSamples(ObjectCalls.ptrcallNoArgsRetLong(getSamplesBind, segment))
    }

    /**
     * The attachment's usage flags, which determine what can be done with it.
     *
     * Generated from Godot docs: RDAttachmentFormat.set_usage_flags
     */
    fun setUsageFlags(pMember: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithUInt32Arg(setUsageFlagsBind, segment, pMember)
    }

    /**
     * The attachment's usage flags, which determine what can be done with it.
     *
     * Generated from Godot docs: RDAttachmentFormat.get_usage_flags
     */
    fun getUsageFlags(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetUInt32(getUsageFlagsBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): RDAttachmentFormat? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): RDAttachmentFormat? =
            if (handle.address() == 0L) null else RefCounted.owned(RDAttachmentFormat(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): RDAttachmentFormat? =
            if (handle.address() == 0L) null else RDAttachmentFormat(GodotHandle(handle))

        private const val SET_FORMAT_HASH = 565531219L
        private val setFormatBind by lazy {
            ObjectCalls.getMethodBind("RDAttachmentFormat", "set_format", SET_FORMAT_HASH)
        }

        private const val GET_FORMAT_HASH = 2235804183L
        private val getFormatBind by lazy {
            ObjectCalls.getMethodBind("RDAttachmentFormat", "get_format", GET_FORMAT_HASH)
        }

        private const val SET_SAMPLES_HASH = 3774171498L
        private val setSamplesBind by lazy {
            ObjectCalls.getMethodBind("RDAttachmentFormat", "set_samples", SET_SAMPLES_HASH)
        }

        private const val GET_SAMPLES_HASH = 407791724L
        private val getSamplesBind by lazy {
            ObjectCalls.getMethodBind("RDAttachmentFormat", "get_samples", GET_SAMPLES_HASH)
        }

        private const val SET_USAGE_FLAGS_HASH = 1286410249L
        private val setUsageFlagsBind by lazy {
            ObjectCalls.getMethodBind("RDAttachmentFormat", "set_usage_flags", SET_USAGE_FLAGS_HASH)
        }

        private const val GET_USAGE_FLAGS_HASH = 3905245786L
        private val getUsageFlagsBind by lazy {
            ObjectCalls.getMethodBind("RDAttachmentFormat", "get_usage_flags", GET_USAGE_FLAGS_HASH)
        }
    }
}
