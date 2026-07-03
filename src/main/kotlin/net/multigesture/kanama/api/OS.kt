package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Provides access to common operating system functionalities.
 *
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

    /**
     * Generates a `PackedByteArray` of cryptographically secure random bytes with given `size`. Note:
     * Generating large quantities of bytes using this method can result in locking and entropy of
     * lower quality on most platforms. Using `Crypto.generate_random_bytes` is preferred in most
     * cases.
     *
     * Generated from Godot docs: OS.get_entropy
     */
    @JvmStatic
    fun getEntropy(size: Int): ByteArray {
        return ObjectCalls.ptrcallWithIntArgRetByteArray(getEntropyBind, singleton, size)
    }

    /**
     * Returns the list of certification authorities trusted by the operating system as a string of
     * concatenated certificates in PEM format.
     *
     * Generated from Godot docs: OS.get_system_ca_certificates
     */
    @JvmStatic
    fun getSystemCaCertificates(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getSystemCaCertificatesBind, singleton)
    }

    /**
     * Returns an array of connected MIDI device names, if they exist. Returns an empty array if the
     * system MIDI driver has not previously been initialized with `open_midi_inputs`. See also
     * `close_midi_inputs`. Note: This method is implemented on Linux, macOS, Windows, and Web. Note:
     * On the Web platform, Web MIDI needs to be supported by the browser. For the time being
     * (https://caniuse.com/midi), it is currently supported by all major browsers, except Safari.
     * Note: On the Web platform, using MIDI input requires a browser permission to be granted first.
     * This permission request is performed when calling `open_midi_inputs`. The browser will refrain
     * from processing MIDI input until the user accepts the permission request.
     *
     * Generated from Godot docs: OS.get_connected_midi_inputs
     */
    @JvmStatic
    fun getConnectedMidiInputs(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getConnectedMidiInputsBind, singleton)
    }

    /**
     * Initializes the singleton for the system MIDI driver, allowing Godot to receive
     * `InputEventMIDI`. See also `get_connected_midi_inputs` and `close_midi_inputs`. Note: This
     * method is implemented on Linux, macOS, Windows, and Web. Note: On the Web platform, Web MIDI
     * needs to be supported by the browser. For the time being (https://caniuse.com/midi), it is
     * currently supported by all major browsers, except Safari. Note: On the Web platform, using MIDI
     * input requires a browser permission to be granted first. This permission request is performed
     * when calling `open_midi_inputs`. The browser will refrain from processing MIDI input until the
     * user accepts the permission request.
     *
     * Generated from Godot docs: OS.open_midi_inputs
     */
    @JvmStatic
    fun openMidiInputs() {
        ObjectCalls.ptrcallNoArgs(openMidiInputsBind, singleton)
    }

    /**
     * Shuts down the system MIDI driver. Godot will no longer receive `InputEventMIDI`. See also
     * `open_midi_inputs` and `get_connected_midi_inputs`. Note: This method is implemented on Linux,
     * macOS, Windows, and Web.
     *
     * Generated from Godot docs: OS.close_midi_inputs
     */
    @JvmStatic
    fun closeMidiInputs() {
        ObjectCalls.ptrcallNoArgs(closeMidiInputsBind, singleton)
    }

    @JvmStatic
    /**
     * Displays a modal dialog box using the host platform's implementation. The engine execution is
     * blocked until the dialog is closed.
     *
     * Generated from Godot docs: OS.alert
     */
    fun alert(text: String, title: String = "Alert!") {
        ObjectCalls.ptrcallWithTwoStringArgs(alertBind, singleton, text, title)
    }

    @JvmStatic
    /**
     * Crashes the engine (or the editor if called within a `@tool` script). See also `kill`. Note:
     * This method should only be used for testing the system's crash handler, not for any other
     * purpose. For general error reporting, use (in order of preference) `@GDScript.assert`,
     * `@GlobalScope.push_error`, or `alert`.
     *
     * Generated from Godot docs: OS.crash
     */
    fun crash(message: String) {
        ObjectCalls.ptrcallWithStringArg(crashBind, singleton, message)
    }

    /**
     * If `true`, the engine optimizes for low processor usage by only refreshing the screen if needed.
     * Can improve battery consumption on mobile. Note: On start-up, this is the same as
     * `ProjectSettings.application/run/low_processor_mode`.
     *
     * Generated from Godot docs: OS.set_low_processor_usage_mode
     */
    @JvmStatic
    fun setLowProcessorUsageMode(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setLowProcessorUsageModeBind, singleton, enable)
    }

    /**
     * If `true`, the engine optimizes for low processor usage by only refreshing the screen if needed.
     * Can improve battery consumption on mobile. Note: On start-up, this is the same as
     * `ProjectSettings.application/run/low_processor_mode`.
     *
     * Generated from Godot docs: OS.is_in_low_processor_usage_mode
     */
    @JvmStatic
    fun isInLowProcessorUsageMode(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isInLowProcessorUsageModeBind, singleton)
    }

    /**
     * The amount of sleeping between frames when the low-processor usage mode is enabled, in
     * microseconds. Higher values will result in lower CPU usage. See also `low_processor_usage_mode`.
     * Note: On start-up, this is the same as
     * `ProjectSettings.application/run/low_processor_mode_sleep_usec`.
     *
     * Generated from Godot docs: OS.set_low_processor_usage_mode_sleep_usec
     */
    @JvmStatic
    fun setLowProcessorUsageModeSleepUsec(usec: Int) {
        ObjectCalls.ptrcallWithIntArg(setLowProcessorUsageModeSleepUsecBind, singleton, usec)
    }

    /**
     * The amount of sleeping between frames when the low-processor usage mode is enabled, in
     * microseconds. Higher values will result in lower CPU usage. See also `low_processor_usage_mode`.
     * Note: On start-up, this is the same as
     * `ProjectSettings.application/run/low_processor_mode_sleep_usec`.
     *
     * Generated from Godot docs: OS.get_low_processor_usage_mode_sleep_usec
     */
    @JvmStatic
    fun getLowProcessorUsageModeSleepUsec(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getLowProcessorUsageModeSleepUsecBind, singleton)
    }

    /**
     * If `true`, the engine filters the time delta measured between each frame, and attempts to
     * compensate for random variation. This only works on systems where V-Sync is active. Note: On
     * start-up, this is the same as `ProjectSettings.application/run/delta_smoothing`.
     *
     * Generated from Godot docs: OS.set_delta_smoothing
     */
    @JvmStatic
    fun setDeltaSmoothing(deltaSmoothingEnabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDeltaSmoothingBind, singleton, deltaSmoothingEnabled)
    }

    /**
     * If `true`, the engine filters the time delta measured between each frame, and attempts to
     * compensate for random variation. This only works on systems where V-Sync is active. Note: On
     * start-up, this is the same as `ProjectSettings.application/run/delta_smoothing`.
     *
     * Generated from Godot docs: OS.is_delta_smoothing_enabled
     */
    @JvmStatic
    fun isDeltaSmoothingEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDeltaSmoothingEnabledBind, singleton)
    }

    /**
     * Returns the number of logical CPU cores available on the host machine. On CPUs with
     * HyperThreading enabled, this number will be greater than the number of physical CPU cores.
     *
     * Generated from Godot docs: OS.get_processor_count
     */
    @JvmStatic
    fun getProcessorCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getProcessorCountBind, singleton)
    }

    /**
     * Returns the full name of the CPU model on the host machine (e.g. `"Intel(R) Core(TM) i7-6700K
     * CPU @ 4.00GHz"`). Note: This method is only implemented on Windows, macOS, Linux and iOS. On
     * Android and Web, `get_processor_name` returns an empty string.
     *
     * Generated from Godot docs: OS.get_processor_name
     */
    @JvmStatic
    fun getProcessorName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getProcessorNameBind, singleton)
    }

    /**
     * Returns the list of font family names available. Note: This method is implemented on Android,
     * iOS, Linux, macOS and Windows.
     *
     * Generated from Godot docs: OS.get_system_fonts
     */
    @JvmStatic
    fun getSystemFonts(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getSystemFontsBind, singleton)
    }

    /**
     * Returns the path to the system font file with `font_name` and style. Returns an empty string if
     * no matching fonts found. The following aliases can be used to request default fonts:
     * "sans-serif", "serif", "monospace", "cursive", and "fantasy". Note: Returned font might have
     * different style if the requested style is not available. Note: This method is implemented on
     * Android, iOS, Linux, macOS and Windows.
     *
     * Generated from Godot docs: OS.get_system_font_path
     */
    @JvmStatic
    fun getSystemFontPath(fontName: String, weight: Int = 400, stretch: Int = 100, italic: Boolean = false): String {
        return ObjectCalls.ptrcallWithStringTwoIntBoolArgsRetString(getSystemFontPathBind, singleton, fontName, weight, stretch, italic)
    }

    /**
     * Returns an array of the system substitute font file paths, which are similar to the font with
     * `font_name` and style for the specified text, locale, and script. Returns an empty array if no
     * matching fonts found. The following aliases can be used to request default fonts: "sans-serif",
     * "serif", "monospace", "cursive", and "fantasy". Note: Depending on OS, it's not guaranteed that
     * any of the returned fonts will be suitable for rendering specified text. Fonts should be loaded
     * and checked in the order they are returned, and the first suitable one used. Note: Returned
     * fonts might have different style if the requested style is not available or belong to a
     * different font family. Note: This method is implemented on Android, iOS, Linux, macOS and
     * Windows.
     *
     * Generated from Godot docs: OS.get_system_font_path_for_text
     */
    @JvmStatic
    fun getSystemFontPathForText(fontName: String, text: String, locale: String = "", script: String = "", weight: Int = 400, stretch: Int = 100, italic: Boolean = false): List<String> {
        return ObjectCalls.ptrcallWithFourStringTwoIntBoolArgsRetPackedStringList(getSystemFontPathForTextBind, singleton, fontName, text, locale, script, weight, stretch, italic)
    }

    /**
     * Returns the file path to the current engine executable. Note: On macOS, if you want to launch
     * another instance of Godot, always use `create_instance` instead of relying on the executable
     * path.
     *
     * Generated from Godot docs: OS.get_executable_path
     */
    @JvmStatic
    fun getExecutablePath(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getExecutablePathBind, singleton)
    }

    /**
     * Reads a user input as a UTF-8 encoded string from the standard input. This operation can be
     * blocking, which causes the window to freeze if `read_string_from_stdin` is called on the main
     * thread. - If standard input is console, this method will block until the program receives a line
     * break in standard input (usually by the user pressing Enter). - If standard input is pipe, this
     * method will block until a specific amount of data is read or pipe is closed. - If standard input
     * is a file, this method will read a specific amount of data (or less if end-of-file is reached)
     * and return immediately. Note: This method automatically replaces `\r\n` line breaks with `\n`
     * and removes them from the end of the string. Use `read_buffer_from_stdin` to read the
     * unprocessed data. Note: This method is implemented on Linux, macOS, and Windows. Note: On
     * exported Windows builds, run the console wrapper executable to access the terminal. If standard
     * input is console, calling this method without console wrapped will freeze permanently. If
     * standard input is pipe or file, it can be used without console wrapper. If you need a single
     * executable with full console support, use a custom build compiled with the
     * `windows_subsystem=console` flag.
     *
     * Generated from Godot docs: OS.read_string_from_stdin
     */
    @JvmStatic
    fun readStringFromStdin(bufferSize: Long = 1024L): String {
        return ObjectCalls.ptrcallWithLongArgRetString(readStringFromStdinBind, singleton, bufferSize)
    }

    /**
     * Reads a user input as raw data from the standard input. This operation can be blocking, which
     * causes the window to freeze if `read_buffer_from_stdin` is called on the main thread. - If
     * standard input is console, this method will block until the program receives a line break in
     * standard input (usually by the user pressing Enter). - If standard input is pipe, this method
     * will block until a specific amount of data is read or pipe is closed. - If standard input is a
     * file, this method will read a specific amount of data (or less if end-of-file is reached) and
     * return immediately. Note: This method is implemented on Linux, macOS, and Windows. Note: On
     * exported Windows builds, run the console wrapper executable to access the terminal. If standard
     * input is console, calling this method without console wrapped will freeze permanently. If
     * standard input is pipe or file, it can be used without console wrapper. If you need a single
     * executable with full console support, use a custom build compiled with the
     * `windows_subsystem=console` flag.
     *
     * Generated from Godot docs: OS.read_buffer_from_stdin
     */
    @JvmStatic
    fun readBufferFromStdin(bufferSize: Long = 1024L): ByteArray {
        return ObjectCalls.ptrcallWithLongArgRetByteArray(readBufferFromStdinBind, singleton, bufferSize)
    }

    /**
     * Returns the type of the standard input device. Note: This method is implemented on Linux, macOS,
     * and Windows. Note: On exported Windows builds, run the console wrapper executable to access the
     * standard input. If you need a single executable with full console support, use a custom build
     * compiled with the `windows_subsystem=console` flag.
     *
     * Generated from Godot docs: OS.get_stdin_type
     */
    @JvmStatic
    fun getStdinType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getStdinTypeBind, singleton)
    }

    /**
     * Returns the type of the standard output device. Note: This method is implemented on Linux,
     * macOS, and Windows.
     *
     * Generated from Godot docs: OS.get_stdout_type
     */
    @JvmStatic
    fun getStdoutType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getStdoutTypeBind, singleton)
    }

    /**
     * Returns the type of the standard error device. Note: This method is implemented on Linux, macOS,
     * and Windows.
     *
     * Generated from Godot docs: OS.get_stderr_type
     */
    @JvmStatic
    fun getStderrType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getStderrTypeBind, singleton)
    }

    @JvmStatic
    /**
     * Executes the given process in a blocking way. The file specified in `path` must exist and be
     * executable. The system path resolution will be used. The `arguments` are used in the given
     * order, separated by spaces, and wrapped in quotes. If an `output` array is provided, the
     * complete shell output of the process is appended to `output` as a single `String` element. If
     * `read_stderr` is `true`, the output to the standard error stream is also appended to the array.
     * On Windows, if `open_console` is `true` and the process is a console app, a new terminal window
     * is opened. This method returns the exit code of the command, or `-1` if the process fails to
     * execute. Note: The main thread will be blocked until the executed command terminates. Use
     * `Thread` to create a separate thread that will not block the main thread, or use
     * `create_process` to create a completely independent process. For example, to retrieve a list of
     * the working directory's contents:
     *
     * Generated from Godot docs: OS.execute
     */
    fun execute(path: String, arguments: List<String>, output: List<Any?> = emptyList(), readStderr: Boolean = false, openConsole: Boolean = false): Int {
        return ObjectCalls.ptrcallWithStringPackedStringListArrayTwoBoolArgsRetInt(executeBind, singleton, path, arguments, output, readStderr, openConsole)
    }

    /**
     * Creates a new process that runs independently of Godot with redirected IO. It will not terminate
     * when Godot terminates. The path specified in `path` must exist and be an executable file or
     * macOS `.app` bundle. The path is resolved based on the current platform. The `arguments` are
     * used in the given order and separated by a space. If `blocking` is `false`, created pipes work
     * in non-blocking mode, i.e. read and write operations will return immediately. Use
     * `FileAccess.get_error` to check if the last read/write operation was successful. If the process
     * cannot be created, this method returns an empty `Dictionary`. Otherwise, this method returns a
     * `Dictionary` with the following keys: - `"stdio"` - `FileAccess` to access the process stdin and
     * stdout pipes (read/write). - `"stderr"` - `FileAccess` to access the process stderr pipe (read
     * only). - `"pid"` - Process ID as an `int`, which you can use to monitor the process (and
     * potentially terminate it with `kill`). Note: This method is implemented on Android, Linux,
     * macOS, and Windows. Note: To execute a Windows command interpreter built-in command, specify
     * `cmd.exe` in `path`, `/c` as the first argument, and the desired command as the second argument.
     * Note: To execute a PowerShell built-in command, specify `powershell.exe` in `path`, `-Command`
     * as the first argument, and the desired command as the second argument. Note: To execute a Unix
     * shell built-in command, specify shell executable name in `path`, `-c` as the first argument, and
     * the desired command as the second argument. Note: On macOS, sandboxed applications are limited
     * to run only embedded helper executables, specified during export or system .app bundle, system
     * .app bundles will ignore arguments.
     *
     * Generated from Godot docs: OS.execute_with_pipe
     */
    @JvmStatic
    fun executeWithPipe(path: String, arguments: List<String>, blocking: Boolean = true): Map<String, Any?> {
        return ObjectCalls.ptrcallWithStringPackedStringListBoolArgsRetDictionary(executeWithPipeBind, singleton, path, arguments, blocking)
    }

    /**
     * Creates a new process that runs independently of Godot. It will not terminate when Godot
     * terminates. The path specified in `path` must exist and be an executable file or macOS `.app`
     * bundle. The path is resolved based on the current platform. The `arguments` are used in the
     * given order and separated by a space. On Windows, if `open_console` is `true` and the process is
     * a console app, a new terminal window will be opened. If the process is successfully created,
     * this method returns its process ID, which you can use to monitor the process (and potentially
     * terminate it with `kill`). Otherwise, this method returns `-1`.
     *
     * Generated from Godot docs: OS.create_process
     */
    @JvmStatic
    fun createProcess(path: String, arguments: List<String>, openConsole: Boolean = false): Int {
        return ObjectCalls.ptrcallWithStringPackedStringListBoolArgsRetInt(createProcessBind, singleton, path, arguments, openConsole)
    }

    /**
     * Creates a new instance of Godot that runs independently. The `arguments` are used in the given
     * order and separated by a space. If the process is successfully created, this method returns the
     * new process' ID, which you can use to monitor the process (and potentially terminate it with
     * `kill`). If the process cannot be created, this method returns `-1`. See `create_process` if you
     * wish to run a different process. Note: This method is implemented on Android, Linux, macOS and
     * Windows.
     *
     * Generated from Godot docs: OS.create_instance
     */
    @JvmStatic
    fun createInstance(arguments: List<String>): Int {
        return ObjectCalls.ptrcallWithPackedStringListArgRetInt(createInstanceBind, singleton, arguments)
    }

    /**
     * Opens one or more files/directories with the specified application. The `program_path` specifies
     * the path to the application to use for opening the files, and `paths` contains an array of
     * file/directory paths to open. Note: This method is mostly only relevant for macOS, where opening
     * files using `create_process` might fail. On other platforms, this falls back to using
     * `create_process`. Note: On macOS, `program_path` should ideally be the path to a `.app` bundle.
     *
     * Generated from Godot docs: OS.open_with_program
     */
    @JvmStatic
    fun openWithProgram(programPath: String, paths: List<String>): Long {
        return ObjectCalls.ptrcallWithStringAndPackedStringListArgRetLong(openWithProgramBind, singleton, programPath, paths)
    }

    @JvmStatic
    /**
     * Kill (terminate) the process identified by the given process ID (`pid`), such as the ID returned
     * by `execute` in non-blocking mode. See also `crash`. Note: This method can also be used to kill
     * processes that were not spawned by the engine. Note: This method is implemented on Android, iOS,
     * Linux, macOS and Windows.
     *
     * Generated from Godot docs: OS.kill
     */
    fun kill(pid: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(killBind, singleton, pid)
    }

    /**
     * Requests the OS to open a resource identified by `uri` with the most appropriate program. For
     * example: - `OS.shell_open("C:\\Users\\name\\Downloads")` on Windows opens the file explorer at
     * the user's Downloads folder. - `OS.shell_open("C:/Users/name/Downloads")` also works on Windows
     * and opens the file explorer at the user's Downloads folder. -
     * `OS.shell_open("https://godotengine.org")` opens the default web browser on the official Godot
     * website. - `OS.shell_open("mailto:example@example.com")` opens the default email client with the
     * "To" field set to `example@example.com`. See RFC 2368 - The mailto URL scheme
     * (https://datatracker.ietf.org/doc/html/rfc2368) for a list of fields that can be added. Use
     * `ProjectSettings.globalize_path` to convert a `res://` or `user://` project path into a system
     * path for use with this method. Note: Use `String.uri_encode` to encode characters within URLs in
     * a URL-safe, portable way. This is especially required for line breaks. Otherwise, `shell_open`
     * may not work correctly in a project exported to the Web platform. Note: This method is
     * implemented on Android, iOS, Web, Linux, macOS and Windows.
     *
     * Generated from Godot docs: OS.shell_open
     */
    @JvmStatic
    fun shellOpen(uri: String): Long {
        return ObjectCalls.ptrcallWithStringArgRetLong(shellOpenBind, singleton, uri)
    }

    /**
     * Requests the OS to open the file manager, navigate to the given `file_or_dir_path` and select
     * the target file or folder. If `open_folder` is `true` and `file_or_dir_path` is a valid
     * directory path, the OS will open the file manager and navigate to the target folder without
     * selecting anything. Use `ProjectSettings.globalize_path` to convert a `res://` or `user://`
     * project path into a system path to use with this method. Note: This method is currently only
     * implemented on Windows and macOS. On other platforms, it will fallback to `shell_open` with a
     * directory path of `file_or_dir_path` prefixed with `file://`.
     *
     * Generated from Godot docs: OS.shell_show_in_file_manager
     */
    @JvmStatic
    fun shellShowInFileManager(fileOrDirPath: String, openFolder: Boolean = true): Long {
        return ObjectCalls.ptrcallWithStringAndBoolArgRetLong(shellShowInFileManagerBind, singleton, fileOrDirPath, openFolder)
    }

    /**
     * Returns `true` if the child process ID (`pid`) is still running or `false` if it has terminated.
     * `pid` must be a valid ID generated from `create_process`. Note: This method is implemented on
     * Android, iOS, Linux, macOS, and Windows.
     *
     * Generated from Godot docs: OS.is_process_running
     */
    @JvmStatic
    fun isProcessRunning(pid: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isProcessRunningBind, singleton, pid)
    }

    /**
     * Returns the exit code of a spawned process once it has finished running (see
     * `is_process_running`). Returns `-1` if the `pid` is not a PID of a spawned child process, the
     * process is still running, or the method is not implemented for the current platform. Note:
     * Returns `-1` if the `pid` is a macOS bundled app process. Note: This method is implemented on
     * Android, Linux, macOS and Windows.
     *
     * Generated from Godot docs: OS.get_process_exit_code
     */
    @JvmStatic
    fun getProcessExitCode(pid: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getProcessExitCodeBind, singleton, pid)
    }

    /**
     * Returns the number used by the host machine to uniquely identify this application. Note: On Web,
     * this method always returns `0`.
     *
     * Generated from Godot docs: OS.get_process_id
     */
    @JvmStatic
    fun getProcessId(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getProcessIdBind, singleton)
    }

    /**
     * Returns `true` if the environment variable with the name `variable` exists. Note: Double-check
     * the casing of `variable`. Environment variable names are case-sensitive on all platforms except
     * Windows.
     *
     * Generated from Godot docs: OS.has_environment
     */
    @JvmStatic
    fun hasEnvironment(variable: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(hasEnvironmentBind, singleton, variable)
    }

    /**
     * Returns the value of the given environment variable, or an empty string if `variable` doesn't
     * exist. Note: Double-check the casing of `variable`. Environment variable names are
     * case-sensitive on all platforms except Windows. Note: On macOS, applications do not have access
     * to shell environment variables.
     *
     * Generated from Godot docs: OS.get_environment
     */
    @JvmStatic
    fun getEnvironment(variable: String): String {
        return ObjectCalls.ptrcallWithStringArgRetString(getEnvironmentBind, singleton, variable)
    }

    /**
     * Sets the value of the environment variable `variable` to `value`. The environment variable will
     * be set for the Godot process and any process executed with `execute` after running
     * `set_environment`. The environment variable will not persist to processes run after the Godot
     * process was terminated. Note: Environment variable names are case-sensitive on all platforms
     * except Windows. The `variable` name cannot be empty or include the `=` character. On Windows,
     * there is a 32767 characters limit for the combined length of `variable`, `value`, and the `=`
     * and null terminator characters that will be registered in the environment block.
     *
     * Generated from Godot docs: OS.set_environment
     */
    @JvmStatic
    fun setEnvironment(variable: String, value: String) {
        ObjectCalls.ptrcallWithTwoStringArgs(setEnvironmentBind, singleton, variable, value)
    }

    /**
     * Removes the given environment variable from the current environment, if it exists. The
     * `variable` name cannot be empty or include the `=` character. The environment variable will be
     * removed for the Godot process and any process executed with `execute` after running
     * `unset_environment`. The removal of the environment variable will not persist to processes run
     * after the Godot process was terminated. Note: Environment variable names are case-sensitive on
     * all platforms except Windows.
     *
     * Generated from Godot docs: OS.unset_environment
     */
    @JvmStatic
    fun unsetEnvironment(variable: String) {
        ObjectCalls.ptrcallWithStringArg(unsetEnvironmentBind, singleton, variable)
    }

    /**
     * Returns the name of the host platform. - On Windows, this is `"Windows"`. - On macOS, this is
     * `"macOS"`. - On Linux-based operating systems, this is `"Linux"`. - On BSD-based operating
     * systems, this is `"FreeBSD"`, `"NetBSD"`, `"OpenBSD"`, or `"BSD"` as a fallback. - On Android,
     * this is `"Android"`. - On iOS, this is `"iOS"`. - On Web, this is `"Web"`. Note: Custom builds
     * of the engine may support additional platforms, such as consoles, possibly returning other
     * names.
     *
     * Generated from Godot docs: OS.get_name
     */
    @JvmStatic
    fun getName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getNameBind, singleton)
    }

    /**
     * Returns the name of the distribution for Linux and BSD platforms (e.g. "Ubuntu", "Manjaro",
     * "OpenBSD", etc.). Returns the same value as `get_name` for stock Android ROMs, but attempts to
     * return the custom ROM name for popular Android derivatives such as "LineageOS". Returns the same
     * value as `get_name` for other platforms. Note: This method is not supported on the Web platform.
     * It returns an empty string.
     *
     * Generated from Godot docs: OS.get_distribution_name
     */
    @JvmStatic
    fun getDistributionName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getDistributionNameBind, singleton)
    }

    /**
     * Returns the exact production and build version of the operating system. This is different from
     * the branded version used in marketing. This helps to distinguish between different releases of
     * operating systems, including minor versions, and insider and custom builds. - For Windows, the
     * major and minor version are returned, as well as the build number. For example, the returned
     * string may look like `10.0.9926` for a build of Windows 10. - For rolling distributions, such as
     * Arch Linux, an empty string is returned. - For macOS and iOS, the major and minor version are
     * returned, as well as the patch number. - For Android, the SDK version and the incremental build
     * number are returned. If it's a custom ROM, it attempts to return its version instead. Note: This
     * method is not supported on the Web platform. It returns an empty string.
     *
     * Generated from Godot docs: OS.get_version
     */
    @JvmStatic
    fun getVersion(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getVersionBind, singleton)
    }

    /**
     * Returns the branded version used in marketing, followed by the build number (on Windows), the
     * version number (on macOS), or the SDK version and incremental build number (on Android).
     * Examples include `11 (build 22000)`, `Sequoia (15.0.0)`, and `15 (SDK 35 build abc528-11988f)`.
     * This value can then be appended to `get_name` to get a full, human-readable operating system
     * name and version combination for the operating system. Windows feature updates such as 24H2 are
     * not contained in the resulting string, but Windows Server is recognized as such (e.g. `2025
     * (build 26100)` for Windows Server 2025). Note: This method is only supported on Windows, macOS,
     * and Android. On other operating systems, it returns the same value as `get_version`.
     *
     * Generated from Godot docs: OS.get_version_alias
     */
    @JvmStatic
    fun getVersionAlias(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getVersionAliasBind, singleton)
    }

    /**
     * Returns the command-line arguments passed to the engine, excluding arguments processed by the
     * engine, such as `--headless` and `--fullscreen`.
     *
     * Generated from Godot docs: OS.get_cmdline_args
     */
    @JvmStatic
    fun getCmdlineArgs(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getCmdlineArgsBind, singleton)
    }

    /**
     * Returns the command-line user arguments passed to the engine. User arguments are ignored by the
     * engine and reserved for the user. They are passed after the double dash `--` argument. `++` may
     * be used when `--` is intercepted by another program (such as `startx`).
     *
     * Generated from Godot docs: OS.get_cmdline_user_args
     */
    @JvmStatic
    fun getCmdlineUserArgs(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getCmdlineUserArgsBind, singleton)
    }

    /**
     * Returns the video adapter driver name and version for the user's currently active graphics card,
     * as a `PackedStringArray`. See also `RenderingServer.get_video_adapter_api_version`. The first
     * element holds the driver name, such as `nvidia`, `amdgpu`, etc. The second element holds the
     * driver version. For example, on the `nvidia` driver on a Linux/BSD platform, the version is in
     * the format `510.85.02`. For Windows, the driver's format is `31.0.15.1659`. Note: This method is
     * only supported on Linux/BSD and Windows when not running in headless mode. On other platforms,
     * it returns an empty array. Note: This method will run slowly the first time it is called in a
     * session; it can take several seconds depending on the operating system and hardware. It is
     * blocking if called on the main thread, so it's recommended to call it on a separate thread using
     * `Thread`. This allows the engine to keep running while the information is being retrieved.
     * However, `get_video_adapter_driver_info` is not thread-safe, so it should not be called from
     * multiple threads at the same time.
     *
     * Generated from Godot docs: OS.get_video_adapter_driver_info
     */
    @JvmStatic
    fun getVideoAdapterDriverInfo(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getVideoAdapterDriverInfoBind, singleton)
    }

    /**
     * If `restart` is `true`, restarts the project automatically when it is exited with
     * `SceneTree.quit` or `Node.NOTIFICATION_WM_CLOSE_REQUEST`. Command-line `arguments` can be
     * supplied. To restart the project with the same command line arguments as originally used to run
     * the project, pass `get_cmdline_args` as the value for `arguments`. This method can be used to
     * apply setting changes that require a restart. See also `is_restart_on_exit_set` and
     * `get_restart_on_exit_arguments`. Note: This method is only effective on desktop platforms, and
     * only when the project isn't started from the editor. It will have no effect on mobile and Web
     * platforms, or when the project is started from the editor. Note: If the project process crashes
     * or is killed by the user (by sending `SIGKILL` instead of the usual `SIGTERM`), the project
     * won't restart automatically.
     *
     * Generated from Godot docs: OS.set_restart_on_exit
     */
    @JvmStatic
    fun setRestartOnExit(restart: Boolean, arguments: List<String>) {
        ObjectCalls.ptrcallWithBoolAndPackedStringArrayArgs(setRestartOnExitBind, singleton, restart, arguments)
    }

    /**
     * Returns `true` if the project will automatically restart when it exits for any reason, `false`
     * otherwise. See also `set_restart_on_exit` and `get_restart_on_exit_arguments`.
     *
     * Generated from Godot docs: OS.is_restart_on_exit_set
     */
    @JvmStatic
    fun isRestartOnExitSet(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isRestartOnExitSetBind, singleton)
    }

    /**
     * Returns the list of command line arguments that will be used when the project automatically
     * restarts using `set_restart_on_exit`. See also `is_restart_on_exit_set`.
     *
     * Generated from Godot docs: OS.get_restart_on_exit_arguments
     */
    @JvmStatic
    fun getRestartOnExitArguments(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getRestartOnExitArgumentsBind, singleton)
    }

    /**
     * Delays execution of the current thread by `usec` microseconds. `usec` must be greater than or
     * equal to `0`. Otherwise, `delay_usec` does nothing and prints an error message. Note:
     * `delay_usec` is a blocking way to delay code execution. To delay code execution in a
     * non-blocking way, you may use `SceneTree.create_timer`. Awaiting with a `SceneTreeTimer` delays
     * the execution of code placed below the `await` without affecting the rest of the project (or
     * editor, for `EditorPlugin`s and `EditorScript`s). Note: When `delay_usec` is called on the main
     * thread, it will freeze the project and will prevent it from redrawing and registering input
     * until the delay has passed. When using `delay_usec` as part of an `EditorPlugin` or
     * `EditorScript`, it will freeze the editor but won't freeze the project if it is currently
     * running (since the project is an independent child process).
     *
     * Generated from Godot docs: OS.delay_usec
     */
    @JvmStatic
    fun delayUsec(usec: Int) {
        ObjectCalls.ptrcallWithIntArg(delayUsecBind, singleton, usec)
    }

    /**
     * Delays execution of the current thread by `msec` milliseconds. `msec` must be greater than or
     * equal to `0`. Otherwise, `delay_msec` does nothing and prints an error message. Note:
     * `delay_msec` is a blocking way to delay code execution. To delay code execution in a
     * non-blocking way, you may use `SceneTree.create_timer`. Awaiting with `SceneTreeTimer` delays
     * the execution of code placed below the `await` without affecting the rest of the project (or
     * editor, for `EditorPlugin`s and `EditorScript`s). Note: When `delay_msec` is called on the main
     * thread, it will freeze the project and will prevent it from redrawing and registering input
     * until the delay has passed. When using `delay_msec` as part of an `EditorPlugin` or
     * `EditorScript`, it will freeze the editor but won't freeze the project if it is currently
     * running (since the project is an independent child process).
     *
     * Generated from Godot docs: OS.delay_msec
     */
    @JvmStatic
    fun delayMsec(msec: Int) {
        ObjectCalls.ptrcallWithIntArg(delayMsecBind, singleton, msec)
    }

    /**
     * Returns the host OS locale as a `String` of the form `language_Script_COUNTRY_VARIANT@extra`.
     * Every substring after `language` is optional and may not exist. - `language` - 2 or 3-letter
     * language code (https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes), in lower case. - `Script`
     * - 4-letter script code (https://en.wikipedia.org/wiki/ISO_15924), in title case. - `COUNTRY` - 2
     * or 3-letter country code (https://en.wikipedia.org/wiki/ISO_3166-1), in upper case. - `VARIANT`
     * - language variant, region and sort order. The variant can have any number of underscored
     * keywords. - `extra` - semicolon separated list of additional key words. This may include
     * currency, calendar, sort order and numbering system information. If you want only the language
     * code and not the fully specified locale from the OS, you can use `get_locale_language`.
     *
     * Generated from Godot docs: OS.get_locale
     */
    @JvmStatic
    fun getLocale(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getLocaleBind, singleton)
    }

    /**
     * Returns the host OS locale's 2 or 3-letter language code
     * (https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes) as a string which should be consistent
     * on all platforms. This is equivalent to extracting the `language` part of the `get_locale`
     * string. This can be used to narrow down fully specified locale strings to only the "common"
     * language code, when you don't need the additional information about country code or variants.
     * For example, for a French Canadian user with `fr_CA` locale, this would return `fr`.
     *
     * Generated from Godot docs: OS.get_locale_language
     */
    @JvmStatic
    fun getLocaleLanguage(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getLocaleLanguageBind, singleton)
    }

    /**
     * Returns the model name of the current device. Note: This method is implemented on Android, iOS,
     * macOS, and Windows. Returns `"GenericDevice"` on unsupported platforms.
     *
     * Generated from Godot docs: OS.get_model_name
     */
    @JvmStatic
    fun getModelName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getModelNameBind, singleton)
    }

    /**
     * Returns `true` if the `user://` file system is persistent, that is, its state is the same after
     * a player quits and starts the game again. Relevant to the Web platform, where this persistence
     * may be unavailable.
     *
     * Generated from Godot docs: OS.is_userfs_persistent
     */
    @JvmStatic
    fun isUserfsPersistent(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUserfsPersistentBind, singleton)
    }

    /**
     * Returns `true` if the engine was executed with the `--verbose` or `-v` command line argument, or
     * if `ProjectSettings.debug/settings/stdout/verbose_stdout` is `true`. See also
     * `@GlobalScope.print_verbose`.
     *
     * Generated from Godot docs: OS.is_stdout_verbose
     */
    @JvmStatic
    fun isStdoutVerbose(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isStdoutVerboseBind, singleton)
    }

    /**
     * Returns `true` if the Godot binary used to run the project is a debug export template, or when
     * running in the editor. Returns `false` if the Godot binary used to run the project is a release
     * export template. Note: To check whether the Godot binary used to run the project is an export
     * template (debug or release), use `OS.has_feature("template")` instead.
     *
     * Generated from Godot docs: OS.is_debug_build
     */
    @JvmStatic
    fun isDebugBuild(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDebugBuildBind, singleton)
    }

    /**
     * Returns the amount of static memory being used by the program in bytes. Only works in debug
     * builds.
     *
     * Generated from Godot docs: OS.get_static_memory_usage
     */
    @JvmStatic
    fun getStaticMemoryUsage(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getStaticMemoryUsageBind, singleton)
    }

    /**
     * Returns the maximum amount of static memory used. Only works in debug builds.
     *
     * Generated from Godot docs: OS.get_static_memory_peak_usage
     */
    @JvmStatic
    fun getStaticMemoryPeakUsage(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getStaticMemoryPeakUsageBind, singleton)
    }

    /**
     * Returns a `Dictionary` containing information about the current memory with the following
     * entries: - `"physical"` - total amount of usable physical memory in bytes. This value can be
     * slightly less than the actual physical memory amount, since it does not include memory reserved
     * by the kernel and devices. - `"free"` - amount of physical memory, that can be immediately
     * allocated without disk access or other costly operations, in bytes. The process might be able to
     * allocate more physical memory, but this action will require moving inactive pages to disk, which
     * can be expensive. - `"available"` - amount of memory that can be allocated without extending the
     * swap file(s), in bytes. This value includes both physical memory and swap. - `"stack"` - size of
     * the current thread stack in bytes. Note: Each entry's value may be `-1` if it is unknown.
     *
     * Generated from Godot docs: OS.get_memory_info
     */
    @JvmStatic
    fun getMemoryInfo(): Map<String, Any?> {
        return ObjectCalls.ptrcallNoArgsRetDictionary(getMemoryInfoBind, singleton)
    }

    /**
     * Moves the file or directory at the given `path` to the system's recycle bin. See also
     * `DirAccess.remove`. The method takes only global paths, so you may need to use
     * `ProjectSettings.globalize_path`. Do not use it for files in `res://` as it will not work in
     * exported projects. Returns `FAILED` if the file or directory cannot be found, or the system does
     * not support this method.
     *
     * Generated from Godot docs: OS.move_to_trash
     */
    @JvmStatic
    fun moveToTrash(path: String): Long {
        return ObjectCalls.ptrcallWithStringArgRetLong(moveToTrashBind, singleton, path)
    }

    /**
     * Returns the absolute directory path where user data is written (the `user://` directory in
     * Godot). The path depends on the project name and
     * `ProjectSettings.application/config/use_custom_user_dir`. - On Windows, this is
     * `%AppData%\Godot\app_userdata\`project_name``, or `%AppData%\`custom_name`` if
     * `use_custom_user_dir` is set. `%AppData%` expands to `%UserProfile%\AppData\Roaming`. - On
     * macOS, this is `~/Library/Application Support/Godot/app_userdata/`project_name``, or
     * `~/Library/Application Support/`custom_name`` if `use_custom_user_dir` is set. - On Linux and
     * BSD, this is `~/.local/share/godot/app_userdata/`project_name``, or
     * `~/.local/share/`custom_name`` if `use_custom_user_dir` is set. - On Android and iOS, this is a
     * sandboxed directory in either internal or external storage, depending on the user's
     * configuration. - On Web, this is a virtual directory managed by the browser. If the project name
     * is empty, ``project_name`` falls back to `[unnamed project]`. Not to be confused with
     * `get_data_dir`, which returns the global (non-project-specific) user home directory.
     *
     * Generated from Godot docs: OS.get_user_data_dir
     */
    @JvmStatic
    fun getUserDataDir(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getUserDataDirBind, singleton)
    }

    /**
     * Returns the path to commonly used folders across different platforms, as defined by `dir`. See
     * the `SystemDir` constants for available locations. Note: This method is implemented on Android,
     * Linux, macOS and Windows. Note: Shared storage is implemented on Android and allows to
     * differentiate between app specific and shared directories, if `shared_storage` is `true`. Shared
     * directories have additional restrictions on Android.
     *
     * Generated from Godot docs: OS.get_system_dir
     */
    @JvmStatic
    fun getSystemDir(dir: Long, sharedStorage: Boolean = true): String {
        return ObjectCalls.ptrcallWithLongAndBoolArgRetString(getSystemDirBind, singleton, dir, sharedStorage)
    }

    /**
     * Returns the global user configuration directory according to the operating system's standards.
     * On the Linux/BSD platform, this path can be overridden by setting the `XDG_CONFIG_HOME`
     * environment variable before starting the project. See File paths in Godot projects
     * ($DOCS_URL/tutorials/io/data_paths.html) in the documentation for more information. See also
     * `get_cache_dir` and `get_data_dir`. Not to be confused with `get_user_data_dir`, which returns
     * the project-specific user data path.
     *
     * Generated from Godot docs: OS.get_config_dir
     */
    @JvmStatic
    fun getConfigDir(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getConfigDirBind, singleton)
    }

    /**
     * Returns the global user data directory according to the operating system's standards. On the
     * Linux/BSD platform, this path can be overridden by setting the `XDG_DATA_HOME` environment
     * variable before starting the project. See File paths in Godot projects
     * ($DOCS_URL/tutorials/io/data_paths.html) in the documentation for more information. See also
     * `get_cache_dir` and `get_config_dir`. Not to be confused with `get_user_data_dir`, which returns
     * the project-specific user data path.
     *
     * Generated from Godot docs: OS.get_data_dir
     */
    @JvmStatic
    fun getDataDir(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getDataDirBind, singleton)
    }

    /**
     * Returns the global cache data directory according to the operating system's standards. On the
     * Linux/BSD platform, this path can be overridden by setting the `XDG_CACHE_HOME` environment
     * variable before starting the project. See File paths in Godot projects
     * ($DOCS_URL/tutorials/io/data_paths.html) in the documentation for more information. See also
     * `get_config_dir` and `get_data_dir`. Not to be confused with `get_user_data_dir`, which returns
     * the project-specific user data path.
     *
     * Generated from Godot docs: OS.get_cache_dir
     */
    @JvmStatic
    fun getCacheDir(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getCacheDirBind, singleton)
    }

    /**
     * Returns the global temporary data directory according to the operating system's standards.
     *
     * Generated from Godot docs: OS.get_temp_dir
     */
    @JvmStatic
    fun getTempDir(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getTempDirBind, singleton)
    }

    /**
     * Returns a string that is unique to the device. Note: This string may change without notice if
     * the user reinstalls their operating system, upgrades it, or modifies their hardware. This means
     * it should generally not be used to encrypt persistent data, as the data saved before an
     * unexpected ID change would become inaccessible. The returned string may also be falsified using
     * external programs, so do not rely on the string returned by this method for security purposes.
     * Note: On Web, returns an empty string and generates an error, as this method cannot be
     * implemented for security reasons.
     *
     * Generated from Godot docs: OS.get_unique_id
     */
    @JvmStatic
    fun getUniqueId(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getUniqueIdBind, singleton)
    }

    /**
     * Returns the given keycode as a `String`.
     *
     * Generated from Godot docs: OS.get_keycode_string
     */
    @JvmStatic
    fun getKeycodeString(code: Long): String {
        return ObjectCalls.ptrcallWithLongArgRetString(getKeycodeStringBind, singleton, code)
    }

    /**
     * Returns `true` if the input keycode corresponds to a Unicode character. For a list of codes, see
     * the `Key` constants.
     *
     * Generated from Godot docs: OS.is_keycode_unicode
     */
    @JvmStatic
    fun isKeycodeUnicode(code: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isKeycodeUnicodeBind, singleton, code)
    }

    /**
     * Finds the keycode for the given string. The returned values are equivalent to the `Key`
     * constants.
     *
     * Generated from Godot docs: OS.find_keycode_from_string
     */
    @JvmStatic
    fun findKeycodeFromString(string: String): Long {
        return ObjectCalls.ptrcallWithStringArgRetLong(findKeycodeFromStringBind, singleton, string)
    }

    /**
     * If `enabled` is `true`, when opening a file for writing, a temporary file is used in its place.
     * When closed, it is automatically applied to the target file. This can useful when files may be
     * opened by other applications, such as antiviruses, text editors, or even the Godot editor
     * itself.
     *
     * Generated from Godot docs: OS.set_use_file_access_save_and_swap
     */
    @JvmStatic
    fun setUseFileAccessSaveAndSwap(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseFileAccessSaveAndSwapBind, singleton, enabled)
    }

    /**
     * Assigns the given name to the current thread. Returns `ERR_UNAVAILABLE` if unavailable on the
     * current platform.
     *
     * Generated from Godot docs: OS.set_thread_name
     */
    @JvmStatic
    fun setThreadName(name: String): Long {
        return ObjectCalls.ptrcallWithStringArgRetLong(setThreadNameBind, singleton, name)
    }

    /**
     * Returns the ID of the current thread. This can be used in logs to ease debugging of
     * multi-threaded applications. Note: Thread IDs are not deterministic and may be reused across
     * application restarts.
     *
     * Generated from Godot docs: OS.get_thread_caller_id
     */
    @JvmStatic
    fun getThreadCallerId(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getThreadCallerIdBind, singleton)
    }

    /**
     * Returns the ID of the main thread. See `get_thread_caller_id`. Note: Thread IDs are not
     * deterministic and may be reused across application restarts.
     *
     * Generated from Godot docs: OS.get_main_thread_id
     */
    @JvmStatic
    fun getMainThreadId(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getMainThreadIdBind, singleton)
    }

    /**
     * Returns `true` if the feature for the given feature tag is supported in the currently running
     * instance, depending on the platform, build, etc. Can be used to check whether you're currently
     * running a debug build, on a certain platform or arch, etc. Refer to the Feature Tags
     * ($DOCS_URL/tutorials/export/feature_tags.html) documentation for more details. Note: Tag names
     * are case-sensitive. Note: On the Web platform, one of the following additional tags is defined
     * to indicate the host platform: `web_android`, `web_ios`, `web_linuxbsd`, `web_macos`, or
     * `web_windows`.
     *
     * Generated from Godot docs: OS.has_feature
     */
    @JvmStatic
    fun hasFeature(tagName: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(hasFeatureBind, singleton, tagName)
    }

    /**
     * Returns `true` if the application is running in the sandbox. Note: This method is only
     * implemented on macOS and Linux.
     *
     * Generated from Godot docs: OS.is_sandboxed
     */
    @JvmStatic
    fun isSandboxed(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSandboxedBind, singleton)
    }

    /**
     * Requests permission from the OS for the given `name`. Returns `true` if the permission has
     * already been granted. See also `MainLoop.on_request_permissions_result`. The `name` must be the
     * full permission name. For example: -
     * `OS.request_permission("android.permission.READ_EXTERNAL_STORAGE")` -
     * `OS.request_permission("android.permission.POST_NOTIFICATIONS")` -
     * `OS.request_permission("macos.permission.RECORD_SCREEN")` -
     * `OS.request_permission("appleembedded.permission.AUDIO_RECORD")` Note: On Android, permission
     * must be checked during export. Note: This method is implemented on Android, macOS, and visionOS
     * platforms.
     *
     * Generated from Godot docs: OS.request_permission
     */
    @JvmStatic
    fun requestPermission(name: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(requestPermissionBind, singleton, name)
    }

    /**
     * Requests dangerous permissions from the OS. Returns `true` if permissions have already been
     * granted. See also `MainLoop.on_request_permissions_result`. Note: Permissions must be checked
     * during export. Note: This method is only implemented on Android. Normal permissions are
     * automatically granted at install time in Android applications.
     *
     * Generated from Godot docs: OS.request_permissions
     */
    @JvmStatic
    fun requestPermissions(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(requestPermissionsBind, singleton)
    }

    /**
     * On Android devices: Returns the list of dangerous permissions that have been granted. On macOS:
     * Returns the list of granted permissions and user selected folders accessible to the application
     * (sandboxed applications only). Use the native file dialog to request folder access permission.
     * On iOS, visionOS: Returns the list of granted permissions.
     *
     * Generated from Godot docs: OS.get_granted_permissions
     */
    @JvmStatic
    fun getGrantedPermissions(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getGrantedPermissionsBind, singleton)
    }

    /**
     * On macOS (sandboxed applications only), this function clears list of user selected folders
     * accessible to the application.
     *
     * Generated from Godot docs: OS.revoke_granted_permissions
     */
    @JvmStatic
    fun revokeGrantedPermissions() {
        ObjectCalls.ptrcallNoArgs(revokeGrantedPermissionsBind, singleton)
    }

    /**
     * Add a custom logger to intercept the internal message stream.
     *
     * Generated from Godot docs: OS.add_logger
     */
    @JvmStatic
    fun addLogger(logger: Logger?) {
        ObjectCalls.ptrcallWithObjectArgs(addLoggerBind, singleton, listOf(logger?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Remove a custom logger added by `add_logger`.
     *
     * Generated from Godot docs: OS.remove_logger
     */
    @JvmStatic
    fun removeLogger(logger: Logger?) {
        ObjectCalls.ptrcallWithObjectArgs(removeLoggerBind, singleton, listOf(logger?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    @JvmStatic
    fun fromHandle(handle: MemorySegment): OS? =
        wrap(handle)

    internal fun wrap(handle: MemorySegment): OS? =
        if (handle.address() == 0L) null else this

    private const val GET_ENTROPY_HASH = 47165747L
    private val getEntropyBind by lazy {
        ObjectCalls.getMethodBind("OS", "get_entropy", GET_ENTROPY_HASH)
    }

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

    private const val GET_SYSTEM_FONT_PATH_HASH = 626580860L
    private val getSystemFontPathBind by lazy {
        ObjectCalls.getMethodBind("OS", "get_system_font_path", GET_SYSTEM_FONT_PATH_HASH)
    }

    private const val GET_SYSTEM_FONT_PATH_FOR_TEXT_HASH = 197317981L
    private val getSystemFontPathForTextBind by lazy {
        ObjectCalls.getMethodBind("OS", "get_system_font_path_for_text", GET_SYSTEM_FONT_PATH_FOR_TEXT_HASH)
    }

    private const val GET_EXECUTABLE_PATH_HASH = 201670096L
    private val getExecutablePathBind by lazy {
        ObjectCalls.getMethodBind("OS", "get_executable_path", GET_EXECUTABLE_PATH_HASH)
    }

    private const val READ_STRING_FROM_STDIN_HASH = 723587915L
    private val readStringFromStdinBind by lazy {
        ObjectCalls.getMethodBind("OS", "read_string_from_stdin", READ_STRING_FROM_STDIN_HASH)
    }

    private const val READ_BUFFER_FROM_STDIN_HASH = 3249455752L
    private val readBufferFromStdinBind by lazy {
        ObjectCalls.getMethodBind("OS", "read_buffer_from_stdin", READ_BUFFER_FROM_STDIN_HASH)
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

    private const val EXECUTE_HASH = 1488299882L
    private val executeBind by lazy {
        ObjectCalls.getMethodBind("OS", "execute", EXECUTE_HASH)
    }

    private const val EXECUTE_WITH_PIPE_HASH = 2851312030L
    private val executeWithPipeBind by lazy {
        ObjectCalls.getMethodBind("OS", "execute_with_pipe", EXECUTE_WITH_PIPE_HASH)
    }

    private const val CREATE_PROCESS_HASH = 2903767230L
    private val createProcessBind by lazy {
        ObjectCalls.getMethodBind("OS", "create_process", CREATE_PROCESS_HASH)
    }

    private const val CREATE_INSTANCE_HASH = 1080601263L
    private val createInstanceBind by lazy {
        ObjectCalls.getMethodBind("OS", "create_instance", CREATE_INSTANCE_HASH)
    }

    private const val OPEN_WITH_PROGRAM_HASH = 2848259907L
    private val openWithProgramBind by lazy {
        ObjectCalls.getMethodBind("OS", "open_with_program", OPEN_WITH_PROGRAM_HASH)
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

    private const val SET_RESTART_ON_EXIT_HASH = 3331453935L
    private val setRestartOnExitBind by lazy {
        ObjectCalls.getMethodBind("OS", "set_restart_on_exit", SET_RESTART_ON_EXIT_HASH)
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

    private const val GET_MEMORY_INFO_HASH = 3102165223L
    private val getMemoryInfoBind by lazy {
        ObjectCalls.getMethodBind("OS", "get_memory_info", GET_MEMORY_INFO_HASH)
    }

    private const val MOVE_TO_TRASH_HASH = 2113323047L
    private val moveToTrashBind by lazy {
        ObjectCalls.getMethodBind("OS", "move_to_trash", MOVE_TO_TRASH_HASH)
    }

    private const val GET_USER_DATA_DIR_HASH = 201670096L
    private val getUserDataDirBind by lazy {
        ObjectCalls.getMethodBind("OS", "get_user_data_dir", GET_USER_DATA_DIR_HASH)
    }

    private const val GET_SYSTEM_DIR_HASH = 3073895123L
    private val getSystemDirBind by lazy {
        ObjectCalls.getMethodBind("OS", "get_system_dir", GET_SYSTEM_DIR_HASH)
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

    private const val GET_KEYCODE_STRING_HASH = 2261993717L
    private val getKeycodeStringBind by lazy {
        ObjectCalls.getMethodBind("OS", "get_keycode_string", GET_KEYCODE_STRING_HASH)
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
