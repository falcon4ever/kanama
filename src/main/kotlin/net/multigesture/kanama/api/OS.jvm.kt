package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for OS (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP OS waits on: ptrcallWithFourStringTwoIntBoolArgsRetPackedStringList,
//   ptrcallWithStringPackedStringListArrayTwoBoolArgsRetInt
// Index: docs/reference/generated/ios-shape-gap.md

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

private val oSSingleton: MemorySegment by lazy {
    ObjectCalls.getSingleton("OS")
}

private const val GET_SYSTEM_FONT_PATH_FOR_TEXT_HASH = 197317981L
private val getSystemFontPathForTextBind by lazy {
    ObjectCalls.getMethodBind("OS", "get_system_font_path_for_text", GET_SYSTEM_FONT_PATH_FOR_TEXT_HASH)
}

private const val EXECUTE_HASH = 1488299882L
private val executeBind by lazy {
    ObjectCalls.getMethodBind("OS", "execute", EXECUTE_HASH)
}
