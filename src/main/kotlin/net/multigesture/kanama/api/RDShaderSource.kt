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
    var language: Long
        @JvmName("languageProperty")
        get() = getLanguage()
        @JvmName("setLanguageProperty")
        set(value) = setLanguage(value)

    fun setStageSource(stage: Long, source: String) {
        ObjectCalls.ptrcallWithLongAndStringArg(setStageSourceBind, handle, stage, source)
    }

    fun getStageSource(stage: Long): String {
        return ObjectCalls.ptrcallWithLongArgRetString(getStageSourceBind, handle, stage)
    }

    fun setLanguage(language: Long) {
        ObjectCalls.ptrcallWithLongArg(setLanguageBind, handle, language)
    }

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
