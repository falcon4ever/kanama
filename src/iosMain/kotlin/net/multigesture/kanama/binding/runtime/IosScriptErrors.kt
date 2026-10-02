package net.multigesture.kanama.binding.runtime

import kotlin.experimental.ExperimentalNativeApi
import kotlinx.cinterop.ExperimentalForeignApi
import net.multigesture.kanama.ios.cinterop.kanama_ios_report_script_error

/**
 * The iOS half of task 131 item 1 (F4): a Kotlin exception contained at a script-call boundary is
 * printed to stderr (its stack trace, as before the containment existed on iOS -- an exception
 * crossing a `@CName` export used to terminate the app) and reported to Godot as a script error
 * through the shim's `kanama_ios_report_script_error`, so Godot's log and the debugger show `SCRIPT
 * ERROR:` with the Kotlin file:line of the top game frame.
 *
 * The file and line come from Kotlin/Native's symbolicated stack trace, which carries
 * `(File.kt:line:column)` only when the binary has source info (debug builds); otherwise the frame
 * still names the class and method, with an empty file and line 0. Never throws.
 */
@OptIn(ExperimentalForeignApi::class, ExperimentalNativeApi::class)
object IosScriptErrors {
  private var reporting = false

  /** The report [report] sends for [t]; pure. */
  fun reportFor(t: Throwable, where: String): ScriptErrorReport =
    ScriptErrorReport.of(t, where, t::class.qualifiedName ?: t::class.simpleName ?: "Throwable") {
      throwable ->
      throwable.getStackTrace().mapNotNull { ScriptErrorReport.parseNativeFrame(it) }
    }

  /** Prints [t] and sends it to Godot; returns true when the engine received the error. */
  fun report(t: Throwable, where: String): Boolean {
    if (reporting) return false
    reporting = true
    try {
      runCatching { t.printStackTrace() }
      val report = reportFor(t, where)
      return kanama_ios_report_script_error(
        report.description,
        report.message,
        report.function,
        report.file,
        report.line,
      ) != 0
    } catch (_: Throwable) {
      return false
    } finally {
      reporting = false
    }
  }
}
