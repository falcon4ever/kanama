package net.multigesture.kanama.web

import kotlinx.coroutines.CoroutineExceptionHandler
import net.multigesture.kanama.api.WebScopeOwner
import net.multigesture.kanama.binding.runtime.ScriptErrorFrame
import net.multigesture.kanama.binding.runtime.ScriptErrorReport

/**
 * Task 131 item 14: a contained Kotlin exception in a Web script callback, reported the way desktop
 * reports one (`ScriptErrors.report`): the exception's type and message and the game frame that
 * threw. The shared [ScriptErrorReport] decides which frame that is; Web supplies the frames
 * ([framesOf]) and the way out ([sink], the bridge's `reportScriptError`, which reaches Godot's
 * `push_error`).
 */
internal object WebScriptErrors {
  /** Where a rendered report goes. Unset in a unit test, which reads [reportFor] instead. */
  var sink: ((String) -> Unit)? = null

  /** Re-entrancy guard: a report that fails inside the sink must not report again. */
  private var reporting = false

  /**
   * The last exception that escaped USER script code (see [userScript]). A callback boundary
   * contains an exception only when it is this one: the registry's own invariants ("Stale Kanama
   * Web signal callback id"), the frame scheduler's owner checks, a failing flush and an
   * untranslated bridge error are runtime failures that must still end the page, not a script error
   * to report and carry on from. Identity, not type: a user `throw IllegalStateException(...)` and
   * a runtime `check` failure look alike.
   */
  private var lastUserFailure: Throwable? = null

  /** Records that [error] came out of user script code. Called by [userScript]. */
  fun markUserFailure(error: Throwable) {
    lastUserFailure = error
  }

  /** Whether [error] is the exception that last escaped user script code. */
  fun isUserFailure(error: Throwable): Boolean = error === lastUserFailure

  /**
   * An exception in a script's coroutine is a script error: reported with its type and message, the
   * scope and the frame loop carry on (a `KanamaScope`'s SupervisorJob keeps its other children).
   */
  val coroutineHandler: CoroutineExceptionHandler = CoroutineExceptionHandler { context, error ->
    val owner = context[WebScopeOwner]?.ownerHandle ?: 0
    val script = webScriptInstance(owner)?.let { it::class.simpleName } ?: "script"
    report(error, "$script.<coroutine>")
  }

  /** Reports [t]. [where] names the containment site (`Player._ready`). Never throws. */
  fun report(t: Throwable, where: String): Boolean {
    if (reporting) return false
    reporting = true
    try {
      val send = sink ?: return false
      send(render(reportFor(t, where)))
      return true
    } catch (inner: Throwable) {
      return false
    } finally {
      reporting = false
    }
  }

  fun reportFor(t: Throwable, where: String): ScriptErrorReport =
    ScriptErrorReport.of(t, where, exceptionName(t), sourcelessGameFrames = true) { framesOf(it) }

  /**
   * The text Godot prints, laid out like desktop's `SCRIPT ERROR: <message>` / `at: <function>
   * (<file>:<line>)` pair. `push_error` supplies its own `ERROR:` prefix.
   */
  fun render(report: ScriptErrorReport): String {
    val place =
      when {
        report.file.isNotEmpty() && report.line > 0 -> "${report.file}:${report.line}"
        report.file.isNotEmpty() -> report.file
        else -> "no source line on Wasm"
      }
    return "SCRIPT ERROR: ${report.message}\n   at: ${report.function} ($place)"
  }

  fun exceptionName(t: Throwable): String =
    t::class.qualifiedName ?: t::class.simpleName ?: "Throwable"

  /**
   * The frames of [t]'s own stack, innermost first.
   *
   * What a Kotlin/Wasm stack trace provides (measured on the production export, Chrome): the text
   * of the JS `Error.stack` captured when the exception was created -- `at kotlin.createJsError`
   * and then one `at <url>.wasm:wasm-function[<index>]:0x<offset>` line per Wasm frame. The
   * production module carries no name section and publishes no source maps (a `.map` fails the
   * export), so those frames have neither a function name nor a Kotlin file and line: they cannot
   * be attributed to game code, and the report names the containment site (the script and the
   * callback that was running) instead. A build that keeps the name section, or a browser that
   * resolves a source map, produces `at <qualified.name> (<url>)` or `at <name> (File.kt:12:5)`
   * lines, which [parseFrame] turns into frames so the shared report picks the game frame.
   */
  fun framesOf(t: Throwable): List<ScriptErrorFrame> =
    // The trace starts inside the exception's own constructor chain (`Throwable.<init>`, ...),
    // which a build with names shows; drop it so the exception class is not the reported frame.
    t.stackTraceToString()
      .lineSequence()
      .mapNotNull(::parseFrame)
      .dropWhile { it.methodName == "<init>" }
      .toList()

  // Chrome / Node `    at name (location)` and Firefox / Safari `name@location`.
  private val CHROME_FRAME = Regex("""^\s*at\s+(\S+)\s+\((.*)\)\s*$""")
  private val GECKO_FRAME = Regex("""^\s*([^\s@]+)@(.*)$""")
  private val SOURCE_LOCATION = Regex("""([^/\\\s():]+\.kt):(\d+)(?::\d+)?\)?\s*$""")

  /**
   * One stack line as a frame, or null for a line that names no Kotlin function: the exception's
   * own header, an anonymous `wasm-function[N]` frame, a JS helper (`kotlin.createJsError`), a
   * frame in a JS file or a name without a package. [ScriptErrorFrame.className] is the qualified
   * name up to the last dot.
   */
  internal fun parseFrame(line: String): ScriptErrorFrame? {
    val match = CHROME_FRAME.find(line) ?: GECKO_FRAME.find(line) ?: return null
    val name = match.groupValues[1]
    val location = match.groupValues[2]
    if (name.startsWith("wasm-function[") || name == "<anonymous>" || '.' !in name) return null
    // JS helpers a Wasm trace passes through (`Object.createJsError__externalAdapter` in Node).
    if ("createJsError" in name || name.startsWith("Object.") || name.startsWith("Module."))
      return null
    val owner = name.substringBeforeLast('.')
    val method = name.substringAfterLast('.')
    val source = SOURCE_LOCATION.find(location)
    // Only a Wasm frame or a Kotlin source line is game code; a frame in a JS file is the browser's
    // (Firefox names its `requestAnimationFrame` callbacks) or the bridge's.
    if (source == null && ".wasm" !in location) return null
    return ScriptErrorFrame(
      className = owner,
      methodName = method,
      fileName = source?.groupValues?.get(1).orEmpty(),
      line = source?.groupValues?.get(2)?.toIntOrNull() ?: 0,
    )
  }
}

/**
 * Runs the user's script code (a lifecycle callback, a registered function, a property accessor, a
 * signal lambda) and records an exception that escapes it as a USER failure, rethrowing it
 * unchanged. Wrap only the call into user code, never the runtime around it (see
 * [WebScriptErrors.isUserFailure]).
 */
internal inline fun <T> userScript(block: () -> T): T =
  try {
    block()
  } catch (error: Throwable) {
    WebScriptErrors.markUserFailure(error)
    throw error
  }
