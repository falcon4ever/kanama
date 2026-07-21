package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: OS
 */
object OS {
    private val singleton: MemorySegment by lazy {
        ObjectCalls.getSingleton("OS")
    }

    const val RENDERING_DRIVER_VULKAN: Long = 0L
    const val RENDERING_DRIVER_OPENGL3: Long = 1L
    const val RENDERING_DRIVER_D3D12: Long = 2L
    const val RENDERING_DRIVER_METAL: Long = 3L
    const val SYSTEM_DIR_DESKTOP: Long = 0L
    const val SYSTEM_DIR_DCIM: Long = 1L
    const val SYSTEM_DIR_DOCUMENTS: Long = 2L
    const val SYSTEM_DIR_DOWNLOADS: Long = 3L
    const val SYSTEM_DIR_MOVIES: Long = 4L
    const val SYSTEM_DIR_MUSIC: Long = 5L
    const val SYSTEM_DIR_PICTURES: Long = 6L
    const val SYSTEM_DIR_RINGTONES: Long = 7L
    const val STD_HANDLE_INVALID: Long = 0L
    const val STD_HANDLE_CONSOLE: Long = 1L
    const val STD_HANDLE_FILE: Long = 2L
    const val STD_HANDLE_PIPE: Long = 3L
    const val STD_HANDLE_UNKNOWN: Long = 4L

    var lowProcessorUsageMode: Boolean
        @JvmName("lowProcessorUsageModeProperty")
        get() = isInLowProcessorUsageMode()
        @JvmName("setLowProcessorUsageModeProperty")
        set(value) = setLowProcessorUsageMode(value)

    var lowProcessorUsageModeSleepUsec: Int
        @JvmName("lowProcessorUsageModeSleepUsecProperty")
        get() = getLowProcessorUsageModeSleepUsec()
        @JvmName("setLowProcessorUsageModeSleepUsecProperty")
        set(value) = setLowProcessorUsageModeSleepUsec(value)

    var deltaSmoothing: Boolean
        @JvmName("deltaSmoothingProperty")
        get() = isDeltaSmoothingEnabled()
        @JvmName("setDeltaSmoothingProperty")
        set(value) = setDeltaSmoothing(value)

    fun getSystemCaCertificates(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getSystemCaCertificatesBind, singleton)
    }

    fun getConnectedMidiInputs(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getConnectedMidiInputsBind, singleton)
    }

    fun openMidiInputs() {
        ObjectCalls.ptrcallNoArgs(openMidiInputsBind, singleton)
    }

    fun closeMidiInputs() {
        ObjectCalls.ptrcallNoArgs(closeMidiInputsBind, singleton)
    }

    fun alert(text: String, title: String = "Alert!") {
        ObjectCalls.ptrcallWithTwoStringArgs(alertBind, singleton, text, title)
    }

    fun crash(message: String) {
        ObjectCalls.ptrcallWithStringArg(crashBind, singleton, message)
    }

    fun setLowProcessorUsageMode(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setLowProcessorUsageModeBind, singleton, enable)
    }

    fun isInLowProcessorUsageMode(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isInLowProcessorUsageModeBind, singleton)
    }

    fun setLowProcessorUsageModeSleepUsec(usec: Int) {
        ObjectCalls.ptrcallWithIntArg(setLowProcessorUsageModeSleepUsecBind, singleton, usec)
    }

    fun getLowProcessorUsageModeSleepUsec(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getLowProcessorUsageModeSleepUsecBind, singleton)
    }

    fun setDeltaSmoothing(deltaSmoothingEnabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDeltaSmoothingBind, singleton, deltaSmoothingEnabled)
    }

    fun isDeltaSmoothingEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDeltaSmoothingEnabledBind, singleton)
    }

    fun getProcessorCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getProcessorCountBind, singleton)
    }

    fun getProcessorName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getProcessorNameBind, singleton)
    }

    fun getSystemFonts(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getSystemFontsBind, singleton)
    }

    fun getExecutablePath(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getExecutablePathBind, singleton)
    }

    fun getStdinType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getStdinTypeBind, singleton)
    }

    fun getStdoutType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getStdoutTypeBind, singleton)
    }

    fun getStderrType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getStderrTypeBind, singleton)
    }

    fun kill(pid: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(killBind, singleton, pid)
    }

    fun shellOpen(uri: String): Long {
        return ObjectCalls.ptrcallWithStringArgRetLong(shellOpenBind, singleton, uri)
    }

    fun shellShowInFileManager(fileOrDirPath: String, openFolder: Boolean = true): Long {
        return ObjectCalls.ptrcallWithStringAndBoolArgRetLong(shellShowInFileManagerBind, singleton, fileOrDirPath, openFolder)
    }

    fun isProcessRunning(pid: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isProcessRunningBind, singleton, pid)
    }

    fun getProcessExitCode(pid: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getProcessExitCodeBind, singleton, pid)
    }

    fun getProcessId(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getProcessIdBind, singleton)
    }

    fun hasEnvironment(variable: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(hasEnvironmentBind, singleton, variable)
    }

    fun getEnvironment(variable: String): String {
        return ObjectCalls.ptrcallWithStringArgRetString(getEnvironmentBind, singleton, variable)
    }

    fun setEnvironment(variable: String, value: String) {
        ObjectCalls.ptrcallWithTwoStringArgs(setEnvironmentBind, singleton, variable, value)
    }

    fun unsetEnvironment(variable: String) {
        ObjectCalls.ptrcallWithStringArg(unsetEnvironmentBind, singleton, variable)
    }

    fun getName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getNameBind, singleton)
    }

    fun getDistributionName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getDistributionNameBind, singleton)
    }

    fun getVersion(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getVersionBind, singleton)
    }

    fun getVersionAlias(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getVersionAliasBind, singleton)
    }

    fun getCmdlineArgs(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getCmdlineArgsBind, singleton)
    }

    fun getCmdlineUserArgs(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getCmdlineUserArgsBind, singleton)
    }

    fun getVideoAdapterDriverInfo(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getVideoAdapterDriverInfoBind, singleton)
    }

    fun isRestartOnExitSet(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isRestartOnExitSetBind, singleton)
    }

    fun getRestartOnExitArguments(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getRestartOnExitArgumentsBind, singleton)
    }

    fun delayUsec(usec: Int) {
        ObjectCalls.ptrcallWithIntArg(delayUsecBind, singleton, usec)
    }

    fun delayMsec(msec: Int) {
        ObjectCalls.ptrcallWithIntArg(delayMsecBind, singleton, msec)
    }

    fun getLocale(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getLocaleBind, singleton)
    }

    fun getLocaleLanguage(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getLocaleLanguageBind, singleton)
    }

    fun getModelName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getModelNameBind, singleton)
    }

    fun isUserfsPersistent(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUserfsPersistentBind, singleton)
    }

    fun isStdoutVerbose(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isStdoutVerboseBind, singleton)
    }

    fun isDebugBuild(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDebugBuildBind, singleton)
    }

    fun getStaticMemoryUsage(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getStaticMemoryUsageBind, singleton)
    }

    fun getStaticMemoryPeakUsage(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getStaticMemoryPeakUsageBind, singleton)
    }

    fun moveToTrash(path: String): Long {
        return ObjectCalls.ptrcallWithStringArgRetLong(moveToTrashBind, singleton, path)
    }

    fun getUserDataDir(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getUserDataDirBind, singleton)
    }

    fun getConfigDir(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getConfigDirBind, singleton)
    }

    fun getDataDir(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getDataDirBind, singleton)
    }

    fun getCacheDir(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getCacheDirBind, singleton)
    }

    fun getTempDir(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getTempDirBind, singleton)
    }

    fun getUniqueId(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getUniqueIdBind, singleton)
    }

    fun isKeycodeUnicode(code: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isKeycodeUnicodeBind, singleton, code)
    }

    fun findKeycodeFromString(string: String): Long {
        return ObjectCalls.ptrcallWithStringArgRetLong(findKeycodeFromStringBind, singleton, string)
    }

    fun setUseFileAccessSaveAndSwap(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseFileAccessSaveAndSwapBind, singleton, enabled)
    }

    fun setThreadName(name: String): Long {
        return ObjectCalls.ptrcallWithStringArgRetLong(setThreadNameBind, singleton, name)
    }

    fun getThreadCallerId(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getThreadCallerIdBind, singleton)
    }

    fun getMainThreadId(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getMainThreadIdBind, singleton)
    }

    fun hasFeature(tagName: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(hasFeatureBind, singleton, tagName)
    }

    fun isSandboxed(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSandboxedBind, singleton)
    }

    fun requestPermission(name: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(requestPermissionBind, singleton, name)
    }

    fun requestPermissions(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(requestPermissionsBind, singleton)
    }

    fun getGrantedPermissions(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getGrantedPermissionsBind, singleton)
    }

    fun revokeGrantedPermissions() {
        ObjectCalls.ptrcallNoArgs(revokeGrantedPermissionsBind, singleton)
    }

    fun addLogger(logger: Logger) {
        ObjectCalls.ptrcallWithObjectArgs(addLoggerBind, singleton, listOf(logger.requireOpenHandle()))
    }

    fun removeLogger(logger: Logger) {
        ObjectCalls.ptrcallWithObjectArgs(removeLoggerBind, singleton, listOf(logger.requireOpenHandle()))
    }

    fun fromHandle(handle: MemorySegment): OS? =
        wrap(handle)

    internal fun wrap(handle: MemorySegment): OS? =
        if (handle.address() == 0L) null else this

    private const val GET_SYSTEM_CA_CERTIFICATES_HASH = 2841200299L
    private val getSystemCaCertificatesBind by lazy {
        ObjectCalls.getMethodBind("OS", "get_system_ca_certificates", GET_SYSTEM_CA_CERTIFICATES_HASH)
    }

    private const val GET_CONNECTED_MIDI_INPUTS_HASH = 2981934095L
    private val getConnectedMidiInputsBind by lazy {
        ObjectCalls.getMethodBind("OS", "get_connected_midi_inputs", GET_CONNECTED_MIDI_INPUTS_HASH)
    }

    private const val OPEN_MIDI_INPUTS_HASH = 3218959716L
    private val openMidiInputsBind by lazy {
        ObjectCalls.getMethodBind("OS", "open_midi_inputs", OPEN_MIDI_INPUTS_HASH)
    }

    private const val CLOSE_MIDI_INPUTS_HASH = 3218959716L
    private val closeMidiInputsBind by lazy {
        ObjectCalls.getMethodBind("OS", "close_midi_inputs", CLOSE_MIDI_INPUTS_HASH)
    }

    private const val ALERT_HASH = 1783970740L
    private val alertBind by lazy {
        ObjectCalls.getMethodBind("OS", "alert", ALERT_HASH)
    }

    private const val CRASH_HASH = 83702148L
    private val crashBind by lazy {
        ObjectCalls.getMethodBind("OS", "crash", CRASH_HASH)
    }

    private const val SET_LOW_PROCESSOR_USAGE_MODE_HASH = 2586408642L
    private val setLowProcessorUsageModeBind by lazy {
        ObjectCalls.getMethodBind("OS", "set_low_processor_usage_mode", SET_LOW_PROCESSOR_USAGE_MODE_HASH)
    }

    private const val IS_IN_LOW_PROCESSOR_USAGE_MODE_HASH = 36873697L
    private val isInLowProcessorUsageModeBind by lazy {
        ObjectCalls.getMethodBind("OS", "is_in_low_processor_usage_mode", IS_IN_LOW_PROCESSOR_USAGE_MODE_HASH)
    }

    private const val SET_LOW_PROCESSOR_USAGE_MODE_SLEEP_USEC_HASH = 1286410249L
    private val setLowProcessorUsageModeSleepUsecBind by lazy {
        ObjectCalls.getMethodBind("OS", "set_low_processor_usage_mode_sleep_usec", SET_LOW_PROCESSOR_USAGE_MODE_SLEEP_USEC_HASH)
    }

    private const val GET_LOW_PROCESSOR_USAGE_MODE_SLEEP_USEC_HASH = 3905245786L
    private val getLowProcessorUsageModeSleepUsecBind by lazy {
        ObjectCalls.getMethodBind("OS", "get_low_processor_usage_mode_sleep_usec", GET_LOW_PROCESSOR_USAGE_MODE_SLEEP_USEC_HASH)
    }

    private const val SET_DELTA_SMOOTHING_HASH = 2586408642L
    private val setDeltaSmoothingBind by lazy {
        ObjectCalls.getMethodBind("OS", "set_delta_smoothing", SET_DELTA_SMOOTHING_HASH)
    }

    private const val IS_DELTA_SMOOTHING_ENABLED_HASH = 36873697L
    private val isDeltaSmoothingEnabledBind by lazy {
        ObjectCalls.getMethodBind("OS", "is_delta_smoothing_enabled", IS_DELTA_SMOOTHING_ENABLED_HASH)
    }

    private const val GET_PROCESSOR_COUNT_HASH = 3905245786L
    private val getProcessorCountBind by lazy {
        ObjectCalls.getMethodBind("OS", "get_processor_count", GET_PROCESSOR_COUNT_HASH)
    }

    private const val GET_PROCESSOR_NAME_HASH = 201670096L
    private val getProcessorNameBind by lazy {
        ObjectCalls.getMethodBind("OS", "get_processor_name", GET_PROCESSOR_NAME_HASH)
    }

    private const val GET_SYSTEM_FONTS_HASH = 1139954409L
    private val getSystemFontsBind by lazy {
        ObjectCalls.getMethodBind("OS", "get_system_fonts", GET_SYSTEM_FONTS_HASH)
    }

    private const val GET_EXECUTABLE_PATH_HASH = 201670096L
    private val getExecutablePathBind by lazy {
        ObjectCalls.getMethodBind("OS", "get_executable_path", GET_EXECUTABLE_PATH_HASH)
    }

    private const val GET_STDIN_TYPE_HASH = 1704816237L
    private val getStdinTypeBind by lazy {
        ObjectCalls.getMethodBind("OS", "get_stdin_type", GET_STDIN_TYPE_HASH)
    }

    private const val GET_STDOUT_TYPE_HASH = 1704816237L
    private val getStdoutTypeBind by lazy {
        ObjectCalls.getMethodBind("OS", "get_stdout_type", GET_STDOUT_TYPE_HASH)
    }

    private const val GET_STDERR_TYPE_HASH = 1704816237L
    private val getStderrTypeBind by lazy {
        ObjectCalls.getMethodBind("OS", "get_stderr_type", GET_STDERR_TYPE_HASH)
    }

    private const val KILL_HASH = 844576869L
    private val killBind by lazy {
        ObjectCalls.getMethodBind("OS", "kill", KILL_HASH)
    }

    private const val SHELL_OPEN_HASH = 166001499L
    private val shellOpenBind by lazy {
        ObjectCalls.getMethodBind("OS", "shell_open", SHELL_OPEN_HASH)
    }

    private const val SHELL_SHOW_IN_FILE_MANAGER_HASH = 3565188097L
    private val shellShowInFileManagerBind by lazy {
        ObjectCalls.getMethodBind("OS", "shell_show_in_file_manager", SHELL_SHOW_IN_FILE_MANAGER_HASH)
    }

    private const val IS_PROCESS_RUNNING_HASH = 1116898809L
    private val isProcessRunningBind by lazy {
        ObjectCalls.getMethodBind("OS", "is_process_running", IS_PROCESS_RUNNING_HASH)
    }

    private const val GET_PROCESS_EXIT_CODE_HASH = 923996154L
    private val getProcessExitCodeBind by lazy {
        ObjectCalls.getMethodBind("OS", "get_process_exit_code", GET_PROCESS_EXIT_CODE_HASH)
    }

    private const val GET_PROCESS_ID_HASH = 3905245786L
    private val getProcessIdBind by lazy {
        ObjectCalls.getMethodBind("OS", "get_process_id", GET_PROCESS_ID_HASH)
    }

    private const val HAS_ENVIRONMENT_HASH = 3927539163L
    private val hasEnvironmentBind by lazy {
        ObjectCalls.getMethodBind("OS", "has_environment", HAS_ENVIRONMENT_HASH)
    }

    private const val GET_ENVIRONMENT_HASH = 3135753539L
    private val getEnvironmentBind by lazy {
        ObjectCalls.getMethodBind("OS", "get_environment", GET_ENVIRONMENT_HASH)
    }

    private const val SET_ENVIRONMENT_HASH = 3605043004L
    private val setEnvironmentBind by lazy {
        ObjectCalls.getMethodBind("OS", "set_environment", SET_ENVIRONMENT_HASH)
    }

    private const val UNSET_ENVIRONMENT_HASH = 3089850668L
    private val unsetEnvironmentBind by lazy {
        ObjectCalls.getMethodBind("OS", "unset_environment", UNSET_ENVIRONMENT_HASH)
    }

    private const val GET_NAME_HASH = 201670096L
    private val getNameBind by lazy {
        ObjectCalls.getMethodBind("OS", "get_name", GET_NAME_HASH)
    }

    private const val GET_DISTRIBUTION_NAME_HASH = 201670096L
    private val getDistributionNameBind by lazy {
        ObjectCalls.getMethodBind("OS", "get_distribution_name", GET_DISTRIBUTION_NAME_HASH)
    }

    private const val GET_VERSION_HASH = 201670096L
    private val getVersionBind by lazy {
        ObjectCalls.getMethodBind("OS", "get_version", GET_VERSION_HASH)
    }

    private const val GET_VERSION_ALIAS_HASH = 201670096L
    private val getVersionAliasBind by lazy {
        ObjectCalls.getMethodBind("OS", "get_version_alias", GET_VERSION_ALIAS_HASH)
    }

    private const val GET_CMDLINE_ARGS_HASH = 2981934095L
    private val getCmdlineArgsBind by lazy {
        ObjectCalls.getMethodBind("OS", "get_cmdline_args", GET_CMDLINE_ARGS_HASH)
    }

    private const val GET_CMDLINE_USER_ARGS_HASH = 2981934095L
    private val getCmdlineUserArgsBind by lazy {
        ObjectCalls.getMethodBind("OS", "get_cmdline_user_args", GET_CMDLINE_USER_ARGS_HASH)
    }

    private const val GET_VIDEO_ADAPTER_DRIVER_INFO_HASH = 1139954409L
    private val getVideoAdapterDriverInfoBind by lazy {
        ObjectCalls.getMethodBind("OS", "get_video_adapter_driver_info", GET_VIDEO_ADAPTER_DRIVER_INFO_HASH)
    }

    private const val IS_RESTART_ON_EXIT_SET_HASH = 36873697L
    private val isRestartOnExitSetBind by lazy {
        ObjectCalls.getMethodBind("OS", "is_restart_on_exit_set", IS_RESTART_ON_EXIT_SET_HASH)
    }

    private const val GET_RESTART_ON_EXIT_ARGUMENTS_HASH = 1139954409L
    private val getRestartOnExitArgumentsBind by lazy {
        ObjectCalls.getMethodBind("OS", "get_restart_on_exit_arguments", GET_RESTART_ON_EXIT_ARGUMENTS_HASH)
    }

    private const val DELAY_USEC_HASH = 998575451L
    private val delayUsecBind by lazy {
        ObjectCalls.getMethodBind("OS", "delay_usec", DELAY_USEC_HASH)
    }

    private const val DELAY_MSEC_HASH = 998575451L
    private val delayMsecBind by lazy {
        ObjectCalls.getMethodBind("OS", "delay_msec", DELAY_MSEC_HASH)
    }

    private const val GET_LOCALE_HASH = 201670096L
    private val getLocaleBind by lazy {
        ObjectCalls.getMethodBind("OS", "get_locale", GET_LOCALE_HASH)
    }

    private const val GET_LOCALE_LANGUAGE_HASH = 201670096L
    private val getLocaleLanguageBind by lazy {
        ObjectCalls.getMethodBind("OS", "get_locale_language", GET_LOCALE_LANGUAGE_HASH)
    }

    private const val GET_MODEL_NAME_HASH = 201670096L
    private val getModelNameBind by lazy {
        ObjectCalls.getMethodBind("OS", "get_model_name", GET_MODEL_NAME_HASH)
    }

    private const val IS_USERFS_PERSISTENT_HASH = 36873697L
    private val isUserfsPersistentBind by lazy {
        ObjectCalls.getMethodBind("OS", "is_userfs_persistent", IS_USERFS_PERSISTENT_HASH)
    }

    private const val IS_STDOUT_VERBOSE_HASH = 36873697L
    private val isStdoutVerboseBind by lazy {
        ObjectCalls.getMethodBind("OS", "is_stdout_verbose", IS_STDOUT_VERBOSE_HASH)
    }

    private const val IS_DEBUG_BUILD_HASH = 36873697L
    private val isDebugBuildBind by lazy {
        ObjectCalls.getMethodBind("OS", "is_debug_build", IS_DEBUG_BUILD_HASH)
    }

    private const val GET_STATIC_MEMORY_USAGE_HASH = 3905245786L
    private val getStaticMemoryUsageBind by lazy {
        ObjectCalls.getMethodBind("OS", "get_static_memory_usage", GET_STATIC_MEMORY_USAGE_HASH)
    }

    private const val GET_STATIC_MEMORY_PEAK_USAGE_HASH = 3905245786L
    private val getStaticMemoryPeakUsageBind by lazy {
        ObjectCalls.getMethodBind("OS", "get_static_memory_peak_usage", GET_STATIC_MEMORY_PEAK_USAGE_HASH)
    }

    private const val MOVE_TO_TRASH_HASH = 2113323047L
    private val moveToTrashBind by lazy {
        ObjectCalls.getMethodBind("OS", "move_to_trash", MOVE_TO_TRASH_HASH)
    }

    private const val GET_USER_DATA_DIR_HASH = 201670096L
    private val getUserDataDirBind by lazy {
        ObjectCalls.getMethodBind("OS", "get_user_data_dir", GET_USER_DATA_DIR_HASH)
    }

    private const val GET_CONFIG_DIR_HASH = 201670096L
    private val getConfigDirBind by lazy {
        ObjectCalls.getMethodBind("OS", "get_config_dir", GET_CONFIG_DIR_HASH)
    }

    private const val GET_DATA_DIR_HASH = 201670096L
    private val getDataDirBind by lazy {
        ObjectCalls.getMethodBind("OS", "get_data_dir", GET_DATA_DIR_HASH)
    }

    private const val GET_CACHE_DIR_HASH = 201670096L
    private val getCacheDirBind by lazy {
        ObjectCalls.getMethodBind("OS", "get_cache_dir", GET_CACHE_DIR_HASH)
    }

    private const val GET_TEMP_DIR_HASH = 201670096L
    private val getTempDirBind by lazy {
        ObjectCalls.getMethodBind("OS", "get_temp_dir", GET_TEMP_DIR_HASH)
    }

    private const val GET_UNIQUE_ID_HASH = 201670096L
    private val getUniqueIdBind by lazy {
        ObjectCalls.getMethodBind("OS", "get_unique_id", GET_UNIQUE_ID_HASH)
    }

    private const val IS_KEYCODE_UNICODE_HASH = 1116898809L
    private val isKeycodeUnicodeBind by lazy {
        ObjectCalls.getMethodBind("OS", "is_keycode_unicode", IS_KEYCODE_UNICODE_HASH)
    }

    private const val FIND_KEYCODE_FROM_STRING_HASH = 1084858572L
    private val findKeycodeFromStringBind by lazy {
        ObjectCalls.getMethodBind("OS", "find_keycode_from_string", FIND_KEYCODE_FROM_STRING_HASH)
    }

    private const val SET_USE_FILE_ACCESS_SAVE_AND_SWAP_HASH = 2586408642L
    private val setUseFileAccessSaveAndSwapBind by lazy {
        ObjectCalls.getMethodBind("OS", "set_use_file_access_save_and_swap", SET_USE_FILE_ACCESS_SAVE_AND_SWAP_HASH)
    }

    private const val SET_THREAD_NAME_HASH = 166001499L
    private val setThreadNameBind by lazy {
        ObjectCalls.getMethodBind("OS", "set_thread_name", SET_THREAD_NAME_HASH)
    }

    private const val GET_THREAD_CALLER_ID_HASH = 3905245786L
    private val getThreadCallerIdBind by lazy {
        ObjectCalls.getMethodBind("OS", "get_thread_caller_id", GET_THREAD_CALLER_ID_HASH)
    }

    private const val GET_MAIN_THREAD_ID_HASH = 3905245786L
    private val getMainThreadIdBind by lazy {
        ObjectCalls.getMethodBind("OS", "get_main_thread_id", GET_MAIN_THREAD_ID_HASH)
    }

    private const val HAS_FEATURE_HASH = 3927539163L
    private val hasFeatureBind by lazy {
        ObjectCalls.getMethodBind("OS", "has_feature", HAS_FEATURE_HASH)
    }

    private const val IS_SANDBOXED_HASH = 36873697L
    private val isSandboxedBind by lazy {
        ObjectCalls.getMethodBind("OS", "is_sandboxed", IS_SANDBOXED_HASH)
    }

    private const val REQUEST_PERMISSION_HASH = 2323990056L
    private val requestPermissionBind by lazy {
        ObjectCalls.getMethodBind("OS", "request_permission", REQUEST_PERMISSION_HASH)
    }

    private const val REQUEST_PERMISSIONS_HASH = 2240911060L
    private val requestPermissionsBind by lazy {
        ObjectCalls.getMethodBind("OS", "request_permissions", REQUEST_PERMISSIONS_HASH)
    }

    private const val GET_GRANTED_PERMISSIONS_HASH = 1139954409L
    private val getGrantedPermissionsBind by lazy {
        ObjectCalls.getMethodBind("OS", "get_granted_permissions", GET_GRANTED_PERMISSIONS_HASH)
    }

    private const val REVOKE_GRANTED_PERMISSIONS_HASH = 3218959716L
    private val revokeGrantedPermissionsBind by lazy {
        ObjectCalls.getMethodBind("OS", "revoke_granted_permissions", REVOKE_GRANTED_PERMISSIONS_HASH)
    }

    private const val ADD_LOGGER_HASH = 4261188958L
    private val addLoggerBind by lazy {
        ObjectCalls.getMethodBind("OS", "add_logger", ADD_LOGGER_HASH)
    }

    private const val REMOVE_LOGGER_HASH = 4261188958L
    private val removeLoggerBind by lazy {
        ObjectCalls.getMethodBind("OS", "remove_logger", REMOVE_LOGGER_HASH)
    }
}
