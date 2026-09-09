package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: ScriptBacktrace
 */
class ScriptBacktrace(handle: MemorySegment) : RefCounted(handle) {
    fun getLanguageName(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getLanguageNameBind, handle)
    }

    fun isEmpty(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isEmptyBind, handle)
    }

    fun getFrameCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getFrameCountBind, handle)
    }

    fun getFrameLine(index: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetInt(getFrameLineBind, handle, index)
    }

    fun getGlobalVariableCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getGlobalVariableCountBind, handle)
    }

    fun getLocalVariableCount(frameIndex: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetInt(getLocalVariableCountBind, handle, frameIndex)
    }

    fun getMemberVariableCount(frameIndex: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetInt(getMemberVariableCountBind, handle, frameIndex)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): ScriptBacktrace? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ScriptBacktrace? =
            if (handle.address() == 0L) null else ScriptBacktrace(handle)

        private const val GET_LANGUAGE_NAME_HASH = 201670096L
        private val getLanguageNameBind by lazy {
            ObjectCalls.getMethodBind("ScriptBacktrace", "get_language_name", GET_LANGUAGE_NAME_HASH)
        }

        private const val IS_EMPTY_HASH = 36873697L
        private val isEmptyBind by lazy {
            ObjectCalls.getMethodBind("ScriptBacktrace", "is_empty", IS_EMPTY_HASH)
        }

        private const val GET_FRAME_COUNT_HASH = 3905245786L
        private val getFrameCountBind by lazy {
            ObjectCalls.getMethodBind("ScriptBacktrace", "get_frame_count", GET_FRAME_COUNT_HASH)
        }

        private const val GET_FRAME_LINE_HASH = 923996154L
        private val getFrameLineBind by lazy {
            ObjectCalls.getMethodBind("ScriptBacktrace", "get_frame_line", GET_FRAME_LINE_HASH)
        }

        private const val GET_GLOBAL_VARIABLE_COUNT_HASH = 3905245786L
        private val getGlobalVariableCountBind by lazy {
            ObjectCalls.getMethodBind("ScriptBacktrace", "get_global_variable_count", GET_GLOBAL_VARIABLE_COUNT_HASH)
        }

        private const val GET_LOCAL_VARIABLE_COUNT_HASH = 923996154L
        private val getLocalVariableCountBind by lazy {
            ObjectCalls.getMethodBind("ScriptBacktrace", "get_local_variable_count", GET_LOCAL_VARIABLE_COUNT_HASH)
        }

        private const val GET_MEMBER_VARIABLE_COUNT_HASH = 923996154L
        private val getMemberVariableCountBind by lazy {
            ObjectCalls.getMethodBind("ScriptBacktrace", "get_member_variable_count", GET_MEMBER_VARIABLE_COUNT_HASH)
        }
    }
}
