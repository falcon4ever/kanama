package net.multigesture.kanama.binding.runtime

import kotlin.concurrent.atomics.AtomicInt
import kotlin.concurrent.atomics.ExperimentalAtomicApi
import kotlin.concurrent.atomics.incrementAndFetch
import kotlin.experimental.ExperimentalNativeApi
import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.toKString
import net.multigesture.kanama.ios.cinterop.kanama_ios_report_script_error
import net.multigesture.kanama.ios.cinterop.kanama_ios_source_line

/**
 * The iOS half of task 131 item 1 (F4): a Kotlin exception contained at a script-call boundary (a
 * script method or virtual, `_ready`, a signal lambda) is printed to stderr and reported to Godot
 * as a script error through the shim's `kanama_ios_report_script_error`, so Godot's log and the
 * debugger show `SCRIPT ERROR:` with the Kotlin file:line of the top game frame. Before task 131
 * such an exception crossed the `@CName` export and terminated the app.
 *
 * The file and line come from Kotlin/Native's stack trace where it carries `(File.kt:line:column)`,
 * and otherwise -- an iOS app carries no DWARF, so its frames end at `kfun:<symbol> + <offset>`,
 * on the device and the simulator -- from the debug build's table of the game's functions
 * (`kanama_ios_source_line`, task 131 item 13). A release frame still names the
 * class and method, reported with an empty file and line 0. Never throws.
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
        throwable.getStackTrace().mapNotNull { ScriptErrorReport.parseNativeFrame(it, ::sourceOf) },
        nameOf(throwable),
      )
    }

  /** The device table's `(File.kt, line)` for a frame, or null when the frame is not in it. */
  private fun sourceOf(symbol: String, offset: Int): Pair<String, Int>? = memScoped {
    val file = allocArray<ByteVar>(SOURCE_FILE_CAPACITY)
    val line = kanama_ios_source_line(symbol, offset, file, SOURCE_FILE_CAPACITY)
    if (line > 0) file.toKString() to line else null
  }

  private const val SOURCE_FILE_CAPACITY = 256

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
