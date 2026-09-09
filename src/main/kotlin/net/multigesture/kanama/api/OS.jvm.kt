package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for OS (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP OS waits on: ptrcallNoArgsRetDictionary, ptrcallWithBoolAndPackedStringArrayArgs,
//   ptrcallWithFourStringTwoIntBoolArgsRetPackedStringList, ptrcallWithIntArgRetByteArray,
//   ptrcallWithLongAndBoolArgRetString, ptrcallWithLongArgRetByteArray, ptrcallWithLongArgRetString,
//   ptrcallWithPackedStringListArgRetInt, ptrcallWithStringAndPackedStringListArgRetLong,
//   ptrcallWithStringPackedStringListArrayTwoBoolArgsRetInt,
//   ptrcallWithStringPackedStringListBoolArgsRetDictionary,
//   ptrcallWithStringPackedStringListBoolArgsRetInt, ptrcallWithStringTwoIntBoolArgsRetString
// Index: docs/contributing/ios-shape-gap.md

/**
 * Generates a `PackedByteArray` of cryptographically secure random bytes with given `size`. Note:
 * Generating large quantities of bytes using this method can result in locking and entropy of
 * lower quality on most platforms. Using `Crypto.generate_random_bytes` is preferred in most
 * cases.
 *
 * Generated from Godot docs: OS.get_entropy
 */
fun OS.getEntropy(size: Int): ByteArray {
    return ObjectCalls.ptrcallWithIntArgRetByteArray(getEntropyBind, oSSingleton, size)
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
fun OS.getSystemFontPath(fontName: String, weight: Int = 400, stretch: Int = 100, italic: Boolean = false): String {
    return ObjectCalls.ptrcallWithStringTwoIntBoolArgsRetString(getSystemFontPathBind, oSSingleton, fontName, weight, stretch, italic)
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
fun OS.getSystemFontPathForText(fontName: String, text: String, locale: String = "", script: String = "", weight: Int = 400, stretch: Int = 100, italic: Boolean = false): List<String> {
    return ObjectCalls.ptrcallWithFourStringTwoIntBoolArgsRetPackedStringList(getSystemFontPathForTextBind, oSSingleton, fontName, text, locale, script, weight, stretch, italic)
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
fun OS.readStringFromStdin(bufferSize: Long = 1024L): String {
    return ObjectCalls.ptrcallWithLongArgRetString(readStringFromStdinBind, oSSingleton, bufferSize)
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
fun OS.readBufferFromStdin(bufferSize: Long = 1024L): ByteArray {
    return ObjectCalls.ptrcallWithLongArgRetByteArray(readBufferFromStdinBind, oSSingleton, bufferSize)
}

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
fun OS.execute(path: String, arguments: List<String>, output: List<Any?> = emptyList(), readStderr: Boolean = false, openConsole: Boolean = false): Int {
    return ObjectCalls.ptrcallWithStringPackedStringListArrayTwoBoolArgsRetInt(executeBind, oSSingleton, path, arguments, output, readStderr, openConsole)
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
fun OS.executeWithPipe(path: String, arguments: List<String>, blocking: Boolean = true): Map<String, Any?> {
    return ObjectCalls.ptrcallWithStringPackedStringListBoolArgsRetDictionary(executeWithPipeBind, oSSingleton, path, arguments, blocking)
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
fun OS.createProcess(path: String, arguments: List<String>, openConsole: Boolean = false): Int {
    return ObjectCalls.ptrcallWithStringPackedStringListBoolArgsRetInt(createProcessBind, oSSingleton, path, arguments, openConsole)
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
fun OS.createInstance(arguments: List<String>): Int {
    return ObjectCalls.ptrcallWithPackedStringListArgRetInt(createInstanceBind, oSSingleton, arguments)
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
fun OS.openWithProgram(programPath: String, paths: List<String>): Long {
    return ObjectCalls.ptrcallWithStringAndPackedStringListArgRetLong(openWithProgramBind, oSSingleton, programPath, paths)
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
fun OS.setRestartOnExit(restart: Boolean, arguments: List<String>) {
    ObjectCalls.ptrcallWithBoolAndPackedStringArrayArgs(setRestartOnExitBind, oSSingleton, restart, arguments)
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
fun OS.getMemoryInfo(): Map<String, Any?> {
    return ObjectCalls.ptrcallNoArgsRetDictionary(getMemoryInfoBind, oSSingleton)
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
fun OS.getSystemDir(dir: Long, sharedStorage: Boolean = true): String {
    return ObjectCalls.ptrcallWithLongAndBoolArgRetString(getSystemDirBind, oSSingleton, dir, sharedStorage)
}

/**
 * Returns the given keycode as a `String`.
 *
 * Generated from Godot docs: OS.get_keycode_string
 */
fun OS.getKeycodeString(code: Long): String {
    return ObjectCalls.ptrcallWithLongArgRetString(getKeycodeStringBind, oSSingleton, code)
}

private val oSSingleton: MemorySegment by lazy {
    ObjectCalls.getSingleton("OS")
}

private const val GET_ENTROPY_HASH = 47165747L
private val getEntropyBind by lazy {
    ObjectCalls.getMethodBind("OS", "get_entropy", GET_ENTROPY_HASH)
}

private const val GET_SYSTEM_FONT_PATH_HASH = 626580860L
private val getSystemFontPathBind by lazy {
    ObjectCalls.getMethodBind("OS", "get_system_font_path", GET_SYSTEM_FONT_PATH_HASH)
}

private const val GET_SYSTEM_FONT_PATH_FOR_TEXT_HASH = 197317981L
private val getSystemFontPathForTextBind by lazy {
    ObjectCalls.getMethodBind("OS", "get_system_font_path_for_text", GET_SYSTEM_FONT_PATH_FOR_TEXT_HASH)
}

private const val READ_STRING_FROM_STDIN_HASH = 723587915L
private val readStringFromStdinBind by lazy {
    ObjectCalls.getMethodBind("OS", "read_string_from_stdin", READ_STRING_FROM_STDIN_HASH)
}

private const val READ_BUFFER_FROM_STDIN_HASH = 3249455752L
private val readBufferFromStdinBind by lazy {
    ObjectCalls.getMethodBind("OS", "read_buffer_from_stdin", READ_BUFFER_FROM_STDIN_HASH)
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

private const val SET_RESTART_ON_EXIT_HASH = 3331453935L
private val setRestartOnExitBind by lazy {
    ObjectCalls.getMethodBind("OS", "set_restart_on_exit", SET_RESTART_ON_EXIT_HASH)
}

private const val GET_MEMORY_INFO_HASH = 3102165223L
private val getMemoryInfoBind by lazy {
    ObjectCalls.getMethodBind("OS", "get_memory_info", GET_MEMORY_INFO_HASH)
}

private const val GET_SYSTEM_DIR_HASH = 3073895123L
private val getSystemDirBind by lazy {
    ObjectCalls.getMethodBind("OS", "get_system_dir", GET_SYSTEM_DIR_HASH)
}

private const val GET_KEYCODE_STRING_HASH = 2261993717L
private val getKeycodeStringBind by lazy {
    ObjectCalls.getMethodBind("OS", "get_keycode_string", GET_KEYCODE_STRING_HASH)
}
