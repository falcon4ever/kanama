package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A captured backtrace of a specific script language.
 *
 * Generated from Godot docs: ScriptBacktrace
 */
class ScriptBacktrace(handle: GodotHandle) : RefCounted(handle) {
    /**
     * Returns the name of the script language that this backtrace was captured from.
     *
     * Generated from Godot docs: ScriptBacktrace.get_language_name
     */
    fun getLanguageName(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getLanguageNameBind, segment)
    }

    /**
     * Returns `true` if the backtrace has no stack frames.
     *
     * Generated from Godot docs: ScriptBacktrace.is_empty
     */
    fun isEmpty(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isEmptyBind, segment)
    }

    /**
     * Returns the number of stack frames in the backtrace.
     *
     * Generated from Godot docs: ScriptBacktrace.get_frame_count
     */
    fun getFrameCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getFrameCountBind, segment)
    }

    /**
     * Returns the name of the function called at the stack frame at the specified index.
     *
     * Generated from Godot docs: ScriptBacktrace.get_frame_function
     */
    fun getFrameFunction(index: Int): String {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetString(getFrameFunctionBind, segment, index)
    }

    /**
     * Returns the file name of the call site represented by the stack frame at the specified index.
     *
     * Generated from Godot docs: ScriptBacktrace.get_frame_file
     */
    fun getFrameFile(index: Int): String {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetString(getFrameFileBind, segment, index)
    }

    /**
     * Returns the line number of the call site represented by the stack frame at the specified index.
     *
     * Generated from Godot docs: ScriptBacktrace.get_frame_line
     */
    fun getFrameLine(index: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetInt(getFrameLineBind, segment, index)
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
        return ObjectCalls.ptrcallNoArgsRetInt(getGlobalVariableCountBind, segment)
    }

    /**
     * Returns the name of the global variable at the specified index.
     *
     * Generated from Godot docs: ScriptBacktrace.get_global_variable_name
     */
    fun getGlobalVariableName(variableIndex: Int): String {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetString(getGlobalVariableNameBind, segment, variableIndex)
    }

    /**
     * Returns the value of the global variable at the specified index. Warning: With GDScript
     * backtraces, the returned `Variant` will be the variable's actual value, including any object
     * references. This means that storing the returned `Variant` will prevent any such object from
     * being deallocated, so it's generally recommended not to do so.
     *
     * Generated from Godot docs: ScriptBacktrace.get_global_variable_value
     */
    fun getGlobalVariableValue(variableIndex: Int): Any? {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetVariantScalar(getGlobalVariableValueBind, segment, variableIndex)
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
        return ObjectCalls.ptrcallWithIntArgRetInt(getLocalVariableCountBind, segment, frameIndex)
    }

    /**
     * Returns the name of the local variable at the specified `variable_index` in the stack frame at
     * the specified `frame_index`.
     *
     * Generated from Godot docs: ScriptBacktrace.get_local_variable_name
     */
    fun getLocalVariableName(frameIndex: Int, variableIndex: Int): String {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntArgsRetString(getLocalVariableNameBind, segment, frameIndex, variableIndex)
    }

    /**
     * Returns the value of the local variable at the specified `variable_index` in the stack frame at
     * the specified `frame_index`. Warning: With GDScript backtraces, the returned `Variant` will be
     * the variable's actual value, including any object references. This means that storing the
     * returned `Variant` will prevent any such object from being deallocated, so it's generally
     * recommended not to do so.
     *
     * Generated from Godot docs: ScriptBacktrace.get_local_variable_value
     */
    fun getLocalVariableValue(frameIndex: Int, variableIndex: Int): Any? {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntArgsRetVariantScalar(getLocalVariableValueBind, segment, frameIndex, variableIndex)
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
        return ObjectCalls.ptrcallWithIntArgRetInt(getMemberVariableCountBind, segment, frameIndex)
    }

    /**
     * Returns the name of the member variable at the specified `variable_index` in the stack frame at
     * the specified `frame_index`.
     *
     * Generated from Godot docs: ScriptBacktrace.get_member_variable_name
     */
    fun getMemberVariableName(frameIndex: Int, variableIndex: Int): String {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntArgsRetString(getMemberVariableNameBind, segment, frameIndex, variableIndex)
    }

    /**
     * Returns the value of the member variable at the specified `variable_index` in the stack frame at
     * the specified `frame_index`. Warning: With GDScript backtraces, the returned `Variant` will be
     * the variable's actual value, including any object references. This means that storing the
     * returned `Variant` will prevent any such object from being deallocated, so it's generally
     * recommended not to do so.
     *
     * Generated from Godot docs: ScriptBacktrace.get_member_variable_value
     */
    fun getMemberVariableValue(frameIndex: Int, variableIndex: Int): Any? {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntArgsRetVariantScalar(getMemberVariableValueBind, segment, frameIndex, variableIndex)
    }

    /**
     * Converts the backtrace to a `String`, where the entire string will be indented by `indent_all`
     * number of spaces, and the individual stack frames will be additionally indented by
     * `indent_frames` number of spaces. Note: Calling `Object.to_string` on a `ScriptBacktrace` will
     * produce the same output as calling `format` with all parameters left at their default values.
     *
     * Generated from Godot docs: ScriptBacktrace.format
     */
    fun format(indentAll: Int = 0, indentFrames: Int = 4): String {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntArgsRetString(formatBind, segment, indentAll, indentFrames)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ScriptBacktrace? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): ScriptBacktrace? =
            if (handle.address() == 0L) null else ScriptBacktrace(GodotHandle(handle))

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

        private const val GET_FRAME_FUNCTION_HASH = 844755477L
        private val getFrameFunctionBind by lazy {
            ObjectCalls.getMethodBind("ScriptBacktrace", "get_frame_function", GET_FRAME_FUNCTION_HASH)
        }

        private const val GET_FRAME_FILE_HASH = 844755477L
        private val getFrameFileBind by lazy {
            ObjectCalls.getMethodBind("ScriptBacktrace", "get_frame_file", GET_FRAME_FILE_HASH)
        }

        private const val GET_FRAME_LINE_HASH = 923996154L
        private val getFrameLineBind by lazy {
            ObjectCalls.getMethodBind("ScriptBacktrace", "get_frame_line", GET_FRAME_LINE_HASH)
        }

        private const val GET_GLOBAL_VARIABLE_COUNT_HASH = 3905245786L
        private val getGlobalVariableCountBind by lazy {
            ObjectCalls.getMethodBind("ScriptBacktrace", "get_global_variable_count", GET_GLOBAL_VARIABLE_COUNT_HASH)
        }

        private const val GET_GLOBAL_VARIABLE_NAME_HASH = 844755477L
        private val getGlobalVariableNameBind by lazy {
            ObjectCalls.getMethodBind("ScriptBacktrace", "get_global_variable_name", GET_GLOBAL_VARIABLE_NAME_HASH)
        }

        private const val GET_GLOBAL_VARIABLE_VALUE_HASH = 4227898402L
        private val getGlobalVariableValueBind by lazy {
            ObjectCalls.getMethodBind("ScriptBacktrace", "get_global_variable_value", GET_GLOBAL_VARIABLE_VALUE_HASH)
        }

        private const val GET_LOCAL_VARIABLE_COUNT_HASH = 923996154L
        private val getLocalVariableCountBind by lazy {
            ObjectCalls.getMethodBind("ScriptBacktrace", "get_local_variable_count", GET_LOCAL_VARIABLE_COUNT_HASH)
        }

        private const val GET_LOCAL_VARIABLE_NAME_HASH = 1391810591L
        private val getLocalVariableNameBind by lazy {
            ObjectCalls.getMethodBind("ScriptBacktrace", "get_local_variable_name", GET_LOCAL_VARIABLE_NAME_HASH)
        }

        private const val GET_LOCAL_VARIABLE_VALUE_HASH = 678354945L
        private val getLocalVariableValueBind by lazy {
            ObjectCalls.getMethodBind("ScriptBacktrace", "get_local_variable_value", GET_LOCAL_VARIABLE_VALUE_HASH)
        }

        private const val GET_MEMBER_VARIABLE_COUNT_HASH = 923996154L
        private val getMemberVariableCountBind by lazy {
            ObjectCalls.getMethodBind("ScriptBacktrace", "get_member_variable_count", GET_MEMBER_VARIABLE_COUNT_HASH)
        }

        private const val GET_MEMBER_VARIABLE_NAME_HASH = 1391810591L
        private val getMemberVariableNameBind by lazy {
            ObjectCalls.getMethodBind("ScriptBacktrace", "get_member_variable_name", GET_MEMBER_VARIABLE_NAME_HASH)
        }

        private const val GET_MEMBER_VARIABLE_VALUE_HASH = 678354945L
        private val getMemberVariableValueBind by lazy {
            ObjectCalls.getMethodBind("ScriptBacktrace", "get_member_variable_value", GET_MEMBER_VARIABLE_VALUE_HASH)
        }

        private const val FORMAT_HASH = 3464456933L
        private val formatBind by lazy {
            ObjectCalls.getMethodBind("ScriptBacktrace", "format", FORMAT_HASH)
        }
    }
}
