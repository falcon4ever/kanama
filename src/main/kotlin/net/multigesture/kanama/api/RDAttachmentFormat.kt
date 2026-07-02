package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Attachment format (used by `RenderingDevice`).
 *
 * Generated from Godot docs: RDAttachmentFormat
 */
class RDAttachmentFormat(handle: MemorySegment) : RefCounted(handle) {
    var format: Long
        @JvmName("formatProperty")
        get() = getFormat()
        @JvmName("setFormatProperty")
        set(value) = setFormat(value)

    var samples: Long
        @JvmName("samplesProperty")
        get() = getSamples()
        @JvmName("setSamplesProperty")
        set(value) = setSamples(value)

    var usageFlags: Long
        @JvmName("usageFlagsProperty")
        get() = getUsageFlags()
        @JvmName("setUsageFlagsProperty")
        set(value) = setUsageFlags(value)

    fun setFormat(pMember: Long) {
        ObjectCalls.ptrcallWithLongArg(setFormatBind, handle, pMember)
    }

    fun getFormat(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getFormatBind, handle)
    }

    fun setSamples(pMember: Long) {
        ObjectCalls.ptrcallWithLongArg(setSamplesBind, handle, pMember)
    }

    fun getSamples(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getSamplesBind, handle)
    }

    fun setUsageFlags(pMember: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setUsageFlagsBind, handle, pMember)
    }

    fun getUsageFlags(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getUsageFlagsBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): RDAttachmentFormat? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): RDAttachmentFormat? =
            if (handle.address() == 0L) null else RDAttachmentFormat(handle)

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
