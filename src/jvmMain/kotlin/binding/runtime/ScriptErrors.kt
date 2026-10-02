package net.multigesture.kanama.binding.runtime

import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.ValueLayout.ADDRESS
import java.lang.foreign.ValueLayout.JAVA_BYTE
import java.lang.foreign.ValueLayout.JAVA_INT
import java.lang.invoke.MethodHandle
import net.multigesture.kanama.ffi.GodotFFI

/**
 * Reports a contained Kotlin exception to Godot as a script error (task 131 item 1, F4).
 *
 * Every containment handler on the desktop/Android backend -- `ScriptBridge.siCall` (script methods
 * and lifecycle virtuals), the property accessors, and the [Upcalls] floor under every other upcall
 * -- used to print the exception to the process stderr only. The editor's Play button starts the
 * game without a pipe, so those traces never reached the Output panel or the Errors tab. [report]
 * keeps the stderr trace (the caller still prints it) and also calls GDExtension
 * `print_script_error_with_message` with the Kotlin file and line of the top user frame, so Godot
 * prints `SCRIPT ERROR: ...` in its own log and its debugger shows the error in the Errors tab.
 *
 * Never throws: it runs inside the catch that exists to keep an exception from escaping an FFM
 * upcall.
 */
object ScriptErrors {

  /** `void (*)(const char*, const char*, const char*, const char*, int32_t, GDExtensionBool)`. */
  val descriptor: FunctionDescriptor =
    FunctionDescriptor.ofVoid(ADDRESS, ADDRESS, ADDRESS, ADDRESS, JAVA_INT, JAVA_BYTE)

  @Volatile private var printScriptError: MethodHandle? = null

  /**
   * Test seam: when set, reports go here instead of to the engine. JVM unit tests have no Godot to
   * call.
   */
  @Volatile internal var sinkOverride: ((ScriptErrorReport) -> Unit)? = null

  /** Re-entrancy guard: a report that fails inside Godot's error handler must not report again. */
  private val reporting = ThreadLocal.withInitial { false }

  /**
   * Resolves `print_script_error_with_message`. Called from `KanamaBinding.init` after the native
   * call surface is prewarmed, so the bind generates no adapter (task 83). A missing entry point
   * leaves [report] as a no-op beyond the caller's stderr line.
   */
  fun bind() {
    printScriptError =
      runCatching { GodotFFI.lookup("print_script_error_with_message", descriptor) }
        .onFailure {
          System.err.println("[kanama:kt] script error reporting unavailable: ${it.message}")
        }
        .getOrNull()
  }

  /** The report [report] would send for [t]; pure, so it is unit-testable. */
  fun reportFor(t: Throwable, where: String): ScriptErrorReport =
    ScriptErrorReport.of(t, where, t.javaClass.name) { throwable ->
      throwable.stackTrace.map {
        ScriptErrorFrame(
          className = it.className,
          methodName = it.methodName,
          fileName = it.fileName.orEmpty(),
          line = it.lineNumber.coerceAtLeast(0),
        )
      }
    }

  /**
   * Sends [t] to Godot as a script error; [where] names the containment site (it stands in for the
   * function when no user frame is found). Returns true when the error reached the engine (or the
   * test sink).
   */
  fun report(t: Throwable, where: String): Boolean {
    if (reporting.get()) return false
    reporting.set(true)
    try {
      val report = reportFor(t, where)
      sinkOverride?.let {
        it(report)
        return true
      }
      val handle = printScriptError ?: return false
      Arena.ofConfined().use { arena ->
        handle.invoke(
          arena.allocateFrom(report.description),
          arena.allocateFrom(report.message),
          arena.allocateFrom(report.function),
          arena.allocateFrom(report.file),
          report.line,
          1.toByte(), // p_editor_notify
        )
      }
      return true
    } catch (inner: Throwable) {
      runCatching {
        System.err.println(
          "[kanama:kt] script error report failed: ${inner.javaClass.name}: ${inner.message}"
        )
      }
      return false
    } finally {
      reporting.set(false)
    }
  }
}
