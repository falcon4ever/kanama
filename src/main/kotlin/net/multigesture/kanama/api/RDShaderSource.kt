package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Shader source code (used by `RenderingDevice`).
 *
 * Generated from Godot docs: RDShaderSource
 */
class RDShaderSource(handle: MemorySegment) : RefCounted(handle) {
    var sourceVertex: String
        @JvmName("sourceVertexProperty")
        get() = getStageSource(0L)
        @JvmName("setSourceVertexProperty")
        set(value) = setStageSource(0L, value)

    var sourceFragment: String
        @JvmName("sourceFragmentProperty")
        get() = getStageSource(1L)
        @JvmName("setSourceFragmentProperty")
        set(value) = setStageSource(1L, value)

    var sourceTesselationControl: String
        @JvmName("sourceTesselationControlProperty")
        get() = getStageSource(2L)
        @JvmName("setSourceTesselationControlProperty")
        set(value) = setStageSource(2L, value)

    var sourceTesselationEvaluation: String
        @JvmName("sourceTesselationEvaluationProperty")
        get() = getStageSource(3L)
        @JvmName("setSourceTesselationEvaluationProperty")
        set(value) = setStageSource(3L, value)

    var sourceCompute: String
        @JvmName("sourceComputeProperty")
        get() = getStageSource(4L)
        @JvmName("setSourceComputeProperty")
        set(value) = setStageSource(4L, value)

    var sourceRaygen: String
        @JvmName("sourceRaygenProperty")
        get() = getStageSource(5L)
        @JvmName("setSourceRaygenProperty")
        set(value) = setStageSource(5L, value)

    var sourceAnyHit: String
        @JvmName("sourceAnyHitProperty")
        get() = getStageSource(6L)
        @JvmName("setSourceAnyHitProperty")
        set(value) = setStageSource(6L, value)

    var sourceClosestHit: String
        @JvmName("sourceClosestHitProperty")
        get() = getStageSource(7L)
        @JvmName("setSourceClosestHitProperty")
        set(value) = setStageSource(7L, value)

    var sourceMiss: String
        @JvmName("sourceMissProperty")
        get() = getStageSource(8L)
        @JvmName("setSourceMissProperty")
        set(value) = setStageSource(8L, value)

    var sourceIntersection: String
        @JvmName("sourceIntersectionProperty")
        get() = getStageSource(9L)
        @JvmName("setSourceIntersectionProperty")
        set(value) = setStageSource(9L, value)

    var language: Long
        @JvmName("languageProperty")
        get() = getLanguage()
        @JvmName("setLanguageProperty")
        set(value) = setLanguage(value)

    /**
     * Source code for the shader's vertex stage.
     *
     * Generated from Godot docs: RDShaderSource.set_stage_source
     */
    fun setStageSource(stage: Long, source: String) {
        ObjectCalls.ptrcallWithLongAndStringArg(setStageSourceBind, handle, stage, source)
    }

    /**
     * Source code for the shader's vertex stage.
     *
     * Generated from Godot docs: RDShaderSource.get_stage_source
     */
    fun getStageSource(stage: Long): String {
        return ObjectCalls.ptrcallWithLongArgRetString(getStageSourceBind, handle, stage)
    }

    /**
     * The language the shader is written in.
     *
     * Generated from Godot docs: RDShaderSource.set_language
     */
    fun setLanguage(language: Long) {
        ObjectCalls.ptrcallWithLongArg(setLanguageBind, handle, language)
    }

    /**
     * The language the shader is written in.
     *
     * Generated from Godot docs: RDShaderSource.get_language
     */
    fun getLanguage(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getLanguageBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): RDShaderSource? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): RDShaderSource? =
            if (handle.address() == 0L) null else RDShaderSource(handle)

        private const val SET_STAGE_SOURCE_HASH = 620821314L
        private val setStageSourceBind by lazy {
            ObjectCalls.getMethodBind("RDShaderSource", "set_stage_source", SET_STAGE_SOURCE_HASH)
        }

        private const val GET_STAGE_SOURCE_HASH = 3354920045L
        private val getStageSourceBind by lazy {
            ObjectCalls.getMethodBind("RDShaderSource", "get_stage_source", GET_STAGE_SOURCE_HASH)
        }

        private const val SET_LANGUAGE_HASH = 3422186742L
        private val setLanguageBind by lazy {
            ObjectCalls.getMethodBind("RDShaderSource", "set_language", SET_LANGUAGE_HASH)
        }

        private const val GET_LANGUAGE_HASH = 1063538261L
        private val getLanguageBind by lazy {
            ObjectCalls.getMethodBind("RDShaderSource", "get_language", GET_LANGUAGE_HASH)
        }
    }
}
