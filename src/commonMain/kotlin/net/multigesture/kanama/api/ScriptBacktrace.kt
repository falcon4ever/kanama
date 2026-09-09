package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * A captured backtrace of a specific script language.
 *
 * Generated from Godot docs: ScriptBacktrace
 */
class ScriptBacktrace(handle: MemorySegment) : RefCounted(handle) {
    /**
     * Returns the name of the script language that this backtrace was captured from.
     *
     * Generated from Godot docs: ScriptBacktrace.get_language_name
     */
    fun getLanguageName(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getLanguageNameBind, handle)
    }

    /**
     * Returns `true` if the backtrace has no stack frames.
     *
     * Generated from Godot docs: ScriptBacktrace.is_empty
     */
    fun isEmpty(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isEmptyBind, handle)
    }

    /**
     * Returns the number of stack frames in the backtrace.
     *
     * Generated from Godot docs: ScriptBacktrace.get_frame_count
     */
    fun getFrameCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getFrameCountBind, handle)
    }

    /**
     * Returns the line number of the call site represented by the stack frame at the specified index.
     *
     * Generated from Godot docs: ScriptBacktrace.get_frame_line
     */
    fun getFrameLine(index: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetInt(getFrameLineBind, handle, index)
    }

    /**
     * Returns the number of global variables (e.g. autoload singletons) in the backtrace. Note: This
     * will be non-zero only if the `include_variables` parameter was `true` when capturing the
     * backtrace with `Engine.capture_script_backtraces`.
     *
     * Generated from Godot docs: ScriptBacktrace.get_global_variable_count
     */
    fun getGlobalVariableCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getGlobalVariableCountBind, handle)
    }

    /**
     * Returns the number of local variables in the stack frame at the specified index. Note: This will
     * be non-zero only if the `include_variables` parameter was `true` when capturing the backtrace
     * with `Engine.capture_script_backtraces`.
     *
     * Generated from Godot docs: ScriptBacktrace.get_local_variable_count
     */
    fun getLocalVariableCount(frameIndex: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetInt(getLocalVariableCountBind, handle, frameIndex)
    }

    /**
     * Returns the number of member variables in the stack frame at the specified index. Note: This
     * will be non-zero only if the `include_variables` parameter was `true` when capturing the
     * backtrace with `Engine.capture_script_backtraces`.
     *
     * Generated from Godot docs: ScriptBacktrace.get_member_variable_count
     */
    fun getMemberVariableCount(frameIndex: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetInt(getMemberVariableCountBind, handle, frameIndex)
    }

    companion object {
        @JvmStatic
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
