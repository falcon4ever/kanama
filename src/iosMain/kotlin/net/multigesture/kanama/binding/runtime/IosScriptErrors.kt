package net.multigesture.kanama.binding.runtime

import kotlin.concurrent.atomics.AtomicInt
import kotlin.concurrent.atomics.ExperimentalAtomicApi
import kotlin.concurrent.atomics.incrementAndFetch
import kotlin.experimental.ExperimentalNativeApi
import kotlinx.cinterop.ExperimentalForeignApi
import net.multigesture.kanama.ios.cinterop.kanama_ios_report_script_error

/**
 * The iOS half of task 131 item 1 (F4): a Kotlin exception contained at a script-call boundary (a
 * script method or virtual, `_ready`, a signal lambda) is printed to stderr and reported to Godot
 * as a script error through the shim's `kanama_ios_report_script_error`, so Godot's log and the
 * debugger show `SCRIPT ERROR:` with the Kotlin file:line of the top game frame. Before task 131
 * such an exception crossed the `@CName` export and terminated the app.
 *
 * The file and line come from Kotlin/Native's symbolicated stack trace, which carries
 * `(File.kt:line:column)` only when the binary has source info (debug builds); a release frame
 * still names the class and method, reported with an empty file and line 0. Never throws.
 */
@OptIn(ExperimentalForeignApi::class, ExperimentalNativeApi::class, ExperimentalAtomicApi::class)
object IosScriptErrors {
  private val reports = AtomicInt(0)

  /** Reports handed to the engine so far; the self-test's containment row reads it. */
  val reportCount: Int
    get() = reports.load()

  /** The report [report] sends for [t]; pure. */
  fun reportFor(t: Throwable, where: String): ScriptErrorReport =
    ScriptErrorReport.of(t, where, nameOf(t), sourcelessGameFrames = true) { throwable ->
      ScriptErrorReport.dropOwnConstructorFrames(
        throwable.getStackTrace().mapNotNull { ScriptErrorReport.parseNativeFrame(it) },
        nameOf(throwable),
      )
    }

  private fun nameOf(t: Throwable): String =
    t::class.qualifiedName ?: t::class.simpleName ?: "Throwable"

  /** Prints [t] and sends it to Godot; returns true when the engine received the error. */
  fun report(t: Throwable, where: String): Boolean {
    // Re-entrancy guard, per thread: a report raised while reporting (inside Godot's error
    // handler) is dropped, while reports from other threads still go through.
    if (ReportingFlag.active) return false
    ReportingFlag.active = true
    try {
      val report = reportFor(t, where)
      val delivered =
        kanama_ios_report_script_error(
          report.description,
          report.message,
          report.function,
          report.file,
          report.line,
        ) != 0
      if (delivered) reports.incrementAndFetch()
      runCatching { t.printStackTrace() }
      return delivered
    } catch (_: Throwable) {
      return false
    } finally {
      ReportingFlag.active = false
    }
  }
}

@kotlin.native.concurrent.ThreadLocal
private object ReportingFlag {
  var active = false
}
