package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Pipeline multisample state (used by `RenderingDevice`).
 *
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

    var sampleMasks: List<Long>
        @JvmName("sampleMasksProperty")
        get() = getSampleMasks()
        @JvmName("setSampleMasksProperty")
        set(value) = setSampleMasks(value)

    /**
     * The number of MSAA samples (or SSAA samples if `enable_sample_shading` is `true`) to perform.
     * Higher values result in better antialiasing, at the cost of performance.
     *
     * Generated from Godot docs: RDPipelineMultisampleState.set_sample_count
     */
    fun setSampleCount(pMember: Long) {
        ObjectCalls.ptrcallWithLongArg(setSampleCountBind, handle, pMember)
    }

    /**
     * The number of MSAA samples (or SSAA samples if `enable_sample_shading` is `true`) to perform.
     * Higher values result in better antialiasing, at the cost of performance.
     *
     * Generated from Godot docs: RDPipelineMultisampleState.get_sample_count
     */
    fun getSampleCount(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getSampleCountBind, handle)
    }

    /**
     * If `true`, enables per-sample shading which replaces MSAA by SSAA. This provides higher quality
     * antialiasing that works with transparent (alpha scissor) edges. This has a very high performance
     * cost. See also `min_sample_shading`. See the per-sample shading Vulkan documentation
     * (https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#primsrast-sampleshading)
     * for more details.
     *
     * Generated from Godot docs: RDPipelineMultisampleState.set_enable_sample_shading
     */
    fun setEnableSampleShading(pMember: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEnableSampleShadingBind, handle, pMember)
    }

    /**
     * If `true`, enables per-sample shading which replaces MSAA by SSAA. This provides higher quality
     * antialiasing that works with transparent (alpha scissor) edges. This has a very high performance
     * cost. See also `min_sample_shading`. See the per-sample shading Vulkan documentation
     * (https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#primsrast-sampleshading)
     * for more details.
     *
     * Generated from Godot docs: RDPipelineMultisampleState.get_enable_sample_shading
     */
    fun getEnableSampleShading(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getEnableSampleShadingBind, handle)
    }

    /**
     * The multiplier of `sample_count` that determines how many samples are performed for each
     * fragment. Must be between `0.0` and `1.0` (inclusive). Only effective if `enable_sample_shading`
     * is `true`. If `min_sample_shading` is `1.0`, fragment invocation must only read from the
     * coverage index sample. Tile image access must not be used if `enable_sample_shading` is not
     * `1.0`.
     *
     * Generated from Godot docs: RDPipelineMultisampleState.set_min_sample_shading
     */
    fun setMinSampleShading(pMember: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMinSampleShadingBind, handle, pMember)
    }

    /**
     * The multiplier of `sample_count` that determines how many samples are performed for each
     * fragment. Must be between `0.0` and `1.0` (inclusive). Only effective if `enable_sample_shading`
     * is `true`. If `min_sample_shading` is `1.0`, fragment invocation must only read from the
     * coverage index sample. Tile image access must not be used if `enable_sample_shading` is not
     * `1.0`.
     *
     * Generated from Godot docs: RDPipelineMultisampleState.get_min_sample_shading
     */
    fun getMinSampleShading(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMinSampleShadingBind, handle)
    }

    /**
     * If `true`, alpha to coverage is enabled. This generates a temporary coverage value based on the
     * alpha component of the fragment's first color output. This allows alpha transparency to make use
     * of multisample antialiasing.
     *
     * Generated from Godot docs: RDPipelineMultisampleState.set_enable_alpha_to_coverage
     */
    fun setEnableAlphaToCoverage(pMember: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEnableAlphaToCoverageBind, handle, pMember)
    }

    /**
     * If `true`, alpha to coverage is enabled. This generates a temporary coverage value based on the
     * alpha component of the fragment's first color output. This allows alpha transparency to make use
     * of multisample antialiasing.
     *
     * Generated from Godot docs: RDPipelineMultisampleState.get_enable_alpha_to_coverage
     */
    fun getEnableAlphaToCoverage(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getEnableAlphaToCoverageBind, handle)
    }

    /**
     * If `true`, alpha is forced to either `0.0` or `1.0`. This allows hardening the edges of
     * antialiased alpha transparencies. Only relevant if `enable_alpha_to_coverage` is `true`.
     *
     * Generated from Godot docs: RDPipelineMultisampleState.set_enable_alpha_to_one
     */
    fun setEnableAlphaToOne(pMember: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEnableAlphaToOneBind, handle, pMember)
    }

    /**
     * If `true`, alpha is forced to either `0.0` or `1.0`. This allows hardening the edges of
     * antialiased alpha transparencies. Only relevant if `enable_alpha_to_coverage` is `true`.
     *
     * Generated from Godot docs: RDPipelineMultisampleState.get_enable_alpha_to_one
     */
    fun getEnableAlphaToOne(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getEnableAlphaToOneBind, handle)
    }

    /**
     * The sample mask array. See the sample mask Vulkan documentation
     * (https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#fragops-samplemask)
     * for more details.
     *
     * Generated from Godot docs: RDPipelineMultisampleState.set_sample_masks
     */
    fun setSampleMasks(masks: List<Long>) {
        ObjectCalls.ptrcallWithTypedIntListArg(setSampleMasksBind, handle, masks)
    }

    /**
     * The sample mask array. See the sample mask Vulkan documentation
     * (https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#fragops-samplemask)
     * for more details.
     *
     * Generated from Godot docs: RDPipelineMultisampleState.get_sample_masks
     */
    fun getSampleMasks(): List<Long> {
        return ObjectCalls.ptrcallNoArgsRetLongList(getSampleMasksBind, handle)
    }

    companion object {
        @JvmStatic
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

        private const val SET_SAMPLE_MASKS_HASH = 381264803L
        private val setSampleMasksBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineMultisampleState", "set_sample_masks", SET_SAMPLE_MASKS_HASH)
        }

        private const val GET_SAMPLE_MASKS_HASH = 3995934104L
        private val getSampleMasksBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineMultisampleState", "get_sample_masks", GET_SAMPLE_MASKS_HASH)
        }
    }
}
