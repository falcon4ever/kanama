package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Shader source code (used by `RenderingDevice`).
 *
 * Generated from Godot docs: RDShaderSource
 */
class RDShaderSource(handle: GodotHandle) : RefCounted(handle) {
    var sourceVertex: String
        @JvmName("sourceVertexProperty")
        get() = getStageSource(RenderingDevice.ShaderStage.VERTEX)
        @JvmName("setSourceVertexProperty")
        set(value) = setStageSource(RenderingDevice.ShaderStage.VERTEX, value)

    var sourceFragment: String
        @JvmName("sourceFragmentProperty")
        get() = getStageSource(RenderingDevice.ShaderStage.FRAGMENT)
        @JvmName("setSourceFragmentProperty")
        set(value) = setStageSource(RenderingDevice.ShaderStage.FRAGMENT, value)

    var sourceTesselationControl: String
        @JvmName("sourceTesselationControlProperty")
        get() = getStageSource(RenderingDevice.ShaderStage.TESSELATION_CONTROL)
        @JvmName("setSourceTesselationControlProperty")
        set(value) = setStageSource(RenderingDevice.ShaderStage.TESSELATION_CONTROL, value)

    var sourceTesselationEvaluation: String
        @JvmName("sourceTesselationEvaluationProperty")
        get() = getStageSource(RenderingDevice.ShaderStage.TESSELATION_EVALUATION)
        @JvmName("setSourceTesselationEvaluationProperty")
        set(value) = setStageSource(RenderingDevice.ShaderStage.TESSELATION_EVALUATION, value)

    var sourceCompute: String
        @JvmName("sourceComputeProperty")
        get() = getStageSource(RenderingDevice.ShaderStage.COMPUTE)
        @JvmName("setSourceComputeProperty")
        set(value) = setStageSource(RenderingDevice.ShaderStage.COMPUTE, value)

    var sourceRaygen: String
        @JvmName("sourceRaygenProperty")
        get() = getStageSource(RenderingDevice.ShaderStage.RAYGEN)
        @JvmName("setSourceRaygenProperty")
        set(value) = setStageSource(RenderingDevice.ShaderStage.RAYGEN, value)

    var sourceAnyHit: String
        @JvmName("sourceAnyHitProperty")
        get() = getStageSource(RenderingDevice.ShaderStage.ANY_HIT)
        @JvmName("setSourceAnyHitProperty")
        set(value) = setStageSource(RenderingDevice.ShaderStage.ANY_HIT, value)

    var sourceClosestHit: String
        @JvmName("sourceClosestHitProperty")
        get() = getStageSource(RenderingDevice.ShaderStage.CLOSEST_HIT)
        @JvmName("setSourceClosestHitProperty")
        set(value) = setStageSource(RenderingDevice.ShaderStage.CLOSEST_HIT, value)

    var sourceMiss: String
        @JvmName("sourceMissProperty")
        get() = getStageSource(RenderingDevice.ShaderStage.MISS)
        @JvmName("setSourceMissProperty")
        set(value) = setStageSource(RenderingDevice.ShaderStage.MISS, value)

    var sourceIntersection: String
        @JvmName("sourceIntersectionProperty")
        get() = getStageSource(RenderingDevice.ShaderStage.INTERSECTION)
        @JvmName("setSourceIntersectionProperty")
        set(value) = setStageSource(RenderingDevice.ShaderStage.INTERSECTION, value)

    var language: RenderingDevice.ShaderLanguage
        @JvmName("languageProperty")
        get() = getLanguage()
        @JvmName("setLanguageProperty")
        set(value) = setLanguage(value)

    /**
     * Source code for the shader's vertex stage.
     *
     * Generated from Godot docs: RDShaderSource.set_stage_source
     */
    fun setStageSource(stage: RenderingDevice.ShaderStage, source: String) {
        checkOpen()
        ObjectCalls.ptrcallWithLongAndStringArg(Binds.setStageSourceBind, segment, stage.value, source)
    }

    /**
     * Source code for the shader's vertex stage.
     *
     * Generated from Godot docs: RDShaderSource.get_stage_source
     */
    fun getStageSource(stage: RenderingDevice.ShaderStage): String {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetString(Binds.getStageSourceBind, segment, stage.value)
    }

    /**
     * The language the shader is written in.
     *
     * Generated from Godot docs: RDShaderSource.set_language
     */
    fun setLanguage(language: RenderingDevice.ShaderLanguage) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setLanguageBind, segment, language.value)
    }

    /**
     * The language the shader is written in.
     *
     * Generated from Godot docs: RDShaderSource.get_language
     */
    fun getLanguage(): RenderingDevice.ShaderLanguage {
        checkOpen()
        return RenderingDevice.ShaderLanguage(ObjectCalls.ptrcallNoArgsRetLong(Binds.getLanguageBind, segment))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): RDShaderSource? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): RDShaderSource? =
            if (handle.address() == 0L) null else RefCounted.owned(RDShaderSource(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): RDShaderSource? =
            if (handle.address() == 0L) null else RDShaderSource(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_STAGE_SOURCE_HASH = 620821314L
        @JvmField
        val setStageSourceBind =
            ObjectCalls.getMethodBind("RDShaderSource", "set_stage_source", SET_STAGE_SOURCE_HASH)

        private const val GET_STAGE_SOURCE_HASH = 3354920045L
        @JvmField
        val getStageSourceBind =
            ObjectCalls.getMethodBind("RDShaderSource", "get_stage_source", GET_STAGE_SOURCE_HASH)

        private const val SET_LANGUAGE_HASH = 3422186742L
        @JvmField
        val setLanguageBind =
            ObjectCalls.getMethodBind("RDShaderSource", "set_language", SET_LANGUAGE_HASH)

        private const val GET_LANGUAGE_HASH = 1063538261L
        @JvmField
        val getLanguageBind =
            ObjectCalls.getMethodBind("RDShaderSource", "get_language", GET_LANGUAGE_HASH)
    }
}
