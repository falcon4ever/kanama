package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: RDPipelineMultisampleState
 */
class RDPipelineMultisampleState(handle: MemorySegment) : RefCounted(handle) {
    var sampleCount: Long
        @JvmName("sampleCountProperty")
        get() = getSampleCount()
        @JvmName("setSampleCountProperty")
        set(value) = setSampleCount(value)

    var enableSampleShading: Boolean
        @JvmName("enableSampleShadingProperty")
        get() = getEnableSampleShading()
        @JvmName("setEnableSampleShadingProperty")
        set(value) = setEnableSampleShading(value)

    var minSampleShading: Double
        @JvmName("minSampleShadingProperty")
        get() = getMinSampleShading()
        @JvmName("setMinSampleShadingProperty")
        set(value) = setMinSampleShading(value)

    var enableAlphaToCoverage: Boolean
        @JvmName("enableAlphaToCoverageProperty")
        get() = getEnableAlphaToCoverage()
        @JvmName("setEnableAlphaToCoverageProperty")
        set(value) = setEnableAlphaToCoverage(value)

    var enableAlphaToOne: Boolean
        @JvmName("enableAlphaToOneProperty")
        get() = getEnableAlphaToOne()
        @JvmName("setEnableAlphaToOneProperty")
        set(value) = setEnableAlphaToOne(value)

    val sampleMasks: List<Long>
        @JvmName("sampleMasksProperty")
        get() = getSampleMasks()

    fun setSampleCount(pMember: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setSampleCountBind, handle, pMember)
    }

    fun getSampleCount(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getSampleCountBind, handle)
    }

    fun setEnableSampleShading(pMember: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setEnableSampleShadingBind, handle, pMember)
    }

    fun getEnableSampleShading(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getEnableSampleShadingBind, handle)
    }

    fun setMinSampleShading(pMember: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setMinSampleShadingBind, handle, pMember)
    }

    fun getMinSampleShading(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getMinSampleShadingBind, handle)
    }

    fun setEnableAlphaToCoverage(pMember: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setEnableAlphaToCoverageBind, handle, pMember)
    }

    fun getEnableAlphaToCoverage(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getEnableAlphaToCoverageBind, handle)
    }

    fun setEnableAlphaToOne(pMember: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setEnableAlphaToOneBind, handle, pMember)
    }

    fun getEnableAlphaToOne(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getEnableAlphaToOneBind, handle)
    }

    fun getSampleMasks(): List<Long> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLongList(getSampleMasksBind, handle)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): RDPipelineMultisampleState? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): RDPipelineMultisampleState? =
            if (handle.address() == 0L) null else RDPipelineMultisampleState(handle)

        private const val SET_SAMPLE_COUNT_HASH = 3774171498L
        private val setSampleCountBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineMultisampleState", "set_sample_count", SET_SAMPLE_COUNT_HASH)
        }

        private const val GET_SAMPLE_COUNT_HASH = 407791724L
        private val getSampleCountBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineMultisampleState", "get_sample_count", GET_SAMPLE_COUNT_HASH)
        }

        private const val SET_ENABLE_SAMPLE_SHADING_HASH = 2586408642L
        private val setEnableSampleShadingBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineMultisampleState", "set_enable_sample_shading", SET_ENABLE_SAMPLE_SHADING_HASH)
        }

        private const val GET_ENABLE_SAMPLE_SHADING_HASH = 36873697L
        private val getEnableSampleShadingBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineMultisampleState", "get_enable_sample_shading", GET_ENABLE_SAMPLE_SHADING_HASH)
        }

        private const val SET_MIN_SAMPLE_SHADING_HASH = 373806689L
        private val setMinSampleShadingBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineMultisampleState", "set_min_sample_shading", SET_MIN_SAMPLE_SHADING_HASH)
        }

        private const val GET_MIN_SAMPLE_SHADING_HASH = 1740695150L
        private val getMinSampleShadingBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineMultisampleState", "get_min_sample_shading", GET_MIN_SAMPLE_SHADING_HASH)
        }

        private const val SET_ENABLE_ALPHA_TO_COVERAGE_HASH = 2586408642L
        private val setEnableAlphaToCoverageBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineMultisampleState", "set_enable_alpha_to_coverage", SET_ENABLE_ALPHA_TO_COVERAGE_HASH)
        }

        private const val GET_ENABLE_ALPHA_TO_COVERAGE_HASH = 36873697L
        private val getEnableAlphaToCoverageBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineMultisampleState", "get_enable_alpha_to_coverage", GET_ENABLE_ALPHA_TO_COVERAGE_HASH)
        }

        private const val SET_ENABLE_ALPHA_TO_ONE_HASH = 2586408642L
        private val setEnableAlphaToOneBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineMultisampleState", "set_enable_alpha_to_one", SET_ENABLE_ALPHA_TO_ONE_HASH)
        }

        private const val GET_ENABLE_ALPHA_TO_ONE_HASH = 36873697L
        private val getEnableAlphaToOneBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineMultisampleState", "get_enable_alpha_to_one", GET_ENABLE_ALPHA_TO_ONE_HASH)
        }

        private const val GET_SAMPLE_MASKS_HASH = 3995934104L
        private val getSampleMasksBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineMultisampleState", "get_sample_masks", GET_SAMPLE_MASKS_HASH)
        }
    }
}
