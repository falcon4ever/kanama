package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Provides access to engine properties.
 *
 * Generated from Godot docs: Engine
 */
object Engine {
    private val singleton: MemorySegment by lazy {
        ObjectCalls.getSingleton("Engine")
    }

    var printErrorMessages: Boolean
        @JvmName("printErrorMessagesProperty")
        get() = isPrintingErrorMessages()
        @JvmName("setPrintErrorMessagesProperty")
        set(value) = setPrintErrorMessages(value)

    var printToStdout: Boolean
        @JvmName("printToStdoutProperty")
        get() = isPrintingToStdout()
        @JvmName("setPrintToStdoutProperty")
        set(value) = setPrintToStdout(value)

    var physicsTicksPerSecond: Int
        @JvmName("physicsTicksPerSecondProperty")
        get() = getPhysicsTicksPerSecond()
        @JvmName("setPhysicsTicksPerSecondProperty")
        set(value) = setPhysicsTicksPerSecond(value)

    var maxPhysicsStepsPerFrame: Int
        @JvmName("maxPhysicsStepsPerFrameProperty")
        get() = getMaxPhysicsStepsPerFrame()
        @JvmName("setMaxPhysicsStepsPerFrameProperty")
        set(value) = setMaxPhysicsStepsPerFrame(value)

    var maxFps: Int
        @JvmName("maxFpsProperty")
        get() = getMaxFps()
        @JvmName("setMaxFpsProperty")
        set(value) = setMaxFps(value)

    var timeScale: Double
        @JvmName("timeScaleProperty")
        get() = getTimeScale()
        @JvmName("setTimeScaleProperty")
        set(value) = setTimeScale(value)

    var physicsJitterFix: Double
        @JvmName("physicsJitterFixProperty")
        get() = getPhysicsJitterFix()
        @JvmName("setPhysicsJitterFixProperty")
        set(value) = setPhysicsJitterFix(value)

    @JvmStatic
    fun setPhysicsTicksPerSecond(physicsTicksPerSecond: Int) {
        ObjectCalls.ptrcallWithIntArg(setPhysicsTicksPerSecondBind, singleton, physicsTicksPerSecond)
    }

    @JvmStatic
    fun getPhysicsTicksPerSecond(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getPhysicsTicksPerSecondBind, singleton)
    }

    @JvmStatic
    fun setMaxPhysicsStepsPerFrame(maxPhysicsSteps: Int) {
        ObjectCalls.ptrcallWithIntArg(setMaxPhysicsStepsPerFrameBind, singleton, maxPhysicsSteps)
    }

    @JvmStatic
    fun getMaxPhysicsStepsPerFrame(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMaxPhysicsStepsPerFrameBind, singleton)
    }

    @JvmStatic
    fun setPhysicsJitterFix(physicsJitterFix: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPhysicsJitterFixBind, singleton, physicsJitterFix)
    }

    @JvmStatic
    fun getPhysicsJitterFix(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPhysicsJitterFixBind, singleton)
    }

    @JvmStatic
    fun getPhysicsInterpolationFraction(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPhysicsInterpolationFractionBind, singleton)
    }

    @JvmStatic
    fun setMaxFps(maxFps: Int) {
        ObjectCalls.ptrcallWithIntArg(setMaxFpsBind, singleton, maxFps)
    }

    @JvmStatic
    fun getMaxFps(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMaxFpsBind, singleton)
    }

    @JvmStatic
    fun setTimeScale(timeScale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTimeScaleBind, singleton, timeScale)
    }

    @JvmStatic
    fun getTimeScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTimeScaleBind, singleton)
    }

    @JvmStatic
    fun getFramesDrawn(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getFramesDrawnBind, singleton)
    }

    @JvmStatic
    fun getFramesPerSecond(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFramesPerSecondBind, singleton)
    }

    @JvmStatic
    fun getPhysicsFrames(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getPhysicsFramesBind, singleton)
    }

    @JvmStatic
    fun getProcessFrames(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getProcessFramesBind, singleton)
    }

    @JvmStatic
    fun getMainLoop(): MainLoop? {
        return MainLoop.wrap(ObjectCalls.ptrcallNoArgsRetObject(getMainLoopBind, singleton))
    }

    @JvmStatic
    fun getVersionInfo(): Map<String, Any?> {
        return ObjectCalls.ptrcallNoArgsRetDictionary(getVersionInfoBind, singleton)
    }

    @JvmStatic
    fun getAuthorInfo(): Map<String, Any?> {
        return ObjectCalls.ptrcallNoArgsRetDictionary(getAuthorInfoBind, singleton)
    }

    @JvmStatic
    fun getCopyrightInfo(): List<Map<String, Any?>> {
        return ObjectCalls.ptrcallNoArgsRetDictionaryList(getCopyrightInfoBind, singleton)
    }

    @JvmStatic
    fun getDonorInfo(): Map<String, Any?> {
        return ObjectCalls.ptrcallNoArgsRetDictionary(getDonorInfoBind, singleton)
    }

    @JvmStatic
    fun getLicenseInfo(): Map<String, Any?> {
        return ObjectCalls.ptrcallNoArgsRetDictionary(getLicenseInfoBind, singleton)
    }

    @JvmStatic
    fun getLicenseText(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getLicenseTextBind, singleton)
    }

    @JvmStatic
    fun getArchitectureName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getArchitectureNameBind, singleton)
    }

    @JvmStatic
    fun isInPhysicsFrame(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isInPhysicsFrameBind, singleton)
    }

    @JvmStatic
    fun hasSingleton(name: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(hasSingletonBind, singleton, name)
    }

    @JvmStatic
    fun getSingleton(name: String): GodotObject? {
        return GodotObject.wrap(ObjectCalls.ptrcallWithStringNameArgRetObject(getSingletonBind, singleton, name))
    }

    @JvmStatic
    fun registerSingleton(name: String, instance: GodotObject) {
        ObjectCalls.ptrcallWithStringNameAndObjectArg(registerSingletonBind, singleton, name, instance.handle)
    }

    @JvmStatic
    fun unregisterSingleton(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(unregisterSingletonBind, singleton, name)
    }

    @JvmStatic
    fun getSingletonList(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getSingletonListBind, singleton)
    }

    @JvmStatic
    fun registerScriptLanguage(language: ScriptLanguage): Long {
        return ObjectCalls.ptrcallWithObjectArgRetLong(registerScriptLanguageBind, singleton, language.handle)
    }

    @JvmStatic
    fun unregisterScriptLanguage(language: ScriptLanguage): Long {
        return ObjectCalls.ptrcallWithObjectArgRetLong(unregisterScriptLanguageBind, singleton, language.handle)
    }

    @JvmStatic
    fun getScriptLanguageCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getScriptLanguageCountBind, singleton)
    }

    @JvmStatic
    fun getScriptLanguage(index: Int): ScriptLanguage? {
        return ScriptLanguage.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getScriptLanguageBind, singleton, index))
    }

    @JvmStatic
    fun captureScriptBacktraces(includeVariables: Boolean = false): List<ScriptBacktrace> {
        return ObjectCalls.ptrcallWithBoolArgRetTypedObjectList(captureScriptBacktracesBind, singleton, includeVariables, ScriptBacktrace::fromHandle)
    }

    @JvmStatic
    fun isEditorHint(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isEditorHintBind, singleton)
    }

    @JvmStatic
    fun isEmbeddedInEditor(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isEmbeddedInEditorBind, singleton)
    }

    @JvmStatic
    fun getWriteMoviePath(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getWriteMoviePathBind, singleton)
    }

    @JvmStatic
    fun setPrintToStdout(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPrintToStdoutBind, singleton, enabled)
    }

    @JvmStatic
    fun isPrintingToStdout(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isPrintingToStdoutBind, singleton)
    }

    @JvmStatic
    fun setPrintErrorMessages(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPrintErrorMessagesBind, singleton, enabled)
    }

    @JvmStatic
    fun isPrintingErrorMessages(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isPrintingErrorMessagesBind, singleton)
    }

    @JvmStatic
    fun fromHandle(handle: MemorySegment): Engine? =
        wrap(handle)

    internal fun wrap(handle: MemorySegment): Engine? =
        if (handle.address() == 0L) null else this

    private const val SET_PHYSICS_TICKS_PER_SECOND_HASH = 1286410249L
    private val setPhysicsTicksPerSecondBind by lazy {
        ObjectCalls.getMethodBind("Engine", "set_physics_ticks_per_second", SET_PHYSICS_TICKS_PER_SECOND_HASH)
    }

    private const val GET_PHYSICS_TICKS_PER_SECOND_HASH = 3905245786L
    private val getPhysicsTicksPerSecondBind by lazy {
        ObjectCalls.getMethodBind("Engine", "get_physics_ticks_per_second", GET_PHYSICS_TICKS_PER_SECOND_HASH)
    }

    private const val SET_MAX_PHYSICS_STEPS_PER_FRAME_HASH = 1286410249L
    private val setMaxPhysicsStepsPerFrameBind by lazy {
        ObjectCalls.getMethodBind("Engine", "set_max_physics_steps_per_frame", SET_MAX_PHYSICS_STEPS_PER_FRAME_HASH)
    }

    private const val GET_MAX_PHYSICS_STEPS_PER_FRAME_HASH = 3905245786L
    private val getMaxPhysicsStepsPerFrameBind by lazy {
        ObjectCalls.getMethodBind("Engine", "get_max_physics_steps_per_frame", GET_MAX_PHYSICS_STEPS_PER_FRAME_HASH)
    }

    private const val SET_PHYSICS_JITTER_FIX_HASH = 373806689L
    private val setPhysicsJitterFixBind by lazy {
        ObjectCalls.getMethodBind("Engine", "set_physics_jitter_fix", SET_PHYSICS_JITTER_FIX_HASH)
    }

    private const val GET_PHYSICS_JITTER_FIX_HASH = 1740695150L
    private val getPhysicsJitterFixBind by lazy {
        ObjectCalls.getMethodBind("Engine", "get_physics_jitter_fix", GET_PHYSICS_JITTER_FIX_HASH)
    }

    private const val GET_PHYSICS_INTERPOLATION_FRACTION_HASH = 1740695150L
    private val getPhysicsInterpolationFractionBind by lazy {
        ObjectCalls.getMethodBind("Engine", "get_physics_interpolation_fraction", GET_PHYSICS_INTERPOLATION_FRACTION_HASH)
    }

    private const val SET_MAX_FPS_HASH = 1286410249L
    private val setMaxFpsBind by lazy {
        ObjectCalls.getMethodBind("Engine", "set_max_fps", SET_MAX_FPS_HASH)
    }

    private const val GET_MAX_FPS_HASH = 3905245786L
    private val getMaxFpsBind by lazy {
        ObjectCalls.getMethodBind("Engine", "get_max_fps", GET_MAX_FPS_HASH)
    }

    private const val SET_TIME_SCALE_HASH = 373806689L
    private val setTimeScaleBind by lazy {
        ObjectCalls.getMethodBind("Engine", "set_time_scale", SET_TIME_SCALE_HASH)
    }

    private const val GET_TIME_SCALE_HASH = 191475506L
    private val getTimeScaleBind by lazy {
        ObjectCalls.getMethodBind("Engine", "get_time_scale", GET_TIME_SCALE_HASH)
    }

    private const val GET_FRAMES_DRAWN_HASH = 2455072627L
    private val getFramesDrawnBind by lazy {
        ObjectCalls.getMethodBind("Engine", "get_frames_drawn", GET_FRAMES_DRAWN_HASH)
    }

    private const val GET_FRAMES_PER_SECOND_HASH = 1740695150L
    private val getFramesPerSecondBind by lazy {
        ObjectCalls.getMethodBind("Engine", "get_frames_per_second", GET_FRAMES_PER_SECOND_HASH)
    }

    private const val GET_PHYSICS_FRAMES_HASH = 3905245786L
    private val getPhysicsFramesBind by lazy {
        ObjectCalls.getMethodBind("Engine", "get_physics_frames", GET_PHYSICS_FRAMES_HASH)
    }

    private const val GET_PROCESS_FRAMES_HASH = 3905245786L
    private val getProcessFramesBind by lazy {
        ObjectCalls.getMethodBind("Engine", "get_process_frames", GET_PROCESS_FRAMES_HASH)
    }

    private const val GET_MAIN_LOOP_HASH = 1016888095L
    private val getMainLoopBind by lazy {
        ObjectCalls.getMethodBind("Engine", "get_main_loop", GET_MAIN_LOOP_HASH)
    }

    private const val GET_VERSION_INFO_HASH = 3102165223L
    private val getVersionInfoBind by lazy {
        ObjectCalls.getMethodBind("Engine", "get_version_info", GET_VERSION_INFO_HASH)
    }

    private const val GET_AUTHOR_INFO_HASH = 3102165223L
    private val getAuthorInfoBind by lazy {
        ObjectCalls.getMethodBind("Engine", "get_author_info", GET_AUTHOR_INFO_HASH)
    }

    private const val GET_COPYRIGHT_INFO_HASH = 3995934104L
    private val getCopyrightInfoBind by lazy {
        ObjectCalls.getMethodBind("Engine", "get_copyright_info", GET_COPYRIGHT_INFO_HASH)
    }

    private const val GET_DONOR_INFO_HASH = 3102165223L
    private val getDonorInfoBind by lazy {
        ObjectCalls.getMethodBind("Engine", "get_donor_info", GET_DONOR_INFO_HASH)
    }

    private const val GET_LICENSE_INFO_HASH = 3102165223L
    private val getLicenseInfoBind by lazy {
        ObjectCalls.getMethodBind("Engine", "get_license_info", GET_LICENSE_INFO_HASH)
    }

    private const val GET_LICENSE_TEXT_HASH = 201670096L
    private val getLicenseTextBind by lazy {
        ObjectCalls.getMethodBind("Engine", "get_license_text", GET_LICENSE_TEXT_HASH)
    }

    private const val GET_ARCHITECTURE_NAME_HASH = 201670096L
    private val getArchitectureNameBind by lazy {
        ObjectCalls.getMethodBind("Engine", "get_architecture_name", GET_ARCHITECTURE_NAME_HASH)
    }

    private const val IS_IN_PHYSICS_FRAME_HASH = 36873697L
    private val isInPhysicsFrameBind by lazy {
        ObjectCalls.getMethodBind("Engine", "is_in_physics_frame", IS_IN_PHYSICS_FRAME_HASH)
    }

    private const val HAS_SINGLETON_HASH = 2619796661L
    private val hasSingletonBind by lazy {
        ObjectCalls.getMethodBind("Engine", "has_singleton", HAS_SINGLETON_HASH)
    }

    private const val GET_SINGLETON_HASH = 1371597918L
    private val getSingletonBind by lazy {
        ObjectCalls.getMethodBind("Engine", "get_singleton", GET_SINGLETON_HASH)
    }

    private const val REGISTER_SINGLETON_HASH = 965313290L
    private val registerSingletonBind by lazy {
        ObjectCalls.getMethodBind("Engine", "register_singleton", REGISTER_SINGLETON_HASH)
    }

    private const val UNREGISTER_SINGLETON_HASH = 3304788590L
    private val unregisterSingletonBind by lazy {
        ObjectCalls.getMethodBind("Engine", "unregister_singleton", UNREGISTER_SINGLETON_HASH)
    }

    private const val GET_SINGLETON_LIST_HASH = 1139954409L
    private val getSingletonListBind by lazy {
        ObjectCalls.getMethodBind("Engine", "get_singleton_list", GET_SINGLETON_LIST_HASH)
    }

    private const val REGISTER_SCRIPT_LANGUAGE_HASH = 1850254898L
    private val registerScriptLanguageBind by lazy {
        ObjectCalls.getMethodBind("Engine", "register_script_language", REGISTER_SCRIPT_LANGUAGE_HASH)
    }

    private const val UNREGISTER_SCRIPT_LANGUAGE_HASH = 1850254898L
    private val unregisterScriptLanguageBind by lazy {
        ObjectCalls.getMethodBind("Engine", "unregister_script_language", UNREGISTER_SCRIPT_LANGUAGE_HASH)
    }

    private const val GET_SCRIPT_LANGUAGE_COUNT_HASH = 2455072627L
    private val getScriptLanguageCountBind by lazy {
        ObjectCalls.getMethodBind("Engine", "get_script_language_count", GET_SCRIPT_LANGUAGE_COUNT_HASH)
    }

    private const val GET_SCRIPT_LANGUAGE_HASH = 2151255799L
    private val getScriptLanguageBind by lazy {
        ObjectCalls.getMethodBind("Engine", "get_script_language", GET_SCRIPT_LANGUAGE_HASH)
    }

    private const val CAPTURE_SCRIPT_BACKTRACES_HASH = 873284517L
    private val captureScriptBacktracesBind by lazy {
        ObjectCalls.getMethodBind("Engine", "capture_script_backtraces", CAPTURE_SCRIPT_BACKTRACES_HASH)
    }

    private const val IS_EDITOR_HINT_HASH = 36873697L
    private val isEditorHintBind by lazy {
        ObjectCalls.getMethodBind("Engine", "is_editor_hint", IS_EDITOR_HINT_HASH)
    }

    private const val IS_EMBEDDED_IN_EDITOR_HASH = 36873697L
    private val isEmbeddedInEditorBind by lazy {
        ObjectCalls.getMethodBind("Engine", "is_embedded_in_editor", IS_EMBEDDED_IN_EDITOR_HASH)
    }

    private const val GET_WRITE_MOVIE_PATH_HASH = 201670096L
    private val getWriteMoviePathBind by lazy {
        ObjectCalls.getMethodBind("Engine", "get_write_movie_path", GET_WRITE_MOVIE_PATH_HASH)
    }

    private const val SET_PRINT_TO_STDOUT_HASH = 2586408642L
    private val setPrintToStdoutBind by lazy {
        ObjectCalls.getMethodBind("Engine", "set_print_to_stdout", SET_PRINT_TO_STDOUT_HASH)
    }

    private const val IS_PRINTING_TO_STDOUT_HASH = 36873697L
    private val isPrintingToStdoutBind by lazy {
        ObjectCalls.getMethodBind("Engine", "is_printing_to_stdout", IS_PRINTING_TO_STDOUT_HASH)
    }

    private const val SET_PRINT_ERROR_MESSAGES_HASH = 2586408642L
    private val setPrintErrorMessagesBind by lazy {
        ObjectCalls.getMethodBind("Engine", "set_print_error_messages", SET_PRINT_ERROR_MESSAGES_HASH)
    }

    private const val IS_PRINTING_ERROR_MESSAGES_HASH = 36873697L
    private val isPrintingErrorMessagesBind by lazy {
        ObjectCalls.getMethodBind("Engine", "is_printing_error_messages", IS_PRINTING_ERROR_MESSAGES_HASH)
    }
}
