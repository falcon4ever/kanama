package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Exposes the internal debugger.
 *
 * Generated from Godot docs: EngineDebugger
 */
object EngineDebugger {
    private val singleton: MemorySegment by lazy {
        ObjectCalls.getSingleton("EngineDebugger")
    }

    /**
     * Returns `true` if the debugger is active otherwise `false`.
     *
     * Generated from Godot docs: EngineDebugger.is_active
     */
    @JvmStatic
    fun isActive(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isActiveBind, singleton)
    }

    /**
     * Registers a profiler with the given `name`. See `EngineProfiler` for more information.
     *
     * Generated from Godot docs: EngineDebugger.register_profiler
     */
    @JvmStatic
    fun registerProfiler(name: String, profiler: EngineProfiler?) {
        ObjectCalls.ptrcallWithStringNameAndObjectArg(registerProfilerBind, singleton, name, profiler?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Unregisters a profiler with given `name`.
     *
     * Generated from Godot docs: EngineDebugger.unregister_profiler
     */
    @JvmStatic
    fun unregisterProfiler(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(unregisterProfilerBind, singleton, name)
    }

    /**
     * Returns `true` if a profiler with the given name is present and active otherwise `false`.
     *
     * Generated from Godot docs: EngineDebugger.is_profiling
     */
    @JvmStatic
    fun isProfiling(name: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(isProfilingBind, singleton, name)
    }

    /**
     * Returns `true` if a profiler with the given name is present otherwise `false`.
     *
     * Generated from Godot docs: EngineDebugger.has_profiler
     */
    @JvmStatic
    fun hasProfiler(name: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(hasProfilerBind, singleton, name)
    }

    /**
     * Calls the `add` callable of the profiler with given `name` and `data`.
     *
     * Generated from Godot docs: EngineDebugger.profiler_add_frame_data
     */
    @JvmStatic
    fun profilerAddFrameData(name: String, data: List<Any?>) {
        ObjectCalls.ptrcallWithStringNameArrayArgs(profilerAddFrameDataBind, singleton, name, data)
    }

    /**
     * Calls the `toggle` callable of the profiler with given `name` and `arguments`. Enables/Disables
     * the same profiler depending on `enable` argument.
     *
     * Generated from Godot docs: EngineDebugger.profiler_enable
     */
    @JvmStatic
    fun profilerEnable(name: String, enable: Boolean, arguments: List<Any?> = emptyList()) {
        ObjectCalls.ptrcallWithStringNameBoolArrayArgs(profilerEnableBind, singleton, name, enable, arguments)
    }

    /**
     * Registers a message capture with given `name`. If `name` is "my_message" then messages starting
     * with "my_message:" will be called with the given callable. The callable must accept a message
     * string and a data array as argument. The callable should return `true` if the message is
     * recognized. Note: The callable will receive the message with the prefix stripped, unlike
     * `EditorDebuggerPlugin._capture`. See the `EditorDebuggerPlugin` description for an example.
     *
     * Generated from Godot docs: EngineDebugger.register_message_capture
     */
    @JvmStatic
    fun registerMessageCapture(name: String, callable: GodotCallable) {
        ObjectCalls.ptrcallWithStringNameAndCallableArgs(registerMessageCaptureBind, singleton, name, callable.target.handle, callable.method)
    }

    /**
     * Unregisters the message capture with given `name`.
     *
     * Generated from Godot docs: EngineDebugger.unregister_message_capture
     */
    @JvmStatic
    fun unregisterMessageCapture(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(unregisterMessageCaptureBind, singleton, name)
    }

    /**
     * Returns `true` if a capture with the given name is present otherwise `false`.
     *
     * Generated from Godot docs: EngineDebugger.has_capture
     */
    @JvmStatic
    fun hasCapture(name: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(hasCaptureBind, singleton, name)
    }

    /**
     * Forces a processing loop of debugger events. The purpose of this method is just processing
     * events every now and then when the script might get too busy, so that bugs like infinite loops
     * can be caught.
     *
     * Generated from Godot docs: EngineDebugger.line_poll
     */
    @JvmStatic
    fun linePoll() {
        ObjectCalls.ptrcallNoArgs(linePollBind, singleton)
    }

    /**
     * Sends a message with given `message` and `data` array.
     *
     * Generated from Godot docs: EngineDebugger.send_message
     */
    @JvmStatic
    fun sendMessage(message: String, data: List<Any?>) {
        ObjectCalls.ptrcallWithStringAndArrayArg(sendMessageBind, singleton, message, data)
    }

    @JvmStatic
    /**
     * Starts a debug break in script execution, optionally specifying whether the program can continue
     * based on `can_continue` and whether the break was due to a breakpoint.
     *
     * Generated from Godot docs: EngineDebugger.debug
     */
    fun debug(canContinue: Boolean = true, isErrorBreakpoint: Boolean = false) {
        ObjectCalls.ptrcallWithTwoBoolArgs(debugBind, singleton, canContinue, isErrorBreakpoint)
    }

    /**
     * Starts a debug break in script execution, optionally specifying whether the program can continue
     * based on `can_continue` and whether the break was due to a breakpoint.
     *
     * Generated from Godot docs: EngineDebugger.script_debug
     */
    @JvmStatic
    fun scriptDebug(language: ScriptLanguage, canContinue: Boolean = true, isErrorBreakpoint: Boolean = false) {
        ObjectCalls.ptrcallWithObjectAndTwoBoolArgs(scriptDebugBind, singleton, language.handle, canContinue, isErrorBreakpoint)
    }

    /**
     * Sets the current debugging lines that remain.
     *
     * Generated from Godot docs: EngineDebugger.set_lines_left
     */
    @JvmStatic
    fun setLinesLeft(lines: Int) {
        ObjectCalls.ptrcallWithIntArg(setLinesLeftBind, singleton, lines)
    }

    /**
     * Returns the number of lines that remain.
     *
     * Generated from Godot docs: EngineDebugger.get_lines_left
     */
    @JvmStatic
    fun getLinesLeft(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getLinesLeftBind, singleton)
    }

    /**
     * Sets the current debugging depth.
     *
     * Generated from Godot docs: EngineDebugger.set_depth
     */
    @JvmStatic
    fun setDepth(depth: Int) {
        ObjectCalls.ptrcallWithIntArg(setDepthBind, singleton, depth)
    }

    /**
     * Returns the current debug depth.
     *
     * Generated from Godot docs: EngineDebugger.get_depth
     */
    @JvmStatic
    fun getDepth(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getDepthBind, singleton)
    }

    /**
     * Returns `true` if the given `source` and `line` represent an existing breakpoint.
     *
     * Generated from Godot docs: EngineDebugger.is_breakpoint
     */
    @JvmStatic
    fun isBreakpoint(line: Int, source: String): Boolean {
        return ObjectCalls.ptrcallWithIntAndStringNameArgRetBool(isBreakpointBind, singleton, line, source)
    }

    /**
     * Returns `true` if the debugger is skipping breakpoints otherwise `false`.
     *
     * Generated from Godot docs: EngineDebugger.is_skipping_breakpoints
     */
    @JvmStatic
    fun isSkippingBreakpoints(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSkippingBreakpointsBind, singleton)
    }

    /**
     * Inserts a new breakpoint with the given `source` and `line`.
     *
     * Generated from Godot docs: EngineDebugger.insert_breakpoint
     */
    @JvmStatic
    fun insertBreakpoint(line: Int, source: String) {
        ObjectCalls.ptrcallWithIntAndStringNameArg(insertBreakpointBind, singleton, line, source)
    }

    /**
     * Removes a breakpoint with the given `source` and `line`.
     *
     * Generated from Godot docs: EngineDebugger.remove_breakpoint
     */
    @JvmStatic
    fun removeBreakpoint(line: Int, source: String) {
        ObjectCalls.ptrcallWithIntAndStringNameArg(removeBreakpointBind, singleton, line, source)
    }

    /**
     * Clears all breakpoints.
     *
     * Generated from Godot docs: EngineDebugger.clear_breakpoints
     */
    @JvmStatic
    fun clearBreakpoints() {
        ObjectCalls.ptrcallNoArgs(clearBreakpointsBind, singleton)
    }

    @JvmStatic
    fun fromHandle(handle: MemorySegment): EngineDebugger? =
        wrap(handle)

    internal fun wrap(handle: MemorySegment): EngineDebugger? =
        if (handle.address() == 0L) null else this

    private const val IS_ACTIVE_HASH = 2240911060L
    private val isActiveBind by lazy {
        ObjectCalls.getMethodBind("EngineDebugger", "is_active", IS_ACTIVE_HASH)
    }

    private const val REGISTER_PROFILER_HASH = 3651669560L
    private val registerProfilerBind by lazy {
        ObjectCalls.getMethodBind("EngineDebugger", "register_profiler", REGISTER_PROFILER_HASH)
    }

    private const val UNREGISTER_PROFILER_HASH = 3304788590L
    private val unregisterProfilerBind by lazy {
        ObjectCalls.getMethodBind("EngineDebugger", "unregister_profiler", UNREGISTER_PROFILER_HASH)
    }

    private const val IS_PROFILING_HASH = 2041966384L
    private val isProfilingBind by lazy {
        ObjectCalls.getMethodBind("EngineDebugger", "is_profiling", IS_PROFILING_HASH)
    }

    private const val HAS_PROFILER_HASH = 2041966384L
    private val hasProfilerBind by lazy {
        ObjectCalls.getMethodBind("EngineDebugger", "has_profiler", HAS_PROFILER_HASH)
    }

    private const val PROFILER_ADD_FRAME_DATA_HASH = 1895267858L
    private val profilerAddFrameDataBind by lazy {
        ObjectCalls.getMethodBind("EngineDebugger", "profiler_add_frame_data", PROFILER_ADD_FRAME_DATA_HASH)
    }

    private const val PROFILER_ENABLE_HASH = 3192561009L
    private val profilerEnableBind by lazy {
        ObjectCalls.getMethodBind("EngineDebugger", "profiler_enable", PROFILER_ENABLE_HASH)
    }

    private const val REGISTER_MESSAGE_CAPTURE_HASH = 1874754934L
    private val registerMessageCaptureBind by lazy {
        ObjectCalls.getMethodBind("EngineDebugger", "register_message_capture", REGISTER_MESSAGE_CAPTURE_HASH)
    }

    private const val UNREGISTER_MESSAGE_CAPTURE_HASH = 3304788590L
    private val unregisterMessageCaptureBind by lazy {
        ObjectCalls.getMethodBind("EngineDebugger", "unregister_message_capture", UNREGISTER_MESSAGE_CAPTURE_HASH)
    }

    private const val HAS_CAPTURE_HASH = 2041966384L
    private val hasCaptureBind by lazy {
        ObjectCalls.getMethodBind("EngineDebugger", "has_capture", HAS_CAPTURE_HASH)
    }

    private const val LINE_POLL_HASH = 3218959716L
    private val linePollBind by lazy {
        ObjectCalls.getMethodBind("EngineDebugger", "line_poll", LINE_POLL_HASH)
    }

    private const val SEND_MESSAGE_HASH = 1209351045L
    private val sendMessageBind by lazy {
        ObjectCalls.getMethodBind("EngineDebugger", "send_message", SEND_MESSAGE_HASH)
    }

    private const val DEBUG_HASH = 2751962654L
    private val debugBind by lazy {
        ObjectCalls.getMethodBind("EngineDebugger", "debug", DEBUG_HASH)
    }

    private const val SCRIPT_DEBUG_HASH = 2442343672L
    private val scriptDebugBind by lazy {
        ObjectCalls.getMethodBind("EngineDebugger", "script_debug", SCRIPT_DEBUG_HASH)
    }

    private const val SET_LINES_LEFT_HASH = 1286410249L
    private val setLinesLeftBind by lazy {
        ObjectCalls.getMethodBind("EngineDebugger", "set_lines_left", SET_LINES_LEFT_HASH)
    }

    private const val GET_LINES_LEFT_HASH = 3905245786L
    private val getLinesLeftBind by lazy {
        ObjectCalls.getMethodBind("EngineDebugger", "get_lines_left", GET_LINES_LEFT_HASH)
    }

    private const val SET_DEPTH_HASH = 1286410249L
    private val setDepthBind by lazy {
        ObjectCalls.getMethodBind("EngineDebugger", "set_depth", SET_DEPTH_HASH)
    }

    private const val GET_DEPTH_HASH = 3905245786L
    private val getDepthBind by lazy {
        ObjectCalls.getMethodBind("EngineDebugger", "get_depth", GET_DEPTH_HASH)
    }

    private const val IS_BREAKPOINT_HASH = 921227809L
    private val isBreakpointBind by lazy {
        ObjectCalls.getMethodBind("EngineDebugger", "is_breakpoint", IS_BREAKPOINT_HASH)
    }

    private const val IS_SKIPPING_BREAKPOINTS_HASH = 36873697L
    private val isSkippingBreakpointsBind by lazy {
        ObjectCalls.getMethodBind("EngineDebugger", "is_skipping_breakpoints", IS_SKIPPING_BREAKPOINTS_HASH)
    }

    private const val INSERT_BREAKPOINT_HASH = 3780747571L
    private val insertBreakpointBind by lazy {
        ObjectCalls.getMethodBind("EngineDebugger", "insert_breakpoint", INSERT_BREAKPOINT_HASH)
    }

    private const val REMOVE_BREAKPOINT_HASH = 3780747571L
    private val removeBreakpointBind by lazy {
        ObjectCalls.getMethodBind("EngineDebugger", "remove_breakpoint", REMOVE_BREAKPOINT_HASH)
    }

    private const val CLEAR_BREAKPOINTS_HASH = 3218959716L
    private val clearBreakpointsBind by lazy {
        ObjectCalls.getMethodBind("EngineDebugger", "clear_breakpoints", CLEAR_BREAKPOINTS_HASH)
    }
}
