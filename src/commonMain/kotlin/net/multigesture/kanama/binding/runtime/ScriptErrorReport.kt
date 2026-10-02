package net.multigesture.kanama.binding.runtime

/**
 * A contained Kotlin exception rendered as the fields of GDExtension's
 * `print_script_error_with_message` (task 131 item 1): Godot prints it as `SCRIPT ERROR: <message>`
 * / `at: <function> (<file>:<line>)` and its debugger shows it in the editor's Errors tab, which is
 * what a GDScript error does. Before this a Kotlin exception reached only the process stderr, which
 * the editor's Play button does not capture.
 *
 * Shared by the desktop/Android backend (frames from `Throwable.stackTrace`) and iOS (frames parsed
 * from Kotlin/Native's `getStackTrace()` lines by [parseNativeFrame]); each backend only supplies
 * its frames and its way of reaching the engine.
 */
class ScriptErrorReport(
  /** The error condition: the exception's class name (Godot's `<Kotlin Error>` row). */
  val description: String,
  /** `<exception class>: <message>`, the line Godot prints after `SCRIPT ERROR:`. */
  val message: String,
  /** `<SimpleClass>.<method>` of the top user frame, or the containment site if none. */
  val function: String,
  /** The Kotlin source file of the top user frame (`Player.kt`), or `""` when unknown. */
  val file: String,
  /** The line in [file], or 0 when unknown. */
  val line: Int,
  /** The reported frame's class (or Kotlin/Native package), `""` when none: locates [file]. */
  val frameClass: String = "",
) {
  /** This report with [file] replaced by [path] (a `res://` path the editor can open). */
  fun withFile(path: String): ScriptErrorReport =
    ScriptErrorReport(description, message, function, path, line, frameClass)

  override fun toString(): String = "$message at $function ($file:$line)"

  companion object {
    /**
     * Builds the report for [t]. The reported frame is the top frame of [t] (then of its causes)
     * that is game code -- not Kanama runtime, generated glue, the Kotlin/Java standard library or
     * a platform class -- and carries a source file and line: the line of the game script that
     * threw or that made the throwing call. A frame without source info (an R8-minified Android
     * build names classes `a.b`, files `SourceFile`) is never attributed as game code, since its
     * class may be an obfuscated runtime one; [sourcelessGameFrames] relaxes that for
     * Kotlin/Native, whose release frames keep real names but carry no file. Failing a game frame,
     * the top Kanama frame with source is reported, then just [where], the containment site.
     * [exceptionName] is the backend's name for `t`'s class (`t.javaClass.name` on the JVM: plain
     * Java reflection, which survives R8, unlike `KClass.qualifiedName`).
     */
    fun of(
      t: Throwable,
      where: String,
      exceptionName: String,
      sourcelessGameFrames: Boolean = false,
      framesOf: (Throwable) -> List<ScriptErrorFrame>,
    ): ScriptErrorReport {
      val frame = reportedFrame(t, sourcelessGameFrames, framesOf)
      val text = t.message
      return ScriptErrorReport(
        description = exceptionName,
        message = if (text.isNullOrEmpty()) exceptionName else "$exceptionName: $text",
        function = frame?.let(::functionName) ?: where,
        file = frame?.fileName.orEmpty(),
        line = frame?.line ?: 0,
        frameClass = frame?.className.orEmpty(),
      )
    }

    /**
     * `Player.ready` for a member; just `helper` for a top-level function, whose owner is a file
     * facade (`PlayerKt`, kept: it names the file) on the JVM and a package on Kotlin/Native.
     */
    private fun functionName(frame: ScriptErrorFrame): String {
      val owner = frame.className.substringAfterLast('.')
      return if (owner.firstOrNull()?.isUpperCase() == true) "$owner.${frame.methodName}"
      else frame.methodName
    }

    private fun hasSource(frame: ScriptErrorFrame): Boolean =
      frame.line > 0 &&
        frame.fileName.isNotEmpty() &&
        frame.fileName != "SourceFile" &&
        frame.fileName != "Unknown Source"

    private fun reportedFrame(
      t: Throwable,
      sourcelessGameFrames: Boolean,
      framesOf: (Throwable) -> List<ScriptErrorFrame>,
    ): ScriptErrorFrame? {
      var current: Throwable? = t
      var depth = 0
      var sourcelessGame: ScriptErrorFrame? = null
      var kanamaFrame: ScriptErrorFrame? = null
      while (current != null && depth < MAX_CAUSE_DEPTH) {
        val frames = framesOf(current)
        frames
          .firstOrNull { !isRuntimeClass(it.className) && hasSource(it) }
          ?.let {
            return it
          }
        if (sourcelessGameFrames && sourcelessGame == null) {
          sourcelessGame = frames.firstOrNull { !isRuntimeClass(it.className) }
        }
        if (kanamaFrame == null) {
          kanamaFrame = frames.firstOrNull { isKanamaClass(it.className) && hasSource(it) }
        }
        current = current.cause
        depth++
      }
      return sourcelessGame ?: kanamaFrame
    }

    /**
     * Drops the exception's own constructor frames from the top of a Kotlin/Native trace, which
     * (unlike the JVM's) starts inside `Throwable`'s constructor chain: `kotlin.Throwable#<init>`,
     * ..., `com.example.MyError#<init>`, then the throw site. Without this a game-defined exception
     * class would be reported as its own constructor. The run is dropped through the frame of
     * [exceptionClassName]; if that name is not found, only the leading platform (`kotlin.*`)
     * constructor frames are dropped, so a game constructor that threw is kept.
     */
    fun dropOwnConstructorFrames(
      frames: List<ScriptErrorFrame>,
      exceptionClassName: String,
    ): List<ScriptErrorFrame> {
      val run =
        frames.indexOfFirst { it.methodName != "<init>" }.let { if (it < 0) frames.size else it }
      val own = (0 until run).lastOrNull { frames[it].className == exceptionClassName }
      if (own != null) return frames.drop(own + 1)
      val platform = (0 until run).takeWhile { isPlatformClass(frames[it].className) }.count()
      return frames.drop(platform)
    }

    private const val MAX_CAUSE_DEPTH = 8

    // Kanama's runtime packages, listed one by one: game scripts may live under
    // net.multigesture.kanama.* too (the example project uses net.multigesture.kanama.example), so
    // the root package is never matched as a prefix.
    private val KANAMA_PREFIXES =
      listOf(
        "net.multigesture.kanama.api.",
        "net.multigesture.kanama.binding.",
        "net.multigesture.kanama.generated.",
        "net.multigesture.kanama.ffi.",
        "net.multigesture.kanama.types.",
        "net.multigesture.kanama.ios.",
        "net.multigesture.kanama.annotations.",
        "net.multigesture.kanama.web.",
        // The one runtime class in the root package (src/jvmMain/kotlin/KanamaBinding.kt).
        "net.multigesture.kanama.KanamaBinding.",
        "net.multigesture.kanama.KanamaBinding$",
      )

    private val PLATFORM_PREFIXES =
      listOf(
        "kotlin.",
        "kotlinx.",
        "java.",
        "javax.",
        "jdk.",
        "sun.",
        "com.sun.",
        "com.v7878.",
        "dalvik.",
        "libcore.",
        "android.",
        "com.android.",
      )

    /**
     * True for a class (or, for a Kotlin/Native top-level function, a package) that belongs to the
     * runtime or the platform rather than to the game: such a frame is never the reported game
     * line. An empty owner is a top-level function in the default package: game code.
     */
    fun isRuntimeClass(className: String): Boolean =
      isPlatformClass(className) || isKanamaClass(className)

    private fun isKanamaClass(className: String): Boolean {
      val dotted = "$className."
      return KANAMA_PREFIXES.any { dotted.startsWith(it) || className.startsWith(it) }
    }

    private fun isPlatformClass(className: String): Boolean {
      val dotted = "$className."
      return PLATFORM_PREFIXES.any { dotted.startsWith(it) }
    }

    // `kfun:<owner>#<function>`; the owner is empty for a top-level function in the default
    // package.
    private val NATIVE_FRAME = Regex("""kfun:([^#\s(]*)#([^(\s]*)""")
    private val NATIVE_SOURCE = Regex("""\(([^()\s]*?)([^/()\s]+\.kt):(\d+)(?::\d+)?\)\s*$""")

    /**
     * Parses one Kotlin/Native `Throwable.getStackTrace()` line, e.g. `at 3 libfoo 0x1049c2f3c
     * kfun:com.example.Player#ready(){} + 52 (/src/Player.kt:12:5)`. The `(file:line:col)` suffix
     * is present only when the binary carries source info (debug builds); without it the frame
     * still names the class and the method, with an empty file and line 0. A line without a `kfun:`
     * symbol (a C or Objective-C frame) yields null.
     */
    fun parseNativeFrame(line: String): ScriptErrorFrame? {
      val symbol = NATIVE_FRAME.find(line) ?: return null
      val owner = symbol.groupValues[1]
      val method = symbol.groupValues[2].ifEmpty { "<anonymous>" }
      val source = NATIVE_SOURCE.find(line)
      return ScriptErrorFrame(
        className = owner,
        methodName = method,
        fileName = source?.groupValues?.get(2).orEmpty(),
        line = source?.groupValues?.get(3)?.toIntOrNull() ?: 0,
      )
    }
  }
}

/** One stack frame, reduced to what [ScriptErrorReport] needs. */
class ScriptErrorFrame(
  val className: String,
  val methodName: String,
  val fileName: String,
  val line: Int,
)
